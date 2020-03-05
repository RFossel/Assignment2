package com.fosselman;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class Graph {
    HashMap<String, Vertex> graphMap = new <String, Vertex>HashMap();

    public class Vertex {
        String value;
        ArrayList<Edge> edges = new ArrayList<Edge>();

        public Vertex(String value) {
            this.value = value;
        }

        //REQUIRED METHOD
        public boolean addEdge(String source, String dest, int cost){
            //check if both names are valid
            if(graphMap.get(source) == null || graphMap.get(dest) == null){
                return false;
            } else{
                //add the edge to the source vertex's arrayList
                Vertex.Edge edge = new Edge(source, dest, cost);
                edges.add(edge);
                return true;
            }
        }

        //REQUIRED METHOD
        public boolean removeEdges(String source, String dest){
            //Check if both names are valid
            if(graphMap.get(source) == null || graphMap.get(dest) == null){
                return false;
            } else{
                //iterate through the edges ArrayList
                for(Edge e : edges){
                    //if an edge exists between the two vertexes remove it
                    if (e.getDest().equals(dest) && e.getSrc().equals(source)){
                        edges.remove(e);
                    }
                }
                return true;
            }
        }

        public String getValue() {
            return value;
        }

        //Edge contains each vertex's outgoing connections
        public class Edge {
            int weight;
            String src;
            String dest;

            public Edge(String src, String dest, int weight) {
                this.src = src;
                this.dest = dest;
                this.weight = weight;
            }

            public String getSrc() {
                return src;
            }

            public String getDest() {
                return dest;
            }

            public int getWeight(){
                return weight;
            }
        }
    }

    //REQUIRED METHOD
    public void addVertex(String name){
        if(!graphMap.containsKey(name)){
            graphMap.put(name, new Vertex(name));
        }
    }

    //REQUIRED METHOD
    public boolean removeVertex(String name){
        if(!graphMap.containsKey(name)){
            return false;
        } else{
            //iterate through each vertex's edges and remove all edges that
            // touch the desired vertex
            for(Vertex vertex : graphMap.values()) {
                for (Vertex.Edge edge : graphMap.get(name).edges) {
                    if(edge.getDest().equals(name) || edge.getSrc().equals(edge)){
                        vertex.edges.remove(edge);
                    }
                }
            }
            //remove the vertex from the hashmap
            graphMap.remove(name);
            return true;
        }
    }

    //REQUIRED METHOD
    public boolean contains(String name){
        return graphMap.containsKey(name);
    }

    //REQUIRED METHOD
    public boolean contains(String source, String dest){
        //iterate over all the edges of the desired vertex, if there is a
        // src->dest edge, return true
        for(Vertex.Edge edge : graphMap.get(source).edges){
            if(edge.getDest().equals(dest)){
                return true;
            }
        }
        return false;
    }

    public Vertex get(String key){
        return graphMap.get(key);
    }

    public int size(){
        return graphMap.size();
    }

}
