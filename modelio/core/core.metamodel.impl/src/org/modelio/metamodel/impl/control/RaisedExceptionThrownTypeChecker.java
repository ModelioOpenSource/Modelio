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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implements type checking for the RaisedException.ThrownType meta-association.
 */
@objid ("b273d66a-ec6f-11e1-91c5-002564c97630")
public class RaisedExceptionThrownTypeChecker extends AbstractDependencyTypeChecker {
    @objid ("02ffc7f0-f027-11e1-8bdc-002564c97630")
    private final SmClass classID;

    @objid ("02ffc7f2-f027-11e1-8bdc-002564c97630")
    private final SmClass componentID;

    @objid ("02ffc7ea-f027-11e1-8bdc-002564c97630")
    private final SmClass dataTypeID;

    @objid ("02ffc7ee-f027-11e1-8bdc-002564c97630")
    private final SmClass enumerationID;

    @objid ("02ffc7ec-f027-11e1-8bdc-002564c97630")
    private final SmClass interfaceID;

    @objid ("02ffc7f4-f027-11e1-8bdc-002564c97630")
    private final SmClass templateParameterID;

    /**
     * C'tor
     */
    @objid ("bf20913b-3d79-4983-8da7-7a0f5a9716fb")
    public RaisedExceptionThrownTypeChecker(SmMetamodel mm) {
        // Cached SmClass
        this.enumerationID = mm.getMClass(Enumeration.class);
        this.templateParameterID = mm.getMClass(TemplateParameter.class);
        this.dataTypeID = mm.getMClass(DataType.class);
        this.componentID = mm.getMClass(Component.class);
        this.classID = mm.getMClass(Class.class);
        this.interfaceID = mm.getMClass(Interface.class);
        
        // Direct checker
        register(mm.getMClass(RaisedException.class), "ThrownType");
        
        // Symetric checker
        ClassifierThrowingChecker symetricChecker = new ClassifierThrowingChecker(this);
        symetricChecker.register(mm.getMClass(Classifier.class), "Throwing");
    }

    @objid ("b3b12278-ec6f-11e1-91c5-002564c97630")
    @Override
    public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
        if (value != null) {
            SmClass valueTypeID = value.getClassOf();
        
            // A RaisedException type must be to a Class, a Component, an
            // Interface, a DataType, a TemplateParameter or an Enumeration.
            return (valueTypeID.extEquals(this.classID) || valueTypeID.extEquals(this.componentID)
                    || valueTypeID.extEquals(this.interfaceID) || valueTypeID.extEquals(this.enumerationID)
                    || valueTypeID.extEquals(this.dataTypeID) || valueTypeID.extEquals(this.templateParameterID)) ? ControlErrorCodes.NO_ERROR
                    : ControlErrorCodes.RAISEDEXCEPTION_INVALID_THROWNTYPE;
        }
        return ControlErrorCodes.NO_ERROR;
    }

    /**
     * Implements type checking for the Classifier.Throwing meta-association.
     */
    @objid ("b3b12284-ec6f-11e1-91c5-002564c97630")
    static class ClassifierThrowingChecker extends AbstractDependencyTypeChecker {
        @objid ("b3b12287-ec6f-11e1-91c5-002564c97630")
         RaisedExceptionThrownTypeChecker symetricChecker;

        @objid ("b3b2a898-ec6f-11e1-91c5-002564c97630")
        public ClassifierThrowingChecker(RaisedExceptionThrownTypeChecker symetricChecker) {
            this.symetricChecker = symetricChecker;
        }

        @objid ("b3b2a89b-ec6f-11e1-91c5-002564c97630")
        @Override
        public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
            return this.symetricChecker.doCheck(value, obj);
        }

    }

}
