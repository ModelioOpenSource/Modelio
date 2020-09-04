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

package org.modelio.vaudit.modelshield.engine.execution;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vaudit.modelshield.engine.plan.IModelShieldPlan;
import org.modelio.vaudit.modelshield.internal.ShieldContext;
import org.modelio.vcore.session.impl.transactions.Transaction;
import org.modelio.vcore.session.impl.transactions.smAction.AppendDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.CreateElementAction;
import org.modelio.vcore.session.impl.transactions.smAction.DeleteElementAction;
import org.modelio.vcore.session.impl.transactions.smAction.EraseDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.IAction;
import org.modelio.vcore.session.impl.transactions.smAction.MoveDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.SetAttributeAction;
import org.modelio.vcore.session.impl.transactions.smAction.smActionInteractions.IActionVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

@objid ("00280c2c-0000-0488-0000-000000000000")
public class TransactionProcessor {
    @objid ("006ad6a0-13d2-1f62-8473-001ec947cd2a")
    private final IModelShieldPlan plan;

    @objid ("006af504-13d2-1f62-8473-001ec947cd2a")
    public TransactionProcessor(final IModelShieldPlan plan) {
        this.plan = plan;
    }

    @objid ("006b123c-13d2-1f62-8473-001ec947cd2a")
    public void check(final Transaction transaction, final ShieldContext context) {
        ActionVisitor visitor = new ActionVisitor(context, this.plan);
        visitor.visitTransaction(transaction);
    }

    @objid ("006b2812-13d2-1f62-8473-001ec947cd2a")
    private static class ActionVisitor implements IActionVisitor {
        @objid ("54d840a2-2e72-11de-b561-001ec947cd2a")
        private final PlanExecution planExecutor;

        @objid ("0028140c-0000-005b-0000-000000000000")
        private final ShieldContext context;

        @objid ("00280c2c-0000-048d-0000-000000000000")
        @Override
        public void visitTransaction(final Transaction theTransaction) {
            for (IAction action : theTransaction.getActions()) {
                action.accept(this);
            }
        }

        @objid ("00280c2c-0000-0491-0000-000000000000")
        @Override
        public void visitCreateElementAction(final CreateElementAction action) {
            executeCheckersFor(action.getRefered(), TriggerType.Create, null);
        }

        @objid ("00280c2c-0000-0494-0000-000000000000")
        @Override
        public void visitDeleteElementAction(final DeleteElementAction action) {
            executeCheckersFor(action.getRefered(), TriggerType.DeleteTrigger, null);
        }

        @objid ("00280c2c-0000-0497-0000-000000000000")
        @Override
        public void visitSetAttributeAction(final SetAttributeAction action) {
            executeCheckersFor(action.getRefered(), TriggerType.Update, action.getAtt().getName());
        }

        @objid ("00280c2c-0000-049b-0000-000000000000")
        @Override
        public void visitEraseDependencyAction(final EraseDependencyAction action) {
            executeCheckersFor(action.getRefered(), TriggerType.Update, action.getDep().getName());
            if (action.getDep().isComponent() && action.getRef() != null) {
                executeCheckersFor(action.getRef(), TriggerType.Move, null);
            }
        }

        @objid ("00280c2c-0000-049f-0000-000000000000")
        @Override
        public void visitAppendDependencyAction(final AppendDependencyAction action) {
            executeCheckersFor(action.getRefered(), TriggerType.Update, action.getDep().getName());
            if (action.getDep().isComponent() && action.getRef() != null) {
                executeCheckersFor(action.getRef(), TriggerType.Move, null);
            }
        }

        @objid ("00280c2c-0000-04a3-0000-000000000000")
        @Override
        public void visitMoveDependencyAction(final MoveDependencyAction action) {
            executeCheckersFor(action.getRefered(), TriggerType.ReorderTrigger, action.getDep().getName());
        }

        @objid ("00280c2c-0000-04a6-0000-000000000000")
        public ActionVisitor(final ShieldContext context, final IModelShieldPlan plan) {
            this.context = context;
            this.planExecutor = new PlanExecution(plan);
        }

        @objid ("00280c2c-0000-04ae-0000-000000000000")
        private void executeCheckersFor(MObject obj, final TriggerType trigger, final String feature) {
            // if (!obj.isDeleted() && !obj.isTobeDeleted() &&
            // !obj.isRamcObject())
            // {
            this.planExecutor.process(this.context, obj, trigger, feature);
            // obj.accept(planExecutor);
            // }
        }

    }

}
