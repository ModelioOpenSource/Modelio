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
package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2AssociationReference;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearAssociationAction;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("55b46864-c754-4c74-91c0-b825b7abe37f")
public class EClearAssociationAction extends EActivityNode {
    @objid ("74b2b045-a9b7-46b1-a01b-1b64fbfe123a")
    private org.eclipse.uml2.uml.ClearAssociationAction ecoreElement;

    @objid ("0242d01d-02bb-43f5-a0c3-8f2379e7b3d7")
    @Override
    public Element createObjingElt() {
        return UML2ClearAssociationAction.create().getElement();
    }

    @objid ("e45e3016-7357-4257-80da-3a297a53d2e0")
    public  EClearAssociationAction(org.eclipse.uml2.uml.ClearAssociationAction element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("4cd50ff6-49b9-4d09-b72e-2221bb8d88a4")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setAssociation((OpaqueAction) objingElt);
        
    }

    @objid ("27c13eef-627c-4fdc-82a0-c0bf6b555cd0")
    private void setAssociation(OpaqueAction objingElt) {
        org.eclipse.uml2.uml.Association association = this.ecoreElement.getAssociation();
        
        if (association != null){
            Object obBehavior = ReverseProperties.getInstance().getMappedElement(association);
        
            if ((obBehavior != null) && (obBehavior instanceof ModelElement)) {
                Dependency dependency = UML2AssociationReference.create().getElement();        
                dependency.setDependsOn((ModelElement) obBehavior);
                dependency.setImpacted(objingElt);
            }
        }
        
    }

}
