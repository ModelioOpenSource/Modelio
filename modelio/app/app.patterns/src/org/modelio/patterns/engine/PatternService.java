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
package org.modelio.patterns.engine;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.modelio.api.modelio.pattern.IPatternService.PatternException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.patterns.api.IPatternService;
import org.modelio.patterns.exporter.impl.PatternExporter;
import org.modelio.patterns.model.RuntimePattern;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.platform.core.events.ModelioEventTopics;

@objid ("87ab8792-a942-47f7-b913-6c8668378fe6")
@Creatable
class PatternService implements IPatternService {
    @objid ("53c5fd73-0f43-4ae8-b694-921d1f320c01")
    private PatternRepository patternCatalog;

    @objid ("5b29ef74-86b6-4b89-a8a1-d1c1587093d7")
    @Override
    public void exportPattern(RuntimePattern pattern) throws PatternException {
        new PatternExporter().exportPattern(pattern);
    }

    @objid ("3747c0ad-6674-46c9-9de8-cf72f9d4ff68")
    @Override
    public PatternRepository getCatalog() {
        return this.patternCatalog;
    }

    /**
     * Called by injection when the current project is closed, to dereference its pattern catalog.
     * @param project the current project.
     */
    @objid ("cd77fe80-0796-44ac-bea1-9c0708119dc0")
    @Inject
    @Optional
    public void onProjectClosed(@EventTopic(ModelioEventTopics.PROJECT_CLOSED) final GProject project) {
        if (project != null) {
            this.patternCatalog = null;
        }
        
    }

    /**
     * Called by injection when a project is opened, to load its pattern catalog.
     * @param project the current project.
     */
    @objid ("5a58cde4-dd24-495b-ad96-c7457c85970a")
    @Inject
    @Optional
    public void onProjectOpened(@EventTopic(ModelioEventTopics.PROJECT_OPENED) final GProject project) {
        if (project != null) {
            this.patternCatalog = new PatternRepository(
                    project.getName(),
                    Patterns.getProjectPatternsDirectory(project),
                    false);
        }
        
    }

}
