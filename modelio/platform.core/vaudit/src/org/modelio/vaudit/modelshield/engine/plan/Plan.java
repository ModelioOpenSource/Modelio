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

package org.modelio.vaudit.modelshield.engine.plan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

@objid ("002b895a-e462-1f61-8473-001ec947cd2a")
public class Plan implements IModelShieldRegistry, IModelShieldPlan {
    @objid ("004415f6-f904-1f61-8473-001ec947cd2a")
    private Map<MClass, MetaclassPlan> metaclassPlans = new HashMap<>();

    @objid ("004441f2-f904-1f61-8473-001ec947cd2a")
    @Override
    public void registerChecker(final IChecker checker, final MClass mc, final TriggerType trigger, final String feature) {
        MetaclassPlan mcPlan = getMetaclassPlan(mc);
        
        // register the rule for the given metaclass (if not abstract)...
        if (!mc.isAbstract()) {
            mcPlan.registerRule(checker, trigger, feature);
        }
        
        // ... and for all its subclasses
        for (MClass sc : mc.getSub(false)) {
            registerChecker(checker, sc, trigger, feature);
        }
    }

    @objid ("00446bf0-f904-1f61-8473-001ec947cd2a")
    private MetaclassPlan getMetaclassPlan(final MClass mc) {
        MetaclassPlan mcPlan = this.metaclassPlans.get(mc);
        if (mcPlan == null) {
            mcPlan = new MetaclassPlan();
            this.metaclassPlans.put(mc, mcPlan);
        }
        return mcPlan;
    }

    @objid ("00448c20-f904-1f61-8473-001ec947cd2a")
    @Override
    public Collection<IChecker> getApplicableCheckers(final TriggerType trigger, final MObject obj, final String feature) {
        MetaclassPlan metaclassPlan = getMetaclassPlan(obj.getMClass());
        switch (trigger) {
        case Create:
            return metaclassPlan.getCreateCheckers();
        case DeleteTrigger:
            return metaclassPlan.getDeleteCheckers();
        case Move:
            return metaclassPlan.getMoveCheckers();
        case ReorderTrigger:
            return metaclassPlan.getReorderCheckers();
        case Update:
            return metaclassPlan.getUpdateCheckers(feature);
        case AnyTrigger:
            return metaclassPlan.getAllCheckers();
        default:
            assert (false);
            return metaclassPlan.getAllCheckers();
        }
    }

    @objid ("0044c6fe-f904-1f61-8473-001ec947cd2a")
    static class MetaclassPlan {
        @objid ("0060b74c-15bf-1f6a-b3fb-001ec947cd2a")
        private final List<IChecker> createCheckers = new ArrayList<>();

        @objid ("00610bc0-15bf-1f6a-b3fb-001ec947cd2a")
        private final List<IChecker> deleteCheckers = new ArrayList<>();

        @objid ("006157ce-15bf-1f6a-b3fb-001ec947cd2a")
        private final List<IChecker> moveCheckers = new ArrayList<>();

        @objid ("0061a454-15bf-1f6a-b3fb-001ec947cd2a")
        private final List<IChecker> reorderCheckers = new ArrayList<>();

        @objid ("00453008-f904-1f61-8473-001ec947cd2a")
        private final Map<String, List<IChecker>> updateCheckers = new HashMap<>();

        @objid ("b1775f3e-87b2-49a0-bcf1-005749b756d2")
        private final List<IChecker> allCheckers = new ArrayList<>();

        @objid ("0045593e-f904-1f61-8473-001ec947cd2a")
        public void registerRule(final IChecker checker, final TriggerType trigger, final String feature) {
            this.allCheckers.add(checker);
            
            switch (trigger) {
            case Create:
                this.createCheckers.add(checker);
                break;
            case DeleteTrigger:
                this.deleteCheckers.add(checker);
                break;
            case Move:
                this.moveCheckers.add(checker);
                break;
            case ReorderTrigger:
                this.reorderCheckers.add(checker);
                break;
            case Update:
                String key = feature;
                List<IChecker> l = this.updateCheckers.get(key);
                if (l == null) {
                    l = new ArrayList<>();
                    this.updateCheckers.put(key, l);
                }
                l.add(checker);
                break;
            case AnyTrigger:
                key = feature;
                l = this.updateCheckers.get(key);
                if (l == null) {
                    l = new ArrayList<>();
                    this.updateCheckers.put(key, l);
                }
                l.add(checker);
                this.createCheckers.add(checker);
                this.deleteCheckers.add(checker);
                this.moveCheckers.add(checker);
                this.reorderCheckers.add(checker);
                break;
            default:
                // Do nothing, we should not be here.
            }
        }

        @objid ("000d7834-0abf-1f62-8473-001ec947cd2a")
        public Collection<IChecker> getCreateCheckers() {
            return this.createCheckers;
        }

        @objid ("000d9fee-0abf-1f62-8473-001ec947cd2a")
        public List<IChecker> getDeleteCheckers() {
            return this.deleteCheckers;
        }

        @objid ("000dc776-0abf-1f62-8473-001ec947cd2a")
        public List<IChecker> getMoveCheckers() {
            return this.moveCheckers;
        }

        @objid ("000def30-0abf-1f62-8473-001ec947cd2a")
        public List<IChecker> getReorderCheckers() {
            return this.reorderCheckers;
        }

        @objid ("000e171c-0abf-1f62-8473-001ec947cd2a")
        public List<IChecker> getUpdateCheckers(final String feature) {
            assert (feature != null && !feature.isEmpty()) : "Called with null or emtpy feature string!";
            if (this.updateCheckers.get(feature) == null) {
                return Collections.emptyList();
            } else {
                return this.updateCheckers.get(feature);
            }
        }

        @objid ("33e89de6-e980-423b-9789-e851b6c8592b")
        public List<IChecker> getAllCheckers() {
            return this.allCheckers;
        }

    }

}
