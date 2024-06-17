### Reinforcement Learning on FrozenLake-v1 Environment

This project involves implementing a Q-learning algorithm to solve the FrozenLake-v1 environment from Farama Gymnasium. The goal is to train an agent to navigate the frozen lake and reach the goal state while avoiding holes and slippery surfaces.

#### Key Features:
- **Environment Overview:** FrozenLake-v1 is a grid-based environment where the agent must navigate from the start to the goal while avoiding holes. The environment is stochastic, meaning the agent's movements may not always result in the intended direction.
- **Q-Learning Algorithm:** Implemented the Q-learning algorithm to enable the agent to learn an optimal policy for navigating the lake. The algorithm uses an epsilon-greedy policy for exploration and exploitation.
- **State and Action Representation:** Represented the environment's states and actions using a discrete model, allowing the agent to learn the best action for each state through interaction with the environment.
- **Reward System:** Designed a reward system where the agent receives a reward of 1 for reaching the goal and 0 otherwise. This helps the agent learn to prioritize reaching the goal state.
- **Hyperparameter Tuning:** Experimented with different hyperparameters, such as the learning rate, discount factor, and exploration rate, to optimize the agent's performance.

#### Technologies Used:
- Python
- Gymnasium Library
- Numpy

This project demonstrates the application of reinforcement learning techniques in a dynamic environment, showcasing skills in algorithm implementation, state representation, and hyperparameter tuning.
