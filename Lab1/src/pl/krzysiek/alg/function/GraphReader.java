package pl.krzysiek.alg.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import pl.krzysiek.alg.data.FileInterface;
import pl.krzysiek.alg.exceptions.EmptyFileException;
import pl.krzysiek.alg.exceptions.InvalidMatrixException;

/**
 *
 * @author krzysiek
 */
public class GraphReader {

    private static final String ERROR_INVALID_FILE = "Error: Provided matrix is invalid. Empty matrix will be used. ";
    private static final String INFO_EMPTY_FILE = "Info: Provided file is empty ";
    
    static List<String> readDataFromFile( String strFileName ) throws Exception {
        List<String> lstData = new ArrayList<>();

        File file = new File(FileInterface.class.getResource(strFileName).getFile());
        try (InputStream is = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            for (String strLine; (strLine = br.readLine()) != null;) {
                lstData.add(strLine);
            }
        }
        if (lstData.isEmpty()) {
            throw new EmptyFileException(INFO_EMPTY_FILE);
        }

        return lstData;
    }

    private static List<List<Integer>> generateMatrix(List<String> lstFileData) {
        List<List<Integer>> lstMatrix = new ArrayList<>();

        for (String s : lstFileData) {
            List<Integer> lstRow = new ArrayList<>();
            for (String v : s.split("\\s")) {
                lstRow.add(Integer.valueOf(v));
            }
            lstMatrix.add(lstRow);
        }

        return lstMatrix;
    }

    private static void validateMatrix(List<List<Integer>> lstMatrix) throws Exception {
        if (lstMatrix.size() != lstMatrix.get(0).size()) {
            throw new InvalidMatrixException(ERROR_INVALID_FILE);
        }

        for (int i = 1; i < lstMatrix.size(); i++) {
            if (lstMatrix.get(i - 1).size() != lstMatrix.get(i).size()) {
                throw new InvalidMatrixException(ERROR_INVALID_FILE);
            }
        }
    }

    public static List<List<Integer>> loadGraphMatrix( ) {
        String strFileName = "graph.txt";
        try {
            List<List<Integer>>  lstMatrix = generateMatrix( readDataFromFile( strFileName ) );
            validateMatrix(lstMatrix);
            return lstMatrix;   
        } catch (EmptyFileException ex) {
            System.out.println(ex.getMessage());
        } catch (InvalidMatrixException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ERROR_INVALID_FILE);
        }
        return new ArrayList<>();
    }
}
