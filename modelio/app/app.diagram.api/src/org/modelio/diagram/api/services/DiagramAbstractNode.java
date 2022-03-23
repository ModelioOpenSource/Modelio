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

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.IDiagramNode.Role;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.FillMode;

/**
 * This class represents a DiagramGraphic of the 'node' kind.
 */
@objid ("ca17d8e0-e96f-462d-acbe-8e4cb3215432")
public abstract class DiagramAbstractNode extends DiagramGraphic implements IDiagramNode {
    /**
     * Creates a diagram node.
     * @param diagramHandle The diagram manipulation class.
     */
    @objid ("fe776269-7dfd-4e28-a41d-a20b864c0880")
    public  DiagramAbstractNode(DiagramHandle diagramHandle) {
        super(diagramHandle);
    }

    /**
     * Resize this diagram node to fit its content.
     */
    @objid ("1223363e-15e1-4dd5-ade6-ee7419951e81")
    @Override
    public void fitToContent() {
        final Dimension oldSize = getBounds().getSize();
        
        Dimension newSize = getMinimumSize();
        
        if (!oldSize.equals(newSize)) {
            setSize(newSize.width, newSize.height);
        }
        
    }

    /**
     * Return the node location and size as a Rectangle.
     * @return the node bounds.
     */
    @objid ("490748e6-68ee-472e-9b8f-a5344eb57588")
    @Override
    public Rectangle getBounds() {
        IGmObject model = getModel();
        final GraphicalEditPart p = this.diagramHandle.getEditPart(model);
        if (p == null) {
            return null;
        }
        
        // Get base bounds from the figure
        Rectangle bounds;
        IFigure figure = p.getFigure();
        if (figure instanceof HandleBounds) {
            bounds = ((HandleBounds) figure).getHandleBounds().getCopy();
        } else {
            bounds = figure.getBounds().getCopy();
        }
        
        figure.translateToAbsolute(bounds);
        return bounds;
    }

    @objid ("e1059f33-9d03-4c43-bb1f-e072f46db3a7")
    @Override
    public String toString() {
        Rectangle bounds = getBounds();
        if (bounds == null) {
            bounds = new Rectangle(0, 0, -1, -1);
        }
        return String.format("%s '%s' [%d,%d, %d,%d]", this.getClass().getSimpleName(), getName(), bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /**
     * This method changes both the location and the size of a node by changing its current bounds.<br>
     * Note however that the requested change might no be performed when some layout constraints are maintained by the parent of the node (ie the requested changes are refused or adapted by the parent of the node).
     * @param newBounds the new node bounds
     */
    @objid ("e6eacc0f-78f1-4b62-8ff1-40ae61305557")
    @Override
    public void setBounds(Rectangle newBounds) {
        if (newBounds.height == -1 || newBounds.width == -1) {
            return;
        }
        
        setLocation(newBounds.x, newBounds.y);
        setSize(newBounds.width, newBounds.height);
        
    }

    @objid ("095e0bf2-e517-4d28-a70e-738130f70331")
    @Override
    public void mask() {
        final GraphicalEditPart editPart = this.diagramHandle.getEditPart(getModel());
        
        final GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
        deleteReq.setEditParts(editPart);
        
        final Command cmd = editPart.getCommand(deleteReq);
        if (cmd != null && cmd.canExecute()) {
            editPart.getViewer().getEditDomain().getCommandStack().execute(cmd);
        }
        
    }

    /**
     * Change the location of the node, setting its new position to (x,y).
     * <p>
     * Note however that the requested change might no be performed when some layout constraints are maintained
     * by the parent of the node (ie the requested change is refused or adapted by the parent of the node).
     * @param x the new X position
     * @param y the new Y position
     * @return true if the change could be requested (different from 'performed' see note above)
     */
    @objid ("5590ddce-3678-4766-8287-f47479e8c737")
    @Override
    public boolean setLocation(int x, int y) {
        final GraphicalEditPart p = this.diagramHandle.getEditPart(getModel());
        if (p == null) {
            return false;
        }
        final Rectangle currentBounds = getBounds();
        if (currentBounds.getTopLeft().equals(new Point(x, y))) {
            return true;
        }
        
        final Point newLocation = new Point(x, y);
        
        final ChangeBoundsRequest req = new ChangeBoundsRequest();
        req.setType(RequestConstants.REQ_MOVE);
        req.setEditParts(p);
        req.setMoveDelta(new Point(newLocation.x - currentBounds.x, newLocation.y - currentBounds.y));
        req.setLocation(new Point(x, y));
        final Command com = p.getCommand(req);
        if (com != null && com.canExecute()) {
            p.getViewer().getEditDomain().getCommandStack().execute(com);
            p.getFigure().getUpdateManager().performValidation();
            return true;
        } else {
            return false;
        }
        
    }

    /**
     * Change the size of the node to (width,height).<br>
     * Note however that the requested change might no be performed when some layout constraints are maintained by the parent of the node (ie the requested change is refused or adapted by the parent of the node).
     * @param width the new width
     * @param height the new height
     * @return true if the change could be requested (different from 'performed' see note above)
     */
    @objid ("8fa4309f-35b3-4a26-bd8b-6b27bf7c31a4")
    @Override
    public boolean setSize(int width, int height) {
        final GraphicalEditPart p = this.diagramHandle.getEditPart(getModel());
        if (p == null) {
            return false;
        }
        final Rectangle currentBounds = getBounds();
        if (currentBounds.getSize().equals(new Dimension(width, height))) {
            return true;
        }
        
        final ChangeBoundsRequest req = new ChangeBoundsRequest();
        req.setType(RequestConstants.REQ_RESIZE);
        req.setEditParts(p);
        req.setSizeDelta(new Dimension(width - currentBounds.width, height - currentBounds.height));
        final Command com = p.getCommand(req);
        if (com != null && com.canExecute()) {
            p.getViewer().getEditDomain().getCommandStack().execute(com);
            p.getFigure().getUpdateManager().performValidation();
            return true;
        } else {
            return false;
        }
        
    }

    @objid ("d6a4a384-7664-4f20-8806-fd675ebb15e9")
    @Override
    public String getFont() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.FONT);
        if (styleKey == null) {
            return null;
        }
        final Font currentFont = getModel().getDisplayedStyle().getProperty(styleKey);
        return StyleKeyTypeConverter.convertToString(styleKey, currentFont);
    }

    @objid ("c0facfaf-0907-49a9-909f-5187ad76f8ea")
    @Override
    public String getLineColor() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINECOLOR);
        if (styleKey == null) {
            return null;
        }
        final Color color = getModel().getDisplayedStyle().getProperty(styleKey);
        return StyleKeyTypeConverter.convertToString(styleKey, color);
    }

    @objid ("2e3b1832-18c8-4aaf-8d2a-df21b47b297a")
    @Override
    public int getLineWidth() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINEWIDTH);
        if (styleKey == null) {
            return 0;
        }
        return getModel().getDisplayedStyle().getProperty(styleKey);
    }

    @objid ("aa814dda-f436-49af-b420-5f1079957e80")
    @Override
    public String getTextColor() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.TEXTCOLOR);
        if (styleKey == null) {
            return null;
        }
        final Color color = getModel().getDisplayedStyle().getProperty(styleKey);
        return StyleKeyTypeConverter.convertToString(styleKey, color);
    }

    @objid ("b8df35e0-7006-4e1a-8de0-9d9c9543de88")
    @Override
    public void setFont(final String value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.FONT);
        if (styleKey == null) {
            return;
        }
        getModel().getDisplayedStyle()
        .setProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        
    }

    @objid ("626281c5-dd49-4cdb-9e6d-0e50396e85fa")
    @Override
    public void setLineColor(final String value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINECOLOR);
        if (styleKey == null) {
            return;
        }
        getModel().getDisplayedStyle()
        .setProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        
    }

    @objid ("f1b4ae70-b1cd-4f3c-9118-c1f2ed820699")
    @Override
    public void setLineWidth(final int value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.LINEWIDTH);
        if (styleKey == null) {
            return;
        }
        
        getModel().getDisplayedStyle().setProperty(styleKey, value);
        
    }

    @objid ("77c804b0-6e6e-4820-b800-4d3672dd29e5")
    @Override
    public void setTextColor(final String value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.TEXTCOLOR);
        if (styleKey == null) {
            return;
        }
        getModel().getDisplayedStyle()
        .setProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        
    }

    @objid ("6ed35269-aaaa-4e50-9d73-6a51724ac79e")
    @Override
    public String getFillColor() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.FILLCOLOR);
        if (styleKey == null) {
            return null;
        }
        final Color color = getModel().getDisplayedStyle().getProperty(styleKey);
        return StyleKeyTypeConverter.convertToString(styleKey, color);
    }

    @objid ("0fb7339a-e77c-4ce5-8c6c-36dae86b47d7")
    @Override
    public int getFillMode() {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.FILLMODE);
        if (styleKey == null) {
            return 0;
        }
        final FillMode pattern = getModel().getDisplayedStyle().getProperty(styleKey);
        
        switch (pattern) {
        case TRANSPARENT:
            return 0;
        case SOLID:
            return 1;
        case GRADIENT:
            return 2;
        default:
            return 2;
        }
        
    }

    @objid ("87bb9332-147d-47c8-9e43-a7d2edd9e73a")
    @Override
    public void setFillColor(final String value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.FILLCOLOR);
        if (styleKey == null) {
            return;
        }
        
        getModel().getDisplayedStyle()
        .setProperty(styleKey, StyleKeyTypeConverter.convertFromString(styleKey, value));
        
    }

    @objid ("a113af60-2c0e-47bd-8a50-c242fca38582")
    @Override
    public void setFillMode(final int value) {
        final StyleKey styleKey = getModel().getStyleKey(MetaKey.FILLMODE);
        if (styleKey == null) {
            return;
        }
        
        FillMode pattern;
        
        switch (value) {
        case 0:
            pattern = FillMode.TRANSPARENT;
            break;
        case 1:
            pattern = FillMode.SOLID;
            break;
        case 2:
            pattern = FillMode.GRADIENT;
            break;
        default:
            pattern = FillMode.GRADIENT;
        }
        
        getModel().getDisplayedStyle().setProperty(styleKey, pattern);
        
    }

    @objid ("a6028698-76ec-4418-8c97-b8d3d836afbb")
    protected Dimension getMinimumSize() {
        final GraphicalEditPart editPart = this.diagramHandle.getEditPart(getModel());
        return editPart.getFigure().getMinimumSize();
    }

    @objid ("0bda4849-78d9-450f-8fdd-5a91aaa88314")
    @Override
    public Rectangle getOverallBounds() {
        return getBounds();
    }

    @objid ("d1698a7e-2acb-4922-967a-58d5bfce7a64")
    @Override
    public IDiagramNode getFirstNode(Role role) {
        Collection<IDiagramNode> c = getNodes(role);
        if (c.isEmpty()) {
            return null;
        } else {
            return c.iterator().next();
        }
        
    }

}
