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
package org.modelio.platform.ui.htmleditor.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("9af47e18-04a7-49b0-85d9-e2c031582f21")
public class GetHtmlCommand extends Command {
    @objid ("d6a9808e-9846-44ff-ac54-becf4070486e")
    public  GetHtmlCommand() {
        super("GetHTML");
    }

    @objid ("b80ffdd1-5700-46c8-b88a-373d98586ebf")
    @Override
    public String getScript() {
        return  "return integration.editor.getData();";
    }

}
