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

package org.modelio.platform.model.ui.nattable.parts.data.element.multi;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.convert.IDisplayConverter;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.painter.cell.AbstractCellPainter;
import org.eclipse.nebula.widgets.nattable.resize.command.RowResizeCommand;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.CellStyleUtil;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.style.IStyle;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.modelio.platform.model.ui.nattable.parts.data.texticon.TextIcon;

/**
 * Implements a NatTable cell painter for a {@link TextIcon} list.<br/>
 * The painter displays the icon on the left (if any) and the text next for each instance.<br/>
 */
@objid ("e413056e-98d5-4ca1-b539-788391985c5f")
public class MultiElementPainter extends AbstractCellPainter {
    @objid ("b5cb4371-3d97-43be-9b37-5a3ba3684b2a")
    private static final String DOT3 = "...";

    @objid ("bf40b275-7d95-47e1-ad4f-4eddb9e6f95e")
    private boolean underline;

    /**
     * Create a new painter that doesn't underline the cell's contents.
     */
    @objid ("24161e43-0863-4e7d-806b-b173bc1bfa70")
    public MultiElementPainter() {
        this(false);
    }

    /**
     * Create a new painter.
     * 
     * @param underline whether the painter should underline the cell's contents or not.
     */
    @objid ("a28dad2b-6324-4845-a686-3340563851bd")
    public MultiElementPainter(boolean underline) {
        this.underline = underline;
    }

    @objid ("01a018bd-b508-4f6c-bce8-bf8665000d0a")
    @Override
    public int getPreferredHeight(ILayerCell cell, GC gc, IConfigRegistry configRegistry) {
        setupGCFromConfig(gc, CellStyleUtil.getCellStyle(cell, configRegistry));
        
        int height = 0;
        for (TextIcon textIcon : convertDataType(cell, configRegistry)) {
            final Image image = textIcon.getIcon();
            if (image != null) {
                int imageHeight = image.getBounds().height;
                height = Math.max(height, imageHeight);
            }
        
            int textHeight = gc.textExtent(textIcon.getText()).y;
            if (this.underline) {
                textHeight += (gc.getFontMetrics().getDescent() / 2) + 2;
            }
            height = Math.max(height, textHeight);
        }
        return height;
    }

    @objid ("971bc9bf-7a37-49c7-b10f-792cf455abc0")
    @Override
    public int getPreferredWidth(ILayerCell cell, GC gc, IConfigRegistry configRegistry) {
        final IStyle cellStyle = CellStyleUtil.getCellStyle(cell, configRegistry);
        setupGCFromConfig(gc, cellStyle);
        
        int spacing = 16;
        HorizontalAlignmentEnum horizontalAlignment = cellStyle.getAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT);
        if (horizontalAlignment == HorizontalAlignmentEnum.CENTER) {
            spacing *= 2;
        }
        
        int width = spacing;
        List<TextIcon> textIcons = convertDataType(cell, configRegistry);
        for (Iterator<TextIcon> iterator = textIcons.iterator(); iterator.hasNext();) {
            TextIcon textIcon = iterator.next();
            final Image image = textIcon.getIcon();
            if (image != null) {
                int imageWidth = image.getBounds().width;
                width += imageWidth;
            }
        
            String text = textIcon.getText();
            if (iterator.hasNext()) {
                text += ", ";
            }
            int textWidth = gc.textExtent(text).x;
            width += textWidth + 3;
        }
        return width;
    }

    @objid ("55de29b9-b82e-4719-96dc-8c10c764e48d")
    @Override
    public void paintCell(ILayerCell cell, GC gc, Rectangle bounds, IConfigRegistry configRegistry) {
        // Paint background
        paintBackground(cell, gc, bounds, configRegistry);
        
        final IStyle cellStyle = CellStyleUtil.getCellStyle(cell, configRegistry);
        setupGCFromConfig(gc, cellStyle);
        
        // 'running' variables for the loop
        int curX = bounds.x; // X cursor position for layout
        int curY = bounds.y; // Y cursor position for layout
        int neededRowHeight = bounds.height; // The needed height for the row to display icons and texts
        
        List<TextIcon> textIcons = convertDataType(cell, configRegistry);
        
        for (Iterator<TextIcon> iterator = textIcons.iterator(); iterator.hasNext();) {
            TextIcon textIcon = iterator.next();
        
            String text = textIcon.getText();
            if (iterator.hasNext()) {
                text += ", ";
            }
            final Image icon = textIcon.getIcon();
            final Rectangle iconBounds = icon != null ? icon.getBounds() : new Rectangle(0, 0, 0, 0);
        
            // Compute x padding
            String displayedText = text;
        
            int textHeight = gc.getFontMetrics().getHeight() * 1; // 1 because one line
        
            neededRowHeight = Math.max(neededRowHeight, Math.max(textHeight, iconBounds.height));
        
            if (gc.textExtent(displayedText).x > bounds.width - iconBounds.width) {
                displayedText = truncateText(text, gc, bounds.width - iconBounds.width);
            }
        
            int x = curX + CellStyleUtil.getHorizontalAlignmentPadding(cellStyle, bounds, iconBounds.width + gc.textExtent(displayedText).x);
            int y = curY + CellStyleUtil.getVerticalAlignmentPadding(cellStyle, bounds, iconBounds.height);
        
            // Paint Icon
            if (icon != null) {
                gc.drawImage(icon, x, y);
            }
        
            // Paint Text
            x += iconBounds.width + 3;
            y = bounds.y + CellStyleUtil.getVerticalAlignmentPadding(cellStyle, bounds, textHeight);
            // bounds.width -= imageBounds.width + 1;
        
            gc.drawText(displayedText, x, y, SWT.DRAW_TRANSPARENT | SWT.DRAW_DELIMITER);
            if (this.underline) {
                // y = start y of text + font height
                // - half of the font descent so the underline is between the
                // baseline and the bottom
                final int underlineY = y + textHeight - (gc.getFontMetrics().getDescent() / 2);
                gc.drawLine(x, underlineY, x + gc.textExtent(text).x, underlineY);
            }
        
            curX = x + gc.textExtent(text).x + 3;
        } // end for
        
        // Now consider a row resize
        if (neededRowHeight > bounds.height) {
            int contentToCellDiff = (cell.getBounds().height - bounds.height);
            ILayer layer = cell.getLayer();
            layer.doCommand(
                    new RowResizeCommand(layer, cell.getRowPosition(), neededRowHeight + contentToCellDiff));
        }
    }

    /**
     * Convert the data value of the cell using the {@link IDisplayConverter} from the {@link IConfigRegistry}
     */
    @objid ("ad5f5ce4-b673-4abe-ac42-c918a068bab2")
    @SuppressWarnings ("unchecked")
    protected List<TextIcon> convertDataType(ILayerCell cell, IConfigRegistry configRegistry) {
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
        
        if (displayValue instanceof TextIcon) {
            return Collections.singletonList((TextIcon) displayValue);
        } else if (displayValue instanceof List) {
            return (List<TextIcon>) displayValue;
        } else {
            return Collections.singletonList(new TextIcon(String.valueOf(displayValue), null)); // $NON-NLS-1$
        }
    }

    @objid ("562d0629-ecb6-497d-b157-1109bcbe335c")
    protected Color getBackgroundColour(ILayerCell cell, IConfigRegistry configRegistry) {
        return CellStyleUtil.getCellStyle(cell, configRegistry).getAttributeValue(CellStyleAttributes.BACKGROUND_COLOR);
    }

    @objid ("5a81f1c3-99ab-4018-8eea-4c915b81de19")
    protected void paintBackground(ILayerCell cell, GC gc, Rectangle bounds, IConfigRegistry configRegistry) {
        final Color backgroundColor = getBackgroundColour(cell, configRegistry);
        if (backgroundColor != null) {
            final Color originalBackground = gc.getBackground();
            gc.setBackground(backgroundColor);
            gc.fillRectangle(bounds);
            gc.setBackground(originalBackground);
        }
    }

    /**
     * Setup the GC by the values defined in the given cell style.
     */
    @objid ("9e5b6194-6c80-4c40-b6a4-f82a93741714")
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

    /**
     * Checks if the given text is bigger than the available space. If not the given text is simply returned without modification. If the text does not fit into the available space, it will be modified by cutting and adding three dots.
     * 
     * @param text the text to compute
     * @param gc the current GC
     * @param availableLength the available space
     * @return the modified text if it is bigger than the available space or the text as it was given if it fits into the available space
     */
    @objid ("9fb4b009-9b96-4b17-8326-df11e7c59056")
    private String truncateText(String text, GC gc, int availableLength) {
        String trialText = text;
        int textWidth = gc.textExtent(trialText).x;
        
        while (textWidth > availableLength) {
            // try an optimization: estimate average char width and adjust
            // accordingly
            final double avgCharWidth = textWidth / trialText.length();
            final int nbExtraChars = 1 + (int) ((textWidth - availableLength) / avgCharWidth);
        
            final int newLength = trialText.length() - nbExtraChars;
            if (newLength > 0) {
                trialText = trialText.substring(0, newLength);
                textWidth = gc.textExtent(trialText + DOT3).x;
            } else {
                break;
            }
        }
        return trialText + DOT3;
    }

}
