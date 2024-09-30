package nu.olivertwistor.java.tui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for writing to and reading from the terminal (standard input and output).
 * <p>
 * It has convenience methods for reading strings, integers and booleans, as well as writing string representations of objects.
 *
 * @since 0.1.0
 */
public final class Terminal
{
	/**
	 * Writes the string representation of a given object to standard output, and ends with a new line.
	 *
	 * @param object the thing which string representation to write
	 *
	 * @see PrintStream#println(Object)
	 * @since 0.1.0
	 */
	public static void writeLine(final Object object)
	{
		writeLine(object, System.out);
	}

	/**
	 * Writes the string representation of a given object to a given print stream, and ends with a new line.
	 *
	 * @param object the thing which string representation to write
	 * @param stream the print stream to which to write
	 *
	 * @see PrintStream#println(Object)
	 * @since //todo next version
	 */
	public static void writeLine(final Object object, final PrintStream stream)
	{
		stream.println(object);
	}

	/**
	 * Writes the string representation of a given object to standard output, without a new line at the end.
	 *
	 * @param object the thing which string representation to write
	 *
	 * @see PrintStream#print(Object)
	 * @since 0.1.0
	 */
	public static void write(final Object object)
	{
		write(object, System.out);
	}

	/**
	 * Writes the string representation of a given object to a given print stream, without a new line at the end.
	 *
	 * @param object the thing which string representation to write
	 * @param stream the print stream to which to write
	 *
	 * @see PrintStream#print(Object)
	 * @since //todo next version
	 */
	public static void write(final Object object, final PrintStream stream)
	{
		stream.print(object);
	}

	/**
	 * Reads an integer from standard input. Everything until the next line feed is read, including whitespace. This method doesn't return until what's read really is an integer.
	 *
	 * @param prompt string to write to standard output before waiting for input, for example "How old are you? " before waiting for the user to input their age
	 *
	 * @return The user input as an integer.
	 *
	 * @throws IOException if reading from standard input failed.
	 * @throws NumberFormatException if user input failed to be converted to an integer.
	 * @see BufferedReader#readLine()
	 * @since 0.4.0 next version Now this method doesn't return until what's read really is an integer
	 * @since 0.1.0
	 */
	public static int readInt(final String prompt) throws IOException
	{
		return readInt(prompt, true);
	}

	/**
	 * Reads an integer from standard input. Everything until the next line feed is read, including whitespace. Optionally, this method doesn't return until a valid integer has been read.
	 *
	 * @param prompt string to write to standard output before waiting for input, for example "How old are you? " before waiting for the user to input their age
	 * @param requireValidInt whether this method should wait to return until a valid integer is read
	 *
	 * @return The user input as an integer.
	 *
	 * @throws IOException if reading from standard input failed.
	 * @throws NumberFormatException if requiredValidInt is false, and user input failed to be converted to an integer.
	 * @see BufferedReader#readLine()
	 * @since 0.4.0
	 */
	public static int readInt(final String prompt, final boolean requireValidInt) throws IOException
	{
		boolean validInput = false;
		int intInput = 0;

		while (!validInput)
		{
			final String rawInput = readString(prompt);
			try
			{
				intInput = Integer.parseInt(rawInput);
				validInput = true;
			}
			catch (final NumberFormatException e)
			{
				if (requireValidInt)
				{
					throw e;
				}
			}
		}

		return intInput;
	}

	/**
	 * Reads a string from standard input, using UTF-8. It reads everything until the next line feed, including whitespace.
	 *
	 * @param prompt string to write to standard output before waiting for input, for example "What is your name? " before waiting for the user to input their name
	 *
	 * @return The user input using the chosen character set.
	 *
	 * @throws IOException if reading from standard input failed.
	 * @see BufferedReader#readLine()
	 * @since //todo next version Input assumes UTF-8.
	 * @since 0.1.0
	 */
	public static String readString(final String prompt) throws IOException
	{
		// Use our InputStream decorator instead of raw System.in.
		System.setIn(new UnclosableInputStream(System.in));

		try
		{
			return readString(System.in, System.out, prompt);
		}
		finally
		{
			// Reset System.in.
			System.setIn(System.in);
		}
	}

	/**
	 * Reads a string from a given input stream, using UTF-8. It reads everything until the next line feed, including whitespace.
	 *
	 * @param inStream the input stream from which to read
	 * @param outStream the print stream to which to write
	 * @param prompt string to write to the out-stream before waiting for input, for example "What is your name? " before waiting for the user to input their name
	 *
	 * @return The user input using UTF-8.
	 *
	 * @throws IOException if reading from the in-stream failed.
	 * @see BufferedReader#readLine()
	 * @since //todo next version
	 */
	public static String readString(final InputStream inStream, final PrintStream outStream, final String prompt) throws IOException
	{
		outStream.print(prompt);

		try (final BufferedReader br = new BufferedReader(new InputStreamReader(inStream, StandardCharsets.UTF_8)))
		{
			return br.readLine();
		}
	}

	/**
	 * Reads an integer from a given input stream, using UTF-8. Everything until the next line feed is read, including whitespace. Optionally, this method doesn't return until a valid integer has been read.
	 *
	 * @param inStream the input stream from which to read
	 * @param outStream the print stream to which to write
	 * @param prompt string to write to the out-stream before waiting for input, for example "How old are you? " before waiting for the user to input their age
	 * @param requireValidInt whether this method should wait to return until a valid integer is read
	 *
	 * @return The user input as an integer.
	 *
	 * @throws IOException if reading from the in-stream failed.
	 * @throws NumberFormatException if requireValidInt is false, and user input failed to be converted to an integer.
	 * @see BufferedReader#readLine()
	 * @since //todo next version
	 */
	public static int readInt(final InputStream inStream, final PrintStream outStream, final String prompt, final boolean requireValidInt) throws IOException
	{
		boolean validInput = false;
		int intInput = 0;

		while (!validInput)
		{
			final String rawInput = readString(inStream, outStream, prompt);
			try
			{
				intInput = Integer.parseInt(rawInput);
				validInput = true;
			}
			catch (final NumberFormatException e)
			{
				if (!requireValidInt)
				{
					throw e;
				}
			}
		}

		return intInput;
	}

	/**
	 * Reads a string from standard input. It reads everything until the next line feed, including whitespace.
	 *
	 * @return The user input using UTF-8.
	 *
	 * @throws IOException if reading from standard input failed.
	 * @see BufferedReader#readLine()
	 * @since 0.1.0
	 */
	public static String readString() throws IOException
	{
		return readString("");
	}

	/**
	 * Reads an integer from standard input. Everything until the next line feed is read, including whitespace. Optionally, this method doesn't return until a valid integer has been read.
	 *
	 * @param requireValidInt whether this method should wait to return until a valid integer is read
	 *
	 * @return The user input as an integer.
	 *
	 * @throws IOException if reading from standard input failed.
	 * @throws NumberFormatException if user input failed to be converted to an integer.
	 * @see BufferedReader#readLine()
	 * @since 0.4.0 Now this method doesn't return until what's read really is an integer
	 * @since 0.1.0
	 */
	public static int readInt(final boolean requireValidInt) throws IOException
	{
		return readInt("", requireValidInt);
	}

	/**
	 * Reads an integer from standard input. Everything until the next line feed is read, including whitespace. This method doesn't return until what's read really is an integer.
	 *
	 * @return The user input as an integer.
	 *
	 * @throws IOException if reading from standard input failed.
	 * @throws NumberFormatException if user input failed to be converted to an integer.
	 * @see BufferedReader#readLine()
	 * @since 0.4.0
	 */
	public static int readInt() throws IOException
	{
		return readInt("", true);
	}

	/**
	 * Reads a boolean from standard input, using UTF-8. It reads everything until the next line feed, including whitespace.
	 *
	 * @param prompt string to write to standard output before waiting for input, for example "Are you hungry? " before waiting for the user to input their answer
	 * @param truthyValues strings that are to be interpreted as Boolean true, for example "y", "1" and "yes". All other strings are interpreted as Boolean false
	 *
	 * @return The user input, interpreted as a boolean.
	 *
	 * @throws IOException if reading from standard input failed.
	 * @see BufferedReader#readLine()
	 * @since 0.3.0
	 */
	public static boolean readBoolean(final String prompt, final String... truthyValues) throws IOException
	{
		return readBoolean(System.in, System.out, prompt, truthyValues);
	}

	/**
	 * Reads a boolean from a given input stream, using UTF-8. It reads everything until the next line feed, including whitespace.
	 *
	 * @param inStream the input stream from which to read
	 * @param outStream the output stream to which to write
	 * @param prompt string to write to the out-stream before waiting for input, for example "Are you hungry? " before waiting for the user to input their name
	 * @param truthyValues strings that are to be interpreted as Boolean true, for example "y", "1" and "yes". All other strings are interpreted as Boolean false
	 *
	 * @return The user input, interpreted as a boolean.
	 *
	 * @throws IOException if reading from standard input failed.
	 * @see BufferedReader#readLine()
	 * @since //todo next version Requires an input stream and an output stream instead of assuming standard in- and output. UTF-8 is also assumed.
	 * @since 0.3.0
	 */
	public static boolean readBoolean(final InputStream inStream, final PrintStream outStream, final String prompt, final String... truthyValues) throws IOException
	{
		final String rawInput = readString(inStream, outStream, prompt);
		final List<String> strings = Arrays.asList(truthyValues);

		return strings.contains(rawInput);
	}
}
