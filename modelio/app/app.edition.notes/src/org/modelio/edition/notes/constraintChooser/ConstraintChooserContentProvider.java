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

import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Default content provider for the constraint chooser dialog.
 */
@objid ("26d53d6b-186f-11e2-bc4e-002564c97630")
public class ConstraintChooserContentProvider implements ITreeContentProvider {
    @objid ("868017ba-1924-11e2-bc4e-002564c97630")
    private IMModelServices modelService;

    @objid ("26d53d6c-186f-11e2-bc4e-002564c97630")
    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    @objid ("26d53d74-186f-11e2-bc4e-002564c97630")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

    @objid ("26d53d77-186f-11e2-bc4e-002564c97630")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to change
    }

    @objid ("26d53d7d-186f-11e2-bc4e-002564c97630")
    @Override
    public Object[] getChildren(Object parent) {
        Set<Object> ret = new HashSet<>();
        
        if (parent instanceof ConstraintChooserModel) {
            ConstraintChooserModel m = (ConstraintChooserModel) parent;
            return m.getRoots().toArray();
        } else if (parent instanceof ModuleComponent) {
            ModuleComponent moduleComponent = (ModuleComponent) parent;
            for (Profile profile : moduleComponent.getOwnedProfile()) {
                for (Stereotype stereotype : profile.getDefinedStereotype()) {
                    if (!stereotype.isIsHidden()) {
                        String referencedClassName = stereotype.getBaseClassName();
                        MMetamodel Metamodel = stereotype.getMClass().getMetamodel();
                        MClass referencedMClass = Metamodel.getMClass(referencedClassName);
                        if (referencedMClass.getJavaInterface().isAssignableFrom(Constraint.class)) {
                            ret.add(stereotype);
                        }
                    }
                }
            }
        }
        return ret.toArray();
    }

    @objid ("26d53d84-186f-11e2-bc4e-002564c97630")
    @Override
    public Object getParent(Object child) {
        if (child instanceof Stereotype) {
            return ((Stereotype) child).getOwner().getOwnerModule();
        } else {
            return null;
        }
    }

    @objid ("26d53d89-186f-11e2-bc4e-002564c97630")
    @Override
    public boolean hasChildren(Object parent) {
        return getChildren(parent).length > 0;
    }

    /**
     * Constructor initializing the model service.
     * 
     * @param modelService the model service needed to find elements.
     */
    @objid ("868017bb-1924-11e2-bc4e-002564c97630")
    public ConstraintChooserContentProvider(IMModelServices modelService) {
        this.modelService = modelService;
    }

}
