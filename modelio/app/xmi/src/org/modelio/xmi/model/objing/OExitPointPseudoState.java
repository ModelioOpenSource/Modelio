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
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;

@objid ("72c66371-aa77-4a4b-8062-5445faa4d4f8")
public class OExitPointPseudoState extends OAbstractPseudoState {
    @objid ("1d9ab8dc-71c2-4e32-9a82-a0d288ebde75")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createPseudostate();
    }

    @objid ("4026c571-8576-493b-b61f-b530d614d314")
    public OExitPointPseudoState(ExitPointPseudoState param) {
        super(param);
    }

    @objid ("01d54c01-b988-4f2f-8243-ca1a00a45c04")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingOwner = this.getObjingElement().getCompositionOwner();
        
        if (objingOwner != null) {
            org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
                
            if (ecoreOwner != null) {
                if (ecoreOwner instanceof  org.eclipse.uml2.uml.State) {
                    ( (org.eclipse.uml2.uml.State) ecoreOwner).getConnectionPoints().add((Pseudostate)ecoreElt);
                }else if (ecoreOwner instanceof  org.eclipse.uml2.uml.StateMachine) {
                        ( (org.eclipse.uml2.uml.StateMachine) ecoreOwner).getConnectionPoints().add((Pseudostate)ecoreElt);
                }else if ((ecoreOwner instanceof  org.eclipse.uml2.uml.Region) && (((org.eclipse.uml2.uml.Region) ecoreOwner).getStateMachine() != null)) {
                    ((org.eclipse.uml2.uml.Region) ecoreOwner).getStateMachine().getConnectionPoints().add((Pseudostate)ecoreElt);
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

    @objid ("da8e8e27-fab9-4b2b-8ef9-16e452eb4425")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setKind( (Pseudostate)ecoreElt);
    }

    @objid ("ae9d7c49-ecdc-4fb2-9d89-913715a7e4c6")
    private void setKind(Pseudostate pseudostate) {
        pseudostate.setKind (org.eclipse.uml2.uml.PseudostateKind.EXIT_POINT_LITERAL);
    }

}
