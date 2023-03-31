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
package org.modelio.gproject.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * IGProject state machine.
 * <p>
 * States order:
 * <ol>
 * <li> {@link GProjectStateEnum#INITIAL} : GProject being built.
 * <li> {@link GProjectStateEnum#NEW} : GProject fully instantiated but open() not called.
 * <li> {@link GProjectStateEnum#SESSIONUP} : first step when opening : instantiate a ICoreSession
 * <li> {@link GProjectStateEnum#OPENING} : installing and mounting parts
 * <li> {@link GProjectStateEnum#OPENED} : project opened.
 * </ol>
 * @author phv
 */
@objid ("0286dc37-c167-40d9-95f8-961d4c864dfa")
public interface IGProjectState {
    @objid ("8a18c4aa-a791-4f0c-8a02-dfe6ce344e9a")
    GProjectStateEnum getValue();

    /**
     * Tells the GProject is instantiated.
     * @return the new state
     * @throws IllegalStateException on wrong previous state.
     */
    @objid ("ef19994b-d63f-4a9e-9c5c-23cce8f66cb9")
    GProjectStateEnum sendNew(IModelioProgress monitorSupplier) throws IllegalStateException;

    /**
     * Tells the ICoreSession is instantiated and open.
     * @return the new state
     * @throws IllegalStateException on wrong previous state.
     */
    @objid ("dd0323a8-cb86-4106-8d6c-ce1bb2987836")
    GProjectStateEnum sendSessionUp(IModelioProgress monitorSupplier) throws IllegalStateException;

    /**
     * Tells the GProject parts are being installed and mount.
     * @return the new state
     * @throws IllegalStateException on wrong previous state.
     */
    @objid ("f9f15e9f-3e59-4dd2-bad7-8be572917c23")
    GProjectStateEnum sendOpening(IModelioProgress monitorSupplier) throws IllegalStateException;

    /**
     * Tells the GProject is fully open.
     * @return the new state
     * @throws IllegalStateException on wrong previous state.
     */
    @objid ("62c56eb8-1380-4202-b2fe-e977a2677389")
    GProjectStateEnum sendOpened(IModelioProgress monitorSupplier) throws IllegalStateException;

    /**
     * @param listener a project state change listener
     */
    @objid ("bde6cfdc-37eb-4366-a624-8b48e18b859c")
    void addListener(IProjectStateChangeListener listener);

    /**
     * @param listener a project state change listener
     */
    @objid ("c9026282-63de-4347-8fd1-a190aa0e266c")
    void removeListener(IProjectStateChangeListener listener);

    /**
     * IGProject state machine.
     * <p>
     * States order:
     * <ol>
     * <li> {@link GProjectStateEnum#INITIAL} : GProject being built.
     * <li> {@link GProjectStateEnum#NEW} : GProject fully instantiated but open() not called.
     * <li> {@link GProjectStateEnum#SESSIONUP} : first step when opening : instantiate a ICoreSession
     * <li> {@link GProjectStateEnum#OPENING} : installing and mounting parts
     * <li> {@link GProjectStateEnum#OPENED} : project opened.
     * </ol>
     * @author phv
     */
    @objid ("4d9465eb-0c39-4125-9645-24cae289434c")
    enum GProjectStateEnum {
        @objid ("39faf062-066c-4307-bbb7-4611450873e6")
        INITIAL,
        @objid ("43dd99cd-dcde-42d1-8d36-afc090047c34")
        NEW,
        @objid ("c3117c93-e587-4fdc-b51b-4c228e96133a")
        SESSIONUP,
        @objid ("cffd0037-c01e-4149-a85a-a035e8a2cbc6")
        OPENING,
        @objid ("6fe0f3e0-2a34-4a4d-b43e-a18fc15fba89")
        OPENED;

    }

    /**
     * Interface for project state changes.
     */
    @objid ("c620299b-1fcf-4f38-8b80-90a17dff6aa9")
    interface IProjectStateChangeListener {
        /**
         * Fired when a project changes state.
         * @param monitorSupplier a progress monitor to be used <b>only</b> if the listener runs long operations
         * @param current the project's state.
         */
        @objid ("6339b634-33cc-4c3c-9a02-9e8f869b4d2f")
        void stateChanged(IModelioProgress monitorSupplier, GProjectStateEnum current);
}
    
}

