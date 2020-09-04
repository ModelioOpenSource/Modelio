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
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.vcore.model.spi.IGMetamodelExtension;

/**
 * Configuration of the {@link GProjectFactory}.
 * <p>
 * This configuration is used to load projects and is given to created projects.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("69607556-01e8-413f-a069-b95cd88a109b")
public interface IGProjectEnv {
    /**
     * @return the modules cache
     */
    @objid ("19143e95-648a-4ba5-bb02-1744b3bd90a3")
    IModuleRTCache getModulesCache();

    /**
     * Get the metamodel fragments to use with the project.
     * @return the metamodel fragments.
     */
    @objid ("2bfd352d-fc79-4a99-abb4-e3d60cd2aff8")
    Collection<IGMetamodelExtension> getDefaultMetamodelExtensions();

    @objid ("929fe88f-c143-43ab-a339-f6d0744e7d93")
    Path getRamcCache();

}
