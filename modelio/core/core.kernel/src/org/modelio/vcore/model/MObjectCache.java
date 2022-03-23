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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
    private ConcurrentMap<MClass, IMClassCache> caches = null;

    @objid ("08448b81-ff4c-474f-a38c-020b42d67aca")
    private SmMetamodel metamodel;

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
        // Put element to cache and check for duplicate identifiers
        SmObjectImpl oldObj = getMClassCache(obj.getClassOf(), true).putIfAbsent(oid, obj);
        if (oldObj != null && oldObj != obj) {
            // Duplicate found: throw exception.
            throw new DuplicateObjectException(oid, oldObj, obj);
        }
        
    }

    /**
     * Get the cache content as a collection.
     * <p>
     * The returned collection is a view on the cache and reflects all changes made on it.
     * The cache should not be modified while walking the returned collection to avoid unspecified
     * behavior.
     * @return all the cache content.
     */
    @objid ("f4aa154a-08b1-11e2-b33c-001ec947ccaf")
    public Collection<SmObjectImpl> asCollection() {
        final Map<MClass, IMClassCache> theCaches = MObjectCache.this.caches;
        return new AbstractCollection<SmObjectImpl>() {
                                                        
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
        return new Iterable<SmObjectImpl>() {
                                                                    @Override
                                                                    public Iterator<SmObjectImpl> iterator() {
                                                                return getIterator();
                                                                            }
                                                                        };
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
        IMClassCache found = this.caches.get(key);
        
        if (found == null) {
            if (createMissing) {
                MClassCache newCache = new MClassCache();
                found = this.caches.putIfAbsent(key, newCache);
                if (found == null) {
                    found = newCache;
                }
            } else {
                return EmptyClassCache.INSTANCE;
            }
        }
        return found;
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
     * This is an immutable singleton.
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
    private interface IMClassCache extends ConcurrentMap<String, SmObjectImpl> {// nothing more
        
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

}
