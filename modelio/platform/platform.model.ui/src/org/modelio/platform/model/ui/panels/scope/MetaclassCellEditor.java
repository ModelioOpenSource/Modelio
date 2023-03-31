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
package org.modelio.platform.model.ui.panels.scope;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.platform.model.ui.swt.selectmetaclass.MetaclassSelector;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Cell editor for {@link MClass} typed cells.
 * <p>
 * Create a metaclass selector widget on edition.
 */
@objid ("d11714b9-0349-4034-81b9-0918e37ad2da")
public class MetaclassCellEditor extends CellEditor {
    @objid ("f30d2de9-d68a-4d76-a15d-0617cd5b9c50")
    private MetaclassSelector selector;

    @objid ("9be4a6fa-db5c-405a-ab0f-b92f27d38e18")
    private MMetamodel metamodel;

    /**
     * Creates a new cell editor under the given parent control. The cell editor has no cell validator.
     * @param parent the parent control
     * @param metamodel the metamodel whose metaclass are proposed by the cell editor
     */
    @objid ("a1a5cac0-0f2f-4bef-bf7b-18df46b3b8c5")
    public  MetaclassCellEditor(Composite parent, MMetamodel metamodel) {
        super();
        this.metamodel= metamodel;
        create(parent);
        
    }

    @objid ("e599fd5b-e676-46c9-abe7-47c940e499cf")
    @Override
    protected boolean dependsOnExternalFocusListener() {
        // Metaclass selector opens a popup, we do not need the standard focus
        // listener
        return false;
    }

    @objid ("3cd3415e-48a1-4bc9-9667-8c3a68f1314d")
    @Override
    protected Control createControl(Composite parentComposite) {
        this.selector = new MetaclassSelector(parentComposite, SWT.BORDER, this.metamodel);
        this.selector.addListener((MClass mClass) -> {
            setValueValid(mClass != null);
            if (mClass != null) {
                fireApplyEditorValue();
            }
            deactivate();
        });
        return this.selector.getControl();
    }

    @objid ("345252c4-ad5f-4663-a7f1-326dae79361e")
    @Override
    protected Object doGetValue() {
        return this.selector.getSelected();
    }

    @objid ("dc5bda4f-6052-4a73-bf19-aeab69f92ade")
    @Override
    protected void doSetFocus() {
        this.selector.getControl().setFocus();
    }

    @objid ("ca8d3d77-687b-46e2-ac04-fcb305bf0139")
    @Override
    protected void doSetValue(Object value) {
        this.selector.setSelected((MClass) value);
    }

}
