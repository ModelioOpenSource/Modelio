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

package org.modelio.audit.engine;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AuditRunnerStatus;
import org.modelio.audit.service.IAuditService;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("aa52cefa-c781-4c11-86a5-0c9ab33fc578")
public class CheckElementRunner implements Runnable {
    @objid ("1daf304d-ad3b-473c-bbbb-19185b1ae848")
    private String jobId;

    @objid ("caf02e9a-eeff-4727-a0d2-3235bc7c42ad")
    private String status;

    @objid ("db0978c6-5bdf-44a1-8332-1e22e011a8d5")
    private List<MObject> selectedElements;

    @objid ("1af52af3-591c-4ce4-a65c-1c635336110b")
    private IAuditService auditService;

    @objid ("6a19525b-2789-4f97-89b1-784d38cc64a9")
    public CheckElementRunner(IAuditService auditService, List<MObject> selection, String jobId) {
        this.selectedElements = selection;
        this.auditService = auditService;
        this.jobId = jobId;
    }

    @objid ("7abd401d-3d30-4dbe-affb-42571de8cb29")
    @Override
    public void run() {
        List<MObject> elements = new ArrayList<>();
        for (MObject e : this.selectedElements) {
            if (e.isValid() && !e.getStatus().isRamc()) {
                collectElements(elements, e);
            }
        }
        postChecksToAuditEngine(elements);
        
        while (this.auditService.getAuditEngine().getAuditRunner().status == AuditRunnerStatus.PROCESSING) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                // ignore
            }
        }
        this.status = "";
    }

    @objid ("dda4240e-ee7d-450e-addd-957cd522ddf2")
    private void collectElements(List<MObject> collectedElements, MObject element) {
        collectedElements.add(element);
        for (MObject child : element.getCompositionChildren()) {
            //Avoid loops
            if (!collectedElements.contains(child))
                collectElements(collectedElements, child);
        }
        if (collectedElements.size() > 10000) {
            postChecksToAuditEngine(collectedElements);
        }
    }

    @objid ("6703ace2-24e7-4e55-9d58-0a2033317f43")
    public String getStatus() {
        return this.status;
    }

    @objid ("bf5b6477-bd81-4f8d-b645-31ed6649cead")
    private void postChecksToAuditEngine(List<MObject> elements) {
        for (MObject e : elements) {
            this.auditService.checkElement(e, this.jobId);
        }
        elements.clear();
    }

}
