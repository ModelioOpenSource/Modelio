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

package org.modelio.editors.texteditors.mdd.partitions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7b5de263-2a77-11e2-9fb9-bc305ba4815c")
public interface MDDPartitionTypes {
    @objid ("abf9dd9d-2a77-11e2-9fb9-bc305ba4815c")
    public static final String RO_PARTITION = "_RO";

    @objid ("abf9dda2-2a77-11e2-9fb9-bc305ba4815c")
    public static final String RW_PARTITION = "_RW";

    @objid ("abfb6437-2a77-11e2-9fb9-bc305ba4815c")
    public static final String TAG_PARTITION = "_TAG";

    @objid ("abfb643c-2a77-11e2-9fb9-bc305ba4815c")
    public static final String KEYWORD_PARTITION = "_KEYWORD";

    @objid ("abfb6441-2a77-11e2-9fb9-bc305ba4815c")
    public static final String COMMENT_PARTITION = "_COMMENT";

}
