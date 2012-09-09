import java.lang.management.*;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/8/12
 * Time: 10:37 PM
 * Simple timing class
 */
public class Timer {
    public enum States {
        RUNNING,READY,ERROR
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
            state = States.READY;
            result = true;
        }

        return result;
    }

    public String report(){
        String report =    "User Time:   " + (stopTimeUser - startTimeUser) + "ns";
               report += "\nCPU Time:    " + (stopTimeCPU - startTimeCPU) + "ns";
               report += "\nSystem Time: " + (stopTimeSystem - startTimeSystem) + "ns (???)";
        return report;
    }


}
