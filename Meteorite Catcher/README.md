**Title: P1 Meteorite Catcher - A Red-Black Tree Adventure**

**Brief Project Description:**
The Meteorite Catcher app is a powerful tool designed to swiftly sift through and list known meteorites by their mass. Harnessing the efficiency of a Red-Black Tree, the app ensures quick insertion of meteorite data and provides sorted retrieval. Users can load data from a local file of their choice, empowering them to explore meteorites with ease.

**Representative Tasks:**
1. **Find Highest Mass Meteorite:** Locate and display the meteorite with the highest mass in the dataset.
2. **Search by Mass Range:** Perform a search and list all meteorites with weights falling between 503 and 504 grams.

**Backend Developer (BD) Role:**
The Backend Developer plays a pivotal role in orchestrating the functionality behind the scenes. Responsibilities include:
- Developing a Java class defining a single meteorite object, facilitating insertion into the Red-Black Tree with mass as the sorting key.
- Reading meteorite data from a CSV file, following the NASA dataset format.
- Exposing essential properties to the frontend: name, latitude, longitude, fall, and mass.
- Implementing backend functionality accessible to the frontend: reading data from a file, retrieving meteorites with maximum mass, and fetching meteorites within specified mass thresholds.
- Demonstrating a search for the highest mass meteorites within the app.

**Frontend Developer (FD) Role:**
The Frontend Developer takes charge of creating an interactive and user-friendly experience. Responsibilities encompass:
- Writing code for an interactive loop, prompting users to select commands, providing required details, and displaying results.
- Designing commands: specify and load a data file, list meteorites with the highest mass, list meteorites within specified mass thresholds, and exit the app.
- Handling invalid user input gracefully, offering instructive feedback.
- Crafting an interface for a frontend class with a constructor accepting backend references and a Scanner instance for user input. This class initiates the main command loop.

**Interface Design Responsibilities:**
- **Meteorite Interface:** Properties for name, latitude, longitude, fall, and mass.
- **Backend Interface:** Methods for reading data, finding max mass meteorites, and searching by mass thresholds.
- **Frontend Interface:** Constructor accepting backend and Scanner, method for starting the main command loop.

**Presentation Responsibilities:**
-  demonstrates searching for the meteorite with the highest mass in the dataset.
- demonstration of the task: Searching and listing meteorites between 503-504 grams.

Embark on the Meteorite Catcher journey, where Red-Black Trees, data efficiency, and user interactivity converge for an out-of-this-world experience! 🚀🌠
