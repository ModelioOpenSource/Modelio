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

package org.modelio.model.browser.view.context;

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
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.facilities.RelatedDiagramHelper;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.model.browser.view.plugin.BrowserViewActivator;

/**
 * Dynamically fills the "related diagrams" e4 menu in the model browser.
 */
@objid ("f4908570-99ce-4b58-a87f-0704ddcc921d")
class RelatedDiagramsForModelBrowser {
    @objid ("9d68520b-f4de-4fdb-ba4c-eb6d1cd513a0")
    @Inject
    private MApplication application;

    @objid ("7f999b1d-0d23-4836-98ad-df44bd2a65ca")
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
        
            // Add a separator
            MMenuSeparator separator = MMenuFactory.INSTANCE.createMenuSeparator();
            items.add(separator);
        
            // Add the related diagrams menu
            items.add(createMenu(related, displaying));
        }
    }

    @objid ("d971407e-5adb-4643-be02-c04c7d964336")
    public MMenu createMenu(Collection<AbstractDiagram> relatedDiagrams, Collection<AbstractDiagram> displaying) {
        final String contributorId = "platform:/plugin/" + BrowserViewActivator.getContext().getBundle().getSymbolicName();
        
        // create a new menu
        MMenu elementCreationMenu = MMenuFactory.INSTANCE.createMenu();
        elementCreationMenu.setLabel(BrowserViewActivator.I18N.getString("RelatedDiagramsMenu.label"));
        elementCreationMenu.setIconURI(contributorId + "/icons/relateddiagram.png");
        
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

    @objid ("fa44b562-d4ee-4164-9ce4-498ba8cb9b87")
    private void createDiagramItems(List<MMenuElement> menuChildren, Collection<AbstractDiagram> relatedDiagrams, String contributorId) {
        for (AbstractDiagram diagram : relatedDiagrams) {
            menuChildren.add(createDiagramItems(diagram, contributorId));
        }
    }

    @objid ("e39e3bdc-4632-4181-bce9-f890af79b0f0")
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

    @objid ("3c52ba9d-ea45-4f66-89eb-869188128b09")
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
    @objid ("e7d4fde8-cb3c-4cbf-b657-ba30661d440b")
    protected ModelElement getSelectedElement() {
        // Get the active selection from the application, to avoid context-related issues when opening the same diagram several times...
        IStructuredSelection selection = getSelection();
        if (selection.size() != 1) {
            return null;
        }
        return SelectionHelper.getFirst(selection, ModelElement.class);
    }

    @objid ("633d7ceb-704b-4ad9-b5a1-662116a91961")
    protected IStructuredSelection getSelection() {
        IStructuredSelection selection = (IStructuredSelection) this.application.getContext().get(IServiceConstants.ACTIVE_SELECTION);
        return selection;
    }

}
