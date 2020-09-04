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

package org.modelio.audit.preferences.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.service.AuditSeverity;

@objid ("8e921541-1ecd-4560-9ab2-1ceeb48215b7")
public class AuditRule {
    @objid ("99209ddd-9f5c-4e1c-bb3d-1268c9d86a3b")
    private boolean enabled;

    @objid ("98a86836-9c21-497f-95ee-cc94d9e95770")
    private String id;

    @objid ("1b5363f2-08d0-4ec5-98a6-d000deb54310")
    private AuditSeverity severity;

    @objid ("9a95a5ab-c697-426a-83a0-c1b85a1ccfc8")
    private String implClass;

    @objid ("09bf2625-960a-456e-8575-5f12917db9e9")
    public AuditRule(String id, AuditSeverity severity, boolean enabled, String implClass) {
        this.id = id;
        this.severity = severity;
        this.enabled = enabled;
        this.implClass = implClass;
    }

    @objid ("fa25e98e-e51f-4c0c-b934-1d242d38a43d")
    public AuditRule(AuditRule anotherRule) {
        this.id = anotherRule.id;
        this.severity = anotherRule.severity;
        this.enabled = anotherRule.enabled;
        this.implClass = anotherRule.implClass;
    }

    @objid ("86be4dee-1ccc-4edf-928a-66845666c654")
    public String getId() {
        return this.id;
    }

    @objid ("064731e4-0fc0-49b6-a4e5-034f9190b83a")
    public AuditSeverity getSeverity() {
        return this.severity;
    }

    @objid ("539b5eeb-3e87-4c76-bf89-949ef11df34d")
    public void setSeverity(AuditSeverity severity) {
        this.severity = severity;
    }

    @objid ("b30c9f30-eb19-4464-8cf9-48d6ec87829f")
    public boolean isEnabled() {
        return this.enabled;
    }

    @objid ("6262e28b-3faa-4553-b27b-0d0768c627e3")
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @objid ("e7a63e71-ac62-45bd-a3ed-9c7638fcda48")
    public String getImplClass() {
        return this.implClass;
    }

}
