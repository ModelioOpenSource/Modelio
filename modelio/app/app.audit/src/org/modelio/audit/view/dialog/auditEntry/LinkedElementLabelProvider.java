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

package org.modelio.audit.view.dialog.auditEntry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.modelio.platform.model.ui.swt.labelprovider.BrowserLabelProvider;
import org.modelio.platform.model.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class provide the labels and the images to display for each linked element that is present in an audit entry.
 */
@objid ("61421ab6-0045-4473-8b3c-034cc11e87e8")
class LinkedElementLabelProvider extends StyledCellLabelProvider {
    @objid ("135b576f-99eb-44a4-937e-1b954ae06412")
    private BrowserLabelProvider elementLabelProvider;

    @objid ("c2b086b9-e51a-4759-907f-86d74119fc86")
    public LinkedElementLabelProvider() {
        this.elementLabelProvider = new UniversalLabelProvider();
    }

    @objid ("c0e687b8-f880-45c7-9d0a-7496335b6298")
    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        
        if (element instanceof MObject) {
            try {
                StyledString text = this.elementLabelProvider.getStyledText(element);
        
                cell.setText(text.toString());
                cell.setStyleRanges(text.getStyleRanges());
                cell.setImage(this.elementLabelProvider.getImage(element));
            } catch (DeadObjectException e) {
                // ignore
            }
        }
        
        super.update(cell);
    }

}
