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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.generation.ProfileExportVisitorImpl;
import org.modelio.xmi.model.objing.profile.PExportProfile;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ProfileUtils;

/**
 * This class handle the export of Modelio Element
 * @author ebrosse
 */
@objid ("c558da07-2032-48cc-8813-16c9fb9e24cf")
public class OElement {
    @objid ("179bfc16-ca8a-4fa1-a63b-3d644a1d32a9")
    private Element objingElt = null;

    /**
     * return the exported Modelio Element
     * 
     * @return the Element
     */
    @objid ("62254bf6-3e93-4edd-8f11-36fb5c541348")
    public Element getObjingElement() {
        return this.objingElt;
    }

    /**
     * return the Modelio identifier of the current Modelio Element
     * 
     * @return the identifier
     */
    @objid ("f523ab67-da60-444d-8604-7bf10742b3b1")
    public String getObjingID() {
        return this.objingElt.getUuid().toString();
    }

    /**
     * Constructor with the exported Modelio IElemnt as parameter
     * 
     * @param element : the exported Modelio Element
     */
    @objid ("38a89a94-5687-4acc-9768-e2896ff78ac1")
    public OElement(final Element element) {
        this.objingElt = element;
        
        if (element instanceof ModelElement){
        
            ModelElement modelElement = (ModelElement) element;
        
            for( Stereotype obStereotype : modelElement.getExtension()){
                if (AbstractObjingModelNavigation.mustBeExported(obStereotype)) {
                    exportStereotypeProfile(obStereotype);
                    GenerationProperties.getInstance().addStereotypeExported(modelElement);
                }
            }
        
            for( TaggedValue obTaggedValue : modelElement.getTag()){
                MetaclassReference reference = obTaggedValue.getDefinition().getOwnerReference();
                if ((reference != null) && (AbstractObjingModelNavigation.mustBeExported(reference)) ){
                    exportReferenceProfile(reference);
                    GenerationProperties.getInstance().addStereotypeExported(modelElement);
                }
            }
        }
    }

    @objid ("961e2670-315d-46b7-83c3-36ac8968dd86")
    private void exportStereotypeProfile(Stereotype stereotype) {
        exportProfile(stereotype.getOwner());
    }

    @objid ("c29fda8b-cbd6-4be8-887f-c6c4bb2728a1")
    private void exportReferenceProfile(MetaclassReference reference) {
        exportProfile(reference.getOwnerProfile());
    }

    /**
     * Return the name of the corresponding Ecore class
     * 
     * @return the name
     */
    @objid ("6091063d-cff7-427a-89d0-f879f55cfedd")
    public List<String> getEcoreClassName() {
        List<String> result = new ArrayList<>();
        result.add("Element");
        return result;
    }

    @objid ("c314194d-bdfb-426f-8ce3-3ed0a0bad4ec")
    private void exportProfile(Profile profile) {
        Object ecoreProfile =  GenerationProperties.getInstance().getMappedElement(profile);
        
        if (ecoreProfile == null){
            ProfileExportVisitorImpl profileVisitor = new ProfileExportVisitorImpl();
            PExportProfile pprofile = new PExportProfile(ProfileUtils.getProfileRoot(profile));
            profileVisitor.visit(pprofile);
        }
    }

}
