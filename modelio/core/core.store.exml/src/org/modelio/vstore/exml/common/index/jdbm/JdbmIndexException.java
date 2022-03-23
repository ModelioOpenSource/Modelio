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
package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOError;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vstore.exml.common.index.IndexException;

@objid ("7a139b29-f189-46e8-91f4-82dcee29a21e")
public class JdbmIndexException extends IndexException {
    @objid ("498e1968-a93a-41bc-a806-bc148f705c40")
    private static final long serialVersionUID = 1L;

    @objid ("97128242-5255-4347-a125-1a0e1849417f")
    public  JdbmIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    @objid ("696a3500-3007-4523-9e53-37da558a280e")
    public static JdbmIndexException from(IOException e) {
        return new JdbmIndexException(FileUtils.getLocalizedMessage(e), e);
    }

    @objid ("2a1473fa-e1bf-4255-b18c-e6d00a8ebc11")
    public static JdbmIndexException from(InternalError e) {
        return new JdbmIndexException(e.getLocalizedMessage(), e);
    }

    @objid ("7301a46d-a43f-4268-bafe-a9bf90c3640d")
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
