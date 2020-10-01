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

package org.modelio.core.modelshield.internal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.modelshield.ModelShield;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * Execution context for a check by the {@link ModelShield}
 */
@objid ("002804f8-0000-0623-0000-000000000000")
public class ShieldContext {
    @objid ("002804f8-0000-062a-0000-000000000000")
    private final IErrorReport diagnostic;

    @objid ("910720af-1a03-4a02-917a-8976140e2c60")
    private final Set<EntryKey> alreadyApplied = new HashSet<>();

    @objid ("01f40340-0000-6eee-0000-000000000000")
    public ShieldContext(IErrorReport diagnostic) {
        this.diagnostic = diagnostic;
    }

    @objid ("002804f8-0000-066d-0000-000000000000")
    public void addDiagnosticEntry(final ModelError anEntry) {
        this.diagnostic.addEntry(anEntry);
    }

    @objid ("002804f8-0000-0677-0000-000000000000")
    public void applyChecker(final IChecker checker, final MObject obj) {
        if (this.alreadyApplied.add(getEntryIndexKey(checker, obj))) {
            doApplyChecker(checker, obj);
        }
    }

    @objid ("002804f8-0000-0673-0000-000000000000")
    private void doApplyChecker(final IChecker checker, final MObject element) {
        if (!element.isDeleted()) {
            checker.check(element, this.diagnostic);
        }
    }

    @objid ("008057a0-d6c6-1f60-8473-001ec947cd2a")
    private static EntryKey getEntryIndexKey(final IChecker checker, final MObject element) {
        return new EntryKey(checker, element);
    }

    @objid ("9f82b1ef-4de8-42c4-9bb6-82390ab9f2e1")
    private static class EntryKey {
        @objid ("d8832f78-54c1-4f8c-a70e-90ffd90759f9")
        private final IChecker checker;

        @objid ("d724e952-df8b-4863-a2a1-4ad24dee7243")
        private final MObject element;

        @objid ("59c28e8d-44b5-4cbc-8aa5-5b7b6268a455")
        public EntryKey(IChecker checker, MObject element) {
            super();
            this.checker = checker;
            this.element = element;
        }

        @objid ("4c7fe32d-a2c2-4f9a-8dc4-cdd28cf70509")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.checker == null) ? 0 : this.checker.hashCode());
            result = prime * result + ((this.element == null) ? 0 : this.element.hashCode());
            return result;
        }

        @objid ("ad424f4b-47cb-4600-84a2-b137cb87b428")
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
            EntryKey other = (EntryKey) obj;
            if (! Objects.equals(this.checker, other.checker)) {
                return false;
            }
            return Objects.equals(this.element, other.element);
        }

    }

}
