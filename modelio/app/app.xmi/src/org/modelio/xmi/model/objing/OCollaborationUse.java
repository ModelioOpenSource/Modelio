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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("f00c449e-99cd-417e-afa7-298a41b16ba2")
public class OCollaborationUse extends OModelElement {
    @objid ("3f21b35a-1943-4e5f-9f07-bd5971d1e4c9")
     GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("b91c7a38-b497-4c49-8dc7-dae6c36db6b9")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createCollaborationUse();
    }

    @objid ("a7eeba05-7a99-4d30-a095-0c9a49f03d9d")
    public OCollaborationUse(CollaborationUse param) {
        super(param);
    }

    @objid ("aa255297-255a-45e4-af29-941b1d611464")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = this.genProp.getMappedElement(getObjingElement().getCompositionOwner());
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.Classifier)
            ( (org.eclipse.uml2.uml.Classifier)ecoreOwner).getCollaborationUses().add( (org.eclipse.uml2.uml.CollaborationUse) ecoreElt);
    }

    @objid ("ae60cc0f-7f98-49ac-ac62-0f109028edb6")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setType( (org.eclipse.uml2.uml.CollaborationUse)ecoreElt);
    }

    @objid ("582c18a3-de18-4ee8-8998-62b479c153af")
    private void setType(org.eclipse.uml2.uml.CollaborationUse ecoreElt) {
        Element objType = getObjingElement().getType();
        if (objType != null) {
            org.eclipse.uml2.uml.Element ecoreType = this.genProp.getMappedElement(objType);
        
            if (ecoreType instanceof  org.eclipse.uml2.uml.Collaboration)
                ecoreElt.setType( (org.eclipse.uml2.uml.Collaboration)ecoreType);
        }
    }

    @objid ("240ee581-2f9a-43e4-aa27-2b4a086098a6")
    @Override
    public CollaborationUse getObjingElement() {
        return (CollaborationUse) super.getObjingElement();
    }

}
