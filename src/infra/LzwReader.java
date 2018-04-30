package infra;

import java.io.*;

public class LzwReader {

    private DataInputStream reader;

    public LzwReader(String filePath) throws Exception {
        try {
            reader = new DataInputStream(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            throw new Exception("Arquivo não encontrado");
        }
    }

    public int nextByte()
    {
        int b = -1;
        try {
            b = reader.readUnsignedByte();
            //System.out.println("Byte: " + b);
        } catch (EOFException eof){
            close();
            b = -1;
        }
        catch (IOException e) {
            close();
            e.printStackTrace();
        }

        //System.out.println("Byte: " + b);
        return b;
    }

    private void close()
    {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
