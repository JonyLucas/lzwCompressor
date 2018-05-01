package infra;

import java.io.*;

public class LzwWriter
{
    private DataOutputStream writer;

    private int[] bufferWriter = new int[8];
    private int indexBuffer = 7;

    public LzwWriter(String filePath) throws Exception {
        try {
            writer = new DataOutputStream(new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            throw new Exception("Arquivo não encontrado");
        }
    }

    private void flush()
    {
        try {
            int b = 0;
            for(int i = 7; i >= 0;  i--)
            {
                b += bufferWriter[i]*Math.pow(2, i);
            }
            writer.write(b);

            indexBuffer = 7;
        }
        catch (IOException e) {
            close();
            e.printStackTrace();
        }
    }

    public void write(int index, int nBits)
    {

        for(int i = nBits-1; i >= 0; i--)
        {
            bufferWriter[indexBuffer--] = getBit(index, i);

            if(indexBuffer < 0) flush();

        }
    }

    public void writeDictionarySize(int size)
    {
        try {
            String header = "" + size + '\n';
            writer.writeBytes(header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getBit(int n, int k) {
        return (n >> k) & 1;
    }

    private void close()
    {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLastChar(int index, int nBits)
    {
        for(int i = nBits-1; i >= 0; i--)
        {
            bufferWriter[indexBuffer--] = getBit(index, i);

            if(indexBuffer < 0) flush();

        }

        if(indexBuffer != 7)
        {
            for(; indexBuffer >= 0; indexBuffer--) bufferWriter[indexBuffer] = 0;
            flush();
        }

    }
}
