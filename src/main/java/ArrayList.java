import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class ArrayList<T> implements List<T> {

    private T[] m = (T[])new Object[1];

    private int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (int i = 0; i < size; i++) {
            if (m[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        final T[] newM = (T[])new Object[this.size()];
        System.arraycopy(m, 0, newM, 0, this.size());
        return newM;
    }

    @Override
    public <T1> T1[] toArray(final T1[] a) {
        if (a.length < size) return (T1[]) Arrays.copyOf(m, size, a.getClass());

        System.arraycopy(m, 0, a, 0, size);

        if (a.length > size) a[size] = null;

        return a;
    }

    @Override
    public boolean add(final T t) {
        if (m.length == size) {
            final T[] oldM = m;
            m = (T[]) new Object[this.size() * 2];
            System.arraycopy(oldM, 0, m, 0, oldM.length);
        }
        m[size++] = t;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        for (int i = 0; i < size(); i++) {
            if (m[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    public void removeRange(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= this.size() || toIndex > this.size() || toIndex < fromIndex) {
            throw new IndexOutOfBoundsException();
        }
        if (toIndex != fromIndex) {
            System.arraycopy(m, toIndex, m, fromIndex, size - toIndex);
            size = size - (toIndex - fromIndex);
        }
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends T> c) {
        if (c == null) throw new NullPointerException();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0 || index == size) {
            addAll(c);
        }
        else {
            final int tempSize = this.size;
            T[] mLeft = (T[]) new Object[index];
            mLeft = Arrays.copyOfRange(m, 0, index);
            T[] mRight = (T[]) new Object[m.length - index];
            mRight = Arrays.copyOfRange(m, index, m.length);
            T[] tempM = (T[]) new Object[m.length + c.size()];
            m = mLeft;
            size = index;
            this.addAll(c);
            System.arraycopy(m, 0, tempM, 0, index + c.size());
            System.arraycopy(mRight, 0, tempM, index + c.size(), mRight.length);
            m = tempM;
            size = tempSize + c.size();
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        m = (T[])new Object[1];
        size = 0;
    }

    @Override
    public T remove(final int index) {
        final T element = m[index];
        if (index != this.size() - 1)
            System.arraycopy(m, index + 1, m, index, this.size() - index - 1);
        size--;
        return element;
    }

    public void trimToSize() {
        if (m.length > size) {
            final T[] tempM = m;
            m = (T[]) new Object[size];
            System.arraycopy(tempM, 0, m, 0, size);
        }
    }

    @Override
    public ListIterator listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        for(int i = size; i >= 0; i--) {
            if (m[i] == target) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOf(final Object o) {
        int currentItem = 0;
        for (final Object item : this) {
            if (item==o) return currentItem;
            currentItem++;
        }
        return -1;
    }

    @Override
    public List<T> subList(final int fromIndex, final int toIndex) {
        if (fromIndex < 0 || toIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        if (fromIndex == toIndex) {
            return new ArrayList<T>();
        }
        final T[] oldM = m;
        final T[] subM = (T[])new Object[toIndex - fromIndex];;
        System.arraycopy(m, fromIndex, subM, 0, toIndex - fromIndex);
        return Arrays.asList(subM);
    }

    @Override
    public void add(final int index, final T element) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException();

        if (size  == 0 || index == size) {
            add(element);

        } else if (m.length == size) {

            final T[] tempM = m;
            m = (T[]) new Object[this.size() * 2];

            System.arraycopy(tempM, 0, m, 0,  index );
            System.arraycopy(tempM, index, m, index + 1, size() - index);

            set(index, element);
            size++;

        } else {

            final T[] tempM = m;
            System.arraycopy(tempM, 0, m, 0, index + 1);
            System.arraycopy(tempM, index, m, index + 1, size() - index);
            set(index, element);
            size++;

        }
    }

    @Override
    public T set(final int index, final T element) {
        m[index] = element;
        return element;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > size) {
            final T[] oldM = m;
            m = (T[]) new Object[minCapacity];
            System.arraycopy(oldM, 0, m, 0, size);
        }
    }

    @Override
    public T get(final int index) {
        return m[index];
    }

    private class ElementsIterator implements ListIterator<T> {

        private static final int LAST_IS_NOT_SET = -1;
        private int index;
        private int lastIndex = LAST_IS_NOT_SET;

        public ElementsIterator() {
            this(0);
        }

        public ElementsIterator(final int index) {
            // BEGIN (write your solution here)
            this.index = index;
            // END
        }

        @Override
        public boolean hasNext() {
            return ArrayList.this.size() > index;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastIndex = index;
            return ArrayList.this.m[index++];
        }

        @Override
        // . 1 . 2 . 8 ^ 3 . 4. 5
        public void add(final T element) {
            // BEGIN (write your solution here)
            ArrayList.this.add(index, element);
            index++;
            // END
        }

        @Override
        public void set(final T element) {
            // BEGIN (write your solution here)
            if (lastIndex == LAST_IS_NOT_SET)
                throw new IllegalStateException();
            ArrayList.this.set(lastIndex, element);
            // END
        }

        @Override
        public int previousIndex(){
            // BEGIN (write your solution here)
            if (index == 0)
                return -1;
            return index - 1;
            // END
        }

        @Override
        public int nextIndex() {
            // BEGIN (write your solution here)
            if (index == size)
                return size;
            return index;
            // END
        }

        @Override
        public boolean hasPrevious() {
            // BEGIN (write your solution here)
            if (index == 0) {
                return false;
            }
            return ArrayList.this.size() > 0;
            // END
        }

        @Override
        public T previous() {
            // BEGIN (write your solution here)
            if (!hasPrevious())
                throw new NoSuchElementException();
            lastIndex = index;
            return ArrayList.this.m[--index];
            // END
        }

        @Override
        public void remove() {
            if (lastIndex == LAST_IS_NOT_SET) throw new IllegalStateException();
            ArrayList.this.remove(lastIndex);
            index--;
            lastIndex = LAST_IS_NOT_SET;
        }
    }
}
