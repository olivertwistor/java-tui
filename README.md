# Java TUI
Java is a verbose language. When you want to deal with user interfaces, there 
is a lot of boiler-plate code you have to write. This library means to 
alleviate a lot of the boiler-plate code necessary when you want to have a 
textual user interface.

* [Installation](#installation)
* [Usage](#usage)
* [Licensing](#licensing)

## Installation
This is a library, not a stand-alone application. Include this in your own 
applications or libraries. 

To make use of this library, please do the following:
1. Make sure you meet the prerequisites:
    * [Java 7 or later][2] 
1. Go to the [releases page][4] and find the specific version you want.
1. Download `java-tui-x.x.x.jar` and place it in your own application's 
classpath.
1. If you want Javadoc, download `java-tui-x.x.x-javadoc.jar`.
    * Please refer to your particular IDE on how to attach that to the previous 
    JAR file you downloaded.
    * If you're not using an IDE, you can unpack the JAR file and browse the 
    Javadoc in a web browser.
    
To build this library from source, please do the following:
1. Make sure you meet the prerequisites:
    * [Java 7 or later][2]
1. Go to the [releases page][4] and find the specific version you want.
1. Download `java-tui-x.x.x-sources.jar` and unpack it.
1. In that package, along with the source files you'll find the files 
`build.xml` and `build.properties`. With those, you can build the library 
(using [Apache ANT][6] or similar tools).

## Usage
In this library, there are two classes: `Terminal` and `UnclosableInputStream`. 
With `Terminal`, you can write to standard output and read from standard input.
An example:

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

The other class, `UnclosableInputStream` provides an InputStream that doesn't automatically close itself after use. An example:

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import nu.olivertwistor.java.tui.UnclosableInputStream;

class App
{
    public static void main(final String[] args) throws Exception
    {
        // Change the system input stream to prevent it closing.
        System.setIn(new UnclosableInputStream(System.in));
    
        try (final BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8)))
        {
            System.out.println(br.readLine());
        }
        finally
        {
            // Reset the system input stream.
            System.setIn(System.in);
        }
    }
}
```

## Licensing
This library is licensed under an *MIT License*. You'll find the full license 
terms in the file [LICENSE][3].


[2]: https://java.com/download/
[3]: LICENSE
[4]: https://github.com/olivertwistor/java-tui/releases
[6]: https://ant.apache.org/
