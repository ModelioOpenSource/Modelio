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

package org.modelio.audit.engine;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AuditRunnerStatus;
import org.modelio.audit.service.IAuditService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * AuditJobRunner
 * <p>
 * Runnable that performs an audit on a set of elements and their composed children.<br/>
 * The produced audit results are made available in the global audit results but they are tagged with a job identifier that allows to distinguish them and to provide a 'filtered' audit report that only display the audit job results.
 * </p>
 */
@objid ("aa52cefa-c781-4c11-86a5-0c9ab33fc578")
public class AuditJobRunner implements Runnable {
    @objid ("1daf304d-ad3b-473c-bbbb-19185b1ae848")
    private String jobId;

    @objid ("04ab4480-5c23-4d59-9475-a3b85676ee54")
    private static final int BATCH_SIZE = 10000;

    @objid ("db0978c6-5bdf-44a1-8332-1e22e011a8d5")
    private List<MObject> selectedElements;

    @objid ("1af52af3-591c-4ce4-a65c-1c635336110b")
    private IAuditService auditService;

    /**
     * C'Tor
     * 
     * @param auditService - the audit service. Provides both the audit engine that will run the checks and the global audit diagnotic where results will be stored.
     * @param selectedElements - the elements that have to be audited by this audit job run.
     * @param jobId - the identifier of the job (used to further filter the results)
     */
    @objid ("6a19525b-2789-4f97-89b1-784d38cc64a9")
    public AuditJobRunner(IAuditService auditService, List<MObject> selectedElements, String jobId) {
        this.selectedElements = selectedElements;
        this.auditService = auditService;
        this.jobId = jobId;
    }

    /**
     * Execute the audit job. This method will only return when all the audit job checks have been performed.
     */
    @objid ("7abd401d-3d30-4dbe-affb-42571de8cb29")
    @Override
    public void run() {
        List<MObject> collectedElements = new ArrayList<>();
        for (MObject e : this.selectedElements) {
            if (e.isValid() && !e.getStatus().isRamc()) {
                collectElements(collectedElements, e);
            }
        }
        postChecksToAuditEngine(collectedElements);
        
        // Wait until the audit job is done.
        while (this.auditService.getAuditEngine().getAuditRunner().status == AuditRunnerStatus.PROCESSING) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                // ignore
            }
        }
    }

    /**
     * Recursively collect the audit jobs elements and their composition children.
     * 
     * Each time the size of the collected elements lists reaches or exceeds BATCH_SIZE, the collected elements are immediately queued for checking to the audit engine so that checking can start while the collecting process contnues.
     * 
     * @param collectedElements - the collected elements receiving list.
     * @param element - the element to explore (composition)
     */
    @objid ("dda4240e-ee7d-450e-addd-957cd522ddf2")
    private void collectElements(List<MObject> collectedElements, MObject element) {
        collectedElements.add(element);
        for (MObject child : element.getCompositionChildren()) {
            // Avoid loops
            if (!collectedElements.contains(child))
                collectElements(collectedElements, child);
        }
        if (collectedElements.size() > BATCH_SIZE) {
            postChecksToAuditEngine(collectedElements);
            collectedElements.clear();
        }
    }

    /**
     * Enqueue the elements to the audit engine, meaning that these elements will be checked by the audit engine. Note that the jobId is used to tags these enqueued entries.
     * 
     * @param elements - the elements to enqueue for checking.
     */
    @objid ("bf5b6477-bd81-4f8d-b645-31ed6649cead")
    private void postChecksToAuditEngine(List<MObject> elements) {
        for (MObject e : elements) {
            this.auditService.checkElement(e, this.jobId);
        }
    }

}
