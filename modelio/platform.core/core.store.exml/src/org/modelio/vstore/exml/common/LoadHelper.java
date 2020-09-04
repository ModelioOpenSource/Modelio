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

package org.modelio.vstore.exml.common;

import java.util.Locale;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.model.GetAbsoluteSymbol;
import org.modelio.vcore.model.MObjectCache;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.StatusState;
import org.modelio.vcore.smkernel.mapi.MetaclassAlreadyExistException;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.IllegalReferenceException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.model.ObjIdName;

/**
 * {@link ILoadHelper} implementation.
 */
@objid ("ab96a3a8-2e6d-11e2-8aaa-001ec947ccaf")
public class LoadHelper implements ILoadHelper {
    @objid ("6784127e-2e7b-11e2-8aaa-001ec947ccaf")
    protected boolean loadReadWrite;

    @objid ("202e4da2-99e2-4041-90a2-446e3f7afafe")
    private final IExmlBase exmlBase;

    @objid ("730051ba-4077-4c7d-8905-1a23ec79e2bf")
    private final MObjectCache loadCache;

    @objid ("ca906cfb-0af6-494b-b57d-a2beb8a28ea7")
    private final SmMetamodel metamodel;

    /**
     * Initialize the loading helper.
     * 
     * @param exmlBase the EXML repository
     * @param loadReadWrite <code>true</code> if the repository is read/write, <code>false</code> if read only.
     */
    @objid ("67841280-2e7b-11e2-8aaa-001ec947ccaf")
    public LoadHelper(IExmlBase exmlBase, boolean loadReadWrite) {
        this.exmlBase = exmlBase;
        this.loadCache = exmlBase.getLoadCache();
        this.loadReadWrite = loadReadWrite;
        this.metamodel = exmlBase.getModelLoaderProvider().getMetamodel();
    }

    @objid ("678412b7-2e7b-11e2-8aaa-001ec947ccaf")
    @Override
    public ExmlStorageHandler createStorageHandler(SmObjectImpl cmsNode, boolean isNodeLoaded) {
        return this.exmlBase.createStorageHandler(cmsNode, isNodeLoaded);
    }

    @objid ("81856cf6-f901-473c-a7a4-3f29bcd6c901")
    @Override
    public SmObjectImpl createStubCmsNode(IModelLoader modelLoader, ObjId id, String defaultName) throws DuplicateObjectException {
        assert (id.classof.isCmsNode()) : id.classof + " is not cms node.";
        assert (this.loadCache.findById(id.classof, id.id) == null) : id + " is already in load cache";
        
        SmObjectImpl newObject;
        // Create a not yet loaded ref.
        newObject = modelLoader.createLoadedObject(id.classof, id.id);
        
        // Set the name now
        setObjectName(modelLoader, newObject, getNameFromIndex(id, defaultName )); 
              
        // Add new object to the loaded objects cache
        this.loadCache.putToCache(newObject);
              
        newObject.setRepositoryObject(createStorageHandler(newObject, false));
        return newObject;
    }

    @objid ("67841285-2e7b-11e2-8aaa-001ec947ccaf")
    @Override
    public final SmObjectImpl createObject(IModelLoader modelLoader, final ObjId id, ExmlStorageHandler handler) throws DuplicateObjectException {
        SmObjectImpl newObject = modelLoader.createLoadedObject(id.classof, id.id);
        
        // Add new object to the loaded objects cache
        this.loadCache.putToCache(newObject);
        
        // set the storage handler
        if (handler != null) {
            newObject.setRepositoryObject(handler);
        }
        
        // Object flags will be initialized by readATT(...)
        return newObject;
    }

    @objid ("e809ae47-041e-4f20-a858-35f519f89e41")
    @Override
    public SmObjectImpl createStubObject(IModelLoader modelLoader, ObjIdName idn, boolean loadNameFromIndex) throws DuplicateObjectException, IllegalReferenceException, IndexException {
        SmObjectImpl newObject;
        
        try {
        
            // Need to load the element
            if (idn.classof.isCmsNode()) {
                // The element is a CMS node.
        
                newObject = createStubCmsNode(modelLoader, idn.toObjId(), idn.name);
            } else {
                // The element is not a CMS node.
        
                // Get the CMS node containing the element
                final ObjId parentId = this.exmlBase.getCmsNodeIndex().getCmsNodeOf(idn.toObjId());
                
                // If no parent, the element does not exist in the repository
                if (parentId == null) {
                    throw new IllegalReferenceException("No CMS node contains "+idn+" in the repository.");
                } else if (! parentId.classof.isCmsNode()) {
                    Log.warning("EXML LoadHelper: The metaclass of %s parent node of %s is not a CMS node", parentId, idn);
                }
                
                // Get the storage handler from the parent CMS node
                SmObjectImpl parentNode = getLoadedObject(parentId);
                if (parentNode == null) {
                    // Create ref to the parent CMS node
                    parentNode = createStubCmsNode(modelLoader, parentId, null);
                
                    // assert the parent node is in the same repository
                    assert (parentNode.getRepositoryObject().getRepositoryId() == this.exmlBase.getRepositoryId());
                }
                
        
                // Create a not yet loaded ref.
                newObject = modelLoader.createLoadedObject(idn.classof, idn.id);
        
                newObject.setRepositoryObject( parentNode.getRepositoryObject());
                
                // Set the name now
                String objName = loadNameFromIndex ? getNameFromIndex(idn.toObjId(), idn.name) : idn.name;
                setObjectName(modelLoader, newObject, objName);
        
                // Add new object to the loaded objects cache
                this.loadCache.putToCache(newObject);
            }
        
            assert (newObject.getRepositoryObject().getRepositoryId() == this.exmlBase.getRepositoryId());
        
            return newObject;
        
        } catch (IndexException e) {
            this.exmlBase.setIndexesDamaged(e);
            throw e;
        }
    }

    @objid ("678412a6-2e7b-11e2-8aaa-001ec947ccaf")
    @Override
    public final void doLoadAtt(IModelLoader modelLoader, final SmObjectImpl obj, final String xattName, String attValue) {
        SmAttribute att = obj.getClassOf().getAttributeDef (xattName);
        if (att == null) {
            // TODO: Report an error
        } else {
            final Class<?> attType = att.getType();
            if (attValue == null) {
                // TODO report something ??
            } else if (attType == UUID.class) {
                // Identifier is already set, don't read it twice and don't reidentify elements.
            } else if (attType == String.class) {
                modelLoader.loadAttribute(obj, att, attValue);
            } else if (attValue.isEmpty()) {
                // No value given, problem
            } else if (attType == Integer.class) {
                modelLoader.loadAttribute(obj, att, Integer.valueOf(attValue));
            } else if (attType == Long.class) {
                modelLoader.loadAttribute(obj, att, Long.valueOf(attValue));
            } else if (attType == Float.class) {
                modelLoader.loadAttribute(obj, att, Float.valueOf(attValue));
            } else if (attType == Double.class) {
                modelLoader.loadAttribute(obj, att, Double.valueOf(attValue));
            } else if (attType == Boolean.class) {
                modelLoader.loadAttribute(obj, att, Boolean.valueOf(attValue));
            } else if (attType.isEnum()) {
                // att is an enum
                if (attValue.isEmpty()) {
                    reportProblem( obj, "Empty value for "+attType.getSimpleName()+" "+att.getName()+" attribute.");
                } else {
                    try {
                        modelLoader.loadAttribute(obj, att, getEnumValue(attValue, attType));
                    } catch (IllegalArgumentException ex) {
                        reportProblem( obj, "Illegal value for "+attType.getSimpleName()+" "+att.getName()+" attribute:"+ex.getLocalizedMessage());
                    }
                }
            } else {
                // Unknown type
                //this.modelLoader.loadAttribute(data, att, attValueBuilder);
                reportProblem( obj, "Unknown type:"+attType.getSimpleName()+" for "+att.getName()+" attribute.");
            }
        }
    }

    @objid ("e6be93c2-cae0-4fa0-87b6-22b3364bd2e1")
    @Override
    public ObjId getCmsNodeId(ObjId id) throws IndexException {
        return this.exmlBase.getCmsNodeIndex().getCmsNodeOf(id);
    }

    @objid ("6784128d-2e7b-11e2-8aaa-001ec947ccaf")
    @Override
    public SmObjectImpl getForeignObject(IModelLoader modelLoader, final ObjIdName id) {
        return modelLoader.loadForeignObject(id.classof, id.id, id.name);
    }

    @objid ("67841295-2e7b-11e2-8aaa-001ec947ccaf")
    @Override
    public final SmObjectImpl getLoadedObject(final ObjId id) {
        if (id.id == null) {
            return null;
        }
        
        SmObjectImpl ret = this.loadCache.findById(id.classof, id.id);
        
        if (ret == null) {
            return null;
        }
        
        // Check the found object metaclass matches the asked one
        SmClass retClassOf = ret.getClassOf();
        if (retClassOf == id.classof ||
                retClassOf.hasBase(id.classof)) {
            return ret;
        }
        
        // Invalid result
        Log.error(String.format("LoadHelper.getObject(%s): \"%s\" found is a '%s' and not a '%s' !",
                id.toString(),
                GetAbsoluteSymbol.get(ret),
                retClassOf.getName(),
                id.classof.getName()));
        return null;
    }

    @objid ("6784129c-2e7b-11e2-8aaa-001ec947ccaf")
    @Override
    public final SmObjectImpl getRefObject(IModelLoader modelLoader, final ObjIdName id) throws DuplicateObjectException, IllegalReferenceException, IndexException {
        if (this.exmlBase.isStored(id.toObjId())) {
            return createStubObject(modelLoader, id, true);
        } else {
            return getForeignObject(modelLoader, id);
        }
    }

    @objid ("bafffe7f-7dc9-44b1-94c8-0b1395f6b758")
    @Override
    public SmClass getSmClass(String xclassof) {
        SmClass mClass = this.metamodel.getMClass(xclassof);
        
        if (mClass == null) {
            try {
                mClass = this.metamodel
                        .fakeClassBuilder()
                        .setQualifiedName(xclassof)
                        .setCmsNode(false)
                        .build();
            } catch (MetaclassAlreadyExistException ex) {
                return (SmClass) ex.getExistingMetaclass();
            }
        }
        return mClass;
    }

    /**
     * Initialize the model object status flags.
     * 
     * @param modelLoader the model loader to use to initialize flags.
     * @param obj the model object to initialize.
     */
    @objid ("1d8fdac0-122c-11e2-816a-001ec947ccaf")
    @Override
    public void initObjectFlags(IModelLoader modelLoader, SmObjectImpl obj) {
        long falseFlags = IRStatus.SHELL ;
        if (!this.loadReadWrite) {
            falseFlags |= IRStatus.USERWRITE;
        }
        modelLoader.setRStatus(obj, 0, falseFlags, 0);
    }

    @objid ("678412be-2e7b-11e2-8aaa-001ec947ccaf")
    @Override
    public boolean isStored(ObjId id) {
        return this.exmlBase.isStored(id);
    }

    /**
     * Method to be called when loading fails with an exception.
     * <p>
     * Fires a warning to repository monitors and set the object as shell.
     * 
     * @param obj the CMS node unable to be loaded.
     * @param modelLoader the model loader
     * @param e the exception
     */
    @objid ("923d683e-88bd-4a14-835e-bde4df90b2b8")
    @Override
    public void loadFailed(SmObjectImpl obj, IModelLoader modelLoader, Exception e) {
        if (obj.getData().hasAllStatus(IRStatus.SHELL) != StatusState.TRUE) {
            modelLoader.setRStatus(obj, IRStatus.SHELL, 0, 0);
            if (e != null) {
                this.exmlBase.getErrorSupport().fireWarning(e);
            }
        }
    }

    @objid ("1d8fdab5-122c-11e2-816a-001ec947ccaf")
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Enum<?> getEnumValue(final String attValue, final Class<?> attType) {
        return Enum.valueOf((Class<? extends Enum>)attType, attValue.toUpperCase(Locale.ROOT));
    }

    @objid ("678412b0-2e7b-11e2-8aaa-001ec947ccaf")
    private static void reportProblem(final SmObjectImpl obj, final String msg) {
        if (Log.ENABLED) {
            Log.warning("EXML loading problem on %s %s: %s ",obj.getClassOf().getName(),obj.getUuid(),msg);
        }
    }

    /**
     * initialize the object  name from the {@link ObjId#name} if non null, from the index in the other case.
     * @param id the name holder
     * 
     * @param modelLoader a model loader
     * @param newObject the object to initialize
     */
    @objid ("1d8fdabc-122c-11e2-816a-001ec947ccaf")
    private void setObjectName(IModelLoader modelLoader, SmObjectImpl newObject, final String name) {
        SmClass cls = newObject.getClassOf();
        SmAttribute nameAtt = cls.getAttributeDef("Name");
        if (nameAtt == null) {
            nameAtt = cls.getAttributeDef("name");
        }
        
        if (nameAtt != null) {
            modelLoader.loadAttribute(newObject, nameAtt, name == null ? "" : name);
        }
    }

    @objid ("7ba3f20f-55c1-49e0-a3dc-62be9c7c48b0")
    @Override
    public ObjIdName withNameFromIndex(ObjId id) {
        try {
            return new ObjIdName(id.classof, this.exmlBase.getCmsNodeIndex().getName(id), id.id);
        } catch (IndexException e) {
            this.exmlBase.setIndexesDamaged(e);
            return new ObjIdName(id.classof, "", id.id);
        }
    }

    @objid ("5a204c11-0852-476c-ba1c-4ccf9a01fe5a")
    private String getNameFromIndex(ObjId id, String defaultName) {
        try {
            return this.exmlBase.getCmsNodeIndex().getName(id);
        } catch (IndexException e) {
            this.exmlBase.setIndexesDamaged(e);
            return defaultName;
        }
    }

}
