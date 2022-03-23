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
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;

/**
 * This class manages the import of Ecore Named org.eclipse.uml2.uml.Element
 * @author ebrosse
 * @param <IMModelServices>
 */
@objid ("1e3af1b3-5b05-4ba0-9b89-0178669ae528")
public class EExtend extends ENamedElement {
    @objid ("9abff620-2fa1-4acf-aa2a-f50363b5c14c")
    private org.eclipse.uml2.uml.Extend ecoreElement;

    @objid ("9dd36771-66c3-465f-ba2a-6302c8b7996f")
    @Override
    public Element createObjingElt() {
        IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
        UseCaseDependency result = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createUseCaseDependency();
        
        try {
            Stereotype extendStereo = mmServices.getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.EXTEND, result.getMClass());
            result.getExtension().add(extendStereo);
        } catch (ElementNotUniqueException e) {
            Xmi.LOG.warning(e);
        }
        return  result;
    }

    /**
     * Constructor with the imported Ecore org.eclipse.uml2.uml.Extend
     * @param element : the imported Ecore org.eclipse.uml2.uml.Extend
     */
    @objid ("7524021f-9aa9-4ee1-831b-07418335f4ad")
    public  EExtend(final org.eclipse.uml2.uml.Extend element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("0aaee0a3-1fec-459b-966a-4ad26fbe0e41")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.UseCase ecoreTarget = this.ecoreElement.getExtendedCase();
        org.eclipse.uml2.uml.UseCase ecoreOrigin = this.ecoreElement.getExtension();
        UseCase objingTarget = (UseCase) revProp
                .getMappedElement(ecoreTarget);
        UseCase objingOrigin = (UseCase) revProp
                .getMappedElement(ecoreOrigin);
        
        if ((objingTarget != null) && (objingOrigin != null)) {
            UseCaseDependency objingUseCaseDependency = (UseCaseDependency) objingElt;
            objingUseCaseDependency.setOrigin(objingOrigin);
            objingUseCaseDependency.setTarget(objingTarget);
        }
        
    }

    @objid ("41997c2f-cdb2-491d-b9b3-232d90635e8c")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setExtensionPoint((UseCaseDependency) objingElt);
        
    }

    @objid ("5a98d150-ed76-4390-97f2-55a4614379c7")
    private void setExtensionPoint(UseCaseDependency dependency) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        for (Object extPoint : this.ecoreElement.getExtensionLocations()) {
            org.eclipse.uml2.uml.ExtensionPoint ecoreExtensionPoint = (org.eclipse.uml2.uml.ExtensionPoint) extPoint;
            ExtensionPoint objingExtensionPoint = (ExtensionPoint) revProp
                    .getMappedElement(ecoreExtensionPoint);
        
            if (objingExtensionPoint != null) {
                dependency.getExtensionLocation().add(objingExtensionPoint);
            }
        }
        
    }

}
