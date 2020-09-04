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

package org.modelio.gproject.module;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Modules store definition.
 * <p>
 * Manage a module store.
 * Main operations consists in adding, removing and searching modules.
 * </p>
 */
@objid ("655a0ce2-0d3f-4ff2-a0cd-5d8045cf982b")
public interface IModuleStore {
    /**
     * Install a module archive (.jmdac) in the store.
     * @param archive the archive path to install the module from.
     * @param monitor the progress monitor to use for reporting progress to the
     * user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts
     * <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @return the module handle
     * @throws java.io.IOException in case of error.
     */
    @objid ("67f57a4f-46c1-4153-a0d3-1e9dbcc748a6")
    IModuleHandle installModuleArchive(Path archive, IModelioProgress monitor) throws IOException;

    /**
     * Remove the module from the store.
     * @param mh the module to remove.
     * @throws java.io.IOException in case of failure.
     * @throws java.nio.file.FileSystemException in case of file system error. Use
     * {@link FileUtils#getLocalizedMessage(FileSystemException)} to
     * get a human readable error message.
     */
    @objid ("edaea3b8-ce50-40bb-87d5-226aded5972a")
    void removeModule(IModuleHandle mh) throws FileSystemException, IOException;

    /**
     * Get all module handles available in the store.
     * @param monitor the progress monitor to use for reporting progress to the
     * user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts
     * <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @return a list of module handles
     * @throws java.io.IOException in case of failure
     * @throws java.nio.file.FileSystemException in case of file system error. Use
     * {@link FileUtils#getLocalizedMessage(FileSystemException)} to
     * get a human readable error message.
     */
    @objid ("8d0480fa-440f-4343-b15f-7c267184a4ae")
    List<IModuleHandle> findAllModules(IModelioProgress monitor) throws FileSystemException, IOException;

    /**
     * Get a module handle from the store given a module archive.
     * The archive is looked up from module  name and version. The name and version are then used to search for the mdoule in the store.
     * WARNING: this method DOES NOT install the archive in the store.
     * @param archivePath the archive path to find the module from.
     * @param monitor the progress monitor to use for reporting progress to the
     * user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts
     * <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @return the module handle attached to the stored for the same module (name+version) as the module archive. Null otherwise.
     * @throws java.io.IOException in case of failure
     * @throws java.nio.file.FileSystemException in case of file system error. Use
     * {@link FileUtils#getLocalizedMessage(FileSystemException)} to
     * get a human readable error message.
     */
    @objid ("b668239b-28d1-402d-9e6e-03081dd94b76")
    IModuleHandle findModule(Path archivePath, IModelioProgress monitor) throws FileSystemException, IOException;

    /**
     * Get a module handle from the store by name and version
     * @param moduleName the module to find.
     * @param moduleVersion the version of the module to find. Might be <code>null</code>
     * to indicate the latest available version.
     * @param monitor the progress monitor to use for reporting progress to the
     * user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts
     * <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @return the module handle, null if the module is not available in the store
     * @throws java.nio.file.FileSystemException in case of file system error. Use
     * {@link FileUtils#getLocalizedMessage(FileSystemException)} to
     * get a human readable error message.
     * @throws java.io.IOException in case of failure
     */
    @objid ("fda22b5b-5a45-4d73-8d68-839a0efadca9")
    IModuleHandle findModule(String moduleName, String moduleVersion, IModelioProgress monitor) throws FileSystemException, IOException;

    /**
     * Get all available versions of a module from the cache by name.
     * @param moduleName the module to find.
     * @param monitor the progress monitor to use for reporting progress to the
     * user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts
     * <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @return all module handles for the module.
     * @throws java.nio.file.FileSystemException in case of file system error. Use
     * {@link FileUtils#getLocalizedMessage(FileSystemException)} to
     * get a human readable error message.
     * @throws java.io.IOException in case of failure
     */
    @objid ("d3548e16-28cd-4014-9f89-3ebefb142706")
    List<IModuleHandle> findModule(String moduleName, IModelioProgress monitor) throws FileSystemException, IOException;

}
