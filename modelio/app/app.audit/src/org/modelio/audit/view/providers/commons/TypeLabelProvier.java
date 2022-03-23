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

@objid ("6990cb60-1736-415d-b958-422abf95d991")
public class TypeLabelProvier extends StyledCellLabelProvider {
    @objid ("9a80d027-8cbb-4009-9eff-498f0963a03d")
    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString text = new StyledString();
        
        if (element instanceof IAuditEntry) {
            IAuditEntry entry = (IAuditEntry) element;
            cell.setImage(entry.getSeverity().getImage());
        }
        
        cell.setText(text.toString());
        super.update(cell);
        
    }

}
