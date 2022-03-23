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
package org.modelio.vbasic.files;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Wraps a checked exception in stream processing.
 * 
 * @credits http://stackoverflow.com/a/36605641/1230870
 */
@objid ("234a8363-5ce9-45c6-8e57-d69564f350dd")
public class StreamException extends RuntimeException {
    @objid ("15e2833e-1508-4bea-8aef-d3ba66d9d252")
    private static final long serialVersionUID = 1L;

    /**
     * @param wrapped the exception to wrap.
     */
    @objid ("8adb1976-6964-42ae-9221-b36980645ddc")
    public  StreamException(Exception wrapped) {
        super(wrapped);
    }

    /**
     * Rethrow the wrapped exception casted to the given type
     * <p>
     * Though exception will be anyway re-thrown during first rethrow() call (oh, Java generics...),
     * this way allows to get a strict static definition of possible exceptions
     * (requires to declare them in throws). And no instanceof or something is needed.
     */
    @objid ("f4bf50a1-3854-4718-816d-c24bc11230ef")
    @SuppressWarnings("unchecked")
    public <T extends Exception> void rethrow() throws T {
        throw (T) getCause();
    }

    /**
     * Get the wrapped exception casted to the given type
     * @param cls the expected wrapped exception type
     * @return the wrapped exception
     * @throws ClassCastException if the wrapped exception does not match the type.
     */
    @objid ("6c568165-9168-4a9f-b8d2-98c50b19b2f4")
    public <T extends Exception> T getWrapped(Class<T> cls) throws ClassCastException {
        try {
        return cls.cast(getCause());
        } catch (ClassCastException e2) {
            e2.addSuppressed(getCause());
            throw e2;
        }
        
    }

}
