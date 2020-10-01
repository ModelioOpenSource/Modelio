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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("9e7af896-b577-4e20-9b60-de945c16069c")
public class EConnector extends EFeature {
    @objid ("654d5826-92fc-4f2f-9618-0f191735b7c2")
    private org.eclipse.uml2.uml.Connector ecoreElement = null;

    @objid ("6dc37cae-b36f-4cff-b57f-62cf88365022")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createConnector();
    }

    @objid ("20f3c4be-139e-4248-88bb-0ce10e586bb1")
    public EConnector(org.eclipse.uml2.uml.Connector element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("9c6bc7a8-73b0-4b2e-91dd-e65ee4c7cf63")
    @Override
    public void attach(Element objingElt) {
        Link objingLink = (Link) objingElt;
        
        int nbEnds = 0;
        for (Object memberEnd : this.ecoreElement.getEnds()) {
            LinkEnd objingLinkEnd = (LinkEnd)  ReverseProperties.getInstance()
            .getMappedElement((org.eclipse.uml2.uml.Element) memberEnd);
        
            if (objingLinkEnd != null) {
                // Links the AssociationEnd to the org.eclipse.uml2.uml.Association:
                    objingLinkEnd.setLink(objingLink);
                    nbEnds++;
            }
        }
        
        if (nbEnds != 2)
            objingElt.delete();
    }

    @objid ("5a22e57d-2e96-44ac-bc34-60d0d5b2ace5")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setBase((Link) objingElt);
    }

    @objid ("aa8e8bdf-7c02-4717-8084-32ad2bf24f06")
    private void setBase(Link objingElt) {
        org.eclipse.uml2.uml.Association type = this.ecoreElement.getType();
        
        if (type != null){
            Element objAssociation = (Element)  ReverseProperties.getInstance().getMappedElement(type);
            if (objAssociation instanceof Association)
                objingElt.setModel((Association)objAssociation);
        }
    }

}
