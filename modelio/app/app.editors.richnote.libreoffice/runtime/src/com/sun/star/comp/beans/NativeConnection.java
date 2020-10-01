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
import com.sun.star.connection.XConnection;

@objid ("a33b58a3-24d6-4b2f-90a3-fd5edae04b18")
class NativeConnection implements XConnection {
    @objid ("566c3db0-02e8-4c09-b44a-d21f4f6f9228")
    private long NativeHandle;

    @objid ("763d17de-4383-4637-a202-32fd77a17b2e")
    private String Description;

    @objid ("3c386232-2c62-4659-948e-a8edc4f704ae")
    public native void connect(final NativeService aNativeService) throws com.sun.star.io.IOException;

    @objid ("bb746f1b-3675-44d9-8f59-093d1e0b0d5a")
    @Override
    public native int read(final byte[][] aReadBytes, final int nBytesToRead) throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException;

    @objid ("0d15416c-c683-44c0-accc-50245cb60076")
    @Override
    public native void write(final byte[] aData) throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException;

    @objid ("b90f7e14-7e4d-4ab4-b0fe-d46f54a4c5ba")
    @Override
    public native void flush() throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException;

    @objid ("7400fe19-b9cf-479a-a694-c02aa1c73b17")
    @Override
    public native void close() throws com.sun.star.io.IOException, com.sun.star.uno.RuntimeException;

    @objid ("cc2669b9-aaba-4bc1-9b6b-0070ac294e32")
    @Override
    public synchronized String getDescription() throws com.sun.star.uno.RuntimeException {
        return this.Description;
    }

}
