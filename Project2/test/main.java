import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/7/12
 * Time: 10:05 AM
 *
 * Just used to test the array sorting/finding code
 *
 */
public class main {

    public static void printPairs(IArrayLookup.Pair pairs[]){
        for(int i = 0; i < pairs.length; ++i){
            System.out.print("["+i+"]"+pairs[i] + ", ");
            if(i%10 == 0){
                System.out.print("\n");
            }
        }
        System.out.println("");
    }

    public static void main(String [ ] args){

        //Timings timers = new Timings();

        int[] tests = new int[7];

        int testRunsForAveraging = 3;

        tests[0] = 10;
        tests[1] = 100;
        tests[2] = 1000;
        tests[3] = 5000;
        tests[4] = 10000;
        tests[5] = 50000;
        tests[6] = 100000;
//        tests[7] = 1000000;
//        tests[6] = 5000000;

        String[] keys = new String[tests.length];

        HashMap<String, String[]> results = new HashMap<String,String[]>();

        for(int i = 0; i < tests.length; ++i){
            Builders builder = new Builders();

            keys[i] = tests[i]+"";

            System.out.println(i+". Working with :" + keys[i] + " objects");

            ArrayLookup lookerUpper = new ArrayLookup();
            IArrayLookup.Pair pairs[] = builder.arrayOfPairsSizeWithGuarenteedKey(tests[i],"Ralph");

            // Find things in O(n) time
            System.out.println("linearLookup");
            Timer linearTimer = new Timer();
                  linearTimer.start();
            lookerUpper.linearLookup(pairs,"Ralph");
                  linearTimer.stop();
            results.put("linear_" + keys[i],linearTimer.valuesAsStringArray());

            // Sort things ------------------------------------
            Timer sortTimer = new Timer();
            sortTimer.start();
            //pairs = sorter.sort(pairs);
            pairs = lookerUpper.selectionSort(pairs);
            sortTimer.stop();
            results.put("sort_" + keys[i],sortTimer.valuesAsStringArray());


            // Find things with logn time ----------------------------------
            System.out.println("logLookup");
            Timer logNTimer = new Timer();
                  logNTimer.start();
            lookerUpper.logLookup(pairs, "Ralph");
                  logNTimer.stop();

            results.put("logn_" + keys[i],logNTimer.valuesAsStringArray());

        }

//        System.out.println("Number of Objects, linear_userTime,systemTime, cpuTime, logn_userTime,systemTime,cpuTime");

        System.out.println("Number of Objects, linear_cpuTime, logn_cpuTime, sort_cpuTime");

        for(String k : keys){

            System.out.print(k + ",");

            String[] linearResults = results.get("linear_"+k);
            System.out.print(linearResults[2]+",");

            String[] logResults = results.get("logn_"+k);
            System.out.print(logResults[2]+",");

            String[] sortResults = results.get("sort_"+k);
            System.out.print(sortResults[2] + "\n");

        }

        return;

    }

}
