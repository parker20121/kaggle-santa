package nparker.gapi.structure;

import java.util.*;

/**
 * This class is a utility class to implement a basic population for a genetic
 * algorithm. Instantiating a <code>BasePopulation</code> creates a
 * &quot;weak&quot; synchronized <code>List</code> using the
 * <code>Collections.synchronizedList()</code> method.
 *
 * @author Nathan Parker
 * @version 1.0
 * @see java.util.Collections#synchronizedList(List)
 */

public class BasePopulation implements Population
{
    /**
     * The population of this genetic algorithm as a synchronized
     * <code>List</code>.
     */
    private List population;

    public BasePopulation()
    {
        this.population = Collections.synchronizedList(new LinkedList());
    }

    public BasePopulation(String listType)
    {
        if (listType == null)
            throw new IllegalArgumentException("listType is null in " +
                "BasePopulation constructor(listType)");

        if (listType.equalsIgnoreCase("Vector"))
            this.population = Collections.synchronizedList(new Vector());
        else if (listType.equalsIgnoreCase("ArrayList"))
            this.population = Collections.synchronizedList(new ArrayList());
        else
            this.population = Collections.synchronizedList(new LinkedList());
    }

    public BasePopulation(String listType, Collection c)
    {
        this(listType);
        this.population.addAll(c);
    }

    public BasePopulation(List l)
    {
        this.population = Collections.synchronizedList(l);
    }

    public void add(int index, Object element)
    {
        this.population.add(index, element);
    }

    public boolean add(Object o)
    {
        return this.population.add(o);
    }

    public boolean addAll(Collection c)
    {
        return this.population.addAll(c);
    }

    public boolean addAll(int index, Collection c)
    {
        return this.population.addAll(index, c);
    }

    public void clear()
    {
        this.population.clear();
    }

    public int size()
    {
        return this.population.size();
    }

    public boolean isEmpty()
    {
        return this.population.isEmpty();
    }

    public boolean contains(Object o)
    {
        return this.population.contains(o);
    }

    public Iterator iterator()
    {
        return this.population.iterator();
    }

    public Object[] toArray()
    {
        return this.population.toArray();
    }

    public Object[] toArray(Object[] a)
    {
        return this.population.toArray(a);
    }

    public boolean remove(Object o)
    {
        return this.population.remove(o);
    }

    public boolean containsAll(Collection c)
    {
        return this.population.containsAll(c);
    }

    public boolean removeAll(Collection c)
    {
        return this.population.removeAll(c);
    }

    public boolean retainAll(Collection c)
    {
        return this.population.retainAll(c);
    }

    public boolean equals(Object o)
    {
        return this.population.equals(o);
    }

    public Object get(int index)
    {
        return this.population.get(index);
    }

    public Object set(int index, Object element)
    {
        return this.population.set(index, element);
    }

    public Object remove(int index)
    {
        return this.population.remove(index);
    }

    public int indexOf(Object o)
    {
        return this.population.indexOf(o);
    }

    public int lastIndexOf(Object o)
    {
        return this.population.lastIndexOf(o);
    }

    public ListIterator listIterator()
    {
        return this.population.listIterator();
    }

    public ListIterator listIterator(int index)
    {
        return this.population.listIterator(index);
    }

    public List subList(int fromIndex, int toIndex)
    {
        return this.population.subList(fromIndex, toIndex);
    }
}