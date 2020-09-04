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

package org.modelio.audit.view.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.audit.service.IAuditService;

/**
 * Handler for the Suspend/Resume action
 */
@objid ("79e31e2d-9295-4bec-a226-122ab12d33db")
public class ResumeAuditHandler {
    @objid ("8a0c634c-a221-4e18-bb35-f5ae5ef10cf3")
    @Execute
    public void execute(IAuditService auditService) {
        auditService.getAuditEngine().resume();
    }

}
