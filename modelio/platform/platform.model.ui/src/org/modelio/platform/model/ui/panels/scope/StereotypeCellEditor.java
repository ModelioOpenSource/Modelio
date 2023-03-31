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

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

@objid ("4edcbde5-34b1-413f-8ee5-5dbc582fe9be")
public final class StereotypeCellEditor extends TextCellEditor {
    @objid ("bdb0be2d-2fd8-48e3-bf88-bd20cd898bd7")
    private int decoPos;

    @objid ("d0fbe977-3b3d-486f-a0f0-8fe6ed1d571a")
    private StereotypeSelector selector;

    @objid ("c2639c79-deec-4d32-9e31-dc4fc17eb6c2")
    private final Predicate<Stereotype> stereotypeFilter;

    @objid ("beca8d9c-7f61-456a-888b-4c64ebef002e")
    private final Collection<Stereotype> availableStereotypes;

    @objid ("c19b7479-70c3-463a-b8ec-3767199c849e")
    public  StereotypeCellEditor(Composite parent, int decoPos, Collection<Stereotype> availableStereotypes, Predicate<Stereotype> stereotypeFilter) {
        // call no-arg constructor because #create(...) can't be called until the instance is fully initialized.
        super();
        this.decoPos = decoPos;
        this.availableStereotypes = Objects.requireNonNull(availableStereotypes);
        this.stereotypeFilter = stereotypeFilter;
        
        setStyle(SWT.SINGLE);
        create(parent);
        
    }

    @objid ("bb68c018-670b-4b82-9f56-42c223e49520")
    @Override
    protected boolean dependsOnExternalFocusListener() {
        // Stereotype selector opens a popup, we do not need
        // the standard focus listener
        return false;
    }

    @objid ("3df1d930-a0a1-466a-9c81-c423ca0e239b")
    @Override
    protected Object doGetValue() {
        Stereotype sel = this.selector.getSelected();
        if (sel != null) {
            return sel;
        } else {
            return super.doGetValue();
        }
        
    }

    @objid ("bd0eaf58-5678-4843-ac94-c72b1257c394")
    @Override
    protected Control createControl(Composite parent) {
        this.selector = new StereotypeSelector(parent, SWT.BORDER, this.decoPos, this.stereotypeFilter);
        this.selector.setStereotypes(this.availableStereotypes);
        
        this.selector.addListener(stereotype -> {
            fireApplyEditorValue();
            deactivate();
        });
        
        
        this.text = this.selector.getControl();
        return this.text;
    }

}
