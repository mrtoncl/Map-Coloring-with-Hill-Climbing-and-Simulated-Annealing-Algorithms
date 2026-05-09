# Map Coloring with Hill Climbing and Simulated Annealing Algorithms

## Project Overview

**Map Coloring with Hill Climbing and Simulated Annealing Algorithms** is a Java-based application designed to solve the classic map coloring problem. The project employs two widely used local search algorithms — Hill Climbing and Simulated Annealing — to find valid color assignments for regions on a map such that no two adjacent regions share the same color. This repository provides a practical illustration of constraint satisfaction and optimization techniques, making it useful for educational, experimental, and applied purposes.

---

## Features

- **Java Implementation**  
  Clean and modular codebase entirely in Java.
- **Hill Climbing Algorithm**  
  A fast, heuristic approach for local optima in map coloring.
- **Simulated Annealing Algorithm**  
  A metaheuristic enabling escape from local minima for improved solutions.
- **Flexible Map Definition**  
  Easily adapt the solution for different map structures and color constraints.
- **Comparative Analysis**  
  Outputs detailed statistics for both algorithms to evaluate their effectiveness.

---

## What is the Map Coloring Problem?

The map coloring problem is a constraint satisfaction problem where the goal is to assign colors to regions on a map such that no two adjacent regions have the same color, while minimizing the total number of colors used. This serves as a classic example for testing optimization and artificial intelligence algorithms.

---

## Algorithms Used

### 1. Hill Climbing

Hill Climbing is a local search algorithm that iteratively moves toward better solutions by selecting the best neighboring state. While efficient, it is prone to getting stuck in local minima.

### 2. Simulated Annealing

Simulated Annealing is a probabilistic optimization technique inspired by the annealing process in metallurgy. By allowing "uphill" moves in the solution space, it can escape local minima, thus providing a greater chance of finding a global optimum.

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher

### Building and Running

1. Clone the repository:
    ```sh
    git clone https://github.com/mrtoncl/Map-Coloring-with-Hill-Climbing-and-Simulated-Annealing-Algorithms.git
    cd Map-Coloring-with-Hill-Climbing-and-Simulated-Annealing-Algorithms
    ```

2. Compile the source code:
    ```sh
    javac -d out src/*.java
    ```

3. Run the application:
    ```sh
    java -cp out Main
    ```

### Customization

- Map data and constraints can be defined in text files (e.g., `map.txt`) or within the code.
- Algorithm parameters (number of colors, annealing schedule, iteration limits, etc.) are configurable in the codebase for experimentation.

---

## Output and Evaluation

Upon completion, the application provides:
- The color assignment for each region,
- Verification of constraint satisfaction (no adjacent regions share the same color),
- Performance metrics, such as runtime and number of iterations,
- Comparative results between Hill Climbing and Simulated Annealing algorithms.

---

## Contributing

Contributions are welcome! Please fork the repository, make your changes, and open a pull request. For reporting bugs or suggesting improvements, feel free to open an issue.

---

## License

All rigths reserved.

---

## Contact

For further questions or suggestions, please [open an issue](https://github.com/mrtoncl/Map-Coloring-with-Hill-Climbing-and-Simulated-Annealing-Algorithms/issues) on GitHub.
