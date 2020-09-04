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

package org.modelio.vaudit.modelshield.engine;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vaudit.modelshield.CheckStatus;
import org.modelio.vaudit.modelshield.IProtectionAgent;
import org.modelio.vaudit.modelshield.engine.execution.ObjectProcessor;
import org.modelio.vaudit.modelshield.engine.execution.TransactionProcessor;
import org.modelio.vaudit.modelshield.engine.plan.CompositePlan;
import org.modelio.vaudit.modelshield.engine.plan.IModelShieldPlan;
import org.modelio.vaudit.modelshield.engine.plan.Plan;
import org.modelio.vaudit.modelshield.internal.ShieldContext;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.metamodel.IMetamodelListener;
import org.modelio.vcore.session.impl.transactions.Transaction;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.ICheckerFactory;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * The CoreProtectionAgent is in charge of checking the model against the
 * standard metamodel.
 * 
 * @author phv
 */
@objid ("007812d4-f0b6-1f4c-b2b8-001ec947cd2a")
public class CoreProtectionAgent implements IProtectionAgent {
    @objid ("00079e32-09c6-1f4d-b2b8-001ec947cd2a")
    private final CompositePlan plan;

    /**
     * @param session the core session
     */
    @objid ("0008c9ec-09c6-1f4d-b2b8-001ec947cd2a")
    public CoreProtectionAgent(ICoreSession session) {
        this.plan = new CompositePlan();
        initCheckers( session);
    }

    @objid ("0008e1a2-09c6-1f4d-b2b8-001ec947cd2a")
    @Override
    public String getId() {
        return "CORE";
    }

    @objid ("00090a60-09c6-1f4d-b2b8-001ec947cd2a")
    @Override
    public IModelShieldPlan getPlan() {
        return this.plan;
    }

    @objid ("00095a60-09c6-1f4d-b2b8-001ec947cd2a")
    @Override
    public CheckStatus check(final Transaction theTransaction, final IErrorReport report) {
        ShieldContext context = new ShieldContext(report);
        
        TransactionProcessor v = new TransactionProcessor(this.plan);
        v.check(theTransaction, context);
        return report.getEntries().isEmpty() ? CheckStatus.Success : CheckStatus.Fail;
    }

    @objid ("00098cba-09c6-1f4d-b2b8-001ec947cd2a")
    @Override
    public CheckStatus check(final MObject obj, final IErrorReport report) {
        ShieldContext context = new ShieldContext(report);
        
        ObjectProcessor v = new ObjectProcessor(context, this.plan);
        v.check(obj);
        return report.getEntries().isEmpty() ? CheckStatus.Success : CheckStatus.Fail;
    }

    @objid ("0009beb0-09c6-1f4d-b2b8-001ec947cd2a")
    @Override
    public boolean isInScope(final MObject object) {
        return true;
    }

    @objid ("00824da8-d6c6-1f60-8473-001ec947cd2a")
    private void initCheckers(ICoreSession session) {
        SmMetamodel mm = session.getMetamodel();
        
        for (MMetamodelFragment fragment : mm.getFragments()) {
            registerCheckers(mm, fragment);
        }
        
        session.getMetamodelSupport().addMetamodelListener(new MmListener());
    }

    @objid ("1a56e35d-1d48-460c-9235-c648c967e5b2")
    protected void registerCheckers(MMetamodel mm, MMetamodelFragment fragment) {
        ISmMetamodelFragment smFrag = (ISmMetamodelFragment) fragment;
        
        // get integrated checkers
        ICheckerFactory integratedCheckers = smFrag.getModelShieldCheckers();
        
        if (integratedCheckers != null) {
            Plan fragPlan = new Plan();
            this.plan.addPlan(fragment, fragPlan);
        
            // register integrated checkers
            integratedCheckers.createCheckers(fragPlan, mm);
        
        }
    }

    /**
     * Listen for new metamodel fragments, ask them their {@link ICheckerFactory}
     * and register all their checkers in a separate plan.
     * <p>
     * On removal drop the plan.
     * 
     * @author cmarin
     * @since 3.6
     */
    @objid ("8f50aca1-cab6-4d9c-a20c-1f4f8bd79a72")
    class MmListener implements IMetamodelListener {
        @objid ("24051048-82ac-4bed-baca-572c4c2c57bf")
        @Override
        public void metamodelFragmentAdded(MMetamodel metamodel, MMetamodelFragment fragment) {
            registerCheckers(metamodel, fragment);
        }

        @objid ("23767539-5db6-4d3b-9009-7132340d241f")
        @Override
        public void metamodelFragmentRemoved(MMetamodel metamodel, MMetamodelFragment fragment) {
            // ignore : the plan is already removed in removingMetamodelFragment(...)
        }

        @objid ("5d0381f0-7c38-4ee8-9148-d115e2132676")
        @Override
        public void removingMetamodelFragment(MMetamodel metamodel, MMetamodelFragment fragment) {
            CoreProtectionAgent.this.plan.removeFragment(fragment);
        }

    }

}
