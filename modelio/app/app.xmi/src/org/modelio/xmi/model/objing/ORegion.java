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
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("b486c4d4-43e3-4991-bfba-ed1a865d4d27")
public class ORegion extends OModelElement {
    @objid ("47610a03-e3db-484c-8de9-b3fdb8b64422")
    private GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("5a17defb-1db6-48a6-abc6-3c867d52f8a6")
    private Region objingElement;

    @objid ("c4608c13-a0ed-465a-ab32-8ad6e84b4318")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return  UMLFactory.eINSTANCE.createRegion();
    }

    @objid ("1539874b-01d0-468c-84f6-dae1e6f9f4e6")
    public ORegion(Region element) {
        super(element);       
        this.objingElement = element;
    }

    @objid ("d607c2a4-f614-4d1a-9468-f93f060df8ef")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        Element objingOwner = this.objingElement.getParent();
        
        if (objingOwner == null)
            objingOwner = this.objingElement.getRepresented();
                
        org.eclipse.uml2.uml.Element ecoreOwner = this.genProp.getMappedElement(objingOwner);
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.State) {
            ((org.eclipse.uml2.uml.State) ecoreOwner).getRegions().add( (org.eclipse.uml2.uml.Region) ecoreElt);
            
        }else if (ecoreOwner instanceof org.eclipse.uml2.uml.StateMachine){
            ((org.eclipse.uml2.uml.StateMachine) ecoreOwner).getRegions().add( (org.eclipse.uml2.uml.Region) ecoreElt);
        }
    }

    @objid ("ab45ebbb-1120-4f05-a10d-70d97ffe3a08")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}
