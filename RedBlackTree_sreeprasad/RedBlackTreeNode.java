public class RedBlackTreeNode{

private int item;
private RedBlackTreeNode left;
private RedBlackTreeNode right;
private boolean Red=true;
private boolean Black=false;
private boolean color;
private int n;

public RedBlackTreeNode(int item,boolean color,int n){
	this.item=item;
 	this.color=color;
	this.n=n;
}

public void setNumberofNodes(int n){
	this.n=n;
}

public int getSize(RedBlackTreeNode root){
	return root.n;
}

public void setLeft(RedBlackTreeNode left)	{
	this.left=left;
}
public RedBlackTreeNode getRight()	{
	return right;
}
public void setRight(RedBlackTreeNode right)	{
	this.right=right;
}
public RedBlackTreeNode getLeft()	{
	return left;
}
public void setColor(boolean color){
	this.color=color;
}
public boolean getColor()	{
	return color;
}
public void setItem(int item){
	this.item=item;
}
public int getItem()	{
	return item;
}

}