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

package org.modelio.diagram.elements.umlcommon.diagramview;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.common.linkednode.CreateLinkedNodeCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.umlcommon.diagramview.diagramselection.DiagramSelectionModel;
import org.modelio.diagram.elements.umlcommon.diagramview.diagramselection.DiagramSelectionPopup;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IModelFactoryService;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Extension of {@link CreateLinkedNodeCommand} to create "related_diagram" dependencies.
 * <p>
 * When creating such dependency, open a dialog box to make the diagram user choose which diagram is to be related.
 * </p>
 */
@objid ("1f378994-8429-4b8e-84d7-d29bdacc70cc")
public final class CreateDiagramViewCommand extends CreateLinkedNodeCommand {
    /**
     * Creates a node creation command.
     * 
     * @param context Details on the MObject and/or the node to create
     */
    @objid ("b7c9c5b2-57be-4605-a6cd-d0b98edf3bc5")
    public CreateDiagramViewCommand(ModelioCreationContext context) {
        super(context);
    }

    @objid ("2dcd2668-da90-439a-877e-2af3a911b042")
    @Override
    protected MObject createElement(IModelFactoryService modelFactory, IElementNamer elementNamer) {
        Dependency dependency = (Dependency) super.createElement(modelFactory, elementNamer);
        if (dependency != null && dependency.getDependsOn() == null) {
            Collection<AbstractDiagram> diagrams = CoreSession.getSession(dependency).getModel().findByClass(AbstractDiagram.class);
        
            // A related diagram can't show its parent diagram, this would cause an infinite loop...
            diagrams.remove(this.sourceNode.getDiagram().getRelatedElement());
        
            // Open diagram selection popup.
            DiagramSelectionModel model = new DiagramSelectionModel(diagrams);
            DiagramSelectionPopup popup = new DiagramSelectionPopup(Display.getDefault().getActiveShell(), model);
            popup.setBlockOnOpen(true);
            if (popup.open() == IDialogConstants.OK_ID) {
                // Set the link target, then proceed.
                AbstractDiagram chosenDiagram = model.getSelected();
                dependency.setDependsOn(chosenDiagram);
            } else {
                // User cancelled.
                throw new OperationCanceledException();
            }
        }
        return dependency;
    }

}
