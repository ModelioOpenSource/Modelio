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

package org.modelio.vcore.model.spi.mm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * Computes a chain of migrator to reach a target metamodel.
 * @author cma
 * @since 3.6
 */
@objid ("9f698862-3647-481d-b2a2-24214e504dba")
public class MigrationChainResolver {
    @objid ("d5f1cc53-184c-4224-9785-7728404fe340")
    private Collection<IMigrationProvider> initCandidates;

    @objid ("bbb3bce8-340b-4456-b5e9-e693ed104087")
    private Deque<IMofRepositoryMigrator> chain;

    @objid ("158c7473-2943-423f-9914-9242066e21ae")
    private final MetamodelVersionDescriptor to;

    @objid ("9916ad18-262c-44c5-9b76-1a224a79cbc8")
    private final MetamodelVersionDescriptor from;

    /**
     * @param from initial metamodel
     * @param to target metamodel
     * @param candidates migration candidates
     */
    @objid ("717ef400-ef95-4f68-9480-dec549059849")
    public MigrationChainResolver(MetamodelVersionDescriptor from, MetamodelVersionDescriptor to, Collection<IMigrationProvider> candidates) {
        this.from = from.unmodifiable();
        this.to = to;
        this.initCandidates = new ArrayList<>(candidates);
    }

    /**
     * Look for a chain of migrators .
     * @return the migration chain, never <i>null</i>.
     */
    @objid ("ab036867-2ddd-4e17-997f-d59e45ea1d95")
    public MigrationChain run() {
        this.chain = new ArrayDeque<>();
        if (step(this.from, this.initCandidates)) {
            return new MigrationChain(this.chain, true);
        } else {
            return new MigrationChain(this.chain, false);
        }
    }

    @objid ("5ff1d67d-2f93-4aaa-8dfd-e5d0b1622039")
    private boolean step(MetamodelVersionDescriptor stateMm, Collection<IMigrationProvider> candidates) {
        if (this.chain.size() > 20) {
            String msg = this.chain.stream()
                    .map(IMofRepositoryMigrator::toString)
                    .collect(Collectors.joining("\n  - ", "Cycle in migration chain:\n  - ", ""));
            throw new RuntimeException(msg);
        }
        
        for (IMigrationProvider candidate : candidates) {
            IMofRepositoryMigrator stepMigrator = candidate.getMigrator(stateMm, this.to);
            if (stepMigrator != null) {
                MetamodelVersionDescriptor stepResultMm = stepMigrator.getTargetMetamodel();
                
                if (MmVersionComparator.withSource(stepResultMm).withTarget(this.to).withMissingRemoved().isTargetCompatible(false)) {
                    // reached target metamodel, finished
                    this.chain.addLast(stepMigrator);
                    return true;
                } 
                if (! stepResultMm.equals(stateMm)) {
                    // progressed toward target metamodel
                    this.chain.addLast(stepMigrator);
        
                    // look for next chain
                    if (step(stepResultMm, candidates)) {
                        // finished
                        return true;
                    } else {
                        // drop branch and try next
                        this.chain.removeLast();
                    }
                }
            }
            
        }
        
        if (this.chain.isEmpty()) {
            return false;
        }
        
        // A chain is found if the obtained metamodel is build compatible with the target one.
        MetamodelVersionDescriptor targetMetamodel = this.chain.getLast().getTargetMetamodel();
        return MmVersionComparator
                                .withSource(targetMetamodel)
                                .withTarget(this.to)
                                .withMissingRemoved()
                                .isTargetCompatible(true);
    }

    /**
     * Look for a chain of migrator from a source toward a target metamodel.
     * @param from the source metamodel
     * @param to the target metamodel
     * @param candidates Candidate migration tools.
     * @return a potentially empty chain of migration tools , never <i>null</i>.
     */
    @objid ("78eb048f-5154-4c59-9fa3-52b95cf72541")
    public static MigrationChain resolve(MetamodelVersionDescriptor from, MetamodelVersionDescriptor to, Collection<IMigrationProvider> candidates) {
        return new MigrationChainResolver(from, to, candidates).run();
    }

    @objid ("a071f2ff-a1c0-4ed5-a63c-e4a265dfc256")
    @Override
    public String toString() {
        return String.format("MigrationChainResolver [from=%s, to=%s]", this.from, this.to);
    }

}
