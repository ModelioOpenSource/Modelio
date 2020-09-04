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

package org.modelio.diagram.elements.common.abstractdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.common.freezone.BaseFreeZoneLayoutEditPolicy;
import org.modelio.diagram.elements.common.freezone.ILayoutAssistant;
import org.modelio.diagram.elements.drawings.layer.GmDrawingLayer;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This policy provides a basic XYLayout behavior that should be used as a base (if not used as is) for the layout of
 * all diagrams.
 * 
 * @author fpoyer
 */
@objid ("80d38eef-1dec-11e2-8cad-001ec947c8cc")
public class DiagramEditLayoutPolicy extends BaseFreeZoneLayoutEditPolicy {
    @objid ("80d38ef1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected MObject getHostElement() {
        final AbstractDiagram diagram = (AbstractDiagram) super.getHostElement();
        return diagram.getOrigin();
    }

    @objid ("80d38ef6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean canHandle(MClass metaclass, String dep) {
        // Any note or constraint figure may owned by the diagram figure.
        if (Note.class.isAssignableFrom(metaclass.getJavaInterface()) || Constraint.class.isAssignableFrom(metaclass.getJavaInterface())) {
            return true;
        }
        return super.canHandle(metaclass, dep);
    }

    /**
     * For diagram the default feedback provided by the base class is ugly because it has a background Specialize and
     * remove the background.
     */
    @objid ("80d38efe-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(final Request request) {
        super.showTargetFeedback(request);
        if (this.highlight != null) {
            this.highlight.setOpaque(false);
            this.highlight.setBackgroundColor(null);
        }
    }

    @objid ("68911685-6e7d-498a-95f8-6bae340817f6")
    @Override
    protected EditPolicy createChildEditPolicy(EditPart child) {
        // No edit policy for drawing layers
        if (child.getModel() instanceof GmDrawingLayer) {
            return null;
        }
        return super.createChildEditPolicy(child);
    }

    /**
     * Redefined to avoid intersections during resize.
     */
    @objid ("f8075492-f05f-4e29-96dc-9efd83861f94")
    @Override
    protected Command getChangeConstraintCommand(ChangeBoundsRequest request) {
        CompoundCommand finalCommand = new CompoundCommand();
        
        // Call super to get the child resize command
        Command cmd = super.getChangeConstraintCommand(request);
        finalCommand.add(cmd);
        
        ILayoutAssistant helper = getLayoutAssistant(request);
        
        // build commands for initial and added requests
        // build commands for moved bend points
        createLayoutAssistantCommands(helper, finalCommand);
        return finalCommand.isEmpty() ? null : finalCommand.unwrap();
    }

}
