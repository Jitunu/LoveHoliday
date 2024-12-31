#!/bin/bash
# Build script with debugging information

echo "Starting build process..."

# Create the output directory for compiled files
mkdir -p bin

echo "Compiling Java files from the src directory..."

# Compile all Java files
javac -d bin src/main/java/com/loveholiday/*.java 2>&1 | tee build.log

# Check if the compilation was successful
if [ $? -eq 0 ]; then
    echo "Build successful. Compiled files are in the 'bin' directory."
else
    echo "Build failed. See build.log for details."
    exit 1
fi
