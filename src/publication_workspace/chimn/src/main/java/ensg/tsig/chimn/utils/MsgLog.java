/**
 * 
 */
package ensg.tsig.chimn.utils;

/**
 * @author hanane
 *
 */

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.*;
 import java.util.*;

     public class MsgLog {
     protected static String defaultLogFile = Paths.get("").toAbsolutePath().toString()+"\\chimnlog.log";
     
         public static void write(String s) throws IOException {
         write(defaultLogFile, s);
     }
    
         public static void write(String f, String s) throws IOException {
        	 System.out.println("gdkazgefkagezfkgaze**********************************"+System.getProperty("user.dir"));
         TimeZone tz = TimeZone.getTimeZone("EST"); // or PST, MID, etc ...
         Date now = new Date();
         DateFormat df = new SimpleDateFormat ("yyyy.mm.dd hh:mm:ss ");
         df.setTimeZone(tz);
         String currentTime = df.format(now);
        
         FileWriter aWriter = new FileWriter(f, true);
         aWriter.write("\n"+currentTime + " " + s );
         aWriter.flush();
         aWriter.close();
     }
 }
