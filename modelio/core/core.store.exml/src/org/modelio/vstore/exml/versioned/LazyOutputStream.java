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

package org.modelio.vstore.exml.versioned;

import java.io.IOException;
import java.io.OutputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vstore.exml.resource.IExmlResourceProvider.ExmlResource;

/**
 * Lazy {@link OutputStream} that asks to a {@link ExmlResource} an output stream
 * on first write.
 * <p>
 * If nothing was wrote on the stream when closing, delete the resource.
 */
@objid ("c2e3a772-1225-11e2-816a-001ec947ccaf")
class LazyOutputStream extends OutputStream {
    @objid ("977d1080-12de-11e2-816a-001ec947ccaf")
    private OutputStream os;

    @objid ("977d1081-12de-11e2-816a-001ec947ccaf")
    private final ExmlResource resource;

    /**
     * Initialize the lazy stream.
     * 
     * @param exmlResource an EXML resource.
     */
    @objid ("977d1082-12de-11e2-816a-001ec947ccaf")
    public LazyOutputStream(ExmlResource exmlResource) {
        this.resource = exmlResource;
    }

    @objid ("977d1086-12de-11e2-816a-001ec947ccaf")
    @Override
    public void write(int b) throws IOException {
        getOs().write(b);
    }

    @objid ("977d108a-12de-11e2-816a-001ec947ccaf")
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        getOs().write(b, off, len);
    }

    @objid ("977d1092-12de-11e2-816a-001ec947ccaf")
    @Override
    public void write(byte[] b) throws IOException {
        getOs().write(b);
    }

    @objid ("977d1098-12de-11e2-816a-001ec947ccaf")
    @Override
    public void flush() throws IOException {
        if (this.os != null) {
            this.os.flush();
        }
    }

    @objid ("977d109b-12de-11e2-816a-001ec947ccaf")
    @Override
    public void close() throws IOException {
        if (this.os != null) {
            this.os.close();
        } else {
            this.resource.delete();
        }
    }

    @objid ("977d109e-12de-11e2-816a-001ec947ccaf")
    private OutputStream getOs() throws IOException {
        if (this.os == null) {
            this.os = this.resource.bufferedWrite();
        }
        return this.os;
    }

}
