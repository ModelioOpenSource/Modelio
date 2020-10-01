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

package org.modelio.diagram.elements.core.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;

/**
 * Command that does nothing and is always executable.
 * 
 * @author cmarin
 */
@objid ("6d48aef3-cab9-40ef-90e3-4b55a475053c")
public final class NoopCommand extends Command {
    /**
     * A readily usable instance.
     */
    @objid ("40a6da15-a565-4dbb-9acb-2de359a70e80")
    public static final NoopCommand INSTANCE = new NoopCommand();

    /**
     * Create a do nothing command.
     */
    @objid ("4a4adbf8-06c2-451d-8f97-9b63c3604a0f")
    public NoopCommand() {
    }

    @objid ("5768fb05-deb1-45df-8d43-8d900d3b5621")
    @Override
    public void execute() {
        // nothing to do
    }

    /**
     * If the other command is not null, discard itself and returns it.
     * Return itself in the other case.
     */
    @objid ("74826922-74e1-4cb7-b742-4d77e9dd769a")
    @Override
    public Command chain(Command command) {
        if (command != null) {
            return command;
        } else {
            return this;
        }
    }

}
