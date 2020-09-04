/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("f0a853bf-e1a1-4236-b547-f8bb222fa91c")
public class OEnumerationLiteral extends OModelElement {
    @objid ("7cda5be0-3200-4de5-a008-11a8d513e015")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE
                                        .createEnumerationLiteral();
    }

    @objid ("b9edea2a-3b05-403f-8c05-59951b05b7ad")
    public OEnumerationLiteral(EnumerationLiteral element) {
        super(element);
    }

    @objid ("8f4e6a99-cbbd-4d96-b8d7-d8c8b741701d")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        Enumeration objingEnum =  getObjingElement().getValuated();
        
        if (objingEnum != null) {
            org.eclipse.uml2.uml.Enumeration ecoreEnum = (org.eclipse.uml2.uml.Enumeration) genProp
            .getMappedElement(objingEnum);
        
            if (ecoreEnum != null) {
                ecoreEnum.getOwnedLiterals().add((org.eclipse.uml2.uml.EnumerationLiteral)ecoreElt);
            }
        }
    }

    @objid ("d11e713a-d26c-4675-8c58-6621b191ddd0")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

    @objid ("0d5995f4-455e-494d-9054-942f03646479")
    @Override
    public EnumerationLiteral getObjingElement() {
        return (EnumerationLiteral) super.getObjingElement();
    }

}
