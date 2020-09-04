/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vbasic.files;

import java.io.Closeable;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <i>try-with-resource</i> shield that closes the index
 * on {@link #close()} unless {@link #success()} has been called.
 * <p>
 * To be used to close a resource when an operation on the resource fails
 * with an exception.
 * <p>
 * Typical usage:
 * <pre>
 * void initResource() {
 * Closeable aResource = ...
 * try (Closeable shield = new {@link #CloseOnFail(Closeable) CloseOnFail(aResource)}) {
 * // initialize the resource content
 * ...
 * ...
 * // validate the resource
 * shield.{@link #success() };
 * } catch (XxxException e) {
 * throw new IOException(e);
 * }
 * </pre>
 */
@objid ("2f38a775-0bbf-11e2-b54c-001ec947ccaf")
public class CloseOnFail implements Closeable {
    @objid ("2f95a240-0bbf-11e2-b54c-001ec947ccaf")
    private Closeable resource;

    /**
     * Initialize the shield
     * 
     * @param resource the resource to close on failure
     */
    @objid ("2f95a241-0bbf-11e2-b54c-001ec947ccaf")
    public CloseOnFail(Closeable resource) {
        this.resource = resource;
    }

    /**
     * Abort the closure of the resource.
     * <p>
     * To be called after the operation has succeeded.
     */
    @objid ("2f95a244-0bbf-11e2-b54c-001ec947ccaf")
    public void success() {
        this.resource = null;
    }

    @objid ("2f95a246-0bbf-11e2-b54c-001ec947ccaf")
    @Override
    public void close() throws IOException {
        if (this.resource != null) {
            this.resource.close();
            this.resource = null;
        }
    }

}
