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
package org.modelio.platform.model.ui.treetable;

import java.text.MessageFormat;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;

/**
 * An abstract cell editor that uses a dialog. Dialog cell editors usually have
 * a Text control on the left and a button on the right. Pressing the button
 * opens a dialog window (for example, a color dialog or a file dialog) to
 * change the cell editor's value. The cell editor's value is the value returned
 * by the dialog.
 * <p>
 * Subclasses may override the following methods:
 * <ul>
 * <li><code>configureButton</code>: customize the cell editor's Button control
 * </li>
 * <li><code>configureButton</code>: customize the cell editor's Text control,
 * typically set it editable or not</li>
 * <li><code>getTextRepresentation</code>: computes the cell editor's displayed
 * value as a String</li>
 * <li><code>openDialogBox</code>: opens the dialog box when the end user
 * presses the button</li>
 * </ul>
 * </p>
 */
@objid ("1381e97b-89f2-4a55-9d92-516b5974149a")
@SuppressWarnings("synthetic-access")
public abstract class EditableDialogCellEditor extends CellEditor {
    /**
     * Image registry key for three dot image (value
     * <code>"cell_editor_dots_button_image"</code>).
     */
    @objid ("13f0fee8-1c55-4395-96a6-b40620a3917a")
    public static final String CELL_EDITOR_IMG_DOTS_BUTTON = "cell_editor_dots_button_image"; // $NON-NLS-1$

    /**
     * Default EditableDialogCellEditor style
     */
    @objid ("6afaf45f-3b22-4d80-9035-c0589e2c6bf5")
    private static final int defaultStyle = SWT.NONE;

    // /**
    // * The editor composite grouping the edit control itself and the button.
    // */
    // @objid("e63eb60f-4609-4186-be92-4b8a21bedf5d")
    // private Composite editor;
    /**
     * The label that gets reused by <code>updateLabel</code>.
     */
    @objid ("0744e98f-da5f-4274-9252-ea47f30bb397")
    private Text contents;

    /**
     * The button.
     */
    @objid ("0067aace-7527-4885-b8aa-6febc91efaa9")
    private Button button;

    /**
     * Listens for 'focusLost' events and fires the 'apply' event as long as the
     * focus wasn't lost because the dialog was opened.
     */
    @objid ("0962a549-8e7f-4d2c-8522-661a075fd76e")
    private FocusListener buttonFocusListener;

    /**
     * The value of this cell editor; initially <code>null</code>.
     */
    @objid ("def511f1-7563-46c6-8318-d9f189a08b1f")
    private Object value = null;

    /**
     * Creates a new dialog cell editor parented under the given control. The
     * cell editor value is <code>null</code> initially, and has no validator.
     * @param parent the parent control
     */
    @objid ("778ed338-4697-45bc-952c-a6b800c7cb41")
    public  EditableDialogCellEditor(Composite parent) {
        super(parent, defaultStyle);
    }

    /**
     * Creates the button for this cell editor under the given parent control.
     * <p>
     * The default implementation of this framework method creates the button
     * display on the right hand side of the dialog cell editor.
     * </p>
     * @param parent the parent control
     * @return the new button control
     */
    @objid ("0d6b7928-ba22-4861-9baa-34087863398a")
    private Button createButton(Composite parent) {
        Button b = new Button(parent, SWT.DOWN);
        return b;
    }

    /**
     * Creates the controls used to show the value of this cell editor.
     * <p>
     * The default implementation of this framework method creates a label
     * widget, using the same font and background color as the parent control.
     * </p>
     * <p>
     * </p>
     * @param cell the control for this cell editor
     * @return the underlying control
     */
    @objid ("5836b5a3-2b7e-43d4-9636-d3b03a110123")
    private Text createContents(Composite cell) {
        Text text = new Text(cell, SWT.LEFT);
        text.setFont(cell.getFont());
        text.setBackground(cell.getBackground());
        return text;
    }

    /*
         * (non-Javadoc) Method declared on CellEditor.
         */
    @objid ("22a70d31-44b2-4a1c-8338-cb946c4222b3")
    @Override
    protected Control createControl(Composite parent) {
        Composite editor = new Composite(parent, getStyle());
        editor.setFont(parent.getFont());
        editor.setBackground(parent.getBackground());
        editor.setLayout(new DialogCellLayout());
        
        editor.addFocusListener(getButtonFocusListener());
        
        this.contents = createContents(editor);
        configureText(editor, this.contents);
        
        if (this.contents.getEditable()) {
            this.contents.addModifyListener(new ModifyListener() {
                @Override
                public void modifyText(ModifyEvent e) {
                    doSetValue(EditableDialogCellEditor.this.contents.getText());
                }
            });
        }
        updateContents(this.value);
        
        this.button = createButton(editor);
        configureButton(editor, this.button);
        this.button.addKeyListener(new KeyAdapter() {
            /*
             * (non-Javadoc)
             * 
             * @see
             * org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.
             * events.KeyEvent)
             */
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.character == '\u001b') { // Escape
                    fireCancelEditor();
                }
            }
        });
        
        this.button.addSelectionListener(new SelectionAdapter() {
            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.
             * eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent event) {
                doSelectValues();
            }
        });
        
        setValueValid(true);
        return editor;
    }

    /*
         * (non-Javadoc)
         * 
         * Override in order to remove the button's focus listener if the cell
         * editor is deactivating.
         * 
         * @see org.eclipse.jface.viewers.CellEditor#deactivate()
         */
    @objid ("ef19dbdf-ecb8-4f99-959c-c79b44f9bf78")
    @Override
    public void deactivate() {
        if (this.button != null && !this.button.isDisposed()) {
            this.getControl().removeFocusListener(getButtonFocusListener());
        }
        super.deactivate();
        
    }

    /*
         * (non-Javadoc) Method declared on CellEditor.
         */
    @objid ("822f6e62-7dab-4c10-ace7-f8ad4933b4d7")
    @Override
    protected Object doGetValue() {
        return this.value;
    }

    /*
         * (non-Javadoc) Method declared on CellEditor. The focus is set to the cell
         * editor's button.
         */
    @objid ("53a2a71d-38f2-4da8-bdbb-6be2213ac7d6")
    @Override
    protected void doSetFocus() {
        this.contents.setFocus();
        this.contents.selectAll();
        
        // add a FocusListener to the editor
        this.getControl().addFocusListener(getButtonFocusListener());
        
    }

    /**
     * Return a listener for button focus.
     * @return FocusListener
     */
    @objid ("ce03001b-5da2-4a9c-8e9d-41a27aff64cb")
    private FocusListener getButtonFocusListener() {
        if (this.buttonFocusListener == null) {
            this.buttonFocusListener = new FocusListener() {
        
                /*
                 * (non-Javadoc)
                 * 
                 * @see
                 * org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.
                 * swt.events.FocusEvent)
                 */
                @Override
                public void focusGained(FocusEvent e) {
                    // Do nothing
                }
        
                /*
                 * (non-Javadoc)
                 * 
                 * @see
                 * org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.
                 * swt.events.FocusEvent)
                 */
        
                @Override
                public void focusLost(FocusEvent e) {
                    EditableDialogCellEditor.this.focusLost();
                }
            };
        }
        return this.buttonFocusListener;
    }

    /*
         * (non-Javadoc) Method declared on CellEditor.
         */
    @objid ("534e75b6-e695-4413-938b-b176898985d0")
    @Override
    protected void doSetValue(Object newValue) {
        this.value = newValue;
        updateContents(newValue);
        
    }

    /**
     * Returns the default label widget created by <code>createContents</code>.
     * @return the default label widget
     */
    @objid ("e5db8bde-b4c2-4bbc-ac1e-39e0b95898b0")
    protected Text getcontents() {
        return this.contents;
    }

    /**
     * Opens a dialog box under the given parent control and returns the
     * dialog's value when it closes, or <code>null</code> if the dialog was
     * canceled or no selection was made in the dialog.
     * <p>
     * This framework method must be implemented by concrete subclasses. It is
     * called when the user has pressed the button and the dialog box must pop
     * up.
     * </p>
     * @param cellEditorWindow the parent control cell editor's window so that a subclass can
     * adjust the dialog box accordingly
     * @return the selected value, or <code>null</code> if the dialog was
     * canceled or no selection was made in the dialog
     */
    @objid ("2f8bd8ff-14ee-44bc-b926-b2127272c01b")
    protected abstract Object openDialogBox(Control cellEditorWindow);

    /**
     * Updates the controls showing the value of this cell editor.
     * <p>
     * The default implementation of this framework method just converts the
     * passed object to a string using <code>toString</code> and sets this as
     * the text of the label widget.
     * </p>
     * <p>
     * Subclasses may re-implement. If you re-implement this method, you should
     * also re-implement <code>createContents</code>.
     * </p>
     * @param newValue the new value of this cell editor
     */
    @objid ("6005c15b-b266-4bff-9d80-3e8a4db7653a")
    protected void updateContents(Object newValue) {
        if (this.contents == null) {
            return;
        }
        String text = getTextRepresentation(newValue);
        
        if (!this.contents.getText().equals(text)) {
            this.contents.setText(text);
        }
        
    }

    /**
     * Configure the button appearance (image, text, tooltip) Subclasses should
     * redefine to customize the button appearance.
     * @param button
     */
    @objid ("da1915f2-5b5f-4a19-8ce2-1ac54bf8ea9c")
    protected void configureButton(Composite parent, Button button) {
        button.setFont(parent.getFont());
        button.setImage(JFaceResources.getImageRegistry().get(CELL_EDITOR_IMG_DOTS_BUTTON));
        return;
    }

    /**
     * Configure the Text appearance (image, text, tooltip) Subclasses should
     * redefine to customize the text appearance. Typical use: forbid/enable
     * direct text edition.
     * @param button
     */
    @objid ("5176c513-63da-4fcd-987d-e85be7073cdc")
    protected void configureText(Composite parent, Text text) {
        text.setFont(parent.getFont());
        text.setEditable(false);
        return;
    }

    /**
     * Called when clicking on the button. Opens the dialog and get new value.
     */
    @objid ("6ca7fcb8-0cb9-468a-a32f-68393dfa3bae")
    private void doSelectValues() {
        // Remove the button's focus listener since it's guaranteed
        // to lose focus when the dialog opens
        getControl().removeFocusListener(getButtonFocusListener());
        
        Object newValue = openDialogBox(getControl());
        
        // Re-add the listener once the dialog closes
        getControl().addFocusListener(getButtonFocusListener());
        
        if (newValue != null) {
            boolean newValidState = isCorrect(newValue);
            if (newValidState) {
                markDirty();
                doSetValue(newValue);
            } else {
                // try to insert the current value into the error
                // message.
                setErrorMessage(MessageFormat.format(getErrorMessage(), new Object[] { newValue.toString() }));
            }
            fireApplyEditorValue();
        }
        deactivate();
        
    }

    @objid ("2c5470a4-8984-430f-8ac0-b330d43d2f39")
    protected abstract String getTextRepresentation(Object value);
static {
            ImageRegistry reg = JFaceResources.getImageRegistry();
            reg.put(CELL_EDITOR_IMG_DOTS_BUTTON,
                    ImageDescriptor.createFromFile(EditableDialogCellEditor.class, "images/dots_button.gif"));//$NON-NLS-1$
        }
    
    /**
     * Internal class for laying out the dialog.
     */
    @objid ("41d87ca2-a9a6-49ec-b44d-e356d077a395")
    private class DialogCellLayout extends Layout {
        @objid ("045fc616-d63e-4ba1-bf78-92841b927675")
        @Override
        public void layout(Composite anEditor, boolean force) {
            Rectangle bounds = anEditor.getClientArea();
            Point size = EditableDialogCellEditor.this.button.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            if (EditableDialogCellEditor.this.contents != null) {
                EditableDialogCellEditor.this.contents.setBounds(0, 0, bounds.width - size.x, bounds.height);
            }
            EditableDialogCellEditor.this.button.setBounds(bounds.width - size.x, 0, size.x, bounds.height);
            
        }

        @objid ("f302f45e-5567-4b8d-a477-cf8f3cbfaca0")
        @Override
        public Point computeSize(Composite anEditor, int wHint, int hHint, boolean force) {
            if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
                return new Point(wHint, hHint);
            }
            Point contentsSize = EditableDialogCellEditor.this.contents.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            Point buttonSize = EditableDialogCellEditor.this.button.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            // Just return the button width to ensure the button is not clipped
            // if the label is long.
            // The label will just use whatever extra width there is
            Point result = new Point(buttonSize.x, Math.max(contentsSize.y, buttonSize.y));
            return result;
        }

    }

}
