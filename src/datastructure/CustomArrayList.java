package datastructure;

/**
 * A self-implemented generic dynamic array, replacement for java.util.ArrayList.
 * Internally uses a fixed Object[] array that doubles in capacity when full (resize).
 * Implements Iterable<T> so it can be used in for-each loops.
 */

public class CustomArrayList<T> implements Iterable<T> {

    // Internal array — Object[] is used because Java does not allow generic arrays (type erasure)
    private Object[] data;

    // Number of elements actually stored in the list
    private int size;

    // Default initial capacity when no size is specified
    private static final int DEFAULT_CAPACITY = 4;

    // ========================
    // CONSTRUCTORS
    // ========================

    // Creates an empty list with the default capacity of 4
    public CustomArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Creates an empty list with a custom initial capacity
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        this.data = new Object[initialCapacity];
        this.size = 0;
    }

    // ========================
    // ADD
    // ========================

    // Adds an element at the end of the list
    // Triggers a resize if the internal array is full
    public void add(T element) {
        ensureCapacity();           // Resize falls nötig
        data[size] = element;
        size++;
    }

    // Inserts an element at a specific index
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity();
        // All elements from that index onward are shifted one position to the right
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    // ========================
    // GET
    // ========================

    // Returns the element at the given index
    // The unchecked cast is safe because only elements of type T are ever added
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    // ========================
    // SET (replace)
    // ========================

    // Replaces the element at the given index and returns the old value
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index);
        T old = (T) data[index];
        data[index] = element;
        return old;
    }

    // ========================
    // REMOVE
    // ========================

    // Removes the element at the given index
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) data[index];
        // All elements after it are shifted one position to the left
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        // The last slot is set to null to allow garbage collection
        data[size - 1] = null;
        size--;
        return removed;
    }

    // Removes the first occurrence of the given element
    // Returns true if the element was found and removed, false otherwise
    public boolean remove(Object element) {
        for (int i = 0; i < size; i++) {
            if (data[i] != null && data[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    // Removes all elements that match the given condition (predicate)
    // Iterates backwards to avoid index shifting issues during removal
    public boolean removeIf(java.util.function.Predicate<T> predicate) {
        boolean removed = false;
        for (int i = size - 1; i >= 0; i--) {
            if (predicate.test(get(i))) {
                remove(i);
                removed = true;
            }
        }
        return removed;
    }

    // ========================
    // HELPER METHODS
    // ========================

    // Returns the number of elements currently stored in the list
    public int size() {
        return size;
    }

    // Returns true if the list contains no elements
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns true if the list contains the given element
    public boolean contains(Object element) {
        for (int i = 0; i < size; i++) {
            if (data[i] != null && data[i].equals(element)) return true;
        }
        return false;
    }

    // Returns the index of the first occurrence of the element, or -1 if not found
    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (data[i] != null && data[i].equals(element)) return i;
        }
        return -1;
    }

    // Removes all elements from the list and resets the size to 0
    public void clear() {
        for (int i = 0; i < size; i++) data[i] = null;
        size = 0;
    }

    // Provides an iterator so the list can be used in for-each loops (service classes)
    // Required by the Iterable<T> interface
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                return (T) data[cursor++];
            }
        };
    }

    // ========================
    // RESIZE
    // ========================

    // Doubles the capacity of the internal array when it is full
    // Uses System.arraycopy() to copy all existing elements into the new array
    // Doubling ensures amortized O(1) performance for add() operations —
    // resizing happens less and less frequently as the list grows
    private void ensureCapacity() {
        if (size == data.length) {
            int newCapacity = data.length * 2;  // Verdopplung
            Object[] newData = new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    // ========================
    // INDEX-CHECKS
    // ========================

    // Validates index for get, set, and remove, index must be within (0, size)
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // Validates index for add, index must be within (0, size) inclusive, to allow appending
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // ========================
    // toString (for debug)
    // ========================

    // Returns a readable string representation of the list contents
    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}

