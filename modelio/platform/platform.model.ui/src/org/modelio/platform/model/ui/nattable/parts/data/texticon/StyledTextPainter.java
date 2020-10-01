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

package org.modelio.platform.model.ui.nattable.parts.data.texticon;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.convert.IDisplayConverter;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.painter.cell.AbstractCellPainter;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.CellStyleUtil;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.style.IStyle;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;

/**
 * NAT Cell painter for {@link StyledString} .
 * 
 * @author cma
 * @deprecated experimental, not yet used
 * @since Valkyrie 3.8
 */
@objid ("d5c2d98d-7f2f-490a-8a3e-1b8e52025d78")
@Deprecated
public class StyledTextPainter extends AbstractCellPainter {
    @objid ("b29b3086-64c1-4ead-af0e-2d5b5a6f7128")
    private final TextLayout textDrawer;

    @objid ("fb28ed8b-34b0-4037-a8ee-f84590485110")
    private final IStyledLabelProvider labelProvider;

    @objid ("a625391e-65c7-4302-a6a7-43324cb71594")
    public StyledTextPainter(TextLayout textDrawer, IStyledLabelProvider labelProvider) {
        this.textDrawer = textDrawer;
        this.labelProvider = labelProvider;
    }

    @objid ("2da6f7a5-e9b8-4f6f-b14e-1a4dc86bda65")
    private boolean isSelected(ILayerCell cell) {
        return (cell.getDisplayMode() == DisplayMode.SELECT
                                                || cell.getDisplayMode() == DisplayMode.SELECT_HOVER);
    }

    @objid ("58f5e449-c43a-484d-a5c2-799c91fda12b")
    @Override
    public void paintCell(ILayerCell cell, GC gc, Rectangle bounds, IConfigRegistry configRegistry) {
        final IStyle cellStyle = CellStyleUtil.getCellStyle(cell, configRegistry);
        final StyledString text = convertDataType(cell, configRegistry);
        
        updateTextLayout(text , cellStyle, false);
        
        // remember colors to restore the GC later
        Color oldForeground = gc.getForeground();
        Color oldBackground = gc.getBackground();
        
        boolean applyColors = ! isSelected(cell);
        
        if (applyColors ) {
            final Color foreground = cellStyle.getAttributeValue(CellStyleAttributes.FOREGROUND_COLOR);
            final Color background = cellStyle.getAttributeValue(CellStyleAttributes.BACKGROUND_COLOR);
        
            if (foreground != null) {
                gc.setForeground(foreground);
            }
        
            if (background != null) {
                gc.setBackground(background);
            }
        }
        
        Rectangle textBounds = cell.getBounds();
        if (textBounds != null) {
        
            Rectangle layoutBounds = this.textDrawer.getBounds();
        
            HorizontalAlignmentEnum horizontalAlignment = cellStyle
                    .getAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT);
        
            int x = textBounds.x;
            switch (horizontalAlignment) {
            case CENTER:
                x = textBounds.x + (textBounds.width - this.textDrawer.getBounds().width)/2;
                break;
            case RIGHT:
                x = textBounds.x + textBounds.width - this.textDrawer.getBounds().width;
                break;
            case LEFT:
            default:
                break;
            }
        
            int y = textBounds.y
                    + Math.max(0, (textBounds.height - layoutBounds.height) / 2);
        
            Rectangle saveClipping = gc.getClipping();
            gc.setClipping(textBounds);
            this.textDrawer.draw(gc, x, y);
            gc.setClipping(saveClipping);
        }
        
        if (applyColors) {
            gc.setForeground(oldForeground);
            gc.setBackground(oldBackground);
        }
    }

    @objid ("1d5b3eb6-72cb-4958-9e58-1516886220ca")
    @Override
    public int getPreferredWidth(ILayerCell cell, GC gc, IConfigRegistry configRegistry) {
        final IStyle cellStyle = CellStyleUtil.getCellStyle(cell, configRegistry);
        StyledString text = convertDataType(cell, configRegistry);
        updateTextLayout(text , cellStyle, false);
        return this.textDrawer.getBounds().width;
    }

    @objid ("29c35d0d-2119-4d81-b7e8-6bcbf2b12df8")
    @Override
    public int getPreferredHeight(ILayerCell cell, GC gc, IConfigRegistry configRegistry) {
        final IStyle cellStyle = CellStyleUtil.getCellStyle(cell, configRegistry);
        StyledString text = convertDataType(cell, configRegistry);
        updateTextLayout(text , cellStyle, false);
        return this.textDrawer.getBounds().height;
    }

    /**
     * Convert the data value of the cell using the {@link IDisplayConverter}
     * from the {@link IConfigRegistry}
     */
    @objid ("89f1b72e-d628-4790-b85d-3752a311fd58")
    protected StyledString convertDataType(ILayerCell cell, IConfigRegistry configRegistry) {
        Object canonicalValue = cell.getDataValue();
        Object displayValue;
        
        IDisplayConverter displayConverter = configRegistry.getConfigAttribute(
                CellConfigAttributes.DISPLAY_CONVERTER,
                cell.getDisplayMode(),
                cell.getConfigLabels().getLabels());
        
        if (displayConverter != null) {
            displayValue = displayConverter.canonicalToDisplayValue(cell, configRegistry, canonicalValue);
        } else {
            displayValue = canonicalValue;
        }
        
        if (displayValue instanceof StyledString) {
            return (StyledString) displayValue;
        } else if (displayValue instanceof String) {
            return new StyledString((String) displayValue);
        } else {
            return this.labelProvider.getStyledText(displayValue); 
        }
    }

    /**
     * @param layout
     * @param cell
     * @param applyColors
     * 
     * @return the text width delta (0 if the text layout contains no other font)
     */
    @objid ("24e93a97-08fa-4f42-9798-b20131190fee")
    private int updateTextLayout(StyledString text, IStyle cellStyle, boolean applyColors) {
        this.textDrawer.setStyle(null, 0, Integer.MAX_VALUE); // clear old styles
        final Font font = cellStyle.getAttributeValue(CellStyleAttributes.FONT);
        
        if (false) {
            HorizontalAlignmentEnum horizontalAlignment = cellStyle
                    .getAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT);
        
            switch (horizontalAlignment) {
            default:
            case LEFT:
                this.textDrawer.setAlignment(SWT.LEFT);
                break;
            case CENTER:
                this.textDrawer.setAlignment(SWT.CENTER);
                break;
            case RIGHT:
                this.textDrawer.setAlignment(SWT.RIGHT);
                break;
            }
        }
        
        this.textDrawer.setFont(font);
        this.textDrawer.setText(text.getString());
        
        int originalTextWidth = this.textDrawer.getBounds().width; // text width without any styles
        boolean containsOtherFont= false;
        
        StyleRange[] styleRanges = text.getStyleRanges();
        if (styleRanges != null) { // user didn't fill styled ranges
            for (int i = 0; i < styleRanges.length; i++) {
                StyleRange curr = prepareStyleRange(styleRanges[i], applyColors);
                this.textDrawer.setStyle(curr, curr.start, curr.start + curr.length - 1);
                if (curr.font != null) {
                    containsOtherFont= true;
                }
            }
        }
        int textWidthDelta = 0;
        if (containsOtherFont) {
            textWidthDelta = this.textDrawer.getBounds().width - originalTextWidth;
        }
        return textWidthDelta;
    }

    /**
     * Prepares the given style range before it is applied to the label. This method makes sure that
     * no colors are drawn when the element is selected.
     * The current version of the {@link StyledCellLabelProvider} will also ignore all font settings on the
     * style range. Clients can override.
     * 
     * @param styleRange the style range to prepare. the style range element must not be modified
     * @param applyColors specifies if colors should be applied.
     * @return
     * returns the style range to use on the label
     */
    @objid ("0d9cca38-5047-4d9f-a41f-8ef320dd84cd")
    protected StyleRange prepareStyleRange(StyleRange styleRange, boolean applyColors) {
        // if no colors apply or font is set, create a clone and clear the
        // colors and font
        if (!applyColors && (styleRange.foreground != null || styleRange.background != null)) {
            styleRange = (StyleRange) styleRange.clone();
            if (!applyColors) {
                styleRange.foreground = null;
                styleRange.background = null;
            }
        }
        return styleRange;
    }

    /**
     * Setup the GC by the values defined in the given cell style.
     */
    @objid ("c035479d-ef4d-441e-9c52-81b80fdba1cc")
    private void setupGCFromConfig(GC gc, IStyle cellStyle) {
        final Color fg = cellStyle.getAttributeValue(CellStyleAttributes.FOREGROUND_COLOR);
        final Color bg = cellStyle.getAttributeValue(CellStyleAttributes.BACKGROUND_COLOR);
        final Font font = cellStyle.getAttributeValue(CellStyleAttributes.FONT);
        
        gc.setAntialias(GUIHelper.DEFAULT_ANTIALIAS);
        gc.setTextAntialias(GUIHelper.DEFAULT_TEXT_ANTIALIAS);
        gc.setFont(font);
        gc.setForeground(fg != null ? fg : GUIHelper.COLOR_LIST_FOREGROUND);
        gc.setBackground(bg != null ? bg : GUIHelper.COLOR_LIST_BACKGROUND);
    }

}
