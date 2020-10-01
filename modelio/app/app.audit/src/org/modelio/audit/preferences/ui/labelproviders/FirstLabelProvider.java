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

package org.modelio.audit.preferences.ui.labelproviders;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.platform.ui.UIColor;

/**
 * Provide category label
 */
@objid ("b8dc1e05-d39c-4dd8-a7a8-9e6111a6ebac")
public class FirstLabelProvider extends ColumnLabelProvider {
    @objid ("845f821c-efad-4354-93d0-7e1917e55700")
    private static final Color DISABLED_COLOR = new Color(Display.getCurrent(), 160, 160, 160);

    @objid ("daaf630b-5d5a-4424-a44f-e27496eae4c2")
    private IAuditConfigurationPlan configurationPlan;

    @objid ("acc54876-ef32-4fe1-8b7b-07695e6e4d20")
    @Override
    public Image getImage(Object element) {
        if (element instanceof AuditCategory) {
            return this.configurationPlan.getImage((AuditCategory) element);
        }
        return null;
    }

    @objid ("d7d79e6e-457d-4353-9ba4-ffac659b1ce5")
    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
    }

    @objid ("2cbfe214-5671-4d49-8a6f-117bfcba188a")
    @Override
    public String getText(Object element) {
        if (element instanceof AuditCategory) {
            return this.configurationPlan.getLabel((AuditCategory) element);
        } else if (element instanceof AuditRule) {
            return this.configurationPlan.getLabel((AuditRule) element);
        }
        return "";
    }

    @objid ("d47dbca6-2f1c-4ce9-8639-5dc62b781afc")
    @Override
    public Color getForeground(Object element) {
        if (element instanceof AuditRule) {
            if (((AuditRule) element).isEnabled()) {
                return UIColor.BLACK;
            }
        
            return FirstLabelProvider.DISABLED_COLOR;
        }
        return super.getForeground(element);
    }

    /**
     * @param configurationPlan the current configuration plan, in charge of i18n...
     */
    @objid ("4e0db125-ca7e-48b8-8279-4789019d0c4e")
    public FirstLabelProvider(IAuditConfigurationPlan configurationPlan) {
        this.configurationPlan = configurationPlan;
    }

}
