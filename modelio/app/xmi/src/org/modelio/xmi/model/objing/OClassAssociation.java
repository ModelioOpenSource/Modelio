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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;

@objid ("38a87f21-3b82-4ed2-b04c-e5162b48ebfc")
public class OClassAssociation extends OModelElement {
    @objid ("f4f55fdf-efcc-489f-9a5b-f3c5dc8b6601")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        // The Ecore org.eclipse.uml2.uml.AssociationClass can be created when parsing the related
        // Ijing org.eclipse.uml2.uml.Association, Class or
        // current ClassAssociation.
        return getOrCreateEcoreAssociationClass();
    }

    @objid ("1743a2ba-b974-47b8-8874-ffd57094d153")
    private org.eclipse.uml2.uml.AssociationClass getOrCreateEcoreAssociationClass() {
        GenerationProperties genProp = GenerationProperties.getInstance();
        // Gets or creates the Ecore org.eclipse.uml2.uml.AssociationClass:
        return (org.eclipse.uml2.uml.AssociationClass) genProp
                                        .getMappedElement(getObjingElement());
    }

    @objid ("d6c13dc6-fa9b-48cd-8075-5fbdf6c209bc")
    public OClassAssociation(ClassAssociation param) {
        super(param);
    }

    @objid ("eb0656f9-c321-42db-b541-c0d0cbf44e8f")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (AbstractObjingModelNavigation.isRepresentedByAUniqueClass(getObjingElement())) {
            GenerationProperties genProp = GenerationProperties.getInstance();
        
            Association objingAssoc = getObjingElement().getAssociationPart();
        
            if (objingAssoc != null) {
                // The owner of the org.eclipse.uml2.uml.AssociationClass will be the nearest owner
                // Package of one of the connected org.eclipse.uml2.uml.Classifiers.
                // The owner Package will be preferentially the nearest Package
                // of a navigable end.
                Classifier ownerClassifier = null;
                Classifier ownerNavigableClassifier = null;
        
                for (AssociationEnd end : objingAssoc.getEnd()) {
                    if (end.isNavigable()) {
                        ownerNavigableClassifier = end.getOwner();
                    }
                    ownerClassifier = end.getOwner();
                    Property ecoreRole = (Property) genProp
                            .getMappedElement(end);
                    if (ecoreRole != null)
                        ecoreRole.setAssociation((org.eclipse.uml2.uml.AssociationClass) ecoreElt);
                }
        
                if (ownerNavigableClassifier != null)
                    ownerClassifier = ownerNavigableClassifier;
        
                if (ownerClassifier != null) {
                    Package objingPkg = AbstractObjingModelNavigation
                            .getNearestPackage(ownerClassifier);
        
                    org.eclipse.uml2.uml.Package ecorePkg = (org.eclipse.uml2.uml.Package) genProp
                            .getMappedElement(objingPkg);
        
                    if (ecorePkg != null) {
                        ecorePkg.getPackagedElements().add((org.eclipse.uml2.uml.PackageableElement)ecoreElt);
                    }
                }
            }
        }
    }

    @objid ("54718478-9540-4230-ac7c-33116ffa6b01")
    @Override
    public ClassAssociation getObjingElement() {
        return (ClassAssociation) super.getObjingElement();
    }

}
