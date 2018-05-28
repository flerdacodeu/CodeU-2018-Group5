public class LinkedListElement<T> {

    class LinkedListNode<T> {
        LinkedListNode<T> next = null;
        public T data;

        public LinkedListNode(T data) {
            this.data = data;
        }

        /**
         * Adds a node at the end of the linked list
         * @param value the value of the node to be appended
         */
        public void addNode (T value){
            LinkedListNode<T> node = this;

            while (node.next != null)
                node = node.next;

            node.next = new LinkedListNode<>(value);
        }
    }

    /**
     * Finds the kth to last element of a linked list
     *
     * @param head first element of the linked list
     * @param k position of the element starting from the end of the list
     * @return kth to last element of the list
     */
    public LinkedListNode<T> kthToLastElement(LinkedListNode<T> head, int k){
        
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

        return normalPointer;
    }
}
