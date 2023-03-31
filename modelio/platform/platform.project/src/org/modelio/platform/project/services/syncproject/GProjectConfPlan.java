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
package org.modelio.platform.project.services.syncproject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.GProblem;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.core.IGProjectState.GProjectStateEnum;
import org.modelio.gproject.core.IGProjectState.IProjectStateChangeListener;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;

/**
 * A configuration plan is a way to update an {@link IGProject} through a series of individual {@link IGProjectConfAction}.
 * 
 * @see GProjectConfUpdater
 * @since 5.2
 */
@objid ("a0ef1fe2-183c-441a-8421-282b83ec191e")
public class GProjectConfPlan {
    @objid ("f62db0e0-aaab-4e2d-805a-db7f0c5886ed")
    private List<IGProjectConfAction> actions = new ArrayList<>();

    @objid ("48f1f857-747b-4654-b71f-53d22c77a675")
    private final IGProject project;

    @objid ("53055710-ed42-4bbb-a83d-bdd6342a36e0")
    private List<GProblem> problems = new ArrayList<>();

    @objid ("613c6f02-55ae-4826-9068-c2bfe42dfd1c")
    private final IProjectStateChangeListener listener = this::apply;

    /**
     * The project being updated.
     * @param project a project.
     */
    @objid ("75ae8937-d647-48f4-b4d6-4fe37d96ab7a")
    public  GProjectConfPlan(IGProject project) {
        this.project = project;
    }

    /**
     * Add an action to the plan.
     * @param action an action.
     */
    @objid ("5defc610-bd16-41fd-bc8d-7c0200e46462")
    public void addAction(IGProjectConfAction action) {
        this.actions.add(action);
    }

    /**
     * Add actions to the plan.
     * @param someActions a bunch of actions.
     */
    @objid ("142b4cfa-2d68-4d6b-9993-2893ccb52164")
    public void addAll(List<IGProjectConfAction> someActions) {
        this.actions.addAll(someActions);
    }

    /**
     * Starts the project reconfiguration.
     * @param aMonitor a progress monitor
     */
    @objid ("12d13d2d-b47c-4734-9434-b12b600a5668")
    public void start(IModelioProgress aMonitor) {
        this.project.getState().addListener(this.listener);
        
        // Manually fire the listener for the current state
        this.listener.stateChanged(aMonitor, this.project.getState().getValue());
        
    }

    @objid ("d17463bc-530b-4749-933f-71c3120caf7c")
    public void stop() {
        this.project.getState().removeListener(this.listener);
    }

    /**
     * @return problems encountered applying the plan.
     */
    @objid ("45cbeeb1-ba71-43b7-9a43-a1dc2436060d")
    public List<GProblem> getProblems() {
        return this.problems;
    }

    /**
     * Apply the plan, i.e. apply all its actions for the current phase.
     * @param phase the project's current state. Actions may apply only on specific phases.
     */
    @objid ("984a90b2-7acb-42ef-87f9-4e043bd126c4")
    private void apply(IModelioProgress monitorSupplier, GProjectStateEnum phase) {
        int nticks = this.actions.size() * 2;
        SubProgress parentMonitor = SubProgress.convert(monitorSupplier, nticks);
        
        for (IGProjectConfAction action : this.actions) {
            this.problems.addAll(action.apply(monitorSupplier, this.project, phase));
            parentMonitor.setWorkRemaining(--nticks);
        }
        
    }

    /**
     * Dump a basic description of the plan for debug purpose.
     * @param lineConsumer a consumer that will receive all lines one by one.
     */
    @objid ("7d2a0977-a874-4e4b-a1d6-543c2c771fa9")
    public void dump(Consumer<String> lineConsumer) {
        if (this.project != null) {
            lineConsumer.accept(String.format("Update project action plan for '%s'", this.project.getName()));
            for (IGProjectConfAction action : this.actions) {
                lineConsumer.accept(String.format("\t- %s", action.getLabel()));
            }
        } else {
            lineConsumer.accept("Empty update project action plan");
        }
        
    }

    /**
     * @return true if there is nothing to do.
     */
    @objid ("8d672842-a507-435d-a09c-d4f18c0c3c04")
    public boolean isEmpty() {
        return this.actions.isEmpty();
    }

}
