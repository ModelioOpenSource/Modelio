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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic.DepCardinalityChecker;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E214:
 * <ul>
 * <li>desc = A Generalization must be linked to a NameSpace.</li>
 * <li>what = A generalization belonging to ''{1}'' is not linked to a name space.</li>
 * </ul>
 */
@objid ("00873b7e-e20d-1f69-b3fb-001ec947cd2a")
public class E214Checker extends DepCardinalityChecker {
    @objid ("0049eb8e-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E214";

    @objid ("0051bb7a-8f92-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "SuperType";

    @objid ("00902270-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=Generalization, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(Generalization.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=Generalization, feature=SuperType
        plan.registerChecker(this, smMetamodel.getMClass(Generalization.class), TriggerType.Update, DEPNAME);
    }

    @objid ("0051cf02-8f92-1f6c-bf9a-001ec947cd2a")
    public E214Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("7d766ae7-914e-4f3c-9ea2-808c8689072e")
    @Override
    protected ModelError createError(final MObject object, MDependency dep, int currentCard) {
        List<Object> objects = new ArrayList<>();
        objects.add(object.getCompositionOwner());
        return new ModelError(ERRORID, object, objects);
    }

}
