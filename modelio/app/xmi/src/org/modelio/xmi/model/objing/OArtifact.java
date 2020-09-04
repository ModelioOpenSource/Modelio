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

package org.modelio.xmi.model.objing;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.IModelerModuleStereotypes;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.XMIProperties;

@objid ("76472803-064d-4d70-b9ad-2415fc160c28")
public class OArtifact extends ONameSpace {
    @objid ("d59af07b-6bb7-416c-998b-cf8c27326438")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (getObjingElement().isStereotyped(XMIProperties.modelerModuleName,IModelerModuleStereotypes.UML2DEPLOYMENTSPECIFICATION)){
            return UMLFactory.eINSTANCE.createDeploymentSpecification();
        }else{
            return UMLFactory.eINSTANCE.createArtifact();
        }
    }

    @objid ("51c0fa88-14bd-4205-9620-abc5c6322239")
    public OArtifact(Artifact param) {
        super(param);
    }

    @objid ("9fd48579-a243-43b1-a41c-92842df3b936")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        ModelTree objingOwner = getObjingElement().getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = genProp.getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
                org.eclipse.uml2.uml.Package ownerIsPkg = (org.eclipse.uml2.uml.Package) ecoreOwner;
                ownerIsPkg.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component) {
                org.eclipse.uml2.uml.Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
                ownerIsCmpnt.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Class) {
                org.eclipse.uml2.uml.Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
                ownerIsClass.getNestedClassifiers().add( (org.eclipse.uml2.uml.Classifier)ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateParameter) {
                org.eclipse.uml2.uml.TemplateParameter ownerIsTemplateParameter = (org.eclipse.uml2.uml.TemplateParameter) ecoreOwner;
                ( (org.eclipse.uml2.uml.Classifier)ecoreElt).setOwningTemplateParameter(ownerIsTemplateParameter);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Artifact) {
                org.eclipse.uml2.uml.Artifact ownerIsArtifact = (org.eclipse.uml2.uml.Artifact) ecoreOwner;
                ownerIsArtifact.getNestedArtifacts().add((org.eclipse.uml2.uml.Artifact)ecoreElt);
        
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
    }

    @objid ("0fa2579b-5027-48bf-83ea-f32a30434959")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setFileName((org.eclipse.uml2.uml.Artifact) ecoreElt);
        setUtilized((org.eclipse.uml2.uml.Artifact) ecoreElt);
    }

    @objid ("bc03b1e8-8b55-4a65-8f1e-3cc813b9ecdc")
    private void setFileName(org.eclipse.uml2.uml.Artifact artifact) {
        String fileName = getObjingElement().getFileName();
        if (!"".equals(fileName))
            artifact.setFileName(fileName);
    }

    @objid ("d09810c6-7d0d-4141-80fa-3d93a32f477e")
    private void setUtilized(org.eclipse.uml2.uml.Artifact artifact) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        List<org.eclipse.uml2.uml.Manifestation> ecoreManifList = new ArrayList<>();
        
        for (Manifestation objingManif : getObjingElement().getUtilized()) {
            org.eclipse.uml2.uml.Manifestation ecoreManif = (org.eclipse.uml2.uml.Manifestation) genProp
            .getMappedElement(objingManif);
        
            if (ecoreManif != null && !ecoreManifList.contains(ecoreManif)) {
                ecoreManifList.add(ecoreManif);
            }
        }
        
        if (ecoreManifList.size() > 0)
            artifact.getManifestations().addAll(ecoreManifList);
    }

    @objid ("841d4c93-031c-46f1-b6a1-eff768ad52aa")
    @Override
    public Artifact getObjingElement() {
        return (Artifact) super.getObjingElement();
    }

}
