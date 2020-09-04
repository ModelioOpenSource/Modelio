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

package org.modelio.audit.view.model;

import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.service.AuditSeverity;

@objid ("4b616a6c-5a20-4c04-8f0d-8bbf5c1f1248")
public class AuditTypeModel {
    @objid ("6bd2a050-c9cb-4b6b-8ddf-61a7e93f49e2")
    public AuditSeverity severity;

    @objid ("c3ccf4d5-5b8d-4bcc-b850-31b06d56a3a5")
    public Set<IAuditEntry> entries = new HashSet<>();

}
