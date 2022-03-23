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
package org.modelio.diagram.styles.editingsupport.number;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

/**
 * A cell editor that manages an integer entry field. The cell editor's value is the integer string.
 * <p>
 * This class may be instantiated or subclassed.
 * </p>
 */
@objid ("85ae1fee-1926-11e2-92d2-001ec947c8cc")
public class IntegerCellEditor extends TextCellEditor {
    /**
     * Creates a new integer string cell editor parented under the given control. The cell editor value is the string
     * itself, which is initially the empty string. Initially, the cell editor has no cell validator.
     * @param parent the parent control
     */
    @objid ("85ae1ff0-1926-11e2-92d2-001ec947c8cc")
    public  IntegerCellEditor(Composite parent, int style) {
        super(parent, style);
        this.setValidator(new IntegerValidator(this));
        
    }

    @objid ("85ae1ff5-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void doSetValue(Object value) {
        String stringValue = "1";
        if (value instanceof Integer) {
            stringValue = ((Integer) value).toString();
        }
        super.doSetValue(stringValue);
        
    }

    @objid ("85ae1ff9-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected Object doGetValue() {
        final String stringValue = super.doGetValue().toString();
        return Integer.parseInt(stringValue);
    }

    /**
     * Check that the value is an integer.
     */
    @objid ("85ae1ffe-1926-11e2-92d2-001ec947c8cc")
    private static final class IntegerValidator implements ICellEditorValidator {
        @objid ("85ae2001-1926-11e2-92d2-001ec947c8cc")
        private IntegerCellEditor editor;

        @objid ("3ae9d3f5-ee8c-44b5-8353-a2955e8e4c9c")
        private Color defaultColor;

        @objid ("85ae2003-1926-11e2-92d2-001ec947c8cc")
        @Override
        public String isValid(Object value) {
            if (value instanceof Integer) {
                this.editor.getControl().setForeground(this.defaultColor);
                return null;
            }
            
            String stringValue = null;
            try {
                stringValue = (String) value;
                Integer.parseInt(stringValue);
                this.editor.getControl().setForeground(this.defaultColor);
                return null;
            } catch (NumberFormatException e) {
                this.editor.getControl().setForeground(this.editor.getControl()
                                                                  .getDisplay()
                                                                  .getSystemColor(SWT.COLOR_RED));
                return ("#NumberFormatException#" + stringValue);
            } catch (ClassCastException e) {
                this.editor.getControl().setForeground(this.editor.getControl()
                                                                  .getDisplay()
                                                                  .getSystemColor(SWT.COLOR_RED));
                return e.toString();
            }
            
        }

        @objid ("85ae2008-1926-11e2-92d2-001ec947c8cc")
        public  IntegerValidator(final IntegerCellEditor integerCellEditor) {
            this.editor = integerCellEditor;
            this.defaultColor = this.editor.getControl().getForeground();
            
        }

    }

}
