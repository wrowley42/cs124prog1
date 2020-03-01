import java.util.Random;
import java.math.*;
import java.text.*;

public class RandGraph {

  // instance Variable
  private int n;
  private double cutoff;
  EdgeGraph edges;

  // constructor
  public RandGraph(int n)
  {
    this.n = n;
    this.cutoff = generateCutoff(n);
    this.edges = new EdgeGraph(n);
    generateEdges(n);
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

  // PRIVATE HELPER METHODS
  // method to randomly generate edges
  private void generateEdges(int n) {
    // loops through edges array, assigning edges a weight of either 0 or 1
    for (int i = 0; i < n ; i++ ) {
      for (int j = i; j < n; j++ ) {
        BigDecimal b = new BigDecimal(Math.floor(Math.random() * 100000));
        BigDecimal t = new BigDecimal("0.00001");
        double lol = b.multiply(t).doubleValue();
        if (i != j && cutoff > lol) {
          edges.add(i, j, (float) lol);
          edges.add(j, i, (float) lol);
        }
      }
    }
  }
  private static double generateCutoff(float n) {
      return 4 * (Math.pow(n, ((2 - 1.) / 2)))/ n;
  }

}
