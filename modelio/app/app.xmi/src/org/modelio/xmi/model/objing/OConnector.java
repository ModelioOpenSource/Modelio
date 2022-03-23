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
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of Connector elements
 * @author ebrosse
 */
@objid ("575c7aaf-28c5-4a0b-bcb9-13685b818ad0")
public class OConnector extends OLink {
    @objid ("62d30b86-6340-4002-9402-c5dcb613d77e")
    private Link objElt = null;

    @objid ("a8066816-7309-4938-9589-c5593415ceb7")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        MObject root = AbstractObjingModelNavigation.getConnectorOwner(this.objElt);
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
     * @param param : the exported Modelio Connector
     */
    @objid ("a207c7b9-2480-4217-8d75-f0beb4459af0")
    public  OConnector(final Link param) {
        super(param);                
        this.objElt = param;
        
    }

    @objid ("015f8257-e3f6-4e61-a730-085229010f2f")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if ((ecoreElt != null) && (ecoreElt instanceof org.eclipse.uml2.uml.Connector)){
            attachConnector(ecoreElt);
        }
        
    }

    @objid ("b5250445-43f3-41d3-b6ef-73523b5b6f47")
    private void attachConnector(org.eclipse.uml2.uml.Element ecoreElt) {
        for (LinkEnd linkEnd : this.objElt.getLinkEnd()) {
                MObject objOwner = linkEnd.getSource();
                if (objOwner != null){
                    org.eclipse.uml2.uml.Element ecoreOwner =  GenerationProperties.getInstance().getMappedElement(objOwner.getCompositionOwner());
                    if (ecoreOwner != null) {
                        // Components and Nodes are handled as Class
                        // (they inherit from Class)
        
                        // Class, Component, Node
                        if (ecoreOwner instanceof org.eclipse.uml2.uml.StructuredClassifier) {
                            org.eclipse.uml2.uml.StructuredClassifier ownerIsClass = (org.eclipse.uml2.uml.StructuredClassifier) ecoreOwner;
                            ownerIsClass.getOwnedConnectors().add((org.eclipse.uml2.uml.Connector)ecoreElt);
                            break;
                        } else if (objOwner instanceof BindableInstance) {
        
                            org.eclipse.uml2.uml.StructuredClassifier ownerIsClass = (org.eclipse.uml2.uml.StructuredClassifier)  GenerationProperties.getInstance().getMappedElement(AbstractObjingModelNavigation
                                    .getBindableInstanceOwner((BindableInstance)objOwner));
                            ownerIsClass.getOwnedConnectors().add((org.eclipse.uml2.uml.Connector)ecoreElt);
                            break;
                        }
                    }
        
            }
        }
        
    }

    @objid ("1992b0eb-ee00-4fdd-9e41-28410d438ed5")
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
