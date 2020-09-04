/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.editors.richnote.gui.nattable;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IConfiguration;
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.style.VerticalAlignmentEnum;
import org.modelio.core.ui.nattable.parts.data.CellTagHelper;
import org.modelio.core.ui.nattable.parts.data.NatValueWrappingDisplayConverter;
import org.modelio.core.ui.nattable.viewer.model.INatTableViewerContext;
import org.modelio.core.ui.nattable.viewer.model.IPropertyTableConfigurationProvider;
import org.modelio.core.ui.nattable.viewer.model.PropertyTableDataModel;

@objid ("9e69e6bb-285f-4999-898a-7ed654b3114a")
public class ConfigurationProvider implements IPropertyTableConfigurationProvider {
    @objid ("43237703-61f3-4962-9fa0-25e583bce3e7")
    @Override
    public IConfiguration getConfiguration(INatTableViewerContext context, PropertyTableDataModel dataModel) {
        return new Configuration(context, dataModel);
    }

    @objid ("95f39b4f-cf6a-4019-8870-ebf911631178")
    private static class Configuration extends AbstractRegistryConfiguration {
        @objid ("fde7962a-3389-466b-990c-d0bc4976b8b7")
        private INatTableViewerContext context;

        @objid ("981b9333-73ac-413e-9a73-3edfc88bb177")
        public Configuration(INatTableViewerContext context, PropertyTableDataModel dataModel) {
            this.context = context;
        }

        @objid ("58ef180c-b4c4-42f8-9116-1b66ba2db29a")
        @Override
        public void configureRegistry(IConfigRegistry configRegistry) {
            final String tag = CellTagHelper.getTypeTag(IDocumentNatValue.class);
            
            // Style and painter
            Style cellStyle = new Style();
            cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
            cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, VerticalAlignmentEnum.TOP);
            configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
            
            
            configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new DocumentPainter(this.context.getSession()),
                    DisplayMode.NORMAL, tag);
            
            // Display converter
            configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                    new NatValueWrappingDisplayConverter(new DocumentDisplayConverter()), DisplayMode.NORMAL, tag);
            
            // Editor
            final DocumentEditor editor = new DocumentEditor(this.context);
            configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, editor, DisplayMode.EDIT, tag);
            
            // Validator
        }

    }

}
