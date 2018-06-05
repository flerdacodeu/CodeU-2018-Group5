public class KthToLast {
    public static void main(String[] args) {
        Node<Integer> n1 = new Node<>(3);
        Node<Integer> n2 = new Node<>(8, n1);
        Node<Integer> n3 = new Node<>(9, n2);
        Node<Integer> n4 = new Node<>(7, n3);
        Node<Integer> n5 = new Node<>(5, n4);
        Node<Integer> n6 = new Node<>(2, n5);
        Node<Integer> n7 = new Node<>(0, n6);
        System.out.println(kthToLastElement(n7, 3).getData());

    }

    /**
     * @param head head of the given linked list
     * @param k the index (from the end of the list) of the element to be returned
     * @return the node corresponding to the k-th to last element of the list or null if the list is empty
     */
    public static Node kthToLastElement(Node head, int k){
        if (head == null)
            return null;
        if (k<0)
            return null;
        Node forward = head;
        Node current = head;
        // gets forward k steps ahead of current
        for (int i = 0;i<k;i++) {
            forward = forward.getNext();
            if (forward == null)
                return null;
        }
        //when forward reaches at the end of the list, current is the kth to the last element
        while (forward.getNext() != null) {
            forward = forward.getNext();
            current = current.getNext();
        }
        return current;
    }
}


