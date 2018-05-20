public class LinkedListElement {

    public class LinkedListNode<T> {
        LinkedListNode<T> next = null;
        public T data;

        public LinkedListNode(T data) {
            this.data = data;
        }

        /**
         * Adds a node at the end of the linked list (used for testing method)
         * @param value the value of the node to be appended
         */
        public void addNode (T value){
            LinkedListNode<T> newNode = new LinkedListNode<>(value);
            LinkedListNode<T> node = this;

            if (node == null)
                node = newNode;

            while (node.next != null)
                node = node.next;

            node.next = newNode;
        }
    }

    /**
     * Method that finds the kth to last element of a linked list
     *
     * @param head first element of the linked list
     * @param k position of the element starting from the end of the list
     * @return kth to last element of the list
     */
    public LinkedListNode<Integer> kthToLastElement(LinkedListNode<Integer> head, int k){
        LinkedListNode<Integer> normalPointer = head;

        if (head == null)
            return normalPointer;

        LinkedListNode<Integer> fastPointer = head.next;

        // set the fast pointer to be k+1 elements in front of the normal pointer
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
