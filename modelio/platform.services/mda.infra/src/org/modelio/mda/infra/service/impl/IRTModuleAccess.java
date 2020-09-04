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

package org.modelio.mda.infra.service.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramCustomizer;
import org.modelio.api.modelio.diagram.tools.IDiagramTool;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.ActionLocation;
import org.modelio.api.module.command.IModuleAction;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.module.GModule;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

/**
 * Privileged access to IRTModule.
 */
@objid ("4024033e-505d-471f-a272-865c3163a262")
public interface IRTModuleAccess extends IRTModule {
    /**
     * Reset the dynamic model.
     */
    @objid ("8c006061-bbe2-488e-a1a3-76b5dc0f5139")
    void resetDynamicModel();

    /**
     * Register a module action for the contextual popupmenu(s) of the application.
     * @param location The action insertion point in the popupmenu (see {@link ActionLocation})
     * @param action Action to store
     */
    @objid ("a7472ba1-61dd-463f-8909-8ee3ee5c978e")
    void registerAction(ActionLocation location, IModuleAction action);

    /**
     * Register a new palette Tool.<br>
     * @see IDiagramTool
     * @param id the tool id
     * @param diagramCommand The tool behavior.
     * @since 3.3.0
     */
    @objid ("61a585f6-5ee9-40b3-9d5f-318d1845f294")
    void registerCustomizedTool(String id, IDiagramTool diagramCommand);

    /**
     * Register a customizer for a stereotyped diagram.
     * @param stereotype the diagram stereotype which the customizer is intended for
     * @param baseDiagramClass the base diagram editor to customize
     * @param customizer the customizer implementation
     * @since 2.2.1
     */
    @objid ("0c320ce4-f485-438f-8d0d-e6071462dce5")
    void registerDiagramCustomization(Stereotype stereotype, Class<? extends AbstractDiagram> baseDiagramClass, IDiagramCustomizer customizer);

    @objid ("98168cc2-93cc-45a4-9d1c-7fdcc50d5ac5")
    void setState(ModuleRuntimeState newState);

    /**
     * Internal use only: set the IModule implementation.
     * @internal
     * @param imodule the module implementation
     */
    @objid ("a4d820d8-888c-429e-af50-aa37c501e6c7")
    void setIModule(IModule imodule);

    /**
     * Internal use only: set the GModule.
     * @internal
     * @param gModule the GModule.
     */
    @objid ("76301f15-b04d-4c40-a05c-fe110df8d03c")
    void setGModule(GModule gModule);

    /**
     * internal use only: set the module class loader.
     * @internal
     * @param aClassLoader the module class loader.
     */
    @objid ("55e18f4d-41ff-47c1-bb2c-03bf0f33584c")
    void setClassLoader(ClassLoader aClassLoader);

    /**
     * Internal use only: set the cause why the module is (still) in this state.
     * @internal
     * @param moduleException
     */
    @objid ("78675b74-5c08-4231-a416-9316ec47bc35")
    void setDownError(ModuleException moduleException);

}
