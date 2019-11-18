
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrada
 */
public class BinaryTree {
    
    Node root = null;
    
    class Node{
        public int value;
        Node left;
        Node right;
        Node father;
        
        public Node(int value){
            this.value = value;
            left = null;
            right = null;
            father = null;
        }
        
        public Node(int value, Node father ){
            this.value = value;
            left = null;
            right = null;
            this.father = father;
        }
        
        public String toString(){
            if (father != null)
                return "Value: "+value+" Father: " + father.value;
            return "Root: " + value;
                    
        }
    }
    
    public void add(int x){
        if (root == null)
            root = new Node(x);
        
        root = SpecialAdd(x, root, null);
    }
    
    public Node SpecialAdd(int x, Node root, Node father){
        if (root == null && father == null)
            return new Node(x);
        if (root == null && father != null)
            return new Node(x, father);
        
        if (x < root.value) {
            root.left = SpecialAdd(x, root.left, root);
        } else if (x > root.value) {
                   root.right = SpecialAdd(x, root.right, root);
                } else {
                    return root;
                }
           return root; 
        }
    

    
    public Node dfs(Node root, int target){
        if (root.value == target)
            return root;
        if (root.left != null)
            return dfs(root.left, target);
        if (root.right != null)
            return dfs(root.right, target);
        
        return null;            
    }
    
    
    /*  Task 1 */
    public ArrayList<Integer> getAncestors(int target ){
        ArrayList<Integer> result = new ArrayList<Integer>();
        
      
        Node aux = dfs(root, target);

        while(aux.father != null){
            result.add(aux.father.value);
            aux = aux.father;
        }

        return result;
    }
    
    
    /* Task 2 */
    public int getCommonAncestor(int x, int y){
        ArrayList<Integer> xAnc = getAncestors(x);
        ArrayList<Integer> yAnc = getAncestors(y);
        
        int result = xAnc.get(0);
        int[] occurances = new int[xAnc.size()];
        
        for (int i = 0; i<xAnc.size(); i++){
            occurances[i] = yAnc.indexOf(xAnc.get(i));
        }
        
        int minim = 10000;
        int ancestor = -1;
        for (int i = 0 ; i < occurances.length; i++){
            if (occurances[i] > 0 && occurances[i] < minim)
                minim = occurances[i];
                ancestor = i;
        }
        
        return xAnc.get(ancestor);
    }
       
    }
    
    
    

