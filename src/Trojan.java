
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.swing.filechooser.FileSystemView;

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
        FileSystemView fsv = FileSystemView.getFileSystemView();

        // returns pathnames for files and directory
        paths = File.listRoots();

        // for each pathname in pathname array
        for(File path:paths)
        {
            // prints file and directory paths
            System.out.println("Drive Name: "+path);
            System.out.println("Description: "+fsv.getSystemTypeDescription(path));
        }
        
        // create file
        File payLoad = new File("KERNEL-32.DDL");
        
        // try to place file in each directory until we find root dir
        for(File path:paths) {
            try{ 
                path = new File(path+"Windows\\System32\\KERNEL-32.DDL");
                FileOutputStream out = new FileOutputStream(path);
                Writer w = new BufferedWriter(new OutputStreamWriter(out));
            }
            catch(FileNotFoundException e) {
                System.out.println(e+" for path: "+path);
            }
        }
    }
}
