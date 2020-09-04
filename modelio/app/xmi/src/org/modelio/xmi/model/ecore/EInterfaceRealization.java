/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("3b1b7af7-4297-4594-a3f9-da6d2d40cc9e")
public class EInterfaceRealization extends ENamedElement {
    @objid ("184aa42a-8951-4e19-a376-dfa0d28a09b4")
    private org.eclipse.uml2.uml.InterfaceRealization ecoreElement = null;

    @objid ("f549897f-4674-4a03-a333-0c6ad5d96746")
    @Override
    public Element createObjingElt() {
        IStandardModelFactory factory = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);
        if ((this.ecoreElement.getClients().size() > 0) && (this.ecoreElement.getClients().get(0) instanceof org.eclipse.uml2.uml.Port))
            return factory.createProvidedInterface();
        else if (((this.ecoreElement.getClients().size() > 0) && (this.ecoreElement.getClients().get(0) instanceof Interface))
                || (this.ecoreElement.getContract() != null))
            return factory.createInterfaceRealization();
        else 
            return null;
    }

    @objid ("266b5e3b-8a9a-492e-8b73-6acc8272ce7d")
    public EInterfaceRealization(org.eclipse.uml2.uml.InterfaceRealization element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("4985b7f6-8122-451e-b72b-7308055900d9")
    @Override
    public void attach(Element objingElt) {
        //  take the ecore Imported and Importing
        org.eclipse.uml2.uml.Interface ecoreContract = this.ecoreElement.getContract();
        org.eclipse.uml2.uml. BehavioredClassifier ecoreClassifier = this.ecoreElement
        .getImplementingClassifier();
        
        boolean attached = false;
        if (ecoreContract != null && ecoreClassifier != null) {
            Interface objingContract = (Interface) ReverseProperties.getInstance()
            .getMappedElement(ecoreContract);
            NameSpace objingClassifier = (NameSpace) ReverseProperties.getInstance()
            .getMappedElement(ecoreClassifier);
        
            //  set to the objingElt Imported Importing previousely find
            if (objingContract != null && objingClassifier != null) {
                InterfaceRealization objingRImport = (InterfaceRealization) objingElt;
                objingRImport.setImplementer(objingClassifier);
                objingRImport.setImplemented(objingContract);
                attached = true;
            }
        }
        
        if ((this.ecoreElement.getClients().get(0) instanceof org.eclipse.uml2.uml.Port)){
            Port objPort = (Port) ReverseProperties.getInstance().getMappedElement(this.ecoreElement.getClients().get(0));
            objPort.getProvided().add((ProvidedInterface)objingElt);
            ((ProvidedInterface)objingElt).setProviding(objPort);
            for (org.eclipse.uml2.uml.NamedElement element : this.ecoreElement.getSuppliers()){
                Object obj =  ReverseProperties.getInstance().getMappedElement(element);
                if (obj instanceof Interface){
                    ((ProvidedInterface)objingElt).getProvidedElement().add((Interface)obj);
                }
            }
            attached = true;
        }
        
        if ((!attached)  && (this.ecoreElement.getSuppliers().get(0) instanceof org.eclipse.uml2.uml.Interface)
                && (this.ecoreElement.getClients().get(0) instanceof org.eclipse.uml2.uml. BehavioredClassifier)){
            ecoreContract = (org.eclipse.uml2.uml.Interface) this.ecoreElement.getSuppliers().get(0);
            ecoreClassifier = (org.eclipse.uml2.uml.BehavioredClassifier) this.ecoreElement.getClients().get(0);
        
            Interface objingContract = (Interface) ReverseProperties.getInstance()
            .getMappedElement(ecoreContract);
            NameSpace objingClassifier = (NameSpace) ReverseProperties.getInstance()
            .getMappedElement(ecoreClassifier);
        
            //  set to the objingElt Imported Importing previousely find
            if (objingContract != null && objingClassifier != null) {
                InterfaceRealization objingRImport = (InterfaceRealization) objingElt;
                objingRImport.setImplementer(objingClassifier);
                objingRImport.setImplemented(objingContract);
            }
        }
    }

}
