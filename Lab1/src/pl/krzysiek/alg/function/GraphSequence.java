package pl.krzysiek.alg.function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import pl.krzysiek.alg.exceptions.EmptyFileException;
import pl.krzysiek.alg.exceptions.SequenceNotGraphicException;

/**
 *
 * @author krzysiek
 */
public class GraphSequence {

    private static final String EMPTY_SEQUENCE = "No sequence is provided";
    private static final String SEQUENCE_NOT_EVEN = "Sum of the degrees is not even";
    private static final String DEGREE_EXCEEDED = "The degree is too big";
    private static final String SEQUENCE_NOT_GRAPHIC = "The provided sequence is not graphic";

    public static void checkGraphicSequence(String strFileName) {
        try {
            List<String> lst = GraphReader.readDataFromFile(strFileName);
            List<Integer> lstDegrees = getDegrees(lst);
            System.out.println(lstDegrees.toString());
            checkSum(lstDegrees);
            sortDesc(lstDegrees);
            int iSize = lstDegrees.size();
            for (int i = 0; i < iSize; i++) {
                int degree = getDegree(lstDegrees);
                lstDegrees.remove(0);
                deductOneFromSequence(lstDegrees, degree);
                if (containsZeroesOnly(lstDegrees)) {
                    break;
                }
                sortDesc(lstDegrees);
            }
            System.out.println( "Provided sequence is graphic");
        } catch (SequenceNotGraphicException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " - sequence file");
        }
    }

    private static List<Integer> getDegrees(List<String> lstSeq) throws Exception {
        List<Integer> lst = new ArrayList<>();
        for (String s : lstSeq) {
            for (String str : s.split("\\s")) {
                lst.add(Integer.valueOf(str));
            }
        }
        if (lst.isEmpty()) {
            throw new EmptyFileException(EMPTY_SEQUENCE);
        }

        return lst;
    }

    private static void checkSum(List<Integer> lst) throws Exception {
        int sum = 0;
        for (int i : lst) {
            sum += i;
        }

        if (sum % 2 != 0) {
            throw new SequenceNotGraphicException(SEQUENCE_NOT_EVEN);
        }
    }

    private static void sortDesc(List<Integer> lst) {
        lst.sort(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    private static int getDegree(List<Integer> lst) throws Exception {
        if (lst.get(0) > lst.size() - 1) {
            throw new Exception(DEGREE_EXCEEDED);
        }
        return lst.get(0);
    }

    private static void deductOneFromSequence(List<Integer> lst, int degree) throws SequenceNotGraphicException {
        for (int i = 0; i < degree; i++) {
            int value = lst.get(i);
            if (value == 0) {
                throw new SequenceNotGraphicException(SEQUENCE_NOT_GRAPHIC);
            }
            lst.set(i, lst.get(i) - 1);
        }
    }

    private static boolean containsZeroesOnly(List<Integer> lst) {
        for (int i : lst) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
