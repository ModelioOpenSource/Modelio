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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E211:
 * <ul>
 * <li>desc = An AssociationEnd must be linked to an Association.</li>
 * <li>what = The ''{0}'' role is not linked to an association.</li>
 * </ul>
 * <ul>
 * <li>For navigable roles: only current source and target must be filled.
 * <li>For opposite roles: only opposite source and target must be filled.
 * <li>For associations navigable from both sides: current source must be equals to opposite target as well as current target and
 * opposite source.
 * <li>For non navigable associations: both sources must be filled, but no target
 * </ul>
 */
@objid ("0082b040-e20d-1f69-b3fb-001ec947cd2a")
public class E211Checker implements IChecker {
    @objid ("0046eb28-6455-1f6c-bf9a-001ec947cd2a")
    private static final String ERRORID = "E211";

    @objid ("00555b68-9e33-1f6c-bf9a-001ec947cd2a")
    private static final String OPPOSITE_DEP = "Opposite";

    @objid ("a5e2c493-19fc-11e2-ad19-002564c97630")
    private static final String TARGET_DEP = "Target";

    @objid ("a5e2c498-19fc-11e2-ad19-002564c97630")
    private static final String SOURCE_DEP = "Source";

    @objid ("ab0ee7d7-19fd-11e2-ad19-002564c97630")
    private static final String NAVIGABILITY_DEP = "Navigability";

    @objid ("008fd27a-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Create, null);
        
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, OPPOSITE_DEP);
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, TARGET_DEP);
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, SOURCE_DEP);
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, NAVIGABILITY_DEP);
    }

    @objid ("a5d81623-19fc-11e2-ad19-002564c97630")
    @Override
    public void check(MObject object, final IErrorReport report) {
        if (object == null) {
            return;
        }
        
        boolean ok = false;
        
        AssociationEnd currentRole = (AssociationEnd) object;
        AssociationEnd oppositeRole = currentRole.getOpposite();
        if (oppositeRole == null) {
            ok = true;
        } else {
            Classifier currentSource = currentRole.getSource();
            Classifier currentTarget = currentRole.getTarget();
            boolean currentNavigability = currentRole.isNavigable();
        
            Classifier oppositeSource = oppositeRole.getSource();
            Classifier oppositeTarget = oppositeRole.getTarget();
            boolean oppositeNavigability = oppositeRole.isNavigable();
        
            if (currentNavigability && !oppositeNavigability) { // THISSIDE
                // only current source and target must be filled
                check (report, currentSource != null, "E211.THISSIDE.SOURCE", currentRole);
                check (report, currentTarget != null, "E211.THISSIDE.TARGET", currentRole);
                check (report, oppositeSource == null, "E211.THISSIDE.OPPSOURCE", currentRole, oppositeRole, oppositeSource);
                check (report, oppositeTarget == null, "E211.THISSIDE.OPPTARGET", currentRole, oppositeRole, oppositeTarget);
                
                ok = currentSource != null && currentTarget != null && oppositeSource == null && oppositeTarget == null;
            } else if (!currentNavigability && oppositeNavigability) { //OTHERSIDE:
                // only opposite source and target must be filled
                ok = currentSource == null && currentTarget == null && oppositeSource != null && oppositeTarget != null;
                
                check (report, currentSource == null, "E211.OTHERSIDE.SOURCE", currentRole, currentSource);
                check (report, currentTarget == null, "E211.OTHERSIDE.TARGET", currentRole, currentTarget);
                check (report, oppositeSource != null, "E211.OTHERSIDE.OPPSOURCE", currentRole, oppositeRole);
                check (report, oppositeTarget != null, "E211.OTHERSIDE.OPPTARGET", currentRole, oppositeRole);
            } else if (currentNavigability && oppositeNavigability) { // BOTHSIDES:
                // current source must be equals to opposite target as well as current target and opposite source
                ok = currentSource != null && currentTarget != null && currentSource.equals(oppositeTarget) && currentTarget.equals(oppositeSource);
        
                check (report, currentSource != null, "E211.BOTHSIDES.SOURCE", currentRole);
                check (report, currentTarget != null, "E211.BOTHSIDES.TARGET", currentRole);
        
                //Source of {0} role is not same as {1} target.\n - {0} source : {2}\n - {1} target : {3}
                check (report, Objects.equals(currentSource, oppositeTarget), "E211.BOTHSIDES.SOURCE_EQ_OPPTARGET", currentRole, oppositeRole, currentSource, oppositeTarget);
                check (report, Objects.equals(currentTarget, oppositeSource), "E211.BOTHSIDES.TARGET_EQ_OPPSOURCE", currentRole, oppositeRole, currentTarget, oppositeSource);
            } else if (!currentNavigability && !oppositeNavigability) { 
                // both sources must be filled, but no target
                ok = currentSource != null && currentTarget == null && oppositeSource != null && oppositeTarget == null;
        
                check (report, currentSource != null, "E211.NONESIDE.SOURCE", currentRole);
                check (report, currentTarget == null, "E211.NONESIDE.TARGET", currentRole, currentTarget);
                check (report, oppositeSource != null, "E211.NONESIDE.OPPSOURCE", currentRole);
                check (report, oppositeTarget == null, "E211.NONESIDE.OPPTARGET", currentRole, oppositeTarget);
            }
        }
        
        if (!ok) {
            List<Object> objects = new ArrayList<>();
            objects.add(currentRole.toString());
            objects.add(String.valueOf(oppositeRole));
            report.addEntry(new ModelError(ERRORID, object, objects));
        }
    }

    @objid ("eaaf9450-dfaf-497e-873f-6153f0644724")
    private void check(final IErrorReport report, boolean expr, String errorid, MObject object, Object... linked) {
        List<Object> l = new ArrayList<>();
        // Transform to string now because objects may loose name when rollbacking transaction
        l.add(object.toString());
        for (Object o : linked)
            l.add(String.valueOf(o));
        
        if (! expr)
            report.addEntry(new ModelError(errorid, object, l));
    }

}
