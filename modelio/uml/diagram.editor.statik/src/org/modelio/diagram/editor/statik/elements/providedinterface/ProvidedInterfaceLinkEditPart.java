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

package org.modelio.diagram.editor.statik.elements.providedinterface;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.editor.statik.elements.requiredinterface.ConnectReqToProvEditPolicy;
import org.modelio.diagram.editor.statik.elements.requiredinterface.GmLollipopConnection;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidEditPart;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmProvidedInterfaceLink} edit part.
 */
@objid ("36577147-55b7-11e2-877f-002564c97630")
public class ProvidedInterfaceLinkEditPart extends LinkToVoidEditPart {
    @objid ("3657714b-55b7-11e2-877f-002564c97630")
    private static final int LOLLIPOP_DIAM = GmProvidedInterfaceLink.LOLLIPOP_DIAM;

    @objid ("3657714d-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        LinkFigure conn = (LinkFigure) super.createFigure();
        
        addTargetDecoration(conn);
        
        // Set style dependent properties
        refreshFromStyle(conn, getModelStyle());
        return conn;
    }

    @objid ("36577152-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.NODE_ROLE, new ConnectReqToProvEditPolicy());
    }

    /**
     * When creating a required interface, return the target anchor of the provided interface link.
     */
    @objid ("36577155-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        if (request instanceof CreateConnectionRequest) {
            final Object newObject = ((CreateConnectionRequest) request).getNewObject();
            if (newObject instanceof ModelioCreationContext) {
                ModelioCreationContext ctx = (ModelioCreationContext) newObject;
                if (ctx.getJavaClass() == RequiredInterface.class) {
                    final LinkFigure fig = (LinkFigure) getFigure();
                    return fig.getTargetAnchor();
                }
            } else if (newObject instanceof ModelioLinkCreationContext) {
                ModelioLinkCreationContext ctx = (ModelioLinkCreationContext) newObject;
                if (ctx.getJavaClass() == RequiredInterface.class) {
                    final LinkFigure fig = (LinkFigure) getFigure();
                    return fig.getTargetAnchor();
                }
            }
        } else if (request instanceof ReconnectRequest) {
            Object model = ((ReconnectRequest) request).getConnectionEditPart().getModel();
            if (model instanceof GmModel) {
                MObject el = ((IGmModelRelated) model).getRelatedElement();
                if (el instanceof RequiredInterface) {
                    final LinkFigure fig = (LinkFigure) getFigure();
                    return fig.getTargetAnchor();
                }
            }
        }
        return super.getTargetConnectionAnchor(request);
    }

    /**
     * @param conn
     */
    @objid ("3657715d-55b7-11e2-877f-002564c97630")
    private void addTargetDecoration(final LinkFigure conn) {
        final CircleDeco dec = new CircleDeco();
        
        conn.setTargetDecoration(dec);
        
        // Set style independent properties
        dec.setOpaque(true);
        dec.setSize(LOLLIPOP_DIAM, LOLLIPOP_DIAM);
        
        // Set style dependent properties
        refreshFromStyle(conn, getModelStyle());
    }

    @objid ("3658f7ba-55b7-11e2-877f-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        super.propertyChange(evt);
        
        // Add or remove the decoration when the target change, depending on the target
        if (evt.getPropertyName().equals(GmLink.PROP_TARGET_GM)) {
            refreshDecoration();
        }
    }

    @objid ("3658f7bf-55b7-11e2-877f-002564c97630")
    @Override
    public void refresh() {
        super.refresh();
        refreshDecoration();
    }

    /**
     * Refresh the connection target decoration.
     * <p>
     * Show a target decoration only if the provided interface is not connected to a lollipop.
     */
    @objid ("3658f7c2-55b7-11e2-877f-002564c97630")
    private void refreshDecoration() {
        final LinkFigure conn = (LinkFigure) getFigure();
        
        if (getTarget() != null && getTarget().getModel() instanceof GmLollipopConnection) {
            // Remove the lollipop decoration if connected to a GmLollipop
            conn.setTargetDecoration(null);
        } else {
            // Add the lollipop decoration if connected to nothing
            if (conn.getTargetDecoration() == null) {
                addTargetDecoration(conn);
            }
        }
    }

    @objid ("3658f7c5-55b7-11e2-877f-002564c97630")
    private static class CircleDeco extends EllipseFigure implements RotatableDecoration {
        @objid ("3658f7c9-55b7-11e2-877f-002564c97630")
        public CircleDeco() {
        }

        @objid ("3658f7cb-55b7-11e2-877f-002564c97630")
        @Override
        public void setReferencePoint(final Point p) {
            // Do nothing
        }

        @objid ("3658f7d0-55b7-11e2-877f-002564c97630")
        @Override
        public void setLocation(final Point p) {
            Dimension dim = getBounds().getSize().scale(0.5);
            super.setLocation(new Point(p.x - dim.width, p.y - dim.height));
        }

    }

}
