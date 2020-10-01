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

package org.modelio.editors.service;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("c1e913df-2e5d-11e2-a8ff-bc305ba4815c")
public interface IEditorListener {
    @objid ("c1e913e0-2e5d-11e2-a8ff-bc305ba4815c")
    void documentSaved(File file);

    @objid ("c1e913e3-2e5d-11e2-a8ff-bc305ba4815c")
    void editorClosed();

}
