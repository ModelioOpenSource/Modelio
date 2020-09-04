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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic.DepCardinalityChecker;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E236:
 * <ul>
 * <li>desc = A Manifestation must be directed towards a ModelElement.</li>
 * <li>what = A manifestation of the ''{1}'' element is not directed towards a model element.</li>
 * </ul>
 */
@objid ("000ba4c8-e20e-1f69-b3fb-001ec947cd2a")
public class E236Checker extends DepCardinalityChecker {
    @objid ("005fd80e-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E236";

    @objid ("00598f58-912e-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "UtilizedElement";

    @objid ("00921c4c-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=Manifestation, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(Manifestation.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=Manifestation, feature=UtilizedElement
        plan.registerChecker(this, smMetamodel.getMClass(Manifestation.class), TriggerType.Update, DEPNAME);
    }

    @objid ("005a9678-912e-1f6c-bf9a-001ec947cd2a")
    public E236Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("d021eb93-376e-4f34-a52f-e80af1bf93fe")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        List<Object> objects = new ArrayList<>();
        objects.add(object.getCompositionOwner());
        return new ModelError(ERRORID, object, objects);
    }

}
