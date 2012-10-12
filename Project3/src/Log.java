///**
// * Created with IntelliJ IDEA.
// * User: brian_anderson
// * Date: 10/10/12
// * Time: 5:02 PM
// * To change this template use File | Settings | File Templates.
// */
//public class Log {
//    public static final int MORE = 3;
//    public static final int LESS = -3;
//
//    private static boolean isDebug = true;
//    private static boolean isDebugPrevious = true;
//    private static int indentationLevel = 1;
//    public String context;
//
//    public Log(String context){
//        this.context = context;
//    }
//
//    static void on(){
//        isDebug = true;
//    }
//
//    static void reset(){
//        indentationLevel = 1;
//    }
//
//    static void separator()
//    {
//        if(Log.isDebug) System.out.println("-----------------------------------------------------");
//    }
//
//    static void off(){
//        isDebugPrevious = isDebug;
//        isDebug = false;
//    }
//
//    static void resume(){
//        isDebug = isDebugPrevious;
//    }
//
//    public void changeIndent(int level){
//        Log.indentationLevel += level;
//        if(Log.indentationLevel < 1){
//            Log.indentationLevel = 1;
//        }
//    }
//
//    public void log(String logval){
//        if(Log.isDebug){
//            System.out.printf("%30s|%"+Log.indentationLevel+"s %s%n", context, "....", logval);
//        }
//    }
//
//    static void d(String result){
//        debug(result);
//    }
//    static void debug(String result){
//        if(Log.isDebug) System.out.println(result);
//    }
//}
