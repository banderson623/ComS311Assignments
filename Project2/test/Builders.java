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

public class Builders<T extends Comparable<? super T>> {
    public List<IArrayLookup.Pair<T, Number>> listOfIntegersSizeAndGuarenteeKey(int size, T key){
        List<IArrayLookup.Pair<T,Number>> pairs = new ArrayList<IArrayLookup.Pair<T, Number>>();

        for(int i = 0; i < size; i++){
            pairs.add(new IArrayLookup.Pair<T, Number>(key,Math.round(Math.random()*100)));
        }

        return pairs;
    }


//    public IArrayLookup.Pair<T, Number>[] arrayOfIntegersSizeAndGuarenteeKey(int size, T key){
//        List<IArrayLookup.Pair<T,Number>> pairs = listOfIntegersSizeAndGuarenteeKey(size, key);
//        IArrayLookup.Pair<T,Number>[] result = (IArrayLookup.Pair<T,Number>[])Array.newInstance(IArrayLookup.Pair<T,Number>,pairs.size());
//
//        return result;
//    }
}

