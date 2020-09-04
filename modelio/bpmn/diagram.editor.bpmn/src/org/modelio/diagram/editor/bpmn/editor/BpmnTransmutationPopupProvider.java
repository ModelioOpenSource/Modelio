/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.AboutToHide;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MParameter;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.diagram.editor.bpmn.plugin.DiagramEditorBpmn;
import org.modelio.diagram.editor.bpmn.wizard.TransformerRegistry.ModelTransformerCommand;
import org.modelio.diagram.editor.bpmn.wizard.TransformerRegistry;
import org.modelio.diagram.editor.context.AbstractCreationPopupProvider;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.utils.i18n.BundledMessages;
import org.osgi.framework.Bundle;

/**
 * Implementation of {@link AbstractCreationPopupProvider} for Bpmn diagram.
 */
@objid ("6d07de5a-39f0-4f44-8e24-d63e21718edc")
public class BpmnTransmutationPopupProvider {
    @objid ("7a0fac83-0d62-4a07-be37-4c5435c0e56a")
    @Inject
    protected MApplication application;

    @objid ("c9ed585a-5cf9-4af0-8109-cfb526d82cb0")
    @Inject
    protected IEclipseContext context;

    /**
     * A local MCommand cache, to minimize navigation in the e4 model.
     */
    @objid ("ccd61890-576b-4ed4-95f6-a2c2d9129529")
    private final Map<String , MCommand> commandCache = new HashMap<>();

    @objid ("6715f0ce-b289-4d7b-a4f6-7e7e9f05e5fc")
    protected String getMenuIconPath() {
        return "platform:/plugin/org.modelio.diagram.editor/icons/uml.png";
    }

    @objid ("c0079ff8-9493-4142-95a4-f50158b6caf8")
    protected String getMenuLabel() {
        return DiagramEditorBpmn.I18N.getString("TransmuteElementMenu.label");
    }

    /**
     * Fills a dynamic creation menu with selection-compatible contributions before display. <br/>
     * Called by the rcp platform through injection.
     * 
     * @param items the item list to fill.
     */
    @objid ("68c430ea-7812-4c4d-99f5-e374c0243a59")
    @AboutToShow
    public void aboutToShow(final List<MMenuElement> items) {
        // Add the creation menu with selection-compatible commands
        final ISelection applicationSelection = getApplicationSelection();
        final List<ModelTransformerCommand> entries = TransformerRegistry.getInstance(this.context).getTransformers(getEditedDiagram(applicationSelection), applicationSelection);
        if (!entries.isEmpty()) {
            // add menu items
            items.add(createMenu(entries));
        }
    }

    @objid ("16659ef9-a5df-4b3e-8cf2-5ac225aab786")
    @AboutToHide
    public void aboutToHide(@SuppressWarnings ("unused") final List<MMenuElement> items) {
        // Here, we could dispose things and so on...
    }

    @objid ("62e2b705-c1bd-45da-97c9-a553f25faa28")
    protected AbstractDiagram getEditedDiagram(final ISelection selection) {
        final EditPart ep = SelectionHelper.getFirst(selection, EditPart.class);
        if (ep != null) {
            final Object model = ep.getModel();
            if (model instanceof GmModel) {
                return ((GmModel) model).getDiagram().getRelatedElement();
            }
        }
        return null;
    }

    /**
     * @return the currently selected elements.
     */
    @objid ("463a1bb6-255a-45d9-85d5-eda9e7dce039")
    protected ISelection getApplicationSelection() {
        // Get the active selection from the application, to avoid context-related issues when opening the same diagram several times...
        return (ISelection) this.application.getContext().get(IServiceConstants.ACTIVE_SELECTION);
    }

    /**
     * Compute a contributor id from a bundle.
     * 
     * @return a contributor id.
     */
    @objid ("45137d19-a403-49cf-b33e-d0cde257a113")
    private String getContributorId(final Bundle bundle) {
        return "platform:/plugin/" + bundle.getSymbolicName();
    }

    /**
     * Create a new handled menu item from a popup entry descriptor.
     * 
     * @param entry the descriptor to convert.
     * @return a new menu elements.
     */
    @objid ("e581fdf3-89cb-4d13-af3d-912126023fe0")
    private MHandledMenuItem createMenuItem(final ModelTransformerCommand entry, final String contributorId) {
        // create a new handled item
        final MHandledMenuItem item = MMenuFactory.INSTANCE.createHandledMenuItem();
        final MCommand command = getCommand(entry);
        item.setCommand(command);
        item.setElementId(entry.i18nKey);
        
        // compute label, tooltip and icon
        final BundledMessages i18nBundle = DiagramEditorBpmn.I18N;
        item.setLabel(i18nBundle.getString(entry.i18nKey + ".label"));
        item.setTooltip(i18nBundle.getString(entry.i18nKey + ".tooltip"));
        
        final String baseIcon = i18nBundle.getString(entry.i18nKey + ".icon");
        if (!baseIcon.contains("!")) {
            item.setIconURI(MetamodelImageService.getIconCompletePath(baseIcon));
        }
        
        // make the item visible
        item.setEnabled(true);
        item.setToBeRendered(true);
        item.setVisible(true);
        
        // bind the item to the contributing plugin
        item.setContributorURI(contributorId);
        
        final MParameter p = MCommandsFactory.INSTANCE.createParameter();
        p.setContributorURI(contributorId);
        p.setName("transformerindex");
        p.setValue(Integer.toString(entry.index));
        item.getParameters().add(p);
        return item;
    }

    /**
     * Get the MCommand defined in the application having a specific id.
     * @param commandId the element id of the MCommand to find.
     * 
     * @return a MCommand, or <code>null</code> if the id is not found.
     */
    @objid ("e5863e77-4084-4d68-a845-51daa7f87e91")
    private MCommand getCommand(final ModelTransformerCommand entry) {
        final String commandId = entry.e4CmdId;
        // Try the cache first...
        MCommand command = this.commandCache.get(commandId);
        if (command == null) {
            // Not in the cache, look into the application commands
            for (final MCommand c : this.application.getCommands()) {
                if (commandId.equals(c.getElementId())) {
                    // Match, keep it in the cache...
                    command = c;
                    this.commandCache.put(commandId, command);
                    break;
                }
            }
        }
        return command;
    }

    @objid ("fa05d584-3e7e-4747-90ca-e25ce21787e9")
    private MMenu createMenu(final List<ModelTransformerCommand> commands) {
        final String contributorId = getContributorId(DiagramEditorBpmn.getContext().getBundle());
        
        // create a new menu
        final MMenu elementCreationMenu = MMenuFactory.INSTANCE.createMenu();
        elementCreationMenu.setLabel(DiagramEditorBpmn.I18N.getString("TransformWizard.label"));
        elementCreationMenu.setTooltip(DiagramEditorBpmn.I18N.getString("TransformWizard.tooltip"));
        
        // make the menu visible
        elementCreationMenu.setEnabled(true);
        elementCreationMenu.setToBeRendered(true);
        elementCreationMenu.setVisible(true);
        
        // bound the menu to the contributing plugin
        elementCreationMenu.setContributorURI(contributorId);
        
        final List<MMenuElement> menuChildren = elementCreationMenu.getChildren();
        
        // add transform commands
        for (final ModelTransformerCommand command : commands) {
            menuChildren.add(createMenuItem(command, contributorId));
        }
        return elementCreationMenu;
    }

}
