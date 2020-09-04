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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.project.conf.dialog.urls.PropertiesUrlAdapter;
import org.modelio.app.project.conf.dialog.urls.UrlEntry;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.data.project.ProjectType;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.fragment.ramcfile.MdaFragment;
import org.modelio.gproject.fragment.ramcfile.RamcFileFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.GModule;
import org.modelio.vbasic.progress.NullProgress;

@objid ("a7474af3-33f6-11e2-a514-002564c97630")
public class ProjectModel {
    @objid ("a7474af4-33f6-11e2-a514-002564c97630")
    private final GProject gProject;

    @objid ("357b55c4-0f90-4438-a772-4efeccb24fb0")
    private PropertiesUrlAdapter propertiesUrlAdapter;

    @objid ("a7477205-33f6-11e2-a514-002564c97630")
    public ProjectModel(GProject gProject) {
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
        return this.gProject.getProjectFileStructure();
    }

    @objid ("a747c02a-33f6-11e2-a514-002564c97630")
    public List<IProjectFragment> getAllFragments() {
        return this.gProject.getFragments();
    }

    @objid ("a747e73b-33f6-11e2-a514-002564c97630")
    public List<IProjectFragment> getSVNFragments() {
        List<IProjectFragment> svnFragments = new ArrayList<>();
        
        for (IProjectFragment fragment : getAllFragments()) {
            switch (fragment.getType()) {
            case EXML_SVN:
                svnFragments.add(fragment);
                break;
            default:
                break;
            }
        }
        return svnFragments;
    }

    @objid ("a7480e4b-33f6-11e2-a514-002564c97630")
    public List<IProjectFragment> getLocalFragments() {
        List<IProjectFragment> exmlFragments = new ArrayList<>();
        
        for (IProjectFragment fragment : getAllFragments()) {
            switch (fragment.getType()) {
            case EXML:
                exmlFragments.add(fragment);
                break;
            default:
                break;
            }
        }
        return exmlFragments;
    }

    @objid ("a748355a-33f6-11e2-a514-002564c97630")
    public List<IProjectFragment> getDistantLibraryFragments() {
        List<IProjectFragment> urlFragments = new ArrayList<>();
        
        for (IProjectFragment fragment : getAllFragments()) {
            switch (fragment.getType()) {
            case EXML_URL:
                urlFragments.add(fragment);
                break;
            default:
                break;
            }
        }
        return urlFragments;
    }

    @objid ("a7485c6b-33f6-11e2-a514-002564c97630")
    public List<RamcFileFragment> getLocalLibraryFragments() {
        List<RamcFileFragment> ramcFragments = new ArrayList<>();
        
        for (IProjectFragment fragment : getAllFragments()) {
            if (fragment.getType() == FragmentType.RAMC) {
                if (!(fragment instanceof MdaFragment)) {
                    if (!fragment.getId().equals("PredefinedTypes")) {
                        ramcFragments.add((RamcFileFragment) fragment);
                    }
                }
            }
        }
        return ramcFragments;
    }

    @objid ("a748837c-33f6-11e2-a514-002564c97630")
    public List<GModule> getModules() {
        return this.gProject.getModules();
    }

    @objid ("a748aa8d-33f6-11e2-a514-002564c97630")
    public GProject getOpenedProject() {
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
        List<IProjectFragment> fragments = getAllFragments();
        for (IProjectFragment fragment : fragments) {
            fragmentIds.add(fragment.getId());
        }
        return fragmentIds;
    }

    /**
     * All the fragments except MdaFragment
     * @return
     */
    @objid ("479ee3d8-103c-4c25-9560-d2b8b45a9697")
    public List<IProjectFragment> getModels() {
        List<IProjectFragment> models = new ArrayList<>();
        
        for (IProjectFragment fragment : getAllFragments()) {
            if (!(fragment instanceof MdaFragment)) {
                models.add(fragment);
            }
        }
        return models;
    }

}
