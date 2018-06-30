/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrada
 */

/** This is a  node in a singly-linked list.
 * It stores an integer value. */


public class List {
        
        class Node{
            /* The value that a node stores */
            int value;
            /* The reference to the  next node in the list */
            Node next;

            public Node(int n){
                value = n;
            }
        }
    
        public Node head;
        int number_of_elements;

        public List(){
		head = null;
                number_of_elements = 0;
	}

	public void show(){
		Node p = head;
		while(p.next!=null){
			System.out.print(p.value + " ");
			p = p.next;
		}
		System.out.println(p.value);
	}

        public void add(int d){
            if (head == null){
                head = new Node(d);
            } else {
                Node end = new Node(d);
                Node p = head;

                while(p.next != null){
                        p = p.next;
                }
                p.next = end;
            }
            number_of_elements++;
        }
        
        /* This is my method for finding the requested element. If the element
        is not found, than the function return null*/
        public int getKthLastElement(int k){
            if ( k > number_of_elements - 1)
                return -1;
            
            int target_node = number_of_elements - k;
   
            Node p = head;
            while (p.next != null){
                
                if (target_node == 1)
                   return p.value;
                
                target_node--;
                p = p.next;
            }         
           return p.value;
        }
        
        public static void main(String[] args){
            int v[] = {1,2,3,4,5,6};
            List obj = new List();
            for(int i = 0; i < 6; i++)
                obj.add(v[i]);
            obj.show();
          
            System.out.println(obj.getKthLastElement(0));
        }   
     
}
