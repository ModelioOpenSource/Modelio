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

package org.modelio.metamodel.impl.control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implements type checking for the ExternDocument.Subject meta-association.
 */
@objid ("8aa662b9-f025-11e1-8bdc-002564c97630")
public class ExternDocumentSubjectChecker extends AbstractDependencyTypeChecker {
    /**
     * C'tor
     */
    @objid ("cd2ce7f3-38b1-4dcc-af51-d9a6323d5dc5")
    public ExternDocumentSubjectChecker(SmMetamodel mm) {
        // Cached SmClass
        // none
        
        // Direct checker
        register(mm.getMClass(AbstractResource.class), "Subject");
        
        // Symetric checker
        ModelElementDocumentChecker symetricChecker = new ModelElementDocumentChecker(this);
        symetricChecker.register(mm.getMClass(ModelElement.class), "Attached");
    }

    @objid ("02f392e6-f027-11e1-8bdc-002564c97630")
    @Override
    public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
        AbstractResource externDocument = (AbstractResource) obj;
        
        if (value != null) {
            SmClass cls = value.getClassOf();
            SmClass baseClass = getBaseClass(externDocument);
            return (baseClass == null || cls.hasBase(baseClass)) ? ControlErrorCodes.NO_ERROR : ControlErrorCodes.NOTE_INCOMPATIBLE_METACLASS;
        }
        return ControlErrorCodes.NO_ERROR;
    }

    @objid ("02f392ef-f027-11e1-8bdc-002564c97630")
    private SmClass getBaseClass(AbstractResource note) {
        ResourceType type = note.getType();
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
     * Implements type checking for the ModelElement.Document meta-association.
     */
    @objid ("02f51986-f027-11e1-8bdc-002564c97630")
    static class ModelElementDocumentChecker extends AbstractDependencyTypeChecker {
        @objid ("02f51989-f027-11e1-8bdc-002564c97630")
         ExternDocumentSubjectChecker symetricChecker;

        @objid ("02f5198a-f027-11e1-8bdc-002564c97630")
        public ModelElementDocumentChecker(ExternDocumentSubjectChecker symetricChecker) {
            this.symetricChecker = symetricChecker;
        }

        @objid ("02f5198d-f027-11e1-8bdc-002564c97630")
        @Override
        public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
            return this.symetricChecker.doCheck(value, obj);
        }

    }

}
