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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("ba02955a-415d-4c04-86d2-186407a815fe")
public interface IDiagnosticCollector {
    @objid ("84581a79-5f67-4bb5-9b6d-fdcb84d69616")
    void addEntry(IAuditEntry entry);

    @objid ("3fc4cf81-4c48-4e47-8337-3094e29bdbe4")
    List<IAuditEntry> getEntries();

    @objid ("8bea0b62-0576-42ab-9bd7-fe9b0412baf3")
    void addEntries(List<IAuditEntry> entries);

}
