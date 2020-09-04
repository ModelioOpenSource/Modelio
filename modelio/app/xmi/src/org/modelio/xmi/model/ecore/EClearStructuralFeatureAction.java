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

@objid ("9cba02d9-9650-4947-b0b6-a81f3d8ce77a")
public class EClearStructuralFeatureAction extends EActivityNode {
    @objid ("a52bf8d8-bae6-428f-a548-ad17881a1cf3")
    private org.eclipse.uml2.uml.ClearStructuralFeatureAction ecoreElement;

    @objid ("9ba410fa-10d5-4415-ad63-5a8f0516fbea")
    @Override
    public Element createObjingElt() {
        IMModelServices mmService = ReverseProperties.getInstance().getMModelServices();
        
        OpaqueAction element = mmService.getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueAction();
        
        try {
            element.addStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CLEARSTRUCTURALFEATUREACTION);
        } catch (ExtensionNotFoundException e) {
            Xmi.LOG.warning(e);
        }
        return element;
    }

    @objid ("db7ce6cc-7550-458f-9241-7d27c8acb875")
    public EClearStructuralFeatureAction(org.eclipse.uml2.uml.ClearStructuralFeatureAction element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("b4a16c13-4014-4360-b3a2-a57de2da1246")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setFeature((OpaqueAction) objingElt);
    }

    @objid ("c3d18153-4016-4f8e-b618-b49e41c4d201")
    private void setFeature(OpaqueAction objingElt) {
        IMModelServices mmServices  = ReverseProperties.getInstance().getMModelServices();
        Dependency dependency = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createDependency();
        
        try {
            dependency.addStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2STRUCTURALFEATUREREFERENCE);
        } catch (ExtensionNotFoundException e) {
            Xmi.LOG.warning(e);
        }
        
        org.eclipse.uml2.uml.StructuralFeature feature = this.ecoreElement.getStructuralFeature();
        ModelElement obBehavior = (ModelElement)ReverseProperties.getInstance().getMappedElement(feature);
        dependency.setDependsOn(obBehavior);
        dependency.setImpacted(objingElt);
    }

}
