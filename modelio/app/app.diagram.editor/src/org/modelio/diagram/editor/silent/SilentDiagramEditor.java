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
package org.modelio.diagram.editor.silent;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.tools.SelectionTool;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.editor.DiagramEditorInput;
import org.modelio.diagram.editor.IDiagramEditor;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.editor.tools.PanSelectionTool;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.IDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.abstractdiagram.InfraDiagramElementDropEditPolicyExtension;
import org.modelio.diagram.elements.common.root.ScalableFreeformRootEditPart2;
import org.modelio.diagram.elements.core.link.ConnectionRoutingServices;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.factories.StandardEditPartFactory;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * Quite the same as an {@link AbstractDiagramEditor}, only with nothing visible.
 * 
 * @author fpoyer
 */
@objid ("66995c53-33f7-11e2-95fe-001ec947c8cc")
public class SilentDiagramEditor implements IDiagramEditor {
    @objid ("0a8adc09-0fb0-40dd-9716-8f16ab6a4ad8")
    private static final String DROPPOLICYEXTENSION_ID = "org.modelio.app.diagram.editor.droppolicy.extensions";

    @objid ("2e5aa8a1-d914-402f-b6f6-79bec22b8b3d")
    private final RootEditPart rootEditPart = new ScalableFreeformRootEditPart2();

    @objid ("661973e9-e9ab-45e3-9994-27aa8f6f8a66")
    private GraphicalViewer graphicalViewer;

    @objid ("c4c77120-f36d-456d-b5d2-eaafaa7ff1c1")
    private EditDomain editDomain;

    @objid ("66995c59-33f7-11e2-95fe-001ec947c8cc")
    private DiagramEditorInput input = null;

    @objid ("2c847454-3a3d-11e2-a430-001ec947c8cc")
    private final IProjectService projectService;

    /**
     * C'tor.
     * @param input the editor input to use as data source.
     * @param projectService a project service
     */
    @objid ("66995c5a-33f7-11e2-95fe-001ec947c8cc")
    public  SilentDiagramEditor(final DiagramEditorInput input, final IProjectService projectService) {
        this.projectService = projectService;
        setEditDomain(new EditDomain());
        this.input = input;
        
        createGraphicalViewer();
        
    }

    @objid ("66995c5f-33f7-11e2-95fe-001ec947c8cc")
    private void configureGraphicalViewer() {
        final GraphicalViewer viewer = getGraphicalViewer();
        
        // Set the root edit part
        viewer.setRootEditPart(this.rootEditPart);
        viewer.setEditPartFactory(createEditPartFactory());
        
        // Configure the edit domain
        // Set the active and default tool
        final SelectionTool selectionTool = new PanSelectionTool();
        getEditDomain().setActiveTool(selectionTool);
        getEditDomain().setDefaultTool(selectionTool);
        
        viewer.setEditDomain(getEditDomain());
        
        // Plug our own command stack that is bound to the Modelio transaction
        // manager
        getEditDomain().setCommandStack(new DiagramCommandStack(getModelingSession().getTransactionSupport()));
        
    }

    @objid ("66995c65-33f7-11e2-95fe-001ec947c8cc")
    private void createGraphicalViewer() {
        final GraphicalViewer viewer = new NoControlGraphicalViewer();
        viewer.createControl(null);
        setGraphicalViewer(viewer);
        configureGraphicalViewer();
        initializeGraphicalViewer();
        
    }

    /**
     * Notifies the editor that the handle opened on it has been closed. Depending on its nature, the editor might decide to delete itself, release some resources, or ignore this event altogether.
     */
    @objid ("66995c67-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void disposeHandle() {
        // Dispose the editor input
        final DiagramEditorInput input = getEditorInput();
        if (input != null) {
            input.dispose();
        }
        
        getEditDomain().setActiveTool(null);
        
    }

    @objid ("66995c6b-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public <T> T getAdapter(Class<T> type) {
        if (type == ZoomManager.class) {
            return type.cast(((ScalableFreeformRootEditPart) getGraphicalViewer().getRootEditPart()).getZoomManager());
        }
        if (type == GraphicalViewer.class) {
            return type.cast(getGraphicalViewer());
        }
        if (type == CommandStack.class) {
            return type.cast(getCommandStack());
        }
        if (type == EditPart.class && getGraphicalViewer() != null) {
            return type.cast(getGraphicalViewer().getRootEditPart());
        }
        if (type == IFigure.class && getGraphicalViewer() != null) {
            return type.cast(((GraphicalEditPart) getGraphicalViewer().getRootEditPart()).getFigure());
        }
        /**
         * This implementation of the method declared by <code>IAdaptable</code> passes the request along to the platform's adapter manager; roughly <code>Platform.getAdapterManager().getAdapter(this, adapter)</code>.
         */
        return Platform.getAdapterManager().getAdapter(this, type);
    }

    @objid ("66995c72-33f7-11e2-95fe-001ec947c8cc")
    private CommandStack getCommandStack() {
        return getEditDomain().getCommandStack();
    }

    @objid ("66995c76-33f7-11e2-95fe-001ec947c8cc")
    private EditDomain getEditDomain() {
        return this.editDomain;
    }

    @objid ("66995c7a-33f7-11e2-95fe-001ec947c8cc")
    private GraphicalViewer getGraphicalViewer() {
        return this.graphicalViewer;
    }

    @objid ("66995c7e-33f7-11e2-95fe-001ec947c8cc")
    private void initializeGraphicalViewer() {
        final GraphicalViewer viewer = getGraphicalViewer();
        
        viewer.setProperty(DiagramElementDropEditPolicy.DROP_EXTENSIONS, loadDropExtensions());
        
        // Initialize connection routers
        viewer.setProperty(ConnectionRoutingServices.ID, initializeConnectionRoutingServices());
        
        // Set the viewer content
        final IGmDiagram gmDiagram = getEditorInput().getGmDiagram();
        viewer.setContents(gmDiagram);
        // Force a complete refresh now that edit parts are finally listening to events that might
        // be sent by the model (e.g.: links that have changed source and/or target while diagram
        // was closed).
        gmDiagram.refreshAllFromObModel();
        
        viewer.flush();
        
    }

    /**
     * Initialize connection routing services:
     * <ul>
     * <li>routers</li>
     * <li>connection helper factory</li>
     * </ul>
     */
    @objid ("332eba48-4b24-401f-b6c9-bd441bf87787")
    protected ConnectionRoutingServices initializeConnectionRoutingServices() {
        // No specialization for the Silent editor, deal with BPMN routers being different right here
        final String mcName = this.input.getDiagram().getMClass().getName();
        if (mcName.startsWith("Bpmn") ||
                mcName.equals("ArchimateView") ||
                //mcName.equals("ViewPointDiagram") ||
                mcName.equals("StateMachineDiagram")) {
            return ConnectionRoutingServices.builder().withAutoOrthogonalDefaults().build();
        } else {
            return ConnectionRoutingServices.builder().withLegacyDefaults().build();
        }
        
    }

    /**
     * Return the root edit part of this editor.
     * @return the root edit part of this editor.
     */
    @objid ("66995c80-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public RootEditPart getRootEditPart() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.rootEditPart;
    }

    @objid ("669bbe97-33f7-11e2-95fe-001ec947c8cc")
    private void setEditDomain(final EditDomain ed) {
        this.editDomain = ed;
    }

    @objid ("669bbe9b-33f7-11e2-95fe-001ec947c8cc")
    private void setGraphicalViewer(final GraphicalViewer viewer) {
        getEditDomain().addViewer(viewer);
        this.graphicalViewer = viewer;
        
    }

    /**
     * Returns the input for this editor.
     * @return the editor input
     */
    @objid ("669bbe9f-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public DiagramEditorInput getEditorInput() {
        return this.input;
    }

    @objid ("2c847450-3a3d-11e2-a430-001ec947c8cc")
    private final ICoreSession getModelingSession() {
        return this.projectService.getSession();
    }

    @objid ("8d672526-367c-4b87-b27c-b4e847bda1de")
    private List<IDiagramElementDropEditPolicyExtension> loadDropExtensions() {
        final List<IDiagramElementDropEditPolicyExtension> ret = new ArrayList<>();
        // Infra contribution is ALWAYS registered
        ret.add(new InfraDiagramElementDropEditPolicyExtension());
        
        for (final IConfigurationElement dropExtensionElement : new ExtensionPointContributionManager(SilentDiagramEditor.DROPPOLICYEXTENSION_ID).getExtensions("droppolicyextension")) {
            for (final IConfigurationElement scope : dropExtensionElement.getChildren("scope")) {
                final String editorId = scope.getAttribute("editorId");
                if (editorId.equals(this.input.getEditorId())) {
                    // TODO handle metaclass & stereotype
                    try {
                        final Object o = dropExtensionElement.createExecutableExtension("class");
                        if (o instanceof IDiagramElementDropEditPolicyExtension) {
                            ret.add((IDiagramElementDropEditPolicyExtension) o);
                        }
                    } catch (final CoreException e1) {
                        DiagramEditor.LOG.error(e1);
                    }
        
                } // else skip it
            }
        }
        return ret;
    }

    @objid ("a886be59-e3ae-4ecd-b08a-76057ba6d760")
    protected final StandardEditPartFactory createEditPartFactory() {
        return new StandardEditPartFactory(this.input.getGmDiagram().getFactoryIdentifier());
    }

}
