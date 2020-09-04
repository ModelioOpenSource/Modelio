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

package org.modelio.vstore.exml.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.UnknownServiceException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.net.UriConnection;
import org.modelio.vbasic.net.UriConnections;
import org.modelio.vbasic.net.UriUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vstore.exml.common.index.IndexOutdatedException;

/**
 * Resource provider for repositories stored on an HTTP server,
 * or any {@link URI} for which {@link UriConnections#createConnection(URI)} will work.
 */
@objid ("e13a24b9-85de-4d80-8778-818427de2546")
public class UriExmlResourceProvider extends AbstractExmlResourceProvider {
    /**
     * Repository name.
     */
    @objid ("7729e040-55f7-4a32-bdad-df0830a1ea64")
    private String name;

    @objid ("1b4b7b90-af88-49af-9496-e4f353500b74")
    private IAuthData auth;

    @objid ("7753972e-48cf-4101-b3a8-82d0b2c0c0f0")
    private Path localIndexStampPath;

    @objid ("cb0b7bc7-6676-4af7-b512-337ad67af63b")
    private URI modelUri;

    @objid ("a27683e1-e669-43c8-89c8-0a3ce49bafbd")
    private URI stampUrl;

    @objid ("5dbc6657-fa15-4de1-8f87-f9f56f7db147")
    private URI versionUri;

    @objid ("cc3e294c-2053-4cf9-a155-e2dce6e1f88c")
    private URI url;

    @objid ("9f07f476-f226-4dc1-90e3-b12bc6430bba")
    private Path localIndexDir;

    /**
     * Initialize the resource provider.
     * 
     * @param url the URL of the repository.
     * @param localDir a local directory to store the index.
     * @param auth user/password (optional)
     */
    @objid ("31fd234d-229a-49d5-ac69-0a785a9c433d")
    public UriExmlResourceProvider(URI url, Path localDir, IAuthData auth) {
        this.url = UriUtils.asDirectoryUri(url);
        this.auth = auth;
        
        this.modelUri = this.url.resolve(IExmlRepositoryGeometry.MODEL_DIRNAME+"/");
        this.stampUrl = this.url.resolve(IStampGeometry.STAMP_DIR_NAME+"/"+IStampGeometry.STAMP_FILE_NAME);
        this.versionUri = this.url.resolve(IExmlRepositoryGeometry.FORMAT_VERSION_PATH);
        
        this.localIndexDir = localDir.resolve(IExmlRepositoryGeometry.INDEX_DIRNAME);
        this.localIndexStampPath = localDir.resolve(IStampGeometry.LOCAL_INDEX_STAMP_FILE);
    }

    @objid ("8aed1788-219d-4a0e-b816-82e4a52e8766")
    @Override
    public void buildIndexes(IModelioProgress monitor) throws IOException {
        try {
            checkLocalIndex();
        } catch (IndexOutdatedException e) {
            Log.trace(e);
        
            String msg = "Retrieving '"+getName()+"' indexes from '"+this.url+"' ...";
        
            Log.trace(msg);
            monitor.subTask(msg);
        
            boolean isLocalDir = Files.isDirectory(this.localIndexDir);
            if (isLocalDir) {
                FileUtils.delete(this.localIndexDir);
            }
        
            Files.createDirectories(this.localIndexDir);
        
            // Hard coded list of all files of a JDBM index
            final String[] files = new String[]{
                    "index.dbf.0", "index.dbf.t","index.dbr.0","index.dbr.t","index.idf.0","index.idf.t",
                    "index.idr.0", "index.idr.t"
            };
        
            Files.createDirectories(this.localIndexDir);
        
            for (String f : files) {
                // Assume the indexes are stored in the repository directory.
                URI indexUrl = this.url.resolve(IExmlRepositoryGeometry.INDEX_DIRNAME+"/"+f);
                try (InputStream is = openURL(indexUrl).getInputStream()) {
                    Path target = this.localIndexDir.resolve(f);
                    Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING );
                }
            }
        
            Files.write(this.localIndexStampPath, getStamp().getBytes(StandardCharsets.UTF_8));
        }
    }

    @objid ("5d9550bb-9137-4d35-b3bd-7d1ed2a92004")
    @Override
    public void close() throws IOException {
        // nothing to do
    }

    @objid ("27d65e8f-d903-4580-98aa-058b30817474")
    @Override
    public void commit() throws IOException {
        // nothing to do
    }

    @objid ("63d296fa-809a-4be5-8699-e16f4df0f7b6")
    @Override
    public boolean exists() throws IOException {
        try (InputStream is = new UriResource(this.url, this.auth).read();) {
            return is != null;
        } catch(FileNotFoundException | NoSuchFileException  e) {
            return false;
        } catch (AccessDeniedException e) {
            return true;
        }
    }

    @objid ("c03ff62f-4459-4335-9d66-cdac805738a9")
    @Override
    public Collection<ExmlResource> getAllResources(IModelioProgress aMonitor) throws IOException {
        throw new AccessDeniedException(this.modelUri.toString(),null, "Impossible to browse a distant repository.");
    }

    @objid ("2d74ba8a-8c66-41bb-94d0-aa30029ef389")
    @Override
    public File getIndexAccessPath() {
        return this.localIndexDir.toFile();
    }

    @objid ("aed8d4e5-85c9-455e-b5e8-0bc0c4229f70")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("f463ffa0-ff1f-4a8b-8f67-4fdc4460df96")
    @Override
    public ExmlResource getRelativePathResource(String relativePath) {
        return new UriResource(this.url.resolve(relativePath), this.auth);
    }

    @objid ("7142952a-3664-47b1-b49a-44655806167a")
    @Override
    public String getStamp() throws IOException {
        try (InputStream is = UriConnections.openInputStream(this.stampUrl,this.auth)){
            return FileUtils.readWhole(is, "UTF-8");
        } catch (FileNotFoundException | NoSuchFileException e) {
            return "";
        }
    }

    @objid ("a5c90c28-c723-4f4e-a506-8ae4246249a3")
    @Override
    public URI getURI() {
        return this.url;
    }

    @objid ("e1bed58b-db17-48d3-8aa8-5d1d5c2b32aa")
    @Override
    public boolean isBrowsable() {
        return false;
    }

    @objid ("b131b6f2-adf1-4f95-85f9-3c57424f01ae")
    @Override
    public boolean isWriteable() {
        return false;
    }

    /**
     * Set the repository name.
     * <p>
     * This name will be used in error messages to tell the user the error location.
     * 
     * @param name the repository name.
     */
    @objid ("044a315e-5be8-466d-92e9-996b2d3b42aa")
    public void setName(String name) {
        this.name = name;
    }

    @objid ("aee71aa1-f89f-42ed-b0b4-19b1c73ce055")
    @Override
    public void writeStamp() throws IOException {
        throw new AccessDeniedException(this.stampUrl.toString());
    }

    @objid ("ed737d83-8073-477b-b9a0-b0b186f98df7")
    @Override
    protected void doCreateRepository(MMetamodel metamodel) throws IOException {
        throw new AccessDeniedException(this.url.toString());
    }

    @objid ("2fae883f-10f4-4baa-a1e9-4876bdf3bdc1")
    private void checkLocalIndex() throws IOException, IndexOutdatedException {
        boolean islocalDir = Files.isDirectory(this.localIndexDir);
        if (! islocalDir) {
            throw new IndexOutdatedException(getName()+" indexes not yet copied in '"+this.localIndexDir+"'.");
        }
        
        final String remoteStamp = getStamp();
        //Log.trace("Checking '"+this.getName()+"' repo indexes, remote stamp="+remoteStamp+" ("+this.stampUrl+")");
        
        try {
            String localStamp = readLocalStamp();
            //Log.trace("Checking '"+this.getName()+"' repo indexes, local stamp ="+localStamp+" ("+this.localIndexStampPath+")");
            if (! localStamp.equals(remoteStamp)) {
                throw new IndexOutdatedException(getName()+" local index stamp outdated:\n"+
                        " - local index stamp: "+localStamp+"\n"+
                        " - remote repository stamp: "+remoteStamp);
            }
        } catch (java.nio.file.NoSuchFileException e) {
            throw new IndexOutdatedException("No '"+this.localIndexStampPath+"' stamp file yet.");
        } catch (IOException e) {
            throw new IndexOutdatedException("Failed reading '"+this.localIndexStampPath+"': "+FileUtils.getLocalizedMessage(e), e);
        }
    }

    @objid ("d1c951d9-400a-432b-be5d-16b72e4bd7ed")
    private UriConnection openURL(URI anUrl) throws IOException {
        UriConnection connection = UriConnections.createConnection(anUrl);
        
        if (this.auth != null) {
            connection.setAuthenticationData(this.auth);
        }
        return connection;
    }

    @objid ("4d53035d-1184-4ef3-83be-06eee47e61a9")
    private String readLocalStamp() throws FileSystemException, IOException {
        return FileUtils.readWhole(this.localIndexStampPath, "UTF-8");
    }

    /**
     * URL based resource.
     */
    @objid ("eafe4cac-4234-414f-bfe9-10793b829687")
    static class UriResource implements ExmlResource {
        @objid ("e4b2714a-14e5-4ba9-a0f2-138df96fd301")
        private URI url;

        @objid ("7abe393d-7269-4050-aa89-fc5beb06920d")
        private IAuthData auth;

        @objid ("6135b20a-047a-4909-84e5-1a94c2064d09")
        public UriResource(URI url, IAuthData auth) {
            this.url = url;
            this.auth = auth;
        }

        @objid ("13562a62-0b81-48a9-af5b-e1bf9ef20f54")
        @Override
        public InputStream read() throws IOException {
            try {
                return openURL().getInputStream();
            } catch (FileNotFoundException e) {
                Log.warning(e.toString());
                return null;
            }
        }

        /**
         * Returns an output stream that writes to this resource.
         * 
         * @return an output stream that writes to this resource.
         * @exception  IOException              if an I/O error occurs while
         * creating the output stream.
         * @exception  UnknownServiceException  if the protocol does not support
         * output.
         */
        @objid ("49c58c42-c2af-4bc1-b5f3-6f4b587c39d0")
        @Override
        public OutputStream write() throws IOException {
            UriConnection conn = openURL();
            conn.setDoOutput(true);
            return conn.getOutputStream();
        }

        @objid ("918656d0-2922-4cb5-8f5f-91b00a602948")
        @Override
        public void delete() throws IOException {
            throw new AccessDeniedException(this.url.toString());
        }

        @objid ("8146a291-35d7-4cf8-9d3f-033c5183fa18")
        @Override
        public String getPublicLocation() {
            return this.url.toString();
        }

        @objid ("a65d8448-5825-48e1-8c18-50c1396a6925")
        private UriConnection openURL() throws IOException {
            UriConnection connection = UriConnections.createConnection(this.url);
            if (this.auth != null) {
                connection.setAuthenticationData(this.auth);
            }
            return connection;
        }

    }

}
