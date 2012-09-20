/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/7/12
 *
 * This is for Fall 2012 Com S 311, Assignment #2 ...
 *
 * Implement three simple algorithms and perform experiments to empirically
 * evaluate the running time of your implementations.
 */
public class ArrayLookup implements IArrayLookup {

    /**
     * Returns the value associated with the key 'key'.
     *
     * @param arr An array containing precisely one element with key value 'key'.
     *            This array need not be sorted.
     * @return
     */
    @Override
    public Object linearLookup(Pair[] pairs, Comparable key) {
        int size = pairs.length;
        for(int i = 0; i < size; ++i){
            if(pairs[i].key.equals(key)){
                System.out.println("\nloops: " + i);

                return pairs[i].value;
            }
        }
        return null;
    }


    public void displayPercentSorted(double percentFromZeroToOne){
        int length = 120;
        if(percentFromZeroToOne > 1.0){
            percentFromZeroToOne = percentFromZeroToOne / (double) length;
        }
        int at = (int) Math.ceil(percentFromZeroToOne * (double) length);

        System.out.print("[");
        for(int i = 0; i < at; ++i){
            System.out.print("*");
        }
        System.out.print("*");

        for(int i = at; i < length; ++i){
            System.out.print("-");
        }
        System.out.println("]");

    }

        @Override
    public Pair[] selectionSort(Pair[] pairs) {

        for(int outerIndex = 0; outerIndex < pairs.length; ++outerIndex){
            int smallestPair = outerIndex;

            //displayPercentSorted( (float)outerIndex / (float)pairs.length ) ;

            for(int innerIndex = outerIndex + 1;
                    innerIndex < pairs.length;
                  ++innerIndex){

                //Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
                if(pairs[innerIndex].compareTo(pairs[smallestPair]) < 0){
                    smallestPair = innerIndex;
                }
            }

            if(smallestPair != outerIndex){
                Pair tmpPair = pairs[outerIndex];
                pairs[outerIndex] = pairs[smallestPair];
                pairs[smallestPair] = tmpPair;
            }
        }

        return pairs;
    }

    /**
     * Returns the value associated with the key 'key'.
     *
     * @param arr An array containing precisely one element with key value 'key'.
     *            This array is sorted according to the ordering defined by T's implementation
     *            of Comparable.
     * @param key
     * @return
     */
    @Override
    public Object logLookup(Pair[] pairs, Comparable key) {

        boolean found = false;
        int midpoint = (int) Math.ceil(pairs.length / 2);
        int jumpFactor = midpoint;
        int loops = 0;

        //System.out.print("\n" + pairs.length + "=>");

        while(!found && loops <= pairs.length){
            //System.out.print("["+midpoint + "|" + jumpFactor + "]");
            ++loops;

            int comparisonResult = pairs[midpoint].key.compareTo(key);

            if(comparisonResult > 0){
                //System.out.print("-");

                jumpFactor = (int) Math.ceil(jumpFactor / 2.0);
                midpoint -= jumpFactor;

            } else if(comparisonResult < 0) {
                //System.out.print("+");

                jumpFactor = (int) Math.ceil(jumpFactor / 2.0);
                midpoint += jumpFactor;

            } else {
                found = true;
            }
        }

        System.out.println("\nloops: " + loops);

        return pairs[midpoint].value;
    }
}
