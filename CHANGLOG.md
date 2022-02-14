# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/).

----
## [Unreleased] (In Git)

### Added

### Changed

### Deprecated

### Removed

### Fixed

## 0.2.0 (Feb 14, 2022)

### Added
* [copy_sources] add script into pom.xml to copy to example_usage\lib from executable_with_all_dependency.jar 
* [copy_sources] add script into pom.xml to copy to example_usage\lib\web_drivers from codeless_test\web_driver

### Changed
* [example_usage] run bat/sh uses identical jar file(codeless-jar-with-dependencies.jar) 
* [main] uses maven 3.8.1 
* [test] web_drivers update, uses guava 31.0.1, common-lang3 3.12.0, extentsreport 5.0.8 
* [core] uses slf4j 1.7.35, restassured 4.5.0, jackson 2.13.1, poi 4.1.2, assertj 3.22.0, commons-csv 1.9.0, swagger-parser 1.0.56, ooxml-schema 1.4, Selenium4  

### Removed
* [example_usage] remove web_drivers folder
* [example_usage] remove *.jar as duplicates

## 0.1.0 (Jan 10, 2022)

### Changed
* [Core] updated dependencies
* [UI] updated driver instances - opera & edge

### Removed
* [UI] remove IE driver instance


## 0.0.11 (Dec 12, 2020)

### Changed
* [Core] updated dependencies

## 0.0.1 (Jan 4, 2019)

* [All] initial build
