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
package org.modelio.gproject.parts;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGPart;
import org.modelio.gproject.core.IGPart.GPartException;
import org.modelio.gproject.core.IGPartState;
import org.modelio.gproject.core.IGPartState.GPartStateEnum;
import org.modelio.gproject.monitor.GProjectEvent;
import org.modelio.gproject.monitor.GProjectEventType;
import org.modelio.vbasic.log.Log;

/**
 * IGPart state machine. The GPartState manages the state transitions of its associated {@link IGPart}:
 * <ul>
 * <li>holds the current state of the part</li>
 * <li>control transition validity</li>
 * <li>fire monitoring events at the project level on state changes</li>
 */
@objid ("309d0eb1-711e-4ba7-8d3c-307f3c5f8057")
public class GPartState implements IGPartState {
    @objid ("cc57da28-2e7e-4282-8c9c-585427b29547")
    private GPartStateEnum currentState;

    @objid ("e104d7e4-e1e2-452e-8287-087d385adfeb")
    private Throwable downError = null;

    @objid ("e7c29cfc-0657-4ab3-9078-485ffc8bc2ee")
    private final IGPart part;

    /**
     * C'tor of a GPartState controlling a given {@link IGPart}
     * @param part the {@link IGPart} this state is attached to.
     */
    @objid ("bfdf214e-f680-42f0-a0cf-621202216608")
    public  GPartState(IGPart part) {
        this.part = part;
        this.currentState = GPartStateEnum.INSTANTIATED;
        
    }

    @objid ("42f46c29-7d0e-4e3c-b807-a02d4df340ce")
    @Override
    public Throwable getDownError() {
        return this.downError;
    }

    /**
     * @return the current state of the managed part.
     */
    @objid ("455e9c97-4aaf-45e5-8f14-90ff433d37ce")
    @Override
    public GPartStateEnum getValue() {
        return this.currentState;
    }

    /**
     * Send Down transition
     * @param error the error that crashed the part .
     * @return the resulting state in case of successful transition.
     */
    @objid ("2cab76d1-25aa-4c32-9f32-f0cbeb7228ed")
    public GPartStateEnum sendDown(Throwable error) {
        GPartStateEnum initialState = this.currentState;
        
        // Carry out the transition
        switch (this.currentState) {
        case DOWN:
            // Already down, ignore and fast exit
            return this.currentState;
        
        case MOUNTING:
        case MOUNTED:
            // Normal cases
            this.currentState = GPartStateEnum.DOWN;
            this.downError = error;
            break;
        default:
            // Log the error but continue anyway
            IllegalStateException e = new IllegalStateException(String.format("'%s' part Down transition not allowed from state '%s'", this.part.getId(), this.currentState));
            Log.trace(e);
        }
        
        // Fire event
        String tm = getTransitionMessage("Down", initialState, this.currentState);
        fireMonitors(new GProjectEvent(GProjectEventType.PART_DOWN, tm, this.part, error));
        return this.currentState;
    }

    /**
     * Send Mounted transition
     * @param error null in case of successful transition, the failure cause on failure.
     * @return the resulting state in case of successful transition.
     * @throws GPartException if transition was not legal.
     */
    @objid ("fcafede9-72b6-45d2-9433-221369d91a17")
    public GPartStateEnum sendEndMount(Throwable error) throws GPartException {
        return (error == null) ? endMountOK() : endMountKO(error);
    }

    @objid ("514d3a75-ae78-4745-a0cf-4716023fad70")
    private GPartStateEnum endMountKO(Throwable error) throws IllegalStateException {
        GPartStateEnum initialState = this.currentState;
        
        // Carry out the transition
        switch (this.currentState) {
        case MOUNTING:
            this.currentState = GPartStateEnum.DOWN;
            this.downError = error;
            break;
        default:
            throw new IllegalStateException(String.format("'%s' part EndMount transition not allowed from state '%s'", this.part.getId(), this.currentState));
        }
        
        // Fire event
        String tm = getTransitionMessage("EndMountFail", initialState, this.currentState);
        fireMonitors(new GProjectEvent(GProjectEventType.PART_DOWN, tm, this.part, this.downError));
        return this.currentState;
    }

    @objid ("55d6b2c0-2e71-4fa1-8176-2965c071aa7f")
    private GPartStateEnum endMountOK() throws IllegalStateException {
        GPartStateEnum initialState = this.currentState;
        
        // Carry out the transition
        switch (this.currentState) {
        case MOUNTING:
            this.currentState = GPartStateEnum.MOUNTED;
            this.downError = null;
            break;
        default:
            throw new IllegalStateException(String.format("'%s' part EndMount transition not allowed from state '%s'", this.part.getId(), this.currentState));
        }
        
        // Fire event
        String tm = getTransitionMessage("EndMountSuccess", initialState, this.currentState);
        fireMonitors(new GProjectEvent(GProjectEventType.PART_ADDED, tm, this.part, null));
        return this.currentState;
    }

    /**
     * Send Installed transition
     * @return the resulting state in case of successful transition.
     * @throws IllegalStateException if transition was not legal.
     */
    @objid ("1bd8b418-4266-4fd2-ab02-f137d0c94499")
    public GPartStateEnum sendInstall() throws IllegalStateException {
        GPartStateEnum initialState = this.currentState;
        
        // Carry out the transition
        switch (this.currentState) {
        case UNINSTALLED:
        case INSTANTIATED:
            this.currentState = GPartStateEnum.INSTALLED;
            break;
        default:
            throw new IllegalStateException(String.format("'%s' part Install transition not allowed from state '%s'", this.part.getId(), this.currentState));
        }
        
        // Fire event
        String tm = getTransitionMessage("Install", initialState, this.currentState);
        fireMonitors(new GProjectEvent(GProjectEventType.PART_INSTALLED, tm, this.part, null));
        return this.currentState;
    }

    /**
     * Send 'Mounting' transition.
     * @return the resulting state in case of successful transition.
     * @throws IllegalStateException if transition was not legal.
     */
    @objid ("6d4f6e14-1b66-47ce-8577-847522047da5")
    public GPartStateEnum sendStartMount() throws IllegalStateException {
        GPartStateEnum initialState = this.currentState;
        
        // Carry out the transition
        switch (this.currentState) {
        case INSTALLED:
        case DOWN:
            this.currentState = GPartStateEnum.MOUNTING;
            break;
        default:
            throw new IllegalStateException(String.format("'%s' part StartMount transition not allowed from state '%s'", this.part.getId(), this.currentState));
        }
        
        // Fire event
        String tm = getTransitionMessage("StartMount", initialState, this.currentState);
        fireMonitors(new GProjectEvent(GProjectEventType.PART_INSTALLED, tm, this.part, null));
        return this.currentState;
    }

    /**
     * Send Uninstalled transition
     * @return the resulting state in case of successful transition.
     * @throws IllegalStateException if transition was not legal.
     */
    @objid ("3cd80196-6530-49d0-8491-9bdf15c7ff31")
    public GPartStateEnum sendUninstall() throws IllegalStateException {
        GPartStateEnum initialState = this.currentState;
        
        // Carry out the transition
        switch (this.currentState) {
        case DOWN:
        case INSTANTIATED:
        case INSTALLED:
            this.currentState = GPartStateEnum.UNINSTALLED;
            break;
        default:
            throw new IllegalStateException(String.format("'%s' part Uninstall transition not allowed from state '%s'", this.part.getId(), this.currentState));
        }
        
        // Fire event
        String tm = getTransitionMessage("Uninstall", initialState, this.currentState);
        fireMonitors(new GProjectEvent(GProjectEventType.PART_UNINSTALLED, tm, this.part, null));
        return this.currentState;
    }

    /**
     * Send 'Mounting' transition.
     * @return the resulting state in case of successful transition.
     * @throws IllegalStateException if transition was not legal.
     */
    @objid ("50a1fcaf-dda3-4f9b-81fa-2d953df97ae1")
    public GPartStateEnum sendUnmount() throws IllegalStateException {
        GPartStateEnum initialState = this.currentState;
        
        // Carry out the transition
        switch (this.currentState) {
        case INSTALLED:
        case MOUNTED:
        case DOWN:
            this.currentState = GPartStateEnum.INSTALLED;
            break;
        default:
            throw new IllegalStateException(String.format("'%s' part Unmount transition not allowed from state '%s'", this.part.getId(), this.currentState));
        }
        
        // Fire event
        String tm = getTransitionMessage("Unmount", initialState, this.currentState);
        fireMonitors(new GProjectEvent(GProjectEventType.PART_UNINSTALLED, tm, this.part, null));
        return this.currentState;
    }

    @objid ("2e3dbc62-0746-4c2a-8e7b-2f83e9f47381")
    @Override
    public void setDownError(Throwable e) throws IllegalStateException {
        if (this.currentState != GPartStateEnum.DOWN) {
            IllegalStateException e2 = new IllegalStateException(String.format("'%s' part is not down but '%s'.", this.part.getId(), this.currentState));
            e2.addSuppressed(e);
            throw e2;
        }
        
        this.downError = e;
        
    }

    @objid ("37a0e9b9-1795-4575-9865-5d44951a51be")
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder()
        .append(getClass().getSimpleName())
        .append(" [part='")
        .append(this.part.getType())
        .append(' ')
        .append(this.part.getId())
        .append("', currentState='")
        .append(this.currentState)
        .append('\'');
        
        if (this.downError != null) {
            s.append(", downError='").append(this.downError.toString()).append('\'');
        }
        
        s.append(']');
        return s.toString();
    }

    @objid ("0b2eb0f9-c849-485d-a74e-7ebd2b556acb")
    private void fireMonitors(GProjectEvent ev) {
        if (this.part.getProject() != null) {
            this.part.getProject().getMonitorSupport().fireMonitors(ev);
        }
        
    }

    @objid ("1a1301a1-c0ce-4458-97ff-8f688e206688")
    private String getTransitionMessage(String transitionName, GPartStateEnum initialState, GPartStateEnum outputState) {
        return String.format("GPartState %s '%s': %s(%s) => %s",
                this.part.getType(), this.part.getId(), transitionName, initialState, outputState);
        
    }

}
