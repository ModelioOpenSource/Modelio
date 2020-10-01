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
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E237;
 * <ul>
 * <li>desc = An AbstractResource must have an ResourceType.</li>
 * <li>what = An AbstractResource belonging to the ''{1}'' element does not have an ResourceType.</li>
 * </ul>
 * </p>
 */
@objid ("d9655369-edfe-11e1-9e29-002564c97630")
public class E237Checker extends DepCardinalityChecker {
    @objid ("0982192c-edff-11e1-9e29-002564c97630")
    private static final String ERRORID = "E237";

    @objid ("09821931-edff-11e1-9e29-002564c97630")
    private static final String DEPNAME = "Type";

    @objid ("0937548e-edff-11e1-9e29-002564c97630")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=Note, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(AbstractResource.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=Note, feature=Model
        plan.registerChecker(this, smMetamodel.getMClass(AbstractResource.class), TriggerType.Update, DEPNAME);
    }

    @objid ("09375493-edff-11e1-9e29-002564c97630")
    public E237Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("87c7077f-e835-472e-8541-75c72074f19e")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        List<Object> objects = new ArrayList<>();
        objects.add(object.getCompositionOwner());
        return new ModelError(ERRORID, object, objects);
    }

}
