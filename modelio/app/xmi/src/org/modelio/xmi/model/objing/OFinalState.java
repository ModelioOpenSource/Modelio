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
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("2268a24e-5ed0-4c07-907e-21c9cb0114a4")
public class OFinalState extends OModelElement {
    @objid ("d499623d-e91a-4c81-8b82-38f317570277")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createFinalState();
    }

    @objid ("fe515d20-7b06-4f2b-8ebb-0e42c402dedd")
    public OFinalState(State param) {
        super(param);
    }

    @objid ("9a610d92-418c-40ae-844b-c0592744b0f6")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(
                getObjingElement().getCompositionOwner());
                    
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.Region){
            ( (org.eclipse.uml2.uml.Region) ecoreOwner).getSubvertices().add((org.eclipse.uml2.uml.State) ecoreElt);
        }
    }

    @objid ("23573c65-d6a6-4fa0-81ec-fb1fccf64be3")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}
