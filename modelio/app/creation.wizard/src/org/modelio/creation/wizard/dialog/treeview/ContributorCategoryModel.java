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

package org.modelio.creation.wizard.dialog.treeview;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.creation.wizard.plugin.CreationWizard;
import org.osgi.framework.Bundle;

/**
 * ContributorCategoryModel
 * It creates the collection of {@link Category}.
 */
@objid ("1e6dc0be-42ce-4aa6-a6c4-707ddde19c79")
public class ContributorCategoryModel {
    @objid ("6ecfdc39-96b1-49fb-9960-d89518d70792")
    private List<Category> categories;

    @objid ("dad78e11-8ad5-4072-b6c1-78a0cf639555")
    private static final Image DEFAULT_DIAGRAM_IMAGE = ContributorCategoryModel.getCategoryIcon("icons/diagram.png");

    @objid ("8d451f35-4718-4f48-8049-b2c79fe4b0d9")
    private static final Image DEFAULT_MATRIX_IMAGE = ContributorCategoryModel.getCategoryIcon("icons/matrix.png");

    @objid ("d151760b-f7b5-4c6b-accf-971100c3416b")
    public ContributorCategoryModel(Map<ContributorCategory, List<IWizardContributor>> contributorsMap) {
        super();
        this.categories = createCategories(contributorsMap);
    }

    @objid ("3a322dc8-4d3d-4531-8376-3e57387ffa17")
    private List<Category> createCategories(Map<ContributorCategory, List<IWizardContributor>> contributorsMap) {
        this.categories = new ArrayList<>();
        for (Map.Entry<ContributorCategory, List<IWizardContributor>> entry : contributorsMap.entrySet()) {
            ContributorCategory key = entry.getKey();
        
            // Build a new category, or reuse an existing one having the same label
            Category category = getCategoryByLabel(this.categories, key.getLabel());
            if (category == null) {
                category = new Category(key.getLabel(), key.getIcon());
                this.categories.add(category);
            }
        
            category.getContributors().addAll(entry.getValue());
        }
        return this.categories;
    }

    @objid ("5e880ce9-37fd-4bee-ae83-20455b0e3beb")
    public List<Category> getCategories() {
        return this.categories;
    }

    @objid ("b534b8f2-cbc8-4573-af00-86209ff21105")
    private Category getCategoryOfContributor(IWizardContributor contributor) {
        for (Category category : getCategories()) {
            if (category.getContributors().contains(contributor)) {
                return category;
            }
        }
        return null;
    }

    @objid ("381ae938-02af-4034-aa98-d8d590b8b8f7")
    private static Image getCategoryIcon(String iconPathString) {
        Image icon = null;
        if (iconPathString != null) {
            Bundle bundle = CreationWizard.getContext().getBundle();
            IPath iconPath = new Path(iconPathString);
            URL iconUrl = FileLocator.find(bundle, iconPath, null);
            icon = (ImageDescriptor.createFromURL(iconUrl)).createImage();
        }
        return icon;
    }

    @objid ("ed8c9ca2-f3c5-4554-ae3e-73e65e50fabd")
    private Category getCategoryByLabel(List<Category> categoryList, String label) {
        for (Category category : categoryList) {
            if (category.getLabel().equals(label)) {
                return category;
            }
        }
        return null;
    }

}
