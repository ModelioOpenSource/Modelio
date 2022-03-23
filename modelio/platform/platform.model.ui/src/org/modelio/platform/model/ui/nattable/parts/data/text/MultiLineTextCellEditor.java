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
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.TextCellEditor;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A specialization of {@link TextCellEditor} that uses a multi line text editor as editor control. To support multi line editing correctly, the behaviour to commit on pressing the enter key is disabled.
 * <p>
 * A multi line editor usually needs some space. Therefore it might be a good decision to set the configuration attribute {@link EditConfigAttributes#OPEN_IN_DIALOG} to <code>true</code> for this editor, so the editor always opens in a subdialog.
 * </p>
 * <p>
 * As some table layouts may support enough space for an inline cell editor, this editor does not specify {@link ICellEditor#openInline(org.eclipse.nebula.widgets.nattable.config.IConfigRegistry, java.util.List)} to always return <code>false</code>.
 * </p>
 */
@objid ("839423b8-688f-49a9-a53b-895c29d2d999")
class MultiLineTextCellEditor extends TextCellEditor {
    /**
     * Flag to configure whether the text control should enable automatic line wrap behaviour or not. By default this editor will support automatic line wrapping.
     */
    @objid ("fc1f5389-146a-41ec-9007-81a74da3493e")
    private boolean lineWrap = true;

    /**
     * Create a new multi line text editor that ensures to not commit the editor value in case enter is typed. The text control will support automatic line wrapping.
     */
    @objid ("b3953f3b-b8b0-463c-985d-31120a0cfa94")
    public  MultiLineTextCellEditor() {
        this(true, true);
    }

    /**
     * Create a new multi line text editor that ensures to not commit the editor value in case enter is typed.
     * @param lineWrap Flag to configure whether the text control should enable automatic line wrap behaviour or not.
     * @param moveSelectionOnEnter Flag to configure whether the selection should move after a value was committed after pressing enter.
     */
    @objid ("f2cc6405-9229-4f60-b236-d579d8218d39")
    public  MultiLineTextCellEditor(boolean lineWrap, boolean moveSelectionOnEnter) {
        super(false, moveSelectionOnEnter, false);
        this.commitOnEnter = false;
        this.lineWrap = lineWrap;
        
    }

    @objid ("66ddc642-ee3b-49fb-8321-c3124d4cf51d")
    @Override
    public Text createEditorControl(Composite parent) {
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
        final Text textControl = super.createEditorControl(parent, style);
        
        if (!openInline) {
            // add the layout data directly so it will not be layouted by the
            // CellEditDialog
            GridDataFactory.fillDefaults().grab(true, true).hint(100, 50).applyTo(textControl);
        }
        
        // on inline editing there need to be a different handling of the return
        // key as the Text control is performing a new line on return, it is not
        // possible to commit a value by pressing enter. So for inline editing
        // we catch enter to perform the commit, while pressing Alt/Shift +
        // enter will add a new line
        if (openInline) {
            this.commitOnEnter = true;
            textControl.addKeyListener(new KeyListener() {
        
                @Override
                public void keyReleased(KeyEvent event) {
                    if (event.keyCode == SWT.CR
                            || event.keyCode == SWT.KEYPAD_CR) {
                        if (event.stateMask == SWT.MOD3) {
                            textControl.insert(textControl.getLineDelimiter());
                        }
                    }
                }
        
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
        }
        return textControl;
    }

    @objid ("3da00647-9ac0-4f1c-b9a9-6523251b5677")
    @Override
    public Rectangle calculateControlBounds(final Rectangle cellBounds) {
        int widthHintForCompute = this.lineWrap ? cellBounds.width : SWT.DEFAULT;
        Point size = getEditorControl().computeSize(widthHintForCompute, SWT.DEFAULT);
        
        int diff = 0;
        if (this.lineWrap) {
            // Because of computeTrim internally the computed width is bigger than the given
            // width. We therefore need to calculate twice by removing the trim diff to get
            // the correct size.
            diff = size.x - cellBounds.width;
            size = getEditorControl().computeSize(widthHintForCompute - diff, SWT.DEFAULT);
        }
        
        final int widthHint = widthHintForCompute - diff;
        
        // add a listener that increases/decreases the size of the control if
        // the text is modified as the calculateControlBounds method is only
        // called in case of inline editing, this listener shouldn't hurt
        // anybody else
        getEditorControl().addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                Point p = getEditorControl().computeSize(widthHint, SWT.DEFAULT, true);
                Point loc = getEditorControl().getLocation();
                getEditorControl().setBounds(
                        loc.x,
                        loc.y,
                        MultiLineTextCellEditor.this.lineWrap ? cellBounds.width : Math.max(p.x, cellBounds.width),
                        Math.max(p.y, cellBounds.height));
            }
        });
        return new Rectangle(
                cellBounds.x,
                cellBounds.y,
                this.lineWrap ? cellBounds.width : Math.max(size.x, cellBounds.width),
                Math.max(size.y, cellBounds.height));
        
    }

    /**
     * @param lineWrap <code>true</code> if the text control should enable automatic line wrap behaviour, <code>false</code> if not
     */
    @objid ("410b1abe-d616-464f-b2a6-70262cb42b01")
    public void setLineWrap(boolean lineWrap) {
        this.lineWrap = lineWrap;
    }

}
