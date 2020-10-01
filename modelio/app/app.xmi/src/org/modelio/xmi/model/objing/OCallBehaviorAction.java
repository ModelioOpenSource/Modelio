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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;

@objid ("8de53f10-2917-46ee-844c-a350b1930c46")
public class OCallBehaviorAction extends OActivityNode {
    @objid ("c1579c5a-09e4-4e07-a796-8cf37d58b7a9")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createCallBehaviorAction();
    }

    @objid ("8ec8511d-63a3-4573-b2d6-3faa0c6ccf95")
    public OCallBehaviorAction(CallBehaviorAction element) {
        super(element);
    }

    @objid ("aed34e13-7ff0-4bec-87f8-ed0696ed65e9")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setSynchronous( (org.eclipse.uml2.uml.CallBehaviorAction) ecoreElt);
        setBehavior( (org.eclipse.uml2.uml.CallBehaviorAction) ecoreElt);
    }

    @objid ("a066fe59-8247-4941-b763-41ac6f31036e")
    private void setSynchronous(org.eclipse.uml2.uml.CallBehaviorAction action) {
        action.setIsSynchronous(getObjingElement().isIsSynchronous());
    }

    @objid ("c5d10da8-1749-4e6d-9c19-2cf7cfe8808b")
    private void setBehavior(org.eclipse.uml2.uml.CallBehaviorAction action) {
        Behavior objingBehavior = getObjingElement().getCalled();
        
        if (objingBehavior != null) {
            org.eclipse.uml2.uml.Element ecoreBehavior =  GenerationProperties.getInstance().getMappedElement(objingBehavior);
        
            if (ecoreBehavior != null) {
                if (ecoreBehavior instanceof org.eclipse.uml2.uml. Behavior)
                    action.setBehavior((org.eclipse.uml2.uml.Behavior) ecoreBehavior);
                else{
                    action.destroy();
                    throw new NotFoundException("The org.eclipse.uml2.uml. Behavior \""
                            + objingBehavior.getName()
                            + "\" has not been mapped correctly.");
                }
            }
        }
    }

    @objid ("92ced73e-14a0-41b0-8959-d1c01fd0bd86")
    @Override
    public CallBehaviorAction getObjingElement() {
        return (CallBehaviorAction) super.getObjingElement();
    }

}
