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
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("c3ab695e-7613-4fbd-8392-585a72f02060")
public class ONaryLink extends OModelElement {
    @objid ("514d2777-5334-40c1-8826-eaef7f045f76")
    private Element linkOwner = null;

    @objid ("01eece0e-ba69-4661-9640-7bf4768b3f98")
    private MObject connectorOwner = null;

    @objid ("ede98d20-8b9e-4736-8fda-62f638a9570c")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (this.connectorOwner != null) {
            return UMLFactory.eINSTANCE.createConnector();
        } else if (this.linkOwner != null) {
            return UMLFactory.eINSTANCE.createInstanceSpecification();
        }
        
        String message = Xmi.I18N.getMessage("logFile.warning.unsupportedExport",
                getObjingElement().getName(),
                getObjingElement().getClass().getSimpleName());
        GenerationProperties.getInstance().addWarning(message, getObjingElement());
        return null;
    }

    @objid ("47a76052-6166-4d85-bb81-b773971cff5e")
    public ONaryLink(NaryLink element) {
        super(element);
        this.linkOwner = AbstractObjingModelNavigation.getNaryLinkOwner(element);
        this.connectorOwner = AbstractObjingModelNavigation.getNaryConnectorOwner(element);
    }

    @objid ("52f33632-9b0d-4312-adb8-175edfa21cc0")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt != null) {
            if (ecoreElt instanceof org.eclipse.uml2.uml.Connector) {
                attachConnector((org.eclipse.uml2.uml.Connector) ecoreElt);
            } else if (ecoreElt instanceof InstanceSpecification) {
                attachInstanceSpecification((InstanceSpecification) ecoreElt);
            }
        }
    }

    @objid ("3a502d83-ea4d-4bbf-b43f-347f38a141bb")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.Connector) {
            setConnectorProperties((org.eclipse.uml2.uml.Connector) ecoreElt);
        }else if (ecoreElt instanceof InstanceSpecification) {
            setName((InstanceSpecification) ecoreElt);
            setBase((InstanceSpecification) ecoreElt);  
            if (GenerationProperties.getInstance().isRoundtripEnabled()){
                ObjingEAnnotation.setIsLink(ecoreElt);  
            }
        }
    }

    @objid ("a353bbd5-474d-4eff-9db5-fe92a7906e55")
    private void setName(InstanceSpecification connector) {
        String eltName = getObjingElement().getName();
        if (AbstractObjingModelNavigation.isNotNullOrEmpty(eltName)) {
            connector.setName(eltName);
        }
    }

    @objid ("e4b0dc1b-6411-4bdf-a7b9-bc1f461b7d6d")
    private void setBase(InstanceSpecification connector) {
        NaryAssociation base = getObjingElement().getModel();
        
        if (base != null) {
            org.eclipse.uml2.uml.Element type = GenerationProperties.getInstance().getMappedElement(base);
            if (type instanceof org.eclipse.uml2.uml.Classifier) {
                connector.getClassifiers().add((org.eclipse.uml2.uml.Classifier) type);
            }
        }
    }

    @objid ("c73bc0b8-ace9-4eb1-ad70-2a02866b29b7")
    private void attachConnector(org.eclipse.uml2.uml.Connector ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(this.connectorOwner);
        if (ecoreOwner != null) {
            // Components and Nodes are handled as Class
            // (they inherit from Class)
        
            // Class, Component, Node
            if (ecoreOwner instanceof org.eclipse.uml2.uml.StructuredClassifier) {
                org.eclipse.uml2.uml.StructuredClassifier ownerIsClass = (org.eclipse.uml2.uml.StructuredClassifier) ecoreOwner;
                ownerIsClass.getOwnedConnectors().add(ecoreElt);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName()
                        + ") Not Found");
            }
        }
    }

    @objid ("88748d07-c9af-4244-b5b6-36684bf25b86")
    private void attachInstanceSpecification(InstanceSpecification ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(this.linkOwner);
        
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
                org.eclipse.uml2.uml.Package ownerIsPkg = (org.eclipse.uml2.uml.Package) ecoreOwner;
                ownerIsPkg.getPackagedElements().add(ecoreElt);
        
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component) {
                org.eclipse.uml2.uml.Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
                ownerIsCmpnt.getPackagedElements().add(ecoreElt);
            } else {
                String message = Xmi.I18N.getMessage("logFile.exception.stringConverter.haveNotOwner", ecoreElt.getClass().toString());
                GenerationProperties.getInstance().addWarning(message, getObjingElement());
            }
        }
    }

    @objid ("5e5d850c-597a-4d36-bbc7-30f288979a62")
    private void setConnectorProperties(org.eclipse.uml2.uml.Connector ecoreElt) {
        setBase(ecoreElt);
        setKind(ecoreElt);
    }

    @objid ("b9895ce4-98c1-4517-b818-2e437526cc6e")
    private void setBase(org.eclipse.uml2.uml.Connector ecoreElt) {
        Element temp = getObjingElement().getModel();
        if (temp != null) {
            org.eclipse.uml2.uml.Element ecoreType = GenerationProperties.getInstance().getMappedElement(temp);
            if (ecoreType instanceof org.eclipse.uml2.uml.Association) {
                ecoreElt.setType((org.eclipse.uml2.uml.Association) ecoreType);
            }
        
        }
    }

    @objid ("37e5e691-2c49-4b7c-8b0f-eb9c019a8986")
    private void setKind(org.eclipse.uml2.uml.Connector ecoreElt) {
        ecoreElt.setKind(org.eclipse.uml2.uml.ConnectorKind.DELEGATION_LITERAL);
    }

    @objid ("4db3b72d-321e-48fd-a3a4-fa43e4349e93")
    @Override
    public NaryLink getObjingElement() {
        return (NaryLink) super.getObjingElement();
    }

}
