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

package org.modelio.mda.infra.service.impl.controller.load.diagram.wizard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.diagramcreation.IDiagramWizardContributor;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Handler;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module.Jxbv2Gui.Jxbv2Diagrams.Jxbv2DiagramType.Jxbv2Wizard;
import org.modelio.mda.infra.plugin.MdaInfra;
import org.modelio.mda.infra.service.contributions.ContributionReader;
import org.modelio.mda.infra.service.impl.IRTModuleAccess;

/**
 * Diagram wizard contributions reader.
 */
@objid ("9d453241-f387-4dfd-83c2-08d928f52fab")
public class DiagramWizardReader {
    /**
     * read wizard contribution from module.xml
     * 
     * @param module the module
     * @param wizardDef the JAXB node
     * @param wizardCategory the contribution category
     * @throws java.io.IOException on failure
     */
    @objid ("64a1fb92-86a1-4553-9bd4-18bc711156ab")
    public void registerWizard(IRTModuleAccess module, Jxbv2Wizard wizardDef, ContributorCategory wizardCategory) throws IOException {
        ContributionReader helper = new ContributionReader(module);
        
        Jxbv2Handler handler = wizardDef.getHandler();
        if (handler == null) {
            return;
        }
        
        // Create contributor
        IDiagramWizardContributor contributor = helper.createHandler(ContributionReader.CONTRIB_WIZARD, handler.getClazz(), IDiagramWizardContributor.class);
        
        // createDiagramWizardContributor(wizardDef.getHandler(), module);
        
        // Initialize the contributor appearance
        contributor.setLabel(module.getLabel(wizardDef.getLabel()));
        contributor.setHelpUrl(wizardDef.getHelpUrl());
        contributor.setInformation(module.getLabel(wizardDef.getInformation()));
        contributor.setDetails(module.getLabel(wizardDef.getDetails()));
        contributor.setModule(module.getIModule());
        ImageDescriptor iconDescriptor;
        
        if ((wizardDef.getIcon() != null) && !wizardDef.getIcon().equals("")) {
            Path bitmap = module.getConfiguration().getModuleResourcesPath()
                    .resolve(module.getLabel(wizardDef.getIcon()));
            if (Files.exists(bitmap)) {
                iconDescriptor = ImageDescriptor.createFromFile(null,
                        bitmap.toString());
                contributor.setIcon(iconDescriptor.createImage());
            } else {
                MdaInfra.LOG.warning("Unable to find wizard preview image: %s", bitmap);
            }
        }
        
        ImageDescriptor previewDescriptor;
        if ((wizardDef.getPreviewImage() != null)
                && !wizardDef.getPreviewImage().isEmpty()) {
            Path bitmap = module.getConfiguration().getModuleResourcesPath()
                    .resolve(module.getLabel(wizardDef.getPreviewImage()));
            if (Files.exists(bitmap)) {
                previewDescriptor = ImageDescriptor.createFromFile(null,
                        bitmap.toString());
                contributor.setPreviewImage(previewDescriptor);
            } else {
                MdaInfra.LOG.warning("Unable to find wizard preview image: %s", bitmap);
            }
        }
        
        // Initialize the applicable scopes
        List<ElementScope> scopes = helper.readScopes(wizardDef.getScope());
        contributor.setScopes(scopes);
        
        // Initialize the contributor hParameters
        Map<String, String> hParameters = helper.readParameters(handler.getHParameter());
        contributor.setParameters(hParameters);
        
        // Register the contributor
        module.registerWizardContribution(wizardCategory, contributor);
    }

}
