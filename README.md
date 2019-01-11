# ETP Codeless

ETP Codeless is a stand-alone Java project created by T-Mobile. ETP codeless project allows you to quickly write tests using basic yaml and spreadsheet files without having to have in-depth understanding of writing Java automation tests.

## Getting Started

Below instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

| Prerequisite | Command to Run | Description |
| ------------ | -------------- | -----------  |
| Java Runtime Environment (JRE) 1.8.x | java -version | As it is a Java project, Java need to be installed locally.
| Apache Maven | mvn -version | Need to install Maven to build the project |
| Git | git clone <code_location> | Project code need to be in local machine |

### Installing

Before you use the ETP Codeless project you must first clone it to your local machine. 
You can clone the project using your favorite git GUI tool or from the command line with: 
git clone https://github.com/tmobile/codeless.git

After getting the project on you local, next step will be to build the project locally. One thing is
this project comes with a pre build jar file under <INSTALL_ROOT>/example_usage/bin path.

##### build
Codeless project comes with four different projects and an example usage folder that has sample test cases and model files for the test cases. You can find the project herarchy and details of each components under wiki.

> Steps to build the project
Open command line and go to <INSTALL_ROOT> path and execute below maven command for quick build with out running unit tests.
```
mvn clean install -DskipTests=true
```
what above command will do is it generates a .jar file that has all four components. After generating the jar it puts the jar under example_usage/lib folder. This is the entry point for scripts under bin folder.

### Project Structure

example_usage
```
Below outlines the main package structure/components of the ETP Codeless example_usage which users would interact with. Please refer here for a quick guide on what the individual components are under example_usage.
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests
##### Windows machine
run <INSTALL_ROOT>/bin/run.bat
##### Mac machine
run <INSTALL_ROOT>/bin/run.bat
### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With


## Contributing


## Versioning


## Authors


## License


## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
