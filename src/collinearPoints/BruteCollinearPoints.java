package collinearPoints;
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

        for (int p = 0; p < pointsCopy.length - 3; p++) {
            for (int q = p + 1; q < pointsCopy.length - 2; q++) {
                double p_q = pointsCopy[p].slopeTo(pointsCopy[q]);
                for (int r = q + 1; r < pointsCopy.length - 1; r++) {
                    double p_r = pointsCopy[p].slopeTo(pointsCopy[r]);
                    if (p_q == p_r) {
                        for (int s = r + 1; s < pointsCopy.length; s++) {
                            double p_s = pointsCopy[p].slopeTo(pointsCopy[s]);
                            if (p_q == p_r && p_q == p_s)
                                lineSegmentArrayList.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }

            }

        }


    }   // finds all line segments containing 4 points

    public int numberOfSegments() {
        return lineSegmentArrayList.size();
    }      // the number of line segments

    public LineSegment[] segments() {
        lineSegments = new LineSegment[lineSegmentArrayList.size()];
        for (int i = 0; i < lineSegmentArrayList.size(); i++) {
            lineSegments[i] = lineSegmentArrayList.get(i);
        }

        return lineSegments;
    }

}
