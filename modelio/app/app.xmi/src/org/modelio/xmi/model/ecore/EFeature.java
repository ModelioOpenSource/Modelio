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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("05beb6c2-9ca2-4495-a535-bd4719439190")
public class EFeature extends ENamedElement {
    @objid ("b818f9e7-5071-4ce0-80e8-821901985ea1")
    private org.eclipse.uml2.uml.Feature ecoreFeatureElt = null;

    @objid ("9def6d61-d437-4f82-9f5f-63fad1be9a77")
    public  EFeature(org.eclipse.uml2.uml.Feature element) {
        super(element);
        this.ecoreFeatureElt = element;
        
    }

    @objid ("0465481d-987d-45bc-b4fc-65157ab8fecf")
    private void setVisibility(Feature objingElement) {
        switch (this.ecoreFeatureElt.getVisibility().getValue()) {
        case org.eclipse.uml2.uml.VisibilityKind.PACKAGE:
            objingElement
            .setVisibility(VisibilityMode.PACKAGEVISIBILITY);
            break;
        case org.eclipse.uml2.uml.VisibilityKind.PRIVATE:
            objingElement.setVisibility(VisibilityMode.PRIVATE);
            break;
        case org.eclipse.uml2.uml.VisibilityKind.PROTECTED:
            objingElement.setVisibility(VisibilityMode.PROTECTED);
            break;
        case org.eclipse.uml2.uml.VisibilityKind.PUBLIC:
            objingElement.setVisibility(VisibilityMode.PUBLIC);
            break;
        default:
            objingElement.setVisibility(null);
        }
        
    }

    @objid ("ec7b38f9-d6d2-457c-a875-de7a47e46931")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        if (objingElt instanceof Feature){
            Feature feature = (Feature) objingElt;
            setVisibility(feature);
            setClass(feature);
        
            if (ReverseProperties.getInstance().isRoundtripEnabled()) {
                setIsUndefined(feature);
                setAbstractEAnnotation(feature);
            }
        
        }
        
    }

    @objid ("dcec4dcd-3390-4800-8fd1-c25e1f1a1952")
    private void setClass(Feature feature) {
        feature.setIsClass(this.ecoreFeatureElt.isStatic());
    }

    @objid ("8ea438a3-f52c-4acb-a2c7-515fe614d01a")
    private void setIsUndefined(Feature feature) {
        if (ObjingEAnnotation.isUndefined(this.ecoreFeatureElt))
            feature.setVisibility(VisibilityMode.VISIBILITYUNDEFINED);
        
    }

    @objid ("368e49bd-65a3-47fd-96db-197c52139baa")
    private void setAbstractEAnnotation(Feature objingElt) {
        objingElt.setIsAbstract(ObjingEAnnotation.isIsAbstract(getEcoreElement()));
    }

}
