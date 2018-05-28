public class Node<T>{
        private T data;
        private com.company.Node<T> next;
        public Node(T data){
            this.data=data;
            this.next=null;
        }
        public Node(T data, com.company.Node<T> next){
            this.data=data;
            this.next=next;
        }
        public com.company.Node getNext(){
            return this.next;
        }

        public T getData() {
            return this.data;
        }

        public void setNext(com.company.Node<T> next) {
            this.next = next;
        }
        public void setData(T data){
            this.data=data;
        }
    }

