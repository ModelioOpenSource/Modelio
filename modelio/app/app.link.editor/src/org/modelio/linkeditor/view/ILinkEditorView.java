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

package org.modelio.linkeditor.view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.linkeditor.panel.ILinkEditor;

@objid ("6a150b80-a72e-445f-949f-368a29eab323")
public interface ILinkEditorView {
    @objid ("4d5a3ebb-57e5-4e82-950e-e661682c6899")
    public static final String POPUPID = "org.modelio.linkeditor.popupmenu";

    @objid ("1af6d31d-546f-46fa-bbba-f84abc888490")
    ILinkEditor getLinkEditor();

    @objid ("7b716597-298a-408d-9614-3c0fd9b1c966")
    void refreshFromCurrentSelection();

}
