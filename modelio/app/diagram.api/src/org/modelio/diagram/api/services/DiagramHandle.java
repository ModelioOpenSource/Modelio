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

package org.modelio.diagram.api.services;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramGraphicFactory;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.editor.DiagramCommandStack;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.DiagramEditorInputProvider;
import org.modelio.diagram.editor.IDiagramEditor;
import org.modelio.diagram.editor.handlers.ImageBuilder;
import org.modelio.diagram.editor.plugin.DiagramEditorsManager;
import org.modelio.diagram.editor.silent.SilentDiagramEditor;
import org.modelio.diagram.elements.common.freezone.ILayoutAssistant;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * A handle on the content of a Diagram, allowing interactions like navigating nodes and links, masking and unmasking elements, saving the content of the diagram into a file, etc. The static method {@link #create(IDiagramEditor)} should be use to get one
 * handle, and the handle should be {@link #close() closed} when it isn't needed anymore.
 * 
 * @since 2.0
 */
@objid ("fb247494-88ea-4dd8-a65f-e58d53a983e2")
public final class DiagramHandle implements IDiagramHandle {
    @objid ("dd8f32f2-1faf-4a90-bdc0-65bd9ba06fb1")
    private Boolean previousLayoutAssistantState;

    @objid ("65afe438-c843-473f-b461-1dd98149c9f4")
    private IDiagramGraphicFactory creationFactory;

    @objid ("02065e98-05fa-4ac7-94da-48748f7c833d")
    private DiagramEditorInput diagramEditorInput;

    @objid ("ea2f31f2-5240-4ed8-9e9e-a53bfaa40bb1")
    private IDiagramEditor editor;

    @objid ("2e2a63ec-e538-4df8-b152-1972694006c4")
    @Override
    public void close() {
        // Restore layout assistant state
        setLayoutAssistantEnabled(this.previousLayoutAssistantState);
        
        setBatchMode(false);
        this.editor.disposeHandle();
        this.diagramEditorInput = null;
    }

    /**
     * Creates and returns a DiagramHandle for the given diagram. It is the caller's responsibility to call {@link #close()} on the handle once it isn't needed anymore.
     * @param abstractDiagram the diagram model element.
     * @param manager a diagram model manager
     * @param projectService the project service
     * @param editorManager the diagram editors registry.
     * @return a diagram handle.
     */
    @objid ("392e5957-d5dd-4caf-abf2-87a798299e50")
    public static DiagramHandle create(AbstractDiagram abstractDiagram, IModelManager manager, IProjectService projectService, DiagramEditorsManager editorManager) {
        MPart editorPart = editorManager.get(abstractDiagram);
        
        if (editorPart != null) {
            return DiagramHandle.create((IDiagramEditor) editorPart.getObject(), true);
        } else {
            DiagramEditorInput input = DiagramEditorInputProvider.createEditorInput(abstractDiagram, manager);
            SilentDiagramEditor editor = new SilentDiagramEditor(input, projectService);
            return DiagramHandle.create(editor, false);
        }
    }

    /**
     * Creates and returns a DiagramHandle for the given diagram editor.
     * <p>
     * It is the caller's responsibility to call {@link #close()} on the handle once it isn't needed anymore.
     * @param editor a diagram editor.
     * @param performValidation whether or not to force a validation on the diagram's figure.
     * @return a diagram handle
     */
    @objid ("b536f45b-38b6-423c-ba07-f253a2085b5b")
    public static DiagramHandle create(final IDiagramEditor editor, boolean performValidation) {
        return new DiagramHandle(editor, editor.getEditorInput(), performValidation);
    }

    @objid ("bd5921e3-757b-44e5-9e28-89551cc10fc3")
    @Override
    public IDiagramGraphicFactory getCreationFactory() {
        return this.creationFactory;
    }

    @objid ("cc80b29d-d5d3-4c0f-be5b-01cc12308fbd")
    @Override
    public AbstractDiagram getDiagram() {
        return getDiagramEditorInput().getDiagram();
    }

    @objid ("3957aa20-22eb-473f-827f-f38a81cae7a5")
    @Override
    public List<IDiagramGraphic> getDiagramGraphics(MObject element) {
        return DGFactory.getInstance().getDiagramGraphics(this, getDiagramGraphicModels(element));
    }

    @objid ("48c7ee41-f49e-4f37-8fed-7668b7294dc8")
    @Override
    public IDiagramDG getDiagramNode() {
        IDiagramDG diagramDG = (IDiagramDG) DGFactory.getInstance().getDiagramNode(this, (GmNodeModel) getDiagramEditorInput().getGmDiagram());
        return diagramDG;
    }

    @objid ("a15e0b0a-3ec8-42c9-b030-b67a1153165d")
    @Override
    public IDiagramGraphic getDrawingGraphic(String identifier) {
        IGmDrawing gm = getDiagramEditorInput().getGmDiagram().getDrawing(identifier);
        
        if (gm != null) {
            return DGFactory.getInstance().getDiagramGraphic(this, gm);
        } else {
            return null;
        }
    }

    /**
     * Returns the edit part for the passed object.
     * @param gmObject the graphic object model
     * @return the edit part
     */
    @objid ("36a43f0b-0ba1-48cb-82ea-2f1ece213b92")
    public final GraphicalEditPart getEditPart(IGmObject gmObject) {
        GraphicalViewer viewer = this.editor.getAdapter(GraphicalViewer.class);
        return (GraphicalEditPart) viewer.getEditPartRegistry().get(gmObject);
    }

    @objid ("0e995abb-251c-4ee3-9dc0-be295615fa08")
    @Override
    public boolean isLayoutAssistantEnabled() {
        return !Boolean.FALSE.equals(getLayoutAssitantState());
    }

    @objid ("6ccbf987-3a62-4d8f-be3f-1ddf7bf58a28")
    @Override
    public void mask(final IDiagramGraphic graphic) {
        graphic.mask();
    }

    @objid ("73f12b9a-b791-4fef-9510-0474f59f00b5")
    @Override
    public void save() {
        getDiagramEditorInput().getGmDiagram().refreshAllFromObModel();
        getDiagramEditorInput().getGmDiagram().getPersister().save(true);
    }

    @objid ("0ac50eb5-b841-4c14-81e2-a213a221030f")
    @Override
    public void saveInFile(final String format, final String targetFile, final int margin) {
        int intFormat;
        
        if (format.equalsIgnoreCase("PNG")) {
            intFormat = SWT.IMAGE_PNG;
        } else if (format.equalsIgnoreCase("BMP")) {
            intFormat = SWT.IMAGE_BMP;
        } else if (format.equalsIgnoreCase("JPEG")) {
            intFormat = SWT.IMAGE_JPEG;
        } else if (format.equalsIgnoreCase("GIF")) {
            intFormat = SWT.IMAGE_GIF;
        } else {
            intFormat = SWT.IMAGE_PNG;
        }
        
        saveAsImage(this.editor.getRootEditPart(), targetFile, intFormat, margin);
    }

    @objid ("510b917d-4b3f-48c4-9df8-07bd36ec7029")
    @Override
    public void setBatchMode(boolean batchMode) {
        CommandStack stack = (this.editor.getAdapter(CommandStack.class));
        if (stack instanceof DiagramCommandStack) {
            ((DiagramCommandStack) stack).setBatchMode(batchMode);
        }
    }

    @objid ("e7782f79-589d-4be4-8ac7-bf0620cc97e6")
    @Override
    public final void setLayoutAssistantEnabled(Boolean enabled) {
        this.editor.getRootEditPart().getViewer().setProperty(ILayoutAssistant.VIEWPROP_ENABLED, enabled);
    }

    @objid ("e2db9e35-dd39-4a1e-9791-b585a3e501fa")
    @Override
    public List<IDiagramGraphic> unmask(MObject element, int x, int y) {
        return getCreationFactory().unmask(element, x, y);
    }

    @objid ("e61c1fa7-ec5e-475f-98a8-83cd1019c2e1")
    DiagramEditorInput getDiagramEditorInput() {
        if (this.diagramEditorInput == null || this.diagramEditorInput.getGmDiagram() == null) {
            throw new IllegalStateException("editor disposed");
        }
        return this.diagramEditorInput;
    }

    @objid ("f3870c49-f586-409e-a99b-6b2c7c91ed31")
    List<GmModel> getDiagramGraphicModels(final MObject element) {
        List<GmModel> ret = new ArrayList<>();
        MRef ref = new MRef(element);
        for (GmModel gm : getDiagramEditorInput().getGmDiagram().getAllGMRepresenting(ref)) {
            if ((gm instanceof GmNodeModel) && !((GmNodeModel) gm).isVisible()) {
                continue;
            }
            ret.add(gm);
        }
        return ret;
    }

    @objid ("869afd7e-eee8-40cf-87f8-cc9785c2b4c0")
    private DiagramHandle(final IDiagramEditor editor, DiagramEditorInput diagramEditorInput, boolean performValidation) {
        this.editor = editor;
        this.creationFactory = new DiagramGraphicFactory(this);
        this.diagramEditorInput = diagramEditorInput;
        
        if (performValidation) {
            // Sometimes, the eclipse editor is hidden, the diagram must be manually updated before accessing its contents
            IGmDiagram gmDiagram = this.diagramEditorInput.getGmDiagram();
            if (gmDiagram != null) {
                gmDiagram.setVisible(true);
                GraphicalEditPart editPart = getEditPart(gmDiagram);
                if (editPart != null) {
                    editPart.getFigure().getUpdateManager().performValidation();
                }
            }
        }
        
        // disable the layout assistant
        this.previousLayoutAssistantState = getLayoutAssitantState();
        setLayoutAssistantEnabled(false);
    }

    /**
     * Tells whether the layout assistant is disabled in the viewer properties.
     * <p>
     * The layout assistant may be disabled temporarily (without modifying the model) by setting {@link ILayoutAssistant#VIEWPROP_ENABLED} property id to <i>false</i>.
     * @return whether the layout assistant is disabled in the viewer properties.
     */
    @objid ("1c07a6cb-76a3-46b0-aa14-c0f2eecd2f1b")
    private Boolean getLayoutAssitantState() {
        return (Boolean) this.editor.getRootEditPart().getViewer().getProperty(ILayoutAssistant.VIEWPROP_ENABLED);
    }

    @objid ("54450799-3ffe-4b7a-a299-8bc07dbea76e")
    private void saveAsImage(final RootEditPart rootEditPart, final String location, final int format, final int margin) {
        ImageBuilder imageBuilder = new ImageBuilder(margin, format);
        Image img = imageBuilder.makeImage(rootEditPart);
        
        if (img != null) {
            try {
                ImageLoader imgLoader = new ImageLoader();
                imgLoader.data = new ImageData[] { img.getImageData() };
                imgLoader.save(location, format);
            } finally {
                img.dispose();
            }
        }
    }

    @objid ("e5e72c0a-e091-45c8-80fb-2c4b15a1064d")
    @Override
    public void refreshDynamicDecoration() {
        getDiagramEditorInput().getGmDiagram().refreshDynamicStyle();
    }

    @objid ("bc0db5e6-31da-4777-bbda-ec1c036c5980")
    private void getDiagramGraphicModels(GmNodeModel gm, MRef ref, List<GmModel> ret) {
        if (ref.equals(gm.getRepresentedRef())) {
            ret.add(gm);
        }
        
        if (gm instanceof GmCompositeNode) {
            for (GmNodeModel child : ((GmCompositeNode) gm).getVisibleChildren()) {
                getDiagramGraphicModels(child, ref, ret);
            }
        }
    }

}
