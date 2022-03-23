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
import org.modelio.platform.ui.plugin.UI;

@objid ("18b2e215-1ff5-493d-86a0-b1dcb76a82b3")
public class CutAction extends Action {
    @objid ("3c491965-83ed-4fb4-afdb-92c0788168b7")
    private ElementPropertyController controller;

    @objid ("b2fb150d-2f4a-4bc7-893a-dea56c34b966")
    public  CutAction(ElementPropertyController controller) {
        this.controller = controller;
        this.setText(ModelProperty.I18N.getString("CutElement.label"));
        this.setImageDescriptor(UI.getImageDescriptor("icons/cut.png"));
        
    }

    @objid ("b61422e6-41ba-40a1-a572-8b3ee1c4b18b")
    @Override
    public void run() {
        this.controller.onCutStereotype();
    }

    @objid ("65c9d220-ae8f-4623-b3f7-103acb9206c6")
    @Override
    public boolean isEnabled() {
        return this.controller.canCutStereotype();
    }

}
