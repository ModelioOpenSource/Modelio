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

package org.modelio.diagram.styles.plugin;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.preferences.IGProjectPreferenceStore;
import org.modelio.app.project.core.prefs.ProjectPreferencesKeys;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.gproject.GProject;
import org.osgi.framework.Bundle;

@objid ("12c16440-19f0-11e2-92d2-001ec947c8cc")
public class DiagramStylesProcessor {
    @objid ("5a71c82b-ba5e-4a42-a011-5acbdab4ef4b")
    private static DiagramStylesProcessor INSTANCE;

    @objid ("06e97363-c458-4118-8d1d-e5da307cfd58")
    @Inject
    @Optional
    private IProjectService projectService;

    @objid ("12c3c691-19f0-11e2-92d2-001ec947c8cc")
    @Execute
    void init() {
        // Create an instance and store it so it can listen to project open/close events.
        DiagramStylesProcessor.INSTANCE = this;
    }

    @objid ("12c628eb-19f0-11e2-92d2-001ec947c8cc")
    @Inject
    @Optional
    void onProjectOpened(@EventTopic (ModelioEventTopics.PROJECT_OPENING) final GProject openedProject) {
        // Ensure that the project style dir is initialized and that a default style exists in it
        DiagramStylesProcessor.checkProjectStyleDirectory(openedProject.getProjectFileStructure().getProjectDataPath());
        
        // Load styles
        DiagramStyles.getStyleManager().reloadStylesIn(openedProject.getProjectFileStructure().getProjectDataPath().resolve(DiagramStyles.PROJECT_STYLE_SUBDIR));
        
        if (this.projectService != null) {
            // Listen to diagram theme changes
            IGProjectPreferenceStore store = this.projectService.getProjectPreferences(ProjectPreferencesKeys.NODE_ID);
            store.addPropertyChangeListener(new IPropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent event) {
                    if (event.getProperty().endsWith(ProjectPreferencesKeys.DIAGRAM_DEFAULT_THEME_PREFKEY)) {
                        DiagramStyles.getStyleManager().setDefaultTheme((String) event.getNewValue());
                    }
                }
            });
        
            // Init default diagram theme
            if (store.getDefaultString(ProjectPreferencesKeys.DIAGRAM_DEFAULT_THEME_PREFKEY).isEmpty()) {
                store.setDefault(ProjectPreferencesKeys.DIAGRAM_DEFAULT_THEME_PREFKEY, DiagramStyles.MODELIO_3X_THEME_NAME);
                store.setToDefault(ProjectPreferencesKeys.DIAGRAM_DEFAULT_THEME_PREFKEY);
            }
            DiagramStyles.getStyleManager().setDefaultTheme(store.getString(ProjectPreferencesKeys.DIAGRAM_DEFAULT_THEME_PREFKEY));
        }
    }

    @objid ("12c628f1-19f0-11e2-92d2-001ec947c8cc")
    private static void checkProjectStyleDirectory(Path projectPath) {
        // ensure the existence of the project style directory and create it if needed.
        Path projectStyleDir = projectPath.resolve(DiagramStyles.PROJECT_STYLE_SUBDIR);
        if (!Files.exists(projectStyleDir)) {
            try {
                Files.createDirectories(projectStyleDir);
            } catch (IOException e) {
                DiagramStyles.LOG.error(e);
            }
        }
        
        // ensure the existence of the style files
        ensuteFileExistence(getStyleDirectory(), DiagramStyles.STYLE_FILE_EXTENSION, projectStyleDir);
        
        // ensure the existence of the theme files
        ensuteFileExistence(getThemeDirectory(), DiagramStyles.THEME_FILE_EXTENSION, projectStyleDir);
    }

    /**
     * Find the default style directory in the plugin resources.
     */
    @objid ("27a920a7-ef4d-460a-8d26-9dc84f98a654")
    private static Path getStyleDirectory() {
        Bundle bundle = DiagramStyles.getContext().getBundle();
        String s = "platform:/plugin/" + bundle.getSymbolicName() + "/" + "res/style/";
        URL url = null;
        try {
            url = new URL(s);
            URL fileURL = FileLocator.toFileURL(url);
            return Paths.get(URIUtil.toURI(fileURL));
        } catch (Exception e) {
            DiagramStyles.LOG.error(e);
            return null;
        }
    }

    /**
     * Find the default theme directory in the plugin resources.
     */
    @objid ("8ddc258d-6b84-4f62-b602-6f02645069d3")
    private static Path getThemeDirectory() {
        Bundle bundle = DiagramStyles.getContext().getBundle();
        String s = "platform:/plugin/" + bundle.getSymbolicName() + "/" + "res/theme/";
        URL url = null;
        try {
            url = new URL(s);
            URL fileURL = FileLocator.toFileURL(url);
            return Paths.get(URIUtil.toURI(fileURL));
        } catch (Exception e) {
            DiagramStyles.LOG.error(e);
            return null;
        }
    }

    /**
     * Walk the given style directory an copy each file having the given extension into the project style directory.
     */
    @objid ("b1f0cce3-0ac2-4899-b1e8-ca101b6abbdd")
    private static void ensuteFileExistence(Path styleDirectory, String styleFileExtension, Path projectStyleDir) {
        try {
            Files.walk(styleDirectory)
                    .filter(Files::isRegularFile)
                    .filter(sub -> sub.toString().endsWith(styleFileExtension))
                    .forEach(sub -> {
                        Path targetPath = projectStyleDir.resolve(sub.getFileName());
                        if (Files.notExists(targetPath)) {
                            try {
                                Files.copy(sub, targetPath);
                            } catch (IOException e) {
                                DiagramStyles.LOG.debug(e);
                            }
                        }
                    });
        } catch (IOException e) {
            DiagramStyles.LOG.debug(e);
        }
    }

}
