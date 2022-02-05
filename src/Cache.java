public class Cache<T> {


    private DLLNode<T> head; // Stores reference to front of DLL
    private DLLNode<T> tail; // Stores reference to end of DLL
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
        tail = null;
    }

    /**
     * @param data Adds new Node containing data to from of DLL
     */
    public void add(T data) {
        DLLNode<T> newNode = new DLLNode<>(data);

        // In case that list is not empty
        if (count != 0) {
            newNode.setNext(head);
            head.setPrev(newNode);
            count++;
        }

        // In case that list is at capacity
        if (count == capacity) {
            removeLast();
        }
        head = newNode;
        count++;
    }

    /**
     *
     */
    public void removeLast() {

        // In case where removing last element in list
        if (count == 1) {
            head = null;
            tail = null;
        } else { // Standard Case
            tail = tail.getPrev();
            tail.setNext(null);
        }

        count--;
    }

    /**
     * @param data
     */
    public T remove(T data) {

        DLLNode<T> badNode = find(data);

        if (badNode != null) {

            T returnValue = badNode.getElement();
            badNode.getNext().setPrev(badNode.getPrev());
            badNode.getPrev().setNext(badNode.getNext());
            return returnValue;
        }

        count--;
        return null;
    }

    /**
     * @return
     */
    // TODO: Checked edge cases
    public DLLNode find(T data) {
        accesses++;
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
    public T move(T data) {

        T outgoingElement = (T)find(data).getElement();

        if (outgoingElement != null) {
            remove(outgoingElement);
        }


        return outgoingElement;
    }

    /**
     * @return
     */
    public double getHitRate() {
        return hits / accesses;
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
