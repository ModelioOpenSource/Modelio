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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("bae7993d-a34a-469e-9797-32de9af1abb6")
public class OExecutionOccurenceSpecification extends OMessageEnd {
    @objid ("ce072347-7457-40ab-8b73-ff14b9705074")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        ExecutionOccurenceSpecification exeOcc = (ExecutionOccurenceSpecification) getObjingElement();
        
        if ((exeOcc.getSentMessage() != null)  
                || (exeOcc.getReceivedMessage() != null))
            return UMLFactory.eINSTANCE.createMessageOccurrenceSpecification();
        return UMLFactory.eINSTANCE.createExecutionOccurrenceSpecification();
    }

    @objid ("a44acb1b-b6f1-4e50-9826-1b717d9b3781")
    public OExecutionOccurenceSpecification(ExecutionOccurenceSpecification param) {
        super(param);
    }

    @objid ("85f2c41a-a827-4ff8-a3df-4ee438ad53b7")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("ac5456b7-b9cb-4bd8-81fd-4ab031e98fa7")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);       
        setStarted(ecoreElt);
        setFinished(ecoreElt);
    }

    @objid ("f0c2c2bd-05ca-4815-a48a-28cd5cef4765")
    public void setStarted(org.eclipse.uml2.uml.Element ecoreElt) {
        ExecutionSpecification exeSpec = ((ExecutionOccurenceSpecification) getObjingElement()).getStarted();
        if (exeSpec != null){
            org.eclipse.uml2.uml.Element ecoreExeSpec =   GenerationProperties.getInstance().getMappedElement(exeSpec);
        
            // We check if execution is set to ExecutionOccurrenceSpecification if
            // not we set it
        
            if (ecoreExeSpec instanceof org.eclipse.uml2.uml.ExecutionSpecification){
                ((org.eclipse.uml2.uml.ExecutionSpecification) ecoreExeSpec)
                .setStart((org.eclipse.uml2.uml.OccurrenceSpecification)ecoreElt);
            }  
        }
    }

    @objid ("43c11555-b4a0-4230-925b-5907a70fb378")
    public void setFinished(org.eclipse.uml2.uml.Element ecoreElt) {
        ExecutionSpecification exeSpec = ((ExecutionOccurenceSpecification) getObjingElement()).getFinished();
        if (exeSpec != null){
            org.eclipse.uml2.uml.Element ecoreExeSpec =  GenerationProperties.getInstance().getMappedElement(exeSpec);
        
            // We check if execution is set to ExecutionOccurrenceSpecification if
            // not we set it
        
            if (ecoreExeSpec instanceof org.eclipse.uml2.uml.ExecutionSpecification){
                ((org.eclipse.uml2.uml.ExecutionSpecification) ecoreExeSpec)
                .setFinish((org.eclipse.uml2.uml.OccurrenceSpecification)ecoreElt);
            }   
        }
    }

}
