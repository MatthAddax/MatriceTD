/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.logging.Level;
import java.util.logging.Logger;
import rational.Rational;

/**
 *
 * @author Matthieu
 */
public class Matrix {
    private Rational[][] matrix;
    private int columns;
    private int rows;

    public Matrix(Rational[][] matrix) throws ColumnsException, RowsException{
        this.matrix = matrix;
        setRows(matrix[0].length);
        setColumns(matrix.length);
    }
    public Matrix(int squareSize) throws ColumnsException, RowsException{
        this(squareSize, squareSize);
    }
    public Matrix(int columns, int rows) throws ColumnsException, RowsException {
        setRows(rows);
        setColumns(columns);
        matrix = new Rational[this.columns][this.rows];
    }
    
    public boolean isMatrixNull(){
        boolean isNull = false;
        int row;
        int column = 0;
        int numberOfZeroes = 0;
        /*Check if all columns are null or not*/
        while(column < columns && isNull == false){
            row = 0;
            numberOfZeroes = 0;
            while(row < rows){
                if(matrix[column][row] == 0 || matrix[column][row] == null){
                    numberOfZeroes++;
                }
                row++;
            }
            if(numberOfZeroes >= rows){
                isNull = true;
            }
            column++;
        }
        
        row = 0;
        
        /*Check if rows are null*/
        while(row < rows && isNull == false){
            column = 0;
            numberOfZeroes = 0;
            while(column < columns){
                if(matrix[column][row] == 0 || matrix[column][row] == null){
                    numberOfZeroes++;
                }
                column++;
            }
            if(numberOfZeroes >= columns){
                isNull = true;
            }
            row++;
        }
        
        return isNull;
    }
    
    public double determinant(){
        System.out.println("Matrice : \n" + this + "\n");
        Matrix tempMatrix;
        Rational[][] pivot;
        Rational[][] temp;
        double factor = 1;
        double determinant = 0;
        
        boolean isNull = isMatrixNull();
        boolean isSquare = isMatrixSquare();
        if(isNull|| !isSquare)
            return 0;
        
        if(columns > 2){
            if(matrix[1][1] == 0){
                pivot = permuteFirstNonNullColmuns();
            }
            pivot = matrix;
            System.out.println("Premier nombre doit être non null : \n" + pivot + "\n");
            int row = 0;
            double divider = pivot[0][0];
            factor*= divider;
            
            while(row < rows){
                pivot[0][row] /= divider;
                row++;
            }

            
            int column = 1;
            while(column < columns){
                divider = pivot[column][0];
                row = 0;
                while(row < rows){
                    pivot[column][row] -= pivot[column][row]/divider;
                    row++;
                }
                column++;
            }
            
            System.out.println("Première ligne doit être 1 puis 0 : \n" + this + "\n");
            
            column = 1;
            temp = new Rational[columns-1][rows-1];
            while(column < columns){
                row = 1;
                while(row < rows){
                    temp[column-1][row-1] = matrix[column][row];
                    row++;
                }
                column++;
            }
            try {
                tempMatrix = new Matrix(temp);
                determinant = tempMatrix.determinant();
                System.out.println("Temp det : " + determinant + "\n");
            } catch (ColumnsException | RowsException ex) {
                Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            determinant = (matrix[0][0] * matrix[1][1]) + (matrix[1][0] * matrix[0][1]);
        }
        
        return factor*determinant;        
    }
    
    private void setColumns(int columns) throws ColumnsException{
        if(columns < 2){
            throw new ColumnsException();
        }
        this.columns = columns;
    }
    
    private void setRows(int rows) throws RowsException{
        if(rows < 2){
            throw new RowsException();
        }
        this.rows = rows;
    }

    public boolean isMatrixSquare() {
        return columns == rows;
    }

    private Rational[][] permuteFirstNonNullColmuns() {
        Rational[][] tempMatrix = matrix;
        int column = 0;
        while(column < columns && matrix[column][0] == 0){
            column ++;
        }
        
        Rational[] tempColumn = tempMatrix[column];
        tempMatrix[column] = tempMatrix[0];
        tempMatrix[0] = tempColumn;
        
        return tempMatrix;
    }
    @Override
    public String toString(){
        String result = "";
        int row = 0;
        int column = 0;
        
        while(column < columns){
            row = 0;
            while(row < rows){
                double value = matrix[column][row];
                result+= value + " ";
                row++;
            }
            result+= "\n";
            column++;
        }
        
        return result;
        
    }
    
    public Matrix translation(){
        Rational[][] translated = new Rational[rows][columns];
        int row;
        int column = 0;
        while(column < columns){
            row = 0;
            while(row < rows){
                translated[row][column] = matrix[column][row];
                row++;
            }
            column++;
        }
        
        try {
            return new Matrix(translated);
        } catch (ColumnsException | RowsException ex) {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Matrix cofactor(){
        Rational[][] cofactor = matrix;
        int row;
        int column = 0;
        while(column < columns){
            row = 0;
            while(row < rows){
                cofactor[column][row] = Math.pow(-1, column+row) * matrix[column][row];
                row++;
            }
            column++;
        }
        try {
            return new Matrix(cofactor);
        } catch (ColumnsException | RowsException ex) {
            Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
            return  null;
        }
    }
    
    //public Matrix 
    
    public Matrix mulitplication(Matrix matrix) throws MatrixMultiplicationException{
        if(columns != matrix.getRows()){
            throw new MatrixMultiplicationException();
        }
        Rational[][] multiplicated = new Rational[columns][matrix.getRows()];
        return null;
    }
    public Matrix multiplication(double extern){
        int column = 0;
        int row;
        Rational[][] multiplicated = matrix;
        while(column < columns){
            row = 0;
            while(row < rows){
                multiplicated[column][row] += extern;
                row++;
            }
            column++;
        }
        return multiplicated;
        
    }
    public Matrix addition(Matrix matrix){
        return null;
    }
    public Matrix subtraction(Matrix matrix){
        return null;
    }
    
    public boolean isSameSize(Matrix matrix){
        return rows == matrix.getRows() && columns == matrix.getColumns();
    }

    public Rational[][] getMatrix() {
        return matrix;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
    
    
}
