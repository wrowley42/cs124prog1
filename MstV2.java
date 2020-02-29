import java.util.*;

// MST that throws away some edges on calculations
// Also works with floats rather than doubles

public class MstV2 {

  // instance variables
  float[][] edges;
  int n;
  float avg;
  float max;


  // class constructor
  public MstV2(float[][] e, int n)
  {
    this.edges = prims(e, n);
    this.n = n;
    this.avg = averageWeight(edges);
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
        System.out.printf("%6.4f|", edges[i][j]);
      }
    }
    System.out.printf("%n");
  }
  // get edges
  public float[][] getEdges() {
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
  private static float[][] prims(float[][] e, int n) {

    //variable instantiation
    int v;
    int w;
    float[][] mst = new float[n][n];
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
        if (e[v][w] != 0.0 && !S[w]) {
          if (dist[w] > e[v][w]) {
            dist[w] = e[v][w];
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
      mst[i][prev[i]] = e[i][prev[i]];
      mst[prev[i]][i] = e[i][prev[i]];
    }
    return mst;
  }
  // computes average weight of the edges in the MST
  private float averageWeight(float[][] e) {
    float average = 0;
    float k = 0;
    for (int i = 0; i < n ; i++ ) {
      for (int j = i; j < n; j++ ) {
        if (i != j && edges[i][j] != 0.0) {
          average = average + edges[i][j];
          k = k + 1.0f;
        }
      }
    }
    return average / k;
  }
  // compute maximum weight of edges in the mst
  private float genMax(float[][] e) {
    float max = 0;
    for (int i = 0; i < n ; i++ ) {
      for (int j = i; j < n; j++ ) {
        if (e[i][j] > max) {
          max = e[i][j];
        }
      }
    }
    return max;
  }
}
