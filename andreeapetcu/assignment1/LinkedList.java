public class LinkedList<T> {

    private class LinkedListNode<T> {
        LinkedListNode<T> next = null;
        private T data;

        public LinkedListNode(T data) {
            this.data = data;
        }
        
        public T getValue(){
            return data;
        }
    }
    
    private LinkedListNode<T> head = null;
    private LinkedListNode<T> tail = null;

    /**
     * Adds a node at the end of the linked list
     * @param value the value of the node to be appended
     */
    public void addAtEnd (T value){
        LinkedListNode<T> newNode = new LinkedListNode<>(value);

        if (head == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }
    
    /**
     * Adds a node at the beginning of the linked list
     * @param value the value of the node to be prepended
     */
    public void addAtBeginning (T value){
        LinkedListNode<T> newNode = new LinkedListNode<>(value);

        if (head == null){
            head = newNode;
            tail = newNode;
        } else{
            newNode.next = head;
            head = newNode;
        }
    }
    
    /**
     * Traverses the list
     */
    public void print(){
        LinkedListNode<T> temp = head;
        while (temp != null){
            System.out.println(temp.getValue());
            temp = temp.next;
        }
    }

    /**
     * Finds the kth to last element of a linked list
     *
     * @param k position of the element starting from the end of the list
     * @return kth to last element of the list
     */
    public T kthToLastElement(int k){
        
        if (head == null)
            return null;
        
        // point two elements in the list that will be k+1 elements apart
        // such that when fastPointer is beyond the end of the list,
        // the normalPointer will be at the k-th to last element
        LinkedListNode<T> normalPointer = head;
        LinkedListNode<T> fastPointer = head.next;
        
        // set fastPointer k+1 elements apart from nomalPointer
        while(k != 0){
            fastPointer = fastPointer.next;
            k--;
        }

        //when the fast pointer is null
        //the normal pointer is on the kth to last element
        while(fastPointer != null){
            normalPointer = normalPointer.next;
            fastPointer = fastPointer.next;
        }

        //return the value of the element that normalPointer is at
        return normalPointer.getValue();
    }
}
