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
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E215:
 * <ul>
 * <li>desc = An InterfaceRealization must be directed towards an Interface.</li>
 * <li>what = An interface realization belonging to ''{1}'' is not directed towards an interface.</li>
 * </ul>
 */
@objid ("0088ba26-e20d-1f69-b3fb-001ec947cd2a")
public class E215Checker extends DepCardinalityChecker {
    @objid ("004ae796-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E215";

    @objid ("002ad046-9043-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "Implemented";

    @objid ("00903cc4-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=InterfaceRealization, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(InterfaceRealization.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=InterfaceRealization, feature=Implemented
        plan.registerChecker(this, smMetamodel.getMClass(InterfaceRealization.class), TriggerType.Update, DEPNAME);
    }

    @objid ("002ae734-9043-1f6c-bf9a-001ec947cd2a")
    public E215Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("53f21dbf-3458-4cf7-b2d5-1c58398f2390")
    @Override
    protected ModelError createError(final MObject object, MDependency dep, int currentCard) {
        List<Object> objects = new ArrayList<>();
        objects.add(object.getCompositionOwner());
        return new ModelError(ERRORID, object, objects);
    }

}
