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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("a16815c5-3e91-4ace-9209-db331254f7d2")
public class OExtensionPoint extends OModelElement {
    @objid ("4d51f3a6-a6f0-468f-a88a-92ffe5c20e29")
    private ExtensionPoint objingElement;

    @objid ("b71492be-7011-4f28-ae0e-93dbf55c67ed")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE
                                                                 .createExtensionPoint();
    }

    @objid ("d6e22db1-0bf1-4091-ab62-6169c547f570")
    public OExtensionPoint(ExtensionPoint param) {
        super(param);
        this.objingElement = param;
    }

    @objid ("484e486f-cd72-4b6b-853d-58e00691e895")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        UseCase objingOwner = this.objingElement.getOwner();
                
        org.eclipse.uml2.uml.UseCase ecoreOwner = (org.eclipse.uml2.uml.UseCase) genProp.getMappedElement(objingOwner);
        org.eclipse.uml2.uml.ExtensionPoint currentExtensionPoint = (org.eclipse.uml2.uml.ExtensionPoint) ecoreElt;
        if (ecoreOwner != null) {
            currentExtensionPoint.setUseCase(ecoreOwner);
        }
    }

    @objid ("17e7c2e5-7eaf-4907-bf32-5560d4739cc0")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setVisibility((org.eclipse.uml2.uml.ExtensionPoint) ecoreElt);
        setUseCaseDependency((org.eclipse.uml2.uml.ExtensionPoint) ecoreElt);
    }

    @objid ("c88248aa-9372-4712-b5b7-1caa487e3657")
    private void setUseCaseDependency(org.eclipse.uml2.uml.ExtensionPoint point) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        List<UseCaseDependency> objingUseCaseDepElts = this.objingElement
                .getExtended();
        for (UseCaseDependency objingUseCaseDepElt : objingUseCaseDepElts) {
            org.eclipse.uml2.uml.Element ecoreUseCaseDep = genProp
                    .getMappedElement(objingUseCaseDepElt);
            if (ecoreUseCaseDep instanceof org.eclipse.uml2.uml.Extend) {
                ((org.eclipse.uml2.uml.Extend) ecoreUseCaseDep).getExtensionLocations().add(point);
            }
        }
    }

    @objid ("f15a20a1-3bf1-4276-a963-1ba557e93ae8")
    private void setVisibility(org.eclipse.uml2.uml.ExtensionPoint ecoreElt) {
        switch (this.objingElement.getVisibility()) {
        case PACKAGEVISIBILITY:
            ecoreElt.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PACKAGE_LITERAL);
            break;
        case PRIVATE:
            ecoreElt.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PRIVATE_LITERAL);
            break;
        case PROTECTED:
            ecoreElt.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PROTECTED_LITERAL);
            break;
        case PUBLIC:
            // It is the Default Value
        //          ecoreElt.setVisibility(org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL);
            break;
        case VISIBILITYUNDEFINED:
            ObjingEAnnotation.setIsUndefined(ecoreElt);
            // No undefined field exist in UML2-Ecore for visibility:
            // a visibility not setted corresponds to a PUBLIC visibility.
            break;
        }
    }

}
