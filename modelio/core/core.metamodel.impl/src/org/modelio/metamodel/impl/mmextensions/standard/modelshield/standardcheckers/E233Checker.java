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
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic.DepCardinalityChecker;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E233:
 * <ul>
 * <li>desc = A StateMachine must have a root Region.</li>
 * <li>what = The ''{0}'' state machine has no root region.</li>
 * </ul>
 */
@objid ("0005489e-e20e-1f69-b3fb-001ec947cd2a")
public class E233Checker extends DepCardinalityChecker {
    @objid ("005a6dec-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E233";

    @objid ("0061a3d2-9e33-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "Top";

    @objid ("0091b270-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=StateMachine, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(StateMachine.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=StateMachine, feature=Top
        plan.registerChecker(this, smMetamodel.getMClass(StateMachine.class), TriggerType.Update, DEPNAME);
        
    }

    @objid ("0061acb0-9e33-1f6c-bf9a-001ec947cd2a")
    public  E233Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("86019f29-1c9f-4a10-889a-0677f5198558")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        return createDefaultError(object, dep, currentCard);
    }

}
