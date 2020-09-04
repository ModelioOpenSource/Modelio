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

package org.modelio.patterns.api;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import javax.xml.bind.JAXBException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.patterns.model.CategoryData;
import org.modelio.patterns.model.RuntimePattern;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Represents a catalog of patterns.
 * @see RuntimePattern
 */
@objid ("626ba26d-a09b-4545-a293-cb4c5cb87172")
public interface IPatternRepository {
    /**
     * Install a pattern into the repository and load it.
     * <p>
     * If the pattern already exists in the repository, it is replaced.
     * </p>
     * @param newPattern a valid <i>.umlt<i> file.
     * 
     * @return a pattern loaded from the given file.
     * @throws javax.xml.bind.JAXBException when the pattern metadatas are invalid.
     * @throws java.io.IOException when the given file doesn't contain a valid {@link Pattern}.
     */
    @objid ("60c42e0d-043b-4a77-9ee3-54a59b24fe9c")
    RuntimePattern addPattern(Path patternPath) throws IOException, JAXBException;

    /**
     * Get the names of all libraries installed in the project.
     * 
     * @return a collection of library names.
     */
    @objid ("117f0773-81ae-4ace-b309-badbcf81f098")
    Collection<String> getAvailableLibraries();

    /**
     * Get the names of all started modules in the project.
     * 
     * @return a collection of module names.
     */
    @objid ("9df60c7f-a1c1-45c2-acf4-74767e9aa5ba")
    Collection<String> getAvailableModules();

    /**
     * Get all categories containing at least one pattern applicable on these elements, from the repository.
     * 
     * @param elements the model elements to filter the categories with.
     * @return a collection of pattern categories.
     */
    @objid ("9d6e56f6-16c1-44fa-921d-ece1b8777708")
    Collection<CategoryData> getCategories(Collection<MObject> elements);

    /**
     * Get all pattern categories, from the repository.
     * 
     * @return a collection of pattern categories.
     */
    @objid ("5a65ec1e-6ec3-42d3-8577-2c92c8453983")
    Collection<CategoryData> getCategories();

    /**
     * Get a pattern from its name.
     * 
     * @param name the name of the pattern to look for.
     * @return the pattern having the given name.
     */
    @objid ("53904ed0-bb4c-49e1-af95-7d6e280c24ea")
    RuntimePattern getPattern(String name);

    /**
     * Get patterns applicable on these elements, from the repository.
     * 
     * @param elements the model elements to filter the patterns with.
     * @return a collection of patterns.
     */
    @objid ("f94cd5f3-b3f3-47b8-8fe5-344daa7b1954")
    Collection<RuntimePattern> getPatterns(Collection<MObject> elements);

    /**
     * Get all patterns, from the repository.
     * 
     * @return a collection of patterns.
     */
    @objid ("06ae55ac-a25b-4e62-b46a-d076dd5abea6")
    Collection<RuntimePattern> getPatterns();

    /**
     * Get the repository path.
     * 
     * @return the directory containing all patterns.
     */
    @objid ("084eb73d-247e-4d1b-851f-4f2997b2a95b")
    Path getRepositoryPath();

    /**
     * Reload all patterns in the repository.
     */
    @objid ("ab5281b5-9861-4112-beb7-2fff9149138e")
    void reloadPatterns();

    /**
     * Remove a pattern from the repository.
     * 
     * @param pattern the pattern to remove.
     */
    @objid ("cff7163c-cb39-4c89-9a1a-419f84fda785")
    void removePattern(RuntimePattern pattern);

}
