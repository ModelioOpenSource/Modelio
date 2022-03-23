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
package org.modelio.patterns.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.pattern.IPatternService.PatternException;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.patterns.exporter.PatternModelAnalysis;
import org.modelio.patterns.exporter.PatternModelAnalysis.ReportStringParameter;
import org.modelio.patterns.exporter.impl.PatternExporter;
import org.modelio.patterns.model.ProfileUtils.ParameterModelData;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerTagTypes;
import org.modelio.patterns.model.information.Category;
import org.modelio.patterns.model.information.ConstantParameter;
import org.modelio.patterns.model.information.ElementParameter;
import org.modelio.patterns.model.information.ModuleDependency;
import org.modelio.patterns.model.information.Parameter;
import org.modelio.patterns.model.information.Pattern;
import org.modelio.patterns.model.information.RAMCDependency;
import org.modelio.patterns.model.information.RootParameter;
import org.modelio.patterns.model.information.StringParameter;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.patterns.utils.ImageRegistry;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.osgi.framework.Bundle;
import org.xml.sax.InputSource;

/**
 * ???
 */
@objid ("5bc73ac7-2e97-4395-9d6b-45298e04060f")
public class RuntimePattern implements Comparable<RuntimePattern> {
    /**
     * Pattern metadatas, loaded with Jaxb.
     */
    @objid ("c200bc98-bca4-4773-a098-7e260e95763a")
    private Pattern jaxbPattern;

    @objid ("5d49af65-0408-4695-99bc-cc75f0345d08")
    private Package modelPattern;

    @objid ("0ea67edc-16a0-4454-a945-2056b1dbc76a")
    private Path patternPath;

    /**
     * Build a new PatternData instance from a model element.
     * @param modelPattern can be an existing model pattern or any other element.
     */
    @objid ("9ec8fa2d-024f-4796-bec4-43863a731b22")
    public  RuntimePattern(Package modelPattern) {
        this.modelPattern = modelPattern;
        this.jaxbPattern = createJaxbModelFromUmlModel(modelPattern);
        
        GProject openedProject = GProject.getProject(modelPattern);
        this.patternPath = Patterns.getProjectPatternsDirectory(openedProject)
                .resolve(this.jaxbPattern.getName() + "_" + this.jaxbPattern.getVersion() + ".umlt");
        
    }

    /**
     * Build a new PatternData instance from a deployed pattern.
     * @param patternPath the file to load the pattern from.
     * @throws PatternException when the pattern metadata are invalid or can't be loaded.
     */
    @objid ("40231e6d-0350-4e8d-bf10-340ea4f9b276")
    public  RuntimePattern(Path patternPath) throws PatternException {
        this.patternPath = patternPath;
        
        try (FileSystem fs = FileSystems.newFileSystem(this.patternPath, this.getClass().getClassLoader())) {
            Path manifest = fs.getPath("Manifest.xml");
            JAXBContext jc = JAXBContext.newInstance("org.modelio.patterns.model.information");
            Unmarshaller um = jc.createUnmarshaller();
        
            InputSource is = new InputSource(Files.newInputStream(manifest));
            is.setSystemId(manifest.toString());
        
            this.jaxbPattern = (Pattern) um.unmarshal(is);
        } catch (IOException | JAXBException e) {
            throw new PatternException(e);
        }
        
    }

    @objid ("41d731d1-b620-4606-838c-befcbc7050b7")
    private void addConstantParameter(Pattern jaxbPattern, ModelElement element) {
        String uid = element.getUuid().toString();
        
        // Do not create a new element parameter if one this the same id already exists
        for (Object sub : jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof ConstantParameter) {
                Parameter dcElement = (Parameter) sub;
                if (uid.equals(dcElement.getId())) {
                    return;
                }
            }
        }
        
        ConstantParameter jaxbConstantParameter = new ConstantParameter();
        jaxbConstantParameter.setId(uid);
        jaxbConstantParameter.setMetaclass(element.getMClass().getQualifiedName());
        
        ParameterModelData parameterData = ProfileUtils.getParameterData(jaxbConstantParameter.getId(), this.modelPattern);
        String label, name, description;
        if (parameterData != null) {
            label = parameterData.label;
            name = parameterData.name;
            description = parameterData.description;
        } else {
            // Look for deprecated label & name
            label = element.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERNPARAMETER_PATTERNPARAMETER_LABEL);
            name = element.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERNPARAMETER_PATTERNPARAMETER_NAME);
            description = "";
        }
        
        // Empty label use root name
        if (label == null || label.isEmpty()) {
            label = element.getName();
        }
        if (name == null || name.isEmpty()) {
            name = element.getName();
        }
        
        jaxbConstantParameter.setName(name);
        jaxbConstantParameter.setLabel(label);
        jaxbConstantParameter.setDescription(description);
        
        jaxbPattern.getCategoryAndExternalDependencyAndParameter().add(jaxbConstantParameter);
        
    }

    @objid ("03cec0be-42a0-4b8e-9fa0-a7b3d20026bf")
    private void addElementParameter(Pattern jaxbPattern, ModelElement element) {
        String uid = element.getUuid().toString();
        
        // Do not create a new element parameter if one this the same id already exists
        for (Object sub : jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof ElementParameter || sub instanceof RootParameter) {
                Parameter dcElement = (Parameter) sub;
                if (uid.equals(dcElement.getId())) {
                    return;
                }
            }
        }
        
        ElementParameter jaxbElementParameter = new ElementParameter();
        jaxbElementParameter.setId(uid);
        jaxbElementParameter.setMetaclass(element.getMClass().getQualifiedName());
        
        ParameterModelData parameterData = ProfileUtils.getParameterData(jaxbElementParameter.getId(), this.modelPattern);
        String label, name, description;
        if (parameterData != null) {
            label = parameterData.label;
            name = parameterData.name;
            description = parameterData.description;
        } else {
            // Look for deprecated label & name
            label = element.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERNPARAMETER_PATTERNPARAMETER_LABEL);
            name = element.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERNPARAMETER_PATTERNPARAMETER_NAME);
            description = "";
        }
        
        // Empty label use root name
        if (label == null || label.isEmpty()) {
            label = element.getName();
        }
        if (name == null || name.isEmpty()) {
            name = element.getName();
        }
        
        jaxbElementParameter.setName(name);
        jaxbElementParameter.setLabel(label);
        jaxbElementParameter.setDescription(description);
        
        jaxbPattern.getCategoryAndExternalDependencyAndParameter().add(jaxbElementParameter);
        
    }

    @objid ("517f03df-6b14-4415-a51d-5dec0cc98b64")
    private void addModuleDependency(Pattern jaxbPattern, ModuleComponent element) {
        if (element == null || element.getName().equals(ProfileUtils.MODULE_NAME)) {
            return;
        }
        
        // Do not create a new module dependency if one this the same name already exists
        for (Object sub : jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof ModuleDependency) {
                ModuleDependency dcElement = (ModuleDependency) sub;
                if (dcElement.getName().equals(element.getName())) {
                    return;
                }
            }
        }
        
        ModuleDependency eDependency = new ModuleDependency();
        eDependency.setName(element.getName());
        eDependency.setVersion(element.getMajVersion() + "." + element.getMinVersion() + "." + element.getMinMinVersion());
        
        jaxbPattern.getCategoryAndExternalDependencyAndParameter().add(eDependency);
        
    }

    @objid ("5ef62b56-8247-4feb-a671-634f756510e3")
    private void addRAMCDependency(Pattern jaxbPattern, ModelElement element) {
        addConstantParameter(jaxbPattern, element);
        
        Project ramcomponent = getRamComponent(element);
        
        // Do not create a new ramc dependency if one this the same name already exists
        if (ramcomponent != null) {
            for (Object sub : jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
                if (sub instanceof RAMCDependency) {
                    RAMCDependency dcElement = (RAMCDependency) sub;
                    if (dcElement.getName().equals(ramcomponent.getName())) {
                        return;
                    }
                }
            }
        
            RAMCDependency eDependency = new RAMCDependency();
            eDependency.setName(ramcomponent.getName());
            jaxbPattern.getCategoryAndExternalDependencyAndParameter().add(eDependency);
        }
        
    }

    @objid ("b01b8e9a-f4a3-48de-a3c0-95f352fe356a")
    private void addStringParameter(Pattern jaxbPattern, String name, MRef mRef) {
        // Do not create a new string parameter if one this the same name already exists
        for (Object sub : jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof StringParameter) {
                StringParameter dcElement = (StringParameter) sub;
                if (dcElement.getName().equals(name)) {
                    return;
                }
            }
        }
        
        StringParameter sParameter = new StringParameter();
        sParameter.setId(mRef.uuid.toString());
        sParameter.setMetaclass(mRef.mc);
        
        ParameterModelData parameterData = ProfileUtils.getParameterData(name, this.modelPattern);
        String label, description;
        if (parameterData != null) {
            label = parameterData.label;
            description = parameterData.description;
        } else {
            // Look for deprecated label
            label = ProfileUtils.getStringParameterLabel(name, this.modelPattern);
            description = "";
        }
        
        // Empty label use root name
        if (label == null || label.isEmpty()) {
            label = name;
        }
        
        sParameter.setName(name);
        sParameter.setLabel(label);
        sParameter.setDescription(description);
        
        jaxbPattern.getCategoryAndExternalDependencyAndParameter().add(sParameter);
        
    }

    /**
     * Execute a pattern with the given parameters.
     * @param parameters the parameters for the pattern to run.
     * @throws PatternException when the pattern execution fails.
     */
    @objid ("3057cba5-8c8f-4874-a3db-38ecbd28e325")
    public void applyPattern(Map<String, Object> parameters, ICoreSession coreSession, MObject refElement) throws PatternException {
        // Make sure constants are initialized before applying the pattern
        // TODO is this initialization at the right place?
        MMetamodel metamodel = refElement.getMClass().getMetamodel();
        for (Parameter param : getParameters()) {
            if (!(param instanceof RootParameter)) {
                if (param instanceof ConstantParameter) {
                    String name = param.getName();
                    ModelElement element = (ModelElement) coreSession.getModel().findById(metamodel.getMClass(param.getMetaclass()), param.getId());
                    parameters.put(name, element);
                } else if (param instanceof ElementParameter) {
                    String name = param.getName();
                    if (!parameters.containsKey(name)) {
                        ModelElement element = (ModelElement) coreSession.getModel().findById(metamodel.getMClass(param.getMetaclass()), param.getId());
                        parameters.put(name, element);
                    }
                }
            }
        }
        
        IPattern result;
        try {
            result = getExecutablePattern();
            if (result != null) {
                result.createModel(refElement, coreSession, parameters);
            }
        } catch (IOException e) {
            throw new PatternException(e);
        }
        
    }

    @objid ("23f34053-353c-4573-ba60-07522dbfde5f")
    @Override
    public int compareTo(RuntimePattern t2) {
        String nameT1 = this.jaxbPattern.getName();
        String nameT2 = t2.jaxbPattern.getName();
        return nameT1.compareTo(nameT2);
    }

    @objid ("4348addf-1d7f-44a4-a0ce-076fe6f7f49f")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }
        RuntimePattern other = (RuntimePattern) obj;
        if (this.jaxbPattern == null) {
            if (other.jaxbPattern != null) {
                return false;
            }
        } else if (!this.jaxbPattern.getName().equals(other.jaxbPattern.getName())) {
            return false;
        }
        return true;
    }

    @objid ("d1a78836-97eb-4938-a96c-512431982601")
    public List<Category> getCategories() {
        List<Category> res = new ArrayList<>();
        for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof Category) {
                Category category = (Category) sub;
        
                res.add(category);
        
                while (category.getSubCategory() != null) {
                    category = category.getSubCategory();
                    res.add(category);
                }
            }
        }
        
        if (res.isEmpty()) {
            Category defaultCategory = new Category();
            defaultCategory.setName(Patterns.I18N.getString("PropertyDefinition.DefaultCategory"));
            defaultCategory.setDescription(Patterns.I18N.getString("PropertyDefinition.DefaultCategoryDescription"));
            res.add(defaultCategory);
        }
        return res;
    }

    /**
     * Load the executable part of a pattern.
     * @return a {@link IPattern} ready to use.
     * @throws IOException when pattern loading fails.
     */
    @objid ("6b7c4853-8523-4efa-9787-d1f3305680cf")
    private IPattern getExecutablePattern() throws IOException {
        try (FileSystem fs = FileSystems.newFileSystem(this.patternPath, this.getClass().getClassLoader())) {
            return loadPatternFromJar(fs.getPath("Pattern.jar"));
        }
        
    }

    @objid ("c4dab12b-a47d-4ae6-82a4-5b0bfaf0580a")
    public String getExternalDependency() {
        StringBuilder result = new StringBuilder();
        for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof ModuleDependency) {
                ModuleDependency moDependency = (ModuleDependency) sub;
                result.append("module :  ");
                result.append(moDependency.getName());
                result.append("    version : ");
                result.append(moDependency.getVersion());
                result.append("\n");
        
            }
        }
        
        for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof RAMCDependency) {
                RAMCDependency rcDependency = (RAMCDependency) sub;
                result.append("ramc     :  ");
                result.append(rcDependency.getName());
                result.append("\n");
            }
        }
        return result.toString();
    }

    @objid ("16b433de-90f8-4d04-8151-ef31b7173492")
    public String getIconPath() {
        String image = this.jaxbPattern.getIcone();
        
        if (image != null && !image.isEmpty()) {
            File imageFile = new File(image);
            try (FileSystem fs = FileSystems.newFileSystem(this.patternPath, this.getClass().getClassLoader())) {
                Path imagePath = fs.getPath("");
                return imagePath.toUri().toString() + "res" + File.separator + imageFile.getName();
            } catch (Exception e) {
                Patterns.LOG.debug(e);
            }
        }
        
        Bundle bundle = Platform.getBundle(Patterns.PLUGIN_ID);
        String s = "platform:/plugin/" + bundle.getSymbolicName() + "/" + "icons/pattern.png";
        URL url = null;
        try {
            url = new URL(s);
            URL fileURL = FileLocator.toFileURL(url);
            return URIUtil.toURI(fileURL).toString();
        } catch (Exception e) {
            Patterns.LOG.debug(e);
        }
        return null;
    }

    @objid ("9f005e92-cca7-4a1e-be96-c887ce864214")
    public Image getImage() {
        String image = this.jaxbPattern.getImage();
        
        if (image != null && !image.isEmpty()) {
            File imageFile = new File(image);
            try (FileSystem fs = FileSystems.newFileSystem(this.patternPath, this.getClass().getClassLoader())) {
                Path imagePath = fs.getPath("");
                return ImageRegistry.getImage(imagePath.toUri().toString() + "res\\" + imageFile.getName());
            } catch (Exception e) {
                Patterns.LOG.debug(e);
            }
        }
        return ImageRegistry.getImage("icons/pattern.png");
    }

    @objid ("4397dffa-5447-46ea-b6e6-ac0fa8ff12d6")
    public Pattern getInfos() {
        return this.jaxbPattern;
    }

    @objid ("d159935d-4226-4810-b3e8-3c0b7319afc4")
    public Package getModelPattern() {
        return this.modelPattern;
    }

    @objid ("7bc17284-4ba7-4e50-83f2-19d3fcc45e76")
    public List<Parameter> getParameters() {
        List<Parameter> strings = new ArrayList<>();
        List<Parameter> owners = new ArrayList<>();
        List<Parameter> objects = new ArrayList<>();
        List<Parameter> constant = new ArrayList<>();
        
        for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof Parameter) {
                if (sub instanceof RootParameter) {
                    owners.add((Parameter) sub);
                } else if (sub instanceof StringParameter) {
                    strings.add((Parameter) sub);
                } else if (sub instanceof ElementParameter) {
                    objects.add((Parameter) sub);
                } else if (sub instanceof ConstantParameter) {
                    constant.add((Parameter) sub);
                }
            }
        }
        
        owners.addAll(strings);
        owners.addAll(objects);
        owners.addAll(constant);
        return owners;
    }

    @objid ("12c172de-827b-42f8-a7f2-cbbc4103a24f")
    public List<Parameter> getPatternOwner() {
        List<Parameter> res = new ArrayList<>();
        for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof RootParameter) {
                res.add((Parameter) sub);
            }
        }
        return res;
    }

    @objid ("320a4dca-df20-4ea1-9f35-5bd8d9dc9726")
    public Path getPatternPath() {
        return this.patternPath;
    }

    @objid ("9d56a47c-6144-400b-a1be-2ae169132bb4")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.jaxbPattern == null ? 0 : this.jaxbPattern.getName().hashCode());
        return result;
    }

    @objid ("12e99295-400d-4409-87f0-ba29f686e866")
    public boolean isRunnableOn(Collection<MObject> elements) {
        for (Object data : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (data instanceof RootParameter) {
                RootParameter root = (RootParameter) data;
        
                for (MObject elt : elements) {
                    MMetamodel metamodel = elt.getMClass().getMetamodel();
                    MClass rootMetaclass = metamodel.getMClass(root.getMetaclass());
                    if (elt.getMClass() == rootMetaclass || elt.getMClass().hasBase(rootMetaclass)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @objid ("d5554a5f-6198-4991-b6e1-feb519166b48")
    public boolean isValid(Collection<String> availableLibraries, Collection<String> availableModules) {
        for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof RAMCDependency) {
                RAMCDependency dp = (RAMCDependency) sub;
                if (!availableLibraries.contains(dp.getName())) {
                    return false;
                }
            } else if (sub instanceof ModuleDependency) {
                ModuleDependency dp = (ModuleDependency) sub;
                if (!availableModules.contains(dp.getName())) {
                    return false;
                }
            }
        }
        return true;
    }

    @objid ("584e9191-03b4-4ed7-98af-d1a7ca6920c8")
    public String resolveMissingDependency(Collection<String> availableLibraries, Collection<String> availableModules) {
        String result = "";
        
        for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof RAMCDependency) {
                RAMCDependency rcDependency = (RAMCDependency) sub;
                if (!availableLibraries.contains(rcDependency.getName())) {
                    result += "ramc     :  " + rcDependency.getName() + "    version : " + rcDependency.getVersion() + "\n";
                }
        
            } else if (sub instanceof ModuleDependency) {
                ModuleDependency moDependency = (ModuleDependency) sub;
                if (!availableModules.contains(moDependency.getName())) {
                    result += "module :  " + moDependency.getName() + "    version : " + moDependency.getVersion() + "\n";
                }
            }
        }
        return result;
    }

    @objid ("4e9f3654-5df8-4646-a2a4-f3cff2f09e56")
    public void setCategory(String value) {
        if (value != null && !value.isEmpty()) {
            Category category = null;
            for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
                if (sub instanceof Category) {
                    category = (Category) sub;
                }
            }
        
            if (category == null) {
                category = new Category();
                this.jaxbPattern.getCategoryAndExternalDependencyAndParameter().add(category);
            }
        
            category.setName(value);
        } else {
        
            Category category = null;
            for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
                if (sub instanceof Category) {
                    category = (Category) sub;
                }
            }
            if (category != null) {
                this.jaxbPattern.getCategoryAndExternalDependencyAndParameter().remove(category);
            }
        }
        
    }

    @objid ("c73831f2-dd37-494a-9646-d8d455b6c603")
    public void setParameter(String name, String label) {
        for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof Parameter) {
                Parameter param = (Parameter) sub;
                if (param.getName() != null && param.getName().equals(name)) {
                    param.setLabel(label);
                }
            }
        }
        
    }

    @objid ("3f3edfa3-3563-42c7-9d1a-f0685f1f5a6e")
    public void setPatternPath(Path patternPath) {
        this.patternPath = patternPath;
    }

    @objid ("b912169b-afd0-4354-9ee6-8d2e6aceed15")
    private String getNamespace(String jarEntryName) {
        String namespace = jarEntryName;
        namespace = namespace.replaceAll("/", "\\.");
        String separator = "/";
        
        int index = namespace.lastIndexOf(separator);
        namespace = namespace.substring(index + 1);
        
        if (namespace.endsWith(".class")) {
            namespace = namespace.substring(0, namespace.length() - 6);
        }
        return namespace;
    }

    @objid ("35118c36-174c-4ed3-b042-a1eb7fd6ab22")
    private Project getRamComponent(MObject element) {
        if (element != null) {
            if (element instanceof Project) {
                return (Project) element;
            } else {
                return getRamComponent(element.getCompositionOwner());
            }
        }
        return null;
    }

    @objid ("aef03613-c42e-4650-a771-d0fea013b8bc")
    private Pattern createJaxbModelFromUmlModel(Package pattern) {
        Pattern jaxbPattern = new Pattern();
        
        if (jaxbPattern.getName() == null || jaxbPattern.getName().isEmpty()) {
            jaxbPattern.setName(pattern.getName());
        }
        
        if (jaxbPattern.getDescription() == null || jaxbPattern.getDescription().isEmpty()) {
            String desc = pattern.getNoteContent("ModelerModule", "description");
            if (desc == null) {
                desc = pattern.getName() + " Pattern";
            }
            jaxbPattern.setDescription(desc);
        }
        
        String version = pattern.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERN_TEMPLATE_VERSION);
        if (version == null || version.isEmpty()) {
            version = "1.0.00";
        }
        jaxbPattern.setVersion(version);
        
        String image = pattern.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERN_TEMPLATE_IMAGE);
        if (image == null) {
            image = "";
        }
        jaxbPattern.setImage(image);
        
        Category parent = null;
        List<String> categories = pattern.getTagValues(ProfileUtils.MODULE_NAME,
                PatternDesignerTagTypes.PATTERN_TEMPLATE_CATEGORIES);
        if (categories != null) {
            for (int i = 0; i < categories.size(); i = i + 3) {
                Category category = new Category();
                category.setName(categories.get(i));
                if (categories.size() > i + 1) {
                    category.setDescription(categories.get(i + 1));
                }
        
                if (categories.size() > i + 2) {
                    category.setImage(categories.get(i + 2));
                }
        
                if (parent == null) {
                    jaxbPattern.getCategoryAndExternalDependencyAndParameter().add(category);
                } else {
                    parent.setSubCategory(category);
                }
                parent = category;
            }
        }
        
        // Fill jaxb model parameters from model pattern
        final PatternModelAnalysis analysis = new PatternExporter().runAnalysis(this.modelPattern);
        
        // Create roots in Jaxb model from pattern UML Model
        for (ModelElement elt : analysis.getRootParameters()) {
            addRootParameter(jaxbPattern, elt);
        }
        
        for (ModelElement element : analysis.getElementParameters()) {
            addElementParameter(jaxbPattern, element);
        }
        
        for (ModuleComponent module : analysis.getModuleDependencies()) {
            addModuleDependency(jaxbPattern, module);
        }
        
        for (ModelElement element : analysis.getRamcDependencies()) {
            addRAMCDependency(jaxbPattern, element);
        }
        
        for (ReportStringParameter param : analysis.getStringParameters()) {
            addStringParameter(jaxbPattern, param.name, param.mRef);
        }
        return jaxbPattern;
    }

    @objid ("b13f24a6-e2fb-4e2e-b31c-04777bfae50e")
    private IPattern loadPatternFromJar(Path jarPath) throws IOException {
        try {
            // Create a temp file for the jar, it must not be part of a zip...
            Path jar = Files.createTempFile("Pattern", ".jar");
            Files.copy(jarPath, jar, StandardCopyOption.REPLACE_EXISTING);
            jar.toFile().deleteOnExit();
        
            // Dynamically load IPattern instance from the jar
            IPattern ret = null;
            try (URLClassLoader loader = new URLClassLoader(new URL[] { jar.toFile().toURI().toURL() },
                    IPattern.class.getClassLoader());
                    JarInputStream jarStream = new JarInputStream(new FileInputStream(jar.toFile()));) {
                JarEntry jarEntry;
                for (jarEntry = jarStream.getNextJarEntry(); jarEntry != null; jarEntry = jarStream.getNextJarEntry()) {
                    if (jarEntry.getName().endsWith(".class")) { //$NON-NLS-1$
                        String metaclassNamespace = getNamespace(jarEntry.getName());
                        Class<?> metaclass = loader.loadClass(metaclassNamespace);
        
                        if (IPattern.class.isAssignableFrom(metaclass)) {
                            ret = (IPattern) metaclass.newInstance();
                        }
                    }
                }
            }
            return ret;
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new IOException("Unable to load pattern ", e);
        }
        
    }

    @objid ("26d8aed3-da86-4811-8942-7c09e6284999")
    public Image getIcon() {
        String image = this.jaxbPattern.getIcone();
        
        if (image != null && !image.isEmpty()) {
            File imageFile = new File(image);
            try (FileSystem fs = FileSystems.newFileSystem(this.patternPath, this.getClass().getClassLoader())) {
                Path imagePath = fs.getPath("/res/" + imageFile.getName());
                return ImageRegistry.getImage(imagePath);
            } catch (Exception e) {
                Patterns.LOG.debug(e);
            }
        }
        return ImageRegistry.getImage("icons/pattern.png");
    }

    @objid ("adb5efa8-c5cd-4a83-8881-285522445bb3")
    public String getName() {
        return this.jaxbPattern.getName();
    }

    @objid ("81fede4c-bc77-434b-8503-d8c2d485013d")
    private void addRootParameter(Pattern jaxbPattern, ModelElement elt) {
        // Always take the root's owner
        ModelElement root = (ModelElement) elt.getCompositionOwner();
        String uid = root.getUuid().toString();
        
        // Do not create a new root parameter if one this the same id already exists
        for (Object sub : jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof RootParameter) {
                RootParameter dcElement = (RootParameter) sub;
                if (uid.equals(dcElement.getId())) {
                    return;
                }
            }
        }
        
        // Create the Jaxb root parameter
        RootParameter jaxbRootParameter = new RootParameter();
        jaxbRootParameter.setMetaclass(root.getMClass().getQualifiedName());
        jaxbRootParameter.setId(uid);
        
        ParameterModelData parameterData = ProfileUtils.getParameterData(jaxbRootParameter.getId(), this.modelPattern);
        String label, name, description;
        if (parameterData != null) {
            label = parameterData.label;
            name = parameterData.name;
            description = parameterData.description;
        } else {
            // Look for deprecated label
            label = root.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERNPARAMETER_PATTERNPARAMETER_LABEL);
            name = root.getName();
            description = "";
        }
        
        // Empty label, use root name
        if (label == null || label.isEmpty()) {
            label = root.getName();
        }
        
        // Empty name, use root name
        if (name == null || name.isEmpty()) {
            name = root.getName();
        }
        
        jaxbRootParameter.setName(name);
        jaxbRootParameter.setLabel(label);
        jaxbRootParameter.setDescription(description);
        
        // Add it to Jaxb model
        jaxbPattern.getCategoryAndExternalDependencyAndParameter().add(jaxbRootParameter);
        
    }

    /**
     * Return the label of parameter 'paramName'
     * @return
     */
    @objid ("6fa4d075-1790-4340-b714-4e4b9e2a89fb")
    public String getParameterLabel(String paramName) {
        for (Object sub : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof Parameter) {
                Parameter param = (Parameter) sub;
                if (param.getName() != null && param.getName().equals(paramName)) {
                    return param.getLabel();
                }
            }
        }
        return null;
    }

    @objid ("17c43b50-4509-4878-bf86-726694634c3a")
    public String getVersion() {
        return this.jaxbPattern.getVersion();
    }

    @objid ("0092b553-9664-4d35-8452-674dc9e74134")
    public String getDescription() {
        return this.jaxbPattern.getDescription();
    }

    @objid ("c56c7c14-61e2-49f3-a774-e5fcc7efae0b")
    public String getCategory() {
        for (Object x : this.jaxbPattern.getCategoryAndExternalDependencyAndParameter()) {
            if (x instanceof Category) {
                return ((Category) x).getName();
            }
        }
        return "";
    }

    @objid ("95ec93f9-35b6-46f6-9aa1-8c4ad0688fb5")
    public void setName(String value) {
        this.jaxbPattern.setName(value);
    }

    @objid ("65f5b9ee-dc49-44ae-8a7f-6b6c86b9d59a")
    public void setDescription(String value) {
        this.jaxbPattern.setDescription(value);
    }

    @objid ("55b141d2-cdf0-4796-8183-efffe73fa9eb")
    public void setVersion(String value) {
        this.jaxbPattern.setVersion(value);
    }

    @objid ("bbbb50cb-227c-4fe7-afcd-3cfe26ef6d23")
    public void setIconPath(String iconPath) {
        if (iconPath != null && !iconPath.isEmpty()) {
            File imageFile = new File(iconPath);
            try (FileSystem fs = FileSystems.newFileSystem(this.patternPath, this.getClass().getClassLoader())) {
                Path fileInsideZipPath = fs.getPath("/res/" + imageFile.getName());
                Files.createDirectories(fileInsideZipPath);
                Files.copy(Paths.get(iconPath), fileInsideZipPath, StandardCopyOption.REPLACE_EXISTING);
        
                this.jaxbPattern.setIcone(fileInsideZipPath.toString());
            } catch (Exception e) {
                Patterns.LOG.debug(e);
            }
        }
        
    }

}
