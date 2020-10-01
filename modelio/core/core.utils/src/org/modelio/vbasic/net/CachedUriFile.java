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

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;

/**
 * Allows to cache a file referenced by an URI in a local directory.
 * <p>
 * When accessing the file, the stamp on the server is compared
 * to the stamp stored in a stamp file in the same directory.
 * The file is downloaded only if the stamps are different.
 */
@objid ("fbd4449f-cccf-427b-aca9-5c4bcea5c003")
public class CachedUriFile {
    @objid ("78dc0b05-2753-4b84-955f-7f01fc881281")
    private int timeout;

    @objid ("2a24d863-5916-42d6-a334-b9a079fa08bd")
    private boolean hasChanged;

    @objid ("02d1e448-88a9-4c8e-897e-fa366a897236")
    private URI uri;

    @objid ("80b2bc6b-19f0-4e12-b142-ac9cf5c84b6b")
    private Path destFile;

    @objid ("8e480dfc-2b39-487b-b4d8-f0a6ca9d039d")
    private Path stampFile;

    @objid ("e50dff9a-891f-4f73-b061-0aa877bdaa05")
    private IAuthData authData;

    /**
     * @param uri the URI to cache.
     * @param destDirectory the destination directory.
     * @param authData authentication data, may be <code>null</code>.
     */
    @objid ("706c2052-0a4e-49da-99a6-1d681c904991")
    public CachedUriFile(URI uri, Path destDirectory, IAuthData authData) {
        this.uri = uri;
        
        String name = UriUtils.getFileName(uri);
        
        this.destFile = destDirectory.resolve(name);
        this.stampFile = destDirectory.resolve(name+".stamp");
        this.authData = authData;
    }

    /**
     * Sets a specified timeout value, in milliseconds, to be used when opening a communications link
     * to the referenced URI.
     * If the timeout expires before the connection can be established,
     * a java.net.SocketTimeoutException is raised.
     * A timeout of zero is interpreted as an infinite timeout.
     * 
     * @param timeout the time out
     */
    @objid ("4f6a159f-591d-4239-8532-8adb704a0197")
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * Get a path to the up to date file.
     * <p>
     * Download the file from the remote location if needed.
     * 
     * @return the cached file path.
     * @throws java.nio.file.FileSystemException in case of file system exception.
     * Use {@link FileUtils#getLocalizedMessage(FileSystemException)} to get an error message.
     * @throws java.io.IOException in case of other I/O errors.
     */
    @objid ("6109fe2a-b2d4-4a63-ba5d-3dd5de305fa0")
    public Path getFile() throws FileSystemException, IOException {
        this.hasChanged = false;
        
        String localStamp = readLocalStamp();
        UriConnection conn = UriConnections.createConnection(this.uri);
        conn.setConnectTimeout(this.timeout);
        conn.setAuthenticationData(this.authData);
        
        if (localStamp != null)
            conn.setIfNotStamp(localStamp);
        
        String remoteStamp = conn.getStamp();
        if (remoteStamp == null || localStamp==null || ! Objects.equals(localStamp, remoteStamp)) {
            try (InputStream is = conn.getInputStream();
                    Backup bak = new Backup()) {
                Files.copy(is, this.destFile, StandardCopyOption.REPLACE_EXISTING);
                this.hasChanged = true;
            } 
        }
        return this.destFile;
    }

    /**
     * Read the local stamp.
     * <p>
     * Returns <code>null</code> if the file has never been downloaded.
     * 
     * @return the local stamp.
     * @throws java.nio.file.FileSystemException in case of file system exception.
     * Use {@link FileUtils#getLocalizedMessage(FileSystemException)} to get an error message.
     * @throws java.io.IOException in case of other I/O errors.
     */
    @objid ("0ea06b7d-32d8-40cc-a100-2b0502fc1312")
    private String readLocalStamp() throws FileSystemException, IOException {
        if (! Files.isRegularFile(this.destFile))
            return null;
        
        try {
            return FileUtils.readWhole(this.stampFile, "UTF-8");
        } catch (NoSuchFileException e) {
            return null;
        }
    }

    /**
     * Tells whether last call to {@link #getFile()} changed the cached file.
     * 
     * @return <code>true</code> if the cached file has been modified, else <code>false</code>.
     */
    @objid ("d0a8f416-2424-4b42-88ab-0cc518f61205")
    public boolean hasChanged() {
        return this.hasChanged;
    }

    /**
     * Get the path of the last retrieved file.
     * <p>
     * This method does not access the network and the file may
     * not exist.
     * 
     * @return the path where the cached file is downloaded.
     */
    @objid ("97419864-df5f-4757-8380-5dfdd76df02c")
    public Path getCachedFileLocation() {
        return this.destFile;
    }

    /**
     * Creates a backup of the destination file and restores
     * it if the destination file has not successfully been replaced.
     */
    @objid ("fd0f3ff9-ba69-4af2-bc42-50bbc9f78ace")
    private class Backup implements Closeable {
        @objid ("c4710d4f-3d2c-4a29-b439-c787c9596601")
        private Path backFile;

        @objid ("258d7a64-c8f3-4ad9-a122-7d0b78c17fca")
        public Backup() throws IOException {
            this.backFile = getCachedFileLocation().resolveSibling(getCachedFileLocation().getFileName().toString()+".old");
            Files.move(getCachedFileLocation(), this.backFile, StandardCopyOption.REPLACE_EXISTING);
        }

        @objid ("414669e5-18e6-4551-a79c-67f21507d75b")
        @Override
        public void close() throws IOException {
            if (!hasChanged()) {
                Files.move(this.backFile, getCachedFileLocation() , StandardCopyOption.REPLACE_EXISTING);
            }
        }

    }

}
