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

package org.modelio.bpmn.ui.audit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractRule;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;

@objid ("0a4405a3-8cf1-4820-b611-966e9acbd6de")
public abstract class AbstractBpmnRule extends AbstractRule {
    @objid ("e68d537f-5466-4c2f-87b1-274c9294e933")
    public abstract void autoRegister(BpmnAuditPlan plan);

    /**
     * Return the BPMN process from a BPMN node.
     * @param lane a BPMN node.
     * @return The owner BPMN Process.
     */
    @objid ("2b168152-4f2f-46cf-8e43-5c2511316dcc")
    protected static BpmnProcess getProcess(final BpmnFlowElement node) {
        BpmnProcess process = node.getContainer();
        if (process != null) {
            return process;
        }
        
        BpmnSubProcess subProcess = node.getSubProcess();
        if (subProcess != null) {
            return getProcess(subProcess);
        }
        
        if (node instanceof BpmnDataInput) {
            BpmnActivity activity = ((BpmnDataInput) node).getOwnerActivity();
            if (activity != null) {
                return getProcess(activity);
            }
        } else if (node instanceof BpmnDataOutput) {
            BpmnActivity activity = ((BpmnDataOutput) node).getOwnerActivity();
            if (activity != null) {
                return getProcess(activity);
            }
        } else if (node instanceof BpmnLoopCharacteristics) {
            BpmnActivity activity = ((BpmnLoopCharacteristics) node).getOwnerActivity();
            if (activity != null) {
                return getProcess(activity);
            }
        }
        return null;
    }

}
