package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final double mean;
    private final double stddev;
    private final int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("row or col index i out of bounds");

        this.trials = trials;

        double[] experimentTresholds = new double[this.trials];

        for (int i = 0; i < this.trials; i++) {
            Percolation trial = new Percolation(n);

            while (!trial.percolates()) {
                int p = StdRandom.uniform(1, n + 1);
                int q = StdRandom.uniform(1, n + 1);
                trial.open(p, q);
            }

            experimentTresholds[i] = (double) trial.numberOfOpenSites() / (n * n);
        }


        this.mean = StdStats.mean(experimentTresholds);
        this.stddev = StdStats.stddev(experimentTresholds);

    } // perform trials independent experiments on an n-by-n grid

    public double mean() {
        return this.mean;
    }                 // sample mean of percolation threshold

    public double stddev() {
        return this.stddev;
    }                      // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return (this.mean() - ((CONFIDENCE_95 * this.stddev()) / Math.sqrt(this.trials)));
    }              // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return (this.mean() + ((CONFIDENCE_95 * this.stddev()) / Math.sqrt(this.trials)));
    }               // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats experiment = new PercolationStats(n, trials);

        System.out.printf("mean                       = %.10f \n", experiment.mean());
        System.out.printf("stddev                     = %.10f \n", experiment.stddev());
        System.out.printf("95%% confidence interval   = [%.10f, %.10f] \n", experiment.confidenceLo(), experiment.confidenceHi());

    }      // test client (described below)
}