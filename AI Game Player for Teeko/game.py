import random
import math
import time
import sys

class TeekoPlayer:
    board = [[' ' for j in range(5)] for i in range(5)]
    pieces = ['b', 'r']

    def __init__(self, playAs=None, maxDepth=5, rand=False):
        self.my_piece = random.choice(self.pieces) if playAs is None else playAs
        self.opp = self.pieces[0] if self.my_piece == self.pieces[1] else self.pieces[1]
        self.pieceMap = {i: [] for i in self.pieces}
        self.maxDepth = maxDepth
        self.random = rand
        self.visited = set()

    def changeForMiniMax(self, state, pieceMap, move, piece, unmake=False):
        if not unmake:
            if len(move) > 1:
                state[move[1][0]][move[1][1]] = ' '
                pieceMap[piece].remove((move[1][0], move[1][1]))

            state[move[0][0]][move[0][1]] = piece
            pieceMap[piece].append((move[0][0], move[0][1]))
        else:
            if len(move) > 1:
                state[move[1][0]][move[1][1]] = piece
                pieceMap[piece].append((move[1][0], move[1][1]))

            state[move[0][0]][move[0][1]] = ' '
            pieceMap[piece].remove((move[0][0], move[0][1]))

    def minimax(self, state, minOrMax, pieceMap, depth, prevMove, alpha, beta):
        g_v = self.game_value(state)
        if g_v != 0:
            return g_v, prevMove

        if depth == self.maxDepth:
            return self.heuristic_game_value(state, pieceMap), prevMove

        best_val = -1 * minOrMax * math.inf
        best_move = None

        moves = self.validMoves(state, minOrMax == 1, pieceMap)
        random.shuffle(moves)
        for i in moves:
            self.changeForMiniMax(state, pieceMap, i, self.my_piece if minOrMax == 1 else self.opp)
            val, move = self.minimax(state, -1 * minOrMax, pieceMap, depth + 1, i, alpha, beta)
            self.changeForMiniMax(state, pieceMap, i, self.my_piece if minOrMax == 1 else self.opp, True)
            if minOrMax * (val - best_val) > 0:
                best_val = val
                best_move = i
            if minOrMax == 1:
                alpha = max(alpha, best_val)
            else:
                beta = min(beta, best_val)

            if alpha >= beta:
                break

        return best_val, best_move

    def validMoves(self, state, myTurn=True, pieceMap=None):
        pieceMap = self.pieceMap if pieceMap is None else pieceMap
        validMoves = []
        pieceCount = sum([len(pieceMap[i]) for i in self.pieces])
        if pieceCount < 8:
            for i in range(5):
                for j in range(5):
                    if state[i][j] == ' ':
                        validMoves.append([(i, j)])
        else:
            piece_loc = pieceMap[self.my_piece if myTurn else self.opp]
            for loc in piece_loc:
                for i in range(9):
                    if i == 4:
                        continue
                    c_i, c_j = i // 3 - 1, i % 3 - 1
                    i, j = loc[0] + c_i, loc[1] + c_j
                    if 0 <= i < 5 and 0 <= j < 5 and state[i][j] == ' ':
                        validMoves.append([(i, j), loc])
        return validMoves

    def make_move(self, state):
        move = None
        if self.random:
            move = random.choice(self.validMoves(state))
        else:
            val, move = self.minimax(state, 1, self.pieceMap, 0, None, -math.inf, math.inf)
        return move

    def opponent_move(self, move):
        if len(move) > 1:
            source_row = move[1][0]
            source_col = move[1][1]
            if source_row is not None and self.board[source_row][source_col] != self.opp:
                raise Exception("You don't have a piece there!")
            if abs(source_row - move[0][0]) > 1 or abs(source_col - move[0][1]) > 1:
                raise Exception('Illegal move: Can only move to an adjacent space')
        if self.board[move[0][0]][move[0][1]] != ' ':
            raise Exception("Illegal move detected")
        self.place_piece(move, self.opp)

    def place_piece(self, move, piece):
        if len(move) > 1:
            self.board[move[1][0]][move[1][1]] = ' '
            self.pieceMap[piece].remove((move[1][0], move[1][1]))

        self.board[move[0][0]][move[0][1]] = piece
        self.pieceMap[piece].append((move[0][0], move[0][1]))
        self.visited.add(str(self.board))

    def print_board(self):
        for row in range(len(self.board)):
            line = str(row) + ": "
            for cell in self.board[row]:
                line += cell + " "
            print(line)
        print("   A B C D E")

    def game_value(self, state):
        for row in state:
            for i in range(2):
                if row[i] != ' ' and row[i] == row[i + 1] == row[i + 2] == row[i + 3]:
                    return 1 if row[i] == self.my_piece else -1

        for col in range(5):
            for i in range(2):
                if state[i][col] != ' ' and state[i][col] == state[i + 1][col] == state[i + 2][col] == state[i + 3][
                    col]:
                    return 1 if state[i][col] == self.my_piece else -1

        for k in range(4):
            i_off, j_off = k // 2, k % 2

            win = state[i_off][j_off]
            win2 = state[i_off][j_off + 3]
            for i in range(4):
                if win != state[i + i_off][i + j_off] or state[i + i_off][i + j_off] == ' ':
                    win = None
                if win2 != state[i + i_off][3 + j_off - i] or state[i + i_off][3 - i + j_off] == ' ':
                    win2 = None
                if win == None and win2 == None:
                    break
            if win != None:
                return 1 if win == self.my_piece else -1
            if win2 != None:
                return 1 if win2 == self.my_piece else -1

        for i in range(4):
            for j in range(4):
                if state[i][j] != ' ' and state[i][j] == state[i + 1][j] == state[i][j + 1] == state[i + 1][j + 1]:
                    return 1 if state[i][j] == self.my_piece else -1

        return 0

    def heuristic_game_value(self, state, pieceMap: dict):
        if str(state) in self.visited:
            return -0.8
        val = {}
        for i in pieceMap.keys():
            coords = pieceMap[i]
            amt = len(pieceMap[i])
            if amt == 0:
                continue
            avg = [0, 0]
            for j in coords:
                avg[0] += j[0]
                avg[1] += j[1]
            avg[0] = avg[0] / amt
            avg[1] = avg[1] / amt

            variance = [0, 0]
            for j in coords:
                variance[0] += (j[0] - avg[0]) ** 2
                variance[1] += (j[1] - avg[1]) ** 2
            variance[0] = variance[0] / amt
            variance[1] = variance[1] / amt

            val[i] = math.sqrt(variance[0] * 2 + variance[1] * 2)
        d1 = val[self.my_piece]
        d2 = val[self.opp]

        area_indexes1 = [(0, 1), (1, 2), (2, 3), (3, 0)]
        area_indexes2 = [(1, 0), (2, 1), (3, 2), (0, 3)]

        my_pieces = pieceMap[self.my_piece]
        opp_pieces = pieceMap[self.opp]

        if len(my_pieces) < 4 or len(opp_pieces) < 4:
            if d1 == 0 and d2 == 0:
                return 0
            return (d2 - d1) / (d1 + d2)

        my_pieces.sort()
        opp_pieces.sort()

        a1 = 0
        a2 = 0

        for i in area_indexes1:
            a1 += my_pieces[i[0]][0] * my_pieces[i[1]][1]
            a2 += opp_pieces[i[0]][0] * opp_pieces[i[1]][1]
        for i in area_indexes2:
            a1 -= my_pieces[i[0]][0] * my_pieces[i[1]][1]
            a2 -= opp_pieces[i[0]][0] * opp_pieces[i[1]][1]
        if d1 + d2 == 0:
            return (a2 - a1) / 50
        return 0.2 * (d2 - d1) / (d1 + d2) + 0.4 * (a2 - a1) / 50 + 0.2 * (50 / (a2 + 50)) - 0.2 * (50 / (a1 + 50))



############################################################################
#
# THE FOLLOWING CODE IS FOR SAMPLE GAMEPLAY ONLY
#
############################################################################
def main():
    print('Hello, this is Samaritan')
    ai = TeekoPlayer()
    piece_count = 0
    turn = 0

    # drop phase
    while piece_count < 8 and ai.game_value(ai.board) == 0:

        # get the player or AI's move
        if ai.my_piece == ai.pieces[turn]:
            ai.print_board()
            move = ai.make_move(ai.board)
            ai.place_piece(move, ai.my_piece)
            print(ai.my_piece+" moved at "+chr(move[0][1]+ord("A"))+str(move[0][0]))
        else:
            move_made = False
            ai.print_board()
            print(ai.opp+"'s turn")
            while not move_made:
                player_move = input("Move (e.g. B3): ")
                while player_move[0] not in "ABCDE" or player_move[1] not in "01234":
                    player_move = input("Move (e.g. B3): ")
                try:
                    ai.opponent_move([(int(player_move[1]), ord(player_move[0])-ord("A"))])
                    move_made = True
                except Exception as e:
                    print(e)

        # update the game variables
        piece_count += 1
        turn += 1
        turn %= 2

    # move phase - can't have a winner until all 8 pieces are on the board
    while ai.game_value(ai.board) == 0:

        # get the player or AI's move
        if ai.my_piece == ai.pieces[turn]:
            ai.print_board()
            move = ai.make_move(ai.board)
            ai.place_piece(move, ai.my_piece)
            print(ai.my_piece+" moved from "+chr(move[1][1]+ord("A"))+str(move[1][0]))
            print("  to "+chr(move[0][1]+ord("A"))+str(move[0][0]))
        else:
            move_made = False
            ai.print_board()
            print(ai.opp+"'s turn")
            while not move_made:
                move_from = input("Move from (e.g. B3): ")
                while move_from[0] not in "ABCDE" or move_from[1] not in "01234":
                    move_from = input("Move from (e.g. B3): ")
                move_to = input("Move to (e.g. B3): ")
                while move_to[0] not in "ABCDE" or move_to[1] not in "01234":
                    move_to = input("Move to (e.g. B3): ")
                try:
                    ai.opponent_move([(int(move_to[1]), ord(move_to[0])-ord("A")),
                                    (int(move_from[1]), ord(move_from[0])-ord("A"))])
                    move_made = True
                except Exception as e:
                    print(e)

        # update the game variables
        turn += 1
        turn %= 2

    ai.print_board()
    if ai.game_value(ai.board) == 1:
        print("AI wins! Game over.")
    else:
        print("You win! Game over.")


if __name__ == "__main__":
    main()
