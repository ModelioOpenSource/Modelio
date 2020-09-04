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

package org.modelio.metamodel.impl.mmextensions.infrastructure.factory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.infrastructure.IResourceBuilder;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.IResourceHandle;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Resource;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.net.UriConnection;
import org.modelio.vbasic.net.UriConnections;
import org.modelio.vbasic.net.UriUtils;
import org.modelio.vcore.session.impl.CoreSession;

/**
 * Attached resource element builder.
 * <p>
 * Builds {@link Document} and {@link Resource} elements.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("c99fe80b-7e39-4016-8c99-b42fa27d3e5c")
public class ResourceBuilder implements IResourceBuilder {
    @objid ("18335f2d-b803-4937-b190-73cf45bde858")
    private String mimeType;

    @objid ("6f2d5cbf-43f5-411a-915c-c626a00c7e8d")
    private String name;

    @objid ("409a62c1-bb30-4ee1-8319-a87a3ae58c3c")
    private String typeModuleName;

    @objid ("f665025e-b150-448f-9a05-235e94bd2413")
    private String typeName;

    @objid ("2d64dc3d-d655-40df-a141-20bc884820ff")
    private String ownerName;

    @objid ("4714cbb0-9117-4595-bbf2-597c2a4be23c")
    private IAuthData authData;

    @objid ("1ba1361e-b289-4b42-a216-a9f3626a75c3")
    private final InfrastructureModelFactoryImpl factory;

    @objid ("9cc7064f-a0b0-4d1d-96fd-abebacf9b06a")
    private Path filePath;

    @objid ("93d79e0b-fc64-46ff-a294-8d5bc769056e")
    private ModelElement owner;

    @objid ("563400fb-746a-4431-bfe5-e4e841398e8d")
    private ResourceType type;

    @objid ("d7b299c0-4ad0-469b-ac27-7969d52d5cfb")
    private URI uri;

    @objid ("7f0c3630-3704-4ede-ae81-368aa1643805")
    public ResourceBuilder(InfrastructureModelFactoryImpl factory) {
        this.factory = factory;
    }

    @objid ("49bd14c8-d83f-4c0e-8eb4-f58a9a181cf6")
    @Override
    public Document createDocumentReference() {
        Document doc = this.factory.createElement(Document.class);
        
        initResource(doc);
        initExternalRef(doc);
        this.factory.elementInitializer.initialize(doc);
        return doc;
    }

    @objid ("c0b8f169-63c1-4597-92fe-1dd1a63a04db")
    @Override
    public Document createEmbeddedDocument() throws IOException {
        Document doc = this.factory.createElement(Document.class);
        
        initResource(doc);
        embedResource(doc);
        this.factory.elementInitializer.initialize(doc);
        return doc;
    }

    @objid ("2911631b-5045-464c-a4e6-8070efb95162")
    @Override
    public Resource createEmbeddedResource() throws IOException {
        Resource res = this.factory.createElement(Resource.class);
        initResource(res);
        embedResource(res);
        this.factory.elementInitializer.initialize(res);
        return res;
    }

    @objid ("fd87e521-e3eb-416f-88d1-9220b1c923a9")
    @Override
    public Resource createResourceReference() {
        Resource res = this.factory.createElement(Resource.class);
        initResource(res);
        initExternalRef(res);
        return res;
    }

    @objid ("0dd9e8fd-dd73-4b0d-b0a0-3a50b6f57965")
    @Override
    public IResourceBuilder withFile(Path file) {
        this.filePath = file;
        return this;
    }

    @objid ("7271e5e3-e449-4bf2-83ae-2207249ff61c")
    @Override
    public IResourceBuilder withMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    @objid ("cf4628e8-2502-406c-b4c0-73694b26747c")
    @Override
    public IResourceBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @objid ("2524655a-5a1f-40c0-808e-13746857ab95")
    @Override
    public IResourceBuilder withOwner(final ModelElement owner) {
        this.owner = owner;
        return this;
    }

    @objid ("0c59432d-f460-4a81-a597-07ae58405ca8")
    @Override
    public IResourceBuilder withRole(String moduleName, String ownerName, String typeName) throws ExtensionNotFoundException, IllegalStateException {
        this.typeModuleName = moduleName;
        this.ownerName = ownerName;
        this.typeName = typeName;
        this.type = resolveRole();
        return this;
    }

    @objid ("fb7acd57-26c9-4be5-ab60-6dabf598ffce")
    @Override
    public IResourceBuilder withRole(ResourceType type) {
        this.type = type;
        this.typeModuleName = null;
        this.typeName = null;
        return this;
    }

    @objid ("9f73dffe-d24f-45d3-9f2b-9b202410d926")
    @Override
    public IResourceBuilder withURI(URI uri) {
        this.uri = uri;
        return this;
    }

    @objid ("6585d046-1f2d-441c-a830-20794c8dec39")
    protected ResourceType resolveRole() throws ExtensionNotFoundException, IllegalStateException {
        if (this.owner == null) {
            throw new IllegalStateException("ResourceBuilder.withOwner(ModelElement) not yet called.");
        }
        
        try {
            this.type = new MModelServices(CoreSession.getSession(this.owner)).getResourceType(this.typeModuleName, this.ownerName, this.typeName, this.owner.getMClass());
            if (this.type == null) {
                throw new ExtensionNotFoundException("'" + this.typeName + "' tag type not found");
            }
        } catch (ElementNotUniqueException e) {
            IllegalArgumentException iae = new IllegalArgumentException("'" + this.typeName + "' tag type is not unique in module '" + this.typeModuleName + "'");
            iae.addSuppressed(e);
            throw iae;
        }
        return this.type;
    }

    @objid ("5b0c8353-d112-4f57-abe9-41c3d88b35f5")
    private void embedResource(AbstractResource res) throws IOException {
        Path fileName = this.filePath != null ? this.filePath.getFileName() : null;
        if (fileName != null) {
            IResourceHandle resHandle = res.createEmbeddedResource(fileName.toString());
        
            try (OutputStream os = resHandle.write();) {
                Files.copy(this.filePath, os);
            }
        } else if (this.uri != null) {
            IResourceHandle resHandle = res.createEmbeddedResource(UriUtils.getFileName(this.uri));
            try (OutputStream os = resHandle.write();
                    InputStream is = UriConnections.openInputStream(this.uri, this.authData);) {
                byte[] buf = new byte[4096];
                int read = 0;
                while ((read = is.read(buf)) > 0) {
                    os.write(buf, 0, read);
                }
            }
        } else {
            res.setStorageInfo("blob:");
        }
    }

    @objid ("28860bb9-c74c-48c7-a743-d57534097443")
    private void initExternalRef(AbstractResource res) {
        if (this.filePath != null) {
            res.createExternalResource(this.filePath.toUri().toString());
        } else if (this.uri != null) {
            res.createExternalResource(this.uri.toString());
        }
    }

    @objid ("8e363dad-9e83-4a14-abfe-5358440c513b")
    private void initResource(AbstractResource res) {
        if (this.mimeType == null || this.mimeType.isEmpty()) {
            if (this.filePath != null) {
                try {
                    this.mimeType = Files.probeContentType(this.filePath);
                } catch (IOException e) {
                    Log.warning("Unable to probe file type of '%s': %s.", this.filePath, FileUtils.getLocalizedMessage(e));
                    Log.trace(e);
                }
            } else if (this.uri != null) {
                try {
                    UriConnection c = UriConnections.createConnection(this.uri);
                    c.setAuthenticationData(this.authData);
                    this.mimeType = c.getContentType();
        
                } catch (IOException e) {
                    Log.warning("Unable to probe file type of '%s': %s.", this.uri, FileUtils.getLocalizedMessage(e));
                    Log.trace(e);
                }
            }
        }
        
        if (this.mimeType == null || this.mimeType.isEmpty()) {
            this.mimeType = "application/octet-stream";
        } else {
            // Strip parameters that might be defined with the content type, for example "text/html; charset=utf-8"
            int index = this.mimeType.indexOf(';');
            if (index > 0) {
                this.mimeType = this.mimeType.substring(0, index);
            }
        }
        
        if (this.name == null) {
            this.name = res.getMClass().getName();
        }
        
        res.setName(this.name);
        res.setMimeType(this.mimeType);
        res.setSubject(this.owner);
        res.setType(this.type);
    }

    @objid ("15ae885d-78f5-4f7d-9ee7-b4f61a192241")
    @Override
    public IResourceBuilder withAuthData(IAuthData auth) {
        this.authData = auth;
        return this;
    }

}
