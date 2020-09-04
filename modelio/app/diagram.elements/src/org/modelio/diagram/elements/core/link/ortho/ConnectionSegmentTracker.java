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

package org.modelio.diagram.elements.core.link.ortho;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.tools.SimpleDragTracker;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;

/**
 * Specific drag tracker "installed" on segments of orthogonal-routed connections. It sends specific type of {@link BendpointRequest} so that the corresponging {@link OrthoBendpointEditPolicy} can understand them.
 * 
 * @author fpoyer
 */
@objid ("8033d12b-1dec-11e2-8cad-001ec947c8cc")
public class ConnectionSegmentTracker extends SimpleDragTracker {
    @objid ("8033d134-1dec-11e2-8cad-001ec947c8cc")
    private int index;

    @objid ("90d340d0-1e83-11e2-8cad-001ec947c8cc")
     static final String REQ_MOVE_SEGMENT = "move connection segment";

    @objid ("90d340d7-1e83-11e2-8cad-001ec947c8cc")
    private Orientation orientation;

    @objid ("26492515-f458-4814-973d-9381cb6fb102")
    private ConnectionEditPart editpart;

    /**
     * Constructs a tracker for the given connection and index.
     * 
     * @param editpart the connection
     * @param index the index of the segment
     * @param orientation the orientation of the segment
     */
    @objid ("8033d136-1dec-11e2-8cad-001ec947c8cc")
    public ConnectionSegmentTracker(final ConnectionEditPart editpart, final int index, final Orientation orientation) {
        setConnectionEditPart(editpart);
        setIndex(index);
        setOrientation(orientation);
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#createOperationSet()
     */
    @objid ("8033d141-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<Object> createOperationSet() {
        List<Object> list = new ArrayList<>();
        list.add(getConnectionEditPart());
        return list;
    }

    /**
     * Creates a BendpointRequest with the specific type REQ_MOVE_SEGMENT.
     */
    @objid ("8033d149-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Request createSourceRequest() {
        BendpointRequest request = new BendpointRequest();
        request.setType(ConnectionSegmentTracker.REQ_MOVE_SEGMENT);
        request.setIndex(getIndex());
        request.setSource(getConnectionEditPart());
        return request;
    }

    @objid ("8033d151-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCommand() {
        return getConnectionEditPart().getCommand(getSourceRequest());
    }

    @objid ("8033d158-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String getCommandName() {
        return ConnectionSegmentTracker.REQ_MOVE_SEGMENT;
    }

    /**
     * Convenience method to obtain the connection editpart's connection figure.
     * 
     * @return the connection figure
     */
    @objid ("8033d15d-1dec-11e2-8cad-001ec947c8cc")
    protected Connection getConnection() {
        return (Connection) getConnectionEditPart().getFigure();
    }

    /**
     * Returns the connection editpart on which the tracker operates.
     * 
     * @return the connection editpart
     */
    @objid ("8033d164-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionEditPart getConnectionEditPart() {
        return this.editpart;
    }

    @objid ("8033d16b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String getDebugName() {
        return "Orthogonnaly routed Connection Segment Handle Tracker " + getCommandName(); //$NON-NLS-1$
    }

    /**
     * Returns the index of the segment being dragged.
     * 
     * @return the index
     */
    @objid ("8033d170-1dec-11e2-8cad-001ec947c8cc")
    protected int getIndex() {
        return this.index;
    }

    /**
     * Sets the connection editpart being operated on.
     * 
     * @param editpart the connection
     */
    @objid ("8036337e-1dec-11e2-8cad-001ec947c8cc")
    public void setConnectionEditPart(final ConnectionEditPart editpart) {
        this.editpart = editpart;
    }

    /**
     * Sets the index of the operation.
     * 
     * @param i the index
     */
    @objid ("80363385-1dec-11e2-8cad-001ec947c8cc")
    public void setIndex(final int i) {
        this.index = i;
    }

    @objid ("8036338a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void updateSourceRequest() {
        BendpointRequest request = (BendpointRequest) getSourceRequest();
        request.setIndex(getIndex());
        request.getExtendedData().put(Orientation.class, this.orientation);
        request.setLocation(getLocation());
    }

    /**
     * Sets the current orientation.
     * 
     * @param orientation the orientation of the reference segment.
     */
    @objid ("8036338d-1dec-11e2-8cad-001ec947c8cc")
    public void setOrientation(final Orientation orientation) {
        this.orientation = orientation;
    }

}
