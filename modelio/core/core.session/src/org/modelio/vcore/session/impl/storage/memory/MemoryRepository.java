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
package org.modelio.vcore.session.impl.storage.memory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.model.MObjectCache;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.api.repository.StorageErrorSupport;
import org.modelio.vcore.session.impl.storage.IModelLoaderProvider;
import org.modelio.vcore.smkernel.IRepositoryObject;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;

/**
 * Memory repository.
 * <p>
 * Only remembers what is in this repository.
 * The implementation currently does not support model reloading.
 */
@objid ("01856643-929b-11e1-81e9-001ec947ccaf")
public class MemoryRepository implements IRepository, IRepositoryObject {
    @objid ("bd813875-92d7-11e1-81e9-001ec947ccaf")
    private byte rid;

    @objid ("bd813874-92d7-11e1-81e9-001ec947ccaf")
    private MObjectCache cache;

    @objid ("0d225411-d66d-11e1-adbb-001ec947ccaf")
    private StorageErrorSupport errorSupport = new StorageErrorSupport(this);

    @objid ("f5345c85-08b1-11e2-b33c-001ec947ccaf")
    private IModelLoaderProvider modelLoaderProvider;

    @objid ("c0c04c0e-2d9b-11e2-8aaa-001ec947ccaf")
    private MemoryEmfResource emfResource;

    @objid ("43ebaaf5-d778-4990-a01c-7171007fee70")
    private Map<String, BlobEntry> blobs = new HashMap<>();

    /**
     * Create a new memory repository.
     */
    @objid ("c0c04c0f-2d9b-11e2-8aaa-001ec947ccaf")
    public  MemoryRepository() {
        this.emfResource = new MemoryEmfResource(this);
    }

    @objid ("bd839b04-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void addObject(final SmObjectImpl newObject) {
        this.cache.putToCache(newObject);
        newObject.setRepositoryObject(this);
        
    }

    @objid ("bd813876-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void attModified(final SmObjectImpl obj, final SmAttribute att) {
        // nothing to do
    }

    @objid ("bd839af8-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void attach(final SmObjectImpl obj) {
        this.cache.putToCache(obj);
        obj.setRepositoryObject(this);
        
    }

    @objid ("bd85fd39-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void close() {
        this.modelLoaderProvider = null;
    }

    @objid ("bd839ac6-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void depValAppended(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) {
        // nothing to do
    }

    @objid ("bd839acc-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void depValErased(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) {
        // nothing to do
    }

    @objid ("bd839ad2-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void depValMoved(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) {
        // nothing to do
    }

    @objid ("bd839af4-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void detach(final SmObjectImpl obj) {
        this.cache.removeFromCache(obj);
    }

    @objid ("bd839b17-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public Collection<MObject> findByAtt(final SmClass cls, boolean withSubClasses, final String att, final Object val) {
        Collection<MObject> results = new ArrayList<>();
        this.cache.findByAtt(cls, withSubClasses, att, val, results);
        return results;
    }

    @objid ("bd839b20-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public Collection<MObject> findByClass(final SmClass cls, boolean withSubClasses) {
        Collection<MObject> results = new ArrayList<>();
        this.cache.findByClass(cls, withSubClasses, results);
        return results;
    }

    @objid ("bd85fd20-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public SmObjectImpl findById(final SmClass cls, final String siteIdentifier) {
        return this.cache.findById(cls, siteIdentifier);
    }

    @objid ("f5392131-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public Collection<SmObjectImpl> getAllLoadedObjects() {
        return this.cache.asCollection();
    }

    @objid ("bd85fd47-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public Iterable<SmObjectImpl> getAllObjects() {
        return this.cache.getIterable();
    }

    @objid ("0aae5ea9-c064-11e1-b511-001ec947ccaf")
    @Override
    public Resource getEmfResource() {
        return this.emfResource;
    }

    @objid ("0d297b0a-d66d-11e1-adbb-001ec947ccaf")
    @Override
    public StorageErrorSupport getErrorSupport() {
        return this.errorSupport;
    }

    /**
     * @return the model loader provider.
     */
    @objid ("22a411f2-2d7d-11e2-8aaa-001ec947ccaf")
    public IModelLoaderProvider getModelLoaderProvider() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.modelLoaderProvider;
    }

    @objid ("bd85fd27-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public byte getRepositoryId() {
        return this.rid;
    }

    @objid ("bd85fd2c-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void init(final byte repoId) {
        this.rid = repoId;
    }

    @objid ("bd839ad8-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public boolean isDepLoaded(final SmObjectImpl obj, final SmDependency dep) {
        return true;
    }

    @objid ("bd839ae5-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public boolean isAttLoaded(SmObjectImpl obj, SmAttribute att) {
        return true;
    }

    @objid ("bd839b0d-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public boolean isOpen() {
        return this.modelLoaderProvider != null;
    }

    @objid ("bd839adf-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public boolean isPersistent(final SmDependency dep) {
        return true;
    }

    @objid ("bd85fd41-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public boolean isStored(final SmObjectImpl val) {
        return val.getRepositoryObject() == this;
    }

    @objid ("bd839aeb-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void loadDep(final SmObjectImpl obj, final SmDependency dep) {
        // nothing to do
    }

    @objid ("bd85fd3c-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void loadDynamicDep(final SmObjectImpl obj, final SmDependency dep) {
        // nothing to do
    }

    @objid ("bd839af0-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void loadAtt(SmObjectImpl obj, SmAttribute att) {
        // nothing to do
    }

    @objid ("bd85fd32-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public ISmObjectData loadObjectData(SmObjectImpl obj) {
        return null;
    }

    @objid ("bd839b12-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void open(final IModelLoaderProvider aModelLoader, IModelioProgress monitor) {
        this.modelLoaderProvider = aModelLoader;
        this.cache = new MObjectCache(this.modelLoaderProvider.getMetamodel());
        
    }

    @objid ("bd839b0a-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public void save(IModelioProgress monitor) {
        // nothing to do
    }

    @objid ("f5392138-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public void unload(SmObjectImpl obj) {
        this.cache.removeFromCache(obj);
    }

    @objid ("db616826-4868-11e2-91c9-001ec947ccaf")
    @Override
    public boolean isDirty(SmObjectImpl obj) {
        return true;
    }

    /**
     * A memory repository does not need save.
     */
    @objid ("750b24eb-50bc-48eb-a98d-5b4b31b082b3")
    @Override
    public boolean isDirty() {
        return false;
    }

    @objid ("9ac86266-5bad-429b-996b-b8818d39057f")
    @Override
    public InputStream readBlob(String key) throws IOException {
        BlobEntry entry = this.blobs.get(key);
        
        if (entry==null) {
            return null;
        } else {
            return new ByteArrayInputStream(entry.content);
        }
        
    }

    @objid ("9c1bc37b-ea33-45c8-8eac-3e171e55475b")
    @Override
    public java.io.OutputStream writeBlob(final IBlobInfo info) throws IOException {
        final Map<String, BlobEntry> theBlobs = this.blobs;
        
        ByteArrayOutputStream out = new ByteArrayOutputStream() {
            @Override
            public void close() throws IOException {
                BlobEntry entry = new BlobEntry();
                entry.info = info;
                entry.content = toByteArray();
                theBlobs.put(info.getKey(), entry);
        
                super.close();
            }
        };
        return out;
    }

    @objid ("a8607a9a-cccc-44b1-922d-bf041b5e6a47")
    @Override
    public void removeBlob(String key) {
        this.blobs.remove(key);
    }

    @objid ("03e1f486-076f-4857-bea5-812bcac3d6b3")
    @Override
    public IBlobInfo readBlobInfo(String key) throws IOException {
        BlobEntry entry = this.blobs.get(key);
        
        if (entry==null) {
            return null;
        } else {
            return entry.info;
        }
        
    }

    /**
     * Get a metaclass from its name.
     * @param name a metaclass name.
     * @return the metaclass.
     */
    @objid ("5685e97a-a516-4a3e-af7a-a4811313d13f")
    SmClass getMetaclass(String name) {
        return this.modelLoaderProvider.getMetamodel().getMClass(name);
    }

    @objid ("c72716f1-2afb-408c-8c05-c37e87bcb65a")
    @Override
    public void addCreatedObject(SmObjectImpl newObject) {
        addObject(newObject);
    }

    @objid ("501cc008-9f31-4a86-9d97-e549274cf12d")
    @Override
    public void attachCreatedObj(SmObjectImpl obj) {
        attach(obj);
    }

    @objid ("6ea72e6c-fa2f-4c37-aeef-af1f37339547")
    @Override
    public void setToReload(SmObjectImpl obj) {
        // TODO Auto-generated method stub
    }

    @objid ("666a4379-8c7c-411c-8598-1a9db6cbdefa")
    @Override
    public Optional<MetamodelDescriptor> getMetamodelDescriptor() {
        return Optional.of(this.modelLoaderProvider.getMetamodel().serialize());
    }

    @objid ("4003ed8a-815e-45c3-88ec-f10cedb3ea98")
    private static class BlobEntry {
        @objid ("6adf73cd-d354-4fe7-a6bf-eb2d274d47a0")
        byte[] content;

        @objid ("e1ea0ac7-0253-4af6-a3ab-ddceefc33146")
        IBlobInfo info;

        @objid ("df6d6b7f-02d2-436f-87d7-4c7c5a09465a")
        public  BlobEntry() {
            // TODO Auto-generated constructor stub
        }

    }

}
