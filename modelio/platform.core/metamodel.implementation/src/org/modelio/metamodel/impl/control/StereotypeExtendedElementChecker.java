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
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implements type checking for the Stereotype.ExtendedElement meta-association.
 */
@objid ("91a1fc5b-f025-11e1-8bdc-002564c97630")
public class StereotypeExtendedElementChecker extends AbstractDependencyTypeChecker {
    /**
     * C'tor
     */
    @objid ("11c09960-56cb-40a0-aff0-3f85b20e324c")
    public StereotypeExtendedElementChecker(SmMetamodel mm) {
        // Cached SmClass
        // none
        
        // Direct checker
        register(mm.getMClass(Stereotype.class), "ExtendedElement");
        
        // Symetric checker
        ModelElementExtensionChecker symetricChecker = new ModelElementExtensionChecker(this);
        symetricChecker.register(mm.getMClass(ModelElement.class), "Extension");
    }

    @objid ("02ffc7f8-f027-11e1-8bdc-002564c97630")
    @Override
    public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
        Stereotype stereo = (Stereotype) obj;
        
        if (value != null && !stereo.isShell()) {
            SmClass cls = value.getClassOf();
            SmClass steClass = CoreSession.getSession(stereo).getMetamodel().getMClass(stereo.getBaseClassName());
        
            return (steClass == null || cls.hasBase(steClass)) ? ControlErrorCodes.NO_ERROR : ControlErrorCodes.STEREOTYPE_INCOMPATIBLE_METACLASS;
        }
        return ControlErrorCodes.NO_ERROR;
    }

    /**
     * Implements type checking for the ModelElement.Extension meta-association.
     */
    @objid ("03014e98-f027-11e1-8bdc-002564c97630")
    static class ModelElementExtensionChecker extends AbstractDependencyTypeChecker {
        @objid ("03014e9b-f027-11e1-8bdc-002564c97630")
         StereotypeExtendedElementChecker symetricChecker;

        @objid ("03014e9c-f027-11e1-8bdc-002564c97630")
        public ModelElementExtensionChecker(StereotypeExtendedElementChecker symetricChecker) {
            this.symetricChecker = symetricChecker;
        }

        @objid ("03014e9f-f027-11e1-8bdc-002564c97630")
        @Override
        public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
            return this.symetricChecker.doCheck(value, obj);
        }

    }

}
