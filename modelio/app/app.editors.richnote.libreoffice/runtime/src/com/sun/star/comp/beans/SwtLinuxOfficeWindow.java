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

package com.sun.star.comp.beans;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.awt.XToolkit;
import com.sun.star.awt.XVclWindowPeer;
import com.sun.star.awt.XWindow2;
import com.sun.star.awt.XWindowPeer;
import com.sun.star.beans.NamedValue;
import com.sun.star.lang.EventObject;
import com.sun.star.lang.XEventListener;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.uno.Any;
import com.sun.star.uno.Type;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeEditors;
import org.modelio.platform.utils.log.writers.PluginLogger;

/**
 * This class represents a local office window.
 * 
 * Other references:
 * 
 * https://github.com/LibreOffice/noa-libre/blob/master/src/ag/ion/bion/officelayer/internal/application/connection/LocalOfficeConnection.java
 * @since OOo 2.0.0
 */
@objid ("03c810c8-7611-4dc8-8eaf-a76e75906aec")
class SwtLinuxOfficeWindow extends Composite implements XEventListener {
    @objid ("3143a696-23f3-472a-b9c3-8b788dd5027b")
    private boolean bPeer = false;

    @objid ("b15f4250-16b9-42c5-be56-791a471dd056")
    private transient XWindowPeer xWindowPeer;

    @objid ("c3da44ed-b54c-417e-a1c6-ff081a3230c6")
    private transient XWindow2 xWindow;

    @objid ("09481281-e538-4d2c-94ce-fc8eded10327")
    private transient OfficeConnection mConnection;

    @objid ("0e954250-a572-418c-9f0a-cb89f93b9ac4")
    private static final PluginLogger LOG = LibreOfficeEditors.LOG;

    /**
     * Constructor.
     * 
     * @param connection The office connection object the window
     * belongs to.
     * @param parent a widget which will be the parent of the new instance (cannot be null)
     */
    @objid ("ec3c6a91-5c95-4c38-9e32-7fb7b8ad9d2b")
    protected SwtLinuxOfficeWindow(final OfficeConnection connection, final Composite parent) {
        super(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND |SWT.NO_REDRAW_RESIZE | SWT.NO_MERGE_PAINTS);
        
        this.mConnection = connection;
        this.mConnection.addEventListener(this);
        
        addDisposeListener(new DisposeListener() {
        
            /**
             * We make sure that the office window is notified that the parent
             * will be removed.
             */
            @Override
            public void widgetDisposed(DisposeEvent ev) {
                LOG.debug("SwtOfficeWindow.DisposeListener: SwtOfficeWindow disposed.");
                removeDisposeListener(this);
        
                try {
                    releaseSystemWindow();
                } catch (java.lang.Exception e) {
                    LOG.warning("SwtOfficeWindow.DisposeListener: Exception in releaseSystemWindow():");
                    LOG.warning(e);
                }
            }
        });
        
        addFocusListener(new FocusListener() {
        
            @Override
            public void focusLost(FocusEvent e) {
                LOG.debug("swt.FocusListener.focusLost: %s", e);
                if (SwtLinuxOfficeWindow.this.xWindow != null) {
                    SwtLinuxOfficeWindow.this.xWindow.setFocus();
                }
            }
        
            @Override
            public void focusGained(FocusEvent e) {
                LOG.debug("swt.FocusListener.focusGained: %s", e);
            }
        });
    }

    /**
     * Retrieves an UNO XWindowPeer object associated with the OfficeWindow.
     * 
     * @return The UNO XWindowPeer object associated with the OfficeWindow.
     */
    @objid ("3bb4520f-706b-4d1b-9792-e03797646afc")
    public synchronized XWindowPeer getUNOWindowPeer() {
        if (this.xWindowPeer == null) {
            // some JNI functions will not work without this
            super.setVisible(true);
        
            com.sun.star.awt.XToolkit xToolkit = queryAWTToolkit();
            com.sun.star.awt.XSystemChildFactory xFac = UnoRuntime.queryInterface(com.sun.star.awt.XSystemChildFactory.class, xToolkit);
        
            byte[] lIgnoredProcessID = new byte[0];
            this.xWindowPeer = xFac.createSystemChild(getWrappedWindowHandle(),
                                                  lIgnoredProcessID,
                                                  OSDetect.getNativeWindowSystemType());
        
            this.xWindow = UnoRuntime.queryInterface(com.sun.star.awt.XWindow2.class, this.xWindowPeer);
        
            aquireSystemWindow();
            this.xWindow.setFocus();
        }
        return this.xWindowPeer;
    }

    /**
     * Receives a notification about the connection has been closed.
     * This method has to set the connection to <code>null</code>.
     * @source The event object.
     */
    @objid ("841c830f-3d4a-4a0e-ace6-abc41df099ad")
    @Override
    public synchronized void disposing(final EventObject source) {
        // the window will be disposed by the framework
        this.xWindowPeer = null;
        this.xWindow = null;
        this.mConnection    = null;
    }

    /**
     * Returns an AWT toolkit.
     */
    @objid ("7e625ebc-2e23-4fd4-9218-faabdba8d70e")
    private XToolkit queryAWTToolkit() {
        XComponentContext xContext = this.mConnection.getComponentContext();
        if ( xContext != null ) {
            // Create a UNO toolkit.
            XMultiComponentFactory compfactory = xContext.getServiceManager();
            XMultiServiceFactory factory = UnoRuntime.queryInterface(XMultiServiceFactory.class, compfactory);
            try {
                Object object = factory.createInstance( "com.sun.star.awt.Toolkit");
                return UnoRuntime.queryInterface(XToolkit.class, object);
            } catch (com.sun.star.uno.Exception e) {
                throw new RuntimeException("Failed getting com.sun.star.awt.Toolkit: "+e.toString(), e);
            }
        } else {
            return null;
        }
    }

    /**
     * called when system parent is available, reparents the bean window
     */
    @objid ("6000e5c1-6d46-48ca-9602-fabbd64fa256")
    private synchronized void aquireSystemWindow() {
        if ( !this.bPeer ) {
            checkWidget();
        
            // set real parent
            XVclWindowPeer xVclWindowPeer = UnoRuntime.queryInterface(XVclWindowPeer.class, this.xWindowPeer);
            xVclWindowPeer.setProperty( "PluginParent", getWrappedWindowHandle());
        
            this.bPeer = true;
        
            // show document window
            this.xWindow.setVisible( true );
            this.xWindow.setEnable(true);
            //this.xWindow.setFocus();
        }
    }

    /**
     * / called when system parent is about to die, reparents the bean window
     */
    @objid ("6e904bda-68c7-4bd0-b287-fcb1c9ca6dc1")
    synchronized void releaseSystemWindow() {
        if ( this.bPeer ) {
            try {
                // hide document window
                if (this.xWindow != null) {
                    this.xWindow.setVisible( false );
                }
        
                // set null parent
                XVclWindowPeer xVclWindowPeer = UnoRuntime.queryInterface(XVclWindowPeer.class, this.xWindowPeer);
                if (xVclWindowPeer != null) {
                    xVclWindowPeer.setProperty( "PluginParent", new Long(0) );
                }
            } catch ( com.sun.star.lang.DisposedException e) {
                // Ignore
            }
        
            this.bPeer = false;
        }
    }

    /**
     * Overriding {@link Composite#setVisible(boolean)} due to Java bug (no showing event).
     */
    @objid ("88b567de-0865-40e2-9cbf-1a025f2f1c8c")
    @Override
    public void setVisible(final boolean b) {
        // Java-Bug: componentShown() is never called :-(
        // is still at least in Java 1.4.1_02
        if ( b ) {
            super.setVisible(b);
            aquireSystemWindow();
        } else {
            releaseSystemWindow();
            super.setVisible(b);
        }
    }

    /**
     * Returns an Any containing a sequences of {@link com.sun.star.beans.NamedValue}.
     * <li>One NamedValue contains the name "WINDOW" and the value is a Long representing the window handle.
     * <li>The second NamedValue has the name "XEMBED" and the value is true, when the XEmbed
     * protocol shall be used fore embedding the native Window.
     */
    @objid ("1a2fbd7e-354f-4338-bd88-c01fe5bc1f1b")
    protected Any getWrappedWindowHandle() {
        NamedValue window = new NamedValue("WINDOW", new Any(new Type(Long.class), new Long(OSDetect.getNativeWindow(this))));
        NamedValue xembed = new NamedValue("XEMBED", new Any(Type.BOOLEAN, Boolean.FALSE));
        
        if (Boolean.parseBoolean(System.getProperty("sun.awt.xembedserver")))
        {
            xembed = new NamedValue("XEMBED", new Any(Type.BOOLEAN, Boolean.TRUE));
        }
        return new Any(new Type("[]com.sun.star.beans.NamedValue"),
                                        new NamedValue[] {window, xembed});
    }

}
