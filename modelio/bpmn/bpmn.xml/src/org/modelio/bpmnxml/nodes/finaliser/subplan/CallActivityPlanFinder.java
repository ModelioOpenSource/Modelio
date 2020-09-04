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

package org.modelio.bpmnxml.nodes.finaliser.subplan;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;

@objid ("8ebc2a3e-a005-48e3-9c1a-14475d5ee982")
public class CallActivityPlanFinder {
    @objid ("20d0ccb6-e461-44d0-a8ca-d82cb5df1b12")
    private Map<String, Object> elementsMap;

    @objid ("97cdc2ca-d2b5-4bf9-8a89-2c7240cd2942")
    public CallActivityPlanFinder(Map<String, Object> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @objid ("2aad9930-5216-487b-ac08-be295eb8608e")
    private BehaviorDiagram findDiagram(ModelElement modelioNode) {
        ModelElement node = modelioNode;
        if (node instanceof BpmnCallActivity) {
            ModelElement process = Called.getTarget(modelioNode);
            if (process instanceof BpmnProcess) {
                node = process;
            }
        }
        
        for (AbstractDiagram diag : node.getProduct()) {
            if (diag instanceof BpmnProcessDesignDiagram || diag instanceof BpmnSubProcessDiagram) {
                return (BehaviorDiagram) diag;
            }
        }
        return null;
    }

}
