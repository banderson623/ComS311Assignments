import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/5/12
 * Time: 12:23 AM
 * Something to run my tests
 */
public class main {
    public static void main(String [ ] args){
        Set<IntervalTest> test = IntervalTest.BuildSetOfLength(15);
        IntervalTest.DisplayIntervals(test);
    }


}
