/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkstream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natton
 */
public class CheckStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            File f = new File("abc.txt");
            writer = new PrintWriter(f);
            writer.write("abc");
        } catch (FileNotFoundException ex) {
        } finally {
            writer.close();
            writer.print("123");
            writer.close();
        }
    }

}
