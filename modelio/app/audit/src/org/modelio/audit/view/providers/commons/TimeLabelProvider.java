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

package org.modelio.audit.view.providers.commons;

import java.text.SimpleDateFormat;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.modelio.audit.engine.core.IAuditEntry;

@objid ("4aac0f60-7dca-4643-a66a-54572b924011")
public class TimeLabelProvider extends StyledCellLabelProvider {
    @objid ("356044f9-effb-4b54-9e66-5da3e70d6d94")
    private final SimpleDateFormat formatter = new SimpleDateFormat("H:mm:ss");

    @objid ("c759960b-9061-4471-b5e9-ee76830973e8")
    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString text = new StyledString();
        
        if (element instanceof IAuditEntry) {
            IAuditEntry entry = (IAuditEntry) element;
            text.append(this.formatter.format(entry.getTimestamp()));
        }
        
        cell.setText(text.toString());
        super.update(cell);
    }

}
