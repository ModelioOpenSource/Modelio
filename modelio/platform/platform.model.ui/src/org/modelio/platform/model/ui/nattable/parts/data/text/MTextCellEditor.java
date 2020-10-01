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

package org.modelio.platform.model.ui.nattable.parts.data.text;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A specialization of {@link MultiLineTextCellEditor} that uses a multi line text editor as editor control. To support multi line editing correctly, the behaviour to commit on pressing the enter key is disabled.
 * <p>
 * This editor includes several behavior modifications:
 * <ul>
 * <li>ALT-ENTER adds a delimiter.</li>
 * <li>The size of edition zone grows when adding text to the edited cell, but never exceed the table bounds.</li>
 * <li>Line wrapping can be set on and off.</li>
 * </ul>
 * </p>
 */
@objid ("24fc3d6a-b5e9-4942-9bb6-61f06986e119")
public class MTextCellEditor extends MultiLineTextCellEditor {
    /**
     * We need to redefined this flag, as the one from the parent is not visible...
     */
    @objid ("193132f2-0389-4f08-8ef7-5daa3d54c078")
    private boolean lineWrap;

    /**
     * Create a new multi line text editor.
     * 
     * @param lineWrap Flag to configure whether the text control should enable automatic line wrap behaviour or not.
     * @param moveSelectionOnEnter Flag to configure whether the selection should move after a value was committed after pressing enter.
     */
    @objid ("15f802c4-3226-414d-8ff8-c0fe16cf222a")
    public MTextCellEditor(boolean lineWrap, boolean moveSelectionOnEnter) {
        super(lineWrap, moveSelectionOnEnter);
        this.lineWrap = lineWrap;
    }

    /**
     * Specialization smartly changes the size of the text to make the editor grow if necessary, but never exceed the table bounds.
     */
    @objid ("196b4543-ca53-4303-8717-afd412703a8e")
    @Override
    public Rectangle calculateControlBounds(Rectangle cellBounds) {
        Text text = getEditorControl();
        
        // add a listener that increases/decreases the size of the control if
        // the text is modified as the calculateControlBounds method is only
        // called in case of inline editing, this listener shouldn't hurt
        // anybody else
        text.addModifyListener(new ModifyListener() {
            @SuppressWarnings ("synthetic-access")
            @Override
            public void modifyText(ModifyEvent e) {
                Rectangle editorBounds = MTextCellEditor.this.layerCell.getLayer().getLayerPainter().adjustCellBounds(
                        MTextCellEditor.this.layerCell.getColumnPosition(),
                        MTextCellEditor.this.layerCell.getRowPosition(),
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
     * Specialization: do not process ALT-ENTER by adding a delimiter
     */
    @objid ("546b07f2-44f4-4e12-ac85-da5cdd513747")
    @Override
    public Text createEditorControl(Composite composite) {
        boolean openInline = openInline(this.configRegistry, this.labelStack.getLabels());
        
        int style = HorizontalAlignmentEnum.getSWTStyle(this.cellStyle) | SWT.MULTI | SWT.BORDER;
        if (!openInline) {
            // if the editor control is opened in a dialog, we add scrolling as
            // the size of the control is dependent on the dialog size
            style = style | SWT.V_SCROLL;
        }
        if (this.lineWrap) {
            style = style | SWT.WRAP;
        } else if (!openInline) {
            // if the editor control is opened in a dialog, we add scrolling as
            // the size of the control is dependent on the dialog size
            style = style | SWT.H_SCROLL;
        }
        final Text textControl = super.createEditorControl(composite, style);
        
        if (!openInline) {
            // add the layout data directly so it will not be layouted by the
            // CellEditDialog
            GridDataFactory.fillDefaults().grab(true, true).hint(100, 50).applyTo(textControl);
        }
        
        // on inline editing there need to be a different handling of the return
        // key as the Text control is performing a new line on return, it is not
        // possible to commit a value by pressing enter. So for inline editing
        // we catch enter to perform the commit, while pressing Alt + enter will
        // add a new line
        if (openInline) {
            this.commitOnEnter = true;
            textControl.addKeyListener(new KeyListener() {
        
                @Override
                public void keyReleased(KeyEvent event) {
                    if ((event.keyCode == SWT.CR) || (event.keyCode == SWT.KEYPAD_CR)) {
                        if (event.stateMask == SWT.ALT) {
                            textControl.insert(textControl.getLineDelimiter());
                        }
                    }
                }
        
                @Override
                public void keyPressed(KeyEvent e) {
                    // Nothing to do
                }
            });
        }
        return textControl;
    }

    @objid ("7ff60173-3e54-4c87-87f0-5fbf0183f83c")
    @Override
    public void setLineWrap(boolean lineWrap) {
        super.setLineWrap(lineWrap);
        this.lineWrap = lineWrap;
    }

    @objid ("7825ae30-9b31-4ce5-a797-48f89ac1e9aa")
    protected Rectangle computeSize(Rectangle cellBounds, Text text) {
        Rectangle cellRect = new Rectangle(cellBounds.x, cellBounds.y, cellBounds.width, cellBounds.height);
        Rectangle containerRect = text.getParent().getBounds();
        
        // Get the text font height
        GC gc = new GC(text);
        Point stringSize = this.lineWrap ? gc.stringExtent(text.getText()) : gc.textExtent(text.getText());
        gc.dispose();
        
        int maxPossibleHeight = (containerRect.height) - cellRect.y;
        if (this.parent.getHorizontalBar() != null && this.parent.getHorizontalBar().isVisible()) {
            maxPossibleHeight -= 20;
        }
        
        int HORIZONTAL_MARGIN = 8;
        int VERTICAL_MARGIN = 8;
        if (stringSize.x + HORIZONTAL_MARGIN > cellRect.width) {
            int maxPossibleWidth = (containerRect.width) - cellRect.x;
            if (this.parent.getVerticalBar() != null && this.parent.getVerticalBar().isVisible()) {
                maxPossibleWidth -= 20;
            }
            Double estimatedNumberOfLines = ((Math.ceil((float) stringSize.x / (cellRect.width - HORIZONTAL_MARGIN)))) + 1;
            int requiredHeight = this.lineWrap ? estimatedNumberOfLines.intValue() * (stringSize.y + VERTICAL_MARGIN) : stringSize.y + VERTICAL_MARGIN;
        
            cellRect.width = Math.min(Math.max(cellRect.width, stringSize.x + HORIZONTAL_MARGIN + 4), maxPossibleWidth);
        
            if (requiredHeight > cellRect.height) {
                cellRect.height = Math.min(requiredHeight, maxPossibleHeight);
            }
        } else if (stringSize.y + VERTICAL_MARGIN > cellRect.height) {
            cellRect.height = Math.min(stringSize.y + VERTICAL_MARGIN, maxPossibleHeight);
        }
        return cellRect;
    }

}
