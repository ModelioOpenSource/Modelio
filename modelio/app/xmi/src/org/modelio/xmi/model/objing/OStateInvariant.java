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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("ebf912bf-2e19-4f99-8511-6875b4372f03")
public class OStateInvariant extends OOccurrenceSpecification {
    @objid ("72363e5f-f4c2-46d0-993e-3f4ca18a52e9")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createStateInvariant();
    }

    @objid ("f2477bcd-4e06-40a7-9dab-e0b254edddef")
    public OStateInvariant(StateInvariant param) {
        super(param);
    }

    @objid ("1f4e23d6-1de8-4569-9618-4eefcfb79b29")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("f019484c-92cb-49d9-9564-6ce208edb46b")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setInvariant((org.eclipse.uml2.uml.StateInvariant) ecoreElt);
        setEndLineNumber(ecoreElt);
    }

    @objid ("8f49a960-4300-41e8-b0bf-19628a1d0d78")
    private void setInvariant(org.eclipse.uml2.uml.StateInvariant ecoreElt) {
        StateInvariant objingStateInvariant = (StateInvariant) getObjingElement();
        String stringConstraint = objingStateInvariant.getBody();
        if ((stringConstraint != null ) && (!stringConstraint.equals(""))){
        
            // org.eclipse.uml2.uml.Constraint creation
            org.eclipse.uml2.uml.Constraint constraint = UMLFactory.eINSTANCE.createConstraint();
            // new org.eclipse.uml2.uml.LiteralString for constraint value
            org.eclipse.uml2.uml.LiteralString valueSpecification = UMLFactory.eINSTANCE
                    .createLiteralString();
            valueSpecification.setValue(stringConstraint);
            constraint.setSpecification(valueSpecification);
        
            // Gets the org.eclipse.uml2.uml.Lifeline:
            List<Lifeline> lifelineList = objingStateInvariant.getCovered();
            Lifeline objingLL = null;
            if (lifelineList != null && lifelineList.size() > 0)
                objingLL = lifelineList.get(0);
        
            if (objingLL != null) {
                org.eclipse.uml2.uml.Lifeline ecoreLifeline = (org.eclipse.uml2.uml.Lifeline) GenerationProperties.getInstance()
                        .getMappedElement(objingLL);
        
                if (ecoreLifeline != null) {
                    try {
                        constraint.getConstrainedElements().add(ecoreLifeline);
                    } catch (ArrayStoreException e) {
                        Xmi.LOG.error(e);
                        constraint.getConstrainedElements().add(ecoreElt);
                    } catch (Exception e) {
                        Xmi.LOG.error(e);
                        constraint.getConstrainedElements().add(ecoreElt);
                    }
                }
            }
        
            // set the constraint
            ecoreElt.setInvariant(constraint);
        }
    }

    @objid ("6f9a43fb-9009-4b60-badb-1c58042366fc")
    private void setEndLineNumber(org.eclipse.uml2.uml.Element ecoreElt) {
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            ObjingEAnnotation.setEndLineNumber(ecoreElt, ((StateInvariant) getObjingElement()).getEndLineNumber());
        }
    }

}
