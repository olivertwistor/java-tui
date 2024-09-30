# Changelog

This file chronicles all noteworthy changes to this project over time. It is based on [Keep a changelog 1.0.0](https://keepachangelog.com/en/1.0.0/). The versioning scheme I use for the project is [Semantic Versioning 2.0.0](https://semver.org/).

## [Unreleased]

- Added
    - Unit testing for the `Terminal` class.
    - Overloaded `Terminal#write`, `Terminal#writeLine`, `Terminal#readString`, `Terminal#readInt` and `Terminal#readBoolean`, adding parameters for input streams and print streams. This is done in order to make the library more generic and not just rely on standard in- and output.
- Changed
    - Methods that took in a charset parameter no longer do. They all assume UTF-8 for both in- and output.
- Removed
    - The private, empty constructor of the `Terminal` class.

## [0.4.0] (2024-08-26)

- Added
    - Overloaded `Terminal#readInt` method to control whether to return only if a valid integer is read.

- Changed
    - All previously existing `Terminal#readInt` methods defaults to only return if a valid integer is read.
    - Requires Java 8 instead of Java 7.

## [0.3.0] (2022-06-08)

* Added
    * A Maven POM to make it easier to add this project as a dependency.
    * Method for reading boolean input.

## [0.1.0] (2019-09-11)

* Added
    * The class `Terminal` with static methods for writing to and reading from standard input and output.

[unreleased]: https://github.com/olivertwistor/java-tui/compare/0.4.0...HEAD

[0.4.0]: https://github.com/olivertwistor/java-tui/compare/0.3.0...0.4.0

[0.3.0]: https://github.com/olivertwistor/java-tui/compare/0.1.0...0.3.0

[0.1.0]: https://github.com/olivertwistor/java-tui/compare/b623b326cf71af3bf9767e909105f9e1e3dde283...0.1.0
