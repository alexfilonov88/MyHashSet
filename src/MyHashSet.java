import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MyHashSet<E> implements Set<E>
{
	private List<E>[] table;
	private int size;
	private int capacity;
	private double loadFactor;

	@SuppressWarnings("unchecked")
	public MyHashSet(int capacity, double loadFactor)
	{
		table = new LinkedList[capacity];
		this.capacity = capacity;
		this.loadFactor = loadFactor;
	}

	public MyHashSet()
	{
		this(16, 0.75);
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override // O(1)
	public boolean contains(Object o)
	{
		int index = getIndex((E) o);

		if (table[index] == null || table[index].isEmpty())
			return false;

		return table[index].contains(o);
	}

	@Override
	public Iterator<E> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray()
	{
		Object[] array = new Object[size];
		int indexArray = 0;

		for (int i = 0; i < table.length; i++)
		{
			if (table[i] == null || table[i].isEmpty())
				continue;

			for (Object obj : table[i])
			{
				array[indexArray++] = obj;
			}
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override // O(1)
	public boolean add(E e)
	{
		int index = getIndex(e);
		if (table[index] != null && table[index].contains(e))
			return false;

		if (capacity * loadFactor <= size)
			recreation();

		if (table[index] == null)
			table[index] = new LinkedList<E>();

		table[index].add(e);
		size++;
		return true;
	}

	@SuppressWarnings("unchecked")
	private void recreation()
	{
		capacity *= 2;
		LinkedList<E>[] temp = new LinkedList[capacity];
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] == null)
				continue;

			for (E obj : table[i])
			{
				int index = getIndex(obj);
				if (temp[index] == null)
					temp[index] = new LinkedList<E>();
				temp[index].add(obj);
			}
		}
		table = temp;
	}

	private int getIndex(E e)
	{
		int hash = e.hashCode();
		return Math.abs(hash) % capacity;
	}

	@Override
	public boolean remove(Object o)
	{
		if (!contains(o))
			return false;

		int index = getIndex((E) o);
		table[index].remove(o);
		size--;
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		for (Object obj : c)
		{
			if (!contains(obj))
				return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		int oldSize = size;
		for (E obj : c)
		{
			add(obj);
		}
		return oldSize < size;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		int oldSize = size;

		Iterator<E> iter = iterator();
		while (iter.hasNext())
		{
			E temp = iter.next();
			if (!c.contains(temp))
				iter.remove();
		}

		return oldSize > size;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		int oldSize = size;

		Iterator<E> iter = iterator();
		while (iter.hasNext())
		{
			E temp = iter.next();
			if (c.contains(temp))
				iter.remove();
		}

		return oldSize > size;
	}

	@Override
	public void clear()
	{
		table = new LinkedList[capacity];
		size = 0;
	}

}
