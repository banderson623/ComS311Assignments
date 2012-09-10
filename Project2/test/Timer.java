import java.lang.management.*;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/8/12
 * Time: 10:37 PM
 * Simple timing class
 */
//public interface Timerable {
//    boolean start();
//    boolean stop();
//    long getUserTime();
//}

public class Timer {
    public enum States {
        RUNNING,READY,COMPLETED,ERROR
    }

    private long startTimeUser;
    private long stopTimeUser;

    private long startTimeCPU;
    private long stopTimeCPU;

    private long startTimeSystem;
    private long stopTimeSystem;

    private States state;
    private boolean isCpuTimeSupported;
    private ThreadMXBean bean;

    Timer(){
        state = States.READY;
        bean = ManagementFactory.getThreadMXBean();
        isCpuTimeSupported = bean.isCurrentThreadCpuTimeSupported();
    }

    public boolean start() {
        boolean result = false;
        if(isCpuTimeSupported && state == States.READY){
            startTimeSystem = bean.getCurrentThreadCpuTime() - bean.getCurrentThreadUserTime();
            startTimeCPU = bean.getCurrentThreadCpuTime();
            startTimeUser = bean.getCurrentThreadUserTime();
            result = true;
            state = States.RUNNING;
        }
        return result;
    }

    public boolean stop(){
        boolean result = false;

        if(state == States.RUNNING){
            stopTimeUser = bean.getCurrentThreadUserTime();
            stopTimeCPU = bean.getCurrentThreadCpuTime();
            stopTimeSystem = bean.getCurrentThreadCpuTime() - bean.getCurrentThreadUserTime();
            state = States.COMPLETED;
            result = true;
        }
        return result;
    }

    public long getUserTime() {
        long userTime = 0;
        if(state == States.COMPLETED){
            userTime = stopTimeUser - startTimeUser;
        }
        return userTime;
    }

    public String report(){
        return reportWithIndentation(0);
    }

    public String reportWithIndentation(int levelOfTabs){
        String tabs = "";
        for(int i = 0; i < levelOfTabs; i++){
            tabs += "  ";
        }

        String report  = tabs+ "User Time:   " + getUserTime() / 1000 + "ms\n";
               report += tabs+ "CPU Time:    " + (stopTimeCPU - startTimeCPU) / 1000 + "ms\n";
               report += tabs+ "System Time: " + (stopTimeSystem - startTimeSystem) / 1000 + "ms (???)\n";
        return report;
    }

    public String toString() {
        return report();
    }

    // For comparable interface
    public int compareTo(Timer otherThing) {
        return (int) (otherThing.getUserTime() - getUserTime());
    }



}
