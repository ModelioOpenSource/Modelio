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

package org.modelio.vstore.exml.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.files.StreamException;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.NullProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.model.MObjectCache;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.StorageErrorSupport;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.session.impl.storage.IModelLoaderProvider;
import org.modelio.vcore.session.impl.storage.IModelRefresher;
import org.modelio.vcore.session.impl.storage.StorageException;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmLiveId;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.AbstractMetaclassException;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.MetamodelWriter;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorReader;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorWriter;
import org.modelio.vstore.exml.common.index.CannotOpenIndexException;
import org.modelio.vstore.exml.common.index.ExmlIndex;
import org.modelio.vstore.exml.common.index.ICmsNodeIndex;
import org.modelio.vstore.exml.common.index.IUserNodeIndex;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.index.IndexOutdatedException;
import org.modelio.vstore.exml.common.model.IllegalReferenceException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.model.ObjIdName;
import org.modelio.vstore.exml.common.utils.ObjIdReader;
import org.modelio.vstore.exml.plugin.VStoreExml;
import org.modelio.vstore.exml.resource.FsExmlResourceProvider;
import org.modelio.vstore.exml.resource.IExmlResourceProvider.ExmlResource;
import org.modelio.vstore.exml.resource.IExmlResourceProvider;
import org.modelio.vstore.exml.resource.LocalExmlResourceProvider;

/**
 * Abstract implementation of EXML repository.
 * @author cmarin
 */
@objid ("2df07649-1e87-11e2-90db-001ec947ccaf")
public abstract class AbstractExmlRepository implements IExmlBase {
    @objid ("dea15b27-b222-4564-82f7-7621e0e741ad")
    private static final boolean TRACE = false;

    @objid ("fd21f5d1-5986-11e1-991a-001ec947ccaf")
    private volatile boolean baseOpen;

    /**
     * Shield against infinite loops.
     */
    @objid ("b4e83141-9442-4bc1-8c36-70f890c44e0e")
    private String lastLoad;

    /**
     * If <code>true</code>, indexes will be rebuilt on next access.
     */
    @objid ("d5c6ab7e-6231-11e1-b31a-001ec947ccaf")
    private boolean needRebuildIndexes;

    /**
     * Repository ID.
     */
    @objid ("fd1f92a7-5986-11e1-991a-001ec947ccaf")
    private byte rid;

    /**
     * if <code>false</code>, all model objects are read only.
     * <i>null</i> means not initialized, you read it too early.
     */
    @objid ("3e072e6e-1ea1-11e2-90db-001ec947ccaf")
    private Boolean writeable = null;

    /**
     * Remembers removed CMS nodes between 2 saves, with their storage handler
     * before removal.
     * <p>
     * The key is the CMS node UUID.
     */
    @objid ("b5b8d35c-7df4-4793-961f-7226b0f0ddb1")
    private final Map<String, ExmlStorageHandler> deletedNodes = new HashMap<> ();

    /**
     * Remembers loaded objects detached from the repository between 2 saves.
     * <p>
     * The key is the object UUID.
     */
    @objid ("24943bc8-3a52-4cf1-a3d4-c4357e084521")
    private final Map<String, SmObjectImpl> detachedObjects = new HashMap<> ();

    /**
     * EMF adapter.
     */
    @objid ("9e88c0c7-dc71-4ec6-9fd4-46c116dd971e")
    private final EmfResource emfResource;

    /**
     * Support for reporting to GUI.
     */
    @objid ("7eb9a6ee-362d-4d71-b47e-6b7690139fff")
    private final StorageErrorSupport errorSupport = new StorageErrorSupport(this);

    /**
     * Indexes.
     */
    @objid ("a2b8893a-ebf4-4646-bff1-15fc4d780863")
    private ExmlIndex indexes;

    /**
     * Caches all loaded model objects.
     */
    @objid ("c3ab3ab1-ef50-486c-9918-cfd601936fbf")
    private MObjectCache loadCache;

    @objid ("a6f3d45c-1402-4b04-a1b6-3ff87b4d0d68")
    private IModelLoaderProvider modelLoaderProvider;

    @objid ("eba6e326-165a-4023-8232-fde088631313")
    private ObjIdReader objIdReader;

    @objid ("2c97ef97-7068-4d5f-b903-b998f8ceddcb")
    private final IRepositoryObject orphansRepoHandler = new OrphansExmlStorageHandler(this, "orphan");

    @objid ("0a09c563-6744-451d-9217-b24b0feb3594")
    private RepositoryVersions repositoryFormatVersion;

    /**
     * EXML resource provider
     */
    @objid ("57ec3f46-98af-4a97-b74b-0cf870fdb837")
    private final IExmlResourceProvider resProvider;

    /**
     * All in memory CMS nodes storage handlers.
     */
    @objid ("5b37fa8e-9df8-418f-8836-e474731d220f")
    private final Collection<WeakReference<ExmlStorageHandler>> storageHandlers = new ArrayList<>();

    @objid ("06c57c0a-d617-46da-ad43-65f2cfedc799")
    private Optional<MetamodelDescriptor> storedMetamodelDescriptor;

    @objid ("656db6a2-d90d-481e-918e-d3a11abc183e")
    private final IRepositoryObject unloadedRepoHandler = new OrphansExmlStorageHandler(this, "unloaded");

    /**
     * initialize the EXML repository.
     * <p>
     * The repository needs to be {@link #open( IModelLoaderProvider, IModelioProgress) opened} before being used.
     * 
     * @param resProvider an EXML resource provider.
     */
    @objid ("cf20c950-03e4-11e2-b5bf-001ec947ccaf")
    public AbstractExmlRepository(IExmlResourceProvider resProvider) {
        Objects.requireNonNull(resProvider, "resProvider must not be null");
        
        this.resProvider = resProvider;
        this.baseOpen = false;
        this.emfResource = new EmfResource(this);
    }

    /**
     * initialize the EXML repository.
     * <p>
     * The repository needs to be {@link #open(IModelLoaderProvider, IModelioProgress) opened} before being used.
     * 
     * @param path the repository data path.
     * @param runtimePath the repository runtime path. This path contains the EXML indexes.
     * @param name a name for this repository. Used in exception messages.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("fe1fcea0-caa7-4b91-9c9c-fdf35282820a")
    public AbstractExmlRepository(final Path path, final Path runtimePath, String name) throws IOException {
        if (path.getFileSystem().equals(FileSystems.getDefault())) {
            this.resProvider = new LocalExmlResourceProvider(path, runtimePath, name);
        } else {
            this.resProvider = new FsExmlResourceProvider(path, runtimePath, name);
        }
        
        this.baseOpen = false;
        this.emfResource = new EmfResource(this);
    }

    @objid ("6fea12a6-51d1-40e8-bf05-98ebe976c11f")
    @Override
    public void addCreatedObject(SmObjectImpl newObject) {
        assertOpen();
        
        if (newObject.getClassOf().isCmsNode()) {
            ExmlStorageHandler newHandler = createStorageHandler(newObject, true);
            newHandler.setDirty(true);
            newObject.setRepositoryObject(newHandler);
        } else {
            // For non CMS nodes, the storage handler will be set when the object
            // will be attached to its parent by the parent storage handler.
            // @see ExmlStorageHandler.depValAppended(...)
            newObject.setRepositoryObject(this.orphansRepoHandler);
        }
        
        // Add the object to our load cache
        getLoadCache().putToCache(newObject);
    }

    @objid ("fd24587e-5986-11e1-991a-001ec947ccaf")
    @Override
    public final void addObject(final SmObjectImpl newObject) {
        assertOpen();
        
        if (newObject.getClassOf().isCmsNode()) {
            ExmlStorageHandler newHandler = this.deletedNodes.remove(newObject.getUuid());
            if (newHandler == null) {
                newHandler = createStorageHandler(newObject, true);
                newHandler.setDirty(true);
            } else {
                synchronized(this.storageHandlers) {
                    this.storageHandlers.add(new WeakReference<>(newHandler));
                }
            }
            newObject.setRepositoryObject(newHandler);
            newHandler.attach(newObject);
        } else {
            // For non CMS nodes, the storage handler will be set when the object
            // will be attached to its parent by the parent storage handler.
            // @see ExmlStorageHandler.depValAppended(...)
            newObject.setRepositoryObject(this.orphansRepoHandler);
        }
        
        // Add the object to our load cache
        synchronized(this.detachedObjects) {
            getLoadCache().putToCache(newObject);
            this.detachedObjects.remove(newObject.getUuid());
        }
    }

    @objid ("fd1f92bb-5986-11e1-991a-001ec947ccaf")
    @Override
    public synchronized void close() {
        if (!this.baseOpen) {
            return;
        }
        
        this.baseOpen = false;
        this.loadCache = null;
        
        if (this.indexes != null) {
            try {
                this.indexes.close();
                this.indexes = null;
            } catch (IndexException e) {
                getErrorSupport().fireWarning( e);
            }
        }
        
        if (this.resProvider != null) {
            try {
                this.resProvider.close();
            } catch (IOException e) {
                getErrorSupport().fireWarning(e);
            }
        }
    }

    /**
     * Create an empty repository.
     * 
     * @param mMetamodel the initial metamodel
     * @throws java.io.IOException in case of failure.
     */
    @objid ("fd21f562-5986-11e1-991a-001ec947ccaf")
    public void create(MMetamodel mMetamodel) throws IOException {
        this.resProvider.createRepository(mMetamodel);
        saveRepositoryVersion(mMetamodel);
        saveMetamodelDescriptor(mMetamodel);
    }

    @objid ("fd21f54c-5986-11e1-991a-001ec947ccaf")
    @Override
    public final ExmlStorageHandler createStorageHandler(SmObjectImpl cmsNode, final boolean isNodeLoaded) {
        ExmlStorageHandler newHandler = instantiateStorageHandler(cmsNode, isNodeLoaded);
        assert (newHandler != null);
        
        synchronized(this.storageHandlers) {
            this.storageHandlers.add(new WeakReference<>(newHandler));
        }
        return newHandler;
    }

    @objid ("fd21f566-5986-11e1-991a-001ec947ccaf")
    @Override
    public Collection<MObject> findByAtt(SmClass cls, boolean withSubClasses, String att, Object val) {
        assertOpen();
        
        Collection<MObject> results = new ArrayList<>();
        try (IModelLoader modelLoader = this.modelLoaderProvider.beginLoadSession()) {
            loadAll(cls, modelLoader, withSubClasses);
        } catch (IndexException e) {
            getErrorSupport().fireError(e);
        } catch (DuplicateObjectException e) {
            getErrorSupport().fireError(e);
        }
        
        getLoadCache().findByAtt(cls, withSubClasses, att, val, results );
        return results;
    }

    @objid ("fd21f5c4-5986-11e1-991a-001ec947ccaf")
    @Override
    public Collection<MObject> findByClass(SmClass cls, boolean withSubClasses) {
        assertOpen();
        
        Collection<MObject> results = new ArrayList<>();
        try (IModelLoader modelLoader = this.modelLoaderProvider.beginLoadSession()) {
            loadAll(cls, modelLoader, withSubClasses);
            getLoadCache().findByClass(cls, withSubClasses, results);
        } catch (IndexException e) {
            getLoadCache().findByClass(cls, withSubClasses, results);
            getErrorSupport().fireError(e);
        } catch (DuplicateObjectException e) {
            getErrorSupport().fireError(e);
        }
        return results;
    }

    @objid ("fd21f5f3-5986-11e1-991a-001ec947ccaf")
    @Override
    public SmObjectImpl findById(SmClass cls, final String siteIdentifier) {
        ObjId id  = new ObjId(cls, siteIdentifier);
        try (IModelLoader modelLoader = this.modelLoaderProvider.beginLoadSession()) {
            // The search is first done for the metaclass itself
            SmObjectImpl obj = findByObjId(id, modelLoader );
        
            // and then it must be carried out for all the metaclass derived from 'cls'
            if (obj == null) {
                for (SmClass mc : cls.getAllSubClasses()) {
                    obj = findByObjId(new ObjId(mc, siteIdentifier), modelLoader );
                    if (obj != null) {
                        return obj;
                    }
                }
            }
        
            return obj;
        } catch (DuplicateObjectException e) {
            getErrorSupport().fireError(e);
        } catch (IndexException e) {
            getErrorSupport().fireError(e);
        } catch (IllegalReferenceException e) {
            getErrorSupport().fireWarning(e);
        }
        return null;
    }

    /**
     * Find an object from its ID in the repository.
     * 
     * @param id an object ID.
     * @param modelLoader the model loader to use to load the model object
     * @return the found model object or <code>null</code>.
     * @throws org.modelio.vcore.model.DuplicateObjectException if another object with the same identifier already exists
     * in another repository.
     * @throws org.modelio.vstore.exml.common.index.IndexException if the indexes are broken
     * @throws org.modelio.vstore.exml.common.model.IllegalReferenceException if there is a consistency problem with the reference
     */
    @objid ("fd24574c-5986-11e1-991a-001ec947ccaf")
    @Override
    public SmObjectImpl findByObjId(final ObjId id, IModelLoader modelLoader) throws DuplicateObjectException, IndexException, IllegalReferenceException {
        assertOpen();
        
        // Return the element if already loaded
        SmObjectImpl object = getLoadedObject(id);
        if (object != null) {
            return object;
        }
        
        // If the object is not stored here, returns null.
        if (! isStored(id)) {
            return null;
        }
        
        // Creates a stub object
        try {
            ILoadHelper helper = getloadHelper();
            return helper.createStubObject(modelLoader, helper.withNameFromIndex(id), false);
        } catch (DuplicateObjectException e) {
            // The object may have been loaded by the same repository in a concurrent thread.
            // in this case return the concurrently loaded object.
            // Wait for the object to finish initialization in LoadHelper.createStubObject(...) for 10*10ms
            object = getConcurrentlyLoadedObject(id);
        
            if (object != null) {
                return object;
            } else {
                throw e; // the object comes from another repository
            }
        }
    }

    @objid ("f4d29ce8-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public final Collection<SmObjectImpl> getAllLoadedObjects() {
        assertOpen();
        return this.loadCache.asCollection();
    }

    @objid ("eff41b5d-92d7-11e1-be7e-001ec947ccaf")
    @Override
    public Iterable<SmObjectImpl> getAllObjects() {
        throw new UnsupportedOperationException();
    }

    @objid ("fd21f667-5986-11e1-991a-001ec947ccaf")
    @Override
    public final ICmsNodeIndex getCmsNodeIndex() throws IndexException {
        try {
            return getIndexes().getCmsNodeIndex();
        } catch (CannotOpenIndexException e) {
            setIndexesDamaged(e);
            throw new IndexException(e.getLocalizedMessage(), e);
        }
    }

    @objid ("86f5fcde-17d7-4c6c-ad2a-bda2e27b6572")
    @Override
    public SmObjectImpl getDetachedObject(ObjId id) {
        synchronized(this.detachedObjects) {
            return this.detachedObjects.get(id.id);
        }
    }

    @objid ("4b252716-c065-11e1-b511-001ec947ccaf")
    @Override
    public final Resource getEmfResource() {
        return this.emfResource;
    }

    @objid ("0cbbcf94-d66d-11e1-adbb-001ec947ccaf")
    @Override
    public final StorageErrorSupport getErrorSupport() {
        return this.errorSupport;
    }

    /**
     * Get an access to the EXML indexes.
     * 
     * @param monitor a progress monitor used when the indexes need to be rebuilt.
     * @return the EXML indexes.
     * @throws org.modelio.vstore.exml.common.index.CannotOpenIndexException if the index cannot be open nor rebuilt.
     */
    @objid ("94dcee3b-90ee-45f3-8cf5-5837d95f5bdb")
    public final ExmlIndex getIndexes(IModelioProgress monitor) throws CannotOpenIndexException {
        // The method is final to preserve the index lifecycle logic.
        if (this.indexes == null || this.needRebuildIndexes) {
            openIndexes(monitor);
        }
        return this.indexes;
    }

    @objid ("fd26b9ec-5986-11e1-991a-001ec947ccaf")
    @Override
    public final MObjectCache getLoadCache() {
        assertOpen();
        return this.loadCache;
    }

    @objid ("fd24588c-5986-11e1-991a-001ec947ccaf")
    @Override
    public final SmObjectImpl getLoadedObject(final ObjId id) {
        assertOpen();
        return getLoadCache().findById(id.classof, id.id, false);
    }

    /**
     * Gives access to available maintenance operations on an EXML repository.
     * 
     * @return the maintenance operations.
     */
    @objid ("d79ead26-1aa7-453d-ab62-b8462e95022c")
    public IMaintenanceOperations getMaintenance() {
        return new MaintenanceOperations(this);
    }

    @objid ("2f82f1a5-f34d-41a3-a6fd-c33cbde19180")
    @Override
    public final Optional<MetamodelDescriptor> getMetamodelDescriptor() {
        if (this.storedMetamodelDescriptor == null) {
            this.storedMetamodelDescriptor = Optional.ofNullable(loadMetamodelDescriptor());
        }
        return this.storedMetamodelDescriptor;
    }

    @objid ("df2704f4-1c43-11e2-8eb9-001ec947ccaf")
    @Override
    public final IModelLoaderProvider getModelLoaderProvider() {
        if (this.modelLoaderProvider == null) {
            throw new IllegalStateException("The '"+getURI()+"' repository is not open.");
        }
        return this.modelLoaderProvider;
    }

    @objid ("fd24587b-5986-11e1-991a-001ec947ccaf")
    @Override
    public final byte getRepositoryId() {
        return this.rid;
    }

    /**
     * Get the EXML resources provider.
     * 
     * @return the EXML resources provider.
     */
    @objid ("9788fc2f-12de-11e2-816a-001ec947ccaf")
    public final IExmlResourceProvider getResourceProvider() {
        return this.resProvider;
    }

    /**
     * Get the location of the repository as an URI.
     * 
     * @return the location of the repository.
     */
    @objid ("cf258dfd-03e4-11e2-b5bf-001ec947ccaf")
    public final URI getURI() {
        return this.resProvider.getURI();
    }

    /**
     * @return the user/used nodes index.
     * @throws org.modelio.vstore.exml.common.index.IndexException if the indexes are not accessible
     */
    @objid ("fd26b9ea-5986-11e1-991a-001ec947ccaf")
    public final IUserNodeIndex getUserNodeIndex() throws IndexException {
        try {
            return getIndexes().getUserNodeIndex();
        } catch (CannotOpenIndexException e) {
            setIndexesDamaged(e);
            throw new IndexException(e.getLocalizedMessage(), e);
        }
    }

    @objid ("fd24588f-5986-11e1-991a-001ec947ccaf")
    @Override
    public void init(final byte arid) {
        this.rid = arid;
    }

    @objid ("4b25271b-c065-11e1-b511-001ec947ccaf")
    @Override
    public final boolean isDirty() {
        return ! getDirtyHandlers().isEmpty() || !this.deletedNodes.isEmpty();
    }

    @objid ("fd26b9b5-5986-11e1-991a-001ec947ccaf")
    @Override
    public final boolean isOpen() {
        return this.baseOpen;
    }

    @objid ("0d638b02-8fb6-11e1-be7e-001ec947ccaf")
    @Override
    public final boolean isStored(final SmObjectImpl obj) {
        return (SmLiveId.getRid(obj.getLiveId()) == this.rid) 
                                                                                                                                                                                                                                                                                && isStored(new ObjId(obj));
    }

    @objid ("cf9811de-d73c-11e1-adbb-001ec947ccaf")
    @Override
    public boolean isStored(ObjId id) {
        try {
            return getDetachedObject(id)==null && getCmsNodeIndex().isStored(id);
        } catch (IndexException e) {
            setIndexesDamaged(e);
            getErrorSupport().fireError(e);
            return false;
        }
    }

    @objid ("fd26b9b2-5986-11e1-991a-001ec947ccaf")
    @Override
    public SmObjectImpl loadCmsNode(ObjId id, IModelLoader modelLoader, boolean force) throws DuplicateObjectException {
        SmObjectImpl obj = null;
        Exception failure = null;
        try {
            obj = findByObjId(id, modelLoader);
        } catch (IndexException e) {
            setIndexesDamaged(e);
            failure = e;
        } catch (IllegalReferenceException e) {
            setIndexesDamaged(e);
            failure = e;
        }
        
        if (obj != null) {
            // Load the node if not moved to another repository and not already loaded.
            final IRepositoryObject repoHandle = obj.getRepositoryObject();
            if (repoHandle.getRepositoryId() == getRepositoryId() && ( force || !((ExmlStorageHandler) repoHandle).isLoaded())) {
                // The CMS node is in this repository and needs reloading, do load it.
                reloadCmsNode(obj, modelLoader);
            }
        } else if (failure != null) {
            // Create and return a shell stub object
            try {
                obj = getloadHelper().createStubCmsNode(modelLoader, id, "");
                
                getloadHelper().loadFailed(obj, modelLoader, failure);
            } catch (DuplicateObjectException e) {
                // The object may be being loaded by the same repository in a concurrent thread.
                obj = getConcurrentlyLoadedObject(id);
                if (obj == null) {
                    throw e; // the object comes from another repository
                }
            } 
        }
        return obj;
    }

    @objid ("0d638b08-8fb6-11e1-be7e-001ec947ccaf")
    @Override
    public final void loadDynamicDep(final SmObjectImpl obj, final SmDependency dep) {
        assertOpen();
        
        try (IModelLoader modelLoader = this.modelLoaderProvider.beginLoadSession()) {
            for (ObjId depTargetId : getUserNodeIndex().getObjectUsers(
                    new ObjId(obj), 
                    dep.getSymetric().getName())) {
                
                SmObjectImpl depTarget = findByObjId(depTargetId, modelLoader);
                if (depTarget != null) {
                    depTarget.getRepositoryObject().loadDep(depTarget, dep.getSymetric());
                }
            }
            
        } catch (DuplicateObjectException e) {
            getErrorSupport().fireError(e);
        } catch (IndexException e) {
            getErrorSupport().fireError(e); 
        } catch (IllegalReferenceException e) {
            getErrorSupport().fireError(e);
        }
    }

    @objid ("fd245859-5986-11e1-991a-001ec947ccaf")
    @Override
    public final ISmObjectData loadObjectData(SmObjectImpl obj) {
        // Avoid infinite loop
        if (obj.getUuid().equals(this.lastLoad)) {
            return null;
        }
        this.lastLoad = obj.getUuid();
        
        final ObjId objid = new ObjId(obj);
        
        // Load the object
        try (IModelLoader modelLoader = this.modelLoaderProvider.beginLoadSession()) {
        
            // Need to load the element
            if (objid.classof.isCmsNode()) {
                // The element is a CMS node.
                // Return a not yet loaded ref if stored in base, else return null
                if (getCmsNodeIndex().isStored(objid)) {
                    ISmObjectData data = modelLoader.createObjectData(obj);
                    data.setRepositoryObject(createStorageHandler(obj, false));
                    this.loadCache.putToCache(obj);
                    return data;
                } else {
                    return null;
                }
            } else {
                // The element is not a CMS node.
                // Get the CMS node containing the element
                final ObjId parentId = getCmsNodeIndex().getCmsNodeOf(objid);
        
                // If no parent, the element does not exist in the repository
                if (parentId == null) {
                    return null;
                }
        
                SmObjectImpl parent = findByObjId(parentId, modelLoader);
        
                if (parent == null) {
                    return null;
                }
        
                ISmObjectData data = modelLoader.createObjectData(obj);
                data.setRepositoryObject(parent.getRepositoryObject());
                this.loadCache.putToCache(obj);
        
                return data;
            }
        
        } catch (DuplicateObjectException e) {
            getErrorSupport().fireError(e);
        } catch (IndexException e) {
            getErrorSupport().fireError(e);
        } catch (IllegalReferenceException e) {
            setIndexesDamaged(e);
            getErrorSupport().fireError(e);
        } finally {
            this.lastLoad = null;
        }
        return null;
    }

    @objid ("fd26b996-5986-11e1-991a-001ec947ccaf")
    @Override
    public void open(final IModelLoaderProvider modelLoadProvider, IModelioProgress monitor) throws IOException {
        if (this.baseOpen) {
            throw new IllegalStateException("The '"+getURI()+"' repository is already open.");
        }
        
        this.resProvider.open();
        this.writeable = this.resProvider.isWriteable();
        this.loadCache = new MObjectCache(modelLoadProvider.getMetamodel());
        this.modelLoaderProvider = modelLoadProvider;
        
        final String clsSimpleName = getClass().getSimpleName()+ ": ";
        this.objIdReader = new ObjIdReader(
                this.modelLoaderProvider.getMetamodel(), 
                () -> clsSimpleName, // prefix
                () -> ""); // suffix
        
        checkVersions();
        
        boolean ok = false;
        try {
            openIndexes(monitor);
            this.baseOpen = true;
        
            initializeLoader(); // implementation may call assertOpen()
            ok = true;
        } catch (CannotOpenIndexException e) {
            throw new IOException(e.getLocalizedMessage(), e); 
        } finally {
            if (! ok) {
                this.baseOpen = false;
            }
        }
    }

    @objid ("3ddf055f-63db-4835-a111-630121be34f8")
    @Override
    public InputStream readBlob(String key) throws IOException {
        return this.resProvider.readBlob(key);
    }

    @objid ("ca3c36d9-0b41-48f1-8f9b-e14ca3377cce")
    @Override
    public IBlobInfo readBlobInfo(String key) throws IOException {
        return this.resProvider.readBlobInfo(key);
    }

    /**
     * Reload the given CMS node objects from repository.
     * 
     * @param toReload the CMS node objects to reload.
     * @param toDelete the CMS node objects to explicitly delete
     * @param toRestore the deleted CMS nodes to undelete
     */
    @objid ("615f95ca-5eff-11e2-b557-001ec947ccaf")
    public void refreshModel(Collection<MObject> toReload, Collection<MObject> toDelete, Collection<MRef> toRestore) {
        IModelLoaderProvider loaderProv = getModelLoaderProvider();
        try (IModelRefresher refresher= loaderProv.beginRefreshSession()) {
            if (toRestore != null) {
                SmMetamodel metamodel = loaderProv.getMetamodel();
                for (MRef ref : toRestore) {
                    // forget detached objects are create new ones
                    synchronized(this.detachedObjects) {
                        this.detachedObjects.remove(ref.uuid);
                        this.deletedNodes.remove(ref.uuid);
                    }
        
                    ObjId id = new ObjId(metamodel.getMClass(ref.mc), ref.uuid);
                    
                    loadCmsNode(id, refresher, true);
                }
            }
            
            for (MObject obj : toReload) {
                synchronized(this.detachedObjects) {
                    this.detachedObjects.remove(obj.getUuid());
                    this.deletedNodes.remove(obj.getUuid());
                }
                SmObjectImpl impl = (SmObjectImpl) obj;
                SmObjectImpl newobj = loadCmsNode(new ObjId(impl), refresher, true);
                if (TRACE) {
                    if( newobj != obj) {
                        Log.trace("AbstractExmlRepository.refreshModel(...): Reloading %s created a new object", newobj);
                    }
                }
            }
        
            if (toDelete != null) {
                for (MObject obj : toDelete) {
                    synchronized(this.detachedObjects) {
                        this.detachedObjects.remove(obj.getUuid());
                        this.deletedNodes.remove(obj.getUuid());
                    }
        
                    SmObjectImpl impl = (SmObjectImpl) obj;
                    IRepositoryObject h = impl.getRepositoryObject();
                    if (h.getRepositoryId() == getRepositoryId()) {
                        // delete the object only if has not been stolen by another repository
                        refresher.deleteObject(impl);
                    }
                    
                }
            }
        } catch (DuplicateObjectException e) {
            getErrorSupport().fireError(e);
        }
    }

    @objid ("074ab913-ee11-446f-a674-4c5e34d2b53a")
    @Override
    public final synchronized void reloadCmsNode(SmObjectImpl obj, IModelLoader modelLoader) throws DuplicateObjectException {
        final ExmlStorageHandler exmlHandler = (ExmlStorageHandler) obj.getRepositoryObject();
        boolean ret = false;
        
        try {
            exmlHandler.setLoaded(true);
        
            doReloadCmsNode(obj, modelLoader);
            ret = true;
        
            exmlHandler.setDirty(false);
        } catch (IOException e) {
            getloadHelper().loadFailed(obj, modelLoader, e);
        } catch (AssertionError err) {
            getloadHelper().loadFailed(obj, modelLoader, new IllegalStateException(err));
        } catch (IndexException e) {
            getloadHelper().loadFailed(obj, modelLoader, e);
            setIndexesDamaged(e);
        } finally {
            if (! ret) {
                exmlHandler.setLoaded(false);
            }
        }
    }

    @objid ("bb8e45e9-4b2a-4ddd-9433-4535f3b0f1c3")
    @Override
    public void removeBlob(String key) throws IOException {
        this.resProvider.deleteBlob(key);
    }

    @objid ("fd26b979-5986-11e1-991a-001ec947ccaf")
    @Override
    public synchronized void removeObject(SmObjectImpl object) {
        synchronized(this.detachedObjects) {
            this.loadCache.removeFromCache(object);
            this.detachedObjects.put(object.getUuid(), object);
        }
        
        if (object.getClassOf().isCmsNode()) {
            // Record deletion and remove the storage handler
            ExmlStorageHandler handler = (ExmlStorageHandler) object.getRepositoryObject();
            this.deletedNodes.put(object.getUuid(), handler);
            synchronized(this.storageHandlers) {
                // don't use this.storageHandlers.remove() : WeakReference does not define equals()
                for (Iterator<WeakReference<ExmlStorageHandler>> it = this.storageHandlers.iterator(); 
                        it.hasNext();) {
                    WeakReference<ExmlStorageHandler> ref = it.next();
                    if (ref.get() == handler) {
                        it.remove();
                    }
                }
            }
        } else {
            object.setRepositoryObject(this.orphansRepoHandler);
        }
    }

    @objid ("fd26b977-5986-11e1-991a-001ec947ccaf")
    @Override
    public synchronized void save(IModelioProgress monitor) {
        // TODO The save should be ACID in order to preserve
        // consistency in case of failure in the middle.
        
        // Contains dirty CMS nodes and deleted ones.
        Collection<ExmlStorageHandler> dirty = getDirtyHandlers();
        
        if (dirty.isEmpty()) {
            return;
        }
        
        String repositoryName = getResourceProvider().getName();
        int nbDirty = dirty.size();
        SubProgress mon = SubProgress.convert(monitor, nbDirty * 20);
        mon.subTask(VStoreExml.I18N.getMessage("AbstractExmlRepository.save.begin", repositoryName));
        try {
        
            if (! this.deletedNodes.isEmpty()) {
                // TODO backup files to roll back save on failure
                mon.subTask(VStoreExml.I18N.getMessage("AbstractExmlRepository.save.deleting", repositoryName, this.deletedNodes.size()));
                deleteCmsNodes(this.deletedNodes.values(), mon.newChild(this.deletedNodes.size()));
            }
        
            int i = 0;
            nbDirty = dirty.size(); // dirty is modified by deleteCmsNodes()
            for (ExmlStorageHandler handler : dirty) {
                try {
                    // Do not save not loaded nodes: these are missing references.
                    // Do not save deleted nodes: the file is already deleted.
                    if (handler.isLoaded() && ! this.deletedNodes.containsKey(handler.getCmsNodeId().id)) {
                        //TODO: backup files in case of future failure
                        save (handler, mon.newChild(10));
                    }
        
                    handler.setDirty(false);
                } catch (IOException e) {
                    // Report save error and try to continue
                    String message = VStoreExml.I18N.getMessage("AbstractExmlRepository.saveNodeFailed",
                            handler.getCmsNodeId(),
                            FileUtils.getLocalizedMessage(e),
                            getResourceProvider().getName());
        
                    getErrorSupport().fireWarning(new StorageException(this, message, e));
                }
        
                mon.worked(1);
                if (++i % 5 == 0) {
                    mon.subTask(VStoreExml.I18N.getMessage("AbstractExmlRepository.save.progress", repositoryName, i, nbDirty));
                }
        
            }
        
            saveMetamodelDescriptor();
            
            // Commit resources, will also write a stamp
            this.resProvider.commit();
            synchronized (this.detachedObjects) {
                this.deletedNodes.clear();
                this.detachedObjects.clear();
            }
        
        } catch (IOException e) {
            getErrorSupport().fireError(e);
        } finally {
            // todo: rollback already saved files in case of failure
            // if (!success) ....
        }
        
        // Now update the indexes
        mon.subTask(VStoreExml.I18N.getMessage("AbstractExmlRepository.save.indexes", repositoryName));
        mon.setWorkRemaining(100);
        updateIndexes(dirty, mon);
        mon.subTask(VStoreExml.I18N.getMessage("AbstractExmlRepository.save.done", repositoryName));
    }

    /**
     * Save a descriptor of the current metamodel.
     * <p>
     * Precondition : {@link #open(IModelLoaderProvider, IModelioProgress)} must have been called.
     * 
     * @throws java.io.IOException on index broken.
     * @since 3.6
     */
    @objid ("10185993-792d-428c-b7a1-37bb8ddbec76")
    public final void saveMetamodelDescriptor() throws IOException {
        SmMetamodel metamodel = getModelLoaderProvider().getMetamodel();
        
        MetamodelDescriptor desc = saveMetamodelDescriptor(metamodel);
        this.storedMetamodelDescriptor = Optional.of(desc);
    }

    @objid ("d5c6ab7f-6231-11e1-b31a-001ec947ccaf")
    @Override
    public final void setIndexesDamaged(final Exception e) {
        if (! this.needRebuildIndexes) {
            String m1 = e.getLocalizedMessage();
            String reposName = this.resProvider.getName();
            String msg = VStoreExml.I18N.getMessage("AbstractExmlRepository.setIndexDamaged",reposName, m1);
            getErrorSupport().fireWarning(new StorageException(this, msg, e));
        
            if (this.indexes != null) {
                try {
                    this.indexes.close();
                } catch (IndexException e1) {
                    msg = VStoreExml.I18N.getMessage("AbstractExmlRepository.setIndexDamaged.closeFailed", reposName, e1.getLocalizedMessage());
                    getErrorSupport().fireWarning(new StorageException(this, msg, e1));
                }
            }
        
            this.needRebuildIndexes = true;
        }
    }

    @objid ("b1d5cf70-147e-4133-98a5-7b61ff991fbd")
    @Override
    public String toString() {
        return "'"+this.resProvider.getName()+"' "+getClass().getSimpleName()+" @ "+this.resProvider.getURI();
    }

    @objid ("fd24580f-5986-11e1-991a-001ec947ccaf")
    @Override
    public void unloadObject(SmObjectImpl obj) {
        ExmlStorageHandler handler = (ExmlStorageHandler) obj.getRepositoryObject();
        
        this.loadCache.removeFromCache(obj);
        this.detachedObjects.remove(obj.getUuid());
        obj.setRepositoryObject(this.unloadedRepoHandler);
        
        handler.setToReload(obj);
    }

    /**
     * Update indexes from the modified CMS nodes.
     * <p>
     * Called by the CMS drivers after having updated the working copy.
     * 
     * @param createdRefs created CMS nodes
     * @param updatedRefs modified CMS nodes
     * @param deletedRefs deleted CMS nodes
     * @param progress a progress monitor.
     */
    @objid ("52223d94-8760-4059-8ee3-d11015f0efa0")
    public void updateIndexes(Collection<MRef> createdRefs, Collection<MRef> updatedRefs, Collection<MRef> deletedRefs, IModelioProgress progress) {
        int nbChanges = createdRefs.size() + updatedRefs.size() + deletedRefs.size();
        if (nbChanges == 0) {
            return;
        }
        
        int workAmount = 10 + nbChanges;
        SubProgress monitor = SubProgress.convert(progress, workAmount);
        
        try {
            // First update stamp
            this.resProvider.writeStamp();
        
            // Update indexes
            ExmlIndex index = getIndexes(monitor.newChild(10));
        
            monitor.subTask(VStoreExml.I18N.getMessage("AbstractExmlRepository.mon.updatingIndexes", this.resProvider.getName() ));
            ObjId nodeId ;
        
            for (MRef r : deletedRefs) {
                nodeId = this.objIdReader.readObjId(r, true);
                index.removeFromIndexes(nodeId);
                monitor.worked(1);
            }
        
            for (MRef r : createdRefs) {
                nodeId = this.objIdReader.readObjId(r, true);
                index.updateIndexes(nodeId);
                monitor.worked(1);
            }
        
            for (MRef r : updatedRefs) {
                nodeId = this.objIdReader.readObjId(r, true);
                index.updateIndexes(nodeId);
                monitor.worked(1);
            }
        
            index.commitDb();
        } catch (IOException | IndexException e) {
            setIndexesDamaged(e);
        } catch (CannotOpenIndexException e) {
            setIndexesDamaged(e);
            getErrorSupport().fireError(e);
        }
        
        monitor.done();
    }

    @objid ("6e7fab48-3221-4bbd-ba31-975ff562efcf")
    @Override
    public OutputStream writeBlob(IBlobInfo info) throws IOException {
        return this.resProvider.writeBlob(info);
    }

    /**
     * Record the CMS node resource as deleted.
     * <p>
     * The default implementation deletes the matching files from the file system
     * by using the resource provider, and mark the EXML handler as not dirty.
     * <p>
     * It may be redefined to have another behavior, then it should call the parent behavior.
     * @throws CannotOpenIndexException if indexes are broken and not repairable.
     * 
     * @param toDelete the deleted CMS nodes handlers.
     */
    @objid ("f7844d44-296d-450c-8e2d-f4ba59b57b08")
    protected void deleteCmsNodes(Collection<ExmlStorageHandler> toDelete, IModelioProgress monitor) {
        SubProgress mon = SubProgress.convert(monitor, toDelete.size() * 2 + 1);
        
        // Delete the files
        for (ExmlStorageHandler del : toDelete) {
            ObjId id = del.getCmsNodeId();
        
            // Delete the file
            try {
                this.resProvider.getResource(id).delete();
                this.resProvider.getLocalResource(id).delete();
            } catch (IOException e) {
                getErrorSupport().fireWarning(e);
            }
        
            del.setDirty(false);
            mon.worked(1);
        }
        
        // Remove CMS nodes from indexes
        try {
            final ExmlIndex lindexes = getIndexes(mon.newChild(1));
            for (ExmlStorageHandler del : toDelete) {
                ObjId id = del.getCmsNodeId();
        
                lindexes.removeFromIndexes(id);
                
                //clean uses index
                for (ObjId objId : getCmsNodeIndex().getCmsNodeContent(id)) {
                    lindexes.getUserNodeIndex().remove(objId);
                }
        
                //clean parent index
                lindexes.getCmsNodeIndex().removeObj(id);
        
                mon.worked(1);
            }
        } catch (IndexException e) {
            setIndexesDamaged(e);
        } catch (CannotOpenIndexException e) {
            setIndexesDamaged(e);
        }
    }

    @objid ("ab2bff3e-06ff-44a6-8ddc-c398ccc51193")
    protected abstract void doReloadCmsNode(SmObjectImpl obj, IModelLoader modelLoader) throws DuplicateObjectException, IOException, IndexException;

    @objid ("fd21f545-5986-11e1-991a-001ec947ccaf")
    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @objid ("67b15d88-2e7b-11e2-8aaa-001ec947ccaf")
    protected abstract ILoadHelper getloadHelper();

    /**
     * Instantiate and initialize the EXML file loader.
     * <p>
     * Called by {@link #open(IModelLoaderProvider, IModelioProgress)}
     */
    @objid ("3e072e70-1ea1-11e2-90db-001ec947ccaf")
    protected abstract void initializeLoader();

    /**
     * Instantiate a new storage handler for the given CMS node.
     * <p>
     * May be redefined in sub classes.
     * 
     * @param cmsNode a CMS node
     * @param isNodeLoaded <code>true</code> to set the node as loaded, else <code>false</code>.
     * @return the new storage handler.
     */
    @objid ("04cc0016-34a1-11e2-985b-001ec947ccaf")
    protected ExmlStorageHandler instantiateStorageHandler(SmObjectImpl cmsNode, final boolean isNodeLoaded) {
        ExmlStorageHandler newHandler = new ExmlStorageHandler(this, cmsNode, isNodeLoaded);
        return newHandler;
    }

    /**
     * Tells whether the repository is read/write or read only.
     * 
     * @return <code>true</code> if the repository is read/write, <code>false</code> if it is read only.
     * @throws java.lang.IllegalStateException if the repository is not open.
     */
    @objid ("3e072e72-1ea1-11e2-90db-001ec947ccaf")
    protected final boolean isWriteable() throws IllegalStateException {
        if (this.writeable == null) {
            throw new IllegalStateException("The base is not open");
        }
        return this.writeable;
    }

    @objid ("3e0990c3-1ea1-11e2-90db-001ec947ccaf")
    protected abstract void save(ExmlStorageHandler handler, IModelioProgress progress) throws IOException;

    /**
     * Save the repository format versions.
     * 
     * @throws java.io.IOException in case of I/O failure
     */
    @objid ("12142941-df53-4a59-958c-4222e7ca32d7")
    protected final void saveRepositoryVersion(MMetamodel mm) throws IOException {
        try (OutputStream out = getResourceProvider().getRepositoryVersionResource().bufferedWrite();) {
            RepositoryVersions v;
            if (this.repositoryFormatVersion != null) {
                v = new RepositoryVersions(this.repositoryFormatVersion.getRepositoryFormat(),mm);
            } else {
                v = new RepositoryVersions(mm);
            }
        
            v.write(out);
        }
    }

    @objid ("8c5f7cc9-d02b-11e1-bf59-001ec947ccaf")
    private void assertOpen() {
        if (! this.baseOpen) {
            throw new IllegalStateException("The '"+getURI()+"' repository is not open.");
        }
    }

    @objid ("66a95b14-c2a3-40db-b880-9f468aacbc72")
    private void checkVersions() throws IOException {
        this.repositoryFormatVersion = getResourceProvider().readRepositoryVersion();
        
        SmMetamodel metamodel = getModelLoaderProvider().getMetamodel();
        if (this.repositoryFormatVersion == null) {
            // Initialize 
            this.repositoryFormatVersion = new RepositoryVersions(metamodel);
        
            if (this.writeable == Boolean.TRUE) {
                // Compute and store repository version
                // This code is to be removed on future versions, this case must not be allowed
                // and the repository must then be migrated.
                Log.trace("No version file for '%s' repository. Creating one", getURI());
                saveRepositoryVersion(metamodel);
                saveMetamodelDescriptor(metamodel);
            } else {
                Log.warning("No version file for read only '%s' repository.", getURI());
            }
        } else {
            this.repositoryFormatVersion.checkCompatible(metamodel);
        }
    }

    @objid ("a2415aa8-4489-4608-93be-751ac357f95f")
    private SmObjectImpl getByObjIdName(final ObjIdName idn, boolean checkExist, IModelLoader modelLoader) throws DuplicateObjectException, IllegalReferenceException, IndexException {
        assertOpen();
        ObjId id = idn.toObjId();
        
        // Return the element if already loaded
        SmObjectImpl object = getLoadedObject(id);
        if (object != null) {
            return object;
        }
        
        // If the object is not stored here, returns null.
        if (checkExist && ! isStored(id)) {
            return null;
        }
        
        // Creates a stub object
        try {
            return getloadHelper().createStubObject(modelLoader, idn, false);
        } catch (DuplicateObjectException e) {
            // The object may have been loaded by the same repository in a concurrent thread.
            // in this case return the concurrently loaded object.
            // Wait for the object to finish initialization in LoadHelper.createStubObject(...) for 10*10ms
            object = getConcurrentlyLoadedObject(id);
        
            if (object != null) {
                return object;
            } else {
                throw e; // the object comes from another repository
            }
        }
    }

    /**
     * To be called in case of {@link DuplicateObjectException} caught,
     * when the object may have been loaded by the same repository in a concurrent thread.
     * <p>
     * In this case return the concurrently loaded object.
     * Wait for the object to finish initialization in LoadHelper.createStubObject(...) for 10*10ms
     * 
     * @param id the object ID
     * @return the found object or <i>null</i>.
     */
    @objid ("55ecd5f6-f737-4dfd-a6f3-50e2c0119024")
    private SmObjectImpl getConcurrentlyLoadedObject(final ObjId id) {
        SmObjectImpl object;
        object = getLoadedObject(id);
        for (int i=0; i<10 && object == null; ++i) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e1) {
                // ignore
            }
            object = getLoadedObject(id);
        }
        //System.err.println("recovered concurrently loaded "+id+" in "+this.resProvider.getName());
        return object;
    }

    /**
     * @return a snapshot of dirty handlers.
     */
    @objid ("2eae7830-8585-11e1-b4fc-001ec947ccaf")
    private Collection<ExmlStorageHandler> getDirtyHandlers() {
        Collection<ExmlStorageHandler> ret = new ArrayList<>();
        synchronized(this.storageHandlers) {
            for (WeakReference<ExmlStorageHandler> handlerRef : this.storageHandlers) {
                ExmlStorageHandler handler = handlerRef.get();
                if (handler != null && handler.isDirty()) {
                    ret.add(handler);
                }
            }
        }
        return ret;
    }

    /**
     * Get an access to the EXML indexes.
     * <p>
     * The indexes will be opened on first access.
     * They will be rebuilt if they are missing or damaged.
     * 
     * @return the EXML indexes.
     * @throws org.modelio.vstore.exml.common.index.CannotOpenIndexException if the indexes cannot be opened nor rebuilt.
     */
    @objid ("f7740c12-d023-11e1-bf59-001ec947ccaf")
    private ExmlIndex getIndexes() throws CannotOpenIndexException {
        // TODO find a way to get a GUI progress monitor
        return getIndexes(new NullProgress());
    }

    @objid ("fd2458f1-5986-11e1-991a-001ec947ccaf")
    private void loadAll(SmClass cls, IModelLoader modelLoader) throws DuplicateObjectException, IndexException {
        try {
        getCmsNodeIndex()
        .idByMClass(cls)
        .forEach(objId -> {
            try {
                getByObjIdName(objId, true, modelLoader);
            } catch (AbstractMetaclassException e) {
                // Report and continue
                getErrorSupport().fireWarning(e);
            } catch (IllegalReferenceException e) {
                // The object may have moved to another repository
                try {
                    SmObjectImpl foundobj = modelLoader.loadForeignObject(cls, objId.id, objId.name);
                    if (foundobj.isShell()) {
                        // Object not found anywhere else, the index is probably dead
                        setIndexesDamaged(e);
        
                        getErrorSupport().fireWarning(e);
                    }
                } catch (RuntimeException e2) {
                    e.addSuppressed(e2);
        
                    // The index is probably dead
                    setIndexesDamaged(e);
        
                    getErrorSupport().fireWarning(e);
                }
            } catch (DuplicateObjectException e) {
                throw new StreamException(e);
            } catch (IndexException e) {
                throw new StreamException(e);
            }
        });
        } catch (StreamException e) {
            /*
             * Though exception will be anyway re-thrown during first rethrow() call (oh, Java generics...), 
             * this way allows to get a strict static definition of possible exceptions 
             * (requires to declare them in throws). And no instanceof or something is needed.
             */
            e.<IndexException>rethrow();
            e.<DuplicateObjectException>rethrow();
        }
    }

    /**
     * Load all the instances of the given metaclass with its sub classes if asked.
     * 
     * @param cls a metamodel class
     * @param recursive <code>true</code> to load all sub classes too.
     * @throws org.modelio.vcore.model.DuplicateObjectException when adding to the cache an object with the same identifier as another one.
     * @throws org.modelio.vstore.exml.common.index.IndexException in case of I/O error.
     */
    @objid ("fd245907-5986-11e1-991a-001ec947ccaf")
    private void loadAll(SmClass cls, IModelLoader modelLoader, final boolean recursive) throws DuplicateObjectException, IndexException {
        // Load all sub meta-classes instances
        if (recursive) {
            for (SmClass c : cls.getAllSubClasses()) {
                loadAll(c, modelLoader);
            }
        }
        
        // Load the meta-class instances itself
        loadAll(cls, modelLoader);
    }

    @objid ("5b09989e-d7c0-4ce9-8848-28734cd0f284")
    private MetamodelDescriptor loadMetamodelDescriptor() {
        try {
            ExmlResource mmDescRes = getResourceProvider().getMetamodelDescriptorResource();
            try (InputStream in = mmDescRes.bufferedRead()){
                if (in != null) {
                    MetamodelDescriptor desc = MetamodelDescriptorReader.readFrom(in, mmDescRes.getPublicLocation());
                    return desc;
                }
                return null;
            }
        } catch(@SuppressWarnings ("unused") FileNotFoundException | NoSuchFileException e) {
            return null;
        } catch (IOException e) {
            getErrorSupport().fireWarning(e);
            return null;
        }
    }

    /**
     * Open the indexes and rebuild them if necessary.
     * 
     * @param aMonitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call done()
     * on the given monitor. Accepts null, indicating that no progress should be reported and that the operation cannot
     * be cancelled.
     * @return <code>true</code> if the indexes were rebuilt, <code>false</code> if they were
     * up to date or rebuild failed.
     * @throws org.modelio.vstore.exml.common.index.CannotOpenIndexException if unable to open and unable to recreate indexes.
     */
    @objid ("fd2458ee-5986-11e1-991a-001ec947ccaf")
    private boolean openIndexes(IModelioProgress aMonitor) throws CannotOpenIndexException {
        if (this.indexes != null) {
            // Indexes opened before the repository
            try {
                this.indexes.close();
            } catch (IndexException e) {
                Log.warning("%s index closing failed: %s", this.resProvider.getName(), e.getLocalizedMessage());
                Log.warning(e);
            }
        }
        
        // Ensure index directory exists to check its write rights
        try {
            Files.createDirectories(this.resProvider.getIndexAccessPath().toPath());
        } catch (IOException e) {
            throw new CannotOpenIndexException(FileUtils.getLocalizedMessage(e), e);
        }
        
        // Open indexes and check their format version
        boolean indexesRebuilt = false;
        this.indexes = new ExmlIndex(this.resProvider, getErrorSupport());
        
        if (this.resProvider.isBrowsable() && this.resProvider.getIndexAccessPath().canWrite()) {
            // The indexes can be rebuilt if needed.
            try {
                this.indexes.open(aMonitor, getModelLoaderProvider().getMetamodel());
        
                this.indexes.checkUptodate();
            } catch (IndexException e) {
                setIndexesDamaged(e);
            } catch (RuntimeException e) {
                setIndexesDamaged(e);
            } catch (IndexOutdatedException e) {
                Log.trace(e.getLocalizedMessage());
                this.needRebuildIndexes = true;
            } catch (IOException e) {
                throw new CannotOpenIndexException(FileUtils.getLocalizedMessage(e), e);
            }
            
            if (this.needRebuildIndexes) {
                // Try deleting index file and rebuild all
                try (ExmlIndex.CloseOnFail shield = this.indexes.getCloseOnFail()) {
                    SubProgress mon = SubProgress.convert(aMonitor, 100);
            
                    // Create a new stamp in case there is none
                    //if (this.resProvider.getStamp().isEmpty())
                    // Workaround 'stamp.dat' versioned and got from fresh svn checkout: rewrite it always
                    this.resProvider.writeStamp();
            
                    mon.subTask( VStoreExml.I18N.getMessage("AbstractExmlRepository.mon.deletingIndexes",this.resProvider.getName()));
                    this.indexes.deleteIndexes();
                    this.indexes.open(mon.newChild(10), this.modelLoaderProvider.getMetamodel());
            
                    mon.subTask( VStoreExml.I18N.getMessage("AbstractExmlRepository.mon.buildingIndexes",this.resProvider.getName()));
                    this.indexes.buildIndexes(mon.newChild(90));
            
                    shield.success();
                    this.needRebuildIndexes = false;
                    indexesRebuilt = true;
                } catch (IOException e1) {
                    String msg = VStoreExml.I18N.getMessage("AbstractExmlRepository.RebuildIndexFailed", this.resProvider.getName(), FileUtils.getLocalizedMessage(e1));
                    throw new CannotOpenIndexException(msg, e1);
                } catch (IndexException e1) {
                    String msg = VStoreExml.I18N.getMessage("AbstractExmlRepository.RebuildIndexFailed", this.resProvider.getName(), e1.getLocalizedMessage());
                    throw new CannotOpenIndexException(msg, e1);
                }
            }
        } else {
            // Indexes cannot be rebuilt : open will fail if indexes are bad.
            try {
                this.indexes.open(aMonitor, this.modelLoaderProvider.getMetamodel());
        
                this.indexes.checkUptodate();
            } catch (RuntimeException e) {
                String msg = VStoreExml.I18N.getMessage("AbstractExmlRepository.RoIndexesDamaged",
                        this.resProvider.getName(), e.toString());
                throw new CannotOpenIndexException(msg, e);
            } catch (IndexOutdatedException e) {
                String msg = VStoreExml.I18N.getMessage("AbstractExmlRepository.RoIndexesOutdated",
                        this.resProvider.getName(), e.getLocalizedMessage());
                throw new CannotOpenIndexException(msg, e);
            } catch (IndexException e) {
                String msg = VStoreExml.I18N.getMessage("AbstractExmlRepository.RoIndexesDamaged",
                        this.resProvider.getName(), e.getLocalizedMessage());
                throw new CannotOpenIndexException(msg, e);
            } catch (IOException e) {
                // the stamp has a problem
                String msg = VStoreExml.I18N.getMessage("AbstractExmlRepository.RoIndexesDamaged",
                        this.resProvider.getName(), FileUtils.getLocalizedMessage(e));
                throw new CannotOpenIndexException(msg, e);
        
            }
        }
        return indexesRebuilt;
    }

    /**
     * Update indexes from the modified CMS nodes.
     * 
     * @param dirty the dirty CMS nodes.
     * @param progress a progress monitor.
     */
    @objid ("2eae7836-8585-11e1-b4fc-001ec947ccaf")
    private final void updateIndexes(final Collection<ExmlStorageHandler> dirty, IModelioProgress progress) {
        // Avoid spare JDBM file sync that consume time for nothing
        if (dirty.isEmpty()) {
            return;
        }
        
        try {
            SubProgress mon = SubProgress.convert(progress, dirty.size() + 5);
            ExmlIndex lindexes = getIndexes(mon.newChild(3));
        
            for (ExmlStorageHandler handler : dirty) {
                lindexes.updateIndexes(handler.getCmsNodeId());
                mon.worked(1);
            }
        
            lindexes.commitDb();
        } catch (IndexException | CannotOpenIndexException e) {
            setIndexesDamaged(e);
        }
    }

    /**
     * Tells whether the repository contain any CMS node of a metaclass of the given metamodel fragment.
     * 
     * @param metamodel the metamodel
     * @param mmf a metamodel fragment
     * @return true only if this metamodel fragment is used in this repository.
     * @throws org.modelio.vbasic.files.StreamException wraps a {@link IndexException} or a {@link CannotOpenIndexException} .
     * @since 3.8
     */
    @objid ("b76ab6c2-cdb2-4ced-8331-7251e5293ebe")
    private boolean isMetamodelFragmentUsed(SmMetamodel metamodel, MMetamodelFragment mmf) throws StreamException {
        for(SmClass mc : metamodel.getRegisteredMClasses(mmf)) {
            try {
                if (mc.isCmsNode() &&
                        getIndexes().getCmsNodeIndex().idByMClass(mc).findAny().isPresent()) {
                    return true;
                }
            } catch (IndexException e) {
                setIndexesDamaged(e);
                throw new StreamException(e);
            } catch (CannotOpenIndexException e) {
               throw new StreamException(e);
            }
        }
        return false;
    }

    @objid ("96e6ec31-8487-4044-a5d8-2bb57216f663")
    private MetamodelDescriptor saveMetamodelDescriptor(MMetamodel metamodel) throws IOException {
        ExmlResource mmDescRes = getResourceProvider().getMetamodelDescriptorResource();
        try (OutputStream out = mmDescRes.bufferedWrite()){
            // TODO :write only the used metamodel fragments and handle changes on each SVN commit.
            MetamodelDescriptor desc = new MetamodelWriter()
            //.withFragmentFilter(mmf -> isMetamodelFragmentUsed(metamodel, mmf)) // write only the used metamodel fragments
            .run(metamodel);
        
            new MetamodelDescriptorWriter().write(desc, out);
            return desc;
        } catch (StreamException e) {
            throw new IOException(e.getCause().getLocalizedMessage(), e);
        }
    }

}
