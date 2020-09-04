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
import com.sun.star.awt.XPointer;
import com.sun.star.awt.XSystemDependentWindowPeer;
import com.sun.star.awt.XToolkit;
import com.sun.star.awt.XWindowPeer;
import com.sun.star.uno.Any;

/**
 * <p>
 * Class to pass the system window handle to the OpenOffice.org toolkit.
 * </p>
 * 
 * @since OOo 2.0.0
 */
@objid ("dd74dcda-4804-4a72-a9c8-018f77462f68")
class JavaWindowPeerFake implements XSystemDependentWindowPeer, XWindowPeer {
    @objid ("7006efb1-c3bf-4072-a6a2-fec3d428d9e2")
    protected int localSystemType;

    @objid ("c1552206-368c-431e-99fa-513b644fac23")
    protected Any wrappedHandle;

    /**
     * Create the faked window peer.
     * @param _hWindow the system handle to the window.
     * @param _systemType specifies the system type.
     */
    @objid ("afd3a18c-9dd8-4f83-a97c-b98ddc36680a")
    public JavaWindowPeerFake(final Any _hWindow, final int _systemType) {
        this.localSystemType = _systemType;
        this.wrappedHandle = _hWindow;
    }

    /**
     * <p>
     * Implementation of XSystemDependentWindowPeer (that's all we really need)
     * </p>
     * This method is called back from the OpenOffice.org toolkit to retrieve the system data.
     */
    @objid ("db8c93aa-4ccd-4b54-929e-69e50c3a6cb6")
    @Override
    public Object getWindowHandle(final byte[] ProcessId, final short SystemType) throws com.sun.star.uno.RuntimeException {
        if (SystemType == this.localSystemType) {
            return this.wrappedHandle;
        } else {
            new IllegalArgumentException(SystemType+" system type not supported").printStackTrace();
            return null;
        }
    }

    /**
     * not really needed.
     */
    @objid ("2640703c-0f33-45c1-af9a-18d6bd06a078")
    @Override
    public XToolkit getToolkit() throws com.sun.star.uno.RuntimeException {
        return null;
    }

    /**
     * not really needed.
     */
    @objid ("7f1ddebe-0a1a-489b-9b49-d6f516180521")
    @Override
    public void setPointer(final XPointer Pointer) throws com.sun.star.uno.RuntimeException {
    }

    /**
     * not really needed.
     */
    @objid ("41df8fee-4bba-4fd9-8c90-d3a4efdbbd38")
    @Override
    public void setBackground(final int Color) throws com.sun.star.uno.RuntimeException {
    }

    /**
     * not really needed.
     */
    @objid ("8c4a942a-0ca4-4283-b1d0-460779797e08")
    @Override
    public void invalidate(final short Flags) throws com.sun.star.uno.RuntimeException {
    }

    /**
     * not really needed.
     */
    @objid ("2d8d7e13-3ea3-4c84-9a32-046199338f21")
    @Override
    public void invalidateRect(final com.sun.star.awt.Rectangle Rect, final short Flags) throws com.sun.star.uno.RuntimeException {
    }

    /**
     * not really needed.
     */
    @objid ("cc78fa2b-ad5a-4af6-98c9-8ffe4d655b3c")
    @Override
    public void dispose() throws com.sun.star.uno.RuntimeException {
    }

    /**
     * not really needed.
     */
    @objid ("afaa71f0-e30c-41c6-b70e-894b13f52ac2")
    @Override
    public void addEventListener(final com.sun.star.lang.XEventListener xListener) throws com.sun.star.uno.RuntimeException {
    }

    /**
     * not really needed.
     */
    @objid ("af66b4af-fa59-46d9-962b-0f9a06e80f04")
    @Override
    public void removeEventListener(final com.sun.star.lang.XEventListener aListener) throws com.sun.star.uno.RuntimeException {
    }

}
