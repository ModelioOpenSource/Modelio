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

package org.modelio.diagram.outline.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.gproject.gproject.GProject;

/**
 * Main class of the diagram outline.
 * It display a outline view according to the last {@link DiagramOutlineView} given by the active part.
 */
@objid ("4319fa2e-fdfb-4a20-b17c-08f80a25a531")
public class DiagramOutlineView {
    @objid ("dad0a3c9-5e5c-447e-b70d-4fbafbf62893")
    private Composite parent;

    @objid ("5b5e7c06-8776-4401-a7c5-12864f843404")
    private Composite panel;

    @objid ("2edbad2f-8179-493d-becb-c84f72c5260a")
     IContentOutlinePage outlinePage;

    @objid ("d8936dce-0bd5-4ad5-a1e8-57f1cf5dca77")
    @PostConstruct
    public void createPartControl(Composite theParent) {
        this.parent = theParent;
        this.panel = new Composite(this.parent, SWT.NONE);
        this.panel.setLayout(new FillLayout());
    }

    /**
     * Called when the modeling session is closed. This method empties this outline page.
     * @param closedProject
     */
    @objid ("ddedb240-a430-4689-a03b-db636a2afcbb")
    @Inject
    @Optional
    void onProjectClosed(@EventTopic(ModelioEventTopics.PROJECT_CLOSED) final GProject closedProject) {
        Display.getDefault().asyncExec(new Runnable() {
            
            @Override
            public void run() {
                if (DiagramOutlineView.this.outlinePage != null) {                    
                    DiagramOutlineView.this.outlinePage.dispose();
                }
            }
        });
    }

    /**
     * Create the outline page when select a part which provides an {@link IContentOutlinePage}.
     * 
     * @param part the active part.
     */
    @objid ("a961591e-2d66-4bd3-8b50-54877732c4dc")
    @Inject
    void activePartChanged(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        if (this.panel == null || this.parent == null) {
            return;
        }
        if (part == null || ! (part.getObject() instanceof IAdaptable)) {
            return;
        }
        if (this.outlinePage != null && this.outlinePage.getControl() != null) {            
            this.outlinePage.dispose();
        }
        this.outlinePage = ((IAdaptable) part.getObject()).getAdapter(IContentOutlinePage.class);
        if (this.outlinePage != null) {
            this.outlinePage.createControl(this.panel);
            this.panel.layout();
            this.parent.layout();
        }
    }

}
