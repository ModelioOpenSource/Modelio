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
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("382d83b0-1b72-4efe-bcb6-b9292b642f66")
public class OGeneralization extends OElement implements IOElement {
    @objid ("bfe63b08-7d62-4c48-abee-b7a41b9c9f1f")
    private Generalization objingElement;

    @objid ("8b8703f2-9f23-4c86-a461-03edf33c5cc9")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createGeneralization();
    }

    @objid ("2eafd1f9-3aea-450d-ad97-e967c3c4698e")
    public OGeneralization(Generalization element) {
        super(element);
        this.objingElement = element;
    }

    @objid ("d79aef30-3b18-464b-90d8-f40646afc226")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        NameSpace objingSubType = this.objingElement.getSubType();
        NameSpace objingSuperType = this.objingElement.getSuperType();
        
        // Gets or creates the ecore subtype element:
        org.eclipse.uml2.uml.Element ecoreSubType = genProp
                .getMappedElement(objingSubType);
        
        // Gets or creates the ecore supertype element:
        org.eclipse.uml2.uml.Element ecoreSuperType = genProp
                .getMappedElement(objingSuperType);
        
        if (ecoreSubType != null && ecoreSuperType != null) {
            org.eclipse.uml2.uml.Generalization ecoreLink = (org.eclipse.uml2.uml.Generalization) ecoreElt;
        
            if (ecoreSubType instanceof org.eclipse.uml2.uml.Classifier
                    && ecoreSuperType instanceof org.eclipse.uml2.uml.Classifier) {
                ecoreLink.setSpecific( (org.eclipse.uml2.uml.Classifier) ecoreSubType);
                ecoreLink.setGeneral( (org.eclipse.uml2.uml.Classifier) ecoreSuperType);
            }
            // If the relationship does not link classifiers, we have to export
            // the link in an
            // EAnnotation as it is not supported by UML2:
            else {
                EAnnotation objingEA = ObjingEAnnotation
                        .getOrCreateObjingEAnnotation(ecoreSubType);
                if (objingEA != null)
                    objingEA.getContents().add(ecoreLink);
                // TODO METTRE LOG
            }
        }
    }

    @objid ("f208959b-479c-4d51-8c95-de97f094dac9")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            setDiscriminatorEAnnotation((org.eclipse.uml2.uml.Generalization) ecoreElt);
        }
    }

    @objid ("b5dcff6b-25d0-48da-aece-c61d847b7233")
    private void setDiscriminatorEAnnotation(org.eclipse.uml2.uml.Generalization ecoreElt) {
        ObjingEAnnotation.setDiscriminator(ecoreElt, this.objingElement
                .getDiscriminator());
    }

}
