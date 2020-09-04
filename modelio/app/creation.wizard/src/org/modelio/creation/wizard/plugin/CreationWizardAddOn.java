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

package org.modelio.creation.wizard.plugin;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.creation.wizard.impl.CreationContributorManager;

/**
 * E4 addon that add a CreationContributorManager to the Eclipse context when opening a project.
 * 
 * @author cma
 * @since 3.6.1
 */
@objid ("38c82e3e-5541-48c0-a4f1-96204edd4d07")
@SuppressWarnings({ "restriction", "unused" })
public class CreationWizardAddOn {
    @objid ("91f88059-1d41-4e5f-83e4-eef8a2080d52")
    @Inject
    @Optional
    void onProjectClosed(@EventTopic(ModelioEventTopics.PROJECT_CLOSED) final Object closedGProject, IEclipseContext context) {
        context.remove(CreationContributorManager.class);
    }

    @objid ("79444529-2095-479b-b4b3-67ed705169f6")
    @Inject
    @Optional
    void onProjectOpening(@EventTopic(ModelioEventTopics.PROJECT_OPENING) final Object closedGProject, IEclipseContext context) {
        context.set(CreationContributorManager.class.getName(), ContextInjectionFactory.make(CreationContributorManager.class, context));
    }

}
