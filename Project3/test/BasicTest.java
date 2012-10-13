/**
 * User: brian_anderson
 * Date: 10/10/12
 */
public class BasicTest {
    public static void main(String [ ] args){

        testEncodingLZTrie();
        testEncodingLZTrie2();

//        testEncodingLZHash();
//        testEncodingLZHash2();

        testEncodingLZHash1andaHalf();
//        testSimpleEncoderDecoder();
//
    }


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
        ILZ lzt = new LZhash();
        String input = "010011110110101110011";
//        String input = "010011110110101110011000001";
        String output = "00 01 010 101 1000 1011 0011 1010 0100";//.replace(" ", "");
        String encoded = lzt.encode(input);



        System.out.println("\n\nExpected          " + output);
        System.out.println("Encoded version: " + encoded);
        System.out.println("Decoded version:  " + LZDecoder.decode(output.replace(" ","")));
        System.out.println("Decoded version:  " + LZDecoder.decode(encoded.replace(" ","")));

        System.out.println("input :           " + input);
    }
    private static void testEncodingLZTrie2(){
        ILZ lzt = new LZtrie();
        String input = "00000000000000000000000000000000000000000000000000";
        String output = "00 10 100 110 1000 1010 1100 1110 10000 0101";//.replace(" ", "");
        String encoded = lzt.encode(input);

        System.out.println("\n\n                  " + output);
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

    private static void testEncodingLZHash1andaHalf(){
        ILZ lz = new LZhash();
        String input = "000001000";
        String encoded = lz.encode(input);

        System.out.println("\n\nInput            " + input);
        System.out.println("                      Encoded version: " + encoded);
        System.out.println("Decoded          " + LZDecoder.decode(encoded.replace(" ","")));
//        System.out.println("Decoded          " + LZDecoder.decode("0010"));
//
//
        input = "0110100000";
        encoded = lz.encode(input);

        System.out.println("\n\nInput            " + input + " [" + input.length() +"]");
        System.out.println("                      Encoded version: " + encoded);
        System.out.println("\nDecoded          " + LZDecoder.decode(encoded.replace(" ","")));
        System.out.println("Input            " + input + " [" + input.length() +"]");

//        String input2 = "0001";
//        String encoded2 = lz.encode(input2);
//
//        System.out.println("\n\nInput            " + input2);
//        System.out.println("                      Encoded version: " + encoded2);
//        System.out.println("Decoded          " + LZDecoder.decode(encoded2.replace(" ","")));

    }

    public static void testEncodingLZHash2(){
        ILZ lz = new LZhash();
        String input = "00000000000000000000000000000000000000000000000000";
        String output = "00 10 100 110 1000 1010 1100 1110 10000 0101".replace(" ", "");
        String encoded = lz.encode(input);

        System.out.println("\n\n                 " + output);
        System.out.println("Encoded version: " + encoded);

    }

    private static void testSimpleEncoderDecoder(){
        ILZ lz = new LZhash();
        String testString = "1";
        String result = "";

        for(int i = 0; i < 10; ++i){
            System.out.println("____________________________________________________________________________________________________");
//
            testString += ((Math.random() > 0.35) ? "1" : "0");
            System.out.println("Number of digits: " + testString.length());
            String encoded = lz.encode(testString);
            System.out.println("Encoding: " + testString + " --> " + encoded);
            try {
                String decodedTestString = LZDecoder.decode(encoded.replace(" ",""));
                System.out.println("Decoding: " + decodedTestString);
                if(!testString.equals(decodedTestString)){
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Failed");
    //                return;
                    result += "-";
                } else {
//                    System.out.println("...............................................................Passed");
                    result += "+";
                }
            } catch(IndexOutOfBoundsException err) {
                System.out.println("************************************************oops " + err);
                result += "*";
            } catch(IllegalArgumentException err) {
                System.out.println("************************************************oops  " + err);
                result += "*";
            }

        }
        System.out.println("\n\nResult : " + result);
    }

}
