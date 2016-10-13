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
	            if(parent != null) {
	                return createRedNode(parent, word,mean);
	            } else { 
	                return createBlackNode(word,mean);
	            }
	        }

	        if(root.word == word) {
	            throw new IllegalArgumentException("word already exists: " + word);
	        }
	        boolean isLeft;
	        int comp=word.compareTo(root.word);
	        if(comp<0) {
	            Node left = insert(root, root.left, word,mean);
	            if(left == root.parent) {
	                return left;
	            }
	            root.left = left;
	            isLeft = true;
	        } else {
	            Node right = insert(root, root.right, word,mean);
	            if(right == root.parent) {
	                return right;
	            }
	            root.right = right;
	            isLeft = false;
	        }

	        if(isLeft) {
	            if(root.color == Color.RED && root.left.color == Color.RED) {
	                Optional<Node> sibling = findSiblingNode(root);
	                if(!sibling.isPresent() || sibling.get().color == Color.BLACK) {
	                    if(isLeftChild(root)) {
	                        rightRotate(root, true);
	                    } else {
	                        rightRotate(root.left, false);
	                        root = root.parent;
	                        leftRotate(root, true);
	                    }

	                } else {
	                    root.color = Color.BLACK;
	                    sibling.get().color = Color.BLACK;
	                    if(root.parent.parent != null) {
	                        root.parent.color = Color.RED;
	                    }
	                }
	            }
	        } else {
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
	 
	    //search inside red black tree
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
