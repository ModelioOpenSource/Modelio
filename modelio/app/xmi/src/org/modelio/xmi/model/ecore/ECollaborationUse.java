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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("2824e10c-a662-467d-9ea0-4fd8ec028c35")
public class ECollaborationUse extends ENamedElement {
    @objid ("3e8c5469-8950-42bf-ad70-4902920a9f48")
    private org.eclipse.uml2.uml.CollaborationUse ecoreElement = null;

    @objid ("07098e66-3add-468f-a686-e8598f1c7614")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createCollaborationUse();
    }

    @objid ("515730b2-ce0e-422a-bddc-f3d175cd90a3")
    public ECollaborationUse(org.eclipse.uml2.uml.CollaborationUse element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("3eeccd90-1658-49e7-82b3-236e7d86a66a")
    @Override
    public void attach(Element objingElt) {
        this.ecoreElement.getOwner();
        
        Element objOwner = (Element)  ReverseProperties.getInstance().getMappedElement(this.ecoreElement.getOwner());
        
        if (objOwner instanceof NameSpace)
            ((NameSpace)objOwner).getOwnedCollaborationUse().add((CollaborationUse)objingElt);
    }

    @objid ("c8af813f-63a9-4c72-9932-3faaab4cbda8")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setType((CollaborationUse) objingElt);
    }

    @objid ("b6949cd3-dacf-4394-b358-bed5f75666ab")
    private void setType(CollaborationUse objingElt) {
        org.eclipse.uml2.uml.Collaboration type = this.ecoreElement.getType();
        if (type != null) {
            Element objType = (Element)  ReverseProperties.getInstance().getMappedElement(type);
        
            if (objType instanceof Collaboration)
                objingElt.setType((Collaboration) objType);
        }
    }

}
