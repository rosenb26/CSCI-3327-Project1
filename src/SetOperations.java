
import java.util.ArrayList;

public class SetOperations {

    /**
     * Find the (mathematical) union of two sets.
     * @param A the first set
     * @param B the second set
     * @return The (mathematical) union of A and B
     */
    public ArrayList<Integer> union (ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> aUnionB = new ArrayList<>();

        for (int x : A) {
            if (!aUnionB.contains(x)) {
                aUnionB.add(x);
            }
        }

        for (int x : B) {
            if (!aUnionB.contains(x)) {
                aUnionB.add(x);
            }
        }

        return aUnionB;
    }

    /**
     * Find the (mathematical) intersection of two sets.
     * @param A The first set.
     * @param B The second set.
     * @return The intersection of the two sets.
     */
    public ArrayList<Integer> intersection (ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> aIntersectB = new ArrayList<>();

        for (int x : A) {
            if (B.contains(x) && !aIntersectB.contains(x)) {
                aIntersectB.add(x);
            }
        }

        return aIntersectB;
    }

    /**
     * Find the (mathematical) complement of a set relative to the universal set.
     * @param universalSet The universal set.
     * @param subset The set whose complement is sought.
     * @return The complement of the subset relative to the universal set.
     */
    public ArrayList<Integer> complement (ArrayList<Integer> universalSet, ArrayList<Integer> subset) {
        ArrayList<Integer> complement = new ArrayList<>();

        for (int x : universalSet) {
            if (!subset.contains(x)) {
                complement.add(x);
            }
        }

        return complement;
    }

}
