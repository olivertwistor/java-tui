# Java TUI

## Table of contents
* [Background](#background)
* [Purpose](#purpose)
* [Goal](#goal)
* [Stakeholders](#stakeholders)
* [Prerequisites][1]
* [Installation instructions](#installation-instructions)
* [Usage](#usage)
* [Licensing](#licensing)
* [How to contribute](#how-to-contribute)
* [Versioning](#versioning)

## Background
Java is a verbose language. When you want to deal with user interfaces, there 
is a lot of boiler-plate code you have to write.

## Purpose
This library means to alleviate a lot of the boiler-plate code necessary when 
you want to have a textual user interface.

## Goal
The goals of the Java TUI library is to provide common textual user interface 
functionality, for example:
* user input following specific formats
* menus and handling of user choice

## Stakeholders
Everyone who don't want to write the same user interface code over and over 
again for each of their projects, may be interested in this. 

## Prerequisites
* [Java 7 or newer][2]

## Installation instructions
This is a library, not a stand-alone application. Include this in your own 
applications or libraries. 

To make use of this library, please do the following:
1. Make sure you meet the [prerequisites][1]. 
1. Go to the [releases page][4] and find the specific version you want. The 
lastest release is [0.1.0][5].
1. Download `java-tui-x.x.x.jar` and place it in your own application's 
classpath.
1. If you want Javadoc, download `java-tui-x.x.x-javadoc.jar`.
    * Please refer to your particular IDE on how to attach that to the previous 
    JAR file you downloaded.
    * If you're not using an IDE, you can unpack the JAR file and browse the 
    Javadoc in a web browser.
    
### Build from source
To build this library from source, please do the following:
1. Make sure you meet the [prerequisites][1]. 
1. Go to the [releases page][4] and find the specific version you want. The 
lastest release is [0.1.0][5].
1. Download `java-tui-x.x.x-sources.jar` and unpack it.
1. In that package, along with the source files you'll find the files 
`build.xml` and `build.properties`. With those, you can build the library 
(using [Apache ANT][6] or similar tools).

## Usage
In this library, there is one usable class: 
`nu.olivertwistor.java.tui.Terminal`. With this class, the user can write to 
and read from standard input and output.

Here are some examples:

```java
import nu.olivertwistor.java.tui.Terminal;

class App
{
    public static void main(final String[] args) throws Exception
    {
        Terminal.writeLine("Welcome to this app!");
        Terminal.write("This is the beginning of one line. ");
        Terminal.writeLine("This is continuing on that line.");
        
        final int age = Terminal.readInt("Please state your age: ");
        Terminal.write("Your age is ");
        Terminal.writeLine(age);
    }
}
``` 

This will produce the following output:

```
Welcome to this app!
This is the beginning of one line. This is continuing on that line.
Please state your age: 36
Your age is 36
```

## Licensing
This library is licensed under the MIT License. You'll find the full license 
terms in the file [LICENSE][3].

## How to contribute
Thank you for wanting to contribute to this project. Open source is all about 
community. Go and read the document [CONTRIBUTING.md][12] for more information 
on with what you can contribute and how to go about it.

## Versioning
This project uses [Semantic Versioning 2.0.0][13] for version numbering. To see 
what's changed between versions, please read [CHANGELOG.md][14]. That file also 
has links to the download section of each release.


[1]: #prerequisites
[2]: https://java.com/download/
[3]: LICENSE
[4]: https://github.com/olivertwistor/java-tui/releases
[5]: https://github.com/olivertwistor/java-tui/releases/tag/v0.1.0
[6]: https://ant.apache.org/
[12]: CONTRIBUTING.md
[13]: http://semver.org/
[14]: CHANGELOG.md
