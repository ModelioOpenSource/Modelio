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

package org.modelio.gproject.module;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;

/**
 * Stub class for modules for which no archive is available.
 */
@objid ("c92dcb89-2f2b-11e2-8f16-002564c97630")
public class EmptyModuleHandle implements IModuleHandle {
    @objid ("cbf7e485-2f2b-11e2-8f16-002564c97630")
    private String moduleName;

    @objid ("cbf0c070-2f2b-11e2-8f16-002564c97630")
    private Version moduleVersion;

    /**
     * C'tor
     * 
     * @param moduleName the module name
     * @param moduleVersion the module version
     */
    @objid ("cbf0c071-2f2b-11e2-8f16-002564c97630")
    public EmptyModuleHandle(String moduleName, Version moduleVersion) {
        this.moduleName = moduleName;
        this.moduleVersion = moduleVersion;
    }

    @objid ("cbf0c07a-2f2b-11e2-8f16-002564c97630")
    @Override
    public List<VersionedItem<?>> getWeakDependencies() {
        return Collections.emptyList();
    }

    @objid ("cbf0c081-2f2b-11e2-8f16-002564c97630")
    @Override
    public Version getVersion() {
        return this.moduleVersion;
    }

    @objid ("cbf0c086-2f2b-11e2-8f16-002564c97630")
    @Override
    public String getUid() {
        return "";
    }

    @objid ("cbf0c08b-2f2b-11e2-8f16-002564c97630")
    @Override
    public Path getResourcePath() {
        return null;
    }

    @objid ("cbf0c090-2f2b-11e2-8f16-002564c97630")
    @Override
    public String getName() {
        return this.moduleName;
    }

    @objid ("cbf0c095-2f2b-11e2-8f16-002564c97630")
    @Override
    public Path getModuleInfosPath() {
        return null;
    }

    @objid ("cbf0c09a-2f2b-11e2-8f16-002564c97630")
    @Override
    public Path getModelComponentPath() {
        return null;
    }

    @objid ("cbf0c09f-2f2b-11e2-8f16-002564c97630")
    @Override
    public String getMainClassName() {
        return "";
    }

    @objid ("cbf0c0a4-2f2b-11e2-8f16-002564c97630")
    @Override
    public List<Path> getJarPaths() {
        return Collections.emptyList();
    }

    @objid ("cbf321d0-2f2b-11e2-8f16-002564c97630")
    @Override
    public Path getDynamicModelPath() {
        return null;
    }

    @objid ("cbf321d5-2f2b-11e2-8f16-002564c97630")
    @Override
    public List<Path> getDocPaths() {
        return Collections.emptyList();
    }

    @objid ("cbf321dc-2f2b-11e2-8f16-002564c97630")
    @Override
    public List<VersionedItem<?>> getDependencies() {
        return Collections.emptyList();
    }

    @objid ("cbf321e3-2f2b-11e2-8f16-002564c97630")
    @Override
    public Version getBinaryVersion() {
        return new Version (0,0,0);
    }

    @objid ("d960c4d6-37da-11e2-8ba4-002564c97630")
    @Override
    public Path getArchive() {
        return null;
    }

    @objid ("78caabf0-4d73-4817-b120-84a705788825")
    @Override
    public Map<String, Path> getStylePaths() {
        return Collections.emptyMap();
    }

    @objid ("8c2204a5-5b4c-4836-af95-97a2b29a960a")
    @Override
    public List<IMetamodelFragmentHandle> getMetamodelFragments() {
        return Collections.emptyList();
    }

}
