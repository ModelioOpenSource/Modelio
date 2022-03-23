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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.IResourceHandle;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vcore.session.api.blob.BlobInfo;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Access to an {@link AbstractResource} content.
 * @author cma
 * @since 3.7
 */
@objid ("9f3be3c4-4d2b-4c5d-b830-3c160e7ed8cd")
public class BlobResourceHandle implements IResourceHandle {
    @objid ("c9fa395b-7f24-4e10-bde7-aebb7eeef151")
    private final String fileName;

    /**
     * Expected prefix of the {@link AbstractResource#getStorageInfo()} attribute.
     */
    @objid ("1f2c062b-85fc-410b-b90c-3979495e66a2")
    public static final String STORAGE_INFO_PREFIX = "blob:";

    @objid ("7032670d-73bb-4a77-a01f-3a3895bb71fa")
    private final IRepository repository;

    @objid ("f7f1fe79-3ebb-49a8-88de-5ef0bf467fc3")
    private final BlobInfo blobInfo;

    @objid ("16f78c91-dfe7-4649-9e88-10c42a5be6c6")
    private AbstractResource modelEl;

    /**
     * Expected format of the {@link AbstractResource#getStorageInfo()} attribute.
     */
    @objid ("74ac7a0b-3f52-493b-89d8-45947bdbfac1")
    private static final Pattern STORAGE_INFO_PATTERN = Pattern.compile("blob:(.*)");

    @objid ("aef6d486-e852-485b-929f-9357f8ab268e")
    public  BlobResourceHandle(AbstractResource modelEl) {
        this.modelEl = modelEl;
        String info = modelEl.getStorageInfo();
        Matcher matcher = STORAGE_INFO_PATTERN.matcher(info);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("%s is not an embedded resource, storage='%s'.", modelEl, info));
        }
        
        // Remove special chars that are forbidden in a file name
        this.fileName = matcher.group(1).replaceAll("[\\/:\\*\\?\"<>`|]", "");
        this.repository = CoreSession.getSession(modelEl).getRepository(modelEl);
        this.blobInfo = new BlobInfo(new MRef(modelEl), IResourceHandle.BLOB_LOCAL_KEY);
        
    }

    @objid ("6f412bd0-73ad-4b94-9e38-7865c2a3bda5")
    @Override
    public InputStream read() throws IOException {
        return this.repository.readBlob(this.blobInfo.getKey());
    }

    @objid ("50c404bb-3c31-4dfd-a40f-ba60ae478e52")
    @Override
    public OutputStream write() throws IOException {
        return this.repository.writeBlob(this.blobInfo);
    }

    @objid ("798b95b9-25fa-4f56-977d-9bd1c9eb5bc0")
    @Override
    public Path extractInto(Path dir) throws IOException {
        Path filePath = dir.resolve(this.fileName);
        try (InputStream is = read();) {
            Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        return filePath;
    }

    @objid ("99a9615f-8233-44a1-9c7a-0f392c63266c")
    @Override
    public void setAuthenticationData(IAuthData auth) {
        // ignored
    }

    /**
     * Delete the attached resource.
     * @throws IOException on I/O failure.
     */
    @objid ("2dd0f4e6-1a39-4ba4-ba2d-f2d0c749f4cc")
    public void delete() throws IOException {
        String oldStorage = this.modelEl.getStorageInfo();
        boolean ok = false;
        
        this.modelEl.setStorageInfo("");
        try {
            this.repository.removeBlob(this.blobInfo.getKey());
        } finally {
            if (! ok) {
                this.modelEl.setStorageInfo(oldStorage);
            }
        }
        
    }

    @objid ("2158a535-5c48-44d1-96cd-5c0732410015")
    @Override
    public URI getLocation() {
        return null;
    }

}
