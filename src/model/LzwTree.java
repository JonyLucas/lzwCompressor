package model;

public class LzwTree {

    private int currentIndex;
    private int maxSize;
    private LzwNode root;



    public LzwTree(int maxSize){
        currentIndex = 0;
        this.maxSize = (maxSize > 256) ? maxSize : 256;
        root = new LzwNode(-1, -1);
        initializeSymbols();
    }

    public LzwTree(){
        this(10000);
    }

    /**
     * Inicializa o dicionário com o alfabeto de 256 bytes (codificação Ascii)
     */
    private void initializeSymbols(){
        for (int i = 0; i < 256; i++){
            root.addChild(i, i);
            currentIndex++;
        }
    }

    /**
     * Função que retorna o nó filho da raiz que possui o símbolo lido
     * @param symbol
     * @return
     */
    public LzwNode getNodeBySymbol(int symbol){
        for (LzwNode node : root.getChildren()){
            if(node.getSymbol() == symbol)
                return node;
        }

        return null;
    }

    public void addSymbol(LzwNode parent, int symbol){
        if(maxSize <= currentIndex) return;
        parent.addChild(currentIndex++, symbol);
    }

    public void showTree(){

    }

    public int getSize() {
        return currentIndex;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
