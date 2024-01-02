# Data Validator

## Table of Contents
-[Introduction](#introduction)
-[Components](#components)

## Introduction
The Open Data Hub Validation project aims to ensure the quality of data received from various sources. The `validation.DataValidator` component is designed to work with any type of JSON object based on configurable parameters such as the timestamp field, value field, and allowed variation speed.

## Overview

This project is a Java-based solution for validating data received from Open Data Hub APIs. It includes a `validation.DataValidator` component that checks a set of data points for values that increase faster than a configured speed.

1. **Main Class (`Main.java`):**
   - Entry point for the application.
   - Utilizes the APIs and Data Validator to fetch and validate data.

2. **Weatehr API (`WeatherAPI.java`):**
   - Connects to the Open Data Hub's Tourism API to retrieve weather data.
   - Uses the `SimpleHttpClient` utility to make HTTP GET requests.

3. **Simple HTTP Client (`SimpleHttpClient.java`):**
   - Utility class for handling HTTP requests to a specified URL.
   - Retrieves and returns the API response.
  
4. **Data Validator (`DataValidator.java`):**
   - Validates temperature data against specified criteria.
   - Checks for variations in temperature values over time.

5. **JUnit Tests (`MainTest.java`):**
   - Contains comprehensive JUnit tests to verify the functionality of application components.
   - Tests cover the Weather API, HTTP client, and data validation.


## Prerequisites

- **Java Development Kit (JDK):** Ensure you have JDK 11 or later installed.
- **Maven (Optional):** If you prefer using Maven, it can be used for dependency management.

## Installation and Setup

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/c-rubin/challenge5.git
    cd challenge5
    ```

2. **Compile and Run:**

   ```bash
   javac -cp .:lib/json-20231013.jar Main.java
   java -cp .:lib/json-20231013.jar Main
   ```

If you use Maven:

```bash
mvn clean install
```

## Running Tests

1. **Configure JUnit:**

   Ensure JUnit is configured in your development environment.

2. **Run Tests:**

   ```bash
   javac -cp .:lib/json-20231013.jar:/path/to/junit/junit-4.13.1.jar:/path/to/hamcrest/hamcrest-core-1.3.jar MainTest.java
   java -cp .:lib/json-20231013.jar:/path/to/junit/junit-4.13.1.jar:/path/to/hamcrest/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MainTest
   ```

3. Replace `/path/to/junit/junit-4.13.1.jar` and `/path/to/hamcrest/hamcrest-core-1.3.jar` with the actual paths to your JUnit and Hamcrest JAR files.


## Usage


## Troubleshooting

## Contributing

## License









