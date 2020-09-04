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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.XMIProperties;

@objid ("55b46864-c754-4c74-91c0-b825b7abe37f")
public class EClearAssociationAction extends EActivityNode {
    @objid ("74b2b045-a9b7-46b1-a01b-1b64fbfe123a")
    private org.eclipse.uml2.uml.ClearAssociationAction ecoreElement;

    @objid ("0242d01d-02bb-43f5-a0c3-8f2379e7b3d7")
    @Override
    public Element createObjingElt() {
        IMModelServices mmService = ReverseProperties.getInstance().getMModelServices();
        
        OpaqueAction element = mmService.getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueAction();
        
        try {
            element.addStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CLEARASSOCIATIONACTION);
        } catch (ExtensionNotFoundException e) {
            Xmi.LOG.warning(e);
        }
        return element;
    }

    @objid ("e45e3016-7357-4257-80da-3a297a53d2e0")
    public EClearAssociationAction(org.eclipse.uml2.uml.ClearAssociationAction element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("4cd50ff6-49b9-4d09-b72e-2221bb8d88a4")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setAssociation((OpaqueAction) objingElt);
    }

    @objid ("27c13eef-627c-4fdc-82a0-c0bf6b555cd0")
    private void setAssociation(OpaqueAction objingElt) {
        org.eclipse.uml2.uml.Association association = this.ecoreElement.getAssociation();
        if (association != null){
            ModelElement obBehavior = (ModelElement)ReverseProperties.getInstance().getMappedElement(association);
        
            IMModelServices mmServices  = ReverseProperties.getInstance().getMModelServices();
            Dependency dependency = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createDependency();
        
            try {
                dependency.addStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2ASSOCIATIONREFERENCE);
            } catch (ExtensionNotFoundException e) {
                Xmi.LOG.warning(e);
            }
        
            dependency.setDependsOn(obBehavior);
            dependency.setImpacted(objingElt);
        }
    }

}
