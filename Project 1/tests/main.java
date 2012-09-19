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
        Scheduler s = new Scheduler();



        Set<? extends IScheduler.IInterval> test = IntervalTest.BuildSetOfLength(10);
        IntervalTest.DisplayIntervals(test);

        Set<IScheduler.IInterval> optimized = s.optimalSchedule((Set<IScheduler.IInterval>)test);
        IntervalTest.DisplayIntervals(optimized);


    }


}
