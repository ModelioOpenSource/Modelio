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

@objid ("d28ca172-410a-4e8e-bd62-85100e1fa4bb")
public class EAddStructuralFeatureValueAction extends EActivityNode {
    @objid ("bc5e6943-99e8-4539-b1f4-f6c7ad093289")
    @Override
    public Element createObjingElt() {
        IMModelServices mmService = ReverseProperties.getInstance().getMModelServices();
        OpaqueAction element = mmService.getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueAction();
        
        try {
            element.addStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2ADDSTRUCTURALFEATUREVALUEACTION);
        } catch (ExtensionNotFoundException e) {
           Xmi.LOG.warning(e);
        }
        return element;
    }

    @objid ("5d523051-d4b5-4ec8-bba2-888cc8d68a6f")
    public EAddStructuralFeatureValueAction(org.eclipse.uml2.uml.AddStructuralFeatureValueAction element) {
        super(element);
    }

    @objid ("9d309405-c765-490c-b839-ac9dc49cbace")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setFeature((OpaqueAction) objingElt);
    }

    @objid ("beeb8435-5d1a-4e6b-9016-71dfb150ae2d")
    private void setFeature(OpaqueAction objingElt) {
        IMModelServices mmServices  = ReverseProperties.getInstance().getMModelServices();
        
        Dependency dependency = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createDependency();
        try {
            dependency.addStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2STRUCTURALFEATUREREFERENCE);
        } catch (ExtensionNotFoundException e) {
           Xmi.LOG.warning(e);
        }
        
        org.eclipse.uml2.uml.StructuralFeature feature = ((org.eclipse.uml2.uml.AddStructuralFeatureValueAction) getEcoreElement()).getStructuralFeature();
        Object behavior = ReverseProperties.getInstance().getMappedElement(feature);
        
        ModelElement obBehavior = null;
        
        if (behavior instanceof ModelElement){
            obBehavior = (ModelElement) behavior;
               
            dependency.setDependsOn(obBehavior);
            dependency.setImpacted(objingElt);
        }else{
            dependency.delete();
        }
    }

}
