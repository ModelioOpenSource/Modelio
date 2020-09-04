/* 
 * Copyright 2013-2018 Modeliosoft
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

@objid ("9192986c-4105-4809-a11a-7b99c4eb4253")
public class PasteAction extends Action {
    @objid ("8ad34f7a-9c12-49da-9ccd-be032fc6f9e8")
    private ElementPropertyController controller;

    @objid ("657cf053-9bb7-428f-acd8-8faeeb50ebc4")
    public PasteAction(ElementPropertyController controller) {
        this.controller = controller;
        this.setText(ModelProperty.I18N.getString("PasteElement.label"));
        this.setImageDescriptor(UI.getImageDescriptor("icons/paste.png"));
    }

    @objid ("209d1a31-ff85-4893-9452-2af561957305")
    @Override
    public void run() {
        this.controller.onPasteStereotype();
    }

    @objid ("8d3c7dd2-234e-4450-93d0-81ea76c72023")
    @Override
    public boolean isEnabled() {
        return this.controller.canPasteStereotype();
    }

}
