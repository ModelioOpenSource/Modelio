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
package org.modelio.uml.statediagram.editor.elements.state;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.resizablegroup.ResizableGroupEditPart;
import org.modelio.diagram.elements.common.resizablegroup.ResizableGroupRefreshFromModelEditPolicy;
import org.modelio.diagram.elements.core.figures.ChildFigureLineSeparator;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specialization for regions group: handle the borders on add and remove.
 * 
 * @author fpoyer
 */
@objid ("f5888f87-55b6-11e2-877f-002564c97630")
public class RegionsGroupEditPart extends ResizableGroupEditPart {
    @objid ("92e69579-325b-40c1-a5d3-17214c44e4dc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(ResizableGroupRefreshFromModelEditPolicy.ROLE,
                new ResizableGroupRefreshFromModelEditPolicy(this::getExpectedChildren, false));
        
    }

    @objid ("d00d1e3c-550f-49dc-a2fc-bcbd40381531")
    private List<? extends MObject> getExpectedChildren(MObject t) {
        State state = (State) t;
        return state.getOwnedRegion();
    }

    @objid ("f5888f8b-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        super.addChildVisual(childEditPart, index);
        updateSeparations(this.getFigure());
        
    }

    @objid ("f5888f92-55b6-11e2-877f-002564c97630")
    @Override
    protected void removeChildVisual(final EditPart childEditPart) {
        super.removeChildVisual(childEditPart);
        updateSeparations(this.getFigure());
        
    }

    @objid ("f5888f97-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        super.refreshFromStyle(aFigure, style);
        updateSeparations(aFigure);
        
    }

    /**
     * Update the separation lines between zones and between regions.
     * @param stateFig The state figure.
     */
    @objid ("f5888fa0-55b6-11e2-877f-002564c97630")
    protected void updateSeparations(IFigure stateFig) {
        final GmModel gmModel = getModel();
        final IStyle style = gmModel.getDisplayedStyle();
        Color lineColor = ColorConstants.gray;
        
        final StyleKey styleKey = gmModel.getStyleKey(MetaKey.LINECOLOR);
        if (styleKey != null) {
            lineColor = style.getColor(styleKey);
        }
        
        int lineWidth = 1;
        final StyleKey styleKey2 = gmModel.getStyleKey(MetaKey.LINEWIDTH);
        if (styleKey2 != null) {
            lineWidth = style.getInteger(styleKey2);
        }
        
        // Update the region separation lines.
        final TLBRBorder regionBorder = new TLBRBorder(lineColor, lineWidth, false, false, true, false);
        
        regionBorder.setStyle(Graphics.LINE_DASH);
        
        ChildFigureLineSeparator.updateSeparation(this, regionBorder);
        
    }

    @objid ("f5888fa4-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // Add a border.
        IFigure fig = super.createFigure();
        fig.setBorder(new TLBRBorder(true, false, false, false));
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

}
