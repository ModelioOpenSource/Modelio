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
import com.sun.star.comp.beans.SwtWinOOoBean;
import com.sun.star.comp.beans.SystemWindowException;
import com.sun.star.frame.XDesktop;
import com.sun.star.lang.DisposedException;
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
@objid ("e351bbf2-8e07-47cc-82fa-716a54e919e0")
public class LibreOfficeSwtWinDocumentViewer implements IEditedDocumentViewer {
    @objid ("b9a56306-1ca9-4731-89a3-65064a7a7170")
    private SwtWinOOoBean aBean;

    @objid ("4981eb71-a67b-4386-a475-ab4c72a659bd")
    private IRichNoteFileRepository fileManager;

    /**
     * Constructor.
     */
    @objid ("7e4c1a0d-35d7-4b91-9276-66aa1ee965fd")
    public LibreOfficeSwtWinDocumentViewer() {
        try {
            System.setProperty("sun.awt.noerasebackground", "true");
            System.setProperty("sun.awt.xembedserver", "true");
        } catch (NoSuchMethodError error) {
            // ignore
        }
    }

    @objid ("f7971dfc-7ec5-4b84-8c66-a31457bb9ec2")
    @Override
    public void setFileManager(IRichNoteFileRepository aFileManager) {
        this.fileManager = aFileManager;
    }

    /**
     * closes the bean viewer, leaves OOo running.
     */
    @objid ("f429ce9f-9d61-4f20-985a-e3aa39992df9")
    @Override
    public void close() {
        this.aBean.stopOOoConnection();
    }

    @objid ("19e3dd05-bdf9-4bd3-b05a-7067b6894aef")
    @Override
    public void closeDocument() throws IOException {
        this.aBean.closeDocument();
    }

    @objid ("0daf3526-2436-4adc-921d-d409e4abea82")
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
            this.aBean.getOOoConnection(); // Ensure native lib is loaded
            this.aBean.loadFromURL(format.getData(), null);
            Path p = this.fileManager.getNewRichNotePath(doc, format);
            this.aBean.storeAsURL(createUNOFileURL(p), null);
            this.fileManager.saveRichNote(doc, p);
        
            this.aBean.layout(true); // workaround view invisible on new doc
        } catch (NoDocumentException e) {
            throw new IOException(e);
        } catch (CloseVetoException e) {
            throw new IOException(e);
        } catch (NoConnectionException e) {
            throw new IOException(e);
        }
    }

    @objid ("a1f62151-e3e0-4495-a0d4-21b7e33a7fa8")
    @Override
    public void createPartControl(final Composite parent) {
        /* Create and setting up frame */
        this.aBean = new SwtWinOOoBean(parent, SWT.NONE);
    }

    @objid ("3b55b2e8-5495-4821-b6cb-a9810df5a0ba")
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

    @objid ("a2578190-675e-4ff8-8d6e-bbb52d4809b7")
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
     * 
     * @return the OpenOffice control.
     */
    @objid ("0d181df0-cbb3-4cc2-856b-a6487807a9f4")
    public Composite getControl() {
        return this.aBean;
    }

    @objid ("e10ede5a-1872-45ea-afd3-84f1f156c7df")
    @Override
    public boolean isDirty() {
        if (this.aBean.isDisposed()) {
            LibreOfficeEditors.LOG.warning(new IllegalStateException(this.aBean + " disposed, let's say it is not modified.").fillInStackTrace());
            return false;
        }
        
        CallWatchThread watchDog = new CallWatchThread(1000, "LibreOfficeSwtDocumentViewer.isModified()");
        try {
            OfficeDocument doc = this.aBean.getDocument();
            boolean ret = doc != null && doc.isModified();
            return ret;
        } catch (DisposedException e) {
            return false;
        } catch (NoConnectionException e) {
            return false;
        } catch (RuntimeException e) {
            // Probably interrupted
            if (e.getMessage().contains("java.lang.InterruptedException")) {
                return false;
            }
            LibreOfficeEditors.LOG.warning(e);
            return false;
        } finally {
            watchDog.cancel();
        }
    }

    @objid ("8cf617cc-731b-4fec-8138-8d0a695d9756")
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
        } catch (CloseVetoException e) {
            throw new IOException(e);
        } catch (NoConnectionException e) {
            throw new IOException(e);
        } catch (SystemWindowException e) {
            throw new IOException(e);
        }
    }

    @objid ("9b56211c-3d9c-478a-9c87-c4db6cba3213")
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

    @objid ("a5dad65c-ef89-42f5-831b-93103820507e")
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

    @objid ("3932852a-a978-46c2-8d95-a4c7680851d2")
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
        
            try {
                // store the document
                try {
                    aDocument.store();
                } catch (com.sun.star.task.ErrorCodeIOException e) {
                    throw new java.io.IOException(ErrorTranslator.getErrorMessage(e), e);
                } catch (com.sun.star.io.IOException aExc) {
                    throw new java.io.IOException(aExc);
                }
            } finally {
                // end runtime timeout
                aCallWatchThread.cancel();
            }
        } catch (NoConnectionException e) {
            throw new IOException(e);
        } catch (NoDocumentException e) {
            throw new IOException(e);
        }
    }

    @objid ("77f274dc-8ca7-4742-aaf6-aee6934d8a60")
    @Override
    public void setFocus() {
        if (this.aBean != null && !this.aBean.isDisposed()) {
            this.aBean.setFocus();
        }
    }

    /**
     * closes the bean viewer and tries to terminate OOo.
     * 
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection is established.
     */
    @objid ("6ccfc0bd-1d56-4aea-ba39-5b55cdf9ce70")
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
     * 
     * @param newfile a file path
     * @return the OpenOffice compatible URL.
     * @throws com.sun.star.comp.beans.NoConnectionException if not connected to OpenOffice
     */
    @objid ("5616ee7f-a3a5-4767-b849-6ab1e7163dca")
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
     * 
     * @return the HTML export filter name, or <code>null</code>.
     * @throws com.sun.star.comp.beans.NoConnectionException if no connection to LibreOffice is established.
     */
    @objid ("41bc2d78-144e-491a-8667-f0476adf5ef5")
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

    @objid ("3d0f57d0-2e76-43a7-a403-6e6114a99872")
    @Override
    public boolean isDisposed() throws IllegalStateException {
        if (this.aBean == null) {
            throw new IllegalStateException("Viewer is not yet initialized.");
        }
        return this.aBean.isDisposed();
    }

}
