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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("fc8339aa-8d4d-4e85-84a9-e68401e7d6ba")
public class EValuePin extends EInputPin {
    @objid ("0bb4bfb5-da6f-4419-acae-88571755a071")
    @Override
    public Element createObjingElt() {
        return  ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createValuePin();
    }

    @objid ("3021b0f3-8d15-464e-a455-a86453a14cc8")
    public EValuePin(org.eclipse.uml2.uml.ValuePin element) {
        super(element);
    }

    @objid ("8bc7574c-130f-4edc-aff7-4c849e494e2e")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setValue((ValuePin) objingElt);
    }

    @objid ("c1a7b94d-a143-4874-89c6-8926c806a35f")
    private void setValue(ValuePin objingElt) {
        org.eclipse.uml2.uml.ValueSpecification value = ((org.eclipse.uml2.uml.ValuePin) getEcoreElement()).getValue();
        
        if (value != null){
            objingElt.setValue(EcoreModelNavigation.getValue(value));
        }
    }

}
