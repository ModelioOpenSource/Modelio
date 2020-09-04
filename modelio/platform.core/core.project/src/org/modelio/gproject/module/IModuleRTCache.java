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

package org.modelio.gproject.module;

import java.io.IOException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;

@objid ("973e6347-d967-4daf-b162-07a18cc15ce4")
public interface IModuleRTCache {
    /**
     * Install a module archive (.jmdac) in the cache.
     * 
     * @param archive the archive path to install the module from.
     * @param monitor the progress monitor to use for reporting progress to the
     * user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts
     * <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @return the module handle
     * @throws java.io.IOException in case of error.
     */
    @objid ("42f3d617-f5c1-4594-b902-a9d9da810ec0")
    IModuleHandle installModuleArchive(Path archive, IModelioProgress monitor) throws IOException;

    /**
     * Get a module handle from the cache by name and version
     * 
     * @param moduleName the module to find.
     * @param moduleVersion the version of the module to find. Might be <code>null</code>
     * to indicate the latest available version.
     * @param monitor the progress monitor to use for reporting progress to the
     * user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts
     * <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @return the module handle
     * @throws java.io.IOException in case of failure
     */
    @objid ("1b45cedd-a3b8-4a89-9749-76d3c844ddeb")
    IModuleHandle findModule(String moduleName, String moduleVersion, IModelioProgress monitor) throws IOException;

}
