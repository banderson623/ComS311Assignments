import java.util.HashSet;
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
        // The ordered set, no larger than the size of the original set
        Set<IInterval> theOptimalSet = new HashSet<IInterval>(s.size());

        java.util.Iterator<? extends IInterval> it = s.iterator();
        if(it.hasNext()){

            IInterval interval = it.next();
            IInterval endingSoonest = interval;
            if(it.hasNext()){
                do{
                    interval = it.next();
                    if(interval.getEndTime() < endingSoonest.getEndTime()){
                        endingSoonest = interval;
                    }
                } while(it.hasNext());
            }
            // add this to optimal set
            theOptimalSet.add(endingSoonest);
            // remove the soonest ending interval
            s.remove(endingSoonest);

            java.util.Iterator<? extends IInterval> iteratorWithPotentialConflicts = s.iterator();
            while(iteratorWithPotentialConflicts.hasNext()){
                IInterval intervalToCheck = iteratorWithPotentialConflicts.next();

                if( // contained within (or equal)
                   (intervalToCheck.getEndTime() <= endingSoonest.getEndTime() &&
                    intervalToCheck.getStartTime() >= endingSoonest.getStartTime())
                   || // starts before but ends during or at the same time
                   (intervalToCheck.getStartTime() <= endingSoonest.getStartTime() &&
                    intervalToCheck.getEndTime() >= endingSoonest.getStartTime())
                   || // starts after or at the same time but ends after
                    (intervalToCheck.getStartTime() >=  endingSoonest.getStartTime() &&
                     intervalToCheck.getStartTime() <= endingSoonest.getEndTime())
                   || // starts before and ends after
                    (intervalToCheck.getStartTime() <= endingSoonest.getStartTime() &&
                     intervalToCheck.getEndTime() >= endingSoonest.getEndTime())
                   ){
                    iteratorWithPotentialConflicts.remove();
                }

            }

            theOptimalSet.addAll(optimalSchedule(s));
        }

        return theOptimalSet;
    }
}
