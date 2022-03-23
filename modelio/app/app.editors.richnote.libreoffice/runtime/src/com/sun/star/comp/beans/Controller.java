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

/**
 * Wrapper class for a com.sun.star.frame.XController.
 * 
 * @since OOo 2.0.0
 */
@objid ("f47ca0cf-85ab-4062-bed9-f230d528eb42")
public class Controller extends Wrapper implements com.sun.star.frame.XController {
    @objid ("c0b4c5e4-88dd-4e05-b92d-077a0ae5046f")
    private com.sun.star.frame.XController xController;

    @objid ("be35c2bc-8038-4a8d-bcb5-c812e6a72c24")
    private com.sun.star.frame.XDispatchProvider xDispatchProvider;

    @objid ("2a090419-1d9c-44cc-a283-d9ff4c52738f")
     Controller(final com.sun.star.frame.XController xController) {
        super( xController );
        this.xController = xController;
        this.xDispatchProvider = UnoRuntime.queryInterface( com.sun.star.frame.XDispatchProvider.class,
            xController );
        
    }

    /**
     * ==============================================================
     * com.sun.star.frame.XController
     * --------------------------------------------------------------
     */
    @objid ("f68e53fc-1f5d-4b35-8d51-35cbb0a8e42d")
    @Override
    public void attachFrame(final com.sun.star.frame.XFrame xFrame) {
        this.xController.attachFrame( xFrame );
    }

    @objid ("2788bdc9-eae9-4c35-9f00-789bf4a7e1a9")
    @Override
    public boolean attachModel(final com.sun.star.frame.XModel xModel) {
        return this.xController.attachModel( xModel );
    }

    @objid ("7dc9a1c1-ae94-4a10-9bc5-283121ad1a3b")
    @Override
    public boolean suspend(final boolean bSuspend) {
        return this.xController.suspend( bSuspend );
    }

    @objid ("8384f874-cad8-4953-96b4-be1b1fd7f3b2")
    @Override
    public java.lang.Object getViewData() {
        return this.xController.getViewData();
    }

    @objid ("39185a64-2134-473d-a097-d3a7c5cee137")
    @Override
    public void restoreViewData(final java.lang.Object aData) {
        this.xController.restoreViewData( aData );
    }

    @objid ("21cfbbff-9552-4089-9e67-9318a82001ea")
    @Override
    public com.sun.star.frame.XModel getModel() {
        return this.xController.getModel();
    }

    @objid ("c95707a2-a1f1-4d49-9997-aa50836cc26f")
    @Override
    public com.sun.star.frame.XFrame getFrame() {
        return this.xController.getFrame();
    }

    /**
     * ==============================================================
     * com.sun.star.frame.XDispatchProvider
     * --------------------------------------------------------------
     */
    @objid ("f6ed7e23-61d0-4b73-afdb-1c3007629288")
    public com.sun.star.frame.XDispatch queryDispatch(final com.sun.star.util.URL aURL, final String aTargetFrameName, final int nSearchFlags) {
        return this.xDispatchProvider.queryDispatch( aURL, aTargetFrameName, nSearchFlags );
    }

    @objid ("43d0079c-a364-4676-a464-0f694af93b67")
    public com.sun.star.frame.XDispatch[] queryDispatches(final com.sun.star.frame.DispatchDescriptor[] aRequests) {
        return this.xDispatchProvider.queryDispatches( aRequests );
    }

}
