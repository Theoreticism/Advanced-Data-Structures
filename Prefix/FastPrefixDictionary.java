package prefix;

import java.util.*;     //List, ArrayList, Map, HashMap, Iterator
import java.io.*;       //FileReader, BufferedReader

/**
 * Class using a sorted tree map designed to be significantly faster than the
 * NaivePrefixDictionary class. Implements the PrefixDictionary interface.
 * @author Christopher Lee
 */

public class FastPrefixDictionary implements PrefixDictionary {

    private final NavigableMap<String, Integer> prefixes = new TreeMap<>();
    
    /**
     * Constructor to read in a file and insert the contents into a HashMap.
     * @param filename CSV file to be read in
     */
    public FastPrefixDictionary(String filename) {
        
        try (BufferedReader file = new BufferedReader(new FileReader (filename))) {
            
            String line;
            String[] list;
            Integer i;
            String s;
            
            while ((line = file.readLine()) != null) {
                
                list = line.split(",");
                s = list[0].trim();
                i = Integer.parseInt(list[1].trim());
                prefixes.put(s, i);
                
            }
            
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
    
    @Override
    public long sum(String prefix) {

        SortedMap<String, Integer> results = new TreeMap<>();
        prefix = prefix.trim();
        long sum = 0;
        
        results.putAll(prefixes.subMap(prefix, prefix + '\uFFFF'));
        
        for (Map.Entry<String, Integer> entry : results.entrySet())
            sum += entry.getValue();
        
        return sum;
        
    }
    
}


//for (int i = 0; i < prefix.length(); i++)
//            results.putAll(prefixes.subMap(prefix, prefix + '\uFFFF'));
//        
//        for (Entry<String, Integer> entry : results.entrySet())
//            sum += entry.getValue();