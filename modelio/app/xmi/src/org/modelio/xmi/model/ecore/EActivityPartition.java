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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("8c7aafa7-0e8e-4c8a-9a62-11efe471ed36")
public class EActivityPartition extends ENamedElement {
    @objid ("88f10cc8-071c-4793-85c9-789cf26c7dd0")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createActivityPartition();
    }

    @objid ("844d836c-2035-45a3-b275-ba7525d05e11")
    public EActivityPartition(org.eclipse.uml2.uml.ActivityPartition element) {
        super(element);
    }

    @objid ("8744ebd6-89b1-4a8d-afb6-2fdc8d4bf8ac")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
                
        org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        ActivityPartition objingPartition = (ActivityPartition) objingElt;
                
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof  org.eclipse.uml2.uml.ActivityPartition) {
                ActivityPartition objingOwner = (ActivityPartition) revProp
                        .getMappedElement(ecoreOwner);
                
                if (objingOwner != null)
                    objingPartition.setSuperPartition(objingOwner);
            } else if (ecoreOwner instanceof  org.eclipse.uml2.uml.Activity) {
                Activity objingOwner = (Activity) revProp
                        .getMappedElement(ecoreOwner);
                
                if (objingOwner != null)
                    objingPartition.setInActivity(objingOwner);
            } else {
                objingElt.delete();
            }
        }
    }

    @objid ("8f05f320-a599-42a4-8f30-ecde2caa2abe")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setDimension((ActivityPartition) objingElt);
        setExternal((ActivityPartition) objingElt);
        setRepresented((ActivityPartition) objingElt);
    }

    @objid ("f79d40e3-3d7b-4159-b471-d2b11d75465b")
    private void setDimension(ActivityPartition partition) {
        partition.setIsDimension(((org.eclipse.uml2.uml.ActivityPartition)getEcoreElement()).isDimension());
    }

    @objid ("a70cebc5-d942-4b6f-8d84-7536335de10d")
    private void setExternal(ActivityPartition partition) {
        partition.setIsExternal(((org.eclipse.uml2.uml.ActivityPartition)getEcoreElement()).isExternal());
    }

    @objid ("72966bca-b9b9-44d2-a642-8d9249487956")
    private void setRepresented(ActivityPartition partition) {
        org.eclipse.uml2.uml.Element ecoreRepresentation = ((org.eclipse.uml2.uml.ActivityPartition)getEcoreElement()).getRepresents();
        if ( ecoreRepresentation != null) {
            Element representation = (Element) ReverseProperties.getInstance().getMappedElement(ecoreRepresentation);
            if ((representation != null ) && (representation instanceof UmlModelElement)){
                partition.setRepresented((UmlModelElement) representation);
            }
        }
    }

}
