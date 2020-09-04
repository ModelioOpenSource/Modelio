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

package org.modelio.uml.ui.dg.bpmn;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.common.PortContainerDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'BpmnBusinessRuleTask' element.
 */
@objid ("baa67d29-d6d0-49eb-a961-29a662d41f69")
public class BpmnBusinessRuleTaskDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("b39b29f3-322e-4ac4-9282-c45e63444a52")
    public BpmnBusinessRuleTaskDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("71c2c6f6-cd0e-4766-bb7e-c53a8b32fe9d")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        return Collections.emptyList();
    }

}
