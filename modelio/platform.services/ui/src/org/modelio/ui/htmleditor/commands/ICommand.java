/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.ui.htmleditor.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("743551cf-b662-4d5a-89c2-a7049cae4cae")
public interface ICommand {
    @objid ("50423ccc-0e5e-48f1-885d-d633b7066a3f")
    String getName();

    @objid ("611864f6-eee9-4095-87d2-9cb0f68387d1")
    String getScript();

}
