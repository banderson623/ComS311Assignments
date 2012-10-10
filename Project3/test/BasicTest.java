/**
 * User: brian_anderson
 * Date: 10/10/12
 */
public class BasicTest {

    public static void main(String [ ] args){
        System.out.println("Hello world, this is Assignment 3");
        trieTest();
        trieTestShouldError();

    }

    public static void trieTest() {
        LZtrie lzt = new LZtrie();
        LZtrie.Trie t = lzt.trieFactory();
        Log.off();
        t.addString("00");
    }

    public static void trieTestShouldError() {
        LZtrie lzt = new LZtrie();
        LZtrie.Trie t = lzt.trieFactory();
        try {
            Log.off();
            t.addString("a");
            Log.resume();
        } catch (InternalError e){
            System.out.println("Got good error!");
        }
    }

}
