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
import org.modelio.core.ui.swt.labelprovider.AbstractContainer;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("f831d42b-1de7-4df0-bc19-42da630164dd")
public class BpmnRolesContainer extends AbstractContainer<BpmnLane> {
    @objid ("c4a97fb0-a8a2-4d64-a3fb-a1f8b1865ae1")
    public BpmnRolesContainer(MObject owner, List<BpmnLane> contents) {
        super(owner, contents);
    }

    @objid ("073398ac-0fb4-4cfc-a6c5-7094a300086e")
    @Override
    public String getLabel() {
        final int n = getContents().size();
        return (n <= 1 ? UmlUi.I18N.getMessage("BpmnRolesContainer.Single", n) : UmlUi.I18N.getMessage("BpmnRolesContainer.Multi", n));
    }

}
