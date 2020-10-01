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

package org.modelio.platform.mda.infra.service.impl.controller.states;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.platform.mda.infra.service.IRTModule;

/**
 * Abstract implementation of a state.
 */
@objid ("a64cb68e-5a2d-4c1c-8cda-5ac674be68c7")
public class AbstractModuleState {
    @objid ("093b07ff-9ed2-4c69-adb9-6b90a8e91f21")
    private String name;

    @objid ("73dbfcc9-4fbc-445b-a31e-e824b885e404")
    private final IRTModule rtModule;

    @objid ("8968f5d9-193f-4fbc-a279-c3335432a83e")
    private final List<IModuleFeature> features = new ArrayList<>();

    @objid ("b1c37854-5baf-4e5c-9772-68b40033da85")
    private Map<Object, Transition> transitions = new HashMap<>();

    /**
     * @param rtModule the module
     * @param name the state name
     */
    @objid ("abff2224-c0a3-4536-98f0-b35cf0edf994")
    public AbstractModuleState(IRTModule rtModule, String name) {
        this.name = name;
        this.rtModule = rtModule;
    }

    /**
     * Add features that must be active in this state
     * 
     * @param pfeatures the features to be active
     */
    @objid ("b8eb8ed2-ed2b-4cde-b19f-5a8fa1e7bc92")
    public void addFeatures(IModuleFeature... pfeatures) {
        this.features.addAll(Arrays.asList(pfeatures));
    }

    @objid ("6a738bee-be29-4db4-9ee2-15f8d88cef83")
    protected IRTModule getRtModule() {
        return this.rtModule;
    }

    /**
     * Called when entering state, just before current state is set.
     * 
     * @param oldState the old state.
     * @throws org.modelio.api.module.lifecycle.ModuleException if state change is refused and the previous state must be restored.
     */
    @objid ("75d05c17-fca6-425f-9553-9413e011a93a")
    public void enterState(AbstractModuleState oldState) throws ModuleException {
        // nothing by default
    }

    /**
     * Called before exiting state.
     * 
     * @param newState the new state
     */
    @objid ("b6683ac5-c762-40a3-9a46-241cf28bc352")
    public void exitState(AbstractModuleState newState) {
        // nothing by default
    }

    /**
     * @param message a message
     * @return the matching transition
     * @throws java.lang.IllegalStateException if there is no such transition
     */
    @objid ("19e5f228-1c29-41b1-a66e-90640c741dab")
    public Transition getTransition(Object message) throws IllegalStateException {
        Transition t = this.transitions.get(message);
        if (t == null) {
            throw new IllegalStateException(this.rtModule.getName()+" module: '"+message+"' message illegal in '"+toString()+"' state.");
        }
        return t;
    }

    /**
     * Register a transition
     * 
     * @param t a transition
     */
    @objid ("182185a1-41c8-49e6-b6db-bd01779f05db")
    public void addTransition(Transition t) {
        this.transitions.put(t.getMessage(), t);
    }

    @objid ("054c7d17-2377-4024-812e-7bfd35bc94aa")
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * @return the state features.
     */
    @objid ("b9438325-b4bd-434a-bfe0-062410de2b74")
    public List<IModuleFeature> getFeatures() {
        return this.features;
    }

    @objid ("d8bb47f6-d664-441f-ad53-cdd99b4c84e2")
    public void addFeatures(List<IModuleFeature> features2) {
        this.features.addAll(features2);
    }

}
