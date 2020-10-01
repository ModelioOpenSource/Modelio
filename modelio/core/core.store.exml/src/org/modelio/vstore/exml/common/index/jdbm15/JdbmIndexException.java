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

package org.modelio.vstore.exml.common.index.jdbm15;

import java.io.IOError;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vstore.exml.common.index.IndexException;

@objid ("0ff45e2e-a891-4f8c-96f1-921ca709ff8e")
class JdbmIndexException extends IndexException {
    @objid ("19b128cb-c561-4f2b-8d68-184b2aa52168")
    private static final long serialVersionUID = 1L;

    @objid ("8612751e-e49c-4d8d-9715-281388143075")
    public JdbmIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    @objid ("75a7864c-1a41-4d59-8cf9-cac85de0067b")
    public static JdbmIndexException from(IOException e) {
        return new JdbmIndexException(FileUtils.getLocalizedMessage(e), e);
    }

    @objid ("b05f4f6c-25f3-4988-9b83-b6ce9d40dd48")
    public static JdbmIndexException from(InternalError e) {
        return new JdbmIndexException(e.getLocalizedMessage(), e);
    }

    @objid ("e0b36053-8b0c-4170-8f55-c7564996b4c8")
    public static JdbmIndexException from(IOError e) {
        String msg;
        Throwable c = e.getCause();
        if (c instanceof IOException) {
            msg = FileUtils.getLocalizedMessage((IOException) c);
        } else {
            msg = c.getLocalizedMessage();
        }
        return new JdbmIndexException(msg, e);
    }

}
