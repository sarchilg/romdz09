/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romdz09;

/**
 *
 * @author user
 */
public class myLog {
    static public boolean isdebug=true;
    public static void debug(String ss){
        if (isdebug)
        myLog.Systemoutprintln(ss);
    }
    public static void info(String ss){
        myLog.Systemoutprintln(ss);
    }
    public static void error(String ss){
        System.out.println("err="+ss);
    }
    public static void error(String ss,Exception e){
        System.out.println("err="+ss+" "+e.toString());
    }
    public static boolean isDebugEnabled(){
        return isdebug;
    }
    public static void Systemoutprintln(String ss){
        if (isdebug)
        System.out.println(ss);
    }
    public static void Systemoutprintln(){
        if (isdebug)
        System.out.println();
    }
}
