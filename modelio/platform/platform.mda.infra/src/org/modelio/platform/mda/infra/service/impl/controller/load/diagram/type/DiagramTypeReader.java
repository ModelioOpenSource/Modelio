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
package org.modelio.platform.mda.infra.service.impl.controller.load.diagram.type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramCustomizer;
import org.modelio.api.modelio.diagram.tools.PaletteEntry;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Palette;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2ToolRef;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.contributions.ContributionReader;
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Registers diagram customizations from module.xml .
 */
@objid ("dcb4fee2-359b-4143-9995-7afa4077a462")
public class DiagramTypeReader {
    /**
     * Registers diagram customizations from module.xml .
     * @param diagramDef the module.xml part
     * @param module the module to fill
     */
    @objid ("e9b6b654-ea63-47c6-be76-983e9d1d4ddc")
    public void registerDiagramType(Jxbv2DiagramType diagramDef, IRTModuleAccess module) {
        IDiagramCustomizer customizer = null;
        try {
            ContributionReader helper = new ContributionReader(module);
        
            customizer = helper.createHandler(ContributionReader.CONTRIB_DIAGRAMCUSTOMIZER,
                    diagramDef.getHandler().getClazz(),
                    IDiagramCustomizer.class);
        
            // Collect palette tool contributions
            List<PaletteEntry> tools = new ArrayList<>();
            boolean keepBasePalette = true;
            Jxbv2Palette palette = diagramDef.getPalette();
            if (palette != null) {
                keepBasePalette = palette.isKeepBasePalette();
                // Jxbv2Commands
                for (Jxbv2ToolRef toolref : palette.getToolRef()) {
                    String group = module.getLabel(toolref.getGroup());
                    tools.add(new PaletteEntry(toolref.getRefid(), group));
                }
            }
        
            // Collect hParameters
            Map<String, String> hParameters = helper.readParameters(diagramDef.getHandler().getHParameter());
        
            // Initialize the customizer
            customizer.initialize(module.getIModule(), tools, hParameters, keepBasePalette);
        
            MMetamodel metamodel = module.getModel().getMClass().getMetamodel();
        
            // Read base class and stereotype
            MClass baseDiagramClass = null;
            if (diagramDef.getBaseDiagram() != null) {
                baseDiagramClass = metamodel.getMClass(diagramDef.getBaseDiagram());
            } else {
                baseDiagramClass = metamodel.getMClass(AbstractDiagram.class);
            }
        
            Stereotype stereotype = null;
            if (diagramDef.getStereotype() != null) {
                stereotype = helper.readStereotypeSpec(baseDiagramClass, diagramDef.getStereotype());
            }
        
            // stereotype CANNOT be null
            if (stereotype == null) {
                throw new IOException(String.format("Invalid or unknown stereotype: <<%s>> on '%s' diagram metaclass.", diagramDef.getStereotype(), baseDiagramClass.getName()));
            }
        
            // Register the diagram type
            @SuppressWarnings("unchecked")
            Class<? extends AbstractDiagram> diagramJavaInterface = (Class<? extends AbstractDiagram>) baseDiagramClass.getJavaInterface();
            module.registerDiagramCustomization(stereotype, diagramJavaInterface, customizer);
        } catch ( IOException e) {
            MdaInfra.LOG.error(e);
            return;
        }
        
    }

}
