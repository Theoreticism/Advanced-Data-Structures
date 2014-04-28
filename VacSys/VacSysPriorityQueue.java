package vacsys;

/**
 * @author Chris
 */
public interface VacSysPriorityQueue {
    
    /**
     * Inserts a new patient in the next slot of the priority queue: the next
     * available slot of the complete binary tree.
     * @param p Patient to insert
     * @throws java.lang.Exception
     */
    public void add(Patient p) throws Exception;
    
    /**
     * Peek simply returns the first item in the priority queue without adding, 
     * removing or changing the queue in any way.
     * @return First item in queue
     * @throws java.lang.Exception
     */
    public Patient peek() throws Exception;
    
    /**
     * Poll removes and returns the first item in the priority queue.
     * @return First item in queue
     * @throws java.lang.Exception
     */
    public Patient poll() throws Exception;
    
    /**
     * Removes all items in the queue.
     */
    public void clear();
    
    /**
     * Checks if the queue is empty or not.
     * @return true if empty, false if not empty
     */
    public boolean isEmpty();
    
    /**
     * Returns the number of patients in the queue.
     * @return size of queue
     */
    public int size();
    
}
