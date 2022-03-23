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
package org.modelio.diagram.elements.common.edition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.modelio.diagram.elements.common.header.IHeaderFigure;
import org.modelio.diagram.elements.core.figures.labelum.LabelumFigure;
import org.modelio.diagram.elements.drawings.core.HAlign;

/**
 * {@link DirectEditManager2} for single line and multiline text fields.
 * <p>
 * Uses a text cell editor layouted like the main label,
 * with the same text alignment.
 * <p>
 * This manager may be configured with
 * <ul>
 * <li>{@link #setMultiline(boolean)}
 * <li>{@link #setWrap(boolean)}
 * </ul>
 * <p>
 * A convenience factory method for {@link LabelumFigure} : {@link #forLabelum(GraphicalEditPart, LabelumFigure, String, boolean)}
 * instantiates and configures the editor to override exactly the labelum text.
 * 
 * @since 3.6
 * @author cma
 */
@objid ("934e70a2-3160-4220-9703-bff36b0c64e0")
public final class TextDirectEditManager extends DirectEditManager2 {
    @objid ("414ea406-9149-49c7-81fb-2e437f822ec6")
    private int controlStyle;

    @objid ("cb0d0b84-d809-4a0b-af11-6018f0c134e9")
    private String initialText;

    @objid ("96dcfe0e-aa23-429d-a900-1694efe769c8")
    private boolean multiline;

    /**
     * Margins to be added when opening the {@link Control} needed for the edition.
     * <p>
     * Values may vary according to the current platform.
     * </p>
     */
    @objid ("a774bffc-7662-478a-adfa-fad86e7a604a")
    private static final Dimension MARGINS = Util.isWindows() ? new Dimension(8, 2) : new Dimension(2, 2);

    /**
     * @param source the edited edit part
     * @param locator the editor locator
     * @param halign text alignment in the Text control
     * @param initialText the initial text
     */
    @objid ("6889f954-2c6d-40dd-8ddb-355eb3443f4f")
    public  TextDirectEditManager(GraphicalEditPart source, CellEditorLocator locator, HAlign halign, String initialText) {
        // Call inherited constructor.
        // we pass no 'cell editor type' because we instantiate it ourself.
        super(source, null, locator);
        
        this.controlStyle = 0;
        this.initialText = initialText;
        
        switch (halign) {
        case Center:
            this.controlStyle |= SWT.CENTER;
            break;
        case Left:
            this.controlStyle |= SWT.LEFT;
            break;
        case Right:
            this.controlStyle |= SWT.RIGHT;
            break;
        default:
            break;
        }
        
    }

    @objid ("93035905-0c8a-4dc0-bba0-64e95cc07579")
    @Override
    protected CellEditor createCellEditorOn(Composite composite) {
        if (this.multiline) {
            return new MultilineTextCellEditor(composite, this.controlStyle);
        } else if ((this.controlStyle & SWT.WRAP) != 0) {
            return new WrappingTextCellEditor(composite, this.controlStyle);
        } else {
            return new TextCellEditor(composite, this.controlStyle);
        }
        
    }

    @objid ("dc93a2a4-0006-4821-8d63-fc6f2cd8e34d")
    @Override
    protected void initCellEditor() {
        final TextCellEditor textEdit = (TextCellEditor) getCellEditor();
        textEdit.setValue(this.initialText);
        
        final Text textControl = (Text) textEdit.getControl();
        textControl.selectAll();
        textControl.setBackground(ColorConstants.white);
        textControl.setForeground(ColorConstants.blue);
        
        super.initCellEditor();
        
    }

    /**
     * Set whether the editor is multiline.
     * @param v true for multiline, else false
     * @return this instance.
     */
    @objid ("8b8c9e7c-998b-4b49-a57a-0fe0f10f73c7")
    public TextDirectEditManager setMultiline(boolean v) {
        this.multiline = v;
        if (this.multiline) {
            this.controlStyle |= SWT.MULTI;
            this.controlStyle &= ~SWT.SINGLE;
        } else {
            this.controlStyle |= SWT.SINGLE;
            this.controlStyle &= ~SWT.MULTI;
        }
        return this;
    }

    /**
     * Set whether wrapping is enabled or not on the widget.
     * <p>
     * Note : This method does not modify the locator whose behavior must be coherent.
     * @param wrap true to enable wrap, else false.
     * @return this instance.
     */
    @objid ("6f9ea752-9fbf-4da4-974f-8661605075b4")
    public TextDirectEditManager setWrap(boolean wrap) {
        if (wrap) {
            this.controlStyle |= SWT.WRAP;
        } else {
            this.controlStyle &= ~SWT.WRAP;
        }
        return this;
    }

    /**
     * Creates a {@link TextDirectEditManager} configured for the given {@link LabelumFigure}.
     * @param source the edited edit part
     * @param headerFigure the header figure owning the Labelum
     * @param initialText the initial text
     * @param autoExpandHorizontally Whether the editor should expand horizontally or wrap text when too large.
     * @return a configured edit manager.
     */
    @objid ("3b8c96b3-5053-493e-929e-3626b6c1b1c8")
    public static TextDirectEditManager forLabelum(GraphicalEditPart source, IHeaderFigure headerFigure, String initialText, boolean autoExpandHorizontally) {
        LabelumFigure label = headerFigure.getMainLabelFigure();
        
        // Convert the Labelum text alignment to a supported HAlign
        HAlign halign;
        switch (label.getLabelAlignment()) {
        case PositionConstants.CENTER:
            halign = HAlign.Center;
            break;
        case PositionConstants.LEFT:
        case PositionConstants.ALWAYS_LEFT:
            halign = HAlign.Left;
            break;
        case PositionConstants.RIGHT:
        case PositionConstants.ALWAYS_RIGHT:
            halign = HAlign.Right;
            break;
        case PositionConstants.LEFT + PositionConstants.RIGHT:
            halign = HAlign.Left;
        break;
        default:
            halign = HAlign.Left;
            break;
        }
        
        final CellEditorLocator cellEditorLocator;
        if (label.getParent() != null) {
            cellEditorLocator = new EditorLocatorForLabelum(
                    label)
                    .setAutoExpandH(autoExpandHorizontally);
        } else {
            // Labelum is not attached to its parent, manually compute the editor's bounds
            cellEditorLocator = new CellEditorLocator() {
                @Override
                public void relocate(CellEditor cellEditor) {
                    final Rectangle rect = headerFigure.getBounds().getCopy();
                    headerFigure.translateToAbsolute(rect);
        
                    final Dimension txtMinSize = TextUtilities.INSTANCE.getStringExtents("pPF", headerFigure.getFont());
                    rect.union(rect.x, rect.y, txtMinSize.width(), txtMinSize.height());
                    cellEditor.getControl().setBounds(rect.x, rect.y, rect.width, rect.height);
                }
            };
        }
        return new TextDirectEditManager(
                source,
                cellEditorLocator,
                halign,
                initialText)
                .setWrap(!autoExpandHorizontally);
        
    }

    /**
     * Single line {@link TextCellEditor} that supports wrapping.
     * <p>
     * Uses a multiline Text with 'Enter' key validating the edition instead of adding a new line.
     * 
     * @author cma
     * @since 3.6
     */
    @objid ("a186c941-5431-4d5f-a8c4-406f02efeca5")
    private static class WrappingTextCellEditor extends TextCellEditor {
        /**
         * Creates a new text string cell editor parented under the given control.
         * The cell editor value is the string itself, which is initially the empty string.
         * Initially, the cell editor has no cell validator.
         * @param parent the parent control
         * @param controlStyle the style bits
         */
        @objid ("fcfe5fa2-9297-480e-8ed9-8cd88e2a685a")
        public  WrappingTextCellEditor(Composite parent, int controlStyle) {
            super(parent, controlStyle | SWT.WRAP | SWT.MULTI);
        }

        @objid ("1ef7d7f7-c972-4b2a-aef1-e1c92a39bc4f")
        @Override
        protected void keyReleaseOccured(KeyEvent keyEvent) {
            if (keyEvent.character == '\r') { // Return key
                // Force apply value
                fireApplyEditorValue();
                deactivate();
            } else {
                super.keyReleaseOccured(keyEvent);
            }
            
        }

    }

    /**
     * {@link CellEditorLocator} that will make the editor override a {@link LabelumFigure} label.
     * @author cma
     */
    @objid ("94b6e0d6-a140-4a12-a21b-aac4fc8f330f")
    public static final class EditorLocatorForLabelum implements CellEditorLocator {
        @objid ("c7cabbe3-c7db-4191-aede-0f81b8a5e090")
        private boolean autoExpand;

        @objid ("dd64bfed-3751-4312-adcb-d5d568cba93e")
        private final LabelumFigure figure;

        /**
         * @param figure the label figure to override.
         */
        @objid ("7319e4c6-de23-4239-8d9b-08d7586eb08b")
        public  EditorLocatorForLabelum(LabelumFigure figure) {
            this.figure = figure;
        }

        /**
         * @param expand whether the editor should expand horizontally when too large.
         * @return this instance for chaining calls.
         */
        @objid ("edae478b-b33a-4fc3-a62c-3e9cdfd3c0f6")
        public EditorLocatorForLabelum setAutoExpandH(boolean expand) {
            this.autoExpand = expand;
            return this;
        }

        @objid ("37dd044b-d2c7-44b4-b55a-c16c4cc575c7")
        @Override
        public void relocate(CellEditor cellEditor) {
            // Force the figure text to the current one.
            // The text figure content is automatically rollbacked by the DirectEditManager.
            this.figure.setText((String) cellEditor.getValue());
            
            final Rectangle figBounds = this.figure.getBounds();
            final Rectangle labelRect = this.autoExpand ? this.figure.getTextBounds() : this.figure.getSubStringBounds();
            final Rectangle absLabelRect = labelRect.getCopy();
            this.figure.translateToAbsolute(absLabelRect);
            
            int widthHint = this.autoExpand ? -1 : figBounds.width;
            Dimension labelPrefSize = this.figure.getPreferredSize(widthHint, -1).getCopy();
            
            Insets insets = this.figure.getInsets();
            labelPrefSize.shrink(insets.getWidth(), insets.getHeight());
            
            final Dimension txtMinSize = TextUtilities.INSTANCE.getStringExtents("pPF", this.figure.getFont());
            final Dimension editorSize = Dimension.max(labelPrefSize, txtMinSize);
            
            absLabelRect.union(absLabelRect.x, absLabelRect.y, editorSize.width(), editorSize.height());
            
            Control textControl = cellEditor.getControl();
            textControl.setFont(this.figure.getTextFont());
            textControl.setBounds(
                    absLabelRect.x,
                    absLabelRect.y,
                    absLabelRect.width + TextDirectEditManager.MARGINS.width,
                    absLabelRect.height + TextDirectEditManager.MARGINS.height);
            
        }

    }

}
