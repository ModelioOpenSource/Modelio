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

import java.util.function.Consumer;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;

/**
 * Cell editor locator for a {@link DirectEditManager2} for text fields.
 * <p>
 * The cell will exactly overwrite the given label figure and use the same font as the figure.
 * <p>
 * The implementation forces the figure text to the current one in order to get the bounds to use.
 * The text figure content is automatically rollbacked by the DirectEditManager.
 */
@objid ("9b7f72a0-3e1a-4875-a73a-6176bc29e831")
public final class EditorLocatorForLabelFigure implements CellEditorLocator {
    @objid ("f83ccf76-c5bb-4b99-b887-0dbb980ec9e2")
    private boolean autoExpand;

    @objid ("75cdd9c6-d1ac-496a-ac24-8c7d20ccaafc")
    private final IFigure figure;

    @objid ("08b0abee-1264-4be4-9c37-b57ef9c2b452")
    private final Consumer<String> figureLabelSetter;

    @objid ("20ab8602-eb65-4d7f-ba0a-3c0b5dc3abcb")
    private Supplier<Font> fontGetter;

    @objid ("51fd617e-41d3-4bf8-94d8-98a7da24beeb")
    private final Dimension minSize = new Dimension();

    /**
     * Margins to be added when opening the {@link Control} needed for the edition.
     * <p>
     * Values may vary according to the current platform.
     * </p>
     */
    @objid ("9919c5b6-23e3-4fd0-a5a8-d22018481ad8")
    private static final Dimension MARGINS = Util.isWindows() ? new Dimension(8, 2) : new Dimension(2, 2);

    /**
     * Standard constructor.
     * 
     * @param figure the label figure to overwrite
     * @param figureLabelSetter a lambda that must set the label figure text.
     * This method will be called at each {@link #relocate(CellEditor)} just after having called 'figureLabelSetter'.
     */
    @objid ("8b9f7a06-695e-45c8-a0b6-51e7717d5963")
    public EditorLocatorForLabelFigure(IFigure figure, Consumer<String> figureLabelSetter) {
        this.figure = figure;
        this.figureLabelSetter = figureLabelSetter;
    }

    @objid ("83ddab97-2e53-4bd3-a049-82f9d70d2328")
    @Override
    public void relocate(CellEditor cellEditor) {
        // Force the figure text to the current one.
        // The text figure content is automatically rollbacked by the DirectEditManager.
        this.figureLabelSetter.accept((String) cellEditor.getValue());
        
        // Get the figure absolute bounds after text change
        final Rectangle absLabelRect = this.figure.getBounds().getCopy();
        this.figure.translateToAbsolute(absLabelRect);
        
        // Some figures may adjust their font to the text and available size.
        Font editorFont = this.fontGetter != null ? this.fontGetter.get() : this.figure.getFont();
        
        int widthHint = this.autoExpand ? -1 : this.figure.getBounds().width;
        Dimension labelPrefSize = this.figure.getPreferredSize(widthHint, -1);
        
        final Dimension txtMinSize = TextUtilities.INSTANCE.getStringExtents("pPF", editorFont);
        final Dimension absMinSize = txtMinSize.union(labelPrefSize).union(this.minSize);
        
        absLabelRect.union(absLabelRect.x, absLabelRect.y, absMinSize.width(), absMinSize.height());
        
        Control swtControl = cellEditor.getControl();
        swtControl.setFont(editorFont);
        swtControl.setBounds(
                absLabelRect.x,
                absLabelRect.y,
                absLabelRect.width + EditorLocatorForLabelFigure.MARGINS.width,
                absLabelRect.height + EditorLocatorForLabelFigure.MARGINS.height);
    }

    /**
     * @param expand whether the editor should expand horizontally if the text is too wide.
     * @return this instance to chain calls.
     */
    @objid ("220b0a27-ce6e-4a0f-b375-8b8d0a6e9e3d")
    public EditorLocatorForLabelFigure setAutoExpand(boolean expand) {
        this.autoExpand = expand;
        return this;
    }

    /**
     * Set a font provider.
     * <p>
     * This method will be called at each {@link #relocate(CellEditor)}
     * just after having called 'figureLabelSetter'.
     * This useful when edited figures adjust their font to the text and available size.
     * 
     * @param fontGetter lambda that returns the font the editor must use.
     * @return this instance to chain calls.
     */
    @objid ("1c39a6ad-a9cb-4708-865c-0c3d0ce06d7b")
    public EditorLocatorForLabelFigure setFontGetter(Supplier<Font> fontGetter) {
        this.fontGetter = fontGetter;
        return this;
    }

    /**
     * Set the minimum editor size.
     * 
     * @param d the minimum editor size.
     * @return this instance to chain calls.
     */
    @objid ("c2fe26de-ecbc-4acf-a01f-3c3a89044ae6")
    public EditorLocatorForLabelFigure setMinSize(Dimension d) {
        this.minSize.setSize(d);
        return this;
    }

}
