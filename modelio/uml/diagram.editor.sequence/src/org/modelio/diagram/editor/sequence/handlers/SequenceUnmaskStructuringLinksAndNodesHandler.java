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

package org.modelio.diagram.editor.sequence.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.modelio.diagram.editor.handlers.unmask.UnmaskStructuringLinksAndNodesHandler;

@objid ("8d9dc8b1-89bc-4cb7-8843-cbec4bf33f54")
public class SequenceUnmaskStructuringLinksAndNodesHandler extends UnmaskStructuringLinksAndNodesHandler {
    @objid ("c7a6a85a-a17c-4edc-ac96-40e98d7b5531")
    @CanExecute
    public boolean canExecute() {
        return false;
    }

}
