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
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("b3b180a8-f097-490e-a25f-ea48843fe394")
public class OPin extends OObjectNode {
    @objid ("498b2710-e366-480f-abd3-721a19bab471")
    public OPin(final Pin element) {
        super(element);
    }

    @objid ("9be6f116-0111-4ec6-b220-c31f599d860e")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("cc58e44f-0f70-4d15-aacf-9303cbd46d80")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPin();
    }

    @objid ("9df0b780-23a9-4991-9383-db1c584019a5")
    private void setExpansionEAnnotation(final org.eclipse.uml2.uml.Pin pin) {
        ObjingEAnnotation.setIsExpansion(pin, ((Pin)getObjingElement()).isIsExpansion());
    }

    @objid ("d61ac0c4-f163-4dda-998e-32b00a467bff")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        if (ecoreElt instanceof org.eclipse.uml2.uml.Pin){
            setControl((org.eclipse.uml2.uml.Pin) ecoreElt);
            if (GenerationProperties.getInstance().isRoundtripEnabled())
                setExpansionEAnnotation((org.eclipse.uml2.uml.Pin) ecoreElt);
        }
    }

    @objid ("348c1032-96e7-49e3-80ce-af0f355828df")
    private void setControl(final org.eclipse.uml2.uml.Pin pin) {
        pin.setIsControl(((Pin)getObjingElement()).isIsControl());
    }

}
