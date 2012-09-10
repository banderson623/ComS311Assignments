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

    public static void main(String [ ] args){

        Builders builder = new Builders();
        Timings timers = new Timings();

        System.out.println(timers);

//        timers.start("The whole thing");
        ArrayLookup lookerUpper = new ArrayLookup();

        timers.start("Creating Array");
            IArrayLookup.Pair pairs[] = builder.arrayOfPairsSizeWithGuarenteedKey(1000,"Ralph");
        timers.stop();

        timers.start("Linear look up of 1,000");
            lookerUpper.linearLookup(pairs,"Ralph");
        timers.stop();

        IArrayLookup.Pair pairs2[] = builder.arrayOfPairsSizeWithGuarenteedKey(10000,"Brian");
        timers.start("Linear look up of 10,000");
        lookerUpper.linearLookup(pairs2,"Ralph");
        timers.stop();


//        timers.stop();
        System.out.println(timers);
    }

}
