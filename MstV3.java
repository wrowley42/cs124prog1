import java.util.*;

// Improves on MSTV2 by using hash tables to store edges

public class MstV3 {

  // instance variables
  Hashtable<String, Float> edges = new Hashtable<String, Float>();
  int n;
  float avg;
  float max;


  // class constructor
  public MstV3(Hashtable<String, Float> e, int n)
  {
    System.out.println("Assigning MST Edges");
    this.edges = prims(e, n);
    this.n = n;
    System.out.println("Calculating MST average");
    this.avg = averageWeight(edges);
    System.out.println("Calculating MST average");
    this.max = genMax(edges);
  }

  // PUBLIC METHODS
  // print method
  public void printEdges()
  {
    System.out.println();
    for (int i = 0; i < n; i++) {
      if (i == n / 2) {
        System.out.printf("MST EDGES  ", i);
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
        System.out.printf("%6.4f|", edges.get(i + " " + j));
      }
    }
    System.out.printf("%n");
  }
  // get edges
  public Hashtable<String, Float> getEdges() {
    return edges;
  }
  // get n
  public int getN() {
    return n;
  }
  // get avg
  public float getAvg(){
    return avg;
  }
  // get max
  public float getMax(){
    return max;
  }

  // PRIVATE HELPER METHODS
  // implementation of prims algorithm
  private static Hashtable<String, Float> prims(Hashtable<String, Float> e, int n) {

    //variable instantiation
    int v;
    int w;
    Hashtable<String, Float> mst = new Hashtable<String, Float>();
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
      for (w = 0; w < n ; w++) {
        // if there exists an edge and w is not in the set
        if (e.get(v + " " + w) != null && !S[w]) {
          if (dist[w] > e.get(v + " " + w)) {
            dist[w] = e.get(v + " " + w);
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
    }
    for (int i = n - 1; i > 0 ; i-- ) {
      mst.put(i + " " + prev[i], e.get(i + " " + prev[i]));
      mst.put(prev[i] + " " + i, e.get(i + " " + prev[i]));
    }
    return mst;
  }
  // computes average weight of the edges in the MST
  private float averageWeight(Hashtable<String, Float> e) {
    float average = 0;
    float k = 0;
    for (int i = 0; i < n ; i++ ) {
      for (int j = i; j < n; j++ ) {
        if (i != j && e.get(j + " " + i) != null) {
            average = average + e.get(i + " " + j);
            k = k + 1.0f;
        }
      }
    }
    return average / k;
  }
  // compute maximum weight of edges in the mst
  private float genMax(Hashtable<String, Float> e) {
    float max = 0;
    for (int i = 0; i < n ; i++ ) {
      for (int j = i; j < n; j++ ) {
        if (e.get(i + " " + j) != null) {
          if (e.get(i + " " + j) > max) {
            max = e.get(i + " " + j);
          }
        }
      }
    }
    return max;
  }
}
