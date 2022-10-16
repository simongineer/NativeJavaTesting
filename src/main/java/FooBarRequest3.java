import java.util.ArrayList;

/**
 * Hey, I Already Did That!
 * ========================
 *
 * Commander Lambda uses an automated algorithm to assign minions randomly to tasks, in order to keep minions on their toes. But you've noticed a flaw in the
 * algorithm -- it eventually loops back on itself, so that instead of assigning new minions as it iterates, it gets stuck in a cycle of values so that the
 * same minions end up doing the same tasks over and over again. You think proving this to Commander Lambda will help you make a case for your next promotion.
 *
 * You have worked out that the algorithm has the following process:
 *
 * 1) Start with a random minion ID n, which is a nonnegative integer of length k in base b
 * 2) Define x and y as integers of length k.  x has the digits of n in descending order, and y has the digits of n in ascending order
 * 3) Define z = x - y.  Add leading zeros to z to maintain length k if necessary
 * 4) Assign n = z to get the next minion ID, and go back to step 2
 *
 * For example, given minion ID n = 1211, k = 4, b = 10, then x = 2111, y = 1112 and z = 2111 - 1112 = 0999. Then the next minion ID will be n = 0999 and the
 * algorithm iterates again: x = 9990, y = 0999 and z = 9990 - 0999 = 8991, and so on.
 *
 * Depending on the values of n, k (derived from n), and b, at some point the algorithm reaches a cycle, such as by reaching a constant value. For example,
 * starting with n = 210022, k = 6, b = 3, the algorithm will reach the cycle of values [210111, 122221, 102212] and it will stay in this cycle no matter how
 * many times it continues iterating. Starting with n = 1211, the routine will reach the integer 6174, and since 7641 - 1467 is 6174, it will stay as that
 * value no matter how many times it iterates.
 *
 * Given a minion ID as a string n representing a nonnegative integer of length k in base b, where 2 <= k <= 9 and 2 <= b <= 10, write a function solution(n,
 * b) which returns the length of the ending cycle of the algorithm above starting with n. For instance, in the example above, solution(210022, 3) would
 * return 3, since iterating on 102212 would return to 210111 when done in base 3. If the algorithm reaches a constant, such as 0, then the length is 1.
 *
 * Test cases
 * Input:
 * Solution.solution('210022', 3)
 * Output:
 *     3
 * Input:
 * Solution.solution('1211', 10)
 * Output:
 *     1
 */
public class FooBarRequest3
{
	private final static int ASCII_OFFSET = 48;
	private final static ArrayList<String> minionIds = new ArrayList<>();

	public static void main(String[] args) {
		String n = "1211";
		int b = 10;
		System.err.println(solution(n, b));
	}

	public static int solution(String n, int b) {
		//only handle numerics from this point on
		if(!n.matches("\\d+"))
			return -1;

		while(!minionIds.contains(n)) {
			minionIds.add(n);
			Wrapper wrapper = sortToDescAndAsc(n);
			//subtract y from x and make sure both terms as well as result is in same base
			String z = Integer.toUnsignedString(Integer.parseInt(wrapper.getDescX(), b) - Integer.parseInt(wrapper.getAscY(), b), b);
			n = fillWithLeadingCharacter(z, n.length(), '0');
		}

		return minionIds.size() - minionIds.indexOf(n);
	}

	/**
	 * Fills specified string with the character in front as long as totalLength was reached. If the specified totalLength is already greater or equals string
	 * length, it will be returned immediately.
	 *
	 * @param n           string to append leading character
	 * @param totalLength total length of string with leading characters
	 * @param character   to append to string in front
	 * @return in front appended characters string
	 */
	private static String fillWithLeadingCharacter(String n, int totalLength, Character character) {
		//return string immediately, since totalLength is lower/equals n's length
		if(n.length() >= totalLength)
			return n;

		StringBuilder stringBuilderNewN = new StringBuilder(n);
		//add character to string in front as long as length wasn't reached
		for(int i = n.length(); i < totalLength; i++)
			stringBuilderNewN.insert(0, character);

		return stringBuilderNewN.toString();
	}

	/**
	 * Sorts the specified numeric string and returns a {@link Wrapper} containing {@link Wrapper#ascY} in ascended- and {@link Wrapper#descX} in descended
	 * sorting order.<br> The sorting is done via counting sort algorithm.
	 *
	 * @param n numeric input string to be sorted
	 * @return wrapper instance containing ascended and descended sorted fields
	 */
	private static Wrapper sortToDescAndAsc(String n) {
		Wrapper wrapper = new Wrapper();
		int[] countArray = new int[10];//0-9 -> 10 decimals
		int[] ascSortedArray = new int[n.length()];
		int[] descSortedArray = new int[n.length()];
		StringBuilder yBuilder = new StringBuilder(), xBuilder = new StringBuilder();

		//count the occurrences of digits
		for(int i = 0; i < n.length(); i++) {
			int decimal = n.charAt(i) - ASCII_OFFSET;
			countArray[decimal]++;
		}

		//add up each value with previous value
		for(int count = 1; count < countArray.length; count++)
			countArray[count] += countArray[count - 1];

		//insert value of input regarding to count array to both desc- and ascending arrays
		for(int j = 0, i = n.length(); i > 0; i--, j++) {
			int inputIndexValue = n.length() - j - 1;
			int countIndexValue = n.charAt(inputIndexValue) - ASCII_OFFSET;
			//decrease count array value to allow making space for duplicates
			countArray[countIndexValue]--;
			//add value to ascending sorted array position
			ascSortedArray[countArray[countIndexValue]] = countIndexValue;
			//add value to descending sorted array position
			descSortedArray[n.length() - 1 - countArray[countIndexValue]] = countIndexValue;
		}

		//convert the sorted arrays to one string each
		for(int i = 0; i < ascSortedArray.length; i++) {
			yBuilder.append(ascSortedArray[i]);
			xBuilder.append(descSortedArray[i]);
		}

		wrapper.setDescX(xBuilder.toString());
		wrapper.setAscY(yBuilder.toString());

		return wrapper;
	}

	/**
	 * Wrapper object containing {@link #descX} and {@link #ascY}.
	 */
	private static class Wrapper {
		private String descX, ascY;
		public void setDescX(String descX) { this.descX = descX; }
		public void setAscY(String ascY) { this.ascY = ascY; }
		public String getDescX() { return descX; }
		public String getAscY() { return ascY; }
	}
}
