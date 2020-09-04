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
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;

@objid ("80a79102-55c2-4b91-9bca-2fae8d2cdbe1")
public class OActivityGroup extends OModelElement {
    @objid ("6808eaa4-86ce-4719-93e0-7e939d964fd1")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    @objid ("e77b4c20-3921-45af-bb75-81edbb084a6c")
    public OActivityGroup(final ActivityPartition element) {
        super(element);
    }

    @objid ("b9ec14df-7b6a-4bc2-a9d2-46054d1f0fb3")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingOwner = getObjingElement().getCompositionOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (objingOwner instanceof ActivityPartition)
                attachToActivityPartition(ecoreElt, ecoreOwner);
            else if (objingOwner instanceof Activity)
                attachToActivity(ecoreElt, ecoreOwner);
        }
    }

    @objid ("a0994055-6fef-47e8-b766-5e2a0d64027a")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

    @objid ("15fa1741-b94a-4be8-a45a-1cdb656ca8ba")
    private void attachToActivityPartition(final org.eclipse.uml2.uml.Element ecoreElt, final org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.ActivityPartition) {
             org.eclipse.uml2.uml.ActivityPartition owner = (org.eclipse.uml2.uml.ActivityPartition) ecoreOwner;
            owner.getSubpartitions().add((org.eclipse.uml2.uml.ActivityPartition)ecoreElt);
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOwner.getClass().getSimpleName() + ") Not Found");
        }
    }

    @objid ("c2bb4ce5-b27b-4962-9a64-8805b6460c2e")
    private void attachToActivity(final org.eclipse.uml2.uml.Element ecoreElt, final org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.Activity) {
             org.eclipse.uml2.uml.Activity owner = (org.eclipse.uml2.uml.Activity) ecoreOwner;
            owner.getGroups().add((org.eclipse.uml2.uml.ActivityGroup)ecoreElt);
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOwner.getClass().getSimpleName() + ") Not Found");
        }
    }

    @objid ("6b14e80e-07a9-4691-9d68-d56856199814")
    @Override
    public ActivityGroup getObjingElement() {
        return (ActivityGroup) super.getObjingElement();
    }

}
