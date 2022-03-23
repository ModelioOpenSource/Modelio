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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.modelio.diagram.elements.core.figures.routers.RakeConstraint;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Another factory to create connection helper.
 */
@objid ("4557c7f0-2e96-49c3-9b3e-f38a69386bfa")
public final class AutoConnectionHelperFactory extends ConnectionHelperFactory {
    @objid ("23ac712d-41c5-4a99-ae06-a9766fccebcd")
    @Override
    public IConnectionHelper convert(final IConnectionHelper toConvert, final ConnectionRouterId mode, final Connection connection) {
        if (mode == ConnectionRouterId.ORTHOGONAL) {
            return new AutoOrthoConnectionHelper(new ArrayList<>(0), connection);
        } else {
            return super.convert(toConvert, mode, connection);
        }
        
    }

    @objid ("c0aee9f6-9231-4496-b9b0-18e5f48a2908")
    @Override
    public IConnectionHelper createFromRawData(final RawPathData rawData, final Connection connection) {
        ConnectionRouterId routingMode = rawData.getRoutingMode();
        if (routingMode == ConnectionRouterId.ORTHOGONAL) {
            AutoOrthoConnectionHelper orthoConnectionHelper = AutoOrthoConnectionHelper.empty(connection);
            orthoConnectionHelper.updateFrom(rawData);
            return orthoConnectionHelper;
        } else {
            return super.createFromRawData(rawData, connection);
        }
        
    }

    /**
     * Create a new connection helper from the graphic model serialized data.
     * @param router The connection router to use
     * @param connectionEp the edit part whose model contains the serialized data.
     * @return a connection helper.
     */
    @objid ("346283f4-d98c-474b-8480-dc0c4c40cf5e")
    @Override
    @SuppressWarnings ("unchecked")
    public IConnectionHelper createFromSerializedData(final ConnectionRouterId router, final ConnectionEditPart connectionEp, final Connection connection) {
        IGmLinkObject model = (IGmLinkObject) connectionEp.getModel();
        
        Object serializedData = model.getPath().getPathData();
        if (serializedData instanceof RakeConstraint) {
            return new RakeConnectionHelper((RakeConstraint) serializedData, connection);
        }
        
        if (router == ConnectionRouterId.ORTHOGONAL) {
            return new AutoOrthoConnectionHelper((List<Point>) serializedData, connection);
        } else {
            return super.createFromSerializedData(router, connectionEp, connection);
        }
        
    }

}
