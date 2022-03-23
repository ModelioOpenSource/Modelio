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
package org.modelio.diagram.elements.core.helpers;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Utility class to unmask a model element in the diagram.
 */
@objid ("7fd47350-1dec-11e2-8cad-001ec947c8cc")
public class UnmaskHelper {
    /**
     * Unmask an element in this viewer at the given coordinates.<br>
     * Uses a ModelElementDropRequest, to emulate a standard drag & drop of the element.
     * @param viewer the viewer to unmask the element on.
     * @param element the element to unmask.
     * @param dropLocation the unmasking location.
     * @return true if the element could be unmasked, else false.
     */
    @objid ("7fd47352-1dec-11e2-8cad-001ec947c8cc")
    public static boolean unmask(final EditPartViewer viewer, final MObject element, final Point dropLocation) {
        Command com = getUnmaskCommand(viewer, element, dropLocation);
        if (com != null && com.canExecute()) {
            viewer.getEditDomain().getCommandStack().execute(com);
            return true;
        }
        return false;
    }

    /**
     * Get a command that unmask an element in this viewer at the given coordinates.
     * <p>
     * Uses a {@link ModelElementDropRequest}, to emulate a standard drag & drop of the element.
     * @param viewer the viewer to unmask the element on.
     * @param element the element to unmask.
     * @param dropLocation the unmasking location.
     * @return The unmask command or null if the element cannot be unmasked at the given location.
     */
    @objid ("7fd6d572-1dec-11e2-8cad-001ec947c8cc")
    public static Command getUnmaskCommand(final EditPartViewer viewer, final MObject element, final Point dropLocation) {
        final ModelElementDropRequest req = new ModelElementDropRequest();
        req.setDroppedElements(new MObject[] { element });
        req.setLocation(dropLocation);
        
        EditPart targetEditPart = viewer.findObjectAtExcluding(dropLocation,
                                                               Collections.EMPTY_LIST,
                                                               new EditPartViewer.Conditional() {
                                                                   @Override
                                                                public boolean evaluate(EditPart editpart) {
                                                                       return editpart.getTargetEditPart(req) != null;
                                                                   }
                                                               });
        
        targetEditPart = targetEditPart.getTargetEditPart(req);
        if (targetEditPart != null) {
        
            return targetEditPart.getCommand(req);
        }
        return null;
    }

    /**
     * Get a command that unmask many elements in this viewer at the given coordinates.
     * <p>
     * Uses a {@link ModelElementDropRequest}, to emulate a standard drag & drop of the element.
     * @param viewer the viewer to unmask the element on.
     * @param elements the element to unmask.
     * @param dropLocation the unmasking location.
     * @return The unmask command or null if the elements cannot be unmasked at the given location.
     */
    @objid ("7fd6d583-1dec-11e2-8cad-001ec947c8cc")
    public static Command getUnmaskCommand(final EditPartViewer viewer, final Collection<? extends MObject> elements, final Point dropLocation) {
        final ModelElementDropRequest req = new ModelElementDropRequest();
        req.setDroppedElements(elements.toArray(new MObject[elements.size()]));
        req.setLocation(dropLocation);
        
        EditPart targetEditPart = viewer.findObjectAtExcluding(dropLocation,
                                                               Collections.EMPTY_LIST,
                                                               new EditPartViewer.Conditional() {
                                                                   @Override
                                                                public boolean evaluate(EditPart editpart) {
                                                                       return editpart.getTargetEditPart(req) != null;
                                                                   }
                                                               });
        
        targetEditPart = targetEditPart.getTargetEditPart(req);
        if (targetEditPart != null) {
        
            return targetEditPart.getCommand(req);
        }
        return null;
    }

}
