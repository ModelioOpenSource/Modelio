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
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("27510f37-41c2-4814-87d2-3f8517009a1d")
public class OAcceptChangeEventAction extends OActivityNode {
    @objid ("148e0630-d807-4a19-a81f-9afa33f0f022")
    private AcceptChangeEventAction objingElement;

    @objid ("568134ab-0805-4464-a0c8-c83b85e2c9bf")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createAcceptEventAction();
    }

    @objid ("f57ff107-c263-4f70-a698-74e6d84495fd")
    public OAcceptChangeEventAction(AcceptChangeEventAction element) {
        super(element);
        this.objingElement = element;
    }

    @objid ("a6e0d360-0329-407f-95f1-b473dc9f1cfa")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("c3f62fd6-559b-4372-b7aa-97bd6404d23c")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setChangeExpression( (org.eclipse.uml2.uml.AcceptEventAction) ecoreElt);
        setChange( (org.eclipse.uml2.uml.AcceptEventAction) ecoreElt);
    }

    @objid ("b1536b4e-c1ec-4f3b-8a09-25153cf062de")
    private void setChangeExpression(org.eclipse.uml2.uml.AcceptEventAction action) {
        String changeExpression = this.objingElement.getChangeExpresion();
        
        if ((changeExpression != null) && (!changeExpression.equals(""))){
        
             org.eclipse.uml2.uml.Trigger trigger = UMLFactory.eINSTANCE.createTrigger();
            action.getTriggers().add(trigger);
        
             org.eclipse.uml2.uml.ChangeEvent changeEvent = UMLFactory.eINSTANCE.createChangeEvent();
            trigger.setEvent(changeEvent);
        
            org.eclipse.uml2.uml.LiteralString literal = UMLFactory.eINSTANCE.createLiteralString();
            changeEvent.setChangeExpression(literal);
            literal.setValue(changeExpression);
        
            // Attach the  org.eclipse.uml2.uml.Event to the model via composition relation:
            org.eclipse.uml2.uml.Package nearestPkg = action.getNearestPackage();
            nearestPkg.getPackagedElements().add(changeEvent);
        }
    }

    @objid ("506aa8a4-4ff7-4dad-afb6-832dbb39a1de")
    private void setChange(final org.eclipse.uml2.uml.AcceptEventAction ecoreElt) {
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            ObjingEAnnotation.setSignal(ecoreElt, "change");
        }
    }

}
