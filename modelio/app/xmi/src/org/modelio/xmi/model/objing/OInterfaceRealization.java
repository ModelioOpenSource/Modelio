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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("f52bf05b-4671-4863-8d75-01d4a7dc6796")
public class OInterfaceRealization extends OModelElement {
    @objid ("30af37d9-009e-4850-8503-6538cdb8d405")
    private InterfaceRealization objingElement;

    @objid ("dcfc25b3-3cd2-4c17-a963-0d40cd30031e")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE
                                        .createInterfaceRealization();
    }

    @objid ("386b8299-c6cf-43cf-b9d9-b8e784d9dafa")
    public OInterfaceRealization(InterfaceRealization element) {
        super(element);
        this.objingElement = element;
    }

    @objid ("1a3ae940-f2fd-4d18-b38e-de0d1beb4205")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
                
        NameSpace objingImplementerNS = this.objingElement.getImplementer();
        Interface objingImplementedItf = this.objingElement.getImplemented();
                
        if (objingImplementerNS != null && objingImplementedItf != null) {
            // Gets or creates the ecore implementer org.eclipse.uml2.uml.Namespace:
            org.eclipse.uml2.uml. BehavioredClassifier ecoreImplementerNS = (org.eclipse.uml2.uml.BehavioredClassifier) genProp
                    .getMappedElement(objingImplementerNS);
                
            // Gets or creates the ecore implemented org.eclipse.uml2.uml.Interface:
            org.eclipse.uml2.uml.Interface ecoreImplementedItf = (org.eclipse.uml2.uml.Interface) genProp
                    .getMappedElement(objingImplementedItf);
                
            if (ecoreImplementerNS != null && ecoreImplementedItf != null) {
                org.eclipse.uml2.uml.InterfaceRealization ecoreItfReal = (org.eclipse.uml2.uml.InterfaceRealization) ecoreElt;
                ecoreItfReal.setContract(ecoreImplementedItf);
                ecoreItfReal.setImplementingClassifier(ecoreImplementerNS);
            }
        }
    }

    @objid ("f8875d77-c6e2-4968-888a-46c3b8f5ef96")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}
