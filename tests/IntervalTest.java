import javax.tools.JavaFileManager;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/4/12
 * Time: 11:29 PM
 * Interval Implementation for testing
 */
public class IntervalTest implements IScheduler.IInterval {
    int m_start, m_end;

    /**
     * This method will generate a set of intervals of specified size with random
     * start and end dates based off of today's date.
     *
     * @param length is the number of intervals to generate
     * @return a java.util.Set of intervals
     */
    static Set<IntervalTest> BuildSetOfLength(int length){
        Set<IntervalTest> theSet =new HashSet<IntervalTest>(length);

        for(int i = 0; i < length; ++i){
            theSet.add(new IntervalTest());
        }

        return theSet;
    }

    static void DisplayIntervals(Set<? extends IScheduler.IInterval> intervals){
        int first = Integer.MAX_VALUE;
        int last = -1;

        java.util.Iterator<? extends IScheduler.IInterval> it = intervals.iterator();
        while(it.hasNext()) {
            IScheduler.IInterval interval = it.next();
            if(interval.getStartTime() < first){
                first = interval.getStartTime();
            }

            if(interval.getEndTime() > last){
                last = interval.getEndTime();
            }
        }

        int size = 60;
        int scale = (last - first) / size;
        System.out.println(first + "                                                      " + last);
        System.out.println("|------------------------------------------------------------|");

        java.util.Iterator<? extends IScheduler.IInterval> it2 = intervals.iterator();
        while(it2.hasNext()) {
            IScheduler.IInterval interval = it2.next();
            // take the start time minus the first and divide by scale
            int firstDisplay = ((interval.getStartTime() - first)  / scale) + 1;
            int lengthDisplay = (interval.getEndTime() - interval.getStartTime())  / scale;
            for(int j = 0; j < firstDisplay; ++j){System.out.print(" ");}
//            System.out.print("|");
            for(int j = 0; j < lengthDisplay; ++j){System.out.print("-");}
            System.out.println("");
        }


    }

    public IntervalTest(){
        Date now = new Date();
        Long longTime = new Long(now.getTime()/1000);

        m_start =  (int) (longTime + Math.round(Math.random() * 10000));
        m_end = m_start + 1000 + (int) Math.round(Math.random() * 1000);
    }

    // Simple constructor
    public IntervalTest(int start, int end){
        if(end < start){
            throw new InvalidParameterException("Start must be before end");
        }

        m_start = start;
        m_end = end;
        System.out.println("Creating Interval m_start: " + m_start + ", m_end: " + m_end);
    }

    @Override
    public int getStartTime() {
        return m_start;
    }

    @Override
    public int getEndTime() {
        return m_end;
    }

    public String toString(){
        return "start: " + m_start +  " - end: " + m_end + " duration: " + ((m_end-m_start)/60);
    }

}
