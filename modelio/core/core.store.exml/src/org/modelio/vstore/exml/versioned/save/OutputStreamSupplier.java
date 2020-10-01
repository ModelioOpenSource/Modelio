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

package org.modelio.vstore.exml.versioned.save;

import java.io.IOException;
import java.io.OutputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Supplier of {@link OutputStream} to allow lazy creation.
 * @author cmarin
 */
@objid ("8f339397-78bd-487a-a0ae-ca850fd6ca86")
public interface OutputStreamSupplier {
    @objid ("d4e390b2-96f1-463d-8f13-2a05c08eac47")
    OutputStream get() throws IOException;

}
