# Challenge 5: Data Validation Project

## Overview

The Data Validation Project is a Java application designed to retrieve, process, and validate weather data from two distinct API endpoints. The project ensures the integrity and reliability of the collected data through a systematic validation process.

## Key Features

- **Data Retrieval:**
  - Utilizes the `SimpleHttpClient` class to make HTTP GET requests to external APIs, fetching weather-related data.

- **API Endpoints:**
  - Default Weather API: Retrieves general weather data [Default API Link](https://tourism.opendatahub.com/v1/Weather?locfilter=3).
  - Weather API v2: Retrieves air temperature measurements [Weather API v2 Link](https://mobility.api.opendatahub.com/v2/tree%2Cnode/MeteoStation/air-temperature/latest?).

- **Data Processing:**
  - The `WeatherAPI` and `WeatherApiV2` classes process the JSON responses from the respective APIs, extracting relevant information.

- **Data Validation:**
  - The core validation is handled by the `DataValidator` class.
  - Iterates through the retrieved JSON data points and validates them based on specified criteria.

- **Temporal Analysis:**
  - Utilizes Java's `Timestamp` for accurate temporal analysis, calculating time differences in seconds and days.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/weather-data-validation.git
