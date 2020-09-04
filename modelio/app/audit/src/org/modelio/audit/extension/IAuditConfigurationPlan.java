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

package org.modelio.audit.extension;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.audit.preferences.model.AuditCategory;
import org.modelio.audit.preferences.model.AuditRule;

@objid ("60bb6d86-1da1-496d-95ff-04210a7fda19")
public interface IAuditConfigurationPlan {
    @objid ("140dd916-bbf8-4695-a276-bad33c7d3a07")
    public static final String RULE_PREFIX = "$";

    @objid ("e9058c9a-1739-4f5c-b3d1-4f2b67638c8b")
    public static final String CATEGORY_PREFIX = "$AuditCategory.";

    @objid ("04c06b58-e26b-453a-8466-deed60a4b0e7")
    List<AuditCategory> getRootCategories();

    @objid ("7931713e-f5c3-4d41-9648-b36c9de7a3c9")
    String getLabel(AuditCategory category);

    @objid ("9f467b31-0893-444c-a7fc-94cd5c4542ea")
    String getDescription(AuditRule rule);

    @objid ("7457dcd5-f0d1-4b8d-8696-7b9c83967726")
    String getMessage(String ruleId);

    @objid ("90705592-c4c9-4078-91a2-0577c00bd6bf")
    String getLabel(AuditRule element);

    @objid ("1a30836c-5edf-4cc7-95db-5dd3b0bca846")
    Image getImage(AuditCategory category);

}
