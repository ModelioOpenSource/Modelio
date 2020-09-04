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
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E269:
 * <ul>
 * <li>desc = A message must not travel back the time : its ReceiveEvent.LineNumber must be >= SendEvent.LineNumber</li>
 * <li>what = The ''{0}'' message travels back in the time: its goes from %2 to %1 time units.</li>
 * </ul>
 */
@objid ("005b4dc0-e20e-1f69-b3fb-001ec947cd2a")
public class E269Checker implements IChecker {
    @objid ("0097dd62-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E269";

    @objid ("00973fe2-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        Message m;
        if (object instanceof Message) {
            m = (Message) object;
        } else {
            MessageEnd end = (MessageEnd)object;
            if (end.getSentMessage() != null) {
                m = end.getSentMessage();
            } else {
                m = end.getReceivedMessage();
            }
        }
        if (m == null) {
            return;
        }
        MessageEnd start = m.getSendEvent();
        MessageEnd end = m.getReceiveEvent();
        
        if (start == null || end == null) {
            return;
        }
        
        if (end.getLineNumber() < start.getLineNumber()) {
            List<Object> objects = new ArrayList<>();
            objects.add(end.getLineNumber());
            objects.add(start.getLineNumber());
        
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
    }

    @objid ("009741ae-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=Message, feature=SendEvent
        plan.registerChecker(this, smMetamodel.getMClass(Message.class), TriggerType.AnyTrigger, "SendEvent");
        
        // trigger=*, metaclass=Message, feature=ReceiveEvent
        plan.registerChecker(this, smMetamodel.getMClass(Message.class), TriggerType.AnyTrigger, "ReceiveEvent");
        
             // trigger=*, metaclass=MessageEnd, feature=LineNumber
        plan.registerChecker(this, smMetamodel.getMClass(MessageEnd.class), TriggerType.AnyTrigger, "LineNumber");
    }

}
