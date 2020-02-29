// Min-heap implementation that takes in a vertex location and distance to source
// sorts by distance to source and returns vertex location

public class MinHeap {

  // variable instantiation

  double[][] Heap;
  int currentsize;
  int maxsize;

  // class constructor
  public MinHeap(int max)
  {
    this.maxsize = max;
    this.currentsize = 0;
    Heap = new double[max + 1][2];
    Heap[0][0] = 0.0;
    Heap[0][1] = 0.0;
  }

  // PUBLIC METHODS
  // toString
  public String toString(){

    String t = "Current Size = " + currentsize + "\n[";
    for (int i = 1; i < currentsize + 1; i++ ) {
      t = t.concat(Heap[i][0] + ", " + Heap[i][1] + "; ");
    }
    return t.concat("]");
  }
  // insert, update and remove functions
  public void insert(double d, double dist){
    currentsize = currentsize + 1;
    Heap[currentsize][0] = d;
    Heap[currentsize][1] = dist;
    int k = currentsize;
    // while a parent is still bigger than k, bubble up
    while (Heap[k][1] < Heap[parent(k)][1]) {
      swap(k, parent(k));
      k = parent(k);
    }
  }
  public boolean update(double d, double dist, int k){
    if (Heap[k][0] == d) {
      Heap[k][1] = dist;
      while (Heap[k][1] < Heap[parent(k)][1]) {
        swap(k, parent(k));
        k = parent(k);
      }
      return true;
    } else if(!leaf(k)) {
        if(update(d, dist, left(k))){
          return true;
        } else if(update(d, dist, right(k))) {
          return true;
        } else {
          return false;
        }
    } else {
        return false;
    }
  }
  public double remove() {
    double t = Heap[1][0];
    currentsize = currentsize - 1;
    Heap[1][0] = Heap[currentsize + 1][0];
    Heap[1][1] = Heap[currentsize + 1][1];
    heapify(1);
    return t;
  }
  // returns if the heap is empty or not
  public boolean isEmpty() {
    if (currentsize == 0) {
      return true;
    }
    return false;
  }

  // PRIVATE HELPER FUNCTIONS
  // returns parent index of element
  private int parent(int k) {
    return k / 2;
  }
  // returns left child index of an element
  private int left(int k) {
    return 2 * k;
  }
  // returns right child index of an element
  private int right(int k) {
    return (2 * k) + 1;
  }
  // returns true if an element has no children
  private boolean leaf(int k) {
    if (Double.valueOf(k) >= (Double.valueOf(currentsize + 1) / 2.0)) {
      return true;
    } else {
      return false;
    }
  }
  // swaps the position of two elements
  private void swap(int i, int j) {
    double t1 = Heap[i][0];
    double t2 = Heap[i][1];
    Heap[i][0] = Heap[j][0];
    Heap[i][1] = Heap[j][1];
    Heap[j][0] = t1;
    Heap[j][1] = t2;
  }
  // recursive function to bubble restore the heap structure from element k and below
  private void heapify(int k) {
    if (!leaf(k)) {
      // larger than both children
      if (Heap[left(k)][1] < Heap[k][1] && Heap[right(k)][1] < Heap[k][1]) {
        // left is smaller
        if (Heap[left(k)][1] < Heap[right(k)][1]) {
          swap(k, left(k));
          heapify(left(k));
        }
        // right is smaller
        else {
          swap(k, right(k));
          heapify(right(k));
        }
      }

      // larger than only left
      if (Heap[left(k)][1] < Heap[k][1]) {
        swap(k, left(k));
        heapify(left(k));
      }

      // larger than only right
      if (Heap[right(k)][1] < Heap[k][1]) {
        swap(k, right(k));
        heapify(right(k));
      }
    }
  }

}
