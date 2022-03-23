/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.vbasic.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * virtual concatenation of many collections.
 * @param <E> The type of elements.
 */
@objid ("fe60694c-c1ef-11e1-92d5-001ec947ccaf")
public class CompoundCollection<E> implements Collection<E> {
    @objid ("ff28ae2d-c1ef-11e1-92d5-001ec947ccaf")
    private final Collection<Collection<? extends E>> content;

    /**
     * Initialize a new compound collection.
     */
    @objid ("ff2b1088-c1ef-11e1-92d5-001ec947ccaf")
    public  CompoundCollection() {
        this.content = new ArrayList<>(2);
    }

    @objid ("ff2b108a-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public int size() {
        int s = 0;
        for (Collection<? extends E>  c: this.content)
            s+=c.size();
        return s;
    }

    @objid ("ff2b108f-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public boolean isEmpty() {
        if (this.content.isEmpty())
            return true;
        
        for (Collection<? extends E>  c: this.content)
            if (! c.isEmpty())
                return false;
        return true;
    }

    @objid ("ff2b1094-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public boolean contains(Object o) {
        for (Collection<? extends E>  c: this.content)
            if (c.contains(o))
                return true;
        return false;
    }

    @objid ("ff2b109a-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public Iterator<E> iterator() {
        return new CompoundIterator<>(this.content);
    }

    @objid ("ff2b10a1-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public Object[] toArray() {
        ArrayList<E> ret = toList();
        return ret.toArray();
    }

    @objid ("ff2b10a8-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public <T> T[] toArray(T[] a) {
        ArrayList<E> ret = toList();
        return ret.toArray(a);
    }

    @objid ("ff2d72de-c1ef-11e1-92d5-001ec947ccaf")
    private ArrayList<E> toList() {
        ArrayList<E> ret = new ArrayList<>();
        for (Collection<? extends E>  c: this.content)
            ret.addAll(c);
        return ret;
    }

    @objid ("ff2d72e4-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("Can only add Collections.");
    }

    @objid ("ff2d72e9-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public boolean remove(Object o) {
        boolean ret =false;
        for (Collection<? extends E>  c: this.content)
            ret |= c.remove(o);
        return ret;
    }

    @objid ("ff2d72ef-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public boolean containsAll(Collection<?> c) {
        return toList().containsAll(c);
    }

    @objid ("ff2d72f7-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return this.content.add(c);
    }

    @objid ("ff2d72ff-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public boolean removeAll(Collection<?> c) {
        if (this.content.remove(c))
            return true;
        else
            throw new IllegalArgumentException("Argument is not part of this compound collection");
        
    }

    @objid ("ff2d7306-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @objid ("ff2d730d-c1ef-11e1-92d5-001ec947ccaf")
    @Override
    public void clear() {
        this.content.clear();
    }

}
