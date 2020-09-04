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
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;

@objid ("df789de6-d0ae-4695-b413-5d4a37f0fde9")
public class OActivityNode extends OModelElement {
    @objid ("06f04fbe-f28a-4d08-8dd8-a97df22c5ea1")
    public OActivityNode(final ActivityNode element) {
        super(element);
    }

    @objid ("f446987c-4e65-45fb-bc19-556e164813fe")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingOwner = this.getObjingElement().getCompositionOwner();
        org.eclipse.uml2.uml.Element ecoreOwner =  GenerationProperties.getInstance().getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (objingOwner instanceof StructuredActivityNode)
                attachToStructuredActivityNode(ecoreElt, ecoreOwner);
            else if (objingOwner instanceof ActivityPartition)
                attachToActivityPartition(ecoreElt, ecoreOwner);
            else if (objingOwner instanceof Activity)
                attachToActivity(ecoreElt, ecoreOwner);
            else if (objingOwner instanceof Clause)
                attachToClause(ecoreElt, ecoreOwner, (Clause) objingOwner);
        }
    }

    @objid ("00dc9388-c6d1-4df0-b22f-2267dc299fc1")
    private void attachToStructuredActivityNode(final org.eclipse.uml2.uml.Element ecoreElt, final org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof org.eclipse.uml2.uml.StructuredActivityNode) {
            org.eclipse.uml2.uml.StructuredActivityNode owner = (org.eclipse.uml2.uml.StructuredActivityNode) ecoreOwner;
            owner.getNodes().add((org.eclipse.uml2.uml.ActivityNode)ecoreElt);
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOwner.getClass().getSimpleName() + ") Not Found");
        }
    }

    @objid ("bfd9011a-145a-440d-aae4-3a21390ed425")
    private void attachToActivityPartition(final org.eclipse.uml2.uml.Element ecoreElt, final org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.ActivityPartition) {
             org.eclipse.uml2.uml.ActivityPartition owner = (org.eclipse.uml2.uml.ActivityPartition) ecoreOwner;
            owner.getNodes().add((org.eclipse.uml2.uml.ActivityNode)ecoreElt);
        
            // Setting composition relation
            Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                    .getEnclosingElement(getObjingElement(), getObjingElement().getMClass().getMetamodel().getMClass(Activity.class));
            if (enclosingActivity != null) {
                org.eclipse.uml2.uml.Element ecoreActivity =  GenerationProperties.getInstance()
                        .getMappedElement(enclosingActivity);
                if (ecoreActivity != null)
                    attachToActivity(ecoreElt, ecoreActivity);
                else{
                    ecoreElt.destroy();
                    throw new NotFoundException("Owner Class not found.");
                }
            }
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOwner.getClass().getSimpleName() + ") Not Found");
        }
    }

    @objid ("53ae8629-74fa-4f46-bf3d-bc31521d375d")
    private void attachToActivity(final org.eclipse.uml2.uml.Element ecoreElt, final org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.Activity) {
             org.eclipse.uml2.uml.Activity owner = (org.eclipse.uml2.uml.Activity) ecoreOwner;
            owner.getNodes().add((org.eclipse.uml2.uml.ActivityNode)ecoreElt);
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOwner.getClass().getSimpleName() + ") Not Found");
        }
    }

    @objid ("ea470137-be74-4b97-9fd3-84fa5e518241")
    private void attachToClause(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Element ecoreOwner, Clause objingOwnerClause) {
        if (ecoreOwner instanceof org.eclipse.uml2.uml.Clause) {
            org.eclipse.uml2.uml.Clause owner =  (org.eclipse.uml2.uml.Clause) ecoreOwner;
        
            // A  org.eclipse.uml2.uml.CentralBufferNode can't have any org.eclipse.uml2.uml.Pin in Objing => use of the
            // "body" role:
            if (ecoreElt instanceof org.eclipse.uml2.uml.ExecutableNode){
                owner.getBodies().add( (org.eclipse.uml2.uml.ExecutableNode) ecoreElt);
            }else{
        
                 ActivityNode objElt = (ActivityNode) getObjingElement();
                 
                 String message = Xmi.I18N.getMessage(" logFile.warning.export.unsupportedOwner.message");
                 String description = Xmi.I18N.getMessage(" logFile.warning.export.unsupportedOwner.description",
                         objElt.getName(),
                         objElt.getClass().getSimpleName(),
                         owner.getClass().getSimpleName());
                 GenerationProperties.getInstance().addWarning(message,  objElt, description);
            }
        
            // Setting composition relation (in org.eclipse.uml2.uml.ConditionalNode):
            ConditionalNode objingConditional = objingOwnerClause.getOwner();
        
            if (objingConditional != null) {
                org.eclipse.uml2.uml.Element ecoreConditional =  GenerationProperties.getInstance()
                        .getMappedElement(objingConditional);
        
                if (ecoreConditional instanceof org.eclipse.uml2.uml.StructuredActivityNode) {
                    ((org.eclipse.uml2.uml.StructuredActivityNode) ecoreConditional).getNodes().add(
                            (org.eclipse.uml2.uml.ActivityNode) ecoreElt);
        
                    return;
                }
            }
        }
        
        // Setting composition relation (in  org.eclipse.uml2.uml.Activity):
        Activity enclosingActivity = (Activity) AbstractObjingModelNavigation.getEnclosingElement(getObjingElement(), getObjingElement().getMClass().getMetamodel().getMClass(Activity.class));
        if (enclosingActivity != null) {
            org.eclipse.uml2.uml.Element ecoreActivity =  GenerationProperties.getInstance()
                    .getMappedElement(enclosingActivity);
            if (ecoreActivity != null){
                attachToActivity(ecoreElt, ecoreActivity);
                Element objElt = getObjingElement();
                String message = Xmi.I18N.getMessage("logFile.warning.unsupportedExport",
                        objElt.getName(), objElt.getClass().getSimpleName());
                String  description = Xmi.I18N.getMessage("logFile.warning.notOwner", objElt.getName());               
                GenerationProperties.getInstance().addWarning(message, objElt, description);
            }else{
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class not found.");
            }
        }
    }

    @objid ("2a2482b8-6529-43e0-a501-dd6fe41bcab5")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    @objid ("f5561a96-bf87-49ea-9e50-01a3c7ab98a6")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}
