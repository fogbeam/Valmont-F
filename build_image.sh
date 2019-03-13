#!/bin/bash

./grailsw clean
./grailsw war

sudo docker build -t fogbeam/valmont-f:latest .



