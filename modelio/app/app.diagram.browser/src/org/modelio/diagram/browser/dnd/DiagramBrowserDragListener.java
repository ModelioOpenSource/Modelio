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

package org.modelio.diagram.browser.dnd;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;

@objid ("6b017796-1ad8-48ba-987a-f2123a42fd5a")
public class DiagramBrowserDragListener implements DragSourceListener {
    @objid ("17f93d8a-f166-4c7d-a5d5-34c413cbd911")
    private final TreeViewer viewer;

    @objid ("14c4dc81-12c5-49cd-9a46-15ed59341e0e")
    public DiagramBrowserDragListener(TreeViewer viewer) {
        this.viewer = viewer;
    }

    @objid ("f759c1a3-2650-4b51-a25c-3c166cb05444")
    @Override
    public void dragStart(DragSourceEvent event) {
        LocalSelectionTransfer.getTransfer().setSelection(this.viewer.getSelection());
    }

    @objid ("058e1a5f-bc24-41d9-9a7b-19ad599ce14a")
    @Override
    public void dragSetData(DragSourceEvent event) {
        if (LocalSelectionTransfer.getTransfer().isSupportedType(event.dataType)) {           
            event.data=LocalSelectionTransfer.getTransfer().getSelection();
        }
    }

    @objid ("ad527feb-3cfc-4fa6-b120-5b1e383b771c")
    @Override
    public void dragFinished(DragSourceEvent event) {
        // Nothing to do
    }

}
