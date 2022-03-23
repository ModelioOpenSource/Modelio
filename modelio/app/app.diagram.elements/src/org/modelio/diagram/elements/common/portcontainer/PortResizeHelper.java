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
package org.modelio.diagram.elements.common.portcontainer;

import java.util.Iterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;

/**
 * Helper methods for port container auto resizing.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("c4063d8d-1c2e-4d8a-9ae6-522296058787")
class PortResizeHelper {
    /**
     * Key for the request extended property containing the requested main node bounds.
     */
    @objid ("47413230-8c15-40f5-90fc-ab5c46a80a3f")
    public static final String REQPROP_MAIN_NODE_BOUNDS = "newHandleBounds";

    /**
     * put main node bounds in the request.
     * <p>
     * In case port container parent is a port container itself, it needs to know the modification of our main node bounds.
     * @param mainNodeConstraint the main node constraint to put, if different from current ones.
     * If <i>null</i> the current one will be used. Must be relative to layout origin.
     * @param containerEditPart the port container edit part
     * @param req the request to complete.
     */
    @objid ("f9f8bbb8-9978-4ec5-b82d-f3bc36078d2f")
    static void putMainNodeBounds(PortContainerEditPart containerEditPart, ChangeBoundsRequest req) {
        IFigure containerFigure = containerEditPart.getFigure();
        Rectangle newMainNodeBounds = getMainNodeBoundsFromConstraint((PortContainerFigure) containerFigure) ;
        
        containerFigure.translateToAbsolute(newMainNodeBounds);
        req.getExtendedData().put(REQPROP_MAIN_NODE_BOUNDS, newMainNodeBounds);
        
    }

    /**
     * Get the main node bounds from the one of the following ways by order:<ol>
     * <li> the main node constraint in the layout manager
     * <li> the port container figure handle bounds.
     * </ol>
     * The returned rectangle is freely modifiable copy.
     * @param containerFigure the port container figure
     * @return the bounds the main node should have
     */
    @objid ("ecdd12e0-1c20-43e3-94cc-6772ce0d2ed6")
    public static Rectangle getMainNodeBoundsFromConstraint(PortContainerFigure containerFigure) {
        PortContainerLayout containerLayout = containerFigure.getPortContainerLayout();
        Rectangle mainNodeBounds = containerLayout.getMainNodeConstraint();
        if (mainNodeBounds != null) {
            mainNodeBounds = mainNodeBounds.getTranslated(containerLayout.getOrigin(containerFigure));
        } else {
            mainNodeBounds = containerFigure.getHandleBounds().getCopy();
        }
        return mainNodeBounds;
    }

    @objid ("20567f1c-43c3-40d3-a673-eabb17479597")
    public static Rectangle computeRequestedMainNodeBounds(PortContainerFigure containerFigure, ChangeBoundsRequest request) {
        Rectangle newHandleBounds = getMainNodeBoundsFromConstraint(containerFigure);
        
        containerFigure.translateToAbsolute(newHandleBounds);
        newHandleBounds = request.getTransformedRectangle(newHandleBounds);
        //containerFigure.translateToRelative(newHandleBounds);
        return newHandleBounds;
    }

    /**
     * Recursively dump the command and all its composition hierarchy to a string.
     * <p>
     * TODO : move this in a more general class.
     * @param cmd the command to dump
     * @return the string representation.
     */
    @objid ("c5c6d460-bc7b-4840-9b95-64734d27e56e")
    public static String dump(Command cmd) {
        StringBuilder s = new StringBuilder();
        dump(cmd, s, "");
        return s.toString();
    }

    @objid ("55141144-06c7-4534-b825-f93c1ec499e7")
    private static void dump(Command cmd, StringBuilder s, String indent) {
        if (cmd instanceof CompoundCommand) {
            CompoundCommand c = (CompoundCommand) cmd;
            String subIndent = indent.concat("  ");
            s.append(c.getClass().getSimpleName());
            s.append(" [");
            for (Iterator<Command> iterator = c.getCommands().iterator(); iterator.hasNext();) {
                Command sub = iterator.next();
                s.append("\n");
                s.append(indent);
                s.append(" - ");
                dump(sub, s, subIndent);
            }
        
        } else {
            s.append(String.valueOf(cmd).replace("\n", "\n"+indent));
        }
        
    }

}
