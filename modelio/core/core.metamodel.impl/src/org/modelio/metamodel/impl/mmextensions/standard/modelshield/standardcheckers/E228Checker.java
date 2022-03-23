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
import org.modelio.metamodel.mda.Project;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E228:
 * <ul>
 * <li>desc = A Project must be linked to a Package.</li>
 * <li>what = The ''{0}'' project is not linked to a package.</li>
 * </ul>
 */
@objid ("0094d400-e20d-1f69-b3fb-001ec947cd2a")
public class E228Checker extends DepCardinalityChecker {
    @objid ("0053bd9e-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E228";

    @objid ("005dbba0-9e33-1f6c-bf9a-001ec947cd2a")
    private static final String DEPNAME = "Model";

    @objid ("00911400-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=Project, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(Project.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=Project, feature=Model
        plan.registerChecker(this, smMetamodel.getMClass(Project.class), TriggerType.Update, DEPNAME);
        
    }

    @objid ("005dc474-9e33-1f6c-bf9a-001ec947cd2a")
    public  E228Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("34183e42-e6cd-46de-97d6-473a62958464")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        return createDefaultError(object, dep, currentCard);
    }

}
