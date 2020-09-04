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

@objid ("bbeaca2d-aa3b-45bc-b1ec-d5b4ef4b1f07")
public class EReadExtentAction extends EActivityNode {
    @objid ("68f1cd8b-491e-4f6d-87b9-4841a4b184bf")
    private org.eclipse.uml2.uml.ReadExtentAction ecoreElement = null;

    @objid ("07cbcc71-b4ec-4e4c-8559-98f30192b8c8")
    @Override
    public Element createObjingElt() {
        IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
        
        OpaqueAction element = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueAction();
        
        try {
            element.addStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2READEXTENTACTION);
        } catch (ExtensionNotFoundException e) {
            Xmi.LOG.warning(e);
        }
        return element;
    }

    @objid ("6106fd33-b159-4dd7-b8f1-51bbb0833002")
    public EReadExtentAction(org.eclipse.uml2.uml.ReadExtentAction element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("9313bd01-211c-423b-9585-42a45c370be6")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setClassifier((OpaqueAction) objingElt);
    }

    @objid ("0910ad03-2bc3-464e-b816-9fba81e21655")
    private void setClassifier(OpaqueAction objingElt) {
        org.eclipse.uml2.uml.Classifier classifier = this.ecoreElement.getClassifier();
        if (classifier != null){
            ModelElement obBehavior = (ModelElement)ReverseProperties.getInstance().getMappedElement(classifier);
        
            IMModelServices mmServices  = ReverseProperties.getInstance().getMModelServices();
            Dependency dependency = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createDependency();
            
            try {
                dependency.addStereotype(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2CLASSIFIERREFERENCE);
            } catch (ExtensionNotFoundException e) {
                Xmi.LOG.warning(e);
            }
        
            dependency.setDependsOn(obBehavior);
            dependency.setImpacted(objingElt);
        }
    }

}
