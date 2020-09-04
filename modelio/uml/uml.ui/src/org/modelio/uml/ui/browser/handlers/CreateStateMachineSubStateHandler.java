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

package org.modelio.uml.ui.browser.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.model.browser.view.handlers.create.CreateElementHandler;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handles to create States under StateMachines.
 * All States must be created in the top region.
 */
@objid ("8922d1a1-c9d8-11e1-b479-001ec947c8cc")
public class CreateStateMachineSubStateHandler extends CreateElementHandler {
    @objid ("4e57e2ad-ccde-11e1-97e5-001ec947c8cc")
    private MObject getEffectiveOwner(MObject owner, IMModelServices mmServices) {
        if (! (owner instanceof StateMachine)) {
            return null;
        }
        StateMachine stateMachine = (StateMachine) owner;
        Region topRegion = stateMachine.getTop();
        // If top region doesn't exist yet, create it.
        if (topRegion == null) {
            IStandardModelFactory mmFactory = mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
            topRegion = mmFactory.createRegion();
            stateMachine.setTop(topRegion);
        }
        return topRegion;
    }

    @objid ("f4396e52-b734-4371-a6f7-465adb9fb471")
    @Override
    protected MObject getNewElementOwner(final Object selection, final MClass metaclass, IMModelServices mmServices) {
        MObject selectedOwner = super.getNewElementOwner(selection,metaclass, mmServices);
        return getEffectiveOwner(selectedOwner, mmServices);
    }

}
