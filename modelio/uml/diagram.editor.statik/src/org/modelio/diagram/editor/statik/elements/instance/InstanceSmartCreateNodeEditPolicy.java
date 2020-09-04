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

package org.modelio.diagram.editor.statik.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodePolicy;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;

/**
 * {@link DeferringCreateNodePolicy} that allows creating "smart" Instances.
 */
@objid ("35434240-55b7-11e2-877f-002564c97630")
class InstanceSmartCreateNodeEditPolicy extends DeferringCreateNodePolicy {
    @objid ("35434244-55b7-11e2-877f-002564c97630")
    public InstanceSmartCreateNodeEditPolicy() {
        super();
    }

    @objid ("35434246-55b7-11e2-877f-002564c97630")
    @Override
    protected EditPart getCreateTargetEditPart(final CreateRequest createRequest) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx == null) {
            return super.getCreateTargetEditPart(createRequest);
        } else if (ctx.getMetaclass().getQualifiedName().equals(Instance.MQNAME) && ctx.getProperties().containsKey("smart")) {
            return getEditPartFor(BindableInstance.class, createRequest.getLocation());
        } else {
            return super.getCreateTargetEditPart(createRequest);
        }
    }

    @objid ("3543424c-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(final CreateRequest createRequest) {
        final ModelioCreationContext ctx = ModelioCreationContext.lookRequest(createRequest);
        if (ctx == null) {
            return super.getCreateCommand(createRequest);
        } else if (ctx.getMetaclass().getQualifiedName().equals(Instance.MQNAME) && ctx.getProperties().containsKey("smart")) {
            return new DeferredSmartCreateInstanceCommand(createRequest, getHost());
        } else {
            return super.getCreateCommand(createRequest);
        }
    }

}
