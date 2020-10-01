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

package org.modelio.creation.wizard.dialog.treeview;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * ContentProvider for the creation contributors tree viewer in the creation wizard dialog.
 */
@objid ("6384e101-a6e8-47a1-a962-953e60508053")
public class ContributorTreeContentProvider implements ITreeContentProvider {
    @objid ("c5556084-0e7f-4187-904d-dc491fafa262")
    private boolean showInvalid;

    @objid ("ee9b5c4e-0f7f-412b-9ed9-a1b424736612")
    private ModelElement context;

    @objid ("2aa75570-7cd5-455c-80ba-87b14df94708")
    private Category[] categories;

    @objid ("7d440f2a-cfa0-4d8c-bcdf-64d9a2c33a8e")
    public ContributorTreeContentProvider(ModelElement context, boolean showInvalid) {
        this.context = context;
        this.showInvalid = showInvalid;
    }

    @objid ("527b1dc3-a015-4604-8a79-04a632b3dbb3")
    @Override
    public void dispose() {
        // Nothing to do
    }

    @objid ("c27ef485-0975-44b3-bf97-e60303266428")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (newInput == null) {
            return;
        } else if (oldInput != newInput || this.categories == null) {
            ContributorCategoryModel model = (ContributorCategoryModel) newInput;
        
            if (this.showInvalid) {
                this.categories = model.getCategories().toArray(new Category[0]);
            } else {
                // In the not show invalid mode, doesn't display the category without valid contributors
                List<Category> cat = new ArrayList<>();
                for (Category category : model.getCategories()) {
                    if (getValidContributors(category).size() > 0) {
                        cat.add(category);
                    } else {
                        for (Category subCategory : category.getSubCategories()) {
                            if (getValidContributors(subCategory).size() > 0) {
                                cat.add(category);
                                break;
                            }
                        }
                    }
                }
                this.categories =  cat.toArray(new Category[0]);
            }
        }
    }

    @objid ("635b2cf2-39e8-476c-8595-caaa8c9e55dc")
    @Override
    public Object[] getElements(Object inputElement) {
        return this.categories;
    }

    @objid ("ba603fc4-bbf5-4f55-9d22-be81947bc646")
    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof Category) {
            Category category = (Category) parentElement;
            List<Object> children = new ArrayList<>();
            if (this.showInvalid) {
                children.addAll(category.getContributors());
                children.addAll(category.getSubCategories());
            } else {
                // In the not show invalid mode, display only the valid contributors
                children.addAll(getValidContributors(category));
                children.addAll(getValidSubCategories(category));
            }
            if (children.size() > 0) {
                return children.toArray();
            }
        }
        return null;
    }

    @objid ("f1086bc6-40e3-40cf-b430-34b6da4b69d8")
    @Override
    public Object getParent(Object element) {
        for (Category rootCategory : this.categories) {
            if (rootCategory.getContributors().contains(element)) {
                return rootCategory;
            }
            for (Category subCategory : rootCategory.getSubCategories()) {
                if (subCategory.equals(element)) {
                    return rootCategory;
                }
                if (subCategory.getContributors().contains(element)) {
                    return subCategory;
                }
            }
        }
        return null;
    }

    @objid ("9c79078f-ccd5-4f78-948a-eb01450bac74")
    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof Category) {
            return getChildren(element) != null;
        }
        return false;
    }

    @objid ("5f6140a5-8176-4f71-8fa4-785aa0d5dcee")
    public void setShowInvalid(boolean showInvalid) {
        if (this.showInvalid != showInvalid) {
            this.showInvalid = showInvalid;
            this.categories = null;
        }
    }

    /**
     * Get all valid contributors for the current context from the given list of contributors
     */
    @objid ("940cab91-6861-403c-95a7-2d5e2fc8a651")
    private List<IWizardContributor> getValidContributors(Category category) {
        List<IWizardContributor> result = new ArrayList<>();
        for (IWizardContributor contributor : category.getContributors()) {
            if (this.context == null || contributor.accept(this.context)) {
                result.add(contributor);
            }
        }
        return result;
    }

    @objid ("6df247cd-d3da-47b5-921e-a6d2eaa18c9c")
    public void setContext(ModelElement context) {
        this.context = context;
    }

    /**
     * Get all valid contributors for the current context from the given list of contributors
     */
    @objid ("dd84b251-19d1-42fe-b825-5aa5882b0cda")
    private List<Category> getValidSubCategories(Category category) {
        List<Category> result = new ArrayList<>();
        for (Category sub : category.getSubCategories()) {
            if (!getValidContributors(sub).isEmpty()) {
                result.add(sub);
            }
        }
        return result;
    }

}
