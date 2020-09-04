/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.convert.IDisplayConverter;
import org.eclipse.nebula.widgets.nattable.layer.cell.CellDisplayConversionUtils;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.painter.cell.AbstractCellPainter;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.CellStyleUtil;
import org.eclipse.nebula.widgets.nattable.style.IStyle;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.modelio.editors.richnote.gui.nattable.IDocumentNatValue;
import org.modelio.editors.richnote.helper.RichNoteLabelProvider;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Implements a NatTable cell painter for Model elements represented by a MRef
 * instance.<br/>
 * The painter displays the metaclass icon and the element name.<br/>
 * Null MRef , null metaclasses, null names are supported.
 */
@objid ("7ad8cd97-f6a9-474c-b42f-d879f80031b9")
public class DocumentPainter extends AbstractCellPainter {
    @objid ("b38e4226-b7d9-4161-89c5-f17611510668")
    private boolean calculate = true;

    @objid ("73552ecf-286c-4fa4-839b-af5dcbb7a6a8")
    private static final String DOT3 = "...";

    @objid ("3b082aa9-9254-4fc0-a41d-e551ca4966af")
    private ICoreSession session;

    @objid ("d6d1d0d1-1165-4e46-ac63-5122d97023de")
    @Override
    public void paintCell(ILayerCell cell, GC gc, Rectangle bounds, IConfigRegistry configRegistry) {
        final IStyle cellStyle = CellStyleUtil.getCellStyle(cell, configRegistry);
        
        // Paint background
        paintBackground(cell, gc, bounds, configRegistry);
        
        // Paint Icon
        final Image image = getImage(cell);
        if (image != null) {
            final Rectangle imageBounds = image.getBounds();
        
            gc.drawImage(image, bounds.x + CellStyleUtil.getHorizontalAlignmentPadding(cellStyle, bounds, imageBounds.width),
                    bounds.y + CellStyleUtil.getVerticalAlignmentPadding(cellStyle, bounds, imageBounds.height));
        
            bounds.x += imageBounds.width + 1;
            bounds.width -= imageBounds.width + 1;
        } else {
            // Paint Text
            final String text = convertDataType(cell, configRegistry);
            if (text != null) {
                paintText(cellStyle, gc, bounds, text);
            }
        }
    }

    @objid ("959ff72c-cda2-49ee-8f65-dd1e0137d4df")
    private void paintText(IStyle cellStyle, GC gc, Rectangle bounds, final String text) {
        String displayedText = text;
        setupGCFromConfig(gc, cellStyle);
        
        final Rectangle originalClipping = gc.getClipping();
        gc.setClipping(bounds.intersection(originalClipping));
        
        final int fontHeight = gc.getFontMetrics().getHeight();
        final int contentHeight = fontHeight * 1 /* one line */;
        final int contentWidth = Math.min(gc.textExtent(text).x, bounds.width);
        
        if (gc.textExtent(displayedText).x > bounds.width) {
            displayedText = truncateText(text, gc, bounds.width);
        }
        
        gc.drawText(text, bounds.x + CellStyleUtil.getHorizontalAlignmentPadding(cellStyle, bounds, contentWidth),
                bounds.y + CellStyleUtil.getVerticalAlignmentPadding(cellStyle, bounds, contentHeight),
                SWT.DRAW_TRANSPARENT | SWT.DRAW_DELIMITER);
        
        gc.setClipping(originalClipping);
    }

    @objid ("6075e213-c1f2-4e68-9f68-a05c43c3b1b8")
    protected Color getBackgroundColour(ILayerCell cell, IConfigRegistry configRegistry) {
        return CellStyleUtil.getCellStyle(cell, configRegistry).getAttributeValue(CellStyleAttributes.BACKGROUND_COLOR);
    }

    @objid ("0bbf5b96-49bb-45a5-8c8f-fc6f01dfdb1a")
    protected void paintBackground(ILayerCell cell, GC gc, Rectangle bounds, IConfigRegistry configRegistry) {
        final Color backgroundColor = getBackgroundColour(cell, configRegistry);
        if (backgroundColor != null) {
            final Color originalBackground = gc.getBackground();
            gc.setBackground(backgroundColor);
            gc.fillRectangle(bounds);
            gc.setBackground(originalBackground);
        }
    }

    @objid ("e4182e4e-14a7-4e2c-9e0c-685272e1d117")
    @Override
    public int getPreferredWidth(ILayerCell cell, GC gc, IConfigRegistry configRegistry) {
        final Image image = getImage(cell);
        final int imageWidth = (image != null) ? image.getBounds().width : 0;
        
        setupGCFromConfig(gc, CellStyleUtil.getCellStyle(cell, configRegistry));
        final int textWidth = gc.textExtent(convertDataType(cell, configRegistry)).x;
        return Math.max(imageWidth, textWidth);
    }

    @objid ("ce767127-61c9-469d-bfa6-596c211b86b1")
    @Override
    public int getPreferredHeight(ILayerCell cell, GC gc, IConfigRegistry configRegistry) {
        final Image image = getImage(cell);
        final int imageHeight = (image != null) ? image.getBounds().height : 0;
        
        setupGCFromConfig(gc, CellStyleUtil.getCellStyle(cell, configRegistry));
        final int textHeight = gc.textExtent(convertDataType(cell, configRegistry)).y;
        return Math.max(imageHeight, textHeight);
    }

    @objid ("73b52dbe-6009-44a0-a6ad-4fa2934ac73d")
    private Image getImage(ILayerCell cell) {
        Object dataValue = cell.getDataValue();
        if (dataValue instanceof IDocumentNatValue) {
            dataValue = ((IDocumentNatValue) dataValue).getValue();
        }
        
        if (dataValue instanceof MRef) {
            final MRef mref = (MRef) dataValue;
            if (mref.mc != null && !mref.mc.isEmpty()) {
                final MObject externDocument = this.session.getModel().findByRef(mref);
                if (externDocument != null && externDocument instanceof Document) {
                    return RichNoteLabelProvider.getIcon((Document) externDocument);
                }
            }
        } else if (dataValue instanceof Document) {
            final Document externDocument = (Document) dataValue;
            return RichNoteLabelProvider.getIcon(externDocument);
        }
        return null;
    }

    /**
     * Convert the data value of the cell using the {@link IDisplayConverter}
     * from the {@link IConfigRegistry}
     */
    @objid ("7be52748-d411-4e51-b21f-006cbc844361")
    protected String convertDataType(ILayerCell cell, IConfigRegistry configRegistry) {
        return CellDisplayConversionUtils.convertDataType(cell, configRegistry);
    }

    /**
     * Setup the GC by the values defined in the given cell style.
     */
    @objid ("d404790a-9111-4403-9f66-5562cad147fd")
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
     * Checks if the given text is bigger than the available space. If not the
     * given text is simply returned without modification. If the text does not
     * fit into the available space, it will be modified by cutting and adding
     * three dots.
     * @param text the text to compute
     * @param gc the current GC
     * @param availableLength the available space
     * @return the modified text if it is bigger than the available space or the
     * text as it was given if it fits into the available space
     */
    @objid ("f1ce925b-b839-463e-87ea-54fd80df551b")
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
                textWidth = gc.textExtent(trialText + DocumentPainter.DOT3).x;
            }
        }
        return trialText + DocumentPainter.DOT3;
    }

    /**
     * Build a new painter instance.
     * @param session the model session, needed for mref lookup.
     */
    @objid ("1d70afe4-8326-4df2-8573-c14d9497e4d1")
    public DocumentPainter(ICoreSession session) {
        this.session = session;
    }

}
