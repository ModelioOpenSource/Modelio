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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("e478e605-d488-4760-98bb-fff5c2cc114f")
public class OGate extends OMessageEnd {
    @objid ("a29536b9-d88f-4370-8c52-34814d6fee89")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createGate();
    }

    @objid ("92922b49-d388-4fe8-9178-8b638d0ae4ac")
    public OGate(Gate param) {
        super(param);
    }

    @objid ("ffd8edfa-c1c4-49c6-91c6-df4b0eb4a726")
    @Override
    public List<String> getEcoreClassName() {
        return new ArrayList<>();
    }

    @objid ("a42ef361-5dad-4aa6-b67c-b6b23f7c1859")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        Object ecoreOwner = GenerationProperties.getInstance().getMappedElement(((Gate)this.getObjingElement()).getCompositionOwner());
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.Interaction){    
            ((org.eclipse.uml2.uml.Interaction)ecoreOwner).getFormalGates().add((org.eclipse.uml2.uml.Gate)ecoreElt);
        
        }else if (ecoreOwner instanceof org.eclipse.uml2.uml.CombinedFragment){
            ( (org.eclipse.uml2.uml.CombinedFragment)ecoreOwner).getCfragmentGates().add((org.eclipse.uml2.uml.Gate)ecoreElt);
        
        }else if (ecoreOwner instanceof org.eclipse.uml2.uml.InteractionUse){
            ((org.eclipse.uml2.uml.InteractionUse)ecoreOwner).getActualGates().add((org.eclipse.uml2.uml.Gate)ecoreElt);
        }else{
            ecoreElt.destroy();
        }
    }

    @objid ("c5af3c53-df49-48b2-96fd-2d0dbb8146f2")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}
