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
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic.DepCardinalityChecker;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E216:
 * <ul>
 * <li>desc = A Link must have at least two instanciated roles.</li>
 * <li>what = The ''{0}'' link does not have at least two instanciated roles.</li>
 * </ul>
 */
@objid ("008a3644-e20d-1f69-b3fb-001ec947cd2a")
public class E216Checker extends DepCardinalityChecker {
    @objid ("004c167a-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E216";

    @objid ("001605b2-9b7c-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "Connection";

    @objid ("0090572c-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=Link, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(LinkEnd.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=Link, feature=Connection
        plan.registerChecker(this, smMetamodel.getMClass(LinkEnd.class), TriggerType.Update, DEPNAME);
    }

    @objid ("00161c0a-9b7c-1f6c-bf9a-001ec947cd2a")
    public E216Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("fe33e3fa-dfdc-45eb-ba93-53f3f5ddabe0")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        return createDefaultError(object, dep, currentCard);
    }

}
