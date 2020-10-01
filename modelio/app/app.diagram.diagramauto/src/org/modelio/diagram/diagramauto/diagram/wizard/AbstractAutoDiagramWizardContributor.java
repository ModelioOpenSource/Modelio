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

package org.modelio.diagram.diagramauto.diagram.wizard;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.model.view.template.service.ModelViewTemplateManager;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

@objid ("93e073db-aaef-41c6-bbd6-38f28ead203c")
public abstract class AbstractAutoDiagramWizardContributor extends AbstractDiagramWizardContributor {
    @objid ("dae3661a-37d3-49d7-95e0-08ae80508540")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("2b2b466c-9a7d-4277-ab76-30d6347c3b56")
    @Inject
    @Optional
    private ModelViewTemplateManager diagramCreationService;

    @objid ("08e2d2f2-c3c3-4ef1-849f-a562ac169398")
    protected MMetamodel getMetamodel() {
        return this.mmServices.getMetamodel();
    }

    @objid ("3e199e4c-4e81-498a-8134-de0f77da1b0e")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, ClassDiagram.MQNAME);
    }

    @objid ("66a4e8ff-3461-416a-a334-d369e54f5c46")
    @Override
    public final AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        IModelViewTemplate<AbstractDiagram> creator = this.diagramCreationService.get(getModelViewTemplateId());
        AbstractDiagram existingDiagram = creator.getExistingView(diagramContext);
        if (existingDiagram != null) {
            creator.updateView(existingDiagram);
            return existingDiagram;
        } else {
            AbstractDiagram diagram = creator.createView(diagramContext);
            if (!diagramName.equals(getLabel())) {
                diagram.setName(diagramName);
            }
            diagram.putNoteContent("ModelerModule", ModelElement.MQNAME, "description", diagramDescription);
            return diagram;
        }
    }

    @objid ("f178b37e-78df-4a5d-99bd-d0fe80017560")
    @Override
    public boolean accept(ModelElement main) {
        if (!super.accept(main)) {
            return false;
        }
        
        IModelViewTemplate<AbstractDiagram> creator = this.diagramCreationService.get(getModelViewTemplateId());
        if (creator == null) {
            return false;
        }
        
        // Deactivate if no context is found
        if (creator.resolveOrigin(main) == null) {
            return false;
        }
        
        AbstractDiagram existingDiagram = creator.getExistingView(main);
        
        // Unmodifiable diagram means the command is disabled
        if (existingDiagram != null && !existingDiagram.getStatus().isModifiable()) {
            return false;
        } else {
            return true;
        }
    }

}
