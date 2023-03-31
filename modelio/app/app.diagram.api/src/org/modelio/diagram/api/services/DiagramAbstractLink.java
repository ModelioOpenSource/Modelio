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
package org.modelio.diagram.api.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramLink.ExtensionRole;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.ILinkRoute;
import org.modelio.api.modelio.diagram.ILinkRouteBuilder;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.LinkPath;
import org.modelio.diagram.api.dg.LinkRoute;
import org.modelio.diagram.api.dg.LinkRouteBuilder;
import org.modelio.diagram.api.style.StyleHandle;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.figures.anchors.RaySlidableAnchor;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmNode;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class represents a DiagramGraphic of the 'link' kind.
 */
@objid ("296cc100-bd45-4e51-ae74-bed98a8a9f8b")
public abstract class DiagramAbstractLink extends DiagramGraphic implements IDiagramLink {
    /**
     * Return the path of the current link.
     * @return The LinkPath that represent the path of the current link.
     */
    @objid ("4a911be1-494e-40f2-a488-dc184896c2c3")
    @Override
    public ILinkPath getPath() {
        final ConnectionEditPart editPart = getConnectionEditPart();
        final Connection cnx = (Connection) editPart.getFigure();
        final LinkPath linkPath = new LinkPath(cnx);
        return linkPath;
    }

    @objid ("16950134-e78b-497e-b059-aa5562c6f43a")
    @Override
    public ILinkRoute getRoute() {
        return new LinkRoute(getConnectionEditPart());
    }

    @objid ("dcf93ec5-84a9-4224-894f-6bd183fd4c21")
    @Override
    public ILinkRouteBuilder buildRoute() {
        return new LinkRouteBuilder(getConnectionEditPart());
    }

    @objid ("6e505b6d-5727-4ea4-8aee-e624e98bb45b")
    @Override
    public void setRoute(ILinkRoute linkPath) throws InvalidSourcePointException, InvalidPointsPathException, InvalidDestinationPointException {
        ((LinkRoute) linkPath).apply(getConnectionEditPart());
    }

    /**
     * Route the path of a link.
     * 
     * This method computes a path so that the link goes through the whole list of points. If the router referenced by the current Link is an orthogonal router the path will have orthogonal angles. If the router referenced by the current Link is a direct
     * router this method is equivalent to the setPath method.
     * @param points A collection of points that must be on the link path.
     */
    @objid ("ae930fec-2b07-434c-b2c3-839f120b09c6")
    @Override
    public void setPath(Collection<Point> points) {
        final LinkPath path = new LinkPath();
        path.setPoints(points);
        try {
            setPath(path);
        } catch (InvalidPointsPathException | InvalidSourcePointException | InvalidDestinationPointException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage(), e);
        }
        
    }

    /**
     * Set the path of a link.
     * 
     * This method tries to set the current link path.
     * @throws InvalidSourcePointException If the source point is invalid.
     * @throws InvalidPointsPathException If the given path is invalid with the router type associated with the current link.
     * @throws InvalidDestinationPointException If the destination point is invalid.
     */
    @objid ("8fb92ab3-249d-45a9-a0dd-c3461c867301")
    @Override
    public void setPath(ILinkPath linkPath) throws InvalidSourcePointException, InvalidPointsPathException, InvalidDestinationPointException {
        final List<Point> points = new ArrayList<>(linkPath.getPoints());
        int npoints = points.size();
        if (npoints < 2) {
            throw new InvalidPointsPathException("You must have at least a source and a destination point");
        }
        
        ILinkRoute route = new LinkRoute(getConnectionEditPart())
                .clearBendPoints()
                .setSourceAnchor(points.get(0), true)
                .setTargetAnchor(points.get(npoints - 1), true);
        
        for (int i = 1; i < npoints - 1; i++) {
            route.addBendPoint(points.get(i));
        }
        
        setRoute(route);
        
    }

    /**
     * Compute a new target anchor
     * @param connectionEditPart the connection
     * @param reqLoc a location in absolute coordinates
     * @return a new target anchor
     */
    @objid ("2cfc2e7d-8027-4295-b975-49a7010db917")
    protected ConnectionAnchor requestTargetAnchor(ConnectionEditPart connectionEditPart, Point reqLoc, boolean exact) {
        NodeEditPart targetEditPart = (NodeEditPart) connectionEditPart.getTarget();
        
        ReconnectRequest reconnectRequest = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
        reconnectRequest.setConnectionEditPart(connectionEditPart);
        reconnectRequest.setTargetEditPart(targetEditPart);
        reconnectRequest.setLocation(reqLoc);
        if (exact) {
            IFigure targetFigure = targetEditPart.getFigure();
            reconnectRequest.getExtendedData().put(CreateLinkConstants.PROP_RECONNECT_ON_SAME_FACE,
                    new RaySlidableAnchor(targetFigure, reqLoc.getDifference(targetFigure.getBounds().getLocation())));
        }
        
        ConnectionAnchor newAnchor = targetEditPart.getTargetConnectionAnchor(reconnectRequest);
        return newAnchor;
    }

    /**
     * Compute a new source anchor
     * @param connectionEditPart the connection
     * @param reqLoc a location in absolute coordinates
     * @return a new target anchor
     */
    @objid ("1795846c-ac2c-43c8-8109-7ea43aeaaa38")
    protected ConnectionAnchor requestSourceAnchor(ConnectionEditPart connectionEditPart, Point reqLoc, boolean exact) {
        NodeEditPart sourceEditPart = (NodeEditPart) connectionEditPart.getSource();
        ReconnectRequest reconnectRequest = new ReconnectRequest(RequestConstants.REQ_RECONNECT_SOURCE);
        reconnectRequest.setConnectionEditPart(connectionEditPart);
        reconnectRequest.setTargetEditPart(sourceEditPart);
        reconnectRequest.setLocation(reqLoc);
        if (exact) {
            IFigure srcFigure = sourceEditPart.getFigure();
            reconnectRequest.getExtendedData().put(CreateLinkConstants.PROP_RECONNECT_ON_SAME_FACE,
                    new RaySlidableAnchor(srcFigure, reqLoc.getDifference(srcFigure.getBounds().getLocation())));
        }
        
        ConnectionAnchor newConnectionAnchor = sourceEditPart.getSourceConnectionAnchor(reconnectRequest);
        return newConnectionAnchor;
    }

    /**
     * Modify the router referenced by the link.
     */
    @objid ("8d217f47-6b19-415e-bbd6-945027d925a0")
    @Override
    public void setRouterKind(LinkRouterKind routerKind) {
        ConnectionRouterId routerId;
        
        // Convert LinkRouterKind to ConnectionRouterId
        switch (routerKind) {
        case BENDPOINT:
            routerId = ConnectionRouterId.BENDPOINT;
            break;
        case DIRECT:
            routerId = ConnectionRouterId.DIRECT;
            break;
        case ORTHOGONAL:
            routerId = ConnectionRouterId.ORTHOGONAL;
            break;
        
        default:
            routerId = ConnectionRouterId.DIRECT;
        }
        
        // Set the router property
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.CONNECTIONROUTER);
        if (styleKey != null) {
        
            getModel().getDisplayedStyle().setProperty(styleKey, routerId);
        }
        
    }

    /**
     * Creates a diagram link.
     * @param diagramHandle The diagram manipulation class.
     */
    @objid ("97fe33c6-52b9-404c-92e0-6fb1718d4678")
    protected  DiagramAbstractLink(DiagramHandle diagramHandle) {
        super(diagramHandle);
    }

    @objid ("cef94e47-024b-4673-823d-0e4816630d85")
    @Override
    public void mask() {
        final ConnectionEditPart editPart = getConnectionEditPart();
        
        final GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
        deleteReq.setEditParts(editPart);
        
        execRequest(editPart, deleteReq);
        
    }

    @objid ("defd5bff-8069-44b1-91d6-296f633251e8")
    @Override
    public boolean isPrimarySelected() {
        final GraphicalEditPart editPart = this.diagramHandle.getEditPart(getModel());
        return editPart.getSelected() == EditPart.SELECTED_PRIMARY;
    }

    @objid ("e21626a6-7af4-4d56-bb00-a965a0e3793a")
    @Override
    public boolean isSelected() {
        final GraphicalEditPart editPart = this.diagramHandle.getEditPart(getModel());
        return editPart.getSelected() != EditPart.SELECTED_NONE;
    }

    @objid ("b2d2d523-2f4e-432d-821d-90f690df8209")
    private void setTargetLocation(final Point targetPoint) {
        final ConnectionEditPart connEditPart = getConnectionEditPart();
        
        final Point absTargetPoint = targetPoint.getCopy();
        connEditPart.getFigure().translateToAbsolute(absTargetPoint);
        
        final ReconnectRequest req = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
        req.setConnectionEditPart(connEditPart);
        req.setTargetEditPart(connEditPart.getTarget());
        req.setLocation(absTargetPoint);
        
        execRequest(connEditPart.getTarget(), req);
        
    }

    @objid ("3ef0bec5-c380-4c93-9e37-177f7bafa61c")
    private void setSourceLocation(final Point sourcePoint) {
        final ConnectionEditPart connEditPart = getConnectionEditPart();
        
        final Point absTargetPoint = sourcePoint.getCopy();
        connEditPart.getFigure().translateToAbsolute(absTargetPoint);
        
        final ReconnectRequest req = new ReconnectRequest(RequestConstants.REQ_RECONNECT_SOURCE);
        req.setConnectionEditPart(connEditPart);
        req.setTargetEditPart(connEditPart.getSource());
        req.setLocation(absTargetPoint);
        
        execRequest(connEditPart.getSource(), req);
        
    }

    /**
     * Get router referenced by the link.
     */
    @objid ("2198317d-639a-4f2e-ad4e-52aeb88c4532")
    @Override
    public LinkRouterKind getRouterKind() {
        switch (getModelPath().getRouterKind()) {
        case BENDPOINT:
            return LinkRouterKind.BENDPOINT;
        case DIRECT:
            return LinkRouterKind.DIRECT;
        
        case ORTHOGONAL:
            return LinkRouterKind.ORTHOGONAL;
        default:
            return LinkRouterKind.DIRECT;
        }
        
    }

    @objid ("30fc150a-8c01-4ab9-a299-ab530433c7b3")
    @Override
    public String getProperty(final String property) {
        final StyleKey key = resolveStyleKey(property);
        
        if (key != null) {
            return StyleKeyTypeConverter.convertToString(key, getModel().getDisplayedStyle().getProperty(key));
        }
        return null;
    }

    @objid ("633a59b1-f57e-4f29-aacb-e06ddfb5aa19")
    @Override
    public String getFont() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.FONT);
        if (styleKey == null) {
            return null;
        }
        final Font currentFont = getModel().getDisplayedStyle().getProperty(styleKey);
        return StyleKeyTypeConverter.convertToString(styleKey, currentFont);
    }

    @objid ("43b7b79f-4a1f-4eff-83d8-72dd58c33d82")
    @Override
    public String getLineColor() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINECOLOR);
        if (styleKey == null) {
            return null;
        }
        final Color color = getModel().getDisplayedStyle().getProperty(styleKey);
        return StyleKeyTypeConverter.convertToString(styleKey, color);
    }

    @objid ("29f4f4e1-fca5-4311-b9c6-44e406591f6a")
    @Override
    public int getLinePattern() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINEPATTERN);
        if (styleKey == null) {
            return 0;
        }
        final LinePattern pattern = getModel().getDisplayedStyle().getProperty(styleKey);
        
        switch (pattern) {
        case LINE_SOLID:
            return 0;
        case LINE_DASH:
            return 1;
        case LINE_DOT:
            return 2;
        case LINE_DASHDOT:
            return 3;
        case LINE_DASHDOTDOT:
            return 4;
        default:
            return 0;
        }
        
    }

    @objid ("a812bbc9-cba6-4046-a69b-3d0940a0c481")
    @Override
    public int getLineRadius() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINERADIUS);
        if (styleKey == null) {
            return 0;
        }
        return getModel().getDisplayedStyle().getProperty(styleKey);
    }

    @objid ("63feee35-4c55-4abc-a079-29fdbaab7982")
    @Override
    public int getLineWidth() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINEWIDTH);
        if (styleKey == null) {
            return 0;
        }
        return getModel().getDisplayedStyle().getProperty(styleKey);
    }

    @objid ("ea3fe941-79de-4f20-8c31-1e9e030d9fba")
    @Override
    public String getTextColor() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.TEXTCOLOR);
        if (styleKey == null) {
            return null;
        }
        final Color color = getModel().getDisplayedStyle().getProperty(styleKey);
        return StyleKeyTypeConverter.convertToString(styleKey, color);
    }

    @objid ("7702510f-e77e-449d-a295-51b6acc25106")
    @Override
    public boolean isDrawLineBridges() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.DRAWLINEBRIDGES);
        if (styleKey == null) {
            return true;
        }
        return getModel().getDisplayedStyle().getProperty(styleKey);
    }

    @objid ("eac2bdd5-1274-4cca-aae3-50195b6ccf8b")
    @Override
    public void setDrawLineBridges(final boolean value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.DRAWLINEBRIDGES);
        if (styleKey == null) {
            return;
        }
        getModel().getDisplayedStyle().setProperty(styleKey, value);
        
    }

    @objid ("969be64f-b098-439f-aa31-86ed02f357a5")
    @Override
    public void setFont(final String value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.FONT);
        if (styleKey == null) {
            return;
        }
        getModel().getDisplayedStyle()
                .setProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        
    }

    @objid ("1936eaf1-a489-4e04-93cb-e4221e2632c3")
    @Override
    public void setLineColor(final String value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINECOLOR);
        if (styleKey == null) {
            return;
        }
        
        getModel().getDisplayedStyle().setProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        
    }

    @objid ("b50b1962-c105-470a-82aa-6bf8ac4a8b96")
    @Override
    public void setLinePattern(final int value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINEPATTERN);
        if (styleKey == null) {
            return;
        }
        LinePattern pattern;
        
        switch (value) {
        case 0:
            pattern = LinePattern.LINE_SOLID;
            break;
        case 1:
            pattern = LinePattern.LINE_DASH;
            break;
        case 2:
            pattern = LinePattern.LINE_DOT;
            break;
        case 3:
            pattern = LinePattern.LINE_DASHDOT;
            break;
        case 4:
            pattern = LinePattern.LINE_DASHDOTDOT;
            break;
        default:
            pattern = LinePattern.LINE_SOLID;
        }
        
        getModel().getDisplayedStyle().setProperty(styleKey, pattern);
        
    }

    @objid ("46eb0249-7c68-4f95-a56e-176ad4318109")
    @Override
    public void setLineRadius(final int value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINERADIUS);
        if (styleKey == null) {
            return;
        }
        
        getModel().getDisplayedStyle().setProperty(styleKey, value);
        
    }

    @objid ("bd397417-551a-4bbe-bc21-ac7d423cc2df")
    @Override
    public void setLineWidth(final int value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINEWIDTH);
        if (styleKey == null) {
            return;
        }
        
        getModel().getDisplayedStyle().setProperty(styleKey, value);
        
    }

    @objid ("9bad231e-ac85-4da7-8fbe-a940a07346ce")
    @Override
    public void setTextColor(final String value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.TEXTCOLOR);
        if (styleKey == null) {
            return;
        }
        
        getModel().getDisplayedStyle().setProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        
    }

    @objid ("336d0253-12cf-4493-ac7b-c8afa543f0fa")
    @Override
    public IStyleHandle getStyle() {
        final NamedStyle style = (NamedStyle) getModel().getDisplayedStyle().getCascadedStyle();
        final StyleHandle newStyle = new StyleHandle(style);
        return newStyle;
    }

    @objid ("d7e771b4-5d16-4440-b86e-03955e7fa787")
    @Override
    public void setStyle(final IStyleHandle style) {
        final NamedStyle namedStyle = DiagramStyles.getStyleManager().getStyle(style.getName());
        getModel().getDisplayedStyle().setCascadedStyle(namedStyle);
        
    }

    @objid ("886b1100-8461-4569-bd2f-10e72daad11e")
    @Override
    public List<String> getLocalPropertyNames() {
        final List<String> ret = new ArrayList<>();
        for (final StyleKey key : getModel().getDisplayedStyle().getLocalKeys()) {
            ret.add(key.getId());
        }
        return ret;
    }

    /**
     * Get a StyleKey from its name, or its MetaKey name.
     */
    @objid ("853a5a8c-5ba8-44dc-8395-3551290129b0")
    private StyleKey resolveStyleKey(final String name) {
        // Look for a property using this StyleKey
        StyleKey foundKey = StyleKey.getInstance(name);
        if (foundKey == null) {
            // No StyleKey found, look for a MetaKey and then a StyleKey
            final MetaKey meta = MetaKey.getInstance(name);
            if (meta != null) {
                foundKey = getModel().getStyleKey(meta);
            }
        }
        return foundKey;
    }

    @objid ("87c7133a-935f-4342-9ea9-f80a992201b0")
    @Override
    public void setProperty(final String property, final String stringValue) {
        final StyleKey key = resolveStyleKey(property);
        
        if (key != null) {
            getModel().getDisplayedStyle()
                    .setProperty(key, StyleKeyTypeConverter.convertFromString(key, stringValue));
        }
        
    }

    /**
     * Get the edited connection edit part.
     * @return the edit connection edit part.
     */
    @objid ("c5ac5668-0654-4f75-a76a-b5ee5d5aa2bd")
    private ConnectionEditPart getConnectionEditPart() {
        return (ConnectionEditPart) this.diagramHandle.getEditPart(getModel());
    }

    /**
     * Get and execute if possible the command produced by the given request on the given edit part.
     * @param editPart an edit part.
     * @param req a request to execute.
     */
    @objid ("46b54f7f-6fab-4ae9-9e1b-1a76e2c4185d")
    private void execRequest(final EditPart editPart, final Request req) {
        final Command cmd = editPart.getCommand(req);
        if (cmd != null && cmd.canExecute()) {
            editPart.getViewer().getEditDomain().getCommandStack().execute(cmd);
            if (editPart instanceof GraphicalEditPart) {
                ((GraphicalEditPart) editPart).getFigure().getUpdateManager().performValidation();
            }
        }
        
    }

    @objid ("002e02c9-e137-417b-9938-25fdcd9a48cd")
    @Override
    public void resetLocalProperties() {
        getModel().getDisplayedStyle().reset();
    }

    @objid ("1b750297-6bdf-4224-8954-f2624289a037")
    @Override
    public void normalizeLocalProperties() {
        getModel().getDisplayedStyle().normalize();
    }

    @objid ("797fb4d5-85a5-4d18-9e44-275a2e54be2e")
    @Override
    public MObject getHyperLink() {
        return null;
    }

    @objid ("c4d50286-a7d6-42d3-a398-744532444018")
    @Override
    public void setHyperLink(MObject obj) {
        // ignore
    }

    @objid ("57ae09dc-65f8-4933-98ca-ae690ecf6d77")
    protected abstract IGmPath getModelPath();

    @objid ("12374dd0-4266-4955-a39c-3a25a6c35324")
    protected abstract Collection<IGmNode> getGmNodes(ExtensionRole role);

    @objid ("f07f40be-2ea3-4620-a24b-00064e260d17")
    @Override
    public final Collection<IDiagramNode> getExtensions(ExtensionRole role) {
        Collection<IGmNode> n = getGmNodes(role);
        if (n.isEmpty()) {
            return Collections.emptyList();
        } else {
            return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, n);
        }
        
    }

    @objid ("844de005-a991-4f3a-a10e-4c4d75032977")
    @Override
    public final IDiagramNode getFirstExtension(ExtensionRole role) {
        Collection<IGmNode> n = getGmNodes(role);
        if (n.isEmpty()) {
            return null;
        } else {
            return DGFactory.getInstance().getDiagramNode(this.diagramHandle, (GmNodeModel) n.iterator().next());
        }
        
    }

    @objid ("88685879-8962-4011-a1ec-fcd5c4d1d0bb")
    private IFigure resolveEmbeddedDiagramFigure() {
        final ConnectionEditPart editPart = getConnectionEditPart();
        GmAbstractDiagram ownerDiagram = (GmAbstractDiagram) ((GmModel) editPart.getModel()).getDiagram();
        GmModel embeddedDiagram = ownerDiagram.getParent();
        GraphicalEditPart embeddedDiagramEditPart = this.diagramHandle.getEditPart(embeddedDiagram);
        if (embeddedDiagramEditPart != null) {
            return embeddedDiagramEditPart.getFigure();
        } else {
            return null;
        }
        
    }

}
