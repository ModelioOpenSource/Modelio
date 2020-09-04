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

package org.modelio.ui.htmleditor.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.ui.htmleditor.HtmlComposer;

/**
 * A command is a wrapper for a JavaScript command which is executed in the context of a {@link HtmlComposer}.
 */
@objid ("942d1118-6719-4eb9-a666-f67b62831336")
public abstract class Command implements ICommand {
    @objid ("e3295387-1bea-4a07-8827-847a40e881a7")
    private final String name;

    @objid ("22616619-d721-4ce8-a9ed-03b9f01292c4")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("07559473-894c-47f7-9266-7397488eae64")
    public Command(String name) {
        this.name = name;
    }

    @objid ("4b8ce4c9-2757-48f8-b0dc-53ca9a9eefb3")
    @Override
    public String toString() {
        return "Command " + this.name + " {" + getScript() + "}";
    }

    @objid ("d944dc8e-19ff-4bcf-9908-82e4565867f3")
    @Override
    public abstract String getScript();

}
