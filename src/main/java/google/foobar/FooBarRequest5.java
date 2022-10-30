package google.foobar;

import java.math.BigInteger;

/**
 * Fuel Injection Perfection =========================
 * <p>
 * Commander Lambda has asked for your help to refine the automatic quantum antimatter fuel injection system for the LAMBCHOP doomsday device. It's a great
 * chance for you to get a closer look at the LAMBCHOP -- and maybe sneak in a bit of sabotage while you're at it -- so you took the job gladly.
 * <p>
 * Quantum antimatter fuel comes in small pellets, which is convenient since the many moving parts of the LAMBCHOP each need to be fed fuel one pellet at a
 * time. However, minions dump pellets in bulk into the fuel intake. You need to figure out the most efficient way to sort and shift the pellets down to a
 * single pellet at a time.
 * <p>
 * The fuel control mechanisms have three operations:
 * <p>
 * 1) Add one fuel pellet 2) Remove one fuel pellet 3) Divide the entire group of fuel pellets by 2 (due to the destructive energy released when a quantum
 * antimatter pellet is cut in half, the safety controls will only allow this to happen if there is an even number of pellets)
 * <p>
 * Write a function called solution(n) which takes a positive integer as a string and returns the minimum number of operations needed to transform the number of
 * pellets to 1. The fuel intake control panel can only display a number up to 309 digits long, so there won't ever be more pellets than you can express in that
 * many digits.
 * <p>
 * For example: solution(4) returns 2: 4 -> 2 -> 1 solution(15) returns 5: 15 -> 16 -> 8 -> 4 -> 2 -> 1 Quantum antimatter fuel comes in small pellets, which is
 * convenient since the many moving parts of the LAMBCHOP each need to be fed fuel one pellet at a time. However, minions dump pellets in bulk into the fuel
 * intake. You need to figure out the most efficient way to sort and shift the pellets down to a single pellet at a time.
 * <p>
 * The fuel control mechanisms have three operations:
 * <p>
 * 1) Add one fuel pellet 2) Remove one fuel pellet 3) Divide the entire group of fuel pellets by 2 (due to the destructive energy released when a quantum
 * antimatter pellet is cut in half, the safety controls will only allow this to happen if there is an even number of pellets)
 * <p>
 * Write a function called solution(n) which takes a positive integer as a string and returns the minimum number of operations needed to transform the number of
 * pellets to 1. The fuel intake control panel can only display a number up to 309 digits long, so there won't ever be more pellets than you can express in that
 * many digits.
 * <p>
 * For example: solution(4) returns 2: 4 -> 2 -> 1 solution(15) returns 5: 15 -> 16 -> 8 -> 4 -> 2 -> 1
 */
public class FooBarRequest5
{
	public static void main(String[] args)
	{
		//		String pellets =
		//		"9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
		String pellets = "7";
		System.err.println("Counter: " + solution(pellets));
	}

	private static final BigInteger BIG_MODULO = new BigInteger("4");
	private static final BigInteger BIG_DIVIDER = new BigInteger("2");

	/**
	 * Takes the specified positive integer (as String) and returns the minimum number of operations needed to transform the number to 1.
	 *
	 * @param x positive numeric
	 * @return number of operations to transform to 0
	 */
	public static int solution(String x)
	{
		BigInteger number = new BigInteger(x);
		boolean isTransformingNumber = true;
		int usedOperationsCounter = 0, remainder;

		//return 0 since number doesn't need to be transformed
		if(number.toByteArray().length == 1 && number.toByteArray()[0] <= 1)
			return 0;

		while(isTransformingNumber)
		{//determine rest of division to choose proper operation
			remainder = number.mod(BIG_MODULO).intValue();

			switch(remainder)
			{
			case 1://closer to 0 than closer to 4 -> subtract one
				number = number.subtract(BigInteger.ONE);
				break;
			case 3://closer to 4 than closer to 0 -> add one
				number = number.add(BigInteger.ONE);
				break;
			default: //even number, divide by two
				number = number.divide(BIG_DIVIDER);
			}

			if(number.toByteArray().length == 1 && number.toByteArray()[0] <= 3)
				//value is <128; toggle while-flag if it is also <= 3
				isTransformingNumber = false;
			else
				//count current used operation
				usedOperationsCounter++;
		}

		if(number.intValue() == 3)
			//three more operations until reaching 1
			usedOperationsCounter += 3;
		else if(number.intValue() == 2)
			//two more operations until reaching 1
			usedOperationsCounter += 2;

		return usedOperationsCounter;
	}

	/**
	 * I looked through java.math package and found {@link BigInteger} class.
	 *
	 * @param x
	 * @return
	 */
	public static int secondApproach(String x)
	{
		int operationsCount = 0;
		BigInteger number = new BigInteger(x);
		BigInteger bigInteger2 = new BigInteger("2");
		BigInteger denominator = new BigInteger("4");
		BigInteger[] result;

		while(number.longValue() > 1)
		{
			result = number.divideAndRemainder(denominator);
			//			number = result[0];

			if(result[1].intValue() == 3)
			{//add one
				number = number.add(BigInteger.ONE);
				System.err.println("number+1: " + number.toString());
			}
			else if(result[1].intValue() == 1)
			{//subtract one
				number = number.subtract(BigInteger.ONE);
				System.err.println("number-1: " + number.toString());
			}
			else
			{//divide two
				number = number.divide(bigInteger2);
				System.err.println("number/2: " + number.toString());
			}
			operationsCount++;
		}

		return operationsCount;
	}

	/**
	 * My first brute-force attempt. And yea, I know... it clearly says '309-digit number', and here I am: parsing it to Integer like I am thinking 'maybe they
	 * forgot about that part hehehe'. Nope, they weren't. It passes only four out of ten(!) test cases.
	 *
	 * @param x
	 * @return
	 */
	public static int solutionFirstTry(String x)
	{
		int operationsCount = 0;
		int numericValue = Integer.parseInt(x);

		while(numericValue > 1)
		{
			if(numericValue % 4 == 3)
				System.err.println(++numericValue);
			else if(numericValue % 4 == 1)
				System.err.println(--numericValue);
			else
				System.err.println(numericValue /= 2);

			operationsCount++;
		}

		return operationsCount;
	}
}
