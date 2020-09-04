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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.XMIProperties;

@objid ("b3b7c823-6857-4231-9af8-58b42da2b277")
public class EDeployment extends EDependency {
    @objid ("9283c8da-6c90-43d7-8a39-e6620bb4ce52")
    private org.eclipse.uml2.uml.Deployment ecoreElement = null;

    @objid ("3c6c2660-e5be-4b57-bef1-02ca1a26bb73")
    public EDeployment(org.eclipse.uml2.uml.Deployment element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("b680fed5-ab3b-44cb-9ae8-eba2d1ea8abb")
    @Override
    public void attach(List<Object> objingElts) {
        EList<?> clientList = this.ecoreElement.getClients();
        EList<?> supplierList = this.ecoreElement.getSuppliers();
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        for (Object eClient : clientList) {
            if (eClient instanceof org.eclipse.uml2.uml.NamedElement) {
                org.eclipse.uml2.uml.NamedElement ecoreClient = (org.eclipse.uml2.uml.NamedElement) eClient;
        
                ModelElement objingClient = (ModelElement) revProp.getMappedElement(ecoreClient);
        
                if (objingClient != null) {
                    for (Object eSupplier : supplierList) {
                        if (eSupplier instanceof org.eclipse.uml2.uml.NamedElement) {
                            org.eclipse.uml2.uml.NamedElement ecoreSupplier = (org.eclipse.uml2.uml.NamedElement) eSupplier;
        
                            ModelElement objingSupplier = (ModelElement) revProp.getMappedElement(ecoreSupplier);
        
                            if (objingSupplier != null) {
                                // Warning : unlike in UML2, in Ijing,
                                // org.eclipse.uml2.uml.Manifestation does no inherit from org.eclipse.uml2.uml.Dependency
                                IMModelServices mmServices = ReverseProperties.getInstance().getMModelServices();
                                
                                Dependency objingTypeOfDependency = mmServices.getModelFactory().getFactory(IStandardModelFactory.class).createDependency();
        
                                try {
                                    objingTypeOfDependency.getExtension().add(mmServices.getStereotype(XMIProperties.modelerModuleName, 
                                            IModelerModuleStereotypes.UML2DEPLOYMENT, objingTypeOfDependency.getMClass()));
                                } catch (ElementNotUniqueException e) {
                                   Xmi.LOG.warning(e);                               
                                }
        
                                String name = this.ecoreElement.getName();
                                if (EcoreModelNavigation.isNotNull(name))
                                    objingTypeOfDependency.setName(name);
                                else 
                                    objingTypeOfDependency.setName("");
        
                                objingTypeOfDependency.setImpacted(objingClient);
                                objingTypeOfDependency.setDependsOn(objingSupplier);
        
                                if (!objingElts.contains(objingTypeOfDependency))
                                    objingElts.add(objingTypeOfDependency);
                            }
                        }
                    }
                }
            }
        }
    }

}
