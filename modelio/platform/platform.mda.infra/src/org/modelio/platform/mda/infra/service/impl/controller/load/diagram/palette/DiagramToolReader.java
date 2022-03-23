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
package org.modelio.platform.mda.infra.service.impl.controller.load.diagram.palette;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.api.modelio.diagram.tools.IDiagramTool;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Tool;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.contributions.ContributionReader;
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;

@objid ("086d1801-6e1a-4f12-b509-4a37501cd671")
public class DiagramToolReader {
    @objid ("9dd9ff5c-65cc-4f0f-9dcc-d66009c848e0")
    public void registerTool(Jxbv2Tool toolDef, IRTModuleAccess module) {
        // Load and instantiate the handler class in the same class loader as
        // the module.
        try {
            Object instance = null;
        
            ContributionReader helper = new ContributionReader(module);
        
            instance = helper.createHandler(
                    ContributionReader.CONTRIB_DIAGRAMTOOL, toolDef
                            .getHandler().getClazz(),
                    IDiagramTool.class);
        
            // Check what we finally got for handler
            if (!(instance instanceof IDiagramTool)) {
                throw new IOException(MdaInfra.I18N.getMessage("L43_class_is_not_IDiagramTool", toolDef.getHandler().getClazz()));
            }
        
            // Process command addition
            IDiagramTool diagramTool = (IDiagramTool) instance;
        
            // Decorate: label, icon, tooltip
            String label = module.getLabel(toolDef.getLabel());
            String tooltip = module.getLabel(toolDef.getTooltip());
        
            ImageDescriptor image = null;
            if ((toolDef.getImage() != null) && !toolDef.getImage().equals("")) {
                Path bitmap = module.getConfiguration()
                        .getModuleResourcesPath()
                        .resolve(module.getLabel(toolDef.getImage()));
                image = ImageDescriptor.createFromFile(null, bitmap.toString());
            }
        
            diagramTool.decorate(label, tooltip, image);
        
            // Collect hParameters
            Map<String, String> hParameters = helper.readParameters(toolDef
                    .getHandler().getHParameter());
        
            // Collect applicable scopes
            List<ElementScope> sourceScopes = helper.readScopes(toolDef
                    .getScopeSource());
            List<ElementScope> targetScopes = helper.readScopes(toolDef
                    .getScopeTarget());
        
            diagramTool.initialize(sourceScopes, targetScopes, hParameters,
                    module.getIModule());
        
            // Register command
            module.registerCustomizedTool(toolDef.getId(), diagramTool);
        
        } catch (IOException e) {
            MdaInfra.LOG.error(e);
        }
        
    }

}
