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
import org.eclipse.uml2.uml.Vertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;

@objid ("627cf5ca-7898-45ae-91ed-72f5bbd3eb18")
public class OAbstractPseudoState extends OModelElement {
    @objid ("40993cb5-08f5-4f9c-b7d0-db030ee5f323")
    public OAbstractPseudoState(AbstractPseudoState param) {
        super(param);
    }

    @objid ("808dac62-06be-4376-89ec-e0e9b94496a2")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingOwner = this.getObjingElement().getCompositionOwner();
                
        if (objingOwner != null) {
            org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
                
            if (ecoreOwner != null) {
                if (ecoreOwner instanceof  org.eclipse.uml2.uml.Region) {
                    ( (org.eclipse.uml2.uml.Region) ecoreOwner).getSubvertices().add((Vertex)ecoreElt);
                } else {
                    ecoreElt.destroy();
                    throw new NotFoundException("Owner Class ("
                            + ecoreOwner.getClass().getSimpleName()
                            + ") Not Found");
                }
            } else {
                ecoreElt.destroy();
                throw new NotFoundException(
                        "Owner Class of "+ this.getObjingElement().getClass().getSimpleName() + " Not Found");
            }
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class of "+ this.getObjingElement().getClass().getSimpleName() + " Not Found");
        }
    }

    @objid ("11f500d4-97b0-4edb-a2a0-b1e8abe9b069")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}
