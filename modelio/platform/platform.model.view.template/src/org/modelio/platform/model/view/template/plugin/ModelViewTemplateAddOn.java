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

package org.modelio.platform.model.view.template.plugin;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.model.view.template.service.ModelViewTemplateManager;

/**
 * E4 addon that add a {@link ModelViewTemplateManager} to the Eclipse context when opening a project.
 * 
 * @since 4.1.0
 */
@objid ("37409fd8-419c-4490-8653-17f66807c9a6")
@SuppressWarnings({ "restriction", "unused" })
public class ModelViewTemplateAddOn {
    @objid ("63119a79-f006-414a-a9e7-ca541c3449ce")
    @Inject
    @Optional
    void onProjectClosed(@EventTopic(ModelioEventTopics.PROJECT_CLOSED) final Object closedGProject, IEclipseContext context) {
        context.remove(ModelViewTemplateManager.class);
    }

    @objid ("dfd6a54a-5f1b-4c3e-90f2-328042091dd1")
    @Inject
    @Optional
    void onProjectOpening(@EventTopic(ModelioEventTopics.PROJECT_OPENING) final Object closedGProject, IEclipseContext context) {
        context.set(ModelViewTemplateManager.class, ContextInjectionFactory.make(ModelViewTemplateManager.class, context));
    }

}
