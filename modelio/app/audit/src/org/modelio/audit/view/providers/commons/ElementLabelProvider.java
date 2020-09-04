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
import org.modelio.core.ui.swt.labelprovider.BrowserLabelProvider;
import org.modelio.core.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("fdfefb0c-a83a-48e5-ab34-4a6f4e1481cd")
public class ElementLabelProvider extends StyledCellLabelProvider {
    @objid ("df3f88f6-5a7e-47c1-830d-570622764c7a")
    private BrowserLabelProvider elementLabelProvider;

    @objid ("1bda739c-875a-4635-9bcc-59acbdb4ba2a")
    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString text = new StyledString();
        
        if (element instanceof IAuditEntry) {
            IAuditEntry diagnostic = (IAuditEntry) element;
            MObject modelObj = diagnostic.getElement();
        
            try {
                cell.setImage(this.elementLabelProvider.getImage(modelObj));
                text.append(this.elementLabelProvider.getStyledText(modelObj));
            } catch (DeadObjectException e) {
                // ignore
            }
        }
        
        cell.setText(text.toString());
        cell.setStyleRanges(text.getStyleRanges());
        
        super.update(cell);
    }

    @objid ("bdd04d4f-d941-47d2-ace4-bc2d5f67a96d")
    public ElementLabelProvider() {
        this.elementLabelProvider = new UniversalLabelProvider();
    }

}
