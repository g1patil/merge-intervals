# merge-intervals
Library to perform operations related to integer intervals.
Intervals.java is the core class which has the main logic.

## merge-intervals
Import the project as gradle project in your favourite IDE. This is standard java project 
which has gradle as build tool.

## Build project
Run command from project root directory

```./gradlew build```

## Tests
Run command from project root directory

```./gradlew test```

## Complexity of algorithm

### Coverall complexity : nlogn

#### Complexity breakdown : 

input validation ( n : linear) + input sorting/ordering ( nlogn) + building new intervals ( n : linear) = nlogn

