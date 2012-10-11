/**
 * User: brian_anderson
 * Date: 10/10/12
 */
public class BasicTest {

    public static void main(String [ ] args){
        System.out.println("Hello world, this is Assignment 3");
        Log.on();
        trieTest();
        trieDoubleAddTest();
        trieTestShouldError();
        trieShouldBeAbleToFindThings();
        trieShouldOnlyFindThingsThatItHas();

    }

    public static void trieTest()
    {
        Log.separator();
        LZtrie lzt = new LZtrie();
        LZtrie.Trie t = lzt.trieFactory();
        t.addString("00");
    }

    public static void trieDoubleAddTest()
    {
        Log.separator();
        LZtrie lzt = new LZtrie();
        LZtrie.Trie t = lzt.trieFactory();
        boolean firstAdd = t.addString("001100");
        System.out.println("First add should be TRUE: " + firstAdd);

        boolean secondAdd = t.addString("00110");
        System.out.println("Second add should be FALSE: " + secondAdd);
    }

    public static void trieTestShouldError()
    {
        Log.separator();
        LZtrie lzt = new LZtrie();
        LZtrie.Trie t = lzt.trieFactory();
        try {
            t.addString("a");
        } catch (InternalError e){
            System.out.println("Got good error!");
        }
    }

    public static void trieShouldBeAbleToFindThings()
    {
        Log.separator();
        LZtrie lzt = new LZtrie();
        LZtrie.Trie t = lzt.trieFactory();
        String toTryOnTrieHaHa = "01001";
        t.addString(toTryOnTrieHaHa);
        if(!t.doesContainString(toTryOnTrieHaHa)) {
            System.out.println("FAIL - Should contain " + toTryOnTrieHaHa);
        } else {
            System.out.println("Trie can find");
        }
        if(!t.doesContainString(toTryOnTrieHaHa.substring(0,2))) {
            System.out.println("FAIL - Should contain " + toTryOnTrieHaHa.substring(0,2));
        } else {
            System.out.println("Trie can find");
        }
    }

    public static void trieShouldOnlyFindThingsThatItHas()
    {
        Log.separator();
        LZtrie lzt = new LZtrie();
        LZtrie.Trie t = lzt.trieFactory();
        String toTryOnTrieHaHa = "001";
        t.addString(toTryOnTrieHaHa);
        if(t.doesContainString(toTryOnTrieHaHa + "01")) {
            System.out.println("Should not contain " + toTryOnTrieHaHa + "01");
        } else {
            System.out.println("Trie can find");
        }

    }

}
