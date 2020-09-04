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
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E234:
 * <ul>
 * <li>desc = A TaggedValue must have a TagType.</li>
 * <li>what = A tagged value belonging to the ''{1}'' element has no tagged value type.</li>
 * </ul>
 */
@objid ("00070850-e20e-1f69-b3fb-001ec947cd2a")
public class E234Checker extends DepCardinalityChecker {
    @objid ("005bd984-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E234";

    @objid ("00623b30-9e33-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "Definition";

    @objid ("0091ccc4-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=TaggedValue, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(TaggedValue.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=TaggedValue, feature=Definition
        plan.registerChecker(this, smMetamodel.getMClass(TaggedValue.class), TriggerType.Update, DEPNAME);
    }

    @objid ("0062442c-9e33-1f6c-bf9a-001ec947cd2a")
    public E234Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("036aab88-5665-419b-9d75-8518199be3e3")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        List<Object> objects = new ArrayList<>();
        objects.add(object.getCompositionOwner());
        return new ModelError(ERRORID, object, objects);
    }

}
