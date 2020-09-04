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

package org.modelio.metamodel.impl.control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implements type checking for the Note.Subject meta-association.
 */
@objid ("8e55ebeb-f025-11e1-8bdc-002564c97630")
public class NoteSubjectChecker extends AbstractDependencyTypeChecker {
    /**
     * C'tor
     */
    @objid ("b7f8235b-a3f8-4909-9076-77eba5f11770")
    public NoteSubjectChecker(SmMetamodel mm) {
        // Cached SmClass
        // none
        
        // Direct checker
        register(mm.getMClass(Note.class), "Subject");
        
        // Symetric checker
        ModelElementDescriptorChecker symetricChecker = new ModelElementDescriptorChecker(this);
        symetricChecker.register(mm.getMClass(ModelElement.class), "Descriptor");
    }

    @objid ("02fcbaa8-f027-11e1-8bdc-002564c97630")
    @Override
    public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
        Note note = (Note) obj;
        
        if (value != null) {
            SmClass cls = value.getClassOf();
            SmClass baseClass = getBaseClass(note);
            return (baseClass == null || cls.hasBase(baseClass)) ? ControlErrorCodes.NO_ERROR : ControlErrorCodes.NOTE_INCOMPATIBLE_METACLASS;
        }
        return ControlErrorCodes.NO_ERROR;
    }

    @objid ("02fcbab1-f027-11e1-8bdc-002564c97630")
    private SmClass getBaseClass(Note note) {
        NoteType type = note.getModel();
        if (type != null && !type.isShell()) {
            SmMetamodel mm = CoreSession.getSession(note).getMetamodel();
            if (type.getOwnerStereotype() != null) {
                return mm.getMClass(type.getOwnerStereotype().getBaseClassName());
            } else if (type.getOwnerReference() != null) {
                return mm.getMClass(type.getOwnerReference().getReferencedClassName());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Implements type checking for the ModelElement.Descriptor meta-association.
     */
    @objid ("02fcbac0-f027-11e1-8bdc-002564c97630")
    static class ModelElementDescriptorChecker extends AbstractDependencyTypeChecker {
        @objid ("02fcbac3-f027-11e1-8bdc-002564c97630")
         NoteSubjectChecker symetricChecker;

        @objid ("02fcbac4-f027-11e1-8bdc-002564c97630")
        public ModelElementDescriptorChecker(NoteSubjectChecker symetricChecker) {
            this.symetricChecker = symetricChecker;
        }

        @objid ("02fe414a-f027-11e1-8bdc-002564c97630")
        @Override
        public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
            return this.symetricChecker.doCheck(value, obj);
        }

    }

}
