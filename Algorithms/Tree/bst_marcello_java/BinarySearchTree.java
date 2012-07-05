/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author Marcello
 */
public class BinarySearchTree {
    
    private BinaryNode head;
    private int size;
    private int height;
    private int nodeMaxHeight;
    

    public BinaryNode predecessor(int value) {
        BinaryNode bn = search(value);
        
        if(bn == null)
            return null;
        
        BinaryNode pred = bn.getLeft();
        if(pred == null)
            return null;
        else {
            while(pred.getRight() != null) {
                pred = pred.getRight();
            }
            return pred;
        }
        
    }
    
    public void add(int value) {
        
        if(head == null) {
            head = new BinaryNode(value);
            size = 1;
            height = 1;
            nodeMaxHeight = 1;
            return;
        }
        
        BinaryNode bn = head;
        int newHeight = 1;
        
        while(true) {
            if(value < bn.getKey()) {
                if(bn.getLeft() == null) {
                    bn.setLeft(new BinaryNode(value));
                    size++;
                    newHeight++;
                    if(newHeight > height) {
                        height = newHeight;
                        nodeMaxHeight = 1;
                    }
                    if(newHeight == height) {
                        nodeMaxHeight++;
                    }
                    
                    return;
                }
                
                bn = bn.getLeft();
                newHeight++;
                
            } else {
                if(bn.getRight() == null) {
                    bn.setRight(new BinaryNode(value));
                    size++;
                    newHeight++;
                    
                    if(newHeight > height) {
                        height = newHeight;
                        nodeMaxHeight = 1;
                    }
                    if(newHeight == height) {
                        nodeMaxHeight++;
                    }
        
                    return;
                }
                
                bn = bn.getRight();
                newHeight++;
            }
        }
                
        
    }
    
    public BinaryNode search(int value) {
        
        return search(head, value);
                
    }
    
    private BinaryNode search(BinaryNode bn, int value) {

        if(bn == null || bn.getKey() == value)
            return bn;
        
        if(value < bn.getKey())
            return search(bn.getLeft(), value);
        else
            return search(bn.getRight(), value);
        
    }
    
    public void inOrderTraversal() {
        inOrderTraversal(head);
    }
    
    private void inOrderTraversal(BinaryNode bn) {
    
        if(bn != null) {
            
            inOrderTraversal(bn.getLeft());
            
            System.out.print(bn.getKey() + ",");
            
            inOrderTraversal(bn.getRight());
            
        }

    }
    
    public void preOrderTraversal() {

        preOrderTraversal(head);
        
    }
    
    private void preOrderTraversal(BinaryNode bn) {
        
        if(bn != null) {
            
            System.out.print(bn.getKey() + ",");

            inOrderTraversal(bn.getLeft());
                        
            inOrderTraversal(bn.getRight());
            
        }

    }
           
        
    public void postOrderTraversal() {
        
        postOrderTraversal(head);
        
    }
    
    private void postOrderTraversal(BinaryNode bn) {        
        
        if(bn != null) {
            
            inOrderTraversal(bn.getLeft());
                        
            inOrderTraversal(bn.getRight());
            
            System.out.print(bn.getKey() + ",");

        }
        
    }
    
    public static BinarySearchTree getInstance() {
               
       return new BinarySearchTree(){{
            add(7);
            add(5);
            add(2);
            add(6);
            add(8);
            add(9);
       }};
        
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" + "head=" + head + ", size=" + size + ", height=" + height + '}';
    }
    
    
    
    public static void main(String[] args) {

        BinarySearchTree bst = BinarySearchTree.getInstance();
        
        System.out.println(bst);

        System.out.print("\nSearch for 6=");
        System.out.print(bst.search(6));
        
        System.out.print("\nIn Order Traversal=");
        bst.inOrderTraversal();
        
        System.out.print("\nPre Order Traversal=");
        bst.preOrderTraversal();

        System.out.print("\nPost Order Traversal=");
        bst.postOrderTraversal();
        
        System.out.print("\nPredecessor=");
        System.out.println(bst.predecessor(7));
    
    }
    
}
