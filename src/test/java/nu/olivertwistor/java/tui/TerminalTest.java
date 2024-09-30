package nu.olivertwistor.java.tui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.UTF_8;

class TerminalTest
{
	@Test
	void whenWriteLine_givenStringObject_objectIsOutputToStream() throws UnsupportedEncodingException
	{
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (final PrintStream ps = new PrintStream(baos, true, UTF_8.name()))
		{
			Terminal.writeLine("Hello World!", ps);
		}
		final String actual = new String(baos.toByteArray(), UTF_8);

		Assertions.assertEquals("Hello World!" + System.lineSeparator(), actual, "Given string object isn't output correctly.");
	}

	@Test
	void whenWrite_givenStringObject_objectIsOutputToStream() throws UnsupportedEncodingException
	{
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (final PrintStream ps = new PrintStream(baos, true, UTF_8.name()))
		{
			Terminal.write("Hello World!", ps);
		}
		final String actual = new String(baos.toByteArray(), UTF_8);

		Assertions.assertEquals("Hello World!", actual, "Given string object isn't output correctly.");
	}

	@Test
	void whenReadString_givenStringInput_readStringAndInputIsEqual() throws IOException
	{
		String actual = "";
		try (final InputStream is = new ByteArrayInputStream("Hello World!".getBytes(UTF_8)))
		{
			actual = Terminal.readString(is, System.out, "");
		}

		Assertions.assertEquals("Hello World!", actual, "Given string object doesn't match input.");
	}

	@Test
	void whenReadInt_givenIntInput_intIsReturned() throws IOException
	{
		int actual = 0;
		try (final InputStream is = new ByteArrayInputStream("42".getBytes(UTF_8)))
		{
			actual = Terminal.readInt(is, System.out, "", false);
		}

		Assertions.assertEquals(42, actual, "Given integer doesn't match input.");
	}

	@Test
	void whenReadInt_givenNotIntInput_andValidInputNotRequired_exceptionShouldBeThrown()
	{
		Assertions.assertThrows(NumberFormatException.class, () -> {
			try (final InputStream is = new ByteArrayInputStream("Hello World!".getBytes(UTF_8)))
			{
				Terminal.readInt(is, System.out, "", false);
			}
		});
	}

	@Test
	void whenReadBoolean_givenTruthyValue_trueShouldBeReturned() throws IOException
	{
		boolean actual = false;
		try (final InputStream is = new ByteArrayInputStream("true".getBytes(UTF_8)))
		{
			actual = Terminal.readBoolean(is, System.out, "", "true", "yes");
		}

		Assertions.assertTrue(actual, "When given a truthy value, Boolean true should be returned.");
	}

	@Test
	void whenReadBoolean_givenFalseyValue_falseShouldBeReturned() throws IOException
	{
		boolean actual = true;
		try (final InputStream is = new ByteArrayInputStream("false".getBytes(UTF_8)))
		{
			actual = Terminal.readBoolean(is, System.out, "", "true", "yes");
		}

		Assertions.assertFalse(actual, "When given a falsey value, Boolean false should be returned.");
	}
}
