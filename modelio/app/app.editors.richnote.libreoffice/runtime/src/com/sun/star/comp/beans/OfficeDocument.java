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
import com.sun.star.uno.UnoRuntime;
import com.sun.star.util.CloseVetoException;
import com.sun.star.util.XCloseListener;
import com.sun.star.util.XCloseable;

/**
 * ==========================================================================
 * Wrapper class for service OfficeDocument which emulates the upcoming mode of automatic
 * runtime Java classes to get rid of the need for queryInterface.
 * 
 * See further information on the wrapping and compatibility limitations in the base class Wrapper.
 * 
 * @since OOo 2.0.0
 */
@objid ("d7b971ff-be65-44c0-8e41-5474a89fc051")
public class OfficeDocument extends Wrapper implements com.sun.star.frame.XModel, com.sun.star.util.XModifiable, com.sun.star.frame.XStorable, com.sun.star.view.XPrintable, com.sun.star.util.XCloseable {
    @objid ("06cb3a71-46d5-4910-9e61-0cf4a3d96dbb")
    private com.sun.star.frame.XModel xModel;

    @objid ("1f6c434f-5079-48fd-bd5b-e6982420a7c3")
    private com.sun.star.util.XModifiable xModifiable;

    @objid ("85f6e310-981a-4278-bf1e-91a61bdd23a9")
    private com.sun.star.view.XPrintable xPrintable;

    @objid ("fa3c7ed0-7e42-48a2-8c63-96b29827ad73")
    private com.sun.star.frame.XStorable xStorable;

    @objid ("945aab8a-dd37-4221-a13a-67ad3cd41d6e")
    private com.sun.star.util.XCloseable xCloseable;

    /**
     * Create the document
     * @param xModel XModel of the document.
     */
    @objid ("88d65d68-31da-4dff-bb61-8637cddf0258")
    public  OfficeDocument(final com.sun.star.frame.XModel xModel) {
        super( xModel );
        
        this.xModel = xModel;
        this.xModifiable = UnoRuntime.queryInterface( 
                                                     com.sun.star.util.XModifiable.class, xModel );
        this.xPrintable = UnoRuntime.queryInterface( 
                                                    com.sun.star.view.XPrintable.class, xModel );
        this.xStorable = UnoRuntime.queryInterface( 
                                                   com.sun.star.frame.XStorable.class, xModel );
        this.xCloseable = UnoRuntime.queryInterface(XCloseable.class, xModel );
        
    }

    /**
     * ==========================================================
     * com.sun.star.frame.XModel
     * ----------------------------------------------------------
     */
    @objid ("1d21b837-f679-4829-9a53-93f092a2fe39")
    @Override
    public boolean attachResource(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) {
        return this.xModel.attachResource( aURL, aArguments );
    }

    @objid ("db00f3f6-43c9-4be5-bd66-38581d870e61")
    @Override
    public String getURL() {
        return this.xModel.getURL();
    }

    @objid ("6c18fe50-ee0c-4956-a1d4-fda558d54eb7")
    @Override
    public com.sun.star.beans.PropertyValue[] getArgs() {
        return this.xModel.getArgs();
    }

    @objid ("4be06ef1-6980-4224-9abf-7baaa3111b04")
    @Override
    public void connectController(final com.sun.star.frame.XController xController) {
        this.xModel.connectController( xController );
    }

    @objid ("5d82c4e0-fb63-473c-8d55-f1a1852b7064")
    @Override
    public void disconnectController(final com.sun.star.frame.XController xController) {
        this.xModel.disconnectController( xController );
    }

    @objid ("929d31b4-59d5-485f-a8c9-151b4defb141")
    @Override
    public void lockControllers() {
        this.xModel.lockControllers();
    }

    @objid ("17f1d1f1-dea6-4818-af50-340aba5dd54f")
    @Override
    public void unlockControllers() {
        this.xModel.unlockControllers();
    }

    @objid ("19eb9d7c-f4b9-496d-9bbe-7c37f028d62e")
    @Override
    public boolean hasControllersLocked() {
        return this.xModel.hasControllersLocked();
    }

    @objid ("cc5703d8-46e7-42dd-8feb-2c9523823da8")
    @Override
    public com.sun.star.frame.XController getCurrentController() {
        return this.xModel.getCurrentController();
    }

    @objid ("f0aacfd3-7f9c-4a6a-8a09-b8b94fa4d66e")
    @Override
    public void setCurrentController(final com.sun.star.frame.XController xController) throws com.sun.star.container.NoSuchElementException {
        this.xModel.setCurrentController( xController );
    }

    @objid ("1c1154bd-f1dd-47ba-a25c-63e54bf06015")
    @Override
    public java.lang.Object getCurrentSelection() {
        return this.xModel.getCurrentSelection();
    }

    /**
     * ==========================================================
     * com.sun.star.util.XModifyBroadcaster
     * ----------------------------------------------------------
     */
    @objid ("b2181aec-10bf-463c-bfce-bf4eeddafddb")
    @Override
    public void addModifyListener(final com.sun.star.util.XModifyListener xListener) {
        this.xModifiable.addModifyListener( xListener );
    }

    @objid ("791bf8d6-da1a-420c-b333-aafdaef487ee")
    @Override
    public void removeModifyListener(final com.sun.star.util.XModifyListener xListener) {
        this.xModifiable.removeModifyListener( xListener );
    }

    /**
     * ==========================================================
     * com.sun.star.util.XModifiable
     * ----------------------------------------------------------
     */
    @objid ("948f6f28-2c1d-4e56-9439-99ae15674c3d")
    @Override
    public synchronized boolean isModified() {
        try {
            boolean ret = this.xModifiable.isModified();
            return ret;
        } catch (com.sun.star.uno.RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    @objid ("f46fc226-0917-401b-b571-6f67a203f898")
    @Override
    public synchronized void setModified(final boolean bModified) throws com.sun.star.beans.PropertyVetoException {
        this.xModifiable.setModified( bModified );
    }

    /**
     * ==========================================================
     * com.sun.star.view.XPrintable
     * ----------------------------------------------------------
     */
    @objid ("afe22b06-d8ba-49d0-b05e-dd34ea749077")
    @Override
    public com.sun.star.beans.PropertyValue[] getPrinter() {
        return this.xPrintable.getPrinter();
    }

    @objid ("aee8fc26-dcab-4d4a-9594-6bac1d79682e")
    @Override
    public void setPrinter(final com.sun.star.beans.PropertyValue[] aPrinter) throws com.sun.star.lang.IllegalArgumentException {
        this.xPrintable.setPrinter( aPrinter );
    }

    @objid ("1df7d90a-95e4-481d-a2b0-29f3d1735048")
    @Override
    public void print(final com.sun.star.beans.PropertyValue[] xOptions) throws com.sun.star.lang.IllegalArgumentException {
        this.xPrintable.print( xOptions );
    }

    /**
     * ==========================================================
     * com.sun.star.frame.XStorable
     * ----------------------------------------------------------
     */
    @objid ("16ceb536-41f6-462f-b7cb-559b54bac12a")
    @Override
    public boolean hasLocation() {
        return this.xStorable.hasLocation();
    }

    @objid ("b1118eb8-0785-48fa-a250-9179b1b64d0c")
    @Override
    public String getLocation() {
        return this.xStorable.getLocation();
    }

    @objid ("03bab449-7bfb-4fe1-8f79-30225211dc93")
    @Override
    public boolean isReadonly() {
        return this.xStorable.isReadonly();
    }

    @objid ("ea2e83d4-3bce-4775-a70c-8a5a5465211f")
    @Override
    public void store() throws com.sun.star.io.IOException {
        this.xStorable.store();
    }

    @objid ("646808c9-fbae-430a-a025-dd4c9548a077")
    @Override
    public void storeAsURL(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) throws com.sun.star.io.IOException {
        this.xStorable.storeAsURL( aURL, aArguments );
    }

    @objid ("42b0823d-2af0-42a9-b6f5-0e2d305fcc93")
    @Override
    public void storeToURL(final String aURL, final com.sun.star.beans.PropertyValue[] aArguments) throws com.sun.star.io.IOException {
        this.xStorable.storeToURL( aURL, aArguments );
    }

    @objid ("3d9863c6-e671-419d-b59f-205681a63a33")
    @Override
    public void addCloseListener(final XCloseListener arg0) {
        this.xCloseable.addCloseListener(arg0);
    }

    @objid ("da883cec-0006-4add-a99b-22bcbad8f9ab")
    @Override
    public void removeCloseListener(final XCloseListener arg0) {
        this.xCloseable.addCloseListener(arg0);
    }

    @objid ("b282fe43-48bf-49ce-9272-2a11e7f1bb73")
    @Override
    public void close(final boolean DeliverOwnership) throws CloseVetoException {
        // It is a full featured office document. 
        // Try to use close mechanism instead of a hard dispose().
        // But maybe such service is not available on this model. 
        if(this.xCloseable!=null) { 
            try { 
                // use close(boolean DeliverOwnership)
                // The boolean parameter DeliverOwnership tells objects vetoing the close process that they may
                // assume ownership if they object the closure by throwing a CloseVetoException
                // Here we give up ownership. To be on the safe side, catch possible veto exception anyway.  
                this.xCloseable.close(true); 
                this.xCloseable = null;
            } catch(com.sun.star.util.CloseVetoException exCloseVeto){
            } 
        } else {
            this.xModel.dispose();
        } 
        
        this.xModel = null;
        this.xPrintable = null;
        this.xStorable = null;
        
    }

}
