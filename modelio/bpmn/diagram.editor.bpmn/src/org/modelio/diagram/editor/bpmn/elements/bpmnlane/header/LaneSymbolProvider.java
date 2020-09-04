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

package org.modelio.diagram.editor.bpmn.elements.bpmnlane.header;

import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.participant.header.ParticipantSymbolProvider;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.PartitionElement;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Utility class that computes lane symbol.
 */
@objid ("612b4ca3-55b6-11e2-877f-002564c97630")
public class LaneSymbolProvider {
    /**
     * This class is not instanciable.
     */
    @objid ("612cd2fa-55b6-11e2-877f-002564c97630")
    private LaneSymbolProvider() {
    }

    /**
     * Get the lane label at the following format: "name : type"
     * 
     * @param elementNamer service that assigns a default name to new model elements.
     * @param lane the lane
     * @return the computed label
     */
    @objid ("612cd2fd-55b6-11e2-877f-002564c97630")
    public static String computeLabel(IElementNamer elementNamer, final BpmnLane lane) {
        StringBuilder ret = new StringBuilder();
        
        ModelElement type = PartitionElement.getTarget(lane);
        if (type != null) {
            if (!LaneSymbolProvider.hasDefaultName(lane, elementNamer)) {
                ret.append(lane.getName());
            }
        
            ret.append(": ");
            ret.append(type.getName());
        } else {
            ret.append(lane.getName());
        }
        
        if (ret.length() == 0) {
            ret.append(" ");
        }
        return ret.toString();
    }

    @objid ("f918d428-19b7-4c93-b25a-751e36a96fe3")
    private static boolean hasDefaultName(MObject element, IElementNamer elementNamer) {
        String basename = elementNamer.getBaseName(element);
        basename = Pattern.quote(basename);
        
        String aLabel = element.getName();
        return aLabel == null || aLabel.matches(basename + "[0-9]*");
    }

    /**
     * Get the lane label at the following format: "name : type"
     * 
     * @param elementNamer service that assigns a default name to new model elements.
     * @param elt the lane or participant
     * @return the computed label
     */
    @objid ("4f6e6823-89ed-443a-a4e1-d4dec06c5d77")
    public static String computeSimpleLabel(IElementNamer elementNamer, ModelElement elt) {
        if (elt instanceof BpmnLane) {
            return computeLabel(elementNamer, (BpmnLane) elt);
        } else if (elt instanceof BpmnParticipant) {
            return ParticipantSymbolProvider.computeLabel((BpmnParticipant) elt);
        } else {
            return " ";
        }
    }

}
