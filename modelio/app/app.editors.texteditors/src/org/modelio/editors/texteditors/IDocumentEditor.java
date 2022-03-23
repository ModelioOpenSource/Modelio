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
package org.modelio.editors.texteditors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.TextViewer;
import org.modelio.editors.texteditors.input.IDocumentInput;

@objid ("7b502698-2a77-11e2-9fb9-bc305ba4815c")
public interface IDocumentEditor {
    @objid ("7b502699-2a77-11e2-9fb9-bc305ba4815c")
    IDocumentInput getDocumentInput();

    @objid ("c1d84aef-2e5d-11e2-a8ff-bc305ba4815c")
    void setReadonlyMode(boolean readOnly);

    /**
     * Get the TextViewer that supports this editor. The exact nature of the TextViewer depends on the kind of editor.
     * @return
     */
    @objid ("f917d94f-d4c3-443d-a40e-82e02a9b5eb0")
    TextViewer getViewer();

}
