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

package org.modelio.audit.preferences.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.preferences.ui.labelproviders.FirstLabelProvider;
import org.modelio.audit.preferences.ui.labelproviders.SeverityLabelProvider;
import org.modelio.audit.preferences.ui.labelproviders.StatusLabelProvider;
import org.modelio.audit.preferences.ui.labelproviders.SummaryLabelProvider;

@objid ("fd7afff2-42fb-427d-8f49-f9d0dc2fe03f")
public class AuditPropertyLabelProvider extends ColumnLabelProvider {
    @objid ("b94f586e-7e28-4807-ae4c-7b3fdef7e2c7")
    private ColumnLabelProvider[] providers;

    @objid ("767289e3-e0dc-4edd-b1c4-376aa353b754")
    public AuditPropertyLabelProvider(IAuditConfigurationPlan configurationPlan) {
        this.providers = new ColumnLabelProvider[] { new FirstLabelProvider(configurationPlan), new SeverityLabelProvider(), new StatusLabelProvider(), new SummaryLabelProvider(configurationPlan) };
    }

    @objid ("674a29f9-1086-4552-a61d-f007c1b876eb")
    @Override
    public void update(ViewerCell cell) {
        this.providers[cell.getColumnIndex()].update(cell);
    }

    @objid ("6ba47dc8-cb67-43b4-8b94-8ebe471f50f5")
    @Override
    public String getToolTipText(Object element) {
        return super.getToolTipText(element);
    }

}
