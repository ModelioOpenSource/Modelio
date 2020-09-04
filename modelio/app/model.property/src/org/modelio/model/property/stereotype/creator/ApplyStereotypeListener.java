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

package org.modelio.model.property.stereotype.creator;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

@objid ("02ebdac0-3942-49f3-b953-82916e253479")
class ApplyStereotypeListener implements SelectionListener {
    @objid ("1905de7a-e5d5-4209-bf8f-b977571209fc")
    private StereotypeEditionDialog dialog = null;

    @objid ("2cba22fe-b8e4-4b58-a8cb-03fefce77cac")
    private StereotypeEditionDataModel dataModel = null;

    @objid ("8fc9893f-0e0b-43ca-ab58-841782fd3e6c")
    public ApplyStereotypeListener(StereotypeEditionDialog dialog, StereotypeEditionDataModel dataModel) {
        this.dialog = dialog;
        this.dataModel = dataModel;
    }

    @objid ("a2e20ffc-d54e-4899-ad36-eff9f9ff7ebd")
    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
        select();
    }

    @objid ("ff79672d-fa7b-49c7-8fc7-62b4b6075eff")
    @Override
    public void widgetSelected(SelectionEvent e) {
        select();
    }

    @objid ("dde22251-6de1-4bd8-b193-b627d2053615")
    private void select() {
        this.dataModel.setApplyStereotype(this.dialog.applyStereotypeCheckbox.getSelection());
    }

}
