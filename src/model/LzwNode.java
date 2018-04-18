package model;

import java.util.ArrayList;

public class LzwNode{

    private int index;
    private int symbol;
    private ArrayList<LzwNode> children = new ArrayList<LzwNode>();

    public  LzwNode(int index, int symbol){
        this.index = index;
        this.symbol = symbol;
    }

    public int getSymbol() { return symbol; }

    public int getIndex(){ return index; }

    /**
     * Realiza a busca do índice do símbolo passado como argumento, retornando o seu índice, caso exista,
     * caso contrário, retorna -1.
     *
     * @param searchSymbol
     * @return
     */
    public int findSymbolIndex(int searchSymbol) {
        if(this.symbol == searchSymbol)
            return index;
        else{

            for (LzwNode node : children){
                int symbolIndex = node.findSymbolIndex(searchSymbol);
                if(symbolIndex != -1)
                    return symbolIndex;
            }

            return -1;
        }
    }

    /**
     * Realiza a busca do nó pelo índice e retorna-o, caso exista, caso contrário, retorna null.
     *
     * @param searchIndex
     * @return
     */
    public LzwNode getNodeByIndex(int searchIndex){
        if(this.index == searchIndex)
            return this;
        else {
            for (LzwNode node : children){
                LzwNode indexedChild = node.getNodeByIndex(searchIndex);
                if(indexedChild != null)
                    return indexedChild;
            }

            return null;
        }
    }

    /**
     * Realiza a busca do nó pelo símbolo, retornando o nó correspondente, caso exista
     *
     * @param searchSymbol
     * @return
     */
    public LzwNode getNodeBySymbol(int searchSymbol){
        if(this.symbol == searchSymbol)
            return this;
        else {
            for (LzwNode node : children){
                LzwNode symbolNode = node.getNodeBySymbol(searchSymbol);
                if(symbolNode != null)
                    return symbolNode;
            }

            return null;
        }
    }

    public void addChild(int index, int symbol) {
        children.add(new LzwNode(index, symbol));
    }

    public void showChildren() {

    }
}
