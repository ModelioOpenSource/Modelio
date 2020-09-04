/* 
 * Copyright 2013-2018 Modeliosoft
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Hash map that may store multiple values for the same key.
 * 
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
@objid ("8d6e5627-1f4a-11e2-8814-001ec947c8cc")
public class MultiHashMap<K, V> extends HashMap<K,List<V>> {
    @objid ("9e939f64-1f4a-11e2-8814-001ec947c8cc")
    private static final long serialVersionUID = 1L;

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the specified value. More formally, returns
     * <tt>true</tt> if and only if this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>. This operation will probably require time linear in the map
     * size for most implementations of the <tt>Map</tt> interface.
     * @throws ClassCastException
     * if the value is of an inappropriate type for this map (optional)
     * @throws NullPointerException
     * if the specified value is null and this map does not permit null values (optional)
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the specified value
     */
    @objid ("9e9601bd-1f4a-11e2-8814-001ec947c8cc")
    public boolean contains(V value) {
        for (List<V> list : values()) {
            if (list.contains(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Modification Operations Associates the specified value with the specified key in this map (optional operation).
     * If the map previously contained a mapping for the key, the old value is replaced by the specified value. (A map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only if {@link #containsKey(Object) m.containsKey(k)} would return <tt>true</tt>.)
     * @throws ClassCastException
     * if the class of the specified key or value prevents it from being stored in this map
     * @throws NullPointerException
     * if the specified key or value is null and this map does not permit null keys or values
     * @throws IllegalArgumentException
     * if some property of the specified key or value prevents it from being stored in this map
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return true if the value was added or false if it was already present in the map.
     */
    @objid ("9e9601c3-1f4a-11e2-8814-001ec947c8cc")
    public boolean putValue(K key, V value) {
        List<V> vals = get(key);
        if (vals == null) {
            vals = new ArrayList<>(2);
            put(key, vals);
        }
        
        if (vals.contains(value)) {
            return false;
        }
        
        vals.add(value);
        return true;
    }

    /**
     * Remove the following pair from the map.
     * @param key The key where the value is mapped
     * @param value The value to remove
     * @return true if the value was removed, false if it was not mapped to the given key.
     */
    @objid ("9e9601ca-1f4a-11e2-8814-001ec947c8cc")
    @Override
    public boolean remove(Object key, Object value) {
        final List<V> vals = get(key);
        if (vals == null) {
            return false;
        }
        return vals.remove(value);
    }

    @objid ("9e9601d1-1f4a-11e2-8814-001ec947c8cc")
    public List<V> getList(K key) {
        List<V> ret = super.get(key);
        if (ret == null) {
            return Collections.emptyList();
        }
        return ret;
    }

}
