#!/bin/bash

function cleanup {
  echo "Cleaning up"
  docker-compose down
}

docker-compose build
docker-compose up

trap cleanup EXIT
