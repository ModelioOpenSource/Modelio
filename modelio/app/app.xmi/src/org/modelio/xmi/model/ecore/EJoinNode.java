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
package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("f6dd86a3-275b-4baa-86d3-4e8b3ec0594b")
public class EJoinNode extends EActivityNode {
    @objid ("48691b2c-68c7-48d8-83d7-383f8efa7d1b")
    private org.eclipse.uml2.uml.JoinNode ecoreElement;

    @objid ("4c4b7d95-df96-436f-acbc-39443e895e2d")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createForkJoinNode();
    }

    @objid ("0c805ee0-f75c-46d2-af17-80750ef4fd7b")
    public  EJoinNode(org.eclipse.uml2.uml.JoinNode element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("2d8511d6-5ae5-4758-ad9a-d86ac966e2bf")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        setJoinSpec((ForkJoinNode) objingElt);
        setCombineDuplicate((ForkJoinNode) objingElt);
        
    }

    @objid ("235160bb-c28a-41bb-87e3-8e574fbcaeb4")
    private void setJoinSpec(ForkJoinNode node) {
        org.eclipse.uml2.uml.ValueSpecification joinSpec = this.ecoreElement.getJoinSpec();
        if (joinSpec != null) {
            String value = joinSpec.stringValue();
            if (value != null)
                node.setJoinSpec(value);
        }
        
    }

    @objid ("336e46a3-3665-4846-94b7-26b317c97dc2")
    private void setCombineDuplicate(ForkJoinNode node) {
        node.setIsCombineDuplicate(this.ecoreElement.isCombineDuplicate());
    }

}
