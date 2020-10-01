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

package org.modelio.platform.model.ui.nattable.parts.data.string.single;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.edit.editor.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A specialization of {@link TextCellEditor} that uses a simple text editor as editor control.
 * <p>
 * This editor includes several behavior modifications:
 * <ul>
 * <li>Always adds a BORDER.</li>
 * <li>Always wraps the text.</li>
 * <li>The size of edition zone grows when adding text to the edited cell, but never exceed the table bounds.</li>
 * </ul>
 * </p>
 */
@objid ("d84cde22-2b92-4bc4-847e-63676bc80e1e")
public class StringEditor extends TextCellEditor {
    /**
     * Build a new editor.
     * 
     * @param commitOnUpDown Flag to configure whether the editor should commit and move
     * the selection in the corresponding way if the up or down key
     * is pressed.
     * @param moveSelectionOnEnter Flag to configure whether the selection should move after a
     * value was committed after pressing enter.
     */
    @objid ("ba4e1f82-5b6b-4a73-86d4-463f13e4b105")
    public StringEditor(boolean commitOnUpDown, boolean moveSelectionOnEnter) {
        super(commitOnUpDown, moveSelectionOnEnter);
    }

    /**
     * Specialization smartly changes the size of the text to make the editor grow if necessary, but never exceed the table bounds.
     */
    @objid ("40ad87ec-2b49-4101-9715-f6065cf5332e")
    @Override
    public Rectangle calculateControlBounds(Rectangle cellBounds) {
        Text text = getEditorControl();
        
        
        // add a listener that increases/decreases the size of the control if
        // the text is modified as the calculateControlBounds method is only
        // called in case of inline editing, this listener shouldn't hurt
        // anybody else
        text.addModifyListener(new ModifyListener() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void modifyText(ModifyEvent e) {
                Rectangle editorBounds = StringEditor.this.layerCell.getLayer().getLayerPainter().adjustCellBounds(
                        StringEditor.this.layerCell.getColumnPosition(),
                        StringEditor.this.layerCell.getRowPosition(),
                        new Rectangle(cellBounds.x, cellBounds.y,
                                cellBounds.width, cellBounds.height));
                
                Point p = getEditorControl().getSize();
                Point loc = getEditorControl().getLocation();
                Rectangle newCellBounds = computeSize(editorBounds, text);
                getEditorControl().setBounds(loc.x, loc.y, Math.max(p.x, newCellBounds.width), Math.max(p.y, newCellBounds.height));
            }
        });
        return computeSize(cellBounds, text);
    }

    /**
     * Specialize by adding BORDER and WRAP flags to the SWT style
     */
    @objid ("4ddb8a2a-5258-45a0-bac4-58685c9fd849")
    @Override
    protected Text createEditorControl(final Composite composite, int style) {
        return super.createEditorControl(composite, style | SWT.BORDER | SWT.WRAP);
    }

    @objid ("2559f74c-7d42-43fc-be9f-913c3ef1f94f")
    private Rectangle computeSize(Rectangle cellBounds, Text text) {
        Rectangle cellRect = new Rectangle(cellBounds.x, cellBounds.y, cellBounds.width, cellBounds.height);
        Rectangle containerRect = text.getParent().getBounds();
        
        // Get the text font height
        GC gc = new GC(text);
        Point stringSize = gc.stringExtent(text.getText());
        gc.dispose();
        
        int HORIZONTAL_MARGIN = 0;
        int VERTICAL_MARGIN = 8;
        if (stringSize.x > cellRect.width) {
            int maxPossibleWidth = (containerRect.width) - cellRect.x;
            if (this.parent.getVerticalBar() != null && this.parent.getVerticalBar().isVisible()) {
                maxPossibleWidth -= 20;
            }
            int maxPossibleHeight = (containerRect.height) - cellRect.y;
            if (this.parent.getHorizontalBar() != null && this.parent.getHorizontalBar().isVisible()) {
                maxPossibleHeight -= 20;
            }
            Double estimatedNumberOfLines = ((Math.ceil((float) stringSize.x / (cellRect.width - HORIZONTAL_MARGIN)))) + 1;
            int requiredHeight = estimatedNumberOfLines.intValue() * (stringSize.y + VERTICAL_MARGIN);
        
            if (requiredHeight > cellRect.height) {
                cellRect.width = Math.min(Math.max(cellRect.width, stringSize.x + 4), maxPossibleWidth);
                // Wrapping
                Double estimatedNumberOfWrappedLines = ((Math.ceil((float) stringSize.x / (cellRect.width - HORIZONTAL_MARGIN)))) + 1;
                cellRect.height = Math.min(estimatedNumberOfWrappedLines.intValue() * (stringSize.y + VERTICAL_MARGIN), maxPossibleHeight);
            }
        }
        return cellRect;
    }

}
