/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author MarcelloPro
 */
public class BinaryNode {

    private int key;
    private BinaryNode left;
    private BinaryNode right;


    public BinaryNode(int key) {
        this.key = key;
    }
    

    public BinaryNode(int key, BinaryNode left, BinaryNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
    
    
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryNode{" + "key=" + key + ", left=" + left + ", right=" + right + '}';
    }
    
    
    
}
