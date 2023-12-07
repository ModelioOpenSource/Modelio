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
package org.modelio.vcore.model;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Cache of model objects.
 * <p>
 * Model objects are sorted by metaclass.
 */
@objid ("006f8c2c-0d1e-1f20-85a5-001ec947cd2a")
public class MObjectCache {
    /**
     * Map of all class caches.
     * <p>
     * Please use {@link #getMClassCache(MClass, boolean)} to access this member.
     */
    @objid ("4fe90ea4-7c19-4d9b-a547-75c3dd69c767")
    private final ConcurrentMap<MClass, IMClassCache> caches;

    @objid ("08448b81-ff4c-474f-a38c-020b42d67aca")
    private final SmMetamodel metamodel;

    /**
     * Creates a new cache.
     * @param metamodel the metamodel
     */
    @objid ("006e8a20-0d1e-1f20-85a5-001ec947cd2a")
    public  MObjectCache(SmMetamodel metamodel) {
        this.metamodel = metamodel;
        this.caches = new ConcurrentHashMap<>(metamodel.getRegisteredMClasses().size()/2, 0.95f, 1);
        
    }

    /**
     * Add an object to the cache.
     * @param obj the object to add
     * @throws DuplicateObjectException if another object with the same identifier is already in the cache.
     */
    @objid ("006c88d8-0d1e-1f20-85a5-001ec947cd2a")
    public void addToCache(SmObjectImpl obj) throws DuplicateObjectException {
        String oid = obj.getUuid();
        IMClassCache mClassCache = getMClassCache(obj.getClassOf(), true);
        
        // Put element to cache and check for duplicate identifiers
        SmObjectImpl oldObj = mClassCache.putIfAbsent(oid, obj);
        if (oldObj != null && oldObj != obj) {
            // Duplicate found: throw exception.
            try {
                throw new DuplicateObjectException(oid, oldObj, obj);
            } catch (@SuppressWarnings ("unused") DeadObjectException e) {
                // Either oldObj or obj is dead.
                obj.getData(); // if obj is dead DeadObjectException will fire again, let it go.
        
                // Here we know oldObj is the dead object, replace oldObj by obj in the cache.
                mClassCache.put(oid, obj);
            }
        }
        
    }

    /**
     * Get the cache content as a collection.
     * <p>
     * The returned collection is a view on the cache and reflects all changes made on it.
     * The cache should not be modified while walking the returned collection to avoid unspecified
     * behavior.
     * @see #stream()
     * @return all the cache content.
     */
    @objid ("f4aa154a-08b1-11e2-b33c-001ec947ccaf")
    public Collection<SmObjectImpl> asCollection() {
        final Map<MClass, IMClassCache> theCaches = MObjectCache.this.caches;
        // ignore "Redundant specification of type arguments <SmObjectImpl>" :
        // Removing it makes tycho 2.2 fai with "'<>' cannot be used with anonymous classes" .
        @SuppressWarnings ("unused")
        AbstractCollection<SmObjectImpl> ret = new AbstractCollection<SmObjectImpl>() {
        
            @Override
            public Iterator<SmObjectImpl> iterator() {
                return getIterator();
            }
        
            @Override
            public int size() {
                int s = 0;
                for (Map.Entry<MClass, IMClassCache> entry : theCaches.entrySet()) {
                    s += entry.getValue().size();
                }
                return s;
            }
        };
        return ret;
    }

    /**
     * Get the cache content as a {@link Stream}.
     * <p>
     * The cache should not be modified while walking the returned Stream to avoid unspecified
     * behavior.
     * @return a Stream on all the cache content.
     * @since 5.4.1 - 23/10/2023
     */
    @objid ("a2c9dbef-02d7-4468-ab0f-333dc1d3f014")
    public Stream<SmObjectImpl> stream() {
        return this.caches.values().stream().flatMap(mc -> mc.values().stream());
    }

    /**
     * Find model objects by a metaclass and an attribute value.
     * @param cls the metaclass.
     * @param withSubClasses if true will look into subclasses hierarchy too. Since 3.6
     * @param att the attribute to search
     * @param val the attribute value
     * @param results where to add the found elements.
     */
    @objid ("006c8bd0-0d1e-1f20-85a5-001ec947cd2a")
    public void findByAtt(final MClass cls, boolean withSubClasses, final String att, Object val, final Collection<MObject> results) {
        SmAttribute smAtt = (SmAttribute) cls.getAttribute(att);
        
        // If 'att' does not exist, throw an exception
        if (smAtt == null) {
            throw new IllegalArgumentException("Unknown attribute \"" + att + "\"");
        }
        
        // The search is first done for the metaclass itself
        for (SmObjectImpl obj : getMClassCache(cls, false).values()) {
            // Object attVal = smAtt.getValue(obj.getData());
            Object attVal = obj.getAttVal(smAtt);
            if (val.equals(attVal)) {
                // Matched!
                results.add(obj);
            }
        }
        
        // and then it must be carried out for all the metaclass derived
        // from 'cls'
        if (withSubClasses) {
            for (MClass mc : cls.getSub(true)) {
                for (SmObjectImpl obj : getMClassCache(mc, false).values()) {
                    // Object attVal = smAtt.getValue(obj.getData());
                    Object attVal = obj.getAttVal(smAtt);
                    if (val.equals(attVal)) {
                        // Matched!
                        results.add(obj);
                    }
                }
            }
        }
        
    }

    /**
     * Get all elements of a given class and the class descendants.
     * @param cls a metaclass.
     * @param withSubClasses if true look in sub classes hierarchy too.
     * @param result all elements of this class will be added here.
     */
    @objid ("006c8c66-0d1e-1f20-85a5-001ec947cd2a")
    public void findByClass(MClass cls, boolean withSubClasses, Collection<? super SmObjectImpl> result) {
        // The search is first done for the metaclass itself
        result.addAll(getMClassCache(cls, false).values());
        
        // and then it must be carried out for all the metaclass derived from'cls'
        if (withSubClasses) {
            for (MClass mc : cls.getSub(true)) {
                result.addAll(getMClassCache(mc, false).values());
            }
        }
        
    }

    /**
     * Find a model object from its MClass and its identifier.
     * <p>
     * Looks in sub classes hierarchy too.
     * @see #findById(MClass, String, boolean) to avoid looking in sub classes
     * @param cls a metaclass
     * @param siteIdentifier an UUID
     * @return the found element or <code>null</code>.
     */
    @objid ("006c8d56-0d1e-1f20-85a5-001ec947cd2a")
    public SmObjectImpl findById(MClass cls, final String siteIdentifier) {
        return findById(cls, siteIdentifier, true);
    }

    /**
     * Find a model object from its MClass and its identifier.
     * @param cls a metaclass
     * @param siteIdentifier an UUID
     * @param lookInsubClasses if true look into subclasses if not found in the given one.
     * @return the found element or <code>null</code>.
     */
    @objid ("aed70f45-a7f1-42ae-b694-f59bde178c67")
    public SmObjectImpl findById(MClass cls, final String siteIdentifier, boolean lookInsubClasses) {
        // The search is first done for the metaclass itself
        SmObjectImpl obj = getMClassCache(cls, false).get(siteIdentifier);
        if (obj != null) {
            return obj;
        }
        
        if (lookInsubClasses) {
            // and then it must be carried out for all the metaclass derived from 'cls'
            for (MClass mc : cls.getSub(true)) {
                obj = getMClassCache(mc, false).get(siteIdentifier);
        
                if (obj != null) {
                    return obj;
                }
            }
        }
        return null;
    }

    /**
     * Find a model object from a reference.
     * <p>
     * Returns <code>null</code> if no object with the given class and identifier is found, or
     * if the metaclass is unknown.
     * @param ref an element reference
     * @return the found element or <code>null</code>.
     */
    @objid ("7d46074c-1c43-11e2-8eb9-001ec947ccaf")
    public MObject findByRef(MRef ref) {
        MClass cls = this.metamodel.getMClass(ref.mc);
        if (cls == null) {
            return null;
        } else {
            return findById(cls, ref.uuid);
        }
        
    }

    /**
     * Get an iterable on all the cache content.
     * <p>
     * The cache should not be modified while walking it to avoid unspecified
     * behavior.
     * @return all the cache content.
     */
    @objid ("bd96ad7d-92d7-11e1-81e9-001ec947ccaf")
    public Iterable<SmObjectImpl> getIterable() {
        return this::getIterator;
    }

    /**
     * Get an iterator on all the cache content.
     * <p>
     * The cache should not be modified while walking it to avoid unspecified
     * behavior.
     * @return all the cache content.
     */
    @objid ("bd96ad84-92d7-11e1-81e9-001ec947ccaf")
    public Iterator<SmObjectImpl> getIterator() {
        return new ContentIterator(this.caches.values().iterator());
    }

    /**
     * @return the metamodel
     */
    @objid ("e56ff4bb-d54f-455e-a64f-e1fbf8e5ee5b")
    public SmMetamodel getMetamodel() {
        return this.metamodel;
    }

    /**
     * Add an object to the cache without duplicate check.
     * <p>
     * @param obj the object to add
     */
    @objid ("f4aa1543-08b1-11e2-b33c-001ec947ccaf")
    public void putToCache(SmObjectImpl obj) {
        String oid = obj.getUuid();
        getMClassCache(obj.getClassOf(), true).put(oid, obj);
        
    }

    /**
     * Remove a model object from the cache.
     * @param obj a model object.
     */
    @objid ("006c8f36-0d1e-1f20-85a5-001ec947cd2a")
    public void removeFromCache(SmObjectImpl obj) {
        getMClassCache(obj.getClassOf(), false).remove(obj.getUuid());
    }

    /**
     * Remove a model object from the cache using its identifier.
     * @param cls the metaclass
     * @param uuid the identifier
     * @since 3.6
     */
    @objid ("2303e101-0753-480f-924d-96419f80edc0")
    public void removeFromCache(MClass cls, String uuid) {
        getMClassCache(cls, false).remove(uuid);
    }

    /**
     * If the specified key is not already associated with a value (or is mapped to null or a dead object),
     * attempts to compute its value using the given mapping function and enters it into this map unless null.
     * 
     * If the mapping function returns null, no mapping is recorded.
     * If the mapping function itself throws an (unchecked) exception, the exception is rethrown, and no mapping is recorded
     * @param cls the metaclass
     * @param uuid the identifier
     * @param supplier the mapping function to supply an SmObjectImpl
     * @return the current (existing or computed) value associated with the specified uuid, or null if the computed value is null
     */
    @objid ("d795e499-617d-4484-ac7c-8cd7e3860577")
    public SmObjectImpl supplyIfAbsent(MClass cls, String uuid, SmObjectSupplier supplier) throws DuplicateObjectException {
        IMClassCache mClassCache = getMClassCache(cls, true);
        try {
            SmObjectImpl ret = mClassCache.compute(uuid, (String oid, SmObjectImpl existing) -> {
                try {
                    if (existing == null) {
                        return supplier.get();
                    }
                    try {
                        // Existing found, ensure it is alive
                        existing.getData();
                        return existing;
                    } catch (@SuppressWarnings ("unused") DeadObjectException e) {
                        // Here we know existing is a dead object, replace existing obj in the cache.
                        return supplier.get();
                    }
                } catch (DuplicateObjectException e) {
                    throw new CompletionException(e);
                }
            });
        
            return ret;
        } catch (CompletionException e) {
            throw (DuplicateObjectException) e.getCause();
        }
        
    }

    /**
     * Get the cache for the given metaclass.
     * <p>
     * If the cache is not found and <code>createMissing</code> is <code>true</code>, creates
     * a new cache and returns it. If <code>createMissing</code> is <code>false</code>
     * returns a non modifiable empty cache.
     * <p>
     * Nearly all methods of this class should call this method to access the {@link #caches} member.
     * @param key a metamodel class
     * @param createMissing <code>true</code> to create a cache if missing, <code>false</code> to return a dummy one.
     * @return the metaclass cache.
     */
    @objid ("0acd6b4f-ffe4-4c41-ab49-69f5f16fd3ec")
    private IMClassCache getMClassCache(final MClass key, boolean createMissing) {
        if (createMissing) {
            return this.caches.computeIfAbsent(key, k -> new MClassCache());
        } else {
            return this.caches.getOrDefault(key, EmptyClassCache.INSTANCE);
        }
        
    }

    /**
     * Iterator used to walk the whole cache.
     * <p>
     * The cache should not be modified while walking it to avoid unspecified
     * behavior.
     */
    @objid ("bd9b722b-92d7-11e1-81e9-001ec947ccaf")
    private static class ContentIterator implements Iterator<SmObjectImpl> {
        @objid ("bd9b722d-92d7-11e1-81e9-001ec947ccaf")
        private final Iterator<IMClassCache> cacheIt;

        @objid ("bd9b7230-92d7-11e1-81e9-001ec947ccaf")
        private Iterator<SmObjectImpl> entryIt;

        @objid ("bd9b7233-92d7-11e1-81e9-001ec947ccaf")
        public  ContentIterator(Iterator<IMClassCache> classCacheIt) {
            this.cacheIt = classCacheIt;
            this.entryIt = null;
            
        }

        @objid ("bd9b7235-92d7-11e1-81e9-001ec947ccaf")
        @Override
        public boolean hasNext() {
            if (this.entryIt != null && this.entryIt.hasNext()) {
                return true;
            }
            return moveCacheIt();
        }

        @objid ("bd9b723a-92d7-11e1-81e9-001ec947ccaf")
        @Override
        public SmObjectImpl next() {
            if (this.entryIt != null && this.entryIt.hasNext()) {
                return this.entryIt.next();
            }
            
            if (moveCacheIt()) {
                return this.entryIt.next();
            } else {
                throw new NoSuchElementException();
            }
            
        }

        @objid ("bd9b723e-92d7-11e1-81e9-001ec947ccaf")
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @objid ("03b2d6f5-d7d1-11e1-adbb-001ec947ccaf")
        private boolean moveCacheIt() {
            while (this.cacheIt.hasNext()) {
                IMClassCache cacheVal = this.cacheIt.next();
                if (cacheVal != null) {
                    this.entryIt = cacheVal.values().iterator();
                    if (this.entryIt.hasNext()) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    /**
     * Empty Map<UUID,SmObjectImpl> implementing {@link IMClassCache}.
     * <p>
     * This is an immutable singleton whose remove(...) method are no-op.
     * It is used as stub "null" pointer to avoid null checks in caller
     */
    @objid ("80504461-413d-4c89-83b6-b42180f2961e")
    private static class EmptyClassCache extends AbstractMap<String, SmObjectImpl> implements IMClassCache {
        @objid ("497e8dfc-93ba-4d69-b587-5bd6f300994d")
        static final IMClassCache INSTANCE = new EmptyClassCache();

        @objid ("b74871f8-9f04-421e-82e6-91a4c0112d61")
        private  EmptyClassCache() {
            // noop
        }

        @objid ("7f5852f6-cbf1-4b78-a58e-f1c8665a2413")
        @Override
        public int size() {
            return 0;
        }

        @objid ("22c4f73b-d741-40af-9f83-60cb171870a3")
        @Override
        public boolean isEmpty() {
            return true;
        }

        @objid ("8e3f9ed6-4682-4839-8d8a-23e4a3f076f7")
        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @objid ("bff80491-fd2c-4e54-9d30-8d1be52202b8")
        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @objid ("ca276e57-8963-4111-b7fc-ef836d68a3d3")
        @Override
        public SmObjectImpl get(Object key) {
            return null;
        }

        @objid ("7a5d7033-9962-467f-92e2-4e2e008c9ae4")
        @Override
        public Set<String> keySet() {
            return Collections.emptySet();
        }

        @objid ("a3178498-8b16-40b5-af47-79b9b533bdbb")
        @Override
        public Collection<SmObjectImpl> values() {
            return Collections.emptySet();
        }

        @objid ("d25852bd-01de-4f97-8149-37796700f079")
        @Override
        public Set<java.util.Map.Entry<String, SmObjectImpl>> entrySet() {
            return Collections.emptySet();
        }

        @objid ("f8052003-165e-4dae-a539-2f3aff97ab48")
        @Override
        public boolean equals(Object o) {
            return (o instanceof IMClassCache) && ((IMClassCache)o).isEmpty();
        }

        @objid ("2f6d1ad7-b474-4dc0-9602-e19e697e8ca1")
        @Override
        public int hashCode() {
            return 0;
        }

        @objid ("03116ab9-1df3-46d2-9f63-3f0a39edfb2b")
        @Override
        public SmObjectImpl putIfAbsent(String key, SmObjectImpl value) {
            throw new UnsupportedOperationException();
        }

        @objid ("578a997e-d849-4b1f-b481-80d9074d8604")
        @Override
        public boolean remove(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @objid ("1a3641d6-b2d5-46b4-b84b-2706d267dd0f")
        @Override
        public boolean replace(String key, SmObjectImpl oldValue, SmObjectImpl newValue) {
            throw new UnsupportedOperationException();
        }

        @objid ("c7676202-8770-4db2-bf34-8516deaed152")
        @Override
        public SmObjectImpl replace(String key, SmObjectImpl value) {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * Typedef to Map<UUID,SmObjectImpl> .
     */
    @objid ("eeabc200-5921-48a9-9945-775681defcde")
    private interface IMClassCache extends ConcurrentMap<String, SmObjectImpl> {
// nothing more
        }
    

    /**
     * Typedef to ConcurrentHashMap<UUID,SmObjectImpl> implementation of {@link IMClassCache}.
     */
    @objid ("009869a8-702b-1f21-85a5-001ec947cd2a")
    private static class MClassCache extends ConcurrentHashMap<String, SmObjectImpl> implements IMClassCache {
        @objid ("00285e38-4fda-1f32-b43f-001ec947cd2a")
        private static final long serialVersionUID = 1L;

        @objid ("00287184-4fda-1f32-b43f-001ec947cd2a")
        public  MClassCache() {
            super(50, 0.8f, 1);
        }

    }

    @objid ("c328a222-2a7f-427e-95f9-ffb726e585e4")
    @FunctionalInterface
    public interface SmObjectSupplier {
        @objid ("21964988-dbb5-4d98-a949-8224f72f7acc")
        SmObjectImpl get() throws DuplicateObjectException;
}
    

}
