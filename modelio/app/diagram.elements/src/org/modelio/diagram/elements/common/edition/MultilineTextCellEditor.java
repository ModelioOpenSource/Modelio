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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * Multi line text cell editor.
 * 
 * @author cmarin
 */
@objid ("7e30d539-1dec-11e2-8cad-001ec947c8cc")
public class MultilineTextCellEditor extends TextCellEditor {
    /**
     * Creates a new text string cell editor parented under the given control.
     * <p>
     * The cell editor value is the string itself, which is initially the empty string.
     * Initially, the cell editor has no cell validator.
     * 
     * @param parent the parent control
     */
    @objid ("7e30d53b-1dec-11e2-8cad-001ec947c8cc")
    public MultilineTextCellEditor(Composite parent) {
        super(parent, SWT.MULTI);
    }

    /**
     * Creates a new text string cell editor parented under the given control.
     * <p>
     * The cell editor value is the string itself, which is initially the empty string.
     * Initially, the cell editor has no cell validator.
     * 
     * @param parent the parent control
     * @param style more style bytes. See {@link org.eclipse.swt.widgets.Text}
     */
    @objid ("a96848b3-7f02-4a80-bf23-4b4a04389589")
    public MultilineTextCellEditor(Composite parent, int style) {
        super(parent, SWT.MULTI | style);
    }

}
