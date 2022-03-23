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
package org.modelio.edition.notes.constraintChooser;

import java.util.Collection;
import java.util.HashSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Small data model for the constraint chooser dialog.
 */
@objid ("26d79eff-186f-11e2-bc4e-002564c97630")
class ConstraintChooserModel {
    @objid ("2c8b5147-8395-4e46-bcff-7f48a0dc55c5")
    Collection<Object> roots = new HashSet<>();

    @objid ("2fcbda68-9566-4bd9-b7b1-dffab08eb427")
    public Collection<Object> getRoots() {
        return this.roots;
    }

    @objid ("66cdade2-efff-4028-be6d-a8fab36bea3a")
    public  ConstraintChooserModel(IMModelServices modelService, MMetamodel mm) {
        for (Stereotype stereotype : modelService.findStereotypes(".*", ".*", mm.getMClass(Constraint.class))) {
            if (!stereotype.isIsHidden()) {
                ModuleComponent moduleComponent = stereotype.getOwner().getOwnerModule();
                this.roots.add(moduleComponent);
            }
        }
        
        //TODO Gnii ??!?
        this.roots.add(Constraint.class);
        
    }

}
