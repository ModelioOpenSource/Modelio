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
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;

@objid ("9a4d8d73-b40a-4715-a0cb-d43b1c3fae0e")
public class OConditionalNode extends OElement implements IOElement {
    @objid ("cc45c765-26f3-4423-9091-972e057bed84")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createConditionalNode();
    }

    @objid ("7d6848ec-cbe7-4724-a0b3-6f44651ebd07")
    public OConditionalNode(ConditionalNode element) {
        super(element);
    }

    @objid ("72740f53-d783-4446-9de8-71bf3f0ac0a8")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // Done when mapping the super type (org.eclipse.uml2.uml.StructuredActivityNode)
    }

    @objid ("f3253b66-4c23-4a3e-8c40-3dd4654c97bb")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        setIsAssured((org.eclipse.uml2.uml.ConditionalNode) ecoreElt);
        setDeterminate((org.eclipse.uml2.uml.ConditionalNode) ecoreElt);
    }

    @objid ("4fb63d44-4343-4cae-ad04-a1fe2968a1f5")
    private void setIsAssured(org.eclipse.uml2.uml.ConditionalNode node) {
        node.setIsAssured(getObjingElement().isIsAssured());
    }

    @objid ("49016c01-29b2-42a1-b8f3-d9eb0610aa19")
    private void setDeterminate(org.eclipse.uml2.uml.ConditionalNode node) {
        node.setIsDeterminate(getObjingElement().isIsDeterminate());
    }

    @objid ("970b022b-efe9-4fe0-b5cc-b0a3f3a0c2ac")
    @Override
    public ConditionalNode getObjingElement() {
        return (ConditionalNode) super.getObjingElement();
    }

}
