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
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;

@objid ("2388b0d9-4df1-48fc-a15c-b82ecd8c97f8")
public class OStructuredActivityNode extends OActivityNode {
    @objid ("967f38c3-031f-4eba-b1a7-b1c60d07784f")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (((ModelElement)getObjingElement()).isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2SEQUENCENODE))
            return UMLFactory.eINSTANCE.createSequenceNode();
        else
            return UMLFactory.eINSTANCE.createStructuredActivityNode();
    }

    @objid ("e11526d0-f90f-40cf-b529-2d26f1212ad7")
    public OStructuredActivityNode(StructuredActivityNode element) {
        super(element);
    }

    @objid ("8837d912-4356-49e5-834a-6e85a4a80e98")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("48c31517-6765-45ef-b2de-2f6b9a702a2a")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setMustIsolate((org.eclipse.uml2.uml.StructuredActivityNode) ecoreElt);
    }

    @objid ("f6d1fb15-a574-4d7e-97c3-11f718df0859")
    private void setMustIsolate(org.eclipse.uml2.uml.StructuredActivityNode node) {
        node.setMustIsolate(getObjingElement().isMustIsolate());
    }

    @objid ("69148909-c984-440a-92f5-53378aa46f6e")
    @Override
    public StructuredActivityNode getObjingElement() {
        return (StructuredActivityNode) super.getObjingElement();
    }

}
