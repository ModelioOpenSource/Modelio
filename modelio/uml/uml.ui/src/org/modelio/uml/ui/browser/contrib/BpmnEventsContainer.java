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
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.platform.model.ui.swt.labelprovider.AbstractContainer;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("611678ea-beea-482d-b137-6314d54e9539")
public class BpmnEventsContainer extends AbstractContainer<BpmnEvent> {
    @objid ("573a21da-5829-445f-bb28-d8ecddd59a91")
    public BpmnEventsContainer(MObject owner, List<BpmnEvent> contents) {
        super(owner, contents);
    }

    @objid ("ca77c463-f9c6-4b7e-af81-3b3800e98b3a")
    @Override
    public String getLabel() {
        final int n = getContents().size();
        return (n <= 1 ? UmlUi.I18N.getMessage("BpmnEventsContainer.Single", n) : UmlUi.I18N.getMessage("BpmnEventsContainer.Multi", n));
    }

}
