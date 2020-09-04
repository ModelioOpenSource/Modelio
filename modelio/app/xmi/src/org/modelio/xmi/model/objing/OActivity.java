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
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class manages the export of IActivty
 * @author ebrosse
 */
@objid ("d210b9e8-7a7f-4156-b669-76619c8f81a7")
public class OActivity extends OModelElement {
    @objid ("ebee7d9a-78eb-4118-b233-86c4c6fe4b9d")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createActivity();
    }

    /**
     * Contructor of the OActivity.
     * It takes the exported Modelio  org.eclipse.uml2.uml.Activity as parameter
     * 
     * @param element : the exported Modelio  org.eclipse.uml2.uml.Activity
     */
    @objid ("8f3595a6-644b-4b59-932c-1abdae6386d4")
    public OActivity(final Activity element) {
        super(element);
    }

    @objid ("16f1b1d2-8423-4627-8ed1-7d21dc73075a")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner =  GenerationProperties.getInstance().getMappedElement(getObjingElement().getCompositionOwner());
        
        if (ecoreOwner != null) {
            if ( ecoreOwner instanceof org.eclipse.uml2.uml.Namespace)
                 attachToNameSpace(ecoreElt, (org.eclipse.uml2.uml.Namespace) ecoreOwner);
            else if (ecoreOwner instanceof  org.eclipse.uml2.uml.Operation)
                attachToOperation(ecoreElt,  (org.eclipse.uml2.uml.Operation) ecoreOwner);
        }
    }

    @objid ("a48744af-5412-41a7-9d3b-758e1bdbca52")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setReadOnly((org.eclipse.uml2.uml.Activity) ecoreElt);
        setSingleExecution((org.eclipse.uml2.uml.Activity) ecoreElt);
        setReentrant((org.eclipse.uml2.uml.Activity) ecoreElt);
    }

    @objid ("3c6d8d6f-a40c-443b-9f61-846494db1c58")
    private void attachToNameSpace(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Namespace ecoreOwner) {
        String ownerId = ObjingEAnnotation.getOwner(ecoreElt);
        if ((ownerId == null) || (ownerId.equals(""))){
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
            org.eclipse.uml2.uml.Package ownerIsPkg = (org.eclipse.uml2.uml.Package) ecoreOwner;
            ownerIsPkg.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
        } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component) {
            org.eclipse.uml2.uml.Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
            ownerIsCmpnt.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
        } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Class) {
            org.eclipse.uml2.uml.Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
            ownerIsClass.getOwnedBehaviors().add((org.eclipse.uml2.uml.Activity)ecoreElt);
        } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
            org.eclipse.uml2.uml.Interface ownerIsItf = (org.eclipse.uml2.uml.Interface) ecoreOwner;
            ownerIsItf.getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
        } else if (ecoreOwner instanceof org.eclipse.uml2.uml. BehavioredClassifier) {
            org.eclipse.uml2.uml. BehavioredClassifier ownerIsBehavioredClassifier = (org.eclipse.uml2.uml.BehavioredClassifier) ecoreOwner;
            ownerIsBehavioredClassifier.getOwnedBehaviors().add((org.eclipse.uml2.uml.Behavior)ecoreElt);
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOwner.getClass().getSimpleName() + ") Not Found");
        }
        }else{
            ObjingEAnnotation.deleteOwner(ecoreElt);
        }
    }

    @objid ("a2fb1a8f-42c8-4702-bcb3-1a68aeda9228")
    private void attachToOperation(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Operation ecoreOwner) {
        Classifier objingOpOwner = getObjingElement().getOwnerOperation().getOwner();
        String message =   Xmi.I18N.getMessage("logFile.warning.moving.activity",
                                                    getObjingElement().getName(), ecoreOwner.getName(),
                                                    objingOpOwner.getName());
        GenerationProperties genProp = GenerationProperties.getInstance();
        genProp.addWarning(message, getObjingElement());
        
        org.eclipse.uml2.uml.Element ecoreOpOwner = genProp.getMappedElement(objingOpOwner);
        
        if (ecoreOpOwner instanceof org.eclipse.uml2.uml.Namespace) {
            attachToNameSpace(ecoreElt, (org.eclipse.uml2.uml.Namespace) ecoreOpOwner);
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOpOwner.getClass().getSimpleName() + ") Not Found");
        }
    }

    @objid ("cc473345-326c-44db-9ec7-ad96d559a915")
    private void setReadOnly(org.eclipse.uml2.uml.Activity activity) {
        activity.setIsReadOnly(getObjingElement().isIsReadOnly());
    }

    @objid ("4bcc53c8-c057-4984-89fe-59ab948bbc98")
    private void setSingleExecution(org.eclipse.uml2.uml.Activity activity) {
        activity.setIsSingleExecution(getObjingElement().isIsSingleExecution());
    }

    @objid ("3cbaa376-52c8-41e9-8e09-5c540c6a8504")
    private void setReentrant(org.eclipse.uml2.uml.Activity activity) {
        activity.setIsReentrant(getObjingElement().isIsReentrant());
    }

    @objid ("88bf3e00-ad13-4944-b87b-dc67961f3853")
    @Override
    public Activity getObjingElement() {
        return (Activity) super.getObjingElement();
    }

}
