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

package com.sun.star.comp.beans;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.uno.UnoRuntime;

/**
 * Wrapper class for a com.sun.star.frame.XFrame.
 * 
 * @since OOo 2.0.0
 */
@objid ("352f0437-f62e-46ea-a762-54cd263a1eb4")
public class Frame extends Wrapper implements com.sun.star.frame.XFrame, com.sun.star.frame.XDispatchProvider, com.sun.star.frame.XDispatchProviderInterception {
    @objid ("10f6a53d-db5b-49a3-a00b-9a4dccec5cd9")
    private com.sun.star.frame.XFrame xFrame;

    @objid ("af373773-db25-436f-96a8-c370936d5b28")
    private com.sun.star.frame.XDispatchProvider xDispatchProvider;

    @objid ("ec975274-62a2-4029-a6a7-80c7493b414f")
    private com.sun.star.frame.XDispatchProviderInterception xDispatchProviderInterception;

    /**
     * Constructor.
     * @param xFrame the wrapped XFrame
     */
    @objid ("87fb604a-59ba-43cd-b7dd-ea7da63c34ac")
    public Frame(final com.sun.star.frame.XFrame xFrame) {
        super( xFrame );
        this.xFrame = xFrame;
        this.xDispatchProvider = UnoRuntime.queryInterface( com.sun.star.frame.XDispatchProvider.class,
            xFrame );
        this.xDispatchProviderInterception = UnoRuntime.queryInterface( com.sun.star.frame.XDispatchProviderInterception.class,
            xFrame );
    }

    /**
     * ==============================================================
     * com.sun.star.frame.XFrame
     * --------------------------------------------------------------
     */
    @objid ("6523b515-a781-49d1-a91c-53530de3770b")
    @Override
    public void initialize(final com.sun.star.awt.XWindow xWindow) {
        this.xFrame.initialize( xWindow );
    }

    @objid ("6bab532b-78e1-49a4-bed1-8ef78d31eab6")
    @Override
    public com.sun.star.awt.XWindow getContainerWindow() {
        return this.xFrame.getContainerWindow();
    }

    @objid ("57796157-87fe-4f91-8ee8-1faab2c74961")
    @Override
    public void setCreator(final com.sun.star.frame.XFramesSupplier xCreator) {
        this.xFrame.setCreator( xCreator );
    }

    @objid ("ca61851b-8ebe-4a6e-97b6-965a776de2e1")
    @Override
    public com.sun.star.frame.XFramesSupplier getCreator() {
        return this.xFrame.getCreator();
    }

    @objid ("778d4c89-2f1d-4638-a3cc-4a26cd2d0de3")
    @Override
    public String getName() {
        return this.xFrame.getName();
    }

    @objid ("1abb63df-7309-43cd-a5d5-620c06587777")
    @Override
    public void setName(final String aName) {
        this.xFrame.setName( aName );
    }

    @objid ("9162313a-894e-485a-8053-75377182d674")
    @Override
    public com.sun.star.frame.XFrame findFrame(final String aTargetFrameName, final int nSearchFlags) {
        return this.xFrame.findFrame( aTargetFrameName, nSearchFlags );
    }

    @objid ("27db3c23-2f68-4c8e-be5f-f46d7aa6c822")
    @Override
    public boolean isTop() {
        return this.xFrame.isTop();
    }

    @objid ("65d7783c-341c-4d29-a3b8-5c47462c4d2f")
    @Override
    public void activate() {
        this.xFrame.activate();
    }

    @objid ("7f5b03c8-da4a-4a13-96af-921af2f3534a")
    @Override
    public void deactivate() {
        this.xFrame.deactivate();
    }

    @objid ("c489eea0-479c-4f99-8497-8bebd5bad312")
    @Override
    public boolean isActive() {
        return this.xFrame.isActive();
    }

    @objid ("e3ee98ae-a286-4140-9f92-a7cbb3a257b4")
    @Override
    public boolean setComponent(final com.sun.star.awt.XWindow xComponentWindow, final com.sun.star.frame.XController xController) {
        return this.xFrame.setComponent( xComponentWindow, xController );
    }

    @objid ("32ab96b5-f711-4369-87a6-4635d18b1a06")
    @Override
    public com.sun.star.awt.XWindow getComponentWindow() {
        return this.xFrame.getComponentWindow();
    }

    @objid ("360958bf-940f-4d88-a938-696841da37e3")
    @Override
    public com.sun.star.frame.XController getController() {
        return this.xFrame.getController();
    }

    @objid ("a029a088-40e6-46ab-bdd8-394abe1e58d1")
    @Override
    public void contextChanged() {
        this.xFrame.contextChanged();
    }

    @objid ("5c006ed6-255a-4886-82b4-872c0105d4fa")
    @Override
    public void addFrameActionListener(final com.sun.star.frame.XFrameActionListener xListener) {
        this.xFrame.addFrameActionListener( xListener );
    }

    @objid ("ce4813d8-0b69-4a33-80e7-2ca36c26e277")
    @Override
    public void removeFrameActionListener(final com.sun.star.frame.XFrameActionListener xListener) {
        this.xFrame.removeFrameActionListener( xListener );
    }

    /**
     * ==============================================================
     * com.sun.star.frame.XDispatchProvider
     * --------------------------------------------------------------
     */
    @objid ("b2481f22-0ecd-4688-b31f-81ad487f3472")
    @Override
    public com.sun.star.frame.XDispatch queryDispatch(final com.sun.star.util.URL aURL, final String aTargetFrameName, final int nSearchFlags) {
        return this.xDispatchProvider.queryDispatch( aURL, aTargetFrameName, nSearchFlags );
    }

    @objid ("d7863404-3637-4c15-8dce-eb02c46f6a1d")
    @Override
    public com.sun.star.frame.XDispatch[] queryDispatches(final com.sun.star.frame.DispatchDescriptor[] aRequests) {
        return this.xDispatchProvider.queryDispatches( aRequests );
    }

    /**
     * ==============================================================
     * com.sun.star.frame.XDispatchProviderInterception
     * --------------------------------------------------------------
     */
    @objid ("198874cd-7544-428f-9296-6693706bdad7")
    @Override
    public void registerDispatchProviderInterceptor(final com.sun.star.frame.XDispatchProviderInterceptor xInterceptor) {
        this.xDispatchProviderInterception.registerDispatchProviderInterceptor( xInterceptor );
    }

    @objid ("0eb733dd-0c2e-48a3-b1e2-ad9561c72fdd")
    @Override
    public void releaseDispatchProviderInterceptor(final com.sun.star.frame.XDispatchProviderInterceptor xInterceptor) {
        this.xDispatchProviderInterception.releaseDispatchProviderInterceptor( xInterceptor );
    }

}
