# Finite State Machine Generator
Create Deterministic finite state machines by defining Terminals, States, and Production Rules. DFA is saved as a JSON file in DFA directory in root.
Created DFA can then be used to test inputs.

## Usage
In the root directory run
```shell
mvn package
```
This packages the application in a jar file located in target directory.
To run the application change directory to target ```cd target``` and then run
```shell
java -jar chomsky-hierarchy-1.0-SNAPSHOT.jar
```
If you don't have Maven installed click <a href="https://maven.apache.org/install.html">here</a>.
