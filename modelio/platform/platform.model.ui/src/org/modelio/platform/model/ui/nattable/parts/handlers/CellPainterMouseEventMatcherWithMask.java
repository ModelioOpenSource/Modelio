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

package org.modelio.platform.model.ui.nattable.parts.handlers;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.layer.LabelStack;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.eclipse.nebula.widgets.nattable.ui.matcher.MouseEventMatcher;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

/**
 * Matches a mouse click with a keyboard mask on a given cell painter within a cell.
 */
@objid ("3edb4314-c6e3-4f6f-861b-8ec322bd3892")
public class CellPainterMouseEventMatcherWithMask extends MouseEventMatcher {
    @objid ("c382ec43-abe1-4660-a058-eb2ce2eb5cfa")
    private final Class<? extends ICellPainter> targetCellPainterClass;

    /**
     * Constructor
     * @param stateMask, see {@link org.eclipse.swt.events.MouseEvent#stateMask}
     * @param eventRegion
     * {@linkplain org.eclipse.nebula.widgets.nattable.grid.GridRegion}
     * @param button
     * @see org.eclipse.swt.events.MouseEvent#button
     * {@link MouseEventMatcher#LEFT_BUTTON},
     * {@link MouseEventMatcher#RIGHT_BUTTON} can be used for convenience
     */
    @objid ("adb0b45a-cf1a-4a0d-bb97-57292ce84c6c")
    public CellPainterMouseEventMatcherWithMask(int stateMask, String regionName, int button, Class<? extends ICellPainter> targetCellPainterClass) {
        super(stateMask, regionName, button);
        this.targetCellPainterClass = Objects.requireNonNull(targetCellPainterClass);
    }

    @objid ("242a8955-6179-40c2-99bd-7880218e31b3")
    @Override
    public boolean matches(NatTable natTable, MouseEvent event, LabelStack regionLabels) {
        if (super.matches(natTable, event, regionLabels)) {
            int columnPosition = natTable.getColumnPositionByX(event.x);
            int rowPosition = natTable.getRowPositionByY(event.y);
        
            ILayerCell cell = natTable.getCellByPosition(columnPosition, rowPosition);
        
            // Bug 407598: only perform a check if the click in the body region
            // was performed on a cell
            // cell == null can happen if the viewport is quite large and
            // contains not enough cells to fill it.
            if (cell != null) {
                IConfigRegistry configRegistry = natTable.getConfigRegistry();
                ICellPainter cellPainter = cell.getLayer().getCellPainter(columnPosition, rowPosition, cell,
                        configRegistry);
        
                GC gc = new GC(natTable.getDisplay());
                try {
                    Rectangle adjustedCellBounds = natTable.getLayerPainter().adjustCellBounds(columnPosition,
                            rowPosition, cell.getBounds());
        
                    ICellPainter clickedCellPainter = cellPainter.getCellPainterAt(event.x, event.y, cell, gc,
                            adjustedCellBounds, configRegistry);
                    if (clickedCellPainter != null) {
                        if (this.targetCellPainterClass.isInstance(clickedCellPainter)) {
                            return true;
                        }
                    }
                } finally {
                    gc.dispose();
                }
            }
        }
        return false;
    }

}
