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

package org.modelio.core.ui.treetable.element;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.core.ui.swt.textelement.ITextElementSelectionListener;
import org.modelio.core.ui.swt.textelement.TextElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * JFace Cell editor usable in JFace table and TreeTable.
 * Allows the selection of a single model element. The underlying edition SWT
 * component is a {@link TextElement} that can be retrieved by
 * {@link SingleElementCellEditor#getTextElement()}. The underlying TextElement
 * is highly configurable to support filtering, completion, D&D, picking...
 * 
 * @author phv
 */
@objid ("8c066241-0fb1-4df1-bc34-9fb2fb23b75c")
public class SingleElementCellEditor extends CellEditor {
    @objid ("1418221a-cc9b-4e53-98fd-4d0c44a22223")
    private TextElement textElement;

    @objid ("3c4c533b-b08a-4eb9-b81a-70119f28c309")
    public SingleElementCellEditor(Composite parent) {
        super(parent);
    }

    @objid ("d6add040-4971-47be-8d15-17000f281813")
    public TextElement getTextElement() {
        return this.textElement;
    }

    @objid ("3e255bb3-38d1-448b-8cc5-6280898e1505")
    @Override
    protected Control createControl(Composite parent) {
        this.textElement = new TextElement(parent, SWT.NONE, true);
        this.textElement.addListener(new ITextElementSelectionListener() {
            @Override
            public void selectedElementChanged(MObject oldElement, MObject newElement) {
                if (!Objects.equals(oldElement, newElement)) {
                    applyEditorValueAndDeactivate();
                }
            }
        });
        return this.textElement.getTextControl();
    }

    /**
     * Applies the currently selected value and deactivates the cell editor
     */
    @objid ("43848c13-24d2-43a6-85c8-673cfdda8054")
    void applyEditorValueAndDeactivate() {
        markDirty();
        fireApplyEditorValue();
        deactivate();
    }

    @objid ("ff0230f6-e0ce-46c6-b7de-fadbd664a312")
    @Override
    protected Object doGetValue() {
        return this.textElement.getValue();
    }

    @objid ("fb3541d2-9f3c-41e8-bada-40433d9649ef")
    @Override
    protected void doSetFocus() {
        this.textElement.getTextControl().setFocus();
    }

    @objid ("3dfd1fdc-d3ab-4bd4-ad17-2d6cb9109ef8")
    @Override
    protected boolean dependsOnExternalFocusListener() {
        return false;
    }

    @objid ("00aada21-2d1f-40ad-9be0-d78f469583ea")
    @Override
    protected void doSetValue(Object value) {
        if (value instanceof MObject) {
            this.textElement.setValue((MObject) value);
        } else {
            this.textElement.setValue(null);
        }
    }

}
