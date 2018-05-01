package controller;

import infra.LzwReader;
import infra.LzwWriter;
import model.LzwNode;
import model.LzwTree;

public class LzwCompressor {

    private static LzwTree dictionary = null;
    private static LzwNode currentNode = null;

    private static int[] bufferWriter = new int[16];

    private static int nBits = 8;

    /**
     * Função que cria o dicionário, caso overwrite seja true
     * A função irá criar um novo dicionário, descartando o
     * dicionário anterior.
     *
     * @param size
     * @param overwrite
     */
    public static void createDictionary(int size, boolean overwrite){
        if(dictionary == null || overwrite){
            dictionary = new LzwTree(size);
        }
    }

    /**
     * Função que recolhe o símbolo lido na entrada e busca na árvore do dicionário,
     * Caso o símbolo lido não esteja presente no dicionário, acrescenta ao código
     * de saída o índice do nó atual e inicia a busca deste símbolo pela raiz da árvore.
     *
     * @param reader
     * @return
     */
    public static String readAndSearch(LzwReader reader, LzwWriter writer){

        String code = "";

        writer.writeDictionarySize(dictionary.getMaxSize());

        int currentSymbol = reader.nextByte();
        while (currentSymbol != -1){
            if(searchAndAddSymbol(currentSymbol)){
                currentSymbol = reader.nextByte();
                if (currentSymbol == -1)
                    writer.writeLastChar(currentNode.getIndex(), nBits);
            } else {
                writer.write(currentNode.getIndex(), nBits);
                if(Math.pow(2, nBits) <= dictionary.getSize()) nBits++;
                currentNode = null;
            }
        }
        return code;

    }

    /**
     * Função que busca o símbolo a partir do nó atual (ultimo nó percorrido),
     * caso o nó atual seja null, inicia a busca pela raiz da árvore dicionário,
     * caso o nó atual possua um filho com o símbolo procurado, este passa a ser
     * o nó atual e é retornado true (indicando que o símbolo está presente no dicionário),
     * caso o nó atual não possua filhos com o símbolo procurado, é adicionado um
     * novo filho a este nó com este símbolo e é retornado false (indicando o símbolo
     * não estava presente na árvore).
     *
     * @param symbol
     * @return
     */
    private static boolean searchAndAddSymbol(int symbol){

        if(currentNode == null) {
            currentNode = dictionary.getNodeBySymbol(symbol);
            //System.out.println(currentNode.getDescription());
            return true;
        } else if(currentNode.findSymbolIndex(symbol) != -1){
            currentNode = currentNode.getNodeBySymbol(symbol);
            //System.out.println(currentNode.getDescription());
            return true;
        } else {
            dictionary.addSymbol(currentNode, symbol);
            //System.out.println(currentNode.getDescription());
            return false;
        }

    }
}
