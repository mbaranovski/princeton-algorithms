package collinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;
    private ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<>();

    /*
     That examines 4 points at a time and checks whether they all
     lie on the same line segment, returning all such line segments.
     To check whether the 4 points p, q, r, and s are collinear,
     check whether the three slopes between p and q, between p and r,
     and between p and s are all equal.
     */
    public BruteCollinearPoints(Point[] points) {

        Arrays.sort(points);

        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                double p_q = points[p].slopeTo(points[q]);
                for (int r = q + 1; r < points.length - 1; r++) {
                    double p_r = points[p].slopeTo(points[r]);
                   // if (p_q != p_r) break;

                    for (int s = r + 1; s < points.length; s++) {
                        double p_s = points[p].slopeTo(points[s]);
                        if (p_q == p_r && p_q == p_s)
                            lineSegmentArrayList.add(new LineSegment(points[p], points[s]));
                    }

                }

            }

        }

        lineSegments = new LineSegment[lineSegmentArrayList.size()];
        for (int i = 0; i < lineSegmentArrayList.size(); i++) {
            lineSegments[i] = lineSegmentArrayList.get(i);
        }

    }   // finds all line segments containing 4 points

    public int numberOfSegments() {
        return this.lineSegments.length;
    }      // the number of line segments

    public LineSegment[] segments() {
        return lineSegments;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
