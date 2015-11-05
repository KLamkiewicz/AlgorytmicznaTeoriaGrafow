/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atgt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author krzysiek
 */
public class Triangle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Integer[][] arr = getArr();
        Integer[][] arr2 = copy(arr);
        
        
        
        
        int multiplications = 2;

        for(int m=1; m<=multiplications; m++){
            arr2 = multiply(arr, arr2);
        };
     
        int iSum=0;
        for(int i=0; i<arr2.length; i++){
            iSum+=arr2[i][i];
        }
        System.out.println("Triangles count " + iSum/6);
    }
    
    public static Integer[][] multiply(Integer[][] arr, Integer[][] arr2){
        Integer[][] outArr = new Integer[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(outArr[i], 0);
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                for (int c = 0; c < arr.length; c++) {
                    outArr[i][j] += arr[i][c] * arr2[c][j];
                }
            }
        }
        
        return outArr;
    }
    
    public static Integer[][] copy(Integer[][] arr){
        Integer[][] copyArr = new Integer[arr.length][arr.length];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                copyArr[i][j]=arr[i][j];
            }
        }
        return copyArr;
    }
    
    public static void displayMatrix(Integer[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static Integer[][] getArr() throws Exception{
        ArrayList<List<Integer>> lst = new ArrayList<>();
        URL path = Triangle.class.getResource("graph.txt");
        File file = new File(path.getPath());
        try (InputStream is = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            for (String strLine; (strLine = br.readLine()) != null;) {
                List<String> elements = Arrays.asList(strLine.split("\\s"));
                List<Integer> row = new ArrayList<>();
                for(String el : elements ){
                    row.add(Integer.valueOf(el));
                }
                lst.add(row);
            }
        }
        Integer[][] arr = new Integer[lst.size()][lst.size()];
        for(int i=0; i<lst.size(); i++)
            arr[i] = lst.get(i).toArray(new Integer[lst.size()]);
            
        return arr;
    }
}
