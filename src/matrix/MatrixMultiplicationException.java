/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 *
 * @author Matthieu
 */
class MatrixMultiplicationException extends Exception {

    public MatrixMultiplicationException() {
    }
    
    @Override
    public String getMessage(){
        return "First matrix's columns must be the same number as second matrix's rows";
    }
}
