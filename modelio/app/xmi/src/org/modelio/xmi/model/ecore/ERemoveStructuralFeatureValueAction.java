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

@objid ("51bd1ce9-b631-4fa1-b692-72b52341b0c5")
public class ERemoveStructuralFeatureValueAction extends EActivityNode {
    @objid ("e8b318d6-236a-449e-b144-d9a09150afc2")
    private org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction ecoreElement = null;

    @objid ("3058ed6d-325a-4116-9b8c-ba07d6c36abc")
    @Override
    public Element createObjingElt() {
        IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
        
        OpaqueAction element = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueAction();
        
        try {
            element.addStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2REMOVESTRUCTURALFEATUREACTION);
        } catch (ExtensionNotFoundException e) {
            Xmi.LOG.warning(e);
        }
        return element;
    }

    @objid ("5800b9d0-70ee-403e-bf06-4384972c8b0b")
    public ERemoveStructuralFeatureValueAction(org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("55d23198-1b78-4959-a863-b612ff3c0099")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setFeature((OpaqueAction) objingElt);
    }

    @objid ("5a6428e6-073a-4ce2-a255-a165bc303339")
    private void setFeature(OpaqueAction objingElt) {
        org.eclipse.uml2.uml.StructuralFeature feature = this.ecoreElement.getStructuralFeature();
        IMModelServices mmServices  = ReverseProperties.getInstance().getMModelServices();
        Dependency dependency = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createDependency();
        
        try {
            dependency.addStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2STRUCTURALFEATUREREFERENCE);
        } catch (ExtensionNotFoundException e) {
            Xmi.LOG.warning(e);
        }
        
        ModelElement obBehavior = (ModelElement)ReverseProperties.getInstance().getMappedElement(feature);
        dependency.setDependsOn(obBehavior);
        dependency.setImpacted(objingElt);
    }

}
