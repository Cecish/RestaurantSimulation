/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ase_cw.Models;

/**
 *
 * @author omiranda93
 */
public class WrongDimensionsBillException extends Exception{
     public WrongDimensionsBillException(){
        super("The names provided in the text files are too long and this is causing an error in the layout of the bill");
    }
}
