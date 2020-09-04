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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic.DepCardinalityChecker;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E230:
 * <ul>
 * <li>desc = A PropertyValueSet must have a PropertySet.</li>
 * <li>what = A property value set belonging to the ''{1}'' element has no property set.</li>
 * </ul>
 */
@objid ("0000d58e-e20e-1f69-b3fb-001ec947cd2a")
public class E230Checker extends DepCardinalityChecker {
    @objid ("0057236c-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E230";

    @objid ("005fd78c-9e33-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "Type";

    @objid ("0091632e-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=PropertyValueSet, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(TypedPropertyTable.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=PropertyValueSet, feature=Type
        plan.registerChecker(this, smMetamodel.getMClass(TypedPropertyTable.class), TriggerType.Update, DEPNAME);
    }

    @objid ("005fe394-9e33-1f6c-bf9a-001ec947cd2a")
    public E230Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("ceb3f8b8-9bc2-4ac0-82d7-018bd81bd02a")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        List<Object> objects = new ArrayList<>();
        objects.add(object.getCompositionOwner());
        return new ModelError(ERRORID, object, objects);
    }

}
