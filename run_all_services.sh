#!/bin/bash
# Use this script to run all the services in the project
# Make sure you have maven installed on your machine with sdkman

# Source sdkman initialization script
source "$HOME/.sdkman/bin/sdkman-init.sh"
#Using sdkman to use maven 3.9.0
sdk use maven 3.9.0

# Check if the sdk command was successful
if [ $? -ne 0 ]; then
  echo "Failed to switch to Maven 3.9.0"
  exit 1
fi

# List of application directories
services=("Config-Server-Application" "Register-Server" "Gateway-Application" "Inventory-Application" "Catalog-Application" "Product-Application")

# Loop through each application directory and run it
for app in "${services[@]}"; do
  echo "Running $app ..."
  cd "$app" || exit
  mvn spring-boot:run &
  echo "Waiting for 20 seconds before starting the next application..."
  sleep 20 &
  cd ..
done

echo "All applications are running."