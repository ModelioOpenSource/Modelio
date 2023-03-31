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
@objid ("77b8f08e-e453-4a3e-9368-7daebfd5c4a7")
public class SimpleLinkNodeAnchorProvider implements INodeAnchorProvider {
    @objid ("562a3bf9-0845-4a1d-8702-d40af4a21b49")
    public static final SimpleLinkNodeAnchorProvider INSTANCE = new SimpleLinkNodeAnchorProvider();

    @objid ("bc01b2af-6e62-4635-8bba-4e3ae7a2b93c")
    @Override
    public Object createAnchorModel(ConnectionAnchor anchor) {
        return null;
    }

    @objid ("fb7b9b9c-957e-4727-bcd2-1db006a142fc")
    public ConnectionAnchor createFromModel(IFigure nodeFigure, GmFixedAnchor gmLinkAnchor) {
        return createAnchorFor(nodeFigure);
    }

    @objid ("602ce84b-8642-47ca-ade9-b902844387ee")
    private LinkAnchor createAnchorFor(IFigure nodeFigure) {
        return new LinkAnchor((Connection) nodeFigure, new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    @objid ("d7f2a356-fb4d-475d-b1ee-89879ba73ab1")
    public Collection<ConnectionAnchor> getAllAnchors(IFigure nodeFig, ConnectionRouterId routerId, Integer face) {
        return Collections.singletonList(createAnchorFor(nodeFig));
    }

    @objid ("e872c6b2-0354-48db-b563-e956fbc7c492")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(GraphicalEditPart nodeEditPart, ConnectionEditPart connEditpart) {
        return createAnchorFor(getConnectionFigure(nodeEditPart));
    }

    @objid ("694e1a4d-0a0e-4d73-852e-025969c88232")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(GraphicalEditPart nodeEditPart, Request request) {
        return getConnectionAnchor(nodeEditPart, request, true);
    }

    @objid ("a60bd56e-6ee9-416f-b1cc-143c8cbb77f6")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(GraphicalEditPart nodeEditPart, ConnectionEditPart connEditpart) {
        return createAnchorFor(getConnectionFigure(nodeEditPart));
    }

    @objid ("dac04c33-8c0a-4476-b685-cc38d9e7e979")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(GraphicalEditPart nodeEditPart, Request request) {
        return getConnectionAnchor(nodeEditPart, request, false);
    }

    @objid ("a169ae33-7472-4b6e-a671-7a529f5b7444")
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
    @objid ("0ce41440-3323-49c1-872b-4a4aeee66e1c")
    protected static Connection getConnectionFigure(GraphicalEditPart nodeEditPart) {
        return (Connection) nodeEditPart.getFigure();
    }

}
