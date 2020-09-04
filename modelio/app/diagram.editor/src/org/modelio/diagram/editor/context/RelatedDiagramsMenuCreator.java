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

package org.modelio.diagram.editor.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MParameter;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuSeparator;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.facilities.RelatedDiagramHelper;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * Dynamically fills the "related diagrams" e4 menu in diagrams.
 */
@objid ("95357df5-2744-4db4-80b5-2b6ab4372fa8")
public class RelatedDiagramsMenuCreator {
    @objid ("6966de7e-c201-4519-b39c-0480dd088831")
    private static final String CONTRIBUTOR_ID = "platform:/plugin/" + DiagramEditor.PLUGIN_ID;

    @objid ("1e4df3dd-6b6a-4407-a61c-b233f2e20f40")
    @Inject
    protected MApplication application;

    @objid ("7d2a7cc3-0159-4dae-872d-2c2d24daac1a")
    @AboutToShow
    public void aboutToShow(final List<MMenuElement> items) {
        // Get current selection
        final ModelElement selectedElement = getSelectedElement();
        if (selectedElement != null) {
            // Get diagrams to display
            final Collection<AbstractDiagram> related = RelatedDiagramHelper.getRelatedDiagrams(selectedElement);
            final Collection<AbstractDiagram> displaying = new ArrayList<>(selectedElement.getDiagramElement());
        
            // Remove the current diagram from the lists
            final AbstractDiagram currentDiagram = getCurrentDiagram();
            related.remove(currentDiagram);
            displaying.remove(currentDiagram);
        
            if (related.isEmpty() && displaying.isEmpty()) {
                // No diagrams to relate to
                return;
            }
        
            // Remove duplicates
            displaying.removeAll(related);
        
            items.add(createSeparator(CONTRIBUTOR_ID));
        
            // Add the related diagrams menu
            items.add(createMenu(related, displaying));
        }
    }

    @objid ("fb7d1e18-ca47-4080-ba87-7bbd74288700")
    public MMenu createMenu(final Collection<AbstractDiagram> relatedDiagrams, final Collection<AbstractDiagram> displaying) {
        // create a new menu
        final MMenu elementCreationMenu = MMenuFactory.INSTANCE.createMenu();
        elementCreationMenu.setLabel(DiagramEditor.I18N.getString("RelatedDiagramsMenu.label"));
        elementCreationMenu.setIconURI(CONTRIBUTOR_ID + "/icons/relateddiagram.png");
        
        // make the menu visible
        elementCreationMenu.setEnabled(true);
        elementCreationMenu.setToBeRendered(true);
        elementCreationMenu.setVisible(true);
        
        // bound the menu to the contributing plugin
        elementCreationMenu.setContributorURI(CONTRIBUTOR_ID);
        
        final List<MMenuElement> menuChildren = elementCreationMenu.getChildren();
        
        // add related diagram items
        if (!relatedDiagrams.isEmpty()) {
            createDiagramItems(menuChildren, relatedDiagrams);
        }
        
        // add diagrams displaying element
        if (!displaying.isEmpty()) {
            // add separator
            menuChildren.add(createSeparator(CONTRIBUTOR_ID));
        
            // add displaying diagrams
            createDiagramItems(menuChildren, displaying);
        }
        return elementCreationMenu;
    }

    @objid ("c97151d6-21b1-41f1-b067-10a84eb289fe")
    private void createDiagramItems(final Collection<MMenuElement> menuChildren, final Collection<AbstractDiagram> relatedDiagrams) {
        for (final AbstractDiagram diagram : relatedDiagrams) {
            menuChildren.add(createDiagramItems(diagram));
        }
    }

    @objid ("ced07e16-3c49-427e-a131-7ff7707c5674")
    private MMenuElement createDiagramItems(final AbstractDiagram diagram) {
        // create a new handled item
        final MHandledMenuItem relatedDiagramItem = MMenuFactory.INSTANCE.createHandledMenuItem();
        relatedDiagramItem.setLabel(diagram.getName());
        relatedDiagramItem.setIconURI(MetamodelImageService.getIconCompletePath(diagram.getMClass()));
        
        // make the menu visible
        relatedDiagramItem.setEnabled(true);
        relatedDiagramItem.setToBeRendered(true);
        relatedDiagramItem.setVisible(true);
        
        // bound the menu to the contributing plugin
        relatedDiagramItem.setContributorURI(CONTRIBUTOR_ID);
        
        // set the command
        final MCommand command = getCommand("org.modelio.app.ui.command.openrelateddiagram");
        relatedDiagramItem.setCommand(command);
        
        // add the opened diagram as parameter
        final MParameter p = MCommandsFactory.INSTANCE.createParameter();
        p.setContributorURI(CONTRIBUTOR_ID);
        p.setName("org.modelio.app.ui.command.parameter.related_diagram");
        p.setValue(diagram.getUuid());
        relatedDiagramItem.getParameters().add(p);
        return relatedDiagramItem;
    }

    @objid ("51aba02e-59b7-4b5e-9779-e59a5dade8f9")
    private MCommand getCommand(final String commandId) {
        for (final MCommand c : this.application.getCommands()) {
            if (commandId.equals(c.getElementId())) {
                return c;
            }
        }
        return null;
    }

    /**
     * Get the currently selected element, or <code>null</code> if the selection size is not equal to one.
     * 
     * @return the selected element.
     */
    @objid ("3c32cacb-f1eb-4709-acea-c4959a571faf")
    protected ModelElement getSelectedElement() {
        // Get the active selection from the application, to avoid context-related issues when opening the same diagram several times...
        final IStructuredSelection selection = getSelection();
        if (selection == null || selection.size() != 1) {
            return null;
        }
        return SelectionHelper.getFirst(selection, ModelElement.class);
    }

    @objid ("a9e0084c-62b9-4f09-8e32-7f9032347d73")
    protected IStructuredSelection getSelection() {
        final IStructuredSelection selection = (IStructuredSelection) this.application.getContext().get(IServiceConstants.ACTIVE_SELECTION);
        return selection;
    }

    /**
     * Get the edited diagram from the selection if the selection is in the diagram.
     * 
     * @return the edited diagram or null.
     */
    @objid ("164d12f7-40a7-48e5-ba83-218466c41c59")
    private AbstractDiagram getCurrentDiagram() {
        final IStructuredSelection sel = getSelection();
        final Object obj = sel.getFirstElement();
        
        if (obj instanceof EditPart) {
            final EditPart ep = (EditPart) obj;
            final Object m = ep.getModel();
            if (m instanceof IGmObject) {
                final IGmObject gm = (IGmObject) m;
                return gm.getDiagram().getRelatedElement();
            }
        }
        return null;
    }

    @objid ("7c28b54e-c5c7-471d-b3ba-6cc9c298c337")
    private MMenuSeparator createSeparator(final String CONTRIBUTOR_ID) {
        final MMenuSeparator sep = MMenuFactory.INSTANCE.createMenuSeparator();
        sep.setContributorURI(CONTRIBUTOR_ID);
        sep.setToBeRendered(true);
        sep.setVisible(true);
        return sep;
    }

}
