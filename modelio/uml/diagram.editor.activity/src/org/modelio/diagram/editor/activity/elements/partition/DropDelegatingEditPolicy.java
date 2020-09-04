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

package org.modelio.diagram.editor.activity.elements.partition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specialisation that will delegate handling of dropped partitions to the "body" edit part.
 * 
 * @author fpoyer
 */
@objid ("2afd3638-55b6-11e2-877f-002564c97630")
public class DropDelegatingEditPolicy extends DefaultElementDropEditPolicy {
    @objid ("2afd363c-55b6-11e2-877f-002564c97630")
    @Override
    protected EditPart getDropTargetEditPart(ModelElementDropRequest request) {
        // If at least 1 partition is dropped, delegate to body
        boolean delegate = false;
        for (MObject droppedElement : request.getDroppedElements()) {
            if (droppedElement instanceof ActivityPartition) {
                delegate = true;
            }
        }
        if (delegate) {
            // Delegate to the "body" edit part.
            GmCompositeNode gmPartitionContainer = ((GmCompositeNode) getHost().getModel()).getCompositeFor(ActivityPartition.class);
            if (gmPartitionContainer != null) {
                // We have the model, lets find the corresponding edit part
                EditPart partitionContainerEditPart = (EditPart) getHost().getViewer()
                        .getEditPartRegistry()
                        .get(gmPartitionContainer);
                if (partitionContainerEditPart != null) {
                    return partitionContainerEditPart.getTargetEditPart(request);
                }
            }
            // Something along the way went wrong: we don't know how to
            // handle this request.
            return null;
        }
        return super.getDropTargetEditPart(request);
    }

}
