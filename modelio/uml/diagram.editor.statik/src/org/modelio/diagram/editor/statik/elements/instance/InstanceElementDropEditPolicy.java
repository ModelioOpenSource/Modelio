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

package org.modelio.diagram.editor.statik.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Overrides the default drop handling policy so that in some case it searches for the parent of the dropped element
 */
@objid ("e8346a95-c9a1-4fdc-a028-db1e570ac2c7")
public class InstanceElementDropEditPolicy extends DefaultElementDropEditPolicy {
    @objid ("08a5ee3c-8325-4f03-92c7-5ca364036117")
    @Override
    protected EditPart getDropTargetEditPart(ModelElementDropRequest request) {
        final GmCompositeNode gmModel = (GmCompositeNode) this.getHost().getModel();
        
        // If either of the dropped elements cannot be unmasked, return null.
        for (MObject droppedElement : request.getDroppedElements()) {
            if (!gmModel.canUnmask(droppedElement)) {
                // Gm doesn't know how to handle this element directly, look if
                // it is an element for which we can do something "smarter".
                if (!isSmartPartNodeTarget(droppedElement, request)) {
                    // It is not a smart interaction
                    //
                    // The Gm cannot unmask this element directly, and we don't know
                    // what to do with it... return null
                    return null;
                }
            }
        }
        
        // All dropped elements understood: return host!
        return this.getHost();
    }

    @objid ("9de49e0e-23a9-4f72-b10e-2726631d85e5")
    @Override
    protected Command getSmartDropCommand(ModelElementDropRequest request) {
        CompoundCommand command = new CompoundCommand();
        
        Point dropLocation = request.getDropLocation();
        
        for (MObject toUnmask : request.getDroppedElements()) {
            if (isSmartPartNodeTarget(toUnmask, request)) {
                command.add(getSmartObjectNodeDropCommand(dropLocation, toUnmask));
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

    @objid ("85729e32-e459-4b69-997c-5344a012da39")
    private Command getSmartObjectNodeDropCommand(final Point dropLocation, final MObject toUnmask) {
        final GmModel gmModel = (GmModel) getHost().getModel();
        final Instance owner = (Instance) gmModel.getRelatedElement();
        return new SmartCreatePartCommand(dropLocation, toUnmask, getHost(), owner);
    }

    @objid ("abd03719-330e-4567-8553-6cde3862bc3c")
    private boolean isSmartPartNodeTarget(final MObject element, final ModelElementDropRequest request) {
        if (!request.isSmart()) {
            return false;
        }
        
        // Exception for ports
        if (element instanceof Port) {
            return false;
        }
        
        final GmModel gmModel = (GmModel) getHost().getModel();
        final Instance owner = (Instance) gmModel.getRelatedElement();
        return (!owner.equals(element.getCompositionOwner()) && (element instanceof AssociationEnd ||
                element instanceof Attribute ||
                element instanceof Instance ||
                element instanceof NameSpace || element instanceof Parameter));
    }

    @objid ("8470488a-43a4-4aa8-bbdf-c1099ef59479")
    private Command createSubCommand(ModelElementDropRequest request, Point dropLocation, MObject toUnmask) {
        ModelElementDropRequest subReq = new ModelElementDropRequest();
        subReq.setDroppedElements(new MObject[] { toUnmask });
        subReq.setExtendedData(request.getExtendedData());
        subReq.setLocation(dropLocation);
        subReq.setSmart(request.isSmart());
        return getDropCommand(subReq);
    }

}
