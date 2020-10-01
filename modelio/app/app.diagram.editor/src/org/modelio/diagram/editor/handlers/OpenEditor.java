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

package org.modelio.diagram.editor.handlers;

import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.model.ui.swt.SelectionHelper;

/**
 * Handler for the "Open diagram..." command in diagrams.
 */
@objid ("a5821d11-c046-44dd-adde-3ba44aa7c7fb")
public class OpenEditor {
    @objid ("8ecc291a-e2ed-48c0-b796-553186c84991")
    @Execute
    public final void execute(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IActivationService activationService) {
        for (GraphicalEditPart editPart : SelectionHelper.toList(selection, GraphicalEditPart.class)) {
            final Element elt = editPart.getAdapter(Element.class);
            if (isHandled(elt)) {
                openEditor(activationService, getMapping(elt));
            }
        }
    }

    @objid ("1799244d-66ac-4e9a-a04c-6a7fe49b3959")
    @CanExecute
    public final boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<GraphicalEditPart> editParts = SelectionHelper.toList(selection, GraphicalEditPart.class);
        if (editParts.isEmpty()) {
            return false;
        }
        for (GraphicalEditPart editPart : editParts) {
            Element elt = editPart.getAdapter(Element.class);
            if (!isHandled(elt)) {
                return false;
            }
        }
        return true;
    }

    @objid ("f5bb7e1b-016d-4ad2-b9d8-2fd7e43fbb85")
    protected final void openEditor(IActivationService activationService, final Element elt) {
        if (elt != null) {
            activationService.activateMObject(elt);
        }
    }

    @objid ("d2879a2a-0fb4-4ccc-9595-ff8ca9df50d3")
    protected Element getMapping(final Element elt) {
        if (elt instanceof Dependency) {
            return ((Dependency) elt).getDependsOn();
        } else if (elt instanceof AbstractDiagram) {
            return elt;
        } else {
            return null;
        }
    }

    @objid ("8e18586d-4ea0-4088-9a26-82c33b9b5e4a")
    protected boolean isHandled(Element elt) {
        return elt instanceof Dependency || elt instanceof AbstractDiagram;
    }

}
