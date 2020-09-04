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
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of NaryConnector elements
 * @author ebrosse
 */
@objid ("51cf4dcd-9ec3-4d0f-aa46-23320401c2b9")
public class ONaryConnector extends ONaryLink {
    @objid ("ffcd5b22-a005-41d6-b27b-f00293dfaed5")
    private NaryConnector objElt = null;

    @objid ("fa83d9e5-8a8e-4dc3-a5cc-c3f092439a54")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        MObject root = AbstractObjingModelNavigation.getNaryConnectorOwner(this.objElt);
        if (root != null){           
            return UMLFactory.eINSTANCE.createConnector();           
        }
        
        String message = Xmi.I18N.getMessage("logFile.warning.unsupportedExport", 
                this.objElt.getName(), 
                this.objElt.getClass().getSimpleName());
        String  description = Xmi.I18N.getMessage("logFile.warning.notOwner",
                this.objElt.getName());
        GenerationProperties.getInstance().addWarning(message, this.objElt, description);
        return null;
    }

    /**
     * Constructor
     * 
     * @param param : the exported Modelio Connector
     */
    @objid ("f88b3648-3057-4206-9002-eda4fc82bc3b")
    public ONaryConnector(final NaryConnector param) {
        super(param);                
        this.objElt = param;
    }

    @objid ("e38fba38-0233-4fa1-a401-70a867e2a6b9")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if ((ecoreElt != null) && (ecoreElt instanceof org.eclipse.uml2.uml.Connector)){
            attachConnector(ecoreElt);
        }
    }

    @objid ("797d34c8-f83c-4144-8c4d-3e18becb2c3c")
    private void attachConnector(org.eclipse.uml2.uml.Element ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner = null;
        for (NaryLinkEnd linkEnd : this.objElt.getNaryLinkEnd()) {   
            if (ecoreOwner == null) {
                MObject objOwner = linkEnd.getNaryLink().getCompositionOwner();
                ecoreOwner =  GenerationProperties.getInstance().getMappedElement(objOwner);
                if (ecoreOwner != null) {
                    // Components and Nodes are handled as Class
                    // (they inherit from Class)
        
                    // Class, Component, Node
                    if (ecoreOwner instanceof org.eclipse.uml2.uml.StructuredClassifier) {
                        org.eclipse.uml2.uml.StructuredClassifier ownerIsClass = (org.eclipse.uml2.uml.StructuredClassifier) ecoreOwner;
                        ownerIsClass.getOwnedConnectors().add((org.eclipse.uml2.uml.Connector)ecoreElt);
                    } else if (objOwner instanceof BindableInstance) {
        
                        org.eclipse.uml2.uml.StructuredClassifier ownerIsClass = (org.eclipse.uml2.uml.StructuredClassifier)  GenerationProperties.getInstance().getMappedElement(AbstractObjingModelNavigation
                                .getBindableInstanceOwner((BindableInstance)objOwner));
                        ownerIsClass.getOwnedConnectors().add((org.eclipse.uml2.uml.Connector)ecoreElt);
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

    @objid ("ee37003f-0ace-49c6-af2c-6fd0c2897082")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt != null){
            super.setProperties(ecoreElt);
        
            if ((ecoreElt instanceof InstanceSpecification)
                    && (this.objElt instanceof Connector))
                ObjingEAnnotation.setIsConnector(ecoreElt);
        }
    }

}
