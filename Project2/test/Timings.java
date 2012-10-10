import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 9/10/12
 * Time: 9:29 AM
 * Handle timing things, and another comment
 */
public class Timings {
    private HashMap<String,Timer> runningTimers;
    private HashMap<String,Timer> completedTimers;
    private LinkedList<String> lastLabel;


    public Timings(){
        runningTimers = new HashMap<String,Timer>();
        completedTimers = new HashMap<String,Timer>();
        lastLabel = new LinkedList<String>();
    }

    public boolean start(String label){
        boolean started = false;
        if(!runningTimers.containsKey(label)){
            System.out.println("Starting timer for " + label);
            lastLabel.addLast(label);
            Timer timer = new Timer();
            runningTimers.put(label,timer);
            started = timer.start();
        }
        return started;
    }

    public boolean stop(String label){
        boolean stopped = false;
        if(runningTimers.containsKey(label)){
            Timer timer = runningTimers.get(label);
            stopped = timer.stop();
            if(stopped){
                runningTimers.remove(timer);
                completedTimers.put(label,timer);
                if(lastLabel.getLast().equals(label)){
                    // help the stack out
                    lastLabel.removeLast();
                }
            }

        }
        return stopped;
    }


    public boolean stop(){
        boolean wasStopped = false;
        if(lastLabel.size() > 0){
            String label = lastLabel.getLast();
            wasStopped = stop(label);
        }
        return wasStopped;
    }

    public String report() {
        Set<String> keys = completedTimers.keySet();
        String report = "";
        for(String k : keys){
            report += "\n" + k +" ------------------ \n";
            report += completedTimers.get(k).reportWithIndentation(1);
//            report += "\n-----------------------------------\n";
        }
        report += "\n";
        return report;
    }


    public String toString(){
        return report();
    }
}
