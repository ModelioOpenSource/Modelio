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
package org.modelio.gproject.monitor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Project event type.
 */
@objid ("38378312-142a-4a96-bde7-07c43aa4c44d")
public enum GProjectEventType {
    /**
     * A part has gone down. The event message and/or exception will be filled.
     */
    @objid ("caae52bd-48c3-4b17-be4f-a9d2ce6b3d89")
    PART_DOWN,
    /**
     * A warning event. The event message and/or exception will be filled. The fragment may be filled.
     */
    @objid ("72db8caf-4546-40be-a6da-02beb3796293")
    WARNING,
    /**
     * A part has been installed. It is not mounted yet.
     */
    @objid ("d53eba01-e934-47bf-aaea-c781dc7c67d2")
    PART_INSTALLED,
    /**
     * A part has been uninstalled.
     */
    @objid ("0def5983-a186-448c-826c-8755bbec00fc")
    PART_UNINSTALLED,
    /**
     * A new part was added.
     * <p>
     * The part is theoretically mounted but it may be not-mounted as mount may have failed.
     */
    @objid ("f3bb843c-5395-4c56-9b25-af0614cccf15")
    PART_ADDED,
    /**
     * A part was removed (unmounted, not uninstalled) from the project.
     */
    @objid ("8c293185-b55d-4aea-abe9-d0057bd0d464")
    PART_REMOVED,
    /**
     * A part state has changed.
     * <p>
     * If the part went DOWN, a PART_DOWN event is fired instead.
     */
    @objid ("11858287-05e7-44f1-9ab4-e1662111f2fd")
    PART_STATE_CHANGED,
    @objid ("68d8641f-e1cb-4c84-8390-912989497d42")
    PROJECT_NEW,
    @objid ("56f19308-06c4-4882-b6c6-a70e2ce8e7d5")
    PROJECT_SESSIONUP,
    @objid ("b5fb8498-a534-431a-8a75-034f040a8fae")
    PROJECT_OPENING,
    @objid ("9377c269-0152-4d38-9ac1-26e80a6b59af")
    PROJECT_MODULESSTARTED,
    @objid ("db5a6f71-b220-49b6-a204-e096f07d6c7a")
    PROJECT_OPENED;

}
