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

package org.modelio.diagram.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.ModelManager;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;

/**
 * OSGI context function dispatching the {@link DiagramEditorInput} instantiation to all {@link IDiagramEditorInputProvider} registered in the extension point. Fils all {@link DiagramEditorInput} injected references at runtime.
 */
@objid ("82a32f6b-5a57-11e2-9c97-002564c97630")
public class DiagramEditorInputProvider extends ContextFunction {
    /**
     * Id of the input provider extension point.
     */
    @objid ("be73200b-5a77-11e2-9c97-002564c97630")
    public static final String INPUTPROVIDER_ID = "org.modelio.app.diagram.editor.inputprovider";

    @objid ("be77b3eb-5a77-11e2-9c97-002564c97630")
    @Override
    public Object compute(final IEclipseContext context, final String contextKey) {
        final String diagramUID = context.get(MPart.class).getPersistedState().get("inputURI");
        final IModelManager modelManager = new ModelManager(context);
        return createEditorInput(diagramUID, modelManager);
    }

    /**
     * Create a diagram editor input, needed to open a diagram editor.
     * <p>
     * Scans the {@value #INPUTPROVIDER_ID}INPUTPROVIDER_ID extension point to find a contribution that is able to instantiate a {@link DiagramEditorInput}.
     * </p>
     * 
     * @param diagram The element to unmask
     * @param modelManager a diagram model manager
     * @return a diagram editor input for the given diagram. <code>null</code> if no contribution supports this diagram's kind.
     */
    @objid ("4c395f09-5c18-43d6-83f5-cbccb98c39f4")
    public static DiagramEditorInput createEditorInput(final AbstractDiagram diagram, final IModelManager modelManager) {
        final String diagramUID = diagram.getUuid();
        return createEditorInput(diagramUID, modelManager);
    }

    @objid ("a44bb2b0-1820-44e5-9c12-2f56073d042e")
    private static DiagramEditorInput createEditorInput(final String diagramUID, final IModelManager modelManager) {
        IDiagramEditorInputProvider lastProvider = null;
        
        for (final IConfigurationElement e : new ExtensionPointContributionManager(DiagramEditorInputProvider.INPUTPROVIDER_ID).getExtensions("inputprovider")) {
            try {
                final Object o = e.createExecutableExtension("class");
                if (o instanceof IDiagramEditorInputProvider) {
                    final IDiagramEditorInputProvider provider = (IDiagramEditorInputProvider) o;
                    if (provider.getClass().getName().contains("StaticDiagramEditorInputProvider")) {
                        // TODO 'static' editor should always be handled last, but checking the class name is kind of ugly
                        lastProvider = provider;
                    } else {
                        final DiagramEditorInput input = provider.compute(diagramUID, modelManager);
                        if (input != null) {
                            return input;
                        }
                    }
                }
            } catch (final CoreException e1) {
                DiagramEditor.LOG.error(e1);
            }
        }
        return lastProvider != null ? lastProvider.compute(diagramUID, modelManager) : null;
    }

}
