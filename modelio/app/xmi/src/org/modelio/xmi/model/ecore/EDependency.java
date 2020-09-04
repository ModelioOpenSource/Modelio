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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.XMIProperties;

@objid ("b49fce4d-2101-4013-8b3e-e6dcfb12bb10")
public class EDependency extends ENamedElement {
    @objid ("545a4730-c126-4154-944e-3f54c608c81d")
    private org.eclipse.uml2.uml.Dependency ecoreElement = null;

    @objid ("fac4958e-916c-434e-904e-8e0a027b2c54")
    @Override
    public List<UmlModelElement> createObjingElt() {
        return new ArrayList<>();
    }

    @objid ("3733e05b-5b41-4ebd-8d38-193ab250cdff")
    public EDependency(org.eclipse.uml2.uml.Dependency element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("c1cf2f1a-0d0e-4baa-a5fb-83b022565c80")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        if (ObjingEAnnotation.isDestroy(this.ecoreElement) 
                || ObjingEAnnotation.isDeleted(this.ecoreElement)){
            objingElt.delete();
        }else{
            setFlow((UmlModelElement) objingElt);
        }
    }

    @objid ("794e01cb-6a2c-42c5-93c0-dd54613499ab")
    @Override
    public void attach(List<Object> objingElts) {
        if (ReverseProperties.getInstance().isRoundtripEnabled() && ObjingEAnnotation.isProvidedInterface(this.ecoreElement)){ 
            attachProvidedInterface(objingElts);
        }else if (ReverseProperties.getInstance().isRoundtripEnabled() && ObjingEAnnotation.isRequiredInterface(this.ecoreElement)){
            attachRequiredInterface(objingElts);    
        }else{
            attachDependency(objingElts);
        }
    }

    @objid ("0272af99-530e-4196-a5c4-f8d950b7b80b")
    private void attachDependency(List<Object> objingElt) {
        EList<?> clientList = this.ecoreElement.getClients();
        EList<?> supplierList = this.ecoreElement.getSuppliers();
        
        for (Object eClient : clientList) {
            if (eClient instanceof org.eclipse.uml2.uml.NamedElement) {
                org.eclipse.uml2.uml.NamedElement ecoreClient = (org.eclipse.uml2.uml.NamedElement) eClient;
        
                UmlModelElement objingClient = getObjingEltFromMap(ecoreClient);
        
                if (objingClient != null) {
                    for (Object eSupplier : supplierList) {
                        if (eSupplier instanceof org.eclipse.uml2.uml.NamedElement) {
                            org.eclipse.uml2.uml.NamedElement ecoreSupplier = (org.eclipse.uml2.uml.NamedElement) eSupplier;
        
                            UmlModelElement objingSupplier = getObjingEltFromMap(ecoreSupplier);
        
                            if (objingSupplier != null) {
                                // Warning : unlike in UML2, in Ijing,
                                // org.eclipse.uml2.uml.Manifestation does no inherit from org.eclipse.uml2.uml.Dependency
                                Element objingTypeOfDependency = createTypeOfDependency(
                                        objingClient, objingSupplier);
        
                                if (objingTypeOfDependency != null
                                        && !objingElt.contains(objingTypeOfDependency))
        
                                    objingElt.add(objingTypeOfDependency);
                            }
                        }
                    }
                }
            }
        }
    }

    @objid ("1f55bee9-bc4f-4d0c-a544-233c395797d4")
    private void attachProvidedInterface(List<Object> objingElt) {
        EList<?> clientList = this.ecoreElement.getClients();
        EList<?> supplierList = this.ecoreElement.getSuppliers();
        
        for (Object eClient : clientList) {
            if (eClient instanceof org.eclipse.uml2.uml.NamedElement) {
                org.eclipse.uml2.uml.NamedElement ecoreClient = (org.eclipse.uml2.uml.NamedElement) eClient;
        
                UmlModelElement objingClient = getObjingEltFromMap(ecoreClient);
        
                if (objingClient != null) {
        
                    List<UmlModelElement> objingSuppliers = new ArrayList<>();
                    for (Object eSupplier : supplierList) {
        
                        if (eSupplier instanceof org.eclipse.uml2.uml.NamedElement) {
                            org.eclipse.uml2.uml.NamedElement ecoreSupplier = (org.eclipse.uml2.uml.NamedElement) eSupplier;
        
                            UmlModelElement objingSupplier = getObjingEltFromMap(ecoreSupplier);
        
                            if (objingSupplier != null) {
                                objingSuppliers.add(objingSupplier);
                            }
                        }
                    }
        
                    ProvidedInterface provided = createProvidedInterface(objingClient, objingSuppliers);
        
                    objingElt.add(provided);
                }
            }
        }
    }

    @objid ("1d915a99-3657-488d-9e98-1359b4b5c914")
    private void attachRequiredInterface(List<Object> objingElt) {
        EList<?> clientList = this.ecoreElement.getClients();
        EList<?> supplierList = this.ecoreElement.getSuppliers();
        
        for (Object eSupplier : supplierList) {
            if (eSupplier instanceof org.eclipse.uml2.uml.NamedElement) {
                org.eclipse.uml2.uml.NamedElement ecoreSupplier = (org.eclipse.uml2.uml.NamedElement) eSupplier;
        
                UmlModelElement objingSupplier = getObjingEltFromMap(ecoreSupplier);
        
                if (objingSupplier != null) {
        
                    List<UmlModelElement> objingClients = new ArrayList<>();
                    for (Object eClient : clientList) {
        
                        if (eClient instanceof org.eclipse.uml2.uml.NamedElement) {
                            org.eclipse.uml2.uml.NamedElement ecoreClient = (org.eclipse.uml2.uml.NamedElement) eClient;
        
                            UmlModelElement objingClient = getObjingEltFromMap(ecoreClient);
        
                            if (objingClient != null) {
                                objingClients.add(objingClient);
                            }
                        }
                    }
        
                    RequiredInterface required = createRequiredInterface(objingClients, objingSupplier);
        
                    objingElt.add(required);
                }
            }
        }
    }

    @objid ("adcaa553-d00d-4035-9521-5b0e3f41a0a9")
    private Element createTypeOfDependency(UmlModelElement objingClient, UmlModelElement objingSupplier) {
        Element objingTypeOfDependency = null;
         if (this.ecoreElement instanceof org.eclipse.uml2.uml.Manifestation) {
            objingTypeOfDependency = createManifestation(objingClient,
                    objingSupplier);
        }else if ((this.ecoreElement.getOwner() instanceof  org.eclipse.uml2.uml.CollaborationUse) 
                && (( (org.eclipse.uml2.uml.CollaborationUse)this.ecoreElement.getOwner()).getRoleBindings().contains(this.ecoreElement) )){
        
            objingTypeOfDependency = createBinding(objingSupplier,  (org.eclipse.uml2.uml.CollaborationUse) this.ecoreElement.getOwner());
        }else{
        
            objingTypeOfDependency = createDependency(objingClient,
                    objingSupplier);
        }
        return objingTypeOfDependency;
    }

    @objid ("bb64fd8a-0608-48b2-90b8-8944e681c880")
    private Binding createBinding(UmlModelElement objingSupplier, org.eclipse.uml2.uml.CollaborationUse ecoreOwner) {
        Binding objElement = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createBinding();
        
        CollaborationUse objOwner = (CollaborationUse) ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        
        objElement.setOwner(objOwner);
        
        for (Object client : this.ecoreElement.getClients()){
            Object objClient = ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)client);
        
            if ((objClient instanceof List<?>) && ((List<Element>) objClient).size() >0){
                objClient = ((List<Element>)objClient).get(0);
            }
        
            if (objClient instanceof ConnectorEnd){
                objElement.setConnectorEndRole((ConnectorEnd) objClient);
            }else if (objClient instanceof NaryConnector){
                objElement.setConnectorRole((NaryConnector) objClient);
            }else if (objClient instanceof BindableInstance){
                objElement.setRole((BindableInstance) objClient);
            }
        }
        
        for(Object supplier : this.ecoreElement.getSuppliers()){
            Object objSupplier =  ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element)supplier);
        
            if ((objSupplier instanceof List<?>) && ((List<Element>) objSupplier).size() >0){
                objSupplier = ((List<Element>)objSupplier).get(0);
            }
        
            if (objSupplier instanceof UmlModelElement)
                objElement.setRepresentedFeature(objingSupplier);
        }
        return objElement;
    }

    @objid ("e2a527de-34be-4b32-9d7c-9f64a44997fa")
    private Manifestation createManifestation(UmlModelElement objingClient, UmlModelElement objingSupplier) {
        if (objingClient instanceof Artifact) {
            Manifestation manif = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createManifestation();
        
            String name = this.ecoreElement.getName();
            if (EcoreModelNavigation.isNotNull(name))
                manif.setName(name);
            else 
                manif.setName("");
        
            manif.setOwner((Artifact) objingClient);
            manif.setUtilizedElement(objingSupplier);
            return manif;
        }
        return null;
    }

    @objid ("08a7905e-af58-4551-a71b-0a6a565d0b55")
    private RequiredInterface createRequiredInterface(List<UmlModelElement> objingClients, UmlModelElement objingSupplier) {
        RequiredInterface required = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createRequiredInterface();
        
        required.setRequiring((Port)objingSupplier);
        for (UmlModelElement objingClient :  objingClients)
            required.getRequiredElement().add((Interface)objingClient);
        return required;
    }

    @objid ("b4020e92-99ad-471f-a5a6-8a23193ccae1")
    private ProvidedInterface createProvidedInterface(UmlModelElement objingClient, List<UmlModelElement> objingSuppliers) {
        ProvidedInterface provided = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createProvidedInterface();
        
        provided.setProviding((Port)objingClient);
        for (UmlModelElement objingSupplier :  objingSuppliers)
            provided.getProvidedElement().add((Interface) objingSupplier);
        return provided;
    }

    @objid ("08988dc5-207d-4eb3-9075-131de65d5f4c")
    private Dependency createDependency(UmlModelElement objingClient, UmlModelElement objingSupplier) {
        Dependency objingDependency = null;
        
        
        IStandardModelFactory factory = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);
        if (this.ecoreElement instanceof org.eclipse.uml2.uml.Realization) {
            objingDependency = factory.createElementRealization();
        } else  if (this.ecoreElement instanceof org.eclipse.uml2.uml.Abstraction) {
            objingDependency = factory.createAbstraction();
        } else if (this.ecoreElement instanceof org.eclipse.uml2.uml.Usage) {
            objingDependency = factory.createUsage();
        } else {
            objingDependency = factory.createDependency();
        }
        
        if (objingDependency != null){
            String name = this.ecoreElement.getName();
            if (EcoreModelNavigation.isNotNull(name))
                objingDependency.setName(name);
            else 
                objingDependency.setName("");
            objingDependency.setImpacted(objingClient);
            objingDependency.setDependsOn(objingSupplier);
        }
        return objingDependency;
    }

    @objid ("cdc13f9a-8dc7-48ec-9fa4-fb1ef4f80c42")
    private UmlModelElement getObjingEltFromMap(final org.eclipse.uml2.uml.NamedElement ecoreClient) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        UmlModelElement objingClient = null;
        
        Object objClient = revProp.getMappedElement(ecoreClient);
        
        // Case of the client is itself a dependency => list of objing elts
        if (objClient instanceof ArrayList
                && ((ArrayList<?>) objClient).size() > 0) {
            objClient = ((ArrayList<Object>) objClient).get(0);
        }
        
        // not else -> objClient may have changed
        if (objClient instanceof UmlModelElement) {
            objingClient = (UmlModelElement) objClient;
        }
        return objingClient;
    }

    @objid ("b4deadb6-4e7a-461c-847a-41ff2207e2b4")
    private void setFlow(UmlModelElement objingElt) {
        if (ObjingEAnnotation.isFlow(this.ecoreElement)){
        
            try {
                Stereotype ster = ReverseProperties.getInstance().getMModelServices().getStereotype(XMIProperties.modelerModuleName, "flow", objingElt.getMClass());
        
                if (!objingElt.isStereotyped(XMIProperties.modelerModuleName, "flow"))
                    objingElt.getExtension().add(ster);
            } catch (ElementNotUniqueException e) {
                Xmi.LOG.warning(e);
            }
        
        }
    }

    @objid ("986f7593-6d83-450b-82d4-05e0167f20af")
    private Substitution createSubstitution(UmlModelElement objingClient, UmlModelElement objingSupplier) {
        if ((objingClient instanceof Classifier) && (objingSupplier instanceof Classifier)) {
        
            Substitution manif = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createSubstitution();
        
            String name = this.ecoreElement.getName();
            if (EcoreModelNavigation.isNotNull(name))
                manif.setName(name);
            else 
                manif.setName("");
        
            manif.setSubstitutingClassifier((Classifier) objingClient);
            manif.setContract((Classifier) objingSupplier);
            return manif;
        }
        return null;
    }

}
