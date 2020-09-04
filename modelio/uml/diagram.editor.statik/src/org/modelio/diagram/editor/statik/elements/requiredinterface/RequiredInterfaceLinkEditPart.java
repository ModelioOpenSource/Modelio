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

package org.modelio.diagram.editor.statik.elements.requiredinterface;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.editor.statik.elements.providedinterface.GmProvidedInterfaceLink;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidEditPart;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.RoundedLinkFigure;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * {@link GmRequiredInterfaceLink} edit part.
 */
@objid ("367f1d8e-55b7-11e2-877f-002564c97630")
public class RequiredInterfaceLinkEditPart extends LinkToVoidEditPart {
    /**
     * Diameter of the required interface lollipop.
     */
    @objid ("367f1d92-55b7-11e2-877f-002564c97630")
    private static final int REQUIRED_DIAM = GmProvidedInterfaceLink.LOLLIPOP_DIAM + 2;

    @objid ("367f1d95-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final RoundedLinkFigure conn = new RequiredInterfaceFigure();
        
        // Set style dependent properties
        refreshFromStyle(conn, getModelStyle());
        return conn;
    }

    @objid ("367f1d9a-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        if (request instanceof CreateConnectionRequest) {
        
            ModelioCreationContext ctx = (ModelioCreationContext) ((CreateConnectionRequest) request).getNewObject();
            if (ctx.getJavaClass() == ProvidedInterface.class) {
                final LinkFigure fig = (LinkFigure) getFigure();
                return new LastConnectionPointAnchor(fig);
            }
        }
        return super.getTargetConnectionAnchor(request);
    }

    @objid ("367f1da1-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        if (request instanceof CreateConnectionRequest) {
            final Object newObject = ((CreateConnectionRequest) request).getNewObject();
            if (newObject instanceof ModelioCreationContext) {
                ModelioCreationContext ctx = (ModelioCreationContext) newObject;
                if (ctx.getJavaClass() == ProvidedInterface.class) {
                    final LinkFigure fig = (LinkFigure) getFigure();
                    return new LastConnectionPointAnchor(fig);
                }
            } else if (newObject instanceof ModelioLinkCreationContext) {
                ModelioLinkCreationContext ctx = (ModelioLinkCreationContext) newObject;
                if (ctx.getJavaClass() == ProvidedInterface.class) {
                    final LinkFigure fig = (LinkFigure) getFigure();
                    return new LastConnectionPointAnchor(fig);
                }
            }
        } else if (request instanceof ReconnectRequest) {
            Object model = ((ReconnectRequest) request).getConnectionEditPart().getModel();
            if (model instanceof GmModel) {
                MObject el = ((IGmModelRelated) model).getRelatedElement();
                if (el instanceof ProvidedInterface) {
                    final LinkFigure fig = (LinkFigure) getFigure();
                    return new LastConnectionPointAnchor(fig);
                }
            }
        }
        return super.getTargetConnectionAnchor(request);
    }

    @objid ("3680a3fb-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Allow connection of provided interfaces to this required interface.
        installEditPolicy(EditPolicy.NODE_ROLE, new ConnectProvToReqEditPolicy());
    }

    /**
     * Required interface decoration.
     * <p>
     * Draws an half circle open on the target side.
     * 
     * @author cmarin
     */
    @objid ("3680a3fe-55b7-11e2-877f-002564c97630")
    public static class RequiredInterfaceDeco extends GradientFigure implements RotatableDecoration {
        @objid ("3680a403-55b7-11e2-877f-002564c97630")
        private double rotation;

        @objid ("78d6caa1-a465-4f5a-b697-1970075bd86f")
        private Point location = new Point();

        @objid ("3680a405-55b7-11e2-877f-002564c97630")
        public RequiredInterfaceDeco() {
        }

        @objid ("3680a407-55b7-11e2-877f-002564c97630")
        @Override
        public void setReferencePoint(final Point ref) {
            final Point pt = new Point(ref);
            pt.negate().translate(this.location);
            
            this.rotation = Math.atan2(pt.y, pt.x);
            
            updateBounds();
        }

        @objid ("3680a40c-55b7-11e2-877f-002564c97630")
        @Override
        public void setLocation(final Point p) {
            this.location.setLocation(p);
            updateBounds();
        }

        /**
         * Updates the decoration bounds so that the center of the circle is on the point given to
         * {@link #setLocation(Point)}.
         */
        @objid ("3680a411-55b7-11e2-877f-002564c97630")
        private void updateBounds() {
            final Dimension halfSize = getBounds().getSize().scale(0.5);
            super.setLocation(new Point(this.location.x - halfSize.width, this.location.y - halfSize.height));
        }

        @objid ("3680a414-55b7-11e2-877f-002564c97630")
        @Override
        protected void paintFigure(final Graphics g) {
            int angle = 180 - (int) Math.toDegrees(this.rotation);
            Rectangle r = Rectangle.SINGLETON;
            r.setBounds(getBounds());
            r.resize(-1, -1);
            g.drawArc(r, angle - 90, 180);
        }

        @objid ("8e640f6e-e7db-4fc5-a15d-ba7c60f96216")
        public double getRotation() {
            return this.rotation;
        }

    }

    /**
     * Required interface connection figure.
     * <p>
     * Same as {@link RoundedLinkFigure} but the connection is shorten at the end for the required lollipop.
     * 
     * @author cmarin
     */
    @objid ("3680a419-55b7-11e2-877f-002564c97630")
    public static class RequiredInterfaceFigure extends RoundedLinkFigure {
        /**
         * Creates the figure.
         */
        @objid ("3680a41e-55b7-11e2-877f-002564c97630")
        public RequiredInterfaceFigure() {
            super();
            
            final RequiredInterfaceDeco dec = new RequiredInterfaceDeco();
            
            setTargetDecoration(dec);
            
            // Set style independent properties
            dec.setOpaque(true);
            dec.setSize(REQUIRED_DIAM, REQUIRED_DIAM);
        }

        @objid ("3680a421-55b7-11e2-877f-002564c97630")
        @Override
        public void layout() {
            // Call normal connection routing
            super.layout();
            
            // Move back the last point so that it doesn't cross the target decotration
            PointList points = getPoints();
            final int nbPoints = points.size();
            if (nbPoints >= 2) {
                Point p1 = points.getPoint(nbPoints - 2);
                Point p2 = points.getPoint(nbPoints - 1);
            
                double rotation = Math.atan2(p2.y - p1.y, p2.x - p1.x);
            
                final Dimension halfSize = getTargetDecoration().getBounds().getSize().scale(0.5);
                Point delta = new Point(-halfSize.width, 0);
                final Transform t = new Transform();
                t.setRotation(rotation);
                delta = t.getTransformed(delta);
            
                p2.translate(delta);
            
                points.setPoint(p2, nbPoints - 1);
            }
        }

        @objid ("3680a424-55b7-11e2-877f-002564c97630")
        @Override
        public boolean containsPoint(final int x, final int y) {
            if (getTargetAnchor() != null) {
                final IFigure target = getTargetAnchor().getOwner();
                if (target != null && !(target instanceof FreeformFigure)) {
                    if (target.containsPoint(x, y))
                        return false;
                }
            }
            return super.containsPoint(x, y);
        }

    }

    /**
     * Anchor that is located on the last point of the given {@link Connection}.
     */
    @objid ("3680a42d-55b7-11e2-877f-002564c97630")
    private static class LastConnectionPointAnchor extends AbstractConnectionAnchor {
        @objid ("63c2495a-5bd5-11e2-9e33-00137282c51b")
        private Connection fig;

        @objid ("3680a431-55b7-11e2-877f-002564c97630")
        public LastConnectionPointAnchor(final Connection fig) {
            super(fig);
            this.fig = fig;
        }

        @objid ("3680a435-55b7-11e2-877f-002564c97630")
        @Override
        public Point getLocation(final Point reference) {
            final Point ret = this.fig.getPoints().getLastPoint();
            this.fig.translateToAbsolute(ret);
            return ret;
        }

    }

}
