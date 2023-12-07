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
package org.modelio.audit.view.providers.byelement;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.view.model.AuditElementModel;
import org.modelio.platform.model.ui.swt.labelprovider.UniversalLabelProvider;

@objid ("9a1ac88b-a495-48a7-b342-b2b3c8fd7099")
public class NumberedElementLabelProvider extends StyledCellLabelProvider {
    @objid ("2cf54f43-f963-44d0-bb83-3a8f0d2fea32")
    private static final SimpleDateFormat formatter = new SimpleDateFormat("H:mm:ss");

    @objid ("bb83b884-4031-4b7a-b094-16cfddea7619")
    private static final UniversalLabelProvider bp = new UniversalLabelProvider();

    @objid ("6ad434dc-788b-45ec-9568-d6cf275f051f")
    private static final NumberFormat numFormat = NumberFormat.getNumberInstance();

    @objid ("0672105d-d785-47f9-9763-cb7c3598917d")
    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString text = new StyledString();
        
        if (element instanceof AuditElementModel) {
            AuditElementModel entry = (AuditElementModel) element;
            cell.setImage(bp.getImage(entry.element));
            text.append(bp.getStyledText(entry.element));
            text.append(" (" + numFormat.format(entry.entries.size()) + ")", StyledString.COUNTER_STYLER);
        
        } else if (element instanceof IAuditEntry) {
            IAuditEntry entry = (IAuditEntry) element;
            text.append(formatter.format(entry.getTimestamp()));
        }
        cell.setText(text.toString());
        cell.setStyleRanges(text.getStyleRanges());
        super.update(cell);
        
    }

}
