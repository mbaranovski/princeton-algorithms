package collinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {


    private ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<>();


    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        // null checking every point
        for (Point p : points) {
            if (p == null) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);

        // uniques
        for (int i = 0; i < pointsCopy.length - 1; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        int pointsCount = pointsCopy.length;

        for (int i = 0; i < pointsCopy.length - 1; i++) {

            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());
            ArrayList<Point> collinearPoints = new ArrayList<>(pointsCount);

            for (int j = i + 1; j < pointsCopy.length; j++) {
                if (i == j) {
                    continue;
                }

                if (collinearPoints.isEmpty()) {
                    collinearPoints.add(pointsCopy[j]);
                } else if (pointsCopy[i].slopeTo(pointsCopy[j - 1]) == pointsCopy[i].slopeTo(pointsCopy[j])) {
                    collinearPoints.add(pointsCopy[j]);
                } else if (collinearPoints.size() > 2) {
                    lineSegmentArrayList.add(new LineSegment(pointsCopy[i], pointsCopy[j]));
                } else {
                    collinearPoints.clear();
                    collinearPoints.add(pointsCopy[j]);
                }
            }
        }

    }   // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return lineSegmentArrayList.size();
    }      // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[lineSegmentArrayList.size()];
        for (int i = 0; i < lineSegmentArrayList.size(); i++) {
            lineSegments[i] = lineSegmentArrayList.get(i);
        }
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
