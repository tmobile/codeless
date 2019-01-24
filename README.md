# ETP Codeless

ETP Codeless is a stand-alone Java project created by T-Mobile. ETP codeless project allows you to quickly write tests using basic yaml and spreadsheet files without having to have in-depth understanding of writing Java automation tests.

## Getting Started

Below instructions will get you a copy of the project up and running on your local machine for testing purposes.

### Prerequisites

| Prerequisite | Command to Run | Description |
| ------------ | -------------- | -----------  |
| Java Runtime Environment (JRE) 1.8.x | java -version | As it is a Java project, Java need to be installed locally.
| Microsoft Excel |  | ETP Codeless currently support excel based test cases

##### Running sample test

Before using ETP Codeless project you must first clone it to your local machine. you can download the project from github
with out using any git tool. go to https://github.com/tmobile/codeless to download the ETP Codeless project.

You can also clone the project using your favorite git GUI tool or from the command line with using git command:
```
git clone https://github.com/tmobile/codeless.git
```

There are example tests included in example_usage/suites/. This tests use PageObject models defined in yaml format and a swagger definations used by service tests located under <INSTALL_ROOT>/model folder respective of the test step.

From the command line change to <INSTALL_ROOT>/example_usage/bin
Use the appropriate run command for your OS. I.E. run.cmd for Windows, run.sh for Mac/Linux
Type run.<os> sampletest.xlsx I.E. run.cmd sampletest.xlsx for Windows and ./run.sh sampletest.xlsx for Mac/Linux
  
  * refer wiki for all combinations and permitations of test execution.

### Project Structure

There are five components under <INSTALL_ROOT>. This five components build as a jar file to run a codeless test suites.

* `/codeless_core:` defines core components of framwork common for both other modules defined below.

* `/codeless_ui:` defines different components that generates selenium ui automation test steps.

* `/codeless_service:`  defines different components that are needed for API automation testing.

* `/codeless_test:` this module is a interacts both with codeless_ui/service modules to run the automation test.

* `/selenium_action:` defines different selenium page actions that are supported by the framework.

/example_usage has five folders. Here you define your suite test and test data's that your tests refer to and also logs for
each test suite executed. 

```
/bin
..run.bat
..run.sh

/lib
..codeless_test-0.0.3-SNAPSHOT-jar-with-dependencies.jar

/logs
..debug/test_suite

> /models
../test_model

> /suites
../test_suite
```

##### Execution outputs

* Extent Report: gives report for each test and test steps (request and response values of each service steps).

* Screen Shots: Codeless framework take screen shots if a test step failed and puts dump it to a folder      <INSTALL_ROOT>/example_usage/lib/FailedTestsScreenshots

* Logs: For each test suite the framework generates debug log under <INSTALL_ROOT>/example_usage/logs/ folder which can be useful when triaging issues.

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Built With
#### Project build process Prerequisites

| Prerequisite | Command to Run | Description |
| ------------ | -------------- | -----------  |
| Jdk |  | Edit source code |
| Git | git clone <code_location> | Project code need to be in local machine |
| Apache Maven | mvn -version | Need to install Maven to build the project if project has been edited |


## Contributing

##### Installation and build process

If you already have the project cloned locally follow below steps, Otherwise go to Getting Started section to get the project
on you local box.

After getting the project on you local, next step will be to build the project locally. One thing is
this project comes with is a pre build jar file under <INSTALL_ROOT>/example_usage/bin path. Using that you can run
a sample spread sheet tests provided located under <INSTALL_ROOT>/example_usage/suites.

##### build
This section is for a user who would like to change source file to contribute to the ETP codeless community or who like to extend
the base features of the framework.
ETP Codeless framework comes with five different components and example_usage folder that has sample test cases and model files.

* To build the project
Open command line and go to <INSTALL_ROOT> path and execute below maven command for quick build.
```
mvn clean install
```
After above command runs, it generates a .jar file that has all five components and puts the jar under <INSTALL_ROOT>/example_usage/lib folder.


## Versioning


## Authors


## License


## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
