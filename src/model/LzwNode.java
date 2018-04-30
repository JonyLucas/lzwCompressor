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

    public ArrayList<LzwNode> getChildren() { return children; }

    /**
     * Realiza a busca do índice do símbolo passado como argumento, retornando o seu índice, caso exista,
     * caso contrário, retorna -1.
     *
     * @param searchSymbol
     * @return
     */
    public int findSymbolIndex(int searchSymbol) {
        for (LzwNode node : children){
            if(node.getSymbol() == searchSymbol)
                return node.getIndex();
        }

        return -1;
    }

    /**
     * Realiza a busca do nó pelo símbolo, retornando o nó correspondente, caso exista
     *
     * @param searchSymbol
     * @return
     */
    public LzwNode getNodeBySymbol(int searchSymbol){
        for (LzwNode node : children){
            if(node.getSymbol() == searchSymbol)
                return node;
        }

        return null;

    }

    public void addChild(int index, int symbol) {
        children.add(new LzwNode(index, symbol));
    }

    public String getDescription() {

        String description = "Parent: Index[" + this.index + "] Symbol[" + this.symbol + "]\n";
        for (LzwNode node : children){
            description += "\tChild: Index[" + node.getIndex() + "] Symbol[" + node.getSymbol() + "]\n";
        }

        return description;

    }
}
