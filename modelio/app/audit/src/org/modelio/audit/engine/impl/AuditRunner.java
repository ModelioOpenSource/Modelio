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

package org.modelio.audit.engine.impl;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AuditRunnerStatus;
import org.modelio.audit.engine.core.IAuditMonitor;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.plugin.Audit;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * Audit check runner engine. Its main role consists in fetching some controls to carry out from the CheckProgram, running theses
 * controls and publishing the results in the AuditDiagnostic.
 */
@objid ("12d79e3c-cdd0-4d7d-9ab1-0d606a3b22b5")
public class AuditRunner implements Runnable {
    @objid ("3fa7d17e-a40c-4876-9a82-fc45d8bbe396")
    public volatile AuditRunnerStatus status;

    @objid ("7f93a3a7-b7b0-4462-bd8a-7f7f87af4312")
    public volatile LoopControlCommand loopControl;

    @objid ("8b0e7784-feb0-4b97-b880-880052abba08")
    private final CheckProgram checkProgram;

    @objid ("00a1db89-a59f-4ef5-8cbb-3edcaa469666")
    private AuditDiagnostic auditDiagnostic = null;

    @objid ("8328a132-e575-4b65-90bc-0268d78a6840")
    public List<IAuditMonitor> auditMonitors = new ArrayList<>();

    @objid ("f5068dbe-4eab-44ad-8be4-312238021aff")
    private ICoreSession session;

    @objid ("e473b09a-bd5d-4d16-a4f7-04a2ced24cab")
    @Override
    public void run() {
        boolean additionnalDiagnosticToPost = true;
        while (true) {
            try {
                switch (this.loopControl) {
                case TERMINATE:
                    // termination requested
                    return;
                case RUN:
                    // process checks
                    CheckBatch batch = this.checkProgram.getNextControlBatch();
                    if (batch != null) {
                        additionnalDiagnosticToPost = true;
                        changeStatus(AuditRunnerStatus.PROCESSING);
                        processBatch(batch);
                         // force a fire status to update the controls counter
                        fireStatus();
                    } else {
                        // Before going IDLE, post an empty diagnostic to force
                        // an update of the global diagnostic
                        if (additionnalDiagnosticToPost) {
                            postDiagnostic(new DiagnosticCollector(""));
                            // force a fire status to update the controls counter
                            fireStatus();
                            additionnalDiagnosticToPost = false;
                        }
                        changeStatus(AuditRunnerStatus.IDLE);
        
                        Thread.sleep(500);
                    }
                    break;
                case SUSPEND:
                    // paused
                    changeStatus(AuditRunnerStatus.SUSPENDED);
                    // force a fire status to update the controls counter
                    fireStatus();
                    Thread.sleep(1000);
                    break;
        
                default:
                    break;
                }
            } catch (InterruptedException e) {
                Audit.LOG.debug(e);
            }
        }
    }

    @objid ("b676d93b-94a6-45e3-859d-1a1f1ceb63d5")
    public AuditRunner(CheckProgram checkProgram, AuditDiagnostic auditDiagnostic) {
        this.checkProgram = checkProgram;
        this.auditDiagnostic = auditDiagnostic;
        this.loopControl = LoopControlCommand.SUSPEND; // paused loop
    }

    @objid ("bfa118d1-b0b0-4952-ba2f-2f853654e7f4")
    private void processBatch(CheckBatch batch) {
        IDiagnosticCollector diagnostic = new DiagnosticCollector(batch.getJobId());
        for (IControl check : batch.getControls()) {
            try {
                check.run(diagnostic, batch.getElement());
            } catch (Exception e) {
                Audit.LOG.debug(e);
            }
        }
        postDiagnostic(diagnostic);
    }

    @objid ("04ae3093-b587-4307-927f-6f732ac47e6d")
    private void postDiagnostic(IDiagnosticCollector diagnostic) {
        this.auditDiagnostic.postDiagnostic(diagnostic.getEntries(), this.session);
    }

    @objid ("ff90750b-68f7-4aa1-9fad-afb79315a932")
    public void addAuditMonitor(IAuditMonitor listener) {
        this.auditMonitors.add(listener);
    }

    @objid ("b93ee2c4-3656-403c-a0b9-78c2e04d15d8")
    public void removeAuditMonitor(IAuditMonitor listener) {
        this.auditMonitors.remove(listener);
    }

    @objid ("6cb967b6-c0dc-4f5e-9eb4-3a980ff572fc")
    public void start(ICoreSession session) {
        this.session = session;
        this.loopControl = LoopControlCommand.RUN;
    }

    @objid ("3c5ce431-58bd-44e8-a638-50f23d559eb6")
    public void stop() {
        this.loopControl = LoopControlCommand.SUSPEND;
    }

    @objid ("03f6c7e9-ed35-420f-abcc-cf8a29372b39")
    public void terminate() {
        this.loopControl = LoopControlCommand.TERMINATE;
    }

    @objid ("6793eb51-180b-4566-8afe-c69ebb4f2a3b")
    protected void changeStatus(AuditRunnerStatus newStatus) {
        if (this.status != newStatus) {
            this.status = newStatus;
            fireStatus();
        }
    }

    @objid ("39fa48f1-af06-4d9a-8eff-cdcf7aaff673")
    protected void fireStatus() {
        int nbRules = this.checkProgram.size();
        boolean notify = false;
        if (nbRules == 0) {
            notify = true;
        } else if (nbRules > 100000) {
            if (nbRules % 100 == 0) {
                notify = true;
            }
        } else if (nbRules > 10000) {
            if (nbRules % 10 == 0) {
                notify = true;
            }
        } else {
            notify = true;
        }
        
        if (notify) {
            for (IAuditMonitor monitor : this.auditMonitors) {
                monitor.status(this.status, this.checkProgram.size());
            }
        }
    }

    @objid ("f1708dbb-a22a-48c3-a924-6b7096b9eae5")
    public int getCheckProgramSize() {
        return this.checkProgram.size();
    }

    @objid ("b985a442-2679-4df9-8cc2-587a1504ed37")
    private enum LoopControlCommand {
        TERMINATE,
        SUSPEND,
        RUN;
    }

}
