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
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("fcb608db-e565-4458-bb26-b0a8cf4807a3")
public class EEnumerationLiteral extends ENamedElement {
    @objid ("894ab0e6-53ca-41c7-90c0-e86d83781f13")
    private org.eclipse.uml2.uml.EnumerationLiteral ecoreElement = null;

    @objid ("66cf2cad-faa9-42a1-817a-184d8355b60f")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createEnumerationLiteral();
    }

    @objid ("487f30c5-c136-4252-94d8-ca33c791f200")
    public  EEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("95e787d7-5de5-4290-bfff-d6df5e0cf417")
    @Override
    public void attach(Element objingElt) {
        Object objingEnumeration = ReverseProperties.getInstance()
                .getMappedElement(this.ecoreElement.getEnumeration());
                
        if ((objingEnumeration != null) && (objingEnumeration instanceof Enumeration)) {
            EnumerationLiteral objingLiteral = (EnumerationLiteral) objingElt;
            objingLiteral.setValuated((Enumeration)objingEnumeration);
        }
        
    }

}
