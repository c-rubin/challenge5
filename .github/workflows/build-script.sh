#!/bin/bash

javac -d out .java

jar cf DataValidator.jar -C out Main.class
