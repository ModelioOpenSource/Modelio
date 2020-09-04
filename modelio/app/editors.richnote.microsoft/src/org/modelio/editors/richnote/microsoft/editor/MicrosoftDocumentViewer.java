/* 
 * Copyright 2013-2019 Modeliosoft
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

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.microsoft.plugin.MicrosoftEditors;
import org.modelio.vbasic.files.FileUtils;

/**
 * Word document viewer.
 */
@objid ("7d87e9aa-e3ad-4d72-90e6-589f44a6313d")
class MicrosoftDocumentViewer {
    @objid ("00a38a23-674f-4073-bf65-d7cb9856fa9e")
    private boolean allowWrite = true;

    /**
     * Tells whether the GUI is active.
     * <p>
     * To be accessed only from the SWT thread.
     */
    @objid ("0a2c17b6-03ec-4ecf-8777-2305332c324f")
    private volatile boolean isActive;

    @objid ("6780a4ef-2144-4041-a2ca-9b96a039f9c1")
    private OleClientSite oleSite;

    @objid ("488b58d3-e826-46f0-88ca-c913fccce2b0")
    private OleFrame oleFrame;

    @objid ("872cf0e9-6953-4704-8dc6-bb986d761624")
    private Canvas idleCanvas;

    /**
     * Image displayed in place of Word when the viewer looses the focus.
     * <p>
     * Allows Modelio menus to be restored.
     */
    @objid ("e3542cbf-1c7f-4262-b5cb-d59aa89a337a")
     Image idleImg;

    /**
     * Close the viewer.
     */
    @objid ("74cd124d-7f21-456e-ae26-76e367cc3f9c")
    public void close() {
        if (this.idleImg != null)
            this.idleImg.dispose();
        
        if (this.oleFrame != null)
            this.oleFrame.dispose();
        
        this.isActive = false;
    }

    /**
     * Create and open the document.
     * 
     * @param file the path for the rich note
     * @param docFormat a rich note format
     * @throws java.io.IOException in case of failure
     */
    @objid ("2b00b6db-2e66-44e0-989f-808fa54371ad")
    public void createDocument(File file, RichNoteFormat docFormat) throws IOException {
        final File parentDir = file.getParentFile();
        if (!parentDir.isDirectory()) {
            parentDir.mkdirs();
            if (!parentDir.isDirectory())
                throw new IOException("Failed create needed '"+file.getParent()+"' directory.");
        }
        
        if (this.oleSite != null)
            this.oleSite.dispose();
        
        String programId = docFormat.getData();
        if (programId==null) {
            this.oleSite = new OleClientSite(this.oleFrame, SWT.NONE, "Word.Document");
        
            final EWdSaveFormat fileFormat = getFileFormat(file);
            createAsFormat(this.oleSite, file.getPath(), fileFormat);
            this.oleSite.dispose();
            
        } else {
            this.oleSite = new OleClientSite(this.oleFrame, SWT.NONE, programId);
            boolean saveOk = this.oleSite.save(file, true);
            this.oleSite.dispose();
        
            if (!saveOk) 
                throw new IOException("Save of '"+file.getPath()+"' failed.");
        }
    }

    /**
     * Creates the SWT controls for this viewer.
     * 
     * @param parent the parent control
     */
    @objid ("e03b4653-439f-4183-97c7-6dca9798373b")
    public void createPartControl(final Composite parent) {
        final GridLayout layout = new GridLayout();
        layout.marginHeight = 1;
        layout.marginWidth = 1;
        parent.setLayout(layout);
        
        this.idleCanvas = new Canvas(parent, SWT.NO_BACKGROUND /*| SWT.NO_REDRAW_RESIZE*/);
        this.idleImg = null;
        this.idleCanvas.setVisible(false);
        GridData gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.exclude = true;
        this.idleCanvas.setLayoutData(gd);
        
        final Display display = parent.getDisplay();
        this.oleFrame = new OleFrame(parent, SWT.CLIP_CHILDREN);
        this.oleFrame.setBackground(JFaceColors.getBannerBackground(display));
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        this.oleFrame.setLayoutData(gd);
        
        this.oleFrame.addDisposeListener(new DisposeListener() {
            
            @Override
            public void widgetDisposed(DisposeEvent e) {
                Image img = MicrosoftDocumentViewer.this.idleImg;
                if (img != null && !img.isDisposed()) {
                    img.dispose();
                }
                
            }
        });
        
        this.idleCanvas.addPaintListener(new PaintListener() {
            
            @Override
            public void paintControl(PaintEvent e) {
                if (MicrosoftDocumentViewer.this.idleImg != null)
                    e.gc.drawImage(MicrosoftDocumentViewer.this.idleImg, 0, 0);
            }
        });
    }

    /**
     * Called when the viewer gains the focus.
     */
    @objid ("1b1f1ff6-1a49-4624-ab9d-039efd0a64dc")
    public void focusGot() {
        activateOleGui();
    }

    /**
     * To be called when the view looses the focus.
     * <p>
     * Restores Modelio menus and replace Word by an Image
     * unless the focus goes to the Modelio menu bar.
     */
    @objid ("99ba3b02-6721-48d4-a781-d394db98b682")
    public void focusLost() {
        if (this.oleSite.isDisposed())
            return;
        
        // Defers the work to later, when we know who owns the new focus.
        this.oleSite.getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                doFocusLost();
            }
        });
    }

    /**
     * @return true if the document is modified.
     */
    @objid ("0010e902-bf33-4f4f-9c2c-9f554006b0d2")
    public boolean isDirty() {
        if (this.oleSite == null)
            return false;
        else
            return this.oleSite.isDirty() && this.allowWrite;
    }

    @objid ("374f676c-1073-4861-8620-62226bea7111")
    public boolean isDisposed() {
        return this.oleSite==null || this.oleSite.isDisposed();
    }

    @objid ("1cee0975-9ce7-4e70-af4a-f992a0252fca")
    public void openDocument(final File file, final String oleProgId) throws IOException {
        if (this.oleSite != null)
            this.oleSite.dispose();
        
        try {
            if (oleProgId==null || oleProgId.isEmpty())
                this.oleSite = new OleClientSite(this.oleFrame, SWT.NONE, "Word.Document", file);
            else
                this.oleSite = new OleClientSite(this.oleFrame, SWT.NONE, oleProgId, file);
            this.oleSite.setBackground(JFaceColors.getBannerBackground(this.oleSite.getDisplay()));
            focusGot();
            
            
            this.oleSite.addFocusListener(new FocusListener() {
                
                @Override
                public void focusLost(FocusEvent e) {
                    MicrosoftDocumentViewer.this.focusLost();
                }
                
                @Override
                public void focusGained(FocusEvent e) {
                    // already handled by MicrosoftEditor.onFocus()
                }
            });
            
        } catch (SWTException e) {
            throw new IOException(e);
        }
    }

    /**
     * Save the content to the given stream.
     * 
     * @param file where the document will be saved.
     * @throws java.io.IOException in case of error saving the document.
     */
    @objid ("4ed875e1-70a2-4f51-b5fe-273c04dadbb6")
    public void saveDocument(final File file) throws IOException {
        final EWdSaveFormat fileFormat = OLE.isOleFile(file) ? EWdSaveFormat.FormatOle : getFileFormat(file);
        
        if (fileFormat == EWdSaveFormat.FormatOle) {
            if (!this.oleSite.save(file, true))
                throw new IOException("Save of '"+file.getPath()+"'failed.");
        } else {
            saveFormat(this.oleSite, file);
        }
    }

    /**
     * Save the content in another file.
     * 
     * @throws java.io.IOException in case of error saving the document.
     */
    @objid ("78de602b-3c55-44b2-b52c-b269febb901c")
    public void saveDocumentAs() throws IOException {
        int options = OLE.OLECMDEXECOPT_PROMPTUSER;
        Variant in = null;
        Variant out = null;
        this.oleSite.exec(OLE.OLECMDID_SAVEAS, options, in, out);
    }

    @objid ("859b55e6-e9b2-49f2-82ff-e2444b38a209")
    public void setWriteable(final boolean writeable) {
        if (this.allowWrite != writeable) {
            this.allowWrite = writeable;
            this.oleSite.setEnabled(this.allowWrite);
        
            activateOleGui();
        }
    }

    /**
     * Schedule the OLE GUI activation in the SWT thread.
     */
    @objid ("d7b2f2ad-f2ef-4e62-abac-8ad56e8593ff")
    private void activateOleGui() {
        if (this.isActive)
            return;
        
        this.oleSite.getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                doActivateOleGui();
            }
        });
    }

    /**
     * Activate the OLE GUI.
     * <p>
     * Must be called from the display thread, by {@link #activateOleGui()}.
     */
    @objid ("b00c1e7a-c9b3-4ce0-9ceb-a5e1ba09b2b1")
    void doActivateOleGui() {
        if (this.oleSite.isDisposed() || !this.oleFrame.getParent().isVisible())
            return;
        
        if (this.isActive)
            return;
        
        this.isActive = true;
               
        this.oleFrame.getParent().setLayoutDeferred(true);
        // Remove idle image
        
        if (this.idleImg != null) {
            if (! this.idleImg.isDisposed())
                this.idleImg.dispose();
            this.idleImg = null;
        }
        
        
        this.idleCanvas.setVisible(false);
        this.oleFrame.setVisible(true);
        this.oleSite.setVisible(true);
        
        // Ensure visibility
        setLayoutIncluded(this.oleFrame, true);
        setLayoutIncluded(this.idleCanvas, false);
        
        this.oleFrame.getParent().setLayoutDeferred(false);
        this.oleFrame.getParent().layout();
        
        // Activate Word, also replace Modelio menus bar by Word menu bar.
        this.oleSite.doVerb(OLE.OLEIVERB_INPLACEACTIVATE);
        
        // This is needed for PowerPoint 2007 to display its GUI
        this.oleSite.setFocus();
        
        //upgradeBackgroundImage(true);
    }

    @objid ("e74c051a-296e-4f89-8d6e-bd2a4cee70e9")
    private void saveAsFormat(final OleClientSite clientSite, final String fileName) throws IOException {
        // Get generic IDispatch interface
        OleAutomation activeDoc = new OleAutomation(clientSite);
        try {
            // Macro to get the current format
            //int[] sformatid = activeDoc.getIDsOfNames(new String[]{"SaveFormat"});
            //System.err.println("format = "+activeDoc.getProperty(sformatid[0]));
            
        
            //Sub SaveAs([FileName], [FileFormat], [LockComments], [Password], [AddToRecentFiles], [WritePassword], 
            //           [ReadOnlyRecommended], [EmbedTrueTypeFonts], [SaveNativePictureFormat], [SaveFormsData], [SaveAsAOCELetter], 
            //           [Encoding], [InsertLineBreaks], [AllowSubstitutions], [LineEnding], [AddBiDiMarks])
            int[] rgdispid = activeDoc.getIDsOfNames(new String[]{"SaveAs", "FileName","FileFormat"});
            Variant[] rgvarg = new Variant[]{new Variant(fileName)};
            int[] idNamedArgs = new int[]{rgdispid[1]};
            
            //rgvarg[1] = new Variant(format.getValue()); 
            //idNamedArgs[1] = rgdispid[2];
            
            try {
                activeDoc.invokeNoReply(rgdispid[0], rgvarg, idNamedArgs);
            } catch (SWTException e) {
                //MicrosoftEditors.LOG.error("Couldn't save '"+fileName+ "' file to "+ format+" format. See the error below.");
                String lerr = activeDoc.getLastError();
                if (lerr==null || lerr.isEmpty())
                    lerr = e.getMessage();
                throw new IOException(lerr, e);
            }
        } finally {
            activeDoc.dispose();
        }
    }

    /**
     * Save the edited document by keeping a backup in the case save fails.
     * 
     * @param clientSite the editor to save
     * @param file the destination file
     * @throws java.io.IOException in case of save or backup failure.
     */
    @objid ("8028dd3f-8768-41b4-b8af-3ae75a23f22d")
    private void saveFormat(final OleClientSite clientSite, final File file) throws IOException {
        try (Backuper b = new Backuper(file.toPath())){
            saveAsFormat(clientSite, file.getPath());
        
            // save was successful so discard the backup
            b.discard();
        }
    }

    /**
     * Rename the given file
     * 
     * @param file the file to rename
     * @param tempFile new name
     */
    @objid ("9610d192-f163-45fa-8bda-f1c8fac5e545")
    private void renameFile(final File file, final File tempFile) {
        Path src = file.toPath();
        Path dest = tempFile.toPath();
        try {
            Files.move(src, dest);
        } catch (IOException e) {
            MicrosoftEditors.LOG.warning("Rename backup '"+tempFile+"' failed:"+e.getMessage());
        }
    }

    /**
     * Try to print in the console the currently open Word documents.
     * 
     * @param application a Word.Application OLE object
     */
    @objid ("dd725439-ed13-463b-a532-e620c3759bb5")
    private void printCurrentDocs(final OleAutomation application) {
        // Get all documents
        
        int[] pId = application.getIDsOfNames(new String[]{"Documents"}); 
        Variant pVarResult = application.getProperty(pId[0]);
        OleAutomation docs = pVarResult.getAutomation();
        
        //Property Count As Long
        pId = docs.getIDsOfNames(new String[]{"Count"});
        pVarResult = docs.getProperty(pId[0]);
        int nbDocs = pVarResult.getInt();
        System.out.println(nbDocs+" documents currently in Word");
        
        int[] pItemId = docs.getIDsOfNames(new String[]{"Item"});
        for (int i=1; i<=nbDocs; ++i){
            Variant vdoc = docs.invoke(pItemId[0], new Variant[]{new Variant(i)});
            OleAutomation doc = vdoc.getAutomation();
            
            pId = doc.getIDsOfNames(new String[]{"Path"});
            pVarResult = docs.getProperty(pId[0]);
            String docPath = pVarResult==null ? "<null>" : pVarResult.getString();
        
            pId = doc.getIDsOfNames(new String[]{"Name"});
            pVarResult = docs.getProperty(pId[0]);
            String name = pVarResult==null ? "<null>" : pVarResult.getString();
        
            System.out.println("  - "+name+":"+docPath);
            doc.dispose();
        }
        docs.dispose();
    }

    @objid ("965ff243-6fbe-4aef-8a5d-a1dc28f310fc")
    private void createAsFormat(final OleClientSite clientSite, final String fileName, final EWdSaveFormat format) throws IOException {
        OleClientSite newSite = null;
        
        OleAutomation dispInterface = null;
        OleAutomation activeDoc = null;
        OleAutomation application = null;
        try {
            newSite = createWordApplicationClientSite(clientSite.getParent());
            dispInterface = new OleAutomation(newSite);
        
            // Get Application
            int[] appId = dispInterface.getIDsOfNames(new String[]{"Application"}); 
            Variant pVarResult = dispInterface.getProperty(appId[0]);
            application = pVarResult.getAutomation();
        
            // Get all documents
        
            int[] pId = application.getIDsOfNames(new String[]{"Documents"}); 
            pVarResult = application.getProperty(pId[0]);
            OleAutomation docs = pVarResult.getAutomation();
        
            //Property Count As Long
            pId = docs.getIDsOfNames(new String[]{"Count"});
            pVarResult = docs.getProperty(pId[0]);
            int nbDocs = pVarResult.getInt();
            System.out.println(nbDocs+" documents currently in Word");
        
            //Function Add([Template], [NewTemplate], [DocumentType], [Visible]) As Document
            pId = docs.getIDsOfNames(new String[]{"Add", "Visible"});
            pVarResult = docs.invoke(pId[0],new Variant[]{new Variant(false)}, new int[]{pId[1]});
            if (pVarResult==null){
                docs.dispose();
                throw new IOException(docs.getLastError());
            }
            activeDoc = pVarResult.getAutomation();
            docs.dispose();
        
            //Sub SaveAs([FileName], [FileFormat], [LockComments], [Password], [AddToRecentFiles], [WritePassword], 
            //           [ReadOnlyRecommended], [EmbedTrueTypeFonts], [SaveNativePictureFormat], [SaveFormsData], [SaveAsAOCELetter], 
            //           [Encoding], [InsertLineBreaks], [AllowSubstitutions], [LineEnding], [AddBiDiMarks])
            int[] rgdispid = activeDoc.getIDsOfNames(new String[]{"SaveAs", "FileName","FileFormat"});
            Variant[] rgvarg = new Variant[2];
            int[] idNamedArgs = new int[2];
            rgvarg[0] = new Variant(fileName); 
            idNamedArgs[0] = rgdispid[1];
            rgvarg[1] = new Variant(format.getValue()); 
            idNamedArgs[1] = rgdispid[2];
            activeDoc.invokeNoReply(rgdispid[0], rgvarg, idNamedArgs);
        
            //Sub Close([SaveChanges], [OriginalFormat], [RouteDocument])
            pId = activeDoc.getIDsOfNames(new String[]{"Close", "SaveChanges", "OriginalFormat", "RouteDocument"});
            try {
                activeDoc.invokeNoReply(pId[0],new Variant[]{new Variant(WdSaveOptions.wdDoNotSaveChanges)}, new int[]{pId[1]});
            } catch(SWTException e) {
                MicrosoftEditors.LOG.error("Couldn't save '"+fileName+ "' to "+ format+" format. See the error below.");
                throw new IOException(activeDoc.getLastError(), e);
            }
        
        } finally {
            if (activeDoc!= null) activeDoc.dispose();
            if (application!= null) application.dispose();
            if (dispInterface!=null) dispInterface.dispose();
            if (newSite!=null) newSite.dispose();
        }
    }

    @objid ("de746768-8683-4af3-8f5a-ea126a7bab4a")
    private EWdSaveFormat getFileFormat(final File file) {
        final String name = file.getName();
        String ext = name.substring(name.indexOf("."));
        
        if (ext.equals(".rtf")) 
            return EWdSaveFormat.wdFormatRTF;
        else if (ext.equals(".html"))
            return EWdSaveFormat.wdFormatFilteredHTML;
        else if (ext.equals(".doc"))
            return EWdSaveFormat.wdFormatDocument97;
        else if (ext.equals(".docx"))
            return EWdSaveFormat.wdFormatXMLDocument;
        else
            return EWdSaveFormat.FormatOle;
    }

    /**
     * Create a Word.Application OLE client.
     * 
     * @param composite @return
     */
    @objid ("76e53bae-e7b0-420f-8b31-1e819bde8451")
    private OleClientSite createWordApplicationClientSite(final Composite composite) throws IOException {
        // Try all known MS Word versions starting with the latest
        final String[] names = new String[]{"Word.Application.14", "Word.Application.12", "Word.Application.8", "Word.Application.6", "Word.Application"};
        SWTException lastErr = null;
        for (String name : names) {
            try {
                OleClientSite newSite = new OleClientSite(composite, 0, name);
                return newSite;
            } catch (SWTException e) {
                if (e.code == OLE.ERROR_CANNOT_CREATE_OBJECT || e.code == OLE.ERROR_INVALID_CLASSID)
                    lastErr = e;
                else
                    throw new IOException(e);
            }
        }
        
        throw new IOException(lastErr);
    }

    /**
     * Save a screenshot of the OLE control in the idle image.
     * 
     * @param create true to create the image if none exist, <code>false</code> to do nothing
     * if the image does not exist.
     */
    @objid ("3491ba55-9c5f-4ba6-a851-12f1b49c6eee")
    void upgradeBackgroundImage(boolean create) {
        if (this.idleImg != null) {
            this.idleImg.dispose();
            this.idleImg = null;
        } else if (! create)
            return;
          
        Rectangle bounds = this.oleFrame.getBounds();
        Display display = this.oleFrame.getDisplay();
        
        if(bounds.isEmpty())
            return;
        
        this.idleImg = new Image(display, bounds);
        GC gc = new GC(this.idleImg);
        this.oleFrame.print(gc);
        
        gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        gc.setAlpha(150);
        gc.fillRectangle(bounds);
        
        gc.setForeground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
        gc.drawRectangle(1,1,bounds.width-2, bounds.height-2);
        
        gc.dispose();
        
        GridData gd = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gd.minimumWidth = bounds.width;
        gd.minimumHeight = bounds.height;
        gd.widthHint = bounds.width;
        gd.heightHint = bounds.height;
        gd.exclude = this.isActive;
        this.idleCanvas.setLayoutData(gd);
    }

    @objid ("9f6f0543-bc43-4c09-8ddf-09940106eded")
    void doFocusLost() {
        if (this.oleSite==null || this.oleSite.isDisposed())
            return;
        
        // Do nothing if :
        // - the shell itself has the focus: It means the Modelio menu bar is selected.
        // The Modelio menu bar contains Ms Office menus so the view must not be disabled.
        //  - another shell is selected : it means a dialog box is open.
        //  - there is no focus : the focus gone to another application
        final Control focus = this.oleSite.getDisplay().getFocusControl();
        final Shell thisShell = this.oleSite.getShell();
        if (focus == this.oleSite)
            return;
        else if ( focus ==  thisShell || focus == null || focus.getShell() != thisShell) {
            //System.out.println(" defer focus = "+focus);
        
            // Defer the desactivation to the moment the shell looses its focus
            thisShell.addFocusListener(new FocusAdapter() {
                
                @Override
                public void focusLost(FocusEvent e) {
                    thisShell.removeFocusListener(this);
                    MicrosoftDocumentViewer.this.focusLost();
                }
            });
        } else {
            //System.out.println("focus disable= "+focus);
            // Disable the GUI now.
            doDisableGui();
        }
    }

    /**
     * Restores Modelio menus and replace Word by an Image.
     */
    @objid ("f423aad9-5ed5-4890-823c-8778dd772380")
    private void doDisableGui() {
        // Hide the tool bars and menu bars
        //this.oleSite.exec(OLE.OLECMDID_HIDETOOLBARS, OLE.OLECMDEXECOPT_DODEFAULT, null, null);
        
        // Setup in place image
        //  Does not work on SWT/Windows 7 where SWT detach the oleFrame
        //  from the shell, setting the focus on the parent shell,
        //  making the editor have the focus again. :(
        if (this.oleFrame.isVisible())
            upgradeBackgroundImage(true);
        
        // Deactivate the OLE control to restore Modelio menu bar.
        //this.oleSite.setVisible(false);
        this.oleFrame.setVisible(false);
        this.idleCanvas.setVisible(true);
        
        this.oleSite.deactivateInPlaceClient();
        
        setLayoutIncluded(this.oleFrame, false);
        setLayoutIncluded(this.idleCanvas, true);
        this.oleFrame.getParent().layout();
        
        this.isActive = false;
        
        /*
        // Try to set print preview mode
        OleAutomation automation = new OleAutomation(this.oleSite);
        
        int[] methodIDs = automation.getIDsOfNames(new String[]{"PrintPreview"});
        if (methodIDs == null)
            return;
        int methodID = methodIDs[0];
        
        Variant result = automation.invoke(methodID, new Variant[]{new Variant(true)});
        System.out.println("Variant result = "+result);
        automation.dispose();  
        */
    }

    @objid ("34f7f17a-6959-472d-9f2b-1647791c81af")
    private static void setLayoutIncluded(Control widget, boolean included) {
        ((GridData)widget.getLayoutData()).exclude = ! included;
    }

    /**
     * @return true if the OLE widget is active.
     */
    @objid ("9d8dbdb0-5c45-470c-8c86-a4c80a80c018")
    boolean isActive() {
        return this.isActive;
    }

    @objid ("b476e69d-bfea-429c-ac98-3abe5fc2c2cb")
    private interface WdSaveOptions {
        @objid ("9dff805a-128b-435a-82f5-3163e4829f67")
        public static final int wdDoNotSaveChanges = 0;

        @objid ("a109ba67-c5df-4761-8901-733b96dfc85c")
        public static final int wdPromptToSaveChanges = -2;

        @objid ("16ab6e82-1371-4e96-b031-b19d123e4b22")
        public static final int wdSaveChanges = -1;

    }

    /**
     * {@link AutoCloseable} that make a backup of a file
     * and restores it on closing unless {@link #discard()} is called.
     * <p>
     * If {@link #discard()} is called then the backup is deleted on close
     * @author cmarin
     */
    @objid ("9fff4c7d-3942-46a0-819c-434ec6a48163")
    private static class Backuper implements Closeable {
        @objid ("1a9034b0-0117-4191-87a4-eac63199f763")
        private boolean success = false;

        @objid ("51274c35-fd4e-4d68-a851-262f7df5bc73")
        private Path tempFile;

        @objid ("92095877-b231-4fb5-8c4f-72228f59444d")
        private Path origFile;

        @objid ("cfefefeb-c3e1-4a75-8ff2-b43fe207bf81")
        public Backuper(Path file) throws IOException {
            this.origFile = file;
            this.tempFile = file.resolveSibling(file.getFileName().toString()+ ".tmp"); //$NON-NLS-1$
            
            Files.deleteIfExists(this.tempFile);
            Files.move(file, this.tempFile);
        }

        @objid ("98f3f458-0674-4eea-9790-bbd2ff01e58b")
        public void discard() {
            this.success = true;
        }

        @objid ("4b6b3c62-a067-4f3b-8918-d57aa25606fc")
        @Override
        public void close() throws IOException {
            if (this.success) {
                // successful so discard the backup
                try {
                    Files.deleteIfExists(this.tempFile);
                } catch (FileSystemException e) {
                    MicrosoftEditors.LOG.warning(FileUtils.getLocalizedMessage(e));
                } catch (IOException e) {
                    MicrosoftEditors.LOG.warning(e);
                }
            } else {
                //  failed so restore the backup
                Files.deleteIfExists(this.origFile);
                Files.move(this.tempFile, this.origFile);
            }
        }

    }

}
