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

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E271:
 * <ul>
 * <li>desc = A Message must have a SendEvent and a ReceiveEvent.</li>
 * <li>what = The ''{0}'' message does not have a SendEvent or a ReceiveEvent.</li>
 * </ul>
 */
@objid ("005ff78a-e20e-1f69-b3fb-001ec947cd2a")
public class E271Checker implements IChecker {
    @objid ("00026b2e-6456-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E271";

    @objid ("00978f4c-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        if (object == null || !(object instanceof Message))
            return;
        
        Message message = (Message) object;
        if ((message.getSendEvent() == null) || (message.getReceiveEvent() == null)) {
            report.addEntry(new ModelError(ERRORID, object, Collections.emptyList()));
        }
    }

    @objid ("00979118-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=Message, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(Message.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=Message, feature=SendEvent
        plan.registerChecker(this, smMetamodel.getMClass(Message.class), TriggerType.Update, "SendEvent");
        
        // trigger=create, metaclass=Message, feature=ReceiveEvent
        plan.registerChecker(this, smMetamodel.getMClass(Message.class), TriggerType.Update, "ReceiveEvent");
    }

}
