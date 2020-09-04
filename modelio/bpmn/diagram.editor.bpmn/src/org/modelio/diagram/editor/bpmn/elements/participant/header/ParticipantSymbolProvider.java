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

package org.modelio.diagram.editor.bpmn.elements.participant.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;

/**
 * Utility class that computes participant symbol.
 */
@objid ("00d48cf2-5df2-408c-a2f4-dac55186e619")
public class ParticipantSymbolProvider {
    /**
     * This class is not instanciable.
     */
    @objid ("a8136cea-092a-4e64-b716-8e86537fe432")
    private ParticipantSymbolProvider() {
    }

    /**
     * Get the participant label which can be :
     * <ul>
     * <li>a process name if it references one.</li>
     * <li>a type name if it references one.</li>
     * <li>simply its name.</li>
     * <li>an empty string for a <code>null</code> participant.</li>
     * </ul>
     * 
     * @param participant the participant
     * @return the computed label.
     */
    @objid ("60e8bd9e-4a06-4cc3-94de-b1ecb24dd138")
    public static String computeLabel(BpmnParticipant participant) {
        StringBuilder ret = new StringBuilder();
        
        if (participant == null) {
            ret.append(" ");
        } else {
            BpmnProcess process = participant.getProcess();
            ModelElement type = Represents.getTarget(participant);
        
            if (process != null) {
                ret.append(process.getName());
            } else if (type != null) {
                ret.append(type.getName());
            } else {
                ret.append(participant.getName());
            }
        }
        
        if (ret.length() == 0) {
            ret.append(" ");
        }
        return ret.toString();
    }

}
