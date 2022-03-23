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
package org.modelio.bpmn.diagram.editor.layout;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.session.api.transactions.ITransaction;

@objid ("9f54d205-50c0-45fd-b818-576a0148cd64")
public class LayoutHandler {
    @objid ("9989add3-215e-48ca-a3e7-c4592cfcc69c")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        return SelectionHelper.getFirst(selection, GraphicalEditPart.class) != null;
    }

    @objid ("4b708755-6f44-46a2-b883-e050492cff34")
    @Execute
    public Object execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection, IProjectService projectService) {
        GraphicalEditPart editPart = SelectionHelper.getFirst(selection, GraphicalEditPart.class);
        GmModel model = ((GmModel) editPart.getModel());
        AbstractDiagram d = (AbstractDiagram) model.getDiagram().getRepresentedElement();
        try (ITransaction t = projectService.getOpenedProject().getSession().getTransactionSupport().createTransaction("layout")) {
            new BpmnLayouter(d).run();
            t.commit();
        }
        return null;
    }

}
