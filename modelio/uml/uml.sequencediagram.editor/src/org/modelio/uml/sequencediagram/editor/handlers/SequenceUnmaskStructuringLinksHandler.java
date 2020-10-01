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

package org.modelio.uml.sequencediagram.editor.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.modelio.diagram.editor.handlers.unmask.UnmaskStructuringLinksHandler;

@objid ("abc61b54-50b4-4def-8bb1-d2ec743c075c")
public class SequenceUnmaskStructuringLinksHandler extends UnmaskStructuringLinksHandler {
    @objid ("00ac8e70-4d63-4566-9189-655b5ab7047b")
    @CanExecute
    public boolean canExecute() {
        return false;
    }

}
