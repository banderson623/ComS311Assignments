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

        Builders builder = new Builders();
        Timings timers = new Timings();

        ArrayLookup lookerUpper = new ArrayLookup();
        IArrayLookup.Pair pairs[] = builder.arrayOfPairsSizeWithGuarenteedKey(200,"Ralph");

        timers.start("Linear look up of " + pairs.length);
        lookerUpper.linearLookup(pairs,"Ralph");
        timers.stop();


//        printPairs(pairs);
        timers.start("Sorting " + pairs.length);
        IArrayLookup.Pair sortedPairs[] = lookerUpper.selectionSort(pairs);
        timers.stop();

//        printPairs(sortedPairs);

        timers.start("logLookup of " + pairs.length);
        Object value = lookerUpper.logLookup(sortedPairs, "Ralph");
        timers.stop();


//        timers.stop();
        System.out.println(timers);
    }

}
