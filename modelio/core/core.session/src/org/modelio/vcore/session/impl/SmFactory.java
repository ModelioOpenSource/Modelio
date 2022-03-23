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
package org.modelio.vcore.session.impl;

import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.IMetaOf;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectDataCache;
import org.modelio.vcore.smkernel.SmLiveId;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.SmStatusFactory;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Model object factory.
 */
@objid ("0052d122-6f75-1f22-8c06-001ec947cd2a")
public class SmFactory {
    @objid ("00071fc0-eb1c-1f22-8c06-001ec947cd2a")
    private final short kid;

    @objid ("000727c2-eb1c-1f22-8c06-001ec947cd2a")
    private final IMetaOf metaObject;

    @objid ("726bb90c-a419-11e1-aa98-001ec947ccaf")
    private final IModel modelFinder;

    @objid ("7b38cf31-b0f1-4b1a-99ec-b87d1019fee8")
    private final ISmObjectDataCache dataCache;

    @objid ("27b0d7d6-b1ef-4b0e-8fe7-4e95ec24a353")
    private IRepository shellRepository;

    /**
     * Package private constructor.
     * <p>
     * <code>SmFactory</code> is built by the {@link CoreSession} only.
     * @param kid the kernel id
     * @param metaObject the default meta object for new model objects.
     * @param modelFinder a model object finder.
     */
    @objid ("0054e2dc-6f75-1f22-8c06-001ec947cd2a")
     SmFactory(final short kid, final IMetaOf metaObject, final IModel modelFinder, ISmObjectDataCache dataCache, IRepository shellRepository) {
        this.kid = kid;
        this.metaObject = metaObject;
        this.modelFinder = modelFinder;
        this.dataCache = dataCache;
        this.shellRepository = shellRepository;
        
    }

    /**
     * Create a new model object.
     * @param metaclass the model class .
     * @param repository the repository to attach the object
     * @return the new object
     */
    @objid ("0007e892-eb1c-1f22-8c06-001ec947cd2a")
    public SmObjectImpl createObject(final SmClass metaclass, final IRepository repository) {
        return createObject(metaclass, repository, null);
    }

    /**
     * Create a model object with a given String.
     * <p>
     * To be used by the model import.
     * @param metaclass the model class .
     * @param repository the repository to attach the object
     * @param forcedUuid the object identifier. If null an object identifier will be allocated and the object will be considered as new in
     * the whole world/universe.
     * @return the new object
     */
    @objid ("000846ca-eb1c-1f22-8c06-001ec947cd2a")
    public SmObjectImpl createObject(final SmClass metaclass, final IRepository repository, final String forcedUuid) {
        String uuid = forcedUuid;
        
        if (forcedUuid != null) {
            // First look for an existing object.
            SmObjectImpl ret = (SmObjectImpl) this.modelFinder.findById(metaclass, forcedUuid);
            if (ret != null) {
                // Object found with same identifier
                if (ret.hasStatus(IRStatus.SHELL)) {
                    // Set the shell object as resolved and move it to the right repository
                    ret.getData().setRFlags(0, IRStatus.SHELL, 0);
        
                    if (ret.getRepositoryObject().getRepositoryId() != repository.getRepositoryId()) {
                        ret.getRepositoryObject().detach(ret);
                        repository.addObject(ret);
                        long liveId = SmLiveId.make(this.kid, repository.getRepositoryId(), metaclass.getId());
                        ret.init(forcedUuid, liveId);
                    }
        
                    return ret;
                } else {
                    // Duplicate object found: refuse the operation
                    throw new IllegalArgumentException(ret + " already exists in the project.");
                }
            }
        } else {
            // Allocate a new UUId and convert it to String.
            uuid = UUID.randomUUID().toString();
        }
        
        // No existing object, create a new one
        {
            boolean isNewInWorld = forcedUuid == null;
        
            // Create the object
            ISmObjectData data = metaclass.getObjectFactory().createData();
            SmObjectImpl newObject = metaclass.getObjectFactory().createImpl();
            newObject.initData(data);
        
            // Set the object SmIdentifier (a new value from the identAllocator)
            long liveId = SmLiveId.make(this.kid, repository.getRepositoryId(), metaclass.getId());
            newObject.init(uuid, liveId);
        
            // initialize the status
            SmStatusFactory.resetRStatus(data);
            SmStatusFactory.resetPStatus(data);
        
            // Set a meta object (SeHandle for transaction management)
            newObject.setMetaOf(this.metaObject);
        
            // Add to data cache before 'data' is out of scope
            this.dataCache.putDataToCache(data);
        
            if (isNewInWorld) {
                // Add to the repository
                repository.addCreatedObject(newObject);
        
                // Triggers the meta object
                this.metaObject.createObject(newObject);
            } else {
                // Add to the repository
                repository.addObject(newObject);
        
                // Triggers the meta object
                this.metaObject.importObject(newObject);
            }
        
            return newObject;
        }
        
    }

    /**
     * Create an object reference.
     * <p>
     * If an object with the same class and String exists, it is directly returned. In the other case a shell object is created and
     * returned.
     * <p>
     * To be used by the model import.
     * @param metaclass The object metaclass
     * @param uuid the object identifier
     * @param name the object name, may be <code>null</code>
     * @return the created shell object
     */
    @objid ("aff295d4-8e82-4d8c-b1ab-0c7b02db024a")
    public SmObjectImpl getObjectReference(final SmClass metaclass, final String uuid, final String name) {
        MObject ref = this.modelFinder.findById(metaclass, uuid);
        if (ref != null) {
            return (SmObjectImpl) ref;
        }
        return createShellObject(metaclass, this.shellRepository, uuid, name);
    }

    /**
     * Create a shell object.
     * @param metaclass The object metaclass
     * @param repository The repository containing the object
     * @param uuid the object identifier
     * @param name the object name, may be <code>null</code>
     * @return the created shell object
     */
    @objid ("005d305e-fd1a-1f27-a7da-001ec947cd2a")
    private SmObjectImpl createShellObject(final SmClass metaclass, final IRepository repository, final String uuid, final String name) {
        // Create the object
        ISmObjectData data = metaclass.getObjectFactory().createData();
        SmObjectImpl newObject = metaclass.getObjectFactory().createImpl();
        newObject.initData(data);
        
        // Set the object SmIdentifier (a new value from the identAllocator)
        long liveId = SmLiveId.make(this.kid, repository.getRepositoryId(), metaclass.getId());
        newObject.init(uuid, liveId);
        
        // Set the object name if given (before marking it shell)
        // also bypasses the metaobject
        if (name != null) {
            newObject.getClassOf().getAttributeDef("Name").setValue(data, name);
        }
        
        // Set a meta object (SeHandle for transaction management)
        newObject.setMetaOf(this.metaObject);
        
        // Add to data cache before 'data' is out of scope
        this.dataCache.putDataToCache(data);
        
        // Add the object to the repository
        repository.addObject(newObject);
        
        // Triggers the meta object
        this.metaObject.importObject(newObject);
        
        // Mark it shell
        data.setRFlags(IRStatus.SHELL, 0, 0);
        return newObject;
    }

}
