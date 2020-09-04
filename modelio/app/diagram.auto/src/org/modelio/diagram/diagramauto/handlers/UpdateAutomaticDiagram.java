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

package org.modelio.diagram.diagramauto.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.api.modelio.diagram.autodiagram.IDiagramCreator;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.IModelioService;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.diagramauto.diagram.creator.ClassArchitectureCreator;
import org.modelio.diagram.diagramauto.diagram.creator.ClassStructureCreator;
import org.modelio.diagram.diagramauto.diagram.creator.DependencyCreator;
import org.modelio.diagram.diagramauto.diagram.creator.InheritanceCreator;
import org.modelio.diagram.diagramauto.diagram.creator.PackageContentStructureCreator;
import org.modelio.diagram.diagramauto.diagram.creator.SubPackageStructureCreator;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MStatus;

@objid ("a08a4aea-1e8b-40e4-9828-fc22f403587c")
public class UpdateAutomaticDiagram {
    @objid ("e7d6baf6-1889-4461-8265-33f0d99278aa")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) final ISelection selection, IProjectService projectService, IMModelServices modelServices, IModelioEventService eventService) {
        try (ITransaction transaction = projectService.getSession().getTransactionSupport()
                .createTransaction("SubPackageStructureDiagram");) {
            for (AbstractDiagram diagram : SelectionHelper.toList(selection, AbstractDiagram.class)) {
                final IDiagramCreator dc = getCorrespondingDiagramCreator(diagram, modelServices);
                AbstractDiagram createDiagram = dc.createDiagram(dc.getMainElement(diagram));
                eventService.postAsyncEvent(new IModelioService() {
                    @Override
                    public String getName() {
                        return "Udate automatic diagram";
                    }
                }, ModelioEvent.EDIT_ELEMENT, createDiagram);
            }
            transaction.commit();
        }
    }

    @objid ("1b66dafc-9118-4b7e-ac5b-1e133e8528d0")
    @CanExecute
    public boolean isEnabled(@Named (IServiceConstants.ACTIVE_SELECTION) final ISelection selection, IMModelServices modelServices) {
        for (AbstractDiagram elt : SelectionHelper.toList(selection, AbstractDiagram.class)) {
            MStatus elementStatus = elt.getStatus();
            if (elt.getMClass().isCmsNode() && elementStatus.isCmsManaged()) {
                if (elementStatus.isRamc()) {
                    return false;
                }
            } else if (!elt.isModifiable()) {
                return false;
            }
        
            // Deactivate if no context is found
            if (getCorrespondingDiagramCreator(elt, modelServices) == null) {
                return false;
            }
        }
        return !selection.isEmpty();
    }

    @objid ("06aee345-97b2-4eb1-843f-69bc65b4677d")
    public IDiagramCreator getCorrespondingDiagramCreator(AbstractDiagram existingdiagramauto, IMModelServices modelServices) {
        ClassArchitectureCreator cac = new ClassArchitectureCreator(modelServices);
        ModelElement mainElement = cac.getMainElement(existingdiagramauto);
        if (mainElement instanceof Classifier && existingdiagramauto.equals(cac.getExistingAutoDiagram(mainElement))) {
            return cac;
        }
        
        ClassStructureCreator csc = new ClassStructureCreator(modelServices);
        mainElement = csc.getMainElement(existingdiagramauto);
        if (mainElement instanceof Classifier && existingdiagramauto.equals(csc.getExistingAutoDiagram(mainElement))) {
            return csc;
        }
        
        DependencyCreator dc = new DependencyCreator(modelServices);
        mainElement = dc.getMainElement(existingdiagramauto);
        if (mainElement != null && existingdiagramauto.equals(dc.getExistingAutoDiagram(mainElement))) {
            return dc;
        }
        
        InheritanceCreator ic = new InheritanceCreator(modelServices);
        mainElement = ic.getMainElement(existingdiagramauto);
        if (mainElement instanceof Classifier && existingdiagramauto.equals(ic.getExistingAutoDiagram(mainElement))) {
            return ic;
        }
        
        PackageContentStructureCreator pcsc = new PackageContentStructureCreator(modelServices);
        mainElement = pcsc.getMainElement(existingdiagramauto);
        if (mainElement instanceof Package && existingdiagramauto.equals(pcsc.getExistingAutoDiagram(mainElement))) {
            return pcsc;
        }
        
        SubPackageStructureCreator spsc = new SubPackageStructureCreator(modelServices);
        mainElement = spsc.getMainElement(existingdiagramauto);
        if (mainElement instanceof Package && existingdiagramauto.equals(spsc.getExistingAutoDiagram(mainElement))) {
            return spsc;
        }
        return null;
    }

}
