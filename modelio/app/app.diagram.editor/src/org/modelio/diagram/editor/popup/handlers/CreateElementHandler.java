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

package org.modelio.diagram.editor.popup.handlers;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This specific handler creates an element of the requested metaclass and attaches it to its composition owner using
 * the requested dependency. If provided, the stereotype is applied to the created element.
 * 
 * @author fpoyer
 * @see com.modeliosoft.modelio.diagram.editor.createpopup.contribs.CreationContributionItem
 */
@objid ("668d709f-33f7-11e2-95fe-001ec947c8cc")
public class CreateElementHandler extends AbstractDiagramCreateHandler {
    @objid ("668d70a1-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public boolean isToFilter(final Map<String, String> context) {
        if (!super.isToFilter(context))
            return false;
        
        // Forbid class-association creation on an association
        // that already has one class-association.
        String metaclassFromContext = context.get("metaclass");
        
        if ("ClassAssociation".equals(metaclassFromContext)) {
            String dependencyFromContext = context.get("dependency");
            if ("LinkToClass".equals(dependencyFromContext)) {
                MObject selectedElement = getSelectedElement();
                if (selectedElement instanceof Association) {
                    Association targetAssociation = (Association) selectedElement;
                    return (targetAssociation.getLinkToClass() == null);
                }
            }
        }
        return true;
    }

}
