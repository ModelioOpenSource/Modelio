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
package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.link.anchors.DefaultAnchorRefResolver;
import org.modelio.diagram.elements.core.link.anchors.IAnchorRefResolver;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specific command for unmasking a link in a diagram.
 * 
 * @author fpoyer
 */
@objid ("7e228746-1dec-11e2-8cad-001ec947c8cc")
public class UnmaskLinkCommand extends Command {
    @objid ("7e22874d-1dec-11e2-8cad-001ec947c8cc")
    private AbstractDiagramEditPart host;

    @objid ("7e22874e-1dec-11e2-8cad-001ec947c8cc")
    private MObject linkElement;

    @objid ("7e24e96f-1dec-11e2-8cad-001ec947c8cc")
    private MObject fromElement;

    @objid ("7e24e970-1dec-11e2-8cad-001ec947c8cc")
    private MObject toElement;

    @objid ("7e24e971-1dec-11e2-8cad-001ec947c8cc")
    private StyleKey routerKey;

    @objid ("7a104643-df7c-4e36-a08a-b88030a3b219")
    private Point dropLocation;

    @objid ("aeabf020-df90-4e25-92ac-f0aad0611a1f")
    private IAnchorRefResolver sourceAnchorResolver;

    @objid ("7c7770dd-47d8-4d7d-a1a0-f72e3607cc92")
    private IAnchorRefResolver targetAnchorResolver;

    @objid ("28514d22-34bf-4bbe-b8a0-821c6bb68288")
    public IAnchorRefResolver getSourceAnchorResolver() {
        return this.sourceAnchorResolver;
    }

    @objid ("c719cc0d-1fb5-49f7-8bb8-8422688879b3")
    public void setSourceAnchorResolver(IAnchorRefResolver sourceAnchorResolver) {
        this.sourceAnchorResolver = sourceAnchorResolver;
    }

    @objid ("0cd33acf-c3f8-4438-8f4e-6f3247aab776")
    public IAnchorRefResolver getTargetAnchorResolver() {
        return this.targetAnchorResolver;
    }

    @objid ("bf040293-2275-4d11-a0e2-2309981fc3a8")
    public void setTargetAnchorResolver(IAnchorRefResolver targetAnchorResolver) {
        this.targetAnchorResolver = targetAnchorResolver;
    }

    /**
     * C'tor.
     * @param link the link to unmask.
     * @param host the edit part that was asked to handle the unmasking.
     * @param dropLocation the drop location.
     * @param sourceAnchorResolver a way to resolve the default source anchor for the link to unmask.
     * @param targetAnchorResolver a way to resolve the default target anchor for the link to unmask.
     * @since 5.1
     */
    @objid ("7e24e972-1dec-11e2-8cad-001ec947c8cc")
    public  UnmaskLinkCommand(IGmLink link, AbstractDiagramEditPart host, Point dropLocation, IAnchorRefResolver sourceAnchorResolver, IAnchorRefResolver targetAnchorResolver) {
        this.linkElement = link.getRepresentedElement();
        this.fromElement = link.getFromElement();
        this.toElement = link.getToElement();
        link.delete();
        this.dropLocation = dropLocation;
        this.host = host;
        this.routerKey = link.getStyleKey(MetaKey.CONNECTIONROUTER);
        this.sourceAnchorResolver = sourceAnchorResolver;
        this.targetAnchorResolver = targetAnchorResolver;
        
    }

    /**
     * C'tor using figure centers as default reference for anchors.
     * @param link the link to unmask.
     * @param host the edit part that was asked to handle the unmasking.
     * @param dropLocation the drop location.
     */
    @objid ("bd09b5e6-11ce-46e2-9339-d0c3158db336")
    public  UnmaskLinkCommand(IGmLink link, AbstractDiagramEditPart host, Point dropLocation) {
        this(link, host, dropLocation, DefaultAnchorRefResolver.get(), DefaultAnchorRefResolver.get());
    }

    @objid ("7e24e97a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        if (this.fromElement == null || this.toElement == null) {
            return false;
        }
        
        // the diagram must be modifiable
        return MTools.getAuthTool().canModify(((IGmDiagram) this.host.getModel()).getRelatedElement());
    }

    @objid ("7e24e97f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        // Simulate creation of the link:
        // 1 - start creation of a connection
        final CreateBendedConnectionRequest req = new CreateBendedConnectionRequest();
        req.setLocation(this.dropLocation);
        req.setSize(new Dimension(-1, -1));
        req.setFactory(new ModelioLinkCreationContext(this.linkElement));
        req.setType(RequestConstants.REQ_CONNECTION_START);
        if (this.routerKey != null) {
            req.getData()
                    .setRoutingMode((ConnectionRouterId) ((IGmDiagram) this.host.getModel()).getDisplayedStyle()
                            .getProperty(this.routerKey));
        } else {
            req.getData().setRoutingMode(ConnectionRouterId.ORTHOGONAL);
        }
        
        // Look for edit part of from element... If none found, unmask it.
        AbstractGraphicalEditPart sourceEditPart = getEditPartFor(this.fromElement, req);
        boolean sourceMissing = false;
        if (sourceEditPart == null) {
            sourceMissing = true;
            unmaskElement(this.fromElement, this.dropLocation.getTranslated(-50, 0));
            sourceEditPart = getEditPartFor(this.fromElement, req);
            if (sourceEditPart == null) {
                return;
            }
        }
        
        req.setSourceEditPart(sourceEditPart);
        req.getData().setSrcPoint(getAbsoluteFigureCenter(sourceEditPart));
        // Set a temp location value, it will be replaced once the targer is found
        req.setLocation(getAbsoluteFigureCenter(sourceEditPart));
        
        req.setStartCommand(sourceEditPart.getCommand(req));
        
        // 2 - end the creation of the connection
        req.setType(RequestConstants.REQ_CONNECTION_END);
        
        // Look for edit part of to element... if none found, unmask it.
        AbstractGraphicalEditPart targetEditPart = getEditPartFor(this.toElement, req);
        if (targetEditPart == null) {
            Point loc = this.dropLocation;
            if (sourceMissing) {
                loc = this.dropLocation.getTranslated(50, 0);
            }
            unmaskElement(this.toElement, loc);
            targetEditPart = getEditPartFor(this.toElement, req);
            if (targetEditPart == null) {
                return;
            }
        }
        req.setTargetEditPart(targetEditPart);
        req.getData().setSrcPoint(this.sourceAnchorResolver.resolveAnchorRef(sourceEditPart, targetEditPart, this.linkElement));
        req.setLocation(this.targetAnchorResolver.resolveAnchorRef(sourceEditPart, targetEditPart, this.linkElement));
        
        Command connectionCreationCommand = targetEditPart.getCommand(req);
        if (connectionCreationCommand != null && connectionCreationCommand.canExecute()) {
            connectionCreationCommand.execute();
        }
        
    }

    @objid ("7e24e982-1dec-11e2-8cad-001ec947c8cc")
    private AbstractGraphicalEditPart getEditPartFor(MObject element, CreateConnectionRequest req) {
        List<AbstractGraphicalEditPart> targetEditParts = new ArrayList<>();
        // Search all gm related the element
        Collection<GmModel> models = ((IGmDiagram) getHost().getModel()).getAllGMRelatedTo(new MRef(element));
        // This boolean will be used to note that the searched End was found
        // unmasked at least once.
        for (GmModel model : models) {
            // For each gm, search the corresponding edit part
            EditPart editPart = (EditPart) getHost().getViewer().getEditPartRegistry().get(model);
            if (editPart != null) {
                // See if this edit part accepts the reconnection request
                EditPart targetEditPart = editPart.getTargetEditPart(req);
                if (targetEditPart instanceof AbstractGraphicalEditPart) {
                    targetEditParts.add((AbstractGraphicalEditPart) targetEditPart);
                }
            }
        }
        
        if (targetEditParts.size() > 0) {
            // Find if an appropriate edit part exists at the request's location
            EditPart pointedEditPart = getHost().getViewer().findObjectAt(this.dropLocation);
            while (pointedEditPart != null) {
                for (EditPart targetEditPart : targetEditParts) {
                    if (targetEditPart.equals(pointedEditPart) && targetEditPart instanceof AbstractGraphicalEditPart) {
                        return (AbstractGraphicalEditPart) targetEditPart;
                    }
                }
                pointedEditPart = pointedEditPart.getParent();
            }
        
            // No valid edit part found, return the first one
            return targetEditParts.get(0);
        } else {
            // For AssociationEnd and LinkEnd, the link itself is unmasked : try return it instead.
            if (element instanceof AssociationEnd) {
                return getEditPartFor(((AssociationEnd) element).getAssociation(), req);
            } else if (element instanceof LinkEnd) {
                return getEditPartFor(((LinkEnd) element).getLink(), req);
            }
        }
        return null;
    }

    @objid ("7e24e98c-1dec-11e2-8cad-001ec947c8cc")
    private AbstractDiagramEditPart getHost() {
        return this.host;
    }

    /**
     * @param element the element to unmask
     * @param location the drop location
     */
    @objid ("7e24e990-1dec-11e2-8cad-001ec947c8cc")
    private void unmaskElement(MObject element, Point location) {
        ModelElementDropRequest dropRequest = new ModelElementDropRequest();
        dropRequest.setDroppedElements(new MObject[] { element });
        dropRequest.setLocation(location);
        EditPart targetEditPart = getHost().getTargetEditPart(dropRequest);
        if (targetEditPart != null) {
            Command command = targetEditPart.getCommand(dropRequest);
            if (command != null && command.canExecute()) {
                command.execute();
            }
        }
        
    }

    /**
     * Get the center of te figure bounds in absolute coordinates.
     * @param editPart a figure edit part
     * @return the center of the figure.
     */
    @objid ("7e24e997-1dec-11e2-8cad-001ec947c8cc")
    protected static final Point getAbsoluteFigureCenter(final AbstractGraphicalEditPart editPart) {
        IFigure fig = editPart.getFigure();
        fig.getUpdateManager().performValidation();
        Point ret = fig.getBounds().getCenter();
        fig.translateToAbsolute(ret);
        return ret;
    }

}
