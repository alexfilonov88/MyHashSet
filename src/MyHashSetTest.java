import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyHashSetTest
{
	MyHashSet<Integer> set;
	Integer[] actual = new Integer[] {2,3,5,7,11,13,17,19};
	List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5,6,7));
	List<Integer> list1 = new ArrayList<>(List.of(2,3,5,7));
	
	@BeforeEach
	void setUp() throws Exception
	{
		set = new MyHashSet<>();
		for(Integer num: actual)
		{
			set.add(num);
		}
	}
	
	@Test
	void testContainsAll()
	{
		assertTrue(set.containsAll(list1));
		assertFalse(set.containsAll(list));
	}
	
	@Test
	void testRemoveAll()
	{
		assertTrue(set.removeAll(list1));
		assertFalse(set.contains(2));
	}
	
	@Test
	void testRetainAll()
	{
		assertTrue(set.retainAll(list1));
		assertTrue(set.contains(2));
	}
	
	@Test
	void testAddAll()
	{
		assertFalse(set.addAll(list1));
		assertEquals(set.size(), actual.length);
		assertTrue(set.addAll(list));
		assertTrue(set.contains(1));
	}

	@Test
	void testAdd()
	{
		assertEquals(set.size(), actual.length);
		assertFalse(set.add(2));
		assertEquals(set.size(), actual.length);
		assertTrue(set.add(0));
		assertEquals(set.size(), actual.length+1);
	}
	
	@Test
	void testContains()
	{
		for(Integer num: actual)
		{
			assertTrue(set.contains(num));
		}
	}

	@Test
	void testRemove()
	{
		assertFalse(set.remove(10));
		assertEquals(set.size(), actual.length);
		assertTrue(set.remove(11));
		assertEquals(set.size(), actual.length - 1);
		assertFalse(set.contains(11));
	}
	
	@Test
	void testToArray()
	{
		Arrays.sort(actual);
		Object[] temp = set.toArray();
		Arrays.sort(temp);
		for(int i=0; i<actual.length; i++)
		{
			assertEquals(actual[i], temp[i]);
		}
	}
	
	@Test
	void testClear()
	{
		assertFalse(set.isEmpty());
		set.clear();
		assertTrue(set.isEmpty());
	}
	
	
}
