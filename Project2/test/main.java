/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/7/12
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class main {

    public static void main(String [ ] args){
        System.out.println("Main Test starting");

        Builders<String> builder = new Builders<String>();
//        builder = new helpers.Builders<String>();

        System.out.println(builder.listOfIntegersSizeAndGuarenteeKey(10, "Brian"));
    }

}
