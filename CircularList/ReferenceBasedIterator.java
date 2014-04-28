/*
 * @author Christopher Lee
 */
package circularlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReferenceBasedIterator<E> implements Iterator<E> {

    private Link first;
    private int count;
    
    /**
     * ReferenceBasedIterator constructor takes the first link in the linked list
     * and the size of the linked list as parameters
     */
    
    public ReferenceBasedIterator(Link f) {
        
        first = f;
        
    }
    
    /*
     * This hasNext function simply checks if the next item in the list exists.
     * Since the iterator will visit the linked list infinitely, this should 
     * always return true
     * @return true if the first link's next link is not null, false if it is
     */
    
    @Override
    public boolean hasNext() {
        
        return first.next != null;
        
    }

    /*
     * The while loop is used to gather the length of the list. The modulo statement
     * in between is used to force the iterator to visit the list infinitely. The
     * for loop at the end is used to iterate through the list again and return 
     * the next value
     * @return the next value in the linked list
     */
    
    @Override
    public E next() throws NoSuchElementException {
        
        if (hasNext()) {
            
            Link listLength = first;
            Link current = first;
            int length = 0;
            int pos = count++;
            
            while (listLength.next != null) {
                listLength = listLength.next;
                length++;
            }
            
            count = count % length;
            for (int i = 0; i <= pos; i++)
                current = current.next;
            
            return (E) current.value;
                
            
        } else
            throw new NoSuchElementException();
        
    }

    @Override
    public void remove() {
        
        throw new UnsupportedOperationException("Not supported yet.");
        
    }
    
}
