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

package org.modelio.vstore.exml.common.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Constants for XML tags and attributes names in a EXML file.
 */
@objid ("39fff454-120e-11e2-816a-001ec947ccaf")
@SuppressWarnings("javadoc")
public interface ExmlTags {
    @objid ("985867e0-12de-11e2-816a-001ec947ccaf")
    public static final String ATT_ATT_TYPE = "type";

    @objid ("f0b538eb-92d7-11e1-be7e-001ec947ccaf")
    public static final String ATT_ATT_VALUE = "value";

    @objid ("985867e5-12de-11e2-816a-001ec947ccaf")
    public static final String ATT_EXT_OBJECT = "object";

    @objid ("985867ea-12de-11e2-816a-001ec947ccaf")
    public static final String ATT_EXT_VERSION = "version";

    @objid ("f0ae11df-92d7-11e1-be7e-001ec947ccaf")
    public static final String ATT_ID_MC = "mc";

    @objid ("98560581-12de-11e2-816a-001ec947ccaf")
    public static final String ATT_ID_NAME = "name";

    @objid ("f0b538e5-92d7-11e1-be7e-001ec947ccaf")
    public static final String ATT_ID_UID = "uid";

    @objid ("f0b538e9-92d7-11e1-be7e-001ec947ccaf")
    public static final String ATT_NAME = "name";

    @objid ("f0b538e7-92d7-11e1-be7e-001ec947ccaf")
    public static final String ATT_RELATION = "relation";

    @objid ("41264432-3faf-11e2-87cb-001ec947ccaf")
    public static final String ATT_TYPE_TYPE = "type";

    /**
     * Format version: <ul>
     * <li> 1 : Modelio <= 2.x
     * <li> 2 : Modelio 3.0
     * <li> 3 : Modelio 3.0, empty dependencies not written anymore
     * <li> 4 : Modelio 3.4: <ul>
     * <li> do more {@value #TAG_DEPS} tag with external references summary
     * <li> no more {@value #TAG_PID} in {@value #TAG_REFOBJ} : they are not reliable over time.
     * </ul>
     * </ul>
     */
    @objid ("42e18b04-3d58-11e2-ab11-001ec947ccaf")
    public static final int FORMAT_VERSION = 4;

    /**
     * Constant telling to indent EXML files or not.
     */
    @objid ("1ed97450-358e-11e2-a87b-001ec947ccaf")
    public static final boolean INDENT_FILES = true;

    @objid ("f0b79b3b-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_ATT = "ATT";

    @objid ("f0b79b43-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_ATTRIBUTES = "ATTRIBUTES";

    @objid ("d9f2bf83-a47a-4b02-9f03-15cbf04baa2c")
    public static final String TAG_CMSNODE_PID = "PID";

    @objid ("f0b538ed-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_COMP = "COMP";

    @objid ("f0b79b3f-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_COMPID = "COMPID";

    @objid ("f0b79b45-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_DEPENDENCIES = "DEPENDENCIES";

    /**
     * DEPS Tag for CMS node dependencies in the beginning of file.
     * 
     * @deprecated since format 4 / Modelio 3.4
     */
    @objid ("985867ef-12de-11e2-816a-001ec947ccaf")
    @Deprecated
    public static final String TAG_DEPS = "DEPS";

    /**
     * DEPS/EXTID tag
     * 
     * @deprecated since format 4 / Modelio 3.4
     */
    @objid ("985867f4-12de-11e2-816a-001ec947ccaf")
    @Deprecated
    public static final String TAG_DEPS_EXTID = "EXTID";

    /**
     * Root tag of an EXML file.
     */
    @objid ("41264443-3faf-11e2-87cb-001ec947ccaf")
    public static final String TAG_EXT = "EXT";

    /**
     * old EXTDEP tag not used anymore in Modelio 3.
     */
    @objid ("f0b538ef-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_EXTDEP = "EXTDEP";

    @objid ("f0b79b41-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_FOREIGNID = "FOREIGNID";

    @objid ("f0b538f1-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_ID = "ID";

    @objid ("f0b538f3-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_LINK = "LINK";

    /**
     * DEPS Tag for CMS node local dependencies in the exml.local file.
     */
    @objid ("27303c4f-034c-43bf-ae1c-17fe54520244")
    public static final String TAG_LOCAL_DEPS = "DEPS";

    @objid ("f0b79b3d-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_OBJECT = "OBJECT";

    @objid ("f0b79b47-92d7-11e1-be7e-001ec947ccaf")
    @Deprecated
    public static final String TAG_PID = "PID";

    @objid ("f0b79b49-92d7-11e1-be7e-001ec947ccaf")
    public static final String TAG_REFOBJ = "REFOBJ";

}
