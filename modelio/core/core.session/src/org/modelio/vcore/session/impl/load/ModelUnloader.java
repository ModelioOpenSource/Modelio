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
package org.modelio.vcore.session.impl.load;

import java.util.Collection;
import java.util.HashSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.cache.CacheManager;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Helper class that deletes a model object.
 */
@objid ("4eb8232a-748c-4aeb-9fd4-9aa9fa37969b")
public class ModelUnloader {
    @objid ("de692b94-e81c-4db0-8d4f-37ee54d8f001")
    private CacheManager cacheManager;

    /**
     * Initialize the unloader.
     * @param cacheManager the session cache manager
     */
    @objid ("58853ff2-8723-43ec-a7b9-b1cb1c94bb71")
    public  ModelUnloader(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Delete the given model object
     * @param objToUnload the object to delete.
     */
    @objid ("6d085ec5-f2a7-4a26-8916-338742e9529b")
    public void unload(Collection<SmObjectImpl> objToUnload) {
        // Compute all objects to unload
        Collection<SmObjectImpl> toUnload = new HashSet<>();
        Collection<SmObjectImpl> toReload = new HashSet<>();
        
        for (SmObjectImpl obj : objToUnload) {
            getAllLoadedComponents(obj, toUnload);
        }
        
        // Set all objects as being deleted
        for (SmObjectImpl obj : toUnload) {
            obj.getData().setRFlags(IRStatus.BEINGDELETED, 0, 0);
        }
        
        // Look for all loaded objects having pointer on
        // objects to unload, they must be reloaded.
        for (SmObjectImpl obj : toUnload) {
            SmClass cls = obj.getClassOf();
            for (final SmDependency dep : cls.getAllDepDef()) {
                SmDependency opposite = dep.getSymetric();
                if (opposite != null) {
                    for (SmObjectImpl target : dep.getValueAsCollection(obj.getData())) {
                        if (! toUnload.contains(target)) {
                            // target must be reloaded
                            //target.getMetaOf().eraseObjDepVal(target, opposite, obj);
                            target.getRepositoryObject().setToReload(target);
                            if( toReload.add(target)) {
                                //Log.trace("ModelUnloader: %s is to reload because of %s is being unloded.", target, obj);
                            }
                        }
                    }
                }
            }
        }
        
        // Unload and forget all objects to unload
        for (SmObjectImpl obj : toUnload) {
            //Log.trace("ModelUnloader: unloading %s.", obj);
            obj.getRepositoryObject().unload(obj);
            obj.getData().setRFlags(IRStatus.DELETED, IRStatus.BEINGDELETED, 0);
            this.cacheManager.removeFromCache(obj);
        }
        
    }

    @objid ("6d27ca74-bc0c-406c-9388-8a7d9834d1ef")
    private void getAllLoadedComponents(SmObjectImpl obj, Collection<SmObjectImpl> toUnload) {
        toUnload.add(obj);
        
        final SmClass cls = obj.getClassOf();
        for (final SmDependency dep : cls.getAllDepDef()) {
            if (dep.isToDelete()) {
                for (SmObjectImpl c : dep.getValueAsCollection(obj.getData())) {
                    if (!toUnload.contains(c)) {
                        getAllLoadedComponents(c, toUnload);
                    }
                }
            }
        }
        
    }

}
