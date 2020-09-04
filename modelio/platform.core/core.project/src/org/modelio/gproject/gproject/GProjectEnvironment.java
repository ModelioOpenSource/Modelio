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

package org.modelio.gproject.gproject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.vcore.model.spi.IGMetamodelExtension;

/**
 * Default implementation of {@link IGProjectEnv}
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("db52247a-2d91-40d3-b0ab-7d23d65db1a6")
public class GProjectEnvironment implements IGProjectEnv {
    @objid ("2bf1d67e-0f09-42e4-8bae-7802cf274f66")
    private IModuleRTCache modulesCache;

    @objid ("e047f088-7ca4-4dee-a5c8-6dd66f0787c0")
    private final Collection<IGMetamodelExtension> defaultMetamodelExtensions = new ArrayList<>();

    @objid ("06d8eb56-814e-496b-a234-1942aa7d42ab")
    private Path ramcCachePath;

    /**
     * @param modulesCatalog the modules catalog.
     * @return this instance to chain calls.
     */
    @objid ("8348aeb8-b582-4f3b-895c-99c7faf2eabb")
    public GProjectEnvironment setModulesCache(IModuleRTCache modulesCatalog) {
        this.modulesCache = modulesCatalog;
        return this;
    }

    /**
     * @param metamodelExtension a metamodel fragment to add.
     * @return this instance to chain calls.
     */
    @objid ("586237ab-ec84-4cae-bf79-3fcc78487f04")
    public GProjectEnvironment addMetamodelExtension(IGMetamodelExtension metamodelExtension) {
        this.defaultMetamodelExtensions.add(metamodelExtension);
        return this;
    }

    /**
     * @return the modules catalog
     */
    @objid ("b478bcd2-1d3f-4506-b98e-f24df4872d76")
    @Override
    public IModuleRTCache getModulesCache() {
        return this.modulesCache;
    }

    @objid ("2a3c2728-d8c0-4d82-b582-93f8f0e16a08")
    @Override
    public Collection<IGMetamodelExtension> getDefaultMetamodelExtensions() {
        return this.defaultMetamodelExtensions;
    }

    /**
     * @param metamodelExtensions metamodel fragments to add.
     * @return this instance to chain calls.
     */
    @objid ("059626c3-7777-43e2-b1e9-b35fa3719c31")
    public GProjectEnvironment addMetamodelExtensions(Collection<IGMetamodelExtension> metamodelExtensions) {
        this.defaultMetamodelExtensions.addAll(metamodelExtensions);
        return this;
    }

    /**
     * @param ramcCachePath the path of the ramc cache (a directory)
     * @return this instance to chain calls.
     */
    @objid ("3e02edca-2ac8-4676-b470-686a5187426a")
    public GProjectEnvironment setRamcCache(Path ramcCachePath) {
        this.ramcCachePath = ramcCachePath;
        return this;
    }

    /**
     * @return the ramc cache directory
     */
    @objid ("852a46cd-ad6e-4b79-940c-fb2927e7d6de")
    @Override
    public Path getRamcCache() {
        return this.ramcCachePath;
    }

}
