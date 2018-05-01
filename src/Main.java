
import controller.LzwCompressor;
import infra.LzwReader;
import infra.LzwWriter;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String fileInputPath = JOptionPane.showInputDialog("File Input Path:");
        String fileOutputPath = "src\\examples\\resultEncoder";

        try {
            LzwCompressor.createDictionary(1000, true);
            LzwCompressor.readAndSearch(new LzwReader(fileInputPath), new LzwWriter(fileOutputPath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
