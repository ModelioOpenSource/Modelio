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

package org.modelio.audit.view.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.audit.service.IAuditService;

@objid ("6dc86ef3-5679-486d-bb58-2d3fc8ef1f17")
public class ClearResultsHandler {
    @objid ("9e1ab168-e3d7-4de7-a924-2f3ccd45dbf4")
    @Execute
    public void execute(IAuditService auditService) {
        auditService.getAuditEngine().pause();
        auditService.getAuditEngine().getAuditDiagnostic().clear();
        auditService.getAuditEngine().clearCheck();
        auditService.getAuditEngine().resume();
    }

}
