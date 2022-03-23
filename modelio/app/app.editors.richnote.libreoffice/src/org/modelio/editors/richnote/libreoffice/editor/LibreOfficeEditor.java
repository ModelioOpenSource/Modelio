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
package org.modelio.editors.richnote.libreoffice.editor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.api.RichNoteFormatRegistry;
import org.modelio.editors.richnote.editor.IRichNoteEditor;
import org.modelio.editors.richnote.editor.IRichNoteInput;
import org.modelio.editors.richnote.helper.RichNoteLabelProvider;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeEditors;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeLoader;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.platform.ui.progress.IModelioProgressService;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IModelChangeSupport;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Libre office documents editor.
 * <p>
 * LibreOffice is the power-packed free, libre and open source personal productivity suite for Windows, Macintosh and GNU/Linux, that gives you six feature-rich applications for all your document production and data processing needs: Writer, Calc, Impress,
 * Draw, Math and Base. Support
 * 
 * @author cmarin
 * @see <a href="http://www.libreoffice.org">LibreOffice main site</a>
 */
@objid ("06490f2f-c8b6-47d8-91a4-cdac10739eb7")
public class LibreOfficeEditor implements IRichNoteEditor {
    /**
     * ID of the editor.
     */
    @objid ("9adf5501-cfae-4e77-87de-6fc623bd4987")
    public static final String EDITOR_ID = "editors.richnote.libreoffice.editorpart";

    @objid ("8ab3220e-6fab-4255-b404-c3f16e0689b4")
    private IEditedDocumentViewer viewer;

    @objid ("faca183b-022d-461f-a1ca-b78d9cf2aef9")
    private ModelListener modelListener = null;

    @objid ("07c41857-3876-48fe-8ba2-6c70cbdf9401")
    private IRichNoteInput richNoteInput;

    @objid ("0d3f4776-6bea-4fe4-a74e-10b722bfe5d1")
    private MPart mpart;

    @objid ("0d1fd94b-9e90-45b9-b866-62af2e45ce41")
    private Path editedFile;

    @objid ("796957c9-ebe8-4bdf-bae3-1e8c602ca35f")
    IModelioProgressService progressService;

    @objid ("bd045bc9-5057-43b1-b92a-04a1e37e377b")
    @Inject
    IShellProvider shellProvider;

    @objid ("f6999d2d-f204-4e8f-8c37-f07282cf2e4d")
    private EPartService partService;

    @objid ("a7c0828d-4b2e-4099-a89d-b01a923a138b")
    private MObject getEditedElement() {
        if (this.richNoteInput == null) {
            return null;
        } else {
            return this.richNoteInput.getEditedElement();
        }
        
    }

    /**
     * Load the element text into the editor.
     * @param element the element to load.
     */
    @objid ("cb95487e-ee2f-4373-a854-eac1c29df69d")
    void loadElement(final MObject element) throws IOException {
        if (element instanceof Artifact) {
            Artifact art = (Artifact) element;
            this.editedFile = this.richNoteInput.getFileManager().getArtifactFile(art);
        
            if (this.editedFile != null && Files.exists(this.editedFile)) {
                try (BufferedInputStream fs = new BufferedInputStream(Files.newInputStream(this.editedFile));) {
                    this.viewer.openDocument(fs, art.getStatus().isModifiable());
                }
            } else {
                throw new NoSuchFileException(this.editedFile.toString());
            }
        
        } else if (element instanceof Note) {
            Note n = (Note) element;
            this.viewer.openDocument(new ByteArrayInputStream(n.getContent().getBytes("UTF-8")), element.getStatus().isModifiable());
        } else if (element instanceof Document) {
            Document doc = (Document) element;
            this.editedFile = this.richNoteInput.getFileManager().openRichNote(doc, this);
        
            if (this.editedFile != null) {
                this.viewer.openDocument(this.editedFile, doc.getStatus().isModifiable());
            } else {
                RichNoteFormat format = RichNoteFormatRegistry.getInstance().getFormat(doc);
                this.viewer.createDocument(doc, format);
                this.editedFile = this.richNoteInput.getFileManager().getNewRichNotePath(doc, format);
            }
        }
        
        // initialize dirty state
        if (this.viewer.isDisposed()) {
            this.mpart.setDirty(false);
        } else {
            this.mpart.setDirty(this.viewer.isDirty());
        }
        
    }

    /**
     * Called by E4 platform to save the rich note.
     */
    @objid ("9059e6ed-e3db-449f-9b1f-971a86cb764f")
    @Persist
    public void doSave() {
        MObject element = getEditedElement();
        try {
            if (element instanceof Artifact) {
                Artifact art = (Artifact) element;
                String fileName = art.getFileName();
                File f = new File(fileName);
                if (f.isFile()) {
                    try (BufferedOutputStream fs = new BufferedOutputStream(new FileOutputStream(f))) {
                        this.viewer.saveDocument(fs);
                    }
                }
        
            } else if (element instanceof Note) {
                Note n = (Note) element;
                try (ByteArrayOutputStream stream = new ByteArrayOutputStream();) {
                    this.viewer.saveDocument(stream);
                    n.setContent(stream.toString("UTF-8"));
                }
            } else if (element instanceof Document) {
                this.viewer.saveDocument();
                doSaveToRepository();
            }
        } catch (IOException e) {
            LibreOfficeEditors.LOG.error(e);
            MessageDialog.openError(null, "Cannot save " + element, e.getLocalizedMessage());
        }
        
    }

    /**
     * E4 Constructor.
     * @param parent a parent shell
     * @param input the rich note to edit
     * @param part the E4 part
     * @param aProgressService a progress monitor provider
     * @param aPartService the E4 edit part service
     */
    @objid ("05700bcc-d228-4880-9642-4cb75d1296f1")
    @PostConstruct
    public void create(Composite parent, IRichNoteInput input, MPart part, IModelioProgressService aProgressService, EPartService aPartService) {
        this.mpart = part;
        this.progressService = aProgressService;
        this.partService = aPartService;
        this.richNoteInput = input;
        
        createPartControl(parent);
        registerlistener(input);
        initTitle();
        
    }

    @objid ("db29745b-6b12-4a45-a168-d888fb206574")
    private void createPartControl(final Composite parent) {
        try {
            parent.setLayout(new FillLayout());
        
            // Instantiate viewer
            this.viewer = LibreOfficeLoader.getDocumentViewerClass().newInstance();
        
            // Initialize GUI
            this.viewer.setFileManager(this.richNoteInput.getFileManager());
            this.viewer.createPartControl(parent);
        
            // Load the document later, when the controls are visible.
            final IRunnableWithProgress runnable = new IRunnableWithProgress() {
                @Override
                @SuppressWarnings ("synthetic-access")
                public void run(IProgressMonitor monitor) throws InvocationTargetException,
                        InterruptedException {
                    if (LibreOfficeEditor.this.viewer != null && getEditedElement() != null) {
                        monitor.beginTask("Loading document...", IProgressMonitor.UNKNOWN);
                        try {
                            loadElement(getEditedElement());
                        } catch (IOException e) {
                            createErrorPartControl(parent, e);
                        }
                    }
                    monitor.done();
        
                    // Updates the dirty state each 3 seconds (wait 5s the 1st time)
                    Runnable modifyListener = new DirtyStateProber(parent.getDisplay(), LibreOfficeEditor.this.viewer);
                    parent.getDisplay().timerExec(5000, modifyListener);
                }
            };
        
            parent.getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    try {
                        LibreOfficeEditor.this.progressService.run("Loading document...", false, false, runnable);
                    } catch (InvocationTargetException e) {
                        createErrorPartControl(parent, e.getCause());
                    } catch (InterruptedException e) {
                        createErrorPartControl(parent, e);
                    }
        
                }
            });
        
        } catch (IllegalAccessException e) {
            createErrorPartControl(parent, e);
        } catch (IOException e) {
            createErrorPartControl(parent, e);
        } catch (InstantiationException e) {
            createErrorPartControl(parent, e);
        }
        
    }

    /**
     * Method to be invoked when the part has been granted focus.
     */
    @objid ("23dc4c67-4889-469c-ba9f-50c798931e7f")
    @Focus
    public void setFocus() {
        this.viewer.setFocus();
    }

    @objid ("9f16cdaf-6881-46a0-bd97-a94f8cfb3332")
    void createErrorPartControl(final Composite parent, final Throwable e) {
        LibreOfficeEditors.LOG.error(e);
        
        for (Control c : parent.getChildren()) {
            c.dispose();
        }
        
        Text t = new Text(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        StringWriter s = new StringWriter();
        s.append(e.getLocalizedMessage());
        s.append("\n\nDetail:\n-------\n");
        e.printStackTrace(new PrintWriter(s));
        t.setText(s.toString());
        
        parent.layout();
        
    }

    /**
     * Invoked by E4 when the part is in the process of being removed by the container
     */
    @objid ("ce9961a7-a8a9-4b7c-8af8-f08fcbd640fd")
    @PreDestroy
    public void dispose() {
        unregisterListener();
        
        if (this.viewer != null) {
            this.viewer.close();
            this.viewer = null;
        }
        
        this.richNoteInput.getFileManager().removeEditor(this);
        
    }

    @objid ("bcf91e5a-b353-4413-bc17-2c878d16d76b")
    private void initTitle() {
        MObject el = getEditedElement();
        if (el == null) {
            this.mpart.setLabel("");
            // this.mpart.setIconURI(null);
        } else if (el instanceof Document) {
            this.mpart.setLabel(RichNoteLabelProvider.getLabel((Document) el));
            /*
             * if (this.editedFile != null) {
             * this.mpart.setIconURI(MenuHelper.getImageUrl(RichNoteLabelProvider.getIcon(this.editedFile)));
             * }
             */
        } else {
            this.mpart.setLabel(el.getName());
            // this.mpart.setIconURI("");
        }
        
    }

    @objid ("ad3a8c6e-9c71-4511-8af3-d4eec8ef38f1")
    private void unregisterListener() {
        if (this.modelListener != null) {
            this.modelListener.dispose();
            this.modelListener = null;
        }
        
    }

    @objid ("d6ccc13c-cc20-4e46-9240-e59ba391822f")
    private void registerlistener(final IRichNoteInput input) {
        // Register the editor as a model change listener to:
        // - update tab's name if the diagram is renamed
        // - close editor if the diagram (or an ancestor) is deleted
        this.modelListener = new ModelListener(input);
        input.getSession().getModelChangeSupport().addModelChangeListener(this.modelListener);
        input.getSession().getModelChangeSupport().addStatusChangeListener(this.modelListener);
        
    }

    @objid ("8530c73f-1747-43f2-bccd-12f91a755219")
    void closeEditor() {
        this.partService.hidePart(this.mpart, true);
    }

    /**
     * Display an error dialog box.
     * <p>
     * If modal, the method waits for the dialog to be closed before returning.
     * If modeless the method returns immediately.
     * @param kind {@link MessageDialog#ERROR}, {@link MessageDialog#WARNING}, {@link MessageDialog#INFORMATION}
     * @param title The dialog title
     * @param message The message.
     * @param modal true if the dialog must be modal, <code>false</code> to be modeless.
     */
    @objid ("fe4dd7c8-2e6a-4eec-a9d4-4cb30b65c5d6")
    void displayDialog(final int kind, final String title, final String message, final boolean modal) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (Display.getDefault().isDisposed()) {
                    return;
                }
                Shell shell = LibreOfficeEditor.this.shellProvider.getShell();
                MessageDialog.open(kind, shell, title, message, kind);
            }
        };
        
        if (modal) {
            Display.getDefault().syncExec(runnable);
        } else {
            Display.getDefault().asyncExec(runnable);
        }
        
    }

    @objid ("0aabd1af-4fbf-4c3d-8212-e819b625e01b")
    @Override
    public void disposeResources() {
        // nothing to do
    }

    @objid ("5530a9ee-a405-41d0-a18e-20de5ac0e5a0")
    @Override
    public MPart getMPart() {
        return this.mpart;
    }

    @objid ("382614aa-5f4f-42f0-8806-1a6723218012")
    @Override
    public void onOriginalDeleted(MObject model) {
        closeEditor();
    }

    @objid ("24286005-98c7-4327-a1eb-91983904ae54")
    @Override
    public void onOriginalModified(MObject model) {
        String message = LibreOfficeEditors.I18N.getMessage("LibreOfficeEditor.onOriginalmodified.message", model.getName());
        String title = LibreOfficeEditors.I18N.getMessage("LibreOfficeEditor.onOriginalmodified.title", model.getName());
        
        displayDialog(MessageDialog.INFORMATION, title, message, true);
        closeEditor();
        
    }

    @objid ("60660a96-7d97-4e71-8e2b-5d0563e14b27")
    void doSaveToRepository() throws IOException {
        Document element = (Document) this.richNoteInput.getEditedElement();
        this.richNoteInput.getFileManager().saveRichNote(element, this.editedFile);
        
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
    @objid ("e16df42c-5a92-4692-ade5-fdd9388610e6")
    private class ModelListener implements IModelChangeListener, IStatusChangeListener {
        @objid ("71381aeb-32fb-4274-9443-61076defa214")
        private boolean wasReadOnly;

        @objid ("5bdb5458-0518-4517-a1cf-65571d14b55b")
        private IRichNoteInput input;

        @objid ("0b319eda-0d84-456e-8da1-2b7e9720263f")
        public  ModelListener(final IRichNoteInput input) {
            this.input = input;
            this.wasReadOnly = getEditedElement().getStatus().isModifiable();
            
        }

        @objid ("d457c685-0f35-448e-acf5-2a0c1168ac60")
        private MObject getEditedElement() {
            return this.input.getEditedElement();
        }

        @objid ("2ec73e8b-648d-48d2-9743-778984d08890")
        @SuppressWarnings ("synthetic-access")
        @Override
        public void statusChanged(final IStatusChangeEvent event) {
            final boolean newModifiable = getEditedElement().getStatus().isModifiable();
            
            if (this.wasReadOnly != newModifiable) {
                if (Display.getCurrent() == null) {
                    // Recursive call in the SWT thread
                    Display.getDefault().asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            statusChanged(event);
                        }
                    });
                } else {
                    try {
                        LibreOfficeEditor.this.viewer.closeDocument();
                        loadElement(getEditedElement());
                        this.wasReadOnly = newModifiable;
                    } catch (IOException e) {
                        LibreOfficeEditors.LOG.error(e);
                        displayDialog(MessageDialog.ERROR, "Cannot reload " + getEditedElement(), FileUtils.getLocalizedMessage(e), false);
            
                        closeEditor();
                    }
                }
            }
            
        }

        /**
         * Invoked when the model has changed.
         * <p>
         * <li>Refresh the diagram title
         * <li>Set the view read only if the model becomes read only (to be done)
         * <li>Close the diagram if deleted from the model.
         * <p>
         * @param changeEvent the model change event
         */
        @objid ("bc4a23a8-9f94-4e25-9dc6-37def96729cf")
        @SuppressWarnings ("synthetic-access")
        @Override
        public void modelChanged(final IModelChangeEvent changeEvent) {
            // Re enter the UI thread
            Display display = Display.getDefault();
            if (display != null) {
                final MObject editedEl = ModelListener.this.input.getEditedElement();
                if (!changeEvent.getDeleteEvents().isEmpty()) {
                    // Some elements were deleted: check for validity of the diagram.
                    if (editedEl != null && !editedEl.isValid()) {
                        // The editor is no longer valid, close the editor
                        closeEditor();
                    }
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
        @objid ("0b332079-5a82-409a-b9ef-79c55e0fb32b")
        public void dispose() {
            ICoreSession session = this.input.getSession();
            if (session.isValid()) {
                final IModelChangeSupport imodel = session.getModelChangeSupport();
                imodel.removeModelChangeListener(this);
                imodel.removeStatusChangeListener(this);
            }
            
        }

    }

    /**
     * Watches the dirty state each {@value #RATE} milliseconds.<br>
     * Fires a {@link org.eclipse.ui.IEditorPart#PROP_DIRTY PROP_DIRTY} property change if the dirty state changes.
     */
    @objid ("2e7c9353-e6f2-4eb9-9aa3-8dd74ce4a7e2")
    private class DirtyStateProber implements Runnable {
        @objid ("ce238192-120e-40cf-a3c7-8d81196a6403")
        private boolean oldDirty = false;

        @objid ("c32cd076-ef0f-4ce4-ab31-67902c17db72")
        private static final int RATE = 3000;

        @objid ("acaad87b-da3b-4c4c-ac30-923cdb31a35b")
        private Display display;

        @objid ("291bb0ae-e5ea-4683-93be-281991d12f46")
        private IEditedDocumentViewer docViewer;

        @objid ("e10a56db-884b-4798-9fd9-882f3c423517")
        public  DirtyStateProber(Display display, IEditedDocumentViewer viewer) {
            this.display = display;
            this.docViewer = viewer;
            
        }

        @objid ("f55ab8c3-89b4-4229-9f1b-81c9f3068593")
        @SuppressWarnings ("synthetic-access")
        @Override
        public void run() {
            if (!this.docViewer.isDisposed()) {
                boolean dirty = this.docViewer.isDirty();
                if (dirty != this.oldDirty) {
                    this.oldDirty = dirty;
                    LibreOfficeEditor.this.mpart.setDirty(dirty);
                    // If not dirty anymore, it is a save.
                    // Report the file to the blob
                    if (!dirty) {
                        try {
                            doSaveToRepository();
                        } catch (IOException e) {
                            displayDialog(MessageDialog.ERROR,
                                    LibreOfficeEditor.this.editedFile.toString(),
                                    FileUtils.getLocalizedMessage(e), false);
                        }
                    }
                }
            
                // run again
                this.display.timerExec(DirtyStateProber.RATE, this);
            }
            
        }

    }

}
