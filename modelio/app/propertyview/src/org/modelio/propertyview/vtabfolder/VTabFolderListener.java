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

package org.modelio.propertyview.vtabfolder;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.internal.SWTEventListener;

@objid ("84517c25-3d34-47dd-98a5-478f8fd2f2ff")
public interface VTabFolderListener extends SWTEventListener {
    /**
     * Sent when the user clicks on the close button of an item in the CTabFolder.  The item being closed is specified
     * in the event.item field. Setting the event.doit field to false will stop the CTabItem from closing.
     * When the CTabItem is closed, it is disposed.  The contents of the CTabItem (see CTabItem.setControl) will be
     * made not visible when the CTabItem is closed.
     * @see CTabItem#setControl
     * @param event an event indicating the item being closed
     */
    @objid ("d6f88066-d32b-49a9-b04e-8983175b6aab")
    void itemClosed(VTabFolderEvent event);

}
