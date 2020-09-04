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

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.modelio.audit.view.AuditView;
import org.modelio.audit.view.providers.AuditProviderFactory.AuditViewMode;

@objid ("c1bfcf20-94db-4362-a9aa-469c7e5d77cd")
public class SetFlatViewMode {
    @objid ("fc58e20d-0a77-454f-b5d4-b06fa1c86471")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) final MPart e) {
        if (e.getObject() instanceof AuditView) {
            AuditView auditView = (AuditView) e.getObject();
            auditView.setAuditViewMode(AuditViewMode.FLAT);
        }
    }

}
