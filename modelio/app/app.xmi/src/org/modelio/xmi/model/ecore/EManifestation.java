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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("a4f17f41-b31d-4830-82b8-26d01ceb3d6c")
public class EManifestation extends ENamedElement {
    @objid ("445dbdfc-6cdb-4441-a4fe-b7cc3008b46e")
    private org.eclipse.uml2.uml.Manifestation ecoreElement = null;

    @objid ("58c3b71a-4740-4595-9019-d80bb488e63a")
    @Override
    public List<Manifestation> createObjingElt() {
        return createManifestation();
    }

    @objid ("78ad4974-a577-45e7-9659-048c5245fcbe")
    public  EManifestation(org.eclipse.uml2.uml.Manifestation element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("04862d70-e9b8-4c2b-a0fd-f3e02420826e")
    private List<Manifestation> createManifestation() {
        List<Manifestation> objingElts = new ArrayList<>();
        EList<?> clientList = this.ecoreElement.getClients();
        EList<?> supplierList = this.ecoreElement.getSuppliers();
        
        for (Object eClient : clientList) {
            if (eClient instanceof org.eclipse.uml2.uml.NamedElement) {
                org.eclipse.uml2.uml.NamedElement ecoreClient = (org.eclipse.uml2.uml.NamedElement) eClient;
        
                Object objingClient = ReverseProperties.getInstance().getMappedElement(ecoreClient);
        
                if ((objingClient != null) && (objingClient instanceof Artifact)) {
                    
                    for (Object eSupplier : supplierList) {
                        if (eSupplier instanceof org.eclipse.uml2.uml.NamedElement) {
                            org.eclipse.uml2.uml.NamedElement ecoreSupplier = (org.eclipse.uml2.uml.NamedElement) eSupplier;
                            Object objingSupplier = ReverseProperties.getInstance().getMappedElement(ecoreSupplier);
        
                            if (objingSupplier != null){
                                // Warning : unlike in UML2, in Objing,
                                // org.eclipse.uml2.uml.Manifestation does no inherit from org.eclipse.uml2.uml.Dependency
                                Manifestation objingTypeOfDependency = createManifestation(
                                        (Artifact) objingClient, (UmlModelElement) objingSupplier);
        
                                if (objingTypeOfDependency != null
                                        && !objingElts.contains(objingTypeOfDependency))
        
                                    objingElts.add(objingTypeOfDependency);
                            }
                        }
                    }
                }
            }
        }
        return objingElts;
    }

    @objid ("6b992a7f-5df0-432e-aa22-149ea71fa9d2")
    private Manifestation createManifestation(Artifact objingClient, UmlModelElement objingSupplier) {
        Manifestation manif = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createManifestation();
        
        String name = this.ecoreElement.getName();
        if (EcoreModelNavigation.isNotNull(name))
            manif.setName(name);
        
        manif.setOwner(objingClient);
        manif.setUtilizedElement(objingSupplier);
        return manif;
    }

    @objid ("1c7b84a6-907c-4b93-a72c-a430106b076f")
    @Override
    public void attach(Element objingElts) {
        //Done during creation
    }

}
