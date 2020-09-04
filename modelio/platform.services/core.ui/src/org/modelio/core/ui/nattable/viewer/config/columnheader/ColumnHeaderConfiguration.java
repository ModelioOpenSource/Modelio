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

package org.modelio.core.ui.nattable.viewer.config.columnheader;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IConfiguration;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.ColumnOverrideLabelAccumulator;
import org.eclipse.nebula.widgets.nattable.painter.cell.GradientBackgroundPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.TextPainter;
import org.eclipse.nebula.widgets.nattable.resize.action.AutoResizeColumnAction;
import org.eclipse.nebula.widgets.nattable.resize.action.ColumnResizeCursorAction;
import org.eclipse.nebula.widgets.nattable.resize.event.ColumnResizeEventMatcher;
import org.eclipse.nebula.widgets.nattable.resize.mode.ColumnResizeDragMode;
import org.eclipse.nebula.widgets.nattable.sort.painter.SortableHeaderTextPainter;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.ui.action.ClearCursorAction;
import org.eclipse.nebula.widgets.nattable.ui.binding.UiBindingRegistry;
import org.eclipse.nebula.widgets.nattable.ui.matcher.MouseEventMatcher;
import org.eclipse.swt.SWT;
import org.modelio.core.ui.nattable.parts.data.string.single.IStringNatValue;
import org.modelio.core.ui.nattable.parts.data.texticon.ITextIconNatValue;
import org.modelio.core.ui.nattable.parts.data.texticon.TextIconConverter;
import org.modelio.core.ui.nattable.parts.theme.ModelioTableTheme;
import org.modelio.core.ui.nattable.viewer.model.PropertyTableDataModel;

/**
 * Configuration for the column headers of Element's nat table.
 * The configuration is dynamically computed from the properties of the
 * propertyModel being displayed in the editor.
 * 
 * Supported types and labels:
 * 
 * HSTRING = horizontal text, non-editable <br/>
 */
@objid ("436c70f6-f010-4dc8-9e71-ab914fd64adf")
public class ColumnHeaderConfiguration implements IConfiguration {
    @objid ("d67f8ca2-c65d-4e51-9809-1c600545e2ac")
    private static final String HSTRING = "HSTRING";

    @objid ("ea053e98-3dd9-4b1c-80e2-0059c3eb4d6e")
    private static final String VSTRING = "VSTRING";

    @objid ("a1a49be1-c0c7-4416-bd96-b35dae2bf8a6")
    private static final String CUSTOM = "CUSTOM";

    @objid ("996954f0-baac-4b22-b183-99593af914d9")
    protected final PropertyTableDataModel dataModel;

    @objid ("9b5bad30-1664-4986-aa21-abc501dab55d")
    private final DataLayer columnHeaderDataLayer;

    @objid ("e7d5e4cc-6d83-4246-a853-d1d559e22f7a")
    private final ModelioTableTheme colorScheme = ModelioTableTheme.getInstance();

    /**
     * Create a new ColumnHeaderConfiguration.
     * 
     * @param dataModel the table's data model.
     * @param columnHeaderDataLayer the layer associated with this configuration.
     */
    @objid ("ad29c8b1-6e68-4915-aa36-ea3c7b88dbf5")
    public ColumnHeaderConfiguration(PropertyTableDataModel dataModel, final DataLayer columnHeaderDataLayer) {
        this.dataModel = dataModel;
        this.columnHeaderDataLayer = columnHeaderDataLayer;
    }

    @objid ("07d614d7-5283-465f-ae7b-cc09eab00a42")
    @Override
    public void configureLayer(ILayer layer) {
        ColumnOverrideLabelAccumulator acc = (ColumnOverrideLabelAccumulator) this.columnHeaderDataLayer
                .getConfigLabelAccumulator();
        
        if (acc == null) {
            this.columnHeaderDataLayer.setConfigLabelAccumulator(
                    new ColumnOverrideLabelAccumulator(this.columnHeaderDataLayer));
            acc = (ColumnOverrideLabelAccumulator) this.columnHeaderDataLayer.getConfigLabelAccumulator();
        }
        
        acc.registerOverridesOnTop(Arrays.asList(HSTRING, CUSTOM));
    }

    @objid ("04fad7a4-c9f3-4442-91b5-ae7b20a3d7a6")
    @Override
    public void configureRegistry(IConfigRegistry configRegistry) {
        configureRegistryForHSTRING(configRegistry);
        configureRegistryForCUSTOM(configRegistry);
    }

    @objid ("46b778a4-cadd-4e4b-a39d-b87b10e31708")
    @Override
    public void configureUiBindings(UiBindingRegistry uiBindingRegistry) {
        // Mouse move - Show col resize cursor
        uiBindingRegistry.registerFirstMouseMoveBinding(
                new ColumnResizeEventMatcher(SWT.NONE, GridRegion.COLUMN_HEADER, 0), new ColumnResizeCursorAction());
        
        // Mouse move - Clear cursor
        uiBindingRegistry.registerMouseMoveBinding(new MouseEventMatcher(), new ClearCursorAction());
        
        // Column resize
        uiBindingRegistry.registerFirstMouseDragMode(
                new ColumnResizeEventMatcher(SWT.NONE, GridRegion.COLUMN_HEADER, 1), new ColumnResizeDragMode());
        uiBindingRegistry.registerDoubleClickBinding(
                new ColumnResizeEventMatcher(SWT.NONE, GridRegion.COLUMN_HEADER, 1), new AutoResizeColumnAction());
    }

    @objid ("206fbefc-e2b9-43b0-81d7-9d07c9092e13")
    private void configureRegistryForHSTRING(IConfigRegistry configRegistry) {
        // Cell Painter
        final TextPainter textPainter = new TextPainter(true /* wrapText */, false /* paintBg */, 2 /* spacing */,
                true /* calculate */);
        final ICellPainter bgGradientPainter = new GradientBackgroundPainter(textPainter, true);
        final ICellPainter horizontalHeaderPainter = new SortableHeaderTextPainter(bgGradientPainter, true, true);
        
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, 
                horizontalHeaderPainter, DisplayMode.NORMAL, HSTRING);
        
        // Cell style
        final Style hCellStyle = new Style();
        hCellStyle.setAttributeValue(CellStyleAttributes.FONT, this.colorScheme.headerFont);
        hCellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_FOREGROUND_COLOR, this.colorScheme.headerGradientForeground);
        hCellStyle.setAttributeValue(CellStyleAttributes.GRADIENT_BACKGROUND_COLOR, this.colorScheme.headerGradientBackground);
        
        hCellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, this.colorScheme.headerForeground);
        hCellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, this.colorScheme.horizontalColumnHeaderHAlign);
        hCellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, this.colorScheme.horizontalColumnHeaderVAlign);
        
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, hCellStyle, DisplayMode.NORMAL, HSTRING);
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.NEVER_EDITABLE, DisplayMode.EDIT, HSTRING);
    }

    @objid ("0fcce662-9553-42a7-a6ff-79d0d5e7f970")
    private void configureRegistryForCUSTOM(IConfigRegistry configRegistry) {
        // Register configurations for the columns
        final String tag = CUSTOM;
        
        LabelProvider labelProvider = new LabelProvider() {
            @Override
            public String getText(Object element) {
                if(element instanceof ITextIconNatValue) {
                    return ((ITextIconNatValue) element).getValue().getText();
                }
                
                return ((IStringNatValue) element).getValue();
            }
        };
        
        // Display converter
        configRegistry.registerConfigAttribute(
                CellConfigAttributes.DISPLAY_CONVERTER, 
                new TextIconConverter(labelProvider),
                DisplayMode.NORMAL, 
                tag);
    }

}
