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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.spi.mm.IMofSession;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * Fixes BpmnDataAssociation 'SourceRef' and 'TargetRef' that are swapped in some cases.
 * @author cma
 */
@objid ("ca7c3694-05a1-42d7-b300-4e1949a50409")
class DataAssociationFixer {
    @objid ("1bc7fe87-c8ce-4c5a-a33d-7954dd35960d")
    private final IMofSession mofSession;

    @objid ("07e0c16e-dfb7-40e2-abce-4fb642d117aa")
    private final MM mm;

    @objid ("63809094-5c53-4203-abe1-ce3c3174d8f5")
    private PrintWriter logger;

    @objid ("845e51a8-8121-455b-aa9a-cccdf68b7e34")
    public DataAssociationFixer(IMofSession mofSession, MM mm) {
        this.mofSession = mofSession;
        this.mm = mm;
        this.logger = mofSession.getReport().getLogger();
    }

    @objid ("0569f997-bf6e-4d21-ad68-969caeb06a5c")
    public void run() {
        this.logger.println("  Fixing BpmnDataAssociations ...");
        for (MofSmObjectImpl dataAssoc : this.mofSession.findByClass(this.mm.bpmnDataAssociationMC, true)) {
            MofSmObjectImpl StartingActivity = dataAssoc.getSingleDep("StartingActivity");
            MofSmObjectImpl EndingActivity = dataAssoc.getSingleDep("EndingActivity");
            dataAssoc.getSingleDep("StartingEvent");
            dataAssoc.getSingleDep("EndingEvent");
            MofSmObjectImpl SourceRef = dataAssoc.getSingleDep("SourceRef");
            MofSmObjectImpl TargetRef = dataAssoc.getSingleDep("TargetRef");
            
            if (StartingActivity != null && SourceRef != null) {
                this.logger.format("    Fixing %s : Setting %s source as target.\n", dataAssoc, SourceRef);
                dumpDataAssociation(dataAssoc);
                dataAssoc.getDep("TargetRef").add(SourceRef);
                dataAssoc.getDep("SourceRef").remove(SourceRef);
            } else if (EndingActivity != null && TargetRef != null) {
                this.logger.format("    Fixing %s : Setting %s target as source.\n", dataAssoc, SourceRef);
                dumpDataAssociation(dataAssoc);
                dataAssoc.getDep("SourceRef").add(TargetRef);
                dataAssoc.getDep("TargetRef").remove(TargetRef);
            } else {
                this.logger.format("      %s looks OK.\n", dataAssoc);
                dumpDataAssociation(dataAssoc);
            }
        }
        this.logger.println("  Fixing BpmnDataAssociations done.");
    }

    @objid ("6ea88221-8cd5-4964-8585-05f06b526746")
    private void dumpDataAssociation(MofSmObjectImpl dataAssoc) {
        MofSmObjectImpl StartingActivity = dataAssoc.getSingleDep("StartingActivity");
        MofSmObjectImpl EndingActivity = dataAssoc.getSingleDep("EndingActivity");
        MofSmObjectImpl SourceRef = dataAssoc.getSingleDep("SourceRef");
        MofSmObjectImpl TargetRef = dataAssoc.getSingleDep("TargetRef");
        
        this.logger.format("      %s dump:\n", dataAssoc);
        this.logger.format("         - StartingActivity : %s .\n",  StartingActivity);
        this.logger.format("         - EndingActivity : %s .\n",  EndingActivity);
        this.logger.format("         - StartingEvent : %s .\n",  dataAssoc.getSingleDep("StartingEvent"));
        this.logger.format("         - EndingEvent : %s .\n",  dataAssoc.getSingleDep("EndingEvent"));
        this.logger.format("         - SourceRef : %s .\n",  SourceRef);
        this.logger.format("         - TargetRef : %s .\n",  TargetRef);
    }

}
