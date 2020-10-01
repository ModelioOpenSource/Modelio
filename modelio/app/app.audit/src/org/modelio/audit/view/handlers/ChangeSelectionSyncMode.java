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
import org.eclipse.e4.ui.model.application.ui.menu.MToolItem;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.audit.view.AuditView;

@objid ("3e691af8-9cf5-4dbd-947a-6a5a81f5bbb5")
public class ChangeSelectionSyncMode {
    @objid ("a5db926f-99d1-476b-a5bf-b4d3f9b595fc")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_PART) final MPart e, @Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection, MToolItem tool) {
        if (e.getObject() instanceof AuditView) {
            AuditView auditView = (AuditView) e.getObject();
            auditView.setSynchronizedSelectionMode(tool.isSelected());
        }
    }

}
