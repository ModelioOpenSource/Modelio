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
package org.modelio.diagram.elements.core.link.path;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;

/**
 * Service to modify a connection path consistently with its routing mode.
 * 
 * @since 5.0.2
 */
@objid ("779f9da4-14fa-476b-aabd-10fe31685c68")
public interface ILinkPathEditor {
    /**
     * Move a connection segment.
     * @param pointIndex the index of the segment first point in the connection point list. Must satisfy 0 <= pointIndex < connection.size() - 1
     * 0 is the source anchor point.
     * @param newLocation the point new location in absolute coordinates
     * @return the new editor to chain calls
     */
    @objid ("f62e525e-6c99-4e05-b73e-f83380097acb")
    ILinkPathEditor moveSegment(int pointIndex, Point newLocation);

    /**
     * Move a connection bend point.
     * @param pointIndex the point index in the connection point list. Must satisfy 0 < pointIndex < connection.size()-1
     * @param newLocation the point new location in absolute coordinates
     * @return the new editor to chain calls
     */
    @objid ("bce8c962-f8a3-44af-a7a8-dbc9d2689ead")
    ILinkPathEditor moveBendPoint(int pointIndex, Point newLocation);

    @objid ("7f7f14ee-d665-4ace-b66f-4f826927a08a")
    ILinkPathEditor setSourceAnchor(ConnectionAnchor newAnchor);

    @objid ("f7d06d11-9df6-4498-a1f9-41596c4650de")
    ILinkPathEditor setTargetAnchor(ConnectionAnchor newAnchor);

    /**
     * Apply a move/resize request to the edited state.
     * <p>
     * The request may involve one or more of:
     * <ul>
     * <li> the source node
     * <li> the target node
     * <li> a parent of the source node
     * <li> a parent of the target node
     * <li> the connection
     * </ul>
     * @param request the move/resize request
     * @param isSimulation if true, assume the source and target nodes are still at their initial location. If false
     * assume source and target nodes already moved.
     * @return this instance to chain calls.
     */
    @objid ("d57614f8-5488-4514-bf2d-e7059e0701b0")
    ILinkPathEditor applyChangeBoundsRequest(final ChangeBoundsRequest request, boolean isSimulation);

    /**
     * Backup the connection anchors and routing constraint in a data object.
     * @return the backup data
     */
    @objid ("be594f6f-8d84-413a-84a9-5ab30d6d79ee")
    ConnectionState backupConnection();

    /**
     * Restore the connection anchors and routing constraint from a data object.
     * @param backup the backup data
     */
    @objid ("9646741f-d957-4ac1-b0c7-8e437329e656")
    void restoreConnection(ConnectionState backup);

    /**
     * Get the resulting state.
     * @return the current state
     */
    @objid ("d142d519-2845-485e-8b26-6d2cec7e3433")
    ConnectionState getState();

    /**
     * Apply the current {@link #getState()} to the edited connection.
     */
    @objid ("b7dabaf5-8fbb-43b9-9a9f-58a9db08607a")
    default void applyStateToConnection() {
        restoreConnection(getState());
    }

    /**
     * Make and return a copy of this editor with a ConnectionState independent from the connection figure and the connected node figure anchors.
     * The returned editor connection state anchors are a copy of the original anchors, owned by dummy figures.
     */
    @objid ("2e8b9ba1-17eb-4f28-91ab-dd666b620fab")
    ILinkPathEditor createFrozenStateCopy();
}

