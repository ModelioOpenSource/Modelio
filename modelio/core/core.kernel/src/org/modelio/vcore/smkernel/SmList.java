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

package org.modelio.vcore.smkernel;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.vcore.smkernel.mapi.fake.FakeMObject;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Model object dependency content implementation.
 * <p>
 * This list reflects the content of a dependency on a object.
 * Modifying this list content does modify the represented object through
 * the {@link SmObjectImpl} API.
 * 
 * @param <T> the type of the dependency
 */
@objid ("005d71f4-fd81-1f1f-85a5-001ec947cd2a")
public class SmList<T> extends AbstractList<T> implements EList<T> {
    @objid ("d9cd5eb3-589c-429d-b40f-fd524991cb16")
    private List<T> delegateList;

    @objid ("5729fd91-b412-4bd7-adff-fed586ef0a17")
    private boolean hasFakes;

    @objid ("005d70be-fd81-1f1f-85a5-001ec947cd2a")
    private final SmDependency dep;

    @objid ("005d57b4-fd81-1f1f-85a5-001ec947cd2a")
    private final SmObjectImpl owner;

    /**
     * Instantiate the SmList.
     * 
     * @param owner the represented model object
     * @param dep the represented model dependency
     */
    @objid ("005d6aa6-fd81-1f1f-85a5-001ec947cd2a")
    public SmList(final SmObjectImpl owner, final SmDependency dep) {
        this.owner = owner;
        this.dep = dep;
    }

    @objid ("005d043a-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public boolean add(final T value) {
        setModified();
        return this.owner.appendDepVal(this.dep, (SmObjectImpl) value);
    }

    @objid ("005d5bce-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public void add(final int index, final T value) {
        setModified();
        this.owner.appendDepVal(this.dep, (SmObjectImpl) value, index);
        return;
    }

    @objid ("005d652e-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public void clear() {
        setModified();
        for (SmObjectImpl obj : toArray(new SmObjectImpl[size()])) {
            this.owner.eraseDepVal(this.dep, obj);
        }
    }

    @objid ("005d6420-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public boolean contains(final Object value) {
        return getDelegateList().contains(value);
    }

    @objid ("005d5534-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public boolean containsAll(final Collection<?> values) {
        return getDelegateList().containsAll(values);
    }

    @objid ("005d5426-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public T get(final int index) {
        return getDelegateList().get(index);
    }

    @objid ("005d5386-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public int indexOf(final Object value) {
        return getDelegateList().indexOf(value);
    }

    @objid ("005d4e54-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public int lastIndexOf(final Object value) {
        return getDelegateList().lastIndexOf(value);
    }

    @objid ("0014de08-3493-1f35-b94f-001ec947cd2a")
    @Override
    public void move(int newPosition, T object) {
        int oldPosition = indexOf(object);
        
        this.owner.getMetaOf().moveObjDepVal(
                this.owner,
                this.dep,
                (SmObjectImpl) object,
                realIndex(newPosition) - realIndex(oldPosition));
        
        setModified();
    }

    @objid ("001508a6-3493-1f35-b94f-001ec947cd2a")
    @Override
    public T move(int newPosition, int oldPosition) {
        T object = get(oldPosition);
        this.owner.getMetaOf().moveObjDepVal(
                this.owner,
                this.dep,
                (SmObjectImpl) object,
                realIndex(newPosition) - realIndex(oldPosition));
        
        setModified();
        return object;
    }

    @objid ("005d4062-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public boolean remove(final Object value) {
        setModified();
        return this.owner.eraseDepVal(this.dep, (SmObjectImpl) value);
    }

    @objid ("005d04d0-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public T remove(final int index) {
        T value = get(index);
        setModified();
        return this.owner.eraseDepVal(this.dep, (SmObjectImpl) value) ? value : null;
    }

    @objid ("005d0d18-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public boolean retainAll(final Collection<?> values) {
        List<Object> toRemove = new ArrayList<>();
        
        for (T obj : getDelegateList()) {
            if (!values.contains(obj)) {
                toRemove.add(obj);
            }
        }
        return removeAll(toRemove);
    }

    @objid ("005d0b6a-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    @SuppressWarnings("unchecked")
    public T set(final int index, final T value) {
        return (T) this.owner.setDepVal(this.dep, realIndex(index), (SmObjectImpl) value);
    }

    @objid ("005d099e-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public int size() {
        return getDelegateList().size();
    }

    @objid ("7f9fc0f1-f4d5-4e1f-a460-8ca6f7f265aa")
    @Override
    public void sort(Comparator<? super T> c) {
        // Default implementation throws java.util.NoSuchElementException
        //  at java.util.AbstractList$Itr.next(AbstractList.java:364)
        //  at java.util.List.sort(List.java:481)
        T[] a = (T[]) this.toArray();
        Arrays.sort(a, c);
        
        for (int i = 0; i < a.length; i++) {
            SmObjectImpl expect = (SmObjectImpl) a[i];
            T current = get(i);
            
            if (expect != current) {
                this.owner.eraseDepVal(this.dep, expect);
                this.owner.appendDepVal(this.dep, expect, i);
            }
        }
    }

    @objid ("005d2596-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public Object[] toArray() {
        return getDelegateList().toArray();
    }

    @objid ("005d0c82-fd81-1f1f-85a5-001ec947cd2a")
    @Override
    public <U> U[] toArray(final U[] a) {
        return getDelegateList().toArray(a);
    }

    @objid ("6c8e0b34-9cd9-4e7f-8ddf-4ddad4fe6aef")
    protected static final boolean isFakeObject(Object t) {
        return t instanceof FakeMObject;
    }

    @objid ("4f0ac181-97dc-4138-801b-d30fd48cf8f7")
    protected void setModified() {
        this.modCount++;
        if (this.hasFakes) {
            // drop the delegate list that must be recomputed
            this.delegateList = null;
        }
    }

    @objid ("005d2f50-fd81-1f1f-85a5-001ec947cd2a")
    @SuppressWarnings("unchecked")
    private List<T> getDelegateList() {
        if (this.delegateList == null) {
            this.hasFakes = false;
        
            Object ret = this.owner.getDepVal(this.dep);
        
            if (ret instanceof List) {
                this.delegateList = (List<T>) ret;
        
                if (hasFakeObjects(this.delegateList)) {
                    // The original list contains fake objects,
                    // instantiate a filtered list that sees only legal objects.
                    this.hasFakes = true;
                    this.delegateList = new FilteredList<>(this.delegateList);
                }
            } else if (ret == null) {
                this.delegateList = Collections.emptyList();
            } else {
                if (isFakeObject(ret)) {
                    this.delegateList = Collections.emptyList();
                    this.hasFakes = true;
                } else {
                    this.delegateList = new UnaryList<>(this.owner, this.dep);
                }
            }
        }
        return this.delegateList;
    }

    @objid ("8a2b8773-5ca9-47a4-afc9-3a3380c6599b")
    @SuppressWarnings("unchecked")
    private List<T> getDelegateList0() {
        Object ret = this.owner.getDepVal(this.dep);
        
        if (ret instanceof List) {
            return (List<T>) ret;
        
        } else if (ret == null) {
            return Collections.emptyList();
        } else {
            return Collections.singletonList((T) ret);
        }
    }

    @objid ("502822c3-89f1-4c4e-beae-05c52e7b612e")
    private boolean hasFakeObjects(List<?> l) {
        for (Object t : l) {
            if (isFakeObject(t)) {
                return true;
            }
        }
        return false;
    }

    @objid ("255b6fa3-f963-4ce4-bcfe-9870a0c5ecca")
    private int realIndex(int i) {
        List<?> d = getDelegateList();
        if (this.hasFakes) {
            FilteredList<?> f = (FilteredList<?>) d;
            return f.getRealIndex(i);
        } else {
            return i;
        }
    }

    /**
     * Filtered view of another {@link List}.
     * <p>
     * The content is filtered with {@link #accept(Object)}.
     * This list records the original list and the indexes of the accepted elements.
     * <p>
     * It currently does not accept modifications but could later.
     * @author cmarin
     * @since 3.4
     * 
     * @param <T> the type of the elements
     */
    @objid ("4c4b6674-6dc6-47fd-af69-f16fa26da00a")
    protected static class FilteredList<T> extends AbstractList<T> {
        @objid ("39f6b074-2d3e-4fab-ab9d-865fa37a9a1b")
        private List<?> realList;

        @objid ("81aaa9e9-6f56-40f6-af57-dec90d534e02")
        private int[] indexes;

        @objid ("c1e7bffd-c853-42a9-9259-2c2aea9a0ca7")
        private int size;

        @objid ("5a2a5153-1b30-4171-bb21-e2d195dc5b53")
        public FilteredList(List<?> realList) {
            this.realList = realList;
        }

        @objid ("9097ea0f-b801-44ee-937b-da6bc3feede0")
        protected int[] getIndexes() {
            if (this.indexes  == null) {
                this.indexes = new int[this.realList.size()];
                int j = 0;
                for (Object object : this.realList) {
                    if (accept(object)) {
                        this.indexes[this.size] = j;
                        this.size++;
                    }
                    j++;
                }
            }
            return this.indexes ;
        }

        @objid ("87620b83-0ad8-4f09-9ebb-84206c92116b")
        public int getRealIndex(int i) {
            return getIndexes()[i];
        }

        @objid ("31e6aa1e-bdcf-454d-b45c-1791e0fe2a92")
        protected boolean accept(Object o) {
            return (! isFakeObject(o)) ;
        }

        @objid ("c57070ff-a90d-48e1-b767-164eb9957305")
        @SuppressWarnings("unchecked")
        @Override
        public T get(int index) {
            return (T) this.realList.get(this.indexes[index]);
        }

        @objid ("6173f6a5-c93c-49b4-80cd-f1c8b30a2843")
        @Override
        public int size() {
            getIndexes();
            return this.size;
        }

    }

    @objid ("e0cc0f22-f3a1-4dad-9cd4-3f8f0cd2c991")
    private static final class UnaryList<T> extends AbstractList<T> {
        @objid ("e180909a-c362-4813-9c4c-ffd78145ac12")
        private final SmDependency dep;

        @objid ("832f4fb1-6589-46bb-aba9-c3524e8d2c36")
        private final SmObjectImpl owner;

        @objid ("6692b03a-ce17-43c4-9a25-7290cb54e4e9")
        public UnaryList(SmObjectImpl owner, SmDependency dep) {
            super();
            this.dep = dep;
            this.owner = owner;
        }

        @objid ("2a37e58f-bde8-4829-8734-42e71e04b043")
        @SuppressWarnings("unchecked")
        @Override
        public T get(int index) {
            return (T) this.owner.getDepVal(this.dep);
        }

        @objid ("72e42969-398f-448d-88fe-cbd6af0dee5c")
        @Override
        public int size() {
            return this.owner.getDepVal(this.dep) == null ? 0 : 1;
        }

    }

}
