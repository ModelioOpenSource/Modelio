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

package org.modelio.audit.preferences.ui.labelproviders;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.ui.UIColor;

/**
 * Provide checkbox label
 */
@objid ("98886db9-d2e7-4cec-ad45-8f908f6ba9b4")
public class SummaryLabelProvider extends ColumnLabelProvider {
    @objid ("4099b556-1cf7-4997-85d9-1c699ed91ad7")
    private static final Color DISABLED_COLOR = new Color(Display.getCurrent(), 160, 160, 160);

    @objid ("fd3f3f5c-72ab-4de7-9328-030046bcc070")
    private IAuditConfigurationPlan configurationPlan;

    /**
     * @param configurationPlan the current configuration plan, in charge of i18n...
     */
    @objid ("82ac04d5-cf76-4004-9da4-1ec2fb8c8ad8")
    public SummaryLabelProvider(IAuditConfigurationPlan configurationPlan) {
        this.configurationPlan = configurationPlan;
    }

    @objid ("0926598a-40d3-4f95-b656-8988c17b212b")
    @Override
    public Image getImage(Object element) {
        return null;
    }

    @objid ("cdbf7626-046f-4a50-8efc-33bd20289d0f")
    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
    }

    @objid ("ea6dc766-0b51-46a3-99fa-4ca4c688bd52")
    @Override
    public String getText(Object element) {
        if (element instanceof AuditRule) {
            return this.configurationPlan.getDescription((AuditRule) element);
        }
        return "";
    }

    @objid ("11e09594-3d1d-4633-bdf2-7be4a68b17e3")
    @Override
    public Color getForeground(Object element) {
        if (element instanceof AuditRule) {
            if (((AuditRule) element).isEnabled()) {
                return UIColor.BLACK;
            }
        
            return SummaryLabelProvider.DISABLED_COLOR;
        }
        return super.getForeground(element);
    }

}
