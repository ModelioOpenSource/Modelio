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

package org.modelio.mda.infra.service.impl.controller.states;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * State machine transition.
 * <p>
 * Use {@link Transition#build()} to get a {@link Builder} to instantiates transitions.
 */
@objid ("72125344-4a4e-4b94-b86c-5703e7deadaa")
public class Transition {
    @objid ("8a91b9e9-feaf-4c22-9422-875ce3572fc1")
    private final AbstractModuleState srcState;

    @objid ("f8d29f09-5f12-431e-9173-06b21a5959fb")
    private final AbstractModuleState targetState;

    @objid ("d1c30fdc-026d-4bbd-8336-7767d688f687")
    private final Object message;

    @objid ("337fcdbe-4783-42bc-bf17-08a76ef7763a")
    private final Object postTransitionMessage;

    @objid ("8a8ec349-df68-46f6-b817-ec743dc2800b")
    private final List<IModuleStateAction> actions;

    @objid ("14b27081-ae42-479c-974d-26a6f6c963a1")
    protected Transition(AbstractModuleState srcState, AbstractModuleState targetState, Object message, Object postTransitionMessage, List<IModuleStateAction> actions) {
        this.srcState = srcState;
        this.targetState = targetState;
        this.message = message;
        this.postTransitionMessage = postTransitionMessage;
        this.actions = actions;
    }

    /**
     * @return the source state
     */
    @objid ("ecb1620c-58c6-45af-89d3-bc957b4596e0")
    public AbstractModuleState getSource() {
        return this.srcState;
    }

    /**
     * @return the target state
     */
    @objid ("58d08674-cfca-47bc-a056-89fb18221e65")
    public AbstractModuleState getTarget() {
        return this.targetState;
    }

    /**
     * @return the message that triggers the transition
     */
    @objid ("f2233f3e-e545-4d93-929b-4d7753646cb8")
    public Object getMessage() {
        return this.message;
    }

    /**
     * @return the message to send once the transition is complete
     */
    @objid ("9164695b-bf72-4617-b824-aaab39ac3851")
    public Object getPostTransitionMessage() {
        return this.postTransitionMessage;
    }

    /**
     * Get a transition builder object.
     * @return a transition builder.
     */
    @objid ("caad743f-aa19-44e7-a042-22c9daf561ab")
    public static Builder build() {
        return new Builder();
    }

    /**
     * Get actions to execute before switching to the target state.
     * @return the transition actions
     */
    @objid ("5fd46033-08ef-447c-9aa2-586137792355")
    public List<IModuleStateAction> getActions() {
        return this.actions;
    }

    /**
     * Transition builder.
     * <p>
     * This is the "Builder" design pattern applied to Transition.
     */
    @objid ("c34a38bf-2eb8-4d63-9f47-88e481736515")
    @SuppressWarnings("hiding")
    public static class Builder {
        @objid ("c75e261f-34f8-42be-93b0-d72dbdff46a1")
        private AbstractModuleState srcState;

        @objid ("9ec62896-fda7-4e17-8f6a-cfe4aa888fcd")
        private AbstractModuleState targetState;

        @objid ("666a59ff-f25d-45bc-8005-6645f3410ee1")
        private Object message;

        @objid ("5ec7b510-9bb8-4b10-ba2d-63966821662e")
        private Object postTransitionMessage;

        @objid ("ec3f1854-109b-451b-9a80-d762532a71fa")
        private List<IModuleStateAction> actions = new ArrayList<>(1);

        /**
         * Set the origin state.
         * @param srcState the origin state
         * @return this
         */
        @objid ("05646ee7-2678-414d-9edf-ebe674ca79c2")
        public Builder from(AbstractModuleState srcState) {
            this.srcState = srcState;
            return this;
        }

        /**
         * Set the target state.
         * @param targetState the target state
         * @return this
         */
        @objid ("91defabe-7d80-4340-8507-a6585e810ecf")
        public Builder to(AbstractModuleState targetState) {
            this.targetState = targetState;
            return this;
        }

        /**
         * Create the transition.
         * <p>
         * The builder can still be used with the configuration already set.
         * @return the created transition.
         */
        @objid ("f215ccae-927f-4857-985c-85812ab9d464")
        public Transition create() {
            Transition t = new Transition(this.srcState, this.targetState, this.message,
                    this.postTransitionMessage, this.actions);
            return t;
        }

        /**
         * Set the message that triggers the transition.
         * @param message the trigger message.
         * @return this
         */
        @objid ("c9c2e493-13c5-466f-8727-c20e0af876fe")
        public Builder message(Object message) {
            this.message = message;
            return this;
        }

        /**
         * Set a new message to send once the transition is complete.
         * @param postTransitionMessage the post transition message to send
         * @return this
         */
        @objid ("d2b37046-0066-4d29-85c6-559aaf730bde")
        public Builder postMessage(Object postTransitionMessage) {
            this.postTransitionMessage = postTransitionMessage;
            return this;
        }

        /**
         * Add an action to execute before switching to the target state.
         * @param action an action to execute.
         * @return this builder for convenience.
         */
        @objid ("eaa7ffb4-ec30-4f3e-8e23-52f885514340")
        public Builder action(IModuleStateAction action) {
            this.actions.add(action);
            return this;
        }

    }

}
