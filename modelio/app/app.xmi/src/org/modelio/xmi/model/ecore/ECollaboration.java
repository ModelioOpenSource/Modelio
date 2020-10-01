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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("d0e19d3b-1f9b-40a9-a7bf-aaf20a585675")
public class ECollaboration extends ENamedElement {
    @objid ("3360dd2a-a073-49f0-a861-78cd1cd82364")
    private org.eclipse.uml2.uml.Collaboration ecoreElement = null;

    @objid ("a11ecca7-e2e2-413c-81a6-9dbf90486145")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createCollaboration();
    }

    @objid ("36d076fa-8276-4756-97a9-0c31ed67c57c")
    public ECollaboration(org.eclipse.uml2.uml.Collaboration element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("da2884d5-3ff5-4eae-bfc8-996f66bd8cf4")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
        ModelElement objingOwner = (ModelElement) revProp
        .getMappedElement(ecoreOwner);
        Collaboration objingCollab = (Collaboration) objingElt;
        
        if (objingOwner instanceof Behavior) {                
            objingCollab.setBRepresented((Behavior) objingOwner);
        } else if (objingOwner instanceof Profile) {
            objingCollab.setOwner(revProp.getExternalPackage());
        } else if (objingOwner instanceof ModelTree) {
            objingCollab.setOwner((ModelTree) objingOwner);
        } else {
            objingCollab.setOwner(revProp.getExternalPackage());
        }
    }

    @objid ("c08f92d5-170e-4c45-8c52-ae8359dcefab")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setAbstract((Collaboration) objingElt);
        setLeaf((Collaboration) objingElt);
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            setConcurrentEAnnotation((Collaboration) objingElt);
            setRootEAnnotation((Collaboration)objingElt);
        }
    }

    @objid ("61abc4fc-2539-47ba-99f8-2adc8a89fd00")
    private void setAbstract(Collaboration objingElt) {
        objingElt.setIsAbstract(this.ecoreElement.isAbstract());
    }

    @objid ("64b12e03-08e1-4afa-b3ee-36331724fc95")
    private void setLeaf(Collaboration objingElt) {
        objingElt.setIsLeaf(this.ecoreElement.isLeaf());
    }

    @objid ("36b9624c-47f1-48c6-a741-8604f6304e0e")
    private void setConcurrentEAnnotation(Collaboration objingElt) {
        objingElt.setIsConcurrent(ObjingEAnnotation.isConcurrent(this.ecoreElement));
    }

    @objid ("b9e670a7-9ae1-4937-92bd-848fa64bb751")
    private void setRootEAnnotation(Collaboration objingElt) {
        objingElt.setIsRoot(ObjingEAnnotation.isRoot(this.ecoreElement));
    }

}
