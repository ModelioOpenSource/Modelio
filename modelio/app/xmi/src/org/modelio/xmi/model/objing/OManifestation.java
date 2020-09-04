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
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;

@objid ("395262d0-29b1-482f-b6fa-8a14736b8c01")
public class OManifestation extends OElement implements IOElement {
    @objid ("a12569c3-9596-4bf5-b71b-64c8bbe15a47")
    private Manifestation objingElement;

    @objid ("e6c8a804-864f-4c28-a2c7-71709180ca8f")
    private org.eclipse.uml2.uml.Artifact ecoreArtifact = null;

    @objid ("2fd8b226-497c-4495-b694-8aad8358ed39")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (AbstractObjingModelNavigation.isManifestationMappable(this.objingElement)) {
            return UMLFactory.eINSTANCE.createManifestation();
        } else {
            String message = Xmi.I18N.getMessage("logFile.warning.export.noumlmanifestation", this.objingElement.getName());
            GenerationProperties.getInstance().addWarning(message, this.objingElement);
            return null;
        }
    }

    @objid ("5bc148c4-82cc-4302-822e-33c918d7b527")
    public OManifestation(Manifestation param) {
        super(param);
        this.objingElement = param;
    }

    @objid ("7252135f-4657-4219-bda2-af54ed689df0")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        Artifact objingArtifact = this.objingElement.getOwner();
        
        if (objingArtifact != null) {
            // Gets the ecore owner org.eclipse.uml2.uml.Artifact element:
            this.ecoreArtifact = (org.eclipse.uml2.uml.Artifact) genProp.getMappedElement(objingArtifact);
        
            if (this.ecoreArtifact != null) {
                EList<org.eclipse.uml2.uml.Manifestation> ecoreManifList = this.ecoreArtifact.getManifestations();
                if (!ecoreManifList.contains(ecoreElt))
                    ecoreManifList.add((org.eclipse.uml2.uml.Manifestation)ecoreElt);
            }
        }
    }

    @objid ("cd0142a6-0dcb-42ea-a723-34ac1c72c1ff")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        setName((org.eclipse.uml2.uml.Manifestation) ecoreElt);
        setUtilizedElement((org.eclipse.uml2.uml.Manifestation) ecoreElt);
    }

    @objid ("948e7065-8318-4973-93c6-73ab608e2d28")
    private void setName(org.eclipse.uml2.uml.Manifestation manifestation) {
        if (AbstractObjingModelNavigation.isNotNullOrEmpty(this.objingElement.getName()))
            manifestation.setName(this.objingElement.getName());
    }

    @objid ("20b7ad5f-5e18-4ec7-aecc-fcdb61b78887")
    private void setUtilizedElement(org.eclipse.uml2.uml.Manifestation manifestation) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        ModelElement objingUtilizedElt = this.objingElement.getUtilizedElement();
        
        if (objingUtilizedElt != null) {
            // Gets the ecore utilized element:
            org.eclipse.uml2.uml.Element ecoreUtilizedElt = genProp
            .getMappedElement(objingUtilizedElt);
        
            if (ecoreUtilizedElt instanceof org.eclipse.uml2.uml.PackageableElement) {
                manifestation
                .setUtilizedElement((org.eclipse.uml2.uml.PackageableElement) ecoreUtilizedElt);
            } else if (this.ecoreArtifact != null) {
                this.ecoreArtifact.getManifestations().remove(manifestation);
                /*
                 * if (objingUtilizedElt instanceof NameSpace) { UMLPath path =
                 * new UMLPath((NameSpace)objingUtilizedElt);
                 * ObjingEAnnotation.setSupplier(manifestation,
                 * path.getPath(".")); } else
                 * ObjingEAnnotation.setSupplier(manifestation,
                 * objingUtilizedElt.getName()); EAnnotation objingEA =
                 * ObjingEAnnotation.getOrCreateObjingEAnnotation(ecoreArtifact);
                 * if (objingEA != null)
                 * objingEA.getContents().add(manifestation);
                 */
            }
        }
    }

}
