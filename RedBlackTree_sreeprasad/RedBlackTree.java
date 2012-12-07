public class RedBlackTree{
	
	/* root for RedBlack tree*/
	public RedBlackTreeNode root;
	
	/* boolean values to represent RED and BLACK color */
	public boolean RED = true;
	public boolean BLACK =false;
	
	/* size to count the number of nodes in RedBlack tree */
	public int size;
	
	
	
	/* constructor 
		set root as null and size which is the total number of nodes in Red Black trees as zero.
	*/	
	public RedBlackTree(){
		root=null;
		size=0;
	}
	
	/* create redblack tree by creating nodes 
	   if root == null then its a new node; create a new RedBlackTreeNode with color RED
	   if item is less than root.value then recursively call function with root.left and item to be inserted
	   else if item is greater than root.value then recursively call function with root.right and item to be inserted.
	   else
			set root.item = item to be inserted
			if root.right is red and root.left is BLACK then rotate root to left
			if root.left and root.left.left is RED then rotate root to right
			if root.left and root.right is RED then flip colors of root, root.left, root.right
			set the number of nodes of root as number of nodes for root.left + number of nodes for root.right +1
	*/
	public RedBlackTreeNode createRedBlackTreeNode(RedBlackTreeNode root,int item){
		if(root ==null) return new RedBlackTreeNode(item,RED,1);
		if(item <root.getItem()){
			return createRedBlackTreeNode(root.getLeft(),item);
		}else if (item > root.getItem()){
			return createRedBlackTreeNode(root.getRight(),item);
		}else{
			root.setItem(item);
			
			if(isRed(root.getRight()) && !isRed(root.getLeft())){
					root = rotateLeft(root);
			}
			if(isRed(root.getLeft())  &&  isRed(root.getLeft().getLeft())){
				root = rotateRight(root);
			}
			if( isRed(root.getLeft()) && isRed(root.getRight())){
				root = flip(root);
			}
			
			root.setNumberofNodes(root.getSize(root.getLeft()) + root.getSize(root.getRight()) + 1);
			return root;
		}
	}
	
	/* check if root.color is RED 
		if root.color is RED return true else return false.
	*/
	public boolean isRed(RedBlackTreeNode root){
		if(root ==null) return false;
		return RED==root.getColor();
	}
	
	
 
	/* insert item 
		 increase number of nodes in RedBlack trees by 1 
		 return createRedblackTree( root, item)
	*/
	public RedBlackTreeNode insertItem(int item){
		size++;
		return createRedBlackTreeNode(getRoot(),item);
	}
	
	/* return root of RedBlack trees */
	public RedBlackTreeNode getRoot(){
		return root;
	}
	
	/*move red right 
	  flip the color of node
	  if node.left.left is red then rotate the node to right and flip color
	*/
	public RedBlackTreeNode moveRedRight(RedBlackTreeNode root){
		root=flip(root);
		if(isRed(root.getLeft().getLeft())){
			root=rotateRight(root);
			root=flip(root);
		}
		return root;
	}
	
	/* fixing up for deletion 
		 this is similar to insert code
		 if node.right is red then rotate left
		 if node.left and node.left.left is red then rotate right
		 if node.left is red and node.right is red then flip color
	*/
	public RedBlackTreeNode fixThis(RedBlackTreeNode root){
		
		if (isRed(root.getRight())){
			root= rotateLeft(root);
		}
		if(isRed(root.getLeft()) && isRed(root.getLeft().getLeft())){
			root=rotateRight(root);
		}
		
		if(isRed(root.getRight()) && isRed(root.getLeft())){
			root=flip(root);
		}
		return root;
	}
	
	
	/* flip node color
	   set the node color as compliment of current color
	   set node.left color as compliment of current node.left color
	   set node.right color as compliment of current node.right color	
	 */
	public RedBlackTreeNode flip(RedBlackTreeNode h){
		   h.setColor(!h.getColor());
		   h.getLeft().setColor(!h.getLeft().getColor());
		   h.getRight().setColor(!h.getRight().getColor());
		return h;
	}
	
	/* rotate node to left 
	  x = node.right
	  node.right = x.left
	  x.left = h
	  x.color= h.color
	  h.color =RED
	*/
	public RedBlackTreeNode rotateLeft(RedBlackTreeNode h){
		RedBlackTreeNode x = h.getRight();
		h.setRight(x.getLeft());
		x.setLeft(h);
		x.setColor(h.getColor());
		h.setColor(RED);
		return x;
	}
	
	/* rotate node to right 
	 x=h.left
	 h.left=x.right
	 x.color =h.color
	 h.color =RED
	*/
	public RedBlackTreeNode rotateRight(RedBlackTreeNode h){
	   RedBlackTreeNode x = h.getLeft();
	   h.setLeft(x.getRight());
	   x.setRight(h);
	   x.setColor(h.getColor());
	   h.setColor(RED);
	   return x;
	}
}