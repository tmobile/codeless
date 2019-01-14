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
this project comes with a pre build jar file under <INSTALL_ROOT>/example_usage/bin path. So that you can run
a sample spread sheet tests located under <INSTALL_ROOT>/example_usage/suites.

##### build
Codeless project comes with four different projects and an example usage folder that has sample test cases and model files for the test cases. You can find the project herarchy and details of each components under wiki.

> Steps to build the project
Open command line and go to <INSTALL_ROOT> path and execute below maven command for quick build with out running unit tests.
```
mvn clean install
```
what above command will do is, it generates a .jar file that has all five components. After generating the jar it puts the jar under <INSTALL_ROOT>/example_usage/lib folder. This is the entry point to run your codeless spread sheet tests.

### Project Structure

There are five components under <INSTALL_ROOT>. This five components build as a jar file to run a codeless test suites.

> /codeless_core

> /codeless_ui

> /codeless_service

> /codeless_test

> /selenium_action

/example_usage has five folders. Here you define your suite test and test data's that your tests refer to. 

```
/bin

..run.bat
..run.sh

/lib
..codeless_test-0.0.3-SNAPSHOT-jar-with-dependencies

/logs

..debug/test_suite


> /models

../test_model

> /suites

../test_suite
```

## Running the tests
#### Executing Tests
Following sections cover how to execute example tests provided by this skeleton

#### Execute UI Tests
Example UI tests included with this skeleton. These tests use PageObject models defined by yaml in the <INSTALL_ROOT>/model folder. They are for the T-Mobile web (<INSTALL_ROOT>/model/TMO) and MyTmobile web (<INSTALL_ROOT>/model/MyTMO) applications.

Make sure you have followed the install and configuration instructions from above.
From the command line change to <INSTALL_ROOT>/bin
Use the appropriate run command for your OS. I.E. run.cmd for Windows, run.sh for Mac/Linux
Type run.<os> demo_ui.xlsx I.E. run.cmd demo_ui.xlsx for Windows and ./run.sh demo_ui.xlsx for Mac/Linux

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Built With


## Contributing


## Versioning


## Authors


## License


## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
