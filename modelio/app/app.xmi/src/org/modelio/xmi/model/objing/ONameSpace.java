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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("173252a2-c8e1-4227-8746-14427f4e1fc0")
public class ONameSpace extends OModelElement {
    @objid ("6d8eff4a-29d1-4ce9-aad8-26b13a005a2f")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    @objid ("8399e3fa-b3ab-459e-95f8-8dd546448825")
    public  ONameSpace(final ModelElement element) {
        super(element);
    }

    @objid ("07430b86-027d-4a81-9c1a-c0f12df50870")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        
    }

    @objid ("b6b51f6c-c1fb-4271-893b-35a17747698a")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        if (ecoreElt instanceof org.eclipse.uml2.uml.Classifier)
            this.setAbstract( (org.eclipse.uml2.uml.Classifier)ecoreElt);
        else if (GenerationProperties.getInstance().isRoundtripEnabled()){
            this.setAbstractEAnnotation(ecoreElt);
        }
        
    }

    @objid ("8a524f74-c36a-429f-ba45-04bbfe119083")
    private void setAbstract(final org.eclipse.uml2.uml.Classifier ecoreElt) {
        ecoreElt.setIsAbstract(((NameSpace)getObjingElement()).isIsAbstract());
    }

    @objid ("1c425a1a-6fe1-4ee2-9e00-17910b04dbb1")
    private void setAbstractEAnnotation(final org.eclipse.uml2.uml.Element ecoreElt) {
        Element objElement = getObjingElement();
        if (objElement instanceof NameSpace)
            ObjingEAnnotation.setIsAbstract(ecoreElt, ((NameSpace)objElement ).isIsAbstract());
        
    }

}
