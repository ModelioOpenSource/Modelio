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
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implements type checking for the ModelTree.Owner meta-association.
 */
@objid ("aceb41f0-ca6c-11e1-b155-002564c97630")
public class ModelTreeOwnerChecker extends AbstractDependencyTypeChecker {
    @objid ("02f20c39-f027-11e1-8bdc-002564c97630")
    private SmClass actorID;

    @objid ("02f20c3b-f027-11e1-8bdc-002564c97630")
    private SmClass artifactID;

    @objid ("02f20c3d-f027-11e1-8bdc-002564c97630")
    private SmClass classID;

    @objid ("02f20c3f-f027-11e1-8bdc-002564c97630")
    private SmClass collaborationID;

    @objid ("02f20c41-f027-11e1-8bdc-002564c97630")
    private SmClass componentID;

    @objid ("02f20c43-f027-11e1-8bdc-002564c97630")
    private SmClass dataTypeID;

    @objid ("02f20c45-f027-11e1-8bdc-002564c97630")
    private SmClass enumerationID;

    @objid ("02f20c47-f027-11e1-8bdc-002564c97630")
    private SmClass interfaceID;

    @objid ("02f20c49-f027-11e1-8bdc-002564c97630")
    private SmClass moduleID;

    @objid ("02f20c4b-f027-11e1-8bdc-002564c97630")
    private SmClass nodeID;

    @objid ("02f20c4d-f027-11e1-8bdc-002564c97630")
    private SmClass packageID;

    @objid ("02f392da-f027-11e1-8bdc-002564c97630")
    private SmClass profileID;

    @objid ("02f392dc-f027-11e1-8bdc-002564c97630")
    private SmClass projectID;

    @objid ("02f392de-f027-11e1-8bdc-002564c97630")
    private SmClass signalID;

    @objid ("02f392e0-f027-11e1-8bdc-002564c97630")
    private SmClass templateParameterID;

    @objid ("02f392e2-f027-11e1-8bdc-002564c97630")
    private SmClass useCaseID;

    @objid ("1b5239b0-ec39-11e1-91c5-002564c97630")
    @Override
    public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
        if (value != null) {
            // An element must not own himself
            if (obj.equals(value)) {
                return ControlErrorCodes.MODELTREE_SELF_OWNER;
            }
        
            // Check specific metaclass cases
            SmClass valueTypeID = value.getClassOf();
            if (obj.getClassOf() == this.actorID) {
                // An Actor must belong to a Package, a Class, a Component or an
                // Interface.
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.classID)
                        || valueTypeID.extEquals(this.componentID) || valueTypeID.extEquals(this.interfaceID)) ? ControlErrorCodes.NO_ERROR
                        : ControlErrorCodes.ACTOR_INVALID_OWNER;
            } else if (obj.getClassOf() == this.artifactID) {
                // An Artifact must belong to a Package, a Module, a Profile, an
                // Artifact or a Component.
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.moduleID)
                        || valueTypeID.extEquals(this.profileID) || valueTypeID.extEquals(this.artifactID) || valueTypeID
                            .extEquals(this.componentID)) ? ControlErrorCodes.NO_ERROR : ControlErrorCodes.ARTIFACT_INVALID_OWNER;
            } else if (obj.getClassOf() == this.classID) {
                // A Class must belong to a Package, a Class, a Component, an Enumeration or an Interface.
        
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.classID)
                        || valueTypeID.extEquals(this.componentID) || valueTypeID.extEquals(this.interfaceID) || valueTypeID
                            .extEquals(this.enumerationID)) ? ControlErrorCodes.NO_ERROR : ControlErrorCodes.CLASS_INVALID_OWNER;
        
            } else if (obj.getClassOf() == this.collaborationID) {
                // A Collaboration must be owned by a Package, a Class, a
                // Component, an Interface, a UseCase, a Signal or an Actor.
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.classID)
                        || valueTypeID.extEquals(this.componentID) || valueTypeID.extEquals(this.interfaceID)
                        || valueTypeID.extEquals(this.useCaseID) || valueTypeID.extEquals(this.signalID) || valueTypeID
                            .extEquals(this.actorID)) ? ControlErrorCodes.NO_ERROR : ControlErrorCodes.COLLABORATION_INVALID_OWNER;
            } else if (obj.getClassOf() == this.componentID) {
                // A Component must belong to a Package, a Component or a Class.
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.componentID) || valueTypeID
                        .extEquals(this.classID)) ? ControlErrorCodes.NO_ERROR : ControlErrorCodes.COMPONENT_INVALID_OWNER;
            } else if (obj.getClassOf() == this.dataTypeID) {
                // A DataType must belong to a Package, a Class, a Component, a Signal, an Enumeration or an Interface.
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.classID)
                        || valueTypeID.extEquals(this.componentID) || valueTypeID.extEquals(this.interfaceID)
                        || valueTypeID.extEquals(this.signalID) || valueTypeID.extEquals(this.enumerationID)) ? ControlErrorCodes.NO_ERROR
                        : ControlErrorCodes.DATATYPE_INVALID_OWNER;
            } else if (obj.getClassOf() == this.enumerationID) {
                // An Enumeration must belong to a Package, a Module, a Profile, a Class, a signal, an Interface, an Enumeration or
                // a Component.
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.moduleID)
                        || valueTypeID.extEquals(this.profileID) || valueTypeID.extEquals(this.classID)
                        || valueTypeID.extEquals(this.signalID) || valueTypeID.extEquals(this.interfaceID)
                        || valueTypeID.extEquals(this.componentID) || valueTypeID.extEquals(this.enumerationID)) ? ControlErrorCodes.NO_ERROR
                        : ControlErrorCodes.ENUMERATION_INVALID_OWNER;
            } else if (obj.getClassOf() == this.interfaceID) {
                // An Interface must belong to a Package, a Class, a Component, an Enumeration or an Interface.
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.classID)
                        || valueTypeID.extEquals(this.componentID) || valueTypeID.extEquals(this.interfaceID) || valueTypeID
                            .extEquals(this.enumerationID)) ? ControlErrorCodes.NO_ERROR
                        : ControlErrorCodes.INTERFACE_INVALID_OWNER;
            } else if (obj.getClassOf() == this.nodeID) {
                // A Node must belong to a Package, a Component or a Node.
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.componentID) || valueTypeID
                        .extEquals(this.nodeID)) ? ControlErrorCodes.NO_ERROR : ControlErrorCodes.NODE_INVALID_OWNER;
            } else if (obj.getClassOf() == this.packageID) {
                // A Package must belong to a Package, a Component, or a
                // Project.
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.componentID) || valueTypeID
                        .extEquals(this.projectID)) ? ControlErrorCodes.NO_ERROR : ControlErrorCodes.PACKAGE_INVALID_OWNER;
        
            } else if (obj.getClassOf() == this.signalID) {
                // A Signal must belong to a Class, an Interface, a Package or a Component.
                return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.classID)
                        || valueTypeID.extEquals(this.componentID) || valueTypeID.extEquals(this.interfaceID)) ? ControlErrorCodes.NO_ERROR
                        : ControlErrorCodes.SIGNAL_INVALID_OWNER;
        
            } else if (obj.getClassOf() == this.templateParameterID) {
                // Owner relationship must be empty for TemplateParameters, use
                // Parameterized instead.
                return ControlErrorCodes.TEMPLATEPARAMETER_INVALID_OWNER;
            }
        }
        return ControlErrorCodes.NO_ERROR;
    }

    /**
     * C'tor
     */
    @objid ("33142dec-82de-4fad-9cfe-58ad3b15d715")
    public ModelTreeOwnerChecker(SmMetamodel mm) {
        // Cached SmClass
        this.actorID = mm.getMClass(Actor.class);
        this.artifactID = mm.getMClass(Artifact.class);
        this.classID = mm.getMClass(Class.class);
        this.collaborationID = mm.getMClass(Collaboration.class);
        this.componentID = mm.getMClass(Component.class);
        this.dataTypeID = mm.getMClass(DataType.class);
        this.enumerationID = mm.getMClass(Enumeration.class);
        this.interfaceID = mm.getMClass(Interface.class);
        this.moduleID = mm.getMClass(ModuleComponent.class);
        this.nodeID = mm.getMClass(Node.class);
        this.packageID = mm.getMClass(Package.class);
        this.profileID = mm.getMClass(Profile.class);
        this.projectID = mm.getMClass(Project.class);
        this.signalID = mm.getMClass(Signal.class);
        this.templateParameterID = mm.getMClass(TemplateParameter.class);
        this.useCaseID = mm.getMClass(UseCase.class);
        
        // Direct checker
        register(mm.getMClass(ModelTree.class), "Owner");
        
        // Symetric checker
        ModelTreeOwnedElementChecker symetricChecker = new ModelTreeOwnedElementChecker(this);
        symetricChecker.register(mm.getMClass(ModelTree.class), "OwnedElement");
    }

    /**
     * Implements type checking for the ModelTree.OwnedElement meta-association.
     */
    @objid ("00411c98-5f5e-1032-829a-001ec947cd2a")
    static class ModelTreeOwnedElementChecker extends AbstractDependencyTypeChecker {
        @objid ("004300c6-5f5e-1032-829a-001ec947cd2a")
         ModelTreeOwnerChecker symetricChecker;

        @objid ("00431796-5f5e-1032-829a-001ec947cd2a")
        public ModelTreeOwnedElementChecker(ModelTreeOwnerChecker symetricChecker) {
            this.symetricChecker = symetricChecker;
        }

        @objid ("00432f10-5f5e-1032-829a-001ec947cd2a")
        @Override
        public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
            return this.symetricChecker.doCheck(value, obj);
        }

    }

}
