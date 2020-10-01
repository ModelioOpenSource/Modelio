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

package org.modelio.editors.richnote.management;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.editors.richnote.management.EditorsRegistry.RichNoteToken;
import org.modelio.gproject.gproject.GProject;
import org.modelio.vcore.session.api.blob.IBlobChangeEvent;
import org.modelio.vcore.session.api.blob.IBlobChangeListener;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Represents a modeling session for the rich notes plugin.
 * <p>
 * There should be one instance per opened {@link GProject}.
 * Its life cycle is managed by {@link RichNoteEditorsManager}.
 */
@objid ("7dc0b412-675d-466d-972e-ee11a98ff679")
public class RichNotesSession implements IBlobChangeListener {
    @objid ("a213c04e-13a8-4819-a658-399d10d6ee14")
    private static Map<GProject, RichNotesSession> sessions = new HashMap<>(3);

    @objid ("ba879bbd-069b-406b-81d0-e35ccc293f8d")
    private GProject project;

    @objid ("a183eb35-e8af-4c4e-8659-9531a6a35244")
    private FileRepository fileManager;

    @objid ("5ac8647c-eb15-45a4-b7bc-30d5beaaa85c")
    private EditorsRegistry editors;

    /**
     * Initialize a new rich note modeling session.
     * <p>
     * Called by {@link RichNoteEditorsManager#onProjectOpen(GProject)}.
     * 
     * @param project the project to handle.
     */
    @objid ("a491b067-18bd-4f2a-88a3-252b110a3fde")
    RichNotesSession(GProject project) {
        assert (project != null);
        assert (sessions.get(project) == null) : project;
        
        sessions.put(project, this);
             
        this.project = project;
        this.editors =  new EditorsRegistry();
        this.fileManager = new FileRepository(project, this.editors);
        
        this.project.getSession().getBlobSupport().addBlobChangeListener(this);
        
        // Add the model change handler
        IModelChangeListener aHandler = new RichNoteChangeHandler(project);
        project.getSession().getModelChangeSupport().addModelChangeListener(aHandler);
    }

    @objid ("3b107441-ca78-47fc-8280-9ee53cb3326d")
    @Override
    public void blobsChanged(IBlobChangeEvent ev) {
        ev.getDeletedBlobs();
        for (IBlobInfo b : ev.getDeletedBlobs()) {
            RichNoteToken token = this.editors.getEditorToken(b.getRelatedElement());
            if (token != null) {
                token.editor.onOriginalDeleted(token.model);
            }
        }
        
        for (IBlobInfo b : ev.getUpdatedBlobs()) {
            
            RichNoteToken token = this.editors.getEditorToken(b.getRelatedElement());
            if (token != null) {
                token.editor.onOriginalModified(token.model);
            }
        }
    }

    /**
     * To be called when the project is closed.
     * <p>
     * Called by {@link RichNoteEditorsManager#onProjectClosed(GProject, org.eclipse.e4.ui.workbench.modeling.EPartService)}
     */
    @objid ("03026df5-4fa2-47bb-bba7-1d777d42c12b")
    void closeSession() {
        sessions.remove(this.project);
    }

    /**
     * @return the editors registry.
     */
    @objid ("e194f12d-6219-470b-a360-48645f2d0f1c")
    public EditorsRegistry getEditorRegistry() {
        return this.editors;
    }

    /**
     * @return the rich notes files repository.
     */
    @objid ("61fe86ad-42b3-44fd-a43d-8188050beccf")
    public IRichNoteFileRepository getFileRepository() {
        return this.fileManager;
    }

    /**
     * Get the rich note session matching a project.
     * 
     * @param openedProject a registered project.
     * @return the matching rich note session.
     */
    @objid ("e2f8fc23-af94-453e-8ffb-a3722c74f34d")
    public static RichNotesSession get(GProject openedProject) {
        return sessions.get(openedProject);
    }

    /**
     * Get the rich note session handling a model object.
     * 
     * @param obj a model object.
     * @return the matching rich note session.
     */
    @objid ("41d3fb48-18f4-4533-8ba9-a259565fb749")
    public static RichNotesSession get(MObject obj) {
        return sessions.get(GProject.getProject(obj));
    }

}
