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

package org.modelio.audit.engine.core;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f54b11ef-3906-40bd-bc09-dd5d78e380a3")
public interface IAuditExecutionPlan {
    @objid ("a42a9c85-34a4-4073-bda9-927330d7c514")
    List<IRule> getRules(String metaclass, int trigger);

    @objid ("eb7c83c0-d2b1-46fd-a904-09d1f7db3b0a")
    IRule getRuleById(String ruleId);

    @objid ("968f31c1-36c8-4666-b314-175829099015")
    Collection<IRule> getAllRules();

    @objid ("b534eb67-ddaa-4867-89b6-7a96ae08dae2")
    void disableRule(IRule rule);

    @objid ("b16595a2-f678-4826-ad25-b24f67b06292")
    final class AuditTrigger {
        @objid ("aefdcfab-7a65-4544-af6a-a7611c122622")
        public static final int CREATE = 1;

        @objid ("676706a0-833d-466e-bf4b-5d3590737fa5")
        public static final int UPDATE = 2;

        @objid ("1d26ab4b-d201-467f-90ab-c3daab4d6a41")
        public static final int DELETE = 4;

        @objid ("79f7ebe8-6796-48e1-b399-010048f630ff")
        public static final int MOVE = 8;

        @objid ("901af8bb-8500-47e6-a3ae-9b268bfeb5de")
        public static final int ALL = AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.DELETE | AuditTrigger.MOVE;

        @objid ("e1703f89-6715-4571-97ed-20b44e99d91b")
        public static boolean isCreate(int value) {
            return (value & AuditTrigger.CREATE) == AuditTrigger.CREATE;
        }

        @objid ("f7d42ab5-ebb1-4316-b9d4-a879f9ea9661")
        public static boolean isUpdate(int value) {
            return (value & AuditTrigger.UPDATE) == AuditTrigger.UPDATE;
        }

        @objid ("bee4522a-b293-4010-9b09-885255294679")
        public static boolean isMove(int value) {
            return (value & AuditTrigger.MOVE) == AuditTrigger.MOVE;
        }

        @objid ("9be7f225-1bb5-4729-bd07-ae4a255a6efe")
        public static boolean isDelete(final int value) {
            return (value & AuditTrigger.DELETE) == AuditTrigger.DELETE;
        }

        /**
         * Make default constructor private, this is a pure utility class.
         */
        @objid ("ae8c6527-4c81-49cf-af2b-b1617314cbb6")
        private AuditTrigger() {
            super();
        }

    }

}
