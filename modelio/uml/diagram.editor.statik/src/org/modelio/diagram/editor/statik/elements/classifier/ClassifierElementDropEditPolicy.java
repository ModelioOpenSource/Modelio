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

package org.modelio.diagram.editor.statik.elements.classifier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.modelio.diagram.editor.statik.elements.instance.SmartCreatePartCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Overrides the default drop handling policy so that in some case it searches for the parent of the dropped element
 */
@objid ("96b1149b-55b6-11e2-877f-002564c97630")
public class ClassifierElementDropEditPolicy extends DefaultElementDropEditPolicy {
    @objid ("34322034-55b7-11e2-877f-002564c97630")
    @Override
    protected EditPart getDropTargetEditPart(final ModelElementDropRequest request) {
        final GmCompositeNode gmModel = (GmCompositeNode) getHost().getModel();
        
        // If either of the dropped elements cannot be unmasked, return null.
        for (final MObject droppedElement : request.getDroppedElements()) {
            if (!gmModel.canUnmask(droppedElement)) {
                // Gm doesn't know how to handle this element directly, look if
                // it is an element for which we can do something "smarter".
                if (!isSmartPartNodeTarget(droppedElement, request) &&
                        !isSmartCollabUseNodeTarget(droppedElement, request)) {
                    // It is not a smart interaction
                    //
                    // The Gm cannot unmask this element directly, and we don't know
                    // what to do with it... return null
                    return null;
                }
            }
        }
        
        // All dropped elements understood: return host!
        return getHost();
    }

    @objid ("3432203d-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getSmartDropCommand(final ModelElementDropRequest request) {
        final CompoundCommand command = new CompoundCommand();
        
        Point dropLocation = request.getDropLocation();
        
        for (final MObject toUnmask : request.getDroppedElements()) {
            if (isSmartPartNodeTarget(toUnmask, request)) {
                command.add(getSmartObjectNodeDropCommand(dropLocation, toUnmask));
            } else if (isSmartCollabUseNodeTarget(toUnmask, request)) {
                command.add(getSmartCreateCollabUseCommand(dropLocation, toUnmask));
            } else if (toUnmask != null) {
                // Deal with 'non-smart' drops
                command.add(createSubCommand(request, dropLocation, toUnmask));
            }
            // Introduce some offset, so that all elements are not totally
            // on top of each other.
            dropLocation = dropLocation.getTranslated(20, 20);
        }
        return command;
    }

    @objid ("34322046-55b7-11e2-877f-002564c97630")
    private Command getSmartObjectNodeDropCommand(final Point dropLocation, final MObject toUnmask) {
        final GmModel gmModel = (GmModel) getHost().getModel();
        final Classifier owner = (Classifier) gmModel.getRelatedElement();
        return new SmartCreatePartCommand(dropLocation, toUnmask, getHost(), owner);
    }

    @objid ("34322050-55b7-11e2-877f-002564c97630")
    private boolean isSmartPartNodeTarget(final MObject element, final ModelElementDropRequest request) {
        if (!request.isSmart()) {
            return false;
        }
        
        // Exception for ports
        if (element instanceof Port) {
            return false;
        }
        
        final GmModel gmModel = (GmModel) getHost().getModel();
        final Classifier owner = (Classifier) gmModel.getRelatedElement();
        return !owner.equals(element.getCompositionOwner()) &&
                !(owner instanceof Signal) &&
                (element instanceof AssociationEnd ||
                        element instanceof Attribute ||
                        element instanceof Instance ||
                        element instanceof NameSpace ||
                        element instanceof Parameter);
    }

    @objid ("3432205c-55b7-11e2-877f-002564c97630")
    private Command getSmartCreateCollabUseCommand(final Point dropLocation, final MObject toUnmask) {
        final GmModel gmModel = (GmModel) getHost().getModel();
        final Classifier owner = (Classifier) gmModel.getRelatedElement();
        return new SmartCreateCollabUseCommand(dropLocation, toUnmask, getHost(), owner);
    }

    @objid ("3433a6bd-55b7-11e2-877f-002564c97630")
    private boolean isSmartCollabUseNodeTarget(final MObject element, final ModelElementDropRequest request) {
        if (!request.isSmart()) {
            return false;
        }
        
        final GmModel gmModel = (GmModel) getHost().getModel();
        final Classifier owner = (Classifier) gmModel.getRelatedElement();
        return !owner.equals(element.getCompositionOwner()) && element instanceof Collaboration;
    }

    @objid ("52614648-b7cc-423b-8f44-80daf97f7bfe")
    private Command createSubCommand(final ModelElementDropRequest request, final Point dropLocation, final MObject toUnmask) {
        final ModelElementDropRequest subReq = new ModelElementDropRequest();
        subReq.setDroppedElements(new MObject[] { toUnmask });
        subReq.setExtendedData(request.getExtendedData());
        subReq.setLocation(dropLocation);
        subReq.setSmart(request.isSmart());
        return getDropCommand(subReq);
    }

}
