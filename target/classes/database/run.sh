#!/bin/bash

# Set the image and container names
IMAGE_NAME="gil-image"
CONTAINER_NAME="gil-container"

# Build the Docker image without using the cache
docker build -t $IMAGE_NAME --no-cache .

# Run the Docker container
docker run -d --name $CONTAINER_NAME -e POSTGRES_DB=gil-db -e POSTGRES_PASSWORD=postgres -p 5432:5432 $IMAGE_NAME

# Wait for the PostgreSQL container to start
echo "Waiting for PostgreSQL container to start..."
while ! docker exec -i $CONTAINER_NAME pg_isready -h localhost -p 5432 -q -U postgres -d gil-db; do
    sleep 2
done
echo "PostgreSQL container is up and running."

# Copy schema and data into the running PostgreSQL container
docker cp schema.sql $CONTAINER_NAME:/docker-entrypoint-initdb.d/
docker cp data.sql $CONTAINER_NAME:/docker-entrypoint-initdb.d/

# Access the PostgreSQL container and execute initialization
docker exec -it $CONTAINER_NAME psql -U postgres -d gil-db -a -f /docker-entrypoint-initdb.d/schema.sql
docker exec -it $CONTAINER_NAME psql -U postgres -d gil-db -a -f /docker-entrypoint-initdb.d/data.sql

echo "Data import completed."

# Keep the container running (remove the "-d" flag if you want it to run in the foreground)
docker logs -f $CONTAINER_NAME

