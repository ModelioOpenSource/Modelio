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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.IResourceHandle;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.net.UriConnection;
import org.modelio.vbasic.net.UriConnections;
import org.modelio.vbasic.net.UriUtils;

/**
 * Access to an {@link AbstractResource} content.
 * @author cma
 * @since 3.7
 */
@objid ("27b154b8-de7d-4941-979e-1df15730ade8")
public class UriResourceHandle implements IResourceHandle {
    @objid ("391e4e3a-081a-4a0a-9476-5443ad53a66b")
    private final URI uri;

    @objid ("8f4248d2-277c-41bd-acf5-2a6e69b47723")
    private IAuthData auth;

    @objid ("49750218-10c7-4173-bfc2-390adeddb59f")
    public UriResourceHandle(AbstractResource modelEl) {
        String loc = modelEl.getStorageInfo();
        
        this.uri = URI.create(loc);
    }

    @objid ("df7f29ff-975d-4bd5-9c62-97118b3caeaa")
    @Override
    public InputStream read() throws IOException {
        return setupConnection().getInputStream();
    }

    @objid ("7e2bbbd4-c7f0-4991-b0a2-6392b82ba16d")
    @Override
    public OutputStream write() throws IOException {
        return setupConnection().getOutputStream();
    }

    /**
     * Set the authentication data that may be needed to access the resource.
     * @param auth the authentication data.
     */
    @objid ("0d03d524-6f8f-44ee-9066-be5eaa13eb58")
    @Override
    public void setAuthenticationData(IAuthData auth) {
        this.auth = auth;
    }

    @objid ("d5703f32-1995-4908-aea2-c67f0720ce90")
    private UriConnection setupConnection() throws IOException, MalformedURLException {
        UriConnection connection = UriConnections.createConnection(this.uri);
        connection.setAuthenticationData(this.auth);
        return connection;
    }

    @objid ("4c713d7e-e552-4839-b23f-76e547271a3e")
    @Override
    public Path extractInto(Path dir) throws IOException {
        String scheme = this.uri.getScheme();
        
        if (scheme.isEmpty() || scheme.equals("file")) {
            Path loc = Paths.get(this.uri);
        
            Path extractedFile = dir.resolve(loc.getFileName());
            Files.copy(loc, extractedFile, StandardCopyOption.REPLACE_EXISTING);
            return extractedFile;
        }
        
        Path extractedFile = dir.resolve(UriUtils.getFileName(this.uri));
        Files.copy(read(), extractedFile, StandardCopyOption.REPLACE_EXISTING);
        return extractedFile;
    }

    @objid ("91fa52c2-1130-443e-a83f-b2325dc3b8e2")
    @Override
    public URI getLocation() {
        return this.uri;
    }

}
