#!/bin/bash

# Step 1: Create the `bin` directory if it doesn't exist
if [ ! -d "bin" ]; then
  echo "Creating bin directory..."
  mkdir bin
fi

# Step 2: Compile the Java files into the `bin` directory
echo "Compiling Java files..."
javac -d bin src/main/java/com/loveholiday/*.java

if [ $? -eq 0 ]; then
  echo "Compilation successful."
else
  echo "Compilation failed. Please check for errors."
  exit 1
fi

# Step 3: Create `run.sh` file inside the `bin` directory
RUN_SCRIPT="bin/list-flight-paths"

echo "Creating list-flight-paths script..."
cat <<EOL > $RUN_SCRIPT
# Debugging information
echo "Starting program..."
echo "Arguments provided \$*"
echo "Running Java program..."

# Run the Java program with the provided arguments
java -cp bin com/loveholiday/LoveHolidayApplication "\$@"

# Check the result
if [ $? -eq 0 ]; then
    echo "Program finished successfully."
else
    echo "Program encountered an error."
    exit 1
fi
EOL

# Step 4: Make the `run.sh` file executable
chmod +x $RUN_SCRIPT
echo "list-flight-paths script created and made executable."

echo "Build complete. Use ./bin/list-flight-paths \"Castle Black\" \"King's Landing\" to run the program."
