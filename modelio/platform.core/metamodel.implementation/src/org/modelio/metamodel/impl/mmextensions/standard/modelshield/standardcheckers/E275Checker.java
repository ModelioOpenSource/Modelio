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

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E275:
 * <ul>
 * <li>desc = A destruction message ends the Lifeline it destroys. There can be no fragment afterward.</li>
 * <li>what = At least one CombinedFragment was detected on the Lifeline after the ''{0}'' destruction message.</li>
 * </ul>
 */
@objid ("007d7c06-e20e-1f69-b3fb-001ec947cd2a")
public class E275Checker implements IChecker {
    @objid ("0018813e-6456-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E275";

    @objid ("0000f1a4-e473-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        if (object == null)
            return;
        else if (object instanceof Lifeline)
            checkForLifeline((Lifeline) object, report);
        else if (object instanceof InteractionFragment)
            checkForFragment((InteractionFragment) object, report);
    }

    @objid ("0000f37a-e473-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=InteractionFragment, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(InteractionFragment.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=InteractionFragment, feature=Covered
        plan.registerChecker(this, smMetamodel.getMClass(InteractionFragment.class), TriggerType.Update, "Covered");
        
        // trigger=create, metaclass=InteractionFragment, feature=Covered
        plan.registerChecker(this, smMetamodel.getMClass(InteractionFragment.class), TriggerType.Move, "Covered");
    }

    @objid ("0016fee0-d3b8-1f71-90c1-001ec947cd2a")
    private static void checkForFragment(final InteractionFragment fragmentToCheck, final IErrorReport report) {
        // at an IF creation/update, we check all the covered lifelines
        // to see if it breaks the destroy message position rule.
        for (Lifeline lifelineToCheck : fragmentToCheck.getCovered()) {
            checkForLifeline(lifelineToCheck, report);
        }
    }

    @objid ("00172348-d3b8-1f71-90c1-001ec947cd2a")
    private static void checkForLifeline(final Lifeline lifeline, final IErrorReport report) {
        ExecutionOccurenceSpecification destroyMessageEnd = null;
        
        for (InteractionFragment fragment : lifeline.getCoveredBy()) {
            if (fragment instanceof ExecutionOccurenceSpecification) {
                ExecutionOccurenceSpecification eos = (ExecutionOccurenceSpecification) fragment;
                if (eos.getReceivedMessage() != null) {
                    Message receivedMessage = eos.getReceivedMessage();
                    if (receivedMessage.getSortOfMessage() == MessageSort.DESTROYMESSAGE) {
                        // we found a destroy message end on the lifeline
                        destroyMessageEnd = eos;
                        break;
                    }
                }
            }
        }
        
        if (destroyMessageEnd != null) {
            int destroyLine = destroyMessageEnd.getLineNumber(); // was
                                                                    // getFirstLine()
                                                                    // ???
        
            for (InteractionFragment fragment : lifeline.getCoveredBy()) {
                if ((fragment.getLineNumber() > destroyLine)
                        || ((fragment instanceof InteractionUse)
                                && (((InteractionUse) fragment).getLineNumber() < destroyLine) && (((InteractionUse) fragment)
                                .getEndLineNumber() > destroyLine))) {
                    Message destroyMessage = destroyMessageEnd.getReceivedMessage();
                    report.addEntry(new ModelError(ERRORID, destroyMessage, Arrays.asList((Object) fragment)));
                }
            }
        }
    }

}
