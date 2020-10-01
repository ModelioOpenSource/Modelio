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

package org.modelio.gproject.module.rtcache;

import java.io.IOException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Default module catalog that caches no module and access directly .jmdac
 * archives.
 */
@objid ("0ca0982e-b756-42e3-8afd-1ae2f0436031")
public class EmptyModuleCache implements IModuleRTCache {
    @objid ("45f87154-5f4a-4962-b0ab-d25d937c7b02")
    private static final EmptyModuleCache INSTANCE = new EmptyModuleCache();

    /**
     * @return the catalog.
     */
    @objid ("b056f4e7-b0e4-47cd-a97e-10945eb7443f")
    public static IModuleRTCache getInstance() {
        return EmptyModuleCache.INSTANCE;
    }

    @objid ("c23ab254-0576-4894-963b-e7278367c11f")
    private EmptyModuleCache() {
        // Nothing
    }

    @objid ("77a69f72-3416-42a1-b295-c2d2ee832067")
    @Override
    public IModuleHandle findModule(String moduleName, String moduleVersion, IModelioProgress monitor) {
        // No modules are available...
        return null;
    }

    @objid ("59eff3ee-0f3f-457b-9e61-4610273022e6")
    @Override
    public IModuleHandle installModuleArchive(Path archive, IModelioProgress monitor) throws IOException {
        return null;
    }

}
