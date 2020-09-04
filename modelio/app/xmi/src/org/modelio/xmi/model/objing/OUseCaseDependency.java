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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;

/**
 * This class manages the export of Modelio UseCaseDependency
 * @author ebrosse
 */
@objid ("9ed4ea40-7f55-44c4-b325-b89a32187052")
public class OUseCaseDependency extends OModelElement {
    @objid ("43d023c4-343f-4699-acb5-155c9a21a5d2")
    private boolean isExtend = false;

    @objid ("1e0b8bbb-ea5a-4306-afab-376fa44cd9ab")
    private boolean isInclude = false;

    @objid ("32765699-1e81-47b4-992a-4f66f644fdd3")
    private UseCaseDependency objingElement;

    @objid ("f9b0ad75-cbb1-4d4a-ab1f-377907ce8247")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (this.isExtend) {
           return UMLFactory.eINSTANCE.createExtend();
        } else if (this.isInclude) {
            return UMLFactory.eINSTANCE.createInclude();
        } else {            
            throw new NotFoundException("UseCaseDependency type not found");
        }
    }

    /**
     * Constructor
     * @param param : the exported Modelio UseCaseDependency
     */
    @objid ("66d8233c-78cd-4b16-ae41-762316848c7e")
    public OUseCaseDependency(final UseCaseDependency param) {
        super(param);
        this.objingElement = param;
        if (AbstractObjingModelNavigation.isStereotyped(this.objingElement, Xmi.I18N
                .getString("objing.java.stereotype.extend")))
            this.isExtend = true;
        else if (AbstractObjingModelNavigation.isStereotyped(this.objingElement, Xmi.I18N
                .getString("objing.java.stereotype.include")))
            this.isInclude = true;
        else{
            throw new NotFoundException("UseCaseDependency type not found");
        }
    }

    @objid ("5a0e3baf-25b0-43d8-ab7d-49aded4bc5fb")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // This method is also called when linking a org.eclipse.uml2.uml.Usage, as org.eclipse.uml2.uml.Usage inherits
        // from org.eclipse.uml2.uml.Dependency.
        GenerationProperties genProp = GenerationProperties.getInstance();
        UseCase objingTarget = this.objingElement.getTarget();
        UseCase objingOrigin = this.objingElement.getOrigin();
        org.eclipse.uml2.uml.UseCase ecoreTarget = (org.eclipse.uml2.uml.UseCase) genProp.getMappedElement(objingTarget);
        org.eclipse.uml2.uml.UseCase ecoreOrigin = (org.eclipse.uml2.uml.UseCase) genProp.getMappedElement(objingOrigin);
        
        if (ecoreTarget != null && ecoreOrigin != null) {
            if (this.isExtend) {
                org.eclipse.uml2.uml.Extend ecoreElementExtend = (org.eclipse.uml2.uml.Extend) ecoreElt;
                ecoreElementExtend.setExtendedCase(ecoreTarget);
                ecoreElementExtend.setExtension(ecoreOrigin);
            } else if (this.isInclude) {
                org.eclipse.uml2.uml.Include ecoreElementInclude = (org.eclipse.uml2.uml.Include) ecoreElt;
                ecoreElementInclude.setAddition(ecoreTarget);
                ecoreElementInclude.setIncludingCase(ecoreOrigin);
            }
        }
    }

    @objid ("249cf2eb-8d1c-4003-9be1-aa4beac3f089")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
                if (this.isExtend){
           setExtensionLocation((org.eclipse.uml2.uml.Extend) ecoreElt);
           setExtendedCase((org.eclipse.uml2.uml.Extend) ecoreElt);
                }
    }

    @objid ("b09a64bd-3db5-485e-920c-ccf408e0950e")
    private void setExtendedCase(org.eclipse.uml2.uml.Extend ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreUseCase = GenerationProperties.getInstance().getMappedElement((this.objingElement).getTarget());
        
        if (ecoreUseCase instanceof org.eclipse.uml2.uml.UseCase)
            ecoreElt.setExtendedCase((org.eclipse.uml2.uml.UseCase) ecoreUseCase);
    }

    @objid ("988fb411-f8de-42f0-b195-33723f19046c")
    private void setExtensionLocation(org.eclipse.uml2.uml.Extend ecoreElt) {
        for (ExtensionPoint extPoint : this.objingElement.getExtensionLocation() ){
            org.eclipse.uml2.uml.Element ecoreExtPoint = GenerationProperties.getInstance().getMappedElement(extPoint);
        
        if (ecoreExtPoint instanceof org.eclipse.uml2.uml.ExtensionPoint)
            ecoreElt.getExtensionLocations().add((org.eclipse.uml2.uml.ExtensionPoint) ecoreExtPoint);
        }
    }

}
