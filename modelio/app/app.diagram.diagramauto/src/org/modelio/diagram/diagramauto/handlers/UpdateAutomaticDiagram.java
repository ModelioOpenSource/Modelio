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

package org.modelio.diagram.diagramauto.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.diagram.diagramauto.diagram.creator.AbstractDiagramTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.core.IModelioEventService;
import org.modelio.platform.core.IModelioService;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.model.view.template.service.ModelViewTemplateManager;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.session.api.transactions.ITransaction;

@objid ("a08a4aea-1e8b-40e4-9828-fc22f403587c")
public class UpdateAutomaticDiagram {
    @objid ("e7d6baf6-1889-4461-8265-33f0d99278aa")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) final ISelection selection, IProjectService projectService, IModelioEventService eventService, ModelViewTemplateManager diagramCreationService) {
        try (ITransaction transaction = projectService.getSession().getTransactionSupport().createTransaction("SubPackageStructureDiagram");) {
            for (AbstractDiagram diagram : SelectionHelper.toList(selection, AbstractDiagram.class)) {
                final IModelViewTemplate creator = getCorrespondingDiagramCreator(diagram, diagramCreationService);
                creator.updateView(diagram);
        
                // Open editor on the updated diagram
                eventService.postAsyncEvent(new IModelioService() {
                    @Override
                    public String getName() {
                        return "Update automatic diagram";
                    }
                }, ModelioEvent.EDIT_ELEMENT, diagram);
            }
            transaction.commit();
        }
    }

    @objid ("1b66dafc-9118-4b7e-ac5b-1e133e8528d0")
    @CanExecute
    public boolean isEnabled(@Named (IServiceConstants.ACTIVE_SELECTION) final ISelection selection, ModelViewTemplateManager diagramCreationService) {
        for (AbstractDiagram elt : SelectionHelper.toList(selection, AbstractDiagram.class)) {
            if (!elt.isModifiable()) {
                return false;
            }
        
            // Deactivate if no context is found
            if (getCorrespondingDiagramCreator(elt, diagramCreationService) == null) {
                return false;
            }
        }
        return !selection.isEmpty();
    }

    @objid ("06aee345-97b2-4eb1-843f-69bc65b4677d")
    private IModelViewTemplate getCorrespondingDiagramCreator(AbstractDiagram existingDiagram, ModelViewTemplateManager diagramCreationService) {
        String kind = existingDiagram.getProperty("AutoDiagram", "Kind");
        IModelViewTemplate creator = diagramCreationService.get(kind);
        if (creator == null && kind != null) {
            // Compatibility, search an old creator...
            for (IModelViewTemplate dc : diagramCreationService.getAll()) {
                if (dc instanceof AbstractDiagramTemplate) {
                    AbstractDiagramTemplate adc = (AbstractDiagramTemplate) dc;
                    if (kind.equals(adc.getOldType())) {
                        return adc;
                    }
                }
            }
        }
        return creator;
    }

}
