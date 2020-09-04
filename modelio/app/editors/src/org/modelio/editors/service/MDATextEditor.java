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

package org.modelio.editors.service;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.modelio.api.modelio.editor.EditorType;
import org.modelio.api.modelio.editor.IMDAEditorListener;
import org.modelio.api.modelio.editor.IMDATextEditor;
import org.modelio.editors.texteditors.IDocumentEditor;
import org.modelio.editors.texteditors.input.IDocumentInput;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("7b488590-2a77-11e2-9fb9-bc305ba4815c")
public class MDATextEditor implements IMDATextEditor, IEditorListener {
    @objid ("6ee9da1d-3007-11e2-a3ce-bc305ba4815c")
    private EditorType type;

    @objid ("7b4b92ad-2a77-11e2-9fb9-bc305ba4815c")
    private IMDAEditorListener listener = null;

    @objid ("7b4b92ae-2a77-11e2-9fb9-bc305ba4815c")
    private IDocumentInput oInput = null;

    @objid ("7b4b92af-2a77-11e2-9fb9-bc305ba4815c")
    private MPart editorPart;

    @objid ("c1060486-3003-11e2-a3ce-bc305ba4815c")
    private ModelElement element;

    @objid ("7b4b92b0-2a77-11e2-9fb9-bc305ba4815c")
    public MDATextEditor(IDocumentInput input, MPart part) {
        this.oInput = input;
        this.editorPart = part;
        this.oInput.setSaveListener(this);
    }

    @objid ("7b4b92b4-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public File getFile() {
        return this.oInput.getFile();
    }

    @objid ("7b4b92b9-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void save() {
        this.oInput.doSave();
    }

    @objid ("7b4b92bc-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void reload() {
        this.oInput.doLoad();
    }

    @objid ("7b4b92bf-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public boolean isDirty() {
        return this.oInput.isDirty();
    }

    @objid ("7b4b92c4-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void setReadonlyMode(boolean readOnly) {
        IDocumentEditor editor = (IDocumentEditor)this.editorPart.getObject();
        editor.setReadonlyMode(readOnly);
    }

    @objid ("7b4b92c8-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void setListener(IMDAEditorListener listener) {
        this.listener = listener;
    }

    @objid ("7b4b92cc-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public EditorType getType() {
        return this.type;
    }

    @objid ("7b4d194e-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public ModelElement getElement() {
        return this.element;
    }

    @objid ("7b4d1953-2a77-11e2-9fb9-bc305ba4815c")
    public MPart getEditor() {
        return this.editorPart;
    }

    @objid ("c1cd9c8a-2e5d-11e2-a8ff-bc305ba4815c")
    @Override
    public void documentSaved(File file) {
        if (this.listener != null) {
            this.listener.documentSaved(this, this.element, file);
        }
    }

    @objid ("c1cd9c8f-2e5d-11e2-a8ff-bc305ba4815c")
    @Override
    public void editorClosed() {
        if (this.listener != null) {
            this.listener.editorClosed(this);
        }
        this.listener = null;
    }

    @objid ("c10a986e-3003-11e2-a3ce-bc305ba4815c")
    protected void setElement(ModelElement element) {
        this.element = element;
    }

    @objid ("6ee238f8-3007-11e2-a3ce-bc305ba4815c")
    protected void setType(EditorType type) {
        this.type = type;
    }

    @objid ("45086418-e24c-448c-80c3-024dbc8b08c4")
    @Override
    public void setCharsetName(String charsetName) {
        this.oInput.setCharsetName(charsetName);
    }

}
