import java.util.*;

// Improves on MSTV2 by using hash tables to store edges

public class MstV4 {

  // instance variables
  EdgeGraph edges;
  int n;
  double average;
  // helper class
  static class Pair {
    EdgeGraph eg;
    double a;

    public Pair(EdgeGraph eg, double a) {
      this.eg = eg;
      this.a = a;
    }
  }

  // class constructor
  public MstV4(EdgeGraph e, int n)
  {
    Pair t = prims(e, n);
    this.edges = t.eg;
    this.n = n;
    this.average = t.a;
  }

  // PUBLIC METHODS
  // print method
  public void printEdges()
  {
    edges.printGraph();
  }
  // get edges
  public EdgeGraph getEdges() {
    return edges;
  }
  // get n
  public int getN() {
    return n;
  }
  // get avg
  public double getAvg(){
    return average;
  }

  // PRIVATE HELPER METHODS
  // implementation of prims algorithm
  private static Pair prims(EdgeGraph e, int n) {

    //variable instantiation
    int v;
    int w;
    float avg = 0;
    EdgeGraph mst = new EdgeGraph(n);
    float[] dist = new float[n];
    int[] prev = new int[n];
    boolean[] S = new boolean[n];
    boolean[] Visited = new boolean[n];
    MinHeap H = new MinHeap(n);

    H.insert(0.0, 0.0);
    for (int i = 0; i < n ; i++ ) {
      dist[i] = Integer.MAX_VALUE;
      prev[i] = -1;
      Visited[i] = false;
      S[i] = false;
    }
    // 0 is always the source
    dist[0] = 0.0f;
    while (!H.isEmpty()) {
      v = (int) H.remove();
      S[v] = true;
      Visited[v] = true;
      // get all edges of V
      float[][] vEdges = e.getAllVEdges(v);
      for (int i = 0; i < vEdges.length ; i++) {
        // if there exists an edge and w is not in the set
        w = (int) vEdges[i][0];
        float d = vEdges[i][1];
        if (!S[w] && dist[w] > d) {
          dist[w] = d;
          prev[w] = v;
          if(Visited[w]) {
            H.update(Double.valueOf(w), dist[w], 1);
          } else {
            Visited[w] = true;
            H.insert(Double.valueOf(w), dist[w]);
          }
        }
      }
    }
    // constructing return
    for (int i = n - 1; i > 0 ; i-- ) {
      float t = e.getDist(i, prev[i]);
      avg = avg + t;
      mst.add(i , prev[i], t);
      mst.add(prev[i], i , t);
    }
    return new Pair(mst, avg / n);
  }
}
