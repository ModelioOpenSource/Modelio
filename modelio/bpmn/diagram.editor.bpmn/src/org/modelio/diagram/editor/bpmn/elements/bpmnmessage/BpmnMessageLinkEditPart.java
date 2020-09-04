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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.core.link.LinkEditPart;

/**
 * Edit part for {@link GmBpmnMessageLink}.
 * <p>
 * Removes note creation policy, which should only be available on the {@link GmBpmnMessagePrimaryNode}.
 * </p>
 */
@objid ("48fd4b22-8b40-4685-a33f-c8aa9b0dd31c")
public class BpmnMessageLinkEditPart extends LinkEditPart {
    @objid ("f1315c0c-a264-48fa-afff-375dd3a5c0e3")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, null);
    }

}
