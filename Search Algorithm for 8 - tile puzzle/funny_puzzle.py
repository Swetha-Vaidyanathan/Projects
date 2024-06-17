import heapq

def get_manhattan_distance(from_state, to_state=[1, 2, 3, 4, 5, 6, 7, 0, 0]):
    """
    Computes the Manhattan distance between two states.

    Args:
        from_state (list): The current state.
        to_state (list): The goal state. Default is [1, 2, 3, 4, 5, 6, 7, 0, 0].

    Returns:
        int: The sum of Manhattan distances for all tiles.
    """
    distance = 0
    for s, g in ((from_state.index(i), to_state.index(i)) for i in range(1, 8)):
        distance += abs(s // 3 - g // 3) + abs(s % 3 - g % 3)
    return distance

def print_succ(state):
    """
    Prints the list of all the valid successors in the puzzle.

    Args:
        state (list): A state of the puzzle represented as a list of length 9.
    """
    succ_states = get_succ(state)
    for succ_state in succ_states:
        print(succ_state, "h={}".format(get_manhattan_distance(succ_state)))

def get_succ(state):
    """
    Generates a list of all the valid successors in the puzzle.

    Args:
        state (list): A state of the puzzle represented as a list of length 9.

    Returns:
        list: A list of all the valid successors in the puzzle.
    """
    succ_states = []
    for i in range(len(state)):
        if state[i] != 0:
            if i % 3 != 2 and state[i + 1] == 0:
                temp = state[:]
                temp[i + 1], temp[i] = temp[i], temp[i + 1]
                succ_states.append(temp)
            if i % 3 != 0 and state[i - 1] == 0:
                temp = state[:]
                temp[i - 1], temp[i] = temp[i], temp[i - 1]
                succ_states.append(temp)
            if i + 3 < len(state) and state[i + 3] == 0:
                temp = state[:]
                temp[i + 3], temp[i] = temp[i], temp[i + 3]
                succ_states.append(temp)
            if i > 2 and state[i - 3] == 0:
                temp = state[:]
                temp[i - 3], temp[i] = temp[i], temp[i - 3]
                succ_states.append(temp)
    return sorted(succ_states)

def solve(state, goal_state=[1, 2, 3, 4, 5, 6, 7, 0, 0]):
    """
    Solves the puzzle using the A* search algorithm.

    Args:
        state (list): An initial state of the puzzle represented as a list of length 9.
        goal_state (list): The goal state of the puzzle. Default is [1, 2, 3, 4, 5, 6, 7, 0, 0].

    Prints:
        Path of configurations from initial state to goal state along with h values, number of moves,
        and max queue length.
    """
    pq = []
    closed = {}
    hist = []
    g = 0
    h = get_manhattan_distance(state, goal_state)
    cost = g + h
    index = -1
    max_length = 0

    heapq.heappush(pq, (cost, state, (g, h, -1)))

    while pq:
        if len(pq) > max_length:
            max_length = len(pq)

        index += 1
        cost, current_state, (g, h, parentIndex) = heapq.heappop(pq)
        hist.append((current_state, parentIndex, g, h))
        closed[tuple(current_state)] = cost

        if h == 0:
            break

        for successor in get_succ(current_state):
            succ_g = g + 1
            succ_h = get_manhattan_distance(successor)
            succ_cost = succ_g + succ_h

            if tuple(successor) not in closed:
                heapq.heappush(pq, (succ_cost, successor, (succ_g, succ_h, index)))
                closed[tuple(successor)] = succ_cost
            elif succ_cost < closed[tuple(successor)]:
                heapq.heappush(pq, (succ_cost, successor, (succ_g, succ_h, index)))
                closed[tuple(successor)] = succ_cost

    state_info_list = []
    parent_index = len(hist) - 1

    while parent_index != -1:
        current_state, _, g, h = hist[parent_index]
        state_info_list.insert(0, (current_state, h, g))
        parent_index = hist[parent_index][1]

    for state_info in state_info_list:
        current_state = state_info[0]
        h = state_info[1]
        move = state_info[2]
        print(current_state, "h={}".format(h), "moves: {}".format(move))
    print("Max queue length: {}".format(max_length))

if __name__ == "__main__":
    # Example usage
    print_succ([2, 5, 1, 4, 0, 6, 7, 0, 3])
    print()
    solve([2, 5, 1, 4, 0, 6, 7, 0, 3])
