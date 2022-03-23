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
package org.modelio.vcore.model.spi.mm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Migration chain resolution result.
 * @author cma
 * @since 3.7.1
 */
@objid ("4bca90d3-77f9-45e5-b807-e11852d71770")
public class MigrationChain {
    @objid ("5a833874-d984-49bd-970b-ce1bf347babf")
    private final boolean successful;

    @objid ("68e26f96-934e-498b-93a8-6a9e4297d970")
    private final List<IMofRepositoryMigrator> steps;

    @objid ("074a338f-1513-4af8-b5a1-ed5c1e856458")
    public  MigrationChain(Collection<IMofRepositoryMigrator> chain, boolean successful) {
        this.successful = successful;
        this.steps = Collections.unmodifiableList(new ArrayList<>(chain));
        
    }

    /**
     * Tells whether the resolution is successful.
     * @return whether the resolution is successful.
     */
    @objid ("fff75bc0-eb0c-48d7-9b12-9888d97b376f")
    public boolean isSuccessful() {
        return this.successful;
    }

    @objid ("96ce18e5-9e7c-48b1-8d50-bd18b3464599")
    public List<IMofRepositoryMigrator> getSteps() {
        return this.steps;
    }

    /**
     * Tells whether the migration chain is a valid migration chain that does not modify the model.
     * <p>
     * This case usually means that source and target metamodel are compatible and only version metadatas need to be updated.
     * @return true if the repository don't need to be modified.
     */
    @objid ("3d14aa4e-79d4-4412-8b22-6319e202a254")
    public boolean isNoopMigrationChain() {
        if (! isSuccessful()) {
            return false;
        }
        
        for (IMofRepositoryMigrator m : this.steps) {
            if (m.doesModifyRepository()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return a copy of this migration chain with one migrator appended to the steps.
     * @param migrator the migrator to append
     * @return a copy of this migration chain with one migrator appended.
     */
    @objid ("5e55840e-8ca3-4f54-8dfa-b3428142d475")
    public MigrationChain add(IMofRepositoryMigrator migrator) {
        Collection<IMofRepositoryMigrator> newChain = new ArrayList<>(this.steps);
        newChain.add(migrator);
        return new MigrationChain(newChain, isSuccessful());
    }

}
