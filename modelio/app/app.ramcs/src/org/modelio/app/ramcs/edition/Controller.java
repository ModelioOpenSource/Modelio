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

package org.modelio.app.ramcs.edition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.app.ramcs.plugin.AppRamcs;
import org.modelio.gproject.ramc.core.model.ModelComponent;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.modelermodule.api.default_.standard.artifact.ModelComponentArchive;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;

@objid ("6355067a-290c-42ba-8bc5-1b6e753d4255")
class Controller {
    @objid ("984a1ca3-9f23-49cb-99fe-eb4392fc37ff")
    private static final String RAMC_FILE_EXTENSION = ".ramc";

    @objid ("384b42ba-16be-4fde-840e-e7e0b4e99066")
    private static final Pattern namePattern = Pattern.compile("[\\p{L}\\p{N}\\._ ]+");

    @objid ("9b119718-a4ae-4847-bc9e-15802ce9c6bb")
    private EditRamcDialog dialog;

    @objid ("9c9939c3-ab97-426b-a650-50a306e03180")
    private RamcModel model;

    @objid ("75d8f3d8-6190-4c00-af79-46446fc6e72f")
    public Controller(EditRamcDialog dialog, RamcModel model) {
        this.dialog = dialog;
        this.model = model;
    }

    @objid ("31e1acd2-d7d2-4659-8183-376e0535be18")
    public void onModifyName(String value) {
        boolean valid = isValidName(value);
        if (valid) {
            this.model.setRamcName(value);
        }
        this.dialog.invalidateRamcName(!valid);
    }

    @objid ("b7432b87-8c7a-4dd1-bf68-6e8b50c7a3b7")
    public void onModifyVersion(String value) {
        boolean valid = isValidVersion(value);
        if (valid) {
            this.model.setRamcVersion(new Version(value));
        }
        this.dialog.invalidateRamcVersion(!valid);
    }

    @objid ("5a896a06-8a07-44b4-9c5a-55ce5a382514")
    public void onModifyDescription(String value) {
        this.model.setRamcDescription(value);
    }

    @objid ("196834e0-cdbc-4713-b590-207aefed5e98")
    private boolean isValidName(String name) {
        return Controller.namePattern.matcher(name).matches();
    }

    @objid ("c15ba24d-f693-4b04-936b-3dacce7f4ab6")
    @SuppressWarnings ("unused")
    private boolean isValidVersion(String version) {
        try {
            new Version(version);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @objid ("b06f3bf7-d017-463c-aa12-9dc86aa2a345")
    private boolean isValidPath(String path) {
        if (path == null || path.isEmpty()) {
            return false;
        }
        
        File file = new File(path);
        try {
            // Test file validity
            file.getCanonicalFile();
        } catch (@SuppressWarnings ("unused") IOException e) {
            return false;
        }
        
        if (path.endsWith(Controller.RAMC_FILE_EXTENSION)) {
            return true;
        } else {
            return false;
        }
    }

    @objid ("1d55f0ab-9ff4-40db-b5fe-e7da7a35d78d")
    public void onApply() {
        ICoreSession session = CoreSession.getSession(this.model.getArtifact());
        try (ITransaction t = session.getTransactionSupport().createTransaction("Apply changes to '" + this.model.getName() + "' RAMC.")) {
            // Force the metamodel version.
            Version v = this.model.getVersion();
            v = new Version(v.getMajorVersion(), v.getMinorVersion(), v.getBuildVersion());
            this.model.setRamcVersion(v);
        
            this.model.updateArtifact();
            t.commit();
        }
    }

    @objid ("c08a3bcb-8271-4f36-98b7-8a1c321eb6c1")
    public void onAddManifestation(List<Element> manifestedElements) {
        this.model.getExportedElements().addAll(manifestedElements);
    }

    @objid ("47448518-0fd2-45f7-9103-b96a98109800")
    public void onRemoveManifestedElement(Element element) {
        this.model.getExportedElements().remove(element);
    }

    @objid ("20ac496c-e3db-4241-adfe-756c11ed8516")
    public void onAddDependency(List<Element> dropedElements) {
        for (Element e : dropedElements) {
            if (isRamcArtifact(e)) {
                if (causeCycle((Artifact) e)) {
                    // TODO better error handling
                    AppRamcs.LOG.error("RAMC cyclic dependency detected between '%s' and '%s'", this.model.getArtifact().getName(), e.getName());
                } else {
                    this.model.getRequiredModelComponents().add(new RamcModel(this.model.getProjectPath(), (Artifact) e));
                }
        
            }
        }
    }

    @objid ("9045efc4-e5af-46f1-b5c5-b3c4c0cf77fc")
    public void onRemoveDependency(ModelComponent modelComponent) {
        this.model.getRequiredModelComponents().remove(modelComponent);
    }

    @objid ("8cc7df3d-362c-42d5-8739-3a03ba8419c3")
    private boolean isValidRamc(Element e) {
        return (e.isValid()
                && ModelComponentArchive.canInstantiate(e)
                && !causeCycle((Artifact) e));
    }

    @objid ("f44df655-1e0c-494d-a51c-dbd66535d9be")
    public boolean isRamcArtifact(Element e) {
        return (e.isValid()
                && (ModelComponentArchive.canInstantiate(e))
                && ((Artifact) e).isStereotyped(ModelComponentArchive.MdaTypes.STEREOTYPE_ELT));
    }

    @objid ("34d1d26a-e95d-4f1e-87b1-603c5004d06a")
    private boolean causeCycle(Artifact artifact) {
        List<ElementImport> elementImports = artifact.getOwnedImport();
        NameSpace importedNamespace = null;
        
        for (ElementImport elementImport : elementImports) {
            importedNamespace = elementImport.getImportedElement();
            if (isRamcArtifact(importedNamespace)) {
                if (importedNamespace.equals(this.model.getArtifact())) {
                    return true;
                } else {
                    return causeCycle((Artifact) importedNamespace);
                }
            }
        }
        return false;
    }

    @objid ("872e6c0b-9dbe-4c22-b79d-651356b02f1d")
    public void onContributorChange(Object[] checkedElements) {
        // Synchronize contributing modules from checked elements
        Map<String, String> contributingModules = this.model.getContributingModules();
        
        // Add new checked elements to the map
        List<String> checkedContributors = new ArrayList<>();
        for (Object o : checkedElements) {
            IModule m = (IModule) o;
            String moduleName = m.getName();
            if (!contributingModules.containsKey(moduleName)) {
                contributingModules.put(moduleName, m.getVersion().toString());
            }
            checkedContributors.add(moduleName);
        }
        
        // Remove unchecked elements from the map
        for (String moduleName : new ArrayList<>(contributingModules.keySet())) {
            if (!checkedContributors.contains(moduleName)) {
                contributingModules.remove(moduleName);
            }
        }
    }

    @objid ("f3f6fe93-b854-4d81-a18c-343e2883ebff")
    public void onModifyProvider(String value) {
        this.model.setProvider(value);
    }

}
