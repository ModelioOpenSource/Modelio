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

package org.modelio.creation.wizard.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("fc9aff98-780c-4955-af66-dc1d17406ef1")
public class CreationWizardModel {
    @objid ("5c03561e-09d2-46d8-b526-129c1bc223da")
    private String name = "";

    @objid ("5028271e-b5e0-4e7b-a00d-a6619d36559a")
    private String description = "";

    @objid ("4ba33db8-3dd8-4365-af97-45d8c4a90ede")
    private boolean showInvalid = false;

    @objid ("3abd44b1-9f25-4799-9a79-aaf5b8f52c70")
    private IWizardContributor selectedContributor = null;

    @objid ("ebeb72cb-e27c-450f-8cc6-4dcff16df918")
    private ModelElement context = null;

    /**
     * The filter specified which kind of 'contributions' are used. If null any kind is accepted.
     */
    @objid ("3de28edd-763a-41b4-8427-038d15ffe36c")
    private java.lang.Class<? extends IWizardContributor> typeFilter = null;

    @objid ("b3f79e3f-0445-4ec3-8f8c-f99e2dfd0f02")
    private Map<ContributorCategory, List<IWizardContributor>> contributors;

    @objid ("c7aa7897-ec4a-40f7-ad2b-66471d58ef8a")
    public String getName() {
        return this.name;
    }

    @objid ("d1c6f3c2-59fa-45f8-b8d8-f93cd4ecfcb0")
    public void setName(final String name) {
        this.name = name;
    }

    @objid ("58c486ab-c2a2-4a6e-981b-4d380451fc15")
    public String getDescription() {
        return this.description;
    }

    @objid ("d9d02d54-1b0a-4fbe-9f05-9fd9950c9420")
    public void setDescription(final String description) {
        this.description = description;
    }

    @objid ("c835f834-9968-4225-96f8-776f1402d886")
    public IWizardContributor getSelectedContributor() {
        return this.selectedContributor;
    }

    @objid ("4571141e-0fb7-40dd-b5c8-77f25d7fae2d")
    public void setSelectedContributor(final IWizardContributor selectedContributors) {
        this.selectedContributor = selectedContributors;
    }

    @objid ("47771c82-0af7-4bbe-a4e1-9e6158c295ca")
    public boolean isShowInvalid() {
        return this.showInvalid;
    }

    @objid ("e757b9d4-7fd5-4506-b13a-62d58d434286")
    public void setShowInvalid(final boolean showInvalid) {
        this.showInvalid = showInvalid;
    }

    @objid ("1b82b0ab-ef93-4f03-987e-114d6f594e1e")
    public ModelElement getContext() {
        return this.context;
    }

    @objid ("989d1188-997e-437b-acd5-bd5d57bda0ed")
    public void setContext(final ModelElement context) {
        this.context = context;
    }

    @objid ("ceb7c216-a15b-4468-aa56-df8da8196885")
    public boolean isValid() {
        return (this.selectedContributor != null && this.context != null && this.selectedContributor.accept(this.context))
                        && this.name != null && !this.name.isEmpty();
    }

    @objid ("db69ad6c-b846-4d67-8390-bb9af6167aeb")
    public java.lang.Class<? extends IWizardContributor> getTypeFilter() {
        return this.typeFilter;
    }

    @objid ("1e095b56-069d-4837-a36b-70864dce9e2d")
    public void setTypeFilter(java.lang.Class<? extends IWizardContributor> typeFilter) {
        this.typeFilter = typeFilter;
    }

    @objid ("5d1345d8-4001-45cd-bf43-8e6ee9ba853c")
    public CreationWizardModel(Map<ContributorCategory, List<IWizardContributor>> map) {
        this.contributors = map;
    }

    @objid ("dffa9d7c-d41c-4112-a84a-be5f2279198e")
    public List<IWizardContributor> getAllContributors() {
        List<IWizardContributor> allContributors = new ArrayList<>();
        for (List<IWizardContributor> cons : this.contributors.values()) {
            allContributors.addAll(cons);
        }
        return allContributors;
    }

    /**
     * Get all contributors by type (Diagram or Matrix)
     * 
     * @param filter the type of contributors specified by its Java interface
     * class. Cannot be null.
     * @param nameFilter optional name for further filtering based on strict string
     * equality (ie no regexp). Can be null in which case the name
     * filter does not apply at all.
     * @return the filtered contributors
     */
    @objid ("b663c31d-85d8-45e1-b408-ceacf22d287f")
    @SuppressWarnings("unchecked")
    public <T extends IWizardContributor> List<T> getAllContributors(java.lang.Class<T> filter, String nameFilter) {
        List<T> allContributors = new ArrayList<>();
        
        for (IWizardContributor contributor : getAllContributors()) {
            if (filter.isAssignableFrom(contributor.getClass())) {
                if (nameFilter == null || contributor.getClass().getSimpleName().equals(nameFilter)) {
                    allContributors.add((T) contributor);
                }
            }
        }
        return allContributors;
    }

    /**
     * Get all contributors by type (Diagram or Matrix)
     * 
     * @param filter the type of contributors specified by its Java interface
     * class. Cannot be null.
     * @return the filtered contributors
     */
    @objid ("db10f01e-dadb-4710-b26e-2d75c1a95a39")
    public <T extends IWizardContributor> List<T> getAllContributors(java.lang.Class<T> filter) {
        return getAllContributors(filter, null);
    }

    @objid ("f96af1cd-d215-4c7b-a8c1-ec43d3cb4667")
    public <T extends IWizardContributor> Map<ContributorCategory, List<IWizardContributor>> getContributorsMap(java.lang.Class<T> filter) {
        Map<ContributorCategory, List<IWizardContributor>> results = new HashMap<>();
        
        for (ContributorCategory category : getCategories()) {
            List<IWizardContributor> validContributors = new ArrayList<>();
        
            for (IWizardContributor candidate : this.contributors.get(category)) {
                if (filter == null || filter.isAssignableFrom(candidate.getClass())) {
                    validContributors.add(candidate);
                }
            }
        
            if (!validContributors.isEmpty()) {
                results.put(category, validContributors);
            }
        }
        return results;
    }

    /**
     * @return all contributor categories.
     */
    @objid ("047bc30e-4a43-42c4-b5fd-72fa92c6c114")
    public Set<ContributorCategory> getCategories() {
        return this.contributors.keySet();
    }

}
