# Java TUI

Library for text-based user interfaces. It's main purpose is to alleviate boilerplate code regarding input and output.

## Goals

Java is a verbose language. When one wants to deal with user interfaces, there is a lot of boilerplate code one has to write. This library means to alleviate some of that when one wants to have a text-based user interface. Java TUI is not intended to be a full-fledged framework for text-based user interfaces, but rather solve a particular small set of problems, mainly handling input from *stdin*.

## Installation

This is a library, not a stand-alone application. Include this in your own application.

To make use of this library, please do the following:

1. Make sure you meet the prerequisites:
    * [Java 8 or later][javadl]
1. Go to the [releases page][releasesPage] and find the specific version you want.
1. Download `java-tui-x.x.x.jar` and place it in your own application's classpath.
1. If you want Javadoc, download `java-tui-x.x.x-javadoc.jar`.
    * Please refer to your particular IDE on how to attach that to the previous JAR file you downloaded.
    * If you're not using an IDE, you can unpack the JAR file and browse the Javadoc in a web browser.

### Build from source

1. Make sure you meet the prerequisites:
    * [Java 8 or later][javadl]
2. Clone this repository.
3. If you want to build the latest release, go to the branch `production`. If you want the bleeding edge (might not be entirely stable, but it should at least build without compiler errors), go to the branch `main`.
4. Build using the file `pom.xml`.

## Usage

In this library, there are two classes: `Terminal` and `UnclosableInputStream`. With `Terminal`, you can write to a print stream and read from an input stream. For *stdin*, `Terminal` makes use of `UnclosableInputStream` internally. An example:

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
		final boolean petOwner = Terminal.readBoolean("Do you own a pet? ", "yes", "y");
		if (petOwner)
		{
			Terminal.writeLine("You own a pet.");
		}
		else
		{
			Terminal.writeLine("You don't own a pet.");
		}
	}
}
```

This will produce the following output:

```
Welcome to this app!
This is the beginning of one line. This is continuing on that line.
Please state your age: 36
Your age is 36
Do you own a pet? No
You don't own a pet.
```

Use the overloaded methods that takes in parameters for input streams and print streams if you want to use other streams than `System.in` and `System.out`.

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

## Licenses

Java TUI is distributed under an *MIT License*. You'll find the full license terms in the file [LICENSE](LICENSE).


[javadl]: https://java.com/download/

[releasesPage]: https://github.com/olivertwistor/java-tui/releases
