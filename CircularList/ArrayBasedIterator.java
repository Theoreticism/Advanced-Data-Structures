/*
 * @author Christopher Lee
 */
package circularlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBasedIterator<E> implements Iterator<E> {

    private E[] array;
    private int pos = 0;
    
    //ArrayBasedIterator constructor takes an array of generic objects as a parameter
    
    public ArrayBasedIterator(E[] a) {
        
        array = a;
        
    }
    
    /*
     * If circular list length is not zero this will always return true so it 
     * can continue looping forever
     * @return true if array length is greater than zero
     */
    
    @Override
    public boolean hasNext() {
        
        //return pos < array.length && array[pos] != null 
        return array.length > 0;
        
    }
    
    /*
     * The next function is designed to infinitely loop the inputted array by
     * assigning the array[length] index to be the first index, array[0]. This
     * is done with modulo, since when the array reaches its length modulo the
     * array length, this will return zero, which will start it at the beginning
     * @return value of current array position modulo the array length
     */
    
    @Override
    public E next() throws NoSuchElementException {
        
        if (hasNext()){
            
            int temp = pos++;
            pos = pos % array.length;
            return array[temp];
            
        }
        else
            throw new NoSuchElementException();
        
    }

    @Override
    public void remove() {
        
        throw new UnsupportedOperationException("Not supported yet."); 
        
    }
    
}
