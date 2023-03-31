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
package org.modelio.diagram.elements.core.model;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.services.EContextService;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.elements.common.abstractdiagram.IDynamicStyler;
import org.modelio.diagram.elements.common.embeddeddiagram.GmEmbeddedDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.obfactory.IModelLinkFactory;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.persistence.PersistenceException;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.vcore.model.api.IModelFactoryService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram graphic model.
 * 
 * @since 3.7
 */
@objid ("1fd716fb-b642-4f17-86ea-c9eae02cf1fb")
public interface IGmDiagram extends IGmNode {
    /**
     * Add a drawing layer to the diagram.
     * @param child a drawing layer.
     */
    @objid ("e7a665bc-e792-4b08-8bdb-2daa23890435")
    void addDrawingLayer(IGmDrawingLayer child);

    /**
     * Register an embedded diagram in the diagram.
     * <p>
     * This method should be called by the object that instantiates the embedded IGmDiagram.
     * @param embeddedDiagram the embedded diagram to add.
     */
    @objid ("17fa2235-5fc2-4b0b-942d-cb53ac50628d")
    void addEmbeddedDiagram(IGmDiagram embeddedDiagram);

    /**
     * Register a graphic element in the diagram.
     * <p>
     * This method should only be called by the GmModel constructor or its read() method.
     * @param model the graphic element to add.
     */
    @objid ("347da1ee-17fe-4f0c-8f94-b4b342658037")
    void addGraphicModel(IGmObject model);

    /**
     * Register a graphic model reference in the diagram.
     * <p>
     * This method should only be called by the IGmReference constructor or its read() method.
     * @param model the IGmReference to add.
     */
    @objid ("4623c2f6-2df6-4a05-a2ae-6d23620eaf26")
    void addGraphicReference(IGmReference<?> model);

    /**
     * Add an action to execute after the diagram and its controller is loaded .
     * <p>
     * @param action the action to run.
     * @since 3.7
     */
    @objid ("773139c2-1426-4a14-a7c4-0ad4b6fb2e06")
    void addPostLoadAction(IPostLoadAction action);

    /**
     * Tells generic elements can be unmasked in this diagram.
     * @return true only if it is consistent to display the given element inside this graphic element, false in the other cases.
     * @since 3.8
     */
    @objid ("fe7f5162-2922-486d-8ef9-f8caf04c3326")
    boolean canUnmaskGenericElements();

    /**
     * Removes the model listener.
     */
    @objid ("4943a6aa-72fb-4704-9bef-1d5ac95e2f24")
    void dispose();

    /**
     * Reconfigure the current refresher for either processing or ignoring the model change events.
     * @param onOff true to process events, false to defer them.
     */
    @objid ("1757fe8a-83d9-461a-86a7-fefd6900809b")
    void enableRefresh(boolean onOff);

    /**
     * Returns all GmModel that are somehow related to the given reference in this diagram, or an empty list is none is found.
     * @param mRef a reference to a model element for which we are searching Gm.
     * @return the list of all Gm related to the passed reference, or an empty list if none is found.
     */
    @objid ("95d15377-7cc8-411a-8b15-1a2ea642382d")
    Collection<GmModel> getAllGMRelatedTo(MRef mRef);

    /**
     * Returns the list of graphic models (Gm) representing (ie: for which the getRepresentedElement() method does return the element of) the given reference in this diagram or an empty list if none is found.
     * @param representedElementRef a reference to a model element for which we are searching Gm.
     * @return the list of all Gm representing the passed reference, or an empty list if none is found.
     */
    @objid ("69ba5903-aaba-4f07-9f31-64c61a6b0fd1")
    List<GmModel> getAllGMRepresenting(MRef representedElementRef);

    /**
     * Get all links of this diagram.
     * <p>
     * The returned collection is not modifiable.
     * @return all links of this diagram.
     */
    @objid ("e092ec96-e937-4b1d-a3e7-d9fbbe518259")
    Collection<IGmLinkObject> getAllLinks();

    /**
     * Get all represented graphic models.
     * @return all graphic models.
     */
    @objid ("280014d4-c969-46e1-8c9b-849b4ada2b92")
    Collection<GmModel> getAllModels();

    /**
     * Get the background drawing layer.
     * @return the background drawing layer.
     */
    @objid ("337265ad-297d-44ad-88ca-e6390107182b")
    IGmDrawingLayer getBackgroundDrawingLayer();

    /**
     * Get the common diagram in the embedding chain of two diagrams.
     * @param sourceDiagram a diagram
     * @param targetDiagram another diagram.
     * @return the common diagram or null.
     */
    @objid ("34c3e8b2-715e-4571-9c36-c62a7334812f")
    static IGmDiagram getCommonDiagramOwner(final IGmDiagram sourceDiagram, final IGmDiagram targetDiagram) {
        if (sourceDiagram == targetDiagram) {
            return sourceDiagram;
        }
        
        ArrayDeque<IGmDiagram> sourceStack = new ArrayDeque<>(5);
        ArrayDeque<IGmDiagram> targetStack = new ArrayDeque<>(5);
        
        for (IGmDiagram d = sourceDiagram; d != null; d = d.getDiagramOwner()) {
            sourceStack.add(d);
        }
        for (IGmDiagram d = targetDiagram; d != null; d = d.getDiagramOwner()) {
            targetStack.add(d);
        }
        
        IGmDiagram a = sourceStack.pollLast();
        IGmDiagram b = targetStack.pollLast();
        IGmDiagram common = null;
        while (a == b) {
            common = a;
            a = sourceStack.pollLast();
            b = targetStack.pollLast();
        }
        return common;
    }

    /**
     * Get the actual node in which an element of the given metaclass must be unmasked.
     * @param metaclass a metaclass
     * @return a composite node.
     */
    @objid ("d985fd3e-8c23-4522-baee-f7593f8081e8")
    GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass);

    /**
     * Get the diagram which embed this diagram.
     * <p>
     * Returns null if this diagram is not embedded.
     * @return the diagram embedding this diagram or null.
     */
    @objid ("3eb23bca-3222-4c99-92a9-1116d502fbbc")
    IGmDiagram getDiagramOwner();

    /**
     * Get the drawing identified by the given string.
     * @param identifier the drawing identifier.
     * @return the found drawing or <i>null</i>.
     */
    @objid ("628136d0-9e47-454c-b240-da360d532962")
    IGmDrawing getDrawing(String identifier);

    /**
     * @return the diagram drawings
     */
    @objid ("a6eb9e61-b0f3-4050-bcf8-c8a0304d059c")
    List<IGmDrawingLayer> getDrawingLayers();

    /**
     * @return the diagram's dynamic styler. Might be <code>null</code>.
     */
    @objid ("edef45f2-9f76-4557-8431-e7d2c3709e3f")
    IDynamicStyler getDynamicStyler();

    /**
     * Get all registered embedded diagrams.
     * <p>
     * The returned collection is a copy.
     * @return all registered embedded diagrams.
     */
    @objid ("1830b787-f69e-49b2-bc30-50c9542b181f")
    Collection<IGmDiagram> getEmbeddedDiagrams();

    /**
     * Get the existing GmModel for a given element.
     * @param element any element.
     * @return null if the model element is not currently (already) unmasked and visible in the diagram
     */
    @objid ("76d1a24c-c667-463c-80f6-26587c20fc94")
    default GmModel getExistingModelFor(final MObject element) {
        final List<GmModel> allGMRepresenting = getAllGMRepresenting(new MRef(element));
        for (final GmModel gmRepresenting : allGMRepresenting) {
            if (!(gmRepresenting instanceof GmNodeModel) || ((GmNodeModel) gmRepresenting).isVisible()) {
                return gmRepresenting;
            }
        }
        return null;
    }

    /**
     * @return the identifier of the diagram, needed to initialize Gm and EditPart factories.
     */
    @objid ("e3a82760-2340-44ed-a6a7-39d642135c72")
    String getFactoryIdentifier();

    /**
     * Get the factory used to unmask relationship model elements such as Associations, ElementImports or Dependencies.
     * @return The graphic link factory.
     */
    @objid ("cd7d3a9e-71a1-4b3c-9b61-0052e179a10f")
    IGmLinkFactory getGmLinkFactory();

    /**
     * Get the factory used to unmask node model elements.
     * @return The graphic node factory
     */
    @objid ("421fe33b-6edc-4e7d-999a-d84b38dd9c82")
    IGmNodeFactory getGmNodeFactory();

    /**
     * Get the model manager storing the session and model factory.
     * @return the model manager.
     */
    @objid ("bbf440a0-d182-428d-85d8-b88b58fd76a0")
    IModelManager getModelManager();

    /**
     * Get the node containing this diagram if it is an embedded diagram.
     * <p>
     * Returns <i>null</i> for root diagrams.
     * @return the node containing this diagram if any, else <i>null</i>.
     */
    @objid ("870c9a26-752e-4255-87a0-6399cb0c03e7")
    IGmNode getParentNode();

    //    /**
    //     * @return the diagram's persistence service
    //     */
    //    @objid ("a4e4aee7-cd47-445c-b7a7-0df88ca0bde6")
    //    IDiagramPersister getPersister();
    @objid ("554a81d6-2b59-4a72-bd2f-90f536e914f1")
    @Override
    AbstractDiagram getRelatedElement();

    /**
     * Get the root diagram in the embedding chain.
     * @param gmDiagram a diagram.
     * @return the root diagram.
     */
    @objid ("c7ae2f32-eef6-47fb-9f08-7a62636c23d8")
    static IGmDiagram getRoot(IGmDiagram gmDiagram) {
        final int LIMIT = 100;
        
        if (gmDiagram == null) {
            return null;
        }
        
        IGmDiagram ret = gmDiagram;
        IGmDiagram owner = gmDiagram.getDiagramOwner();
        int watchdog = 0;
        while (owner != null && watchdog < LIMIT) {
            ret = owner;
            owner = ret.getDiagramOwner();
            watchdog++;
        }
        
        if (watchdog >= LIMIT) {
            throw new IllegalArgumentException(String.format("Cycle in diagram composition chain of %s", gmDiagram));
        }
        return ret;
    }

    /**
     * Tells whether the diagram model is disposed.
     * <p>
     * A disposed diagram model won't react to model modifications and shouldn't be used anymore.
     * @return <code>true</code> if the diagram model is disposed, else <code>false</code>.
     */
    @objid ("5ad70792-43ba-4a49-9d49-71052e3f6e0c")
    boolean isDisposed();

    /**
     * Load the diagram from the model.
     * @throws PersistenceException on failure
     */
    @objid ("90ddf05b-9e1d-47b9-add1-34dc2928981a")
    void load() throws PersistenceException;

    /**
     * Force refresh of the whole diagram.
     */
    @objid ("d30f6e70-ad5b-4e4f-9b76-84e946e154ba")
    void refreshAllFromObModel();

    /**
     * Refresh all {@link IGmReference} registered with {@link #addGraphicReference(IGmReference)}.
     */
    @objid ("081ea885-9a06-4abe-a04e-976a5dc88e7f")
    void refreshAllGmReferences();

    /**
     * Make sure dynamic styler is applied.
     */
    @objid ("7f358c5c-871c-4845-b67d-6c72f7a9120a")
    void refreshDynamicStyle();

    /**
     * Remove a embedded diagram from the diagram.
     * <p>
     * Diagram implementers should never call this method, it is to be called only by {@link GmEmbeddedDiagram}.
     * </p>
     * @param embeddedDiagram the graphic element to remove.
     */
    @objid ("db0a8f3b-bac6-4e74-8b2a-b02c8d1400c2")
    void removeEmbeddedDiagram(IGmDiagram embeddedDiagram);

    /**
     * Remove a graphic model from the diagram.
     * <p>
     * Diagram implementers should never call this method, it is to be called only by {@link GmModel#delete()}.
     * @param model the graphic element to remove.
     */
    @objid ("aaa8dff3-412a-41e1-a45d-56472ff82df8")
    void removeGraphicModel(IGmObject model);

    /**
     * Remove a gm reference from the diagram.
     * <p>
     * Diagram implementers should never call this method.
     * </p>
     * @param model the graphic element to remove.
     */
    @objid ("817cce98-77c3-41be-9d07-81afcf99227e")
    void removeGraphicReference(IGmReference<?> model);

    /**
     * Remove a drawing layer
     * @param gmDrawingLayer a drawing layer
     */
    @objid ("aea44b04-4aaf-43ff-ad27-59ce0081b233")
    void removeLayer(IGmDrawingLayer gmDrawingLayer);

    /**
     * Save the diagram in the model.
     * @param withEmbeddeddiagrams if true, modifiable embedded diagrams will be saved too.
     * @throws PersistenceException on failure
     */
    @objid ("22b64041-8219-4e7a-9472-686c01728670")
    void save(boolean withEmbeddeddiagrams) throws PersistenceException;

    /**
     * Initialize a dynamic styler on the diagram.
     * @param dynamicStyler a dynamic styler.
     */
    @objid ("cc58a823-695c-4410-b461-da52a89f86fc")
    void setDynamicStyler(IDynamicStyler dynamicStyler);

    /**
     * Sets whether the node is visible or not and fire listeners.
     * @see GmNodeModel#setVisible(boolean)
     * @param b whether or not the gm should be visible.
     */
    @objid ("c9958f76-7b94-40e1-badc-ca6e58746c23")
    void setVisible(boolean b);

    /**
     * Unmask the given model element as a node inside the given graphic node.
     * @param parentNode The parent graphic node that will contain the element
     * @param newElement The element to unmask
     * @param initialLayoutData The initial layout data of the unmasked element.<br>
     * The specified initial layout data are taken as a hint, they may be taken into account or not.
     * @return The unmasked node, or <code>null</code> if the newElement can't be unmasked.
     */
    @objid ("f7485217-93de-43d4-b0f3-84de5846b4cf")
    GmNodeModel unmask(GmCompositeNode parentNode, MObject newElement, Object initialLayoutData);

    /**
     * Unmask the given model element as a node as a direct child of the diagram .
     * <p>
     * FIXME : this method exists only to workaround that a diagram GM is actually a GmCompositeNode but we don't have an interface for composite nodes.
     * @param newElement The element to unmask
     * @param initialLayoutData The initial layout data of the unmasked element.<br>
     * The specified initial layout data are taken as a hint, they may be taken into account or not.
     * @return The unmasked node, or <code>null</code> if the newElement can't be unmasked.
     */
    @objid ("b3c18c41-940a-4c91-a8c9-81b8ad3d95e6")
    GmNodeModel unmaskAsChild(MObject newElement, Object initialLayoutData);

    /**
     * Unmask the given link element in the diagram.
     * <p>
     * @param createdLinkElement The link to unmask
     * @param fromNode The source node
     * @param toNode The destination node
     * @param initialLayoutData The initial layout data of the unmasked element.<br>
     * @return the link graphic element or <i>null</i> if one of the 2 extremities cannot be unmasked..
     */
    @objid ("f57970a8-73a0-4180-8777-002e3c8c37de")
    IGmLink unmaskLink(MObject createdLinkElement, final IGmLinkable fromNode, final IGmLinkable toNode, Object initialLayoutData);

    /**
     * Creates a GmLink for the given element. Link is not initialized (ie: it have no source node, no destination node and no layout data).
     * @param linkElement the element for which to create a GmLink
     * @return the uninitialized GmLink for the element, or <code>null</code>.
     */
    @objid ("7dfcd90b-46ec-465a-8d33-14a48921ba8f")
    IGmLink unmaskLink(MObject linkElement);

    /**
     * Makes the link between the Gm model and the Ob model.
     * 
     * @author cma
     * @since 3.7
     */
    @objid ("14b54765-50b9-4f6c-b25a-e36eb71f6a76")
    interface IModelManager {
        /**
         * Get the diagram project metamodel.
         * @return the project metamodel.
         */
        @objid ("14cbcaba-2804-41af-9858-3d7cb1bfd440")
        default MMetamodel getMetamodel() {
            return getModelingSession().getMetamodel();
        }

        /**
         * Returns the service associated with the given class.
         * @param clazz the class that needs to be found in the context
         * @return an object corresponding to the given class, or <code>null</code>
         */
        @objid ("0a6ce9e1-7806-4cf2-9e3d-b6bef530213f")
        <T> T getService(Class<T> clazz);

        /**
         * @return the Modelio activation service.
         */
        @objid ("0bf9dc69-9369-46c1-bf39-e7d8c28aed31")
        IActivationService getActivationService();

        /**
         * Resolve an MRef.
         * @param <E> the type of the element, makes an automatic cast.
         * @param ref The reference to resolve
         * @return the found MObject or <i>null</i> if the element is not present in the project.
         */
        @objid ("e9db988f-b597-4d52-989b-b1490082e403")
        <E extends MObject> E resolveRef(MRef ref);

        /**
         * Get the model factory used to create a model object in the same repository as the given object.
         * @return a model factory.
         */
        @objid ("c0333ed2-562e-4a22-a2eb-c2a9f3603daf")
        IModelFactoryService getModelFactory();

        /**
         * Get the project directory path.
         * @return the project directory path.
         */
        @objid ("6bcdc6f1-8fee-473e-b850-0ca13723f59e")
        Path getProjectPath();

        /**
         * @return the Modelio navigation service
         */
        @objid ("5e468ee1-9062-4ea3-a311-eb8434f2fb9a")
        IModelioNavigationService getNavigationService();

        /**
         * Get the Ob link factory.
         * @return the link factory.
         */
        @objid ("ca7a3251-3551-47ca-a2a1-943303b53363")
        IModelLinkFactory getModelLinkFactory();

        /**
         * @return the E4 context service.
         */
        @objid ("6c61a097-8cbc-4fb0-8a77-a71d4dedf86c")
        EContextService getContextService();

        /**
         * Get the MDA expert to ask for example whether a stereotyped element can be put under an element.
         * @return the MDA expert.
         * @since Modelio 3.4
         */
        @objid ("bc5bcde5-8b3b-4f11-aa4d-35466bcb817b")
        IMdaExpert getMdaExpert();

        /**
         * Get the Modelio model services.
         * @return the model services.
         */
        @objid ("c9b92b9c-89dc-4fea-9b5a-c6093572cb0d")
        IMModelServices getModelServices();

        /**
         * Get the modeling session.
         * @return the modeling session.
         */
        @objid ("79b35881-7604-4721-936a-f54b06f7a432")
        ICoreSession getModelingSession();
}
    
}

