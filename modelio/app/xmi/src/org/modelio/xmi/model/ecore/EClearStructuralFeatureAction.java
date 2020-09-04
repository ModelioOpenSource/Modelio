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
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2StructuralFeatureReference;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ClearStructuralFeatureAction;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("9cba02d9-9650-4947-b0b6-a81f3d8ce77a")
public class EClearStructuralFeatureAction extends EActivityNode {
    @objid ("a52bf8d8-bae6-428f-a548-ad17881a1cf3")
    private org.eclipse.uml2.uml.ClearStructuralFeatureAction ecoreElement;

    @objid ("9ba410fa-10d5-4415-ad63-5a8f0516fbea")
    @Override
    public Element createObjingElt() {
        return UML2ClearStructuralFeatureAction.create().getElement();
    }

    @objid ("db7ce6cc-7550-458f-9241-7d27c8acb875")
    public EClearStructuralFeatureAction(org.eclipse.uml2.uml.ClearStructuralFeatureAction element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("b4a16c13-4014-4360-b3a2-a57de2da1246")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setFeature((OpaqueAction) objingElt);
    }

    @objid ("c3d18153-4016-4f8e-b618-b49e41c4d201")
    private void setFeature(OpaqueAction objingElt) {
        org.eclipse.uml2.uml.StructuralFeature feature = this.ecoreElement.getStructuralFeature();
        
        if (feature != null) {
        
            Object obBehavior = ReverseProperties.getInstance().getMappedElement(feature);
        
            if ((obBehavior != null) && (obBehavior instanceof ModelElement)) {
                Dependency dependency =  UML2StructuralFeatureReference.create().getElement();        
                dependency.setDependsOn((ModelElement) obBehavior);
                dependency.setImpacted(objingElt);
            }
        }
    }

}
