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
class RowsException extends Exception {

    public RowsException() {
    }
    
    @Override
    public String getMessage(){
        return "The number of rows must be higher than 1";
    }
}
