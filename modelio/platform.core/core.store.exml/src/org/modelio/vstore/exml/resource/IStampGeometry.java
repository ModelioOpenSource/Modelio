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

package org.modelio.vstore.exml.resource;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Constants related to repository and index stamp files.
 */
@objid ("941783bc-4c76-4f8e-91b1-7d9acb1d06d7")
public interface IStampGeometry {
    /**
     * File name for the stamp of the local index copy.
     * <p>
     * The content of this file is compared with the stamp of the repository.
     */
    @objid ("a76b32df-a465-4cbc-9629-bc995266de1d")
    public static final String LOCAL_INDEX_STAMP_FILE = "index_copy_stamp.dat";

    /**
     * Name of the directory containing the stamp file.
     */
    @objid ("345a8f26-1530-421c-8fb5-16cbe8cac4f1")
    public static final String STAMP_DIR_NAME = "admin";

    /**
     * Name of the file containing the stamp.
     */
    @objid ("99ba1c9d-548b-40b9-b04b-225260317d55")
    public static final String STAMP_FILE_NAME = "stamp.dat";

}
