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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("cf21353e-c5a2-4453-a989-24ba2d017de1")
public class ODependency extends OModelElement {
    @objid ("d20c4cc4-2c4c-4055-98ee-5b4bd333eed0")
    private Dependency objingElement;

    @objid ("d3e81b41-0756-4da2-86f1-ab55ade1012a")
    private GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("2a0938ea-9aef-4787-8753-46fc67ded246")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ASSOCIATIONREFERENCE) 
                || this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2METHODREFERENCE)
                || this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2STRUCTURALFEATUREREFERENCE)
                || this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2CLASSIFIERREFERENCE)
                || this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ENDCREATIONDATAREFERENCE)
                || this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ENDDESTRUCTIONDATAREFERENCE)
                || this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2INSTANCEVALUE)
                 || this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2EVENT)
                || this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME,IModelerModuleStereotypes.PART)){
            return null;
        }else if (this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2COMPONENTREALIZATION)){
            return UMLFactory.eINSTANCE.createComponentRealization();
        }else if (this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2DEPLOYMENT)){
            return UMLFactory.eINSTANCE.createDeployment();
        }else if (this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2PROTOCOLCONFORMANCE)){
            return UMLFactory.eINSTANCE.createProtocolConformance();
        }else if (this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ABSTRACTION)){
            return UMLFactory.eINSTANCE.createAbstraction();
        }else if (this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.SATISFY)
                ||this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.VERIFY)
                ||this.objingElement.isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.DERIVE)){
                    this.genProp.addSysMLExported(this.objingElement);
            return UMLFactory.eINSTANCE.createAbstraction();
        }else{
            org.eclipse.uml2.uml.Dependency ecoreDependency = UMLFactory.eINSTANCE.createDependency();
            ecoreDependency.setName(this.objingElement.getName());
            return ecoreDependency;
        }
    }

    @objid ("a07add4b-7d54-404b-8c7e-b69ee5fed1cc")
    public ODependency(Dependency element) {
        super(element);
        this.objingElement = element;
    }

    @objid ("8094065c-4c49-406f-bae2-96cad2be47e1")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (!(ecoreElt instanceof org.eclipse.uml2.uml.InterfaceRealization)) {
            // This method is also called when linking a org.eclipse.uml2.uml.Usage, as org.eclipse.uml2.uml.Usage inherits
            // from org.eclipse.uml2.uml.Dependency.
           
                
            ModelElement objingClient = this.objingElement.getImpacted();
            ModelElement objingSupplier = this.objingElement.getDependsOn();
                
            if (objingClient != null && objingSupplier != null) {
                // Gets or creates the ecore "Client" element:
                org.eclipse.uml2.uml.Element ecoreClient = this.genProp
                .getMappedElement(objingClient);
                
                // Gets or creates the ecore "Supplier" element:
                org.eclipse.uml2.uml.Element ecoreSupplier = this.genProp
                .getMappedElement(objingSupplier);
                
                if (ecoreClient != null && ecoreSupplier != null ) {
                
                    if ((ecoreClient instanceof org.eclipse.uml2.uml.NamedElement) && (ecoreSupplier instanceof org.eclipse.uml2.uml.NamedElement)){
                        org.eclipse.uml2.uml.Dependency ecoreDependency = (org.eclipse.uml2.uml.Dependency) ecoreElt;
                
                        ecoreDependency.getClients().add((org.eclipse.uml2.uml.NamedElement)ecoreClient);
                        ecoreDependency.getSuppliers().add((org.eclipse.uml2.uml.NamedElement)ecoreSupplier);
                
                        org.eclipse.uml2.uml.Package ecorePkg = ecoreClient.getNearestPackage();
                        if (ecorePkg == null) {
                            this.genProp.getEcoreModel().getPackagedElements().add(
                                    ecoreDependency);
                        } else {
                            ecorePkg.getPackagedElements().add(ecoreDependency);
                        }
                    }else{
                        ecoreElt.destroy();
                    }
                }else{
                    ecoreElt.destroy();
                }
            }else 
                ecoreElt.destroy();
        }
    }

    @objid ("2b625889-4ddd-4a7a-b84e-00f856024712")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}
