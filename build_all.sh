#!/bin/bash

## Building client
cd client
mvn clean package
cd ..

## Building the polyevent system
python buildPolyEvent.py

## Building the .Net system
cd dotNet
./compile.sh
cd ..
