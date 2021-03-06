/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.pnnl.svf.core.collections;

/**
 * This indexer will iterate through indices in a linear fashion.
 *
 * @author D3X573
 */
public class LinearIndexer implements Indexer {

    private final int size; // the size of the collection
    private int index; // the current index
    private int prev; // the previous index
    private int count; // the current count

    /**
     * Constructor
     *
     * @param size the size of the collection (number of values to iterate
     *             through)
     *
     * @throws IllegalArgumentException if size is less than zero
     */
    public LinearIndexer(final int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size");
        }
        this.size = size;
    }

    @Override
    public int peek() {
        return prev;
    }

    @Override
    public int next() {
        if (index == -1) {
            return index;
        }
        if (index >= size) {
            index = -1;
            return index;
        } else {
            count++;
            prev = index;
            return index++;
        }
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int size() {
        return size;
    }

}
