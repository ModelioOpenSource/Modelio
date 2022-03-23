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
import org.eclipse.nebula.widgets.nattable.data.convert.DefaultDisplayConverter;
import org.eclipse.nebula.widgets.nattable.painter.cell.TextPainter;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.modelio.platform.model.ui.nattable.parts.theme.ModelioTableTheme;

/**
 * Configurations for the Element's nat table.
 */
@objid ("71edd578-f5b8-4392-b9b5-96473a21c2a8")
public class NatTableStyleConfiguration extends AbstractRegistryConfiguration {
    @objid ("8872f6d2-fe00-43cd-af10-a956d4aaee60")
    @Override
    public void configureRegistry(IConfigRegistry configRegistry) {
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new TextPainter());
        
        ModelioTableTheme colorScheme = ModelioTableTheme.getInstance();
        
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.FONT, colorScheme.cellFont);
        cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, colorScheme.cellBackground);
        cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, colorScheme.cellForeGround);
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, colorScheme.cellHAlign);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, colorScheme.cellVAlign);
        
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle);
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER, new DefaultDisplayConverter());
        
    }

}
