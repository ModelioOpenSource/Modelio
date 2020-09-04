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
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("5c308ba5-6dba-4ef1-b326-5717bd1f1b46")
public class OAcceptTimeEventAction extends OActivityNode {
    @objid ("77abbb27-a09e-425c-8b8f-34274ec380c2")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createAcceptEventAction();
    }

    @objid ("d63a187f-574d-4b5b-98ed-f17687d701c9")
    public OAcceptTimeEventAction(AcceptTimeEventAction element) {
        super(element);
    }

    @objid ("07e7053b-3185-4cdb-bb55-4a1ab2337825")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("5bae2b69-3827-4e5e-bd86-a4d02715ca1a")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setTimeExpression( (org.eclipse.uml2.uml.AcceptEventAction) ecoreElt);
        setTime((org.eclipse.uml2.uml.AcceptEventAction) ecoreElt);
    }

    @objid ("3de38f11-5819-4bcf-9f7e-b5594c0c61f4")
    private void setTimeExpression(org.eclipse.uml2.uml.AcceptEventAction action) {
        String timeExpression = getObjingElement().getTimeExpresion();
        
        if ((timeExpression != null) && (!timeExpression.equals(""))){
            org.eclipse.uml2.uml.Trigger trigger = UMLFactory.eINSTANCE.createTrigger();
            action.getTriggers().add(trigger);
        
            org.eclipse.uml2.uml.TimeEvent timeEvent = UMLFactory.eINSTANCE.createTimeEvent();
            trigger.setEvent(timeEvent);
        
        
            org.eclipse.uml2.uml.TimeExpression time = UMLFactory.eINSTANCE.createTimeExpression();
            org.eclipse.uml2.uml.LiteralString literal = UMLFactory.eINSTANCE.createLiteralString();
            literal.setValue(timeExpression);
            time.setExpr(literal);
            timeEvent.setWhen(time);
        
            // Attach the  org.eclipse.uml2.uml.Event to the model via composition relation:
            org.eclipse.uml2.uml.Package nearestPkg = action.getNearestPackage();
            nearestPkg.getPackagedElements().add(timeEvent);
        }
    }

    @objid ("ccde4bab-cbe2-42bd-a426-9c0b8aca91dd")
    private void setTime(final org.eclipse.uml2.uml.AcceptEventAction ecoreElt) {
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            ObjingEAnnotation.setSignal(ecoreElt, "time");
        }
    }

    @objid ("36211ee2-a664-46f6-9afb-3bb208351ab0")
    @Override
    public AcceptTimeEventAction getObjingElement() {
        return (AcceptTimeEventAction) super.getObjingElement();
    }

}
