# Weighted Directed Graph Project - Lab3

#### TLDR
Make a weighted directed graph from scratch, load in values from a CSV file and implement functions to find and print various characteristics of the graph

#### 1. Overview
In this programming assignment, students shall build an application
which constructs a weighted, directed graph from a single CSV file. Students
must build both the driver application, responsible for loading the data and
displaying information about the graph, as well as the weighted-directed
graph itself.

#### 2. Requirements
This assignment incorporates several abstractions, and it necessitates several
ADTs covered throughout the semester. Students must implement the graph
and application identified in this assignment directly from scratch. Students
need not implement underlying data structures, like the list, vector, map,
or queue, however, and they may instead use the versions provided in the
standard library 1

Using the path to the input file specified as the command line argument,
the application shall construct a weighted, directed graph. It shall extract
the vertex and edge information from the comma-delineated input file. With the graph constructed in the program’s memory, the program shall then print several characteristics about the graph as
defined below:
- The total number of vertices in the graph.
- List any vertices with exactly zero inbound edges.
- List any vertices with self edges.
- List any vertices with exactly zero outbound edges.
- List the edge with the heaviest weight.
