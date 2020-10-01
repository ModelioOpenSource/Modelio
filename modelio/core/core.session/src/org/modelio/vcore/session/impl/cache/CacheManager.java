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

package org.modelio.vcore.session.impl.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.model.MObjectCache;
import org.modelio.vcore.session.impl.load.UnloadedRepositoryHandle;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectDataCache;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.SmStatus;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Cache for already loaded objects in a modeling session.
 * <p>
 * Also caches in memory {@link ISmObjectData} and maintains the deleted objects list.
 */
@objid ("9c73fce8-354d-11e2-985b-001ec947ccaf")
public class CacheManager extends MObjectCache implements ISmObjectDataCache {
    @objid ("0195cecd-bed3-4fb9-89ae-8b857071f78e")
    private final Collection<SmObjectImpl> deletedObjects;

    @objid ("bee76f3e-82d9-4af3-a6d5-a957d6bef951")
    private final Map<String, ISmObjectData> dataCache;

    /**
     * Creates a new cache.
     * @param metamodel
     */
    @objid ("9c73fcee-354d-11e2-985b-001ec947ccaf")
    public CacheManager(SmMetamodel metamodel) {
        super(metamodel);
        this.deletedObjects = new HashSet<>();
        this.dataCache = new ConcurrentHashMap<>(1000, 0.85f, 1);
        
        MemoryManager.get().addManagedCache(this.dataCache);
    }

    /**
     * Add an object to the cache.
     * 
     * @param obj the object to add
     * @throws org.modelio.vcore.model.DuplicateObjectException if another object with the same identifier is already in the cache.
     */
    @objid ("9c73fcef-354d-11e2-985b-001ec947ccaf")
    @Override
    public synchronized void addToCache(SmObjectImpl obj) throws DuplicateObjectException {
        // Look for a duplicate object whatever its MClass is.
        final String oid = obj.getUuid();
        final ISmObjectData oldData = getCachedData(oid);
        if (oldData!= null && oldData != obj.getData()) {
            SmObjectImpl oldObj = findById(oldData.getClassOf(), oid);
            if (oldObj == null) {
                // No SmObject for the duplicate SmData !
                // Create one for the occasion.
                Log.error("CacheManager: Orphan duplicate %s found in cache: {%s} %s, status=(%s), repo handle=%s.",
                        oldData.getClass().getSimpleName(), 
                        oldData.getUuid(), 
                        oldData.getClassOf(),
                        SmStatus.toString(oldData.getStatus()),
                        oldData.getRepositoryObject());
                oldObj = oldData.getClassOf().getObjectFactory().createImpl();
                oldObj.init(oid, oldData.getLiveId());
                oldObj.initData(oldData);
            }
        
            throw new DuplicateObjectException(oid, oldObj, obj);
        }
        
        // Add to cache
        super.addToCache(obj);
    }

    /**
     * Register a deleted object.
     * 
     * @param obj a deleted object
     */
    @objid ("9c73fcf0-354d-11e2-985b-001ec947ccaf")
    public final void addToDeleted(SmObjectImpl obj) {
        synchronized(this.deletedObjects) {
            this.deletedObjects.add(obj);
        }
    }

    /**
     * Clear the deleted objects list.
     */
    @objid ("9c73fcf1-354d-11e2-985b-001ec947ccaf")
    public void clearDeletedObjects() {
        synchronized(this.deletedObjects) {
            for (SmObjectImpl  deleted: this.deletedObjects) {
                removeFromCache(deleted);
            }
        
            this.deletedObjects.clear();
        }
    }

    /**
     * Get the deleted objects.
     * 
     * @return the deleted objects.
     */
    @objid ("9c73fcf5-354d-11e2-985b-001ec947ccaf")
    public Collection<SmObjectImpl> getDeletedObjects() {
        return this.deletedObjects;
    }

    /**
     * Remove a model object and its {@link ISmObjectData data} from the cache.
     * <p>
     * <b>Note:</b> The Modelio memory model will make this call result in the object {@link ISmObjectData data} being
     * inaccessible (because referenced by weak references) then garbaged definitively from the VM.
     */
    @objid ("9c73fcf6-354d-11e2-985b-001ec947ccaf")
    @Override
    public synchronized void removeFromCache(SmObjectImpl obj) {
        super.removeFromCache(obj);
        this.dataCache.remove(obj.getUuid());
    }

    /**
     * Remove an object from the deleted objects list.
     * 
     * @param obj a not deleted anymore object.
     */
    @objid ("9c73fcf7-354d-11e2-985b-001ec947ccaf")
    public void removeFromDeleted(SmObjectImpl obj) {
        synchronized (this.deletedObjects) {
            this.deletedObjects.remove(obj);
        }
    }

    @objid ("9c73fcfb-354d-11e2-985b-001ec947ccaf")
    @Override
    public ISmObjectData getCachedData(final String uid) {
        return this.dataCache.get(uid);
    }

    @objid ("9c73fcfc-354d-11e2-985b-001ec947ccaf")
    @Override
    public void putDataToCache(final ISmObjectData data) {
        if (data.getRepositoryObject() instanceof UnloadedRepositoryHandle) {
            throw new AssertionError(
                    String.format("Trying to add deleted %s data to cache: {%s} %s, status=(%s), repo handle=%s, metaof=%s.",
                            data.getClass().getSimpleName(), 
                            data.getUuid(), 
                            data.getClassOf(),
                            SmStatus.toString(data.getStatus()),
                            data.getRepositoryObject(),
                            data.getMetaOf()));
        }
            
        this.dataCache.put(data.getUuid(), data);
        
        
        /*if (findById(data.getClassOf(), data.getUuid()) == null) {
            // no impl object in cache, create one
            SmObjectImpl impl = data.getClassOf().getObjectFactory().createImpl();
            impl.init(data.getUuid(), data.getLiveId());
            impl.initData(data);
        }*/
    }

    /**
     * To be called when this cache manager is not used anymore.
     * <p>
     * Release the cache manager resources.
     */
    @objid ("94392bef-04cf-497c-9731-3dec28723976")
    public void dispose() {
        MemoryManager.get().removeManagedCache(this.dataCache);
    }

    @objid ("2a4d3b63-1d29-45c6-8362-3da4c76fb3f1")
    @Override
    protected void finalize() throws Throwable {
        dispose();
        
        super.finalize();
    }

    /**
     * Remove a model object and its {@link ISmObjectData data} from the cache.
     * <p>
     * <b>Note:</b> The Modelio memory model will make this call result in the object {@link ISmObjectData data} being
     * inaccessible (because referenced by weak references) then garbaged definitively from the VM.
     * @since 3.6
     */
    @objid ("6b7d8625-0126-47d0-a5f6-9c1d0c103fca")
    @Override
    public void removeFromCache(MClass cls, String uuid) {
        super.removeFromCache(cls, uuid);
        this.dataCache.remove(uuid);
    }

}
