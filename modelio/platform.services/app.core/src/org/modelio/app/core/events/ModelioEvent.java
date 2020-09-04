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

package org.modelio.app.core.events;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This enumeration defines the topics that can be used to post Modelio event on
 * ModelioEventService.
 * 
 * 
 * @author phv
 */
@objid ("0011b764-fd67-103d-8282-001ec947cd2a")
public enum ModelioEvent {
    /**
     * WORKSPACE_SWITCH is fired after the current workspace has been changed.
     * 
     * Event data: Path workspace - the new workspace
     */
    WORKSPACE_SWITCH (ModelioEventTopics.WORKSPACE_SWITCH),
    /**
     * WORKSPACE_CONTENTS is fired when the current workspace project set might
     * have changed (ie a project directory has been added, removed or renamed).
     * 
     * Event data: Path workspace - the workspace
     */
    WORKSPACE_CONTENTS (ModelioEventTopics.WORKSPACE_CONTENTS),
    /**
     * WORKSPACE_CONTENTS is fired to ask the workspace to select the passed
     * project in its view
     * 
     * Event data: the name of the project to select
     */
    WORKSPACE_NAVIGATE (ModelioEventTopics.WORKSPACE_NAVIGATE),
    /**
     * PROJECT_OPENING is fired while a project is being opened.<br>
     * At this stage, its core session is setup but modules are not started yet.
     * 
     * Event data: GProject project - the current project
     */
    PROJECT_OPENING (ModelioEventTopics.PROJECT_OPENING),
    /**
     * PROJECT_OPENED is fired after a project has been opened and has become
     * the current project. At this stage, its core session is setup and modules
     * have been started.
     * 
     * Event data: GProject project - the current project
     */
    PROJECT_OPENED (ModelioEventTopics.PROJECT_OPENED),
    /**
     * PROJECT_CLOSING is fired just before the current project is closed
     * meaning that there is still a current project and valid session
     * available.
     * 
     * Event data: GProject project - the still valid current project being
     * closed
     */
    PROJECT_CLOSING (ModelioEventTopics.PROJECT_CLOSING),
    /**
     * PROJECT_CLOSED is fired after the current project has been closed meaning
     * that there is more a current project.
     * 
     * Event data: GProject project - null
     */
    PROJECT_CLOSED (ModelioEventTopics.PROJECT_CLOSED),
    /**
     * PROJECT_SAVED is fired when the current project is about to be saved.
     * 
     * Event data: GProject project - the current project
     */
    PROJECT_SAVING (ModelioEventTopics.PROJECT_SAVING),
    /**
     * PROJECT_SAVED is fired when the current project is about to be saved.
     * 
     * Event data: GProject project - the current project
     */
    PROJECT_SAVED (ModelioEventTopics.PROJECT_SAVED),
    /**
     * FRAGMENT_ADDED is fired when a fragment has been added to the current
     * project.
     * 
     * Event data: IProjectFragment fragment - the added fragment
     */
    FRAGMENT_ADDED (ModelioEventTopics.FRAGMENT_ADDED),
    /**
     * FRAGMENT_DOWN is fired when a fragment ping status changed to from 'up'
     * to 'down'.
     * 
     * Event data: IProjectFragment fragment - the fragment whose status changed
     */
    FRAGMENT_DOWN (ModelioEventTopics.FRAGMENT_DOWN),
    /**
     * FRAGMENT_UP is fired when a fragment ping status changed to from 'down'
     * to 'up'.
     * 
     * Event data: IProjectFragment fragment - the fragment whose status changed
     */
    FRAGMENT_UP (ModelioEventTopics.FRAGMENT_UP),
    /**
     * FRAGMENT_REMOVED is fired when a fragment has been removed from the
     * current project.
     * 
     * Event data: IProjectFragment fragment - the removed fragment
     */
    FRAGMENT_REMOVED (ModelioEventTopics.FRAGMENT_REMOVED),
    /**
     * MODULE_DEPLOYED is fired when a module has been successfully deployed in
     * the current project.
     * 
     * Event data: GProjectModule module - the deployed module
     */
    MODULE_DEPLOYED (ModelioEventTopics.MODULE_DEPLOYED),
    /**
     * MODULE_REMOVED is fired when a module has been removed from the current
     * project.
     * 
     * Event data: GProjectModule module - the removed module
     */
    MODULE_REMOVED (ModelioEventTopics.MODULE_REMOVED),
    /**
     * MODULE_STARTED is fired when a module has been successfully started in
     * current project.
     * 
     * Event data: IMdac mdac - the started mdac
     */
    MODULE_STARTED (ModelioEventTopics.MODULE_STARTED),
    /**
     * MODULE_STOPPED is fired when a module has been stopped in the current
     * project.
     * 
     * Event data: IMdac mdac - the stopped mdac
     */
    MODULE_STOPPED (ModelioEventTopics.MODULE_STOPPED),
    /**
     * PICKING_START is fired when a picking client needs a value to be picked.
     * 
     * Event data: IPickingSession - the started picking session
     */
    PICKING_START (ModelioEventTopics.PICKING_START),
    /**
     * PICKING_STOP: is fired when the picking client who initiated the picking
     * session wants to terminate it.
     * 
     * Event data: IPickingSession - the terminating picking session
     */
    PICKING_STOP (ModelioEventTopics.PICKING_STOP),
    /**
     * EDIT_ELEMENT: is fired whenever the edition of an element is requested.
     * 
     * Event data: MObject - the edited element
     */
    EDIT_ELEMENT (ModelioEventTopics.EDIT_ELEMENT),
    /**
     * EDIT_PROPERTIES: is fired whenever the edition of the properties of an
     * element is requested. Properties are edited in a specific dialog.
     * 
     * Event data: MObject - the element whose properties are to be edited
     */
    EDIT_PROPERTIES (ModelioEventTopics.EDIT_PROPERTIES),
    /**
     * NAVIGATE_ELEMENT: is fired to request a particular element to be selected
     * by those views which can/accept to do it
     * 
     * Event data: A List of MObject
     */
    NAVIGATE_ELEMENT (ModelioEventTopics.NAVIGATE_ELEMENT);

    @objid ("0002f4d6-0276-103e-8282-001ec947cd2a")
    private String topic;

    @objid ("00011dbe-0276-103e-8282-001ec947cd2a")
    private ModelioEvent(String topicString) {
        this.topic = topicString;
    }

    @objid ("000139b6-0276-103e-8282-001ec947cd2a")
    public String topic() {
        return this.topic;
    }

    @objid ("2c811cf9-f1eb-11e1-9538-002564c97630")
    @Override
    public String toString() {
        return this.topic;
    }

}
