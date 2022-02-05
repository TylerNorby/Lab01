public class Cache<T> {


    private DLLNode<T> head; // Stores reference to front of DLL
    private int count; // # elements in DLL instance
    private int capacity; // max # in DLL instance
    private int hits; // times desired element has been found
    private int accesses; // times DLL has been accessed

    /**
     * @param capacity Default constructor for cache class
     */
    public Cache(int capacity) {
        count = 0;
        this.capacity = capacity;
        hits = 0;
        accesses = 0;
        head = null;
    }

    public void print(){
        System.out.print("Cache layer Values: ");
        DLLNode<T> node = head;
        while (node != null){
            System.out.print(node.getElement() + ", ");
            node = node.getNext();
        }
    }

    /**
     * @param data Adds new Node containing data to from of DLL
     */
    public T add(T data) {
        DLLNode<T> newNode = new DLLNode<>(data);

        // In case that list is not empty
        if (head != null) {
            newNode.setNext(head);
            head.setPrev(newNode);
        }

        head = newNode;
        count++;

        // In case that list is at capacity
        if (count > capacity) {
            return removeLast();
        }
        return null;
    }

    /**
     *
     */
    public T removeLast() {
        DLLNode<T> temp = head;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }

        if (temp == head){
            head = null;
        } else {
            temp.getPrev().setNext(null);
            temp.setPrev(null);
        }

        count--;
        return temp.getElement();
    }

    /**
     * @param data
     */
    public T remove(T data) {
        DLLNode<T> badNode = find(data, false);

        if (data == null){
            return null;
        }

        if (badNode != null) {
            T returnValue = badNode.getElement();

            if (badNode.getNext() != null) { // Make sure to not set next on null node
                badNode.getNext().setPrev(badNode.getPrev());
            }
            if (badNode.getPrev() != null) { // Head does not have a previous node.
                badNode.getPrev().setNext(badNode.getNext());
            } else {
                head = badNode.getNext();
            }

            count--; // Only count remove if it removed something
            return returnValue;
        }
        return null;
    }

    /**
     * @return
     */
    // TODO: Checked edge cases
    public DLLNode<T> find(T data, boolean trackAccesses) {
        if (trackAccesses) {
            accesses++;
        }
        DLLNode<T> current = head;

        while (current != null) {
            if (current.getElement().equals(data)) {
                hits++;
                return current;
            }else{
                current = current.getNext();
            }
        }
        return null;

    }

    /**
     * @param data
     */
    public void move(T data) {
        DLLNode<T> temp = head;

        while (temp != null){
            if (temp.getElement().equals(data)){
                if (temp == head){
                    return;
                }

                if (temp.getPrev() != null){
                    temp.getPrev().setNext(temp.getNext());
                }
                if (temp.getNext() != null){
                    temp.getNext().setPrev(temp.getPrev());
                }
                temp.setPrev(null);
                temp.setNext(head);
                head.setPrev(temp);

                head = temp;
                return;
            }

            temp = temp.getNext();
        }
    }

    /**
     * @return
     */
    public double getHitRate() {
        return (double) hits / accesses;
    }

    public int getAccesses() {
        return accesses;
    }

    /**
     * @return
     */
    public int getHits() {
        return hits;
    }

    /**
     * @return
     */
    public void setHits() {
        hits++;
    }

    /**
     * @return
     */
    public int setCount() {
        return count;
    }

    /**
     * @return
     */
    public int getCount() {
        return count;
    }


}
