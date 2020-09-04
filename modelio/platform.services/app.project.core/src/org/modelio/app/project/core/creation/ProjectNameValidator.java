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

package org.modelio.app.project.core.creation;

import java.io.File;
import java.nio.file.Path;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IInputValidator;
import org.modelio.app.project.core.plugin.AppProjectCore;

/**
 * Implementation of {@link IInputValidator} to check the Modelio Projec's name.
 * The current pattern is accessible as a public field,
 * {@link ProjectNameValidator#PROJECT_NAME_PATTERN}.
 */
@objid ("0044acb4-cc35-1ff2-a7f4-001ec947cd2a")
public class ProjectNameValidator implements IInputValidator {
    @objid ("0044da9a-cc35-1ff2-a7f4-001ec947cd2a")
    private Path workspace = null;

    @objid ("0044dbbc-cc35-1ff2-a7f4-001ec947cd2a")
    public static final Pattern PROJECT_NAME_PATTERN = Pattern.compile("[\\p{L}\\p{N}\\._ ]+");

    @objid ("004637e6-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public String isValid(final String newText) {
        // Check project name syntax must match "[a-zA-Z0-9 _]+"
        if (!PROJECT_NAME_PATTERN.matcher(newText).matches()) {
            return AppProjectCore.I18N.getMessage("ProjectName.InvalidCharacters");
        }
        // Check that the project name does not match an already existing
        // project we also have to look for a directory of the same name even if it is
        // not a real ProjectSpace
        File dir = new File(this.workspace.toFile(), newText);
        
        if (dir.exists()) {
            return AppProjectCore.I18N.getMessage("ProjectName.NameAlreadyUsed");
        
        }
        return null;
    }

    /**
     * Default constructor.
     * 
     * @param workspace The Modelio work space.
     */
    @objid ("00463886-cc35-1ff2-a7f4-001ec947cd2a")
    public ProjectNameValidator(final Path workspace) {
        this.workspace = workspace;
    }

}
