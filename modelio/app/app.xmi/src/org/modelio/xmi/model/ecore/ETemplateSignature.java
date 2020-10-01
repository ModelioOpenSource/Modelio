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
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("c564792d-5160-424c-bb5a-53ca0befb1a6")
public class ETemplateSignature extends EElement {
    @objid ("0eb36f44-055b-4bd3-9d91-f64081703927")
    private org.eclipse.uml2.uml.TemplateSignature ecoreElement = null;

    @objid ("280ed59f-a602-46ba-96f5-77b551ff8648")
    @Override
    public Element createObjingElt() {
        if (!ObjingEAnnotation.isDeleted(this.ecoreElement)){
            org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
            Object objingOwner =  ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        
            if ((objingOwner instanceof Classifier)  
                    && !(objingOwner instanceof Enumeration)){
        
                IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
        
                Operation result = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createOperation();
        
                try {
                    result.getExtension().add(mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2TEMPLATESIGNATURE, result.getMClass()));
                } catch (ElementNotUniqueException e) {
                    Xmi.LOG.warning(e);
                }
        
                return result;
            }
        }
        return null;
    }

    @objid ("3aadecd8-a551-4b9f-a38f-a7247d60d739")
    public ETemplateSignature(org.eclipse.uml2.uml.TemplateSignature element) {
        super(element);
        this.ecoreElement = element;
    }

}
