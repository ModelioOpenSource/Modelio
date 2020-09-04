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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic.DepCardinalityChecker;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E238:
 * <ul>
 * <li>desc = A CombinedFragment must have at least one InteractionOperand.</li>
 * <li>what = The ''{0}'' combined fragment must have at least one interaction operand.</li>
 * </ul>
 */
@objid ("000d1236-e20e-1f69-b3fb-001ec947cd2a")
public class E238Checker extends DepCardinalityChecker {
    @objid ("00611296-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E238";

    @objid ("0063664a-9e33-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "Operand";

    @objid ("009238a8-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=CombinedFragment, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(CombinedFragment.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=CombinedFragment, feature=Operand
        plan.registerChecker(this, smMetamodel.getMClass(CombinedFragment.class), TriggerType.Update, DEPNAME);
    }

    @objid ("00636f32-9e33-1f6c-bf9a-001ec947cd2a")
    public E238Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("1b2151e4-1e51-4683-88f0-af07dc360817")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        return createDefaultError(object, dep, currentCard);
    }

}
