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
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;

@objid ("8e558fe5-c3f6-49ea-85c3-7426fa8601d2")
public class OValuePin extends OInputPin {
    @objid ("3c042370-1484-4b1b-9d45-7715f7f4c42b")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("ce3a70f2-c895-48ea-a59b-c2dfedfd58db")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createValuePin();
    }

    @objid ("cde3b1ed-64a8-4113-9121-1b8be58e5550")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        if (!(ecoreElt instanceof org.eclipse.uml2.uml.ExpansionNode)){
            setValue((org.eclipse.uml2.uml.InputPin) ecoreElt);
        }
    }

    @objid ("493a581b-5f4d-4431-8e00-19618ffe4220")
    public OValuePin(final ValuePin element) {
        super(element);
    }

    @objid ("a524049c-c334-41f4-933d-f0c4bd2785a0")
    private void setValue(final org.eclipse.uml2.uml.InputPin pin) {
        String objingValue = ((ValuePin) getObjingElement()).getValue();
        if (objingValue.length() > 0 && pin instanceof ValuePin) {
            org.eclipse.uml2.uml.LiteralString literal = UMLFactory.eINSTANCE.createLiteralString();
            literal.setName("Value");
            literal.setValue(objingValue);
            ((org.eclipse.uml2.uml.ValuePin) pin).setValue(literal);
        }
    }

}
