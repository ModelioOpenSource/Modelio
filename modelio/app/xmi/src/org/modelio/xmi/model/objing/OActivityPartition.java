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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("2dbf7f92-6fed-44af-b222-6b183ced1c4e")
public class OActivityPartition extends OActivityGroup {
    @objid ("2f795595-d6af-4d95-9ac5-cedffb11ca92")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createActivityPartition();
    }

    @objid ("64488dba-8e85-4869-8021-cbce84d6aa12")
    public OActivityPartition(ActivityPartition element) {
        super(element);
    }

    @objid ("376ee3f1-54b6-40c5-975c-52d82a4f568d")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setDimension((org.eclipse.uml2.uml.ActivityPartition) ecoreElt);
        setExternal((org.eclipse.uml2.uml.ActivityPartition) ecoreElt);
        setRepresented((org.eclipse.uml2.uml.ActivityPartition) ecoreElt);
    }

    @objid ("2ab7b618-e59d-4fab-bddc-205375f360fe")
    private void setDimension(org.eclipse.uml2.uml.ActivityPartition partition) {
        partition.setIsDimension(getObjingElement().isIsDimension());
    }

    @objid ("b74999c6-1907-4afd-9160-66b36fd1bf8a")
    private void setExternal(org.eclipse.uml2.uml.ActivityPartition partition) {
        partition.setIsExternal(getObjingElement().isIsExternal());
    }

    @objid ("2b195bcd-78a5-4275-9eb5-0a5cb7ddbe73")
    private void setRepresented(org.eclipse.uml2.uml.ActivityPartition partition) {
        Element representation = getObjingElement().getRepresented();
        if (representation != null) {
            org.eclipse.uml2.uml.Element ecoreRepresentation = GenerationProperties.getInstance().getMappedElement(representation);
            if (ecoreRepresentation != null)
                partition.setRepresents(ecoreRepresentation);
        }
    }

    @objid ("3434f68d-0e6b-4857-af8a-b584b1ea440c")
    @Override
    public ActivityPartition getObjingElement() {
        return (ActivityPartition) super.getObjingElement();
    }

}
