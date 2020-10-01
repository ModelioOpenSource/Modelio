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

package org.modelio.vstore.exml.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.NoSuchFileException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.CloseOnFail;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.BlobServices;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vstore.exml.common.RepositoryVersions;
import org.modelio.vstore.exml.common.model.ObjId;

/**
 * Base implementation of {@link IExmlResourceProvider}.
 * <p>
 * All implementations of {@link IExmlResourceProvider} should inherit from this class.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("b8bee60d-2a13-4520-9eb1-0999429f32d1")
public abstract class AbstractExmlResourceProvider implements IExmlResourceProvider {
    @objid ("5426525d-2547-44c3-a907-673fb9b7fad8")
    private IExmlRepositoryGeometry geometry;

    /**
     * {@inheritDoc}
     * <p>
     * Initialize a new repository geometry first before calling {@link #doCreateRepository(MMetamodel)}.
     * <p>
     * Subclasses must redefine {@link #doCreateRepository(MMetamodel)} which is
     * called at the end instead of this method.
     */
    @objid ("e1a7d945-a475-4f2d-97eb-aeb2632ab31d")
    @Override
    public void createRepository(MMetamodel metamodel) throws IOException {
        this.geometry = new ExmlRepositoryGeometry2();
        doCreateRepository(metamodel);
    }

    @objid ("bdb40586-18b8-45f4-b44f-e22437648ff4")
    @Override
    public final void deleteBlob(String blob) throws IOException {
        String blobPath = getGeometry().getBlobPath(blob);
        ExmlResource res = getRelativePathResource(blobPath);
        res.delete();
    }

    @objid ("d7d93bce-5a26-4205-b5c5-fba2a71526e3")
    @Override
    public final IExmlRepositoryGeometry getGeometry() {
        if (this.geometry==null) {
            throw new IllegalStateException("Call open() or createRepository() first.");
        }
        return this.geometry;
    }

    /**
     * Redefine only if really necessary. Redefining {@link #getResource(String)} should be suffisient.
     * @throws IOException
     */
    @objid ("bb3d84ce-1125-4c7d-ba3a-75a873d9b5ee")
    @Override
    public ExmlResource getLocalResource(ObjId cmsNodeId) throws IOException {
        return getRelativePathResource(getGeometry().getLocalFileRelativePath(cmsNodeId.toMRef()));
    }

    @objid ("c402d40a-e3b8-482c-a828-bd4bd35294b1")
    @Override
    public ExmlResource getMetamodelDescriptorResource() throws IOException {
        if (this.geometry == null) {
            open();
        }
        return getRelativePathResource(getGeometry().getMetamodelDescriptorPath());
    }

    /**
     * Redefine only if really necessary. Redefining {@link #getResource(String)} should be suffisient.
     * 
     * @throws java.io.IOException in case of failure
     */
    @objid ("8901ae83-c450-468c-bc38-e074402ef419")
    @Override
    public final ExmlResource getRepositoryVersionResource() throws IOException {
        return getRelativePathResource(IExmlRepositoryGeometry.FORMAT_VERSION_PATH);
    }

    /**
     * Redefine only if really necessary. Redefining {@link #getResource(String)} should be suffisient.
     */
    @objid ("0cb8b28a-0302-4f81-966f-d14b7a186978")
    @Override
    public ExmlResource getResource(ObjId id) throws IOException {
        return getRelativePathResource(getGeometry().getRelativePath(id.toMRef()));
    }

    @objid ("f5e3c652-c17d-434e-9398-7ee41a22f785")
    @Override
    public void open() throws IOException {
        if (! exists()) {
            throw new FileNotFoundException("The repository at '"+getURI()+"' does not exist.");
        }
        
        this.geometry = readGeometry();
    }

    @objid ("b45202ed-785e-48e6-8fa4-4c424f24f4a3")
    @Override
    public final InputStream readBlob(String key) throws IOException {
        String blobPath = getGeometry().getBlobPath(key);
        ExmlResource res = getRelativePathResource(blobPath);
        InputStream is = res.bufferedRead();
        if (is != null) {
            try (CloseOnFail c = new CloseOnFail(is)) {
                // consume the IBlobInfo stored first in the file.
                @SuppressWarnings("unused")
                IBlobInfo unused = BlobServices.readBlobInfo(is);
        
                c.success();
            }
        }
        return is;
    }

    @objid ("231035bc-875e-470e-881f-47cd59c026b0")
    @Override
    public final IBlobInfo readBlobInfo(String key) throws IOException {
        String blobPath = getGeometry().getBlobPath(key);
        ExmlResource res = getRelativePathResource(blobPath);
        try (InputStream is = res.bufferedRead()) {
            if (is != null) {
                return BlobServices.readBlobInfo(is);
            } else {
                return null;
            }
        }
    }

    /**
     * Read the repository formats versions from {@link #getRepositoryVersionResource()}.
     * <p>
     * May return <i>null</i> if it is an old repository with no format version file.
     * 
     * @return the repository versions, null if none stored yet.
     * @throws java.io.IOException in case of error getting the versions
     */
    @objid ("1255b148-7bb7-45e7-bc9c-bd8e927999ad")
    @Override
    public RepositoryVersions readRepositoryVersion() throws IOException {
        try (InputStream inStream = getRepositoryVersionResource().bufferedRead();) {
            if (inStream != null) {
                return RepositoryVersions.fromStream(inStream);
            } else {
                return null;
            }
        } catch (FileNotFoundException| NoSuchFileException e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Delegates to {@link #doCreateRepository(MMetamodel)}.
     * <p>
     * Subclasses may redefine either this method or {@link #doCreateRepository(MMetamodel)} .
     */
    @objid ("84b6622a-d4cc-402a-bed3-fc57ccd5c3a4")
    @Override
    public void updateRepositoryStructure(MMetamodel metamodel) throws IOException {
        doCreateRepository(metamodel);
    }

    @objid ("813597e2-3ee0-4bab-906e-f1e77abb901e")
    @Override
    public final OutputStream writeBlob(IBlobInfo info) throws IOException {
        String blobPath = getGeometry().getBlobPath(info.getKey());
        ExmlResource res = getRelativePathResource(blobPath);
        
        OutputStream os = res.bufferedWrite();
        
        try (CloseOnFail c = new CloseOnFail(os)) {
            BlobServices.write(info, os);
            c.success();
        }
        return os;
    }

    /**
     * Called by {@link #createRepository(MMetamodel)} and {@link #updateRepositoryStructure(MMetamodel)} default implementation.
     * <p>
     * Subclasses must redefine this method to do something for {@link #createRepository(MMetamodel)}.
     * {@link #getGeometry()} works inside this method.
     * 
     * @param metamodel the metamodel
     * @throws java.io.IOException on failure.
     */
    @objid ("b674901e-8a4c-4c01-9cf3-2c8fbe82e18e")
    protected abstract void doCreateRepository(MMetamodel metamodel) throws IOException;

    /**
     * Read the repository geometry from the repository.
     * <p>
     * Called by {@link #open()}.
     * Implementations may call {@link #readGeometry(File)}.
     * 
     * @return the repository geometry.
     * @throws java.io.IOException on non recoverable failure.
     */
    @objid ("b8c2b13a-b5e3-498f-b496-e0d3acab341f")
    protected IExmlRepositoryGeometry readGeometry() throws IOException {
        RepositoryVersions repositoryVersion = readRepositoryVersion();
        
        int repoFormat = (repositoryVersion == null) ? 0 :repositoryVersion.getRepositoryFormat();
        if (repoFormat < 2) {
            return new ExmlRepositoryGeometry1();
        } else if (repoFormat <= RepositoryVersions.CURRENT_FORMAT){
            return new ExmlRepositoryGeometry2();
        }
                
        throw new IOException(String.format("Unsupported repository format vers '%d'. Last known format is %d",repoFormat, RepositoryVersions.CURRENT_FORMAT));
    }

}
