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
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implements type checking for the TaggedValue.Annoted meta-association.
 */
@objid ("9470eb65-f025-11e1-8bdc-002564c97630")
public class TaggedValueAnnotedChecker extends AbstractDependencyTypeChecker {
    /**
     * C'tor
     */
    @objid ("f2d63946-bfd8-49e7-bea5-c429708e2311")
    public TaggedValueAnnotedChecker(SmMetamodel mm) {
        // Cached SmClass
        // none
        
        // Direct checker
        register(mm.getMClass(TaggedValue.class), "Annoted");
        
        // Symetric checker
        ModelElementTagChecker symetricChecker = new ModelElementTagChecker(this);
        symetricChecker.register(mm.getMClass(ModelElement.class), "Tag");
    }

    @objid ("03014eac-f027-11e1-8bdc-002564c97630")
    @Override
    public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
        TaggedValue taggedValue = (TaggedValue) obj;
        
        if (value != null) {
            SmClass cls = value.getClassOf();
            SmClass baseClass = getBaseClass(taggedValue);
            return (baseClass == null || cls.hasBase(baseClass)) ? ControlErrorCodes.NO_ERROR : ControlErrorCodes.NOTE_INCOMPATIBLE_METACLASS;
        }
        return ControlErrorCodes.NO_ERROR;
    }

    @objid ("03014eb5-f027-11e1-8bdc-002564c97630")
    private SmClass getBaseClass(TaggedValue taggedValue) {
        TagType type = taggedValue.getDefinition();
        if (type != null && !type.isShell()) {
            SmMetamodel mm = CoreSession.getSession(taggedValue).getMetamodel();
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
     * Implements type checking for the ModelElement.Tag meta-association.
     */
    @objid ("03014ec4-f027-11e1-8bdc-002564c97630")
    static class ModelElementTagChecker extends AbstractDependencyTypeChecker {
        @objid ("0302d530-f027-11e1-8bdc-002564c97630")
         TaggedValueAnnotedChecker symetricChecker;

        @objid ("0302d531-f027-11e1-8bdc-002564c97630")
        public ModelElementTagChecker(TaggedValueAnnotedChecker symetricChecker) {
            this.symetricChecker = symetricChecker;
        }

        @objid ("0302d534-f027-11e1-8bdc-002564c97630")
        @Override
        public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
            return this.symetricChecker.doCheck(value, obj);
        }

    }

}
