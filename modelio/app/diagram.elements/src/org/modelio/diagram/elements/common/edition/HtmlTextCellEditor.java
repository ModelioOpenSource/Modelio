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

package org.modelio.diagram.elements.common.edition;

import java.text.MessageFormat;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.ui.htmleditor.HtmlComposer;

/**
 * A cell editor that manages a text entry field.
 * The cell editor's value is the text string itself.
 * <p>
 * This class may be instantiated or subclassed.
 * </p>
 */
@objid ("b1d4ae29-7526-464d-83fc-2254e92fd1bb")
public class HtmlTextCellEditor extends CellEditor {
    /**
     * Default TextCellEditor style
     * specify no borders on text widget as cell outline in table already
     * provides the look of a border.
     */
    @objid ("3feaa370-739b-418c-8a72-6ac9c18f6039")
    private static final int defaultStyle = SWT.SINGLE;

    @objid ("22b9723e-dfb0-40dc-a921-ff2ce09a1faa")
    private boolean isDeleteable = false;

    @objid ("c8c4eadb-cafd-4c0e-9c3f-3754a5e5228d")
    private boolean isSelectable = false;

    /**
     * State information for updating action enablement
     */
    @objid ("da4a097f-6d83-4a7a-b20b-7e1f19343542")
    private boolean isSelection = false;

    @objid ("0404305d-80ab-498e-ad0e-540f91c221a7")
    private ModifyListener modifyListener;

    /**
     * The text control; initially <code>null</code>.
     */
    @objid ("0d63b7b8-e851-4527-b721-8afd2325a16a")
    protected HtmlComposer text;

    /**
     * Creates a new text string cell editor with no control
     * The cell editor value is the string itself, which is initially the empty
     * string. Initially, the cell editor has no cell validator.
     * @since 2.1
     */
    @objid ("debbc622-6573-46e5-920f-01591d5d78ab")
    public HtmlTextCellEditor() {
        setStyle(defaultStyle);
    }

    /**
     * Creates a new text string cell editor parented under the given control.
     * The cell editor value is the string itself, which is initially the empty string.
     * Initially, the cell editor has no cell validator.
     * 
     * @param parent the parent control
     */
    @objid ("0f1b4827-ccf4-4f03-8fcd-5790406f0a0a")
    public HtmlTextCellEditor(Composite parent) {
        this(parent, defaultStyle);
    }

    /**
     * Creates a new text string cell editor parented under the given control.
     * The cell editor value is the string itself, which is initially the empty string.
     * Initially, the cell editor has no cell validator.
     * 
     * @param parent the parent control
     * @param style the style bits
     * @since 2.1
     */
    @objid ("1c5336e1-b0c8-448d-b4a0-9748176201d0")
    public HtmlTextCellEditor(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * Since a text editor field is scrollable we don't
     * set a minimumSize.
     */
    @objid ("ba8bb36d-ae07-406a-8b56-968062a52256")
    @Override
    public LayoutData getLayoutData() {
        LayoutData data = new LayoutData();
        data.minimumWidth= 0;
        return data;
    }

    /**
     * The <code>TextCellEditor</code>  implementation of this
     * <code>CellEditor</code> method returns <code>true</code> if
     * the current selection is not empty.
     */
    @objid ("1c107ca7-32ef-4f42-be2b-f24e3ac7fb16")
    @Override
    public boolean isCopyEnabled() {
        if (this.text == null || this.text.isDisposed()) {
            return false;
        }
        return !getSelection().isEmpty();
    }

    /**
     * The <code>TextCellEditor</code>  implementation of this
     * <code>CellEditor</code> method returns <code>true</code> if
     * the current selection is not empty.
     */
    @objid ("beb2f9f6-7954-4343-bb41-27433f8c1f9a")
    @Override
    public boolean isCutEnabled() {
        if (this.text == null || this.text.isDisposed()) {
            return false;
        }
        return !getSelection().isEmpty();
    }

    /**
     * The <code>TextCellEditor</code>  implementation of this
     * <code>CellEditor</code> method returns <code>true</code>
     * if there is a selection or if the caret is not positioned
     * at the end of the text.
     */
    @objid ("743459ba-8f61-4360-97e0-e5eb1accc23c")
    @Override
    public boolean isDeleteEnabled() {
        if (this.text == null || this.text.isDisposed()) {
            return false;
        }
        return  !getSelection().isEmpty()/*
                                                                        || this.text.getCaretPosition() < this.text.getCharCount()*/;
    }

    /**
     * The <code>TextCellEditor</code>  implementation of this
     * <code>CellEditor</code> method always returns <code>true</code>.
     */
    @objid ("df8bd14e-f68f-42f9-b407-ea344b778432")
    @Override
    public boolean isPasteEnabled() {
        if (this.text == null || this.text.isDisposed()) {
            return false;
        }
        return true;
    }

    /**
     * Check if save all is enabled
     * 
     * @return true if it is
     */
    @objid ("e71f02fd-e8f0-4424-a861-91bab06658f2")
    public boolean isSaveAllEnabled() {
        if (this.text == null || this.text.isDisposed()) {
            return false;
        }
        return true;
    }

    /**
     * Returns <code>true</code> if this cell editor is
     * able to perform the select all action.
     * <p>
     * This default implementation always returns
     * <code>false</code>.
     * </p>
     * <p>
     * Subclasses may override
     * </p>
     * 
     * @return <code>true</code> if select all is possible,
     * <code>false</code> otherwise
     */
    @objid ("87df8866-08e3-4ba4-92f3-8beee41aaa80")
    @Override
    public boolean isSelectAllEnabled() {
        if (this.text == null || this.text.isDisposed()) {
            return false;
        }
        
        final String html = this.text.getHtml();
        return html != null && !html.isEmpty();
    }

    /**
     * The <code>TextCellEditor</code> implementation of this
     * <code>CellEditor</code> method copies the
     * current selection to the clipboard.
     */
    @objid ("79bb322c-e1cf-49ba-a219-d4b89e661f39")
    @Override
    public void performCopy() {
        getBrowser().execute("execCommand('copy')");
        //this.text.copy();
    }

    /**
     * The <code>TextCellEditor</code> implementation of this
     * <code>CellEditor</code> method cuts the
     * current selection to the clipboard.
     */
    @objid ("799f0ed2-ac17-4126-9e6d-87ff6cfebf8a")
    @Override
    public void performCut() {
        //this.text.cut();
        getBrowser().execute("execCommand('cut')");
        checkSelection();
        checkDeleteable();
        checkSelectable();
    }

    /**
     * The <code>TextCellEditor</code> implementation of this
     * <code>CellEditor</code> method deletes the
     * current selection or, if there is no selection,
     * the character next character from the current position.
     */
    @objid ("a839b8f3-5c27-42dd-8f3c-2ca57b512b8d")
    @Override
    public void performDelete() {
        if (! getSelection().isEmpty()) {
            // remove the contents of the current selection
            this.text.setHtml(""); //$NON-NLS-1$
        } else {
            // remove the next character
            getBrowser().execute("execCommand('delete')");
            /*int pos = this.text.getCaretPosition();
            if (pos < this.text.getCharCount()) {
                this.text.setSelection(pos, pos + 1);
                this.text.insert(""); //$NON-NLS-1$
            }*/
        }
        checkSelection();
        checkDeleteable();
        checkSelectable();
    }

    /**
     * The <code>TextCellEditor</code> implementation of this
     * <code>CellEditor</code> method pastes the
     * the clipboard contents over the current selection.
     */
    @objid ("561ab030-8770-47bd-9a9f-0d0bc3334ca5")
    @Override
    public void performPaste() {
        //this.text.paste();
        getBrowser().execute("execCommand('paste')");
        checkSelection();
        checkDeleteable();
        checkSelectable();
    }

    /**
     * The <code>TextCellEditor</code> implementation of this
     * <code>CellEditor</code> method selects all of the
     * current text.
     */
    @objid ("226223f8-a053-4214-85f9-6d3d2caf3fb7")
    @Override
    public void performSelectAll() {
        //this.text.selectAll();
        getBrowser().execute("execCommand('selectAll')");
        checkSelection();
        checkDeleteable();
    }

    @objid ("3f2b6cfd-7ded-4fa1-a128-e7e587c93def")
    @Override
    protected Control createControl(Composite parent) {
        this.text = new HtmlComposer(parent, getStyle());
        /*this.getBrowser().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                handleDefaultSelection(e);
            }
        });*/
        getBrowser().addKeyListener(new KeyAdapter() {
            // hook key pressed - see PR 14201  
            @SuppressWarnings("synthetic-access")
            @Override
            public void keyPressed(KeyEvent e) {
                keyReleaseOccured(e);
        
                // as a result of processing the above call, clients may have
                // disposed this cell editor
                if ((getControl() == null) || getControl().isDisposed()) {
                    return;
                }
                checkSelection(); // see explanation below
                checkDeleteable();
                checkSelectable();
            }
        });
        this.text.addTraverseListener(new TraverseListener() {
            @Override
            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_ESCAPE
                        || e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                }
            }
        });
        // We really want a selection listener but it is not supported so we
        // use a key listener and a mouse listener to know when selection changes
        // may have occurred
        getBrowser().addMouseListener(new MouseAdapter() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void mouseUp(MouseEvent e) {
                checkSelection();
                checkDeleteable();
                checkSelectable();
            }
        });
        this.text.addFocusListener(new FocusAdapter() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void focusLost(FocusEvent e) {
                HtmlTextCellEditor.this.focusLost();
            }
        });
        this.text.setFont(parent.getFont());
        this.text.setBackground(parent.getBackground());
        //this.text.setHtml("");//$NON-NLS-1$
        this.text.addModifyListener(getModifyListener());
        return getBrowser();
    }

    /**
     * This implementation of
     * {@link CellEditor#dependsOnExternalFocusListener()} returns false if the
     * current instance's class is TextCellEditor, and true otherwise.
     * Subclasses that hook their own focus listener should override this method
     * and return false. See also bug 58777.
     * @since 3.4
     */
    @objid ("19e6dfde-5c6b-4f74-81c5-9a2a123c3dae")
    @Override
    protected boolean dependsOnExternalFocusListener() {
        return getClass() != HtmlTextCellEditor.class;
    }

    /**
     * The <code>TextCellEditor</code> implementation of
     * this <code>CellEditor</code> framework method returns
     * the text string.
     * 
     * @return the text string
     */
    @objid ("b7f20f38-ea8c-411e-9723-b43e558a8a14")
    @Override
    protected Object doGetValue() {
        return this.text.getHtml();
    }

    @objid ("86ae8548-85dc-4449-a386-00f4a27f4046")
    @Override
    protected void doSetFocus() {
        if (this.text != null) {
            //getBrowser().evaluate("document.select()"); //TODO
            //this.text.selectAll();
            this.text.setFocus();
            checkSelection();
            checkDeleteable();
            checkSelectable();
        }
    }

    /**
     * The <code>TextCellEditor</code> implementation of
     * this <code>CellEditor</code> framework method accepts
     * a text string (type <code>String</code>).
     * 
     * @param value a text string (type <code>String</code>)
     */
    @objid ("31e96cd4-b640-4613-be78-902179345eb5")
    @Override
    protected void doSetValue(Object value) {
        Assert.isTrue(this.text != null && (value instanceof String));
        this.text.removeModifyListener(getModifyListener());
        this.text.setHtml((String) value);
        this.text.addModifyListener(getModifyListener());
    }

    /**
     * Processes a modify event that occurred in this text cell editor.
     * This framework method performs validation and sets the error message
     * accordingly, and then reports a change via <code>fireEditorValueChanged</code>.
     * Subclasses should call this method at appropriate times. Subclasses
     * may extend or reimplement.
     * 
     * @param e the SWT modify event
     */
    @objid ("e387f1db-6975-4e9f-9836-a03172a0b0e9")
    protected void editOccured(ModifyEvent e) {
        String value = this.text.getHtml();
        if (value == null) {
            value = "";//$NON-NLS-1$
        }
        Object typedValue = value;
        boolean oldValidState = isValueValid();
        boolean newValidState = isCorrect(typedValue);
        if (!newValidState) {
            // try to insert the current value into the error message.
            setErrorMessage(MessageFormat.format(getErrorMessage(),
                    new Object[] { value }));
        }
        valueChanged(oldValidState, newValidState);
    }

    /**
     * Handles a default selection event from the text control by applying the editor
     * value and deactivating this cell editor.
     * 
     * @param event the selection event
     * 
     * @since 3.0
     */
    @objid ("9d1b286e-d989-481d-b180-8db414cd5564")
    protected void handleDefaultSelection(SelectionEvent event) {
        // same with enter-key handling code in keyReleaseOccured(e);
        fireApplyEditorValue();
        deactivate();
    }

    /**
     * Processes a key release event that occurred in this cell editor.
     * <p>
     * The <code>TextCellEditor</code> implementation of this framework method
     * ignores when the RETURN key is pressed since this is handled in
     * <code>handleDefaultSelection</code>.
     * An exception is made for Ctrl+Enter for multi-line texts, since
     * a default selection event is not sent in this case.
     * </p>
     * 
     * @param keyEvent the key event
     */
    @objid ("73629437-39ea-4051-84fc-b4c8247eb3b9")
    @Override
    protected void keyReleaseOccured(KeyEvent keyEvent) {
        if (keyEvent.character == '\r') { // Return key
            // Enter is handled in handleDefaultSelection.
            // Do not apply the editor value in response to an Enter key event
            // since this can be received from the IME when the intent is -not-
            // to apply the value.  
            // See bug 39074 [CellEditors] [DBCS] canna input mode fires bogus event from Text Control
            //
            // An exception is made for Ctrl+Enter for multi-line texts, since
            // a default selection event is not sent in this case. 
            if (this.text != null && !this.text.isDisposed()
                    && (this.text.getStyle() & SWT.MULTI) != 0) {
                if ((keyEvent.stateMask & SWT.CTRL) != 0) {
                    super.keyReleaseOccured(keyEvent);
                }
            }
            return;
        }
        super.keyReleaseOccured(keyEvent);
    }

    /**
     * Checks to see if the "deletable" state (can delete/
     * nothing to delete) has changed and if so fire an
     * enablement changed notification.
     */
    @objid ("0a190497-fe64-491c-9240-74d36975e231")
    private void checkDeleteable() {
        boolean oldIsDeleteable = this.isDeleteable;
        this.isDeleteable = isDeleteEnabled();
        if (oldIsDeleteable != this.isDeleteable) {
            fireEnablementChanged(DELETE);
        }
    }

    /**
     * Checks to see if the "selectable" state (can select)
     * has changed and if so fire an enablement changed notification.
     */
    @objid ("2f31aa38-b940-4ad9-840e-194def20f201")
    private void checkSelectable() {
        boolean oldIsSelectable = this.isSelectable;
        this.isSelectable = isSelectAllEnabled();
        if (oldIsSelectable != this.isSelectable) {
            fireEnablementChanged(SELECT_ALL);
        }
    }

    /**
     * Checks to see if the selection state (selection /
     * no selection) has changed and if so fire an
     * enablement changed notification.
     */
    @objid ("d65c9454-ee84-469a-9027-984558193156")
    private void checkSelection() {
        boolean oldIsSelection = this.isSelection;
        this.isSelection = !getSelection().isEmpty();
        if (oldIsSelection != this.isSelection) {
            fireEnablementChanged(COPY);
            fireEnablementChanged(CUT);
        }
    }

    @objid ("99e6a6ea-da13-42ef-9c45-fdd989c10d4c")
    private Browser getBrowser() {
        return this.text.getBrowser();
    }

    /**
     * Return the modify listener.
     */
    @objid ("d7a62a29-e68d-4bfc-af61-dcb5304ab868")
    private ModifyListener getModifyListener() {
        if (this.modifyListener == null) {
            this.modifyListener = new ModifyListener() {
                @Override
                public void modifyText(ModifyEvent e) {
                    editOccured(e);
                }
            };
        }
        return this.modifyListener;
    }

    @objid ("e4498bf2-1ca3-4eae-890b-e8a358725246")
    private String getSelection() {
        try {
            Object sel = getBrowser().evaluate("window.getSelection()");
            return sel != null ? (String) sel : "";
        } catch (SWTException e) {
            // Ignore SWT exception
            return "";
        }
    }

}
