package vacsys;

/**
 * The Patient in a priority-based vaccination system stores the name, age, 
 * zip code and knows the heap it's allocated in. Priority given to those at 
 * the most risk--the young and the old--and areas of high request concentrations.
 * @author Chris
 */
public class Patient implements Comparable {
    
    /* Name of the patient */
    private final String name;
    
    /* Age of the patient */
    private final int age;
    
    /* Zip code of the patient's home */
    private final int zip;
    
    /* Heap the patient is allocated in */
    private VacSysHeap heap;
    
    /** 
     * VacSys Patient class constructor, defining name, age, zip code
     * @param n Name of patient
     * @param a Age of patient
     * @param z Zip code of patient's home
     */
    public Patient(String n, int a, int z) {
        
        this.name = n;
        this.age = a;
        this.zip = z;
        
    }
    
    /**
     * VacSys Patient alternate class constructor, additionally defining 
     * heap patient is contained in
     * @param n Name of patient
     * @param a Age of patient
     * @param z Zip code of patient's home
     * @param h Heap the patient is allocated in
     */
    public Patient(String n, int a, int z, VacSysHeap h) {
        
        this.name = n;
        this.age = a;
        this.zip = z;
        this.heap = h;
        
    }

    /**
     * This compareTo override of the Comparable class uses the given formula
     * for priority calculation to calculate the difference in priorities between
     * the original patient and the patient being compared. This function will
     * return a number less than zero, equal to zero, or greater than zero.
     * @param o Other patient being compared
     * @return Positive number, zero, or negative number
     */
    @Override
    public int compareTo(Object o) {
        
        Patient target = (Patient) o;
        
        int originalPriority = (Math.abs(35 - age) / 5) + 
                ((zpop() / tpop()) * 10);
        int targetPriority = (Math.abs(35 - target.getAge()) / 5) +
                ((target.zpop() / target.tpop()) * 10);
        return originalPriority - targetPriority;
        
    }

    /**
     * Checks if the target patient's priority is equal to the priority of the
     * patient being compared.
     * @param o Other patient being compared
     * @return true if zero, false if not zero
     */
    @Override
    public boolean equals(Object o) {
        
        if (o == null) 
            return false;
        else if (!(o instanceof Patient))
            return false;
        return this.compareTo(o) == 0;
        
    }
    
    /**
     * Checks if the target patient's priority is greater than the priority of
     * the patient being compared.
     * @param o Other patient being compared
     * @return true if less than zero, false if zero or greater
     */
    public boolean isLessThan(Object o) {
        return this.compareTo(o) < 0;
    }
    
    /**
     * Checks if the target patient's priority is less than the priority of the
     * patient being compared.
     * @param o Other patient being compared
     * @return true if greater than zero, false if zero or lesser
     */
    public boolean isGreaterThan(Object o) {
        return this.compareTo(o) > 0;
    }
    
    /**
     * Name getter method
     * @return Name of patient
     */
    public String getName() {
        return name;
    }
    
    /**
     * Age getter method
     * @return Age of patient
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Zip code getter method
     * @return Zip code of patient's home
     */
    public int getZip() {
        return zip;
    }

    /**
     * The zpop function represents the specific population of patients in the
     * zip code of the request.
     * @return number of patients in the system at this zip code
     */
    int zpop() {
        return heap.zpop(zip);
    }
    
    /**
     * The tpop function represents the total population of patients in the 
     * system.
     * @return total number of patients in the heap
     */
    int tpop() {
        return heap.size();
    }
    
}
