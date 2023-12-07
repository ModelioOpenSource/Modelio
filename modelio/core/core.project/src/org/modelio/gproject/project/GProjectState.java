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
package org.modelio.gproject.project;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGPart;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.core.IGProjectState;
import org.modelio.gproject.core.IGProjectState.GProjectStateEnum;
import org.modelio.gproject.core.IGProjectState.IProjectStateChangeListener;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.OptionalProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * The GProjectState manages the state transitions of its associated {@link GProject}:
 * <ul>
 * <li>holds the current state of the project</li>
 * <li>controls transition validity</li>
 * <li>fire state change events at the project level</li>
 */
@objid ("944451bc-7892-4553-994c-10a110261b9b")
public class GProjectState implements IGProjectState {
    @objid ("77ca6e7d-a7a2-4792-9d60-2eeba9a2d211")
    private GProjectStateEnum currentState = GProjectStateEnum.INITIAL;

    @objid ("812bc60a-fb76-4acf-bd7a-c686822e0ff3")
    private final IGProject project;

    @objid ("95afd313-010f-4a9d-97db-b898646af73f")
    private final List<IProjectStateChangeListener> listeners = new CopyOnWriteArrayList<>();

    /**
     * C'tor of a GProjectState controlling a given {@link IGPart}
     * @param project the project this state is attached to.
     */
    @objid ("114c9c9c-4d02-4327-a533-774b50b358e1")
    public  GProjectState(IGProject project) {
        this.project = project;
    }

    /**
     * @return the current state of the managed project.
     */
    @objid ("ae1fc95d-28b8-4f37-90d0-d2a8b5c38dff")
    @Override
    public GProjectStateEnum getValue() {
        return this.currentState;
    }

    @objid ("762ec7fe-c35f-4c6b-83d3-93dd34aba111")
    private void setValue(IModelioProgress monitor, GProjectStateEnum newState) {
        this.currentState = newState;
        
        // Fire event
        fireListeners(monitor);
        
    }

    /**
     * Send 'New' transition.
     * 
     * Transition: (INITIAL) => NEW
     * @return the resulting state in case of successful transition.
     * @throws IllegalStateException if transition was not legal.
     */
    @objid ("094b87cc-5e1e-4b50-85d8-86f36b7cc932")
    @Override
    public GProjectStateEnum sendNew(IModelioProgress supplier) throws IllegalStateException {
        // Check transition is legal
        if (this.currentState != GProjectStateEnum.INITIAL) {
            IllegalStateException e = new IllegalStateException(String.format("'%s' project 'new' transition not allowed from state '%s'", this.project.getName(), this.currentState));
            Log.trace(e);
            throw e;
        }
        
        // Carry out the transition
        setValue(supplier, GProjectStateEnum.NEW);
        return this.currentState;
    }

    @objid ("659fdcc2-5f7c-48df-93c8-e2a5c4cd2628")
    public GProjectStateEnum sendClosing(IModelioProgress supplier) throws IllegalStateException {
        // Check transition is legal
        switch (this.currentState) {
        case OPENED:
        case OPENING:
        case SESSIONUP:
            // State allowed
            break;
        case CLOSED:
        case CLOSING:
        case INITIAL:
        case NEW:
        default:
            IllegalStateException e = new IllegalStateException(String.format("'%s' project 'closing' transition not allowed from state '%s'", this.project.getName(), this.currentState.name()));
            Log.trace(e);
            throw e;
        }
        
        // Carry out the transition
        setValue(supplier, GProjectStateEnum.CLOSING);
        return this.currentState;
    }

    @objid ("6fd2145f-4802-4e7e-ae97-1491aecace30")
    public GProjectStateEnum sendClosed(IModelioProgress monitorSupplier) throws IllegalStateException {
        // Check transition is legal
        if (this.currentState != GProjectStateEnum.CLOSING) {
            IllegalStateException e = new IllegalStateException(String.format("'%s' project 'closed' transition not allowed from state '%s'", this.project.getName(), this.currentState));
            Log.trace(e);
            throw e;
        }
        
        // Carry out the transition
        setValue(monitorSupplier, GProjectStateEnum.CLOSED);
        return this.currentState;
    }

    /**
     * Send 'SessionUp' transition.
     * 
     * Transition: (NEW) => SESSIONUP
     * @return the resulting state in case of successful transition.
     * @throws IllegalStateException if transition was not legal.
     */
    @objid ("5d3d206b-77a7-4388-93f6-4a3ce22644eb")
    @Override
    public GProjectStateEnum sendSessionUp(IModelioProgress monitorSupplier) throws IllegalStateException {
        // Check transition is legal
        if (this.currentState != GProjectStateEnum.NEW) {
            IllegalStateException e = new IllegalStateException(String.format("'%s' project 'Session up' transition not allowed from state '%s'", this.project.getName(), this.currentState));
            Log.trace(e);
            throw e;
        }
        
        // Carry out the transition
        setValue(monitorSupplier, GProjectStateEnum.SESSIONUP);
        return this.currentState;
    }

    /**
     * Send 'Opening' transition.
     * 
     * Transition: (SESSIONUP) => OPENING
     * @return the resulting state in case of successful transition.
     * @throws IllegalStateException if transition was not legal.
     */
    @objid ("494d68e0-b516-4ba0-be89-8639bf7e944e")
    @Override
    public GProjectStateEnum sendOpening(IModelioProgress monitorSupplier) throws IllegalStateException {
        // Check transition is legal
        if (this.currentState != GProjectStateEnum.SESSIONUP) {
            IllegalStateException e = new IllegalStateException(String.format("'%s' project 'opening' transition not allowed from state '%s'", this.project.getName(), this.currentState));
            Log.trace(e);
            throw e;
        }
        
        // Carry out the transition
        setValue(monitorSupplier, GProjectStateEnum.OPENING);
        return this.currentState;
    }

    /**
     * Send 'Opened' transition.
     * 
     * Transition: (OPENING) => OPENED
     * @return the resulting state in case of successful transition.
     * @throws IllegalStateException if transition was not legal.
     */
    @objid ("408330ed-7fba-42cb-a750-f5047e6c7a88")
    @Override
    public GProjectStateEnum sendOpened(IModelioProgress monitorSupplier) throws IllegalStateException {
        // Check transition is legal
        if (this.currentState != GProjectStateEnum.OPENING) {
            IllegalStateException e = new IllegalStateException(String.format("'%s' project 'opened' transition not allowed from state '%d'", this.project.getName(), this.currentState));
            Log.trace(e);
            throw e;
        }
        
        // Carry out the transition
        setValue(monitorSupplier, GProjectStateEnum.OPENED);
        return this.currentState;
    }

    @objid ("4b7804f2-3fd7-4601-9891-4853581ff335")
    private void fireListeners(IModelioProgress monitorSupplier) {
        SubSubMonitorSupplier mon = new SubSubMonitorSupplier(monitorSupplier);
        for (IProjectStateChangeListener listener : this.listeners) {
            listener.stateChanged(mon.createChild(), this.currentState);
        }
        
    }

    @objid ("6d21d8ee-181e-4285-ae70-a2536802bc8a")
    @Override
    public void addListener(IProjectStateChangeListener listener) {
        this.listeners.add(listener);
    }

    @objid ("5c3a6c62-48c4-49cd-9fe9-99994595d731")
    @Override
    public void removeListener(IProjectStateChangeListener listener) {
        this.listeners.remove(listener);
    }

    @objid ("bb8dc817-cda1-4b5e-8b2f-6bcf8aac6097")
    private static class SubSubMonitorSupplier implements Supplier<IModelioProgress> {
        @objid ("dac6e954-b5a7-44e2-82e2-7d881158d7f6")
        private final IModelioProgress root;

        @objid ("737480d0-b4d7-476f-a088-ffdffaa8a200")
        private SubProgress subProgress;

        @objid ("0f6ef776-8a09-4186-8bea-18cd72c3fc31")
        public  SubSubMonitorSupplier(IModelioProgress root) {
            this.root = root;
        }

        @objid ("d13195b6-100b-463c-8261-7729b1943258")
        @Override
        public IModelioProgress get() {
            if (this.subProgress == null) {
                this.subProgress = SubProgress.convert(this.root);
            }
            
            this.subProgress.setWorkRemaining(4);
            return this.subProgress.newOptionalChild(1);
        }

        @objid ("0beceefd-afde-44a6-b399-b9ea120d7e73")
        public IModelioProgress createChild() {
            return new OptionalProgress(this.root, this);
        }

    }

}
