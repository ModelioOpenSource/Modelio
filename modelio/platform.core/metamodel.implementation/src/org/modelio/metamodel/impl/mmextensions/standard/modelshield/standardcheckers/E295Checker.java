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

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E295;
 * <ul>
 * <li>desc = An AssociationEnd must have an Association.</li>
 * <li>what = The ''{0}'' association end must have a related Association.</li>
 * </ul>
 * </p>
 */
@objid ("058dcef8-0ddb-4f3e-9aa3-ff88f6398297")
public class E295Checker implements IChecker {
    @objid ("ccd6ee59-dbdc-4100-a889-9d97db2ab126")
    private static final String ERRORID = "E295";

    @objid ("24e95fe1-7a6d-4022-9571-c14968644d6f")
    private static final String DEPNAME = "Association";

    @objid ("8aba82f3-e80e-4831-99ee-a644465041d4")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=AssociationEnd, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=AssociationEnd, feature=Association
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, DEPNAME);
    }

    @objid ("e5cee3d8-584a-4c5f-9470-99d767e15cfd")
    @Override
    public void check(MObject object, final IErrorReport report) {
        AssociationEnd end = (AssociationEnd) object;
        
        if (end.getAssociation() == null) {
            report.addEntry(new ModelError(ERRORID, object, Arrays.<Object>asList(end)));
        }
    }

}
