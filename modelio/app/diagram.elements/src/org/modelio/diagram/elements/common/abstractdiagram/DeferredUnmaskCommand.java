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

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Command that unmask an element in the first diagram node representing the parent composite element that allows unmasking . its parent IF said parent is also unmasked in the diagram at the time of execution.
 * 
 * @author fpoyer
 */
@objid ("7e0d121b-1dec-11e2-8cad-001ec947c8cc")
public class DeferredUnmaskCommand extends Command {
    @objid ("7e0d121f-1dec-11e2-8cad-001ec947c8cc")
    private final MObject parent;

    @objid ("7e0d1221-1dec-11e2-8cad-001ec947c8cc")
    private final MObject child;

    @objid ("7e0d1223-1dec-11e2-8cad-001ec947c8cc")
    private final AbstractDiagramEditPart diagramEditPart;

    @objid ("39bed0bb-7429-4ca8-8207-d9cbcb6fa839")
    private final Point dropLocation;

    /**
     * C'tor.
     * 
     * @param parent the parent in which to unmask.
     * @param child the child to unmask.
     * @param dropLocation the point where the child should be unmasked.
     * @param diagramEditPart the diagram in which the unmasking takes place.
     */
    @objid ("7e0f744a-1dec-11e2-8cad-001ec947c8cc")
    public DeferredUnmaskCommand(MObject parent, MObject child, Point dropLocation, AbstractDiagramEditPart diagramEditPart) {
        this.parent = parent;
        this.child = child;
        this.dropLocation = dropLocation;
        this.diagramEditPart = diagramEditPart;
    }

    @objid ("7e0f7453-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        // Should always return true, we don't know is the deferred command can be executed until the preceding commands have been executed.
        return true;
    }

    @objid ("7e0f7458-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        Command command = createCommand();
        
        if (command != null && command.canExecute()) {
            command.execute();
        }
    }

    /**
     * Create the real command.
     * 
     * @return the real command.
     */
    @objid ("7e0f745b-1dec-11e2-8cad-001ec947c8cc")
    private Command createCommand() {
        // Build a request to unmask the child.
        CreateRequest req = new CreateRequest();
        req.setLocation(this.dropLocation);
        req.setSize(new Dimension(-1, -1));
        req.setFactory(new ModelioCreationContext(this.child));
        
        // Look for all Gm representing the parent, get the corresponding edit part and ask it to unmask the child.
        IGmDiagram gmDiagram = (IGmDiagram) this.diagramEditPart.getModel();
        Collection<GmModel> parentModels = gmDiagram.getAllGMRelatedTo(new MRef(this.parent));
        for (GmModel parentModel : parentModels) {
            EditPart parentEditPart = (EditPart) this.diagramEditPart.getViewer()
                    .getEditPartRegistry()
                    .get(parentModel);
            if (parentEditPart != null) {
                // Force immediate validation of the figure to ensure bounds are set.
                final IFigure parentFigure = ((GraphicalEditPart) parentEditPart).getFigure();
                parentFigure.revalidate();
                parentFigure.getUpdateManager().performValidation();
        
                EditPart targetPart = parentEditPart.getTargetEditPart(req);
                if (targetPart != null) {
                    // Parent provided a valid target
                    return targetPart.getCommand(req);
                }
            }
        }
        return null;
    }

}
