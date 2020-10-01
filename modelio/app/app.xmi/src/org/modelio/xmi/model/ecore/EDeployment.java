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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.NamedElement;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.xmi.infrastructure.dependency.UML2Deployment;
import org.modelio.xmi.reverse.ReverseProperties;

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
    public void attach(Element objingElts) {
        //Done during creation
    }

    @objid ("4c64f967-7a0f-4d96-ad11-54292dece368")
    @Override
    public List<Element> createObjingElt() {
        return createDeployment();
    }

    @objid ("18099666-7beb-40ec-90ba-bb5873ddd360")
    private List<Element> createDeployment() {
        List<Element> objingElts = new ArrayList<>();
        EList<NamedElement> clientList = this.ecoreElement.getClients();
        EList<NamedElement> supplierList = this.ecoreElement.getSuppliers();
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        for (NamedElement ecoreClient : clientList) {
        
            Object objingClient = revProp.getMappedElement(ecoreClient);
        
            if ((objingClient != null) && (objingClient instanceof ModelElement )) {
        
                for (NamedElement eSupplier : supplierList) {
        
                    Object objingSupplier = revProp.getMappedElement(eSupplier);
        
                    if ((objingSupplier != null) && (objingSupplier instanceof ModelElement )) {
                        // Warning : unlike in UML2, in Modelio,
                        // org.eclipse.uml2.uml.Manifestation does no inherit from org.eclipse.uml2.uml.Dependency
                        Dependency objingTypeOfDependency = UML2Deployment.create().getElement();
                        objingTypeOfDependency.setImpacted((ModelElement) objingClient);
                        objingTypeOfDependency.setDependsOn((ModelElement) objingSupplier);
        
                        if (!objingElts.contains(objingTypeOfDependency))
                            objingElts.add(objingTypeOfDependency);
                    }
                }
            }
        }
        return objingElts;
    }

}
