
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private int size;
    private boolean [][]table;
    private WeightedQuickUnionUF set; // 0 and size * size + 1 reserved for head and tail
    public Percolation(int n) {
        if (n <= 0)
            throw new java.lang.IllegalArgumentException("size n out of bounds");
        size = n;
        table = new boolean[size + 1][size + 1];
        set = new WeightedQuickUnionUF(size * size + 2);
        // create n-by-n grid, with all sites blocked, indix from 1..n,           
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                table[i][j] = false; // false means blocked, true means open
            }
        }
    }
   public void open(int i, int j){
       if (i < 1 || i > size || j < 1 || j > size)
           throw new java.lang.IndexOutOfBoundsException("indix i out of bound");
       if (table[i][j] != true){
           table[i][j] = true;// open site (row i, column j) if it is not open already
           if (i == 1) // up site is open, note open sites on the first column 
                                                  //always connected to head
               set.union(0, (i - 1) * size + j);
           else if (table[i - 1][j] == true)
               set.union((i - 2) * size + j, (i - 1) * size + j);
           if (i == size)
               set.union(size * size + 1, (i - 1) * size + j);
           else if (table[i + 1][j] == true) // site down is open
               set.union(i * size + j, (i - 1) * size + j);
           if (j > 1 && table[i][j - 1] == true) // site on the left is open
               set.union((i - 1) * size + j - 1, (i - 1) * size + j);
           if (j < size && table[i][j + 1] == true) // site on the right is open
               set.union((i - 1) * size + j + 1, (i - 1) * size + j);
       }
   }
   public boolean isOpen(int i, int j){
      if (i < 1 || i > size || j < 1 || j > size)
           throw new java.lang.IndexOutOfBoundsException("indix i out of bound");
       return table[i][j];// is site (row i, column j) open?
        
   }
   public boolean isFull(int i, int j){
       if (i < 1 || i > size || j < 1 || j > size)
           throw new java.lang.IndexOutOfBoundsException("indix i out of bound");
       return set.connected(0, (i - 1) * size + j);
       // is site (row i, column j) full?
   }
   public boolean percolates(){
       return set.connected(0, size * size + 1);// does the system percolate?
   }
   public static void main(String[] args){}  // test client (optional)
}
