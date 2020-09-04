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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.XMIProperties;

/**
 * This class manages the Association elements
 * @author ebrosse
 */
@objid ("d8300710-57dd-42fe-9e40-d51da6b06465")
public class OAssociation extends OModelElement {
    @objid ("b553ce60-79f0-4587-b02a-1eb3455aaefb")
    private boolean isIsClassAssociation = true;

    @objid ("0d9493e6-7d92-47ee-b497-536842e79387")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (!this.isIsClassAssociation){
            if (getObjingElement().isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2COMMUNICATIONPATH)){
               return UMLFactory.eINSTANCE.createCommunicationPath();
            }else if (getObjingElement().isStereotyped(XMIProperties.modelerModuleName, IModelerModuleStereotypes.UML2EXTENSION)){
                return UMLFactory.eINSTANCE.createExtension();
            }else{
                return createEcoreAssociation();
            }
        }else
            // The Ecore org.eclipse.uml2.uml.AssociationClass can be created when parsing the
            // related Ijing ClassAssociation, Class
            // or current org.eclipse.uml2.uml.Association.
           return getOrCreateEcoreAssociationClass();
    }

    @objid ("77c53512-552a-470f-be7c-a1a45e186996")
    private org.eclipse.uml2.uml.AssociationClass getOrCreateEcoreAssociationClass() {
        GenerationProperties genProp = GenerationProperties.getInstance();
        // Gets or creates the Ecore org.eclipse.uml2.uml.AssociationClass:
        return  (org.eclipse.uml2.uml.AssociationClass) genProp
                                                 .getMappedElement(getObjingElement());
    }

    @objid ("dd4c8de5-7416-4da2-943e-5259c6598182")
    private org.eclipse.uml2.uml.Element createEcoreAssociation() {
        return UMLFactory.eINSTANCE.createAssociation();
    }

    /**
     * Constructor of OAssociation.
     * it takes the exported Modelio org.eclipse.uml2.uml.Association as parameter
     * @param element : the exported Modelio org.eclipse.uml2.uml.Association
     */
    @objid ("f72568fa-cb09-47d9-bf66-5fbabcf90299")
    public OAssociation(final Association element) {
        super(element);
        this.isIsClassAssociation = AbstractObjingModelNavigation.isIsClassAssociation(element);
    }

    @objid ("771b65ef-0beb-4f57-b5d0-f0354cc5d68a")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (!AbstractObjingModelNavigation.isOwnedByActor(getObjingElement())) {
        
            if (!this.isIsClassAssociation)
                linkEcoreAssociation((org.eclipse.uml2.uml.Association) ecoreElt);
        }else{
            
            String message = Xmi.I18N.getMessage("logFile.warning.unexportedAssociationCauseOwnerTypeActor", getObjingElement().getName());
            GenerationProperties.getInstance().addWarning(message,  getObjingElement());
        
            ecoreElt.destroy();
        }
    }

    @objid ("77efb628-9a57-492e-99e3-12eeab81b220")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

    @objid ("12222621-ec8f-4cde-b1eb-dbd67b6b04e5")
    private void linkEcoreAssociation(org.eclipse.uml2.uml.Association ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        org.eclipse.uml2.uml.Classifier ecoreOwner = null;
        for (AssociationEnd assocEnd : getObjingElement().getEnd()) {
            // Links the org.eclipse.uml2.uml.Association to the OwnerClassifier of one of the
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

    @objid ("60ce3c10-8bbd-4feb-be40-c18867eab289")
    @Override
    public Association getObjingElement() {
        return (Association) super.getObjingElement();
    }

}
