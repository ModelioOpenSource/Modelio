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

package org.modelio.mda.infra.service.impl.controller.states.features;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.mda.infra.plugin.MdaInfra;
import org.modelio.mda.infra.service.impl.IRTModuleAccess;

/**
 * Install diagram styles.
 */
@objid ("a0aae73c-1dc0-4f2b-86c1-b1b257fd7fb0")
public class DiagramStylesInstalledFeature extends AbstractFeature {
    /**
     * @param module the module
     */
    @objid ("e651ef4d-74dd-446f-a1df-28d090ba8b6c")
    public DiagramStylesInstalledFeature(IRTModuleAccess module) {
        super(module);
    }

    @objid ("e37e1d0f-333b-4490-b0d8-33b39a8a5dd4")
    @Override
    public void enable() throws ModuleException {
        installStyles();
    }

    @objid ("c8ee223f-55b7-481f-b475-d9eb45b1444d")
    @Override
    public void disable() {
        // noop
    }

    @objid ("d43767a1-8219-4723-9d5e-56155bec0751")
    private void installStyles() {
        for (Entry<String, Path> style : this.module.getConfiguration().getStylePath().entrySet()) {
            Path stylePath = style.getValue();
            String styleName = style.getKey();
            try {
                if (Files.exists(stylePath)) {
                    this.module.getIModule().getModuleContext().getModelioServices().getDiagramService().registerStyle(styleName, "default", stylePath.toFile());
                } else {
                    MdaInfra.LOG.warning(" '%s' file for '%s' diagram style missing.",stylePath, styleName);
                }
            } catch (Exception e) {
                // Just log and continue
                MdaInfra.LOG.warning(" Failed installing '%s' file for '%s' diagram style:",stylePath, styleName, e.toString());
                MdaInfra.LOG.warning(e);
            }
        }
    }

    @objid ("11166ab3-d5de-44bc-bd5f-bb6116a03c98")
    @Override
    public String toString() {
        return "Diagram styles installed";
    }

}
