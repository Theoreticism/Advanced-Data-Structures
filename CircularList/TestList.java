package circularlist;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class to test the circularlist package
 */
public class TestList {

    public static void main(String [] args)
    {
	// Create a CircularList of Strings
	//CircularList<String> cl = new CircularListArrayBased<>();
        CircularList<String> clrb = new CircularListReferenceBased<>();
	
	// Add elements
	//cl.add("A"); cl.add("B"); cl.add("C"); cl.add("D"); cl.add("E");
        clrb.add("Hello"); clrb.add("World"); clrb.add("dlroW"); clrb.add("olleH");
        
        // Add elements via overloaded function
        //cl.add(2,"BC");
        //clrb.add(1, "KayaK");
        
        // Remove elements
        //cl.remove(3);
        //clrb.remove(2);
        
        // Check if array is empty
        //System.out.println(cl.isEmpty());
        //System.out.println(clrb.isEmpty());
        
	// Use the foreach loop to print out elements from the iterator
//	System.out.print("{ ");
//	int i = 1;
//	for(String s : clrb) {
//	    System.out.print(s + " ");
//	    if(i++ > 25)
//		break;
//	}
//	System.out.println("}");
        
        // Use the iterator while loop to print out infinite elements from the iterator
//        Iterator it = cl.iterator();
//        
//        while(it.hasNext()) {
//            if (it.hasNext()) {
//                System.out.print(it.next() + ", ");
//            }
//            else
//                System.out.print(it.next());
//        }
        
        // Use the foreach loop to print out elements from the referenced based array
        Iterator it2 = clrb.iterator();
        
        while (it2.hasNext()) {
            if (it2.hasNext()) {
                System.out.print(it2.next() + ", ");
            }
            else
                System.out.print(it2.next());
        }
        
        
        // Test ReferenceBased get methods
//        System.out.println(clrb.get(0));
//        System.out.println(clrb.get(1));
//        System.out.println(clrb.get(2));
//        System.out.println(clrb.get(3));
//        System.out.println(clrb.get(4));
        
    }
	
}