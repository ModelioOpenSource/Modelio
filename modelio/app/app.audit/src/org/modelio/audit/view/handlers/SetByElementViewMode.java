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

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.modelio.audit.view.AuditView;
import org.modelio.audit.view.providers.AuditProviderFactory.AuditViewMode;

@objid ("c18a5fc7-7792-4f24-b3c5-15ba20e3e440")
public class SetByElementViewMode {
    @objid ("7ae5d55f-094f-45e3-95c6-93114e41c6af")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) final MPart e) {
        if (e.getObject() instanceof AuditView) {
            AuditView auditView = (AuditView) e.getObject();
            auditView.setAuditViewMode(AuditViewMode.BYELEMENT);
        }
    }

}
