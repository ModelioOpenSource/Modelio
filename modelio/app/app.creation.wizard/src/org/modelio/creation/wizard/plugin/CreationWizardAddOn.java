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
package org.modelio.creation.wizard.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.modelio.creation.wizard.impl.CreationContributorManager;
import org.modelio.platform.core.events.ModelioEventTopics;

/**
 * E4 addon that add a CreationContributorManager to the Eclipse context when opening a project.
 * 
 * @author cma
 * @since 3.6.1
 */
@objid ("dbe4f059-fce9-42da-9ffe-46a00cb60e07")
@SuppressWarnings({ "restriction", "unused" })
public class CreationWizardAddOn {
    @objid ("7ed6b1ce-e30e-4e74-8f6d-5486ac7ddc9d")
    @Inject
    @Optional
    void onProjectClosed(@EventTopic(ModelioEventTopics.PROJECT_CLOSED) final Object closedGProject, IEclipseContext context) {
        context.remove(CreationContributorManager.class);
    }

    @objid ("19a1ed0b-e727-4f78-a143-cf6df4d501aa")
    @Inject
    @Optional
    void onProjectOpening(@EventTopic(ModelioEventTopics.PROJECT_OPENING) final Object closedGProject, IEclipseContext context) {
        context.set(CreationContributorManager.class.getName(), ContextInjectionFactory.make(CreationContributorManager.class, context));
    }

}
