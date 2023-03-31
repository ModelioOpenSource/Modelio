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
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IDiagnosticCollector;

@objid ("729cd53b-3990-45d5-92ad-b9be80f9f175")
public class DiagnosticCollector implements IDiagnosticCollector {
    @objid ("e8a0eed2-5ee2-467e-9e93-d18889107aa3")
    private String jobId;

    @objid ("09ec3f95-daa3-48ef-b9e1-c2fc40492e30")
    private List<IAuditEntry> entries;

    @objid ("e01e0421-bfe8-4021-9163-9e36dcd63b95")
    @Override
    public List<IAuditEntry> getEntries() {
        return this.entries;
    }

    @objid ("90f84c48-4417-4ed5-a5a2-0fae08f90ffd")
    public  DiagnosticCollector(String jobId) {
        this.entries = new ArrayList<>();
        this.jobId = jobId;
        
    }

    @objid ("1800e34a-add2-4fa1-9c58-b1466df2dd3f")
    @Override
    public void addEntry(IAuditEntry entry) {
        entry.setJobId(this.jobId);
        
        this.entries.add(entry);
        
    }

    @objid ("16ed671b-1524-430e-b083-de1354beb1f3")
    @Override
    public void addEntries(List<IAuditEntry> entries) {
        for (IAuditEntry entry : entries) {
            entry.setJobId(this.jobId);
        }
        
        this.entries.addAll(entries);
        
    }

}
