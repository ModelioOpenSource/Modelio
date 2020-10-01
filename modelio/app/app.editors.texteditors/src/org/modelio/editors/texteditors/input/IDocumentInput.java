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

package org.modelio.editors.texteditors.input;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.IDocument;
import org.modelio.editors.service.IEditorListener;

@objid ("7b54ba73-2a77-11e2-9fb9-bc305ba4815c")
public interface IDocumentInput extends IInput {
    @objid ("7b54ba74-2a77-11e2-9fb9-bc305ba4815c")
    IDocument getDocument(IDocument document);

    @objid ("7b54ba77-2a77-11e2-9fb9-bc305ba4815c")
    File getFile();

    @objid ("7b54ba79-2a77-11e2-9fb9-bc305ba4815c")
    void doSave();

    @objid ("7b54ba7a-2a77-11e2-9fb9-bc305ba4815c")
    void doLoad();

    @objid ("7b54ba7b-2a77-11e2-9fb9-bc305ba4815c")
    boolean isDirty();

    @objid ("c1ec211c-2e5d-11e2-a8ff-bc305ba4815c")
    void setSaveListener(IEditorListener listener);

    @objid ("c1ec211e-2e5d-11e2-a8ff-bc305ba4815c")
    void dispose();

    /**
     * Get the current file charset. Default value is <b>UTF-8</b>.
     */
    @objid ("0e3b5f67-b67b-4135-a121-53b1deeadbe5")
    String getCharsetName();

    /**
     * Change current charset and reload the file.
     */
    @objid ("202c375f-a2ae-423e-bdb0-21d1a748082d")
    void setCharsetName(String charsetName);

}
