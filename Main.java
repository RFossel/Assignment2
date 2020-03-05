//   Raul Fosselman - Lab 3
//   Edoras: cssc1082
//   CS 310
//   11/20/2019

package com.fosselman;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws IOException {
	    Graph graph = new Graph();

        try {
            File graphData = new File(args[0]);
            loadCSVToGraph(graphData, graph);

        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Please use a csv file in your argument");
            ex.printStackTrace();
            System.exit(0);
        }

//        ASSIGNMENT OUTPUT:

        //Print the total number of vertices
        System.out.println("Number of vertices: "+ graph.size());
        //List all vertices with self edges
        System.out.println("\n" +"Vertices with self edges:");
        listSelfEdgeVertices(graph);
        //List all vertices with no outbound edges
        System.out.println("\n\n" +"Vertices with no outbound edges:");
        listNoOutboundEdgeVertices(graph);
        //List the heaviest edge
        System.out.println("\n\n" + "Heaviest edge:");
        System.out.println(getHeaviestEdge(graph));

    }

    public static void loadCSVToGraph(File csvFile, Graph graph) throws FileNotFoundException {
        Scanner scanner = new Scanner(csvFile);
        scanner.useDelimiter("\n");

        while(scanner.hasNextLine()){
            //grab the next line, and break it up by commas
            String line  = scanner.nextLine();
            String[] vals = line.split(",");

            String src;
            String dest;
            int weight;

            if(vals.length == 3) {//max size of a properly formatted line
                src = vals[0];
                dest = vals[1];

                //if the last value isn't a weight, skip this iteration/move on
                if(!isInteger(vals[2])){
                    continue;
                }
                weight = Integer.parseInt(vals[2]);


                //if the vertex isn't there add it to the graph, already handled
                // by the addVertex method
                graph.addVertex(src);
                graph.addVertex(dest);

                //add edge (contained within the src vertex) to the graph
                graph.get(src).addEdge(src, dest, weight);
            }
            //if there's only 1 value, add the vertex if it's not there
            else if(vals.length == 1){
                src = vals[0];
                graph.addVertex(src);
            }
        }
    }

    public static void listSelfEdgeVertices(Graph graph){
        //iterate through all the vertices in the graph
        for (Graph.Vertex vertex: graph.graphMap.values()) {
            //iterate through all the edges in the vertex
            for(Graph.Vertex.Edge edge : vertex.edges){
                //if the src is the dest, then print it
                if(edge.getDest().equals(edge.getSrc())){
                    System.out.print(vertex.getValue() + ", ");
                    //if it hits once it's already known that it has a self edge
                    break;
                }
            }
        }
    }

    public static void listNoOutboundEdgeVertices(Graph graph){
        //iterate through all the vertices in the graph
        for (Graph.Vertex vertex: graph.graphMap.values()) {
            //if the vertex's edge list is null, print it
            if (vertex.edges.isEmpty()) {
                System.out.print(vertex.getValue() + ", ");
            }
        }
    }

    List<Integer> listAdder(){
        List nn = new ArrayList();
        List s = new Stack();
        return s;
    }

    public static String getHeaviestEdge(Graph graph){
        //instead of iterating through the graph to initialize heaviest edge,
        // just initialize the heaviest value as practically neg infinity
        int heaviest = Integer.MIN_VALUE;
        String edgeHolder = "No weights found";
        //iterate through the graph vertices
        for (Graph.Vertex vertex: graph.graphMap.values()) {
            //iterate through all the edges in the vertex
            for (Graph.Vertex.Edge edge : vertex.edges) {
                //note: if there are ties the first will return as the
                // heaviest, which is arbitrary within the graph
                if (edge.getWeight() > heaviest) {
                    heaviest = edge.getWeight();
                    edgeHolder =
                            edge.getSrc() + ", " + edge.getDest() + ", " + edge.getWeight();
                }
            }
        }
        return edgeHolder;
    }

    public static boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
