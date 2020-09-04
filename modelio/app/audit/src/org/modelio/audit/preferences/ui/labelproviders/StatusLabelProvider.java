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
import org.eclipse.swt.graphics.Image;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.model.AuditRule;

/**
 * Provide checkbox label
 */
@objid ("9c85767f-699d-4ca3-a309-5b4892c5df17")
public class StatusLabelProvider extends ColumnLabelProvider {
    @objid ("f0900a89-a51d-47ac-b981-e4356ab711f8")
    private static final Image ON = Audit.getImageDescriptor("icons/on.png").createImage();

    @objid ("afbe88b3-93a3-463d-9b7a-24096886d64f")
    private static final Image OFF = Audit.getImageDescriptor("icons/off.png").createImage();

    @objid ("bb80dc93-5169-4b45-89fe-4089d1a50e54")
    public StatusLabelProvider() {
    }

    @objid ("d8ea2074-df43-4b8d-8155-55928f5a58ca")
    @Override
    public Image getImage(Object element) {
        if (element instanceof AuditRule) {
            if (((AuditRule) element).isEnabled()) {
                return StatusLabelProvider.ON;
            } else {
                return StatusLabelProvider.OFF;
            }
        }
        return null;
    }

    @objid ("e974762f-3e54-46ee-b41e-7d5e4eb066ab")
    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
    }

    @objid ("cd4737ae-ba39-4c8b-893e-cefe92a5233a")
    @Override
    public String getText(Object element) {
        return null;
    }

}
