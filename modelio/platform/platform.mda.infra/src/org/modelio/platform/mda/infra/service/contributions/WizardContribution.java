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

package org.modelio.platform.mda.infra.service.contributions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.module.contributor.IWizardContributor;

@objid ("63d681f2-3aad-4278-aa51-cb8ffc0703b4")
public class WizardContribution {
    @objid ("1354e76a-977d-4e90-873d-5d9e04e0eb3c")
    private IWizardContributor contributor;

    @objid ("e7eaf5b5-cb59-4f7c-b197-9337edaeb3d8")
    private ContributorCategory category;

    @objid ("a41cc25f-b5bc-4a36-89a5-bfa0f557e37f")
    public WizardContribution(ContributorCategory category, IWizardContributor contributor) {
        this.category = category;
        this.contributor = contributor;
    }

    @objid ("7ba39f70-3da4-4f88-810e-ebc3241f76d3")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.category == null) ? 0 : this.category.hashCode());
        result = prime * result + ((this.contributor == null) ? 0 : this.contributor.hashCode());
        return result;
    }

    @objid ("a1c13ee5-2ce5-4a04-a3b9-a1497e5b1019")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WizardContribution other = (WizardContribution) obj;
        if (this.category == null) {
            if (other.category != null)
                return false;
        } else if (!this.category.equals(other.category))
            return false;
        if (this.contributor == null) {
            if (other.contributor != null)
                return false;
        } else if (!this.contributor.equals(other.contributor))
            return false;
        return true;
    }

    @objid ("7e21cb18-73b3-442d-80d6-e2f3b3b5f124")
    public IWizardContributor getContributor() {
        return this.contributor;
    }

    @objid ("7f356fba-23e3-4429-996b-d425b7d8bb02")
    public ContributorCategory getCategory() {
        return this.category;
    }

}
