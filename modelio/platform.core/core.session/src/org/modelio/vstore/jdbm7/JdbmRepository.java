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

package org.modelio.vstore.jdbm7;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.PrimaryHashMap;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.RecordManagerOptions;
import jdbm.SerializerInput;
import jdbm.SerializerOutput;
import org.modelio.vbasic.collections.CompoundCollection;
import org.modelio.vbasic.files.CloseOnFail;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.model.MObjectCache;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.StorageErrorSupport;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.session.impl.storage.IModelLoaderProvider;
import org.modelio.vcore.session.plugin.VCoreSession;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.StatusState;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorReader;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorWriter;
import org.modelio.vstore.jdbm7.impl.Helper;
import org.modelio.vstore.jdbm7.impl.ILoadHelper;
import org.modelio.vstore.jdbm7.impl.SmObjectDataSerializer;
import org.modelio.vstore.jdbm7.impl.migration.v4.V4Migrator;
import org.modelio.vstore.jdbm7.index.JdbmIndex;

/**
 * Repository where everything is saved in a JDBM base.
 * <p>
 * Blobs are not supported (at least for the moment) in this repository.
 */
@objid ("a613158e-8121-42d2-bc74-3be9a237202e")
public class JdbmRepository implements IRepository {
    /**
     * Repository format version.
     * <p>
     * History:
     * <ul>
     * <li> 1 & 2 : first formats
     * <li> 3 : 22/08/2013 : don't use StoreReference in indexes, directly store the collection
     * <li> 4 : 11/09/2013 : version 3 failed saving silently.
     * change version to force computing namespace uses again.
     * <li> 5 : 07/01/2016 : UUIDs are now strings (Modelio 3.5).
     * <li> 6 : 16/09/2016 : Artificial change for Modelio 3.6 : no NamespaceUse anymore
     * <li> 7 : 25/10/2016 : indexes wrongly stored external users
     * </ul>
     */
    @objid ("87a5d594-855e-4c38-a0e2-8178830daaf9")
    public static final int FORMAT_VERSION = 7;

    /**
     * JDBM object name storing the format version
     */
    @objid ("062e7af6-1082-44ce-ae17-cc674cb931e1")
    private static final String FORMAT_VERSION_KEY = "FORMAT_VERSION";

    @objid ("a6f5dafc-9f2b-47b8-a2ff-61705082675f")
    private boolean baseOpen;

    @objid ("67c30b03-926c-4410-b465-48f90543f3a5")
    private RecordManager db;

    @objid ("600a25dd-097b-410a-8390-079e49b39ce0")
    private PrimaryHashMap<String,byte[]> dbContent;

    /**
     * A user friendly repository name.
     */
    @objid ("c64510fe-b00c-4260-8513-130023df86c7")
    private String name;

    @objid ("e4964281-7f49-4dc4-8044-cbac1115f2f3")
    private byte rid = -1;

    @objid ("0a592fdd-5fe7-44f5-a697-ef55c61703c4")
    private BlobsRepository blobsRepository;

    @objid ("601b7d22-f22b-4fa3-bf90-349c91ac34a2")
    private final ReadWriteLock dbLock;

    @objid ("25cc1f50-527c-4344-a8dc-695e90fcaa4a")
    private Collection<SmObjectImpl> dirty;

    @objid ("2e2fbca2-5104-4b51-8063-d63b2d50cf9b")
    private final Object dirtyLock;

    @objid ("76f45381-b05a-42d5-8827-7a4349c20422")
    private JdbmEmfResource emfRes;

    @objid ("8c3b4d50-6e8f-49c5-8089-c18e39ac316c")
    private final StorageErrorSupport errSupport;

    @objid ("ec147ce4-3244-400f-a71a-278d286e74b5")
    private JdbmStorageHandler handler;

    @objid ("07caa02f-77e6-4817-ba48-8de8cba918c2")
    private JdbmIndex index;

    @objid ("316bf686-5598-4af2-9590-c2c25a9ab42c")
    private MObjectCache loadCache;

    @objid ("99f9f6c4-1a97-437a-82cc-327172e3c59e")
    private ILoadHelper loadHelper;

    @objid ("895168bf-a162-4dfa-97fe-c470942d66aa")
    private IModelLoaderProvider modelLoaderProvider;

    @objid ("38dd152d-5282-4c69-92d0-6610f128b08b")
    private File repositoryPath;

    @objid ("5bbac903-6971-443a-a015-7918b01cd82a")
    private SmObjectDataSerializer serializer;

    @objid ("763211ac-5484-4268-b4af-326a982fb46b")
    private MetamodelDescriptor storedMetamodelDescriptor;

    /**
     * Initialize the repository.
     * @param repositoryPath the repository directory.
     */
    @objid ("db0b2956-5f0e-4201-a468-506a13e1575e")
    public JdbmRepository(File repositoryPath) {
        this.emfRes = new JdbmEmfResource(this);
        this.errSupport = new StorageErrorSupport(this);
        this.repositoryPath = repositoryPath;
        this.dirtyLock = new Object();
        this.dbLock = new ReentrantReadWriteLock();
    }

    @objid ("f20b3dc0-0258-4f6d-b1c3-4e49065b18ed")
    @Override
    public void addObject(SmObjectImpl newObject) {
        this.handler.attach(newObject);
    }

    @objid ("878a0f61-23a1-46ff-9bbc-17880cc973e7")
    @Override
    public void close() {
        if (this.db != null) {
            try {
                this.db.close();
            } catch (IllegalStateException e) {
                //  java.lang.IllegalStateException: "RecordManager has been closed"
                // ignore
            } catch (IOException e) {
                getErrorSupport().fireWarning(e);
            }
        }
        
        this.db = null;
        this.baseOpen = false;
    }

    @objid ("8cfd65a6-cb73-466c-a6d2-df4eb00524af")
    @Override
    public Collection<MObject> findByAtt(SmClass cls, boolean withSubClasses, String att, Object val) {
        assertOpen();
        
        Collection<MObject> results = new ArrayList<>();
        try (IModelLoader modelLoader = this.modelLoaderProvider.beginLoadSession()){
            findAll(cls, modelLoader, withSubClasses);
        } catch (DuplicateObjectException e) {
            getErrorSupport().fireError(e);
        } catch (IOError e) {
            getErrorSupport().fireWarning(e);
        } catch (InternalError e) {
            getErrorSupport().fireWarning(e);
        }
        
        getLoadCache().findByAtt(cls, withSubClasses, att, val, results );
        return results;
    }

    @objid ("49f9fef4-f282-42c6-998b-450b94ff1821")
    @Override
    public Collection<MObject> findByClass(SmClass cls, boolean withSubClasses) {
        assertOpen();
        
        @SuppressWarnings("unchecked")
        Collection<MObject> ret = (Collection<MObject>) findAllLazy(cls, withSubClasses);
        return ret;
    }

    @objid ("246a2568-15dd-425e-90bf-a8b296debdda")
    @Override
    public SmObjectImpl findById(SmClass cls, String siteIdentifier) {
        SmObjectImpl ret = getLoadedObject(cls, siteIdentifier);
        
        if (ret == null && isStored(siteIdentifier)) {
            try (IModelLoader modelLoader = this.modelLoaderProvider.beginLoadSession()) {
                return findById(modelLoader, cls, siteIdentifier, true);
            } catch (DuplicateObjectException e) {
                getErrorSupport().fireError(e);
            }
        }
        return null;
    }

    @objid ("5d326922-daeb-406a-add4-aef15bdd1ed7")
    @Override
    public Collection<SmObjectImpl> getAllLoadedObjects() {
        return this.loadCache.asCollection();
    }

    @objid ("6a875355-61c4-41e5-bff5-df918a0485ec")
    @Override
    public Iterable<SmObjectImpl> getAllObjects() {
        SmClass cls = this.modelLoaderProvider.getMetamodel().getMClass(MObject.class);
        @SuppressWarnings("unchecked")
        final Iterable<SmObjectImpl> ret = (Iterable<SmObjectImpl>) findAllLazy(cls, true);
        return ret;
    }

    @objid ("653db8f7-1aeb-4d26-99b1-00e3fee44b2d")
    @Override
    public StorageErrorSupport getErrorSupport() {
        return this.errSupport;
    }

    /**
     * Get the repository name.
     * <p>
     * The repository name is intended to be displayed to the user.
     * @return the repository name.
     */
    @objid ("974f8290-5743-4473-a1e2-1f122bf1fa4b")
    public String getName() {
        return this.name;
    }

    @objid ("ed0098ce-7158-4376-8d53-4411ba8221ed")
    @Override
    public byte getRepositoryId() {
        return this.rid;
    }

    @objid ("6c2ffc62-eea5-4118-a71e-bbd7451da1ba")
    @Override
    public void init(byte arid) {
        this.rid = arid;
    }

    @objid ("fdd1fad7-e8e5-4a7c-acee-dc98aa3704ce")
    @Override
    public boolean isDirty() {
        synchronized(this.dirtyLock) {
            return ! this.dirty.isEmpty();
        }
    }

    @objid ("c63e108f-a1c1-4416-91c9-9e5420458440")
    @Override
    public boolean isOpen() {
        return this.baseOpen;
    }

    @objid ("4cbd6ee0-49d9-485f-a326-db59284d4554")
    @Override
    public boolean isStored(SmObjectImpl val) {
        assertOpen();
        return isStored(val.getUuid());
    }

    @objid ("833532dc-7259-49e6-98ae-111418ccc0a2")
    @Override
    public void loadDynamicDep(SmObjectImpl obj, SmDependency dep) {
        if (! isloadEnabled()) {
            return;
        }
        
        // If the object to load is in this repository and the dependency persistent,
        // just load the object and return.
        IRepositoryObject objHandler = obj.getRepositoryObject();
        if (objHandler==this.handler && objHandler.isPersistent(dep)) {
            objHandler.loadDep(obj, dep);
            return;
        }
        
        this.dbLock.readLock().lock();
        try (IModelLoader modelLoader = this.modelLoaderProvider.beginLoadSession()) {
            SmMetamodel mm = this.modelLoaderProvider.getMetamodel();
            final Collection<MRef> userRefs = this.index.getUserRefs(new MRef(obj));
        
            for (MRef ref :userRefs) {
                SmClass mClass = mm.getMClass(ref.mc);
                if (mClass != null) {
                    SmObjectImpl user = findById(modelLoader, mClass, ref.uuid, false); 
        
                    if (! user.getRepositoryObject().isAttLoaded(user, null)) {
                        loadObj(user, modelLoader);
                    }
                } else {
                    getErrorSupport().fireWarning(new Throwable(String.format("'%s' metaclass not found while loading %s needed by %s.%s", ref.mc, ref, obj, dep)));
                }
            }
        } catch (IOError e) {
            // thrown by JDBM library
            getErrorSupport().fireWarning(e);
        } catch (InternalError e) {
            // thrown by JDBM library
            getErrorSupport().fireWarning(e);
        } catch (IOException e) {
            getErrorSupport().fireWarning(e);
        } catch (DuplicateObjectException e) {
            getErrorSupport().fireWarning(e);
        } finally {
            this.dbLock.readLock().unlock();
        }
    }

    @objid ("3d2c34d5-104a-4603-a4c9-eea44797e385")
    @Override
    public ISmObjectData loadObjectData(SmObjectImpl obj) {
        this.dbLock.readLock().lock();
        try {
            if (! isStored(obj)) {
                return null;
            }
        
            try (IModelLoader modelLoader = this.modelLoaderProvider.beginLoadSession()) {
                ISmObjectData data = modelLoader.createObjectData(obj);
                data.setRepositoryObject(this.handler);
                loadObj(obj, modelLoader);
                return data;
            } catch (DuplicateObjectException e) {
                getErrorSupport().fireError(e);
            } catch (IOException e) {
                getErrorSupport().fireWarning(e);
            } catch (IOError e) {
                getErrorSupport().fireWarning(e);
            } catch (InternalError e) {
                getErrorSupport().fireWarning(e);
            }
        
            // Here, loading failed. Return null.
            return null;
        
        } finally {
            this.dbLock.readLock().unlock();
        }
    }

    @objid ("a89c81d0-4857-44e9-a436-25804bdffad5")
    @Override
    public void open(IModelLoaderProvider modelLoader, IModelioProgress monitor) throws IOException {
        this.modelLoaderProvider = modelLoader;
        this.dirty = new HashSet<>();
        this.handler = new JdbmStorageHandler(this);
        this.loadCache = new MObjectCache(modelLoader.getMetamodel());
        this.blobsRepository = new BlobsRepository(this.repositoryPath.toPath().resolve("blobs"));
        
        if (this.name == null|| this.name.isEmpty()) {
            this.name = this.repositoryPath.getName();
        }
        
        Files.createDirectories(this.repositoryPath.toPath());
        Files.createDirectories(this.repositoryPath.toPath().resolve("blobs"));
        
        this.db = instantiateDb(this.repositoryPath, this.repositoryPath.getName());
        
        try (CloseOnFail shield = Helper.toCloseOnFail(this.db)){
            this.loadHelper = new LoadHelper();
        
            handleFormatVersion(monitor);
        
            this.index = new JdbmIndex(this.db);
            this.serializer = new SmObjectDataSerializer(this.rid, this.loadHelper);
            this.dbContent = this.db.hashMap("main", null, null);
            
            shield.success();
            this.baseOpen = true;
        } catch (IOError e) {
            throw new IOException(e);
        }
    }

    @objid ("cc477df9-2d6a-4dae-aaa4-f82cf4052307")
    @Override
    public InputStream readBlob(String key) throws IOException {
        return this.blobsRepository.readBlob(key);
    }

    @objid ("37591099-e375-4823-a039-1d1bd1676996")
    @Override
    public IBlobInfo readBlobInfo(String key) throws IOException {
        return this.blobsRepository.readBlobInfo(key);
    }

    @objid ("16df7471-6980-41c5-909a-b128c67cd099")
    @Override
    public void removeBlob(String key) throws IOException {
        this.blobsRepository.removeBlob(key);
    }

    @objid ("4d25355c-dd36-4bd1-9c04-0edf8bf01d5e")
    @Override
    public void save(IModelioProgress monitor) throws IOException {
        Collection<SmObjectImpl> toSave = null;
        
        try {
            // initialize serializers
            ByteArrayOutputStream os = new ByteArrayOutputStream(200);
            @SuppressWarnings("resource")
            SerializerOutput dos = new SerializerOutput(os);
        
            writeFormatVersion();
            
            saveMetamodelDescriptor();
        
            // Get and clean dirty elements list.
            synchronized (this.dirtyLock)
            {
                toSave = this.dirty;
                this.dirty = new HashSet<>();
            }
        
            int nbDirty = toSave.size();
            SubProgress mon = SubProgress.convert(monitor, nbDirty+70);
            int i = 0;
            for (SmObjectImpl obj : toSave) {
                MRef objRef = new MRef(obj);
                this.dbLock.writeLock().lock();
                try {
                    if (obj.isDeleted() || obj.getRepositoryObject() != this.handler) {
                        this.index.removeObj(objRef);
                        this.index.removeCrossRefs(objRef);
                        this.dbContent.remove(obj.getUuid());
                    } else {
                        // reuse same byte array stream
                        os.reset();
                        dos.__resetWrittenCounter();
        
                        this.serializer.serialize(dos, (SmObjectData) obj.getData());
        
                        byte[] old = this.dbContent.put(obj.getUuid(), os.toByteArray());
        
                        // Add to main index if needed
                        if (old == null) {
                            this.index.addToMain(obj);
                        } else {
                            this.index.removeCrossRefs(objRef);
                        }
        
                        // Update cross reference indexes
                        this.index.addCrossRefs(obj);
                    }
        
                } finally {
                    this.dbLock.writeLock().unlock();
                }
        
                mon.worked(1);
                ++i;
                if (i % 20 == 0) {
                    mon.subTask(VCoreSession.getMessage("JdbmRepository.save", getName(), i, nbDirty));
                } else if (i % 9011 == 0) {
                    // Save too large for one transaction
                    mon.subTask(VCoreSession.getMessage("JdbmRepository.save", getName(), i, nbDirty) + VCoreSession.getMessage("JdbmRepository.save.commit", getName()));
                    this.db.commit();
                }
            }
            
            // Remove from cache as last operation : 
            // I found this.index.addCrossRefs(obj) accessing deleted objects.
            for (SmObjectImpl obj : toSave) {
                if (obj.isDeleted() || obj.getRepositoryObject() != this.handler) {
                    this.loadCache.removeFromCache(obj);
                }
            }
        
        
            mon.subTask(VCoreSession.getMessage("JdbmRepository.save.commit", getName()));
            this.db.clearCache();
            mon.worked(30);
            this.db.commit();
            mon.worked(30);
        
            mon.subTask(VCoreSession.getMessage("JdbmRepository.save.done", getName()));
            for (SmObjectImpl obj : toSave) {
                obj.setRStatus(IRStatus.REPO_LOADED, IRStatus.REPO_DIRTY, 0);
            }
        
            mon.worked(10);
            mon.subTask("");
        
            toSave = null;
        } catch (IOError e) {
            getErrorSupport().fireWarning(e);
        } catch (InternalError e) {
            getErrorSupport().fireWarning(e);
        } catch (RuntimeException e) {
            getErrorSupport().fireWarning(e);
        } finally {
            if (toSave != null) {
                // Save failed, add back elements to the dirty list.
                synchronized (this.dirtyLock) {
                    this.dirty.addAll(toSave);
                }
            }
        }
    }

    /**
     * Set the repository name.
     * <p>
     * The repository name is intended to be displayed to the user.
     * @param name the repository name.
     */
    @objid ("e2643e1a-2c7e-4269-9deb-84c3d314a4ea")
    public void setName(String name) {
        this.name = name;
    }

    @objid ("5b9b4c0d-ad67-4edc-b133-ac36185b6919")
    @Override
    public OutputStream writeBlob(IBlobInfo info) throws IOException {
        return this.blobsRepository.writeBlob(info);
    }

    @objid ("164a0a84-4375-4efd-a59e-5ecee84889da")
    void addDirty(SmObjectImpl obj) {
        synchronized (this.dirtyLock) {
            this.dirty.add(obj);
        }
    }

    @objid ("f3285ea0-efb6-4232-9a95-d91e7d302683")
    SmObjectImpl createStubObject(SmClass classof, String uuid, IModelLoader modelLoader) throws DuplicateObjectException {
        SmObjectImpl real = modelLoader.createLoadedObject(classof, uuid);
        real.setRepositoryObject(this.handler);
        modelLoader.setRStatus(real, 0, IRStatus.MASK_REPO, IRStatus.MASK_CMS);
        getLoadCache().addToCache(real);
        return real;
    }

    /**
     * Get the EMF mapping.
     * @return the EMF mapping.
     */
    @objid ("b026dcc8-a2c7-4bfd-8b99-a6e883dc62ca")
    JdbmEmfResource getEmfResource() {
        return this.emfRes;
    }

    @objid ("57a1a6ee-7246-457f-8516-52826acb2fb4")
    final MObjectCache getLoadCache() {
        return this.loadCache;
    }

    @objid ("fc6ff57d-9e48-4063-81f7-565039642aaf")
    final SmObjectImpl getLoadedObject(MClass cls, String id) {
        return getLoadCache().findById(cls, id);
    }

    /**
     * Tells whether the identifier is one of the stored objects.
     * @param uuid the object identifier.
     * @return true if stored else false.
     */
    @objid ("97aa8826-ce87-4ff6-8f99-f3df3926c80f")
    boolean isStored(String uuid) {
        try {
            return this.dbContent.containsKey(uuid);
        } catch (IOError e) {
            getErrorSupport().fireError(e);
        } catch (InternalError e) {
            getErrorSupport().fireError(e);
        }
        return false;
    }

    @objid ("7bcbc356-c36f-49db-8b2d-15ace765e3fd")
    boolean isloadEnabled() {
        return this.baseOpen;
    }

    @objid ("0113b5af-ebae-4b5e-a686-65b0529b0baa")
    void loadObj(SmObjectImpl obj) {
        if (!isloadEnabled()) {
            return;
        }
        
        try (IModelLoader modelLoader = this.modelLoaderProvider.beginLoadSession()) {
            loadObj(obj, modelLoader);
        } catch (DuplicateObjectException e) {
            getErrorSupport().fireError(e);
        } catch (IOException e) {
            getErrorSupport().fireWarning(e);
        } catch (IOError e) {
            getErrorSupport().fireWarning(e);
        } catch (InternalError e) {
            getErrorSupport().fireWarning(e);
        }
    }

    /**
     * Remove the object from the repository.
     * @param obj the object to remove.
     */
    @objid ("8f71b094-4378-4b48-85af-84d7444cea23")
    void removeObj(SmObjectImpl obj) {
        addDirty(obj);
        //getLoadCache().removeFromCache(obj); : keep the object in the load cache because it is referenced in indexes
    }

    /**
     * Unload a model object.
     * @param obj a model object to forget.
     */
    @objid ("7278e970-e52b-4e97-a787-89e91db59bf5")
    void unloadObject(SmObjectImpl obj) {
        obj.getData().setRFlags(IRStatus.MASK_REPO, StatusState.FALSE);
        getLoadCache().removeFromCache(obj);
        
        synchronized (this.dirtyLock) {
            this.dirty.remove(obj);
        }
    }

    @objid ("c05a1185-2823-4e27-841e-20cf667af909")
    private void assertOpen() {
        if (! this.baseOpen) {
            throw new IllegalStateException("The '"+this.repositoryPath+"' repository is not open.");
        }
    }

    /**
     * Find all instances of the given metaclass with its sub classes if asked.
     * <p>
     * The model objects are not loaded, only stubs are instantiated.
     * @param cls a metamodel class
     * @param recursive <code>true</code> to load all sub classes too.
     * @throws org.modelio.vcore.model.DuplicateObjectException when adding to the cache an object with the same identifier as another one.
     */
    @objid ("a6c2efe6-155f-405f-92cc-c38d31f8b735")
    private void findAll(SmClass cls, IModelLoader modelLoader, final boolean recursive) throws DuplicateObjectException {
        // Load all sub meta-classes instances
        if (recursive) {
            for (SmClass c : cls.getAllSubClasses()) {
                findAll(c, modelLoader, false);
            }
        }
        
        // Load the meta-class instances itself
        try {
            for (String id : this.index.getByMClass(cls)) {
                @SuppressWarnings("unused")
                SmObjectImpl obj = findById(modelLoader, cls, id, false);
            }
        } catch (IOError e) {
            getErrorSupport().fireWarning(e);
        }
    }

    /**
     * Find all instances of the given metaclass with its sub classes if asked.
     * <p>
     * Same as {@link #findAll(SmClass, IModelLoader, boolean)} but the returned collection loads
     * its content lazily.
     * <p>
     * The model objects are not loaded, only stubs are instantiated lazily.
     * @param cls a metamodel class
     * @param recursive <code>true</code> to load all sub classes too.
     * @throws java.lang.IllegalStateException when adding to the cache an object with the same identifier as another one.
     */
    @objid ("8ebe4d1e-c1be-4415-84d4-4c5a3c0a4e0d")
    private Collection<? super SmObjectImpl> findAllLazy(SmClass cls, final boolean recursive) throws IllegalStateException {
        if (recursive) {
            Collection<SmObjectImpl> ret = new CompoundCollection<>();
            FilteredContent coll = new FilteredContent(cls);
            ret.addAll(coll);
        
            for (SmClass subClass : cls.getAllSubClasses()) {
                coll = new FilteredContent(subClass);
                ret.addAll(coll);
            }
        
            return ret;
        } else {
            return new FilteredContent(cls);
        }
    }

    @objid ("e8d27afd-78c7-4078-b2c4-67914a6d9c82")
    private void handleFormatVersion(IModelioProgress monitor) throws IOException {
        int readFormat = readFormatVersion();
        if (readFormat == 0 && this.db.getNamedObject("main")==0) {
            // The base is empty, write the version
            writeFormatVersion();
        } else if (readFormat == 4) {
            // need to migrate UUIDs to strings
            this.db.close();
            this.index = null;
            
            new V4Migrator(this.repositoryPath, this.repositoryPath.getName(), this.modelLoaderProvider.getMetamodel())
            .execute(monitor);
            
            this.db = instantiateDb(this.repositoryPath, this.repositoryPath.getName());
            
            writeFormatVersion();
        } else if (readFormat != FORMAT_VERSION) {
            throw new InvalidFormatException(getName(), this.repositoryPath, readFormat, FORMAT_VERSION);
        }
    }

    @objid ("b556e0f8-b6f0-4af7-8d64-611ea2a5dc92")
    private void loadObj(SmObjectImpl obj, IModelLoader loader) throws DuplicateObjectException, IOError, IOException, InternalError {
        boolean ok = false;
        try {
            // avoid recursive call or call from debugger
            loader.setRStatus(obj, IRStatus.REPO_LOADED,0,0);
            
            byte[] bdata = this.dbContent.get(obj.getUuid());
            if (bdata == null) {
                throw new IOException(obj.getUuid()+" "+obj.getClassOf().getName()+" not found.");
            }
        
            ByteArrayInputStream bis = new ByteArrayInputStream(bdata);
            try (SerializerInput dis = new SerializerInput(bis);) {
                this.serializer.deserialize(dis, obj, loader);
        
                // Consistency check
                assert (obj.getData().getUuid() != null) : obj.getData();
        
                // set the object loaded, non shell, non dirty
                loader.setRStatus(obj, IRStatus.REPO_LOADED, IRStatus.SHELL | IRStatus.REPO_DIRTY, 0);
            }
        
            ok = true;
        } catch (IOException | RuntimeException | Error e) {
            // Debug code
            Log.warning("jdbm: Failed loading {%s} %s: %s", obj.getUuid(), obj.getMClass().getName(), e.toString());
            throw e;
        } finally {
            if (! ok) {
                loader.setRStatus(obj, IRStatus.SHELL, 0, 0);
            }
        }
    }

    @objid ("890d9dc0-06f7-44d8-854d-335233556040")
    private int readFormatVersion() throws IOException {
        return (Integer) readNamedObject(FORMAT_VERSION_KEY, 0);
    }

    @objid ("009add5c-6444-4a40-b236-784abc49b6c0")
    private void writeFormatVersion() throws IOException {
        writeNamedObject(FORMAT_VERSION_KEY, FORMAT_VERSION);
    }

    /**
     * Get access to the maintenance operations.
     * @return the maintenance operations.
     */
    @objid ("216c678b-f363-4088-b00b-ffe0033b3737")
    public IJdbmRepositoryMaintenance getMaintenance() {
        return new JdbmMaintenanceOperations(this.db);
    }

    @objid ("73581cd8-df02-4b74-9275-d02d84946a5f")
    protected SmMetamodel getMetamodel() {
        return this.modelLoaderProvider.getMetamodel();
    }

    @objid ("6a6e67f5-dcea-4cc1-b7d2-fb0aa1c1cbfe")
    @Override
    public void addCreatedObject(SmObjectImpl newObject) {
        addObject(newObject);
    }

    @objid ("ce26ea24-c278-4166-820e-87208bbef978")
    protected SmObjectImpl findById(IModelLoader modelLoader, SmClass cls, String siteIdentifier, boolean testExist) throws DuplicateObjectException {
        SmObjectImpl ret = getLoadedObject(cls, siteIdentifier);
        
        if (ret == null && (!testExist || isStored(siteIdentifier))) {
            try {
                return createStubObject(cls, siteIdentifier, modelLoader);
        
            } catch (DuplicateObjectException e) {
                // The object may have been loaded by a concurrent thread.
                // in this case return the concurrently loaded object.
                ret = getLoadedObject(cls, siteIdentifier);
                if (ret != null) {
                    return ret;
                } else {
                    throw e;
                }
            }
        }
        return ret;
    }

    @objid ("33e9c369-b99f-43de-b1e0-29d72584ce60")
    public static RecordManager instantiateDb(File dbDir, String dbName) throws IOException {
        File dbPath = new File(dbDir, dbName);
        
        Properties props = new Properties();
        props.setProperty(RecordManagerOptions.DISABLE_TRANSACTIONS, "false");
        props.setProperty(RecordManagerOptions.CACHE_TYPE, "mru");
        props.setProperty(RecordManagerOptions.CACHE_SIZE, "3000");
        return RecordManagerFactory.createRecordManager(dbPath.getAbsolutePath(), props );
    }

    /**
     * Save a descriptor of the current metamodel.
     * @throws java.io.IOException on failure
     */
    @objid ("9aabd6a5-4013-498e-85e2-64b5f011d65e")
    private void saveMetamodelDescriptor() throws IOException {
        MetamodelDescriptor desc = this.modelLoaderProvider.getMetamodel().serialize();
        String str = MetamodelDescriptorWriter.dumpToString(desc);
        writeNamedObject("metamodel_descriptor.xml", str);
        this.storedMetamodelDescriptor = desc;
    }

    @objid ("017bcb60-1c72-4fe3-b3d0-1d8a4202cf3b")
    private MetamodelDescriptor loadMetamodelDescriptor() {
        try {
            String xmlString = (String) readNamedObject("metamodel_descriptor.xml", null);
            if (xmlString != null){
                MetamodelDescriptor desc = MetamodelDescriptorReader.readFrom(xmlString);
                return desc;
            } else {
                return null;
            }
        } catch (IOException e) {
            getErrorSupport().fireWarning(e);
            return null;
        }
    }

    @objid ("06ded8c6-f830-4344-b6aa-a4751bad59f8")
    @Override
    public Optional<MetamodelDescriptor> getMetamodelDescriptor() {
        if (this.storedMetamodelDescriptor == null) {
            this.storedMetamodelDescriptor = loadMetamodelDescriptor();
        }
        return Optional.ofNullable(this.storedMetamodelDescriptor);
    }

    @objid ("08cea6ef-7a1a-4c24-8e24-1cce4a070158")
    private Object readNamedObject(String key, Object defaultVal) throws IOException {
        long recId = this.db.getNamedObject(key);
        if (recId == 0) {
            return defaultVal;
        } else {
            return this.db.fetch(recId);
        }
    }

    @objid ("4beabf29-49f7-4762-a9f2-8537c6694147")
    private void writeNamedObject(String key, Object value) throws IOException {
        long recId = this.db.getNamedObject(key);
        if (recId == 0) {
            recId = this.db.insert(value);
            this.db.setNamedObject(key, recId);
        } else {
            this.db.update(recId, value);
        }
        this.db.commit();
    }

    /**
     * Allows to load lazily the instances of a metaclass.
     * <p>
     * Note: {@link #size()} iterates the whole collection content, avoid calling it.
     */
    @objid ("48c4f4d2-6797-443f-b976-dee192dce1ef")
    private class FilteredContent extends AbstractCollection<SmObjectImpl> {
        @objid ("a2449220-4635-40ba-9a77-434a07bc5fc4")
        private final Collection<String> coll;

        @objid ("e425e5de-7fe0-4648-b080-00d9a90791ae")
        private final SmClass cls;

        @objid ("7b038d0c-4307-4aa1-8b2e-3bb6ffd4db00")
        @SuppressWarnings("synthetic-access")
        public FilteredContent(SmClass cls) {
            this.coll = JdbmRepository.this.index.getByMClass(cls);
            this.cls = cls;
        }

        @objid ("6326336b-2bb0-4526-8075-5c5e26a71b4c")
        @Override
        public Iterator<SmObjectImpl> iterator() {
            @SuppressWarnings("synthetic-access")
            final IModelLoaderProvider loaderProvider = JdbmRepository.this.modelLoaderProvider;
            final Iterator<String> it = this.coll.iterator();
            return new IteratorImp(it, loaderProvider, this.cls);
        }

        /**
         * Returns the number of elements in this collection.
         * <p>
         * If this collection contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
         * <p>
         * <b>Note: Iterates the whole collection content, avoid calling it. </b>
         * @return the collection size.
         */
        @objid ("13070010-9c5f-4544-a051-b5fc7450c9bc")
        @Override
        public int size() {
            // JDBM implementation Iterates the whole collection content
            return this.coll.size();
        }

        @objid ("7ee87e2d-ef36-41ec-8092-17fcebbac589")
        @Override
        public boolean isEmpty() {
            // JDBM implementation of isEmpty() calls size() that iterates the whole index.
            // This is a faster implementation.
            return !this.coll.iterator().hasNext();
        }

        @objid ("b19f495b-fb46-4160-8485-4955891b8680")
        @Override
        public boolean contains(Object o) {
            if (! (o instanceof MObject)) {
                return false;
            }
            
            MObject obj = (MObject) o;
            if (obj.getMClass() != this.cls) {
                return false;
            }
            return (this.coll.contains(obj.getUuid()));
        }

        /**
         * Iterator implementation for this collection class..
         */
        @objid ("f72c2d37-7f76-4296-900c-31cb888dcfea")
        private final class IteratorImp implements Iterator<SmObjectImpl> {
            @objid ("d27d7680-7dae-4a9b-9f80-612b44fe4006")
            private final Iterator<String> it;

            @objid ("b5a07c72-9075-4cbf-b5b3-38e64471ab1a")
            private final IModelLoaderProvider loaderProvider;

            @objid ("d24491bf-9964-44cc-9b24-ff1797e6cbac")
            private final SmClass itCls;

            @objid ("bf242ff1-7ce6-46f1-83ed-6b2b785de982")
            IteratorImp(Iterator<String> it, IModelLoaderProvider loaderProvider, final SmClass cls) {
                this.it = it;
                this.loaderProvider = loaderProvider;
                this.itCls = cls;
            }

            @objid ("0271027a-550d-4dbc-a1d5-13625796fee4")
            @Override
            public boolean hasNext() {
                return this.it.hasNext();
            }

            @objid ("1a1b7f24-d14c-44df-97e0-b59848f1de8f")
            @Override
            public SmObjectImpl next() {
                String uuid = this.it.next();
                SmObjectImpl obj = getLoadedObject(this.itCls, uuid);
                if (obj == null) {
                    try (IModelLoader modelLoader = this.loaderProvider.beginLoadSession()){
                        obj = findById(modelLoader, this.itCls, uuid, false);
                    } catch (DuplicateObjectException e) {
                        throw new IllegalStateException(e.getMessage(), e);
                    }
                }
                return obj;
            }

            @objid ("c79b3a80-e904-466f-8086-5f07fe8c5adf")
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        }

    }

    @objid ("702319cf-4288-42bb-a51c-b68871dc94bd")
    private class LoadHelper implements ILoadHelper {
        @objid ("bec8a189-57d8-4f10-8915-1725f729b804")
        public LoadHelper() {
            // Nothing
        }

        @objid ("7a622eae-054f-473a-8979-0e00100342fd")
        @Override
        public SmObjectImpl getLocalRef(SmClass cls, String uuid, IModelLoader modelLoader) throws DuplicateObjectException {
            SmObjectImpl ret = findById(modelLoader, cls, uuid, false) ;
            return ret;
        }

        @objid ("b0234e99-19e1-49a2-8a0b-feb4c1582f84")
        @SuppressWarnings("synthetic-access")
        @Override
        public SmClass getClass(String clsid) {
            return JdbmRepository.this.modelLoaderProvider.getMetamodel().getMClass(clsid);
        }

    }

}
