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

package org.modelio.diagram.editor.activity.elements.activitydiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command used for smart unmask interaction: creating an ObjectNode representing the dropped element.
 * 
 * @author fpoyer
 */
@objid ("299800d9-55b6-11e2-877f-002564c97630")
public class SmartObjectNodeCommand extends Command {
    @objid ("299800db-55b6-11e2-877f-002564c97630")
    private MObject parentElement;

    @objid ("299800de-55b6-11e2-877f-002564c97630")
    private MObject toUnmask;

    @objid ("299800e1-55b6-11e2-877f-002564c97630")
    private Point location;

    @objid ("299800e2-55b6-11e2-877f-002564c97630")
    private EditPart parentEditPart;

    /**
     * @param dropLocation the location where the ObjectNode is to be unmasked.
     * @param toUnmask the element that the ObjectNode will represent.
     * @param parentEditPart the edit part handling the unmasking
     * @param parentElement the element that will own the new ObjectNode
     */
    @objid ("299800e3-55b6-11e2-877f-002564c97630")
    public SmartObjectNodeCommand(final Point dropLocation, final MObject toUnmask, final EditPart parentEditPart, final MObject parentElement) {
        this.location = dropLocation;
        this.toUnmask = toUnmask;
        this.parentEditPart = parentEditPart;
        this.parentElement = parentElement;
    }

    @objid ("299800f2-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        GmModel gmModel = (GmModel) this.parentEditPart.getModel();
        IGmDiagram gmDiagram = gmModel.getDiagram();
        IStandardModelFactory modelFactory = gmDiagram.getModelManager().getModelFactory().getFactory(IStandardModelFactory.class);
        
        // Create the smart node
        InstanceNode instanceNode = modelFactory.createInstanceNode();
        
        // Attach to parent
        MExpert mExpert = this.parentElement.getMClass().getMetamodel().getMExpert();
        final MDependency effectiveDependency = mExpert.getDefaultCompositionDep(this.parentElement, instanceNode);
        
        if (effectiveDependency == null) {
            StringBuilder msg = new StringBuilder();
            msg.append("Cannot find a composition dependency to attach ");
            msg.append(instanceNode.toString());
            msg.append(" to ");
            msg.append(this.parentElement.toString());
            throw new IllegalStateException(msg.toString());
        }
        
        this.parentElement.mGet(effectiveDependency).add(instanceNode);
        
        // Attach to dropped element
        if (this.toUnmask instanceof Instance) {
            instanceNode.setRepresented((Instance) this.toUnmask);
        } else if (this.toUnmask instanceof Attribute) {
            instanceNode.setRepresentedAttribute((Attribute) this.toUnmask);
        } else if (this.toUnmask instanceof AssociationEnd) {
            instanceNode.setRepresentedRole((AssociationEnd) this.toUnmask);
        } else if (this.toUnmask instanceof GeneralClass) {
            instanceNode.setType((GeneralClass) this.toUnmask);
        } else if (this.toUnmask instanceof Parameter) {
            Activity owningActivity = null;
            MObject tmp = this.parentElement;
            while (tmp != null) {
                if (tmp instanceof Activity) {
                    owningActivity = (Activity) tmp;
                    break;
                }
                tmp = tmp.getCompositionOwner();
            }
            if (owningActivity != null) {
                BehaviorParameter behaviorParameter = modelFactory.createBehaviorParameter();
                behaviorParameter.setOwner(owningActivity);
                behaviorParameter.setMapped((Parameter) this.toUnmask);
                instanceNode.setRepresentedRealParameter(behaviorParameter);
            } else {
                // TODO: handle error case?
            }
        }
        
        unmaskElement(instanceNode);
    }

    @objid ("299800f5-55b6-11e2-877f-002564c97630")
    private void unmaskElement(final MObject el) {
        final ModelioCreationContext gmCreationContext = new ModelioCreationContext(el);
        
        final CreateRequest creationRequest = new CreateRequest();
        creationRequest.setLocation(this.location);
        creationRequest.setSize(new Dimension(-1, -1));
        creationRequest.setFactory(gmCreationContext);
        
        final Command cmd = this.parentEditPart.getTargetEditPart(creationRequest).getCommand(creationRequest);
        if (cmd != null && cmd.canExecute()) {
            cmd.execute();
        }
    }

    @objid ("299800fb-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final GmModel gmModel = (GmModel) this.parentEditPart.getModel();
        final IGmDiagram gmDiagram = gmModel.getDiagram();
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        return this.parentElement != null && this.parentElement.isValid() && this.parentElement.getStatus().isModifiable();
    }

}
