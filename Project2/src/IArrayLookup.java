/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/7/12
 * Time: 9:24 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IArrayLookup<T extends Comparable<? super T>, E>  {

    //arr need not be sorted.  Method should take O(n) time.
    /**
     * Returns the value associated with the key 'key'.
     * @param arr
     * An array containing precisely one element with key value 'key'.
     * This array need not be sorted.
     * @return
     */
    public E linearLookup(Pair<T, E>[] arr, T key);

    //Method should take O(n^2) time.
    public Pair<T, E>[] selectionSort(Pair<T, E>[] arr);

    /**
     * Returns the value associated with the key 'key'.
     * @param arr
     * An array containing precisely one element with key value 'key'.
     * This array is sorted according to the ordering defined by T's implementation
     * of Comparable.
     * @param key
     * @return
     */
    //arr must be sorted.  Method should take O(log(n)) time.
    public E logLookup(Pair<T, E>[] arr, T key);

    public class Pair<K extends Comparable<? super K>, V> implements Comparable<Pair<K, V>>
    {
        public K key;
        public V value;

        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Pair<K, V> anotherPair) {
            return key.compareTo(anotherPair.key);
        }


        public String toString(){
            return key + " => " + value;
        }
    }
}
