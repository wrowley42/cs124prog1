import java.util.Random;
import java.math.*;
import java.text.*;

public class GeoGraph {

  // global final variable
  public static final char[] DIMENSIONS = {'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f'};
  // instance variables
  private int n;
  private int dim;
  private double[][] edges;
  // an n by dim array
  private double[][] vertices;

  // constructor
  public GeoGraph(int n, int dim) {
    this.n = n;
    this.dim = dim;
    this.vertices = generateVertices(n, dim);
    this.edges = generateEdges(this.vertices, n, dim);
  }

  // PUBLIC METHODS
  // print the edges
  public void printEdges()
  {
    System.out.println();
    for (int i = 0; i < n; i++) {
      if (i == n / 2) {
        System.out.printf("GEO EDGES  ", i);
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
  public double[][] getEdges() {
    return edges;
  }
  // get n
  public int getN() {
    return n;
  }

  // PRIVATE HELPER METHODS
  // method to generate vertices
  private static double[][] generateVertices(int n, int dim) {
    double[][] vertices = new double[n][dim];
    for (int i = 0; i < n ; i++ ) {
      for (int d = 0; d < dim ; d++) {
        BigDecimal b = new BigDecimal(Math.floor(Math.random() * 100000));
        BigDecimal t = new BigDecimal("0.00001");
        vertices[i][d] = b.multiply(t).doubleValue();
      }
    }
    return vertices;
  }
  // method to generate edges from given vertices
  private static double[][] generateEdges(double[][] vertices, int n, int dim) {
    // variable instantion
    double[][] edges = new double[n][n];

    // loops through edges array, assigning edges a weight of either 0 or 1
    for (int i = 0; i < n ; i++ ) {
      for (int j = i; j < n; j++ ) {
        if (i != j) {
          edges[i][j] = edges[j][i] = euclideanDist(dim, vertices[i], vertices[j]);
        } else {
          edges[i][j] = edges[j][i] =  0.0;
        }
      }
    }
    return edges;
  }
  // method to calculate euclidean distance given two points
  private static double euclideanDist(int dim, double[] v, double[] w) {
    double dist = 0.0;
    for (int i = 0; i < dim ; i++) {
      dist = dist + ((v[i] - w[i]) * (v[i] - w[i]));
    }
    return Math.sqrt(dist);
  }

}
