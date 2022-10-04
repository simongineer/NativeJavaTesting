import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
	public static void main(String[] args)
	{
		int[] arr1 = { 1, 4, 6, 8, 10, 12, 13, 14, 15, 22, 24};
		int[] arr2 = { 2, 3, 5 };
		int[][][] test = new int[5][8][7];

		System.err.println("Length: "+test.length);

		Linked

//		System.out.println(medianOfArrays(arr1.length, arr2.length, arr1, arr2));
//		logicOp(true, false);

	}

	static double medianOfArrays(int n, int m, int a[], int b[])
	{
		int middle = -1;
		double median = -1;
		List<Integer> finalList = new ArrayList<>();
		List<Integer> sortedList;

		for(int i : a)
			finalList.add(i);
		for(int i : b)
			finalList.add(i);

		sortedList = finalList.stream().sorted().collect(Collectors.toList());

		System.err.println("List: " + sortedList);

		middle = sortedList.size()/2;


		if(sortedList.size() % 2 != 0)
		{//odd number count, need to consider two elements
			median = sortedList.get(middle);
		}
		else
		{//even number count, just take the middle element
			median = ((double) (sortedList.get(middle) + sortedList.get(middle - 1))) / 2;
		}

		System.err.println("Middle: "+sortedList.get(middle));

		return median;
	}

	static void logicOp(boolean a, boolean b){
		/*output (a&&b), (a||b), and ((!a)&&(!b))separated by spaces*/
		System.out.println((a&&b)+" "+(a||b)+" "+(!a&&!b));
	}
}
