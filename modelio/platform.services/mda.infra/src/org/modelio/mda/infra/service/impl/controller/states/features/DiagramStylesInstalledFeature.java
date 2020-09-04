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

package org.modelio.mda.infra.service.impl.controller.states.features;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.mda.infra.plugin.MdaInfra;
import org.modelio.mda.infra.service.IRTModule;
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
        if (this.module.getConfiguration().getStylePath().size() > 0) {
            Display.getDefault().syncExec(new DiagramToolsAdder(this.module));
        }
    }

    @objid ("c8ee223f-55b7-481f-b475-d9eb45b1444d")
    @Override
    public void disable() {
        // noop
    }

    @objid ("11166ab3-d5de-44bc-bd5f-bb6116a03c98")
    @Override
    public String toString() {
        return "Diagram styles installed";
    }

    @objid ("4b492e23-a73a-4541-9c27-0d19ea4f9d02")
    private static class DiagramToolsAdder implements Runnable {
        @objid ("48b39629-a0dd-4614-b169-bbd78aeb6bab")
        private final IRTModule rtModule;

        @objid ("8cffe1c3-a497-4500-9f60-3d24f6b60ca6")
        public DiagramToolsAdder(final IRTModule rtModule) {
            this.rtModule = rtModule;
        }

        @objid ("d6b69012-aa6b-4408-80fa-38b8eb2d6721")
        @Override
        public void run() {
            for (Entry<String, Path> style : this.rtModule.getConfiguration().getStylePath().entrySet()) {
                Path stylePath = style.getValue();
                String styleName = style.getKey();
                try {
                    if (Files.exists(stylePath)) {
                        this.rtModule.getIModule().getModuleContext().getModelioServices().getDiagramService().registerStyle(styleName, "default", stylePath.toFile());
                    } else {
                        MdaInfra.LOG.warning(" '%s' file for '%s' diagram style missing.", stylePath, styleName);
                    }
                } catch (Exception e) {
                    // Just log and continue
                    MdaInfra.LOG.warning(" Failed installing '%s' file for '%s' diagram style:", stylePath, styleName, e.toString());
                    MdaInfra.LOG.warning(e);
                }
            }
        }

    }

}
