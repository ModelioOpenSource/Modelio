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

package org.modelio.editors.richnote.microsoft.editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.api.RichNoteFormatRegistry;
import org.modelio.editors.richnote.editor.IRichNoteEditor;
import org.modelio.editors.richnote.editor.IRichNoteInput;
import org.modelio.editors.richnote.helper.RichNoteLabelProvider;
import org.modelio.editors.richnote.microsoft.plugin.MicrosoftEditors;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IModelChangeSupport;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Microsoft Office documents editor.
 * <p>
 * 
 * @author cmarin
 * @see <a href="http://www.microssoft.com">Microsoft main site</a>
 */
@objid ("8e9438cf-c418-464a-bfe0-06d5902d8109")
public class MicrosoftEditor implements IRichNoteEditor {
    /**
     * ID of the editor.
     */
    @objid ("1d34cb48-c071-43da-9411-4fc7dc26e006")
    public static final String EDITOR_ID = "org.modelio.editors.richnote.microsoft";

    @objid ("8b5c888b-1c7f-439b-958d-84c67c7860dd")
    private DirtyWatcher dirtyWatcher;

    @objid ("2d10d807-c177-4df7-8497-6e2b437da4b6")
    private MicrosoftDocumentViewer viewer;

    @objid ("57db37c7-6b79-408d-a0ee-2d5b0680a71c")
    private ModelListener modelListener;

    @objid ("be3156ea-89a8-4fd0-8dd4-7a44bacaf433")
    private MPart mPart;

    @objid ("90fb93c6-7a8a-4c96-aade-6b7cd95ef1ae")
    private Path editedFilePath;

    @objid ("8b0938c0-b34b-4c7b-9759-af4eaec1967f")
     EPartService partSvc;

    @objid ("7dcb5a01-fb69-49ec-bb52-8b3dbf4f9a1b")
    private IRichNoteInput input;

    @objid ("0fc1e2fd-24ba-4e89-9a87-f1a80fff66d8")
    private Composite parentComposite;

    /**
     * E4 constructor.
     * 
     * @param aPart the E4 part
     * @param parent the SWT parent
     * @param editedInput the edited rich note
     * @param epartSvc the Eclipse part service
     */
    @objid ("55f64fd6-a5e1-46fd-ad97-bef7d9a8dc57")
    @PostConstruct
    public void init(MPart aPart, Composite parent, IRichNoteInput editedInput, EPartService epartSvc) {
        this.mPart = aPart;
        this.partSvc = epartSvc;
        this.parentComposite = parent;
        this.input = editedInput;
        
        createPartControl(parent);
        
        initTitle();
    }

    @objid ("726fdd04-3428-464f-858f-1adeb7d8397c")
    private void createPartControl(final Composite parent) {
        try {
            this.viewer = new MicrosoftDocumentViewer();
        
            this.viewer.createPartControl(parent);
        
            if (this.input.getEditedElement() != null) {
                loadElement(this.input.getEditedElement());
            }
        
            initTitle();
            // Register the editor as a model change listener to:
            // - update tab's name if the diagram is renamed
            // - close editor if the diagram (or an ancestor) is deleted
            this.modelListener = new ModelListener(this.input);
        
            this.dirtyWatcher = new DirtyWatcher(parent.getDisplay(), this.mPart);
        
        } catch (RuntimeException e) {
            createErrorPartControl(parent, e);
        }
    }

    /**
     * E4 destructor
     */
    @objid ("bddf0342-4ab9-4c62-8935-64604efc6f49")
    @PreDestroy
    public void dispose() {
        unregisterListener();
        
        if (this.viewer != null) {
            this.viewer.close();
        }
        
        if (this.dirtyWatcher != null) {
            this.dirtyWatcher = null;
        }
        
        this.input.getFileManager().removeEditor(this);
    }

    /**
     * Called by E4 to save the rich note.
     * 
     * @param monitor a
     */
    @objid ("c57aad39-b5e7-47cb-8336-309dd2b656c2")
    @Persist
    public void doSave(final IProgressMonitor monitor) {
        MObject element = this.input.getEditedElement();
        try {
            if (element instanceof Artifact) {
                Artifact art = (Artifact) element;
                String fileName = art.getFileName();
                File f = new File(fileName);
                this.viewer.saveDocument(f);
        
            } else if (element instanceof Document) {
                Document doc = (Document) element;
        
                this.viewer.saveDocument(this.editedFilePath.toFile());
                this.input.getFileManager().saveRichNote(doc, this.editedFilePath);
            }
        } catch (IOException e) {
            MicrosoftEditors.LOG.error(MicrosoftEditors.PLUGIN_ID, e);
            MessageDialog.openError(null, "Cannot save " + element, e.getLocalizedMessage());
        }
        
        this.mPart.setDirty(false);
    }

    /**
     * Display the "Save as" dialog and save the rich note in another file.
     */
    @objid ("e4f00963-d8fb-4a17-b32c-27bb6304b5ba")
    public void doSaveAs() {
        MObject element = this.input.getEditedElement();
        try {
            if (element instanceof Artifact) {
                this.viewer.saveDocumentAs();
        
            } else if (element instanceof Document) {
                this.viewer.saveDocumentAs();
            }
        } catch (IOException e) {
            MicrosoftEditors.LOG.error(MicrosoftEditors.PLUGIN_ID, e);
            MessageDialog.openError(null, "Cannot save " + element, e.getLocalizedMessage());
        }
    }

    /**
     * Get the OLE program ID to use to open the document
     * 
     * @param doc a document
     * @return the OLE program ID.
     */
    @objid ("8dbb8710-012a-492f-9a64-4c5574019573")
    private String getProgramId(final Document doc) {
        RichNoteFormat format = RichNoteFormatRegistry.getInstance().getFormat(doc);
        if (format == null) {
            return null;
        }
        
        for (String ext : format.getFileExtensions()) {
            String progId = OLE.findProgramID(ext);
            if (progId != null && !progId.isEmpty()) {
                return progId;
            }
        }
        
        if (format.getData() != null) {
            return format.getData();
        }
        return null;
    }

    @objid ("e7b91eaf-43fa-454d-bb01-a1aeca532268")
    void initTitle() {
        MObject el = this.input.getEditedElement();
        
        String title;
        if (el == null) {
            return;
        } else if (el instanceof Document) {
            title = RichNoteLabelProvider.getLabel((Document) el);
        } else {
            title = el.getName();
        }
        
        if (el.getStatus().isModifiable()) {
            this.mPart.setLabel(title);
        } else {
            this.mPart.setLabel(title + MicrosoftEditors.I18N.getString("read_only"));
        }
        
        /*
         * MObject el = this.input.getEditedElement(); if (el==null) return ; else if (el instanceof Document) { Image ss = RichNoteLabelProvider.getIcon((Document) el); } else { StandardImageService.getStereotypedImage(el, null); }
         */
    }

    @objid ("841bcc7b-df1d-4c0a-b5f3-c0410b987533")
    MicrosoftDocumentViewer getViewer() {
        return this.viewer;
    }

    @objid ("aace737f-64db-4355-bed9-6d60c67b9e5a")
    private void createErrorPartControl(final Composite parent, final Throwable e) {
        Text text = new Text(parent, SWT.MULTI);
        text.setText(e.toString());
        
        MicrosoftEditors.LOG.error(e);
    }

    /**
     * Load the file referenced by the artifact.
     * 
     * @param art the artifact to load
     * @throws java.io.IOException in case of error opening the file.
     * @throws java.io.FileNotFoundException if the referenced file is not found.
     */
    @objid ("04aca5d6-e88d-4300-b033-c7cc6c133121")
    private void loadArtifact(final Artifact art) throws FileNotFoundException, IOException {
        File f = this.input.getFileManager().getArtifactFile(art).toFile();
        
        if (f.isFile()) {
            this.viewer.openDocument(f, getProgramID(f));
        } else {
            throw new FileNotFoundException("'" + f.getPath() + "' not found.");
        }
    }

    /**
     * Load the element text into the editor.
     * 
     * @param element the edited model object
     */
    @objid ("fff6bcc6-59ae-453e-9517-9e495dc8fa43")
    private void loadElement(final MObject element) {
        try {
            if (element instanceof Artifact) {
                Artifact art = (Artifact) element;
                loadArtifact(art);
        
            } else if (element instanceof Document) {
                Document doc = (Document) element;
                loadDocument(doc);
            } else {
                MessageDialog.openError(null, "Cannot load element.", element + " is not handled.");
            }
        
            // set the read/write mode
            final boolean writeMode = element.getStatus().isModifiable();
            this.viewer.setWriteable(writeMode);
        
        } catch (IOException e) {
            MicrosoftEditors.LOG.error(e);
            MessageDialog.openError(null, "Cannot load " + element, e.getLocalizedMessage());
        }
    }

    /**
     * Load the file referenced by the external document.
     * 
     * @param doc the rich note to load
     * @throws java.io.IOException in case of error opening the file.
     */
    @objid ("ec1ec55e-81da-4aa6-bd9f-532e0b0a5c8d")
    private void loadDocument(final Document doc) throws IOException {
        this.editedFilePath = this.input.getFileManager().openRichNote(doc, this);
        
        if (this.editedFilePath != null) {
            File f = this.editedFilePath.toFile();
            this.viewer.openDocument(f, getProgramId(doc));
        } else {
            RichNoteFormat docFormat = RichNoteFormatRegistry.getInstance().getFormat(doc);
            this.editedFilePath = this.input.getFileManager().getNewRichNotePath(doc, docFormat);
            File f = this.editedFilePath.toFile();
            this.viewer.createDocument(f, docFormat);
            this.viewer.openDocument(f, getProgramId(doc));
        }
    }

    @objid ("b472c2b3-03e1-4257-9897-b6bbc7359f37")
    private void unregisterListener() {
        if (this.modelListener != null) {
            this.modelListener.dispose();
            this.modelListener = null;
        }
    }

    @objid ("034fcac0-3d38-4d99-97ef-16d06b6169df")
    private String getProgramID(final File f) {
        final String name = f.getName();
        String ext = name.substring(name.indexOf("."));
        return OLE.findProgramID(ext);
    }

    /**
     * Show/hide Word menu bar in place of the Modelio menu bar when the editor has the focus.
     */
    @objid ("c0430414-f63f-4508-ae0a-497792e8f54f")
    @Focus
    public void onFocus() {
        MicrosoftEditor.this.getViewer().focusGot();
    }

    @objid ("be1c6c4e-19d6-420f-81f3-70ed6e918dee")
    @Override
    public void disposeResources() {
        // nothing to do
    }

    @objid ("ab9b20a2-df5d-4d90-83f2-76006141bc30")
    @Override
    public MPart getMPart() {
        return this.mPart;
    }

    @objid ("0924c4f0-0601-45af-ac2a-a9d052967193")
    @Override
    public void onOriginalDeleted(MObject model) {
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                callCloseMPart();
            }
        });
    }

    @objid ("7b403788-c6e2-4903-8fca-8d7532d4587c")
    @Override
    public void onOriginalModified(MObject model) {
        final String title = MicrosoftEditors.I18N.getMessage("MicrosoftEditor.onOriginalModified.title", this.editedFilePath.toString(), model.getName());
        final String message = MicrosoftEditors.I18N.getMessage("MicrosoftEditor.onOriginalModified.msg", this.editedFilePath.toString(), model.getName());
        final MicrosoftDocumentViewer v = this.viewer;
        final Composite composite = this.parentComposite;
        
        MicrosoftEditors.LOG.info(message);
        MicrosoftEditors.LOG.debug("Edited file:" + this.editedFilePath);
        
        Display.getDefault().syncExec(new Runnable() {
        
            @Override
            public void run() {
                final Shell shell = composite.getShell();
                // Show warning
                MessageDialog.openWarning(shell, title, message);
        
                // If modified, offer change to save as
                if (v.isDirty()) {
                    try {
                        v.saveDocumentAs();
                    } catch (IOException e) {
                        MessageDialog.openWarning(shell, title, e.getLocalizedMessage());
                    }
                }
        
                // Close the part
                callCloseMPart();
            }
        });
    }

    @objid ("33e9c48f-75b4-49d4-a841-8c1ddcf7ca3b")
    void callCloseMPart() {
        this.partSvc.hidePart(this.mPart, true);
    }

    /**
     * Create a new Microsoft file.
     * 
     * @param file the file path
     * @param format the file format
     * @throws java.io.IOException in case of failure
     */
    @objid ("c73ba36b-8012-4941-974f-f8285b0a9e9e")
    public static void createEmptyFile(File file, RichNoteFormat format) throws IOException {
        final File parentDir = file.getParentFile();
        if (!parentDir.isDirectory()) {
            Files.createDirectories(parentDir.toPath());
        }
        
        Shell shell = new Shell(Display.getDefault());
        MicrosoftDocumentViewer aviewer = new MicrosoftDocumentViewer();
        aviewer.createPartControl(shell);
        aviewer.createDocument(file, format);
        aviewer.close();
        shell.dispose();
    }

    /**
     * Microsoft editor dirty state watcher.
     * <p>
     * Runs periodically in the SWT thread. Sets the {@link MPart#setDirty(boolean) dirty} property when the dirty state change.
     */
    @objid ("0347ed27-e7e6-4736-a27a-9b058e38186a")
    private class DirtyWatcher implements Runnable {
        @objid ("e2fbb157-ccbf-43d3-95b4-65519becc05a")
        private final MPart part;

        @objid ("e64e3315-1950-4c69-8acf-88b0d57c0d8b")
        private final Display display;

        @objid ("1459212f-ad29-46e7-8636-599760afc068")
        public DirtyWatcher(Display display, MPart part) {
            this.part = part;
            this.display = display;
            
            display.timerExec(1000, this);
        }

        @objid ("f0b4f4f0-5a5c-47fe-a8a3-978868c8e75e")
        @Override
        public void run() {
            final MicrosoftDocumentViewer docViewer = getViewer();
            if (!docViewer.isDisposed()) {
                final boolean newDirty = docViewer.isDirty();
                if (newDirty && !DirtyWatcher.this.part.isDirty()) {
                    DirtyWatcher.this.part.setDirty(newDirty);
                }
            
                // Schedule again
                this.display.timerExec(2000, this);
            }
        }

    }

    /**
     * Invoked when the model has changed.
     * <p>
     * <ul>
     * <li>Refresh the editor title
     * <li>Set the view read only if the model becomes read only.
     * <li>Close the diagram if deleted from the model.
     * </ul>
     */
    @objid ("833b411d-1c7b-48ce-bf74-34ea55cfe101")
    private class ModelListener implements IModelChangeListener, IStatusChangeListener {
        @objid ("98f10ff7-c222-4b46-b4b5-ce15d5d95dbf")
        private boolean wasReadOnly;

        @objid ("cf923c55-cbcc-49f2-962c-b8219a502dbf")
        private IRichNoteInput minput;

        @objid ("dbb23035-3237-4b84-a595-68043ba1b416")
        public ModelListener(final IRichNoteInput input) {
            this.minput = input;
            this.wasReadOnly = getEditedElement().getStatus().isModifiable();
            
            final IModelChangeSupport imodel = input.getSession().getModelChangeSupport();
            imodel.addModelChangeListener(this);
            imodel.addStatusChangeListener(this);
        }

        @objid ("cc6dd97f-8b28-4755-aa10-e8c5a6225ba1")
        private MObject getEditedElement() {
            return this.minput.getEditedElement();
        }

        @objid ("aef26953-0405-4008-8f75-044e5844f1ec")
        @SuppressWarnings ("synthetic-access")
        @Override
        public void statusChanged(final IStatusChangeEvent event) {
            final boolean newModifiable = getEditedElement().getStatus().isModifiable();
            
            if (this.wasReadOnly != newModifiable) {
                MicrosoftEditor.this.viewer.setWriteable(newModifiable);
                // loadElement(getEditedElement());
                this.wasReadOnly = newModifiable;
            }
        }

        /**
         * Invoked when the model has changed.
         * <p>
         * <li>Refresh the diagram title
         * <li>Set the view read only if the model becomes read only (to be done)
         * <li>Close the diagram if deleted from the model.
         * <p>
         * 
         * @param changeEvent the model change event
         */
        @objid ("545bbad0-71a5-4a81-9508-0f074d1ff684")
        @Override
        public void modelChanged(final IModelChangeEvent changeEvent) {
            final MObject editedEl = this.minput.getEditedElement();
            if (!changeEvent.getDeleteEvents().isEmpty()) {
                // Some elements were deleted: check for validity of the diagram.
                if (editedEl != null && !editedEl.isValid()) {
                    // The diagram in no longer valid, close the editor
                    callCloseMPart();
                    return;
                }
            
                // At this point, we know that editedEl is still valid, update the editor's title and icon.
                if (editedEl != null) {
                    initTitle();
                }
            }
        }

        /**
         * Unregister the listener.
         */
        @objid ("f03ac601-082c-45e1-b9fa-a11e33a01f33")
        public void dispose() {
            ICoreSession session = this.minput.getSession();
            if (session.isValid()) {
                final IModelChangeSupport imodel = session.getModelChangeSupport();
                imodel.removeModelChangeListener(this);
                imodel.removeStatusChangeListener(this);
            }
        }

    }

}
