import numpy as np
import csv
import matplotlib.pyplot as plt
from scipy.cluster.hierarchy import dendrogram


def load_data(filepath):
    #This empty dictionary keeps track of the data.
    data = []
    #reads the csv
    with open(filepath, newline='') as csvfile:
        #uses DictReader to read into the csv file.
        reader = csv.DictReader(csvfile)
        for row in reader:
            data.append(row)
    return data

def  calc_features(row):
    #converting all the datatypes to float.
    x1 = float(row['Population'])
    x2 = float(row['Net migration'])
    x3 = float(row['GDP ($ per capita)'])
    x4 = float(row['Literacy (%)'])
    x5 = float(row['Phones (per 1000)'])
    x6 = float(row['Infant mortality (per 1000 births)'])
    #returns array of all the values in float.
    array = np.array([x1, x2, x3, x4, x5, x6], dtype=np.float64)
    return array

def hac(features):
    features = np.array(features)
    #stores the length
    n = len(features)
    #Creates a lost of indices in the reange of length.
    cluster_indices = {i:[i] for i in range(n)}

    #mainting the size of the output.
    Z = np.zeros((n - 1, 4))

    # Initialize distance matrix
    distance_matrix = np.zeros((n, n))
    for i in range(n):
        for j in range(i + 1, n):
            distance_matrix[i, j] = np.linalg.norm(features[i] - features[j])
            distance_matrix[j, i] = distance_matrix[i, j]

    for i in range(n - 1):

        minimum_distance = np.inf
        min_indices = (-1, 1)
        #Find closest pair of points
        #min_indices = (-1, -1)
        for j in cluster_indices:
            for k in cluster_indices:
                if j < k:
                    linkage = -1
                    for elem1 in cluster_indices[j]:
                        for elem2 in cluster_indices[k]:
                            if distance_matrix[elem1, elem2] > linkage:
                                linkage = distance_matrix[elem1, elem2]
                    if linkage < minimum_distance:
                        minimum_distance = linkage
                        min_indices = (j, k)

                    elif linkage == minimum_distance and (j < min_indices[0] or (j == min_indices[0] and k < min_indices[1])):
                        min_indices = (j, k)

        # Fill in linkage matrix
        Z[i, 0] = min_indices[0]
        Z[i, 1] = min_indices[1]
        Z[i, 2] = minimum_distance
        Z[i, 3] = len(cluster_indices[min_indices[0]]) + len(cluster_indices[min_indices[1]])

        #merging clusters
        index = n+i
        cluster_indices[index] = set(cluster_indices[min_indices[0]]).union(set(cluster_indices[min_indices[1]]))
        del cluster_indices[min_indices[0]]
        del cluster_indices[min_indices[1]]
    return Z


def fig_hac(Z, names):
    #creating a plot with figsize.
    fig = plt.figure(figsize=(10, 6))
    #using dendrogram to add label names and leaf rotation
    dendrogram(Z, labels=names, leaf_rotation=90)
    #tightening the layout so the x-labels do not cut off
    plt.tight_layout()
    plt.show()
    return fig


def normalize_features(features):
    # Convert the input features to a NumPy array for efficient operations
    features_array = np.array(features)

    # Initialize an empty list to store normalized feature columns
    normalized_features = []

    # Iterate over each feature column
    for i in range(features_array.shape[1]):
        # Calculate mean and standard deviation for the current feature column
        mean_value = np.mean(features_array[:, i])
        std_dev = np.std(features_array[:, i])

        # Perform z-score normalization for the current feature column
        normalized_column = (features_array[:, i] - mean_value) / std_dev

        # Append the normalized feature column to the list
        normalized_features.append(normalized_column)

    # Convert the list of normalized feature columns back to a NumPy array,
    # transpose it to get features as rows and data points as columns,
    # and convert it to a list of lists before returning
    return np.array(normalized_features).T.tolist()


if __name__ == "__main__":
    data = load_data("countries.csv")
     # Load data from 'countries.csv'
    # Extract country names and features from the data
    country_names = [row["Country"] for row in data]
    features = [calc_features(row) for row in data]

    # Normalize the features
    features_normalized = normalize_features(features)
    n = len(features)

    # Perform hierarchical agglomerative clustering on raw data
    Z_raw = hac(features[:n])

    # Perform hierarchical agglomerative clustering on normalized data
    Z_normalized = hac(features_normalized[:n])

    # Plot hierarchical agglomerative clustering dendrogram for raw data
    fig_raw = fig_hac(Z_raw, country_names[:n])
    plt.show()