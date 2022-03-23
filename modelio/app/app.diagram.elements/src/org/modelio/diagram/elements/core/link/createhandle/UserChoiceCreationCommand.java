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
package org.modelio.diagram.elements.core.link.createhandle;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * Command that first display a popup menu with all allowed links found in the diagram palette, let the user choose one and do the creation.
 */
@objid ("c0ef1e86-dd9d-49ca-a4ac-1c8a140738a5")
public class UserChoiceCreationCommand extends Command {
    @objid ("045c3dd2-1ea1-43bf-9eec-ceaf05305f1a")
    private CreateConnectionRequest finalRequest;

    @objid ("dded0ff7-0b6b-4b42-a6b1-9284e517faf3")
    private ICreationActionProvider actionProvider;

    @objid ("538f1592-b936-4778-8e01-7efb27d8925e")
    private EditPartViewer viewer;

    @objid ("1b735e96-7c25-4bc9-93b7-3e9aeb981863")
    public  UserChoiceCreationCommand(EditPartViewer viewer, ICreationActionProvider actionProvider) {
        this.viewer = viewer;
        this.actionProvider = actionProvider;
        
    }

    @objid ("e9cbcd98-07b5-4a04-83ce-847a685c84b9")
    public void update(CreateConnectionRequest newReq) {
        Objects.requireNonNull(newReq);
        this.finalRequest = newReq;
        
    }

    @objid ("ea2f2746-b877-4405-8a15-91909c9e49a0")
    @Override
    public void execute() {
        final EditPart sourceEditPart = this.finalRequest.getSourceEditPart();
        final EditPart targetEditPart = this.finalRequest.getTargetEditPart();
        
        final Control control = this.viewer.getControl();
        final Display display = control.getDisplay();
        final Menu pop = new Menu(control);
        final ResourceManager paletteRes = this.viewer.getEditDomain().getPaletteViewer().getResourceManager();
        
        // The menu selection listener
        SelectionListener listener = new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                doCreateLink(e);
            }
        };
        
        // Fill the menu
        this.actionProvider.getPaletteActions(this.finalRequest)
                // hide commands that can't be executed
                .filter(action -> action.getCommand() != null && action.getCommand().canExecute())
                .forEach(action -> {
                    MenuItem item = new MenuItem(pop, 0);
                    item.setData(action);
                    item.setText(action.getLabel());
                    ImageDescriptor icon = action.getIcon();
                    item.addSelectionListener(listener);
                    if (icon != null) {
                        item.setImage((Image) paletteRes.get(icon));
                    }
                });
        
        // Display the menu and wait
        try {
            // Display feedback again, until menu is disposed.
            sourceEditPart.showSourceFeedback(this.finalRequest);
            targetEditPart.showTargetFeedback(this.finalRequest);
        
            // Set menu position
            org.eclipse.draw2d.geometry.Point reqLocation = this.finalRequest.getLocation();
            Point menuPos = display.map(control, null, reqLocation.x, reqLocation.y);
            pop.setLocation(menuPos);
        
            // Display menu
            pop.setVisible(true);
        
            // Wait for the user
            while (!pop.isDisposed() && pop.isVisible()) {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            }
            while (display.readAndDispatch()) {
                // needed, to get the selection event, which is fired AFTER the menu is hidden
            }
        } finally {
            pop.dispose();
        
            // Make sure the "link" feedback is cleared
            sourceEditPart.eraseSourceFeedback(this.finalRequest);
            targetEditPart.eraseTargetFeedback(this.finalRequest);
        
            // Opening a menu fucks up the active tool, manually select the default tool to end the interaction.
            EditDomain editDomain = sourceEditPart.getViewer().getEditDomain();
            editDomain.setActiveTool(editDomain.getDefaultTool());
        }
        
    }

    @objid ("ac143616-fb7a-4429-916f-dd1a2c5bfd91")
    private void doCreateLink(SelectionEvent event) {
        MenuItem item = (MenuItem) event.widget;
        ICreationActionDescriptor action = (ICreationActionDescriptor) item.getData();
        action.execute(this.viewer);
        
    }

    @objid ("99304b5b-155d-4acd-b5af-a65f8303b49c")
    public ICreationActionProvider getActionProvider() {
        return this.actionProvider;
    }

}
