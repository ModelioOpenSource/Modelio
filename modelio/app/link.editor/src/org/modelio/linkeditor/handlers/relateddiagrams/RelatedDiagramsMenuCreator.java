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

package org.modelio.linkeditor.handlers.relateddiagrams;

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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.linkeditor.gef.background.BackgroundEditPart;
import org.modelio.linkeditor.gef.node.NodeEditPart;
import org.modelio.linkeditor.panel.model.GraphNode;
import org.modelio.linkeditor.plugin.LinkEditor;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.facilities.RelatedDiagramHelper;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * Dynamically fills the "related diagrams" e4 menu in link explorer.
 */
@objid ("e83f645f-33cc-430b-a36e-102a2ce9a428")
public class RelatedDiagramsMenuCreator {
    @objid ("9f77d55e-f993-466f-9335-7fff40241756")
    @Inject
    protected MApplication application;

    @objid ("841355d0-10b5-44ba-b850-adcb5632ea78")
    @AboutToShow
    public void aboutToShow(List<MMenuElement> items) {
        // Get current selection
        final ModelElement selectedElement = getSelectedElement();
        if (selectedElement != null) {
            // Get diagrams to display
            Collection<AbstractDiagram> related = RelatedDiagramHelper.getRelatedDiagrams(selectedElement);
            Collection<AbstractDiagram> displaying = new ArrayList<>(selectedElement.getDiagramElement());
        
            if (related.isEmpty() && displaying.isEmpty()) {
                // No diagrams to relate to
                return;
            }
        
            // Remove duplicates
            displaying.removeAll(related);
        
            // Add the related diagrams menu
            items.add(createMenu(related, displaying));
        }
    }

    @objid ("0a6c6dde-a58c-4a76-a788-7d2ffb47295b")
    public MMenu createMenu(Collection<AbstractDiagram> relatedDiagrams, Collection<AbstractDiagram> displaying) {
        final String contributorId = "platform:/plugin/" + LinkEditor.PLUGIN_ID;
        
        // Get the relative file name
        String iconUri = contributorId + "/icons/relateddiagram.png";
        
        // create a new menu
        MMenu elementCreationMenu = MMenuFactory.INSTANCE.createMenu();
        elementCreationMenu.setLabel(LinkEditor.I18N.getString("RelatedDiagrams.label"));
        elementCreationMenu.setIconURI(iconUri);
        
        // make the menu visible
        elementCreationMenu.setEnabled(true);
        elementCreationMenu.setToBeRendered(true);
        elementCreationMenu.setVisible(true);
        
        // bound the menu to the contributing plugin
        elementCreationMenu.setContributorURI(contributorId);
        
        List<MMenuElement> menuChildren = elementCreationMenu.getChildren();
        
        // add related diagram items
        if (!relatedDiagrams.isEmpty()) {
            createDiagramItems(menuChildren, relatedDiagrams, contributorId);
        }
        
        // add diagrams displaying element
        if (!displaying.isEmpty()) {
            // add separator
            MMenuSeparator sep = MMenuFactory.INSTANCE.createMenuSeparator();
            sep.setContributorURI(contributorId);
            sep.setToBeRendered(true);
            sep.setVisible(true);
            menuChildren.add(sep);
        
            // add displaying diagrams
            createDiagramItems(menuChildren, displaying, contributorId);
        }
        return elementCreationMenu;
    }

    @objid ("8771cbb5-46ce-44fe-a891-5fd67cb60e84")
    private void createDiagramItems(Collection<MMenuElement> menuChildren, Collection<AbstractDiagram> relatedDiagrams, String contributorId) {
        for (AbstractDiagram diagram : relatedDiagrams) {
            menuChildren.add(createDiagramItems(diagram, contributorId));
        }
    }

    @objid ("7a34f9a0-b6b2-4c8d-b6ae-b0d90383d15f")
    private MMenuElement createDiagramItems(AbstractDiagram diagram, String contributorId) {
        // create a new handled item
        MHandledMenuItem relatedDiagramItem = MMenuFactory.INSTANCE.createHandledMenuItem();
        relatedDiagramItem.setLabel(diagram.getName());
        relatedDiagramItem.setIconURI(MetamodelImageService.getIconCompletePath(diagram.getMClass()));
        
        // make the menu visible
        relatedDiagramItem.setEnabled(true);
        relatedDiagramItem.setToBeRendered(true);
        relatedDiagramItem.setVisible(true);
        
        // bound the menu to the contributing plugin
        relatedDiagramItem.setContributorURI(contributorId);
        
        // set the command
        MCommand command = getCommand("org.modelio.app.ui.command.openrelateddiagram");
        relatedDiagramItem.setCommand(command);
        
        // add the opened diagram as parameter
        MParameter p = MCommandsFactory.INSTANCE.createParameter();
        p.setName("org.modelio.app.ui.command.parameter.related_diagram");
        p.setValue(diagram.getUuid().toString());
        relatedDiagramItem.getParameters().add(p);
        return relatedDiagramItem;
    }

    @objid ("1ffc42b8-fd57-45e1-851b-4cb44917526d")
    private MCommand getCommand(String commandId) {
        for (MCommand c : this.application.getCommands()) {
            if (commandId.equals(c.getElementId())) {
                return c;
            }
        }
        return null;
    }

    /**
     * Get the currently selected element, or <code>null</code> if the selection size is not equal to one.
     * @return the selected element.
     */
    @objid ("e072fa9b-1a39-4c29-b51c-8d6744396018")
    private ModelElement getSelectedElement() {
        // Get the active selection from the application, to avoid context-related issues when opening the same diagram several times...
        IStructuredSelection selection = getSelection();
        if (selection.size() != 1) {
            return null;
        }
        
        List<?> selectedObjects = selection.toList();
        for (Object selectedObject : selectedObjects) {
            if (selectedObject instanceof NodeEditPart) {
                GraphNode node = ((NodeEditPart) selectedObject).getModel();
                if (node.getData() != null) {
                    return (ModelElement) node.getData();
                }
            } else if (selectedObject instanceof BackgroundEditPart) {
                GraphNode node = ((BackgroundEditPart) selectedObject).getModel().getCenter();
                if (node.getData() != null) {
                    return (ModelElement) node.getData();
                }
            }
        }
        return null;
    }

    @objid ("e08d20d7-edc4-4dc8-8749-7da83a3c000c")
    private IStructuredSelection getSelection() {
        IStructuredSelection selection = (IStructuredSelection) this.application.getContext().get(IServiceConstants.ACTIVE_SELECTION);
        return selection;
    }

}
