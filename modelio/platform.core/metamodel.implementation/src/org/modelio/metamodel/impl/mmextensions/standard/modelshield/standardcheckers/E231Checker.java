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
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E231:
 * <ul>
 * <li>desc = A RaisedException must have a Classifier as its destination.</li>
 * <li>what = A raised exception belonging to the ''{1}'' element has no destination.</li>
 * </ul>
 */
@objid ("00027e70-e20e-1f69-b3fb-001ec947cd2a")
public class E231Checker extends DepCardinalityChecker {
    @objid ("00582096-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E231";

    @objid ("006076ba-9e33-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "ThrownType";

    @objid ("00917d96-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=RaisedException, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(RaisedException.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=RaisedException, feature=ThrownType
        plan.registerChecker(this, smMetamodel.getMClass(RaisedException.class), TriggerType.Update, DEPNAME);
    }

    @objid ("00608024-9e33-1f6c-bf9a-001ec947cd2a")
    public E231Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("09e7ebfc-087d-4088-9c04-20ea9c4994d1")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        List<Object> objects = new ArrayList<>();
        objects.add(object.getCompositionOwner());
        return new ModelError(ERRORID, object, objects);
    }

}
