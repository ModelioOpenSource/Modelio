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

package org.modelio.creation.wizard.dialog.treeview;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.module.contributor.IWizardContributor;

/**
 * Category
 * Composite with name, an icon and a list of {@link IWizardContributor}
 */
@objid ("a078b1be-09c4-4fba-8e7c-82d41c433039")
public class Category {
    @objid ("9cb8e08f-0828-4b91-a87b-70476eb70319")
    private String label;

    @objid ("9ce9d132-86bd-41fb-bb79-53f3f72e2470")
    private List<IWizardContributor> contributors = new ArrayList<>();

    @objid ("ed042def-dee7-47c8-bc10-b2aff805c720")
    private List<Category> subCategories = new ArrayList<>();

    @objid ("736b8793-0820-4a43-8664-8b2f630d4159")
    private Image icon;

    @objid ("738e2616-2783-42cd-909a-3554975497dc")
    public Category(String label, Image icon) {
        this.label = label;
        this.icon = icon;
    }

    @objid ("e270e213-d9ad-4ed8-a125-5d3448acf90c")
    public String getLabel() {
        return this.label;
    }

    @objid ("b1c55f9c-cf6b-4de2-9ad2-398d2e58d4e9")
    public List<IWizardContributor> getContributors() {
        return this.contributors;
    }

    @objid ("6b5f7e09-20f4-4024-a1cb-9bdda951e638")
    public Image getIcon() {
        return this.icon;
    }

    @objid ("cfd7ef48-05f3-4b2b-9a11-407d0990f8c5")
    public List<Category> getSubCategories() {
        return this.subCategories;
    }

    @objid ("0f2c04ac-77e4-4e15-86da-12dd0252351e")
    @Override
    public String toString() {
        return this.label;
    }

}
