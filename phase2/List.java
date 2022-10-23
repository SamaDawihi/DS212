public interface List<T> {
    boolean full();
    boolean last();
    boolean empty();
    void findFirst();
    void findNext();
    T retrieve();
    void update(T value);
    void insert(T value);
    void remove();
}
