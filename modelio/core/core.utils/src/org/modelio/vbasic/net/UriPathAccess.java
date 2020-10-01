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

package org.modelio.vbasic.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;

/**
 * Allows to access to an URI as a {@link Path}.
 * <p>
 * If the URI is not directly convertible to a path,
 * a connection is made to copy the content to a temporary file.
 * This temporary file is deleted when {@link #close()} is called.
 */
@objid ("f6d8c282-9e55-4fa2-8a4e-5a7d0d7ccd31")
public class UriPathAccess implements AutoCloseable {
    @objid ("aa086ef5-da78-44c3-bfaf-8223f2058711")
    private URI uri;

    @objid ("52c22510-9bad-45e2-b273-7640af644164")
    private Path path;

    @objid ("41acfc84-c687-4184-b417-2d8bfe2bc595")
    private Path localPath;

    @objid ("3dee8dde-da18-4a26-a8c6-6103d00154de")
    private IAuthData authData;

    /**
     * Constructor
     * 
     * @param uri the URI to access a a Path.
     * @deprecated Please use {@link #UriPathAccess(URI, IAuthData)} with an authentication data or <code>null</code>
     * if none is needed.
     */
    @objid ("1c5f59d7-d5ca-4c53-8504-f5eb35950306")
    @Deprecated
    public UriPathAccess(URI uri) {
        this.uri = uri;
    }

    /**
     * Constructor
     * 
     * @param uri the URI to access a a Path.
     * @param auth authentication data, may be <code>null</code>.
     */
    @objid ("16ad0406-1982-4623-a694-0796f242b97b")
    public UriPathAccess(URI uri, IAuthData auth) {
        this.uri = uri;
        this.authData = auth;
    }

    /**
     * Convert the URI to a {@link Path}.
     * 
     * @return the converted path.
     * @throws java.io.IOException in case of I/O failure.
     * @throws java.nio.file.FileSystemException in case of file system failure
     */
    @objid ("6f37298c-2604-40e0-ac5d-74c915898b42")
    public Path getPath() throws IOException, FileSystemException {
        if (this.path == null) 
            lookup();
        return this.path;
    }

    /**
     * Set the authentication data.
     * 
     * @param auth authentication data, may be <code>null</code>.
     */
    @objid ("fde22ca8-57f1-4f57-adb4-8788735d8951")
    public void setAuthentication(IAuthData auth) {
        this.authData = auth;
    }

    /**
     * Delete the temporary file if one was created.
     * Deletion errors are silently ignored.
     */
    @objid ("5a2a70c3-0330-48d2-a6db-b55c19ad6156")
    @Override
    public void close() {
        if (this.localPath != null) try {
            FileUtils.delete(this.localPath);
        } catch (IOException e) {
            // ignore
            //Log.trace(e);
        }
    }

    @objid ("4fb461c0-e355-401b-9bf1-e18b5aec58c7")
    private void lookup() throws IOException, FileSystemException {
        try {
            this.path = Paths.get(this.uri);
        } catch (FileSystemNotFoundException | IllegalArgumentException e) {
            //Log.trace(uri.toString()+": "+e.toString());
            // No file system for this URI, copy the RAMC to a temporary file
            try (InputStream is = UriConnections.openInputStream(this.uri, this.authData)) {
                this.localPath = Files.createTempFile("temp","");
                Files.copy(is, this.localPath, StandardCopyOption.REPLACE_EXISTING );
                this.path = this.localPath;
            }
        }
    }

}
