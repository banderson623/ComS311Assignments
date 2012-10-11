/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 10/10/12
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Log {
    private static boolean isDebug = true;
    private static boolean isDebugPrevious = true;
    public String context;

    public Log(String context){
        this.context = context;
    }

    static void on(){
        isDebug = true;
    }

    static void off(){
        isDebugPrevious = isDebug;
        isDebug = false;
    }

    static void resume(){
        isDebug = isDebugPrevious;
    }


    public void log(String logval){
        if(Log.isDebug){
            System.out.printf("%30s | %s%n", context, logval);
        }
    }

    static void d(String result){
        debug(result);
    }
    static void debug(String result){
        if(Log.isDebug) System.out.println(result);
    }
}
