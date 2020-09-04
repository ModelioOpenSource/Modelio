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

package org.modelio.core.ui.nattable.parts.data.texticon;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.convert.IDisplayConverter;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.painter.cell.BackgroundPainter;
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

/**
 * Implements a NatTable cell painter for {@link TextIcon} instances.<br/>
 * The painter displays the icon on the left (if any) and the text next.<br/>
 */
@objid ("f3dd9ca9-97e8-48bd-bcdc-2f9a91438f2d")
public class TextIconPainter extends BackgroundPainter {
    @objid ("c5fd6a26-2809-45ae-9947-5bbf05a355ab")
    private static final String DOT3 = "...";

    @objid ("82ccd576-bf1d-4b53-b5a3-87d9749fe82f")
    private boolean underline;

    /**
     * Create a new painter that doesn't underline the cell's contents.
     */
    @objid ("c6db16b3-cf29-4f5a-bfec-94d935ac54cd")
    public TextIconPainter() {
        this(false);
    }

    /**
     * Create a new painter.
     * 
     * @param underline whether the painter should underline the cell's contents or not.
     */
    @objid ("cd3ffb0b-be4e-4b52-88ec-b11ed4007c6f")
    public TextIconPainter(boolean underline) {
        this.underline = underline;
    }

    @objid ("7b4cf244-3406-4df0-8614-2e6a44b8c01b")
    @Override
    public int getPreferredHeight(ILayerCell cell, GC gc, IConfigRegistry configRegistry) {
        final Image image = getImage(cell, configRegistry);
        final int imageHeight = (image != null) ? image.getBounds().height : 0;
        
        setupGCFromConfig(gc, CellStyleUtil.getCellStyle(cell, configRegistry));
        int textHeight = gc.textExtent(convertDataType(cell, configRegistry).getText()).y;
        if (this.underline) {
            textHeight += (gc.getFontMetrics().getDescent() / 2) + 2;
        }
        return Math.max(imageHeight, textHeight);
    }

    @objid ("70183a80-595e-4fcd-a12b-8d157b83cd79")
    @Override
    public int getPreferredWidth(ILayerCell cell, GC gc, IConfigRegistry configRegistry) {
        final IStyle cellStyle = CellStyleUtil.getCellStyle(cell, configRegistry);
        setupGCFromConfig(gc, cellStyle);
        
        final Image image = getImage(cell, configRegistry);
        final int imageWidth = (image != null) ? image.getBounds().width : 0;
        
        final int textWidth = gc.textExtent(convertDataType(cell, configRegistry).getText()).x;
        
        int spacing = 16;
        HorizontalAlignmentEnum horizontalAlignment = cellStyle.getAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT);
        if (horizontalAlignment == HorizontalAlignmentEnum.CENTER) {
            spacing *= 2;
        }
        return imageWidth + textWidth + 3 + spacing;
    }

    @objid ("d1fd988b-0aad-42c0-a62e-cc8a4eff4fcf")
    @Override
    public void paintCell(ILayerCell cell, GC gc, Rectangle bounds, IConfigRegistry configRegistry) {
        // Paint background
        super.paintCell(cell, gc, bounds, configRegistry);
        
        final IStyle cellStyle = CellStyleUtil.getCellStyle(cell, configRegistry);
        
        final TextIcon textIcon = convertDataType(cell, configRegistry);
        final String text = textIcon.getText();
        final Image icon = textIcon.getIcon();
        final Rectangle imageBounds = icon != null ? icon.getBounds() : new Rectangle(0, 0, 0, 0);
        
        // Compute x padding
        String displayedText = text;
        setupGCFromConfig(gc, cellStyle);
        
        int fontHeight = gc.getFontMetrics().getHeight();
        int textHeight = fontHeight * 1 /* one line */;
        
        Point textExtent = gc.textExtent(displayedText);
        if (textExtent.x > bounds.width - imageBounds.width) {
            displayedText = truncateText(text, gc, bounds.width - imageBounds.width);
        }
        
        int x = bounds.x + CellStyleUtil.getHorizontalAlignmentPadding(cellStyle, bounds, imageBounds.width + textExtent.x);
        int y = bounds.y + CellStyleUtil.getVerticalAlignmentPadding(cellStyle, bounds, imageBounds.height);
        
        // If the content height is bigger than the available row height
        // we're extending the row height
        
        // int contentHeight = textExtent.y + (this.lineSpacing * (numberOfNewLines - 1)) + (this.spacing * 2);
        int contentHeight = icon != null ? Math.max(icon.getBounds().height, textHeight) : textHeight;
        if (performRowResize(contentHeight, bounds)) {
            int contentToCellDiff = (cell.getBounds().height - bounds.height);
            ILayer layer = cell.getLayer();
            layer.doCommand(
                    new RowResizeCommand(layer, cell.getRowPosition(), contentHeight + contentToCellDiff));
        }
        
        // Paint Icon
        if (icon != null) {
            gc.drawImage(icon, x, y);
        }
        
        // Paint Text
        x += imageBounds.width + 3;
        y = bounds.y + CellStyleUtil.getVerticalAlignmentPadding(cellStyle, bounds, textHeight);
        bounds.width -= imageBounds.width + 1;
        
        gc.drawText(displayedText, x, y, SWT.DRAW_TRANSPARENT | SWT.DRAW_DELIMITER);
        if (this.underline) {
            // check and draw underline and strikethrough separately so it is
            // possible to combine both
            if (this.underline) {
                // y = start y of text + font height
                // - half of the font descent so the underline is between the
                // baseline and the bottom
                final int underlineY = y + fontHeight - (gc.getFontMetrics().getDescent() / 2);
                gc.drawLine(x, underlineY, x + gc.textExtent(text).x, underlineY);
            }
        }
    }

    @objid ("7eed7c00-04a0-43e3-a6ed-fc79a2038c72")
    private Image getImage(ILayerCell cell, IConfigRegistry configRegistry) {
        final TextIcon textIcon = convertDataType(cell, configRegistry);
        return textIcon.getIcon();
    }

    /**
     * Checks if the given text is bigger than the available space. If not the given text is simply returned without modification. If the text does not fit into the available space, it will be modified by cutting and adding three dots.
     * 
     * @param text the text to compute
     * @param gc the current GC
     * @param availableLength the available space
     * @return the modified text if it is bigger than the available space or the text as it was given if it fits into the available space
     */
    @objid ("68d0a9b7-7c6c-4bc3-bd22-a79c9f6cbe59")
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
                textWidth = gc.textExtent(trialText + TextIconPainter.DOT3).x;
            } else {
                break;
            }
        }
        return trialText + TextIconPainter.DOT3;
    }

    /**
     * Checks if a row resize needs to be triggered.
     * 
     * @param contentHeight The necessary height to show the content completely
     * @param rectangle The available rectangle to render to
     * @return <code>true</code> if a row resize needs to be performed, <code>false</code> if not
     */
    @objid ("6dde4c40-9c13-4690-b54e-7cbec526bcfe")
    protected boolean performRowResize(int contentHeight, Rectangle rectangle) {
        return (contentHeight > rectangle.height);
    }

    /**
     * Convert the data value of the cell using the {@link IDisplayConverter} from the {@link IConfigRegistry}
     */
    @objid ("c9691c10-fdbf-4239-baac-d9652eea4d36")
    protected TextIcon convertDataType(ILayerCell cell, IConfigRegistry configRegistry) {
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
            return (TextIcon) displayValue;
        } else {
            return new TextIcon(String.valueOf(displayValue), null);
        }
    }

    /**
     * Setup the GC by the values defined in the given cell style.
     */
    @objid ("ff0d7e4e-0cae-45a8-8089-e20e878a5040")
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
