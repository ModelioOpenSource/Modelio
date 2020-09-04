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

package org.modelio.model.property.panel.actions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.Action;
import org.modelio.model.property.panel.ElementPropertyController;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.ui.plugin.UI;

@objid ("394c7cc8-b55f-45cd-a8a6-0ed8805d4d08")
public class CopyAction extends Action {
    @objid ("0c5cd1f7-142b-467a-acbf-d3cda1d03cc2")
    private ElementPropertyController controller;

    @objid ("fe7bed18-11e5-4aec-862a-1196e4c74e97")
    public CopyAction(ElementPropertyController controller) {
        this.controller = controller;
        this.setText(ModelProperty.I18N.getString("CopyElement.label"));
        this.setImageDescriptor(UI.getImageDescriptor("icons/copy.png"));
    }

    @objid ("456f1848-2607-46f9-a245-61be46c52fa5")
    @Override
    public void run() {
        this.controller.onCopyStereotype();
    }

    @objid ("6890a754-a865-43d9-a939-09d762d56ebc")
    @Override
    public boolean isEnabled() {
        return this.controller.canCopyStereotype();
    }

}
