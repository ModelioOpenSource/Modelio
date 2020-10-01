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

package org.modelio.metamodel.impl.mmextensions.standard.configurator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.events.BpmnCancelEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnErrorEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.events.BpmnTerminateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of {@link IElementConfigurator} for Standard metamodel fragment.
 */
@objid ("c6062c0e-d3f3-41de-b640-131bcdb7e333")
public class StandardElementConfigurator implements IElementConfigurator {
    /**
     * Property name for {@link #configure(IModelFactory, MObject, Map)}
     * to configure aggregation kind for associations.
     */
    @objid ("85700315-dc4c-4802-9d59-6fc08ac87f88")
    public static final String AGGREGATION = "aggregation";

    /**
     * Property name for {@link #configure(IModelFactory, MObject, Map)}
     * to configure activity nodes and activity parameters.
     */
    @objid ("e25dcfc8-6ebe-4d58-adbd-5dc53a9dc32b")
    public static final Object COMPLETE = "complete";

    /**
     * Property name for {@link #configure(IModelFactory, MObject, Map)}
     * to configure BPMN event types.
     */
    @objid ("0cf64ef4-8d53-469c-9944-32c085a0a36b")
    private static final Object TYPE = "type";

    /**
     * Property name for {@link #configure(IModelFactory, MObject, Map)}
     * to configure BPMN start event types.
     */
    @objid ("2a6487f3-79d3-458a-afef-6570d5bb40cd")
    public static final Object ISINTERRUPTING = "isinterrupting";

    @objid ("acbfb6ad-881f-4ad7-8ecc-32369d96baba")
    @Override
    public void configure(MObject element, Map<String, Object> properties) {
        ICoreSession session = CoreSession.getSession(element);
        ElementConfiguratorVisitor visitor = new ElementConfiguratorVisitor(
                MTools.get(session).getModelFactory(IStandardModelFactory.class),
                properties);
        
        element.accept(visitor);
    }

    @objid ("c745700e-ddc0-4084-bc51-3a266176de0c")
    private static class ElementConfiguratorVisitor extends DefaultModelVisitor {
        @objid ("a203cc2b-c11b-4e66-8ffd-8ac6811a1d4f")
        private IStandardModelFactory modelFactory;

        @objid ("5c34b0ea-fc59-40f1-acd9-867e5eac5aea")
        private Map<String, Object> properties;

        @objid ("79aec992-f3d8-4e18-8563-f55286b8601b")
        public ElementConfiguratorVisitor(IStandardModelFactory modelFactory, Map<String, Object> properties) {
            this.modelFactory = modelFactory;
            this.properties = properties;
        }

        @objid ("ba64da7f-0cf7-4000-b81d-b77c589b47e1")
        @Override
        public Object visitActivityParameterNode(ActivityParameterNode theActivityParameterNode) {
            if (this.properties.containsKey(StandardElementConfigurator.COMPLETE)) {
                // Create the behavior parameter
                final BehaviorParameter param = this.modelFactory.createBehaviorParameter();
            
                final Activity activity = theActivityParameterNode.getOwner();
                activity.getParameter().add(param);
            
                // Associate the behavior parameter to the activity parameter
                // node.
                theActivityParameterNode.setRepresentedRealParameter(param);
                theActivityParameterNode.setName(param.getName());
            
            }
            return theActivityParameterNode;
        }

        @objid ("706486ff-9ca5-4f26-a238-ec7a026a3c3b")
        @Override
        public Object visitAssociationEnd(AssociationEnd theAssociationEnd) {
            AggregationKind v = theAssociationEnd.getAggregation();
            String prop = (String) this.properties.get(StandardElementConfigurator.AGGREGATION);
            if (prop != null) {
                v = AggregationKind.valueOf(prop);
            }
            if (v != null) {
                theAssociationEnd.setAggregation(v);
            
                if (v == AggregationKind.KINDISCOMPOSITION) {
                    AssociationEnd opposite = theAssociationEnd.getOpposite();
                    opposite.setMultiplicityMin("1");
                    opposite.setMultiplicityMax("1");
                } else if (v == AggregationKind.KINDISAGGREGATION) {
                    AssociationEnd opposite = theAssociationEnd.getOpposite();
                    opposite.setMultiplicityMin("0");
                    opposite.setMultiplicityMax("1");
                } else {
                    theAssociationEnd.setMultiplicityMin("0");
                    theAssociationEnd.setMultiplicityMax("1");
                }
            
            }
            return super.visitAssociationEnd(theAssociationEnd);
        }

        @objid ("61367e9b-4b04-4b07-b6d1-3bd2503ef3b2")
        @Override
        public Object visitObjectNode(ObjectNode theObjectNode) {
            if (this.properties.containsKey(StandardElementConfigurator.COMPLETE)) {
                // Create a variable in the activity local collaboration
                final Activity activity = ElementConfiguratorVisitor.getActivity(theObjectNode);
                final Collaboration locals = getLocalCollaboration(activity);
                final BindableInstance var = this.modelFactory.createBindableInstance();
            
                var.setOwner(locals);
                ICoreSession session = CoreSession.getSession(var);
                //var.setName(MTools.get(session).getNamer().getUniqueName(var));
                var.setName(theObjectNode.getName());
            
                // Let the node reference the variable
                theObjectNode.setRepresented(var);
                //theObjectNode.setName(var.getName());
            
            }
            return theObjectNode;
        }

        @objid ("6b2f0859-155f-43e7-9c00-5a883a926b47")
        private static Activity getActivity(ActivityNode aNode) {
            MObject o = aNode;
            
            while (o != null && !(o instanceof Activity)) {
                o = o.getCompositionOwner();
            }
            return (Activity) o;
        }

        @objid ("5f52971c-135d-4429-9108-f35a548ea05d")
        private Collaboration getLocalCollaboration(Activity activity) {
            final List<Collaboration> collabs = activity.getOwnedCollaboration();
            // Return the first collaboration names "locals"
            for (Collaboration c : collabs) {
                if (c.getName().equals("locals")) {
                    return c;
                }
            }
            
            // Return the first collaboration
            if (!collabs.isEmpty()) {
                return collabs.get(0);
            }
            
            // Create a "locals" collaboration and return it.
            final Collaboration created = this.modelFactory.createCollaboration();
            created.setName("locals");
            activity.getOwnedCollaboration().add(created);
            return created;
        }

        @objid ("ae331f42-ddcf-4dba-82a6-8e7e151e8c93")
        @Override
        public Object visitBpmnStartEvent(BpmnStartEvent theStartEvent) {
            if (this.properties.containsKey(StandardElementConfigurator.ISINTERRUPTING)) {
                boolean value = Boolean.valueOf((boolean) this.properties.get(StandardElementConfigurator.ISINTERRUPTING));
                theStartEvent.setIsInterrupting(value);
            }
            return super.visitBpmnStartEvent(theStartEvent);
        }

        @objid ("0ba5bb23-2b15-4ee9-b23f-551bc9bc1724")
        @Override
        public Object visitBpmnEvent(BpmnEvent theEvent) {
            if (this.properties.containsKey(StandardElementConfigurator.TYPE)) {
                Class<? extends BpmnEventDefinition> eventType;
                switch ((String) this.properties.get(StandardElementConfigurator.TYPE)) {
                case "MULTIPLE":
                    List<BpmnEventDefinition> definitions = theEvent.getEventDefinitions();
                    if (definitions.size() == 0) {
                        createEventType(theEvent, BpmnMessageEventDefinition.class);
                        createEventType(theEvent, BpmnSignalEventDefinition.class);
                    } else if (definitions.size() == 1) {
                        createEventType(theEvent, BpmnMessageEventDefinition.class);
                    }
                    return theEvent;
                case "CANCEL":
                    eventType = BpmnCancelEventDefinition.class;
                    break;
                case "COMPENSATE":
                    eventType = BpmnCompensateEventDefinition.class;
                    break;
                case "CONDITIONAL":
                    eventType = BpmnConditionalEventDefinition.class;
                    break;
                case "ERROR":
                    eventType = BpmnErrorEventDefinition.class;
                    break;
                case "ESCALATION":
                    eventType = BpmnEscalationEventDefinition.class;
                    break;
                case "LINK":
                    eventType = BpmnLinkEventDefinition.class;
                    break;
                case "MESSAGE":
                    eventType = BpmnMessageEventDefinition.class;
                    break;
                case "SIGNAL":
                    eventType = BpmnSignalEventDefinition.class;
                    break;
                case "TERMINATE":
                    eventType = BpmnTerminateEventDefinition.class;
                    break;
                case "TIMER":
                    eventType = BpmnTimerEventDefinition.class;
                    break;
                case "NONE":
                default:
                    eventType = null;
                }
            
                deleteEventType(theEvent);
                if (eventType != null) {
                    createEventType(theEvent, eventType);
                }
            
                // Set default name
                theEvent.setName(MTools.get(theEvent).getNamer().getUniqueName(theEvent));
            }
            return theEvent;
        }

        @objid ("9e1a582b-d91c-4536-95f3-dc232b252422")
        private void createEventType(BpmnEvent event, Class<? extends BpmnEventDefinition> eventType) {
            BpmnEventDefinition event_definition = this.modelFactory.createElement(eventType);
            event_definition.setDefined(event);
        }

        @objid ("158e0a17-6309-4044-9fab-13a1adc3f8cf")
        private void deleteEventType(BpmnEvent event) {
            for (BpmnEventDefinition definition : new ArrayList<>(event.getEventDefinitions())) {
                definition.delete();
            }
        }

        /**
         * Create the mandatory top region.
         */
        @objid ("beb1feea-1819-4f93-a3b6-b217f041fdf4")
        @Override
        public Object visitStateMachine(StateMachine stateMachine) {
            Region topRegion = this.modelFactory.createRegion();
            stateMachine.setTop(topRegion);
            return stateMachine;
        }

    }

}
