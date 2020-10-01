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
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implements type checking for the BindableInstance.Owner meta-association.
 */
@objid ("928b4880-ec6f-11e1-91c5-002564c97630")
public class BindableInstanceInternalOwnerChecker extends AbstractDependencyTypeChecker {
    @objid ("02f51999-f027-11e1-8bdc-002564c97630")
    private final SmClass bindableInstanceID;

    @objid ("02f5199b-f027-11e1-8bdc-002564c97630")
    private final SmClass interfaceID;

    /**
     * C'tor
     */
    @objid ("d6ded598-8849-43d3-9b41-6b0ed81e40e8")
    public BindableInstanceInternalOwnerChecker(SmMetamodel mm) {
        // Cached SmClass
        this.bindableInstanceID = mm.getMClass(BindableInstance.class);
        this.interfaceID = mm.getMClass(Interface.class);
        
        // Direct checker
        this.register(this.bindableInstanceID, "InternalOwner");
        
        // Symetric checker
        SmClass classifierID = mm.getMClass(Classifier.class);
        ClassifierInternalStructureChecker symetricChecker = new ClassifierInternalStructureChecker(this);
        symetricChecker.register(classifierID, "InternalStructure");
    }

    @objid ("93e51c0c-ec6f-11e1-91c5-002564c97630")
    @Override
    public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
        if (value != null) {
            SmClass valueTypeID = value.getClassOf();
            return (valueTypeID.extEquals(this.interfaceID)) ? ControlErrorCodes.BINDABLEINSTANCE_INVALID_INTERNALOWNER
                    : ControlErrorCodes.NO_ERROR;
        }
        return ControlErrorCodes.NO_ERROR;
    }

    /**
     * Implements type checking for the Classifier.InternalStructure meta-association.
     */
    @objid ("93e6a234-ec6f-11e1-91c5-002564c97630")
    static class ClassifierInternalStructureChecker extends AbstractDependencyTypeChecker {
        @objid ("93e6a237-ec6f-11e1-91c5-002564c97630")
        private BindableInstanceInternalOwnerChecker symetricChecker;

        @objid ("93e9ae78-ec6f-11e1-91c5-002564c97630")
        public ClassifierInternalStructureChecker(BindableInstanceInternalOwnerChecker symetricChecker) {
            this.symetricChecker = symetricChecker;
        }

        @objid ("93e9ae7b-ec6f-11e1-91c5-002564c97630")
        @Override
        public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
            return this.symetricChecker.doCheck(value, obj);
        }

    }

}
