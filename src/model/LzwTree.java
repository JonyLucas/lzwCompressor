package model;

public class LzwTree {

    private int currentIndex;
    private int maxSize;
    private LzwNode root;

    public LzwTree(int maxSize){
        currentIndex = 0;
        this.maxSize = maxSize;
    }

    /**
     * Inicializa o dicionário com o alfabeto de 256 bytes (codificação Ascii)
     */
    private void initialSymbols(){
        for (int i = 0; i < 256; i++){
            root.addChild(i, i);
            currentIndex++;
        }
    }

    /**
     * Função que indexa (codifica) o símbolo passado como argumento
     * @param symbol
     * @return
     */
    public int indexSymbol(int symbol){

//        return root.findSymbolIndex(symbol);
        return 0;
    }

}
