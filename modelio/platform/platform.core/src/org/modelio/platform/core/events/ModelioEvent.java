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
    @objid ("00985404-0275-103e-8282-001ec947cd2a")
    WORKSPACE_SWITCH(ModelioEventTopics.WORKSPACE_SWITCH),
    /**
     * WORKSPACE_CONTENTS is fired when the current workspace project set might
     * have changed (ie a project directory has been added, removed or renamed).
     * 
     * Event data: Path workspace - the workspace
     */
    @objid ("00987a60-0275-103e-8282-001ec947cd2a")
    WORKSPACE_CONTENTS(ModelioEventTopics.WORKSPACE_CONTENTS),
    /**
     * WORKSPACE_CONTENTS is fired to ask the workspace to select the passed
     * project in its view
     * 
     * Event data: the name of the project to select
     */
    @objid ("05485796-e03b-4a99-9ae4-95b17fcfac60")
    WORKSPACE_NAVIGATE(ModelioEventTopics.WORKSPACE_NAVIGATE),
    /**
     * PROJECT_OPENING is fired while a project is being opened.<br>
     * At this stage, its core session is setup but modules are not started yet.
     * 
     * Event data: GProject project - the current project
     */
    @objid ("001434f8-002b-106f-bbdd-001ec947cd2a")
    PROJECT_OPENING(ModelioEventTopics.PROJECT_OPENING),
    /**
     * PROJECT_OPENED is fired after a project has been opened and has become
     * the current project. At this stage, its core session is setup and modules
     * have been started.
     * 
     * Event data: GProject project - the current project
     */
    @objid ("0098919e-0275-103e-8282-001ec947cd2a")
    PROJECT_OPENED(ModelioEventTopics.PROJECT_OPENED),
    /**
     * PROJECT_CLOSING is fired just before the current project is closed
     * meaning that there is still a current project and valid session
     * available.
     * 
     * Event data: GProject project - the still valid current project being
     * closed
     */
    @objid ("00131884-21b0-105b-aa42-001ec947cd2a")
    PROJECT_CLOSING(ModelioEventTopics.PROJECT_CLOSING),
    /**
     * PROJECT_CLOSED is fired after the current project has been closed meaning
     * that there is more a current project.
     * 
     * Event data: GProject project - null
     */
    @objid ("00001360-0276-103e-8282-001ec947cd2a")
    PROJECT_CLOSED(ModelioEventTopics.PROJECT_CLOSED),
    /**
     * PROJECT_SAVED is fired when the current project is about to be saved.
     * 
     * Event data: GProject project - the current project
     */
    @objid ("001d3058-9c06-106d-bbdd-001ec947cd2a")
    PROJECT_SAVING(ModelioEventTopics.PROJECT_SAVING),
    /**
     * PROJECT_SAVED is fired when the current project is about to be saved.
     * 
     * Event data: GProject project - the current project
     */
    @objid ("001d502e-9c06-106d-bbdd-001ec947cd2a")
    PROJECT_SAVED(ModelioEventTopics.PROJECT_SAVED),
    /**
     * FRAGMENT_ADDED is fired when a fragment has been added to the current
     * project.
     * 
     * Event data: IProjectFragment fragment - the added fragment
     */
    @objid ("00004362-0276-103e-8282-001ec947cd2a")
    FRAGMENT_ADDED(ModelioEventTopics.FRAGMENT_ADDED),
    /**
     * FRAGMENT_DOWN is fired when a fragment ping status changed to from 'up'
     * to 'down'.
     * 
     * Event data: IProjectFragment fragment - the fragment whose status changed
     */
    @objid ("00005bfe-0276-103e-8282-001ec947cd2a")
    FRAGMENT_DOWN(ModelioEventTopics.FRAGMENT_DOWN),
    /**
     * FRAGMENT_UP is fired when a fragment ping status changed to from 'down'
     * to 'up'.
     * 
     * Event data: IProjectFragment fragment - the fragment whose status changed
     */
    @objid ("000076f2-0276-103e-8282-001ec947cd2a")
    FRAGMENT_UP(ModelioEventTopics.FRAGMENT_UP),
    /**
     * FRAGMENT_REMOVED is fired when a fragment has been removed from the
     * current project.
     * 
     * Event data: IProjectFragment fragment - the removed fragment
     */
    @objid ("00009010-0276-103e-8282-001ec947cd2a")
    FRAGMENT_REMOVED(ModelioEventTopics.FRAGMENT_REMOVED),
    /**
     * MODULE_DEPLOYED is fired when a module has been successfully deployed in
     * the current project.
     * 
     * Event data: GProjectModule module - the deployed module
     */
    @objid ("0000a9ba-0276-103e-8282-001ec947cd2a")
    MODULE_DEPLOYED(ModelioEventTopics.MODULE_DEPLOYED),
    /**
     * MODULE_REMOVED is fired when a module has been removed from the current
     * project.
     * 
     * Event data: GProjectModule module - the removed module
     */
    @objid ("0000c378-0276-103e-8282-001ec947cd2a")
    MODULE_REMOVED(ModelioEventTopics.MODULE_REMOVED),
    /**
     * MODULE_STARTED is fired when a module has been successfully started in
     * current project.
     * 
     * Event data: IMdac mdac - the started mdac
     */
    @objid ("0000dd7c-0276-103e-8282-001ec947cd2a")
    MODULE_STARTED(ModelioEventTopics.MODULE_STARTED),
    /**
     * MODULE_STOPPED is fired when a module has been stopped in the current
     * project.
     * 
     * Event data: IMdac mdac - the stopped mdac
     */
    @objid ("0000f7d0-0276-103e-8282-001ec947cd2a")
    MODULE_STOPPED(ModelioEventTopics.MODULE_STOPPED),
    /**
     * PICKING_START is fired when a picking client needs a value to be picked.
     * 
     * Event data: IPickingSession - the started picking session
     */
    @objid ("00897e0c-02bb-106f-bbdd-001ec947cd2a")
    PICKING_START(ModelioEventTopics.PICKING_START),
    /**
     * PICKING_STOP: is fired when the picking client who initiated the picking
     * session wants to terminate it.
     * 
     * Event data: IPickingSession - the terminating picking session
     */
    @objid ("00899d88-02bb-106f-bbdd-001ec947cd2a")
    PICKING_STOP(ModelioEventTopics.PICKING_STOP),
    /**
     * EDIT_ELEMENT: is fired whenever the edition of an element is requested.
     * 
     * Event data: MObject - the edited element
     */
    @objid ("37f30a01-2ce5-11e2-95fe-001ec947c8cc")
    EDIT_ELEMENT(ModelioEventTopics.EDIT_ELEMENT),
    /**
     * EDIT_PROPERTIES: is fired whenever the edition of the properties of an
     * element is requested. Properties are edited in a specific dialog.
     * 
     * Event data: MObject - the element whose properties are to be edited
     */
    @objid ("38ce5984-dac0-4444-ad8f-821b44094f4a")
    EDIT_PROPERTIES(ModelioEventTopics.EDIT_PROPERTIES),
    /**
     * NAVIGATE_ELEMENT: is fired to request a particular element to be selected
     * by those views which can/accept to do it
     * 
     * Event data: A List of MObject
     */
    @objid ("002a0832-a721-10ac-8258-001ec947cd2a")
    NAVIGATE_ELEMENT(ModelioEventTopics.NAVIGATE_ELEMENT);

    @objid ("0002f4d6-0276-103e-8282-001ec947cd2a")
    private String topic;

    @objid ("00011dbe-0276-103e-8282-001ec947cd2a")
    private  ModelioEvent(String topicString) {
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
