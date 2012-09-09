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
                return pairs[i].value;
            }
        }
        return null;
    }

    @Override
    public Pair[] selectionSort(Pair[] pairs) {
        return new Pair[0];  //To change body of implemented methods use File | Settings | File Templates.
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
    public Object logLookup(Pair[] arr, Comparable key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
