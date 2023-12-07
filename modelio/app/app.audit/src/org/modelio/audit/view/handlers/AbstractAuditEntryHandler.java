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

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.modelio.audit.service.IAuditService;
import org.modelio.audit.view.AuditView;

@objid ("f2a051d0-c23a-4060-8608-cb68e7cdf717")
public abstract class AbstractAuditEntryHandler {
    @objid ("a686e3a0-749c-4e9c-9232-a73486e9175a")
    protected Object getSelectedAuditEntry(EModelService modelService, MApplication application) {
        List<MPart> parts = modelService.findElements(application, AuditView.VIEW_ID, MPart.class, Collections.emptyList());
        for (MPart part : parts) {
            if (part.getObject() instanceof AuditView) {
                AuditView view = (AuditView) part.getObject();
                return view.getSelectedDiagnosticEntry();
            }
        }
        return null;
    }

    @objid ("76202796-f776-4bd8-8698-11d15c3c0632")
    @Deprecated(forRemoval = true, since = "5.4.1")
    private void refreshAuditView(EModelService modelService, MApplication application, IAuditService auditService) {
        List<MPart> parts = modelService.findElements(application, AuditView.VIEW_ID, MPart.class, Collections.emptyList());
        for (MPart part : parts) {
            if (part.getObject() instanceof AuditView) {
                AuditView view = (AuditView) part.getObject();
                view.refresh(auditService.getAuditEngine().getAuditDiagnostic());
            }
        }
        
    }

}
