
import gymnasium as gym
import random
import numpy as np
import time
from collections import deque
import pickle
from collections import defaultdict

# Constants
EPISODES = 20000
LEARNING_RATE = 0.1
DISCOUNT_FACTOR = 0.99
EPSILON = 1
EPSILON_DECAY = 0.999
MIN_EPSILON = 0.01  # Define a minimum for epsilon

def default_Q_value():
    return 0

if __name__ == "__main__":
    env = gym.envs.make("FrozenLake-v1")
    env.reset(seed=1)

    Q_table = defaultdict(default_Q_value)
    episode_reward_record = deque(maxlen=100)

    for episode in range(EPISODES):
        episode_reward = 0
        done = False
        obs = env.reset()[0]

        while not done:
            current_state = obs
            if random.uniform(0, 1) < EPSILON:
                action = env.action_space.sample()
            else:
                # Select the action with the highest Q-value for the current state
                action = np.argmax([Q_table[(current_state, a)] for a in range(env.action_space.n)])

            obs, reward, terminated, truncated, info = env.step(action)
            done = terminated or truncated
            episode_reward += reward

            next_state = obs
            if not done:
                # Update Q-value for the current state and action pair
                best_next_reward = max([Q_table[(next_state, a)] for a in range(env.action_space.n)])
                Q_table[(current_state, action)] += LEARNING_RATE * (reward + DISCOUNT_FACTOR * best_next_reward - Q_table[(current_state, action)])
            else:
                # Update Q-value for the terminal state
                Q_table[(current_state, action)] += LEARNING_RATE * (reward - Q_table[(current_state, action)])

        # Decay epsilon but not below a minimum value to ensure some exploration
        EPSILON = max(EPSILON * EPSILON_DECAY, MIN_EPSILON)
        episode_reward_record.append(episode_reward)

        if episode % 100 == 0:
            avg_reward = sum(episode_reward_record) / len(episode_reward_record)
            print(f"Episode: {episode}/{EPISODES}, Avg Reward (last 100): {avg_reward:.2f}, Epsilon: {EPSILON:.3f}")

        
 #### DO NOT MODIFY ######
    model_file = open('Q_TABLE.pkl' ,'wb')
    pickle.dump([Q_table,EPSILON],model_file)
    model_file.close()
    #########################