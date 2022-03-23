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

import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.service.AuditSeverity;

@objid ("c2e689c5-1ee6-44b7-bc8a-ab97d4ae6555")
public class AuditRuleModel {
    @objid ("2688e4f8-4bb0-48be-a49a-799858899eac")
    public AuditSeverity severity;

    @objid ("85c62c2b-60e8-41bc-8584-048e4adac015")
    public String rule;

    @objid ("8f7cf4f4-4715-4488-a8a2-9075cf332f7c")
    public Set<IAuditEntry> entries = new HashSet<>();

}
