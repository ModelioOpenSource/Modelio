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

package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * Provides a ConnectionEndpointLocator adapted to the mouse move.
 * 
 * @author cmarin
 */
@objid ("8008e6cf-1dec-11e2-8cad-001ec947c8cc")
public class LocatorFactory {
    @objid ("8008e6d1-1dec-11e2-8cad-001ec947c8cc")
    private static LocatorFactory instance = new LocatorFactory();

    @objid ("8008e6d2-1dec-11e2-8cad-001ec947c8cc")
    private SidedConnectionEndpointLocatorFactory sidedConnectionEndpointLocatorFactory = new SidedConnectionEndpointLocatorFactory();

    /**
     * Get the singleton.
     * 
     * @return The singleton.
     */
    @objid ("8008e6d3-1dec-11e2-8cad-001ec947c8cc")
    public static LocatorFactory getInstance() {
        return LocatorFactory.instance;
    }

    /**
     * Create a {@link SidedConnectionEndpointLocator} from the given parameters.
     * <p>
     * Compute a new uDistance and vDistance depending on the move delta and the edge object figure.
     * 
     * @param conn The connection figure
     * @param extension The extension figure to move
     * @param moveDelta the current move delta
     * @param mouseLocation the mouse location
     * @return The locator for the new position.
     */
    @objid ("8008e6d8-1dec-11e2-8cad-001ec947c8cc")
    public SidedConnectionEndpointLocator getLocator(Connection conn, IFigure extension, Dimension moveDelta, Point mouseLocation) {
        return this.sidedConnectionEndpointLocatorFactory.getLocator(conn, extension, moveDelta, mouseLocation);
    }

    /**
     * Create a figure Locator from the given locator model.
     * 
     * @param connection A connection figure.
     * @param layoutContraint a locator model.
     * @return a Locator.
     */
    @objid ("8008e6e9-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings ("deprecation")
    public Locator getLocator(Connection connection, IGmLocator layoutContraint) {
        if (layoutContraint == null) {
            return null;
        } else if (layoutContraint instanceof GmFractionalConnectionLocator) {
            return getLocator(connection, (GmFractionalConnectionLocator) layoutContraint);
        } else if (layoutContraint instanceof GmConnectionLocator) {
            return getLocator(connection, (GmConnectionLocator) layoutContraint);
        } else if (layoutContraint instanceof GmConnectionEndpoinLocator) {
            return getLocator(connection, (GmConnectionEndpoinLocator) layoutContraint);
        } else {
            throw new IllegalArgumentException(layoutContraint.toString() + " not supported.");
        }
    }

    /**
     * Create a ConnectionLocator from a GmFractionalConnectionLocator.
     * 
     * @param conn The Connection the locator must be relative to.
     * @param gmLoc The locator model.
     * @return The created Locator
     */
    @objid ("8008e6f3-1dec-11e2-8cad-001ec947c8cc")
    private Locator getLocator(final Connection conn, final GmFractionalConnectionLocator gmLoc) {
        final FractionalConnectionLocator ret;
        if (!gmLoc.isUseObsoleteLocator()) {
            ret = new FractionalConnectionLocator(conn, gmLoc.getFraction(), gmLoc.isTowardTarget());
        } else {
            // Special migration case: use an obsolete locator for GMs created before Modelio 3.8.1
            ret = new ObsoleteFractionalConnectionLocator(conn, gmLoc.getFraction(), gmLoc.isTowardTarget());
        }
        ret.setUDistance(gmLoc.getUDistance());
        ret.setVDistance(gmLoc.getVDistance());
        ret.setWidthConstraint(gmLoc.getWidthConstraint());
        ret.setHeightConstraint(gmLoc.getHeightConstraint());
        return ret;
    }

    /**
     * Create a ConnectionEndpointLocator from a GmConnectionEndpoinLocator.
     * 
     * @param conn The Connection the locator must be relative to.
     * @param gmLoc The locator model.
     * @return The created ConnectionEndpointLocator
     */
    @objid ("8008e700-1dec-11e2-8cad-001ec947c8cc")
    private SidedConnectionEndpointLocator getLocator(Connection conn, GmConnectionEndpoinLocator gmLoc) {
        final SidedConnectionEndpointLocator ret = new SidedConnectionEndpointLocator(conn, gmLoc.isEnd());
        ret.setUDistance(gmLoc.getUDistance());
        ret.setVDistance(gmLoc.getVDistance());
        ret.setWidthConstraint(gmLoc.getWidthConstraint());
        ret.setHeightConstraint(gmLoc.getHeightConstraint());
        return ret;
    }

    /**
     * Create a ConnectionLocator from a GmConnectionLocator.
     * 
     * @param conn The Connection the locator must be relative to.
     * @param gmLoc The locator model.
     * @return The created ConnectionLocator
     */
    @objid ("8008e709-1dec-11e2-8cad-001ec947c8cc")
    @Deprecated
    private ConnectionLocator getLocator(Connection conn, GmConnectionLocator gmLoc) {
        final ConnectionLocator ret = new ConnectionLocator(conn, gmLoc.getAlignment());
        ret.setGap(gmLoc.getGap());
        ret.setRelativePosition(gmLoc.getRelativePosition());
        return ret;
    }

}
