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
package org.modelio.xmi.model.objing.profile;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.generation.ProfileExportVisitorImpl;
import org.modelio.xmi.util.ProfileUtils;

@objid ("95d2454c-82bf-495d-90bc-dd4d2ec85f48")
public class PExportGeneralization implements IExportProfileElement {
    @objid ("a8bd47eb-4192-48ea-980a-004f39724282")
    private String baseClass = "";

    @objid ("4a04093f-9072-4018-821f-96930006d038")
    private Stereotype stereotype = null;

    @objid ("5e9a234c-3243-442f-9a54-4d3b89271685")
    public  PExportGeneralization(String baseClass, Stereotype stereotype) {
        this.baseClass = baseClass;
        this.stereotype = stereotype;
        
    }

    @objid ("2b8e0769-8cd5-441c-9c1c-27913b2d54bc")
    @Override
    public void accept(ProfileExportVisitorImpl visitor) {
        visitor.visit(this);
    }

    @objid ("66dd00de-3a08-471b-ab33-af50458175b1")
    public void visit() {
        org.eclipse.uml2.uml.Stereotype ecoreStereotype = (org.eclipse.uml2.uml.Stereotype)  GenerationProperties.getInstance().getMappedElement(this.stereotype);
        
        for (String newMetaclassName : ProfileUtils.getEcoreNameClass(this.baseClass)){
            ProfileUtils.addReference(ecoreStereotype, newMetaclassName);
        }
        
    }

}
