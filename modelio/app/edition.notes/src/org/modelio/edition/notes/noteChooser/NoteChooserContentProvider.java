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

package org.modelio.edition.notes.noteChooser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Default content provider for the note chooser dialog. NoteTypes are displayed in a tree, below their parent Stereotype or Module.
 */
@objid ("26e1241e-186f-11e2-bc4e-002564c97630")
public class NoteChooserContentProvider implements ITreeContentProvider {
    @objid ("42b4c365-1917-11e2-bc4e-002564c97630")
    private IMModelServices modelService;

    @objid ("42b4c366-1917-11e2-bc4e-002564c97630")
    private ModelElement element;

    @objid ("26e1241f-186f-11e2-bc4e-002564c97630")
    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    @objid ("26e12427-186f-11e2-bc4e-002564c97630")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

    @objid ("26e1242a-186f-11e2-bc4e-002564c97630")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to change
    }

    @objid ("26e12430-186f-11e2-bc4e-002564c97630")
    @Override
    public Object[] getChildren(Object parent) {
        Set<Object> ret = new HashSet<>();
        if (parent instanceof NoteChooserModel) {
            NoteChooserModel model = (NoteChooserModel) parent;
            this.element = model.getElement();
        
            List<NoteType> noteTypes = this.modelService.findNoteTypes(".*", ".*", ".*", this.element.getMClass());
            for (NoteType noteType : noteTypes) {
                if (!noteType.isIsHidden() && noteType.getOwnerReference() != null) {
                    ModuleComponent module = noteType.getOwnerReference().getOwnerProfile().getOwnerModule();
                    if (module != null) {
                        ret.add(module);
                    }
                } else {
                    Stereotype ownerStereotype = noteType.getOwnerStereotype();
                    if (!noteType.isIsHidden() && ownerStereotype != null && ownerStereotype.getModule() != null && this.element.isStereotyped(ownerStereotype.getModule().getName(), ownerStereotype.getName())) {
                        ModuleComponent module = noteType.getModule();
                        if (module != null) {
                            ret.add(module);
                        }
                    }
                }
            }
        } else if (parent instanceof ModuleComponent) {
            ModuleComponent moduleComponent = (ModuleComponent) parent;
            MMetamodel mm = moduleComponent.getMClass().getMetamodel();
            for (Profile profile : moduleComponent.getOwnedProfile()) {
                for (MetaclassReference metaclass : profile.getOwnedReference()) {
                    String referencedClassName = metaclass.getReferencedClassName();
                    MClass referencedMClass = mm.getMClass(referencedClassName);
                    if (referencedMClass != null && referencedMClass.getJavaInterface().isAssignableFrom(this.element.getClass())) {
                        for (NoteType noteType : metaclass.getDefinedNoteType()) {
                            if (!noteType.isIsHidden()) {
                                ret.add(noteType);
                            }
                        }
                    }
                }
        
                for (Stereotype stereotype : profile.getDefinedStereotype()) {
                    if (this.element.getExtension().contains(stereotype)) {
                        while (stereotype != null) {
                            for (NoteType noteType : stereotype.getDefinedNoteType()) {
                                if (!noteType.isIsHidden()) {
                                    ret.add(stereotype);
                                    break;
                                }
                            }
                            stereotype = stereotype.getParent();
                        }
                    }
                }
            }
        } else if (parent instanceof Stereotype) {
            Stereotype stereotype = (Stereotype) parent;
            if (stereotype.getModule() != null && this.element.isStereotyped(stereotype.getModule().getName(), stereotype.getName())) {
                while (stereotype != null) {
                    for (NoteType noteType : stereotype.getDefinedNoteType()) {
                        if (!noteType.isIsHidden()) {
                            ret.add(noteType);
                        }
                    }
                    stereotype = stereotype.getParent();
                }
            }
        }
        return ret.toArray();
    }

    @objid ("26e12437-186f-11e2-bc4e-002564c97630")
    @Override
    public Object getParent(Object child) {
        if (child instanceof NoteType) {
            NoteType noteType = (NoteType) child;
            Stereotype ownerStereotype = noteType.getOwnerStereotype();
            if (ownerStereotype != null) {
                return ownerStereotype;
            } else {
                MetaclassReference metaclass = noteType.getOwnerReference();
                return metaclass.getOwnerProfile().getOwnerModule();
            }
        } else if (child instanceof Stereotype) {
            return ((Stereotype) child).getOwner().getOwnerModule();
        } else {
            return null;
        }
    }

    @objid ("26e1243c-186f-11e2-bc4e-002564c97630")
    @Override
    public boolean hasChildren(Object parent) {
        if (parent instanceof NoteChooserModel) {
            return true;
        } else if (parent instanceof ModuleComponent) {
            ModuleComponent moduleComponent = (ModuleComponent) parent;
            MMetamodel mm = moduleComponent.getMClass().getMetamodel();
            for (Profile profile : moduleComponent.getOwnedProfile()) {
                for (MetaclassReference metaclass : profile.getOwnedReference()) {
                    String referencedClassName = metaclass.getReferencedClassName();
                    MClass referencedMClass = mm.getMClass(referencedClassName);
                    if (referencedMClass == null) {
                        EditionNotes.LOG.warning(moduleComponent.getName() + " module has an invalid '" + referencedClassName + "' metaclass reference.");
                    } else if (referencedMClass.getJavaInterface().isAssignableFrom(this.element.getClass())) {
                        for (NoteType noteType : metaclass.getDefinedNoteType()) {
                            if (!noteType.isIsHidden()) {
                                return true;
                            }
                        }
                    }
                }
        
                for (Stereotype stereotype : profile.getDefinedStereotype()) {
                    if (this.element.getExtension().contains(stereotype)) {
                        while (stereotype != null) {
                            for (NoteType noteType : stereotype.getDefinedNoteType()) {
                                if (!noteType.isIsHidden()) {
                                    return true;
                                }
                            }
                            stereotype = stereotype.getParent();
                        }
                    }
                }
            }
        } else if (parent instanceof Stereotype) {
            Stereotype stereotype = (Stereotype) parent;
            while (stereotype != null) {
                for (NoteType noteType : stereotype.getDefinedNoteType()) {
                    if (!noteType.isIsHidden()) {
                        return true;
                    }
                }
                stereotype = stereotype.getParent();
            }
        }
        return false;
    }

    /**
     * Constructor initializing the model service.
     * 
     * @param modelService the model service needed to find elements.
     */
    @objid ("42b4c367-1917-11e2-bc4e-002564c97630")
    public NoteChooserContentProvider(IMModelServices modelService) {
        this.modelService = modelService;
    }

}
