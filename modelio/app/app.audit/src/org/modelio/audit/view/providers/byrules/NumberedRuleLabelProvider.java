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

package org.modelio.audit.view.providers.byrules;

import java.text.SimpleDateFormat;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.view.model.AuditRuleModel;

@objid ("26c1dcc6-372e-4dd1-9ba6-5e30022e34e6")
public class NumberedRuleLabelProvider extends StyledCellLabelProvider {
    @objid ("3eec002c-3d80-4666-aeb3-a6618edd5d42")
    private final SimpleDateFormat formatter = new SimpleDateFormat("H:mm:ss");

    @objid ("aedd5f98-f5e3-49fc-a8a0-836ed317e076")
    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString text = new StyledString();
        
        if (element instanceof AuditRuleModel) {
            AuditRuleModel entry = (AuditRuleModel) element;
            cell.setImage(entry.severity.getImage());
            text.append(entry.rule);
            text.append(" (" + entry.entries.size() + ")", StyledString.COUNTER_STYLER);
        
        } else if (element instanceof IAuditEntry) {
            IAuditEntry entry = (IAuditEntry) element;
            text.append(this.formatter.format(entry.getTimestamp()));
        }
        
        cell.setText(text.toString());
        cell.setStyleRanges(text.getStyleRanges());
        super.update(cell);
    }

}
