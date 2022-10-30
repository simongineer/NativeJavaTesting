package google.foobar;

import java.util.ArrayList;

/**
 * Find the Access Codes =====================
 * <p>
 * In order to destroy Commander Lambda's LAMBCHOP doomsday device, you'll need access to it. But the only door leading to the LAMBCHOP chamber is secured with
 * a unique lock system whose number of passcodes changes daily. Commander Lambda gets a report every day that includes the locks' access codes, but only the
 * Commander knows how to figure out which of several lists contains the access codes. You need to find a way to determine which list contains the access codes
 * once you're ready to go in.
 * <p>
 * Fortunately, now that you're Commander Lambda's personal assistant, Lambda has confided to you that all the access codes are "lucky triples" in order to make
 * it easier to find them in the lists. A "lucky triple" is a tuple (x, y, z) where x divides y and y divides z, such as (1, 2, 4). With that information, you
 * can figure out which list contains the number of access codes that matches the number of locks on the door when you're ready to go in (for example, if
 * there's 5 passcodes, you'd need to find a list with 5 "lucky triple" access codes).
 * <p>
 * Write a function solution(l) that takes a list of positive integers l and counts the number of "lucky triples" of (li, lj, lk) where the list indices meet
 * the requirement i < j < k.  The length of l is between 2 and 2000 inclusive.  The elements of l are between 1 and 999999 inclusive.  The solution fits within
 * a signed 32-bit integer. Some of the lists are purposely generated without any access codes to throw off spies, so if no triples are found, return 0.
 * <p>
 * For example, [1, 2, 3, 4, 5, 6] has the triples: [1, 2, 4], [1, 2, 6], [1, 3, 6], making the solution 3 total.
 */
public class FooBarRequest4
{

	//	private static int[] l = { 1, 99, 98, 97, 33, 4, 66, 13723, 111, 11111, 1111, 11, 1, 1, 1, 1111, 24, 52, 237374, 9696343, 15, 16, 17, 18 };
	//	private static int[] l = { 1,1,1,1,1,1,1,2,1,1,1,1,1,1,1 };
	private static int[] l = { 1, 99, 999999, 999999, 999999, 999999, 2, 8 };
	//			private static int[] l = {1,2,3,4,5,6};
	//		private static int[] l = { 1, 1, 1 };

	public static void main(String[] args)
	{
		System.err.println("Counted lucky triples: " + solution(l));
		//		System.err.println("Counted lucky triples: " + solution(l));
		System.err.println("####################################################");
		System.err.println("Counted lucky triples: " + solutionBruteforce(l));
	}

	/**
	 * Takes a list of positive integers and counts the number of "lucky triples" of (li, lj, lk) where the list indices meet the requirement i < j < k. If no
	 * triples are found, 0 will be returned.
	 *
	 * @param l list containing dividable triples
	 * @return count of dividable
	 */
	public static int solution(int[] l)
	{
		int luckyTriplesCount = 0;
		int smallerDividableCount, greaterDividableCount;

		//triplets can't be found with being triplets, I am </3 broken
		if(l.length < 3) {
			return luckyTriplesCount;
		}

		for(int j = 1; j < l.length - 1; j++) {
			//reset dividable counters to start over
			smallerDividableCount = 0;
			greaterDividableCount = 0;

			//go through each (smaller) element until current index
			for(int i = 0; i < j; i++) {
				//current element is divisible by smaller element
				if(l[j] % l[i] == 0) {
					smallerDividableCount++;
				}
			}

			//go through each (greater) element after current index
			for(int k = j + 1; k < l.length; k++) {
				//greater element is divisible by current element
				if(l[k] % l[j] == 0) {
					greaterDividableCount++;
				}
			}

			//add product of both sides: add 0 if one of both sides is 0; add quadratic if all numbers are divisible by itself (duplicates), etc.
			luckyTriplesCount += smallerDividableCount * greaterDividableCount;
		}

		return luckyTriplesCount;
	}

	/**
	 * This was my second working approach. It does have a O(n²m) -> O(n²) time complexity. Yet it didn't pass test case 3, unfortunately. I'd love to know
	 * what
	 * the inputs are <.<
	 *
	 * @param l
	 * @return
	 */
	public static int solutionOptimized(int[] l)
	{
		int luckyTriplesCount = 0;
		ArrayList<Integer[]> memoDividerIndex = new ArrayList<>();

		if(l.length < 3)
			return 0;

		//go through each element
		for(int i = 0; i < l.length - 1; i++)
		{//take each time next element as divider
			for(int j = i + 1; j < l.length; j++)
			{//check if values are dividable, if so memoize value and its index
				if(l[j] % l[i] == 0)
				{
					memoDividerIndex.add(new Integer[] { l[j], j });
				}
			}
		}

		//yea, not great but still O(n²)
		for(int k = 0; k < l.length; k++)
		{//go through each memoized value#index pair
			for(Integer[] smallerDividable : memoDividerIndex)
			{//check if saved index is below current index & current value is dividable with memoized value
				if(smallerDividable[1] < k && l[k] % smallerDividable[0] == 0)
				{
					luckyTriplesCount++;
				}
			}
		}

		return luckyTriplesCount;
	}

	/**
	 * This was my first working approach, yet with bruteforce and a horrible O(n³).
	 *
	 * @param l array to find self dividable triples from
	 * @return dividable triples count
	 */
	public static int solutionBruteforce(int[] l)
	{
		int luckyTriplesCount = 0;

		if(l.length < 3)
			return 0;

		for(int i = l.length - 1; i >= 2; i--)
			for(int j = i - 1; j >= 1; j--)
				for(int k = j - 1; k >= 0; k--)
					if(l[i] % l[j] == 0 && l[j] % l[k] == 0)
						luckyTriplesCount++;

		return luckyTriplesCount;
	}
}
