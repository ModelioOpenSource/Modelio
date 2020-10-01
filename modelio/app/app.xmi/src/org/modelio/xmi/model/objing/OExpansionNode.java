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
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("c2058076-5bc7-427f-b65f-6e005d08851b")
public class OExpansionNode extends OObjectNode {
    @objid ("3f66224a-6c92-4185-afd1-9509f1a04d70")
    public OExpansionNode(ExpansionNode element) {
        super(element);
    }

    @objid ("352d0d6a-e792-4aea-acf7-d2feb3def151")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        ExpansionRegion objOwner =((ExpansionNode) getObjingElement()).getRegionAsInput();
        if (objOwner != null){
             org.eclipse.uml2.uml.ExpansionRegion ecoreOwner = (org.eclipse.uml2.uml.ExpansionRegion) GenerationProperties.getInstance().getMappedElement(objOwner);
             ((org.eclipse.uml2.uml.ExpansionNode) ecoreElt).setRegionAsInput(ecoreOwner);
             ((org.eclipse.uml2.uml.ExpansionNode) ecoreElt).setInStructuredNode(ecoreOwner);
        }else{
             org.eclipse.uml2.uml.ExpansionRegion ecoreOwner = (org.eclipse.uml2.uml.ExpansionRegion) GenerationProperties.getInstance().getMappedElement(
                     ((ExpansionNode) getObjingElement()).getRegionAsOutput());
             ((org.eclipse.uml2.uml.ExpansionNode) ecoreElt).setRegionAsOutput(ecoreOwner);
             ((org.eclipse.uml2.uml.ExpansionNode) ecoreElt).setInStructuredNode(ecoreOwner);
        }
    }

    @objid ("ca8ff8ca-123d-4052-81e8-3b0438d87aff")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

    @objid ("8f165a31-cca6-43c8-9e57-3f31d38342b7")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createExpansionNode();
    }

}
