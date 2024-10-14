# WS Log Analyzer

**WS Log Analyzer** is a Java-based application designed for analyzing web server log files. It operates through a four-phase process that efficiently parses, processes, and visualizes log data. The application leverages external libraries for enhanced data analysis and clustering, providing an interactive graphical interface for insights.

## Key Features

### 1. Log File Processing
The application processes the raw server log file and generates a sanitized output file:
- It utilizes `JAWK` (an AWK implementation on the Java Virtual Machine) to run a script that extracts key information from the log, such as:
  - IP address of the requester
  - Timestamp of the request
  - HTTP method
  - Requested resource
  - Response status code
  - Response size
  - Referrer URL
  - User agent
- The script generates a file, `sanitized.txt`, in which irrelevant characters (`, [, ]`) are removed to facilitate further parsing by Java. 

### 2. Data Parsing and Storage
The sanitized log data is concurrently read and stored:
- Two threads handle the reading of the file, one for each half.
- The `Data` class, following the Singleton pattern, stores the requests based on their HTTP methods (`GET`, `POST`, `PUT`, `HEAD`, `DELETE`).
- These requests are organized in `RequestArray` objects and parsed using the `Request` class hierarchy.
- The application also integrates `UserAgentAnalyzer` to identify the browser used based on the user agent string.

### 3. Data Analysis
Once stored, the data is processed in two main ways:
- **Statistical Analysis**: The `StatCalculator` class computes usage percentages for HTTP methods, status codes, user agents, and protocols, storing the results in a `BasicStats` object.
- **Geographical Clustering**: Using the `GeoIP2` library, IP addresses are converted into approximate geographic coordinates. These coordinates are then clustered into six groups using the `EKmeans` Java implementation of the k-means algorithm. The clustering data is stored in a `HashMap<Coordinate, Integer>`.

### 4. Data Visualization
The results are visualized through an interactive graphical interface:
- The `GUI` class creates a `JFrame` with two tabs:
  - **ChartsPanel**: Displays pie charts representing the statistics of HTTP methods, status codes, user agents, and protocols using the `JFreeChart` library.
  - **PointsPanel**: Displays a map, using `JMapViewer`, with markers for each clustered geographic coordinate. Each marker is color-coded according to its cluster.

## External Libraries
- **JMapViewer**: Renders OpenStreetMap maps for displaying geographic data.
- **JFreeChart**: Creates pie charts for visualizing log statistics.
- **GeoIP2**: Converts IP addresses into geographic coordinates.
- **EKmeans**: Implements the k-means clustering algorithm.
- **Yet Another User Agent Analyzer**: Identifies the browser from the user agent string.
- **JAWK**: Executes AWK scripts within the Java environment.

This project efficiently handles log data processing, clustering, and visualization, providing both statistical and geographic insights into web server requests.
