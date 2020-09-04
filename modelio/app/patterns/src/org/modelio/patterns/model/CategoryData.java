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

package org.modelio.patterns.model;

import java.net.URL;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.patterns.model.information.Category;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.patterns.utils.ImageRegistry;

@objid ("37d0b927-5499-4ffa-9578-29e64b7eb1b4")
public class CategoryData implements Comparable<CategoryData> {
    /**
     * Category metadatas, loaded with Jaxb.
     */
    @objid ("b02f7c71-40be-47a6-a48a-32954dde77aa")
    private final Category infos;

    /**
     * Patterns in this category
     */
    @objid ("21b2d094-9fdc-4664-af30-1e3f7a23be34")
    private Set<RuntimePattern> patterns = new TreeSet<>();

    @objid ("e26dba75-4795-4b30-bdc4-685b5d7a38f8")
    public CategoryData(Category category) {
        this.infos = category;
    }

    @objid ("ef0c56a5-4708-4ab4-9a80-7c6ab65d1e07")
    public void addPattern(RuntimePattern pattern) {
        this.patterns.add(pattern);
    }

    @objid ("ec6edb40-0528-4298-a0d6-38356c1af0d3")
    @Override
    public int compareTo(CategoryData t2) {
        String nameT1 = this.infos.getName();
        String nameT2 = t2.infos.getName();
        return nameT1.compareTo(nameT2);
    }

    @objid ("fe5ff332-60a5-4dff-b8c7-f906e4689d6c")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }
        CategoryData other = (CategoryData) obj;
        if (this.infos == null) {
            if (other.infos != null) {
                return false;
            }
        } else if (!this.infos.getName().equals(other.infos.getName())) {
            return false;
        }
        return true;
    }

    /**
     * @return the category icon image
     */
    @objid ("37e0fee2-1458-41e2-bad6-fb290dd4b30f")
    public Image getIcon() {
        return ImageRegistry.getImage("icons/directory.png");
    }

    /**
     * @return the category icon URL
     */
    @objid ("25dec2d7-3799-425d-a36b-316e078c89c4")
    public URL getIconUrl() {
        return Patterns.getContext().getBundle().getEntry("/icons/directory.png");
    }

    @objid ("6a5eb856-480c-4dbe-86e4-15c409d931d6")
    public String getName() {
        String name = this.infos.getName();
        if (name == null || name.equals("")) {
            name = "Pattern";
        }
        return name;
    }

    @objid ("6650ee27-d9bc-40ef-bb12-2e61a3f2dd64")
    public Collection<RuntimePattern> getPatterns() {
        return this.patterns;
    }

    @objid ("382280e4-62ca-40cf-8690-a54de7f7edb3")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.infos == null) ? 0 : this.infos.getName().hashCode());
        return result;
    }

    @objid ("0f7150bb-1e19-4cdf-ad4c-688aaf50db24")
    public void removePattern(RuntimePattern pattern) {
        this.patterns.remove(pattern);
    }

}
