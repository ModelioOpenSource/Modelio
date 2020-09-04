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

package org.modelio.core.ui.nattable.viewer.config;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.selection.config.DefaultSelectionLayerConfiguration;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.SelectionStyleLabels;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.modelio.core.ui.nattable.parts.theme.ModelioTableTheme;

/**
 * Configuration for the selection layer of the Element's nat table.
 */
@objid ("bcb4bdf5-a6b0-42af-bc63-dce789cabf43")
public class SelectionLayerConfiguration extends DefaultSelectionLayerConfiguration {
    @objid ("a5c0608a-e818-4505-b450-a764269652bd")
    @Override
    protected void addSelectionStyleConfig() {
        addConfiguration(new SelectionStyleConfiguration());
    }

    @objid ("7648ecdf-f65a-4966-8ba8-ba71caf95a01")
    private static class SelectionStyleConfiguration extends AbstractRegistryConfiguration {
        @objid ("d41e319b-52dc-4a60-85e0-c04fe2185f78")
        private ModelioTableTheme colorScheme = ModelioTableTheme.getInstance();

        /**
         * Configure selection style for Scope.
         */
        @objid ("c130cd21-8e95-4896-966d-e6e449ed3b52")
        @Override
        public void configureRegistry(IConfigRegistry configRegistry) {
            configureSelectedCell(configRegistry);
            configureSelectedHeader(configRegistry);
        }

        @objid ("ab23ce46-eeee-4c62-9740-f6d0dd999448")
        private void configureSelectedHeader(IConfigRegistry configRegistry) {
            Style cellStyle = new Style();
            
            cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, this.colorScheme.selectedHeaderForeground);
            cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, this.colorScheme.selectedHeaderBackground);
            cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_FOREGROUND_COLOR, this.colorScheme.selectedHeaderGradientForeground);
            cellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_BACKGROUND_COLOR, this.colorScheme.selectedHeaderGradientBackground);
            cellStyle.setAttributeValue(CellStyleAttributes.FONT, this.colorScheme.selectedHeaderFont);
            
            configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT, GridRegion.COLUMN_HEADER);
            configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT, GridRegion.CORNER);
            configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT, GridRegion.ROW_HEADER);
        }

        @objid ("35b001a0-9702-457f-a998-3395614d8c71")
        private void configureSelectedCell(IConfigRegistry configRegistry) {
            Style cellStyle = new Style();
            
            // Selection anchor style for select display mode
            // (ie the grid rectangle line around the cell)
            
            cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, this.colorScheme.selectedCellGridBorderStyle);
            configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT, SelectionStyleLabels.SELECTION_ANCHOR_GRID_LINE_STYLE);
            
            // Selected cell background, foreground and font
            cellStyle = new Style();
            cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, this.colorScheme.selectedCellBackground);
            cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, this.colorScheme.selectedCellForeground);
            cellStyle.setAttributeValue(CellStyleAttributes.FONT, this.colorScheme.selectedCellFont);
            
            configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT, SelectionStyleLabels.SELECTION_ANCHOR_STYLE);
            
            // Fully Selected row or col
            cellStyle = new Style();
            cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, this.colorScheme.selectedRowColumnBackground);
            cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, this.colorScheme.selectedRowColumnForeground);
            cellStyle.setAttributeValue(CellStyleAttributes.FONT, this.colorScheme.selectedRowColumnFont);
            
            configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT);
        }

        @objid ("4489b60d-15dd-46e2-9501-20607a816877")
        public SelectionStyleConfiguration() {
            super();
        }

    }

}
