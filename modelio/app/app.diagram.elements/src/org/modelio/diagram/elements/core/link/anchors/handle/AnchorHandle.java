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
package org.modelio.diagram.elements.core.link.anchors.handle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.handles.AbstractHandle;
import org.modelio.diagram.elements.core.figures.anchors.AnchorFigureFactory;

/**
 * Handle that represents a {@link ConnectionAnchor}.
 * <p>
 * The handle has no drag tracker. You may put one with {@link #setDragTracker(DragTracker)}.
 * <p>
 * The handle is drawn a little apart from the node figure .
 * 
 * @author cmarin
 * @since 5.3.1
 */
@objid ("4e473d97-9503-49c1-b1c5-792c773060e3")
public class AnchorHandle extends AbstractHandle {
    /**
     * Make a circle figure by default
     * @param owner the node edit part
     * @param anchor the connection anchor
     */
    @objid ("f901dbd4-069b-4156-955f-521215b88f47")
    public  AnchorHandle(GraphicalEditPart owner, ConnectionAnchor anchor) {
        this(owner, new TranslatedAnchorLocator(anchor), AnchorFigureFactory.createHandleFigure(anchor));
    }

    /**
     * @param owner the node edit part
     * @param locator the handle Locator
     * @param anchorFigure the figure to use to display the handle.
     */
    @objid ("d0ebe610-c47a-4edc-a130-058dd89b4c2d")
    public  AnchorHandle(GraphicalEditPart owner, Locator locator, IFigure anchorFigure) {
        super(owner, locator);
        setLayoutManager(new StackLayout());
        
        add(anchorFigure);
        
        setCursor(SharedCursors.CURSOR_PLUG);
        
    }

    /**
     * SelectionTool does not like Handle with child figures so prevent search to go to children and return itself.
     */
    @objid ("6e0fb350-8991-4cda-92b4-a559b9c8cefb")
    @Override
    protected IFigure findDescendantAtExcluding(int x, int y, TreeSearch search) {
        // SelectionTool does not like Handle with child figures so prevent search to go to children and return itself
        return null;
    }

    @objid ("61b63aa7-c576-4255-9f73-cbc271551c07")
    @Override
    protected DragTracker createDragTracker() {
        return null;
    }

    @objid ("b26241e6-e008-4a83-9acc-6f289e44fd1f")
    public void setTransludent() {
        for (Object object : getChildren()) {
            setTransludent((IFigure) object);
        }
        
    }

    @objid ("4e8f4787-4cba-4054-b144-681b72b61093")
    private void setTransludent(IFigure fb) {
        fb.setOpaque(false);
        if (fb instanceof Shape) {
            Shape shape = (Shape) fb;
            shape.setFill(false);
            shape.setAlpha(150);
            shape.setAntialias(1);
        }
        
    }

}
