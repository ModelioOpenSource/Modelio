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

package org.modelio.diagram.editor.bpmn.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.handlers.OpenEditor;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * Specialized 'open editor' handler for Bpmn diagrams.
 * <p>
 * This handler is used to open the first diagram of a {@link BpmnProcess}, {@link BpmnParticipant} or {@link BpmnSubProcess}.
 * </p>
 */
@objid ("78e87181-b810-432b-85fb-3b90a994f0ce")
public class BpmnOpenEditor extends OpenEditor {
    @objid ("6fcf02c4-c6ce-4db2-8fc5-36816a37ef39")
    @Override
    protected boolean isHandled(Element elt) {
        if (elt instanceof BpmnParticipant) {
            return true;
        } else if (elt instanceof BpmnProcess) {
            return true;
        } else if (elt instanceof BpmnSubProcess) {
            return true;
        } else {
            return super.isHandled(elt);
        }
    }

    @objid ("ff31201d-e1fe-475a-8faf-2d9d1e97678e")
    @Override
    protected Element getMapping(Element elt) {
        if (elt instanceof BpmnParticipant) {
            BpmnProcess process = ((BpmnParticipant) elt).getProcess();
            return process != null ? getMapping(process) : null;
        } else if (elt instanceof BpmnProcess) {
            for (BpmnProcessDesignDiagram diag : ((BpmnProcess) elt).getProduct(BpmnProcessDesignDiagram.class)) {
                return diag;
            }
            return null;
        } else if (elt instanceof BpmnSubProcess) {
            for (BpmnSubProcessDiagram diag : ((BpmnSubProcess) elt).getProduct(BpmnSubProcessDiagram.class)) {
                return diag;
            }
            return null;
        } else {
            return super.getMapping(elt);
        }
    }

}
