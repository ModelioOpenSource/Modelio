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
package org.modelio.diagram.elements.common.freezone;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;

/**
 * A layout assistant may moves edit parts and connection bendpoints for example to avoid intersections after a move or resize.
 * <p>
 * A layout assistant must use and being fed only with absolute coordinates.
 * 
 * @since 3.4.1
 */
@objid ("5c71ebc2-28a2-4189-8ea9-832f7ea756a8")
public interface ILayoutAssistant {
    /**
     * Viewer property to set to <i>false</i> to disable layout assistant.
     */
    @objid ("5723b194-a8e9-4910-a191-9591f180725a")
    public static final String VIEWPROP_ENABLED = "layout_assitant_enabled";

    /**
     * Get the requests to apply to avoid connection intersections with changed nodes.
     * @return the bend point requests to apply.
     */
    @objid ("eeec8036-f6c1-47ba-b244-1ec521a5ac5a")
    Collection<BendpointRequest> getBendPointRequests();

    /**
     * Get the requests to apply to avoid node intersections.
     * @return the requests to apply.
     */
    @objid ("3e9cd1f0-6eae-4cec-bfe3-a44796476f9b")
    Collection<ChangeBoundsRequest> getNodeRequests();

    /**
     * Record a command to execute.
     * <p>
     * The recorded commands will be garbaged if the helper is modified after this call.
     * @param cmd a command to execute.
     */
    @objid ("ff275aeb-e045-47c1-a478-a3d3c443f68c")
    void addCommand(Command cmd);

    /**
     * Create a new command that will execute the commands recorded by {@link #addCommand(Command)}.
     * <p>
     * The returned command will be invalidated to do nothing if the helper is modified after this call.
     * @return an intersection remover command.
     */
    @objid ("8725ca20-73c6-492c-835e-c9f620fdad58")
    Command createExecuteCommand();

    /**
     * Record a moved/resized node
     * @param movedEp the changed node.
     * @param oldRect its old bounding rectangle in absolute coordinates.
     * @param newRect its new bounding rectangle in absolute coordinates.
     */
    @objid ("865c269a-6077-4cbb-8c25-45aa95abcd0f")
    void addBoundsChange(GraphicalEditPart movedEp, PrecisionRectangle oldRect, PrecisionRectangle newRect);

}
