/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.module.modelermodule.api;

import java.nio.file.Path;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IPeerModule;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;

/**
 * Interface gathering all public services provided by Modeler Module.
 * Another module may call those services, if it has a dependency towards ModelerModule.
 */
@objid ("8e56d740-6ad2-494a-b432-b324566b5491")
public interface IModelerModulePeerModule extends IPeerModule {
    @objid ("dec66be7-ac89-4d74-b771-282cd6e56bcb")
    public static final String MODULE_NAME = "ModelerModule";

    /**
     * Create an attribute from an attribute link. If the class doesn't exists, it is also created.
     * 
     * @param attr The attribute link to create a new attribute from.
     * @return <code>true</code> when an attribute is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException when the attribute already exists, or when the base of the instance isn't a classifier.
     */
    @objid ("1ef5d499-133a-4227-b84e-587708ae972f")
    boolean createAttribute(final AttributeLink attr) throws ModelerModuleException;

    /**
     * Create a classifier from an instance.
     * Includes creation of:
     * - ports from the instance ports.
     * - attributes from attribute links.
     * - operations from incoming messages.
     * 
     * @param inst The instance to create the classifier from.
     * @return <code>true</code> when a new classifier is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during creation.
     */
    @objid ("82aed8f5-f20f-4c91-bac8-4ac8a65c4327")
    boolean createClassifier(final Instance inst) throws ModelerModuleException;

    /**
     * Create a classifier from a lifeline.
     * Includes creation of:
     * - an instance represented by the lifeline.
     * - ports from the instance ports.
     * - attributes from attribute links.
     * - operations from incoming messages.
     * 
     * @param ll The lifeline to create the classifier from.
     * @return <code>true</code> when a new classifier is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during creation.
     */
    @objid ("2e2d7c4f-6806-47f4-9875-20ef1e778f7f")
    boolean createClassifierByLifeline(final Lifeline ll) throws ModelerModuleException;

    /**
     * Create an operation from a message.
     * An Instance might be created in the process, or a Classifier.
     * 
     * @param message The message to create the operation from.
     * @return <code>true</code> if a new operation is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during the creation.
     */
    @objid ("0f897680-5ea7-45d4-aa82-8e4c2df836cd")
    boolean createOperationFromMessage(final Message message) throws ModelerModuleException;

    /**
     * Create an operation from a transition.
     * An Instance might be created in the process, or a Classifier.
     * 
     * @param transition The transition to create the operation from.
     * @return <code>true</code> if a new transition is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during the creation.
     */
    @objid ("5a889cba-ccc6-42c4-9369-c9957b093586")
    boolean createOperationFromTransition(final Transition transition) throws ModelerModuleException;

    /**
     * Create Operations in those Classifiers from those defined in their implemented Interfaces.
     * 
     * @param classifiers The Classifiers to create the Operations in.
     */
    @objid ("d30dd53a-79f5-4a75-b7a1-052c50fd56aa")
    void implementInterfaces(final List<Classifier> classifiers);

    /**
     * Delete Operations in Classifiers from those defined in their implemented Interfaces.
     * 
     * @param classifiers The Classifiers to remove the Operations from.
     */
    @objid ("f759f7bd-ee15-40c3-a507-ce0b090c98e5")
    void unimplementInterfaces(final List<Classifier> classifiers);

    /**
     * For all classifiers implementing those interfaces, synchronize all operation signatures.
     * Missing operations are created.
     * 
     * @param interfaces the interfaces to update operations from.
     */
    @objid ("651d2c3e-2634-44f0-8dcc-fda73057c3a5")
    void updateClassesFromInterface(final List<Interface> interfaces);

    /**
     * Update a part contents from its base classifier.
     * Allows creation of a new classifier if no base exists, or referencing an existing classifier.
     * 
     * @param inst the instance to update.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException when an error occurs during the update.
     */
    @objid ("2f9bc9c1-5acc-4b18-b00b-c939cf996527")
    void updateInstanceFromClassifier(final Instance inst) throws ModelerModuleException;

    /**
     * Update a lifeline's represented instance contents from its base classifier.
     * Allows creation of the instance, and of a new classifier if no base exists, or referencing an existing classifier.
     * 
     * @param ll the lifeline to update.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException when an error occurs during the update.
     */
    @objid ("56389721-7ce9-4be6-9731-2089605bfebf")
    void updateFromClassifierByLifeline(final Lifeline ll) throws ModelerModuleException;

    /**
     * Update the internal structure of a class.
     * Updates all parts from their base classifiers, and allows creation of all missing bases.
     * It is also possible to reference an existing classifier.
     * 
     * @param classToUpdate The class to update.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during the update.
     */
    @objid ("ce238806-4320-468c-9c99-04a7bc9f06cc")
    void updateInternalStructure(final Class classToUpdate) throws ModelerModuleException;

    /**
     * Create a sub state machine from a state having entry and exit points.
     * 
     * @param state the state to create the sub state machine from.
     * @return the create sub state machine.
     */
    @objid ("3ad54a1b-2c5e-41c9-ade5-58fb48d28284")
    StateMachine createSubStateMachineFromCompositeState(final State state);

    /**
     * Updates a state machine from a sub state machine. (entry, exit points)
     * 
     * @param state the state to update.
     */
    @objid ("eabbafc2-81a9-448f-bb95-434f8c5ac5b0")
    void updateStateFromStateMachine(final State state) throws ModelerModuleException;

    @objid ("8a7663ad-c072-4a7d-a3d1-80b88bea1305")
    Path computePath(final Artifact fileArtifact);

}
