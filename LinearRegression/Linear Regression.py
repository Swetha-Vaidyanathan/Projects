import sys
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from numpy.linalg import inv

#This method is used to clean the dataset
'''
def clean_and_save_data(filename):
    # Load the original data from the provided link
    original_data = pd.read_csv(filename)

    # Extract relevant columns
    original_data['year'] = original_data['Winter'].apply(lambda x: int(x.split('-')[0]))
    original_data['days'] = original_data['Number of days']

    # Aggregate data for years with multiple freeze-thaw cycles
    cleaned_data = original_data.groupby('year')['days'].max().reset_index()

    # Filter data from 1855-56 to 2021-22
    cleaned_data = cleaned_data[(cleaned_data['year'] >= 1855) & (cleaned_data['year'] <= 2021)]

    # Save the cleaned dataset to "hw5.csv"
    cleaned_data.to_csv("hw5.csv", index=False, header=['year', 'days'])
    '''


def plot_data(filename):
    # Load data from the specified CSV file
    data = pd.read_csv(filename)

    data['year'] = data['year'].fillna(0).astype(int)
    data['days'] = data['days'].fillna(0).astype(int)
    years = data['year'].tolist()
    days = data['days'].tolist()

    plt.plot(years, days)
    plt.xlabel('Year')
    plt.ylabel('Number of Frozen Days')
    plt.savefig("plot.jpg")

    return data

def q3a(df):
    print("Q3a:")
    df["column"] = 1
    X = df[["column", "year"]].to_numpy().astype(np.int64)
    print(X)
    return X

def q3b(df):
    print("Q3b:")
    Y = df["days"].to_numpy().astype(np.int64)
    print(Y)
    return Y

def q3c(X):
    print("Q3c:")
    Z = np.dot(np.transpose(X), X).astype(np.int64)
    print(Z)
    return Z

def q3d(Z):
    print("Q3d:")
    I = inv(Z)
    print(I)
    return I

def q3e(X, I):
    print("Q3e:")
    PI = np.matmul(I, X.transpose())
    print(PI)
    return PI

def q3f(PI, Y):
    print("Q3f:")
    B = np.matmul(PI, Y)
    print(B)
    return B

def predict(x_test, beta_hat):
    y_hat_test = beta_hat.item(0) + beta_hat.item(1) * x_test
    print(f"Q4: {y_hat_test}")
    return y_hat_test

def model_interpretation(beta1):
    # Interpret the sign of β1 as specified in Question 5
    # Return the symbol and a short answer explanation
    if beta1 > 0:
        symbol = '>'
        explanation = 'The sign indicates a positive correlation between the year and the number of ice days.'
    elif beta1 < 0:
        symbol = '<'
        explanation = 'The sign indicates a negative correlation between the year and the number of ice days.'
    else:
        symbol = '='
        explanation = 'The sign indicates no correlation between the year and the number of ice days.'

    print(f"Q5a: {symbol}")
    print(f"Q5b: {explanation}")
    return symbol, explanation

def model_limitation(df, beta_hat):
    # Predict the year by solving for x* in the equation 0 = beta_hat[0] + beta_hat[1] * x*
    x_star = -beta_hat[0] / beta_hat[1]
    print(f"Q6a: {x_star}")

    # Discuss whether x* is a compelling prediction
    if beta_hat[1] > 0:
        answer = "The positive trend in the data suggests that Lake Mendota may never completely stop freezing, and the prediction for the year when it won't freeze is not compelling."
    elif beta_hat[1] < 0:
        answer = "The negative trend in the data suggests that Lake Mendota may eventually stop freezing, but predicting a specific year is challenging due to variations in the data."
    else:
        answer = "The zero trend in the data suggests that there is no linear relationship between the years and the freezing pattern of Lake Mendota, making the prediction less meaningful."

    print(f"Q6b: {answer}")
    return x_star, answer

if __name__ == "__main__":
    filename = sys.argv[1]
    df = plot_data(filename)
    X = q3a(df)
    Y = q3b(df)
    Z = q3c(X)
    I = q3d(Z)
    PI = q3e(X, I)
    B = q3f(PI, Y)
    predict(x_test=2022, beta_hat=B)
    model_interpretation(B.item(1))
    model_limitation(df, B)
