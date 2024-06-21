package performanceTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestLoopsAppl
{
	private static final int SIZE = 1000000;
	private static final int MIN = -100;
	private static final int MAX = 100;

	public static void main(String[] args)
	{
		int[] array = new int[SIZE];
		fillArray(array);
		
		long sum =0;
		long start = System.currentTimeMillis();
		for(int i=0; i<array.length; i++)
		{
			sum += array[i];
		}
		System.out.println("Sum = " + sum);
		System.out.println("Duration array for " + (System.currentTimeMillis() - start));
		
		sum =0;
		start = System.currentTimeMillis();
		for(int i: array)
		{
			sum += i;
		}
		System.out.println("Sum = " + sum);
		System.out.println("Duration array for each " + (System.currentTimeMillis() - start));
		
		List<Integer> list = new LinkedList<>();
		fillList(list);
		
		sum =0;
		start = System.currentTimeMillis();
		for(int i: list)
		{
			sum += i;
		}
		System.out.println("Sum = " + sum);
		System.out.println("Duration list for each " + (System.currentTimeMillis() - start));
		
		sum =0;
		start = System.currentTimeMillis();
		Iterator<Integer> iter = list.iterator();
		while(iter.hasNext())
		{
			sum += iter.next();
		}
		System.out.println("Sum = " + sum);
		System.out.println("Duration list iterator " + (System.currentTimeMillis() - start));
		
		sum =0;
		start = System.currentTimeMillis();
		for(int i=0; i<list.size(); i++)
		{
			sum += list.get(i);
		}
		System.out.println("Sum = " + sum);
		System.out.println("Duration list for " + (System.currentTimeMillis() - start));
		
	}

	private static void fillList(List<Integer> list)
	{
		for(int i=0; i<SIZE; i++)
		{
			list.add(MIN + new Random().nextInt(MAX - MIN + 1));
		}
	}

	private static void fillArray(int[] array)
	{
		for(int i=0; i<array.length; i++)
		{
			array[i] = MIN + new Random().nextInt(MAX - MIN + 1);
		}
	}

}
