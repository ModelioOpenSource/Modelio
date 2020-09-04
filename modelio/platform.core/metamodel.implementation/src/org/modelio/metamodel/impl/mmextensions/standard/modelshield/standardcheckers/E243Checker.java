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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic.TypeChecker;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E243:
 * <ul>
 * <li>desc = An AssociationEnd can belong to a Class, an Interface, a Component, an Actor, a DataType, a Node or a Signal.</li>
 * <li>what = The ''{0}'' role cannot belong to the ''{1}'' %2.</li>
 * </ul>
 */
@objid ("00550bb8-e20d-1f69-b3fb-001ec947cd2a")
public class E243Checker extends TypeChecker {
    @objid ("00244d66-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E243";

    @objid ("008ca5f0-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        addRequiredType(smMetamodel.getMClass(AssociationEnd.class));
        addRequiredType(smMetamodel.getMClass(Class.class));
        addRequiredType(smMetamodel.getMClass(Actor.class));
        addRequiredType(smMetamodel.getMClass(UseCase.class));
        addRequiredType(smMetamodel.getMClass(DataType.class));
        addRequiredType(smMetamodel.getMClass(Interface.class));
        addRequiredType(smMetamodel.getMClass(Node.class));
        addRequiredType(smMetamodel.getMClass(Signal.class));
        addRequiredType(smMetamodel.getMClass(Artifact.class));
        addRequiredType(smMetamodel.getMClass(Component.class));
        addRequiredType(smMetamodel.getMClass(TemplateParameter.class));
        addRequiredType(smMetamodel.getMClass(Enumeration.class));
        
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, "Source");
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, "Target");
        
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, "Opposite");
        
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Create, null);
    }

    @objid ("002ca718-38c1-1f6b-b3fb-001ec947cd2a")
    public E243Checker() {
        super(ERRORID);
    }

    @objid ("002cbbe0-38c1-1f6b-b3fb-001ec947cd2a")
    @Override
    protected MObject getCheckedObject(final MObject obj) {
        return obj.getCompositionOwner();
    }

}
