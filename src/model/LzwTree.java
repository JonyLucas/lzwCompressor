package model;

public class LzwTree {

    private int currentIndex;
    private int maxSize;
    private LzwNode root;

    public LzwTree(int maxSize){
        currentIndex = 0;
        this.maxSize = (maxSize > 256) ? maxSize : 256;
    }

    public LzwTree(){
        this(10000);
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
    public LzwNode getNodeBySymbol(int symbol){
        return root.getNodeBySymbol(symbol);
    }

    public void addSymbol(LzwNode parent, int symbol){
        parent.addChild(currentIndex++, symbol);
    }

}
