/**
 * @author Christopher Lee
 */

package circularlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CircularListArrayBased has to implement the generic interface CircularList
 * with the same E, so it must implement not the raw interface CircularList,
 * but the interface CircularList<E>.
 * @author Chris
 * @param <E> generic type
 */

public class CircularListArrayBased<E> implements CircularList<E> {

  private E[] cArray;
  
  /**
   * Default constructor creates an empty array of size 10
   */
  public CircularListArrayBased() {
      
      cArray = (E[])new Object[0];
      
  }
  
  /**
   * Second constructor allows user to specify array to be used
   * @param iArray the array input
   */
  public CircularListArrayBased(E[] iArray) {
      
      cArray = iArray;
      
  }
  
 /**
  * Uses the array object's length property to determine if the array is empty
  */
  @Override
  public boolean isEmpty() {
      
    return cArray.length == 0;
    
  }

 /**
  * Determines the length of a list using the array object's length property
  */
  @Override
 public int size() {
      
     return cArray.length;
     
 }
 
 /**
  * Removes all elements from the list by reassigning the existing array to 
  * an array of length 0
  */
  @Override
 public void clear() {
      
      cArray = (E[])new Object[0];
      
  }

 /**
  * Uses a temporary array tArray to replace the circular array cArray with
  * a new array of length + 1, with the newest addition being the added item.
  * @param item the new item to add
  * @return true if the list was modified
  */
  @Override
 public boolean add(E item) { 
     
     if (item != null) {
         
        E[] tArray = (E[])new Object[cArray.length + 1];
        
        System.arraycopy(cArray, 0, tArray, 0, cArray.length);
     
        tArray[cArray.length] = item;
        cArray = tArray;
        return true;
        
     }
     return false;
 }

 /**
  * Creates a new array of array length + 1 and fills the array until the given
  * index with the items in the old array. It then stops, inserts the new item,
  * and loops a second time to fill in the rest of the array
  * @param index where to add the new item
  * @param item the new item to add
  * @throws IndexOutOfBoundsException if index is negative
  */
  @Override
 public void add(int index, E item) throws IndexOutOfBoundsException { 
     
     int length = cArray.length;
     
     if (index >= 0) {
        
        E[] tArray = (E[])new Object[length + 1];
        
        System.arraycopy(cArray, 0, tArray, 0, index);
        tArray[index] = item;
        
        for (int i = index + 1; i < tArray.length; i++)
            tArray[i] = cArray[i - 1];
        
        cArray = tArray;
     
     } else if (index < 0)
         throw new IndexOutOfBoundsException("The index cannot be a negative value.");
 }

 /**
  * Creates a new array of array length - 1 and fills the array with the items 
  * in the old array. At the index, the loop will skip the item at the index
  * and continue to the end
  * @param index the position of the item to remove
  * @return the item that was removed
  * @throws IndexOutOfBoundsException if index is negative
  */
  @Override
 public E remove(int index) throws IndexOutOfBoundsException {
      
      E indexValue;
      int length = cArray.length;
      
      if (index >= 0) {
          
          E[] tArray = (E[])new Object[length - 1];
          indexValue = cArray[index];
          int j = 0;
          
          for (int i = 0; i < length; i++) {
              
              if (i == index)
                  continue;
              tArray[j] = cArray[i];
              j++;
              
          }
          
          cArray = tArray;
          return indexValue;
          
      } else if (index < 0)
          throw new IndexOutOfBoundsException("The index cannot be a negative value.");
      
      return null;
      
  }

 /**
  * This getter method simply returns the object at the index value specified
  * of the circular array cArray.
  * @param index the position of the item to retrieve
  * @return the item at position index
  * @throws IndexOutOfBoundsException if index is negative
  */
  @Override
 public E get(int index) throws IndexOutOfBoundsException {
      
      if (index >= 0) {
          
          return cArray[index];
          
      } else if (index < 0)
          throw new IndexOutOfBoundsException("The index cannot be a negative value.");
      
      return null;
      
  }
 
  
  
 /**
  * Generate an iterator for the list.  The iterator should 
  * visit the items in a circular pattern.  As long as the
  * list is not empty, it should never stop.
  */
  @Override
 public Iterator<E> iterator() { 
      
      return new ArrayBasedIterator(cArray);
      
 }
}