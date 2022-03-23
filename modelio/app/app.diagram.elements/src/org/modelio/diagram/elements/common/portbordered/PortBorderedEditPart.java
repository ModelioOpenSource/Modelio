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
package org.modelio.diagram.elements.common.portbordered;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for elements that can have Ports. The 'ports' must be controlled by an EditPart that implements
 * IPortEditPart
 * <p>
 * eg: classes, instances and component.<br>
 * May be used in the future for Pin owners in activity diagram.
 * 
 * @author phv
 */
@objid ("7eda1c2a-1dec-11e2-8cad-001ec947c8cc")
public abstract class PortBorderedEditPart extends AbstractNodeEditPart {
    /**
     * Creates a {@link PortBorderedFigure} that contains as primary figure the figure created by
     * {@link #createPrimaryFigure()}.
     */
    @objid ("7eda1c2c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        IFigure primaryFigure = createPrimaryFigure();
        PortBorderedFigure portFigure = new PortBorderedFigure(primaryFigure);
        
        // Set style independent features.
        // Set the colors to invalid colors so as to detect immediately a problem in the figure composition.
        portFigure.setBackgroundColor(ColorConstants.red);
        portFigure.setForegroundColor(ColorConstants.red);
        // Set style dependent features.
        IStyle style = ((GmAbstractObject) this.getModel()).getDisplayedStyle();
        refreshFromStyle(portFigure, style);
        return portFigure;
    }

    @objid ("7eda1c34-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IFigure getContentPane() {
        // The closest figure to a content is the primary figure.
        return getPrimaryfigure();
    }

    /**
     * Creates the primary figure.
     * @return the primary figure.
     */
    @objid ("7edc7e85-1dec-11e2-8cad-001ec947c8cc")
    protected abstract IFigure createPrimaryFigure();

    /**
     * Add a child depending on its nature to either the primary figure or the portContainer.
     */
    @objid ("7edc7e8a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        IFigure childFigure = ((GraphicalEditPart) childEditPart).getFigure();
        
        if (childEditPart instanceof IPortEditPart) {
            // Add to the portContainer
            ((PortBorderedFigure) getFigure()).addPortFigure(childFigure);
        } else {
            // Add to the primary figure
            ((PortBorderedFigure) getFigure()).addToPrimaryFigure(childFigure, index);
        }
        
    }

    /**
     * Remove a child depending on its nature from either the primary figure or the port container.
     */
    @objid ("7edc7e92-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        // 
        IFigure childFigure = ((GraphicalEditPart) childEditPart).getFigure();
        
        if (childEditPart instanceof IPortEditPart) {
            // Add to the portContainer
            ((PortBorderedFigure) getFigure()).removePortFigure(childFigure);
        } else {
            // Add to the primary figure
            ((PortBorderedFigure) getFigure()).removeFromPrimaryFigure(childFigure);
        }
        
    }

    @objid ("7edc7e99-1dec-11e2-8cad-001ec947c8cc")
    private void initFigureFromStyle(PortBorderedFigure portFigure, IStyle style) {
        // Set the colors to invalid colors so as to detect immediately a problem
        // in the figure composition.
        portFigure.setBackgroundColor(ColorConstants.red /* style.getColor(StyleKey.CLASS_FILLCOLOR)*/);
        portFigure.setForegroundColor(ColorConstants.red /* style.getColor(StyleKey.CLASS_LINECOLOR)*/);
        //figure.setFont(style.getFont(StyleKey.CLASS_FONT));
        
    }

    @objid ("7edc7e9d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure figure, IStyle style) {
        if (figure instanceof PortBorderedFigure) {
            // Refresh the primary figure instead
            PortBorderedFigure portFig = (PortBorderedFigure) figure;
            if (portFig.getPrimaryFigure() != null)
                super.refreshFromStyle(portFig.getPrimaryFigure(), style);
        } else {
            super.refreshFromStyle(figure, style);
        }
        
    }

    /**
     * Returns the primary figure.
     * @return the primary figure.
     */
    @objid ("7edc7ea4-1dec-11e2-8cad-001ec947c8cc")
    protected IFigure getPrimaryfigure() {
        PortBorderedFigure fig = (PortBorderedFigure) getFigure();
        return fig.getPrimaryFigure();
    }

}
