/** EchoRiddle1.java --- main program in Java
 * @author Uwe Assmann
 * @version 1.1
 * @date 2019-05-04
 */

// for import of System.out.println()
import java.util.*;

/**
   Simple main program of class EchoRiddle1.  Print all arguments given in
   the argument array args[].  'main', the main function of a Java
   program, is called by the run-time system (operating system).  Main
   operation "main" is a class "static" operation, i.e., it exists
   only once.

   Where is the bug?
 */
public class EchoRiddle1 {
    public static void main(String args[]) {
        int i = 0;
        for (i = 0; i < 5; i++) {
            if (i == 0) {
                System.out.print(args[i]);
            } else {
                System.out.print(" "+args[i]);
            }
        }
        System.out.println("");
    }
}


