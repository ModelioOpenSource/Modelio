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

package org.modelio.vcore.session.impl.storage.serialized;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vbasic.files.CloseOnFail;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.StorageErrorSupport;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.session.impl.storage.IModelLoaderProvider;
import org.modelio.vcore.session.impl.storage.StorageException;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmLiveId;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.SmObjectSmClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorReader;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorWriter;

/**
 * Repository implementation where each object is saved in one file using java serialization.
 * 
 * @author cmarin
 */
@objid ("006dd576-fd1a-1f27-a7da-001ec947cd2a")
public class SerializedRepository implements IRepository {
    @objid ("006e4434-fd1a-1f27-a7da-001ec947cd2a")
     byte rid;

    @objid ("006e4a6a-fd1a-1f27-a7da-001ec947cd2a")
     short kid;

    @objid ("006ddfda-fd1a-1f27-a7da-001ec947cd2a")
    private final Path dir;

    @objid ("006def3e-fd1a-1f27-a7da-001ec947cd2a")
    private Collection<SmObjectImpl> toSave;

    @objid ("006e0b4a-fd1a-1f27-a7da-001ec947cd2a")
    private Map<Path, SmObjectImpl> loaded = new HashMap<>();

    @objid ("006e285a-fd1a-1f27-a7da-001ec947cd2a")
    private Set<SmObjectImpl> loaded2 = new HashSet<>();

    @objid ("006e3e80-fd1a-1f27-a7da-001ec947cd2a")
    private IRepositoryObject handle;

    @objid ("00171f60-4fda-1f32-b43f-001ec947cd2a")
    private IModelLoaderProvider modelLoaderProvider;

    @objid ("aaf3a33b-c063-11e1-b511-001ec947ccaf")
    private final EmfResource emfResouce;

    @objid ("0c9a6ee1-d66d-11e1-adbb-001ec947ccaf")
    private final StorageErrorSupport errorSupport = new StorageErrorSupport(this);

    @objid ("dc3edad4-e4b0-405d-ba40-b93b1ebb545a")
    private final Path blobsDir;

    @objid ("c81efeff-77f1-4761-97ea-e5c8a68d71f8")
    private MetamodelDescriptor storedMetamodelDescriptor;

    /**
     * Initialize the repository.
     * 
     * @param dir the repository directory path.
     */
    @objid ("006e57d0-fd1a-1f27-a7da-001ec947cd2a")
    public SerializedRepository(final Path dir) {
        this.dir = dir;
        this.blobsDir = dir.resolve("blobs");
        
        this.toSave = new HashSet<>();
        this.handle = new SerializedRepositoryObject(this);
        this.emfResouce = new EmfResource(this);
    }

    @objid ("006e731e-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void addObject(final SmObjectImpl newObject) {
        this.toSave.add(newObject);
        newObject.setRepositoryObject(this.handle);
    }

    @objid ("0071c50a-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void close() {
        this.toSave = null;
        this.loaded = null;
        this.loaded2 = null;
        // cacheManager = null;
        this.handle = null;
        this.modelLoaderProvider = null;
    }

    @objid ("006f0554-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public Collection<MObject> findByAtt(SmClass cls, boolean withSubClasses, String att, Object val) {
        Collection<MObject> results = new ArrayList<>();
        
        try (final IModelLoader loader = this.modelLoaderProvider.beginLoadSession()) {
        
            // The search is first done for the metaclass itself
            findByAtt0(loader, cls, att, val, results);
            // and then it must be carried out for all the metaclass derived
            // from 'cls'
            if (withSubClasses) {
                for (SmClass c : cls.getAllSubClasses()) {
                    findByAtt0(loader, c, att, results, results);
                }
            }
        }
        return results;
    }

    @objid ("006f76ce-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public Collection<MObject> findByClass(SmClass cls, boolean withSubClasses) {
        Collection<MObject> results = new ArrayList<>();
        
        try (final IModelLoader loader = this.modelLoaderProvider.beginLoadSession()) {
            findByClass(loader, cls, withSubClasses, results);
        } catch (IOException e) {
            getErrorSupport().fireError(e);
        }
        return results;
    }

    @objid ("006fd27c-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public SmObjectImpl findById(final SmClass cls, final String siteIdentifier) {
        Path f = getFile(cls, siteIdentifier);
        if (!Files.isRegularFile(f)) {
            return null;
        }
        
        try (final IModelLoader loader = this.modelLoaderProvider.beginLoadSession()) {
            return getImpl(loader, cls, f);
        } catch (DuplicateObjectException e) {
            getErrorSupport().fireError(e);
        } catch (IOException e) {
            getErrorSupport().fireError(e);
        }
        return null;
    }

    @objid ("f4b86346-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public Collection<SmObjectImpl> getAllLoadedObjects() {
        return this.loaded2;
    }

    @objid ("bd8f867a-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public Iterable<SmObjectImpl> getAllObjects() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @objid ("0c9f3392-d66d-11e1-adbb-001ec947ccaf")
    @Override
    public StorageErrorSupport getErrorSupport() {
        return this.errorSupport;
    }

    /**
     * @return the repository path
     */
    @objid ("aaf605a0-c063-11e1-b511-001ec947ccaf")
    public String getPath() {
        return this.dir.toString();
    }

    @objid ("00714008-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public byte getRepositoryId() {
        return this.rid;
    }

    @objid ("00716060-fd1a-1f27-a7da-001ec947cd2a")
    @SuppressWarnings("hiding")
    @Override
    public void init(final byte rid) {
        this.rid = rid;
    }

    @objid ("006eb996-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public boolean isOpen() {
        return this.modelLoaderProvider != null;
    }

    @objid ("dc30c32a-8fb5-11e1-81e9-001ec947ccaf")
    @Override
    public boolean isStored(final SmObjectImpl val) {
        return (val.getRepositoryObject() == this.handle);
    }

    @objid ("dc30c330-8fb5-11e1-81e9-001ec947ccaf")
    @Override
    public void loadDynamicDep(final SmObjectImpl obj, final SmDependency dep) {
        Set<SmObjectImpl> s = new HashSet<>();
        
        try (final IModelLoader loader = this.modelLoaderProvider.beginLoadSession()) {
            findByClass(loader, dep.getType(), true, s);
        } catch (IOException e) {
            getErrorSupport().fireError(e);
        }
        
        for (SmObjectImpl v : s) {
            v.getRepositoryObject().loadDep(v, dep.getSymetric());
        }
    }

    @objid ("00718fa4-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public ISmObjectData loadObjectData(SmObjectImpl obj) {
        Path f = getFile(obj.getClassOf(), obj.getUuid());
        if (!Files.isRegularFile(f)) {
            return null;
        }
        
        try (final IModelLoader loader = this.modelLoaderProvider.beginLoadSession()) {
            return load(f);
        } catch (IOException e) {
            getErrorSupport().fireError(e);
        }
        return null;
    }

    @objid ("006ee1be-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void open(final IModelLoaderProvider aModelLoader, IModelioProgress monitor) throws IOException {
        this.modelLoaderProvider = aModelLoader;
        this.kid = this.modelLoaderProvider.getKid();
        
        Files.createDirectories(this.blobsDir);
    }

    @objid ("006e9f88-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void save(IModelioProgress monitor) {
        try {
            saveMetamodelDescriptor();
        
            for (SmObjectImpl i : this.toSave) {
                save(i.getData());
            }
            this.toSave.clear();
            
        } catch (IOException e) {
            getErrorSupport().fireError(e);
        }
    }

    /**
     * @return the EMF adapter.
     */
    @objid ("aaf6059c-c063-11e1-b511-001ec947ccaf")
    Resource getEmfResource() {
        return this.emfResouce;
    }

    /**
     * @return <code>true</code> if some objects need to be saved.
     */
    @objid ("aaf60593-c063-11e1-b511-001ec947ccaf")
    @Override
    public boolean isDirty() {
        return this.toSave != null && !this.toSave.isEmpty();
    }

    @objid ("0070e98c-fd1a-1f27-a7da-001ec947cd2a")
    boolean isLoaded(final SmObjectImpl obj) {
        return this.loaded2.contains(obj) && !isToReload(obj);
    }

    @objid ("0071110a-fd1a-1f27-a7da-001ec947cd2a")
    void load(final SmObjectImpl obj) throws DuplicateObjectException, IOException {
        Path f = getFile(obj.getClassOf(), obj.getUuid());
        if (!Files.isRegularFile(f)) {
            return;
        }
        
        try (final IModelLoader loader = this.modelLoaderProvider.beginLoadSession()) {
            getImpl(loader, obj.getClassOf(), f);
        }
    }

    @objid ("0071280c-fd1a-1f27-a7da-001ec947cd2a")
    void removeFromStorage(final SmObjectImpl obj) throws IOException {
        Path f = getFile(obj.getClassOf(), obj.getUuid());
        if (Files.isRegularFile(f)) {
            Files.delete(f);
        }
    }

    @objid ("0070d352-fd1a-1f27-a7da-001ec947cd2a")
    void setModified(final SmObjectImpl obj) {
        this.toSave.add(obj);
    }

    /**
     * Unload the given object.
     * 
     * @param obj the object to unload.
     */
    @objid ("f4b600ee-08b1-11e2-b33c-001ec947ccaf")
    void unload(SmObjectImpl obj) {
        Path f = getFile(obj.getClassOf(), obj.getUuid());
        this.loaded.remove(f);
        this.loaded2.remove(obj);
    }

    @objid ("006f4456-fd1a-1f27-a7da-001ec947cd2a")
    private void findByAtt0(IModelLoader loader, final SmClass cls, final String attName, final Object val, final Collection<MObject> results) {
        SmAttribute att = cls.getAttributeDef(attName);
        if (att == null) {
            throw new IllegalArgumentException("No '" + attName + "' attribute on '" + cls.getName() + "'");
        }
        
        try (DirectoryStream<Path> dirStream = listFiles(cls);) {
            for (Path f : dirStream) {
                SmObjectImpl impl = getImpl(loader, cls, f);
                if (impl != null && impl.getAttVal(att).equals(val)) {
                    assert (SmLiveId.getKid(impl.getLiveId()) == this.kid);
                    if ((SmLiveId.getKid(impl.getLiveId()) != this.kid)) {
                        throw getErrorSupport().fireError(new StorageException(this, "bad kernel id"));
                    }
                    results.add(impl);
                }
            }
        } catch (Exception e) {
            getErrorSupport().fireError(e);
        }
    }

    @objid ("006fa9aa-fd1a-1f27-a7da-001ec947cd2a")
    private void findByClass0(IModelLoader loader, final SmClass cls, final Collection<? super SmObjectImpl> results) throws IOException {
        try (DirectoryStream<Path> dirStream = listFiles(cls)) {
            for (Path f : dirStream) {
                try {
                    SmObjectImpl impl = getImpl(loader, cls, f);
                    if (impl != null) {
                        assert ((SmLiveId.getKid(impl.getLiveId()) == this.kid));
                        results.add(impl);
                    }
                } catch (DuplicateObjectException e) {
                    getErrorSupport().fireError(e);
                }
            }
        }
    }

    @objid ("0f9b1945-c1f0-11e1-92d5-001ec947ccaf")
    private void findByClass(IModelLoader loader, final SmClass cls, boolean withSubClasses, final Collection<? super SmObjectImpl> results) throws IOException {
        // The search is first done for the metaclass itself
        findByClass0(loader, cls, results);
        // and then it must be carried out for all the metaclass derived from
        // 'cls'
        if (withSubClasses) {
            for (SmClass c : cls.getAllSubClasses()) {
                findByClass0(loader, c, results);
            }
        }
    }

    @objid ("00702c54-fd1a-1f27-a7da-001ec947cd2a")
    private Path getFile(final SmClass cls, final String id) {
        return this.dir.resolve(cls.getName()).resolve(id.toString());
    }

    @objid ("007004fe-fd1a-1f27-a7da-001ec947cd2a")
    private SmObjectImpl getImpl(IModelLoader loader, final SmClass cls, final Path f) throws DuplicateObjectException, IOException {
        SmObjectImpl impl = this.loaded.get(f);
        if (impl != null) {
            if (isToReload(impl)) {
                reload(loader, impl, f);
            }
        
            return impl;
        } else {
        
            SmObjectData d = load(f);
        
            impl = loader.createLoadedObject(cls, d.getUuid(), d);
        
            this.loaded.put(f, impl);
            this.loaded2.add(impl);
        
            d.setRFlags(IRStatus.REPO_LOADED,0 , 0);
        
            return impl;
        }
    }

    @objid ("aaf3a33d-c063-11e1-b511-001ec947ccaf")
    private DirectoryStream<Path> listFiles(final SmClass cls) throws IOException {
        return Files.newDirectoryStream(this.dir.resolve(cls.getName()));
    }

    @objid ("0070bade-fd1a-1f27-a7da-001ec947cd2a")
    private SmObjectData load(final Path f) throws IOException {
        try (ObjectInputStream s = new MyObjectInputStream(Files.newInputStream(f))) {
        
            SmObjectData data = (SmObjectData) s.readObject();
            data.setRepositoryObject(this.handle);
        
            return data;
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    @objid ("0070594a-fd1a-1f27-a7da-001ec947cd2a")
    private void save(final ISmObjectData data) throws IOException {
        Path file = getFile(data.getClassOf(), data.getUuid());
        Files.createDirectories(file.getParent());
        
        try (OutputStream os = Files.newOutputStream(file); ObjectOutputStream s = new ObjectOutputStream(os)) {
            s.writeObject(data);
        }
    }

    @objid ("dbc589ed-4868-11e2-91c9-001ec947ccaf")
    final boolean isDirty(SmObjectImpl obj) {
        return this.toSave.contains(obj);
    }

    @objid ("2219010f-94bb-4b29-9cf1-7d35bc38e2ee")
    @Override
    public OutputStream writeBlob(IBlobInfo key) throws IOException {
        OutputStream os = Files.newOutputStream(this.blobsDir.resolve(key.getKey()+".blob"));
        
        ObjectOutputStream oos = new ObjectOutputStream(os);
        
        try (CloseOnFail shield = new CloseOnFail(oos)){
            oos.writeObject(key);
            shield.success();
        }
        return oos;
    }

    @objid ("4a063377-e529-4f2a-8bc4-455d1f52a25e")
    @Override
    public InputStream readBlob(String key) throws IOException {
        Path blobPath = getBlobPath(key);
        if (Files.isRegularFile(blobPath)) {
            InputStream is = Files.newInputStream(blobPath);
            ObjectInputStream iis = new ObjectInputStream(is);
            try (CloseOnFail shield = new CloseOnFail(iis)){
                // Read & ignore blob info
                iis.readObject();
                shield.success();
            } catch (ClassNotFoundException e) {
                throw new IOException(e.getLocalizedMessage(), e);
            }
            return iis;
        } else {
            return null;
        }
    }

    @objid ("089bbed9-366a-48a2-91c9-fcd7ee797c72")
    private Path getBlobPath(String key) {
        Path blobPath = this.blobsDir.resolve(key+".blob");
        return blobPath;
    }

    @objid ("59b54554-fada-461a-8c0a-637de9f27030")
    @Override
    public void removeBlob(String key) throws IOException {
        Path blobPath = getBlobPath(key);
        Files.deleteIfExists(blobPath);
    }

    @objid ("104a5416-a2bf-4fe1-bf9a-d6eb1ba3b775")
    @Override
    public IBlobInfo readBlobInfo(String key) throws IOException {
        Path blobPath = getBlobPath(key);
        if (Files.isRegularFile(blobPath)) {
        
            try (ObjectInputStream iis = new ObjectInputStream(Files.newInputStream(blobPath));) {
                // Read & return blob info
                return (IBlobInfo) iis.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException(e.getLocalizedMessage(), e);
            }
        } else {
            return null;
        }
    }

    @objid ("eaea7315-0f93-43d6-a90b-7360ce38d737")
    SmMetamodel getMetamodel() {
        return this.modelLoaderProvider.getMetamodel();
    }

    @objid ("3c8fddc0-da66-4628-af48-431b7bc44076")
    @Override
    public void addCreatedObject(SmObjectImpl newObject) {
        addObject(newObject);
    }

    @objid ("fa06b2d4-f122-4a85-8595-f1c559d2d8b2")
    void setToReload(SmObjectImpl obj) {
        // remove loaded flag
        obj.getData().setRFlags(0, IRStatus.REPO_LOADED, 0);
    }

    @objid ("ca57dd33-9bf6-4db3-819f-d46ef616f9b7")
    private void reload(IModelLoader loader, SmObjectImpl impl, Path f) throws IOException {
        SmObjectData srcData = load(f);
        SmObjectSmClass cls = srcData.getClassOf();
        
        for (SmAttribute smAttribute : cls.getAllAttDef()) {
            loader.loadAttribute(impl, smAttribute, smAttribute.getValue(srcData));
        }
        
        for (SmDependency dep : cls.getAllDepDef()) {
            if (dep.isComponent() || dep.isPartOf() ||dep.isSharedComposition()) {
                if (! dep.isMultiple()) {
                    SmObjectImpl readValue = (SmObjectImpl) dep.getValue(srcData);
                    SmObjectImpl realo = loader.loadForeignObject(readValue.getClassOf(), readValue.getUuid(), readValue.getName());
        
                    loader.loadDependency(impl, dep, Collections.singletonList(realo));
                } else {
                    Collection<SmObjectImpl> readcontent = dep.getValueAsCollection(srcData);
        
                    // as the read objects may be a copy of real data, look for the real objects
                    List<SmObjectImpl> newcontent = new ArrayList<>(readcontent.size());
                    for (SmObjectImpl ro : readcontent) {
                        SmObjectImpl realo = loader.loadForeignObject(ro.getClassOf(), ro.getUuid(), ro.getName());
                        newcontent.add(realo);
                    }
        
                    loader.loadDependency(impl, dep, newcontent);
                }
            }
        }
        
        impl.getData().setRFlags(IRStatus.REPO_LOADED, 0, 0);
    }

    @objid ("526911cb-4f09-4554-b78b-19a48dcea2a1")
    private boolean isToReload(SmObjectImpl impl) {
        return impl.hasStatus(IRStatus.REPO_LOADED);
    }

    /**
     * Save a descriptor of the current metamodel.
     * 
     * @throws java.io.IOException on failure
     */
    @objid ("ba3a638b-f344-482b-8298-dae0f63ca993")
    private void saveMetamodelDescriptor() throws IOException {
        try (OutputStream out = Files.newOutputStream(this.dir.resolve("metamodel_descriptor.xml"))){
            MetamodelDescriptor desc = this.modelLoaderProvider.getMetamodel().serialize();
            new MetamodelDescriptorWriter().write(desc, out);
            this.storedMetamodelDescriptor = desc;
        }
    }

    @objid ("57bebe9e-0def-46b5-a1f8-c25fda382e42")
    private MetamodelDescriptor loadMetamodelDescriptor() {
        Path location = this.dir.resolve("metamodel_descriptor.xml");
        try (InputStream in = Files.newInputStream(location)){
            MetamodelDescriptor desc = MetamodelDescriptorReader.readFrom(in, location.toString());
            return desc;
        } catch(FileNotFoundException | NoSuchFileException e) {
            return null;
        } catch (IOException e) {
            getErrorSupport().fireWarning(e);
            return null;
        }
    }

    @objid ("99e11d1f-5081-4689-8c56-ccb124b36a5d")
    @Override
    public Optional<MetamodelDescriptor> getMetamodelDescriptor() {
        if (this.storedMetamodelDescriptor == null) {
            this.storedMetamodelDescriptor = loadMetamodelDescriptor();
        }
        return Optional.ofNullable(this.storedMetamodelDescriptor);
    }

    @objid ("00706c14-fd1a-1f27-a7da-001ec947cd2a")
    private class MyObjectInputStream extends ObjectInputStream {
        @objid ("007073ee-fd1a-1f27-a7da-001ec947cd2a")
        public MyObjectInputStream(final InputStream inputStream) throws IOException {
            super(inputStream);
            enableResolveObject(true);
        }

        @objid ("00708bcc-fd1a-1f27-a7da-001ec947cd2a")
        @Override
        protected Object resolveObject(final Object obj) throws IOException {
            // if (obj instanceof SmLiveId) {
            // SmLiveId liveId = (SmLiveId) obj;
            // liveId.kid = SerializedRepository.this.kid;
            // liveId.rid = SerializedRepository.this.rid;
            // }
            return obj;
        }

    }

}
