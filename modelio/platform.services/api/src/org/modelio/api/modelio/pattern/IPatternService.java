/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.pattern;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Represents a project's catalog of patterns.
 * @since 3.3.4
 */
@objid ("0d98a9b5-b2d2-420f-ade9-84623a1bb105")
public interface IPatternService {
    /**
     * Export a pattern.
     * @param pattern the pattern to export.
     * 
     * @throws java.io.IOException when the Pattern can't be exported.
     */
    @objid ("b59fc083-9c1b-439c-8a47-a85b99260cd4")
    void exportPattern(Package modelPattern, Path patternPath) throws IOException;

    /**
     * Install a pattern into the catalog and load it.
     * <p>
     * If the pattern already exists in the catalog, it is replaced.
     * </p>
     * @param newPattern a valid <i>.umlt<i> file.
     * 
     * @throws java.io.IOException when the given file doesn't contain a valid pattern.
     */
    @objid ("c2757188-36ba-4048-ac0e-b18923d08bdd")
    void addPattern(Path patternPath) throws IOException;

    /**
     * Get all available patterns.
     * 
     * @return a collection of pattern names.
     */
    @objid ("b917660c-083f-471f-8ab2-055f8e215760")
    Collection<String> getPatterns();

    /**
     * Get patterns applicable on these elements from the catalog.
     * 
     * @param elements the model elements to filter the patterns with.
     * @return a collection of pattern names.
     */
    @objid ("64a4fcfa-13c0-479f-a118-f71046f67cb5")
    Collection<String> getPatterns(Collection<MObject> elements);

    /**
     * Remove a pattern from the catalog.
     * @param pattern the pattern to remove.
     * 
     * @throws java.security.InvalidParameterException when no pattern with this name is found in the catalog.
     */
    @objid ("3f273794-0ca9-4cb2-bd75-2b80c7474771")
    void removePattern(String patternName) throws InvalidParameterException;

    /**
     * Execute a pattern with the given parameters.
     * @param pattern the name of the pattern to execute.
     * 
     * @param parameters the parameters for the pattern to run.
     * @throws java.io.IOException when the pattern loading fails.
     * @throws java.security.InvalidParameterException when no pattern with this name is found in the catalog.
     */
    @objid ("3ae8bff3-f0e6-481f-9f40-573275816fbc")
    void applyPattern(String patternName, Map<String, Object> parameters) throws IOException, InvalidParameterException;

    /**
     * Check that all module and model components dependencies are resolved before applying a pattern.
     * @param pattern the name of the pattern to execute.
     * 
     * @return <code>true</code> when all module and model components the pattern depends on are resolved. <code>false</code> if at least one of them is missing.
     * @throws java.io.IOException when the pattern loading fails.
     * @throws java.security.InvalidParameterException when no pattern with this name is found in the catalog.
     */
    @objid ("afede9f3-763f-413b-8aca-e001508e071a")
    boolean canApplyPattern(String patternName) throws IOException, InvalidParameterException;

    /**
     * Execute a pattern with the given parameters.
     * 
     * @param patternPath the jar of the pattern to execute.
     * @param parameters the parameters for the pattern to run.
     * @throws java.io.IOException when the pattern loading fails.
     */
    @objid ("708a8da3-dfa9-42a8-a635-c2e9a0d41871")
    void applyPattern(Path patternPath, Map<String, Object> parameters) throws IOException;

}
