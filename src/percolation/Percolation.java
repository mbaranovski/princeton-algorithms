package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int topVirtualSite = 0;
    private final int bottomVirtualSite;
    private final int[][] matrix;
    private boolean[] openSites;
    private int openSitesCount = 0;
    private final WeightedQuickUnionUF uf;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        if (n < 1) throw new IllegalArgumentException("Grid must be at least 1-by-1.");
        uf = new WeightedQuickUnionUF(n * n + 2);

        this.matrix = new int[n + 1][n + 1]; // +1 in order to start counting rows and columns from 1.

        int site = 1;
        for (int i = 1; i < this.matrix.length; i++) {
            for (int j = 1; j < this.matrix[i].length; j++) {
                this.matrix[i][j] = site;
                site++;
            }
        }

        bottomVirtualSite = n * n + 1;
        this.initVirtualSites(n);
    }

    public void open(int row, int col) {  // open site (row, col) if it is not open already
        this.validateRowCol(row, col);

        if (!this.isOpen(row, col)) {
            this.findAndUnionSiteNeighbours(row, col);
            this.openSites[this.xyTo1D(row, col)] = true;
            this.openSitesCount += 1;
        }
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        this.validateRowCol(row, col);

        return this.openSites[this.xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {  // is site (row, col) full?
        this.validateRowCol(row, col);

        return this.uf.find(this.xyTo1D(row, col)) == this.topVirtualSite;
    }

    public int numberOfOpenSites() {  // number of open sites
        return this.openSitesCount;
    }

    public boolean percolates() { // does the system percolate?
        return this.uf.connected(this.topVirtualSite, this.bottomVirtualSite);
    }

    private void initVirtualSites(int n) {
        if (n < 1) throw new IllegalArgumentException("Grid must be at least 2-by-2.");

        this.openSites = new boolean[n * n + 2];
        this.openSites[this.topVirtualSite] = true;
        this.openSites[this.bottomVirtualSite] = true;
    }

    private void findAndUnionSiteNeighbours(int row, int col) {
        int openingSite = this.xyTo1D(row, col);

        if (row == 1) { // union top virtual site with site from the first row
            this.uf.union(this.topVirtualSite, openingSite);
        }

        if (row == this.matrix.length - 1) { // union bottom virtual site with site from the last row
            this.uf.union(this.bottomVirtualSite, openingSite);
        }

        if (col > 1 && this.isOpen(row, col - 1)) { // left neighbour
            this.uf.union(openingSite, this.xyTo1D(row, col - 1));
        }

        if (row > 1 && this.isOpen(row - 1, col)) { // top neighbour
            this.uf.union(openingSite, this.xyTo1D(row - 1, col));
        }

        if (col < (this.matrix.length - 1) && this.isOpen(row, col + 1)) { // right neighbour
            this.uf.union(openingSite, this.xyTo1D(row, col + 1));
        }

        if (row < (this.matrix.length - 1) && this.isOpen(row + 1, col)) { // bottom neighbour
            this.uf.union(openingSite, this.xyTo1D(row + 1, col));
        }
    }

    private int xyTo1D(int row, int col) {
        this.validateRowCol(row, col);

        return this.matrix[row][col];
    }

    private void validateRowCol(int row, int col) {
        if (row <= 0 || col <= 0 || row > this.matrix.length - 1 || col > this.matrix.length - 1)
            throw new IllegalArgumentException("row or col index i out of bounds");
    }

    public static void main(String[] args) { // test client (optional)

        Percolation experiment = new Percolation(3);

        experiment.open(1, 1);
        experiment.open(2, 1);
        experiment.open(3, 1);

        experiment.open(3, 3);

        System.out.println(experiment.numberOfOpenSites());
        System.out.println(experiment.isFull(3,3));

        System.out.println("percolates: " + experiment.percolates());
    }
}
