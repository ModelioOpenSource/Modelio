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
package org.modelio.audit.view.model;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.service.AuditSeverity;

@objid ("c2e689c5-1ee6-44b7-bc8a-ab97d4ae6555")
public class AuditRuleModel {
    @objid ("2688e4f8-4bb0-48be-a49a-799858899eac")
    public AuditSeverity severity;

    @objid ("85c62c2b-60e8-41bc-8584-048e4adac015")
    public final String rule;

    @objid ("8f7cf4f4-4715-4488-a8a2-9075cf332f7c")
    public final Collection<IAuditEntry> entries = new ArrayList<>();

    @objid ("bff1d125-4b8d-4e9f-80a7-32bf6c8c4c6e")
    public  AuditRuleModel(String rule, AuditSeverity severity) {
        this.rule = rule;
        this.severity = severity;
        
    }

    @objid ("713fd1c9-2803-4ef6-9fac-565f3175a4cb")
    public void addEntry(IAuditEntry entry) {
        this.entries.add(entry);
        if( this.severity.compareTo(entry.getSeverity()) < 0) {
            this.severity = entry.getSeverity();
        }
        
    }

}
