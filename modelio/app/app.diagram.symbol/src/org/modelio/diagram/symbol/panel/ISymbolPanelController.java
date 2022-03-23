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
package org.modelio.diagram.symbol.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("820519da-bd6d-4ea9-aa2c-ed3fa16a03af")
interface ISymbolPanelController {
    @objid ("b106a9c4-dd8d-4aa8-8c64-6cfd6607a47f")
    void onShowHelp();

    /**
     * Refresh the symbol panel
     */
    @objid ("66ac3574-57ce-453a-bff6-a4ba30226dce")
    void refreshView();

}
