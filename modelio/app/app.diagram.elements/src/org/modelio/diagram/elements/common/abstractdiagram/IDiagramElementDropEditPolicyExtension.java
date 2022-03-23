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
package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This interface is used to add an unmask behavior to a diagram.
 * <p>
 * Implementation classes are contributed to the Eclipse extension point (See AbstractDiagramEditor#DROPPOLICYEXTENSION_ID).
 * </p>
 */
@objid ("89b93e86-def8-46a6-9acf-0dd16ed04b69")
public interface IDiagramElementDropEditPolicyExtension {
    /**
     * Creates and return the unmask command for the given element.
     * @param dropPolicy the drop policy of the current diagram
     * @param droppedElement the element to unmask.
     * @param dropLocation the point where the drop occured
     * @return the drop command.
     */
    @objid ("cea62454-aee0-4b3c-ab01-9cadb64addc9")
    Command getUnmaskCommandFor(DiagramElementDropEditPolicy dropPolicy, MObject droppedElement, Point dropLocation);

    /**
     * Returns whether the element should be unmasked as part of the given hierarchy or not. The method implementation MUST NOT add the element to the hierarchy.
     * @param context the context the graphical element is to be unmasked in.
     * @param hierarchy the hierarchy of elements that will be unmasked. Do not modify.
     * @param candidate the element for which to decide if it should be unmasked in the hierarchy
     * @return <code>true</code> if this element is to be unmasked, <code>false</code> otherwise.
     */
    @objid ("0d323690-fdca-4090-b75f-dbb7dd4db2a3")
    boolean isToBeAddedToHierarchy(IGmDiagram context, Deque<MObject> hierarchy, MObject candidate);

    /**
     * Return the graphical parent for 'element'. Can be redefined if the graphical parent of an element does not match its model composition owner.
     * @param context the context the graphical element is to be unmasked in.
     * @param element the element whose graphical parent is to be returned
     * @return the graphical parent for 'element'
     */
    @objid ("28147515-0d67-4380-8803-ccacc76c4d42")
    MObject getParentInGraphicalHierarchy(IGmDiagram context, MObject element);

    /**
     * Check if 'candidate' can be unmasked by this extension.
     * @param dropPolicy the drop policy of the current diagram
     * @param candidate the element for which to decide if it should be unmasked.
     * @return <code>true</code> if 'candidate' can be unmasked by this extension.
     */
    @objid ("8016633e-cb46-41b0-b13a-831d51dc1ec6")
    boolean canUnmask(DiagramElementDropEditPolicy dropPolicy, MObject candidate);

}
