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

package org.modelio.diagram.editor.activity.elements.partition.bodyhybridcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.diagram.editor.activity.elements.partition.bodyhybridcontainer.BodyHybridContainerEditPart.Behaviour;
import org.modelio.diagram.editor.activity.elements.partitioncontainer.PartitionDropEditPolicy;
import org.modelio.diagram.editor.activity.elements.policies.SmartDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;

/**
 * Very specific hybrid edit policy that behave either like a default free zone layout edit policy OR like a partition container layout edit policy, depending on the nature of the current children of the Gm.
 * 
 * @author fpoyer
 */
@objid ("2af56dfa-55b6-11e2-877f-002564c97630")
public class BodyHybridContainerDropEditPolicy extends AbstractEditPolicy {
    /**
     * Current behavioural state. Defaults to {@link Behavior.UNDEFINED}
     */
    @objid ("33d74b60-55b6-11e2-877f-002564c97630")
    private Behaviour behaviour = Behaviour.HYBRID;

    /**
     * This will be used to "get" the behaviour of a free zone policy.
     */
    @objid ("2af56dfc-55b6-11e2-877f-002564c97630")
    private SmartDropEditPolicy freeZonePolicy;

    /**
     * This will be used to "get" the behaviour of a partition container policy.
     */
    @objid ("3132d165-58a2-11e2-9574-002564c97630")
    private PartitionDropEditPolicy partitionContainerPolicy;

    /**
     * C'tor.
     */
    @objid ("2af56e02-55b6-11e2-877f-002564c97630")
    public BodyHybridContainerDropEditPolicy() {
        super();
        // Create an instance of both DefaultFreeZoneLayoutEditPolicy and
        // PartitionContainerLayoutEditPolicy.
        this.freeZonePolicy = new SmartDropEditPolicy();
        this.partitionContainerPolicy = new PartitionDropEditPolicy();
    }

    @objid ("2af56e05-55b6-11e2-877f-002564c97630")
    @Override
    public void activate() {
        super.activate();
        switch (this.behaviour) {
        case HYBRID: {
            this.freeZonePolicy.activate();
            this.partitionContainerPolicy.activate();
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.activate();
            this.partitionContainerPolicy.deactivate();
            break;
        }
        case PARTITION_CONTAINER: {
            this.freeZonePolicy.deactivate();
            this.partitionContainerPolicy.activate();
            break;
        }
        }
    }

    @objid ("2af56e08-55b6-11e2-877f-002564c97630")
    @Override
    public void deactivate() {
        switch (this.behaviour) {
        case HYBRID: {
            this.freeZonePolicy.deactivate();
            this.partitionContainerPolicy.deactivate();
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.deactivate();
            break;
        }
        case PARTITION_CONTAINER: {
            this.partitionContainerPolicy.deactivate();
            break;
        }
        }
        super.deactivate();
    }

    @objid ("2af56e0b-55b6-11e2-877f-002564c97630")
    @Override
    public void eraseSourceFeedback(Request request) {
        super.eraseSourceFeedback(request);
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.eraseSourceFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.eraseSourceFeedback(request);
            break;
        }
        case PARTITION_CONTAINER: {
            this.partitionContainerPolicy.eraseSourceFeedback(request);
            break;
        }
        }
    }

    @objid ("2af56e0f-55b6-11e2-877f-002564c97630")
    @Override
    public void eraseTargetFeedback(Request request) {
        super.eraseTargetFeedback(request);
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.eraseTargetFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.eraseTargetFeedback(request);
            break;
        }
        case PARTITION_CONTAINER: {
            this.partitionContainerPolicy.eraseTargetFeedback(request);
            break;
        }
        }
    }

    @objid ("2af56e13-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(Request request) {
        Command command = null;
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                command = editPolicy.getCommand(request);
            }
            break;
        }
        case FREE_ZONE: {
            command = this.freeZonePolicy.getCommand(request);
            break;
        }
        case PARTITION_CONTAINER: {
            command = this.partitionContainerPolicy.getCommand(request);
            break;
        }
        }
        return command;
    }

    @objid ("2af56e19-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(Request request) {
        EditPart targetEditPart = null;
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                targetEditPart = editPolicy.getTargetEditPart(request);
            }
            break;
        }
        case FREE_ZONE: {
            targetEditPart = this.freeZonePolicy.getTargetEditPart(request);
            break;
        }
        case PARTITION_CONTAINER: {
            targetEditPart = this.partitionContainerPolicy.getTargetEditPart(request);
            break;
        }
        }
        return targetEditPart;
    }

    @objid ("2af6f499-55b6-11e2-877f-002564c97630")
    @Override
    public void setHost(EditPart editpart) {
        super.setHost(editpart);
        this.freeZonePolicy.setHost(editpart);
        this.partitionContainerPolicy.setHost(editpart);
    }

    @objid ("2af6f49d-55b6-11e2-877f-002564c97630")
    @Override
    public void showSourceFeedback(Request request) {
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.showSourceFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.showSourceFeedback(request);
            break;
        }
        case PARTITION_CONTAINER: {
            this.partitionContainerPolicy.showSourceFeedback(request);
            break;
        }
        }
    }

    @objid ("2af6f4a1-55b6-11e2-877f-002564c97630")
    @Override
    public void showTargetFeedback(Request request) {
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                editPolicy.showTargetFeedback(request);
            }
            break;
        }
        case FREE_ZONE: {
            this.freeZonePolicy.showTargetFeedback(request);
            break;
        }
        case PARTITION_CONTAINER: {
            this.partitionContainerPolicy.showTargetFeedback(request);
            break;
        }
        }
    }

    @objid ("2af6f4a5-55b6-11e2-877f-002564c97630")
    @Override
    public boolean understandsRequest(Request request) {
        switch (this.behaviour) {
        case HYBRID: {
            GraphicalEditPolicy editPolicy = getPolicyInvolvedWith(request);
            if (editPolicy != null) {
                return editPolicy.understandsRequest(request);
            }
            // else
            return false;
        }
        case FREE_ZONE: {
            return this.freeZonePolicy.understandsRequest(request);
        }
        case PARTITION_CONTAINER: {
            return this.partitionContainerPolicy.understandsRequest(request);
        }
        }
        return false;
    }

    /**
     * Sets the behaviour to adopt.
     * 
     * @param value the new behaviour.
     */
    @objid ("2af6f4ab-55b6-11e2-877f-002564c97630")
    public void setBehaviour(Behaviour value) {
        // If state actually changes, update he behaviour.
        if (this.behaviour != value) {
            Behaviour previousBehaviour = this.behaviour;
            this.behaviour = value;
            switch (this.behaviour) {
            case HYBRID: {
                if (previousBehaviour == Behaviour.PARTITION_CONTAINER) {
                    this.freeZonePolicy.activate();
                }
                if (previousBehaviour == Behaviour.FREE_ZONE) {
                    this.partitionContainerPolicy.activate();
                }
                break;
            }
            case FREE_ZONE: {
                if (previousBehaviour == Behaviour.PARTITION_CONTAINER) {
                    this.partitionContainerPolicy.deactivate();
                    this.freeZonePolicy.activate();
                }
                if (previousBehaviour == Behaviour.HYBRID) {
                    this.partitionContainerPolicy.deactivate();
                }
                break;
            }
            case PARTITION_CONTAINER: {
                if (previousBehaviour == Behaviour.FREE_ZONE) {
                    this.freeZonePolicy.deactivate();
                    this.partitionContainerPolicy.activate();
                }
                if (previousBehaviour == Behaviour.HYBRID) {
                    this.freeZonePolicy.deactivate();
                }
                break;
            }
            }
        }
    }

    @objid ("2af6f4af-55b6-11e2-877f-002564c97630")
    private GraphicalEditPolicy getPolicyInvolvedWith(Request request) {
        if (ModelElementDropRequest.TYPE.equals(request.getType())) {
            return getPolicyInvolvedWith((ModelElementDropRequest) request);
        }
        return null;
    }

    /**
     * <p>
     * This Request analysis method is used when in hybrid mode, to determine which policy should handle a specific request.
     * </p>
     * <p>
     * Basically, if the request concerns the creation of sub partitions, then the partition container policy should handle it. Otherwise, the free zone policy is concerned.
     * </p>
     * 
     * @param request @return
     */
    @objid ("2af6f4b4-55b6-11e2-877f-002564c97630")
    private GraphicalEditPolicy getPolicyInvolvedWith(ModelElementDropRequest request) {
        if (this.partitionContainerPolicy.getTargetEditPart(request) != null) {
            return this.partitionContainerPolicy;
        }
        if (this.freeZonePolicy.getTargetEditPart(request) != null) {
            return this.freeZonePolicy;
        }
        return null;
    }

    /**
     * Returns the current behaviour.
     * 
     * @return the current behaviour.
     */
    @objid ("2af6f4bc-55b6-11e2-877f-002564c97630")
    public Behaviour getBehaviour() {
        return this.behaviour;
    }

}
