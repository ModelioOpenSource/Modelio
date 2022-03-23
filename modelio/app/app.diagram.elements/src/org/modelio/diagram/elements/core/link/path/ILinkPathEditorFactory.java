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
package org.modelio.diagram.elements.core.link.path;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.ConnectionEditPart;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;

@objid ("a9402da1-4461-42bb-adb4-79d2db5082c5")
public interface ILinkPathEditorFactory {
    /**
     * Restore the connection anchors and routing constraint from a data object.
     * @param connectionEditPart the connection to restore
     */
    @objid ("82e06563-4ba5-480a-b12c-d86f38579190")
    ILinkPathEditor from(ConnectionEditPart connectionEditPart);

    /**
     * Restore the connection anchors and routing constraint from a data object.
     * @param connectionEditPart the connection to restore
     * @param backup the backup data
     */
    @objid ("dd87bbe1-c13d-46dc-8c64-e3005a0f7ec5")
    ILinkPathEditor from(ConnectionEditPart connectionEditPart, ConnectionState backup);

}
