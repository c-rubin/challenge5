# Data Validator

This project is a Java-based solution for validating data received from Open Data Hub APIs. It includes a `DataValidator` component that checks a set of data points for values that increase faster than a configured speed.

## Table of Contents
-[Introduction](#introduction)
-[Components](#components)

## Introduction
The Open Data Hub Validation project aims to ensure the quality of data received from various sources. The `DataValidator` component is designed to work with any type of JSON object based on configurable parameters such as the timestamp field, value field, and allowed variation speed.

## Components

### 1. DataValidator

The `DataValidator` class provides a method to validate a set of data points in JSON format. It checks for values that deviate from the configured speed of change between consecutive data points.
