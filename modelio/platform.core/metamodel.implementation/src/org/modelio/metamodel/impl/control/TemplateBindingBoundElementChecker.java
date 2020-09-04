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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implements type checking for the TemplateBinding.BoundElement meta-association.
 */
@objid ("e8769c63-ec50-11e1-91c5-002564c97630")
public class TemplateBindingBoundElementChecker extends AbstractDependencyTypeChecker {
    @objid ("0302d540-f027-11e1-8bdc-002564c97630")
    private SmClass artifactID;

    @objid ("0302d542-f027-11e1-8bdc-002564c97630")
    private SmClass classID;

    @objid ("0302d544-f027-11e1-8bdc-002564c97630")
    private SmClass collaborationID;

    @objid ("0302d546-f027-11e1-8bdc-002564c97630")
    private SmClass componentID;

    @objid ("0302d548-f027-11e1-8bdc-002564c97630")
    private SmClass dataTypeID;

    @objid ("0302d54a-f027-11e1-8bdc-002564c97630")
    private SmClass enumerationID;

    @objid ("0302d54c-f027-11e1-8bdc-002564c97630")
    private SmClass interfaceID;

    @objid ("0302d54e-f027-11e1-8bdc-002564c97630")
    private SmClass nodeID;

    @objid ("0302d550-f027-11e1-8bdc-002564c97630")
    private SmClass packageID;

    @objid ("0302d552-f027-11e1-8bdc-002564c97630")
    private SmClass signalID;

    @objid ("0302d554-f027-11e1-8bdc-002564c97630")
    private SmClass useCaseID;

    /**
     * C'tor
     */
    @objid ("3c6b87b5-8597-495c-8bb3-69564024855b")
    public TemplateBindingBoundElementChecker(SmMetamodel mm) {
        // Cached SmClass
        this.artifactID = mm.getMClass(Artifact.class);
        this.classID = mm.getMClass(Class.class);
        this.collaborationID = mm.getMClass(Collaboration.class);
        this.componentID = mm.getMClass(Component.class);
        this.dataTypeID = mm.getMClass(DataType.class);
        this.enumerationID = mm.getMClass(Enumeration.class);
        this.interfaceID = mm.getMClass(Interface.class);
        this.nodeID = mm.getMClass(Node.class);
        this.packageID = mm.getMClass(Package.class);
        this.signalID = mm.getMClass(Signal.class);
        this.useCaseID = mm.getMClass(UseCase.class);
        
        // Direct checker
        register(mm.getMClass(TemplateBinding.class), "BoundElement");
        
        // Symetric checker
        NameSpaceDeclaredChecker symetricChecker = new NameSpaceDeclaredChecker(this);
        symetricChecker.register(mm.getMClass(NameSpace.class), "Declared");
    }

    @objid ("ea01823a-ec50-11e1-91c5-002564c97630")
    @Override
    public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
        if (value != null) {
            SmClass valueTypeID = value.getClassOf();
        
            // A TemplateBinding can only be part of a Package, a Collaboration,
            // a Node, a Signal, an Interface, an Enumeration, a DataType, a
            // Class, a Component, an Artifact or a UseCase.
            return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.collaborationID)
                    || valueTypeID.extEquals(this.nodeID) || valueTypeID.extEquals(this.signalID)
                    || valueTypeID.extEquals(this.interfaceID) || valueTypeID.extEquals(this.enumerationID)
                    || valueTypeID.extEquals(this.dataTypeID) || valueTypeID.extEquals(this.classID)
                    || valueTypeID.extEquals(this.componentID) || valueTypeID.extEquals(this.artifactID) || valueTypeID
                        .extEquals(this.useCaseID)) ? ControlErrorCodes.NO_ERROR
                    : ControlErrorCodes.TEMPLATEBINDING_INVALID_BOUNDELEMENT;
        }
        return ControlErrorCodes.NO_ERROR;
    }

    /**
     * Implements type checking for the NameSpace.Declared meta-association.
     */
    @objid ("ea0308c4-ec50-11e1-91c5-002564c97630")
    static class NameSpaceDeclaredChecker extends AbstractDependencyTypeChecker {
        @objid ("ea0308c7-ec50-11e1-91c5-002564c97630")
         TemplateBindingBoundElementChecker symetricChecker;

        @objid ("ea0308c8-ec50-11e1-91c5-002564c97630")
        public NameSpaceDeclaredChecker(TemplateBindingBoundElementChecker symetricChecker) {
            this.symetricChecker = symetricChecker;
        }

        @objid ("ea0308cb-ec50-11e1-91c5-002564c97630")
        @Override
        public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
            return this.symetricChecker.doCheck(value, obj);
        }

    }

}
