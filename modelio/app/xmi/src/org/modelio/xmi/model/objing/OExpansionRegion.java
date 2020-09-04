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
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionKind;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;

@objid ("e8681f17-83f9-4cb4-9e18-a1a46ff9864b")
public class OExpansionRegion extends OStructuredActivityNode {
    @objid ("fa90df7b-544b-4c8a-8aea-95a9a0dc6ffd")
    public OExpansionRegion(ExpansionRegion element) {
        super(element);
    }

    @objid ("120384ca-8f00-4386-b303-bb3813949103")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("fbe08f0d-34bb-4c0a-8e1a-2950f79c16e1")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setMode((org.eclipse.uml2.uml.ExpansionRegion) ecoreElt);
    }

    @objid ("c1f20fd1-4572-4f32-b544-392d15a557e9")
    private void setMode(org.eclipse.uml2.uml.ExpansionRegion ecoreElt) {
        ExpansionKind  mode = ((ExpansionRegion) getObjingElement()).getMode();
        
        switch (mode) {
        case  STREAM:
            ecoreElt.setMode(org.eclipse.uml2.uml.ExpansionKind.STREAM_LITERAL);
            break;
            
        case PARALLEL:
            ecoreElt.setMode(org.eclipse.uml2.uml.ExpansionKind.PARALLEL_LITERAL);
            break;
            
        default:
            ecoreElt.setMode(org.eclipse.uml2.uml.ExpansionKind.ITERATIVE_LITERAL);
            break;
        
        }
    }

    @objid ("8f42d1c2-90d1-48bd-8514-b0a87bc7ca24")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createExpansionRegion();
    }

}
