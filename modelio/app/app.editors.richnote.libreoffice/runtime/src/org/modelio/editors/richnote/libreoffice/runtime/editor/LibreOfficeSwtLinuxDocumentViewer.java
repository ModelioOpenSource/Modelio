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
package org.modelio.editors.richnote.libreoffice.runtime.editor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.beans.PropertyValue;
import com.sun.star.comp.beans.CallWatchThread;
import com.sun.star.comp.beans.ErrorTranslator;
import com.sun.star.comp.beans.NoConnectionException;
import com.sun.star.comp.beans.NoDocumentException;
import com.sun.star.comp.beans.OfficeDocument;
import com.sun.star.comp.beans.SwtLinuxOOoBean;
import com.sun.star.comp.beans.SystemWindowException;
import com.sun.star.frame.XDesktop;
import com.sun.star.uno.RuntimeException;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.CloseVetoException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.editors.richnote.libreoffice.editor.IEditedDocumentViewer;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeEditors;
import org.modelio.metamodel.uml.infrastructure.Document;

/**
 * LibreOffice document viewer.
 * <p>
 * Loads LibreOffice and displays it in the provided SWT widget.
 * @author cmarin
 */
@objid ("3b52add0-1e5a-47db-a26c-59f4faebe036")
public class LibreOfficeSwtLinuxDocumentViewer implements IEditedDocumentViewer {
    @objid ("a38ee288-2c12-4ed7-9ece-72706a5eec6d")
    private SwtLinuxOOoBean aBean;

    @objid ("f01261a1-d6f2-499e-87ba-3b00c62d334a")
    private IRichNoteFileRepository fileManager;

    /**
     * Constructor.
     */
    @objid ("dc1c5e59-ddd6-4f8a-972e-1a227cf67403")
    public  LibreOfficeSwtLinuxDocumentViewer() {
        try {
            System.setProperty("sun.awt.noerasebackground", "true");
            System.setProperty("sun.awt.xembedserver", "true");
        } catch (NoSuchMethodError error) {
            // ignore
        }
        
    }

    @objid ("a6fd78d0-25df-4856-8f94-0b6aba66d36b")
    @Override
    public void setFileManager(IRichNoteFileRepository aFileManager) {
        this.fileManager = aFileManager;
    }

    /**
     * closes the bean viewer, leaves OOo running.
     */
    @objid ("f1e184a6-944a-4627-a265-a1d3e864ed49")
    @Override
    public void close() {
        this.aBean.stopOOoConnection();
    }

    @objid ("e526f60d-ba63-4d95-835e-66278bfa3895")
    @Override
    public void closeDocument() throws IOException {
        this.aBean.closeDocument();
    }

    @objid ("7be3a168-c171-48a4-a0ea-012d5a7aa22b")
    @Override
    public void createDocument(final Document doc, RichNoteFormat format) throws IOException {
        this.aBean.setVisible(true);
        
        /**
         * Create and open a new text document.
         * @param doc the document to create.
         * @throws com.sun.star.util.CloseVetoException
         *             if the currently displayed document cannot be closed because it is still be used, for example it is
         *             printed.
         * @throws com.sun.star.comp.beans.NoConnectionException if the connection is not established.
         * @throws java.io.IOException if an IO error occurs reading the resource specified by the URL.
         * @throws com.sun.star.comp.beans.SystemWindowException if no system window can be acquired.
         */
        if (format.getData() == null || format.getData().isEmpty()) {
            throw new IOException("'" + format.getLabel() + "' rich notes cannot be created with OpenOffice.");
        }
        
        try {
            LibreOfficeSwtLinuxDocumentViewer.this.aBean.getOOoConnection(); // Ensure native lib is loaded
            LibreOfficeSwtLinuxDocumentViewer.this.aBean.loadFromURL(format.getData(), null);
            Path p = this.fileManager.getNewRichNotePath(doc, format);
            LibreOfficeSwtLinuxDocumentViewer.this.aBean.storeAsURL(createUNOFileURL(p), null);
            this.fileManager.saveRichNote(doc, p);
        
        } catch (NoDocumentException e) {
            throw new IOException(e);
        } catch (CloseVetoException e) {
            throw new IOException(e);
        } catch (NoConnectionException e) {
            throw new IOException(e);
        }
        
    }

    @objid ("25d3f092-26fd-4d23-8943-2074dc09ca44")
    @Override
    public void createPartControl(final Composite parent) {
        /* Create and setting up frame */
        this.aBean = new SwtLinuxOOoBean(parent, SWT.NONE);
        
    }

    @objid ("9a417d47-f45e-4fd0-a47e-ae32daef6b04")
    @Override
    public String getAsHtml() throws IOException {
        try {
            // see:
            // - http://wiki.services.openoffice.org/wiki/Framework/Article/Filter/FilterList_OOo_3_0#FilterOption
            // - http://codesnippets.services.openoffice.org/Office/Office.ConvertDocuments.snip
            // - http://wiki.services.openoffice.org/wiki/Documentation/DevGuide/OfficeDev/Storing_Documents
        
            String conversionFilter = getHtmlExportFilterName();
        
            if (conversionFilter == null) {
                throw new IOException("This document does not support HTML export.");
            }
        
            // Set properties for conversions
            PropertyValue[] conversionProperties = new PropertyValue[3];
        
            conversionProperties[0] = new PropertyValue();
            conversionProperties[0].Name = "Overwrite";
            conversionProperties[0].Value = new Boolean(true);
        
            conversionProperties[1] = new PropertyValue();
            conversionProperties[1].Name = "FilterName";
            conversionProperties[1].Value = conversionFilter;
        
            conversionProperties[2] = new PropertyValue();
            conversionProperties[2].Name = "CharacterSet";
            conversionProperties[2].Value = "UTF-8";
        
            ByteArrayOutputStream stream = new ByteArrayOutputStream(20000);
            this.aBean.storeToStream(stream, conversionProperties);
            return stream.toString("UTF-8");
        } catch (NoConnectionException e) {
            throw new IOException(e);
        } catch (NoDocumentException e) {
            throw new IOException(e);
        }
        
    }

    @objid ("eec840a9-8899-4095-b626-df1a0298d759")
    @Override
    public String getAsText() throws IOException {
        try {
            com.sun.star.frame.XModel model = this.aBean.getDocument();
            com.sun.star.text.XTextDocument myDoc = UnoRuntime.queryInterface(com.sun.star.text.XTextDocument.class, model);
            com.sun.star.text.XText xText = myDoc.getText();
            return xText.getString();
        } catch (NoConnectionException e) {
            throw new IOException(e);
        }
        
    }

    /**
     * Get the OpenOffice control.
     * @return the OpenOffice control.
     */
    @objid ("fd2d7f2b-5927-4fcd-bc54-17a488fa889c")
    public Composite getControl() {
        return this.aBean;
    }

    @objid ("aedc221f-b7fe-4148-ab6e-7c27250c430b")
    @Override
    public boolean isDirty() {
        if (this.aBean.isDisposed()) {
            LibreOfficeEditors.LOG.warning(new IllegalStateException(this.aBean + " disposed, let's say it is not modified.").fillInStackTrace());
            return false;
        }
        
        final SwtLinuxOOoBean bean = this.aBean;
        CallWatchThread watchDog = new CallWatchThread(1000, "LibreOfficeSwtDocumentViewer.isModified()");
        try {
            OfficeDocument doc = bean.getDocument();
            boolean ret = doc != null && doc.isModified();
            return ret;
        } catch (RuntimeException e) {
            // Probably interrupted
            if (e.getMessage().contains("java.lang.InterruptedException")) {
                return false;
            }
            LibreOfficeEditors.LOG.warning(e);
            return false;
        } catch (NoConnectionException e) {
            LibreOfficeEditors.LOG.warning(e);
            return false;
        } finally {
            watchDog.cancel();
        }
        
    }

    @objid ("6bf8141b-2237-45f5-9c7e-b5b81a6f60e4")
    @Override
    public void openDocument(final InputStream stream, final boolean modifiable) throws IOException {
        try {
        
            // Read only state
            com.sun.star.beans.PropertyValue args[] = new com.sun.star.beans.PropertyValue[] {
                    new com.sun.star.beans.PropertyValue("ReadOnly",
                            -1,
                            new Boolean(!modifiable),
                            com.sun.star.beans.PropertyState.DIRECT_VALUE) };
        
            this.aBean.loadFromStream(stream, args);
            this.aBean.aquireSystemWindow();
            // addModifylistener(this.aBean.getDocument());
        } catch (CloseVetoException e) {
            throw new IOException(e);
        } catch (NoConnectionException e) {
            throw new IOException(e);
        } catch (SystemWindowException e) {
            throw new IOException(e);
        }
        
    }

    @objid ("323b3e7d-4069-4f3a-8348-adbfd1445820")
    @Override
    public void openDocument(final Path file, final boolean modifiable) throws IOException {
        try {
            String aURL = createUNOFileURL(file);
        
            // Read only state
            com.sun.star.beans.PropertyValue args[] = new com.sun.star.beans.PropertyValue[] {
                    new com.sun.star.beans.PropertyValue(
                            "ReadOnly",
                            -1,
                            new Boolean(!modifiable),
                            com.sun.star.beans.PropertyState.DIRECT_VALUE),
                    new com.sun.star.beans.PropertyValue("Preview",
                            -1,
                            new Boolean(!modifiable),
                            com.sun.star.beans.PropertyState.DIRECT_VALUE)
            };
        
            this.aBean.loadFromURL(aURL, args);
            this.aBean.aquireSystemWindow();
            // addModifylistener(this.aBean.getDocument());
        } catch (CloseVetoException e) {
            throw new IOException(e);
        } catch (NoConnectionException e) {
            throw new IOException(e);
        } catch (SystemWindowException e) {
            throw new IOException(e);
        } catch (UnsatisfiedLinkError e) {
            throw new IOException(e);
        }
        
    }

    @objid ("ee71b2ef-5408-4531-9067-d8e4806631ba")
    @Override
    public void saveDocument(final OutputStream stream) throws IOException {
        try {
            this.aBean.storeToStream(stream, null);
        } catch (NoConnectionException e) {
            throw new IOException(e);
        } catch (NoDocumentException e) {
            throw new IOException(e);
        }
        
    }

    @objid ("26f4b3f5-48bd-4d17-af0b-26860ce771e9")
    @Override
    public void saveDocument() throws IOException {
        try {
            /**
             * Store the currently edited document.
             * @throws java.lang.IllegalArgumentException if either of the arguments is out of the specified range.
             * @throws java.io.IOException if an IO error occurs reading the resource specified by the URL.
             * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established.
             * @throws com.sun.star.comp.beans.NoDocumentException if no document is loaded
             */
            OfficeDocument aDocument = this.aBean.getDocument();
            // no document available?
            if (aDocument == null) {
                throw new NoDocumentException();
            }
        
            // start runtime timeout
            CallWatchThread aCallWatchThread = new CallWatchThread(10000, "storeToURL");
        
            // store the document
            try {
                aDocument.store();
            } catch (com.sun.star.task.ErrorCodeIOException e) {
                throw new java.io.IOException(ErrorTranslator.getErrorMessage(e), e);
            } catch (com.sun.star.io.IOException aExc) {
                throw new java.io.IOException(aExc);
            }
        
            // end runtime timeout
            aCallWatchThread.cancel();
        
            this.aBean.setToolVisible("private:resource/toolbar/toolbar", false, true);
        
        } catch (java.lang.InterruptedException aExc) {
            throw new IOException(aExc);
        } catch (NoConnectionException e) {
            throw new IOException(e);
        } catch (NoDocumentException e) {
            throw new IOException(e);
        }
        
    }

    @objid ("509fca64-fb3c-44b9-8fbb-14b0d18279c9")
    @Override
    public void setFocus() {
        if (this.aBean != null && !this.aBean.isDisposed()) {
            this.aBean.setFocus();
        }
        
    }

    /**
     * closes the bean viewer and tries to terminate OOo.
     * @throws NoConnectionException if no connection is established.
     */
    @objid ("55c7e8e2-65b9-402e-83e5-bcdadbaca812")
    public void terminate() throws NoConnectionException {
        XDesktop xDesktop = null;
        xDesktop = this.aBean.getOOoDesktop();
        this.aBean.setVisible(false);
        this.aBean.stopOOoConnection();
        if (xDesktop != null) {
            xDesktop.terminate();
        }
        
    }

    /**
     * Creating a correct File URL that OpenOffice can handle. This is
     * necessary to be platform independent.
     * @param newfile a file path
     * @return the OpenOffice compatible URL.
     * @throws NoConnectionException if not connected to OpenOffice
     */
    @objid ("542b563b-52a0-4924-8fca-f25fee7d0187")
    private String createUNOFileURL(final Path newfile) throws NoConnectionException {
        java.net.URL before = null;
        try {
            before = newfile.toUri().toURL();
        } catch (MalformedURLException e) {
            throw new java.lang.IllegalArgumentException(e);
        }
        
        XComponentContext xRemoteContext;
        try {
            xRemoteContext = this.aBean.getOOoConnection().getComponentContext();
        } catch (NoConnectionException e) {
            throw e;
        } catch (Exception aExc) {
            throw new NoConnectionException(aExc);
        }
        if (xRemoteContext == null) {
            throw new NoConnectionException();
        }
        
        // Create a URL, which can be used by UNO
        String myUNOFileURL = com.sun.star.uri.ExternalUriReferenceTranslator
                .create(xRemoteContext).translateToInternal(before.toExternalForm());
        
        if (myUNOFileURL.isEmpty() && !newfile.toString().isEmpty()) {
            throw new IllegalArgumentException("'" + before.toString() +
                    "' URL conversion failed. Filelocation " +
                    "contains illegal characters: " +
                    newfile.toString());
        }
        return myUNOFileURL;
    }

    /**
     * Get the filter name that must be used to export the current document to HTML.
     * <p>
     * The filter name depends on the document type.
     * Returns <code>null</code> if the document cannot be exported to HTML.
     * @return the HTML export filter name, or <code>null</code>.
     * @throws NoConnectionException if no connection to LibreOffice is established.
     */
    @objid ("36b76489-ccc8-46a2-b94c-fe71ab008ec4")
    private String getHtmlExportFilterName() throws NoConnectionException {
        // Detect document type by asking XServiceInfo
        com.sun.star.lang.XServiceInfo xInfo = UnoRuntime.queryInterface(
                com.sun.star.lang.XServiceInfo.class, this.aBean.getDocument());
        
        // Determine suitable HTML filter name for export.
        if (xInfo != null) {
            if (xInfo.supportsService("com.sun.star.text.TextDocument")) {
                return "HTML (StarWriter)";
            } else if (xInfo.supportsService("com.sun.star.text.WebDocument")) {
                return "HTML";
            } else if (xInfo.supportsService("com.sun.star.sheet.SpreadsheetDocument")) {
                return "HTML (StarCalc)";
            }
        }
        return null;
    }

    @objid ("4c1ee893-1188-4cfb-ac0e-e6a3701f5576")
    @Override
    public boolean isDisposed() throws IllegalStateException {
        if (this.aBean == null) {
            throw new IllegalStateException("Viewer is not yet initialized.");
        }
        return this.aBean.isDisposed();
    }

}
