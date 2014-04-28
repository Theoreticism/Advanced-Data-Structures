/*
 * @author Christopher Lee
 */
package circularlist;

import java.util.Iterator;

public class CircularListReferenceBased<E> implements CircularList<E> {

    private Link first;
    private int size;
    
    /*
     * Default constructor initializes the linked list with a size of zero
     */
    
    public CircularListReferenceBased() {
        
        first = new Link(null, null);
        size = 0;
        
    }
    
    /**
     * Checks the status of the first object in the linked list to determine if
     * the list is empty
     * @return true if the first value in the linked list is null
     */
    
    @Override
    public boolean isEmpty() {
        
        return first == null;
        
    }

    /**
     * The size variable is incremented as items are added into the linked list
     * @return size the size of the linked list
     */
    
    @Override
    public int size() {
        
        return size;
        
//        int size = 0;
//        for (Link l = first; l.next != null; l = l.next)
//           size++;
//        return size;
         
    }

    /**
     * Clears the linked list by setting the first link equal to null, eliminating
     * each next link. Also resets the size of the linked list
     */
    
    @Override
    public void clear() {
        
        first = null;
        size = 0;
        
    }

    /**
     * Inserts an item at the end of the linked list after iterating through the
     * list and reaching the end
     * @param item the new item to add
     * @return true if the list was modified
     */
    
    @Override
    public boolean add(E item) {
        
        if (item != null) {
            
            Link current = first;
            
            while (current.next != null)
                current = current.next;
            
            current.next = new Link(item, null);
            
            size++;
            return true;
            
        }
        
        return false;
        
    }

    /**
     * Iterates to the index or the end of the list, then ties the link to be 
     * added to the index link's next link. Then it links the index link's next
     * link to the new link
     * @param index where to add the new item
     * @param item the new item to add
     * @throws IndexOutOfBoundsException if index is negative
     */
    
    @Override
    public void add(int index, E item) throws IndexOutOfBoundsException {               //CHECK THIS
        
        Link addedLink;
        Link current = first;
        
        if (index >= 0) {
        
            for (int i = 0; i < index && current.next != null; i++)
                current = current.next;
            
            addedLink = new Link(item, current.next);
            current.next = addedLink;
            size++;
            
        } else if (index < 0)
            throw new IndexOutOfBoundsException("The index cannot be a negative value.");
    }

    /*
     * Iterates from one to the index value, making each link equal to the next
     * link. Once it gets to the index value, it stops and sets the current 
     * link's next link to the current link's next link's next link (thus 
     * eliminating the link in between, effectively removing it)
     * @param index what item to remove
     * @throws IndexOutOfBoundsException if index is negative
     */
    
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        
        E retVal = null;
        
        if (index >= 0) {
            
            Link current = first;
            
            for (int i = 0; i < index && current.next != null; i++)
                current = current.next;
            
            retVal = (E) current.next.value;
            current.next = current.next.next;
            size--;
            return retVal;
            
        } else if (index < 0)
            throw new IndexOutOfBoundsException("The index cannot be a negative value.");
        
        return null;
        
    }
    
    /*
     * This get method iterates through the linked list until it reaches the 
     * given index, then it returns the item at said index
     * @return item at given index
     */
    
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        
        Link current = first;
        
        if (index >= 0) {
            
            for (int i = 0; i <= index; i++) 
                current = current.next;
            
            return (E) current.value;
            
        } else if (index < 0)
            throw new IndexOutOfBoundsException("The index cannot be a negative value.");
        
        return null;
        
    }

    @Override
    public Iterator<E> iterator() {
        
        return new ReferenceBasedIterator(first);
        
    }
    
}
