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

package org.modelio.audit.engine.core;

import java.util.Calendar;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("6cd598b4-c286-498b-8819-cee7248915d2")
public final class AuditEntry implements IAuditEntry {
    @objid ("4bfdfb7a-02c8-4c36-90ab-1b3bc12cdda6")
    private final long timestamp;

    @objid ("b8db7e73-7851-45dd-a5d8-1b38409443f8")
    private String ruleId = "";

    @objid ("394321a1-0bad-468b-af41-8cdd9231ba41")
    private String jobId;

    @objid ("ac6b3ae6-ba24-4eca-97f1-fc1f7d84f9b0")
    private AuditSeverity severity = null;

    @objid ("55a02ba5-1145-41d5-a8bd-dcbbc4a93d08")
    private MObject element = null;

    @objid ("25205598-0720-44ea-9823-f1c459a9c655")
    private List<Object> linkedObjects = null;

    @objid ("fc5e199f-decb-40ec-8a4a-d3173c23bd62")
    public AuditEntry(String ruleId, AuditSeverity severity, MObject element, List<Object> linkedObjects) {
        this.ruleId = ruleId;
        this.severity = severity;
        this.element = element;
        this.linkedObjects = linkedObjects;
        this.timestamp = Calendar.getInstance().getTimeInMillis();
        this.jobId = "";
    }

    @objid ("54b41b5d-7fd7-4f08-8527-ff9c20552e08")
    @Override
    public MObject getElement() {
        return this.element;
    }

    @objid ("a89e68c6-c775-431b-a168-1f2b1a702c26")
    @Override
    public String getRuleId() {
        return this.ruleId;
    }

    @objid ("bf2e9f73-4bd1-48f7-ab2b-828eadbf27d2")
    @Override
    public AuditSeverity getSeverity() {
        return this.severity;
    }

    @objid ("a99b9072-9741-40d1-96bb-cf961093348e")
    @Override
    public void setSeverity(AuditSeverity severity) {
        this.severity = severity;
    }

    @objid ("7ccf0883-3480-409e-a231-486e8d80b207")
    @Override
    public boolean equals(Object other) {
        if (other instanceof AuditEntry) {
            if (this.element.equals(((AuditEntry) other).getElement()) &&
                    this.ruleId.equals(((AuditEntry) other).getRuleId())) {
                return true;
            }
        }
        return false;
    }

    @objid ("c7890861-9da6-42a0-a09d-e0fc7e6c89ef")
    public void setLinkedInfos(List<Object> linkedObjects) {
        this.linkedObjects = linkedObjects;
    }

    @objid ("92109b88-9176-4a95-850c-f3ad80cc677c")
    @Override
    public List<Object> getLinkedObjects() {
        return this.linkedObjects;
    }

    @objid ("9e6e4109-ac1a-4ca2-94bf-aaf396498f3d")
    @Override
    public long getTimestamp() {
        return this.timestamp;
    }

    @objid ("0fff395d-567f-4dbc-ac7c-ebfa8e2c09a6")
    @Override
    public String getJobId() {
        return this.jobId;
    }

    @objid ("683a8287-82d0-44ef-bf93-2f3914a36d1f")
    @Override
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @objid ("363379fd-6807-40b2-ab63-8a1edabd630c")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.element == null) ? 0 : this.element.hashCode());
        result = prime * result + ((this.jobId == null) ? 0 : this.jobId.hashCode());
        result = prime * result + ((this.ruleId == null) ? 0 : this.ruleId.hashCode());
        return result;
    }

}
