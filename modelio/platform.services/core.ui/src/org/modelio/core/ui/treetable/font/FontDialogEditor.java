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

package org.modelio.core.ui.treetable.font;

import java.text.MessageFormat;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.modelio.ui.CoreFontRegistry;
import org.modelio.ui.LocalFontRegistry;

@objid ("6b36fd01-1eba-11e2-9382-bc305ba4815c")
public class FontDialogEditor extends CellEditor {
    /**
     * The value of this cell editor; initially <code>null</code>.
     */
    @objid ("6b36fd02-1eba-11e2-9382-bc305ba4815c")
    private Font value = null;

    /**
     * The editor control.
     */
    @objid ("6b36fd04-1eba-11e2-9382-bc305ba4815c")
    private Composite editor;

    /**
     * The current contents.
     */
    @objid ("6b372411-1eba-11e2-9382-bc305ba4815c")
    private Control contents;

    /**
     * The label that gets reused by <code>updateLabel</code>.
     */
    @objid ("6b372413-1eba-11e2-9382-bc305ba4815c")
    private Label defaultLabel;

    /**
     * The button.
     */
    @objid ("6b374b20-1eba-11e2-9382-bc305ba4815c")
    private Button button;

    /**
     * Listens for 'focusLost' events and fires the 'apply' event as long as the focus wasn't lost because the dialog
     * was opened.
     */
    @objid ("6b374b22-1eba-11e2-9382-bc305ba4815c")
    private FocusListener buttonFocusListener;

    @objid ("15fb1e3a-8e0a-4de1-9358-eebaa3a04368")
    private LocalFontRegistry fontReg;

    /**
     * Creates a new dialog cell editor with no control
     * @param color
     * 
     * @param parent the parent control
     */
    @objid ("6b374b24-1eba-11e2-9382-bc305ba4815c")
    protected FontDialogEditor(Composite parent) {
        this(parent, SWT.NONE);
    }

    /**
     * Creates a new dialog cell editor parented under the given control. The cell editor value is <code>null</code>
     * initially, and has no validator.
     * 
     * @param parent the parent control
     * @param style the style bits
     * @since 2.1
     */
    @objid ("6b377232-1eba-11e2-9382-bc305ba4815c")
    public FontDialogEditor(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * Creates the button for this cell editor under the given parent control.
     * <p>
     * The default implementation of this framework method creates the button display on the right hand side of the
     * dialog cell editor. Subclasses may extend or reimplement.
     * </p>
     * 
     * @param parent the parent control
     * @return the new button control
     */
    @objid ("6b379942-1eba-11e2-9382-bc305ba4815c")
    protected Button createButton(Composite parent) {
        Button result = new Button(parent, SWT.DOWN);
        result.setText("..."); //$NON-NLS-1$
        return result;
    }

    /**
     * Creates the controls used to show the value of this cell editor.
     * <p>
     * The default implementation of this framework method creates a label widget, using the same font and background
     * color as the parent control.
     * </p>
     * <p>
     * Subclasses may reimplement. If you reimplement this method, you should also reimplement
     * <code>updateContents</code>.
     * </p>
     * 
     * @param cell the control for this cell editor
     * @return the underlying control
     */
    @objid ("6b37c051-1eba-11e2-9382-bc305ba4815c")
    protected Control createContents(Composite cell) {
        this.defaultLabel = new Label(cell, SWT.LEFT);
        this.defaultLabel.setFont(cell.getFont());
        this.defaultLabel.setBackground(cell.getBackground());
        return this.defaultLabel;
    }

    @objid ("6b37e761-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected Control createControl(final Composite parent) {
        Font font = parent.getFont();
        Color bg = parent.getBackground();
        
        
        this.editor = new Composite(parent, this.getStyle());
        this.editor.setFont(font);
        this.editor.setBackground(bg);
        this.editor.setLayout(new DialogCellLayout());
        
        // Can't use a local font registry because the font must be kept by the calling Viewer.
        // A LocalResourceManager should be passed by the viewer.
        this.fontReg = CoreFontRegistry.getGlobal();
        
        this.contents = this.createContents(this.editor);
        this.updateContents(this.value);
        
        this.button = this.createButton(this.editor);
        this.button.setFont(font);
        
        this.button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.character == '\u001b') { // Escape
                    FontDialogEditor.this.fireCancelEditor();
                }
            }
        });
        
        this.button.addFocusListener(getButtonFocusListener());
        
        this.button.addListener(SWT.Selection, (Event ev) -> {
            // Remove the button's focus listener since it's guaranteed
            // to lose focus when the dialog opens
            this.button.removeFocusListener(getButtonFocusListener());
            
            FontData newValue = openDialogBox(this.editor);
            
            // Re-add the listener once the dialog closes
            this.button.addFocusListener(getButtonFocusListener());
            
            if (newValue != null) {
                boolean newValidState = isCorrect(newValue);
                if (newValidState) {
                    markDirty();
                    doSetValue(this.fontReg.getFont(newValue));
                } else {
                    // try to insert the current value into the error message.
                    setErrorMessage(MessageFormat.format(
                            getErrorMessage(),
                            new Object[] { newValue.toString() }));
                }
                fireApplyEditorValue();
            }
        });
        
        this.setValueValid(true);
        
        this.defaultLabel.addListener(SWT.MouseDoubleClick, ev -> {
            FontData newValue = openDialogBox(this.editor);
            if (newValue != null) {
                boolean newValidState = isCorrect(newValue);
                if (newValidState) {
                    markDirty();
                    doSetValue(this.fontReg.getFont(newValue));
                } else {
                    // try to insert the current value into the error message.
                    setErrorMessage(MessageFormat.format(
                            getErrorMessage(),
                            new Object[] { newValue.toString() }));
                }
                fireApplyEditorValue();
            }
        });
        return this.editor;
    }

    @objid ("6b380e73-1eba-11e2-9382-bc305ba4815c")
    @Override
    public void deactivate() {
        if (this.button != null && !this.button.isDisposed()) {
            this.button.removeFocusListener(this.getButtonFocusListener());
        }
        
        super.deactivate();
    }

    @objid ("6b383580-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected Object doGetValue() {
        return this.value;
    }

    @objid ("6b385c90-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected void doSetFocus() {
        this.button.setFocus();
        // add a FocusListener to the button
        this.button.addFocusListener(this.getButtonFocusListener());
    }

    /**
     * Return a listener for button focus.
     * 
     * @return FocusListener
     */
    @objid ("6b385c93-1eba-11e2-9382-bc305ba4815c")
    private FocusListener getButtonFocusListener() {
        if (this.buttonFocusListener == null) {
            this.buttonFocusListener = new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    // Do nothing
                }
        
                @Override
                public void focusLost(FocusEvent e) {
                    FontDialogEditor.this.focusLost();
                }
            };
        }
        return this.buttonFocusListener;
    }

    @objid ("6b3883a2-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected void doSetValue(Object value) {
        this.value = (Font) value;
        this.updateContents(value);
    }

    /**
     * Returns the default label widget created by <code>createContents</code>.
     * 
     * @return the default label widget
     */
    @objid ("6b38aab0-1eba-11e2-9382-bc305ba4815c")
    protected Label getDefaultLabel() {
        return this.defaultLabel;
    }

    /**
     * Opens a dialog box under the given parent control and returns the dialog's value when it closes, or
     * <code>null</code> if the dialog was canceled or no selection was made in the dialog.
     * <p>
     * This framework method must be implemented by concrete subclasses. It is called when the user has pressed the
     * button and the dialog box must pop up.
     * </p>
     * 
     * @param cellEditorWindow the parent control cell editor's window so that a subclass can adjust the dialog box accordingly
     * @return the selected value, or <code>null</code> if the dialog was canceled or no selection was made in the
     * dialog
     */
    @objid ("6b38d1c0-1eba-11e2-9382-bc305ba4815c")
    protected FontData openDialogBox(Control cellEditorWindow) {
        final Display display = cellEditorWindow.getDisplay();
        final Shell centerShell = new Shell(cellEditorWindow.getShell(), SWT.NO_TRIM);
        centerShell.setLocation(display.getCursorLocation());
        
        FontDialog ftDialog = new FontDialog(centerShell, SWT.NONE);
        if (this.value != null) {
            ftDialog.setFontList(this.value.getFontData());
        }
        FontData fData = ftDialog.open();
        
        if (fData != null) {
            return fData;
        }
        return null;
    }

    /**
     * Updates the controls showing the value of this cell editor.
     * <p>
     * The default implementation of this framework method just converts the passed object to a string using
     * <code>toString</code> and sets this as the text of the label widget.
     * </p>
     * <p>
     * Subclasses may reimplement. If you reimplement this method, you should also reimplement
     * <code>createContents</code>.
     * </p>
     * 
     * @param value the new value of this cell editor
     */
    @objid ("6b38f8d1-1eba-11e2-9382-bc305ba4815c")
    protected void updateContents(Object value) {
        if (this.defaultLabel == null) {
            return;
        }
        
        String text = "";//$NON-NLS-1$
        if (value != null) {
            if (value instanceof Font)
                text = FontService.getFontLabel((Font) value);
            else
                text = value.toString();
        }
        this.defaultLabel.setText(text);
    }

    /**
     * Internal class for laying out the dialog.
     */
    @objid ("6b38f8d5-1eba-11e2-9382-bc305ba4815c")
    private class DialogCellLayout extends Layout {
        @objid ("6b391fe1-1eba-11e2-9382-bc305ba4815c")
        @Override
        public void layout(Composite editor, boolean force) {
            Rectangle bounds = editor.getClientArea();
            Point size = FontDialogEditor.this.button.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            if (FontDialogEditor.this.contents != null) {
                FontDialogEditor.this.contents.setBounds(0, 0, bounds.width - size.x, bounds.height);
            }
            FontDialogEditor.this.button.setBounds(bounds.width - size.x, 0, size.x, bounds.height);
        }

        @objid ("6b3946f0-1eba-11e2-9382-bc305ba4815c")
        @Override
        public Point computeSize(Composite editor, int wHint, int hHint, boolean force) {
            if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
                return new Point(wHint, hHint);
            }
            Point contentsSize = FontDialogEditor.this.contents.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            Point buttonSize = FontDialogEditor.this.button.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            // Just return the button width to ensure the button is not clipped
            // if the label is long.
            // The label will just use whatever extra width there is
            Point result = new Point(buttonSize.x, Math.max(contentsSize.y, buttonSize.y));
            return result;
        }

    }

}
