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
        System.out.println("Main Test starting");

        Builders builder = new Builders();

        Timer buildTiming = new Timer();
        ArrayLookup lookerUpper = new ArrayLookup();
        IArrayLookup.Pair pairs[] = builder.arrayOfPairsSizeWithGuarenteedKey(1000,"Ralph");

        buildTiming.start();
        lookerUpper.linearLookup(pairs,"Ralph");
        buildTiming.stop();

        System.out.println("Building took\n" + buildTiming.report());
    }

}
