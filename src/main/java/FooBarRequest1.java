import java.util.Hashtable;

/**
 * Braille Translation
 * <p>
 * Because Commander Lambda is an equal-opportunity despot, they have several visually-impaired minions. But Lambda never bothered to follow intergalactic
 * standards for workplace accommodations, so those minions have a hard time navigating her space station. You figure printing out Braille signs will help them,
 * and -- since you'll be promoting efficiency at the same time -- increase your chances of a promotion.
 * <p>
 * Braille is a writing system used to read by touch instead of by sight. Each character is composed of 6 dots in a 2x3 grid, where each dot can either be a
 * bump or be flat (no bump). You plan to translate the signs around the space station to Braille so that the minions under Commander Lambda's command can feel
 * the bumps on the signs and "read" the text with their touch. The special printer which can print the bumps onto the signs expects the dots in the following
 * order: 1 4 2 5 3 6
 * <p>
 * So given the plain text word "code", you get the Braille dots:
 * <p>
 * 11 10 11 10 00 01 01 01 00 10 00 00
 * <p>
 * where 1 represents a bump and 0 represents no bump.  Put together, "code" becomes the output string "100100101010100110100010".
 * <p>
 * Write a function solution(plaintext) that takes a string parameter and returns a string of 1's and 0's representing the bumps and absence of bumps in the
 * input string. Your function should be able to encode the 26 lowercase letters, handle capital letters by adding a Braille capitalization mark before that
 * character, and use a blank character (000000) for spaces. All signs on the space station are less than fifty characters long and use only letters and
 * spaces.
 * <p>
 * Test cases:
 * <p>
 * Input: solution("code") Output: 100100101010100110100010
 * <p>
 * Input: solution("Braille") Output: 000001110000111010100000010100111000111000100010
 * <p>
 * Input: solution("The quick brown fox jumps over the lazy dog") Output:
 * 000001011110110010100010000000111110101001010100100100101000000000110000111010101010010111101110000000110100101010101101000000010110101001101100111100011100000000101010111001100010111010000000011110110010100010000000111000100000101011101111000000100110101010110110
 */
public class FooBarRequest1
{
	/** Table used to map characters and symbols to Braille signs. **/
	private static final Hashtable<Character, String> charSignalTable = new Hashtable<>();

	static
	{//didn't find any pattern in braille signs, so here is the hardcoded mapping to 26(!) latin letters
		charSignalTable.put(' ', "000000"); //blank
		charSignalTable.put('a', "100000");
		charSignalTable.put('b', "110000");
		charSignalTable.put('c', "100100");
		charSignalTable.put('d', "100110");
		charSignalTable.put('e', "100010");
		charSignalTable.put('f', "110100");
		charSignalTable.put('g', "110110");
		charSignalTable.put('h', "110010");
		charSignalTable.put('i', "010100");
		charSignalTable.put('j', "010110");
		charSignalTable.put('k', "101000");
		charSignalTable.put('l', "111000");
		charSignalTable.put('m', "101100");
		charSignalTable.put('n', "101110");
		charSignalTable.put('o', "101010");
		charSignalTable.put('p', "111100");
		charSignalTable.put('q', "111110");
		charSignalTable.put('r', "111010");
		charSignalTable.put('s', "011100");
		charSignalTable.put('t', "011110");
		charSignalTable.put('u', "101001");
		charSignalTable.put('v', "111001");
		charSignalTable.put('w', "010111");
		charSignalTable.put('x', "101101");
		charSignalTable.put('y', "101111");
		charSignalTable.put('z', "101011");
	}

	public static void main(String[] args)
	{
		System.out.println(solution("The quick brown fox jumps over the lazy dog"));
	}

	/**
	 * Some quick note: All my test cases failed and I couldn't figure why. I downgraded my local setup from JDK17 to JDK8, tried a different approach avoiding
	 * the Hashtable and static initializer and removed a non-printable sign as capitalization mark (still hard coded it in code now). Yet no success.<br> I
	 * read the readme over and over again, and there was one thing I didn't pay attention: <b>The String parameter name in method 'solution(String)'</b>.<br>
	 * It generated me following signature:<br><code>public static String solution(String s)</code> instead it should be<br><code>public static String
	 * solution(String plaintext)</code>.<br> Well, I wasted 2 hours for that...
	 *
	 * @param plaintext to translate into Braille signs
	 * @return translated braille signs
	 */
	public static String solution(String plaintext)
	{
		String brailleSigns = "";
		char currentCharacter = ' ';

		for(int i = 0; i < plaintext.length(); i++)
		{
			currentCharacter = plaintext.charAt(i);

			if(Character.isUpperCase(currentCharacter))
			{//current character is upper case, add capitalization mark and set char lower case
				brailleSigns = brailleSigns.concat("000001");
				currentCharacter = Character.toLowerCase(currentCharacter);
			}

			if(charSignalTable.containsKey(currentCharacter))
				//add value of keyed character
				brailleSigns = brailleSigns.concat(charSignalTable.get(currentCharacter));
		}

		return brailleSigns;
	}
}
