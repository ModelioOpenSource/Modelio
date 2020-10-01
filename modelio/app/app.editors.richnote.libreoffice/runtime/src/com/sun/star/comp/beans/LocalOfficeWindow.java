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

import java.awt.Component;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.awt.Rectangle;
import com.sun.star.awt.WindowAttribute;
import com.sun.star.awt.WindowClass;
import com.sun.star.awt.WindowDescriptor;
import com.sun.star.awt.XToolkit;
import com.sun.star.awt.XVclWindowPeer;
import com.sun.star.awt.XWindow;
import com.sun.star.awt.XWindowPeer;
import com.sun.star.beans.NamedValue;
import com.sun.star.lang.EventObject;
import com.sun.star.lang.SystemDependent;
import com.sun.star.lang.XEventListener;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.uno.Any;
import com.sun.star.uno.Type;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

/**
 * This class represents a local office window.
 * 
 * @since OOo 2.0.0
 */
@objid ("c66ae712-16c4-41e4-9ae1-2b35003b945f")
public class LocalOfficeWindow extends java.awt.Canvas implements OfficeWindow, XEventListener {
    @objid ("8e79c217-980d-457b-9391-f26b0542859f")
    private boolean bPeer = false;

    @objid ("9ac81825-48b2-436f-bf2a-817528b7c5a6")
    private transient XWindowPeer mParentProxy;

    @objid ("5e8b519f-56ac-4f35-b472-d66ddb50d85c")
    private transient XWindowPeer mWindow;

    @objid ("ee758d06-446c-47c8-8bd3-0eb7d25f8c19")
    private static final long serialVersionUID = 1L;

    @objid ("a447e655-a75e-46c8-a323-ed5530f0c22b")
    private transient OfficeConnection mConnection;

    /**
     * Constructor.
     * 
     * @param connection The office connection object the window
     * belongs to.
     */
    @objid ("3667ef08-f14a-4c2b-803a-1fc238b3f550")
    protected LocalOfficeWindow(final OfficeConnection connection) {
        this.mConnection    = connection;
        this.mConnection.addEventListener(this);
    }

    /**
     * Retrieves an AWT component object associated with the OfficeWindow.
     * 
     * @return The AWT component object associated with the OfficeWindow.
     * @deprecated
     */
    @objid ("07838253-2568-451f-b88a-89a9d3ab53b1")
    @Deprecated
    @Override
    public Component getAWTComponent() {
        return this;
    }

    /**
     * Retrieves an UNO XWindowPeer object associated with the OfficeWindow.
     * 
     * @return The UNO XWindowPeer object associated with the OfficeWindow.
     * @deprecated
     */
    @objid ("1fadfb9f-c43a-48d4-ac16-8c1fcb335913")
    @Deprecated
    @Override
    public XWindowPeer getUNOWindowPeer() {
        if (this.mWindow == null)
            createUNOWindowPeer();
        return this.mWindow;
    }

    /**
     * Receives a notification about the connection has been closed.
     * This method has to set the connection to <code>null</code>.
     * @source The event object.
     */
    @objid ("36387179-1593-43ca-9761-815a407b2269")
    @Override
    public void disposing(final EventObject source) {
        // the window will be disposed by the framework
        this.mWindow = null;
        this.mConnection    = null;
    }

    /**
     * Returns an AWT toolkit.
     */
    @objid ("36beada7-a70a-43cb-8bce-2429590f8e5a")
    private XToolkit queryAWTToolkit() throws com.sun.star.uno.Exception {
        // Create a UNO toolkit.
        XMultiComponentFactory  compfactory;
        XComponentContext xContext = this.mConnection.getComponentContext();
        if ( xContext != null )
        {
            compfactory     = xContext.getServiceManager();
            XMultiServiceFactory    factory;
            factory = UnoRuntime.queryInterface(XMultiServiceFactory.class, compfactory);
        
            Object object = factory.createInstance( "com.sun.star.awt.Toolkit");
            return UnoRuntime.queryInterface(XToolkit.class, object);
        }
        else
            return null;
    }

    /**
     * called when system parent is available, reparents the bean window
     */
    @objid ("a0b43f80-885d-4ff2-9731-bd8f1d5e07cd")
    private synchronized void aquireSystemWindow() {
        if ( !this.bPeer )
        {
            // set real parent
            XVclWindowPeer xVclWindowPeer = UnoRuntime.queryInterface(
                                                                      XVclWindowPeer.class, this.mWindow);
        
            xVclWindowPeer.setProperty( "PluginParent", getWrappedWindowHandle());
            this.bPeer = true;
            // show document window
            XWindow aWindow = UnoRuntime.queryInterface(XWindow.class, this.mWindow);
            aWindow.setVisible( true );
        }
    }

    /**
     * / called when system parent is about to die, reparents the bean window
     */
    @objid ("9f38a0bf-ed32-497d-817d-bd6626c201fe")
    private synchronized void releaseSystemWindow() {
        if ( this.bPeer )
        {
            // hide document window
            XWindow aWindow = UnoRuntime.queryInterface(XWindow.class, this.mWindow);
            if (aWindow != null)
                aWindow.setVisible( false );
        
            // set null parent
            XVclWindowPeer xVclWindowPeer = UnoRuntime.queryInterface(XVclWindowPeer.class, this.mWindow);
            if (xVclWindowPeer != null)
                xVclWindowPeer.setProperty( "PluginParent", new Long(0) );
        
            this.bPeer = false;
        }
    }

    /**
     * / Overriding java.awt.Component.setVisible() due to Java bug (no showing event).
     */
    @objid ("ba47dcd2-5c00-4891-aa20-51e4db42c4ac")
    @Override
    public void setVisible(final boolean b) {
        super.setVisible(b);
        
        // Java-Bug: componentShown() is never called :-(
        // is still at least in Java 1.4.1_02
        if ( b )
            aquireSystemWindow();
        else
            releaseSystemWindow();
    }

    /**
     * Factory method for a UNO AWT toolkit window as a child of this Java window.
     */
    @objid ("4242186a-40fa-4984-a3c4-30ed8d884308")
    private synchronized XWindowPeer createUNOWindowPeer() {
        try
        {
            // get this windows native window type
            int type = getNativeWindowSystemType();
        
            // Java AWT windows only have a system window when showing.
            XWindowPeer parentPeer;
            if ( isShowing() )
            {
                // create direct parent relationship
                //setVisible( true );
                parentPeer = new JavaWindowPeerFake(getWrappedWindowHandle(), type);
                this.bPeer = true;
            }
            else
            {
                // no parent yet
                parentPeer = null;
                this.bPeer = false;
            }
        
            // create native window (mWindow)
            Rectangle aRect = new Rectangle( 0, 0, 20, 20 );
            WindowDescriptor desc = new WindowDescriptor();
            desc.Type = WindowClass.TOP;
            desc.Parent = parentPeer;
            desc.Bounds = aRect;
            desc.WindowServiceName = "workwindow";
            desc.WindowAttributes = (type == SystemDependent.SYSTEM_WIN32)
            ? WindowAttribute.SHOW : 0;
            this.mWindow    = queryAWTToolkit().createWindow(desc);
        
        
            // set initial visibility
            XWindow aWindow = UnoRuntime.queryInterface(XWindow.class, this.mWindow);
            aWindow.setVisible( this.bPeer );
        }
        catch (com.sun.star.uno.Exception exp) {
        }
        return this.mWindow;
    }

    /**
     * We make sure that the office window is notified that the parent
     * will be removed.
     */
    @objid ("3775fdf3-38ef-4726-aa62-e93f78aef61f")
    @Override
    public void removeNotify() {
        try {
            releaseSystemWindow();
        } catch (java.lang.Exception e) {
            System.err.println("LocaleOfficeWindow.removeNotify: Exception in releaseSystemWindow.");
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
        super.removeNotify();
    }

    /**
     * Retrives a platform dependant system window identifier.
     * 
     * @return The system window identifier.
     */
    @objid ("eecaeef6-39d6-417f-873f-73e669a4c144")
    private native long getNativeWindow();

    /**
     * Retrives a platform dependant system window type.
     * 
     * @return The system window type.
     */
    @objid ("f4530b30-89c1-4eca-b47a-18b1684ae846")
    private native int getNativeWindowSystemType();

    /**
     * Returns an Any containing a sequences of com.sun.star.beans.NamedValue. One NamedValue
     * contains the name "WINDOW" and the value is a Long representing the window handle.
     * The second NamedValue  has the name "XEMBED" and the value is true, when the XEmbed
     * protocol shall be used fore embedding the native Window.
     */
    @objid ("267be8e0-5e44-4a9f-98e6-a16f40035c12")
    protected Any getWrappedWindowHandle() {
        NamedValue window = new NamedValue("WINDOW", new Any(new Type(Long.class), new Long(getNativeWindow())));
        NamedValue xembed = new NamedValue("XEMBED", new Any(new Type(Boolean.class), new Boolean(false)));
        
        if (getNativeWindowSystemType() == SystemDependent.SYSTEM_XWINDOW )
        {
            if (Boolean.parseBoolean(System.getProperty("sun.awt.xembedserver")))
            {
                xembed = new NamedValue("XEMBED",
                                        new Any(new Type(Boolean.class), new Boolean(true)));
            }
        }
        return new Any(
                new Type("[]com.sun.star.beans.NamedValue"),
                new NamedValue[] {window, xembed});
    }

}
