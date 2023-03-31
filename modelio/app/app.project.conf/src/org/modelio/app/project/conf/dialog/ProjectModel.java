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
package org.modelio.app.project.conf.dialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.project.conf.dialog.urls.PropertiesUrlAdapter;
import org.modelio.app.project.conf.dialog.urls.UrlEntry;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.data.project.ProjectType;
import org.modelio.gproject.parts.module.GModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.vbasic.progress.NullProgress;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a7474af3-33f6-11e2-a514-002564c97630")
public class ProjectModel {
    @objid ("6a0b4eb7-937a-417a-9efc-93394ce24dbd")
    private final IGProject gProject;

    @objid ("357b55c4-0f90-4438-a772-4efeccb24fb0")
    private PropertiesUrlAdapter propertiesUrlAdapter;

    @objid ("a7477205-33f6-11e2-a514-002564c97630")
    public  ProjectModel(IGProject gProject) {
        this.gProject = gProject;
        
        if (gProject != null) {
            this.propertiesUrlAdapter = new PropertiesUrlAdapter(gProject.getProperties());
        } else {
            this.propertiesUrlAdapter = new PropertiesUrlAdapter(new GProperties());
        }
        
    }

    @objid ("a7477208-33f6-11e2-a514-002564c97630")
    public String getName() {
        return this.gProject.getName();
    }

    @objid ("a7479918-33f6-11e2-a514-002564c97630")
    public GProperties getProperties() {
        return this.gProject.getProperties();
    }

    @objid ("a747c026-33f6-11e2-a514-002564c97630")
    public ProjectFileStructure getProjectFileStructure() {
        return this.gProject.getPfs();
    }

    @objid ("a747c02a-33f6-11e2-a514-002564c97630")
    public List<IGModelFragment> getAllFragments() {
        return this.gProject.getParts(IGModelFragment.class);
    }

    @objid ("a747e73b-33f6-11e2-a514-002564c97630")
    public List<IGModelFragment> getSVNFragments() {
        List<IGModelFragment> svnFragments = new ArrayList<>();
        
        for (IGModelFragment fragment : getAllFragments()) {
            switch (fragment.getType()) {
            case SVNFRAGMENT:
                svnFragments.add(fragment);
                break;
            default:
                break;
            }
        }
        return svnFragments;
    }

    @objid ("a7480e4b-33f6-11e2-a514-002564c97630")
    public List<IGModelFragment> getLocalFragments() {
        List<IGModelFragment> exmlFragments = new ArrayList<>();
        
        for (IGModelFragment fragment : getAllFragments()) {
            switch (fragment.getType()) {
            case EXMLFRAGMENT:
                exmlFragments.add(fragment);
                break;
            default:
                break;
            }
        }
        return exmlFragments;
    }

    @objid ("a748355a-33f6-11e2-a514-002564c97630")
    public List<IGModelFragment> getDistantLibraryFragments() {
        List<IGModelFragment> urlFragments = new ArrayList<>();
        
        for (IGModelFragment fragment : getAllFragments()) {
            switch (fragment.getType()) {
            case HTTPFRAGMENT:
                urlFragments.add(fragment);
                break;
            default:
                break;
            }
        }
        return urlFragments;
    }

    @objid ("a7485c6b-33f6-11e2-a514-002564c97630")
    public List<IGModelFragment> getLocalLibraryFragments() {
        List<IGModelFragment> ramcFragments = new ArrayList<>();
        
        List<IGModelFragment> allFragments = getAllFragments();
        List<String> toSkip = new ArrayList<>();
        toSkip.add("PredefinedTypes");
        toSkip.addAll(getModules()
                .stream()
                .filter(f -> f.getType() == GProjectPartType.MODULE)
                .map(f -> f.getId())
                .collect(Collectors.toList()));
        for (IGModelFragment fragment : allFragments) {
            switch (fragment.getType()) {
            case RAMC:
                if (!toSkip.contains(fragment.getId())) {
                    ramcFragments.add(fragment);
                }
                break;
            default:
                break;
            }
        }
        return ramcFragments;
    }

    @objid ("a748837c-33f6-11e2-a514-002564c97630")
    public List<GModule> getModules() {
        return this.gProject.getParts(GModule.class);
    }

    @objid ("a748aa8d-33f6-11e2-a514-002564c97630")
    public IGProject getOpenedProject() {
        return this.gProject;
    }

    @objid ("4c149cf6-9e40-4b85-a3b1-80d9b09358f6")
    public List<UrlEntry> getUrls() {
        return this.propertiesUrlAdapter.getUrls();
    }

    @objid ("d7df3c48-0c12-4a5d-b809-d7ad32757af7")
    public void addUrl(UrlEntry newEntry) {
        this.propertiesUrlAdapter.addUrlEntry(newEntry);
        if (this.gProject != null) {
            try {
                this.gProject.save(new NullProgress());
            } catch (IOException e) {
                AppProjectConf.LOG.error(e);
            }
        }
        
    }

    @objid ("cfc16cbc-eb8d-41c9-bcf0-f00f67f09d0e")
    public void removeUrl(UrlEntry entry) {
        this.propertiesUrlAdapter.removeUrlEntry(entry);
        if (this.gProject != null) {
            try {
                this.gProject.save(new NullProgress());
            } catch (IOException e) {
                AppProjectConf.LOG.error(e);
            }
        }
        
    }

    @objid ("242c69dd-5b9a-4553-8ccb-388352cf6271")
    public boolean isLocalProject() {
        return this.gProject.getType() == ProjectType.LOCAL;
    }

    @objid ("f99e5007-1dad-40b0-8c74-042279ddbbfa")
    public List<String> getFragmentIdList() {
        List<String> fragmentIds = new ArrayList<>();
        List<IGModelFragment> fragments = getAllFragments();
        for (IGModelFragment fragment : fragments) {
            fragmentIds.add(fragment.getId());
        }
        return fragmentIds;
    }

    /**
     * All the fragments except MdaFragment
     * @return
     */
    @objid ("479ee3d8-103c-4c25-9560-d2b8b45a9697")
    public List<IGModelFragment> getModels() {
        List<IGModelFragment> models = new ArrayList<>();
        
        for (IGModelFragment fragment : getAllFragments()) {
            for (MObject root : fragment.getRoots()) {
                if (!(root instanceof ModuleComponent)) {
                    models.add(fragment);
                    break;
                }
            }
        }
        return models;
    }

}
