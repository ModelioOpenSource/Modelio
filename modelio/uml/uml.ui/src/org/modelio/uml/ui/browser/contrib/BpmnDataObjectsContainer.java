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

package org.modelio.uml.ui.browser.contrib;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.swt.labelprovider.AbstractContainer;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("12684ef9-e61a-471c-b922-8f9c05f3d7dc")
public class BpmnDataObjectsContainer extends AbstractContainer<BpmnItemAwareElement> {
    @objid ("f21a74e0-668b-4561-8151-bd049f4e1e56")
    public BpmnDataObjectsContainer(MObject owner, List<BpmnItemAwareElement> contents) {
        super(owner, contents);
    }

    @objid ("3736bbe6-6539-48e6-b213-c688b73f9d18")
    @Override
    public String getLabel() {
        final int n = getContents().size();
        return (n <= 1 ? UmlUi.I18N.getMessage("BpmnDataObjectsContainer.Single", n) : UmlUi.I18N.getMessage("BpmnDataObjectsContainer.Multi", n));
    }

}
