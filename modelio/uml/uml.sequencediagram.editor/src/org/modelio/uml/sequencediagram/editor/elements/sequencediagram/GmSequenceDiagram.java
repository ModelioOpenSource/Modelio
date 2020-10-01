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

package org.modelio.uml.sequencediagram.editor.elements.sequencediagram;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.common.abstractdiagram.IDiagramRefresher;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.PersistenceException;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.mmextensions.standard.facilities.interaction.MessageSequencer;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.uml.sequencediagram.editor.plugin.DiagramEditorSequence;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a SequenceDiagram.
 */
@objid ("d972ecd6-55b6-11e2-877f-002564c97630")
public class GmSequenceDiagram extends GmAbstractDiagram {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d972ecde-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d972ece1-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("29786e60-eab3-46ff-a2be-58afa093fff4")
    private static GmSequenceDiagramStyleKeys STYLEKEYS = new GmSequenceDiagramStyleKeys();

    @objid ("734cb902-9939-41e9-9f29-d2039d03cd0f")
    private SequenceDiagram obDiagram;

    /**
     * Default constructor.
     * 
     * @param manager the model manager for this diagram.
     * @param theSequenceDiagram the represented sequence diagram.
     * @param diagramRef a reference to the represented diagram.
     */
    @objid ("d9747339-55b6-11e2-877f-002564c97630")
    public GmSequenceDiagram(IModelManager manager, SequenceDiagram theSequenceDiagram, MRef diagramRef) {
        super(manager, diagramRef);
        this.obDiagram = theSequenceDiagram;
    }

    @objid ("d9747345-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // Allow creation of lifeline, gate, interaction use and combined
        // fragment
        return Lifeline.class.isAssignableFrom(type)
                || Gate.class.isAssignableFrom(type)
                || InteractionUse.class.isAssignableFrom(type)
                || CombinedFragment.class.isAssignableFrom(type)
                || Note.class.isAssignableFrom(type)
                || Constraint.class.isAssignableFrom(type)
                || Document.class.isAssignableFrom(type);
    }

    @objid ("d974734d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean doCanUnmask(MObject el) {
        if (canCreate(el.getClass())) {
            return true;
        }
        
        if (el instanceof InteractionFragment) {
            final InteractionFragment fragment = (InteractionFragment) el;
            final Interaction interaction = fragment.getEnclosingInteraction();
            return interaction != null && interaction.equals(this.obDiagram.getOrigin());
        }
        return false;
    }

    @objid ("d9747355-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        } else {
            return null;
        }
    }

    @objid ("d974735e-55b6-11e2-877f-002564c97630")
    @Override
    public SequenceDiagram getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("d9747365-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("d974736c-55b6-11e2-877f-002564c97630")
    @Override
    public SequenceDiagram getRepresentedElement() {
        return this.obDiagram;
    }

    @objid ("d9747373-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmSequenceDiagram.STYLEKEYS.getStyleKey(metakey);
    }

    @objid ("d974737d-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmSequenceDiagram.STYLEKEYS.getStyleKeys();
    }

    @objid ("d975f9dd-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmSequenceDiagram.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
    }

    @objid ("d975f9e3-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Eliminate children that no longer belong here.
        super.refreshFromObModel();
        
        Interaction interaction = getRelatedInteraction();
        if (interaction != null && interaction.isValid()) {
            // Auto-unmask lifelines, CombinedFragments and InteractionUse.
            boolean forceUpdate = false;
        
            for (Lifeline lifeline : interaction.getOwnedLine()) {
                if (getChild(new MRef(lifeline)) == null) {
                    unmask(this, lifeline, null);
                    forceUpdate = true;
                }
            }
        
            for (InteractionUse interactionUse : interaction.getFragment(InteractionUse.class)) {
                if (getChild(new MRef(interactionUse)) == null) {
                    unmask(this, interactionUse, null);
                    forceUpdate = true;
                }
            }
        
            for (CombinedFragment combinedFragment : interaction.getFragment(CombinedFragment.class)) {
                if (getChild(new MRef(combinedFragment)) == null) {
                    unmask(this, combinedFragment, null);
                    forceUpdate = true;
                }
            }
        
            for (Gate gate : interaction.getFormalGate()) {
                if (gate.getOwnerUse() == null && getChild(new MRef(gate)) == null) {
                    unmask(this, gate, null);
                    forceUpdate = true;
                }
            }
            if (forceUpdate) {
                refreshAllFromObModel();
            }
        }
    }

    @objid ("d975f9e6-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmSequenceDiagram.", GmSequenceDiagram.MINOR_VERSION);
    }

    @objid ("d975f9ec-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.obDiagram = (SequenceDiagram) resolveRef(getRepresentedRef());
    }

    @objid ("d975f9f1-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmSequenceDiagram.MAJOR_VERSION;
    }

    @objid ("7e70498b-5426-4f5c-84c4-d719eaebe9e2")
    @Override
    protected IDiagramRefresher createVisibleDiagramRefresher() {
        return new SequenceDiagramRefresher(this, super.createVisibleDiagramRefresher());
    }

    @objid ("2c3ed835-2200-4e61-ab3a-01cd80010fc4")
    public Interaction getRelatedInteraction() {
        return (Interaction) getRelatedElement().getOrigin();
    }

    @objid ("1649ed5e-68db-4312-9dd3-20715acb69df")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return new SequenceDiagramSymbolViewModelProvider().create(getPersistedStyle(), this);
    }

    @objid ("93a69bd9-4186-427d-874f-809adb57f4fa")
    @Override
    public String getFactoryIdentifier() {
        return SequenceDiagram.MNAME;
    }

    @objid ("9bd7950a-2986-4710-878b-243f4cc4e38e")
    @Override
    public boolean canUnmaskGenericElements() {
        return false;
    }

    /**
     * Updates the Graphic Model from the Ob model.
     */
    @objid ("d975f9f6-55b6-11e2-877f-002564c97630")
    private static class SequenceDiagramRefresher implements IDiagramRefresher {
        @objid ("65279281-f6a9-4693-8d77-1e4cde56e0da")
        private GmSequenceDiagram gmSequenceDiagram;

        @objid ("9db7c3b8-fffe-4939-be93-d4e75603f8e9")
        private IDiagramRefresher originalHandler;

        @objid ("d975f9fa-55b6-11e2-877f-002564c97630")
        @Override
        public void updateView(IModelChangeEvent event) {
            final AbstractDiagram diagram = this.gmSequenceDiagram.getRelatedElement();
            // Refresh the diagram in the display thread
            Display.getDefault().syncExec(() -> {
                if (!diagram.isValid() || this.gmSequenceDiagram.isDisposed() || !diagram.getStatus().isModifiable()) {
                    // The diagram has been deleted, do nothing.
                    // Another listener will close the view.
                    return;
                } else if (diagram.getUiDataVersion() == this.gmSequenceDiagram.lastSavedUiDataVersion) {
            
                    refreshAllDiagram();
                }
            });
        }

        /**
         * Reload the diagram if it has been modified outside.
         * 
         * @param event The change event.
         */
        @objid ("d975fa03-55b6-11e2-877f-002564c97630")
        @SuppressWarnings ("synthetic-access")
        @Override
        public void modelChanged(IModelChangeEvent event) {
            final AbstractDiagram diagram = this.gmSequenceDiagram.getRelatedElement();
            
            if (!diagram.isValid() || this.gmSequenceDiagram.isDisposed()) {
                // The diagram has been deleted, do nothing.
                // Another listener will close the view.
                return;
            } else if (diagram.getUiDataVersion() != this.gmSequenceDiagram.lastSavedUiDataVersion) {
                // Schedule a diagram reload
                Display.getDefault().asyncExec(() -> {
                    if (!this.gmSequenceDiagram.isDisposed()) {
                        try {
                            this.gmSequenceDiagram.load();
                        } catch (PersistenceException pe) {
                            // Failed to read string, log error.
                            DiagramEditorSequence.LOG.error(DiagramEditorSequence.PLUGIN_ID, pe);
                            // FIXME correct handling of failed reading.
                            assert (false) : "Failed to load diagram!";
                        }
                    }
                });
            
            }
        }

        @objid ("d975fa0d-55b6-11e2-877f-002564c97630")
        protected void refreshAllDiagram() {
            // Update Messages sequence number
            new MessageSequencer(this.gmSequenceDiagram.getRelatedInteraction()).updateModel();
            
            // Refresh diagram
            final Collection<GmModel> toRefresh = this.gmSequenceDiagram.getAllModels();
            
            for (GmModel model : toRefresh) {
                MObject el = model.getRelatedElement();
                if (el != null && !el.isValid()) {
                    model.delete();
                } else if (model.isValid()) {
                    model.obElementsUpdated();
                }
            }
            
            // Save the refreshed diagram
            this.gmSequenceDiagram.save(false);
        }

        @objid ("443a0a9d-e2b8-4a21-9f67-acf426b7e99a")
        public SequenceDiagramRefresher(GmSequenceDiagram gmSequenceDiagram, IDiagramRefresher originalHandler) {
            this.gmSequenceDiagram = gmSequenceDiagram;
            this.originalHandler = originalHandler;
        }

        @objid ("10e43b29-5140-41d1-9a8a-ccc19e93cd83")
        @Override
        public void visibilityChanged(boolean visible) {
            this.originalHandler.visibilityChanged(visible);
        }

    }

}
