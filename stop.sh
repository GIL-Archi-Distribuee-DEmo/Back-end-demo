#!/bin/bash

# Define the container name
CONTAINER_NAME="gil-container"

# Stop the Docker container
docker stop $CONTAINER_NAME

# Remove the container (optional, use if you want to remove the container)
# docker rm $CONTAINER_NAME

echo "Container $CONTAINER_NAME stopped."
