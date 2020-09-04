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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("0f17fcda-e812-4fc3-a6a0-6ce279fc50cc")
public class ECallBehaviorAction extends EActivityNode {
    @objid ("0232e931-78ef-4568-ad8a-067318b63a23")
    private org.eclipse.uml2.uml.CallBehaviorAction ecoreElement;

    @objid ("77a3b356-d7a2-4e11-90f3-c23a15d9f9e0")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createCallBehaviorAction();
    }

    @objid ("a3c6ee8f-b017-4885-9495-7e6d2194bf1a")
    public ECallBehaviorAction(org.eclipse.uml2.uml.CallBehaviorAction element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("2f88ab35-cced-43d7-8c4e-c4f87284bac3")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setSynchronous((CallBehaviorAction) objingElt);
        setBehavior((CallBehaviorAction) objingElt);
    }

    @objid ("bce1e5ba-260f-42ca-a5c2-87531d69173b")
    private void setSynchronous(CallBehaviorAction action) {
        action.setIsSynchronous(this.ecoreElement.isSynchronous());
    }

    @objid ("9b965d2e-607a-411b-99fe-08f7ddb0a8ab")
    private void setBehavior(CallBehaviorAction action) {
        org.eclipse.uml2.uml. Behavior ecoreBehavior = this.ecoreElement.getBehavior();
                
        if (ecoreBehavior != null) {
            Object objingBehavior =  ReverseProperties.getInstance().getMappedElement(ecoreBehavior);
            if (objingBehavior instanceof Behavior) {
                action.setCalled((Behavior) objingBehavior);
            }
        }
    }

}
