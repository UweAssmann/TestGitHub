/** Polygon.java --- Polygone in Java
 * @author Uwe Assmann
 * @version 1.1
 * @date 2019-05-04
 */

// for import of System.out.println()
import java.util.*;
// JUnit functions: Be careful, junit should be listed in the CLASSPATH environment variable
import junit.framework.*;

/**
   Polygons are sequences of points and lines. 
 */

public class Polygon { 
    private int numberOfPoints;
    private int points[] = new int[100];
    public Polygon (int zahl) throws ArrayIndexOutOfBoundsException {
	// Check precondition
	if  (zahl >= 100) {
	    throw  new ArrayIndexOutOfBoundsException();
	} else  {
	    for (i = 0; i < 100; i++) {
		points[i] = 0;
	    }
	}
    }
    public void setPoint (int index, int x, int y) {
    }
    public void whoAmI (Polygon p) {
	// Check invariants: 
	// 3 edges: Triangle
	if  (numberOfPoints == 3) {
	    System.out.println("Dreieck");
	} else if (zahl == 4) {
	// 4 edges: Square or Rectangle?
	    System.out.println("Rectangle");
	} else {
	    System.out.println("Rectangle with "+numberOfPoints+"nodes");
	}
	
    }
}

class Dreieck extends Polygon { 
    private Polygon myData;
    public Dreieck (int l0, int l1, int l2) {
	myData = new Polygon(3);
	points[0] = l0;
	points[1] = l1;
	points[2] = l2;
    }
    @Test
    public void testGueltig() {
        Dreieck dreieck = new Dreieck(2, 2, 2);
        assertTrue(dreieck.istGueltig());
    } 

    @Test
    public void testUnguelting() {
        Dreieck dreieck = new Dreieck(2, 2, -2);
        assertFalse(dreieck.istGueltig());
    }
    public static void main(String args[]) {
	int i = 0;
	while (i < args.length) {
	    if (i == 0) {
		System.out.print(args[i]);
	    } else {
		System.out.print(" "+args[i]);
	    }
	    i++;
	}
	System.out.println("");
    }
}


class Rechteck extends Polygon { 
}
