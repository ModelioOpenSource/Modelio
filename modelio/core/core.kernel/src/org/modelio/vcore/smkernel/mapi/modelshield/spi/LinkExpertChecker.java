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

package org.modelio.vcore.smkernel.mapi.modelshield.spi;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * Checker for link metaclasses that checks source and targets are valid from the
 * metamodel expert.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("e2609f2a-a5ca-46fb-b679-e09269e0597b")
public class LinkExpertChecker implements IChecker {
    @objid ("245e847f-09f9-4e48-a473-4f72de0ac87b")
    private final String ruleId;

    @objid ("4514f123-9383-47ad-b43a-ae3bd8e4d7cb")
    private final Class<? extends MObject> linkClass;

    @objid ("7c883e53-b5d7-4359-9bb6-82a97b188443")
    private MExpert mExpert;

    @objid ("2a922882-1c02-407e-80ea-582370773c6b")
    @Override
    public void check(MObject object, final IErrorReport report) {
        MObject src = this.mExpert.getSource(object);
        MObject target = this.mExpert.getTarget(object);
        
        boolean ok = this.mExpert.canLink(object.getMClass(), src, target);
        if (! ok) {
            IModelError anEntry = new ModelError(this.ruleId, object, Arrays.<Object>asList(src, target));
            report.addEntry(anEntry);
        }
    }

    @objid ("2f6715ff-3f2f-4752-bf56-cb25f279f35f")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        MClass cls = smMetamodel.getMClass(this.linkClass);
        
        if (cls == null)
            throw new IllegalArgumentException(this.linkClass.toString());
        if (! cls.isLinkMetaclass())
            throw new IllegalArgumentException(this.linkClass.toString()+" is not a link metaclass");
        
        
        this.mExpert = smMetamodel.getMExpert();
        
        plan.registerChecker(this, cls, TriggerType.Create, null);
        for (MDependency dep : cls.getLinkMetaclassSources()) {
            plan.registerChecker(this, cls, TriggerType.Update, dep.getName());
        }
        for (MDependency dep : cls.getLinkMetaclassTargets()) {
            plan.registerChecker(this, cls, TriggerType.Update, dep.getName());
        }
    }

    /**
     * @param ruleId the rule id
     * @param linkClass the java interface of the link metaclass.
     */
    @objid ("ad7cb240-f9bd-4c4d-bc5e-140bd8dab595")
    public LinkExpertChecker(String ruleId, Class<? extends MObject> linkClass) {
        this.ruleId = ruleId;
        this.linkClass = linkClass;
    }

}
