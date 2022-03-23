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
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E210:
 * <ul>
 * <li>desc = An Association must have at least two AssociationEnds.</li>
 * <li>what = The ''{0}'' association does not have at least two roles.</li>
 * </ul>
 */
@objid ("00814d86-e20d-1f69-b3fb-001ec947cd2a")
public class E210Checker extends DepCardinalityChecker {
    @objid ("00458d6e-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E210";

    @objid ("005483dc-9e33-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "Connection";

    @objid ("008fb812-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=Association, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=Association, feature=Connection
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, DEPNAME);
        
    }

    @objid ("00549728-9e33-1f6c-bf9a-001ec947cd2a")
    public  E210Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("dccf33b0-75ee-4c19-90c6-f468189a03f0")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        return createDefaultError(object, dep, currentCard);
    }

}
