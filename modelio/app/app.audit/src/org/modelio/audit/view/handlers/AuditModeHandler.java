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

package org.modelio.audit.view.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.audit.engine.AuditRunningMode;
import org.modelio.audit.service.IAuditService;

@objid ("b6127f75-92aa-4d13-8fe3-c2faae686b5a")
public class AuditModeHandler {
    @objid ("ff695ae0-af76-4255-a1aa-db78bd3ac5ef")
    @Execute
    public void execute(IAuditService auditService) {
        switch (auditService.getAuditEngine().getRunningMode()) {
        case AUTO:
            auditService.getAuditEngine().setRunningMode(AuditRunningMode.MANUEL);
            break;
        case MANUEL:
        default:
            auditService.getAuditEngine().setRunningMode(AuditRunningMode.AUTO);
            break;
        }
    }

}
