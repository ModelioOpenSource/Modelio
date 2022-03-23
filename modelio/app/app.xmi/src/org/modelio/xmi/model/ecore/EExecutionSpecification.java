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
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("3805ded7-4e3f-402e-addd-a749719130f1")
public class EExecutionSpecification extends EInteractionFragment {
    @objid ("2ea7f94c-e04f-444a-b01c-f4d5047e1f12")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createExecutionSpecification();
    }

    @objid ("aa3b68be-b0d8-4dbe-b393-c87f07772c5e")
    public  EExecutionSpecification(org.eclipse.uml2.uml.ExecutionSpecification element) {
        super(element);
    }

    @objid ("f137c719-30bb-4581-aaae-d732a2f443c1")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        if (objingElt instanceof ExecutionSpecification){
            setStart((ExecutionSpecification) objingElt);
            setFinish((ExecutionSpecification) objingElt);
        }
        
    }

    @objid ("d0316e60-a70b-409c-a335-279272fe163d")
    private void setStart(ExecutionSpecification objingElt) {
        org.eclipse.uml2.uml.ExecutionSpecification ecoreES = (org.eclipse.uml2.uml.ExecutionSpecification) this.getEcoreElement();
        
        org.eclipse.uml2.uml.OccurrenceSpecification start = ecoreES.getStart();
        
        if (start != null){
            Object objStart = ReverseProperties.getInstance().getMappedElement(start);
        
            if (objStart instanceof ExecutionOccurenceSpecification)
                objingElt.setStart((ExecutionOccurenceSpecification) objStart);
        
        }
        
    }

    @objid ("59399bf7-0235-46d6-ae07-0cea8828411e")
    private void setFinish(ExecutionSpecification objingElt) {
        org.eclipse.uml2.uml.ExecutionSpecification ecoreES = (org.eclipse.uml2.uml.ExecutionSpecification) this.getEcoreElement();
        org.eclipse.uml2.uml.OccurrenceSpecification finish = ecoreES.getFinish();
        
        if (finish != null){
            Object objFinish = ReverseProperties.getInstance().getMappedElement(finish);
        
            if (objFinish instanceof ExecutionOccurenceSpecification)
                objingElt.setFinish((ExecutionOccurenceSpecification) objFinish);
        }
        
    }

}
