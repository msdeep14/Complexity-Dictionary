/*
 * avl tree implementation for dictionary;
 * 
 * references:
 * https://www.youtube.com/watch?v=rbg7Qf8GkQ4
 * http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * */

//avl node;
class avlnode{
		String word;
		String meaning;
		avlnode left;
		avlnode right;
		int height;
		public avlnode(){
			left=null;
			right=null;
			word="word";
			meaning="meaning";
			height=1;
		}
	}

public class Avl {
	
	/*
	private final avlnode root;
	public Avl(){
		root=new avlnode();
	}
	*/
	public static avlnode newnode(String word,String mean){
		avlnode node=new avlnode();
		//System.out.println("in avl word:: "+word);
		node.left=null;
		node.right=null;
		node.word=word;
		node.meaning=mean;
		node.height=1;
		return node;
	}
	
	 private int setHeight(avlnode root){
	        if(root == null){
	            return 0;
	        }
	        return 1 + Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0));
	    }
	    
	 //find height
	    private int height(avlnode root){
	        if(root == null){
	            return 0;
	        }else {
	            return root.height;
	        }
	    }
	    
	    //leftrotate
	 private avlnode rotateLeft(avlnode root){
	        avlnode newRoot = root.right;
	        root.right = root.right.left;
	        newRoot.left = root;
	        root.height = setHeight(root);
	        newRoot.height = setHeight(newRoot);
	        return newRoot;
	    }
	    
	 //rightrotate
	    private avlnode rotateRight(avlnode root){
	        avlnode newRoot = root.left;
	        root.left = root.left.right;
	        newRoot.right = root;
	        root.height = setHeight(root);
	        newRoot.height = setHeight(newRoot);
	        return newRoot;
	    }
	
	    //insert
	public avlnode AvlInsert(avlnode root,String word, String mean){
		if(root==null) return newnode(word,mean);
		int comp=word.compareTo(root.word);
		if(comp<=0)
			root.left=AvlInsert(root.left,word,mean);
		else
			root.right=AvlInsert(root.right,word,mean);
		int balance=height(root.left)-height(root.right);
		if(balance>1){
			if(height(root.left.left)>=height(root.left.right))
				return rotateRight(root);
			else{
				root.left=rotateLeft(root.left);
				return rotateRight(root);
			}
		}
		else if(balance<-1){
			if(height(root.right.right)>=height(root.right.left))
				return rotateLeft(root);
			else{
				root.right=rotateRight(root.right);
				return rotateLeft(root);
			}
		}
		 else{
	         root.height = setHeight(root);
	     }
		//root.height=1+Math.max(height(root.left),height(root.right));
		
		return root;
	}
	
	public avlnode AvlSearch(avlnode root,String word){
		if(root==null){
			//System.out.println("in search");
			return null;
		}
		int r=word.compareTo(root.word);
		if(r==0)
			return root;
		else if(r<0) {
			//System.out.println("inside left");
			return AvlSearch(root.left,word);
		}
			
		else{
			//System.out.println("inside right");
			return AvlSearch(root.right,word);
		}
	}
	/*
	public void inorder(avlnode root){
		if(root==null) return;
		inorder(root.left);
		System.out.println(root.word);
		inorder(root.right);
	}*/
}
