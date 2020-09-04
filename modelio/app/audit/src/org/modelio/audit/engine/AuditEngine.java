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

package org.modelio.audit.engine;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AuditRunnerStatus;
import org.modelio.audit.engine.core.IAuditExecutionPlan;
import org.modelio.audit.engine.core.IAuditMonitor;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.engine.impl.AuditDiagnostic;
import org.modelio.audit.engine.impl.AuditDispatcher;
import org.modelio.audit.engine.impl.AuditRunner;
import org.modelio.audit.engine.impl.CheckProgram;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("c14d6c0a-1aae-41cf-bf57-43964505d8cc")
public class AuditEngine {
    @objid ("4cbb859e-df95-45e6-ac20-840ab2ca64ca")
    private AuditRunningMode runningMode = AuditRunningMode.AUTO;

    @objid ("e5c0de12-4253-44b6-955b-343d93f02a53")
    private final AuditRunner auditRunner;

    @objid ("cf077656-ac94-4365-9dbc-4b2ac6f42d3f")
    private IAuditExecutionPlan activePlan;

    @objid ("6252676e-c894-4698-9877-69b8445cb700")
    private final AuditDiagnostic auditDiagnostic;

    @objid ("2b32aa9d-f583-43c5-8c2c-4304c52d15df")
    private final AuditDispatcher auditDispatcher;

    @objid ("a4afbd11-3e66-4216-87e4-0c661d701793")
    private final CheckProgram controlProgram;

    @objid ("392ee00d-9cdd-4e79-a706-38839730de8c")
    private ICoreSession session;

    @objid ("4371ff55-d43d-4b6b-ae3f-9540692832ba")
    private Thread auditThread;

    @objid ("54d1b224-431d-4f26-9fd2-ad0b65773c35")
    public AuditEngine() {
        // create and assemble a dispatcher and a runner connected to a control
        // program
        this.controlProgram = new CheckProgram();
        this.auditDiagnostic = new AuditDiagnostic();
        this.auditRunner = new AuditRunner(this.controlProgram, this.auditDiagnostic);
        this.auditDispatcher = new AuditDispatcher(this.controlProgram);
    }

    @objid ("eeed88c7-125d-4d01-abc5-f81f96f5983d")
    public void setPlan(IAuditExecutionPlan plan) {
        // pause the audit while changing the plan
        pause();
        
        this.activePlan = plan;
        this.auditDispatcher.setPlan(this.activePlan);
        
        Map<String, IRule> rules = new HashMap<>();
        for (IRule rule : plan.getAllRules()) {
            rules.put(rule.getRuleId(), rule);
        }
        
        // refresh the audit results and resume the audit
        this.auditDiagnostic.auditPlanChanged(rules);
        resume();
    }

    @objid ("e5210aad-f7b8-49cd-90f7-2efd9f5bc0ea")
    public void start(ICoreSession aSession) {
        // register the dispatcher as model change listener
        aSession.getModelChangeSupport().addModelChangeListener(this.auditDispatcher);
        aSession.getModelChangeSupport().addStatusChangeListener(this.auditDispatcher);
        
        // start the runner in a thread
        this.auditThread = new Thread(this.auditRunner);
        this.auditThread.setPriority(Thread.MIN_PRIORITY);
        this.auditThread.setName("AUDIT");
        this.auditThread.start();
        this.session = aSession;
        
        // set the audit mode to AUTO
        setRunningMode(AuditRunningMode.AUTO);
        resume();
    }

    @objid ("bd57d4b7-0385-477b-8a71-4142e561a053")
    public void stop(ICoreSession aSession) {
        // first stop the audit activity
        pause();
        
        // unregister the dispatcher
        aSession.getModelChangeSupport().removeModelChangeListener(this.auditDispatcher);
        aSession.getModelChangeSupport().removeStatusChangeListener(this.auditDispatcher);
        
        // terminate the runner thread
        this.auditRunner.terminate();
        
        this.auditDiagnostic.clear();
        this.session = null;
    }

    /**
     * Set the running mode of the audit:
     * <ul>
     * <li>AUTO: the dispatcher is running analyzing transactions and posting
     * controls to the check program queue. The audit runner is running,
     * processing the posted controls and pushing results in the audit
     * diagnotic. The audit 'Check model' command when executed, simply post
     * additionnal controls in the queue producing results that are merged with
     * the global results.</li>
     * <li>MANUAL: the dispatcher is stopped, ie no longer analyzing
     * transactions and therefore not posting any controls to the check program
     * queue. The audit runner is still running, processing the posted controls
     * and pushing results in the audit diagnostic. The control queue is emptied
     * when switching to this mode along with the current diagnostics (clears
     * the result view). The audit 'Check model' command' is therefore the only
     * one to post controls in the queue, and as the runner is still running,
     * these controls are performed and their results displayed</li>
     * <li>PAUSED: both the dispatcher and the runner are stopped. The 'Check
     * model' command is inactive</li>
     * </ul>
     */
    @objid ("4750c613-9862-4a1e-a233-1cd33362288e")
    public void setRunningMode(final org.modelio.audit.engine.AuditRunningMode mode) {
        switch (mode) {
        case AUTO:
            switchToAuto();
            break;
        case MANUEL:
            switchToManual();
            break;
        }
        this.runningMode = mode;
    }

    /**
     * Switch the audit in PAUSED mode:
     * <ul>
     * <li>audit dispatcher is inactive</li>
     * <li>audit runner is stopped</li>
     * </ul>
     */
    @objid ("92f075d4-74be-4674-b95f-e1a34541354a")
    public void pause() {
        this.auditDispatcher.stop();
        this.auditRunner.stop();
    }

    @objid ("968d1905-fbb1-4155-9225-6a534560adda")
    public void resume() {
        switch (this.runningMode) {
        case AUTO:
            this.auditDispatcher.start();
            this.auditRunner.start(this.session);
            break;
        case MANUEL:
            this.auditDispatcher.stop();
            this.auditRunner.start(this.session);
            break;
        }
    }

    @objid ("c86c2789-456a-40eb-8108-18f6d170788c")
    public void checkElement(MObject elementToCheck, String jobId) {
        this.auditDispatcher.submitElement(elementToCheck, jobId);
    }

    @objid ("182ede6c-4421-45ef-aaba-43944aa55b95")
    public AuditDiagnostic getAuditDiagnostic() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.auditDiagnostic;
    }

    @objid ("119c0cb6-7493-416b-b880-7533f02df583")
    public org.modelio.audit.engine.AuditRunningMode getRunningMode() {
        return this.runningMode;
    }

    @objid ("b20ae5c1-aee8-4052-ac35-11b07dfb722d")
    public AuditRunnerStatus getRunningStatus() {
        return this.auditRunner.status;
    }

    @objid ("8a79cf4e-c024-4a81-a5aa-86520338ee37")
    public synchronized void addAuditMonitor(IAuditMonitor monitor) {
        this.auditRunner.auditMonitors.add(monitor);
    }

    @objid ("c3db2031-3214-4aa2-b454-110ff24507ef")
    public synchronized void removeAuditMonitor(IAuditMonitor monitor) {
        this.auditRunner.auditMonitors.remove(monitor);
    }

    /**
     * Switch the audit in AUTO mode:
     * <ul>
     * <li>audit dispatcher is active</li>
     * <li>audit runner is running</li>
     * </ul>
     */
    @objid ("fbf4d38e-9d3e-40d1-8357-939eabd6599c")
    private void switchToAuto() {
        this.auditRunner.start(this.session);
        this.auditDispatcher.start();
    }

    /**
     * Switch the audit in MANUAL mode:
     * <ul>
     * <li>audit dispatcher is inactive</li>
     * <li>audit runner is running</li>
     * </ul>
     */
    @objid ("1b74127d-da2e-4209-8493-d174708cbefa")
    private void switchToManual() {
        this.auditRunner.start(this.session);
        this.auditDispatcher.stop();
    }

    @objid ("1578d2d0-04a6-4416-904c-e20b9c7dd665")
    public void clearCheck() {
        this.auditDispatcher.clearCheck();
    }

    @objid ("f929cea5-087f-4b57-8286-c8cd646007f0")
    public AuditRunner getAuditRunner() {
        return this.auditRunner;
    }

}
