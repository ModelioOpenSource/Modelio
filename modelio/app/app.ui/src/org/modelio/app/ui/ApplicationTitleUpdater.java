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
package org.modelio.app.ui;

import java.lang.management.MemoryUsage;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.core.ModelioEnv;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.vcore.session.api.memory.IMemoryEventListener;

/**
 * This class is in charge of updating the application shell title label. The
 * displayed label integrates the current project name (if some) the user login
 * name (if some) and Modelio version.
 */
@objid ("00448928-cc35-1ff2-a7f4-001ec947cd2a")
@Creatable
public class ApplicationTitleUpdater implements IMemoryEventListener {
    @objid ("00476c10-cc35-1ff2-a7f4-001ec947cd2a")
    private static String MODELIO = "Modelio";

    @objid ("ad351e8b-89de-497f-81c2-be66bb3d3484")
    private boolean swapping;

    @objid ("0044c096-cc35-1ff2-a7f4-001ec947cd2a")
    @Inject
    void applicationIsThere(@Optional final MApplication application) {
        this.swapping = false;
        updateShellTitle(application, null);
        
    }

    @objid ("0044c14a-cc35-1ff2-a7f4-001ec947cd2a")
    private MWindow getApplicationShellWindow(final MApplication application) {
        final EModelService modelService = application.getContext().get(EModelService.class);
        final List<MWindow> windows = modelService.findElements(application, null, MWindow.class, null);
        for (final MWindow w : windows) {
            if (w.getElementId().equals("org.modelio.app.ui.trimmed")) {
                return w;
            }
        }
        return null;
    }

    @objid ("004044b2-4082-1ff4-9ac8-001ec947cd2a")
    @Inject
    @Optional
    void onProjectOpened(final MApplication application, @EventTopic (ModelioEventTopics.PROJECT_OPENED) GProject project) {
        updateShellTitle(application, project);
    }

    @objid ("004080ee-4082-1ff4-9ac8-001ec947cd2a")
    @Inject
    @Optional
    void onProjectClosed(final MApplication application, @EventTopic (ModelioEventTopics.PROJECT_CLOSED) GProject project) {
        updateShellTitle(application, null);
    }

    @objid ("36b09344-f211-409c-a8cf-72b5baac18ce")
    private void updateShellTitle(final MApplication application, GProject project) {
        final MWindow appShell = getApplicationShellWindow(application);
        if (appShell == null) {
            return;
        }
        
        final StringBuilder buffer = new StringBuilder();
        
        if (project != null) {
            buffer.append(project.getName());
            buffer.append(" - ");
        }
        final ModelioEnv modelioEnv = application.getContext().get(ModelioEnv.class);
        
        buffer.append(ApplicationTitleUpdater.MODELIO);
        buffer.append(" " + modelioEnv.getVersion().getMajorVersion() + "." + modelioEnv.getVersion().getMinorVersion());
        
        if (this.swapping) {
            buffer.append(" (...)");
        }
        
        appShell.setLabel(buffer.toString());
        
    }

    @objid ("b2924121-5a1d-425a-82ce-08a55fa26f0a")
    @Override
    public void onFreeMemoryStart(MemoryUsage memState) {
        this.swapping = true;
    }

    @objid ("c7bffa2e-4f24-4907-9982-fe395f451145")
    @Override
    public void onFreeMemoryEnd(int swappedObjects, MemoryUsage memState) {
        this.swapping = false;
    }

}
