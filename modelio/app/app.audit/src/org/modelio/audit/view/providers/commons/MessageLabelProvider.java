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
package org.modelio.audit.view.providers.commons;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.view.model.DiagnosticFormatter;
import org.modelio.platform.model.ui.swt.labelprovider.UniversalLabelProvider;

@objid ("cfc9e28d-b17d-4677-afdf-437ef1d1a082")
public class MessageLabelProvider extends StyledCellLabelProvider {
    @objid ("48463d46-c9bb-4e5e-9ca1-0e2d4201b461")
    private UniversalLabelProvider bp;

    @objid ("6b8c67aa-1d21-4ee8-801d-8b6dea6a3ad1")
    private IAuditConfigurationPlan auditConfigurationPlan;

    @objid ("d80538cd-3094-4456-b972-6de22909ff02")
    public  MessageLabelProvider(IAuditConfigurationPlan auditConfigurationPlan) {
        this.bp = new UniversalLabelProvider();
        this.auditConfigurationPlan = auditConfigurationPlan;
        
    }

    @objid ("b2beadfc-9cf9-4d8c-ab4b-f050a373570c")
    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString text = new StyledString();
        
        if (element instanceof IAuditEntry) {
            IAuditEntry entry = (IAuditEntry) element;
            text.append(DiagnosticFormatter.getStyledMessage(entry, this.bp, this.auditConfigurationPlan));
        }
        
        cell.setText(text.toString());
        cell.setStyleRanges(text.getStyleRanges());
        
        super.update(cell);
        
    }

}
