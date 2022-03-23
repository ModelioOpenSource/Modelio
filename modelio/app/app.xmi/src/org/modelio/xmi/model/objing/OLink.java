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
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("fbb72fc7-d37d-41cd-b234-c67dadb617b8")
public class OLink extends OModelElement {
    @objid ("aa62f2ae-a55a-4259-9624-9d0c63278e89")
    private Element linkOwner = null;

    @objid ("1e0c97f7-0de4-464f-a460-effea8a56ea0")
    private MObject connectorOwner = null;

    @objid ("06104e9e-d134-4a7a-a245-89c9908ff89f")
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
        GenerationProperties.getInstance().addInfo(message, getObjingElement());
        return null;
    }

    @objid ("d6197858-76be-4945-8e5a-ba275cd5cb03")
    public  OLink(Link element) {
        super(element);
        this.linkOwner = AbstractObjingModelNavigation.getLinkOwner(element);
        this.connectorOwner = AbstractObjingModelNavigation.getConnectorOwner(element);
        
    }

    @objid ("90e3aeec-c2d1-44fa-8a41-25583b2d5bb6")
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

    @objid ("147fed30-0d7e-4d69-b283-3bc74bd0a288")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        if (ecoreElt instanceof org.eclipse.uml2.uml.Connector) {
            setConnectorProperties((org.eclipse.uml2.uml.Connector) ecoreElt);
        } else if (ecoreElt instanceof InstanceSpecification) {
            setName((InstanceSpecification) ecoreElt);
            setBase((InstanceSpecification) ecoreElt);
            if (GenerationProperties.getInstance().isRoundtripEnabled()) {
                ObjingEAnnotation.isLink(ecoreElt);
            }
        }
        
    }

    @objid ("08c3c5a7-37c0-4d26-b939-ce16df0a330d")
    private void setName(InstanceSpecification connector) {
        String eltName = getObjingElement().getName();
        if (AbstractObjingModelNavigation.isNotNullOrEmpty(eltName)) {
            connector.setName(eltName);
        }
        
    }

    @objid ("a528125d-1d14-4a40-9137-f4e6a45c1b26")
    private void setBase(InstanceSpecification connector) {
        Association base = getObjingElement().getModel();
        
        if (base != null) {
            org.eclipse.uml2.uml.Element type = GenerationProperties.getInstance().getMappedElement(base);
            if (type instanceof org.eclipse.uml2.uml.Classifier) {
                connector.getClassifiers().add((org.eclipse.uml2.uml.Classifier) type);
            }
        }
        
    }

    @objid ("a0d79b2e-46bf-4349-90a6-20d375b54e9c")
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

    @objid ("b6dc4ed5-6b77-4d2c-9bf6-998548d134b7")
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

    @objid ("45d7a5b3-1159-450a-b867-e709848b8549")
    private void setConnectorProperties(org.eclipse.uml2.uml.Connector ecoreElt) {
        setBase(ecoreElt);
    }

    @objid ("93316ddb-20b3-4418-930d-b53c7753ae80")
    private void setBase(org.eclipse.uml2.uml.Connector ecoreElt) {
        Element temp = getObjingElement().getModel();
        if (temp != null) {
            org.eclipse.uml2.uml.Element ecoreType = GenerationProperties.getInstance().getMappedElement(temp);
            if (ecoreType instanceof org.eclipse.uml2.uml.Association) {
                ecoreElt.setType((org.eclipse.uml2.uml.Association) ecoreType);
            }
        
        }
        
    }

    @objid ("6118497a-87e2-43a2-afe3-86bf89738dd1")
    @Override
    public Link getObjingElement() {
        return (Link) super.getObjingElement();
    }

}
