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
package org.modelio.audit.preferences.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Audit rule category model.
 */
@objid ("5dee0e78-97c3-42cb-9aaf-fb8a86256294")
public class AuditCategory implements Comparable<AuditCategory> {
    @objid ("512f62a8-ea8a-4300-b313-8194a6a5e726")
    private String id;

    @objid ("a836f660-1c6c-403c-8aa7-9293ee4ced33")
    private Set<AuditRule> rules = new TreeSet<>(new Comparator<AuditRule>() {
    		@Override
    		public int compare(AuditRule o1, AuditRule o2) {
    			return o1.getId().compareTo(o2.getId());
    		}
    	});

    /**
     * Initialize a category.
     * @param id the category's identifier.
     */
    @objid ("dd86bc9d-fcea-431c-8d95-a8ff8ae67480")
    public  AuditCategory(String id) {
        this.id = id;
    }

    /**
     * Add a rule.
     * @param rulePref a rule
     */
    @objid ("8b55405a-7b63-4fe8-beda-96d41cbb0aa0")
    public void add(AuditRule rulePref) {
        this.rules.add(rulePref);
    }

    /**
     * Get the category rules.
     * @return the category rules.
     */
    @objid ("9102932c-a9bc-43da-84c5-912b1cd6b158")
    public List<AuditRule> getRules() {
        // Compatibility: return a list instead of a set
        return new ArrayList<>(this.rules);
    }

    /**
     * Remove a rule
     * @param rulePref a rule
     */
    @objid ("3d045178-c06c-4bb3-957a-e96b894c7805")
    public void remove(AuditRule rulePref) {
        this.rules.remove(rulePref);
    }

    /**
     * @return the category's identifier.
     */
    @objid ("b1bee5eb-94c5-45d2-92ec-648e11b020fb")
    public String getId() {
        return this.id;
    }

    @objid ("8f85bd41-b824-42f0-96e7-45c80c11c743")
    @Override
    public int compareTo(AuditCategory o) {
        return this.id.compareTo(o.id);
    }

    @objid ("0052ede4-8454-4527-aa73-47ae80074acc")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.rules == null) ? 0 : this.rules.hashCode());
        return result;
    }

    @objid ("ce327c35-e78a-40e5-8c53-ab28bebf8b09")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AuditCategory other = (AuditCategory) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.rules == null) {
            if (other.rules != null) {
                return false;
            }
        } else if (!this.rules.equals(other.rules)) {
            return false;
        }
        return true;
    }

}
