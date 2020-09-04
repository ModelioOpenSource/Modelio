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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vstore.exml.common.index.IndexOutdatedException;

/**
 * Same as {@link LocalExmlResourceProvider} but assume the given {@link Path}
 * is on another file system and the indexes are stored in the same directory.
 * <p>
 * The index files are copied from the distant file system to a temporary local directory.
 */
@objid ("9814becb-0326-11e2-b5bf-001ec947ccaf")
public class FsExmlResourceProvider extends LocalExmlResourceProvider {
    @objid ("cf2a52ad-03e4-11e2-b5bf-001ec947ccaf")
    private Path localIndexPath;

    /**
     * Path of the stamp for the local index copy.
     * <p>
     * The content of this file is compared with the stamp of the repository.
     */
    @objid ("10024e4e-2374-44ce-b07c-4069dd5d0e6c")
    private Path localIndexStampPath;

    /**
     * Initialize the resource provider.
     * 
     * @param distantPath the location of the repository
     * @param runtimePath a directory in the local file system.
     * If <code>null</code> a temporary directory will be created in $(TEMP).
     * @param name the provider name, used in messages to identify the repository.
     * @throws java.lang.IllegalArgumentException if the path is not valid.
     * @throws java.io.IOException in case of failure
     */
    @objid ("cf2a52ae-03e4-11e2-b5bf-001ec947ccaf")
    public FsExmlResourceProvider(Path distantPath, Path runtimePath, String name) throws IOException, IllegalArgumentException {
        super(distantPath, runtimePath, name);
        
        if (runtimePath == null) {
            this.localIndexPath = Files.createTempDirectory(distantPath.getFileName().toString());
            this.localIndexStampPath = this.localIndexPath.resolve(IStampGeometry.LOCAL_INDEX_STAMP_FILE);
        } else {
            validateLocalPath(runtimePath);
            this.localIndexPath = runtimePath.resolve(".index");
            this.localIndexStampPath = runtimePath.resolve(IStampGeometry.LOCAL_INDEX_STAMP_FILE);
        }
    }

    @objid ("cf2a52b7-03e4-11e2-b5bf-001ec947ccaf")
    @Override
    public void buildIndexes(IModelioProgress monitor) throws IOException {
        // Not a standard file system, copy all into a directory
        // accessible to JDBM.
        // The copy is made only if needed.
        
        try {
            checkLocalIndex();
        } catch (IndexOutdatedException e) {
            Log.trace(e);
        
            String msg = "Retrieving '"+getName()+"' indexes from '"+this.repositoryPath+"' ...";
        
            Log.trace(msg);
            monitor.subTask(msg);
        
            boolean islocalDir = Files.isDirectory(this.localIndexPath);
        
            if (islocalDir) {
                FileUtils.delete(this.localIndexPath);
            }
        
            Files.createDirectories(this.localIndexPath);
        
            // Assume the indexes are stored in the same directory.
            Path remoteIndexPath = this.repositoryPath.resolve(".index");
            if (Files.isDirectory(remoteIndexPath)) {
                FileUtils.copyDirectoryTo(remoteIndexPath, this.localIndexPath);
                Files.write(this.localIndexStampPath, getStamp().getBytes(StandardCharsets.UTF_8));
            }
        
        }
    }

    @objid ("5a0e8afa-e6d0-4281-8893-277986a48704")
    @Override
    public File getIndexAccessPath() {
        return this.localIndexPath.toFile();
    }

    @objid ("5a426f9b-0724-11e2-9eb7-001ec947ccaf")
    @Override
    public boolean isWriteable() {
        return false;
    }

    @objid ("807f81d1-f741-4d24-a78c-2a343d584b54")
    private void checkLocalIndex() throws IndexOutdatedException {
        boolean islocalDir = Files.isDirectory(this.localIndexPath);
        if (! islocalDir) {
            throw new IndexOutdatedException(getName()+" indexes not yet copied in '"+this.localIndexPath+"'.");
        }
        
        try {
            String localStamp = readLocalStamp();
            if (! localStamp.equals(getStamp())) {
                throw new IndexOutdatedException(getName()+" local index stamp outdated:\n"+
                        " - local index stamp: "+localStamp+"\n"+
                        " - remote repository stamp: "+getStamp());
            }
        } catch (java.nio.file.NoSuchFileException e) {
            throw new IndexOutdatedException("No '"+this.localIndexStampPath+"' stamp file yet.");
        } catch (IOException e) {
            throw new IndexOutdatedException("Failed reading '"+this.localIndexStampPath+"': "+e.toString(), e);
        }
    }

    @objid ("2e9294ab-a6d6-4827-9f23-c62c514d6e9c")
    private String readLocalStamp() throws FileSystemException, IOException {
        try {
            return FileUtils.readWhole(this.localIndexStampPath, "UTF-8");
        } catch (NoSuchFileException e) {
            Path old = this.localIndexStampPath.getParent().resolve("index_stamp.conf");
            try {
                return FileUtils.readWhole(old, "UTF-8");
            } catch (IOException e2) {
                e.addSuppressed(e2);
                throw e;
            }
        }
    }

    /**
     * Check the given path is a local directory.
     * 
     * @param localWorkPath a path
     * @throws java.lang.IllegalArgumentException if the path is not valid.
     */
    @objid ("cf2a52b3-03e4-11e2-b5bf-001ec947ccaf")
    private static void validateLocalPath(Path localWorkPath) throws IllegalArgumentException {
        if (localWorkPath.getFileSystem() != FileSystems.getDefault() ) {
            throw new IllegalArgumentException(localWorkPath+" is not on the local file system.");
        }
        if (! Files.isDirectory(localWorkPath)) {
            throw new IllegalArgumentException(localWorkPath+" is not a directory.");
        }
    }

}
