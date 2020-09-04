/* 
 * Copyright 2013-2018 Modeliosoft
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.tools.ConnectionBendpointTracker;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;

/**
 * Specialisation of the {@link ConnectionBendpointTracker} to add the information of the previous segment orientation.
 * 
 * @author fpoyer
 */
@objid ("803633c5-1dec-11e2-8cad-001ec947c8cc")
public class OrientedBendpointTracker extends ConnectionBendpointTracker {
    @objid ("90d80577-1e83-11e2-8cad-001ec947c8cc")
    private Orientation orientation = Orientation.NONE;

    /**
     * Sets the orientation of the segment preceding the reference bendpoint.
     * @param orientation the orientation of the segment preceding the reference bendpoint.
     */
    @objid ("803895d7-1dec-11e2-8cad-001ec947c8cc")
    public void setOrientation(final Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * C'tor.
     * @param orientation the orientation of the segment preceding the reference bendpoint.
     */
    @objid ("803895dc-1dec-11e2-8cad-001ec947c8cc")
    public OrientedBendpointTracker(final Orientation orientation) {
        super();
        this.orientation = orientation;
    }

    /**
     * C'tor.
     * @param editpart the connection
     * @param i the index of the bendpoint
     * @param orientation the orientation of the segment preceding the reference bendpoint.
     */
    @objid ("803895e1-1dec-11e2-8cad-001ec947c8cc")
    public OrientedBendpointTracker(final ConnectionEditPart editpart, final int i, final Orientation orientation) {
        super(editpart, i);
        this.orientation = orientation;
    }

    @objid ("803895ec-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void updateSourceRequest() {
        super.updateSourceRequest();
        BendpointRequest request = (BendpointRequest) getSourceRequest();
        request.getExtendedData().put(Orientation.class, this.orientation);
    }

}
