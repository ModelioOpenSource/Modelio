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

package org.modelio.editors.richnote.helper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.statik.Artifact;

/**
 * Service class to get the file of an external document.
 */
@objid ("82bc0485-020a-4509-91d7-804f383115ab")
public class RichNoteFilesGeometry {
    @objid ("4e8ec592-aaeb-422e-9971-58b9f3dfe606")
    private static final String PROJECT_KEYWORD = "{$project}";

    @objid ("e5cb7f09-d516-4621-9a10-0cb747cfeb84")
    private static final String RICHNOTE_DIRNAME = "richnotes";

    @objid ("94228891-c349-4824-921c-aa1abcad4f78")
    private GProject project;

    /**
     * initialize the service.
     * @param project the project to handle
     */
    @objid ("8f1bf648-57fd-48fb-8d86-069b26a5ecdc")
    public RichNoteFilesGeometry(GProject project) {
        this.project = project;
    }

    /**
     * Get the file path of the given document.
     * @param doc an external document.
     * @return the file path.
     */
    @objid ("4802475e-1349-4948-94ed-dcf4484514da")
    public Path getPath(final Artifact doc) {
        String fileName = doc.getFileName();
        
        if (fileName.isEmpty()) {
            return null;
        }
        
        Path f = Paths.get(fileName);
        if (! Files.exists(f)) {
            f = getAbsoluteFile(fileName);
        }
        return f;
    }

    /**
     * Relativize the given path and return it in a format that can be directly
     * stored in the {@link ExternDocument#getPath() path} rich note field.
     * @param filePath the path to relativize.
     * @return the relativized path.
     */
    @objid ("b50e1f0d-f2d7-4271-8f33-997f70f507e4")
    public String getRelativePath(Path filePath) {
        if (filePath.startsWith(getRuntimePath())) {
            // Relative to the runtime directory
            return getRuntimePath().relativize(filePath).toString();
        } else if (filePath.startsWith(this.project.getProjectPath())) {
            // Relative to the project directory
            String docPath = this.project.getProjectPath().relativize(filePath).toString();
            return (PROJECT_KEYWORD+"/"+docPath);
        
        } else {
            // Store as is
            return (filePath.toString());
        }
    }

    /**
     * Build the default file path for the given rich note.
     * <p>
     * By default a rich note is stored in the runtime/richnotes directory of the project space,
     * in a file with the element UUID as name with the given extension.
     * <p>
     * The returned path should be made relative before being stored in the rich note element using {@link ExternDocument#setPath(String)}.
     * @param doc a rich note.
     * @param extension the file extension
     * @return its default path.
     */
    @objid ("6aa98df4-b292-43c4-8d58-a00c2ee7217b")
    public Path getDefaultPath(final AbstractResource doc, String extension) {
        return getEditedFilesdirectory().resolve(doc.getUuid().toString()+ "." + extension);
    }

    /**
     * Get an absolute path from a path relative to the project space.
     * <p>
     * If <i>fileName</i> contains {@value #PROJECT_KEYWORD}, the path is relative to the project
     * root directory. In the other case the path is relative to the project
     * runtime directory.
     * @param fileName a relative file path
     * @return an absolute file path.
     */
    @objid ("76abffcd-969c-415c-aede-3715f9a3c53b")
    private Path getAbsoluteFile(final String fileName) {
        int idx = fileName.lastIndexOf(PROJECT_KEYWORD);
        if (idx == -1) {
            return getRuntimePath().resolve(fileName);
        } else {
            return this.project.getProjectPath().resolve(fileName.substring(idx+PROJECT_KEYWORD.length()));
        }
    }

    /**
     * @return the project space path.
     */
    @objid ("6ca7b7a7-4d2f-4082-a310-cd59c3d8a69d")
    private Path getRuntimePath() {
        return this.project.getProjectRuntimePath();
    }

    @objid ("6b07fd7b-c36f-4dfd-b90a-bf46f462c68c")
    public Path getEditedFilesdirectory() {
        return getRuntimePath().resolve(RICHNOTE_DIRNAME);
    }

}
