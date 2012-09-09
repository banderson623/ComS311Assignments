import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/7/12
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */

public class Builders {

    public String randomString(int length){
        String text = "";
        for(int i = 0; i < length; ++i){
            text = text + Long.toString((long) Math.ceil(Math.random()*100), 32);
        }
//        System.out.println("Created random key: " + text);
        return text;
    }

    public List<IArrayLookup.Pair<String, Number>> listOfPairsSizeWithGuarenteedKey(int size, String requiredKey){
        List<IArrayLookup.Pair<String,Number>> pairs = new ArrayList<IArrayLookup.Pair<String, Number>>();

        int indexForGuaranteedKey = (int) Math.round(Math.random() * size + 1) % size;

        for(int i = 0; i < size; i++){
            String key = this.randomString(5);

            if(indexForGuaranteedKey == i){
                key = requiredKey;
            }
            pairs.add(new IArrayLookup.Pair<String, Number>(key,Math.round(Math.random()*100)));
        }

        return pairs;
    }

    public IArrayLookup.Pair[] test() {

        IArrayLookup.Pair[] a = new IArrayLookup.Pair[5];
//        for(int i = 0; i < a.length; i++){
            a[1] = new IArrayLookup.Pair("Brian",123);
//        }
        return a;
    }

    public IArrayLookup.Pair[] arrayOfPairsSizeWithGuarenteedKey(int size, String key){
        List<IArrayLookup.Pair<String,Number>> pairs = listOfPairsSizeWithGuarenteedKey(size, key);

        IArrayLookup.Pair[] array = new IArrayLookup.Pair[size];

        for(int i=0; i< array.length; ++i) {
            array[i] = new IArrayLookup.Pair(pairs.get(i).key,pairs.get(i).value);
        }

        return array;
    }
}

