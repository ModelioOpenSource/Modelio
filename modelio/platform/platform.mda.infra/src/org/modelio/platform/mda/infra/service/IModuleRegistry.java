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
package org.modelio.platform.mda.infra.service;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.ModuleId;
import org.modelio.gproject.parts.module.GModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.vbasic.version.VersionedItem;

/**
 * This interface:
 * <ul>
 * <li>gives access to loaded and started modules.</li>
 * <li>provides the API that maps an {@link ModuleComponent} to a loaded
 * {@link IRTModule}.</li>
 * </ul>
 * Any module installed in a project is loaded when the project starts and is
 * added to the loaded modules list. When an module is started it is added in
 * the started modules list.
 */
@objid ("c1468600-edc2-11e1-88ee-001ec947c8cc")
public interface IModuleRegistry {
    /**
     * Get the started {@link IRTModule} corresponding to the given
     * {@link ModuleComponent}.
     * @param model the module model.
     * @return the matching started module or <i>null</i> if no started module
     * matches the <i>IRTModule</i>
     */
    @objid ("1e6375e4-edc3-11e1-88ee-001ec947c8cc")
    IRTModule getStartedModule(GModule model);

    /**
     * Get the {@link IRTModule} corresponding to the given
     * {@link GModule}.
     * @param model the module model.
     * @return the matching <code>IRTModule</code> or <i>null</i> if no module
     * matches the <i>GModule</i>
     */
    @objid ("1e6375f1-edc3-11e1-88ee-001ec947c8cc")
    IRTModule getModule(GModule model);

    /**
     * Get the {@link IRTModule} which name correspond to the given
     * {@link ModuleId} name and which version is newer or equal to the given
     * version.
     * @param moduleId the Id of the searched module.
     * @return the matching module or <code>null</code> if no module
     * matches the ModuleId.
     */
    @objid ("bc4337ab-f37d-11e1-9458-001ec947c8cc")
    IRTModule getModule(VersionedItem<?> moduleId);

    /**
     * Get the registered modules.
     * @return the registered modules.
     */
    @objid ("943a41cf-9a7a-406a-ae6a-f11d8c1eaa52")
    Collection<IRTModule> getModules();

    /**
     * Get the started {@link IRTModule} which name correspond to the given
     * {@link ModuleId} name and which version is newer or equal to the given
     * version.
     * @param moduleId the Id of the searched module.
     * @return the matching started module or <code>null</code> if no started module
     * matches the ModuleId.
     */
    @objid ("bc4337af-f37d-11e1-9458-001ec947c8cc")
    IRTModule getStartedModule(VersionedItem<?> moduleId);

    /**
     * Get the started modules.
     * @return The started modules.
     */
    @objid ("1e6375df-edc3-11e1-88ee-001ec947c8cc")
    List<IRTModule> getStartedModules();
}

