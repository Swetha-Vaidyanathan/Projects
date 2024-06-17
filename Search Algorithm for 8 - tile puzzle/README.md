### A* Search Algorithm for 8-Tile Puzzle

This project implements an A* search algorithm to solve the classic 8-tile puzzle. The goal is to efficiently find the optimal solution path to rearrange the tiles into a specified goal state using the A* search technique.

#### Key Features:
- **Problem Definition:** The 8-tile puzzle is a sliding puzzle consisting of a 3x3 grid with 8 numbered tiles and one empty space. The objective is to move the tiles to reach the goal state, typically ordered sequentially with the empty space at the bottom-right corner.
- **Heuristic Function:** Utilized the Manhattan distance as the heuristic function to guide the A* search algorithm. This heuristic calculates the sum of the absolute differences between the current and goal positions of the tiles.
- **State Space Generation:** Implemented efficient state space generation, handling tile movements and ensuring valid states within the 3x3 grid.
- **Priority Queue Management:** Leveraged Python's `heapq` library to manage the priority queue, ensuring that the state with the lowest cost is explored first.
- **Solution Path:** Developed the algorithm to not only find the optimal path but also to print each step of the solution, demonstrating the sequence of tile movements.

#### Technologies Used:
- Python
- `heapq` Library

This project demonstrates advanced search algorithms and heuristic techniques to solve combinatorial puzzles, showcasing problem-solving and algorithm design skills.

---

