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
import java.util.List;
import java.util.ListIterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.platform.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.platform.mda.infra.service.impl.controller.states.actions.CallModuleInstallAction;
import org.modelio.platform.mda.infra.service.impl.controller.states.actions.SelectModuleAction;
import org.modelio.platform.mda.infra.service.impl.controller.states.actions.SetGModuleStateAction;
import org.modelio.platform.mda.infra.service.impl.controller.states.actions.UnselectModuleAction;
import org.modelio.platform.mda.infra.service.impl.controller.states.features.DiagramStylesInstalledFeature;
import org.modelio.platform.mda.infra.service.impl.controller.states.features.DocFeature;
import org.modelio.platform.mda.infra.service.impl.controller.states.features.DynamicModelFeature;
import org.modelio.platform.mda.infra.service.impl.controller.states.features.GuiContribFeature;
import org.modelio.platform.mda.infra.service.impl.controller.states.features.IModuleLoadedFeature;
import org.modelio.platform.mda.infra.service.impl.controller.states.features.MmFragmentsFeature;
import org.modelio.platform.mda.infra.service.impl.controller.states.features.StartedFeature;

/**
 * {@link IRTModule} state machine.
 */
@objid ("05ca0a56-9bbc-473c-87fa-41791f625c58")
public class States {
    @objid ("582db6c3-53f5-4dfb-a01a-6b371ecaf344")
    private static final boolean DEBUG_FEATURES = false;

    /**
     * Message to install the module.
     */
    @objid ("aa8e7402-0a76-4de5-a609-bc7280b2786c")
    public static final Object MSGINSTALL = "install";

    /**
     * Message to select the module.
     */
    @objid ("d5662d47-b47e-4dd5-8111-0022669e1ca3")
    public static final Object MSGSELECT = "select";

    /**
     * Message to activate a disabled module.
     */
    @objid ("4410e9bf-b3cb-47bf-bfa4-8021ec263d18")
    public static final Object MSGACTIVATE = "activate";

    /**
     * Message to disable an activated module.
     */
    @objid ("e0aed7e6-6031-4c14-b455-ffa1906dc467")
    public static final Object MSGDISABLE = "disable";

    /**
     * Message to start an activated module.
     */
    @objid ("a61e4b44-5bf2-4dc9-afeb-e527cb92d218")
    public static final Object MSGSTART = "start";

    /**
     * Message to stop a started module.
     */
    @objid ("c8ab4e90-2489-4775-9e64-e5bca557396b")
    public static final Object MSGSTOP = "stop";

    /**
     * Message to remove the module from the project.
     */
    @objid ("5723dfad-48ca-4f7a-a651-1671eb8cdd86")
    public static final Object MSGDELETE = "delete";

    /**
     * Message to unload the IModule..
     */
    @objid ("9ab310a1-e6e9-4b56-b82e-45881cbb2d50")
    public static final Object MSGUNLOAD = "unload";

    /**
     * Message to load an activated module.
     */
    @objid ("e072f7af-8928-4c57-80fc-0d62bae881ad")
    public static final Object MSGLOADACTIVATED = "load activated";

    /**
     * Message to load an disabled module.
     */
    @objid ("32cb9c2c-7688-43cb-b34a-24fd42058bc1")
    public static final Object MSGLOADDISABLED = "load_disabled";

    /**
     * Message to close the IModule : to be called when closing the project.
     * The module should unload resources that won't be freed by the project.
     */
    @objid ("b74a4cbb-580c-4607-910e-0b8ee087fbba")
    public static final Object MSGCLOSE = "close";

    @objid ("870328f1-223a-4d7e-abac-9912fb333ee4")
    private final IModuleFeature docFeature;

    @objid ("2cbfa39e-6547-4a9b-88e6-678af3617239")
    private final IModuleFeature dynamicModelFeature;

    @objid ("01136db3-7e70-41c2-af4b-588fb67f70b9")
    private final IModuleFeature loadedFeature;

    @objid ("a50f6ba3-e8a0-43f9-91ca-3196e7d703d2")
    private final IModuleFeature mmFragmentFeature;

    @objid ("427fcbdc-d25e-4f3c-9492-028f01c1fc8b")
    private final IModuleFeature startedFeature;

    @objid ("e3b3f82c-c47e-4bf9-9a7c-5d28dc239534")
    private final AbstractModuleState started;

    @objid ("cfc32ac6-62c5-4bf6-8141-69790c90a008")
    private final AbstractModuleState toselect;

    @objid ("0b293a48-1bab-4d1e-8ce1-339f7d2e6b92")
    private final AbstractModuleState disabled;

    @objid ("5a328465-ac2d-47df-a8c0-fcc8b7f4dda1")
    private final AbstractModuleState activated;

    @objid ("1a1ebce7-b140-4466-8ed6-684bffb74799")
    private final AbstractModuleState unloaded;

    @objid ("0b5305e0-af70-457f-aff9-451686772772")
    private final AbstractModuleState removed;

    @objid ("fc1b30b9-66b2-4dba-ab62-31035b5175df")
    private final AbstractModuleState closed;

    @objid ("bdbd86c6-0109-4ab3-bdda-d6ee33222a89")
    private AbstractModuleState currentState;

    /**
     * Currently activated features
     */
    @objid ("b1e21270-af24-4204-98b6-6ec2653c17c5")
    private List<IModuleFeature> currentFeatures = new ArrayList<>();

    @objid ("9b73813f-279b-436e-ada6-94a478b3c936")
    private final IRTModuleAccess rtModule;

    @objid ("de1ab3bb-f9d9-47cc-ba97-8313189ded38")
    private final IModuleFeature guiFeature;

    @objid ("8fb2d50c-771e-4f15-841a-b613b333ce7b")
    private final IModuleFeature diagramStylesFeature;

    /**
     * Initialize the state machine.
     * @param rtModule the module controller
     */
    @objid ("99cea2e7-63bf-4465-b0bd-6b2667966f65")
    public  States(IRTModuleAccess rtModule) {
        this.rtModule = rtModule;
        
        // First initialize features
        // -------------------------
        this.docFeature = new DocFeature(this.rtModule);
        this.dynamicModelFeature = new DynamicModelFeature(this.rtModule);
        this.loadedFeature = new IModuleLoadedFeature(this.rtModule);
        this.mmFragmentFeature = new MmFragmentsFeature(this.rtModule);
        this.startedFeature = new StartedFeature(this.rtModule);
        this.guiFeature = new GuiContribFeature(this.rtModule);
        this.diagramStylesFeature = new DiagramStylesInstalledFeature(rtModule);
        
        
        // Define states
        // -------------
        this.toselect = new AbstractModuleState(this.rtModule, "To select"); // installed, to select
        this.disabled = new AbstractModuleState(this.rtModule, "Disabled");
        this.activated = new AbstractModuleState(this.rtModule, "Activated"); // activated but stopped
        this.started = new AbstractModuleState(this.rtModule, "Started");
        this.unloaded = new AbstractModuleState(this.rtModule, "Unloaded"); // not yet loaded or broken
        this.removed = new AbstractModuleState(this.rtModule, "Removed"); // definitively removed from project
        this.closed = new AbstractModuleState(this.rtModule, "Project closed"); // fast unload on project close
        
        // temp states
        AbstractModuleState starting = new AbstractModuleState(this.rtModule, "Starting");
        AbstractModuleState stopping = new AbstractModuleState(this.rtModule, "Stopping");
        AbstractModuleState unloading = new AbstractModuleState(this.rtModule, "Unloading");
        AbstractModuleState activating = new AbstractModuleState(this.rtModule, "Activating");
        AbstractModuleState disabling = new AbstractModuleState(this.rtModule, "Disabling");
        AbstractModuleState closing = new AbstractModuleState(this.rtModule, "Closing");
        
        
        // Initialize the current state
        this.currentState = this.unloaded;
        
        // Set states features
        // -------------------
        // Note: The features are activated in the order they are declared and deactivated in the reverse one.
        //       Order is important because some features depend on others
        //       (many depend on this.loadedFeature).
        
        this.unloaded.addFeatures();
        
        this.toselect.addFeatures(this.loadedFeature);
        
        this.disabled.addFeatures(this.loadedFeature, this.diagramStylesFeature);
        
        this.activated.addFeatures(this.loadedFeature,
                this.mmFragmentFeature,
                this.diagramStylesFeature,
                this.dynamicModelFeature,
                this.docFeature
                );
        
        this.started.addFeatures(this.loadedFeature,
                this.mmFragmentFeature,
                this.diagramStylesFeature,
                this.dynamicModelFeature,
                this.guiFeature,
                this.docFeature,
                this.startedFeature);
        
        
        // Metamodel fragments are too costly to remove
        this.closed.addFeatures(this.mmFragmentFeature);
        
        this.removed.addFeatures();
        
        starting.addFeatures(this.started.getFeatures());
        unloading.addFeatures();
        disabling.addFeatures(this.disabled.getFeatures());
        activating.addFeatures(this.activated.getFeatures());
        stopping.addFeatures(this.activated.getFeatures());
        closing.addFeatures(this.closed.getFeatures());
        
        
        // Some transition actions
        SetGModuleStateAction setDisabledAction = new SetGModuleStateAction(this.rtModule, false);
        SetGModuleStateAction setActivatedAction = new SetGModuleStateAction(this.rtModule, true);
        
        // Transitions, grouped by use cases
        // ---------------------------------
        
        // Installation : install, activates and start the module
        add(Transition.build().from(this.unloaded).to(this.toselect).message(MSGINSTALL).postMessage(MSGSELECT)
                .action(new CallModuleInstallAction(this.rtModule)) );
        add(Transition.build().from(this.toselect).to(this.disabled).message(MSGSELECT).postMessage(MSGACTIVATE)
                .action(new SelectModuleAction(this.rtModule)) );
        
        // Activate : activate and starts the module
        add(Transition.build().from(this.disabled).to(activating).message(MSGACTIVATE).postMessage(MSGACTIVATE));
        add(Transition.build().from(this.unloaded).to(activating).message(MSGACTIVATE).postMessage(MSGACTIVATE));
        add(Transition.build().from(activating).to(this.activated).message(MSGACTIVATE).postMessage(MSGSTART)
                .action(setActivatedAction));
        add(Transition.build().from(this.activated).to(this.activated).message(MSGACTIVATE).postMessage(MSGSTART));
        
        // Start : activate if needed
        add(Transition.build().from(this.unloaded).to(activating).message(MSGSTART).postMessage(MSGACTIVATE));
        add(Transition.build().from(this.activated).to(starting).message(MSGSTART).postMessage(MSGSTART));
        add(Transition.build().from(starting).to(this.started).message(MSGSTART));
        
        // Temporary stop
        add(Transition.build().from(this.started).to(stopping).message(MSGSTOP).postMessage(MSGSTOP));
        add(Transition.build().from(stopping).to(this.activated).message(MSGSTOP));
        
        // Deactivate : stop if needed, works on broken module
        add(Transition.build().from(this.started).to(stopping).message(MSGDISABLE).action(setDisabledAction).postMessage(MSGDISABLE));
        add(Transition.build().from(stopping).to(this.activated).message(MSGDISABLE).postMessage(MSGDISABLE));
        add(Transition.build().from(this.activated).to(disabling).message(MSGDISABLE).postMessage(MSGDISABLE));
        add(Transition.build().from(disabling).to(this.disabled).message(MSGDISABLE)
                .action(setDisabledAction));
        
        add(Transition.build().from(this.unloaded).to(this.unloaded).message(MSGDISABLE)
                .action(setDisabledAction));
        
        // Unload : stop if needed
        add(Transition.build().from(this.started).to(stopping).message(MSGUNLOAD).postMessage(MSGUNLOAD));
        add(Transition.build().from(stopping).to(this.activated).message(MSGUNLOAD).postMessage(MSGUNLOAD));
        add(Transition.build().from(this.activated).to(unloading).message(MSGUNLOAD).postMessage(MSGUNLOAD));
        add(Transition.build().from(this.disabled).to(unloading).message(MSGUNLOAD).postMessage(MSGUNLOAD));
        add(Transition.build().from(unloading).to(this.unloaded).message(MSGUNLOAD));
        
        // Load on project open, without starting
        add(Transition.build().from(this.unloaded).to(activating).message(MSGLOADACTIVATED).postMessage(MSGLOADACTIVATED));
        add(Transition.build().from(activating).to(this.activated).message(MSGLOADACTIVATED));
        add(Transition.build().from(this.unloaded).to(this.disabled).message(MSGLOADDISABLED));
        
        // Stop and fast unload on project close
        add(Transition.build().from(this.started).to(stopping).message(MSGCLOSE).postMessage(MSGCLOSE));
        add(Transition.build().from(starting).to(this.activated).message(MSGCLOSE).postMessage(MSGCLOSE));
        add(Transition.build().from(activating).to(this.closed).message(MSGCLOSE));
        add(Transition.build().from(starting).to(this.closed).message(MSGCLOSE));
        add(Transition.build().from(stopping).to(this.closed).message(MSGCLOSE));
        add(Transition.build().from(this.toselect).to(this.closed).message(MSGCLOSE));
        add(Transition.build().from(this.activated).to(this.closed).message(MSGCLOSE));
        add(Transition.build().from(this.disabled).to(this.closed).message(MSGCLOSE));
        
        // Remove from project
        add(Transition.build().from(this.started).to(this.disabled).message(MSGDELETE).postMessage(MSGDELETE));
        add(Transition.build().from(this.activated).to(this.disabled).message(MSGDELETE).postMessage(MSGDELETE));
        add(Transition.build().from(this.disabled).to(this.removed).message(MSGDELETE)
                .action(new UnselectModuleAction(this.rtModule)));
        add(Transition.build().from(this.unloaded).to(this.removed).message(MSGDELETE));
        
        
        // No-op Messages when the module is already in the right state
        setNoEffect(MSGSTART, this.started);
        setNoEffect(MSGSTOP, this.activated, this.disabled, this.unloaded);
        setNoEffect(MSGUNLOAD, this.unloaded);
        setNoEffect(MSGCLOSE, this.closed, this.unloaded, this.removed);
        setNoEffect(MSGACTIVATE, this.started);
        setNoEffect(MSGDISABLE, this.disabled);
        setNoEffect(MSGLOADACTIVATED, this.activated, starting, this.started);
        setNoEffect(MSGLOADDISABLED, this.disabled);
        
    }

    /**
     * Add the transition to the state graph
     * @param tb the transition descriptor
     */
    @objid ("1cd3721f-4434-4d85-a89c-347f7eb9c3d0")
    private void add(org.modelio.platform.mda.infra.service.impl.controller.states.Transition.Builder tb) {
        Transition t = tb.create();
        t.getSource().addTransition(t);
        
    }

    /**
     * Try to move to the given state.
     * <p>
     * Executes all exit() and enter() on state change.
     * @param newState
     * @throws ModuleException on failure
     */
    @objid ("53444d26-82fb-4297-9ae3-55bd6657b82e")
    public void changeState(AbstractModuleState newState) throws ModuleException {
        if (this.currentState == newState) {
            // NOOP
            MdaInfra.LOG.debug("'%s' v%s module state already '%s'", this.rtModule.getName(), this.rtModule.getVersion(), this.currentState);
            return;
        }
        
        MdaInfra.LOG.debug("'%s' v%s module state changing from '%s' to '%s'", this.rtModule.getName(), this.rtModule.getVersion(), this.currentState, newState);
        
        AbstractModuleState oldState = this.currentState;
        
        // Exit previous state
        this.currentState.exitState(newState);
        
        try {
            // Enter new state
            newState.enterState(this.currentState);
        
            // Record state
            this.currentState = newState;
        
            // Update features
            try {
                MdaInfra.LOG.indent();
                updateFeatures(newState);
            } finally {
                MdaInfra.LOG.dedent();
            }
        
            // Reset down error
            this.rtModule.setDownError(null);
        
        } catch (ModuleException ex) {
            MdaInfra.LOG.debug("'%s' v%s module state change to '%s' failed: %s",this.rtModule.getName(), this.rtModule.getVersion(), newState, ex.getMessage());
        
            // Restore previous state
            try {
                this.currentState.enterState(oldState);
                this.rtModule.setDownError( ex);
            } catch (ModuleException | RuntimeException | LinkageError  e2) {
                e2.addSuppressed(ex);
                throw new ModuleException("Unable to restore previous state: "+e2.toString(), e2);
            }
            this.currentState = oldState;
        
            // rethrow exception
            throw ex;
        } catch (RuntimeException | LinkageError ex) {
            String msg = String.format("'%s' v%s module state change to '%s' unexpectedly failed: %s",this.rtModule.getName(), this.rtModule.getVersion(), newState, ex);
            MdaInfra.LOG.debug(msg);
            ModuleException moduleException = new ModuleException(msg, ex);
        
            // Restore previous state
            try {
                this.currentState.enterState(oldState);
                this.rtModule.setDownError( moduleException);
            } catch (ModuleException | RuntimeException | LinkageError  e2) {
                ex.addSuppressed(new ModuleException("Unable to restore previous state: "+e2.toString(), e2));
            }
        
            this.currentState = oldState;
        
            // throw new exception
            throw moduleException;
        }
        
    }

    /**
     * Force the current state.
     * @param newState the new state
     */
    @objid ("a68aa1b7-8856-40e4-bc94-a3c6433bd3bd")
    public void forceCurrentState(AbstractModuleState newState) {
        // Record state
        this.currentState = newState;
        
    }

    /**
     * Handle a message.
     * @param message the received message
     * @throws ModuleException on failure
     */
    @objid ("0a0cdd18-cad5-4abd-8fc9-467939f0422a")
    public void handleMessage(Object message) throws ModuleException {
        MdaInfra.LOG.debug("'%s' v%s module [%s] : message '%s'", this.rtModule.getName(), this.rtModule.getVersion(), this.currentState, message);
        
        // Look for transition, may throw IllegalStateException
        Transition t = this.currentState.getTransition(message);
        
        
        // Execute transition actions
        for (IModuleStateAction a : t.getActions()) {
            a.execute();
        }
        
        // Change target and features
        AbstractModuleState target = t.getTarget();
        changeState(target);
        
        // Send post transition message if any
        Object postTransitionMessage = t.getPostTransitionMessage();
        if (postTransitionMessage != null) {
            handleMessage(postTransitionMessage);
        }
        
    }

    @objid ("57b76af8-3295-4342-89a2-7a875dba6472")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.currentState.toString()).append("\n");
        sb.append("Current features:");
        
        for (IModuleFeature iModuleFeature : this.currentFeatures) {
            sb.append("\n - ").append(iModuleFeature);
        }
        return sb.toString();
    }

    /**
     * @return true if the module is started.
     */
    @objid ("8accdfb7-f5ef-4c32-8535-15f3a5f386f5")
    public boolean isStarted() {
        return this.currentFeatures.contains(this.startedFeature);
    }

    /**
     * @return true if the IModule class is loaded.
     */
    @objid ("59db1446-050f-490e-b475-91475d3335fc")
    public boolean isLoaded() {
        return this.currentFeatures.contains(this.loadedFeature);
    }

    /**
     * Set the current state as the broken state.
     */
    @objid ("29a6f6f1-49b0-47a2-a6b2-dbdc53d9d7c5")
    public void setBroken() {
        try {
            changeState(this.unloaded);
        } catch (ModuleException  e) {
            forceCurrentState(this.unloaded);
        }
        
    }

    @objid ("6c686694-c1b5-4093-898e-d31ff7ec709a")
    private void setNoEffect(Object msg, AbstractModuleState... states) {
        Transition.Builder builder = Transition.build().message(msg);
        for (AbstractModuleState s : states) {
            add(builder.from(s).to(s));
        }
        
    }

    @objid ("7a11d515-3ace-41d0-bfc1-b78c370bc34f")
    private void setFeature(IModuleFeature feature, AbstractModuleState... states) {
        for (AbstractModuleState st : states) {
            st.addFeatures(feature);
        }
        
    }

    /**
     * Update currently activated features from the new state.
     * @param newState the new state
     * @throws ModuleException if state change is refused by a feature, in case of breaking error
     */
    @objid ("7c2b7d77-6966-4d95-97d5-f74d3d59ca84")
    private void updateFeatures(AbstractModuleState newState) throws ModuleException {
        List<IModuleFeature> newFeatures = newState.getFeatures();
        
        // disable features to remove, in the reverse order
        for (ListIterator<IModuleFeature> it = this.currentFeatures.listIterator(this.currentFeatures.size());  it.hasPrevious(); ) {
            IModuleFeature f = it.previous();
        
            if (! newFeatures.contains(f)) {
                try {
                    if (DEBUG_FEATURES) {
                        MdaInfra.LOG.debug("'%s' v%s module state [%s] : disabling feature '%s' ",this.rtModule.getName(), this.rtModule.getVersion(), newState,f);
                    }
        
                    f.disable();
        
                    it.remove();
                } catch (ModuleException | RuntimeException | LinkageError e) {
                    // Log and continue
                    MdaInfra.LOG.debug("'%s' v%s module state [%s] : disabling feature '%s' failed: %s",this.rtModule.getName(), this.rtModule.getVersion(),newState,f,e.toString());
                    MdaInfra.LOG.warning(e);
                }
            }
        }
        
        // Activate new features
        boolean ok = false;
        ArrayList<IModuleFeature> toAbort = new ArrayList<>(newFeatures.size());
        IModuleFeature processed = null;
        
        try {
            for (IModuleFeature f : newFeatures) {
                processed = f;
                if (! this.currentFeatures.contains(f)) {
                    if (DEBUG_FEATURES) {
                        MdaInfra.LOG.debug("'%s' v%s module state [%s] : enabling feature '%s' ",this.rtModule.getName(), this.rtModule.getVersion(),newState,f);
                    }
        
                    f.enable();
        
                    toAbort.add(f);
                    this.currentFeatures.add(f);
                }
            }
        
            ok = true;
        } finally {
            if (! ok) {
                MdaInfra.LOG.debug("'%s' v%s module state [%s] :  '%s' feature failed to enable, reverting %d features...  ",this.rtModule.getName(), this.rtModule.getVersion(),newState, processed, toAbort.size());
                MdaInfra.LOG.indent();
        
                try {
                    for (IModuleFeature f : toAbort) {
                        try {
                            f.disable();
                            this.currentFeatures.remove(f);
                            MdaInfra.LOG.debug("'%s' v%s module state [%s] : reverted feature '%s' ", this.rtModule.getName(), this.rtModule.getVersion(), newState, f);
                        } catch (ModuleException e) {
                            MdaInfra.LOG.debug("'%s' v%s module state [%s] : reverting feature '%s' failed: %s", this.rtModule.getName(), this.rtModule.getVersion(), newState, f, e.toString());
                            MdaInfra.LOG.debug(e);
                        } catch (RuntimeException | LinkageError e) {
                            MdaInfra.LOG.warning("'%s' v%s module state [%s] : reverting feature '%s' failed: %s", this.rtModule.getName(), this.rtModule.getVersion(), newState, f, e.toString());
                            MdaInfra.LOG.warning(e);
                        }
                    }
                } finally {
                    MdaInfra.LOG.dedent();
                }
            }
        }
        
    }

}
