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

package org.modelio.core.ui.nattable.parts.data.element.single;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IToolTipProvider;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
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
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.NatValueWrappingLabelProvider;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Implements a NatTable cell painter for Model elements represented by a {@link MRef} or {@link MObject} instance.
 * <p>
 * The painter displays the metaclass icon and the element name.<br/>
 * Null MRef , null metaclasses, null names are supported.
 * 
 * @author phv
 */
@objid ("cedf4bdf-a51e-4106-bbac-f378818e4758")
public class ElementPainter extends BackgroundPainter implements IToolTipProvider {
    @objid ("eb274e49-a13d-4b88-b604-fcf876f92705")
    private static final String DOT3 = "...";

    @objid ("8db1aa57-1e14-480b-92e3-51638bfea36e")
    private boolean underline;

    @objid ("6c0a9f1a-aeaa-4e7e-b385-47269df573f4")
    private final ILabelProvider labelProvider;

    /**
     * Create a new painter.
     * @param labelProvider a label provider to delegate image/text computing to.
     * @param underline whether the painter should underline the cell's contents or not.
     */
    @objid ("f3734ca0-3837-4f7a-91ba-ba2e344f66d8")
    public ElementPainter(ILabelProvider labelProvider, boolean underline) {
        this.labelProvider = new NatValueWrappingLabelProvider(Objects.requireNonNull(labelProvider));
        this.underline = underline;
    }

    /**
     * Create a new painter that underlines the cell's contents.
     */
    @objid ("61e8a89c-cb69-45f2-b197-59fca85b262f")
    public ElementPainter(ILabelProvider labelProvider) {
        this(labelProvider, true);
    }

    @objid ("d42708f7-7c8d-4640-8885-ab25c04e6230")
    @Override
    public int getPreferredHeight(ILayerCell cell, GC gc, IConfigRegistry configRegistry) {
        final Image image = getImage(cell, configRegistry);
        final int imageHeight = (image != null) ? image.getBounds().height : 0;
        
        setupGCFromConfig(gc, CellStyleUtil.getCellStyle(cell, configRegistry));
        int textHeight = gc.textExtent(getText(cell, configRegistry)).y;
        if (this.underline) {
            textHeight += (gc.getFontMetrics().getDescent() / 2) + 2;
        }
        return Math.max(imageHeight, textHeight);
    }

    @objid ("368edcd5-9c67-4326-8356-757f37b3c106")
    @Override
    public int getPreferredWidth(ILayerCell cell, GC gc, IConfigRegistry configRegistry) {
        final IStyle cellStyle = CellStyleUtil.getCellStyle(cell, configRegistry);
        setupGCFromConfig(gc, cellStyle);
        
        final Image image = getImage(cell, configRegistry);
        final int imageWidth = (image != null) ? image.getBounds().width : 0;
        
        final int textWidth = gc.textExtent(getText(cell, configRegistry)).x;
        
        int spacing = 16;
        HorizontalAlignmentEnum horizontalAlignment = cellStyle.getAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT);
        if (horizontalAlignment == HorizontalAlignmentEnum.CENTER) {
            spacing *= 2;
        }
        return imageWidth + textWidth + 3 + spacing;
    }

    @objid ("4916d4b3-614d-4ea2-90ab-4a0b31dd75d1")
    @Override
    public void paintCell(ILayerCell cell, GC gc, Rectangle bounds, IConfigRegistry configRegistry) {
        // Paint background
        super.paintCell(cell, gc, bounds, configRegistry);
        
        final IStyle cellStyle = CellStyleUtil.getCellStyle(cell, configRegistry);
        
        final String text = getText(cell, configRegistry);
        final Image icon = getImage(cell, configRegistry);
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

    @objid ("7a4606e2-194d-4a76-bb2e-d793acce8e3f")
    protected Image getImage(ILayerCell cell, IConfigRegistry configRegistry) {
        Object data = INatValue.getValue(cell.getDataValue());
        
        if (data instanceof MRef) {
            MRef mref = (MRef) data;
            if (mref.mc != null) {
                return MetamodelImageService.getIcon(mref.mc);
            }
        } else {
            return this.labelProvider.getImage(data);
        }
        return null;
    }

    /**
     * Setup the GC by the values defined in the given cell style.
     */
    @objid ("52142198-062e-464e-b85f-53c8b3116bed")
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
     * @param text the text to compute
     * @param gc the current GC
     * @param availableLength the available space
     * @return the modified text if it is bigger than the available space or the text as it was given if it fits into the available space
     */
    @objid ("a0ab28f6-b5e6-4fbd-8aa0-58ccda4e80e0")
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
                textWidth = gc.textExtent(trialText + ElementPainter.DOT3).x;
            } else {
                break;
            }
        }
        return trialText + ElementPainter.DOT3;
    }

    @objid ("bdc1f617-3ce5-48dd-b45d-e9679b28c263")
    @Override
    public String getToolTipText(Object element) {
        Object data = INatValue.getValue(element);
        
        if (data instanceof MRef) {
            MRef mref = (MRef) data;
            return mref.name;
        } else if (data instanceof MObject) {
            String name = this.labelProvider.getText(data);
            String description;
            if (data instanceof NoteType) {
                description = ModuleI18NService.getDescription((NoteType) data);
            } else if (data instanceof Profile) {
                description = ModuleI18NService.getDescription((Profile) data);
            } else if (data instanceof PropertyDefinition) {
                description = ModuleI18NService.getDescription((PropertyDefinition) data);
            } else if (data instanceof ResourceType) {
                description = ModuleI18NService.getDescription((ResourceType) data);
            } else if (data instanceof Stereotype) {
                description = ModuleI18NService.getDescription((Stereotype) data);
            } else if (data instanceof TagType) {
                description = ModuleI18NService.getDescription((TagType) data);
            } else if (data instanceof ModelElement) {
                description = ((ModelElement) data).getNoteContent("ModelerModule", ModelElement.MQNAME, "description");
            } else {
                description = null;
            }
        
            if (description != null) {
                StringBuilder ret = new StringBuilder();
                ret.append(name);
                ret.append("\n");
                ret.append(description);
                return ret.toString();
            }
            return name;
        }
        return null;
    }

    @objid ("1c5c9c30-443e-4d19-9d84-314a18438cee")
    protected String getText(ILayerCell cell, IConfigRegistry configRegistry) {
        return this.labelProvider.getText(cell.getDataValue());
    }

    /**
     * Checks if a row resize needs to be triggered.
     * @param contentHeight The necessary height to show the content completely
     * @param rectangle The available rectangle to render to
     * @return <code>true</code> if a row resize needs to be performed, <code>false</code> if not
     */
    @objid ("878dd595-124c-4b32-a2b8-78ade11aa629")
    protected boolean performRowResize(int contentHeight, Rectangle rectangle) {
        return (contentHeight > rectangle.height);
    }

}
