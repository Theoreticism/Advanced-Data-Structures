package vacsys;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Chris
 */
public class VacSysHeap implements VacSysPriorityQueue {

    /* Max elements in heap */
    private final int max;
    
    /* Current elements (patients) in heap */
    private int size;
    
    /* LinkedList of Queue<Patient> used to represent the heap-of-queues */
    private final LinkedList<Queue<Patient>> heapOfQueues;
    
    /**
     * Creates and initializes the heap of queues with a size of 0 and a max
     * number of elements defined by user. Queue is used to get access to the 
     * peek and poll functions.
     * @param m Maximum size of heap
     */
    public VacSysHeap(int m) {
        
        this.max = m;
        this.size = 0;
        heapOfQueues = new LinkedList<>();
        
    }
    
    /**
     * Adds a patient into the heap if a queue for the patient already exists or
     * if the queue does not exist, creates one at the bottom of the list
     * @param p The patient being added to the system
     * @throws Exception if heap is full
     */
    @Override
    public void add(Patient p) throws Exception {
        
        if (size < max) {
            
            for (Queue<Patient> ps : heapOfQueues) {
                if (ps.peek().equals(p)) {
                    
                    ps.add(p);
                    size++;
                    return;
                    
                }
            }
            
            //Queue is abstract, so cannot be instantiated
            //Switched from ArrayList to LinkedList since queues are reference
            //based structures like linked lists (so ArrayList does not work)
            Queue<Patient> q = new LinkedList<>();
            q.add(p);
            heapOfQueues.add(q);
            size++;
            heapRebuildUp(heapOfQueues.size() - 1);
            
        } else 
            throw new Exception("Heap is full");
        
    }

    /**
     * Peek uses the Java peek function to return the first queue in the heap.
     * @return First queue in the heap (at the head)
     * @throws Exception if heap is empty
     */
    @Override
    public Patient peek() throws Exception {
        
        if (heapOfQueues.isEmpty() == false) 
            return heapOfQueues.get(0).peek();
        else
            throw new Exception("Heap is empty");
        
    }

    /**
     * Poll takes the first queue in the heap (the head) and polls it, getting
     * the highest priority patient, then removes the queue if it is empty, 
     * otherwise it reduces its size by 1. Swaps the last element with the first
     * then calls heapRebuild.
     * @return The highest priority patient in the heap
     * @throws Exception if heap is empty
     */
    @Override
    public Patient poll() throws Exception {
        
        if (heapOfQueues.isEmpty() == false) {
            
            Queue<Patient> head = heapOfQueues.get(0);
            Patient first = head.poll();
            
            if (head.isEmpty() == true) {
                
                //Swaps the first and last elements, removes the last element,
                //then calls heapRebuild to rebuild the heap
                int tSize = heapOfQueues.size();
                swap(0, tSize - 1);
                heapOfQueues.remove(tSize - 1);
                if (tSize - 1 > 0)
                    heapRebuildDown(0, tSize - 1);
                size--;
                return first;
                
            } else {
                
                size--;
                return first;
                
            }
            
        } else
            throw new Exception("Heap is empty");
        
    }
    
    /**
     * Rebuilds the heap from the bottom up (trickle up function). Checks to see
     * if every parent is greater than the child, if not, switches the two
     * until it reaches the root.
     */
    private void heapRebuildUp(int pos) throws Exception {
       
        if (pos >= 0) {
            
            Patient parent = heapOfQueues.get((pos - 1) / 2).peek();
            Patient child = heapOfQueues.get(pos).peek();
            
            if (parent.isLessThan(child)) {
                
                //Parent retrieval algorithm
                swap(((pos - 1) / 2), pos);
                //Recursive call
                heapRebuildUp((pos - 1) / 2);
                
            }
            
        } else 
            throw new Exception("Index cannot be less than zero.");
        
    }
    
    /**
     * Rebuilds the heap from the top down (trickle down function). Checks to 
     * see if the left or the right child holds a higher priority and if one
     * does, it swaps them.
     * @param pos 
     * @param heapSize 
     */
    private void heapRebuildDown(int pos, int heapSize) {
        
        int leftChild = 2 * pos + 1;
        int rightChild = 2 * pos + 2;
        Patient origin = heapOfQueues.get(pos).peek();
        Patient destination;
        int toSwap = 0;
        
        //Both left and right child exists
        if (leftChild < heapSize && rightChild < heapSize) {
            
            Patient right = heapOfQueues.get(rightChild).peek();
            Patient left = heapOfQueues.get(leftChild).peek();
            
            if (right.isLessThan(left))
                toSwap = leftChild;
            else if (right.isGreaterThan(left))
                toSwap = rightChild;
            
            destination = heapOfQueues.get(toSwap).peek();
            if (origin.isLessThan(destination)) {
                
                swap(toSwap, pos);
                //Recursive call
                heapRebuildDown(toSwap, heapSize);
                
            }
        
        //Only right child exists
        } else if (rightChild < heapSize) {
            
            toSwap = rightChild;
            destination = heapOfQueues.get(toSwap).peek();
            if (origin.isLessThan(destination)) {
                
                swap(toSwap, pos);
                //Recursive call
                heapRebuildDown(toSwap, heapSize);
                
            }
            
        //Only left child exists
        } else if (leftChild < heapSize) {
            
            toSwap = leftChild;
            destination = heapOfQueues.get(toSwap).peek();
            if (origin.isLessThan(destination)) {
                
                swap(toSwap, pos);
                //Recursive call
                heapRebuildDown(toSwap, heapSize);
                
            }
            
        }
    
    }

    /**
     * Clears the heap by removing all elements in the LinkedList (heap). Also
     * reverts size to 0.
     */
    @Override
    public void clear() {
        size = 0;
        heapOfQueues.clear();
    }

    /**
     * Checks if the heap is empty or not.
     * @return true if heap is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Retrieves the size of the heap of queues.
     * @return the size of the heap
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Calculates the population of patients in the system in the zip code of
     * the request.
     * @param zip Zip code of the request
     * @return Population of patients in the zip code
     */
    public int zpop(int zip) {
        
        for (Queue<Patient> ps : heapOfQueues) {
            if (ps.peek().getZip() == zip) {
                return ps.size();
            }
        }
        return 0;
        
    }

    /**
     * Swap function to switch the places of one Patient in the heap with another
     * @param a The first Patient being switched
     * @param b The second Patient being switched
     */
    private void swap(int a, int b) {
        
        Queue<Patient> temp = heapOfQueues.get(a);
        heapOfQueues.set(a, heapOfQueues.get(b));
        heapOfQueues.set(b, temp);
        
    }
    
}