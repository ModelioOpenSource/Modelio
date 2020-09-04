/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vcore.session.impl.mm;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.IMetamodelSupport;
import org.modelio.vcore.session.api.metamodel.IMetamodelListener;
import org.modelio.vcore.session.impl.cache.CacheManager;
import org.modelio.vcore.session.impl.load.ModelUnloader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * {@link IMetamodelSupport} service implementation.
 */
@objid ("582e2da2-9a00-4967-a841-622bcb7aabeb")
public class MetamodelSupport implements IMetamodelSupport {
    @objid ("2167a865-6406-4d6e-b38d-3af780124926")
    private final CacheManager cacheManager;

    @objid ("01b734a3-9e56-41b1-a88d-13d21535e067")
    private final SmMetamodel metamodel;

    @objid ("b3107870-6cfc-4ce4-a807-059c95c92eb4")
    private final List<IMetamodelListener> listeners = new CopyOnWriteArrayList<>();

    /**
     * Initialize the service.
     * 
     * @param cacheManager the cache of all loaded objects
     * @param metamodel the metamodel
     */
    @objid ("08e8d00a-7e1a-4694-a1e3-77424f6e28e5")
    public MetamodelSupport(CacheManager cacheManager, SmMetamodel metamodel) {
        this.cacheManager = cacheManager;
        this.metamodel = metamodel;
    }

    /**
     * Add a new metamodel fragment.
     * 
     * @param mmFragment the metamodel fragment to add.
     */
    @objid ("6c407264-83b5-4d15-ab26-b76127434bad")
    @Override
    public void addMetamodelFragment(ISmMetamodelFragment mmFragment) {
        Collection<SmClass> removedFakes = this.metamodel.addMetamodelFragment(mmFragment);
        
        // Unload all instances of the removed metaclasses.
        unloadMetaclassInstances(removedFakes);
        
        // fire listeners
        for (IMetamodelListener l : this.listeners) {
            l.metamodelFragmentAdded(this.metamodel, mmFragment);
        }
    }

    /**
     * Remove a metamodel fragment and unload all model objects
     * typed by a metaclass defined by the fragment.
     * 
     * @param removedMm the metamodel fragment to remove
     */
    @objid ("6ab1ff1b-8e22-4331-bf96-5c2dc39e144e")
    @Override
    public void removeMetamodelFragment(ISmMetamodelFragment removedMm) {
        // fire listeners
        for (IMetamodelListener l : this.listeners) {
            l.removingMetamodelFragment(this.metamodel, removedMm);
        }
        
        Collection<SmClass> mclassesToUnload = this.metamodel.getRegisteredMClasses(removedMm);
        
        // Unload all instances of the given metaclasses.
        unloadMetaclassInstances(mclassesToUnload);
        
        // Unregister the metamodel fragment
        this.metamodel.removeFragment(removedMm);
        
        
        // fire listeners
        for (IMetamodelListener l : this.listeners) {
            l.metamodelFragmentRemoved(this.metamodel, removedMm);
        }
    }

    /**
     * Unload all instances of the given metaclasses.
     * 
     * @param mclassesToUnload the metaclasses to unload
     */
    @objid ("7e645dd2-94ec-411a-b5df-5fe316d5117f")
    private void unloadMetaclassInstances(Collection<SmClass> mclassesToUnload) {
        // Compute all objects to unload
        Collection<SmObjectImpl> toUnload = new HashSet<>();
        for (SmClass smClass : mclassesToUnload) {
            this.cacheManager.findByClass(smClass, false, toUnload);
        }
        
        // Unload the objects
        new ModelUnloader(this.cacheManager).unload(toUnload);
    }

    /**
     * <p>Add a listener that will be triggered when a metamodel fragment is added or removed.</p>
     * 
     * @param listener the metamodel fragment to add.
     */
    @objid ("5d02017f-aff0-4f9e-9562-42c1e8c6900e")
    @Override
    public void addMetamodelListener(IMetamodelListener listener) {
        this.listeners.add(listener);
    }

    /**
     * @param listener the metamodel fragment to add.
     */
    @objid ("0a40f505-c2da-4271-8c81-15fb599fae67")
    @Override
    public void removeMetamodelListener(IMetamodelListener listener) {
        this.listeners.remove(listener);
    }

}
