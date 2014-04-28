package vacsys;

/**
 * @author Chris
 */
public class VacSysTest {
    
    public static void main (String [] args) throws Exception {
        
        VacSys sys1 = new VacSys("src/vacsys/test1K.csv");
//        VacSys sys2 = new VacSys("src/vacsys/test10K.csv");
//        VacSys sys3 = new VacSys("src/vacsys/test100K.csv");
//        VacSys sys4 = new VacSys("src/vacsys/test1M.csv");
//        VacSys sys5 = new VacSys("src/vacsys/input.csv");
        
        sys1.remove(sys1.size(), "src/vacsys/results.csv");
//        sys5.remove(sys5.size(), "src/vacsys/output2.csv");
        
    }
    
}
