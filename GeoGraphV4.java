import java.util.Random;
import java.math.*;
import java.util.*;
import java.text.*;

// Improves on V2 by using a hash table to build edges
public class GeoGraphV4 {

  // global final variable
  public static final char[] DIMENSIONS = {'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f'};
  // instance variables
  private int n;
  private int dim;
  private double cutoff;
  EdgeGraph edges;
  // an n by dim array
  private float[][] vertices;

  // constructor
  public GeoGraphV4(int n, int dim) {
    this.n = n;
    this.dim = dim;
    this.cutoff = generateCutoff(n, dim);
    this.vertices = generateVertices(n, dim);
    this.edges = new EdgeGraph(n);
    // edge instantiation
    generateEdges(this.vertices, n, dim, cutoff);
  }

  // PUBLIC METHODS
  // print the edges
  public void printEdges()
  {
    edges.printGraph();
  }
  // print the vertices
  public void printVertices()
  {
    System.out.println();
    for (int i = 0; i < n; i++) {
      if (i == dim / 2) {
        System.out.printf("GEO VERTS  ", i);
      } else {
        System.out.printf("       ");
      }
    }
    System.out.printf("%n    |");
    for (int i = 0; (i < dim && i < 8); i++) {
      System.out.printf("   %c  |", DIMENSIONS[i]);
    }
    for (int i = 0; i < n; i++) {
      System.out.printf("%n%3d |", i);
      for (int j = 0; j < dim; j++) {
        System.out.printf("%6.4f|", vertices[i][j]);
      }
    }
    System.out.printf("%n");
  }
  // get edges
  public EdgeGraph getEdges() {
    return edges;
  }
  // get n
  public int getN() {
    return n;
  }

  // PRIVATE HELPER METHODS
  // method to generate vertices
  private static float[][] generateVertices(int n, int dim) {
    float[][] vertices = new float[n][dim];
    for (int i = 0; i < n ; i++ ) {
      for (int d = 0; d < dim ; d++) {
        BigDecimal b = new BigDecimal(Math.floor(Math.random() * 100000));
        BigDecimal t = new BigDecimal("0.00001");
        vertices[i][d] = b.multiply(t).floatValue();
      }
    }
    return vertices;
  }
  // method to generate edges from given vertices
  private void generateEdges(float[][] vertices, int n, int dim, double cutoff) {
    for (int i = 0; i < n ; i++ ) {
      for (int j = i; j < n; j++ ) {
        float t = euclideanDist(dim, vertices[i], vertices[j]);
        if (i != j && cutoff > t) {
          String key1 = i + " " + j;
          String key2 = j + " " + i;
          edges.add(i, j, t);
          edges.add(j, i, t);
        }
      }
    }
  }
  // generate cutoff
  private static double generateCutoff(float n, float dim) {
    if (dim > 1) {
      return 4 * (Math.pow(n, ((dim - 1.) / dim)))/ n;
    } else if (dim == 1){
      return 20 * (dim / n);
    }
    return 3.0;
  }
  // method to calculate euclidean distance given two points
  private static float euclideanDist(int dim, float[] v, float[] w) {
    float dist = 0;
    for (int i = 0; i < dim ; i++) {
      dist = dist + ((v[i] - w[i]) * (v[i] - w[i]));
    }
    return (float)Math.sqrt(dist);
  }

}
