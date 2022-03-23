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
package org.modelio.platform.core.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * These constants define the available topics for listening to Modelio events.
 * See the associated enumeration ModelioEvent for each topic documentation and
 * semantics.
 * 
 * @author phv
 */
@objid ("00565428-0a02-103e-8282-001ec947cd2a")
public class ModelioEventTopics {
    @objid ("007536ae-0ab6-103e-8282-001ec947cd2a")
    private static final String WORKSPACE = "org/modelio/app/workspace/";

    @objid ("00756778-0ab6-103e-8282-001ec947cd2a")
    private static final String PROJECT = "org/modelio/app/project/";

    @objid ("0075972a-0ab6-103e-8282-001ec947cd2a")
    private static final String FRAGMENT = "org/modelio/app/fragment/";

    @objid ("0075c6be-0ab6-103e-8282-001ec947cd2a")
    private static final String MODULE = "org/modelio/app/module/";

    @objid ("0075f684-0ab6-103e-8282-001ec947cd2a")
    public static final String WORKSPACE_SWITCH = ModelioEventTopics.WORKSPACE + "switch";

    @objid ("00762564-0ab6-103e-8282-001ec947cd2a")
    public static final String WORKSPACE_CONTENTS = ModelioEventTopics.WORKSPACE + "contents";

    @objid ("0022c3e2-002b-106f-bbdd-001ec947cd2a")
    public static final String PROJECT_OPENING = PROJECT + "opening";

    @objid ("007653e0-0ab6-103e-8282-001ec947cd2a")
    public static final String PROJECT_OPENED = PROJECT + "opened";

    @objid ("00564640-9c06-106d-bbdd-001ec947cd2a")
    public static final String PROJECT_SAVING = PROJECT + "saving";

    @objid ("0076af20-0ab6-103e-8282-001ec947cd2a")
    public static final String PROJECT_SAVED = PROJECT + "saved";

    @objid ("00155b12-21b0-105b-aa42-001ec947cd2a")
    public static final String PROJECT_CLOSING = PROJECT + "closing";

    @objid ("00768216-0ab6-103e-8282-001ec947cd2a")
    public static final String PROJECT_CLOSED = PROJECT + "closed";

    @objid ("0076dbb2-0ab6-103e-8282-001ec947cd2a")
    public static final String FRAGMENT_ADDED = FRAGMENT + "added";

    @objid ("00770812-0ab6-103e-8282-001ec947cd2a")
    public static final String FRAGMENT_REMOVED = FRAGMENT + "removed";

    @objid ("00773418-0ab6-103e-8282-001ec947cd2a")
    public static final String FRAGMENT_UP = FRAGMENT + "up";

    @objid ("00775f88-0ab6-103e-8282-001ec947cd2a")
    public static final String FRAGMENT_DOWN = FRAGMENT + "down";

    @objid ("00778a76-0ab6-103e-8282-001ec947cd2a")
    public static final String MODULE_DEPLOYED = MODULE + "deployed";

    @objid ("0077b53c-0ab6-103e-8282-001ec947cd2a")
    public static final String MODULE_REMOVED = MODULE + "removed";

    @objid ("0077df58-0ab6-103e-8282-001ec947cd2a")
    public static final String MODULE_STARTED = MODULE + "started";

    @objid ("00780910-0ab6-103e-8282-001ec947cd2a")
    public static final String MODULE_STOPPED = MODULE + "stopped";

    /**
     * Used to listen to any 'PROJECT' related event
     */
    @objid ("000415d2-0c49-103e-8282-001ec947cd2a")
    public static final String PROJECT_EVENTS = PROJECT + "*";

    /**
     * Used to listen to any 'FRAGMENT' related event
     */
    @objid ("0004cc52-0c49-103e-8282-001ec947cd2a")
    public static final String FRAGMENT_EVENTS = FRAGMENT + "*";

    /**
     * Used to listen to any 'MODULE' related event
     */
    @objid ("0005a4b0-0c49-103e-8282-001ec947cd2a")
    public static final String MODULE_EVENTS = MODULE + "*";

    @objid ("0091c3d2-02bb-106f-bbdd-001ec947cd2a")
    private static final String PICKING = "org/modelio/app/core/picking/";

    @objid ("0091f4d8-02bb-106f-bbdd-001ec947cd2a")
    public static final String PICKING_START = PICKING + "start";

    @objid ("0092257a-02bb-106f-bbdd-001ec947cd2a")
    public static final String PICKING_STOP = PICKING + "stop";

    @objid ("97803f79-2cdf-11e2-95fe-001ec947c8cc")
    public static final String EDIT_ELEMENT = "org/modelio/app/edit";

    @objid ("4bb2310e-dc1f-4c66-a908-daa5739ae745")
    public static final String EDIT_PROPERTIES = "org/modelio/app/editproperties";

    @objid ("008e432e-aa30-10ac-8258-001ec947cd2a")
    public static final String NAVIGATE_ELEMENT = "org/modelio/app/navigate";

    @objid ("04babcef-688b-4501-b509-88f607ac79a0")
    public static final String WORKSPACE_NAVIGATE = ModelioEventTopics.WORKSPACE + "navigate";

}
