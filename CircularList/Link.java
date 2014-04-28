/**
 * Link object for linked list
 * @author Chris
 */
package circularlist;

public class Link<E> {
    
    public E value;
    public Link<E> next;
    
    //Default constructor
    public Link() {
        
        this.value = null;
        this.next = null;
        
    }
    
    //Link constructor
    public Link(E tVal, Link l) {
        
        this.value = tVal;
        this.next = l;
        
    }
    
    /*
     * Gets the next value of the called link
     */
    
    public Link getNext() {
        
        return next;
        
    }
    
    /*
     * Sets the next value of the link that setNext is called on to the param
     * in order to connect a disconnected link to the linked list
     */
    
    public void setNext(Link next) {
        
        this.next = next;
        
    }
    
}
