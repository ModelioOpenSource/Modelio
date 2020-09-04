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

package org.modelio.audit.view.providers.bytype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.view.model.AuditTypeModel;
import org.modelio.core.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("2f48b448-3be1-4ca8-902d-2ee8d599964d")
public class NumberedTypeLabelProvider extends StyledCellLabelProvider {
    @objid ("9a76f277-25e2-400a-975e-99ca42b7a836")
    private static final UniversalLabelProvider bp = new UniversalLabelProvider();

    @objid ("5df37ca2-cf5c-40de-9c08-61cf2472893c")
    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString text = new StyledString();
        
        if (element instanceof AuditTypeModel) {
            AuditTypeModel entry = (AuditTypeModel) element;
            cell.setImage(entry.severity.getImage());
            text.append(entry.severity.getLabel());
            text.append(" (" + entry.entries.size() + ")", StyledString.COUNTER_STYLER);
        
        } else if (element instanceof IAuditEntry) {
            IAuditEntry diagnostic = (IAuditEntry) element;
            MObject modelObj = diagnostic.getElement();
        
            try {
                cell.setImage(NumberedTypeLabelProvider.bp.getImage(modelObj));
                text.append(NumberedTypeLabelProvider.bp.getStyledText(modelObj));
            } catch (DeadObjectException e) {
                // ignore
            }
        
        }
        
        cell.setText(text.toString());
        cell.setStyleRanges(text.getStyleRanges());
        
        super.update(cell);
    }

}
