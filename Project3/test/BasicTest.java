/**
 * User: brian_anderson
 * Date: 10/10/12
 */
public class BasicTest {



    public static void main(String [ ] args){
        System.out.println("Hello world, this is Assignment 3");
//        Log.off();
//        trieTest();
//        trieDoubleAddTest();
//        trieTestShouldError();
//        trieShouldBeAbleToFindThings();
//        trieShouldOnlyFindThingsThatItHas();
//        testSimpleLZAdding();
        testEncodingLZTrie();
        testEncodingLZTrie2();

        testEncodingLZHash();
        testEncodingLZHash2();


    }
//
//    public static void trieTest()
//    {
//        Log.separator();
//        LZtrie lzt = new LZtrie();
//        LZtrie.Trie t = lzt.trieFactory();
//        t.addString("00");
//    }
//
//    public static void trieDoubleAddTest()
//    {
//        Log.separator();
//        LZtrie lzt = new LZtrie();
//        LZtrie.Trie t = lzt.trieFactory();
//        boolean firstAdd = t.addString("001100");
//        System.out.println("First add should be TRUE: " + firstAdd);
//
//        boolean secondAdd = t.addString("00110");
//        System.out.println("Second add should be FALSE: " + secondAdd);
//    }
//
//    public static void trieTestShouldError()
//    {
//        Log.separator();
//        LZtrie lzt = new LZtrie();
//        LZtrie.Trie t = lzt.trieFactory();
//        try {
//            t.addString("a");
//        } catch (InternalError e){
//            System.out.println("Got good error!");
//        }
//    }
//
//    public static void trieShouldBeAbleToFindThings()
//    {
//        Log.separator();
//        LZtrie lzt = new LZtrie();
//        LZtrie.Trie t = lzt.trieFactory();
//        String toTryOnTrieHaHa = "01001";
//        t.addString(toTryOnTrieHaHa);
//        if(!t.doesContainString(toTryOnTrieHaHa)) {
//            System.out.println("FAIL - Should contain " + toTryOnTrieHaHa);
//        } else {
//            System.out.println("Trie can find");
//        }
//        if(!t.doesContainString(toTryOnTrieHaHa.substring(0,2))) {
//            System.out.println("FAIL - Should contain " + toTryOnTrieHaHa.substring(0,2));
//        } else {
//            System.out.println("Trie can find");
//        }
//        // shouldn't find this
//        if(t.doesContainString(toTryOnTrieHaHa.substring(2))) {
//            System.out.println("FAIL - Should contain " + toTryOnTrieHaHa.substring(2));
//        } else {
//            System.out.println("Trie can find");
//        }
//    }
//
//    public static void trieShouldOnlyFindThingsThatItHas()
//    {
//        Log.separator();
//        LZtrie lzt = new LZtrie();
//        LZtrie.Trie t = lzt.trieFactory();
//        String toTryOnTrieHaHa = "001";
//        t.addString(toTryOnTrieHaHa);
//        if(t.doesContainString(toTryOnTrieHaHa + "01")) {
//            System.out.println("Should not contain " + toTryOnTrieHaHa + "01");
//        } else {
//            System.out.println("Trie can find");
//        }
//
//    }


    private static void testSimpleLZAdding()
    {
        ILZ lzt = new LZtrie();
        lzt.encode("01001010100100001001010111");

        String enc = lzt.encode("00101010101010101");
        System.out.println("\n\n                 0 01 010 1 0101 010101");
        System.out.println("Encoded version: " + enc);


        System.out.println("000000000000000000 is parsed as 0 00 000 0000 00000 000");
        String encoded = lzt.encode("000000000000000000");

        System.out.println("\n\n                 0 00 000 0000 00000 000");
        System.out.println("Encoded version: " + encoded);

    }

    private static void testEncodingLZTrie(){
        ILZ lzt = new LZtrie();
        String input = "010011110110101110011";
        String output = "00 01 010 101 1000 1011 0011 1010 0100".replace(" ", "");
        String encoded = lzt.encode(input);

        System.out.println("\n\n                 " + output);
        System.out.println("Encoded version: " + encoded);

    }
    private static void testEncodingLZTrie2(){
        ILZ lzt = new LZtrie();
        String input = "00000000000000000000000000000000000000000000000000";
        String output = "00 10 100 110 1000 1010 1100 1110 10000 0101".replace(" ", "");
        String encoded = lzt.encode(input);

        System.out.println("\n\n                 " + output);
        System.out.println("Encoded version: " + encoded);

    }

    private static void testEncodingLZHash(){
        ILZ lz = new LZhash();
        String input = "010011110110101110011";
        String output = "00 01 010 101 1000 1011 0011 1010 0100".replace(" ", "");
        String encoded = lz.encode(input);

        System.out.println("\n\n                 " + output);
        System.out.println("Encoded version: " + encoded);

    }
    public static void testEncodingLZHash2(){
        ILZ lz = new LZhash();
        String input = "00000000000000000000000000000000000000000000000000";
        String output = "00 10 100 110 1000 1010 1100 1110 10000 0101".replace(" ", "");
        String encoded = lz.encode(input);

        System.out.println("\n\n                 " + output);
        System.out.println("Encoded version: " + encoded);

    }

}
