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

package org.modelio.diagram.editor.state.elements.state;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.DeferredCreateCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodePolicy;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Create node edit policy that creates a region before creating a state vertex if there is none.
 */
@objid ("f5888fb1-55b6-11e2-877f-002564c97630")
public class StateCreateNodeEditPolicy extends DeferringCreateNodePolicy {
    @objid ("f5888fb5-55b6-11e2-877f-002564c97630")
    @Override
    protected EditPart getCreateTargetEditPart(CreateRequest createRequest) {
        // First call inherited behavior.
        EditPart ret = super.getCreateTargetEditPart(createRequest);
        if (ret != null) {
            return ret;
        }
        
        // Inherited failed, return host if the asked element
        // can be created under a region.
        if (createRequest.getNewObject() instanceof ModelioCreationContext) {
            final ModelioCreationContext ctx = (ModelioCreationContext) createRequest.getNewObject();
            MClass toCreate = ctx.getMetaclass();
            String depName = ctx.getDependencyName();
            MMetamodel mm = toCreate.getMetamodel();
            MExpert mExpert = mm.getMExpert();
        
            MClass owner = mm.getMClass(Region.class);
        
            if (mExpert.canCompose(owner, toCreate, depName)) {
                return getHost();
            }
        }
        return null;
    }

    @objid ("f5888fbb-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(CreateRequest createRequest) {
        // First try inherited behavior.
        EditPart editPart = super.getCreateTargetEditPart(createRequest);
        if (editPart == getHost()) {
            return super.getCreateCommand(createRequest);
        }
        
        GmModel gmModel = (GmModel) getHost().getModel();
        MMetamodel mm = gmModel.getDiagram().getModelManager().getMetamodel();
        MClass regionMClass = mm.getMClass(Region.class);
        
        // Inherited didn't work. Build a Command that:
        // - creates a region and unmask it
        // - validates all figures
        // - creates the asked element and unmask it under the region
        if (!stateHasRegion() && createRequest.getNewObject() instanceof ModelioCreationContext) {
        
            // First request to create a new region
            final ModelioCreationContext regionCtx = new ModelioCreationContext(regionMClass,
                    regionMClass.getDependency("OwnedRegion"), null);
        
            final CreateRequest createRegionReq = new CreateRequest();
            createRegionReq.setFactory(regionCtx);
            createRegionReq.setLocation(createRequest.getLocation());
        
            final CompoundCommand cmd = new CompoundCommand();
            final Command createRegionCommand = new DeferredCreateCommand(createRegionReq, getHost());
            cmd.add(createRegionCommand);
        
            // Validate the host figure and all its children so that
            // everybody's bounds are valid. (they are {0,0,0,0} without this)
            final Command validateCmd = new Command() {
                @Override
                public void execute() {
                    ((GraphicalEditPart) getHost()).getFigure().validate();
                }
            };
            cmd.add(validateCmd);
        
            // Then request to create the element into the region
            final Command createCommand = new DeferredCreateCommand(createRequest, getHost()) {
                @Override
                public boolean canExecute() {
                    // Override parent method because the GmRegion
                    // does not exist yet.
                    return true;
                }
            };
        
            cmd.add(createCommand);
        
            return cmd;
        } else if (stateHasRegion() && createRequest.getNewObject() instanceof ModelioCreationContext) {
            //Issue 11934
            return null;
        } else {
            // Call inherited behavior.
            return super.getCreateCommand(createRequest);
        }
    }

    @objid ("f58a161a-55b6-11e2-877f-002564c97630")
    private boolean stateHasRegion() {
        final GmStatePrimaryNode gmState = (GmStatePrimaryNode) getHost().getModel();
        return gmState.getCompositeFor(Region.class).hasChildren();
    }

}
