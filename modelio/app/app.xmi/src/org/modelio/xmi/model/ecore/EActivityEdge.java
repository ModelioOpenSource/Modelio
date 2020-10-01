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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("b10d3d1c-c140-474b-bede-35fac0ab9fe3")
public class EActivityEdge extends ENamedElement {
    @objid ("464784e9-6969-468e-89a8-6696df37c0db")
    public EActivityEdge(org.eclipse.uml2.uml.ActivityEdge element) {
        super(element);
    }

    @objid ("2146c8ab-a0bd-442d-837b-8e4baa17a9d1")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setGuard((ActivityEdge) objingElt);
        setWeight((ActivityEdge) objingElt);
        setSource((ActivityEdge) objingElt);
        setTarget((ActivityEdge) objingElt);
        setInterrupts((ActivityEdge) objingElt);
    }

    @objid ("aeb03c4e-14d3-468e-b633-cb7293be08c7")
    private void setGuard(ActivityEdge flow) {
        org.eclipse.uml2.uml.ValueSpecification ecoreGuard = ((org.eclipse.uml2.uml.ActivityEdge)getEcoreElement()).getGuard();
        
        if (ecoreGuard != null) {
            String stringValue = ecoreGuard.stringValue();
            if (stringValue != null)
                flow.setGuard(stringValue);
            else
                flow.setGuard("");
        
        }else
            flow.setGuard("");
    }

    @objid ("a72f0e58-5014-4e90-bd85-98679f5a148a")
    private void setWeight(ActivityEdge flow) {
        boolean isSet = false;
        org.eclipse.uml2.uml.ValueSpecification ecoreWeight = ((org.eclipse.uml2.uml.ActivityEdge)getEcoreElement()).getWeight();
        if (ecoreWeight != null) {
            String stringValue = ecoreWeight.stringValue();
            if (stringValue != null) {
                flow.setWeight(stringValue);
                isSet = true;
            }
        }
        
        if (!isSet)
            flow.setWeight("1");
    }

    @objid ("0c75dd05-7cfd-4316-b23d-87cbd460319e")
    private void setTarget(ActivityEdge flow) {
        org.eclipse.uml2.uml.ActivityNode ecoreTarget = ((org.eclipse.uml2.uml.ActivityEdge)getEcoreElement()).getTarget();
        if (ecoreTarget != null) {
            Object objingTarget = ReverseProperties.getInstance().getMappedElement(ecoreTarget);
            if (objingTarget instanceof ActivityNode)
                flow.setTarget((ActivityNode) objingTarget);
        }
    }

    @objid ("74cc6e42-748e-4dca-8980-73361b3316b0")
    private void setSource(ActivityEdge flow) {
        org.eclipse.uml2.uml.ActivityNode ecoreSource = ((org.eclipse.uml2.uml.ActivityEdge)getEcoreElement()).getSource();
        org.eclipse.uml2.uml.ActivityNode ecoreTarget = ((org.eclipse.uml2.uml.ActivityEdge)getEcoreElement()).getTarget();
        if ((ecoreSource != null) && (ecoreTarget != null)) {
            Object objingSource = ReverseProperties.getInstance().getMappedElement(ecoreSource);
            if ((objingSource != null) && (objingSource instanceof ActivityNode)){
                 flow.setSource((ActivityNode) objingSource);
            }else{
                flow.delete();
            }
        }else{
            flow.delete();
        }
    }

    @objid ("7f09a941-96fd-46b8-a233-8930ef1b6ba9")
    private void setInterrupts(ActivityEdge flow) {
        org.eclipse.uml2.uml.InterruptibleActivityRegion ecoreRegion = ((org.eclipse.uml2.uml.ActivityEdge)getEcoreElement()).getInterrupts();
        if (ecoreRegion != null) {
            Object objingRegion = ReverseProperties.getInstance().getMappedElement(ecoreRegion);
            if (objingRegion instanceof InterruptibleActivityRegion)
                flow.setInterrupts((InterruptibleActivityRegion) objingRegion);
        }
    }

}
