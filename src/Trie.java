/*
 * trie implementation of dictionary
 * */
import java.util.HashMap;
import java.util.Map;

public class Trie {
	private class trienode{
		Map<Character,trienode> child;
		boolean endofword;
		String meaning;
		public trienode(){
			child=new HashMap<>();
			endofword=false;
			meaning="not end";
		}
	}
	private final trienode root;
	public Trie(){
		root=new trienode();
	}
	//insert
	/*
	 * start from root node and check if the current character of string exists in node;
	 * if not exists create a new node and add entry to the current node;
	 * */
	public void TrieInsert(String word,String mean){
		trienode current=root;
		for(int i=0;i<word.length();i++){
			char ch=word.charAt(i);
			//check if character is present in hashmap; 
			trienode node = current.child.get(ch);
			//if not present create a new node and enter the character in the current node;
			if(node==null){
				node=new trienode();
				current.child.put(ch,node);
			}
			current=node;
		}
		//in the last node, put endofword==true and meaning into it;
		current.endofword=true;
		current.meaning=mean;
		//System.out.println(word+" inserted");
	}
	
	//search
	public String TrieSearch(String word){
		trienode current=root;
		for(int i=0;i<word.length();i++){
			char ch=word.charAt(i);
			trienode node=current.child.get(ch);
			if(node==null) return "not found!!!";
			current=node;
		}
		if(current.endofword==true) 
			return current.meaning;
		else
			return "not found!!!";
		
	}
}
