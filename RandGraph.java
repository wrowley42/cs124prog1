import java.util.Random;
import java.math.*;
import java.text.*;

public class RandGraph {

  // instance Variable
  private int n;
  private double[][] edges;

  // constructor
  public RandGraph(int n)
  {
    this.n = n;
    this.edges = generateEdges(n);
  }

  // PUBLIC METHODS
  // print method
  public void printEdges()
  {
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
  public double[][] getEdges() {
    return edges;
  }
  // get n
  public int getN() {
    return n;
  }

  // PRIVATE HELPER METHODS
  // method to randomly generate edges
  private static double[][] generateEdges(int n) {

    // variable instantion
    double[][] edges = new double[n][n];

    // loops through edges array, assigning edges a weight of either 0 or 1
    for (int i = 0; i < n ; i++ ) {
      for (int j = i; j < n; j++ ) {
        if (i != j) {
          BigDecimal b = new BigDecimal(Math.floor(Math.random() * 100000));
          BigDecimal t = new BigDecimal("0.00001");
          edges[i][j] = edges[j][i] =  b.multiply(t).doubleValue();
        } else {
          edges[i][j] = edges[j][i] =  0.0;
        }
      }
    }
    return edges;
  }
}
