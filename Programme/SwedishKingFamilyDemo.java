/** SwedishKingFamilyDemo.java --- demonstration of operations on directed graphs
 * 
 * Copyright (C) 2008  Minh Van Nguyen <nguyenminh2@gmail.com>
 * Copyright (C) 2012-15  Uwe Aßmann
 *
 * This program is free software; you can redistribute it and/or  modify
 * it under the terms of the GNU General Public License as published  by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

import java.util.*;
import org.jgrapht.alg.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;

/**
 * This class demonstrates some of the operations that can be performed on
 * directed graphs. After constructing a basic directed graph, it computes a
 * cycle test, depth-first search, breadth-first search, path search. Also, the graph is reinterpreted
 * as undirected. Then, reverse paths can be found.
 *
 * Experimentation:
 * - Compare depth-first and breadth-first traversals. How do they differ?
 * Visualize.
 * 
 * You can try to add a cycle by commenting out line *1*.
 *
 * @author Minh Van Nguyen, Uwe Assmann
 * @since 2012-04-17
 */
public class SwedishKingFamilyDemo {
    public static void main(String args[]) {
	String adam = "Adam";
	String victoria = "Victoria";
	String madeleine = "Madeleine";
        // constructs a directed graph with the specified vertices and edges
        DirectedGraph<String, DefaultEdge> parentOf =
            new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
            // new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        parentOf.addVertex(adam);
        parentOf.addVertex("Eve");
        parentOf.addVertex("Sibylla");
        parentOf.addVertex("Gustav Adolf");
        parentOf.addVertex("Alice Sommerlath");
        parentOf.addVertex("Walter Sommerlath");
        parentOf.addVertex("Ralf");
        parentOf.addVertex("Sylvia");
        parentOf.addVertex("Carl Gustav");
        parentOf.addVertex("Desiree");
        parentOf.addVertex(victoria);
        parentOf.addVertex("Carl Philipp");
        parentOf.addVertex(madeleine);

   	// add edges
        parentOf.addEdge("Adam", "Gustav Adolf");
        parentOf.addEdge("Adam", "Sibylla");
        parentOf.addEdge("Adam", "Walter Sommerlath");
        parentOf.addEdge("Adam", "Alice Sommerlath");
        parentOf.addEdge("Walter Sommerlath","Sylvia");
        parentOf.addEdge("Alice Sommerlath","Sylvia");
        parentOf.addEdge("Walter Sommerlath","Ralf");
        parentOf.addEdge("Alice Sommerlath","Ralf");
        parentOf.addEdge("Gustav Adolf", "Desiree");
        parentOf.addEdge("Sibylla", "Desiree");
        parentOf.addEdge("Gustav Adolf", "Carl Gustav");
        parentOf.addEdge("Sibylla", "Carl Gustav");
        parentOf.addEdge("Carl Gustav", "Victoria");
        parentOf.addEdge("Carl Gustav", "Carl Philipp");
        parentOf.addEdge("Carl Gustav", "Madeleine");
        parentOf.addEdge("Sylvia", "Victoria");
        parentOf.addEdge("Sylvia", "Carl Philipp");
        parentOf.addEdge("Sylvia", "Madeleine");
        /* 1 */ // 
parentOf.addEdge(victoria,adam);

        System.out.println("parentOf graph: "+parentOf.toString());

	// (a) cycle detection
        CycleDetector<String, DefaultEdge> cycleDetector = new CycleDetector<String, DefaultEdge>(parentOf);
        Set<String> cycleVertices = cycleDetector.findCycles();
        System.out.println("******* Cycle: "+cycleVertices.toString());

	// (b) depth-first iteration, diving as deep as possible into the graph
	System.out.println("depth first enumeration: =======");
	DepthFirstIterator<String,DefaultEdge> dfi = new DepthFirstIterator<String, DefaultEdge>(parentOf);
	for (String node = dfi.next(); dfi.hasNext(); node = dfi.next()) {
	    System.out.println("node: "+node);
	} 

	// (c) breadth-first iteration: "staying as far out as possible"
	System.out.println("breadth first enumeration: =======");
	BreadthFirstIterator<String,DefaultEdge> bfi = new BreadthFirstIterator<String, DefaultEdge>(parentOf);
//  	for (String node: ((java.lang.Iterable)bfi)) {
// 	for (String node: bfi) {
 	for (String node = bfi.next(); bfi.hasNext(); node = bfi.next()) {
	    System.out.println("node: "+node);
	} 

	// (d) Shortest path with Dijkstra's method
	DijkstraShortestPath<String,DefaultEdge> descendantPath = new DijkstraShortestPath<String, DefaultEdge>(parentOf,adam,victoria);
	System.out.println("shortest path in the DIRECTED graph between Adam and Victoria ("+descendantPath.getPathLength()+"): ===========");
	GraphPath<String,DefaultEdge> path = descendantPath.getPath();
	// Hint: Graphs is an algorithm class (helper class)
	List<String> nodeList = Graphs.getPathVertexList(path);
	for (String node : nodeList) {
	    System.out.println("node: "+node);
	} 

	// Now interpret the directed graph as undirected
	AsUndirectedGraph<String,DefaultEdge> descendantOrAscendant = new AsUndirectedGraph<String, DefaultEdge>(parentOf);
        System.out.println("related graph: "+descendantOrAscendant.toString()+"=============");

	// (e) Shortest path with Dijkstra's method in the undirected graph
	DijkstraShortestPath<String,DefaultEdge> ancestorPath = new DijkstraShortestPath<String, DefaultEdge>(descendantOrAscendant,madeleine,adam);
	System.out.println("shortest path between Madeleine and Adam in the UNDIRECTED graph ("+ancestorPath.getPathLength()+"):");
// 	System.out.println("path between Madeleine and Adam:");
	path = ancestorPath.getPath();
	nodeList = Graphs.getPathVertexList(path);
	for (String node : nodeList) {
	    System.out.println("node: "+node);
	} 
    }
}
