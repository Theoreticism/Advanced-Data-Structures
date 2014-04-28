package vacsys;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Chris
 */
public class VacSys {

    /* Vaccination system heap-of-queues */
    private final VacSysHeap heap;
    
    /**
     * Default constructor, creates a system with an empty priority queue
     */
    public VacSys() {
        heap = new VacSysHeap(50);
    }
    
    /**
     * Creates a system with requests from the batch file using FileReader and 
     * the Java String functions trim and split to remove all whitespace and 
     * split each entry at the comma in the CSV file. It then creates a String
     * array holding each separate value and creates a new Patient using these
     * values in the array. Finally, it adds the patients in the heap.
     * @param filename the given batch file with requests
     * @throws java.lang.Exception
     */
    public VacSys(String filename) throws Exception {
        
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            
            String temp;
            String[] splitList;
            
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                
                while ((temp = reader.readLine()) != null) {
                    
                    splitList = temp.trim().split(",");
                    Patient p = new Patient(
                            splitList[0].trim(),
                            Integer.valueOf(splitList[1].trim()),
                            Integer.valueOf(splitList[2].trim()));
                    patients.add(p);
                    
                }
            }
            
        } catch (IOException e) {
            
            System.err.println("Error: " + e.getMessage());
            
        }
        
        heap = new VacSysHeap(patients.size());
        for (Patient p : patients) 
            insert(p.getName(), p.getAge(), p.getZip());
        
    }
    
    /**
     * Add a new request to the system
     * @param name name of the requester
     * @param age age of the requester
     * @param zip zip code of the requester's home
     * @return true if new request successfully added, false if not
     * @throws java.lang.Exception
     */
    public final boolean insert(String name, int age, int zip) throws Exception {
        
        Patient patient = new Patient(name, age, zip, heap);
        heap.add(patient);
        return true;
        
    }
    
    /**
     * Removes the next request from the system
     * @return the request value
     * @throws java.lang.Exception
     */
    public String remove() throws Exception {
        
        Patient p = heap.poll();
        return p.getName() + ", " + p.getAge() + ", " + p.getZip();
        
    }
    
    /**
     * Remove X requests and stores them in CSV format in the given batch file
     * using FileWriter
     * @param num this determines the amount of requests being removed (X)
     * @param filename the given batch file
     * @return 
     * @throws java.lang.Exception 
     */
    public boolean remove(int num, String filename) throws Exception {
        
        try {
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                for (int i = 0; i < num; i++)
                    writer.write(remove() + "\n");
            }
            
        } catch (IOException e) {
            
            System.err.println("Error writing file: " + e.getMessage() + "\n");
            return false;
            
        }
        return true;
        
    }
    
    /**
     * Gives you the heap size.
     * @return size of heap
     */
    public int size() {
        return heap.size();
    }
    
}