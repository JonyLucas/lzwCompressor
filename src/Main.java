
import controller.LzwCompressor;
import infra.LzwReader;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o caminho do arquivo");
        String filePath = scan.nextLine();

        System.out.println("Digite o tamanho da árvore");
        int size = scan.nextInt();

        try {
            LzwCompressor.createDictionary(size, true);
            LzwCompressor.readAndSearch(new LzwReader(filePath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
