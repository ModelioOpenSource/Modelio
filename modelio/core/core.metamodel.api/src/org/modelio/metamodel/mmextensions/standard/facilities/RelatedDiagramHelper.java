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

package org.modelio.metamodel.mmextensions.standard.facilities;

import java.util.LinkedHashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * Helper class to compute 'related' diagrams of a model element.
 * 
 * @since 3.7
 */
@objid ("b56f03ec-5ae3-45e1-b9f3-405d740fabe2")
public abstract class RelatedDiagramHelper {
    /**
     * Get all 'related' diagrams of a model element, in the following order:
     * <ul>
     * <li>Diagrams linked through a << related_diagram >> Dependency;</li>
     * <li>Owned diagrams;</li>
     * <li>For BpmnBehaviors, related diagrams of its child BpmnProcesses</li>
     * <li>For BpmnCallActivities, related diagrams of the called BpmnProcess</li>
     * <li>For CallBehaviorActions, related diagrams of the called Behavior</li>
     * </ul>
     * <p>
     * <i>Please note that <b>diagrams the element is displayed into are not included</b>, you must call {@link ModelElement#getDiagramElement()} to get them.</i>
     * </p>
     * 
     * @param elt a model element.
     * @return a diagram list.
     */
    @objid ("83a6272c-bced-486d-8157-d1164557f54e")
    public static Set<AbstractDiagram> getRelatedDiagrams(final ModelElement elt) {
        final Set<AbstractDiagram> relatedDiagrams = new LinkedHashSet<>();
        
        // Get diagrams targeted by <<related_diagram>> dependencies.
        for (final Dependency dependency : elt.getDependsOnDependency()) {
            if (dependency.isStereotyped("ModelerModule", "related_diagram")) {
                final ModelElement relatedElement = dependency.getDependsOn();
                if (relatedElement instanceof AbstractDiagram) {
                    relatedDiagrams.add((AbstractDiagram) relatedElement);
                }
            }
        }
        
        // Get owned diagrams
        relatedDiagrams.addAll(elt.getProduct());
        
        if (elt instanceof BpmnParticipant) {
            // For BpmnParticipant, get related diagrams of the BpmnProcesses
            BpmnParticipant participant = (BpmnParticipant) elt;
            BpmnProcess process = participant.getProcess();
            if (process != null) {
                relatedDiagrams.addAll(RelatedDiagramHelper.getRelatedDiagrams(process));
            }
        } else if (elt instanceof BpmnCallActivity) {
            // For BpmnCallActivity, get related diagrams of the called element
            BpmnCallActivity callActivity = (BpmnCallActivity) elt;
        
            for (MethodologicalLink dep : callActivity.getDependsOnDependency(MethodologicalLink.class)) {
                if (dep.isStereotyped("ModelerModule", "Called")) {
                    ModelElement target = dep.getDependsOn();
                    if (target != null) {
                        relatedDiagrams.addAll(RelatedDiagramHelper.getRelatedDiagrams(target));
                    }
                }
            }
        } else if (elt instanceof CallBehaviorAction) {
            // For CallBehaviorAction, get related diagrams of the called Behavior
            CallBehaviorAction callBehavior = (CallBehaviorAction) elt;
            Behavior behavior = callBehavior.getCalled();
            if (behavior != null) {
                relatedDiagrams.addAll(RelatedDiagramHelper.getRelatedDiagrams(behavior));
            }
        }
        
        // Make sure the current diagram isn't part of the related diagrams
        relatedDiagrams.remove(elt);
        return relatedDiagrams;
    }

}
