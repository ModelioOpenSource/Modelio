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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("e53c1c8e-1d0a-472e-aa0c-f3ca1ffc6ed8")
public class ENamedElement extends EElement {
    @objid ("7372b2aa-5506-4be0-821f-e6c23b2b9fda")
    public ENamedElement(org.eclipse.uml2.uml.NamedElement element) {
        super(element);
    }

    @objid ("95f86853-ff0b-451f-b897-9e8b040b627f")
    private void setVisibility(NameSpace objingElt) {
        org.eclipse.uml2.uml.NamedElement ecoreNamedElt = (org.eclipse.uml2.uml.NamedElement) getEcoreElement();
        
        switch (ecoreNamedElt.getVisibility().getValue()) {
        case org.eclipse.uml2.uml.VisibilityKind.PACKAGE:
            objingElt
            .setVisibility(VisibilityMode.PACKAGEVISIBILITY);
            break;
        case org.eclipse.uml2.uml.VisibilityKind.PRIVATE:
            objingElt.setVisibility(VisibilityMode.PRIVATE);
            break;
        case org.eclipse.uml2.uml.VisibilityKind.PROTECTED:
            objingElt.setVisibility(VisibilityMode.PROTECTED);
            break;
        default:
            objingElt.setVisibility(VisibilityMode.PUBLIC);
        }
        
        if (ObjingEAnnotation.isUndefined(ecoreNamedElt))
            objingElt.setVisibility(VisibilityMode.VISIBILITYUNDEFINED);
    }

    @objid ("ab923ca8-9674-4783-a7fe-a24b1904ce7d")
    private void setName(final ModelElement objingElt) {
        String name = ((org.eclipse.uml2.uml.NamedElement)getEcoreElement()).getName();
        
        if (EcoreModelNavigation.isNotNull(name))
            objingElt.setName(name);
        else 
            objingElt.setName("");
    }

    @objid ("58ed30e2-e564-4b73-8822-a82c01fc6d1d")
    @Override
    public void setProperties(final Element objingElement) {
        super.setProperties(objingElement);
        
        if (objingElement != null){
            if (objingElement instanceof ModelElement)
                setName((ModelElement) objingElement);
        
            if (objingElement instanceof NameSpace)
                setVisibility((NameSpace)objingElement);
        }
    }

}
