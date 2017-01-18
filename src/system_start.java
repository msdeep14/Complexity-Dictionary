/*
 * dictionary project
 * compares time complexity of trie, red black tree, avl tree and hashmap for large dictionary(36,351 entries)
 * inbuilt java hashmap is used
 * 
 * author: mandeep singh
 * */

/*
 * dictionary file source : https://raw.githubusercontent.com/sujithps/Dictionary/master/Oxford%20English%20Dictionary.txt
 * 
 * c++ code for removing the empty lines from the dict file(already removed);
 * 
#include<bits/stdc++.h>
using namespace std;
int main(){

	std::ifstream in_file("d_file.txt");
	std::ofstream out_file("d_file1.txt");

	std::string line;
	while ( getline( in_file, line ) ) {
    	if ( ! line.empty()) {
        	out_file << line<<"\n";
    	}
	}
}
 * 
 * 
 * */
import java.io.BufferedReader;
import java.util.Scanner; 
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
public class system_start {
	public static void main(String args[]){
		System.out.println("\t\t\tHello Dictionary!!!\n\n\t\t\tA msdeep14 creation");
		
		FileInputStream fis = null;
        BufferedReader reader = null;
        //trie declaration
        Trie t;
        t=new Trie();
        //avl declaration
        Avl A;
        A=new Avl();
        avlnode root=null;
        //hashmap declaration
        Map<String, String> dict=new HashMap<String,String>();
        
        //RedBlack declaration
        RedBlack rb;
        rb=new RedBlack();
        RedBlack.Node root_rb=null;
        
        //trie insert;
        double TrieInsertStart=System.nanoTime();
        try {
            fis = new FileInputStream("dict_file.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
           // System.out.println("Reading File line by line using BufferedReader");
            String line = reader.readLine();
            while(line != null){
                //System.out.println(line);
                //separate out first word and the meaning;

                int i = line.indexOf("  ");
                String word = line.substring(0, i);
                word=word.toLowerCase();
                String meaning = line.substring(i+1); 
               // System.out.println("word: "+word+"\nmeaning:: "+meaning);
                
                //store word and meaning into the dictionary
                //trie
                t.TrieInsert(word,meaning);
                
                //avl insert
                //root=A.AvlInsert(root,word,meaning);
               // if(root==null) System.out.println("avl null");
                
                //hashmap insert
               // dict.put(word,meaning);
                
                //read next line
                line = reader.readLine();
            }           
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        double TrieInsertEnd=System.nanoTime();
        double TrieInsertTotal=(TrieInsertEnd-TrieInsertStart)/1000000.0;
        
        //for avl insert
        double AvlInsertStart=System.nanoTime();
        try {
            fis = new FileInputStream("dict_file.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
           // System.out.println("Reading File line by line using BufferedReader");
            String line = reader.readLine();
            while(line != null){
                //System.out.println(line);
                //separate out first word and the meaning;

                int i = line.indexOf("  ");
                String word = line.substring(0, i);
                word=word.toLowerCase();
                String meaning = line.substring(i+1); 
               // System.out.println("word: "+word+"\nmeaning:: "+meaning);
                
                //store word and meaning into the dictionary
                //trie
               // t.TrieInsert(word,meaning);
                
                //avl insert
                root=A.AvlInsert(root,word,meaning);
               // if(root==null) System.out.println("avl null");
                
                //hashmap insert
                //dict.put(word,meaning);
                
                //read next line
                line = reader.readLine();
            }           
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        double AvlInsertEnd=System.nanoTime();
        double AvlInsertTotal=(AvlInsertEnd-AvlInsertStart)/1000000.0;
        
        //HashMap Insert
        double HashInsertStart=System.nanoTime();
        try {
            fis = new FileInputStream("dict_file.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
           // System.out.println("Reading File line by line using BufferedReader");
            String line = reader.readLine();
            while(line != null){
                //System.out.println(line);
                //separate out first word and the meaning;

                int i = line.indexOf("  ");
                String word = line.substring(0, i);
                word=word.toLowerCase();
                String meaning = line.substring(i+1); 
               // System.out.println("word: "+word+"\nmeaning:: "+meaning);
                
                //store word and meaning into the dictionary
                //trie
               // t.TrieInsert(word,meaning);
                
                //avl insert
                //root=A.AvlInsert(root,word,meaning);
               // if(root==null) System.out.println("avl null");
                
                //hashmap insert
                dict.put(word,meaning);
                
                //read next line
                line = reader.readLine();
            }           
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        double HashInsertEnd=System.nanoTime();
        double HashInsertTotal=(HashInsertEnd-HashInsertStart)/1000000.0;
        
        
        //RedBlack Insert
        double rbInsertStart=System.nanoTime();
        try {
            fis = new FileInputStream("dict_file.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
           // System.out.println("Reading File line by line using BufferedReader");
            String line = reader.readLine();
            while(line != null){
                //System.out.println(line);
                //separate out first word and the meaning;

                int i = line.indexOf("  ");
                String word = line.substring(0, i);
                word=word.toLowerCase();
                String meaning = line.substring(i+1); 
               // System.out.println("word: "+word+"\nmeaning:: "+meaning);
                
                //store word and meaning into the dictionary
                //trie
               root_rb= rb.insert(root_rb,word,meaning);
                
                //avl insert
                //root=A.AvlInsert(root,word,meaning);
               // if(root==null) System.out.println("avl null");
                
                //hashmap insert
               // dict.put(word,meaning);
                
                //read next line
                line = reader.readLine();
            }           
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        double rbInsertEnd=System.nanoTime();
        double rbInsertTotal=(rbInsertEnd-rbInsertStart)/1000000.0;
        
        
        //A.inorder(root);
        //calculate search time for all the entries 
        //through all the dictionary building techniques;
        
        //trie
        double TrieAllStart=System.nanoTime();
        try {
            fis = new FileInputStream("dict_file.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
           // System.out.println("Reading File line by line using BufferedReader");
            String line = reader.readLine();
            while(line != null){
                //System.out.println(line);
                //separate out first word and the meaning;

                int i = line.indexOf("  ");
                String word = line.substring(0, i);
                word=word.toLowerCase();
                String meaning = line.substring(i+1); 
                String search_str=t.TrieSearch(word);
                line = reader.readLine();
            }           
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        double TrieAllEnd=System.nanoTime();
        double TrieAllTotal=(TrieAllEnd-TrieAllStart)/1000000.0;
      //  System.out.println("trie all time:: "+TrieAllTotal+" ms");
        
        //avl
        double AvlAllStart=System.nanoTime();
        try {
            fis = new FileInputStream("dict_file.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
           // System.out.println("Reading File line by line using BufferedReader");
            String line = reader.readLine();
            while(line != null){
                //System.out.println(line);
                //separate out first word and the meaning;

                int i = line.indexOf("  ");
                String word = line.substring(0, i);
                word=word.toLowerCase();
                String meaning = line.substring(i+1); 
                avlnode nn=A.AvlSearch(root,word);
                line = reader.readLine();
            }           
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        double AvlAllEnd=System.nanoTime();
        double AvlAllTotal=(AvlAllEnd-AvlAllStart)/1000000.0;
        //System.out.println("Avl all time:: "+AvlAllTotal+" ms");
        
        //RedBlack
        double rbAllStart=System.nanoTime();
        try {
            fis = new FileInputStream("dict_file.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
           // System.out.println("Reading File line by line using BufferedReader");
            String line = reader.readLine();
            while(line != null){
                //System.out.println(line);
                //separate out first word and the meaning;

                int i = line.indexOf("  ");
                String word = line.substring(0, i);
                word=word.toLowerCase();
                String meaning = line.substring(i+1); 
                boolean bb=rb.rbSearch(root_rb,word);
                line = reader.readLine();
            }           
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        double rbAllEnd=System.nanoTime();
        double rbAllTotal=(rbAllEnd-rbAllStart)/1000000.0;
       // System.out.println("RedBlack all time:: "+rbAllTotal+" ms");
        
        //HashMap
        double HashAllStart=System.nanoTime();
        try {
            fis = new FileInputStream("dict_file.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
          
           // System.out.println("Reading File line by line using BufferedReader");
            String line = reader.readLine();
            while(line != null){
                //System.out.println(line);
                //separate out first word and the meaning;

                int i = line.indexOf("  ");
                String word = line.substring(0, i);
                word=word.toLowerCase();
                String meaning = line.substring(i+1); 
                String dict_ser=dict.get(word);
                line = reader.readLine();
            }           
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(system_start.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        double HashAllEnd=System.nanoTime();
        double HashAllTotal=(HashAllEnd-HashAllStart)/1000000.0;
       // System.out.println("Hash all time:: "+HashAllTotal+" ms");
        
        
        //search word
        String res;
        Scanner scan = new Scanner(System.in);
        System.out.println("enter word to search or 0 to exit::");
        String word = scan.next();
	word=word.toLowerCase();
	//System.out.print("entered word is:: ");
	//System.out.println(word);
        while(word.charAt(0)!='0'){
        	double TrieStart = System.nanoTime();
        	res=t.TrieSearch(word);
        	double TrieEnd   = System.nanoTime();
        	double TrieTotal = (TrieEnd - TrieStart)/1000000.0;
        	
        	double AvlStart=System.nanoTime();
        	if(root==null) System.out.println("avl null");
        	//System.out.println(root.word);
        	avlnode res2=A.AvlSearch(root,word);
        	double AvlEnd=System.nanoTime();
        	if(res2==null){
        		res="not found!!!";
        	}
        	
        	/*else{
        		System.out.println("avl meaning::"+res2.meaning);
        	}*/
        	
        	double AvlTotal=(AvlEnd-AvlStart)/1000000.0;
        	// System.out.println("Entered word: "+word);
        	//hashmap search
        	double HashStart=System.nanoTime();
        	String dict_val=dict.get(word);
        	double HashEnd=System.nanoTime();
        	double HashTotal=(HashEnd-HashStart)/1000000.0;
        	if(dict_val==null) res="not found!!!";
        	
        	double rbStart=System.nanoTime();
        	boolean res_rb=rb.rbSearch(root_rb,word);
        	double rbEnd=System.nanoTime();
        	double rbTotal=(rbEnd-rbStart)/1000000.0;
        	if(res_rb==true) {
        		res="not found!!!";
        		//System.out.println(res);
        	}
        	//System.out.println("mean: "+res_rb);
        	if(res=="not found!!!")
        		System.out.println("Entered word not in dictionary!!!");
        	else{
        		System.out.println("Meaning: "+res);
        		System.out.println("\nTime taken for search operation is ");
        		System.out.println("Trie    : "+TrieTotal+" ms");
        		System.out.println("AVL     : "+AvlTotal+" ms");
        		System.out.println("HashMap : "+HashTotal+" ms");
        		System.out.println("RedBlack: "+rbTotal+" ms");
        	}
        	System.out.println("\n\nenter word to search or 0 to exit::");
        	word = scan.next();
		word=word.toLowerCase();
        }
        System.out.println("\nTime taken for building up whole dictionary");
        System.out.println("Trie    : "+TrieInsertTotal+" ms");
        System.out.println("AVL     : "+AvlInsertTotal+" ms");
        System.out.println("HashMap : "+HashInsertTotal+" ms");
        System.out.println("RedBlack: "+rbInsertTotal+" ms");
        System.out.println("\nAverage Time taken for Searching a word (total entries=36,351)");
        System.out.println("Trie    : "+TrieAllTotal/36351+" ms");
        System.out.println("AVL     : "+AvlAllTotal/36351+" ms");
        System.out.println("HashMap : "+HashAllTotal/36351+" ms");
        System.out.println("RedBlack: "+rbAllTotal/36351+" ms");
        System.out.println("\n\t\t\tA msdeep14 creation");
        scan.close();
	}
}
