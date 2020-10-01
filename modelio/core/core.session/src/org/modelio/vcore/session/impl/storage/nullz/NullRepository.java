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

package org.modelio.vcore.session.impl.storage.nullz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vbasic.progress.IModelioProgress;
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
 * Repository that stores nothing.
 */
@objid ("0048e61c-eb1c-1f22-8c06-001ec947cd2a")
public class NullRepository implements IRepository, IRepositoryObject {
    @objid ("00633292-fd1a-1f27-a7da-001ec947cd2a")
    private byte rid;

    @objid ("0042afc2-4eb2-1f29-adbc-001ec947cd2a")
    private static NullRepository instance = new NullRepository();

    @objid ("0d0cdeff-d66d-11e1-adbb-001ec947ccaf")
    private StorageErrorSupport errorSupport = new StorageErrorSupport(this);

    @objid ("0048fc42-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void attModified(final SmObjectImpl obj, final SmAttribute att) {
        // no op
    }

    @objid ("004923a2-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void depValAppended(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) {
        // no op
    }

    @objid ("00494ab2-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void depValErased(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) {
        // no op
    }

    @objid ("004971fe-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void depValMoved(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) {
        // no op
    }

    @objid ("0049994a-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public boolean isDepLoaded(final SmObjectImpl obj, final SmDependency dep) {
        return true;
    }

    @objid ("0049c9d8-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public boolean isPersistent(final SmDependency dep) {
        return true;
    }

    @objid ("0049f5fc-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public boolean isAttLoaded(SmObjectImpl obj, SmAttribute att) {
        return true;
    }

    @objid ("004a21d0-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void loadDep(final SmObjectImpl obj, final SmDependency dep) {
        // no op
    }

    @objid ("004a52a4-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void loadAtt(SmObjectImpl obj, SmAttribute att) {
        // no op
    }

    @objid ("004a7f22-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void detach(final SmObjectImpl obj) {
        // no op
    }

    @objid ("004ae818-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public byte getRepositoryId() {
        return this.rid;
    }

    @objid ("004b1144-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void save(IModelioProgress monitor) {
        // no op
    }

    @objid ("004b2d78-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public boolean isOpen() {
        return true;
    }

    @objid ("004b5744-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void open(final IModelLoaderProvider aModelLoader, IModelioProgress monitor) {
        // no op
    }

    @objid ("004b7ca6-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public Collection<MObject> findByAtt(SmClass cls, boolean withSubClasses, String att, Object val) {
        return Collections.emptyList();
    }

    @objid ("004bbc98-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public Collection<MObject> findByClass(SmClass cls, boolean withSubClasses) {
        return Collections.emptyList();
    }

    @objid ("004bf096-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public SmObjectImpl findById(final SmClass cls, final String siteIdentifier) {
        return null;
    }

    @objid ("004c244e-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void attach(final SmObjectImpl newObject) {
        newObject.setRepositoryObject(this);
    }

    @objid ("004c5176-eb1c-1f22-8c06-001ec947cd2a")
    @Override
    public void addObject(final SmObjectImpl newObject) {
        newObject.setRepositoryObject(this);
    }

    /**
     * Instantiate the repository
     */
    @objid ("00633e90-fd1a-1f27-a7da-001ec947cd2a")
    public NullRepository() {
    }

    @objid ("0067a1c4-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public ISmObjectData loadObjectData(SmObjectImpl obj) {
        return null;
    }

    @objid ("0067d7ca-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void init(final byte aRid) {
        this.rid = aRid;
    }

    @objid ("00680510-fd1a-1f27-a7da-001ec947cd2a")
    @Override
    public void close() {
        // no op
    }

    /**
     * @return the singleton instance.
     */
    @objid ("00428984-4eb2-1f29-adbc-001ec947cd2a")
    public static NullRepository getInstance() {
        return instance;
    }

    @objid ("dc2739d3-8fb5-11e1-81e9-001ec947ccaf")
    @Override
    public void loadDynamicDep(final SmObjectImpl obj, final SmDependency dep) {
        // no op
    }

    @objid ("dc2bfe7b-8fb5-11e1-81e9-001ec947ccaf")
    @Override
    public boolean isStored(final SmObjectImpl val) {
        return val.getRepositoryObject() == this;
    }

    @objid ("bd8ac1d1-92d7-11e1-81e9-001ec947ccaf")
    @Override
    public Iterable<SmObjectImpl> getAllObjects() {
        return Collections.emptyList();
    }

    @objid ("0ab32359-c064-11e1-b511-001ec947ccaf")
    @Override
    public Resource getEmfResource() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @objid ("0d11a3af-d66d-11e1-adbb-001ec947ccaf")
    @Override
    public StorageErrorSupport getErrorSupport() {
        return this.errorSupport;
    }

    @objid ("f523ac39-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public Collection<SmObjectImpl> getAllLoadedObjects() {
        return Collections.emptyList();
    }

    @objid ("f523ac40-08b1-11e2-b33c-001ec947ccaf")
    @Override
    public void unload(SmObjectImpl obj) {
        // nothing to do
    }

    @objid ("dbccb0eb-4868-11e2-91c9-001ec947ccaf")
    @Override
    public boolean isDirty(SmObjectImpl obj) {
        return false;
    }

    @objid ("395b4619-0ea4-4fe9-8442-8aa11c044ae1")
    @Override
    public boolean isDirty() {
        return false;
    }

    @objid ("ff861e79-9f7b-4fc7-bb63-5965459c53c0")
    @Override
    public InputStream readBlob(String key) throws IOException {
        return null;
    }

    @objid ("2ec09876-1958-4efb-9fd6-2826160bd34f")
    @Override
    public OutputStream writeBlob(IBlobInfo key) throws IOException {
        // Instantiate a dummy output stream
        return new OutputStream() {
        
                    @Override
                    public void write(int b) throws IOException {
                        // ignore
                    }
        
                    @Override
                    public void write(byte[] b, int off, int len) throws IOException {
                        // ignore
                    }
                };
    }

    @objid ("bf054ad0-ac52-4051-8fb3-8e89cade165b")
    @Override
    public void removeBlob(String key) {
        // ignore
    }

    @objid ("dabaceff-dc16-45ba-b69f-8feecde203e9")
    @Override
    public IBlobInfo readBlobInfo(String key) throws IOException {
        return null;
    }

    @objid ("25b0d1d7-8713-4373-a671-b338b10b6448")
    @Override
    public void attachCreatedObj(SmObjectImpl obj) {
        attach(obj);
    }

    @objid ("c4632741-aa27-4a20-aed3-102bbe927ed9")
    @Override
    public void addCreatedObject(SmObjectImpl newObject) {
        addObject(newObject);
    }

    @objid ("6e97bcd5-007e-4c88-bdc4-56d5fc6a8e77")
    @Override
    public void setToReload(SmObjectImpl obj) {
        // nothing to do
    }

    @objid ("bf34c67e-495f-408d-86bc-d4bfe38150ff")
    @Override
    public Optional<MetamodelDescriptor> getMetamodelDescriptor() {
        return Optional.empty();
    }

}
