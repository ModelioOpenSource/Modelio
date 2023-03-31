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
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.modelio.api.modelio.editor.EditorType;
import org.modelio.api.modelio.editor.IMDATextEditor;
import org.modelio.editors.texteditors.IDocumentEditor;
import org.modelio.editors.texteditors.input.IDocumentInput;
import org.modelio.editors.texteditors.mdd.MDDEditor;
import org.modelio.editors.texteditors.rt.RTEditor;
import org.modelio.editors.texteditors.txt.TXTEditor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.rcp.inputpart.IInputPartService;

@objid ("7b46fecb-2a77-11e2-9fb9-bc305ba4815c")
public class EditionManager {
    @objid ("ab475608-2a77-11e2-9fb9-bc305ba4815c")
    private static final String BUNDLE_PREFIX = "bundleclass://";

    /**
     * The default charset name used to open an editor.
     */
    @objid ("1e2ad6af-efea-4653-8299-076a13317c92")
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";/*
     * // $NON-NLS-1$
     */
    

    @objid ("ab475603-2a77-11e2-9fb9-bc305ba4815c")
    private static final String PARENT_PART_ID = "org.modelio.app.center.parts";

    @objid ("ab47560d-2a77-11e2-9fb9-bc305ba4815c")
    private static final String URI_SEPARATOR = "/";

    @objid ("7b46fed5-2a77-11e2-9fb9-bc305ba4815c")
    private static final EditionManager INSTANCE = new EditionManager();

    @objid ("e2d85694-2fd8-11e2-a79f-bc305ba4815c")
    @Inject
    @Optional
    private IInputPartService inputservice;

    @objid ("7b48857b-2a77-11e2-9fb9-bc305ba4815c")
    public void activateEditor(final IMDATextEditor editor) {
        if (editor instanceof MDATextEditor) {
            MDATextEditor mdaEditor = (MDATextEditor) editor;
        
            MPart mPart = mdaEditor.getEditor();
            this.inputservice.showInputPart(mPart.getElementId(), mdaEditor.getFile().getAbsolutePath(), PartState.ACTIVATE);
        }
        
    }

    @objid ("7b48856b-2a77-11e2-9fb9-bc305ba4815c")
    public void closeEditor(final IMDATextEditor editor) {
        if (editor instanceof MDATextEditor) {
            MDATextEditor mdaEditor = (MDATextEditor) editor;
            mdaEditor.getEditor().setParent(null);
        }
        
    }

    /**
     * Open an editor for the given parameters.
     * @param modelElement the represented model element
     * @param file the file to open
     * @param editorTypeID the editor type
     * @param readonly whether the editor is read only
     * @param charsetName the file charset name. If <i>null</i> it will be the UTF-8 charset.
     * @param askedTitle the tab title. If <i>null</i> it will be the model element name.
     * @param askedTooltip the tab tooltip. If <i>null</i> it will be the file absolute path.
     * @return the opened editor
     */
    @objid ("e1f5a79a-6ceb-40da-a7a6-98bc8b77b234")
    public IMDATextEditor openEditor(final ModelElement modelElement, final File file, final EditorType editorTypeID, final boolean readonly, String charsetName, String askedTitle, String askedTooltip) {
        MPart inputPart = null;
        
        String partid = null;
        
        switch (editorTypeID) {
        case MDDEditor:
            partid = MDDEditor.EDITOR_ID;
            break;
        case RTEditor:
            partid = RTEditor.EDITOR_ID;
            break;
        case TXTEditor:
            partid = TXTEditor.EDITOR_ID;
            break;
        default:
            break;
        }
        
        MPart shownPart = this.inputservice.showInputPart(partid, file.getAbsolutePath(), PartState.ACTIVATE);
        
        String title = askedTitle;
        String tooltip = askedTooltip;
        if (title == null && modelElement != null) {
            title = modelElement.getName();
        }
        if (tooltip == null) {
            tooltip = file.getAbsolutePath();
        }
        
        shownPart.setLabel(title);
        shownPart.setTooltip(tooltip);
        
        if (shownPart instanceof MPlaceholder) {
            inputPart = (MPart) ((MPlaceholder) shownPart).getRef();
        } else {
            inputPart = shownPart;
        }
        
        if (inputPart != null && inputPart.getObject() != null) {
            IDocumentEditor editor = (IDocumentEditor) inputPart.getObject();
            IDocumentInput input = editor.getDocumentInput();
            editor.setReadonlyMode(readonly);
            input.setCharsetName(charsetName);
        
            MDATextEditor proxy = new MDATextEditor(input, inputPart, editorTypeID);
            proxy.setElement(modelElement);
            return proxy;
        }
        return null;
    }

    @objid ("7b46fed6-2a77-11e2-9fb9-bc305ba4815c")
    public static synchronized EditionManager services() {
        return EditionManager.INSTANCE;
    }

    /**
     * Singleton c'tor.
     */
    @objid ("7b46fed2-2a77-11e2-9fb9-bc305ba4815c")
    private  EditionManager() {
        super();
    }

}
