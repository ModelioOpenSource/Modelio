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
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("62154cad-ecea-42b6-a4d5-3274cb7ba67d")
public class OInformationFlow extends OModelElement {
    @objid ("028a681a-2750-4b13-afbc-9909419eb5df")
     InformationFlow objingElement;

    @objid ("b04b9658-f150-4374-a691-7984193a0978")
    private GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("4635bc9a-e02f-4b1f-bfc4-b79831728026")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createInformationFlow();
    }

    @objid ("434b0e2f-34b5-4071-a561-0cb227393db4")
    public OInformationFlow(InformationFlow element) {
        super(element);
        this.objingElement = element;
    }

    @objid ("4a3b803d-d6fa-4417-b1f4-f1069d3c188c")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        NameSpace objingOwner =  this.objingElement.getOwner();
        
        if (objingOwner != null) {
            org.eclipse.uml2.uml.Element ecoreOwner =  this.genProp.getMappedElement(objingOwner);
        
            if (ecoreOwner != null) {
                org.eclipse.uml2.uml.Package nearestPkg = null;
                if (ecoreOwner instanceof org.eclipse.uml2.uml.Package)
                    nearestPkg = (org.eclipse.uml2.uml.Package)ecoreOwner;
                else
                    nearestPkg = ecoreOwner.getNearestPackage();
        
                if (nearestPkg != null) {
                    nearestPkg.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
                }
            }
        }
    }

    @objid ("386eb18e-54ef-467b-b1fb-31790430b573")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setInformationSource((org.eclipse.uml2.uml.InformationFlow)ecoreElt);
        setInformationTarget((org.eclipse.uml2.uml.InformationFlow)ecoreElt);
        setRealizingMessage((org.eclipse.uml2.uml.InformationFlow)ecoreElt);
        setRealizingActivityEdge((org.eclipse.uml2.uml.InformationFlow)ecoreElt);
        setConveyed((org.eclipse.uml2.uml.InformationFlow)ecoreElt);
        //        setRealizingConnector((org.eclipse.uml2.uml.InformationFlow)ecoreElt);
        setRealization((org.eclipse.uml2.uml.InformationFlow)ecoreElt);
    }

    @objid ("2a02ac17-3e4f-4bdf-a9f6-0c48abc00e56")
    private void setInformationSource(org.eclipse.uml2.uml.InformationFlow flow) {
        for (ModelElement objingSource :  this.objingElement.getInformationSource()) {
            org.eclipse.uml2.uml.Element ecoreSource =  this.genProp.getMappedElement(objingSource);
            if (ecoreSource instanceof org.eclipse.uml2.uml.NamedElement) {
                flow.getInformationSources().add((org.eclipse.uml2.uml.NamedElement)ecoreSource);
            }
        }
    }

    @objid ("a27192f3-0037-4df6-a011-029138a57d19")
    private void setInformationTarget(org.eclipse.uml2.uml.InformationFlow flow) {
        for (ModelElement objingTarget :  this.objingElement.getInformationTarget()) {
            org.eclipse.uml2.uml.Element ecoreTarget =  this.genProp.getMappedElement(objingTarget);
            if (ecoreTarget instanceof org.eclipse.uml2.uml.NamedElement) {
                flow.getInformationTargets().add((org.eclipse.uml2.uml.NamedElement)ecoreTarget);
            }
        }
    }

    @objid ("f2ce3c2a-797c-42e7-82f2-c56058eba010")
    private void setRealizingMessage(org.eclipse.uml2.uml.InformationFlow flow) {
        for (Message objingMsg :  this.objingElement.getRealizingMessage()) {
            org.eclipse.uml2.uml.Element ecoreMsg =  this.genProp.getMappedElement(objingMsg);
            if (ecoreMsg instanceof org.eclipse.uml2.uml.Message)
                flow.getRealizingMessages().add((org.eclipse.uml2.uml.Message)ecoreMsg);
        }
    }

    @objid ("132fab02-14ac-442b-b259-cb4ffcecc30b")
    private void setRealizingActivityEdge(org.eclipse.uml2.uml.InformationFlow flow) {
        for (ActivityEdge objingEdge :  this.objingElement.getRealizingActivityEdge()) {
            org.eclipse.uml2.uml.Element ecoreEdge =  this.genProp.getMappedElement(objingEdge);
            if (ecoreEdge instanceof  org.eclipse.uml2.uml.ActivityEdge)
                flow.getRealizingActivityEdges().add((org.eclipse.uml2.uml.ActivityEdge)ecoreEdge);
        }
    }

    @objid ("174e0f7f-883d-4bc4-a25e-6b2da9551545")
    private void setConveyed(org.eclipse.uml2.uml.InformationFlow flow) {
        for (Classifier objingConveyed :  this.objingElement.getConveyed()) {
            org.eclipse.uml2.uml.Element ecoreConveyed =  this.genProp.getMappedElement(objingConveyed);
            if (ecoreConveyed instanceof org.eclipse.uml2.uml.Classifier)
                flow.getConveyeds().add( (org.eclipse.uml2.uml.Classifier)ecoreConveyed);    
        }
    }

    @objid ("a141fcb0-14d9-4eaa-bcd4-325a01cf82c9")
    private void setRealization(org.eclipse.uml2.uml.InformationFlow flow) {
        AssociationEnd realizingAssocEnd = this.objingElement.getChannel();
        if (realizingAssocEnd != null){
            Association realizingAssoc =  realizingAssocEnd.getAssociation();
            org.eclipse.uml2.uml.Element realization =  this.genProp.getMappedElement(realizingAssoc);
            if (realization instanceof org.eclipse.uml2.uml.Relationship)
                flow.getRealizations().add((org.eclipse.uml2.uml.Relationship)realization);
        }
    }

}
