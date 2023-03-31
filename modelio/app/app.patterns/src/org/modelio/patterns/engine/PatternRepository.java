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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.ReadOnlyFileSystemException;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.pattern.IPatternService.PatternException;
import org.modelio.api.module.IPeerModule;
import org.modelio.patterns.api.IPatternRepository;
import org.modelio.patterns.model.CategoryData;
import org.modelio.patterns.model.RuntimePattern;
import org.modelio.patterns.model.information.Category;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of {@link IPatternRepository} based on a directory.
 */
@objid ("9a7838f1-dea0-4f8a-bc86-cbd2ea279f15")
class PatternRepository implements IPatternRepository {
    @objid ("73df0612-b84a-4321-95d9-1aad0895bd25")
    private String catalogName;

    @objid ("672bc535-9235-4398-985b-1fc8645d90dc")
    private Boolean isReadOnly;

    @objid ("0bb67a08-109b-4bc0-a77a-ed235d01cc64")
    private Map<String, CategoryData> categoryMap;

    @objid ("d9e598a4-ccf8-4e29-a544-113adc319a52")
    private Set<RuntimePattern> patterns;

    @objid ("5199269e-6a53-4547-ab05-9d7ad84570f8")
    private Path repositoryPath;

    @objid ("9fb72550-6f5a-4c91-8f35-374f011f5857")
    @Override
    public RuntimePattern addPattern(Path newPattern) throws PatternException {
        if (this.isReadOnly) {
            throw new ReadOnlyFileSystemException();
        }
        
        // Copy the file into the repository
        Path target = this.repositoryPath.resolve(newPattern.toFile().getName());
        if (!target.equals(newPattern)) {
            try {
                Files.copy(newPattern, target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new PatternException(e);
            }
        }
        
        // Load the pattern
        return loadPattern(target);
    }

    @objid ("7a718ede-db6b-460e-aa25-f34e003e13ac")
    @Override
    public Set<String> getAvailableLibraries() {
        Set<String> availableLibraries = new HashSet<>();
        for (MObject root : Modelio.getInstance().getModelingSession().getModel().getLibraryRoots()) {
            availableLibraries.add(root.getName());
        }
        return availableLibraries;
    }

    @objid ("67852a72-d859-4822-b9d4-c3a7998778e4")
    @Override
    public Set<String> getAvailableModules() {
        Set<String> availableModules = new HashSet<>();
        for (IPeerModule module : Modelio.getInstance().getModuleService().getAllPeerModules()) {
            availableModules.add(module.getName());
        }
        return availableModules;
    }

    @objid ("4656c2a6-6f62-40e5-82da-002590df992e")
    @Override
    public Collection<CategoryData> getCategories() {
        return Collections.unmodifiableCollection(this.categoryMap.values());
    }

    @objid ("2ee9cdab-383f-4652-9df6-9eb6f09afcb5")
    @Override
    public Collection<CategoryData> getCategories(Collection<MObject> elements) {
        final Set<String> availableLibraries = getAvailableLibraries();
        final Set<String> availableModules = getAvailableModules();
        
        Collection<CategoryData> relevantCategories = new ArrayList<>();
        
        for (CategoryData candidateCategory : getCategories()) {
            for (RuntimePattern p : candidateCategory.getPatterns()) {
                if (p.isRunnableOn(elements) && p.isValid(availableLibraries, availableModules)) {
                    // As we have at least one applicable pattern in the category the category is relevant and we can leave the
                    // loop
                    relevantCategories.add(candidateCategory);
                    break;
                }
            }
        }
        return relevantCategories;
    }

    /**
     * Get the catalog's name.
     */
    @objid ("1c6802c8-ffd8-41c3-85bd-a70869d23ad7")
    public String getName() {
        return this.catalogName;
    }

    @objid ("b9567262-e37a-44f4-bbdc-d221a286735d")
    @Override
    public RuntimePattern getPattern(String name) {
        for (RuntimePattern entry : this.patterns) {
            if (entry.getInfos().getName().equals(name)) {
                return entry;
            }
        }
        return null;
    }

    @objid ("b4a7d74c-c525-40fb-acf6-1e69db90d462")
    @Override
    public Collection<RuntimePattern> getPatterns() {
        return Collections.unmodifiableCollection(this.patterns);
    }

    @objid ("9694c7f7-5acd-4769-b5a1-0d8735760b0a")
    @Override
    public Collection<RuntimePattern> getPatterns(Collection<MObject> elements) {
        final Set<String> availableLibraries = getAvailableLibraries();
        final Set<String> availableModules = getAvailableModules();
        
        Collection<RuntimePattern> ret = new ArrayList<>();
        for (RuntimePattern p : getPatterns()) {
            if (p.isRunnableOn(elements) && p.isValid(availableLibraries, availableModules)) {
                ret.add(p);
            }
        }
        return ret;
    }

    @objid ("2a589a06-3181-47e9-86e7-d46be18ab119")
    @Override
    public Path getRepositoryPath() {
        return this.repositoryPath;
    }

    @objid ("696be61a-0fcb-41b0-aa5f-824d81ded80a")
    @Override
    public void reloadPatterns() {
        this.patterns = new TreeSet<>();
        this.categoryMap = new HashMap<>();
        
        try (Stream<Path> fileWalker = Files.walk(this.repositoryPath)) {
            fileWalker
            .filter(Files::isRegularFile)
            .filter(sub -> sub.toString().endsWith(".umlt"))
            .forEach(sub -> {
                try {
                    loadPattern(sub);
                } catch (PatternException e) {
                    Patterns.LOG.error("Invalid '%s' pattern: %s" ,sub.getFileName(), e.getCause() instanceof IOException ? FileUtils.getLocalizedMessage((IOException) e.getCause()) : e.getMessage());
                    Patterns.LOG.debug(e);
                }
            });
        } catch (IOException e1) {
            Patterns.LOG.error("Unable to load pattern catalog from '%s': %s" , this.repositoryPath, FileUtils.getLocalizedMessage(e1));
            Patterns.LOG.debug(e1);
        }
        
    }

    @objid ("934f5482-9a41-457f-becf-4e7cdf37912b")
    @Override
    public void removePattern(RuntimePattern pattern) {
        Path patternPath = pattern.getPatternPath();
        try {
            if (Files.isWritable(patternPath)) {
                Files.delete(patternPath);
                reloadPatterns();
            }
        } catch (IOException e) {
            Patterns.LOG.error("Unable to delete '%s' pattern : %s" , patternPath, FileUtils.getLocalizedMessage(e));
            Patterns.LOG.debug(e);
        }
        
    }

    @objid ("6abce19e-4353-47a6-bbd3-c38a84507ac9")
     PatternRepository(String catalogName, Path aPath, Boolean isReadOnly) {
        this.catalogName = catalogName;
        this.isReadOnly = isReadOnly;
        
        this.repositoryPath = aPath;
        this.repositoryPath.toFile().mkdirs();
        
        reloadPatterns();
        
    }

    @objid ("89fdb0fc-2055-4be4-b9c2-bdc1eda9c4af")
    private void initCategories(RuntimePattern pattern) {
        if (pattern.isValid(getAvailableLibraries(), getAvailableModules())) {
            List<Category> categories = pattern.getCategories();
        
            for (Category cat : categories) {
                CategoryData category = this.categoryMap.get(cat.getName());
                if (category == null) {
                    category = new CategoryData(cat);
                    this.categoryMap.put(cat.getName(), category);
        
                    category.addPattern(pattern);
                } else {
        
                    category.addPattern(pattern);
                }
            }
        } else {
            CategoryData category = this.categoryMap.get("Invalid");
            if (category == null) {
                final Category cat = new Category();
                cat.setName("Invalid");
        
                category = new CategoryData(cat);
                this.categoryMap.put("Invalid", category);
                category.addPattern(pattern);
            }
        }
        
    }

    @objid ("83ff1338-806a-48eb-9094-19041f5070a5")
    private RuntimePattern loadPattern(Path patternFile) throws PatternException {
        RuntimePattern patternInfo = new RuntimePattern(patternFile);
        
        // Make sure an old version of the pattern is not already registered
        if (this.patterns.contains(patternInfo)) {
            this.patterns.remove(patternInfo);
        }
        
        // Insert new pattern
        this.patterns.add(patternInfo);
        
        initCategories(patternInfo);
        return patternInfo;
    }

}
