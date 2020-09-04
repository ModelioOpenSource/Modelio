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

package org.modelio.diagram.styles.editingsupport.combo;

import java.text.MessageFormat;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * This class is a modified copy of the original {@link org.eclipse.jface.viewers.ComboBoxCellEditor ComboBoxCellEditor} class.
 * <p>
 * The modification provides direct validation of the editor once a value is selected
 * (otherwise the user has to click to select a value and to validate the editor separately).
 * <p>
 * The original ComboBoxCellEditor class is not intended to be subclassed.
 * </p>
 */
@objid ("8593e62e-1926-11e2-92d2-001ec947c8cc")
class ComboBoxCellEditor2 extends CellEditor {
    /**
     * The list is dropped down when the activation is done through the mouse
     */
    @objid ("8593e632-1926-11e2-92d2-001ec947c8cc")
    public static final int DROP_DOWN_ON_MOUSE_ACTIVATION = 1;

    /**
     * The list is dropped down when the activation is done through the keyboard
     */
    @objid ("8593e635-1926-11e2-92d2-001ec947c8cc")
    public static final int DROP_DOWN_ON_KEY_ACTIVATION = 1 << 1;

    /**
     * The list is dropped down when the activation is done without ui-interaction
     */
    @objid ("8593e638-1926-11e2-92d2-001ec947c8cc")
    public static final int DROP_DOWN_ON_PROGRAMMATIC_ACTIVATION = 1 << 2;

    /**
     * The list is dropped down when the activation is done by traversing from cell to cell
     */
    @objid ("8593e63b-1926-11e2-92d2-001ec947c8cc")
    public static final int DROP_DOWN_ON_TRAVERSE_ACTIVATION = 1 << 3;

    @objid ("8593e63e-1926-11e2-92d2-001ec947c8cc")
    private int activationStyle = SWT.NONE;

    /**
     * The zero-based index of the selected item.
     */
    @objid ("8596486d-1926-11e2-92d2-001ec947c8cc")
     int selection;

    /**
     * Default ComboBoxCellEditor style
     */
    @objid ("85964873-1926-11e2-92d2-001ec947c8cc")
    private static final int defaultStyle = SWT.NONE;

    /**
     * The list of items to present in the combo box.
     */
    @objid ("26fe9b5a-1927-11e2-92d2-001ec947c8cc")
    private String[] items;

    /**
     * The custom combo box control.
     */
    @objid ("d78768d3-ab06-4882-aa33-60c85c0a6145")
     CCombo comboBox;

    /**
     * Creates a new cell editor with no control and no st of choices.
     * Initially, the cell editor has no cell validator.
     * @since 2.1
     * @see CellEditor#setStyle(int)
     * @see CellEditor#create(Composite)
     * @see #setItems(String[])
     * @see CellEditor#dispose()
     */
    @objid ("85964876-1926-11e2-92d2-001ec947c8cc")
    public ComboBoxCellEditor2() {
        setStyle(defaultStyle);
    }

    /**
     * Creates a new cell editor with a combo containing the given list of choices and parented under the given control.
     * The cell editor value is the zero-based index of the selected item. Initially, the cell editor has no cell
     * validator and the first item in the list is selected.
     * 
     * @param parent the parent control
     * @param items the list of strings for the combo box
     */
    @objid ("85964879-1926-11e2-92d2-001ec947c8cc")
    public ComboBoxCellEditor2(final Composite parent, final String[] items) {
        this(parent, items, defaultStyle);
    }

    /**
     * Creates a new cell editor with a combo containing the given list of choices and parented under the given control.
     * The cell editor value is the zero-based index of the selected item. Initially, the cell editor has no cell
     * validator and the first item in the list is selected.
     * 
     * @param parent the parent control
     * @param items the list of strings for the combo box
     * @param style the style bits
     * @since 2.1
     */
    @objid ("85964884-1926-11e2-92d2-001ec947c8cc")
    public ComboBoxCellEditor2(final Composite parent, final String[] items, final int style) {
        super(parent, style);
        setItems(items);
    }

    /**
     * Returns the list of choices for the combo box
     * 
     * @return the list of choices for the combo box
     */
    @objid ("85964891-1926-11e2-92d2-001ec947c8cc")
    public String[] getItems() {
        return this.items;
    }

    /**
     * Sets the list of choices for the combo box
     * 
     * @param items the list of choices for the combo box
     */
    @objid ("85964898-1926-11e2-92d2-001ec947c8cc")
    public void setItems(final String[] items) {
        Assert.isNotNull(items);
        this.items = items;
        populateComboBoxItems();
    }

    @objid ("8596489f-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void activate(final ColumnViewerEditorActivationEvent activationEvent) {
        super.activate(activationEvent);
        if (this.activationStyle != SWT.NONE) {
            boolean dropDown = false;
            if ((activationEvent.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION || activationEvent.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION) &&
                (this.activationStyle & DROP_DOWN_ON_MOUSE_ACTIVATION) != 0) {
                dropDown = true;
            } else if (activationEvent.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED &&
                       (this.activationStyle & DROP_DOWN_ON_KEY_ACTIVATION) != 0) {
                dropDown = true;
            } else if (activationEvent.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC &&
                       (this.activationStyle & DROP_DOWN_ON_PROGRAMMATIC_ACTIVATION) != 0) {
                dropDown = true;
            } else if (activationEvent.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL &&
                       (this.activationStyle & DROP_DOWN_ON_TRAVERSE_ACTIVATION) != 0) {
                dropDown = true;
            }
        
            if (dropDown) {
                getControl().getDisplay().asyncExec(new Runnable() {
        
                    @Override
                    public void run() {
                        ((CCombo) getControl()).setListVisible(true);
                    }
        
                });
        
            }
        }
    }

    @objid ("859b0d20-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected Control createControl(final Composite parent) {
        this.comboBox = new CCombo(parent, getStyle());
        this.comboBox.setFont(parent.getFont());
        
        populateComboBoxItems();
        
        this.comboBox.addKeyListener(new KeyAdapter() {
            // hook key pressed - see PR 14201
            @Override
            public void keyPressed(KeyEvent e) {
                keyReleaseOccured(e);
            }
        });
        
        this.comboBox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetDefaultSelected(SelectionEvent event) {
                applyEditorValueAndDeactivate();
            }
        
            @Override
            public void widgetSelected(SelectionEvent event) {
                ComboBoxCellEditor2.this.selection = ComboBoxCellEditor2.this.comboBox.getSelectionIndex();
                // Immediately apply the value
                applyEditorValueAndDeactivate();
            }
        });
        
        this.comboBox.addTraverseListener((TraverseEvent e) -> {
            if (e.detail == SWT.TRAVERSE_ESCAPE || e.detail == SWT.TRAVERSE_RETURN) {
                e.doit = false;
            }
        });
        
        this.comboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ComboBoxCellEditor2.this.focusLost();
            }
        });
        return this.comboBox;
    }

    /**
     * The <code>ComboBoxCellEditor</code> implementation of this <code>CellEditor</code> framework method returns the
     * zero-based index of the current selection.
     * 
     * @return the zero-based index of the current selection wrapped as an <code>Integer</code>
     */
    @objid ("859b0d2b-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected Object doGetValue() {
        return Integer.valueOf(this.selection);
    }

    @objid ("859b0d31-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void doSetFocus() {
        this.comboBox.setFocus();
    }

    /**
     * The <code>ComboBoxCellEditor</code> implementation of this <code>CellEditor</code> framework method sets the
     * minimum width of the cell. The minimum width is 10 characters if <code>comboBox</code> is not <code>null</code>
     * or <code>disposed</code> else it is 60 pixels to make sure the arrow button and some text is visible. The list of
     * CCombo will be wide enough to show its longest item.
     */
    @objid ("859b0d34-1926-11e2-92d2-001ec947c8cc")
    @Override
    public LayoutData getLayoutData() {
        LayoutData layoutData = super.getLayoutData();
        if ((this.comboBox == null) || this.comboBox.isDisposed()) {
            layoutData.minimumWidth = 60;
        } else {
            // make the comboBox 10 characters wide
            GC gc = new GC(this.comboBox);
            layoutData.minimumWidth = (gc.getFontMetrics().getAverageCharWidth() * 10) + 10;
            gc.dispose();
        }
        return layoutData;
    }

    /**
     * This method allows to control how the combo reacts when activated
     * 
     * @param activationStyle the style used
     */
    @objid ("859b0d3c-1926-11e2-92d2-001ec947c8cc")
    public void setActivationStyle(final int activationStyle) {
        this.activationStyle = activationStyle;
    }

    /**
     * The <code>ComboBoxCellEditor</code> implementation of this <code>CellEditor</code> framework method accepts a
     * zero-based index of a selection.
     * 
     * @param value the zero-based index of the selection wrapped as an <code>Integer</code>
     */
    @objid ("859b0d41-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void doSetValue(final Object value) {
        Assert.isTrue(this.comboBox != null);
        this.selection = ((Integer) value).intValue();
        this.comboBox.select(this.selection);
    }

    /**
     * Updates the list of choices for the combo box for the current control.
     */
    @objid ("859b0d47-1926-11e2-92d2-001ec947c8cc")
    private void populateComboBoxItems() {
        if (this.comboBox != null && this.items != null) {
            this.comboBox.removeAll();
            for (int i = 0; i < this.items.length; i++) {
                this.comboBox.add(this.items[i], i);
            }
        
            setValueValid(true);
            this.selection = 0;
        }
    }

    /**
     * Applies the currently selected value and deactivates the cell editor
     */
    @objid ("859b0d4a-1926-11e2-92d2-001ec947c8cc")
    void applyEditorValueAndDeactivate() {
        // must set the selection before getting value
        this.selection = this.comboBox.getSelectionIndex();
        Object newValue = doGetValue();
        markDirty();
        boolean isValid = isCorrect(newValue);
        setValueValid(isValid);
        
        if (!isValid) {
            // Only format if the 'index' is valid
            if (this.items.length > 0 && this.selection >= 0 && this.selection < this.items.length) {
                // try to insert the current value into the error message.
                setErrorMessage(MessageFormat.format(getErrorMessage(), new Object[] { this.items[this.selection] }));
            } else {
                // Since we don't have a valid index, assume we're using an
                // 'edit'
                // combo so format using its text value
                setErrorMessage(MessageFormat.format(getErrorMessage(), new Object[] { this.comboBox.getText() }));
            }
        }
        
        fireApplyEditorValue();
        deactivate();
    }

    @objid ("859b0d4d-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void focusLost() {
        if (isActivated()) {
            if (this.comboBox.getSelectionIndex() != this.selection) {
                applyEditorValueAndDeactivate();
            } else {
                fireCancelEditor();
                deactivate();
            }
        }
    }

    @objid ("859b0d50-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void keyReleaseOccured(final KeyEvent keyEvent) {
        if (keyEvent.character == '\u001b') { // Escape character
            fireCancelEditor();
        } else if (keyEvent.character == '\t') { // tab key
            applyEditorValueAndDeactivate();
        }
    }

}
