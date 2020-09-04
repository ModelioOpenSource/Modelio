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

package org.modelio.metamodel.impl.mmextensions.standard.migration.from_bpmn_36;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.spi.mm.IMofSession;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * Migrates diagrams owned by a BPMN Process or a BPMN Behavior.
 * <p>
 * If all displayed elements belong to a BPMN Process, the diagram is moved to that process
 * and transmuted to a process design diagram.
 * In the other case the diagram is moved into the Collaboration and transmuted to a collaboration diagram.
 */
@objid ("50488730-b267-4467-90c6-b0a412629012")
class OwnedDiagramsMigrator {
    @objid ("90b0dcb4-2b72-4cbf-8de0-06921aa7565c")
    private final MM mm;

    @objid ("bca8ccdf-d277-49c7-9310-c3ccceee71d0")
    public OwnedDiagramsMigrator(MM mm) {
        this.mm = mm;
    }

    @objid ("7573ab6b-cdc3-413e-942d-e8f5ebf1301b")
    public void run(IMofSession mofSession, MofSmObjectImpl owner, MofSmObjectImpl collaboration) {
        @SuppressWarnings ("resource")
        PrintWriter logger = mofSession.getReport().getLogger();
        List<MofSmObjectImpl> wProcessDiagrams = owner.getDep("Product");
        
        logger.format("    Migrating diagrams under %s ...\n", owner);
        
        for (MofSmObjectImpl diagram : new ArrayList<>(wProcessDiagrams)) {
            boolean isBpmnDiagram = this.mm.bpmnProcessCollaborationDiagramMC.isInstance(diagram);
        
            MofSmObjectImpl representedProcess = getDiagramRepresentedProcess(diagram);
            logger.format("     - %s displays elements from %s\n", diagram, representedProcess != null ? representedProcess : " no or many BPMN Processes");
            if (Objects.equals(owner, representedProcess)) {
                if (isBpmnDiagram) {
                    // transmute and stay here
                    mofSession.transmute(diagram, this.mm.bpmnProcessDesignDiagramMC);
                }
            } else if (representedProcess != null) {
                if (isBpmnDiagram) {
                    logger.format("        Move %s to %s and transmute to process design diagram.\n", diagram, collaboration);
                    // Move and transmute
                    wProcessDiagrams.remove(diagram);
                    representedProcess.getDep("Product").add(diagram);
                    mofSession.transmute(diagram, this.mm.bpmnProcessDesignDiagramMC);
                } else {
                    logger.format("        Move %s to %s.\n", diagram, collaboration);
                    // Just move
                    wProcessDiagrams.remove(diagram);
                    representedProcess.getDep("Product").add(diagram);
                }
            } else if (collaboration != null) {
                if (isBpmnDiagram) {
                    // Move and transmute to Collaboration diagram
                    logger.format("        Move %s to %s and transmute to collaboration diagram.\n", diagram, collaboration);
                    wProcessDiagrams.remove(diagram);
                    collaboration.getDep("Product").add(diagram);
                    mofSession.transmute(diagram, this.mm.bpmnCollaborationDiagramMC);
                } else {
                    logger.format("        Move %s to %s.\n", diagram, collaboration);
                    // Just move
                    wProcessDiagrams.remove(diagram);
                    collaboration.getDep("Product").add(diagram);
                }
            } else {
                if (isBpmnDiagram) {
                    logger.format("       WARN : Cannot move %s away from %s , no BPMN Collaboration available.\n", diagram, owner);
                    mofSession.transmute(diagram, this.mm.bpmnCollaborationDiagramMC);
                }
            }
        }
    }

    @objid ("b5dd4af0-7856-4855-b12d-33ec37f10663")
    private MofSmObjectImpl getDiagramRepresentedProcess(MofSmObjectImpl diagram) {
        MofSmObjectImpl proc = null;
        
        for (MofSmObjectImpl el : diagram.getDep("Represented")) {
            MofSmObjectImpl elProc = getProcessOwner(el, 0);
            if (proc == null) {
                proc = elProc;
            } else if (!Objects.equals(proc, elProc)) {
                return null;
            }
        }
        return proc;
    }

    @objid ("3e8567bd-174d-4111-ad69-3368b6f1522e")
    private MofSmObjectImpl getProcessOwner(MObject bpmnEl, int shield) {
        if (bpmnEl == null) {
            return null;
        }
        if (this.mm.processMclass.isInstance(bpmnEl)) {
            return (MofSmObjectImpl) bpmnEl;
        } else if (shield < 100) {
            return getProcessOwner(bpmnEl.getCompositionOwner(), shield + 1);
        } else {
            throw new IllegalArgumentException("Cycle in ownership tree of " + bpmnEl);
        }
    }

}
