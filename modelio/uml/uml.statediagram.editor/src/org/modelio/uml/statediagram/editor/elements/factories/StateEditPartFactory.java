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
package org.modelio.uml.statediagram.editor.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.elements.common.label.base.ElementLabelEditPart;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.core.model.factory.GenericUserImageModeEditPartFactory;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.uml.statediagram.editor.elements.choice.ChoiceEditPart;
import org.modelio.uml.statediagram.editor.elements.choice.GmChoice;
import org.modelio.uml.statediagram.editor.elements.choice.GmChoicePrimaryNode;
import org.modelio.uml.statediagram.editor.elements.common.StateNonSelectableImageEditPart;
import org.modelio.uml.statediagram.editor.elements.connectionpoint.ConnectionPointEditPart;
import org.modelio.uml.statediagram.editor.elements.connectionpoint.GmConnectionPoint;
import org.modelio.uml.statediagram.editor.elements.connectionpoint.GmConnectionPointPrimaryNode;
import org.modelio.uml.statediagram.editor.elements.deephistory.DeepHistoryEditPart;
import org.modelio.uml.statediagram.editor.elements.deephistory.GmDeepHistory;
import org.modelio.uml.statediagram.editor.elements.deephistory.GmDeepHistoryPrimaryNode;
import org.modelio.uml.statediagram.editor.elements.entry.EntryEditPart;
import org.modelio.uml.statediagram.editor.elements.entry.GmEntry;
import org.modelio.uml.statediagram.editor.elements.entry.GmEntryPrimaryNode;
import org.modelio.uml.statediagram.editor.elements.exit.ExitEditPart;
import org.modelio.uml.statediagram.editor.elements.exit.GmExitPoint;
import org.modelio.uml.statediagram.editor.elements.exit.GmExitPointPrimaryNode;
import org.modelio.uml.statediagram.editor.elements.finalstate.FinalStateEditPart;
import org.modelio.uml.statediagram.editor.elements.finalstate.GmFinalState;
import org.modelio.uml.statediagram.editor.elements.finalstate.GmFinalStatePrimaryNode;
import org.modelio.uml.statediagram.editor.elements.fork.ForkStatePrimaryNodeEditPart;
import org.modelio.uml.statediagram.editor.elements.fork.ForkStateSatelliteContainerEditPart;
import org.modelio.uml.statediagram.editor.elements.fork.GmForkState;
import org.modelio.uml.statediagram.editor.elements.fork.GmForkStatePrimaryNode;
import org.modelio.uml.statediagram.editor.elements.initialstate.GmInitialState;
import org.modelio.uml.statediagram.editor.elements.initialstate.GmInitialStatePrimaryNode;
import org.modelio.uml.statediagram.editor.elements.initialstate.InitialStateEditPart;
import org.modelio.uml.statediagram.editor.elements.internaltransition.GmInternalTransition;
import org.modelio.uml.statediagram.editor.elements.internaltransition.InternalTransitionEditPart;
import org.modelio.uml.statediagram.editor.elements.join.GmJoin;
import org.modelio.uml.statediagram.editor.elements.join.GmJoinPrimaryNode;
import org.modelio.uml.statediagram.editor.elements.join.JoinPrimaryNodeEditPart;
import org.modelio.uml.statediagram.editor.elements.join.JoinSatelliteContainerEditPart;
import org.modelio.uml.statediagram.editor.elements.junction.GmJunction;
import org.modelio.uml.statediagram.editor.elements.junction.GmJunctionPrimaryNode;
import org.modelio.uml.statediagram.editor.elements.junction.JunctionEditPart;
import org.modelio.uml.statediagram.editor.elements.region.GmRegion;
import org.modelio.uml.statediagram.editor.elements.region.RegionEditPart;
import org.modelio.uml.statediagram.editor.elements.shallowhistory.GmShallowHistory;
import org.modelio.uml.statediagram.editor.elements.shallowhistory.GmShallowHistoryPrimaryNode;
import org.modelio.uml.statediagram.editor.elements.shallowhistory.ShallowHistoryEditPart;
import org.modelio.uml.statediagram.editor.elements.state.GmRegionsGroup;
import org.modelio.uml.statediagram.editor.elements.state.GmState;
import org.modelio.uml.statediagram.editor.elements.state.GmStatePrimaryNode;
import org.modelio.uml.statediagram.editor.elements.state.RegionsGroupEditPart;
import org.modelio.uml.statediagram.editor.elements.state.StateEditPart;
import org.modelio.uml.statediagram.editor.elements.state.StateSimpleEditPart;
import org.modelio.uml.statediagram.editor.elements.statediagram.GmStateDiagram;
import org.modelio.uml.statediagram.editor.elements.statediagram.StateDiagramEditPart;
import org.modelio.uml.statediagram.editor.elements.terminal.GmTerminal;
import org.modelio.uml.statediagram.editor.elements.terminal.GmTerminalPrimaryNode;
import org.modelio.uml.statediagram.editor.elements.terminal.TerminalEditPart;
import org.modelio.uml.statediagram.editor.elements.transition.GmTransition;
import org.modelio.uml.statediagram.editor.elements.transition.GmTransitionGuardLabel;
import org.modelio.uml.statediagram.editor.elements.transition.GmTransitionPostConditionLabel;
import org.modelio.uml.statediagram.editor.elements.transition.TransitionEditPart;

/**
 * The State diagram EditPart factory.
 * <p>
 * This factory only processes the state specific edit parts. It is intended to be used only as a cascaded factory in order to dynamically enriching the Modelio standard factory so that this latter ends by being able to process the complete UML. The
 * StateEditPartFactory does not provide edit part for simple and image mode.
 */
@objid ("f5933e1e-55b6-11e2-877f-002564c97630")
public class StateEditPartFactory implements EditPartFactory {
    /**
     * the default factory to use when structured mode is requested.
     */
    @objid ("f5933e22-55b6-11e2-877f-002564c97630")
    private final StructuredModeEditPartFactory structuredModeEditPartFactory = new StructuredModeEditPartFactory();

    @objid ("f594c479-55b6-11e2-877f-002564c97630")
    private final SimpleModeEditPartFactory simpleModeEditPartFactory = new SimpleModeEditPartFactory();

    /**
     * the default factory to use when image mode is requested.
     */
    @objid ("6ba3b634-07a2-42aa-907c-0c01154a20b9")
    private final EditPartFactory imageModeEditPartFactory = new ImageModeEditPartFactory();

    /**
     * the default factory to use when user image mode is requested.
     */
    @objid ("bfee634b-d3f0-4fca-980e-5034970495b2")
    private final EditPartFactory userImageModeEditPartFactory = new GenericUserImageModeEditPartFactory(this.imageModeEditPartFactory);

    @objid ("f594c47a-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart;
        if (model instanceof GmNodeModel) {
            // For node models, delegates according the representation model.
            GmNodeModel node = (GmNodeModel) model;
            switch (node.getRepresentationMode()) {
            case USER_IMAGE:
                editPart = this.userImageModeEditPartFactory.createEditPart(context, model);
                break;
            case IMAGE:
                editPart = this.imageModeEditPartFactory.createEditPart(context, model);
                break;
            case STRUCTURED:
                editPart = this.structuredModeEditPartFactory.createEditPart(context, model);
                break;
            case SIMPLE:
                editPart = this.simpleModeEditPartFactory.createEditPart(context, model);
                break;
            default:
                editPart = null; // generically supported by standard factory
            }
        
            return editPart;
        } else {
            // Link models are always in structured mode.
            editPart = this.structuredModeEditPartFactory.createEditPart(context, model);
            return editPart;
        }
        
    }

    /**
     * EditPart factory for node models in standard structured mode.
     * <p>
     * This is the default mode so the default factory.
     */
    @objid ("f594c487-55b6-11e2-877f-002564c97630")
    private static final class StructuredModeEditPartFactory implements EditPartFactory {
        @objid ("f594c489-55b6-11e2-877f-002564c97630")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart = null;
            
            if (model.getClass() == GmTerminal.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmChoice.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDeepHistory.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmShallowHistory.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJunction.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExitPoint.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmFinalState.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInitialState.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmEntry.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmForkState.class) {
                editPart = new ForkStateSatelliteContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJoin.class) {
                editPart = new JoinSatelliteContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmStateDiagram.class) {
                editPart = new StateDiagramEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmState.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmStatePrimaryNode.class) {
                editPart = new StateEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmRegionsGroup.class) {
                editPart = new RegionsGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmRegion.class) {
                editPart = new RegionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInternalTransition.class) {
                editPart = new InternalTransitionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJunctionPrimaryNode.class) {
                editPart = new JunctionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInitialStatePrimaryNode.class) {
                editPart = new InitialStateEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmFinalStatePrimaryNode.class) {
                editPart = new FinalStateEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmTerminalPrimaryNode.class) {
                editPart = new TerminalEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmChoicePrimaryNode.class) {
                editPart = new ChoiceEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExitPointPrimaryNode.class) {
                editPart = new ExitEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmEntryPrimaryNode.class) {
                editPart = new EntryEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmShallowHistoryPrimaryNode.class) {
                editPart = new ShallowHistoryEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmForkStatePrimaryNode.class) {
                editPart = new ForkStatePrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJoinPrimaryNode.class) {
                editPart = new JoinPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDeepHistoryPrimaryNode.class) {
                editPart = new DeepHistoryEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmConnectionPoint.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmConnectionPointPrimaryNode.class) {
                editPart = new ConnectionPointEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmTransition.class) {
                editPart = new TransitionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmTransitionGuardLabel.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmTransitionPostConditionLabel.class) {
                editPart = new ElementLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // not found
            return null;
        }

    }

    /**
     * EditPart factory for node models in standard simple mode.
     */
    @objid ("f594c490-55b6-11e2-877f-002564c97630")
    private static final class SimpleModeEditPartFactory implements EditPartFactory {
        @objid ("f594c492-55b6-11e2-877f-002564c97630")
        @Override
        public EditPart createEditPart(final EditPart context, final Object model) {
            EditPart editPart = null;
            
            if (model.getClass() == GmChoice.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmChoicePrimaryNode.class) {
                editPart = new ChoiceEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmConnectionPoint.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmConnectionPointPrimaryNode.class) {
                editPart = new ConnectionPointEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDeepHistory.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDeepHistoryPrimaryNode.class) {
                editPart = new DeepHistoryEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmEntry.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmEntryPrimaryNode.class) {
                editPart = new EntryEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExitPoint.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExitPointPrimaryNode.class) {
                editPart = new ExitEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmFinalState.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmFinalStatePrimaryNode.class) {
                editPart = new FinalStateEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmForkState.class) {
                editPart = new ForkStateSatelliteContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmForkStatePrimaryNode.class) {
                editPart = new ForkStatePrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInitialState.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInitialStatePrimaryNode.class) {
                editPart = new InitialStateEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJoin.class) {
                editPart = new JoinSatelliteContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJoinPrimaryNode.class) {
                editPart = new JoinPrimaryNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJunction.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJunctionPrimaryNode.class) {
                editPart = new JunctionEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmShallowHistory.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmShallowHistoryPrimaryNode.class) {
                editPart = new ShallowHistoryEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmStatePrimaryNode.class) {
                editPart = new StateSimpleEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmTerminal.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmTerminalPrimaryNode.class) {
                editPart = new TerminalEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // not found
            return null;
        }

    }

    /**
     * EditPart factory for node models in stereotype image mode.
     */
    @objid ("4ae2fe14-968e-470e-a655-b24b1ceec4ba")
    private static final class ImageModeEditPartFactory implements EditPartFactory {
        @objid ("d5043e58-28e3-4bf2-8ed8-9a6ac0e4e6a1")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart = null;
            
            if (model.getClass() == GmChoicePrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(false);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmConnectionPointPrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(true);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmDeepHistoryPrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(true);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmEntryPrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(true);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmExitPointPrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(true);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmFinalStatePrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(true);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmForkState.class) {
                editPart = new StateNonSelectableImageEditPart(false);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmForkStatePrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(false);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmInitialStatePrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(true);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJoin.class) {
                editPart = new StateNonSelectableImageEditPart(false);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJoinPrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(false);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmJunctionPrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(true);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmShallowHistoryPrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(true);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmStatePrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(false);
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmTerminalPrimaryNode.class) {
                editPart = new StateNonSelectableImageEditPart(false);
                editPart.setModel(model);
                return editPart;
            }
            
            // not found
            return null;
        }

    }

}
