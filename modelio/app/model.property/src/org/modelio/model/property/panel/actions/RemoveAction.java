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

package org.modelio.model.property.panel.actions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.Action;
import org.modelio.model.property.panel.ElementPropertyController;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.ui.plugin.UI;

@objid ("91fd6013-b589-4184-89d0-a8f427f744c9")
public class RemoveAction extends Action {
    @objid ("16f51c7f-49ee-45e7-b0e9-ca77989d3012")
    private ElementPropertyController controller;

    @objid ("fb62ff18-cfa6-448e-a8cc-2b74b1a992eb")
    public RemoveAction(ElementPropertyController controller) {
        this.controller = controller;
        this.setText(ModelProperty.I18N.getString("RemoveStereotype.label"));
        this.setImageDescriptor(UI.getImageDescriptor("icons/remove.png"));
    }

    @objid ("1266c217-a1b6-43a9-833e-5badd6ef8e05")
    @Override
    public void run() {
        this.controller.onRemoveStereotype();
    }

    @objid ("72940f05-94e1-44fe-bb38-0845942dbae7")
    @Override
    public boolean isEnabled() {
        return this.controller.canRemoveStereotype();
    }

}
