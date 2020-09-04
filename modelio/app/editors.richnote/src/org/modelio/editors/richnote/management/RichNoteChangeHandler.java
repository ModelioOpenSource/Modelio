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

package org.modelio.editors.richnote.management;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.editors.richnote.management.EditorsRegistry.RichNoteToken;
import org.modelio.gproject.gproject.GProject;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;

/**
 * Model change event listener that:<ul>
 * <li> Triggers the editor when its rich note is deleted
 * </ul>
 */
@objid ("e5aa4acf-55ed-423e-b917-29ea528d8e1b")
class RichNoteChangeHandler implements IModelChangeListener {
    @objid ("1ae1e490-0cb6-4e54-93a2-6c821ba55416")
    private GProject project;

    @objid ("28a114ec-2ce4-4a7a-a17f-72bac63cb52d")
    private IRichNoteFileRepository fileManager;

    @objid ("e9017b4c-8209-44d6-a2bc-cdbe412e3751")
    private EditorsRegistry editorRegistry;

    /**
     * Initialize the rich notes handler.
     * @param session the modeling session to handle.
     */
    @objid ("ce5a559d-aede-4b4b-a30f-fa5cb234d56a")
    public RichNoteChangeHandler(final GProject session) {
        this.project = session;
        this.editorRegistry = RichNotesSession.get(session).getEditorRegistry();
    }

    @objid ("e3a51cb6-8f65-4267-b68d-e3dc31e2e77b")
    @Override
    public void modelChanged(IModelChangeEvent event) {
        if (! event.getDeleteEvents().isEmpty()) {
            for (RichNoteToken n : new ArrayList<>(this.editorRegistry.getAllEditors())) {
                if (n.model.isDeleted()) {
                    n.editor.onOriginalDeleted(n.model);
                }
            }
        }
    }

}
