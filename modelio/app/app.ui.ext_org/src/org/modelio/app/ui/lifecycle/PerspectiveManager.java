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

package org.modelio.app.ui.lifecycle;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.rcp.uiservice.IModelioUiService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * This class manages perspectives for Modelio, implementing the IModelioUiService.<br/>
 * It is also listening to project open and close events in order to automatically switch from the workspace to the model perspective
 */
@objid ("8c71273a-9f27-4da1-9262-50cc3dccfc54")
@Creatable
public class PerspectiveManager implements EventHandler, IModelioUiService {
    @objid ("e8d6123c-0b74-4f2d-b3c5-7459ff1cfae9")
    private static final String WELCOME_PERSPECTIVE_ID = "org.modelio.app.ui.welcome.perspective";

    @objid ("69e0498b-55e8-4e01-b765-cae30f8b57f8")
    @Inject
     MApplication application;

    @objid ("2b19e231-042a-424a-86c9-79961d35e456")
    @Inject
    protected IEventBroker eventBroker;

    @objid ("3cb6ebee-4b0e-4a55-890b-e130d186843a")
    @Inject
     IProjectService projectService;

    /**
     * {@inheritDoc}
     */
    @objid ("3e3731c9-ac74-42a3-8a6b-7ecc5fcc7517")
    @Override
    public void switchToPerspective(MPerspective perspective) {
        showWelcome(false);
        
        final MPerspectiveStack stack = getPerspectiveStack();
        if (stack == null) {
            return;
        }
        
        final MPerspective switchTo = (perspective != null) ? perspective : getDefaultPerspective();
        
        stack.setSelectedElement(switchTo);
    }

    /**
     * {@inheritDoc}
     */
    @objid ("7a04d6e5-0c96-4c45-bec1-7d14095edb1a")
    @Override
    public void switchToWorkspace() {
        MPerspective workspacePerspective = getWorkspacePerspectives().get(0);
        switchToPerspective(workspacePerspective);
    }

    /**
     * {@inheritDoc}
     */
    @objid ("242445c1-b0ac-4807-ad59-8713310fecda")
    @Override
    public void showWelcome(boolean onOff) {
        MPartStack welcome = getWelcomeStack();
        MPerspectiveStack pstack = getPerspectiveStack();
        
        if (welcome != null) {
            welcome.setVisible(onOff);
        }
        if (pstack != null) {
            pstack.setVisible(!onOff);
        }
    }

    @objid ("8e84dec0-c251-4114-896d-e662a7c12dab")
    @PostConstruct
    void init() {
        this.eventBroker.subscribe(ModelioEventTopics.PROJECT_EVENTS, this);
    }

    @objid ("d2449a37-b73a-413e-8f86-2e416ee41ebb")
    @Override
    public void handleEvent(Event event) {
        switch (event.getTopic()) {
        
        case ModelioEventTopics.PROJECT_OPENING:
            // Switch to a 'workable' perspective on project openING to give chance to views to benefit from an available 'workable' perspective when listening to openED event
            for (MPerspective p : getProjectPerspectives()) {
                if (p.getTags().contains("default")) {
                    switchToPerspective(p);
                    return;
                }
            }
            break;
        case ModelioEventTopics.PROJECT_CLOSED:
            for (MPerspective p : getWorkspacePerspectives()) {
                if (p.getTags().contains("default")) {
                    switchToPerspective(p);
                    return;
                }
            }
            break;
        default:
            // Nothing to do
        }
    }

    @objid ("8dedeadf-68a8-420d-a605-bdc755f2fffc")
    private MPerspectiveStack getPerspectiveStack() {
        EModelService modelService = this.application.getContext().get(EModelService.class);
        List<MPerspectiveStack> perspectivestacks = modelService.findElements(this.application,
                "org.modelio.app.ui.stack.perspectives", MPerspectiveStack.class, null);
        for (MPerspectiveStack p : perspectivestacks) {
            if (p.getElementId().equals("org.modelio.app.ui.stack.perspectives")) {
                return p;
            }
        }
        return null;
    }

    @objid ("3b0b48a7-b3ea-414f-a2c9-76386b635d7c")
    private List<MPerspective> getWorkspacePerspectives() {
        EModelService modelService = this.application.getContext().get(EModelService.class);
        return modelService.findElements(this.application, null, MPerspective.class, Arrays.asList("workspace"));
    }

    @objid ("0174474f-003b-4ffb-97e8-68c83a723cc4")
    private List<MPerspective> getProjectPerspectives() {
        EModelService modelService = this.application.getContext().get(EModelService.class);
        return modelService.findElements(this.application, null, MPerspective.class, Arrays.asList("project"));
    }

    @objid ("1ec8ab45-99b4-4a0e-9334-e4ba1e0e8349")
    private MPerspective getDefaultPerspective() {
        if (this.projectService != null && this.projectService.getOpenedProject() != null) {
            return getProjectPerspectives().get(0);
        } else {
            return getWorkspacePerspectives().get(0);
        }
    }

    @objid ("a59b6624-52d0-480d-87ae-7506b1736b43")
    private MPartStack getWelcomeStack() {
        EModelService modelService = this.application.getContext().get(EModelService.class);
        List<MPartStack> views = modelService.findElements(this.application, "org.modelio.app.ui.welcome.view", MPartStack.class,
                null);
        for (MPartStack p : views) {
            if (p.getElementId().equals("org.modelio.app.ui.welcome.view")) {
                return p;
            }
        }
        return null;
    }

}
