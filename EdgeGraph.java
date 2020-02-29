import java.util.Random;
import java.math.*;
import java.text.*;
import java.util.*;

// class for storing edges in an adjacency list
public class EdgeGraph {

  // helper class for edges
  static class Edge {
        int v;
        int w;
        float dist;

        public Edge(int v, int w, float dist) {
            this.v = v;
            this.w = w;
            this.dist = dist;
        }
    }

  // instance variables
  private int n;
  LinkedList<Edge> edges[];

  // constructor
  public EdgeGraph(int n) {
    this.n = n;
    this.edges = new LinkedList[n];
    for (int i = 0; i <  n; i++) {
        edges[i] = new LinkedList<>();
    }
  }

  public void add(int v, int w, float dist) {
    Edge e = new Edge(v, w, dist);
    edges[v].add(e);
  }

  // return list of vertecies and weights that v is connected to
  public float[][] getAllVEdges(int v) {
    LinkedList<Edge> vEdges = edges[v];
    float[][] values = new float[n][2];

    for (int i = 0; i < vEdges.size() ; i++) {
        values[i][0] = vEdges.get(i).w;
        values[i][1] = vEdges.get(i).dist;
    }

    return values;
  }

  public float getDist(int v, int w){
    LinkedList<Edge> vEdges = edges[v];
    for (int i = 0 ; i < vEdges.size() ; i++ ) {
      if (vEdges.get(i).w == w) {
        return vEdges.get(i).dist;
      }
    }
    return -1.0f;
  }

  public void printGraph(){
    Hashtable<String, Float> out = new Hashtable<String, Float>();
    for (int i = 0; i < n ; i++) {
      LinkedList<Edge> V = edges[i];
      for (int j = 0; j < V.size() ; j++) {
        out.put(i + " " + V.get(j).w, V.get(j).dist);
        out.put(V.get(j).w + " " + i, V.get(j).dist);
      }
    }
    System.out.println();
    for (int i = 0; i < n; i++) {
      if (i == n / 2) {
        System.out.printf("    EDGES  ", i);
      } else {
        System.out.printf("       ");
      }
    }
    System.out.printf("%n    |");
    for (int i = 0; i < n; i++) {
      System.out.printf("  %2d  |", i);
    }
    for (int i = 0; i < n; i++) {
      System.out.printf("%n %2d |", i);
      for (int j = 0; j < n; j++) {
        System.out.printf("%6.4f|", out.get(i + " " + j));
      }
    }
    System.out.printf("%n");
  }

}
