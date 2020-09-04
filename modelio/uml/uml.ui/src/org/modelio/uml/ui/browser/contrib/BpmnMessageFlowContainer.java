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
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("72272eca-c4bc-432f-b552-b94dcf0a5a43")
public class BpmnMessageFlowContainer extends AbstractContainer<BpmnBaseElement> {
    @objid ("56f44160-eaf3-40a4-bfba-6c5379509d05")
    public BpmnMessageFlowContainer(MObject owner, List<BpmnBaseElement> contents) {
        super(owner, contents);
    }

    @objid ("256096ff-2ae4-4c71-8fef-ad47570c1995")
    @Override
    public String getLabel() {
        final int n = this.getContents().size();
        return (n <= 1 ? UmlUi.I18N.getMessage("BpmnMessageFlowsContainer.Single", n) : UmlUi.I18N.getMessage("BpmnMessageFlowsContainer.Multi", n));
    }

}
