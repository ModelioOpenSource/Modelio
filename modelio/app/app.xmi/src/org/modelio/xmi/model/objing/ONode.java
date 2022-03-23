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
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;

@objid ("9b31f892-cc73-4864-b253-575d5bd7820f")
public class ONode extends ONameSpace {
    @objid ("a37ba665-e0de-4913-b948-96a338291a73")
    Node objingElement;

    @objid ("1999f5cd-c118-4906-b2c7-d27d741f908d")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DEVICE))
            return UMLFactory.eINSTANCE.createDevice();
        else if (this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2EXECUTIONENVIRONMENT))
            return UMLFactory.eINSTANCE.createExecutionEnvironment();
        else
            return UMLFactory.eINSTANCE.createNode();
        
    }

    @objid ("aa73f205-92b7-44c1-8b78-138d5107feeb")
    public  ONode(Node param) {
        super(param);
        this.objingElement = param;
        
    }

    @objid ("fc9f98bf-cce2-4261-a841-04c940c93735")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        ModelTree objingOwner = this.objingElement.getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = genProp.getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
                org.eclipse.uml2.uml.Package ownerIsPkg = (org.eclipse.uml2.uml.Package) ecoreOwner;
                ownerIsPkg.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component) {
                org.eclipse.uml2.uml.Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
                ownerIsCmpnt.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Node) {
                org.eclipse.uml2.uml.Node ownerIsNode = (org.eclipse.uml2.uml.Node) ecoreOwner;
                ownerIsNode.getNestedNodes().add((org.eclipse.uml2.uml.Node)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateParameter) {
                org.eclipse.uml2.uml.TemplateParameter ownerIsTemplateParameter = (org.eclipse.uml2.uml.TemplateParameter) ecoreOwner;
                ( (org.eclipse.uml2.uml.Classifier)ecoreElt).setOwningTemplateParameter(ownerIsTemplateParameter);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
        
    }

    @objid ("1dd5831d-f140-4418-8bb9-ded9d7580118")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}
