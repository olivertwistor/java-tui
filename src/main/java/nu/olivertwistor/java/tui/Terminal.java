package nu.olivertwistor.java.tui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for writing to and reading from the terminal (standard input and output).
 *
 * It has convenience methods for reading strings and integers, as well as writing string representations of objects. Many of these methods are wrappers for {@link PrintStream} and {@link BufferedReader}.
 *
 * @since  0.1.0
 */
@SuppressWarnings({"ClassWithoutLogger", "PublicMethodWithoutLogging",
		"WeakerAccess"})
public final class Terminal
{
	/**
	 * Writes the string representation of a given object to standard output, and ends with a new line.
	 *
	 * @param object the thing which string representation to write
	 *
	 * @since 0.1.0
	 *
	 * @see PrintStream#println(Object)
	 */
	public static void writeLine(final Object object)
	{
		System.out.println(object);
	}

	/**
	 * Writes the string representation of a given object to standard output, without a new line at the end.
	 *
	 * @param object the thing which string representation to write
	 *
	 * @since 0.1.0
	 *
	 * @see PrintStream#print(Object)
	 */
	public static void write(final Object object)
	{
		System.out.print(object);
	}

	/**
	 * Reads a string from standard input. It reads everything until the next line feed, including whitespace.
	 *
	 * @param prompt  string to write to standard output before waiting for input, for example "What is your name? " before waiting for the user to input their name
	 * @param charset which character set to use when parsing input
	 *
	 * @return The user input using the chosen character set.
	 *
	 * @throws IOException if reading from standard input failed.
	 *
	 * @since 0.1.0
	 *
	 * @see BufferedReader#readLine()
	 */
	@SuppressWarnings("WeakerAccess")
	public static String readString(final String prompt, final Charset charset)	throws IOException
	{

		System.out.print(prompt);

		// Use our InputStream decorator instead of raw System.in.
		System.setIn(new UnclosableInputStream(System.in));

		try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in, charset)))
		{
			return br.readLine();
		}
		finally
		{
			// Reset System.in.
			System.setIn(System.in);
		}
	}

	/**
	 * Reads a string from standard input. It reads everything until the next line feed, including whitespace.
	 *
	 * @param prompt string to write to standard output before waiting for input, for example "What is your name? " before waiting for the user to input their name
	 *
	 * @return The user input using the character set UTF-8.
	 *
	 * @throws IOException if reading from standard input failed.
	 *
	 * @since 0.1.0
	 *
	 * @see BufferedReader#readLine()
	 */
	@SuppressWarnings("WeakerAccess")
	public static String readString(final String prompt) throws IOException
	{
		return readString(prompt, StandardCharsets.UTF_8);
	}

	/**
	 * Reads a string from standard input. It reads everything until the next line feed, including whitespace.
	 *
	 * @param charset which character set to use when parsing input
	 *
	 * @return The user input using the chosen character set.
	 *
	 * @throws IOException if reading from standard input failed.
	 *
	 * @since 0.1.0
	 *
	 * @see BufferedReader#readLine()
	 */
	public static String readString(final Charset charset) throws IOException
	{
		return readString("", charset);
	}

	/**
	 * Reads a string from standard input. It reads everything until the next line feed, including whitespace.
	 *
	 * @return The user input using the character set UTF-8.
	 *
	 * @throws IOException if reading from standard input failed.
	 *
	 * @since 0.1.0
	 *
	 * @see BufferedReader#readLine()
	 */
	public static String readString() throws IOException
	{
		return readString("");
	}

	/**
	 * Reads an integer from standard input. Everything until the next line feed is read, including whitespace. Optionally, this method doesn't return until a valid integer has been read.
	 *
	 * @param prompt string to write to standard output before waiting for input, for example "How old are you? " before waiting for the user to input their age
	 * @param requireValidInt whether this method should wait to return until a valid integer is read
	 *
	 * @return The user input as an integer.
	 *
	 * @throws IOException           if reading from standard input failed.
	 * @throws NumberFormatException if user input failed to be converted to an integer.
	 *
	 * @since //todo next version
	 *
	 * @see BufferedReader#readLine()
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
				if (!requireValidInt)
				{
					throw e;
				}
			}
		}

		return intInput;
	}

	/**
	 * Reads an integer from standard input. Everything until the next line feed is read, including whitespace. This method doesn't return until what's read really is an integer.
	 *
	 * @param prompt string to write to standard output before waiting for input, for example "How old are you? " before waiting for the user to input their age
	 *
	 * @return The user input as an integer.
	 *
	 * @throws IOException           if reading from standard input failed.
	 * @throws NumberFormatException if user input failed to be converted to an integer.
	 *
	 * @since //todo next version Now this method doesn't return until what's read really is an integer
	 * @since 0.1.0
	 *
	 * @see BufferedReader#readLine()
	 */
	public static int readInt(final String prompt) throws IOException
	{
		return readInt(prompt, true);
	}

	/**
	 * Reads an integer from standard input. Everything until the next line feed is read, including whitespace. Optionally, this method doesn't return until a valid integer has been read.
	 *
	 * @param requireValidInt whether this method should wait to return until a valid integer is read
	 *
	 * @return The user input as an integer.
	 *
	 * @throws IOException           if reading from standard input failed.
	 * @throws NumberFormatException if user input failed to be converted to an integer.
	 *
	 * @since //todo next version Now this method doesn't return until what's read really is an integer
	 * @since 0.1.0
	 *
	 * @see BufferedReader#readLine()
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
	 * @throws IOException           if reading from standard input failed.
	 * @throws NumberFormatException if user input failed to be converted to an integer.
	 *
	 * @since //todo next version
	 *
	 * @see BufferedReader#readLine()
	 */
	public static int readInt() throws IOException
	{
		return readInt("", true);
	}

	/**
	 * Reads a boolean from standard input. It reads everything until the next line feed, including whitespace.
	 *
	 * @param prompt       string to write to standard output before waiting for input, for example "What is your name? " before waiting for the user to input their name
	 * @param charset      which character set to use when parsing input
	 * @param truthyValues strings that are to be interpreted as Boolean true, for example "y", "1" and "yes". All other strings are interpreted as Boolean false
	 *
	 * @return The user input using the chosen character set, interpreted as a boolean.
	 *
	 * @throws IOException if reading from standard input failed.
	 *
	 * @since 0.3.0
	 *
	 * @see BufferedReader#readLine()
	 */
	public static boolean readBoolean(final String prompt, final Charset charset, final String[] truthyValues) throws IOException
	{
		final String rawInput = readString(prompt, charset);
		final List<String> strings = Arrays.asList(truthyValues);

		return strings.contains(rawInput);
	}

	/**
	 * Reads a boolean from standard input. It reads everything until the next line feed, including whitespace.
	 *
	 * @param prompt       string to write to standard output before waiting for input, for example "What is your name? " before waiting for the user to input their name
	 * @param truthyValues strings that are to be interpreted as Boolean true, for example "y", "1" and "yes". All other strings are interpreted as Boolean false
	 *
	 * @return The user input using UTF-8, interpreted as a boolean.
	 *
	 * @throws IOException if reading from standard input failed.
	 *
	 * @since 0.3.0
	 *
	 * @see BufferedReader#readLine()
	 */
	public static boolean readBoolean(final String prompt, final String[] truthyValues)	throws IOException
	{
		return readBoolean(prompt, StandardCharsets.UTF_8, truthyValues);
	}

	/**
	 * Empty constructor, only for preventing instantiation.
	 *
	 * @since 0.1.0
	 */
	private Terminal() { }
}
