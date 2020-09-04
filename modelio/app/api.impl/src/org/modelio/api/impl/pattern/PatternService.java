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

package org.modelio.api.impl.pattern;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.xml.bind.JAXBException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.api.modelio.pattern.IPatternService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerStereotypes;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.patterns.model.RuntimePattern;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("f853e772-63e5-4dba-b9bd-1110bd230d61")
public class PatternService implements IPatternService {
    @objid ("ca3f961e-438b-4023-bec6-d8414926bb75")
    private org.modelio.patterns.api.IPatternService patternService;

    @objid ("7b43fab9-c756-4cb1-aa8e-d9940040d90b")
    private IProjectService projectService;

    @objid ("272dd984-11d7-4315-a451-ae8c44da8c3f")
    public PatternService(final IEclipseContext eclipseContext) {
        this.patternService = eclipseContext.get(org.modelio.patterns.api.IPatternService.class);
        this.projectService = eclipseContext.get(IProjectService.class);
    }

    @objid ("7a484490-62c1-4bcc-85e8-c47109b7541f")
    @Override
    public void exportPattern(final Package modelPattern, final Path patternPath) throws IOException {
        if (modelPattern.isStereotyped(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERN)) {
            try {
                RuntimePattern pattern = new RuntimePattern(modelPattern);
                pattern.setPatternPath(patternPath.resolve(pattern.getName() + ".umlt"));
                this.patternService.exportPattern(pattern);
            } catch (JAXBException e) {
                throw new IOException("Invalid pattern", e);
            }
        } else {
            throw new InvalidParameterException("Invalid pattern");
        }
    }

    @objid ("6ccc33ca-ef6e-4064-a9c4-66456806499f")
    @Override
    public void addPattern(final Path patternPath) throws IOException {
        try {
            this.patternService.getCatalog().addPattern(patternPath);
        } catch (JAXBException e) {
            throw new IOException("Invalid pattern", e);
        }
    }

    @objid ("0a7963ae-b834-43c7-ac63-ec2e2df9e945")
    @Override
    public Collection<String> getPatterns() {
        Collection<String> ret = new ArrayList<>();
        for (RuntimePattern patternData : this.patternService.getCatalog().getPatterns()) {
            ret.add(patternData.getName());
        }
        return ret;
    }

    @objid ("30688978-688c-48dd-967f-b8ecb339fff0")
    @Override
    public Collection<String> getPatterns(final Collection<MObject> elements) {
        Collection<String> ret = new ArrayList<>();
        for (RuntimePattern patternData : this.patternService.getCatalog().getPatterns(elements)) {
            ret.add(patternData.getName());
        }
        return ret;
    }

    @objid ("9f3c7bc7-bd40-41cb-8267-677163795891")
    @Override
    public void removePattern(final String patternName) {
        RuntimePattern pattern = this.patternService.getCatalog().getPattern(patternName);
        if (pattern == null) {
            throw new InvalidParameterException("Invalid pattern " + patternName);
        }
        this.patternService.getCatalog().removePattern(pattern);
    }

    @objid ("eb279c38-1f10-488d-9609-677aa746ace7")
    @Override
    public boolean canApplyPattern(final String patternName) throws IOException {
        RuntimePattern pattern = this.patternService.getCatalog().getPattern(patternName);
        if (pattern == null) {
            throw new InvalidParameterException("Invalid pattern " + patternName);
        }
        return pattern.isValid(this.patternService.getCatalog().getAvailableLibraries(), this.patternService.getCatalog().getAvailableModules());
    }

    @objid ("9150cd8b-0bd2-4bb6-a369-971a9afe4dd7")
    @Override
    public void applyPattern(final String patternName, final Map<String, Object> parameters) throws IOException {
        RuntimePattern pattern = this.patternService.getCatalog().getPattern(patternName);
        if (pattern == null) {
            throw new InvalidParameterException("Invalid pattern " + patternName);
        }
        pattern.applyPattern(parameters, this.projectService.getSession(), getModelRoot());
    }

    @objid ("ba0855f4-91e0-4d41-8cce-1383100d6265")
    @Override
    public void applyPattern(final Path patternPath, final Map<String, Object> parameters) throws IOException {
        RuntimePattern pattern;
        try {
            pattern = new RuntimePattern(patternPath);
            pattern.applyPattern(parameters, this.projectService.getSession(), getModelRoot());
        } catch (JAXBException e) {
            throw new IOException("Invalid pattern", e);
        }
    }

    @objid ("80745b6d-debe-469b-a17b-01ce2d96fa1b")
    private MObject getModelRoot() {
        for (IProjectFragment fragment : this.projectService.getOpenedProject().getFragments()) {
            switch (fragment.getType()) {
            case EXML:
            case EXML_SVN:
                if(fragment.getRoots().size() > 0){
                    return fragment.getRoots().iterator().next();
                }
                break;
            default:
                break;
            }
        }
        return null;
    }

}
