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
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("268ee9bb-3fc4-4463-b384-3f3bf1468d8e")
public class EExpansionNode extends ENamedElement {
    @objid ("49eb4238-0f68-443d-b38f-2a99adc255f0")
    private boolean isInput = false;

    @objid ("8cf23599-d80e-4136-8229-4ff4f9c3290e")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createExpansionNode();
    }

    @objid ("c2f372da-09c0-4dcb-9dc0-6dac7e206020")
    public EExpansionNode(org.eclipse.uml2.uml.ExpansionNode element) {
        super(element);
        this.isInput = (element.getRegionAsInput()!= null);
    }

    @objid ("98545e29-76c2-41ef-b70a-e7f2bf152438")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.ExpansionNode ecoreElement = (org.eclipse.uml2.uml.ExpansionNode) getEcoreElement();
        org.eclipse.uml2.uml.Element region = ecoreElement.getOwner();
                
        Object objOwner =  ReverseProperties.getInstance().getMappedElement(region);
                
        if ((objOwner != null) && (objOwner instanceof ExpansionRegion)){
                
            if (this.isInput)
                ((ExpansionRegion)objOwner).getInputElement().add((ExpansionNode) objingElt);
            else
                ((ExpansionRegion)objOwner).getOutputElement().add((ExpansionNode) objingElt);
        }else{
            objingElt.delete();
        }
    }

}
