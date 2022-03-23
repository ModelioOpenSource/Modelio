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
package org.modelio.diagram.elements.core.link.anchors;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.modelio.diagram.elements.core.figures.anchors.LinkAnchor;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Basic node anchor provider for connection edit parts
 * <p>
 * It only creates anchors at the middle of the connection.
 * @author cma
 * @since 5.1.0
 */
@objid ("dd5e9896-1c9e-454b-82c0-ec9d7de459ad")
public class SimpleLinkNodeAnchorProvider implements INodeAnchorProvider, IFixedConnectionAnchorFactory {
    @objid ("d1699ee1-8e35-49a0-8e4d-141623d60bec")
    public static final SimpleLinkNodeAnchorProvider INSTANCE = new SimpleLinkNodeAnchorProvider();

    @objid ("9413f5e0-674c-4fd1-8818-e08dcf455d25")
    @Override
    public Object createAnchorModel(ConnectionAnchor anchor) {
        return null;
    }

    @objid ("40ab1293-02bb-4409-8491-7e5b45194df0")
    @Override
    public ConnectionAnchor createFromModel(IFigure nodeFigure, GmFixedAnchor gmLinkAnchor) {
        return createAnchorFor(nodeFigure);
    }

    @objid ("e77ac0f9-cab8-400d-a45f-16a7fb07de48")
    private LinkAnchor createAnchorFor(IFigure nodeFigure) {
        return new LinkAnchor((Connection) nodeFigure, new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    @objid ("513be3ef-a76e-4656-93a9-9dbd9f44271e")
    @Override
    public Collection<ConnectionAnchor> getAllAnchors(IFigure nodeFig, ConnectionRouterId routerId, Integer face) {
        return Collections.singletonList(createAnchorFor(nodeFig));
    }

    @objid ("3196e69f-1f25-418c-9a61-f440f62c6be9")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(GraphicalEditPart nodeEditPart, ConnectionEditPart connEditpart) {
        return createAnchorFor(getConnectionFigure(nodeEditPart));
    }

    @objid ("3d50bf5c-3a46-4ec7-929d-2709c06804a3")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(GraphicalEditPart nodeEditPart, Request request) {
        return getConnectionAnchor(nodeEditPart, request, true);
    }

    @objid ("5b174961-283a-4783-b595-7784cc0da396")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(GraphicalEditPart nodeEditPart, ConnectionEditPart connEditpart) {
        return createAnchorFor(getConnectionFigure(nodeEditPart));
    }

    @objid ("64894842-ac7e-4061-846f-347e682294d0")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(GraphicalEditPart nodeEditPart, Request request) {
        return getConnectionAnchor(nodeEditPart, request, false);
    }

    @objid ("6d2d7762-8f80-4083-8a02-0a27faa6bd41")
    protected ConnectionAnchor getConnectionAnchor(GraphicalEditPart nodeEditPart, Request request, boolean source) {
        IFigure nodeFigure = (IFigure) request.getExtendedData().get(CreateLinkConstants.PROP_RECONNECT_ON_FIGURE);
        if (nodeFigure == null) {
            nodeFigure = nodeEditPart.getFigure();
        }
        return createAnchorFor(nodeFigure);
    }

    /**
     * Convenience method for casting this GraphicalEditPart's Figure to a
     * {@link Connection}
     * @return the Figure as a Connection
     */
    @objid ("3ada75b0-b677-4a27-a0b2-54106e9f07c3")
    protected static Connection getConnectionFigure(GraphicalEditPart nodeEditPart) {
        return (Connection) nodeEditPart.getFigure();
    }

}
