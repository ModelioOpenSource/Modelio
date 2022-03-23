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
package org.modelio.platform.model.ui.nattable.viewer.config;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.grid.cell.AlternatingRowConfigLabelAccumulator;
import org.eclipse.nebula.widgets.nattable.grid.layer.config.DefaultGridLayerConfiguration;
import org.eclipse.nebula.widgets.nattable.layer.CompositeLayer;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.modelio.platform.model.ui.nattable.parts.theme.ModelioTableTheme;

/**
 * Configuration for the grid layer of the Element's nat table.
 * Adds odd/even row colors.
 */
@objid ("b01fdf59-d666-4376-8b2d-590739752b29")
public class GridLayerConfiguration extends DefaultGridLayerConfiguration {
    /**
     * Create a new ColumnHeaderConfiguration.
     * @param gridLayer the layer associated with this configuration.
     */
    @objid ("bf771737-0e34-40ae-81b4-1848bfeab83a")
    public  GridLayerConfiguration(CompositeLayer gridLayer) {
        super(gridLayer);
    }

    /**
     * Specialization for odd/even row colors
     */
    @objid ("de7ccc99-1aed-467f-ac51-e61c955fbc73")
    @Override
    protected void addAlternateRowColoringConfig(CompositeLayer gridLayer) {
        addConfiguration(new ModelioRowStyleConfiguration());
        gridLayer.setConfigLabelAccumulatorForRegion(GridRegion.BODY, new AlternatingRowConfigLabelAccumulator(gridLayer.getChildLayerByRegionName(GridRegion.BODY)));
        
    }

    @objid ("07165c09-1d8c-46e2-9b85-1364da3719e9")
    private static class ModelioRowStyleConfiguration extends AbstractRegistryConfiguration {
        @objid ("6d30abd0-f96d-463a-a493-f0f97c9a465d")
        private ModelioTableTheme colorScheme = ModelioTableTheme.getInstance();

        @objid ("c0997365-9457-44cb-a68d-db5bc9700cc8")
        @Override
        public void configureRegistry(IConfigRegistry configRegistry) {
            configureOddRowStyle(configRegistry);
            configureEvenRowStyle(configRegistry);
            
        }

        @objid ("25527e4b-96bb-4fa5-9e8f-9ac0961cc011")
        private void configureOddRowStyle(IConfigRegistry configRegistry) {
            Style cellStyle = new Style();
            cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, this.colorScheme.oddRowBackground);
            configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, AlternatingRowConfigLabelAccumulator.EVEN_ROW_CONFIG_TYPE);
            
        }

        @objid ("bde04cf6-dd11-4abf-9296-dae91640a428")
        private void configureEvenRowStyle(IConfigRegistry configRegistry) {
            Style cellStyle = new Style();
            cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, this.colorScheme.evenRowBackground);
            configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, AlternatingRowConfigLabelAccumulator.ODD_ROW_CONFIG_TYPE);
            
        }

        @objid ("3cb0c138-c9a7-4fc5-8f03-2139212c50d3")
        public  ModelioRowStyleConfiguration() {
            super();
        }

    }

}
