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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic.TypeChecker;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E263:
 * <ul>
 * <li>desc = An interaction can be owned by:
 * - an Actor
 * - a Class
 * - a Collaboration
 * - a Composant
 * - an Interface
 * - a Node
 * - an Operation
 * - a Package
 * - a Use Case.</li>
 * <li>what = The ''{0}'' interaction cannot be owned by the element ''{1}'' of type %2. </li>
 * </ul>
 */
@objid ("004baa14-e20e-1f69-b3fb-001ec947cd2a")
public class E263Checker extends TypeChecker {
    @objid ("008bb96a-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E263";

    @objid ("00963ab6-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        addRequiredType(smMetamodel.getMClass(Package.class));
        addRequiredType(smMetamodel.getMClass(Actor.class));
        addRequiredType(smMetamodel.getMClass(Class.class));
        addRequiredType(smMetamodel.getMClass(Collaboration.class));
        addRequiredType(smMetamodel.getMClass(Component.class));
        addRequiredType(smMetamodel.getMClass(Interface.class));
        addRequiredType(smMetamodel.getMClass(Node.class));
        addRequiredType(smMetamodel.getMClass(Operation.class));
        addRequiredType(smMetamodel.getMClass(Package.class));
        addRequiredType(smMetamodel.getMClass(UseCase.class));
        
        // trigger=create, metaclass=Interaction, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(Interaction.class), TriggerType.Create, null);
        
        // trigger=move, metaclass=Interaction, feature=
        plan.registerChecker(this, smMetamodel.getMClass(Interaction.class), TriggerType.Move, "");
    }

    @objid ("001cacd2-159d-1f6a-b3fb-001ec947cd2a")
    public E263Checker() {
        super(ERRORID);
    }

    @objid ("c76b08cd-d730-11e1-bf21-002564c97630")
    @Override
    protected MObject getCheckedObject(final MObject obj) {
        return obj.getCompositionOwner();
    }

}
