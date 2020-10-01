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
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("063db966-f3ac-47ed-808c-890108247b18")
public class ERegion extends ENamedElement {
    @objid ("8adacaa7-f0cd-4c5c-a778-7b767797e5ec")
    private org.eclipse.uml2.uml.Region ecoreElement = null;

    @objid ("b523d344-12b1-42c9-93d1-0af7cddc340c")
    @Override
    public Element createObjingElt() {
        Region region = null;
        
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
        if  (ecoreOwner instanceof org.eclipse.uml2.uml.StateMachine){
            region = ((StateMachine) ReverseProperties.getInstance().getMappedElement(ecoreOwner)).getTop();
        
        }else{
            region = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createRegion();
        }
        return region;
    }

    @objid ("9ba453e6-37ad-4d48-8e49-34c6eb79393f")
    public ERegion(org.eclipse.uml2.uml.Region element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("e303abed-276f-440a-8c46-078ed8f47ae6")
    @Override
    public void attach(Element objingElt) {
        Element objOwner = (Element) ReverseProperties.getInstance().getMappedElement(this.ecoreElement.getOwner());
        
        if ((objOwner != null ) && (objOwner instanceof State)){
            ((State) objOwner).getOwnedRegion().add((Region)objingElt);
            ((Region)objingElt).setParent((State) objOwner);
        }
        
        if ((objOwner != null ) && (objOwner instanceof StateMachine)){
            ((StateMachine) objOwner).setTop((Region)objingElt);
            ((Region)objingElt).setRepresented((StateMachine) objOwner);
        
        }
    }

}
