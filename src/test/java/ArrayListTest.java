import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

public class ArrayListTest {
    private ArrayList <Integer> testInstance;
    private ListIterator<Integer> listIterator;

    @Before
    public void setUp() throws Exception {
        testInstance = new ArrayList<>();
        listIterator = testInstance.listIterator();
    }

    @Test
    public void testSizeWhenSizeIs0() throws Exception {

        assertEquals(0, testInstance.size());
    }

    @Test
    public void testIsEmptyWhenEmpty() throws Exception {
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testToArrayWhenInputArrayHaveSizeOne() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[1];

        final Integer[] result = testInstance.toArray(input);
        assertNotEquals(input, result);
        assertEquals((Integer)1, result[0]);
        assertEquals((Integer)2, result[1]);
        assertEquals((Integer)3, result[2]);
        assertEquals(3, result.length);
    }

    @Test
    public void testToArrayWhenInputArrayHaveCorrectSize() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[3];

        final Integer[] result = testInstance.toArray(input);
        assertArrayEquals(input, result);
    }

    @Test
    public void testContains() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        assertTrue(testInstance.contains(3));
        assertFalse(testInstance.contains(0));
    }

    @Test
    public void testAdd() throws Exception {

        testInstance.add(1);
        testInstance.add(1);

        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveFirstElement() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.remove(1);

        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveLastElement() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.remove(1);

        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContainsAll() throws Exception {

        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(1);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testAddAll() throws Exception {

        testInstance.add(1);
        testInstance.add(2);

        testInstance.add(3);
        testInstance.add(4);

        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
    }

    @Test
    public void testSubListWhenIndexesOutOfRange() throws Exception {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        try {
            testInstance.subList(-1, 2);
            fail("subList do not throw the Exception when fromIndex < 0");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            testInstance.subList(0, testInstance.size() + 1);
            fail("subList do not throw the Exception when toIndex > size");
        } catch (final IndexOutOfBoundsException e) {}
    }

    @Test
    public void testSubListWhenIndexesEquals() throws Exception {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        List<Integer> subList = new ArrayList<>();
        subList = testInstance.subList(1, 1);
        assertTrue(subList.isEmpty());
    }

    @Test
    public void testSubList() throws Exception {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        List<Integer> subList = new ArrayList<>();
        subList = testInstance.subList(1, 3);
        assertFalse(subList.isEmpty());
        assertSame("subList[0] = ",2, subList.get(0));
        assertSame("subList[0] = ",3, subList.get(1));
        assertEquals(2, subList.size());
        assertFalse(testInstance.isEmpty());
        assertSame("testInstance[0] = ",1, testInstance.get(0));
        assertSame("testInstance[4] = ",5, testInstance.get(4));
    }

    @Test
    public void testRemoveRangeWhenIndexOutOfRange() throws Exception {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        try {
            testInstance.removeRange(-1, 2);
            fail("removeRange do not throw the Exception when fromIndex < 0");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            testInstance.removeRange(0, testInstance.size() + 1);
            fail("removeRange do not throw the Exception when toIndex > size");
        } catch (final IndexOutOfBoundsException e) {}
    }

    @Test
    public void removeRangeWhenIndexesEquals() throws Exception {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.removeRange(2, 2);
        assertFalse(testInstance.isEmpty());
        assertEquals(5, testInstance.size());
    }

    @Test
    public void testRemoveRange() throws Exception {
        testInstance.add(10);
        testInstance.add(11);
        testInstance.add(12);
        testInstance.add(13);
        testInstance.add(14);
        testInstance.add(15);
        assertEquals(6, testInstance.size());
        testInstance.removeRange(0, 2);
        assertEquals(4, testInstance.size());
        assertSame("testInstance[0] = ",12, testInstance.get(0));
        assertSame("testInstance[3] = ",15, testInstance.get(3));
        testInstance.removeRange(1, 2);
        assertEquals(3, testInstance.size());
        assertSame("testInstance[0] = ",12, testInstance.get(0));
        assertSame("testInstance[1] = ",14, testInstance.get(1));
    }

    @Test
    public  void testAddAllWhenIndexLessZero() throws Exception {
        testInstance.add(1);
        testInstance.add(4);
        testInstance.add(5);

        ArrayList<Integer> testCollection = new ArrayList<>();
        testCollection.add(2);
        testCollection.add(3);
        try {
            testInstance.addAll(-1, testCollection);
            fail("addAll() do not throw the Exception with index less than 0");
        } catch (final IndexOutOfBoundsException e) {}
    }

    @Test
    public  void testAddAllWhenCollectionIsEmpty() throws Exception {
        assertTrue(testInstance.isEmpty());

        ArrayList<Integer> testCollection = new ArrayList<>();
        testCollection.add(1);
        testCollection.add(2);

        testInstance.addAll(0, testCollection);
        assertFalse(testInstance.isEmpty());
        assertEquals(2, testInstance.size());
        assertSame("Index = 1 ",2, testInstance.get(1));
    }

    @Test
    public void testAddAllInTheMiddleWhenCollectionIsNotEmpty() throws Exception {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(8);
        testInstance.add(9);
        testInstance.add(10);

        assertFalse(testInstance.isEmpty());

        ArrayList<Integer> testCollection = new ArrayList<>();
        testCollection.add(4);
        testCollection.add(5);
        testCollection.add(6);
        testCollection.add(7);

        testInstance.addAll(3, testCollection);
        assertSame("Index = 0 ",1, testInstance.get(0));
        assertSame("Index = 3 ",4, testInstance.get(3));
        assertSame("Index = 9 ",10, testInstance.get(9));

        assertEquals(10,testInstance.size());
    }

    @Test
    public void testIndexOf() {
        testInstance.add(10);
        testInstance.add(20);
        testInstance.add(30);
        testInstance.add(40);
        testInstance.add(40);
        testInstance.add(50);
        assertFalse(testInstance.isEmpty());
        assertEquals(3, testInstance.indexOf(40));
    }

    @Test
    public void testLastIndexOf() {
        testInstance.add(10);
        testInstance.add(20);
        testInstance.add(30);
        testInstance.add(40);
        testInstance.add(40);
        testInstance.add(50);
        assertFalse(testInstance.isEmpty());
        assertEquals(4, testInstance.lastIndexOf(40));
        testInstance.remove(5);
        testInstance.remove(4);
        testInstance.remove(3);
        testInstance.remove(2);
        testInstance.remove(1);
        assertFalse(testInstance.isEmpty());
        assertEquals(0, testInstance.lastIndexOf(10));
        testInstance.clear();
        assertTrue(testInstance.isEmpty());
        assertEquals(-1, testInstance.lastIndexOf(10));
    }

    @Test
    public void testIndexOfWhenNoSuchElementInList() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        assertFalse(testInstance.isEmpty());
        assertEquals(-1, testInstance.indexOf(10));
    }

    @Test
    public void testRemoveAll() throws Exception {

        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.removeAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(1));
    }

    @Test
    public void testRetainAll() throws Exception {

        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.retainAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(2));
    }

    @Test
    public void testClear() throws Exception {

        testInstance.add(1);
        testInstance.add(1);

        testInstance.clear();

        assertTrue(testInstance.isEmpty());
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testRemoveBeforeNext() throws Exception {

        testInstance.add(2);
        final Iterator<Integer> iter = testInstance.iterator();
        try {
            iter.remove();
            fail("remove do not throw the Exception when called before next");
        } catch (final IllegalStateException e) {}
    }

    @Test
    public void testNextOnEmptyCollection() throws Exception {

        testInstance.add(1);
        testInstance.add(2);

        final Iterator<Integer> iter = testInstance.iterator();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        try {
            iter.next();
            fail("next do not throw the Exception when no more ellements");
        } catch (final java.util.NoSuchElementException e) {}
    }

    @Test
    public void testHasPreviousWhenIteratorAtTheEndOfTheCollection() {

        testInstance.add(1);
        testInstance.add(2);
        assertFalse(listIterator.hasPrevious());
        listIterator.next();
        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testPreviousIndexWhenItEqualsTo1() {

        testInstance.add(1);
        testInstance.add(1);
        listIterator.next();
        listIterator.next();

        assertEquals(1, listIterator.previousIndex());
    }

    @Test
    public void testAddInIteratorLastIsNotSet() {

        listIterator.add(1);
        listIterator.add(2);
        listIterator.add(3);
        try {
            listIterator.set(222);
            fail("set method can not be called after add (E). Wrong last element.");
        } catch (final IllegalStateException e){}

        listIterator.add(4);
    }

    @Test
    public void testAddInIteratorAfterNext() {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        listIterator.next();
        listIterator.next();
        assertSame("previousIndex wrong",1, listIterator.previousIndex());
        assertSame("nextIndex wrong",2, listIterator.nextIndex());
        assertSame("previous element ",2, listIterator.previous());
        assertSame(1, testInstance.get(0));
        assertEquals("size",4, testInstance.size());
        listIterator.add(9);
        assertSame("previous element ",9, listIterator.previous());
        listIterator.add(10);
        assertEquals("size",6, testInstance.size());
        assertSame("previousIndex wrong",1, listIterator.previousIndex());
        assertSame("nextIndex wrong",2, listIterator.nextIndex());
        assertSame("previous element ",10, listIterator.previous());
    }

    @Test
    public void testAddInIteratorWhenEmptyList() {

        listIterator.add(1);
        listIterator.add(2);
        assertSame("previousIndex ",1, listIterator.previousIndex());
        assertSame("previous element ", 2, listIterator.previous());
        assertSame("First element ", 1, testInstance.get(0));
        assertEquals("size",2, testInstance.size());
    }

    @Test
    public void testAddInIteratorWhenIsNotEmptyListToTheBeginning() {


        testInstance.add(0);
        testInstance.add(0);
        testInstance.add(0);
        listIterator.add(1);
        assertSame("previousIndex ",0, listIterator.previousIndex());
        assertSame("nextIndex ",1, listIterator.nextIndex());
        assertSame("previous element ", 1, listIterator.previous());
        assertSame("Get first element ",1, testInstance.get(0));
        assertEquals("size",4, testInstance.size());
    }


    @Test
    public void testSetWhenNeitherNextNorPreviousHaveBeenCalled() {
        testInstance.add(1);
        try {
            listIterator.set(null);
            fail("set method do not throw IllegalStateException the if neither next nor previous have been called");
        } catch (final IllegalStateException e){}
        listIterator.add(2);
        try {
            listIterator.set(null);
            fail("set method do not throw IllegalStateException the if neither next nor previous have been called");
        } catch (final IllegalStateException e){}
    }

    @Test
    public void testSet() {
        testInstance.add(1);
        listIterator.next();
        listIterator.set(2);
        assertEquals((Integer)2, testInstance.get(0));
    }

    @Test
    public void testPreviouseOnCollectionWithOneElement() {
        testInstance.add(10);
        final Integer next = listIterator.next();
        final Integer previous = listIterator.previous();
        assertEquals(next, previous);
    }
    @Test
    public void testPreviouseIndex() {
        testInstance.add(1);
        listIterator.next();
        assertEquals(0, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexWhenEmptyCollection() {
        assertEquals(-1, listIterator.previousIndex());
    }

    @Test
    public void testPreviouseWhenEmptyCollection() {

        try {
            listIterator.previous();
            fail("list iterator do not throw the Exception when called previous method on empty collection");
        } catch (final java.util.NoSuchElementException e) {}
    }

    @Test
    public void testHasPreviouseWhenEmptyCollection() {
        assertFalse(listIterator.hasPrevious());
    }

    @Test
    public void testRemoveTwoTimeInTheRow() throws Exception {
        testInstance.add(1);
        testInstance.add(2);

        final Iterator<Integer> iter = testInstance.iterator();
        iter.next();
        iter.remove();
        assertEquals("Expected collection size is 1, however actual is not", 1, testInstance.size());
        try {
            iter.remove();
            fail("remove do not throw the Exception when called twice");
        } catch (final IllegalStateException e) {}
    }
}