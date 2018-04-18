package infra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader; //Provisorio
import java.io.IOException;

public class LzwReader {

    private BufferedReader reader;

    public LzwReader(String path) throws Exception {
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new Exception("Arquivo não encontrado");
        }
    }

    public int readByte(){
        try {
            return reader.read();
        } catch (IOException e) {
            return -1;
        }

    }

}
