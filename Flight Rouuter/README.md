**Flight Router - Navigating the Skies with Dijkstra's Magic**

**Brief Project Description:**
Welcome aboard the Flight Router app, where users can discover the shortest sequence of flights between airports, all while enjoying the scenic route with minimal miles. Dijkstra's shortest path algorithm takes center stage, turning airports into nodes and flights into edges. Load your data file, specify your route, and let the app guide you through the friendly skies.

**Representative Tasks:**
1. **Find ATL to SFO Route:** Search and list the flights, with mile details, to travel from Atlanta (ATL) to San Francisco (SFO).
2. **Display Dataset Statistics:** Showcase the number of airports, flights, and total miles in the dataset.

**Backend Developer (BD) Role:**
The Backend Developer orchestrates the backend magic, ensuring seamless interaction with the frontend. Responsibilities include:
- Writing code to read graph data from a DOT file and insert it into a graph data structure implementing the GraphADT interface.
- Creating a class storing shortest path search results, providing route details, segment miles, and total miles.
- Implementing a backend interface exposing functionality to the frontend: reading data, finding the shortest route, and providing dataset statistics.
- Demonstrating a search for the shortest route between ATL and SFO, showcasing miles for each segment and the total.

**Interface Design Responsibilities:**
- **ShortestPathResults Interface:** Methods for retrieving the route, segment miles, and total miles for a shortest path search.
- **Backend Interface:** Methods for reading data, finding the shortest route, and obtaining dataset statistics.

**Role Code and Placeholder Responsibilities:**
- Implement fully functional classes following the provided specifications for both interfaces.
- Create a placeholder class for the GraphADT, facilitating test execution and success.

**Frontend Developer (FD) Role:**
The Frontend Developer takes charge of crafting a user-friendly experience, making navigation through the skies effortless. Responsibilities include:
- Writing code for an interactive loop, prompting users to select commands, providing required details, and displaying results.
- Designing commands: specify and load a data file, show dataset statistics, list the shortest route between specified airports, and exit the app.
- Crafting an interface for a frontend class with a constructor accepting backend references and a Scanner instance for user input.

**Interface Design Responsibilities:**
- **Frontend Interface:** Constructor accepting backend and Scanner, method for starting the main command loop.

**Role Code and Placeholder Responsibilities:**
- Implement a fully functional class following the provided specifications for the Frontend Interface.
- Create a placeholder class for the Backend Interface, facilitating test execution and success.

**Presentation Responsibilities:**
- Demonstrate the app, showcasing dataset statistics, including the number of airports, flights, and total miles.
- Demonstrate a search for the shortest route between ATL and SFO, displaying segment miles and the total route.


Embark on the Flight Router journey, where Dijkstra's algorithm charts the course, and users navigate the skies with ease! ✈️🌐
