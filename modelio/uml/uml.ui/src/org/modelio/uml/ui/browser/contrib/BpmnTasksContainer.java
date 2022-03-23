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
package org.modelio.uml.ui.browser.contrib;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.platform.model.ui.swt.labelprovider.AbstractContainer;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("80944ed5-d72c-4ea3-b936-bb03a03bb716")
public class BpmnTasksContainer extends AbstractContainer<BpmnActivity> {
    @objid ("cf1f05ac-a039-4697-b57d-59b64913e7ef")
    public  BpmnTasksContainer(MObject owner, List<BpmnActivity> contents) {
        super(owner, contents);
    }

    @objid ("2f4fc4b6-1467-46fe-937a-16c93a9aea2b")
    @Override
    public String getLabel() {
        final int n = getContents().size();
        return (n <= 1 ? UmlUi.I18N.getMessage("BpmnTasksContainer.Single", n) : UmlUi.I18N.getMessage("BpmnTasksContainer.Multi", n));
    }

}
