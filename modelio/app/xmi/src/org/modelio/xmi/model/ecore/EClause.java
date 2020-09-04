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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("0433955f-d458-4051-b3aa-36ad210aff2d")
public class EClause extends EElement {
    @objid ("de3d3df4-2ffb-4836-99bd-0687ec716aff")
    private org.eclipse.uml2.uml.Clause ecoreElement;

    @objid ("1baa9b18-99f4-48d4-94f8-a250367bee4c")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createClause();
    }

    @objid ("91c20ce3-1463-47b8-be92-ed9b046244fd")
    public EClause(org.eclipse.uml2.uml.Clause element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("6a1fc42b-21a1-4f60-b0c4-1b12ba003e9b")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
                
        if (ecoreOwner != null) {
            Object objingOwner =  ReverseProperties.getInstance().getMappedElement(ecoreOwner);
            if (objingOwner instanceof ConditionalNode)
                ((Clause) objingElt)
                        .setOwner((ConditionalNode) objingOwner);
        }
    }

    @objid ("d7afe647-7ccf-48e4-9bb7-92cd3876e6f0")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
               
        setBody((Clause) objingElt);
        setTest((Clause) objingElt);
        setName((Clause) objingElt);
    }

    @objid ("973ce1fc-7640-4643-b578-c806dc8e305b")
    private void setBody(Clause clause) {
        for (Object ecoreBody : this.ecoreElement.getBodies()) {
            Object objingBody =  ReverseProperties.getInstance()
                    .getMappedElement( (org.eclipse.uml2.uml.ExecutableNode) ecoreBody);
            if (objingBody instanceof ActivityNode)
                ((ActivityNode) objingBody).setOwnerClause(clause);
        }
    }

    @objid ("994781c5-b0ca-462e-881c-ed45dbf806cc")
    private void setTest(Clause clause) {
        String objingTest = "";
        for (Object ecoreTest : this.ecoreElement.getTests()) {
            if (ecoreTest instanceof  org.eclipse.uml2.uml.ValueSpecificationAction) {
                 org.eclipse.uml2.uml.ValueSpecification ecoreValue = ( (org.eclipse.uml2.uml.ValueSpecificationAction) ecoreTest)
                        .getValue();
                if (ecoreValue != null) {
                    String stringValue = ecoreValue.stringValue();
                    if (stringValue != null)
                        objingTest = objingTest.concat(stringValue);
                }
               GenerationProperties.getInstance().setRoundtripEnabled(true);
               ObjingEAnnotation.setIsDeleted((org.eclipse.uml2.uml.ValueSpecificationAction) ecoreTest);
            } else if (ecoreTest != null) {
                String ecoreName = ( (org.eclipse.uml2.uml.ExecutableNode) ecoreTest).getName();
                if (ecoreName != null)
                    objingTest = objingTest.concat(ecoreName);
            }
        }
        clause.setTest(objingTest);
    }

    @objid ("f70cb271-6812-4f2f-a694-28a96797e3e8")
    private void setName(Clause clause) {
        if ( ReverseProperties.getInstance().isRoundtripEnabled())
            clause.setName(ObjingEAnnotation.getName(this.ecoreElement));
    }

}
