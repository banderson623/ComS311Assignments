import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/4/12
 * Time: 11:24 PM
 * Implement the Earliest Deadline algorithm in order to
 * compute the maximum set of jobs that may be scheduled
 * that do not overlap. The input is a set of jobs described
 * by their integer start and ending times.
 *      No job starts before time 0.
 * Thus an interval of a job is the start time and end time
 * taken together. Note that two jobs A and B do not overlap
 * if A ends at the same time that B starts.
 * Write a class called “Scheduler” that completely implements the
 * interface IScheduler defined below.
 * You may use any classes from the Java collections, which should make
 * this assignment easy to finish quickly. The method optimalSchedule takes a set of intervals as input and
 * returns the optimal set of intervals. For this problem, you are to turn the file Scheduler.java in via Blackboard.
 * This is the only way this problem will be accepted.
 */
public class Scheduler implements IScheduler{
    @Override

    public Set<IInterval> optimalSchedule(Set<IInterval> s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
