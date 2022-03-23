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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DefinitionScope;

/**
 * Flag persisted in the module parameters
 * to ask Modelio to make a special action on a module
 * when opening the project.
 * 
 * @since Modelio 3.4
 */
@objid ("76a25ec7-fd8a-4876-b255-d5066f98d99e")
enum ModuleStartAction {
    /**
     * Ask Modelio to deploy this module on project open.
     */
    @objid ("204cfb8d-0e89-4ad4-b743-ec74da37ae47")
    INSTALL,
    /**
     * Ask Modelio to update this module on project open.
     */
    @objid ("c985a2fe-51f9-4674-92ed-12cb4f1c5663")
    UPDATE,
    /**
     * Ask Modelio to remove this module on project open.
     */
    @objid ("b0158cc1-f96c-4340-a3f8-9ee628f1a8e8")
    REMOVE,
    /**
     * Ask Modelio to start normally this module on project open.
     */
    @objid ("c0ead66b-8ec1-4447-aa30-e82b8fca88c4")
    START;

    /**
     * Project property to ask Modelio to make a special action on this module on project open.
     */
    @objid ("8bbd14d9-7f40-45ad-8b27-e3abcd286ae1")
    private static final String PROP = "action-on-open";

    /**
     * Get the action to do on this module on project open.
     * @param m the module.
     * @return the action to do.
     */
    @objid ("b2f54913-4ecc-4b60-a308-2785bf0bd3f0")
    public static ModuleStartAction get(GModule m) {
        String v = m.getParameters().getValue(PROP);
        if (v == null) {
            return START;
        }
        return ModuleStartAction.valueOf(v);
    }

    /**
     * Set the action to do on this module on project open.
     * @param m the module.
     * @param f the action to do.
     */
    @objid ("2c531f2c-d668-4c2b-908c-2538f43d0a23")
    public static void set(GModule m, ModuleStartAction f) {
        f.set(m);
    }

    /**
     * Remove the action from the module parameters.
     * @param m the module.
     */
    @objid ("70079ff7-afa5-46f0-bd9c-ddb31154930c")
    public static void clear(GModule m) {
        m.getParameters().remove(PROP);
    }

    /**
     * Persist this action to do on the module on project open.
     * @param m the module.
     */
    @objid ("995a841b-2819-496b-a251-d4a571aae834")
    public void set(GModule m) {
        if (this == START) {
            m.getParameters().remove(PROP);
        } else {
            m.getParameters().setProperty(PROP, name(), DefinitionScope.LOCAL);
        }
        
    }

}
