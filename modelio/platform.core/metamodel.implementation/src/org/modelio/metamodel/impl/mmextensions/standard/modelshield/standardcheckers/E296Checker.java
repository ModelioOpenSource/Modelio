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
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E296;
 * <ul>
 * <li>desc = A LinkEnd must have a Link.</li>
 * <li>what = The ''{0}'' LinkEnd must have a related Link.</li>
 * </ul>
 * </p>
 */
@objid ("60850b16-5ca7-447f-964c-a4e978004b8d")
public class E296Checker implements IChecker {
    @objid ("5cc9859b-2d49-4f57-93c0-ece682c02258")
    private static final String ERRORID = "E296";

    @objid ("408b8310-b413-4d6e-9876-aa7edd55d460")
    private static final String DEPNAME = "Association";

    @objid ("93239aa5-69fa-4de3-a7e7-9ee748f7a181")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=LinkEnd, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(LinkEnd.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=LinkEnd, feature=Association
        plan.registerChecker(this, smMetamodel.getMClass(LinkEnd.class), TriggerType.Update, DEPNAME);
        
        // trigger=create, metaclass=ConnectorEnd, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(ConnectorEnd.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=ConnectorEnd, feature=Association
        plan.registerChecker(this, smMetamodel.getMClass(ConnectorEnd.class), TriggerType.Update, DEPNAME);
    }

    @objid ("3819aebb-3b8f-46a6-bb3f-5f850cf697f0")
    @Override
    public void check(MObject object, final IErrorReport report) {
        LinkEnd end = (LinkEnd) object;
        
        if (end.getLink() == null) {
            report.addEntry(new ModelError(ERRORID, object, Arrays.<Object>asList(end)));
        }
    }

}
