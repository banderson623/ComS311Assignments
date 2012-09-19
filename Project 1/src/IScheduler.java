/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/4/12
 * Time: 11:24 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Set;
public interface IScheduler
{
    public interface IInterval
    {
        public int getStartTime();
        public int getEndTime();
    }
    public Set<IInterval> optimalSchedule(Set<IInterval> s);
}