/*
 * references: 
 * https://www.youtube.com/watch?v=UaLIHuR1t8Q
 * http://www.geeksforgeeks.org/red-black-tree-set-1-introduction-2/
 * http://www.geeksforgeeks.org/red-black-tree-set-2-insert/
 * https://en.wikipedia.org/wiki/Red–black_tree
 * 
 * */

import java.util.Optional;
public class RedBlack {
	 public enum Color {
	        RED,
	        BLACK
	    }

	    public static class Node {
	        String word;
	        String meaning;
	        Color color;
	        Node left;
	        Node right;
	        Node parent;
	        boolean isNullLeaf;
	    }

	    private static Node createBlackNode(String word,String mean) {
	        Node node = new Node();
	        node.word=word;
	        node.meaning=mean;
	        node.color = Color.BLACK;
	        node.left = createNullLeafNode(node);
	        node.right = createNullLeafNode(node);
	        return node;
	    }

	    private static Node createNullLeafNode(Node parent) {
	        Node leaf = new Node();
	        leaf.color = Color.BLACK;
	        leaf.isNullLeaf = true;
	        leaf.parent = parent;
	        return leaf;
	    }

	    private static Node createRedNode(Node parent, String word, String mean) {
	        Node node = new Node();
	        node.word=word;
	        node.meaning=mean;
	        node.color = Color.RED;
	        node.parent = parent;
	        node.left = createNullLeafNode(node);
	        node.right = createNullLeafNode(node);
	        return node;
	    }

	   //insert
	    public Node insert(Node root, String word,String mean) {
	        return insert(null, root, word,mean);
	    }
	    
	    private Optional<Node> findSiblingNode(Node root) {
	        Node parent = root.parent;
	        if(isLeftChild(root)) {
	            return Optional.ofNullable(parent.right.isNullLeaf ? null : parent.right);
	        } else {
	            return Optional.ofNullable(parent.left.isNullLeaf ? null : parent.left);
	        }
	    }
	    
	    //rightrotate
	    private void rightRotate(Node root, boolean changeColor) {
	        Node parent = root.parent;
	        root.parent = parent.parent;
	        if(parent.parent != null) {
	            if(parent.parent.right == parent) {
	                parent.parent.right = root;
	            } else {
	                parent.parent.left = root;
	            }
	        }
	        Node right = root.right;
	        root.right = parent;
	        parent.parent = root;
	        parent.left = right;
	        if(right != null) {
	            right.parent = parent;
	        }
	        if(changeColor) {
	            root.color = Color.BLACK;
	            parent.color = Color.RED;
	        }
	    }
	    
	    //leftrotate
	    private void leftRotate(Node root, boolean changeColor) {
	        Node parent = root.parent;
	        root.parent = parent.parent;
	        if(parent.parent != null) {
	            if(parent.parent.right == parent) {
	                parent.parent.right = root;
	            } else {
	                parent.parent.left = root;
	            }
	        }
	        Node left = root.left;
	        root.left = parent;
	        parent.parent = root;
	        parent.right = left;
	        if(left != null) {
	            left.parent = parent;
	        }
	        if(changeColor) {
	            root.color = Color.BLACK;
	            parent.color = Color.RED;
	        }
	    }
	    private boolean isLeftChild(Node root) {
	        Node parent = root.parent;
	        if(parent.left == root) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    private Node insert(Node parent, Node root, String word,String mean) {
	        if(root  == null || root.isNullLeaf) {
	            //if parent is not null means tree is not empty
	            //so create a red leaf node
	            if(parent != null) {
	                return createRedNode(parent, word,mean);
	            } else { 
	            	//otherwise create a black root node if tree is empty
	                return createBlackNode(word,mean);
	            }
	        }

	        //check for duplicate insertion;
	        if(root.word == word) {
	            throw new IllegalArgumentException("word already exists: " + word);
	        }
	        //if we go on left side then isLeft will be true
	        //if we go on right side then isLeft will be false.
	        boolean isLeft;
	        int comp=word.compareTo(root.word);
	        if(comp<0) {
	            Node left = insert(root, root.left, word,mean);
	            //if left becomes root parent means rotation
	            //happened at lower level. So just return left
	            //so that nodes at upper level can set their
	            //child correctly
	            if(left == root.parent) {
	                return left;
	            }
	            //set the left child returned to be left of root node
	            root.left = left;
	            //set isLeft to be true
	            isLeft = true;
	        } else {
	            Node right = insert(root, root.right, word,mean);
	            //if right becomes root parent means rotation
	            //happened at lower level. So just return right
	            //so that nodes at upper level can set their
	            //child correctly
	            if(right == root.parent) {
	                return right;
	            }
	            //set the right child returned to be right of root node
	            root.right = right;
	            //set isRight to be true
	            isLeft = false;
	        }

	        if(isLeft) {
	            //if we went to left side check to see Red-Red conflict
	            //between root and its left child
	            if(root.color == Color.RED && root.left.color == Color.RED) {
	                //get the sibling of root. It is returning optional means
	                //sibling could be empty
	                Optional<Node> sibling = findSiblingNode(root);
	                //if sibling is empty or of BLACK color
	                if(!sibling.isPresent() || sibling.get().color == Color.BLACK) {
	                    //check if root is left child of its parent
	                    if(isLeftChild(root)) {
	                        //this creates left left situation. So do one right rotate
	                        rightRotate(root, true);
	                    } else {
	                        //this creates left-right situation so do one right rotate followed
	                        //by left rotate

	                        //do right rotation without change in color. So sending false.
	                        //when right rotation is done root becomes right child of its left
	                        //child. So make root = root.parent because its left child before rotation
	                        //is new root of this subtree.
	                        rightRotate(root.left, false);
	                        //after rotation root should be root's parent
	                        root = root.parent;
	                        //then do left rotate with change of color
	                        leftRotate(root, true);
	                    }

	                } else {
	                    //we have sibling color as RED. So change color of root
	                    //and its sibling to Black. And then change color of their
	                    //parent to red if their parent is not a root.
	                    root.color = Color.BLACK;
	                    sibling.get().color = Color.BLACK;
	                    //if parent's parent is not null means parent is not root.
	                    //so change its color to RED.
	                    if(root.parent.parent != null) {
	                        root.parent.color = Color.RED;
	                    }
	                }
	            }
	        } else {
	            //this is mirror case of above. So same comments as above.
	            if(root.color == Color.RED && root.right.color == Color.RED) {
	                Optional<Node> sibling = findSiblingNode(root);
	                if(!sibling.isPresent() || sibling.get().color == Color.BLACK) {
	                    if(!isLeftChild(root)) {
	                        leftRotate(root, true);
	                    } else {
	                        leftRotate(root.right, false);
	                        root = root.parent;
	                        rightRotate(root, true);
	                    }
	                } else {
	                    root.color = Color.BLACK;
	                    sibling.get().color = Color.BLACK;
	                    if(root.parent.parent != null) {
	                        root.parent.color = Color.RED;
	                    }
	                }
	            }
	        }
	        return root;
	    }
	 
	    //search
	    public boolean rbSearch(Node root,String word){
	    	if(root.isNullLeaf==true){
				//System.out.println("in search");
				return root.isNullLeaf;
			}
	    	int r=0;
	    	if(root.isNullLeaf==false)
	    		r=word.compareTo(root.word);
	    	if(r==0){
				//System.out.println("word found");
				return root.isNullLeaf;
			}
			else if(r<0) {
				//System.out.println("inside left");
				return rbSearch(root.left,word);
			}
				
			else{
				//System.out.println("inside right");
				return rbSearch(root.right,word);
			}
	    }

}
