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

package org.modelio.diagram.persistence;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Constants for the XML schema used to serialize the diagram
 * 
 * @author cmarin
 */
@objid ("cb7049e0-186f-11e2-92d2-001ec947c8cc")
interface SchemaConstants {
    @objid ("ebc890a2-186f-11e2-92d2-001ec947c8cc")
    public static final String ATT_EXTREF_EXTDBID = "extDbId";

    @objid ("ebc890a7-186f-11e2-92d2-001ec947c8cc")
    public static final String ATT_EXTREF_EXTID = "extId";

    @objid ("ebc890ac-186f-11e2-92d2-001ec947c8cc")
    public static final String ATT_MAP_NAME = "relation";

    @objid ("ebc890b1-186f-11e2-92d2-001ec947c8cc")
    public static final String ATT_OBJECT_ID = "id";

    @objid ("ebc890b6-186f-11e2-92d2-001ec947c8cc")
    public static final String ATT_OBJECT_TYPE = "type";

    @objid ("ebc890bb-186f-11e2-92d2-001ec947c8cc")
    public static final String ATT_OBJECT_MAJOR_VERSION = "major_version";

    @objid ("ebc890c0-186f-11e2-92d2-001ec947c8cc")
    public static final String ATT_PROP_NAME = "name";

    @objid ("ebc890c5-186f-11e2-92d2-001ec947c8cc")
    public static final String ATT_VALUE_TYPE = "type";

    @objid ("ebc890ca-186f-11e2-92d2-001ec947c8cc")
    public static final String ATT_VALUE_VALUE = "value";

    @objid ("ebcaf2fa-186f-11e2-92d2-001ec947c8cc")
    public static final String TAG_EXTREF = "ExtRef";

    @objid ("ebcaf2ff-186f-11e2-92d2-001ec947c8cc")
    public static final String TAG_LIST = "List";

    @objid ("ebcaf304-186f-11e2-92d2-001ec947c8cc")
    public static final String TAG_MAP = "Map";

    @objid ("ebcaf309-186f-11e2-92d2-001ec947c8cc")
    public static final String TAG_MAP_KEY = "Key";

    @objid ("ebcaf30e-186f-11e2-92d2-001ec947c8cc")
    public static final String TAG_MAP_VALUE = "Value";

    @objid ("ebcaf313-186f-11e2-92d2-001ec947c8cc")
    public static final String TAG_PERSISTENT = "Object";

    @objid ("ebcaf318-186f-11e2-92d2-001ec947c8cc")
    public static final String TAG_PROP = "Property";

    @objid ("ebcaf31d-186f-11e2-92d2-001ec947c8cc")
    public static final String TAG_REF = "Ref";

    @objid ("ebcaf322-186f-11e2-92d2-001ec947c8cc")
    public static final String TAG_VALUE = "Value";

}
