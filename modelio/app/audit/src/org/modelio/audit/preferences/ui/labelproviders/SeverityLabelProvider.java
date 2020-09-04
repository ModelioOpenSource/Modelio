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
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.ui.UIColor;

/**
 * Provide checkbox label
 */
@objid ("62cd6592-83c6-4bb0-a551-3a1f8abd0d32")
public class SeverityLabelProvider extends ColumnLabelProvider {
    @objid ("fc951f8b-ed38-44e8-a315-405cc67033e9")
    public SeverityLabelProvider() {
    }

    @objid ("b61f9fec-5154-494c-ba75-bf8b465e7525")
    @Override
    public Image getImage(Object element) {
        if (element instanceof AuditRule) {
            final AuditSeverity severity = ((AuditRule) element).getSeverity();
            if (severity != null) {
                return severity.getImage();
            }
        }
        return null;
    }

    @objid ("2b100af2-a16a-4ade-89a7-a040d85addbc")
    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
    }

    @objid ("6518a19e-2444-4ae9-94a5-d5f5bb292422")
    @Override
    public String getText(Object element) {
        return "";
    }

    @objid ("e8437390-9596-4171-83c8-55d682c8cd61")
    @Override
    public Color getForeground(Object element) {
        if (element instanceof AuditRule) {
            if (((AuditRule) element).isEnabled()) {
                return UIColor.BLACK;
            }
        
            return new Color(Display.getCurrent(), 160, 160, 160);
        }
        return super.getForeground(element);
    }

}
