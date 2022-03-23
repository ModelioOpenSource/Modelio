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
package org.modelio.diagram.elements.common.group;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;

/**
 * Figure for labels group.
 * <p>
 * The group content is vertically stacked. It can also display a trailing "..." indicator label telling that some
 * elements are not displayed in the group.
 * 
 * @author cmarin
 */
@objid ("7e5bbf85-1dec-11e2-8cad-001ec947c8cc")
public class GroupFigure extends GradientFigure {
    @objid ("655ea45b-1e83-11e2-8cad-001ec947c8cc")
    private Label incompleteIndicator = null;

    @objid ("655ea45c-1e83-11e2-8cad-001ec947c8cc")
    private Figure elementsList;

    /**
     * Creates a group figure.
     * @param stretchLastChild true to grab excess space, else false.
     */
    @objid ("7e5bbf8d-1dec-11e2-8cad-001ec947c8cc")
    public  GroupFigure(boolean stretchLastChild) {
        super();
        
        final GridLayout manager = new GridLayout(1, true);
        manager.marginHeight = 0;
        setLayoutManager(manager);
        
        // The element list itself is a simple figure
        // It is transparent too and receives a vertical ToolbarLayout
        this.elementsList = new Figure();
        this.elementsList.setOpaque(false);
        this.elementsList.setBackgroundColor(null);
        this.elementsList.setBorder(new MarginBorder(3, 2, 3, 2));
        
        ToolbarLayoutWithGrab layout = new ToolbarLayoutWithGrab();
        layout.setLastChildGrab(stretchLastChild);
        layout.setMinorAlignment(OrderedLayout.ALIGN_TOPLEFT);
        layout.setStretchMinorAxis(true);
        layout.setHorizontal(false);
        layout.setSpacing(0);
        this.elementsList.setLayoutManager(layout);
        
        this.add(this.elementsList, createElementListGridData());
        
        // Incomplete indicator
        this.incompleteIndicator = new Label("...");
        this.incompleteIndicator.setLabelAlignment(PositionConstants.CENTER);
        
    }

    /**
     * Get the place where child figures must be added.
     * @return the place where child figures must be added.
     */
    @objid ("7e5bbf91-1dec-11e2-8cad-001ec947c8cc")
    public IFigure getContenPane() {
        return this.elementsList;
    }

    /**
     * Show or hide the incomplete indicator label.
     * @param show true to show the indicator, false to hide it.
     */
    @objid ("7e5bbf98-1dec-11e2-8cad-001ec947c8cc")
    public void showIncompleteIndicator(boolean show) {
        if (this.incompleteIndicator.getParent() != null) {
            remove(this.incompleteIndicator);
        }
        
        if (show) {
            add(this.incompleteIndicator, createIndicatorGridData());
        }
        
    }

    @objid ("7e5bbf9c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setVisible(boolean visible) {
        if (visible != isVisible()) {
            if (visible) {
                this.add(this.elementsList, createElementListGridData());
            } else {
                remove(this.elementsList);
            }
        
            this.elementsList.setVisible(visible);
        }
        
        super.setVisible(visible);
        
    }

    @objid ("7e5bbfa0-1dec-11e2-8cad-001ec947c8cc")
    private static GridData createElementListGridData() {
        return new GridData(SWT.FILL, SWT.FILL, true, true);
    }

    @objid ("7e5bbfa6-1dec-11e2-8cad-001ec947c8cc")
    private static GridData createIndicatorGridData() {
        return new GridData(SWT.FILL, SWT.TOP, true, false);
    }

}
