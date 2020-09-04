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

package org.modelio.app.ramcs.edition;

import java.nio.file.Path;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.gproject.ramc.core.model.ModelComponent;
import org.modelio.metamodel.uml.statik.Artifact;

/**
 * Extends the {@link ModelComponent} in-memory representation of a Model Component to add
 * project-level data, like the modules contributing to the packaging.
 */
@objid ("b9fa5ab0-ddc4-4711-9d89-8b77f18ab25a")
public class RamcModel extends ModelComponent {
    /**
     * The path of the project the ramc model comes from.
     */
    @objid ("147574a5-25f1-4b59-9b60-de2546f99159")
    private Path projectPath;

    /**
     * Possible module contributors to the model component packaging
     */
    @objid ("cc926e8d-d082-4713-9fd8-a0406568879e")
    private List<IModule> contributorCandidates;

    /**
     * @param projectPath the path of the project the ramc model comes from.
     * @param ramc the model component artifact.
     */
    @objid ("af33b675-81be-489f-88e8-bc5c79e22efd")
    public RamcModel(Path projectPath, Artifact ramc) {
        super(ramc);
        this.projectPath = projectPath;
    }

    /**
     * Replace the current contributors.
     * @param contributorCandidates the module contributors to the model component packaging.
     */
    @objid ("2ca9927b-cda5-4edf-9835-4e3ad6a9b02d")
    public void setContributorCandidates(List<IModule> contributorCandidates) {
        this.contributorCandidates = contributorCandidates;
    }

    /**
     * @return the possible module contributors to the model component packaging.
     */
    @objid ("d5d720a0-8946-4e52-9177-685f3a635394")
    public List<IModule> getContributorCandidates() {
        return this.contributorCandidates;
    }

    /**
     * @return the path of the project the ramc model comes from.
     */
    @objid ("f12263bc-509d-40d1-8658-48dadde22e9b")
    public Path getProjectPath() {
        return this.projectPath;
    }

    /**
     * @return <code>true</code> if the ramc can be edited.
     */
    @objid ("1b294bb8-29f3-4abb-b8e0-008f2637da24")
    public boolean isEditable() {
        return getArtifact().isModifiable();
    }

}
