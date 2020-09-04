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

package org.modelio.core.ui.treetable.number;

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
@objid ("6b3e7711-1eba-11e2-9382-bc305ba4815c")
public class IntegerCellEditor extends TextCellEditor {
    /**
     * Creates a new integer string cell editor parented under the given control. The cell editor value is the string
     * itself, which is initially the empty string. Initially, the cell editor has no cell validator.
     * 
     * @param parent the parent control
     */
    @objid ("6b3e7713-1eba-11e2-9382-bc305ba4815c")
    public IntegerCellEditor(Composite parent, int style) {
        super(parent, style);
        this.setValidator(new IntegerValidator(this));
    }

    @objid ("6b3e9e23-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected void doSetValue(Object value) {
        String stringValue = "1";
        if (value instanceof Integer) {
            stringValue = ((Integer) value).toString();
        }
        super.doSetValue(stringValue);
    }

    @objid ("6b3ec532-1eba-11e2-9382-bc305ba4815c")
    @Override
    protected Object doGetValue() {
        final String stringValue = super.doGetValue().toString();
        return Integer.parseInt(stringValue);
    }

    /**
     * Check that the value is an integer.
     */
    @objid ("6b3eec41-1eba-11e2-9382-bc305ba4815c")
    private static final class IntegerValidator implements ICellEditorValidator {
        @objid ("6b3eec44-1eba-11e2-9382-bc305ba4815c")
        private IntegerCellEditor editor;

        @objid ("6b3f1350-1eba-11e2-9382-bc305ba4815c")
        private Color defaultColor;

        @objid ("6b3f1351-1eba-11e2-9382-bc305ba4815c")
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

        @objid ("6b3f3a60-1eba-11e2-9382-bc305ba4815c")
        public IntegerValidator(final IntegerCellEditor integerCellEditor) {
            this.editor = integerCellEditor;
            this.defaultColor = this.editor.getControl().getForeground();
        }

    }

}
