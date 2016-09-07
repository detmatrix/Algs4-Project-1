
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private int trials;
    double[] result;
    public PercolationStats(int n, int trials)   {
        if (n <= 0 )
            throw new java.lang.IllegalArgumentException("size n out of bound");
        if (trials <= 0)
            throw new java.lang.IllegalArgumentException("trials out of bound");
        this.trials = trials;
        result = new double[trials];
          int count, idx = 0;
          double total = n * n;
          // perform trials independent experiments on an n-by-n grid
          for (int c = 0; c < trials; c++) {
              Percolation percolation = new Percolation(n); 
              count = 0;
              while (percolation.percolates() == false){
                   int random, i, j;
                   random = StdRandom.uniform(1, n * n);
                   i = random / n + 1;
                   j = random % n;
                   if (j == 0){
                       j = n;
                       i--;
                   }
                   if (percolation.isOpen(i, j) == false){
                       percolation.open(i, j);
                       count++;
                   }
          }
          result[idx++] = count / total;
       }
             
    }
   public double mean() { 
       return StdStats.mean(result);// sample mean of percolation threshold
   }
   public double stddev()     {
      return StdStats.stddev(result);
   }                   // sample standard deviation of percolation threshold
   public double confidenceLo()    {
     return this.mean() - 1.96 * this.stddev() / Math.sqrt(trials);
   }              // low  endpoint of 95% confidence interval
   public double confidenceHi()     {
       return this.mean() + 1.96 * this.stddev() / Math.sqrt(trials);
   }             // high endpoint of 95% confidence interval

   public static void main(String[] args) {
       int n = Integer.parseInt(args[0]), trial = Integer.parseInt(args[1]);// test client (described below)
       PercolationStats exp = new PercolationStats(n, trial);
       StdOut.printf("mean                    = %f\n", exp.mean());
       StdOut.printf("stddev                  = %f\n", exp.stddev());
       StdOut.printf("95%% confidence interval = %f, %f\n", exp.confidenceLo(), exp.confidenceHi());
   }
}
