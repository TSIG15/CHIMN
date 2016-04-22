/**
 * 
 */
package ensg.tsig.chimn.utils;

/**
 *
 *
 */

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.*;
 import java.util.*;
/**
 * 
 * 
 *
 */
     public class MsgLog {
     protected static String defaultLogFile  = System.getenv("CATALINA_HOME")+"\\logs\\chimnlog.log";
     
     	/**This method writes messages log in default log file.
     	 * 
     	 * @param s
     	 * @throws IOException
     	 */
         public static void write(String s) throws IOException {
         write(defaultLogFile, s);
     }
    
         /** This method writes messages log.
          * 
          * @param f
          * @param s
          * @throws IOException
          */
         public static void write(String f, String s) throws IOException {
        	// System.out.println("**********************************"+defaultLogFile+"***********************");
         TimeZone tz = TimeZone.getTimeZone("Europe/Paris"); // or PST, MID, etc ...
         Date now = new Date();
         DateFormat df = new SimpleDateFormat ("dd.MM.yyyy HH:mm:ss ");
         df.setTimeZone(tz);
         String currentTime = df.format(now);
        
         FileWriter aWriter = new FileWriter(f, true);
         aWriter.write("\n"+currentTime + " " + s );
         aWriter.flush();
         aWriter.close();
     }
 }
