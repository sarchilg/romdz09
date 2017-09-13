/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romdz09;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class functions {
public static int str2int(String ss){
try{
    return Integer.decode(ss);
}catch (Exception e){
    return -1;
} 
}
public static int str2int(String ss,int def){
try{
    return Integer.decode(ss);
}catch (Exception e){
    return def;
} 
}
    public static void byte2file(String ff,byte[] bb,int off,int len){
        if (off+len>bb.length) {myLog.Systemoutprintln("save offset error "+ff);return;}
        File file=new File(ff);
      FileOutputStream fileOutputStream = null;
      //byte[] bFile = new byte[(int) file.length()];
      try
      {
         //convert file into array of bytes
         fileOutputStream = new FileOutputStream(file);
         if (off==-1)
            fileOutputStream.write(bb);
         else
             fileOutputStream.write(bb,off,len);
         fileOutputStream.close();
          myLog.Systemoutprintln("save "+ff);
    /*     for (int i = 0; i < bFile.length; i++)
         {
            System.out.print((char) bFile[i]);
         }*/
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
    }
    public static FileOutputStream byte2file1(String ff){
      File file=new File(ff);myLog.Systemoutprintln("================"+file.exists());
      FileOutputStream fileOutputStream = null;
      //byte[] bFile = new byte[(int) file.length()];
      try
      {
         //convert file into array of bytes
         fileOutputStream = new FileOutputStream(file);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }      
      return fileOutputStream;
    }
    public static void byte2file2(String ff,byte[] bb,int off,int len,FileOutputStream fileOutputStream){
        if (fileOutputStream==null) return;
        if (off+len>bb.length) {myLog.Systemoutprintln("save offset error "+ff);return;}
          try
      {

         if (off==-1)
            fileOutputStream.write(bb);
         else
             fileOutputStream.write(bb,off,len);
          //myLog.Systemoutprintln("save "+ff+" "+len);
    /*     for (int i = 0; i < bFile.length; i++)
         {
            System.out.print((char) bFile[i]);
         }*/
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
    }
    public static void byte2file3(FileOutputStream fileOutputStream){
        if (fileOutputStream==null) return;
              try
      {

         fileOutputStream.close();

      }
      catch (Exception e)
      {
         //e.printStackTrace();
      }
    }    
    
    public static void byte2file(String ff,byte[] bb)
   {
        byte2file( ff,bb, -1, -1);
   }
      public static String tohex(int ii){
     return String.format("%02X ", ii);
  }
  public static String tohex(Long ii){
     return String.format("%02X ", ii);
  }
    public static byte[] file2byte(String ff)
   {File file=new File(ff);
      FileInputStream fileInputStream = null;
      byte[] bFile = new byte[(int) file.length()];
      try
      {
         //convert file into array of bytes
         fileInputStream = new FileInputStream(file);
         fileInputStream.read(bFile);
         fileInputStream.close();
    /*     for (int i = 0; i < bFile.length; i++)
         {
            System.out.print((char) bFile[i]);
         }*/
      }
      catch (Exception e)
      {
          System.out.println("err "+e.toString());
         //e.printStackTrace();
      }
      return bFile;
   }
     public static byte str2byte(String s){
        s=s.trim();
         byte b=0;
                
                if (s.length()==2){
                b=(byte) ((Character.digit(s.charAt(0), 16) << 4)
                             + Character.digit(s.charAt(1), 16));

                }
                else if (s.length()==1){
                 b=(byte) (Character.digit(s.charAt(0), 16));                   
                    

                }
        return b;
    }
    public static String tobit(byte b) {
        return ("0000000" + Integer.toBinaryString(0xFF & b)).replaceAll(".*(.{8})$", "$1");
    }

    public static byte getbit(byte b, int n) {
        int x = 0xFF & b;
        for (int i = 0; i < n; i++) {
            x = x / 2;
        }

        return (byte) (x % 2);
    }
    public static String rev(String ss) {
        String s2 = "";
        for (int i = 0; i < ss.length(); i++) {
            s2 += ss.charAt(ss.length() - i - 1);
        }
        return s2;
    }
     public static int log2(int ii){
        if (ii<=2) return 1;
        if (ii<=4) return 2;
        if (ii<=8) return 3;
        if (ii<=16) return 4;
        if (ii<=32) return 5;
        if (ii<=64) return 6;
        if (ii<=128) return 7;
        if (ii<=256) return 0;
        if (ii<=512) return 9;
        if (ii<=1024) return 10;
        if (ii<=2048) return 11;
        if (ii<=4096) return 12;
        if (ii<=8192) return 13;
        if (ii<=16384) return 14;
        if (ii<=32768) return 15;
        if (ii<=65536) return 16;                        
        if (ii<=131072) return 17;
        if (ii<=262144) return 18;
        return 0;
    }
    public static int log2i(int ii){
        if (ii<=2) return 1;
        if (ii<=4) return 2;
        if (ii<=8) return 3;
        if (ii<=16) return 4;
        if (ii<=32) return 5;
        if (ii<=64) return 6;
        if (ii<=128) return 7;
        if (ii<=256) return 8;
        if (ii<=512) return 9;
        if (ii<=1024) return 10;
        if (ii<=2048) return 11;
        if (ii<=4096) return 12;
        if (ii<=8192) return 13;
        return 14;
    }
    public static void str2file(String str, String filename) {
        try {
//        FileOutputStream ff=new FileOutputStream(filename);
            OutputStreamWriter ff = new OutputStreamWriter(new FileOutputStream(filename));
//            BufferedWriter ff=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            ff.write(str);
            ff.close();
        } catch (Exception e) {
            myLog.Systemoutprintln(e.toString());
        }
    }

    public static String file2str(String filename) {

        BufferedReader ff;
        String ss, s1 = "";
        try {

            ff = new BufferedReader(new InputStreamReader(new FileInputStream(
                    filename)));
            while (true) {
                ss = ff.readLine();
                if (ss == null) {
                    break;
                }
                s1 += ss + "\n";
            }
            ff.close();

        } catch (Exception e) {
            myLog.Systemoutprintln(e.toString());
        }
        return s1;
    }
    public static String resource2str(String resname) {

        BufferedReader ff;
        String ss, s1 = "";
        try {

            ff = new BufferedReader(new InputStreamReader(functions.class.getResourceAsStream(resname)));
            while (true) {
                ss = ff.readLine();
                if (ss == null) {
                    break;
                }
                s1 += ss + "\n";
            }
            ff.close();

        } catch (Exception e) {
            myLog.Systemoutprintln(e.toString());
        }
        return s1;
    }
    public static String hex2str(int ii) {
        if (ii == 10) {
            return "a";
        }
        if (ii == 11) {
            return "b";
        }
        if (ii == 12) {
            return "c";
        }
        if (ii == 13) {
            return "d";
        }
        if (ii == 14) {
            return "e";
        }
        if (ii == 15) {
            return "f";
        }
        return "" + ii;
    }
    public static String[] mysplit(String str){
        List<String> list = new ArrayList<String>();
Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(str);
while (m.find())
    list.add(m.group(1)); // Add .replace("\"", "") to remove surrounding quotes.
String[] s2=new String[list.size()];
for (int i=0;i<s2.length;i++){
    s2[i]=list.get(i).replace("\"", "");//myLog.Systemoutprintln(s2[i]);
}
        return s2;
    }
    public static String getmemoryinfo(){
                Runtime runtime = Runtime.getRuntime();
long maxMemory = runtime.maxMemory();
long allocatedMemory = runtime.totalMemory();
long freeMemory = runtime.freeMemory();
long totalFreeMemory = freeMemory + (maxMemory - allocatedMemory);
long usedMemory = maxMemory - totalFreeMemory;
    String ss="maxMemory="+maxMemory+"\nallocatedMemory="+allocatedMemory
+"\nfreeMemory="+freeMemory+"\ntotalFreeMemory="+totalFreeMemory+"\n           usedMemory="+usedMemory;
        System.out.println(ss);
        return ss;
    }
}
