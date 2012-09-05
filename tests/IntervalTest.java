import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/4/12
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
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

    IntervalTest(){
        Date now = new Date();
        Long longTime = new Long(now.getTime()/1000);

        m_start =  (int) (longTime + Math.round(Math.random() * 10000));
        m_end = m_start + 1000 + (int) Math.round(Math.random() * 1000);
    }

    // Simple constructor
    IntervalTest(int start, int end){
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
}
