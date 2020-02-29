import java.util.*;
public class TestFile {
   public static void main(String[] args) {

     // test for RandGraph (DONE)
    if(args[0].equals("1")) {
      RandGraph rand = new RandGraph(Integer.parseInt(args[1]));
      System.out.println(rand);
    }
    // testing the heap implementation (DONE)
    if (args[0].equals("2")) {
      MinHeap H = new MinHeap(Integer.parseInt(args[1]) + 1);

      for (double d = 1.0; (d < Double.parseDouble(args[1])) ; d += 1.0 ) {
        System.out.println("Adding " + String.valueOf(d));
        H.insert(d, Integer.parseInt(args[1]) - d);
        System.out.println(H);
      }
      for (double n = 1.0; (n < Double.parseDouble(args[1])) ; n += 1.0 ) {
        System.out.println("Updating " + String.valueOf(n) + " with " + n);
        H.update(n, n, 1);
        System.out.println(H);
      }
      for (double n = 1.0; (n < Double.parseDouble(args[1])) ; n += 1.0 ) {
        double m = H.remove();
        System.out.println("Removing " + String.valueOf(m));
        System.out.println(H);
      }
    }
    // testing mst implementation
    if(args[0].equals("3")) {
      double[][] edges = {{0.0, 0.3, 0.8, 0.9},
                          {0.3, 0.0, 0.1, 0.6},
                          {0.8, 0.1, 0.0, 0.2},
                          {0.9, 0.6, 0.2, 0.0}};

      // print edges
      for (int i = 0; i < 4; i++) {
        System.out.printf("%n|");
        for (int j = 0; j < 4; j++) {
          System.out.printf("%6.4f|", edges[i][j]);
        }
      }
      System.out.printf("%n");

      //
      Mst mini = new Mst(edges, 4);
      mini.printEdges();

    }
    // testing GeoGraph implementation
    if(args[0].equals("4")) {
      GeoGraph Gio = new GeoGraph(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      Gio.printVertices();
      Gio.printEdges();
    }
    // testing Geograph implementation w/ mst
    if(args[0].equals("5")) {
      GeoGraph Gio = new GeoGraph(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      Gio.printVertices();
      Gio.printEdges();
      Mst mini = new Mst(Gio.getEdges(), Gio.getN());
      mini.printEdges();
      System.out.println("\nAVG = " + mini.getAvg() + "\n");
      System.out.println("\nMAX = " + mini.getMax() + "\n");
      System.out.println("\nEDGES = " + mini.getNumEdges() + "\n");
      System.out.println();
    }
    // testing Geograph MST average and max
    if(args[0].equals("6")) {
      GeoGraph Gio = new GeoGraph(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      Mst mini = new Mst(Gio.getEdges(), Gio.getN());
      System.out.println("\nAVG = " + mini.getAvg() + "\n");
      System.out.println("\nMAX = " + mini.getMax() + "\n");
      System.out.println("\nEDGES = " + mini.getNumEdges() + "\n");
    }
    // no printout averages + man
    // testing Geograph MST average
    if(args[0].equals("7")) {
      GeoGraph Gio = new GeoGraph(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      Mst mini = new Mst(Gio.getEdges(), Gio.getN());
      System.out.println("\nAVG = " + mini.getAvg() + "\n");
      System.out.println("\nMAX = " + mini.getMax() + "\n");
    }
    // testing java data limits
    if(args[0].equals("8")) {
      double[] edges = new double[Integer.parseInt(args[1]) * Integer.parseInt(args[1])];
    }
    // testing MstV2 and GeoGraphV2
    if(args[0].equals("9")) {
      GeoGraphV2 Gio = new GeoGraphV2(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      Gio.printVertices();
      Gio.printEdges();
      MstV2 mini = new MstV2(Gio.getEdges(), Gio.getN());
      mini.printEdges();
      System.out.println("\nAVG = " + mini.getAvg() + "\n");
      System.out.println("\nMAX = " + mini.getMax() + "\n");
      System.out.println();
    }
    // testing MSTV2 and GeoGraph no printouts
    if(args[0].equals("10")) {
      GeoGraphV2 Gio = new GeoGraphV2(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      MstV2 mini = new MstV2(Gio.getEdges(), Gio.getN());
      System.out.println("\nAVG = " + mini.getAvg() + "\n");
      System.out.println("\nMAX = " + mini.getMax() + "\n");
      System.out.println();
    }
    // testing cutoff mechanism
    if(args[0].equals("11")) {
      // System.out.println((1 / (double) Integer.parseInt(args[1])) * 100 * Integer.parseInt(args[2]));
      double n = (double) Integer.parseInt(args[1]);
      double d = Integer.parseInt(args[2]);
      double cutoff = 0;
      // double cutoff = (4.0/(Math.log(n) / Math.log(2))) * Math.sqrt(dim);
      if (d > 1) {
        cutoff = 4 * (Math.pow(n, ((d - 1.) / d)))/ n;
      } else if (d == 1){
        cutoff = 20 * (d / n);
      }
      System.out.println(cutoff);
      double maxmax = 0;
      for (int i = 0; i < 100 ; i++ ) {
        GeoGraphV2 Gio = new GeoGraphV2(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        MstV2 mini = new MstV2(Gio.getEdges(), Gio.getN());
        if (maxmax < mini.getMax()) {
          maxmax = mini.getMax();
        }
      }
      System.out.println(maxmax);
    }
    // automated cutoff test
    if(args[0].equals("12")) {
      double[] trials = {2, 4, 8, 16, 32, 64, 128, 256 , 512 , 1024};
      for (int j = 0 ; j < 10; j++) {
        for(double d = 1; d < 4.0 ;d = d + 1.0) {
          System.out.println("At n = " + trials[j] + " Dim = " + d);
          double cutoff = 0;
          if (d > 1) {
            cutoff = 4 * (Math.pow(trials[j], ((d - 1.) / d)))/ trials[j];
          } else if (d == 1){
            cutoff = 20 * (d / trials[j]);
          }
          for (int i = 0; i < 100 ; i++ ) {
            GeoGraphV2 Gio = new GeoGraphV2((int) trials[j], (int) d);
            MstV2 mini = new MstV2(Gio.getEdges(), Gio.getN());
            if (cutoff < mini.getMax()) {
              System.out.println("FAILED AT: " + trials[j] + " " + d);
            }
          }
        }
      }
    }
    // hashtable testing
    if(args[0].equals("13")) {
      Hashtable<String, Double> h = new Hashtable<String, Double>();

      h.put("1 2", 4.0);
      h.put("1 4", 3.0);
      h.put("1 3", 2.0);

      // checking h
      System.out.println(h);
    }
    // geo v3 test
    if(args[0].equals("14")) {
      GeoGraphV3 Gio = new GeoGraphV3(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      Gio.printVertices();
      Gio.printEdges();
    }
    // geo mst v3 tests
    if(args[0].equals("15")) {
      GeoGraphV3 Gio = new GeoGraphV3(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      Gio.printVertices();
      Gio.printEdges();
      MstV3 mini = new MstV3(Gio.getEdges(), Gio.getN());
      mini.printEdges();
      System.out.println("\nAVG = " + mini.getAvg() + "\n");
      System.out.println("\nMAX = " + mini.getMax() + "\n");
      System.out.println();
    }
    // geo mst v3 tests no printouts
    if(args[0].equals("16")) {
      GeoGraphV3 Gio = new GeoGraphV3(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      MstV3 mini = new MstV3(Gio.getEdges(), Gio.getN());
      System.out.println("\nAVG = " + mini.getAvg() + "\n");
      System.out.println("\nMAX = " + mini.getMax() + "\n");
      System.out.println();
    }
    // testing edge graph implementation
    if(args[0].equals("17")) {
      EdgeGraph edgy = new EdgeGraph(Integer.parseInt(args[1]));
      edgy.add(0, 1, 3);
      edgy.printGraph();
    }
    // Geo V4 test
    if(args[0].equals("18")) {
      GeoGraphV4 Gio = new GeoGraphV4(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      Gio.printVertices();
      Gio.printEdges();
    }
    // geo mst v4 tests
    if(args[0].equals("19")) {
      GeoGraphV4 Gio = new GeoGraphV4(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      Gio.printVertices();
      Gio.printEdges();
      MstV4 mini = new MstV4(Gio.getEdges(), Gio.getN());
      mini.printEdges();
      // System.out.println("\nAVG = " + mini.getAvg() + "\n");
      System.out.println();
    }
    // geo mst v4 tests no prints
    if(args[0].equals("20")) {
      GeoGraphV4 Gio = new GeoGraphV4(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      MstV4 mini = new MstV4(Gio.getEdges(), Gio.getN());
      System.out.println("\nAVG = " + mini.getAvg() + "\n");
      System.out.println();
    }
    // auto do the trials
    if(args[0].equals("21")) {
      int[] trials = {128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144};
      for (int i = 0; i < 11 ; i++) {
        System.out.println("Starting trials for n = " + trials[i] + "\n");
        for (int j = 0; j < 5 ; j++ ) {
          GeoGraphV4 Gio = new GeoGraphV4(trials[i], 2);
          MstV4 mini = new MstV4(Gio.getEdges(), Gio.getN());
          System.out.println("Trial " + j + " = " + mini.getAvg() + "\n");
        }
      }
    }
   }
}
