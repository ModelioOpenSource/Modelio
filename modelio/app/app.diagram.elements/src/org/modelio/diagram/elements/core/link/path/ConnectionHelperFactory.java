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
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Factory to create connection helper.
 * 
 * @author cmarin
 */
@objid ("80448198-1dec-11e2-8cad-001ec947c8cc")
public class ConnectionHelperFactory implements IConnectionHelperFactory {
    @objid ("8046e3f1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IConnectionHelper convert(final IConnectionHelper toConvert, final ConnectionRouterId mode, final Connection connection) {
        // TODO: write more useful conversion algorithms.
        switch (mode) {
        case DIRECT:
            return new DirectConnectionHelper();
        case BENDPOINT:
            return new ObliqueConnectionHelper(new ArrayList<Point>(0), connection);
        case ORTHOGONAL:
            return new OrthoConnectionHelper(new ArrayList<Point>(0), connection);
        
        default:
            throw new IllegalArgumentException(mode + " is unknown");
        }
        
    }

    @objid ("8044819a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IConnectionHelper createFromRawData(final RawPathData rawData, final Connection connection) {
        switch (rawData.getRoutingMode()) {
        case DIRECT:
            return new DirectConnectionHelper();
        case BENDPOINT:
            return new ObliqueConnectionHelper(rawData, connection);
        case ORTHOGONAL: {
            OrthoConnectionHelper orthoConnectionHelper = new OrthoConnectionHelper(connection);
            orthoConnectionHelper.updateFrom(rawData);
            return orthoConnectionHelper;
        }
        default:
            throw new IllegalArgumentException(rawData.getRoutingMode() + " is unknown");
        }
        
    }

    @objid ("99268caa-97d4-43b1-afc6-6e8fb189fb1b")
    @Override
    public IConnectionHelper createFromRawData(CreateBendedConnectionRequest request, Connection connection) {
        RawPathData rawData = request.getData();
        return createFromRawData(rawData, connection);
    }

    @objid ("43794ba0-a248-4959-8b34-20041126ff7c")
    @Override
    @SuppressWarnings ("unchecked")
    public IConnectionHelper createFromSerializedData(final ConnectionRouterId router, final ConnectionEditPart connectionEp, final Connection connection) {
        IGmLinkObject model = (IGmLinkObject) connectionEp.getModel();
        
        Object serializedData = model.getPath().getPathData();
        if (serializedData instanceof RakeConstraint) {
            return new RakeConnectionHelper((RakeConstraint) serializedData, connection);
        }
        
        switch (router) {
        case DIRECT:
            return new DirectConnectionHelper();
        case BENDPOINT:
            return new ObliqueConnectionHelper((List<Point>) serializedData, connection);
        case ORTHOGONAL:
            return new OrthoConnectionHelper((List<Point>) serializedData, connection);
        
        default:
            throw new IllegalArgumentException(router + " is unknown");
        }
        
    }

    @objid ("8b1b9927-9968-4845-8d4d-752905d35049")
    @Override
    public IConnectionHelper getUpdatedConnectionHelper(final CreateBendedConnectionRequest req, final Connection connection) {
        // Look for the IConnectionHelper cached in the request
        IConnectionHelper connHelper = (IConnectionHelper) req.getExtendedData().get(IConnectionHelper.class);
        
        if (connHelper == null || !connHelper.isUsable() || !isCompatible(req, connHelper)) {
            // Create a new helper
            connHelper = createFromRawData(req, connection);
            req.getExtendedData().put(IConnectionHelper.class, connHelper);
        }
        connHelper.updateFrom(req.getData());
        return connHelper;
    }

    @objid ("3ca59866-3245-4f90-b7be-7627f1000b0d")
    private static boolean isCompatible(CreateBendedConnectionRequest request, IConnectionHelper connHelper) {
        return (connHelper.getRoutingMode() == request.getData().getRoutingMode()) ;
    }

}
