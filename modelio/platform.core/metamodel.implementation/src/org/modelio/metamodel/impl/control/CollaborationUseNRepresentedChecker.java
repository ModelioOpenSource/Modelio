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

package org.modelio.metamodel.impl.control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * Implements type checking for the CollaborationUse.NRepresented meta-association.
 */
@objid ("e0a86f22-ecfb-11e1-91c5-002564c97630")
public class CollaborationUseNRepresentedChecker extends AbstractDependencyTypeChecker {
    @objid ("02f6a02e-f027-11e1-8bdc-002564c97630")
    private SmClass actorID;

    @objid ("02f6a022-f027-11e1-8bdc-002564c97630")
    private SmClass classID;

    @objid ("02f6a030-f027-11e1-8bdc-002564c97630")
    private SmClass collaborationID;

    @objid ("02f6a024-f027-11e1-8bdc-002564c97630")
    private SmClass componentID;

    @objid ("02f6a020-f027-11e1-8bdc-002564c97630")
    private SmClass nodeID;

    @objid ("02f6a028-f027-11e1-8bdc-002564c97630")
    private SmClass packageID;

    @objid ("02f6a02a-f027-11e1-8bdc-002564c97630")
    private SmClass signalID;

    @objid ("02f6a026-f027-11e1-8bdc-002564c97630")
    private SmClass templateParameterID;

    @objid ("02f6a02c-f027-11e1-8bdc-002564c97630")
    private SmClass useCaseID;

    /**
     * C'tor
     */
    @objid ("31634809-bc63-40c4-8d11-054c11991f58")
    public CollaborationUseNRepresentedChecker(SmMetamodel mm) {
        // Cached SmClass
        this.templateParameterID = mm.getMClass(TemplateParameter.class);
        this.componentID = mm.getMClass(Component.class);
        this.classID = mm.getMClass(Class.class);
        this.actorID = mm.getMClass(Actor.class);
        this.classID = mm.getMClass(Class.class);
        this.collaborationID = mm.getMClass(Collaboration.class);
        this.componentID = mm.getMClass(Component.class);
        this.nodeID = mm.getMClass(Node.class);
        this.packageID = mm.getMClass(Package.class);
        this.signalID = mm.getMClass(Signal.class);
        this.templateParameterID = mm.getMClass(TemplateParameter.class);
        this.useCaseID = mm.getMClass(UseCase.class);
        
        // Direct checker
        register(mm.getMClass(CollaborationUse.class), "NRepresented");
        
        // Symetric checker
        NameSpaceOwnedCollaborationUseChecker symetricChecker = new NameSpaceOwnedCollaborationUseChecker(this);
        symetricChecker.register(mm.getMClass(NameSpace.class), "OwnedCollaborationUse");
    }

    @objid ("e4c440cc-ecfb-11e1-91c5-002564c97630")
    @Override
    public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
        if (value != null) {
            SmClass valueTypeID = value.getClassOf();
        
            // A CollaborationUse must be owned by a node, a Class, a Component,
            // a TemplateParameter, a Package, a Signal, a useCase, an Actor or
            // a Collaboration.
            return (valueTypeID.extEquals(this.packageID) || valueTypeID.extEquals(this.nodeID)
                    || valueTypeID.extEquals(this.signalID) || valueTypeID.extEquals(this.useCaseID)
                    || valueTypeID.extEquals(this.actorID) || valueTypeID.extEquals(this.classID)
                    || valueTypeID.extEquals(this.componentID) || valueTypeID.extEquals(this.templateParameterID) || valueTypeID
                        .extEquals(this.collaborationID)) ? ControlErrorCodes.NO_ERROR
                    : ControlErrorCodes.COLLABORATIONUSE_INVALID_NREPRESENTED;
        }
        return ControlErrorCodes.NO_ERROR;
    }

    /**
     * Implements type checking for the NameSpace.OwnedCollaborationUse meta-association.
     */
    @objid ("e4c5c76d-ecfb-11e1-91c5-002564c97630")
    static class NameSpaceOwnedCollaborationUseChecker extends AbstractDependencyTypeChecker {
        @objid ("e4c5c770-ecfb-11e1-91c5-002564c97630")
         CollaborationUseNRepresentedChecker symetricChecker;

        @objid ("e4c5c771-ecfb-11e1-91c5-002564c97630")
        public NameSpaceOwnedCollaborationUseChecker(CollaborationUseNRepresentedChecker symetricChecker) {
            this.symetricChecker = symetricChecker;
        }

        @objid ("e4c5c774-ecfb-11e1-91c5-002564c97630")
        @Override
        public int doCheck(final SmObjectImpl obj, final SmObjectImpl value) {
            return this.symetricChecker.doCheck(value, obj);
        }

    }

}
