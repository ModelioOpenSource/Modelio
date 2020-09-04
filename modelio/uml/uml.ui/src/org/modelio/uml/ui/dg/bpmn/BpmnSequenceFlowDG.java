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

package org.modelio.uml.ui.dg.bpmn;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.GmBpmnSequenceFlow;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;

/**
 * This class represents the DiagramGraphic of a 'BpmnSequenceFlow' element.
 */
@objid ("90d46b34-e703-4cfc-b580-f024828ab81b")
public class BpmnSequenceFlowDG extends DiagramLink {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The graphic model link represented by this class.
     */
    @objid ("25ed1eff-79ac-4114-8783-7ba86d0d8fa0")
    public BpmnSequenceFlowDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

    @objid ("d7e82608-7c73-4b6b-82f1-a977d1e915db")
    @Override
    protected Collection<IGmNode> getGmNodes(ExtensionRole role) {
        switch (role) {
        case EDGE_GUARD:
            return getGmLink().getExtensions(GmBpmnSequenceFlow.ROLE_GUARD);
        case MAIN:
            return getGmLink().getExtensions(IGmLink.ROLE_MAIN_LABEL);
        case BPMN_DATAOBJECT:
            return getGmLink().getExtensions(GmBpmnSequenceFlow.ROLE_DATAOBJECT);
            //$CASES-OMITTED$
        default:
            return Collections.emptyList();
        
        }
    }

}
