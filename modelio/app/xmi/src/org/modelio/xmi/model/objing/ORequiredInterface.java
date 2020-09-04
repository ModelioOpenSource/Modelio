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
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("9a1a4ab5-c20a-45da-9278-c5b0d7265dcf")
public class ORequiredInterface extends OElement implements IOElement {
    @objid ("cd28402b-8ad0-48a9-a7c1-8b08de92ddf2")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        RequiredInterface req = getObjingElement();
        Port owner = req.getRequiring();
        org.eclipse.uml2.uml.Element ecoreOwner =  GenerationProperties.getInstance().getMappedElement(owner);
        
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            if (getObjingElement().getRequiredElement().size() == 0){
                ObjingEAnnotation.setNumberRequiredInterface(ecoreOwner,
                        ObjingEAnnotation.getNumberRequiredInterface(ecoreOwner) + 1 ) ;
            }else{
                org.eclipse.uml2.uml.Dependency dep = UMLFactory.eINSTANCE.createDependency();
                ObjingEAnnotation.setIsRequiredInterface(dep);
                return dep;
            }
        }
        
        String message = Xmi.I18N.getMessage("logFile.warning.unsupportedExport", 
                req.getName(), 
                req.getClass().getSimpleName());
        String  description = Xmi.I18N.getMessage("logFile.warning.notUML",
                req.getName());
        GenerationProperties.getInstance().addWarning(message, req, description);
        return null;
    }

    @objid ("d1aad5fe-7d1a-4f1b-ac75-ae374b002247")
    public ORequiredInterface(RequiredInterface param) {
        super(param);
    }

    @objid ("a635f70f-b48f-49b0-9ede-b342967dfd05")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        Port objPort = getObjingElement().getRequiring();
        ModelElement objingClient = objPort.getBase();
        org.eclipse.uml2.uml.Element ecorePort = GenerationProperties.getInstance().getMappedElement(objPort);
        
        if (objingClient != null) {
            if (getObjingElement().getRequiredElement().size() >0){
                for (ModelElement objingSupplier : getObjingElement().getRequiredElement()){
        
                    org.eclipse.uml2.uml.Element ecoreClient = GenerationProperties.getInstance()
                            .getMappedElement(objingClient);
        
                    org.eclipse.uml2.uml.Element ecoreSupplier = GenerationProperties.getInstance()
                            .getMappedElement(objingSupplier);
        
                    if (ecoreClient != null && ecoreSupplier != null) {
        
                        org.eclipse.uml2.uml.Usage ecoreUsage = null;
        
                        for (org.eclipse.uml2.uml.Dependency usage : ((org.eclipse.uml2.uml.NamedElement)ecoreClient).getClientDependencies()){
                            if (usage instanceof org.eclipse.uml2.uml.Usage)
                                if (((org.eclipse.uml2.uml.Usage) usage).getSuppliers().contains(ecoreSupplier)){
                                    ecoreUsage = (org.eclipse.uml2.uml.Usage) usage;
                                }
                        }
        
                        if (ecoreUsage != null){
        
                            ecoreUsage.getClients().add((org.eclipse.uml2.uml.NamedElement)ecoreClient);
                            ecoreUsage.getSuppliers().add((org.eclipse.uml2.uml.NamedElement)ecoreSupplier);
        
                            Package ecorePkg = ecoreClient.getNearestPackage();
                            if (ecorePkg == null) {
                                GenerationProperties.getInstance().getEcoreModel().getPackagedElements().add(
                                        ecoreUsage);
                            } else {
                                ecorePkg.getPackagedElements().add(ecoreUsage);
                            }
                        }else
                            ecoreElt.destroy();
                    }else
                        ecoreElt.destroy();
        
                }
            }else
                ecoreElt.destroy();
        }else{
            if (GenerationProperties.getInstance().isRoundtripEnabled()) {
        
                if (getObjingElement().getRequiredElement().size() == 0){
                    setNumberRequiredInterface(ecorePort);
                    ecoreElt.destroy();
                }else{
        
                    org.eclipse.uml2.uml.Dependency dependency =  (org.eclipse.uml2.uml.Dependency) ecoreElt;
        
                    org.eclipse.uml2.uml.Element port = GenerationProperties.getInstance().getMappedElement(objPort);
                    Package pack = ecorePort.getNearestPackage();
        
                    if ((port instanceof org.eclipse.uml2.uml.NamedElement) && (pack!= null)){
        
                        dependency.getSuppliers().add((org.eclipse.uml2.uml.NamedElement) port);
        
                        for (Interface inter : getObjingElement().getRequiredElement())
                            dependency.getClients().add((org.eclipse.uml2.uml.NamedElement)GenerationProperties.getInstance().getMappedElement(inter));
        
                        ObjingEAnnotation.setIsRequiredInterface(dependency);
        
                        pack.getPackagedElements().add(dependency);
                    }else{
                        GenerationProperties.getInstance().getEcoreModel().getPackagedElements().add(
                                dependency);
                    }
                }
            }
        
        }
    }

    @objid ("c5a2d042-7546-4046-bb69-ce6baaba0b63")
    private void setNumberRequiredInterface(org.eclipse.uml2.uml.Element ecoreElt) {
        int number = ObjingEAnnotation.getNumberRequiredInterface(ecoreElt);
        
        for (RequiredInterface required : getObjingElement().getRequiring().getRequired()){
            if (required.getRequiredElement().size() == 0)
                number++;
        }
        
        if (number != 0)
            ObjingEAnnotation.setNumberRequiredInterface(
                    GenerationProperties.getInstance().getMappedElement(getObjingElement().getRequiring()), number);
    }

    @objid ("76678cb7-d9b7-44eb-9966-ac01743830e0")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
    }

    @objid ("715525c7-cfc8-4e56-8887-1fc8ea2e285d")
    @Override
    public RequiredInterface getObjingElement() {
        return (RequiredInterface) super.getObjingElement();
    }

}
