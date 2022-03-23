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
package org.modelio.audit.engine.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("ef11eaf1-c4ec-462c-8c03-4ef2d9d345bb")
public abstract class AbstractRule implements IRule {
    @objid ("fbc5c37a-9f9c-46f9-a0a3-dae8f6ba37dc")
    protected int triggers = 0;

    /**
     * For performance's sake, IRules implementations have the current severity too.
     */
    @objid ("722e8b87-dd35-4135-a36e-409f9d37f58a")
    private AuditSeverity severity = AuditSeverity.AuditError;

    @objid ("caa559ca-5c8a-4c24-a8cd-455b7588c5cf")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    @objid ("1dcf90a4-255c-4a46-b4ef-1977fbb0c180")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    @objid ("c41e6eac-12e8-4559-bab8-208d6c0fa5b7")
    @Override
    public IControl getUpdateControl(MObject element) {
        return null;
    }

    @objid ("7f85af46-9ec7-4308-9214-de9fc2199b69")
    @Override
    public abstract String getRuleId();

    @objid ("b49baaec-e5ac-43ba-8d3a-6bfb39e350c9")
    public final void setTriggers(int value) {
        this.triggers = value;
    }

    @objid ("075fbbfe-bdfa-49d6-93ab-f5b0f93ad361")
    @Override
    public final AuditSeverity getSeverity() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.severity;
    }

    @objid ("7eddfb59-7a43-48ed-ad82-094d9c333324")
    @Override
    public final void setSeverity(AuditSeverity value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.severity = value;
        
    }

    @objid ("2fc55880-af76-41ac-85e7-43fb87c38164")
    @Override
    public IControl getDeleteControl(final MObject element) {
        return null;
    }

}
