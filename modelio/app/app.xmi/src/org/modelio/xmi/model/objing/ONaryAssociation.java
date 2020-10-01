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
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.NotFoundException;

/**
 * This class manages the NAryAssociation elements
 * @author ebrosse
 */
@objid ("cae3a5ab-0d2e-40cf-9f88-d2b837d60ba5")
public class ONaryAssociation extends OModelElement {
    @objid ("6bf88d4f-fe41-4b95-9f4c-e164f6b52a2a")
    private boolean isIsClassAssociation = true;

    @objid ("6c1e2d18-44b8-4d2b-b6ed-c3492d9668a5")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (!this.isIsClassAssociation){
            if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2COMMUNICATIONPATH)){
               return UMLFactory.eINSTANCE.createCommunicationPath();
            }else if (getObjingElement().isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2EXTENSION)){
                return UMLFactory.eINSTANCE.createExtension();
            }else{
                return createEcoreAssociation();
            }
        }else
           return getOrCreateEcoreAssociationClass();
    }

    @objid ("2e3a34e7-639f-4d6f-aa0a-22c692080d14")
    private org.eclipse.uml2.uml.AssociationClass getOrCreateEcoreAssociationClass() {
        GenerationProperties genProp = GenerationProperties.getInstance();
        // Gets or creates the Ecore org.eclipse.uml2.uml.AssociationClass:
        return  (org.eclipse.uml2.uml.AssociationClass) genProp
                                                 .getMappedElement(getObjingElement());
    }

    @objid ("d8f56553-1b1f-417c-9762-4b634a4d8cc4")
    private org.eclipse.uml2.uml.Element createEcoreAssociation() {
        return UMLFactory.eINSTANCE.createAssociation();
    }

    /**
     * Constructor of OAssociation.
     * it takes the exported Modelio org.eclipse.uml2.uml.Association as parameter
     * 
     * @param element : the exported Modelio org.eclipse.uml2.uml.Association
     */
    @objid ("15bdd97c-f9ff-48a1-a5c7-2c1556b46e60")
    public ONaryAssociation(final NaryAssociation element) {
        super(element);
        this.isIsClassAssociation = AbstractObjingModelNavigation.isIsClassAssociation(element);
    }

    @objid ("84d39e31-9968-432b-ae56-555c67728fea")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (!AbstractObjingModelNavigation.isOwnedByActor(getObjingElement())) {
        
            if (!this.isIsClassAssociation)
                linkEcoreAssociation((org.eclipse.uml2.uml.Association) ecoreElt);
        }else{
            String message = Xmi.I18N.getMessage("logFile.warning.unexportedAssociationCauseOwnerTypeActor",
                                                 getObjingElement().getName());
            GenerationProperties.getInstance().addWarning(message,  getObjingElement());
        
            ecoreElt.destroy();
        }
    }

    @objid ("444d675c-0920-4325-b3d7-48f9ae4c90b5")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

    @objid ("be022814-b7b5-4aaa-85c7-5287a9aa9d8f")
    private void linkEcoreAssociation(org.eclipse.uml2.uml.Association ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        org.eclipse.uml2.uml.Classifier ecoreOwner = null;
        for (NaryAssociationEnd assocEnd : getObjingElement().getNaryEnd()) {
            // Links the org.eclipse.uml2.uml.Association to the OwnerClassifNaryier of one of the
            // Properties
            // (sets the owner of the org.eclipse.uml2.uml.Association):
            if (ecoreOwner == null) {
                ecoreOwner =  (org.eclipse.uml2.uml.Classifier) genProp.getMappedElement(assocEnd
                        .getOwner());
                if (ecoreOwner != null) {
                    // Components and Nodes are handled as Class
                    // (they inherit from Class)
        
                    // Class, Component, Node
                    if (ecoreOwner instanceof org.eclipse.uml2.uml.Class) {
                        EcoreModelNavigation.attachAssocToNearestPkg(assocEnd
                                .getOwner(), ecoreElt);
                    } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {// org.eclipse.uml2.uml.Interface
                        EcoreModelNavigation.attachAssocToNearestPkg(assocEnd
                                .getOwner(), ecoreElt);
                    } else if (ecoreOwner instanceof org.eclipse.uml2.uml.UseCase) {// org.eclipse.uml2.uml.UseCase
                        EcoreModelNavigation.attachAssocToNearestPkg(assocEnd
                                .getOwner(), ecoreElt);
                    } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Actor) {// org.eclipse.uml2.uml.Actor
                        EcoreModelNavigation.attachAssocToNearestPkg(assocEnd
                                .getOwner(), ecoreElt);
                    } else if (ecoreOwner instanceof org.eclipse.uml2.uml.DataType) {// org.eclipse.uml2.uml.DataType
                        EcoreModelNavigation.attachAssocToNearestPkg(assocEnd
                                .getOwner(), ecoreElt);
                    } else if (ecoreOwner instanceof  org.eclipse.uml2.uml.Signal) {//  org.eclipse.uml2.uml.Signal
                        EcoreModelNavigation.attachAssocToNearestPkg(assocEnd
                                .getOwner(), ecoreElt);
                    } else {
                        ecoreElt.destroy();
                        throw new NotFoundException("Owner Class ("
                                + ecoreOwner.getClass().getSimpleName()
                                + ") Not Found");
                    }
                }
            }
        }
    }

    @objid ("2f47fdca-cb49-4026-afe9-69aa7737127d")
    @Override
    public NaryAssociation getObjingElement() {
        return (NaryAssociation) super.getObjingElement();
    }

}
